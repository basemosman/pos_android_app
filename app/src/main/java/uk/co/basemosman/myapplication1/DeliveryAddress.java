package uk.co.basemosman.myapplication1;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DeliveryAddress extends AppCompatActivity implements View.OnClickListener {


    Button btnFindAddress,btnnextsell;
    EditText editFindPostcode;
    TextView AddressView;

    ProgressDialog dialog;
    LinearLayout linearLayout_address;

    String street="";
    String postcode="";
    String area="";
    String doorNumber="";


    EditText editpostcode,editstreet,editarea,editdoornumber,phonenumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_address);


        btnFindAddress = findViewById(R.id.btnFindAddress);
        btnFindAddress.setOnClickListener(this);

        btnnextsell = findViewById(R.id.btnNextSell);
        btnnextsell.setOnClickListener(this);

        editFindPostcode = findViewById(R.id.postcode);


        editdoornumber = findViewById(R.id.editdoornumber);
        editstreet = findViewById(R.id.editstreetname);
        editarea = findViewById(R.id.editarea);
        editpostcode = findViewById(R.id.editpost);
        phonenumber = findViewById(R.id.editphonenumber);




        linearLayout_address = findViewById(R.id.address_linear);
        linearLayout_address.setOnClickListener(this);

    }


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btnFindAddress:{

                String editposcode = editFindPostcode.getText().toString();

                if (!isNetworkAvailable()|| editposcode.isEmpty()){

                    Toast.makeText(DeliveryAddress.this,"You Need To Connect To Internet",Toast.LENGTH_LONG).show();
                    return;
                }

                String pcode = String.format("https://maps.googleapis.com/maps/api/geocode/json?address=%s&key=AIzaSyAQDhTWkPmRh-ksku8LwYTH_7J-5U6TQk0",editposcode);
                new GetAddress().execute(pcode);

                break;
            }case R.id.address_linear:{

                InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                in.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                break;

            }case R.id.btnNextSell:{

                if (editstreet.getText().toString().isEmpty() || editpostcode.getText().toString().isEmpty()|| editdoornumber.getText().toString().isEmpty()){

                    Toast.makeText(this,"Some Of Field Is Empty ", Toast.LENGTH_LONG).show();

                    return;
                }

                Intent intent = new Intent(DeliveryAddress.this,SellDisplay.class);
                intent.putExtra("street" , editstreet.getText().toString());
                intent.putExtra("doornumber" , editdoornumber.getText().toString());
                intent.putExtra("area" , editarea.getText().toString());
                intent.putExtra("postcode" , editpostcode.getText().toString());
                intent.putExtra("phonenumber" , phonenumber.getText().toString());


                intent.putExtra("delivery" , true);



                startActivity(intent);

                break;
            }

        }
    }


    private class GetAddress extends AsyncTask<String ,Void,String>{


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            dialog = new ProgressDialog(DeliveryAddress.this);
            dialog.setMessage("Please Wait");
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        }



        @Override
        protected String doInBackground(String... strings) {

            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {

                URL url = new URL(strings[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                    Log.d("Response: ", "> " + line);   //here u ll get whole response...... :-)
                }
                return buffer.toString();

                } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            return null;
        }



        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if (dialog.isShowing()){
                dialog.dismiss();
            }

            String status;


            try {


                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("results");

                status = jsonObject.get("status").toString();

                if (status.equals("OK")){
                    street = jsonArray.getJSONObject(0).getJSONArray("address_components").getJSONObject(1).get("long_name").toString();
                    postcode = jsonArray.getJSONObject(0).getJSONArray("address_components").getJSONObject(0).get("long_name").toString();
                    area = jsonArray.getJSONObject(0).getJSONArray("address_components").getJSONObject(2).get("long_name").toString();


                }else{

                    Toast.makeText(DeliveryAddress.this,"Can Not Connect to Data.... Try Again Later",Toast.LENGTH_LONG).show();

                }


                Log.d("street", "> " + street);


            } catch (JSONException e) {
                e.printStackTrace();
            }

            //AddressView.setText(street+", "+area+", "+postcode);


            editstreet.setText(street);
            editarea.setText(area);
            editpostcode.setText(postcode);


        }
    }
}
