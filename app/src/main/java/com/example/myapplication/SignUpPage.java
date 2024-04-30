package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.UserHandle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.graphics.Insets;
import androidx.core.util.Pair;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputLayout;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SignUpPage extends AppCompatActivity {

    // Varaible
    Button loginbtn, signupbtn;
    ImageView logo;
    TextInputLayout uname, pass, mail, phoneNo, fname;

    FirebaseDatabase rootNode;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        loginbtn = findViewById(R.id.loginBtn);
        signupbtn = findViewById(R.id.signUpBtn);
        logo = findViewById(R.id.logo);
        uname = findViewById(R.id.username);
        pass = findViewById(R.id.pwd);
        phoneNo = findViewById(R.id.phone);
        mail = findViewById(R.id.email);
        fname = findViewById(R.id.name);




        DBHelper dbHelper = new DBHelper(this);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpPage.this, LoginPage.class);
                Pair[] pair = new Pair[3];
                pair[0] = new Pair<View, String>(logo, "logo_img");
                pair[1] = new Pair<View, String>(loginbtn, "sign_up_in");
                pair[2] = new Pair<View, String>(signupbtn, "back_sign_up_in");

                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(SignUpPage.this, pair);
                startActivity(intent, options.toBundle());
            }
        });

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = uname.getEditText().getText().toString();
                String password = pass.getEditText().getText().toString();
                String fullNmae = fname.getEditText().getText().toString();
                String email = mail.getEditText().getText().toString();
                String phone = phoneNo.getEditText().getText().toString();

                if (validateEmail() && validateName() && validatePassword() && validateUsername() && validatePhone())
                {
                    boolean insert =  dbHelper.insertData(username,password,fullNmae,email,phone);
                    if (insert)
                    {
                        Toast.makeText(SignUpPage.this, "Account Crated Sucesfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SignUpPage.this,LoginPage.class);
                        startActivity(intent);

                    }else {
                        Toast.makeText(SignUpPage.this,"not inserted",Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(SignUpPage.this,"not inserted",Toast.LENGTH_LONG).show();
                }

            }
        });


    }

    private boolean validateName() {
        String val = fname.getEditText().getText().toString();

        if (val.isEmpty()) {
            fname.setError("Field cannot be empty");
            return false;
        } else {
            fname.setError(null);
            return true;
        }
    }

    private boolean validateUsername() {
        String val = uname.getEditText().getText().toString();
        String noWhiteSpace = "(?=\\S+$) ";

        if (val.isEmpty()) {
            uname.setError("Field cannot be empty");
            return false;
        } else if(val.length() >= 15) {
            uname.setError("Username too long");
            return false;
        } else if (val.matches(noWhiteSpace)) {
            uname.setError("Username whitespaces are not allowed");
            return false;
        } else {
            uname.setError(null);
            uname.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatePassword() {
        String val = pass.getEditText().getText().toString();
        String pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d@$!%*?&]{8,}$";

        if (val.isEmpty()) {
            pass.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(pattern)) {
            pass.setError("password too weak");
            return false;
        } else {
            pass.setError(null);
            return true;
        }
    }

    private boolean validateEmail() {
        String val = mail.getEditText().getText().toString();
        // valid mail example.samplemail@gmail.com
        // invalid email sample?examplemail@gmail.com
        String pattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()) {
            mail.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(pattern)) {
            mail.setError("Invalid E-mail address");
            return false;
        } else {
            mail.setError(null);
            return true;
        }
    }

    private boolean validatePhone() {
        String val = phoneNo.getEditText().getText().toString();

        if (val.isEmpty()) {
            phoneNo.setError("Field cannot be empty");
            return false;
        } else if (val.length() != 10){
            phoneNo.setError("should be 10 digit");
            return false;
        }else{
            phoneNo.setError(null);
            return true;
        }
    }



}