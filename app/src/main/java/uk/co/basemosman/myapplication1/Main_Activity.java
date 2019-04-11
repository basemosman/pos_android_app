package uk.co.basemosman.myapplication1;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import Database.CategroiesDao;
import Database.DBMenuCategroies;
import Database.MenuItemsDataBase;
import Database.Orders;

public class Main_Activity extends Activity implements OnClickListener{

    private static final int REQUEST_CONNECT_DEVICE = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_screen);


        Button btnDelivery =  findViewById(R.id.btndeliver);
        btnDelivery.setOnClickListener(this);

        Button btncollection = findViewById(R.id.btncollection);
        btncollection.setOnClickListener(this);


        Button btnorders = findViewById(R.id.Orders);
        btnorders.setOnClickListener(this);

        Button btnSetUp = findViewById(R.id.SetUp);
        btnSetUp.setOnClickListener(this);

      //  new CategroiesItems().execute();

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
                case R.id.btncollection: {
                    Intent serverIntent = new Intent(Main_Activity.this, SellDisplay.class);
                    startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE);
                    break;
                }

            case R.id.btndeliver:{

                Intent serverIntent = new Intent(Main_Activity.this, DeliveryAddress.class);
                startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE);

                break;
            }

            case R.id.Orders:{

                Intent serverIntent = new Intent(Main_Activity.this, Orders.class);
                startActivity(serverIntent);
                break;
            }
            case R.id.SetUp:{
                Intent serverIntent = new Intent(Main_Activity.this, MenuSetUp.class);
                startActivity(serverIntent);
                break;
            }
        }
    }
}
