package Database;


import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import java.util.List;

public class CustomerRepository {

    private CustomerDao customerDao;
    private LiveData<List<Customer>> allCustomers;
    private LiveData<List<Customer>> allCustomersOnDay;
    private LiveData<List<Customer>> allCustomersOnDayAndType;
    private LiveData<List<Customer>> allCustomersOnDayAndTypeinList;


    public static String check ="";

    public CustomerRepository(Application application) {
        CustomerDatabase database = CustomerDatabase.getInstance(application);
        customerDao = database.customerDao();
        allCustomers = customerDao.getAllCustomers();
    }

    public void insert(Customer customer) {
        new InsertCustomerAsyncTask(customerDao).execute(customer);
    }

    public void update(Customer customer) {
        new UpdateCustomerAsyncTask(customerDao).execute(customer);
    }

    public void delete(Customer customer) {
        new DeleteCustomerAsyncTask(customerDao).execute(customer);
    }

    public void deleteAllNotes() {
        new DeleteAllCustomerAsyncTask(customerDao).execute();
    }



    public LiveData<List<Customer>> getAllCustomer() {
        return allCustomers;
    }

    public LiveData<List<Customer>> getAllCustomerOnAdy(String date) {

        allCustomersOnDay = customerDao.getAllCustomerOnDay(date);

        return allCustomersOnDay;
    }

    public LiveData<List<Customer>> getAllCustomerOnDayAndType(String date , String type) {

        allCustomersOnDayAndType = customerDao.getAllCustomerOnDayAndType(date,type);

        return allCustomersOnDayAndType;
    }

//    public LiveData<List<Customer>> getAllCustomerOnDayAndTypeInList(String date , String type) {
//
//        allCustomersOnDayAndTypeinList = customerDao.getAllCustomerOnDayAndTypeInList(date,type);
//
//        return allCustomersOnDayAndTypeinList;
//    }

    void getAllCustomerOnDayAndTypeInListcheck(String date , String type) {

       new AllCustomerOnDayAndTypeInList(customerDao).execute(date,type);
    }



//    public int getAllCustomerDayOrdersNum(String date) {
//
//        int AllCustomerDayOrdersNum = customerDao.getAllCustomerDayOrdersNum(date);
//
//        return AllCustomerDayOrdersNum;
//    }




    private static class InsertCustomerAsyncTask extends  AsyncTask<Customer,Void,Void>{

        private CustomerDao customerDao;

        private InsertCustomerAsyncTask(CustomerDao customerDao) {
            this.customerDao=customerDao;
        }

        @Override
        protected Void doInBackground(Customer... customers) {

            customerDao.insert(customers[0]);
            return null;

        }
    }

    private static class UpdateCustomerAsyncTask extends AsyncTask<Customer, Void, Void> {
        private CustomerDao customerDao;

        private UpdateCustomerAsyncTask(CustomerDao customerDao) {
            this.customerDao = customerDao;
        }

        @Override
        protected Void doInBackground(Customer... customers) {
            customerDao.update(customers[0]);
            return null;
        }
    }

    private static class DeleteCustomerAsyncTask extends AsyncTask<Customer, Void, Void> {
        private CustomerDao customerDao;

        private DeleteCustomerAsyncTask(CustomerDao customerDao) {
            this.customerDao = customerDao;
        }

        @Override
        protected Void doInBackground(Customer... customers) {
            customerDao.delete(customers[0]);
            return null;
        }
    }

    private static class DeleteAllCustomerAsyncTask extends AsyncTask<Void, Void, Void> {
        private CustomerDao customerDao;

        private DeleteAllCustomerAsyncTask(CustomerDao customerDao) {
            this.customerDao = customerDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            customerDao.deleteAllCustomers();
            return null;
        }
    }

    private static class AllCustomerOnDayAndTypeInList extends AsyncTask<String, Void, List<Customer>> {
        private CustomerDao customerDao;

        private AllCustomerOnDayAndTypeInList(CustomerDao customerDao) {
            this.customerDao = customerDao;
        }

        @Override
        protected List<Customer> doInBackground(String... strings) {
            customerDao.getAllCustomerOnDayAndTypeInList(strings[0] , strings[1]);
            return null;
        }

        @Override
        protected void onPostExecute(List<Customer> customers) {
            super.onPostExecute(customers);

            StringBuffer n = new StringBuffer();
            for (Customer c : customers) {

                n.append(c.getCusName());
                n.append(" ");
                n.append(c.getCusPrice());
                n.append("\n");
            }
            check = n.toString();

        }
    }

}
