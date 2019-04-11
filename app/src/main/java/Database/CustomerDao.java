package Database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface CustomerDao {

    @Insert
    void insert(Customer customer);

    @Update
    void update(Customer customer);

    @Delete
    void delete(Customer customer);

    @Query("DELETE FROM customer_table")
    void deleteAllCustomers();

    @Query("SELECT * FROM customer_table ORDER BY cusid DESC")
    LiveData<List<Customer>> getAllCustomers();

    @Query("SELECT * FROM customer_table Where data =:daydate ORDER BY cusid DESC")
    LiveData<List<Customer>> getAllCustomerOnDay(String daydate);

    @Query("SELECT * FROM customer_table Where data =:daydate and OrderType =:type ORDER BY cusid DESC")
    LiveData<List<Customer>> getAllCustomerOnDayAndType(String daydate,String type);


    @Query("SELECT COUNT(DayOrderNum) FROM customer_table Where data =:daydate")
    int getAllCustomerDayOrdersNum(String daydate);


//    @Query("SELECT * FROM customer_table Where data =:daydate and OrderType =:type ORDER BY cusid DESC")
//    LiveData<List<Customer>> getAllCustomerOnDayAndTypeInList(String daydate,String type);


    @Query("SELECT * FROM customer_table Where data =:daydate and OrderType =:type ORDER BY cusid DESC")
    List<Customer> getAllCustomerOnDayAndTypeInList(String daydate,String type);
}
