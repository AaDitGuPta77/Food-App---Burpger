package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputLayout;

public class ForgetPass extends AppCompatActivity {

    ImageView back;
    Button forgetPass;
    TextInputLayout uname,pass,repass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_forget_password_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        uname = findViewById(R.id.Fusername);
        pass = findViewById(R.id.NewPassword);
        repass = findViewById(R.id.NewRePassword);
        forgetPass = findViewById(R.id.ForgetBtn);
        back = findViewById(R.id.backToLogin);

        DBHelper dbHelper = new DBHelper(this);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgetPass.this, LoginPage.class);
                startActivity(intent);
            }
        });

        forgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = uname.getEditText().getText().toString();
                String password = pass.getEditText().getText().toString();
                String rePassword = repass.getEditText().getText().toString();

                if (!password.equals(rePassword))
                {
                    repass.setError("Password dose not match");
                    pass.setError("Password dose not match");
                } else if (!dbHelper.checkUsername(username)) {
                    uname.setError("User dose not exist");
                } else if (password.equals(rePassword) && dbHelper.checkUsername(username)) {
                    dbHelper.changePassword(username,password);
                    Toast.makeText(ForgetPass.this, "Pssword changed sucessfuly", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ForgetPass.this, LoginPage.class);
                    startActivity(intent);
                } else {
                    uname.setError("Error");
                }
            }
        });

    }
}