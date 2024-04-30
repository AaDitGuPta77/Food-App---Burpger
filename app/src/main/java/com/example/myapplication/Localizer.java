package com.example.myapplication;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class Localizer extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button langBtn;
    String[] lang = { "English", "Hindi",
            "Spanish"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_localizer);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        langBtn = findViewById(R.id.confirmLang);
        Spinner spino = findViewById(R.id.spinner);
        spino.setOnItemSelectedListener(this);

        ArrayAdapter ad
                = new ArrayAdapter(this, android.R.layout.simple_spinner_item, lang);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spino.setAdapter(ad);

        langBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Localizer.this, LoginPage.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        Toast.makeText(getApplicationContext(), courses[position], Toast.LENGTH_LONG).show();
        String selectedLanguage = parent.getItemAtPosition(position).toString();
        setLocale(selectedLanguage);
//        context = LocaleHelper.setLocale(MainActivity.this, "hi");
//        resources = context.getResources();
//        messageView.setText(resources.getString(R.string.language));

    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void setLocale(String selectedLanguage) {
        Locale locale;
        switch (selectedLanguage) {
            case "Spanish":
                locale = new Locale("sp");
                break;
            case "Hindi":
                locale = new Locale("hi");
                break;
            default:
                locale = new Locale("en");
                break;
        }
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());// Restart the activity to apply the new locale
    }
}