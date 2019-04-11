package uk.co.basemosman.myapplication1;

import android.os.Parcel;
import android.os.Parcelable;

public class item_row implements Parcelable {

    String itemName;
    Double itemsPrice;
    int itemCount = 1;

    public item_row(String Name,Double Price) {
        this.itemName = Name;
        this.itemsPrice = Price;
    }

    protected item_row(Parcel in) {

        itemName = in.readString();
        if (in.readByte() == 0) {
            itemsPrice = null;
        } else {
            itemsPrice = in.readDouble();
        }
        itemCount = in.readInt();
    }

    public static final Creator<item_row> CREATOR = new Creator<item_row>() {
        @Override
        public item_row createFromParcel(Parcel in) {
            return new item_row(in);
        }

        @Override
        public item_row[] newArray(int size) {
            return new item_row[size];
        }
    };

    public Double getItemsPrice() {
        return itemsPrice;
    }



    public String getItemName() {

        return itemName;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemsPrice(Double itemsPrice) {
        this.itemsPrice = itemsPrice;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(itemName);
        if (itemsPrice == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(itemsPrice);
        }
        dest.writeInt(itemCount);
    }



    @Override
    public String toString() {

        String d = String.format("%.2f",itemsPrice);
        String s = String.format("%-20s %5s%n",itemName,d);

        return itemCount +"X "+s;
    }
}
