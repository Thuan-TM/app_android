package vn.tlu.edu.android.cartman.register;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.time.LocalDateTime;

import vn.tlu.edu.android.cartman.Login;
import vn.tlu.edu.android.cartman.MainActivity;
import vn.tlu.edu.android.cartman.R;
import vn.tlu.edu.android.cartman.sqllite.UserRepository;
import vn.tlu.edu.android.cartman.sqllite.UserTable;


public class register extends AppCompatActivity {

    EditText editText1 ,editText2, editText3; //Deklarasi object dari class EdiText
    String username ,password, full_name; //Deklarasi object string

    int created;
    Button btn_register;


    public UserRepository userRepository;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userRepository = new UserRepository(getApplication());

        btn_register = findViewById(R.id.button);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleregister();
            }
        });

    }


    public void handleregister(){
        editText1 = findViewById(R.id.editTextText);
        editText2 = findViewById(R.id.editTextTextPassword);
        editText3 = findViewById(R.id.editTextText3);


        username = editText1.getText().toString();
        password = editText2.getText().toString();
        full_name = editText3.getText().toString();


        LocalDateTime currentTime = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            currentTime = LocalDateTime.now();
        }
        int convertedTime = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            convertedTime = (int) currentTime.toEpochSecond(java.time.ZoneOffset.UTC);
        }
        created = convertedTime;


        //Kondisi jika username dan password benar maka akan menampilkan pesan text toast
        //Login sukses dan masuk ke activity 2

        UserTable user = new UserTable(username,password,full_name,created);

        Log.d("alo", user.getFull_name());

        // check neu ton tai show toast

        userRepository.insert(user);

//        check neu co thi show mess
        UserTable user_check = userRepository.getItemByUserName(username);

        if(user_check != null) {
            Toast.makeText(this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(register.this, Login.class);
            startActivity(intent);
//              Intent intent = new Intent(this, MainActivity.class);
//              startActivity(intent);
        }
    }



}