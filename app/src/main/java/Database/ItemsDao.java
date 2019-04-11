package Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import java.util.List;

@Dao
public interface ItemsDao {

    @Insert
    void insert(DBMenuItems dbMenuItems);

    @Update
    void update(DBMenuItems... dbMenuItems);

    @Delete
    void delete(DBMenuItems... dbMenuItems);

    @Query("SELECT * FROM DBMenuItems")
    List<DBMenuItems> getAllitems();

    @Query("SELECT * FROM DBMenuItems WHERE CateItem_Id =:cateId")
    List<DBMenuItems> findItemsForOneCategroies(final int cateId);
}
