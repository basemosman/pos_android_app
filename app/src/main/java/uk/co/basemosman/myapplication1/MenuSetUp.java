package uk.co.basemosman.myapplication1;

import android.content.Intent;
import android.support.v4.app.FragmentManager;;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import Database.CategroiesDao;
import Database.DBMenuCategroies;
import Database.DBMenuItems;
import Database.ItemsDao;
import Database.MenuItemsDataBase;

public class MenuSetUp extends AppCompatActivity implements View.OnClickListener {

//    Spinner spCategory;
//    EditText Edname,EdPrice;
//    boolean nameText = false;
//
//    int selectedNameAtPos;
//    String selectedCateName;

    public static FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_set_up);


      fragmentManager = getSupportFragmentManager();

      if (findViewById(R.id.fragment_Contiant_Item )!= null){
          if (savedInstanceState != null){
              return;
          }
          fragmentManager.beginTransaction().add(R.id.fragment_Contiant_Item,new AddItemMenuFragment()).commit();
        }


        Button btnadditemlayout = findViewById(R.id.additemlayout);
        btnadditemlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragmentManager.beginTransaction().replace(R.id.fragment_Contiant_Item,new AddItemMenuFragment())
                        .addToBackStack(null).commit();

            }
        });

        Button btndeleteitemlayout = findViewById(R.id.deleteitemlayout);
        btndeleteitemlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragmentManager.beginTransaction().replace(R.id.fragment_Contiant_Item,new DeleteItemFragment())
                        .addToBackStack(null).commit();

            }
        });

        Button btnupdateitemlayout = findViewById(R.id.updateitemlayout);
        btnupdateitemlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragmentManager.beginTransaction().replace(R.id.fragment_Contiant_Item,new UpdateItemFragment())
                        .addToBackStack(null).commit();

            }
        });





//        spCategory = findViewById(R.id.spCategory);
//        spCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//                selectedNameAtPos = position +1;
//                selectedCateName = parent.getItemAtPosition(position).toString();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//
//
//        Edname = findViewById(R.id.edItemName);
//        Edname.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//                if (s.length() > 20 || (s.length() <= 0 && !nameText)) {
//                    nameText = false;
//                    Toast toast = Toast.makeText(MenuSetUp.this,"Item Name Can Not Be More Than 20 Char Or Empty", Toast.LENGTH_LONG);
//                    toast.setGravity(Gravity.CENTER,0,0);
//                    toast.show();
//                } else {
//
//                    nameText = true;
//                }
//            }
//        });
//
//        EdPrice = findViewById(R.id.edItemPrice);
//
//
        LinearLayout linearLayout = findViewById(R.id.setmenuframe);
        linearLayout.setOnClickListener(this);
//
//        Button btnaddItem = findViewById(R.id.btnItemAdded);
//        btnaddItem.setOnClickListener(this);
//
//
//
//        new CategroiesItems().execute();
//        new getItemAsyncTask().execute(1);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.setmenuframe: {
                InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                in.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                break;
            }
//           case R.id.btnItemAdded:{
//
//                if (Edname.getText().toString().isEmpty() || EdPrice.getText().toString().isEmpty()){
//
//                    Toast.makeText(MenuSetUp.this,"Item Name or Item Price Field is Empty", Toast.LENGTH_LONG).show();
//
//                    return;
//                }else if (!nameText){
//                    Toast.makeText(MenuSetUp.this,"Item Name Can Not Be More Than 20 Char", Toast.LENGTH_LONG).show();
//                    return;
//                }
//
//                ItemsDao itemsDao = MenuItemsDataBase.getInstance(MenuSetUp.this).getMenuItemsDao();
//
//                DBMenuItems dbMenuItems = new DBMenuItems();
//                dbMenuItems.setItemName(Edname.getText().toString());
//                dbMenuItems.setItemPrice(EdPrice.getText().toString());
//                dbMenuItems.setCateItem_Id(selectedNameAtPos);
//
//                new InsertItemAsyncTask(itemsDao).execute(dbMenuItems);
//
//                break;
//            }

            //       }

        }
    }


//    private class CategroiesItems extends AsyncTask<Void,Void,List<DBMenuCategroies>> {
//
//
//        @Override
//        protected List<DBMenuCategroies> doInBackground(Void... voids) {
//
//            CategroiesDao categroiesDao = MenuItemsDataBase.getInstance(MenuSetUp.this).getMenuCategroiesDao();
//
//            return categroiesDao.getAllcategroies();
//        }
//
//        @Override
//        protected void onPostExecute(List<DBMenuCategroies> dbMenuCategroies) {
//            super.onPostExecute(dbMenuCategroies);
//
//            List<String> list = new ArrayList<>();
//
//            for (DBMenuCategroies d: dbMenuCategroies) {
//                list.add(d.getCateName());
//            }
//
//            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(MenuSetUp.this,android.R.layout.simple_list_item_1,list);
//            spCategory.setAdapter(arrayAdapter);
//        }
//    }
//
//    private class InsertItemAsyncTask extends  AsyncTask<DBMenuItems,Void,Void>{
//
//        private ItemsDao itemsDao;
//
//        private InsertItemAsyncTask(ItemsDao itemsDao) {
//            this.itemsDao = itemsDao;
//        }
//
//        @Override
//        protected Void doInBackground(DBMenuItems... dbMenuItems) {
//
//            itemsDao.insert(dbMenuItems[0]);
//            return null;
//
//        }
//
//        @Override
//        protected void onPostExecute(Void aVoid) {
//            super.onPostExecute(aVoid);
//
//            Toast.makeText(MenuSetUp.this,"Added " + Edname.getText().toString() + " " + EdPrice.getText().toString()
//                    + " On " + selectedCateName + " Category", Toast.LENGTH_LONG).show();
//
//            EdPrice.setText("");
//            Edname.setText("");
//        }
//    }
//
//    private class getItemAsyncTask extends  AsyncTask<Integer,Void,List<DBMenuItems>>{
//
//
//        @Override
//        protected List<DBMenuItems> doInBackground(Integer... integers) {
//
//            ItemsDao itemsDao = MenuItemsDataBase.getInstance(MenuSetUp.this).getMenuItemsDao();
//
//            return itemsDao.findItemsForOneCategroies(integers[0]);
//        }
//
//        @Override
//        protected void onPostExecute(List<DBMenuItems> dbMenuItems) {
//            super.onPostExecute(dbMenuItems);
//
//            String x = "";
//            String xx = "";
//
//            for (DBMenuItems d : dbMenuItems) {
//
//                x +=  "\n"+d.getItemName() +  d.getItemPrice();
//            }
//
//            Toast.makeText(MenuSetUp.this, x, Toast.LENGTH_LONG).show();
//        }
//    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent serverIntent = new Intent(MenuSetUp.this, Main_Activity.class);
        startActivity(serverIntent);

    }

}
