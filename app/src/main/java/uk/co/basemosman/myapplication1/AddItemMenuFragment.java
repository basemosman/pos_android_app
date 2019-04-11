package uk.co.basemosman.myapplication1;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class AddItemMenuFragment extends Fragment implements View.OnClickListener {


    Spinner spCategory;
    EditText Edname,EdPrice;
    boolean nameText = false;

    int selectedNameAtPos;
    String selectedCateName;

    View view;

    public AddItemMenuFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view  = inflater.inflate(R.layout.fragment_add_item, container, false);


        spCategory = view.findViewById(R.id.spCategory);
        spCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                selectedNameAtPos = position +1;
                selectedCateName = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        Edname = view.findViewById(R.id.edItemName);
        Edname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (s.length() > 20) {
                    nameText = false;
                    Toast toast = Toast.makeText(getContext(),"Item Name Can Not Be More Than 20 Char Or Empty", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();
                } else {

                    nameText = true;
                }
            }
        });

        EdPrice = view.findViewById(R.id.edItemPrice);

        Button btnaddItem = view.findViewById(R.id.btnItemAdded);
        btnaddItem.setOnClickListener(this);


        new CategroiesItems().execute();

        return view;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

           case R.id.btnItemAdded:{

                if (Edname.getText().toString().isEmpty() || EdPrice.getText().toString().isEmpty()){

                    Toast.makeText(getContext(),"Item Name or Item Price Field is Empty", Toast.LENGTH_LONG).show();

                    return;
                }else if (!nameText){
                    Toast.makeText(getContext(),"Item Name Can Not Be More Than 20 Char", Toast.LENGTH_LONG).show();
                    return;
                }

                Double d = Double.valueOf(EdPrice.getText().toString());
                String x = String.format("%.2f",d);

                ItemsDao itemsDao = MenuItemsDataBase.getInstance(getContext()).getMenuItemsDao();
                DBMenuItems dbMenuItems = new DBMenuItems();
                dbMenuItems.setItemName(Edname.getText().toString());
                dbMenuItems.setItemPrice(x);
                dbMenuItems.setCateItem_Id(selectedNameAtPos);

                new InsertItemAsyncTask(itemsDao).execute(dbMenuItems);

                break;
            }

        }
    }

    private class CategroiesItems extends AsyncTask<Void,Void,List<DBMenuCategroies>> {


        @Override
        protected List<DBMenuCategroies> doInBackground(Void... voids) {

            CategroiesDao categroiesDao = MenuItemsDataBase.getInstance(getContext()).getMenuCategroiesDao();

            return categroiesDao.getAllcategroies();
        }

        @Override
        protected void onPostExecute(List<DBMenuCategroies> dbMenuCategroies) {
            super.onPostExecute(dbMenuCategroies);

            List<String> list = new ArrayList<>();

            for (DBMenuCategroies d: dbMenuCategroies) {
                list.add(d.getCateName());
            }

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_list_item_1,list);
            spCategory.setAdapter(arrayAdapter);
        }
    }

    private class InsertItemAsyncTask extends  AsyncTask<DBMenuItems,Void,Void>{

        private ItemsDao itemsDao;

        private InsertItemAsyncTask(ItemsDao itemsDao) {
            this.itemsDao = itemsDao;
        }

        @Override
        protected Void doInBackground(DBMenuItems... dbMenuItems) {

            itemsDao.insert(dbMenuItems[0]);
            return null;

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            Toast.makeText(getContext(),"Added " + Edname.getText().toString() + " " + EdPrice.getText().toString()
                    + " On " + selectedCateName + " Category", Toast.LENGTH_LONG).show();

            EdPrice.setText("");
            Edname.setText("");
        }
    }
}
