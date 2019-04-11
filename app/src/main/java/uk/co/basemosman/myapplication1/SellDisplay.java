package uk.co.basemosman.myapplication1;


import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class SellDisplay extends AppCompatActivity implements View.OnClickListener, SubItemsMenu.SubitemMenuListener ,
        EditPriceOrder.orderPriceListener , OrderComment.orderCommentListener, SpecialItem.orderSpItemListener {
    LinearLayout linearLayout,linearLayout_menu;
    Button button , buttonCategies;
    Button btnDelete , btnExtraPrice;
    FragmentManager fragmentManager;
    MenuItems menuItems;
    ArrayList<String> catList;
    ArrayList<String> itemsList;
    TextView itemscount;
    List<Button> btn;
    ArrayList<item_row> arrayList;
    ListView listView;
    EditText customerName;
    CustomAdapter customAdapter;
    String classicsItems=null;
    Double OrderFinalTotalPrice = 0.00;
    double OrderEditPrice = 0.00;
    int [] btncolor;
    Button charge , btncomment;
    String OrderComment ="";
    Map<String,Double> MapItemsList;

    TableRow tableRowitem1,tableRowitem2,tableRowitem3,tableRowitem4,tableRowitem5,tableRowitem6,tableRowitem7;

    String selectedSubItem,sp1SelectedItem,sp2SelectedItem,sp3SelectedItem,sp4SelectedItem,sp5SelectedItem;
    String sp11SelectedItem,sp21SelectedItem,sp41SelectedItem,selectedSubItem2;

    TableRow tableRow1,tableRow2,tableRow3;

    boolean delivery=false;

    String address;

    String doornumber,street,area,postcode,phonenumber;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orders_display);


        //Set Categories List
        getItemsCategories();

        btncolor = getResources().getIntArray(R.array.btncolor);

        btn = new ArrayList<>();

        arrayList = new ArrayList<>();
        listView = findViewById(R.id.itemslistview);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                customAdapter.clearOneItem(position);

                charge.setText("TOTAL " + totalItemsPrice());
                itemscount.setTextSize(24);
                itemscount.setTypeface(null,Typeface.BOLD);

                itemscount.setText("Items ("+ arrayList.size()+")");
                itemscount.setTextSize(24);
                itemscount.setTypeface(null,Typeface.BOLD);

                return false;
            }
        });



        charge = findViewById(R.id.btncharge);
        charge.setOnClickListener(this);

        itemscount = findViewById(R.id.Tviewitems);
        customerName = findViewById(R.id.customername);


        tableRow1 = findViewById(R.id.tablerow1);
        tableRow2 = findViewById(R.id.tablerow2);
        tableRow3 = findViewById(R.id.tablerow3);




        linearLayout_menu = findViewById(R.id.linearLayout_menu);
        linearLayout_menu.setOnClickListener(this);


        fragmentManager = getSupportFragmentManager();

        if (findViewById(R.id.fragment_container) != null){

            if (savedInstanceState != null){
                return;
            }
            fragmentManager.beginTransaction().add(R.id.fragment_container,new ItemsFragment()).commit();
        }

        setButtonCategies(catList.size());


        btnDelete = findViewById(R.id.btndelete);
        btnDelete.setOnClickListener(this);

        btnExtraPrice = findViewById(R.id.btnextraprice);
        btnExtraPrice.setOnClickListener(this);

        btncomment = findViewById(R.id.btncomment);
        btncomment.setOnClickListener(this);


        Intent intent = getIntent();

         doornumber = intent.getStringExtra("doornumber");
         street = intent.getStringExtra("street");
         area = intent.getStringExtra("area");
         postcode = intent.getStringExtra("postcode");
         phonenumber = intent.getStringExtra("phonenumber");
         delivery = intent.getBooleanExtra("delivery",false);

        address = "\r"+doornumber+" "+street+"\n\r"+area+", "+postcode+"\n\r"+phonenumber;

    }

    @Override
    public void onClick(View v) {

        if (v.getTag() != null ){

            switch (v.getTag().toString()){

                case "Appetisers":{
                    classicsItems = null;
                   clearItemsTableRows();
                   getItemsOfOneCategories("Appetisers");
                   displayItemsButtonsInRows(itemsList.size());
                 break;
                }
                case "Tandoori Grilled":{
                    classicsItems = null;
                    clearItemsTableRows();
                    getItemsOfOneCategories("Tandoori Grilled");
                    displayItemsButtonsInRows(itemsList.size());

                    break;
                }case "Classies":{
                    clearItemsTableRows();
                    getItemsOfOneCategories("Classies");
                    displayItemsButtonsInRows(itemsList.size());

                    break;
                }
                case "Mossalla":{
                    classicsItems = null;
                    clearItemsTableRows();
                    getItemsOfOneCategories("Mossalla");
                    displayItemsButtonsInRows(itemsList.size());

                      break;
                }case "Vegetable Side":{
                    classicsItems = null;
                    clearItemsTableRows();
                    getItemsOfOneCategories("Vegetable Side");
                    displayItemsButtonsInRows(itemsList.size());

                    break;
                }case "Vegetable Main":{
                    classicsItems = null;
                    clearItemsTableRows();
                    getItemsOfOneCategories("Vegetable Main");
                    displayItemsButtonsInRows(itemsList.size());

                    break;
                }case "Variations":{
                    classicsItems = null;
                    clearItemsTableRows();
                    getItemsOfOneCategories("Variations");
                    displayItemsButtonsInRows(itemsList.size());

                    break;
                }case "Biryani":{
                    classicsItems = v.getTag().toString();
                    clearItemsTableRows();
                    getItemsOfOneCategories("Biryani");
                    displayItemsButtonsInRows(itemsList.size());

                    break;
                }case "SeaFood":{
                    classicsItems = null;
                    clearItemsTableRows();
                    getItemsOfOneCategories("SeaFood");
                    displayItemsButtonsInRows(itemsList.size());

                    break;
                }case "Chef's Signature":{
                    classicsItems = null;
                    clearItemsTableRows();
                    getItemsOfOneCategories("Chef's Signature");
                    displayItemsButtonsInRows(itemsList.size());

                    break;
                }case "Rice Dishes":{
                    classicsItems = null;
                    clearItemsTableRows();
                    getItemsOfOneCategories("Rice Dishes");
                    displayItemsButtonsInRows(itemsList.size());

                    break;
                }case "Sundries":{
                    classicsItems = null;
                    clearItemsTableRows();
                    getItemsOfOneCategories("Sundries");
                    displayItemsButtonsInRows(itemsList.size());

                    break;
                }case "Sauces":{
                    classicsItems = null;
                    clearItemsTableRows();
                    getItemsOfOneCategories("Sauces");
                    displayItemsButtonsInRows(itemsList.size());

                    break;
                }case "Drinks":{
                    classicsItems = null;
                    clearItemsTableRows();
                    getItemsOfOneCategories("Drinks");
                    displayItemsButtonsInRows(itemsList.size());

                    break;
                }case "Extra Items":{
                    classicsItems = null;
                    clearItemsTableRows();
                    getItemsOfOneCategories("Extra Items");
                    displayItemsButtonsInRows(itemsList.size());

                    break;
                }case "Set Meals":{
                    classicsItems = null;
                    clearItemsTableRows();
                    getItemsOfOneCategories("Set Meals");
                    displayItemsButtonsInRows(itemsList.size());

                    break;
                }case "Veg Meal":{
                    classicsItems = null;
                    clearItemsTableRows();
                    getItemsOfOneCategories("Veg Meal");
                    displayItemsButtonsInRows(itemsList.size());
                    break;
                } case "Madras":{
                    classicsItems = v.getTag().toString();
                    clearItemsTableRows();
                    getItemsOfOneClassics(v.getTag().toString());
                    displayItemsButtonsInRows(itemsList.size());
                    break;
                }case "Malayan":{
                    classicsItems = v.getTag().toString();
                    clearItemsTableRows();
                    getItemsOfOneClassics(v.getTag().toString());
                    displayItemsButtonsInRows(itemsList.size());
                    break;
                }case "Vindaloo":{
                    classicsItems = v.getTag().toString();
                    clearItemsTableRows();
                    getItemsOfOneClassics(v.getTag().toString());
                    displayItemsButtonsInRows(itemsList.size());
                    break;
                }case "Rogon Josh":{
                    classicsItems = v.getTag().toString();
                    clearItemsTableRows();
                    getItemsOfOneClassics(v.getTag().toString());
                    displayItemsButtonsInRows(itemsList.size());
                    break;
                }case "Pathia":{
                    classicsItems = v.getTag().toString();
                    clearItemsTableRows();
                    getItemsOfOneClassics(v.getTag().toString());
                    displayItemsButtonsInRows(itemsList.size());
                    break;
                }case "Dansak":{
                    classicsItems = v.getTag().toString();
                    clearItemsTableRows();
                    getItemsOfOneClassics(v.getTag().toString());
                    displayItemsButtonsInRows(itemsList.size());
                    break;
                }case "Korai":{
                    classicsItems = v.getTag().toString();
                    clearItemsTableRows();
                    getItemsOfOneClassics(v.getTag().toString());
                    displayItemsButtonsInRows(itemsList.size());
                    break;
                }case "Korma":{
                    classicsItems = v.getTag().toString();
                    clearItemsTableRows();
                    getItemsOfOneClassics(v.getTag().toString());
                    displayItemsButtonsInRows(itemsList.size());
                    break;
                }case "Balti":{
                    classicsItems = v.getTag().toString();
                    clearItemsTableRows();
                    getItemsOfOneClassics(v.getTag().toString());
                    displayItemsButtonsInRows(itemsList.size());
                    break;
                }case "Jal Frezi":{
                    classicsItems = v.getTag().toString();
                    clearItemsTableRows();
                    getItemsOfOneClassics(v.getTag().toString());
                    displayItemsButtonsInRows(itemsList.size());
                    break;
                }case "Kashmir":{
                    classicsItems = v.getTag().toString();
                    clearItemsTableRows();
                    getItemsOfOneClassics(v.getTag().toString());
                    displayItemsButtonsInRows(itemsList.size());
                    break;
                }case "Jhall Bhuna":{
                    classicsItems = v.getTag().toString();
                    clearItemsTableRows();
                    getItemsOfOneClassics(v.getTag().toString());
                    displayItemsButtonsInRows(itemsList.size());
                    break;
                }case "Dupiaza":{
                    classicsItems = v.getTag().toString();
                    clearItemsTableRows();
                    getItemsOfOneClassics(v.getTag().toString());
                    displayItemsButtonsInRows(itemsList.size());
                    break;
                }case "Sag":{
                    classicsItems = v.getTag().toString();
                    clearItemsTableRows();
                    getItemsOfOneClassics(v.getTag().toString());
                    displayItemsButtonsInRows(itemsList.size());
                    break;
                }case "Bhuna":{
                    classicsItems = v.getTag().toString();
                    clearItemsTableRows();
                    getItemsOfOneClassics(v.getTag().toString());
                    displayItemsButtonsInRows(itemsList.size());
                    break;
                }
                case "Set_For One Person":{
                    classicsItems = null;
                    openDialog(v.getTag().toString());
                    break;
                }case "Set_For Two Person":{
                    classicsItems = null;
                    openDialog(v.getTag().toString());

                    break;
                }case "Veg_For One Person":{
                    classicsItems = null;
                    openDialog(v.getTag().toString());

                    break;
                }case "Veg_For Two Person":{
                    classicsItems = null;
                    openDialog(v.getTag().toString());
                  
                    break;
                }case "Special":{
                    classicsItems = null;
                    openSpecialItem();
                    break;
                }

                default:{
                    addItemToListView(v.getTag().toString());
                    //clearItemsTableRows();
                    break;
                }

            }

        }else{
            switch (v.getId()){

                case R.id.btndelete: {

                    customAdapter.clear();
                    arrayList.clear();


                    charge.setText("TOTAL " + totalItemsPrice());
                    itemscount.setTextSize(24);
                    itemscount.setTypeface(null,Typeface.BOLD);

                    itemscount.setText("Items ("+ arrayList.size()+")");
                    itemscount.setTextSize(24);
                    itemscount.setTypeface(null,Typeface.BOLD);

                    break;
                }

                case R.id.linearLayout_menu:{
                    InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    in.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                    break;
                }
                case R.id.btnextraprice:{


                    EditPriceOrder priceOrder = new EditPriceOrder();
                    Bundle args = new Bundle();
                    args.putString("currentPrice" , String.format("%.2f",totalItemsPrice() - OrderEditPrice));
                    charge.setText("TOTAL " + String.format("%.2f",totalItemsPrice() - OrderEditPrice));
                    priceOrder.setArguments(args);
                    priceOrder.show(getSupportFragmentManager(),"EditPriceOrder");
                    break;

                }

                case R.id.btncharge:{

                    if (arrayList.size() <=0){
                        Toast.makeText(this,"Can Not Print Empty List",Toast.LENGTH_LONG).show();
                        return;
                    }

                    Intent intent = new Intent(SellDisplay.this, PrintReceipt.class);
                    intent.putExtra("list",arrayList);
                    intent.putExtra("totalItemsPrice" , totalItemsPrice());
                    intent.putExtra("editPrice" , OrderEditPrice);
                    intent.putExtra("customername",customerName.getText().toString());
                    intent.putExtra("OrderComment",OrderComment);
                    intent.putExtra("doornumber",doornumber);
                    intent.putExtra("street",street);
                    intent.putExtra("area",area);
                    intent.putExtra("postcode",postcode);
                    intent.putExtra("phonenumber",phonenumber);


                    intent.putExtra("address",address);
                    intent.putExtra("delivery" , delivery);
                    startActivity(intent);

                    break;
                }
                case R.id.btncomment:{

                    OrderComment orderComment = new OrderComment();
                    Bundle args = new Bundle();
                    args.putString("comment" , OrderComment);
                    orderComment.setArguments(args);
                    orderComment.show(getSupportFragmentManager(),"ordercomment");
                }

            }
        }
    }

    private void openDialog(String item) {

        SubItemsMenu subItemsMenu = new SubItemsMenu();

        Bundle args = new Bundle();
        args.putString("item" , item);
        subItemsMenu.setArguments(args);
        subItemsMenu.show(getSupportFragmentManager(),"subitemsmenu");

    }

    private void openSpecialItem(){

        SpecialItem specialItem = new SpecialItem();
        specialItem.show(getSupportFragmentManager(), "Special Item");

    }


    public void displayItemsButtonsInRows(int btnNumers){


        btn = setButtonitems(btnNumers);

        for (int i = 0; i < btn.size() ; i++) {
            if (i <= 3) {
                tableRowitem1.addView(btn.get(i));
            } else if (i >= 4 && i < 8) {
                tableRowitem2.addView(btn.get(i));
            } else if (i >= 8 && i < 12) {
                tableRowitem3.addView(btn.get(i));
            }else if (i >= 12 && i < 16) {
                tableRowitem4.addView(btn.get(i));
            }else if (i >= 15 && i < 20) {
                tableRowitem5.addView(btn.get(i));
            }else if (i >= 20 && i < 24) {
                tableRowitem6.addView(btn.get(i));
            }else if (i >= 24 && i < 28) {
                tableRowitem7.addView(btn.get(i));
            }
        }

        btn.clear();
    }


    public void clearItemsTableRows(){

        tableRowitem1 = findViewById(R.id.itemsbtnsco1);
        tableRowitem2 = findViewById(R.id.itemsbtnsco2);
        tableRowitem3 = findViewById(R.id.itemsbtnsco3);
        tableRowitem4 = findViewById(R.id.itemsbtnsco4);
        tableRowitem5 = findViewById(R.id.itemsbtnsco5);
        tableRowitem6 = findViewById(R.id.itemsbtnsco6);
        tableRowitem7 = findViewById(R.id.itemsbtnsco7);

        tableRowitem1.removeAllViews();
        tableRowitem2.removeAllViews();
        tableRowitem3.removeAllViews();
        tableRowitem4.removeAllViews();
        tableRowitem5.removeAllViews();
        tableRowitem6.removeAllViews();
        tableRowitem7.removeAllViews();
    }


    public void setButtonCategies(int btnnum) {

        for (int i = 0; i < btnnum ; i++) {
            buttonCategies = new Button(this);
            buttonCategies.setId(200 + i);
            buttonCategies.setText(catList.get(i));
            buttonCategies.setTag(catList.get(i));
            buttonCategies.setPadding(10, 10, 10, 10);
            buttonCategies.setTextSize(14);
            buttonCategies.setHeight(130);
            buttonCategies.setWidth(230);
            buttonCategies.setBackgroundColor(btncolor[i]);


            if (i <= 5) {
                tableRow1.addView(buttonCategies);
            } else if (i >= 6 && i < 12) {
                tableRow2.addView(buttonCategies);
            } else if (i >= 12 && i < 22) {
                tableRow3.addView(buttonCategies);
            }

            buttonCategies.setOnClickListener(this);
        }

        buttonCategies = new Button(this);
        buttonCategies.setText("Special Item");
        buttonCategies.setTag("Special");
        buttonCategies.setPadding(10, 10, 10, 10);
        buttonCategies.setTextSize(14);
        buttonCategies.setHeight(130);
        buttonCategies.setWidth(230);
        buttonCategies.setOnClickListener(this);
        tableRow3.addView(buttonCategies);
    }


        public List<Button> setButtonitems(int btnnum){

             btn.clear();

            for (int i = 0; i < btnnum ; i++) {
                button = new Button(this);
                button.setId(200+i);
                button.setText(itemsList.get(i));
                button.setTag(itemsList.get(i));
                button.setPadding(10,10,10,10);
                button.setTextSize(14);
                button.setHeight(130);
                button.setWidth(320);

                btn.add(button);

                button.setOnClickListener(this);

            }

            return btn;
    }



    public ArrayList getItemsCategories(){
        catList = new ArrayList<>();
        menuItems = new MenuItems(getApplication());
        Map<String,Map> catKeys = menuItems.getMenuCategories();

        for(Map.Entry<String,Map> key : catKeys.entrySet()){
            catList.add(key.getKey());
        }

        return catList;
    }

    public void getItemsOfOneCategories(String category){

        Map<String,Map> catKeys = menuItems.getMenuCategories();
        for(Map.Entry<String,Map> key : catKeys.entrySet()){
            itemsList = new ArrayList<>();
            menuItems = new MenuItems(getApplication());
            if (key.getKey().equals(category)){

                MapItemsList = key.getValue();

                for(Map.Entry<String,Double> itemsofonecategory : MapItemsList.entrySet()){
                    itemsList.add(itemsofonecategory.getKey());
                }
                break;
            }
        }
    }


    public void getItemsOfOneClassics(String items){

        Map<String,Map> catKeys = menuItems.getMenuCategories().get("Classies");
        for(Map.Entry<String,Map> key : catKeys.entrySet()){
            itemsList = new ArrayList<>();
            menuItems = new MenuItems(getApplication());
            if (key.getKey().equals(items)){

                MapItemsList = key.getValue();

                for(Map.Entry<String,Double> itemsofonecategory : MapItemsList.entrySet()){
                    itemsList.add(itemsofonecategory.getKey());
                }
                break;
            }
        }

    }



    String itemName ="";
    Double itemPrice=0.00;

    public void addItemToListView(String nameItem){

        if (!nameItem.equals("Special")){
            itemName = nameItem;
            itemPrice = MapItemsList.get(itemName);
        }

        itemName = setFinalItemName(itemName);

        item_row oneitem = new item_row(itemName,itemPrice);

        addItem(oneitem,itemName,itemPrice);

        customAdapter = new CustomAdapter(SellDisplay.this,arrayList);
        listView.setAdapter(customAdapter);


        charge.setText("TOTAL " + String.format("%.2f",totalItemsPrice()));
        charge.setTextSize(24);
        charge.setTypeface(null,Typeface.BOLD);


        itemscount.setText("Items ("+ arrayList.size()+")");
        itemscount.setTextSize(24);
        itemscount.setTypeface(null,Typeface.BOLD);

        classicsItems = null;

    }


    public void addItem(item_row oneitem , String itemName , Double itemPrice){

        int count=0;
        int n = -1;
        boolean d = false;



        if (arrayList.size()> 0){

            Iterator<item_row> itemlist = arrayList.iterator();

            while (itemlist.hasNext()) {

                item_row item = itemlist.next();

                if (item.getItemName().equals(itemName)){
                    d = true;

                    count = item.getItemCount();
                    n =  arrayList.indexOf(item);
                }
            }
        }

        if (d){

            customAdapter.clearOneItem(n);

            count = count + oneitem.getItemCount();
            oneitem.setItemCount(count);
            itemPrice = count * oneitem.getItemsPrice();
            oneitem.setItemsPrice(itemPrice);

            arrayList.add(n,oneitem);

        }else {

            arrayList.add(oneitem);

        }

    }

    public Double totalItemsPrice(){

        OrderFinalTotalPrice = 0.0;
 //       OrderEditPrice = 0.0;

        for (int i = 0; i <arrayList.size() ; i++) {

            OrderFinalTotalPrice += arrayList.get(i).itemsPrice;
        }

        OrderFinalTotalPrice += OrderEditPrice;

        return OrderFinalTotalPrice;

    }


    public static void Toast(Context context , String st , int num){
        Toast.makeText(context,st,num).show();
    }

    @Override
    public void applyTexts(String sp1, String sp2, String sp3, String sp4, String sp5, String spSubItem, String sp11, String sp21, String sp41, String spSubItem2,String categories) {


        if (categories.equals("Veg_For One Person") || categories.equals("Set_For One Person")){

            sp1SelectedItem = sp1;
            sp2SelectedItem = sp2;
            sp3SelectedItem = sp3;
            sp4SelectedItem = sp4;
            sp5SelectedItem = sp5;
            selectedSubItem = spSubItem;

            addItemToListView(categories);

        }else {

            sp1SelectedItem = sp1;
            sp2SelectedItem = sp2;
            sp3SelectedItem = sp3;
            sp4SelectedItem = sp4;
            sp5SelectedItem = sp5;
            selectedSubItem = spSubItem;
            sp11SelectedItem = sp11;
            sp21SelectedItem = sp21;
            sp41SelectedItem = sp41;
            selectedSubItem2 = spSubItem2;

            addItemToListView(categories);
        }

    }


    public String setFinalItemName(String itemName){

        if (classicsItems != null){
            itemName = classicsItems+" "+itemName;
            clearItemsTableRows();
        }

        if (itemName.equals("Set_For One Person")){

            itemName = itemName +"\n\r"+sp1SelectedItem+"\n\r" +
                    sp2SelectedItem+" "+selectedSubItem+"\n\r" +
                    sp3SelectedItem+"\n\r" +
                    sp4SelectedItem+"\n\r" +
                    sp5SelectedItem;
        }else if (itemName.equals("Set_For Two Person")){

            itemName = itemName +"\n\r"+sp1SelectedItem+"\n\r" +sp11SelectedItem+"\n\r" +
                    sp2SelectedItem+" "+selectedSubItem+"\n\r" +sp21SelectedItem+" "+selectedSubItem2+"\n\r" +
                    sp4SelectedItem+"\n\r" + sp41SelectedItem+"\n\r"+
                    sp3SelectedItem+"\n\r" +
                    sp5SelectedItem;
        } else if (itemName.equals("Veg_For One Person")){

            itemName = itemName +"\n\r"+sp1SelectedItem+"\n\r" +
                    sp2SelectedItem+"\n\r" +
                    sp3SelectedItem+"\n\r" +
                    sp4SelectedItem+"\n\r" +
                    sp5SelectedItem;
        }else if (itemName.equals("Veg_For Two Person")){

            itemName = itemName +"\n\r"+sp1SelectedItem+"\n\r" +sp11SelectedItem+"\n\r" +
                    sp2SelectedItem+"\n\r" +sp21SelectedItem+"\n\r" +
                    sp4SelectedItem+"\n\r" + sp41SelectedItem+"\n\r"+
                    sp3SelectedItem+"\n\r" +
                    sp5SelectedItem;
        }

        return itemName;

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent serverIntent = new Intent(SellDisplay.this, Main_Activity.class);
        startActivity(serverIntent);

    }


    @Override
    public void applyPrice(double EditOrderPrice) {

        Toast.makeText(SellDisplay.this, "Price Edited Successfully", Toast.LENGTH_LONG).show();
        OrderEditPrice = EditOrderPrice;
        charge.setText("TOTAL " + String.format("%.2f",totalItemsPrice()));

    }

    @Override
    public void applyComment(String comment) {

        OrderComment = comment;
        Toast.makeText(SellDisplay.this, "Comment Added", Toast.LENGTH_LONG).show();
    }

    @Override
    public void applySpecialItem(String name, double price) {

        itemName = name;
        itemPrice = price;

        addItemToListView("Special");
    }
}
