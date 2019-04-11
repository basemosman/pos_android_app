package uk.co.basemosman.myapplication1;

import android.app.Application;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import Database.DBMenuItems;
import Database.ItemsDao;
import Database.MenuItemsDataBase;


public class MenuItems {


    private Map<String, Map> menuCategories;
    private Application app;

    private Map<String, Double> appetisersItems;
    private Map<String, Double> Tandoori_GrilledItems;

    private Map<String, Map> ClassiesItems;
    private Map<String, Double> ClassiesItemssub;

    private Map<String, Double> MossallaItems;
    private Map<String, Double> Vegetable_SideItems;
    private Map<String, Double> Vegetable_MainItems;
    private Map<String, Double> VariationsItems;
    private Map<String, Double> BiryaniItems;
    private Map<String, Double> SeaFoodItems;
    private Map<String, Double> Chefs_SignatureItems;
    private Map<String, Double> RichItems;
    private Map<String, Double> SundriesItems;
    private Map<String, Double> saucesItems;
    private Map<String, Double> drinksItems;
    private Map<String, Double> extraItems;

    private Map<String, Double> MenuItemsMap;



    public MenuItems(Application application) {

        app = application;
        MenuItemsMap = new LinkedHashMap<>();

        menuCategories = new LinkedHashMap<>();
        menuCategories.put("Appetisers",appettisersMenuItems());
        menuCategories.put("Tandoori Grilled",Tandoori_GrilledMenuItems());
        menuCategories.put("Classies",ClassiesMenuItems());
        menuCategories.put("Mossalla",MossallaMenuItems());
        menuCategories.put("Vegetable Side",Vegetable_SideMenuItems());
        menuCategories.put("Vegetable Main",Vegetable_MainsMenuItems());
        menuCategories.put("Variations",VariationsMenuItems());
        menuCategories.put("Biryani",BiryaniMenuItems());
        menuCategories.put("SeaFood",SeaFoodMenuItems());
        menuCategories.put("Chef's Signature",Chefs_SignatureMenuItems());
        menuCategories.put("Rice Dishes",RichMenuItems());
        menuCategories.put("Sundries",SundriesMenuItems());
        menuCategories.put("Sauces",Sauces());
        menuCategories.put("Drinks",Drinks());
        menuCategories.put("Extra Items",ExtraItems());
        menuCategories.put("Set Meals",Set_MealsMenuItems());
        menuCategories.put("Veg Meal",veg_MealsMenuItems());

    }


    public Map<String, Map> getMenuCategories() {
        return menuCategories;
    }

    public void addmenuCategories(String key , Map value){
        menuCategories.put(key , value);
    }


    private Map<String, Double> appettisersMenuItems() {

        appetisersItems = new LinkedHashMap<>();
        appetisersItems.put("Mixed Platter", 4.50);
        appetisersItems.put("Chicken Liver", 4.95);
        appetisersItems.put("Aloo Bond", 2.95);
        appetisersItems.put("Panner Chatri", 3.50);
        appetisersItems.put("Desi Wrap", 3.50);
        appetisersItems.put("Chicken Chat", 3.25);
        appetisersItems.put("Jhinga Bhaji", 3.95);
        appetisersItems.put("Onion Bhaji", 2.50);
        appetisersItems.put("Tandoori Chicken", 3.25);
        appetisersItems.put("Tikka Chicken", 3.10);
        appetisersItems.put("Sheek Kebab", 2.95);
        appetisersItems.put("Chicken Pakora", 2.95);
        appetisersItems.put("Vag,Meat Somosa", 2.50);
        appetisersItems.put("Mini roll", 1.95);
        appetisersItems.put("BH.PR.PR", 3.50);
        appetisersItems.put("Garlic Chicken Tikka", 3.50);
        appetisersItems.put("Lamb Tikka", 3.50);

        new getItemAsyncTask().execute(1);

        return appetisersItems;
    }


    private Map Tandoori_GrilledMenuItems() {

        Tandoori_GrilledItems = new LinkedHashMap<>();
        Tandoori_GrilledItems.put("T.Mixed Platter", 8.95);
        Tandoori_GrilledItems.put("Tandoori Special",7.50);
        Tandoori_GrilledItems.put("T.King Jhinga", 8.50);
        Tandoori_GrilledItems.put("Sylheti Lamb Chops", 6.50);
        Tandoori_GrilledItems.put("Murghi Shashlic", 6.25);
        Tandoori_GrilledItems.put("Chicken Tikka", 5.50);
        Tandoori_GrilledItems.put("Tandoori Chicken", 5.95);
        Tandoori_GrilledItems.put("Tandoori Dakna", 5.50);
        Tandoori_GrilledItems.put("Garlic Chicken Tikka", 6.25);

        new getItemAsyncTask().execute(2);

        return Tandoori_GrilledItems;
    }

    private Map ClassiesMenuItems() {

        ClassiesItemssub = new LinkedHashMap<>();
        ClassiesItemssub.put("Chicken",5.25);
        ClassiesItemssub.put("Lamb",5.95);
        ClassiesItemssub.put("King Prawn",8.95);
        ClassiesItemssub.put("Vegetables",4.50);
        ClassiesItemssub.put("Chicken Tikka",6.25);
        ClassiesItemssub.put("Lamb Tikka",6.95);
        ClassiesItemssub.put("Prawn",6.95);



        ClassiesItems = new LinkedHashMap<>();
        ClassiesItems.put("Madras", ClassiesItemssub);
        ClassiesItems.put("Malayan", ClassiesItemssub);
        ClassiesItems.put("Vindaloo", ClassiesItemssub);
        ClassiesItems.put("Kashmir", ClassiesItemssub);
        ClassiesItems.put("Rogon Josh", ClassiesItemssub);
        ClassiesItems.put("Pathia", ClassiesItemssub);
        ClassiesItems.put("Dansak", ClassiesItemssub);
        ClassiesItems.put("Korai", ClassiesItemssub);
        ClassiesItems.put("Korma", ClassiesItemssub);
        ClassiesItems.put("Balti", ClassiesItemssub);
        ClassiesItems.put("Jal Frezi", ClassiesItemssub);
        ClassiesItems.put("Jhall Bhuna", ClassiesItemssub);
        ClassiesItems.put("Dupiaza", ClassiesItemssub);
        ClassiesItems.put("Sag", ClassiesItemssub);
        ClassiesItems.put("Bhuna", ClassiesItemssub);


        return ClassiesItems;
    }

    private Map MossallaMenuItems() {

        MossallaItems = new LinkedHashMap<>();
        MossallaItems.put("Mos.Chicken Tikka", 6.25);
        MossallaItems.put("Mos.Lamb Tikka", 6.95);
        MossallaItems.put("Mos.Tandoori King Prawn", 9.95);
        MossallaItems.put("Ch.Tikka.Chili.Moss", 6.95);


        new getItemAsyncTask().execute(4);

        return MossallaItems;
    }

    private Map Vegetable_SideMenuItems() {

        Vegetable_SideItems = new LinkedHashMap<>();
        Vegetable_SideItems.put("Thaja Bindi",2.75);
        Vegetable_SideItems.put("Garlic Mushroom Bhaji", 2.75);
        Vegetable_SideItems.put("Sobzi Chilli", 2.75);
        Vegetable_SideItems.put("Bombay Aloo", 2.75);
        Vegetable_SideItems.put("Palak Sag", 2.75);
        Vegetable_SideItems.put("Aloo Gobi", 2.75);
        Vegetable_SideItems.put("Matar Paneer", 2.95);
        Vegetable_SideItems.put("Tarka Dhall", 2.75);
        Vegetable_SideItems.put("Sag Paneer", 2.95);
        Vegetable_SideItems.put("Chana Sag", 2.75);

        new getItemAsyncTask().execute(5);

        return Vegetable_SideItems;
    }



    private Map Vegetable_MainsMenuItems() {

        Vegetable_MainItems = new LinkedHashMap<>();

        Vegetable_MainItems.put("Sag Paneer Malai", 4.95);
        Vegetable_MainItems.put("Chana Masala", 4.50);
        Vegetable_MainItems.put("Aloo Gobi Masala", 4.50);
        Vegetable_MainItems.put("Vegetable Koria", 4.50);
        Vegetable_MainItems.put("Paneer Butter Masala", 4.95);
        Vegetable_MainItems.put("Sabji Jalfrezi", 4.50);
        Vegetable_MainItems.put("Aloo Palak", 4.50);
        Vegetable_MainItems.put("Aloo Morich", 4.50);
        Vegetable_MainItems.put("Dhall Sambar", 4.50);
        Vegetable_MainItems.put("{Lal uri bisi & dall", 5.25);

        new getItemAsyncTask().execute(6);

        return Vegetable_MainItems;
    }



    private Map VariationsMenuItems() {

        VariationsItems = new LinkedHashMap<>();
        VariationsItems.put("Special Chicken", 5.95);
        VariationsItems.put("Lamb Dhoi", 6.10);
        VariationsItems.put("Methi Gost", 6.10);
        VariationsItems.put("Rajala Chicken", 5.95);
        VariationsItems.put("Ginger Chicken", 5.95);
        VariationsItems.put("Tikka Marinate Chicken", 5.95);
        VariationsItems.put("Kaleeya Lamb", 6.10);
        VariationsItems.put("Lamb Pasanda", 6.10);
        VariationsItems.put("Chicken cylon", 6.25);
        VariationsItems.put("Tan B Chi", 6.95);

        new getItemAsyncTask().execute(7);

        return VariationsItems;
    }



    private Map BiryaniMenuItems() {

        BiryaniItems = new LinkedHashMap<>();
        BiryaniItems.put("King prawn", 10.50);
        BiryaniItems.put("Chicken Tikka", 7.25);
        BiryaniItems.put("Prawn", 8.50);
        BiryaniItems.put("Lamb", 7.95);
        BiryaniItems.put("Tuna", 6.95);
        BiryaniItems.put("Vegetable",6.25);
        BiryaniItems.put("Chicken",6.95);
        BiryaniItems.put("Mix",8.95);

        new getItemAsyncTask().execute(8);

        return BiryaniItems;
    }



    private Map SeaFoodMenuItems() {

        SeaFoodItems = new LinkedHashMap<>();
        SeaFoodItems.put("Shahi Machli Tarka", 7.25);
        SeaFoodItems.put("Shathkora Thelafia", 7.25);
        SeaFoodItems.put("Khobi Mach", 7.25);
        SeaFoodItems.put("Machli Korma", 7.25);
        SeaFoodItems.put("Badshhi Khana", 8.95);
        SeaFoodItems.put("King Prawn Delight", 8.95);
        SeaFoodItems.put("Special Jhings", 8.95);
        SeaFoodItems.put("Lal Chingri", 8.95);
        SeaFoodItems.put("K PR.Bhuna", 8.95);

        new getItemAsyncTask().execute(9);

        return SeaFoodItems;
    }



    private Map Chefs_SignatureMenuItems() {

        Chefs_SignatureItems = new LinkedHashMap<>();
        Chefs_SignatureItems.put("Kathmandu", 6.25);
        Chefs_SignatureItems.put("Chicken Gizzard", 6.25);
        Chefs_SignatureItems.put("Marati Alphoso", 6.25);
        Chefs_SignatureItems.put("Chicken Tikka Chasni", 6.25);
        Chefs_SignatureItems.put("Kabuli Bhuna", 6.25);
        Chefs_SignatureItems.put("Shathkora Chicken", 6.25);
        Chefs_SignatureItems.put("Murgh Tikka Jaipur", 6.25);
        Chefs_SignatureItems.put("Chicken Podina", 6.25);
        Chefs_SignatureItems.put("Apna Style Bhuna Chicken", 6.25);
        Chefs_SignatureItems.put("Green Delight", 6.25);
        Chefs_SignatureItems.put("Westmoor Special", 6.50);
        Chefs_SignatureItems.put("Dilkush", 6.50);
        Chefs_SignatureItems.put("Zalka Gosht", 6.50);
        Chefs_SignatureItems.put("Naga Lamb", 6.50);
        Chefs_SignatureItems.put("Mixed Mnrghi,", 7.95);

        new getItemAsyncTask().execute(10);

        return Chefs_SignatureItems;
    }



    private Map RichMenuItems() {

        RichItems = new LinkedHashMap<>();
        RichItems.put("Boiled Rice", 2.30);
        RichItems.put("Pilau Rice", 2.40);
        RichItems.put("Vegetable Rice", 2.50);
        RichItems.put("Keema Rice", 2.50);
        RichItems.put("Coconut Rice", 2.50);
        RichItems.put("Mushroom Rice", 2.50);
        RichItems.put("Nut Rice", 2.75);
        RichItems.put("Special Rice", 2.95);
        RichItems.put("Chilli Rice", 2.50);
        RichItems.put("Onion Rice", 2.50);
        RichItems.put("Egg Rice", 2.50);
        RichItems.put("Garlic Rice", 2.50);
        RichItems.put("Tikka Rice", 2.95);

        new getItemAsyncTask().execute(11);

        return RichItems;
    }



    private Map SundriesMenuItems() {

        SundriesItems = new LinkedHashMap<>();
        SundriesItems.put("Nan", 2.20);
        SundriesItems.put("Garlic Nan", 2.45);
        SundriesItems.put("Peshawary Nan", 2.45);
        SundriesItems.put("Keema Nan", 2.45);
        SundriesItems.put("Cheese Nan", 2.45);
        SundriesItems.put("Tikka Nan", 2.95);
        SundriesItems.put("Tandoori Roti", 2.20);
        SundriesItems.put("Plain Pratha", 2.50);
        SundriesItems.put("Stuffed Pratha", 2.95);
        SundriesItems.put("Chapati", 1.00);
        SundriesItems.put("Puree", 1.00);
        SundriesItems.put("Papadoms", 0.80);
        SundriesItems.put("Spiced Papadom", 0.85);
        SundriesItems.put("Chips", 1.50);
        SundriesItems.put("Raitha", 1.50);
        SundriesItems.put("Lime Pickle", 1.00);
        SundriesItems.put("Mango Chutney", 1.00);
        SundriesItems.put("Onion Salad", 0.85);
        SundriesItems.put("Spicy Chips", 2.10);
        SundriesItems.put("Mint Sauce", 0.85);
        SundriesItems.put("      Nan", 0.85);

        new getItemAsyncTask().execute(12);

        return SundriesItems;
    }



    private Map Sauces() {

        saucesItems = new LinkedHashMap<>();
        saucesItems.put("Balti Sauce", 2.95);
        saucesItems.put("Jal Frezi Sauce", 2.95);
        saucesItems.put("Curry Sauce", 2.95);
        saucesItems.put("Mossalla Sauce", 2.95);
        saucesItems.put("Korma Sauce", 2.95);
        saucesItems.put("Madras Sauce", 2.95);
        saucesItems.put("        Sauce", 2.95);

        new getItemAsyncTask().execute(13);

        return saucesItems;
    }



    private Map Drinks() {

        drinksItems = new LinkedHashMap<>();
        drinksItems.put("Coke", 1.00);
        drinksItems.put("Diet Coke", 1.00);
        drinksItems.put("Fanta", 1.00);
        drinksItems.put("Rubicon", 1.10);
        drinksItems.put("Bottle Coke", 3.00);
        drinksItems.put("Bottle D.Coke", 3.00);

       new getItemAsyncTask().execute(14);

        return drinksItems;
    }


    private Map ExtraItems() {

        extraItems = new LinkedHashMap<>();
        extraItems.put("Combo Platter", 14.95);
        extraItems.put("Lamb Shank",8.95);
        extraItems.put("Spicy Donner,Chips",4.25);
        extraItems.put("Spicy Donner,Nan",4.95 );

        extraItems.put("Chips&Curry",3.25);
        extraItems.put("Chips&Chi.Curry",3.95);
        extraItems.put("Chi.Curry Rice",3.95);
        extraItems.put("Chi.Curry,Rice.Chips",4.25);
        extraItems.put("Cheesy Chips",2.25);
        extraItems.put("Chi.Tikka Roll",3.95);
        extraItems.put("Sheek.Keb Roll",3.85);

       new getItemAsyncTask().execute(15);

        return extraItems;
    }

    private Map Set_MealsMenuItems() {

        Map<String, Double> Set_MealsItems = new LinkedHashMap<>();

        Set_MealsItems.put("Set_For One Person", 14.95);
        Set_MealsItems.put("Set_For Two Person", 23.95);

        return Set_MealsItems;
    }


    private Map veg_MealsMenuItems() {

        Map<String, Double> veg_MealsItems = new LinkedHashMap<>();

        veg_MealsItems.put("Veg_For One Person", 12.95);
        veg_MealsItems.put("Veg_For Two Person", 19.95);

        return veg_MealsItems;
    }



    private void getMenuItemMap(int categoryNum){



        switch (categoryNum){
            case 1:{
                appetisersItems.putAll(MenuItemsMap);

                break;
            }case 2:{
                Tandoori_GrilledItems.putAll(MenuItemsMap);
                break;
            }case 3:{
                ClassiesItemssub.putAll(MenuItemsMap);
                break;
            }case 4:{
                MossallaItems.putAll(MenuItemsMap);
                break;
            }case 5:{
                Vegetable_SideItems.putAll(MenuItemsMap);
                break;
            }case 6:{
                Vegetable_MainItems.putAll(MenuItemsMap);
                break;
            }case 7:{
                VariationsItems.putAll(MenuItemsMap);
                break;
            }case 8:{
                BiryaniItems.putAll(MenuItemsMap);
                break;
            }case 9:{
                SeaFoodItems.putAll(MenuItemsMap);
                break;
            }case 10:{
                Chefs_SignatureItems.putAll(MenuItemsMap);
                break;
            }case 11:{
                RichItems.putAll(MenuItemsMap);
                break;
            }case 12:{
                SundriesItems.putAll(MenuItemsMap);
                break;
            }case 13:{
                saucesItems.putAll(MenuItemsMap);
                break;
            }case 14:{
                drinksItems.putAll(MenuItemsMap);
                break;
            }case 15:{
                extraItems.putAll(MenuItemsMap);
                break;
            }
        }
    }


    private class getItemAsyncTask extends AsyncTask<Integer,Void,List<DBMenuItems>> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<DBMenuItems> doInBackground(Integer... integers) {

            ItemsDao itemsDao = MenuItemsDataBase.getInstance(app).getMenuItemsDao();

            return itemsDao.findItemsForOneCategroies(integers[0]);
        }

        @Override
        protected void onPostExecute(List<DBMenuItems> dbMenuItems) {
            super.onPostExecute(dbMenuItems);

            String x = "";
            String xx = "";

            for (DBMenuItems d : dbMenuItems) {

                MenuItemsMap.put(d.getItemName(), Double.valueOf(d.getItemPrice()));

//                x +=  "\n"+d.getItemName();
//                xx += d.getItemPrice();
            }

            if (!dbMenuItems.isEmpty()){
                getMenuItemMap(dbMenuItems.get(0).getCateItem_Id());
            }

            MenuItemsMap.clear();
//            Toast.makeText(app, x +" "+ xx , Toast.LENGTH_LONG).show();
        }
    }

}
