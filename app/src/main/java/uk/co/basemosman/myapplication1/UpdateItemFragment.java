package uk.co.basemosman.myapplication1;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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
public class UpdateItemFragment extends Fragment {

    Spinner spCategory,spItem;
    EditText edItemName, edItemPrice;
    int selectCatPos = 1;
    String Categoryelected;
    String itemselected;
    List<DBMenuItems> itemsList;
    DBMenuItems dbMenuItems = null;
    AlertDialog.Builder builder;

    public UpdateItemFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_update_item, container, false);

        edItemName = view.findViewById(R.id.edItemNameUp);
        edItemPrice = view.findViewById(R.id.edItemPriceUp);
        spCategory = view.findViewById(R.id.spCategoryUpdate);
        spCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                selectCatPos = position;
                edItemName.setText("");
                edItemPrice.setText("");

                new getItemAsyncTask().execute(position + 1);
                Categoryelected = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spItem = view.findViewById(R.id.spItemUpdate);
        spItem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                dbMenuItems  = itemsList.get(position);

                edItemName.setText(dbMenuItems.getItemName());
                edItemPrice.setText(dbMenuItems.getItemPrice());

                itemselected = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button btnUpdate = view.findViewById(R.id.btnItemUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (dbMenuItems !=null) {
                    if (!edItemName.getText().toString().isEmpty() && !edItemPrice.getText().toString().isEmpty()) {
                        if (edItemName.getText().toString().length() < 20) {
                            builder = new AlertDialog.Builder(getContext());
                            builder.setTitle("Update Item")
                                    .setCancelable(true)
                                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            Double d = Double.valueOf(edItemPrice.getText().toString());
                                            String x = String.format("%.2f",d);

                                            dbMenuItems.setItemid(dbMenuItems.getItemid());
                                            dbMenuItems.setItemName(edItemName.getText().toString());
                                            dbMenuItems.setItemPrice(x);
                                            dbMenuItems.setCateItem_Id(dbMenuItems.getCateItem_Id());
                                            new UpdateItemAsyncTask().execute(dbMenuItems);
                                            new getItemAsyncTask().execute(selectCatPos + 1);
                                        }
                                    }).setNegativeButton("No", null)
                                    .setMessage("Are Sure You Want Update ...(( " + itemselected + "))... From ...(( " + Categoryelected + " ))... Category");
                            builder.create().show();
                        } else {
                            Toast.makeText(getContext(),"Item Name Can Not Be More Than 20 Letter",Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(getContext(),"Item Name or Price Fields Is Empty",Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getContext(),"Select Item To Update",Toast.LENGTH_LONG).show();
                }

            }
        });

       new CategroiesItemsAsyncTask().execute();

        return view;
    }

    private class CategroiesItemsAsyncTask extends AsyncTask<Void,Void,List<DBMenuCategroies>> {

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

    private class getItemAsyncTask extends AsyncTask<Integer,Void,List<DBMenuItems>> {

        @Override
        protected List<DBMenuItems> doInBackground(Integer... integers) {

            ItemsDao itemsDao = MenuItemsDataBase.getInstance(getContext()).getMenuItemsDao();

            return itemsDao.findItemsForOneCategroies(integers[0]);
        }

        @Override
        protected void onPostExecute(List<DBMenuItems> dbMenuItems) {
            super.onPostExecute(dbMenuItems);

            itemsList = dbMenuItems;
            List<String> list = new ArrayList<>();

            for (DBMenuItems d : dbMenuItems) {
                list.add(d.getItemName());
            }

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_list_item_1,list);
            spItem.setAdapter(arrayAdapter);
        }
    }

    private class UpdateItemAsyncTask extends  AsyncTask<DBMenuItems,Void,Void>{

        ItemsDao itemsDao = MenuItemsDataBase.getInstance(getContext()).getMenuItemsDao();

        @Override
        protected Void doInBackground(DBMenuItems... dbMenuItems) {

            itemsDao.update(dbMenuItems[0]);
            return null;

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            Toast.makeText(getContext(),"Item Updated", Toast.LENGTH_LONG).show();
            edItemName.setText("");
            edItemPrice.setText("");
        }
    }
}
