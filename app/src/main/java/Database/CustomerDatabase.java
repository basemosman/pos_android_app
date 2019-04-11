package Database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;


@Database(entities = {Customer.class}, version = 1,exportSchema = false)
public abstract class CustomerDatabase extends RoomDatabase {


    private static CustomerDatabase instance;

    public abstract CustomerDao customerDao();

    public static synchronized CustomerDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    CustomerDatabase.class, "customer_database")
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
        private CustomerDao customerDao;

        private PopulateDbAsyncTask(CustomerDatabase db) {
            customerDao = db.customerDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            Customer customer = new Customer("07453994139", "Basem", "328 chesters avenue","Longbenton","Ne128QN","Chips", "2.15","25/12/2018");
            customer.setOrderType("Delivery");
            customer.setDayOrderNum("1");
            customerDao.insert(customer);

            Customer customer1 = new Customer("07453994139", "Basem", "","","","Chips", "2.15","25/12/2018");
            customer1.setOrderType("Collection");
            customer1.setDayOrderNum("2");
            customerDao.insert(customer1);

            Customer customer2 = new Customer("07453994139", "Omar", "328 limes avenue","Longbenton","Ne128QN","Chips", "2.15","26/12/2018");
            customer2.setOrderType("Delivery");
            customer2.setDayOrderNum("1");
            customerDao.insert(customer2);

            Customer customer4 = new Customer("07453994139", "Youssef", "","","","Chips", "2.15","26/12/2018");
            customer4.setOrderType("Collection");
            customer4.setDayOrderNum("2");
            customerDao.insert(customer4);

            Customer customer5 = new Customer("07453994139", "Ebtisam", "328 view avenue","Longbenton","Ne128QN","Chips", "2.15","27/12/2018");
            customer5.setOrderType("Delivery");
            customer5.setDayOrderNum("1");
            customerDao.insert(customer5);

            Customer customer6 = new Customer("07453994139", "basma", "","","","Chips", "2.15","27/12/2018");
            customer6.setOrderType("Collection");
            customer6.setDayOrderNum("2");
            customerDao.insert(customer6);

            Customer customer7 = new Customer("07453994139", "Ahmed", "328 killing avenue","Longbenton","Ne128QN","Chips", "2.15","27/12/2018");
            customer7.setOrderType("Delivery");
            customer7.setDayOrderNum("3");
            customerDao.insert(customer7);


            return null;
        }
    }



}
