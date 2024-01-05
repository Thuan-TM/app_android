package vn.tlu.edu.android.cartman.sqllite;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserCRUD {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(UserTable user);

    @Query("DELETE FROM users")
    void deleteAll();

    @Query("SELECT * FROM users Where id = :id")
    UserTable getItem(int id);

    @Query("SELECT * FROM users Where username = :username")
    UserTable getByUsername(String username);

    @Query("SELECT * FROM users ORDER BY id ASC")
    List<UserTable> getAlphabetizedWords();
}
