package uk.co.basemosman.myapplication1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    Context mContext;
    ArrayList<item_row> item;


    public CustomAdapter(Context mContext, ArrayList<item_row> item) {

        this.mContext = mContext;
        this.item = item;
    }


    public void clearOneItem(int index){
        item.remove(index);
        notifyDataSetChanged();
    }

    public void  clear(){
        item.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public Object getItem(int position) {
        return item.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.row_item,parent,false);

        }

        item_row oneitem = (item_row) getItem(position);

        TextView name = convertView.findViewById(R.id.itemName);
        TextView price = convertView.findViewById(R.id.itemPrice);
        TextView count = convertView.findViewById(R.id.itemcount);

        name.setText(oneitem.getItemName());
        price.setText(String.format("%1$,.2f",oneitem.getItemsPrice()));
        count.setText(oneitem.getItemCount()+" X ");




        return convertView;
    }
}
