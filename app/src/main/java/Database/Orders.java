package Database;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.hardware.usb.UsbDevice;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;
import com.zj.usbsdk.UsbController;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import uk.co.basemosman.myapplication1.Command;
import uk.co.basemosman.myapplication1.PrinterCommand;
import uk.co.basemosman.myapplication1.R;

public class Orders extends AppCompatActivity implements View.OnClickListener {

    private TextView datedisplay;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    List<Customer> CusList;
    CustomerAdapter adapter = null;
    private CustomerViewModel customerViewModel;
    TextView displayOrders;
    String Odisplay , OrderRequiredTime;
    Button btnprint,btnOrdersDelivery,btnOrdersCollection,btnOrdersAll;
    Button btnOrdersCashUp;

    double CollectionsTotal = 0.0;
    int Collectionlistsize = 0;
    double DeliveriesTotal = 0.0;
    int Deliverieslistsize = 0;
    double OrdersTotalsOfDay =0.0;

    static UsbController usbCtrl;
    static UsbDevice dev = null;
    private int[][] u_infor;


//    final String ShopAddress = "WESTMOOR TANDOORI"+"\n"+
//                               "91 Great Lime Road,\n" +
//                               "West Moor, NE12 7NJ"+"\n"+
//                               "01912681980";

    String Date,orderList,orderitems,itemTotal,deliveryFee,Toatlprice,CSname, DateOnDB;
    String Line = "==============================";
    String Line1 = "-----------------------------";
    String thanks = "Thank You For your Custom";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        adapter = new CustomerAdapter();
        recyclerView.setAdapter(adapter);

        displayOrders = findViewById(R.id.displayOrders);


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

        usbConnetion();

        customerViewModel = ViewModelProviders.of(this).get(CustomerViewModel.class);


        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date = sdf.format(System.currentTimeMillis());

        datedisplay = findViewById(R.id.orderdate);
        datedisplay.setText(Date);


        getAllOrderONDay(Date);

        datedisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                displayOrders.setText("");

                Calendar calendar = Calendar.getInstance();
                int Year = calendar.get(Calendar.YEAR);
                int Day = calendar.get(Calendar.DAY_OF_MONTH);
                int Month = calendar.get(Calendar.MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        Orders.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,mDateSetListener,Year,Month,Day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });


        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String selectedDate = sdf.format(view.getCalendarView().getDate());

                getAllOrderONDay(selectedDate);
                datedisplay.setText(selectedDate);

                new AllCustomerOnDayAndCollectionInList().execute(datedisplay.getText().toString(),"Collection");
                new AllCustomerOnDayAndDeliveryInList().execute(datedisplay.getText().toString(),"Delivery");

            }
        };

        adapter.setOnItemClickListener(new CustomerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Customer customer) {

                DateOnDB = customer.getData() +"  "+customer.getOrderTime();
                CSname = "Customer Name : " + customer.getCusName();
                orderitems = customer.getOrderItemsNum();
                orderList = customer.getCusOrder();
                Toatlprice = customer.getCusPrice();
                OrderRequiredTime = customer.getRequestTime();


                String OrderType = customer.getOrderType();

                if (OrderType.equals("Collection")){


                     Odisplay = "\n\n"+DateOnDB +"\n" +CSname+"\n"+OrderRequiredTime+"\n"+
                            Line1+"\n"+orderitems+Line+"\n\n"+orderList+Line+"\n"+Toatlprice+"\n\n"+thanks+"\n\n";

                    displayOrders.setText(Odisplay);

                }else {

                    deliveryFee = String.format("%-20s %5s%n","Delivery Charge :","1.50");
                    String DAddress = customer.getCusAddress()+"\n"+customer.getCusArea()+", "+customer.getCusPostCode()+"\n"+customer.getCusPhoneNumber();

                     Odisplay = "\n\n"+DateOnDB+"\n" +CSname+"\n"+OrderRequiredTime+"\n"+
                            Line1+"\n"+orderitems+Line+"\n\n"+orderList+Line+"\n"+"\n"+deliveryFee+"\n"+Toatlprice+"\n\n"+DAddress+"\n\n"+thanks+"\n\n";

                    displayOrders.setText(Odisplay);
                }
            }
        });




        btnprint = findViewById(R.id.OrderPrint);
        btnprint.setOnClickListener(this);

        btnOrdersDelivery = findViewById(R.id.ordersDeliveries);
        btnOrdersDelivery.setOnClickListener(this);

        btnOrdersCollection = findViewById(R.id.ordersCollection);
        btnOrdersCollection.setOnClickListener(this);

        btnOrdersAll = findViewById(R.id.allOrders);
        btnOrdersAll.setOnClickListener(this);


        btnOrdersCashUp = findViewById(R.id.OrdersCashUp);
        btnOrdersCashUp.setOnClickListener(this);




        //  new getnumberOfOrders().execute(Date);

//        Button datainsert = findViewById(R.id.datainser);
//        datainsert.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
////                Customer customer = new Customer();
////
////                customer.setCusName("basem");
////                customer.setOrderType("Collection");
////                customer.setDayOrderNum("1");
////                customer.setCusPhoneNumber("07453994139");
//                //  customer.setCusid(24);
//               //   customerViewModel.insert(customer)
//                //customerViewModel.insert(customer);
//                // customerViewModel.delete(adapter.getCustomerAt(0));
//            }
//        });

        new AllCustomerOnDayAndCollectionInList().execute(datedisplay.getText().toString(),"Collection");
        new AllCustomerOnDayAndDeliveryInList().execute(datedisplay.getText().toString(),"Delivery");



        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

                customerViewModel.delete(adapter.getCustomerAt(viewHolder.getAdapterPosition()));
                Toast.makeText(Orders.this, "Order Deleted" , Toast.LENGTH_LONG).show();

                new AllCustomerOnDayAndCollectionInList().execute(datedisplay.getText().toString(),"Collection");
                new AllCustomerOnDayAndDeliveryInList().execute(datedisplay.getText().toString(),"Delivery");

            }
        }).attachToRecyclerView(recyclerView);

    }


    @SuppressLint("HandlerLeak") private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UsbController.USB_CONNECTED:
                    Toast.makeText(getApplicationContext(),getString(R.string.msg_getpermission),
                            Toast.LENGTH_SHORT).show();
                    // sendButton.setEnabled(true);
                    break;
                default:
                    break;
            }
        }
    };


    public void getAllOrderONDay(String date){

        customerViewModel.getAllCustomersOnDay(date).observe(this, new Observer<List<Customer>>() {
            @Override
            public void onChanged(@Nullable List<Customer> customers) {
                adapter.setCutomers(customers);
            }
        });
    }

    public void getAllOrderONDayAndType(String date , String type){

        customerViewModel.getAllCustomersOnDayAndType(date,type).observe(this, new Observer<List<Customer>>() {
            @Override
            public void onChanged(@Nullable List<Customer> customers) {
                adapter.setCutomers(customers);
            }
        });
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
               // sendButton.setEnabled(true);
            }
        }else{
            Toast.makeText(this,"Can Not Print,... Check USB Connection", Toast.LENGTH_LONG).show();
        }
    }

    private void SendDataByte(byte[] data){
        if(data.length>0)
            usbCtrl.sendByte(data, dev);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.OrderPrint:{
                if (CheckUsbPermission()){

                    if(displayOrders.getText().toString().length()>0){

                        SendDataByte(PrinterCommand.POS_Print_Text(displayOrders.getText().toString(),"BIG5", 0, 0, 1,0));
                        SendDataByte(Command.LF);

                    }else{
                        Toast.makeText(Orders.this, "I Can Not Print Empty List", Toast.LENGTH_SHORT).show();
                    }

                }else {

                    Toast.makeText(Orders.this,"Check USB Connection", Toast.LENGTH_LONG).show();
                    return;
                }

                break;
            }

            case R.id.ordersDeliveries:{
                getAllOrderONDayAndType(datedisplay.getText().toString(),"Delivery");
                break;
            }

            case R.id.ordersCollection:{
                getAllOrderONDayAndType(datedisplay.getText().toString(),"Collection");
                break;
            }

            case R.id.allOrders:{
                getAllOrderONDay(datedisplay.getText().toString());
                break;
            }

            case R.id.OrdersCashUp:{
                 ordersCheckedUp();
                break;
            }
        }
    }

    public void ordersCheckedUp(){

        String CX = String.format("%-20s %s%n","Cash Up Orders On :",datedisplay.getText().toString());

        String CXx = String.format("%-20s %s%n","Collections No:",String.valueOf(Collectionlistsize));
        String CXxx = String.format("%-20s %.2f%n","Collections Total Price :",CollectionsTotal);

        String DXx = String.format("%-20s %s%n","Deliveries No:",String.valueOf(Deliverieslistsize));
        String DXxx = String.format("%-20s %.2f%n","Deliveries Total Price :",DeliveriesTotal);

        OrdersTotalsOfDay = DeliveriesTotal + CollectionsTotal;

        String TX = String.format("%-20s %s%n"," Total Orders No:",String.valueOf(Deliverieslistsize + Collectionlistsize));
        String Total_price = String.format("%-20s %.2f%n","Total Orders Price :",OrdersTotalsOfDay);


        String Final_display = CX + "\n" + Line1 + "\n" + CXx + "\n" + CXxx + "\n"
                + Line + "\n" + DXx + "\n" + DXxx + "\n" + Line +"\n"+ TX + "\n" + Total_price +"\n\n";

        displayOrders.setText(Final_display);

    }


     class AllCustomerOnDayAndCollectionInList extends AsyncTask<String , Void , List<Customer>>{


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            CollectionsTotal = 0.0;
            OrdersTotalsOfDay = 0.0;
        }

        @Override
        protected List<Customer> doInBackground(String... strings) {

            CustomerDao customerDao = CustomerDatabase.getInstance(Orders.this).customerDao();

            return customerDao.getAllCustomerOnDayAndTypeInList(strings[0], strings[1]);
        }

        @Override
        protected void onPostExecute(List<Customer> customers) {
            super.onPostExecute(customers);

            for (Customer c : customers) {
                String d = c.getCusPrice().substring(13).trim();
                CollectionsTotal += Double.parseDouble(d);
            }

            Collectionlistsize = customers.size();
        }
    }



    class AllCustomerOnDayAndDeliveryInList extends AsyncTask<String , Void , List<Customer>>{


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            DeliveriesTotal = 0.0;
            OrdersTotalsOfDay = 0.0;
        }

        @Override
        protected List<Customer> doInBackground(String... strings) {

            CustomerDao customerDao = CustomerDatabase.getInstance(Orders.this).customerDao();
            return customerDao.getAllCustomerOnDayAndTypeInList(strings[0], strings[1]);
        }

        @Override
        protected void onPostExecute(List<Customer> customers) {
            super.onPostExecute(customers);

            for (Customer c : customers) {
                String d = c.getCusPrice().substring(13).trim();
                DeliveriesTotal += Double.parseDouble(d);
            }

            Deliverieslistsize = customers.size();

        }
    }
}
