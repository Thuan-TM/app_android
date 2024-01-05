package vn.tlu.edu.android.cartman;

import androidx.appcompat.app.AppCompatActivity;

import vn.tlu.edu.android.cartman.cart.CartActivity;
import vn.tlu.edu.android.cartman.register.register;
import vn.tlu.edu.android.cartman.sqllite.UserRepository;
import vn.tlu.edu.android.cartman.sqllite.UserTable;

import android.content.Intent;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText editText1 ,editText2; //Deklarasi object dari class EdiText
    String username ,password; //Deklarasi object string

    Button btn_register;
    public UserRepository userRepository;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userRepository = new UserRepository(getApplication());

        btn_register = findViewById(R.id.button3);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleregister();
            }
        } );
    }

    public void handleregister(){
        Intent intent = new Intent(this, register.class);
        startActivity(intent);
    }
    public void handlelogin(View view){
        editText1 = (EditText)findViewById(R.id.edittext_username);
        editText2 = (EditText) findViewById(R.id.edittext_password);

        username = editText1.getText().toString();
        password = editText2.getText().toString();

        //Kondisi jika username dan password benar maka akan menampilkan pesan text toast
        //Login sukses dan masuk ke activity 2
        UserTable user = userRepository.getItemByUserName(username);

        if(user != null) {
            if ((password.contains(user.getPassword()))) {
                Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
//            setContentView(R.layout.activity_main);

                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);
//            Intent intent = new Intent(this, MainActivity.class);
//            startActivity(intent);
            }
        }
    }

}