package com.elysino.androidtask.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.elysino.androidtask.R;

public class CityFind extends AppCompatActivity {

    EditText editText;
    ImageView backButton;
    RelativeLayout btnSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_find);
        init();

        backButton.setOnClickListener(v -> {
                finish();
        });

        btnSearch.setOnClickListener(v -> {
            String newCity = editText.getText().toString();
            Intent intent = new Intent(CityFind.this, HomeActivity.class);
            intent.putExtra("City",newCity);
            startActivity(intent);
            finish();
        });
    }

    void init(){
        editText = findViewById(R.id.searchCity);
        backButton = findViewById(R.id.backButton);
        btnSearch=findViewById(R.id.btnSearch);
    }
}