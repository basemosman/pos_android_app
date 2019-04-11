package Database;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "DB_MenuCategroies")
public class DBMenuCategroies {

    @ColumnInfo(name = "CatItemid")
    @PrimaryKey
    private int CatItemid;
    private String CateName;

    public DBMenuCategroies() {
    }

    public void setCateName(String cateName) {
        CateName = cateName;
    }

    public int getCatItemid() {
        return CatItemid;
    }

    public String getCateName() {
        return CateName;
    }

    public void setCatItemid(int catItemid) {
        CatItemid = catItemid;
    }
}
