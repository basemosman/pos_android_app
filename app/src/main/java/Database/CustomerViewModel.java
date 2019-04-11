package Database;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;
import android.arch.lifecycle.LiveData;
import java.util.List;

public class CustomerViewModel extends AndroidViewModel {

    private CustomerRepository customerRepository;
    private LiveData<List<Customer>> allCustomers;
    private LiveData<List<Customer>> allCustomersOnDay;


    public CustomerViewModel(@NonNull Application application) {
        super(application);

        customerRepository = new CustomerRepository(application);
        allCustomers = customerRepository.getAllCustomer();
    }


    public void insert(Customer customer) {
        customerRepository.insert(customer);
    }

    public void update(Customer customer) {
        customerRepository.update(customer);
    }

    public void delete(Customer customer) {
        customerRepository.delete(customer);
    }

    public void deleteAllNotes() {
        customerRepository.deleteAllNotes();
    }

    public String AllCustomerOnDayAndTypeInList(String date , String type){
        customerRepository.getAllCustomerOnDayAndTypeInListcheck(date,type);
        return CustomerRepository.check;
    }

    public LiveData<List<Customer>> getAllCustomers() {
        return allCustomers;
    }


    public LiveData<List<Customer>> getAllCustomersOnDay(String date) {

        return customerRepository.getAllCustomerOnAdy(date);
    }


    public LiveData<List<Customer>> getAllCustomersOnDayAndType(String date ,String type) {

        return customerRepository.getAllCustomerOnDayAndType(date , type);
    }

//    public LiveData<List<Customer>> getAllCustomersOnDayAndTypeInList(String date ,String type) {
//
//        return customerRepository.getAllCustomerOnDayAndTypeInList(date , type);
//    }

//    public int getAllCustomerDayOrdersNum(String date) {
//
//        return customerRepository.getAllCustomerDayOrdersNum(date);
//    }

}
