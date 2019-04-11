package uk.co.basemosman.myapplication1;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.hardware.usb.UsbDevice;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import com.zj.usbsdk.UsbController;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import Database.Customer;
import Database.CustomerDao;
import Database.CustomerDatabase;
import Database.CustomerViewModel;


public class PrintReceipt extends Activity  implements View.OnClickListener {

    // Intent request codes
    private static final int REQUEST_CONNECT_DEVICE = 1;

    private TimePickerDialog.OnTimeSetListener mTimeSetListener;
    private Button sendButton = null;
    static UsbController usbCtrl;
    static UsbDevice dev = null;
    private int[][] u_infor;
    ArrayList<item_row> item_rows;
    Double totalItemsPrice = 0.00;
    String time;
    long date;
    String customerName;
    String DAddress = "";
    boolean delivery=false;
    String doornumber,street,area,postcode,phonenumber;
    String sdfDate,sdfTime;
    int dayOrdersNum = 0;
    double editPrice;
    String Line1 = "-----------------------------";
    String OrderComment="";
    String timeRequired = "ASAP";
    Button timepicker;
    TextView textVComment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Intent intent = getIntent();
        item_rows = new ArrayList<>();
        item_rows = intent.getParcelableArrayListExtra("list");
        totalItemsPrice = intent.getDoubleExtra("totalItemsPrice",0.00);
        editPrice = intent.getDoubleExtra("editPrice",0.00);
        customerName = intent.getStringExtra("customername");
        DAddress = intent.getStringExtra("address");
        delivery = intent.getBooleanExtra("delivery",false);
        OrderComment = intent.getStringExtra("OrderComment");

        doornumber = intent.getStringExtra("doornumber");
        street = intent.getStringExtra("street");
        area = intent.getStringExtra("area");
        postcode = intent.getStringExtra("postcode");
        phonenumber = intent.getStringExtra("phonenumber");


        usbCtrl = new UsbController(this,mHandler);
        u_infor = new int[8][2];
        u_infor[0][0] = 0x1CBE;
        u_infor[0][1] = 0x0003;
        u_infor[1][0] = 0x1CB0;
        u_infor[1][1] = 0x0003;
        u_infor[2][0] = 0x0483;
        u_infor[2][1] = 0x5740;
        u_infor[3][0] = 0x0493;
        u_infor[3][1] = 0x8760;
        u_infor[4][0] = 0x0416;
        u_infor[4][1] = 0x5011;
        u_infor[5][0] = 0x0416;
        u_infor[5][1] = 0xAABB;
        u_infor[6][0] = 0x1659;
        u_infor[6][1] = 0x8965;
        u_infor[7][0] = 0x0483;
        u_infor[7][1] = 0x5741;

        date = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy    hh:mma");
        SimpleDateFormat Date = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat Time = new SimpleDateFormat("hh:mma");
        sdfDate = Date.format(date);
        sdfTime = Time.format(date);
        time = sdf.format(date);

        TextView printview  = findViewById(R.id.viewPrint);
        printview.setText(printListItems());

        sendButton =  findViewById(R.id.Send_Button);
        sendButton.setOnClickListener(this);

        sendButton.setEnabled(false);

        timepicker = findViewById(R.id.timepicker);
        timepicker.setOnClickListener(this);

        textVComment = findViewById(R.id.TextVordercomment);
        if (!OrderComment.isEmpty()){
            textVComment.setText("Comment: " + OrderComment+"\n"+Line1+"\n");
        }

        new getnumberOfOrders().execute(sdfDate);
        usbConnetion();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        usbCtrl.close();
    }


    public boolean CheckUsbPermission(){
        if( dev != null ){
            if( usbCtrl.isHasPermission(dev)){
                return true;
            }
        }
        Toast.makeText(getApplicationContext(),"Not Enable To Connected Printer Check Wires",
                Toast.LENGTH_SHORT).show();
        return false;
    }


    @SuppressLint("HandlerLeak") private final  Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UsbController.USB_CONNECTED:
                    Toast.makeText(getApplicationContext(),getString(R.string.msg_getpermission),
                            Toast.LENGTH_SHORT).show();
                    sendButton.setEnabled(true);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.Send_Button: {

                if (CheckUsbPermission()){
                    print();
                    Intent serverIntent = new Intent(PrintReceipt.this, Main_Activity.class);
                    startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE);
                }else {

                    Toast.makeText(this,"Check USB Connection", Toast.LENGTH_LONG).show();
                    return;
                }
            }

            case R.id.timepicker:{

                Calendar calendar = Calendar.getInstance();
                final int hour = calendar.get(Calendar.HOUR);
                int min = calendar.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog;
                timePickerDialog = new TimePickerDialog(PrintReceipt.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        timeRequired = String.format("%02d:%02d", hourOfDay, minute);
                        timepicker.setText("Order Required for : "+timeRequired);
                    }
                }, hour, min, true);
                timePickerDialog.show();
                break;
            }
        }
    }
    private String printListItems() {

        StringBuffer itemsPrint= new StringBuffer();
        if (item_rows != null && item_rows.size() > 0){

            for (int i = 0; i <item_rows.size() ; i++) {

                itemsPrint.append(item_rows.get(i).toString());
                itemsPrint.append("\n");
            }

            if (editPrice > 0.00){
                String x = String.format("%.2f" , editPrice);
                itemsPrint.append(String.format("%-20s %7s%n"," Price Increase ",x));
                itemsPrint.append("\n");
            }else if (editPrice < 0.00){
                String x = String.format("%.2f" , editPrice);
                itemsPrint.append(String.format("%-20s %7s%n"," Price Decrease ",x));
                itemsPrint.append("\n");
            }

        }else {
            Toast.makeText(this,"I Can Not Print Empty List",Toast.LENGTH_LONG).show();
        }

        String printString = String.valueOf(itemsPrint);
        return printString;
    }

    public void print(){

        try {
//            String title= "WESTMOOR TANDOORI";
//            String address ="91 Great Lime Road,\n" +
//                    "West Moor, NE12 7NJ";
//            String tel = "01912681980";
            String d = String.format("%.2f",totalItemsPrice);
            String orderList =  printListItems();
            String Date = time;
            String Line = "==============================";
            String Line1 = "-----------------------------";
            String orderitems = String.format("%s %-20s %d%n",textVComment.getText().toString(),"Orders Items :",item_rows.size());
            String itemTotal = String.format("%-20s %5s%n","Items Total :",d);
            String deliveryFee = String.format("%-20s %5s%n","Delivery Charge :","1.50");
            String Toatlprice = String.format("%-20s %5s%n","Total Price :",d);
            String CSname = "Customer Name : " + customerName;
            String thanks = "Thank You For your Custom";
            String msgPrint="";
            String ordertime = "Order Required for : "+timeRequired;

            if (delivery){

                String totalprice = String.format("%.2f",totalItemsPrice+1.50);
                Toatlprice = String.format("%-20s %5s%n","Total Price :",totalprice);

                 msgPrint = "\n\n"+Date +"\n" +CSname+"\n"+ordertime+"\n"+Line1
                         +"\n"+orderitems+Line+"\n\n"+orderList+Line+"\n"+itemTotal+"\n"+deliveryFee+"\n"+Toatlprice+"\n\n"+DAddress+"\n\n"+thanks+"\n\n";


                Customer customer = new Customer(phonenumber,customerName,doornumber+" "+street,area,postcode,orderList,Toatlprice,sdfDate);
                customer.setOrderType("Delivery");
                customer.setDayOrderNum(String.valueOf(dayOrdersNum));
                customer.setOrderTime(sdfTime);
                customer.setOrderItemsNum(orderitems);
                customer.setRequestTime(ordertime);

                CustomerViewModel model = new CustomerViewModel(getApplication());
                model.insert(customer);

                Toast.makeText(PrintReceipt.this," Delivery Order Added Successfully",Toast.LENGTH_LONG).show();

                Log.d("Dorder" , customer.toString());


            }else {

                 msgPrint = "\n\n"+Date +"\n" +CSname+"\n"+ordertime+"\n"+
                        Line1+"\n"+orderitems+Line+"\n\n"+orderList+Line+"\n"+Toatlprice+"\n\n"+thanks+"\n\n";

                Customer customer = new Customer(phonenumber,customerName,"","","",orderList,Toatlprice,sdfDate);
                customer.setOrderType("Collection");
                customer.setDayOrderNum(String.valueOf(dayOrdersNum));
                customer.setOrderTime(sdfTime);
                customer.setOrderItemsNum(orderitems);
                customer.setRequestTime(ordertime);

                CustomerViewModel model = new CustomerViewModel(getApplication());
                model.insert(customer);

                Toast.makeText(PrintReceipt.this," Collection Order Added Successfully ",Toast.LENGTH_LONG).show();

            }

            if(printListItems().length()>0){

                SendDataByte(PrinterCommand.POS_Print_Text(msgPrint,"BIG5", 0, 0, 1,0));
                SendDataByte(Command.LF);

            }else{
                Toast.makeText(PrintReceipt.this, "I Can Not Print Empty List", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void usbConnetion(){
        usbCtrl.close();
        for( int i = 0 ; i < 8 ; i++ ){
            dev = usbCtrl.getDev(u_infor[i][0],u_infor[i][1]);
            if(dev != null)
                break;
        }

        if( dev != null ){
            if( !(usbCtrl.isHasPermission(dev))){
                usbCtrl.getPermission(dev);
            }else{
                sendButton.setEnabled(true);
            }
        }else{
             Toast.makeText(this,"Can Not Print,... Check USB Connection", Toast.LENGTH_LONG).show();
        }
    }

    private void SendDataByte(byte[] data){
        if(data.length>0)
            usbCtrl.sendByte(data, dev);
    }


    private class getnumberOfOrders extends AsyncTask<String,Void,Integer> {



        @Override
        protected Integer doInBackground(String... strings) {

            CustomerDao customerDao = CustomerDatabase.getInstance(PrintReceipt.this).customerDao();

            return customerDao.getAllCustomerDayOrdersNum(strings[0]);
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);

            if (integer  >= 0 ){
                dayOrdersNum = integer + 1;
            }

            //Toast.makeText(PrintReceipt.this,integer + " ",Toast.LENGTH_LONG).show();

        }
    }

}
