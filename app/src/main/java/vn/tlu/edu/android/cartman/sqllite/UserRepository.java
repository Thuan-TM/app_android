package vn.tlu.edu.android.cartman.sqllite;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class UserRepository {
    private UserCRUD User_crud;
    private List<UserTable> user_list;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    public UserRepository(Application application) {
        DbRoom db = DbRoom.getDatabase(application);
        User_crud = db.User_crud();
        user_list = User_crud.getAlphabetizedWords();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public List<UserTable> getAllusers() {
        return user_list;
    }

    public UserTable getItem(int id){
        return User_crud.getItem(id);
    }

    public UserTable getItemByUserName(String username){
        return User_crud.getByUsername(username);
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    public void insert(UserTable user) {
        User_crud.insert(user);
    }

}
