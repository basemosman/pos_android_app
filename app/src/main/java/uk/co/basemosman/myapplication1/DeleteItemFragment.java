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
public class DeleteItemFragment extends Fragment {

    Spinner spCategory,spItem;
    int selectCatPos = 1;

    String itemselected;
    String Categoryelected;

    int Booleanitemselected = -1;

    AlertDialog.Builder builder;

    List<DBMenuItems> itemsList;
    DBMenuItems dbMenuItems = null;
    ArrayAdapter<String> arrayAdapter;

    public DeleteItemFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_delete_item, container, false);


        spCategory = view.findViewById(R.id.spCategoryDelete);
        spCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                selectCatPos = position;
                new getItemAsyncTask().execute(position + 1);
                Categoryelected = parent.getItemAtPosition(position).toString();
                Booleanitemselected = -1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spItem = view.findViewById(R.id.spitemDelete);
        spItem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                dbMenuItems  = itemsList.get(position);
                itemselected = parent.getItemAtPosition(position).toString();
                Booleanitemselected = position;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button btnDeleted = view.findViewById(R.id.btnItemDeleted);
        btnDeleted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (dbMenuItems != null) {
                    builder = new AlertDialog.Builder(getContext());
                    builder.setTitle("Delete Item")
                            .setCancelable(true)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    new DeleteItemAsyncTask().execute(dbMenuItems);
//                                    arrayAdapter.remove(dbMenuItems.getItemName());
//                                    arrayAdapter.notifyDataSetChanged();
//                                    spItem.setAdapter(arrayAdapter);
                                    dbMenuItems = null;
                                    new getItemAsyncTask().execute(selectCatPos + 1);
                                }
                            }).setNegativeButton("No", null)
                            .setMessage("Are Sure You Want Delete ...(( " + itemselected + "))... From ...(( " + Categoryelected + " ))... Category");
                    builder.create().show();
                }else {
                    Toast.makeText(getContext(), "Select Item To Delete it" , Toast.LENGTH_LONG).show();
                }
            }
        });


        new CategroiesItems().execute();

        return view;
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

            arrayAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_list_item_1,list);
            spItem.setAdapter(arrayAdapter);
        }
    }

    private class DeleteItemAsyncTask extends  AsyncTask<DBMenuItems,Void,Void>{

        ItemsDao itemsDao = MenuItemsDataBase.getInstance(getContext()).getMenuItemsDao();

        @Override
        protected Void doInBackground(DBMenuItems... dbMenuItems) {

            itemsDao.delete(dbMenuItems[0]);
            return null;

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            Toast.makeText(getContext(),"Item Deleted ", Toast.LENGTH_LONG).show();
        }
    }

}
