package Database;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import uk.co.basemosman.myapplication1.R;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.CustomerHolder> {

    private List<Customer> customers = new ArrayList<>();
    private OnItemClickListener listener;



    @NonNull
    @Override
    public CustomerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.table_row, parent, false);

        return new CustomerHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerHolder holder, int position) {

        Customer currentCustomer = customers.get(position);
        holder.dayOrderNum.setText(String.valueOf(currentCustomer.getDayOrderNum()));
        holder.colName.setText(currentCustomer.getCusName());
        holder.colAddress.setText(currentCustomer.getCusAddress());
//        holder.colArea.setText(currentCustomer.getCusArea());
//        holder.colPostcode.setText(currentCustomer.getCusPostCode());
//        holder.colPhonenumber.setText(currentCustomer.getCusPhoneNumber());
        holder.colOrderType.setText(currentCustomer.getOrderType());

//        holder.colOrder.setText(currentCustomer.getCusOrder());
//        holder.colPrice.setText(currentCustomer.getCusPrice());

    }

    @Override
    public int getItemCount() {
        return customers.size();
    }

    public void setCutomers(List<Customer> customers) {
        this.customers = customers;
        notifyDataSetChanged();
    }

    public Customer getCustomerAt(int position){

        return customers.get(position);
    }



    class CustomerHolder extends RecyclerView.ViewHolder{

        private TextView dayOrderNum;
        private TextView colName;
        private TextView colAddress;
//        private TextView colArea;
//        private TextView colPostcode;
//        private TextView colPhonenumber;
//        private TextView colOrder;
//        private TextView colPrice;

        private TextView colOrderType;







        public CustomerHolder(View itemView) {
            super(itemView);

            dayOrderNum = itemView.findViewById(R.id.col_dayordernum);
            colOrderType = itemView.findViewById(R.id.col_orderType);
            colName = itemView.findViewById(R.id.col_name);
            colAddress = itemView.findViewById(R.id.col_address);
//            colArea = itemView.findViewById(R.id.col_area);
//            colPostcode = itemView.findViewById(R.id.col_postcode);
//            colPhonenumber = itemView.findViewById(R.id.col_phone);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(customers.get(position));
                    }
                }
            });

//            colOrder = itemView.findViewById(R.id.col_order);
//            colPrice = itemView.findViewById(R.id.col_price);

        }
    }


    public interface OnItemClickListener {
        void onItemClick(Customer customer);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

}
