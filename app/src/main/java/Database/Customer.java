package Database;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "customer_table")
public class Customer {


    @PrimaryKey(autoGenerate = true)
    private int cusid;

    private String cusPhoneNumber;

    private String cusName;
    private String cusAddress;
    private String cusArea;
    private String cusPostCode;
    private String cusOrder;
    private String cusPrice;
    private String data;
    private String OrderType;
    private String DayOrderNum;
    private String OrderTime;
    private String OrderItemsNum;

    private String RequestTime;




    public Customer(String cusPhoneNumber, String cusName, String cusAddress, String cusArea, String cusPostCode, String cusOrder, String cusPrice, String data) {
        this.cusPhoneNumber = cusPhoneNumber;
        this.cusName = cusName;
        this.cusAddress = cusAddress;
        this.cusArea = cusArea;
        this.cusPostCode = cusPostCode;
        this.cusOrder = cusOrder;
        this.cusPrice = cusPrice;
        this.data = data;
    }



    public int getCusid() {
        return cusid;
    }

    public void setCusid(int cusid) {
        this.cusid = cusid;
    }

    public String getCusPhoneNumber() {
        return cusPhoneNumber;
    }

    public void setCusPhoneNumber(String cusPhoneNumber) {
        this.cusPhoneNumber = cusPhoneNumber;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getCusAddress() {
        return cusAddress;
    }

    public void setCusAddress(String cusAddress) {
        this.cusAddress = cusAddress;
    }

    public String getCusArea() {
        return cusArea;
    }

    public void setCusArea(String cusArea) {
        this.cusArea = cusArea;
    }

    public String getCusPostCode() {
        return cusPostCode;
    }

    public void setCusPostCode(String cusPostCode) {
        this.cusPostCode = cusPostCode;
    }

    public String getCusOrder() {
        return cusOrder;
    }

    public void setCusOrder(String cusOrder) {
        this.cusOrder = cusOrder;
    }

    public String getCusPrice() {
        return cusPrice;
    }

    public void setCusPrice(String cusPrice) {
        this.cusPrice = cusPrice;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getOrderType() {
        return OrderType;
    }

    public void setOrderType(String orderType) {
        OrderType = orderType;
    }

    public String getDayOrderNum() {
        return DayOrderNum;
    }

    public void setDayOrderNum(String dayOrderNum) {
        DayOrderNum = dayOrderNum;
    }

    public String getOrderTime() {
        return OrderTime;
    }

    public void setOrderTime(String orderTime) {
        OrderTime = orderTime;
    }

    public String getOrderItemsNum() {
        return OrderItemsNum;
    }

    public void setOrderItemsNum(String orderItemsNum) {
        OrderItemsNum = orderItemsNum;
    }


    public String getRequestTime() {
        return RequestTime;
    }

    public void setRequestTime(String requestTime) {
        RequestTime = requestTime;
    }

}