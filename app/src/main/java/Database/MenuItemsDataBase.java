package Database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;


@Database(entities = { DBMenuCategroies.class, DBMenuItems.class },
        version = 1, exportSchema = false)
public abstract class MenuItemsDataBase extends RoomDatabase{

    public abstract CategroiesDao getMenuCategroiesDao();
    public abstract ItemsDao getMenuItemsDao();

    private static MenuItemsDataBase instance;

    public static synchronized MenuItemsDataBase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    MenuItemsDataBase.class, "MenuItems_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }


    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private CategroiesDao categroiesDao;

        private PopulateDbAsyncTask(MenuItemsDataBase db) {
            categroiesDao = db.getMenuCategroiesDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            DBMenuCategroies dbMenuCategroies1 = new DBMenuCategroies();
            dbMenuCategroies1.setCatItemid(1);
            dbMenuCategroies1.setCateName("Appetisers");
            categroiesDao.insert(dbMenuCategroies1);

            DBMenuCategroies dbMenuCategroies2 = new DBMenuCategroies();
            dbMenuCategroies2.setCatItemid(2);
            dbMenuCategroies2.setCateName("Tandoori Grilled");
            categroiesDao.insert(dbMenuCategroies2);

            DBMenuCategroies dbMenuCategroies3 = new DBMenuCategroies();
            dbMenuCategroies3.setCatItemid(3);
            dbMenuCategroies3.setCateName("Classies");
            categroiesDao.insert(dbMenuCategroies3);

            DBMenuCategroies dbMenuCategroies4 = new DBMenuCategroies();
            dbMenuCategroies4.setCatItemid(4);
            dbMenuCategroies4.setCateName("Mossalla");
            categroiesDao.insert(dbMenuCategroies4);

            DBMenuCategroies dbMenuCategroies5 = new DBMenuCategroies();
            dbMenuCategroies5.setCatItemid(5);
            dbMenuCategroies5.setCateName("Vegetable Side");
            categroiesDao.insert(dbMenuCategroies5);

            DBMenuCategroies dbMenuCategroies6 = new DBMenuCategroies();
            dbMenuCategroies6.setCatItemid(6);
            dbMenuCategroies6.setCateName("Vegetable Main");
            categroiesDao.insert(dbMenuCategroies6);

            DBMenuCategroies dbMenuCategroies7 = new DBMenuCategroies();
            dbMenuCategroies7.setCatItemid(7);
            dbMenuCategroies7.setCateName("Variations");
            categroiesDao.insert(dbMenuCategroies7);

            DBMenuCategroies dbMenuCategroies8 = new DBMenuCategroies();
            dbMenuCategroies8.setCatItemid(8);
            dbMenuCategroies8.setCateName("Biryani");
            categroiesDao.insert(dbMenuCategroies8);

            DBMenuCategroies dbMenuCategroies9 = new DBMenuCategroies();
            dbMenuCategroies9.setCatItemid(9);
            dbMenuCategroies9.setCateName("SeaFood");
            categroiesDao.insert(dbMenuCategroies9);

            DBMenuCategroies dbMenuCategroies10 = new DBMenuCategroies();
            dbMenuCategroies10.setCatItemid(10);
            dbMenuCategroies10.setCateName("Chef's Signature");
            categroiesDao.insert(dbMenuCategroies10);

            DBMenuCategroies dbMenuCategroies11 = new DBMenuCategroies();
            dbMenuCategroies11.setCatItemid(11);
            dbMenuCategroies11.setCateName("Rice Dishes");
            categroiesDao.insert(dbMenuCategroies11);

            DBMenuCategroies dbMenuCategroies12 = new DBMenuCategroies();
            dbMenuCategroies12.setCatItemid(12);
            dbMenuCategroies12.setCateName("Sundries");
            categroiesDao.insert(dbMenuCategroies12);

            DBMenuCategroies dbMenuCategroies13 = new DBMenuCategroies();
            dbMenuCategroies13.setCatItemid(13);
            dbMenuCategroies13.setCateName("Sauces");
            categroiesDao.insert(dbMenuCategroies13);

            DBMenuCategroies dbMenuCategroies14 = new DBMenuCategroies();
            dbMenuCategroies14.setCatItemid(14);
            dbMenuCategroies14.setCateName("Drinks");
            categroiesDao.insert(dbMenuCategroies14);

            DBMenuCategroies dbMenuCategroies15 = new DBMenuCategroies();
            dbMenuCategroies15.setCatItemid(15);
            dbMenuCategroies15.setCateName("Extra Items");
            categroiesDao.insert(dbMenuCategroies15);

            DBMenuCategroies dbMenuCategroies16 = new DBMenuCategroies();
            dbMenuCategroies16.setCatItemid(16);
            dbMenuCategroies16.setCateName("Set Meals");
            categroiesDao.insert(dbMenuCategroies16);

            DBMenuCategroies dbMenuCategroies17 = new DBMenuCategroies();
            dbMenuCategroies17.setCatItemid(17);
            dbMenuCategroies17.setCateName("Veg Meal");
            categroiesDao.insert(dbMenuCategroies17);

            return null;
        }
    }

}
