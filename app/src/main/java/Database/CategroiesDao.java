package Database;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface CategroiesDao {

    @Insert
    void insert(DBMenuCategroies... dbMenuCategroies);

    @Update
    void update(DBMenuCategroies... dbMenuCategroies);

    @Delete
    void delete(DBMenuCategroies... dbMenuCategroies);

    @Query("SELECT * FROM DB_MenuCategroies")
    List<DBMenuCategroies> getAllcategroies();

}
