package Database;



import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.ForeignKey;


@Entity(foreignKeys = @ForeignKey(entity = DBMenuCategroies.class,
parentColumns = "CatItemid" , childColumns = "CateItem_Id"), indices = @Index("CateItem_Id"))
public class DBMenuItems {

    @PrimaryKey(autoGenerate = true)
    private int Itemid;

    private String ItemName;
    private String ItemPrice;
    private String SauceType;

    @ColumnInfo(name = "CateItem_Id")
    private int CateItem_Id;

    public DBMenuItems() {
    }


    public int getItemid() {
        return Itemid;
    }

    public void setItemid(int itemid) {
        Itemid = itemid;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public String getItemPrice() {
        return ItemPrice;
    }

    public void setItemPrice(String itemPrice) {
        ItemPrice = itemPrice;
    }

    public String getSauceType() {
        return SauceType;
    }

    public void setSauceType(String sauceType) {
        SauceType = sauceType;
    }

    public int getCateItem_Id() {
        return CateItem_Id;
    }

    public void setCateItem_Id(int cateItem_Id) {
        CateItem_Id = cateItem_Id;
    }
}
