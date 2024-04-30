package com.example.myapplication;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.graphics.Insets;
import androidx.core.util.Pair;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class LoginPage extends AppCompatActivity {

    // Varaibles

    Button signup,signin,forgetpass;
    ImageView logo;
    TextInputLayout username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.main_activity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        signup = findViewById(R.id.signUpBtn);
        logo = findViewById(R.id.logo);
        signin = findViewById(R.id.signInBtn);
        username = findViewById(R.id.username);
        password = findViewById(R.id.pass);
        forgetpass  = findViewById(R.id.forgetBtn);

        DBHelper dbHelper = new DBHelper(this);


        forgetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginPage.this, ForgetPass.class);
                startActivity(intent);
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginPage.this, SignUpPage.class);
                Pair[] pair = new Pair[3];
                pair[0] = new Pair<View,String>(logo,"logo_img");
                pair[1] = new Pair<View,String>(signin,"sign_up_in");
                pair[2] = new Pair<View,String>(signup,"back_sign_up_in");

                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(LoginPage.this,pair);
                startActivity(intent,options.toBundle());
                finish();
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname = username.getEditText().getText().toString();
                String pass = password.getEditText().getText().toString();

                boolean isPresent = dbHelper.checkCredantials(uname,pass);

                if (isPresent){
                    Intent intent = new Intent(LoginPage.this, HomePage.class);
                    startActivity(intent);
                    finish();
                }else {
                    username.setError("incorrect Unsername or password");
                    password.setError("incorrect Unsername or password");
                }
            }
        });


    }
}