package com.elysino.androidtask.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.elysino.androidtask.R;
import com.elysino.androidtask.contract.HomeContract;
import com.elysino.androidtask.datamodel.Temp;
import com.elysino.androidtask.pressenter.HomePresenter;

public class HomeActivity extends AppCompatActivity implements HomeContract.View {
    TextView nameOfCity, weatherState, Temperature;
    ImageView weatherIcon;
    RelativeLayout mCityFinder,layout;
    ProgressDialog progressDialog;
    HomeContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e) {}
        init();
        presenter = new HomePresenter(this);
        mCityFinder.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, CityFind.class);
            moveToNextScreen(intent,false);
        });
    }

    void init() {
        layout=findViewById(R.id.layout);
        weatherState = findViewById(R.id.weatherCondition);
        Temperature = findViewById(R.id.temperature);
        weatherIcon = findViewById(R.id.weatherIcon);
        mCityFinder = findViewById(R.id.cityFinder);
        nameOfCity = findViewById(R.id.cityName);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent mIntent=getIntent();
        String city= mIntent.getStringExtra("City");
        if(city!=null) {
            presenter.start(city,layout);
        }
    }

    @Override
    public void showProgressBar() {
        progressDialog=new ProgressDialog(this);
        progressDialog.show();
        progressDialog.setCancelable(false);
        progressDialog.setContentView(R.layout.prgogessdialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
    }

    @Override
    public void dismissProgressBar() {
        progressDialog.dismiss();
    }

    @Override
    public void moveToNextScreen(Intent intent, Boolean isFinish) {
        if (isFinish) {
            startActivity(intent);
            finish();
        } else {
            startActivity(intent);
        }
    }

    @Override
    public Boolean isActive() {
        return !isFinishing();
    }

    @Override
    public void updateUI(Temp main, String name, String weatherType, String mIcon) {
        Double temp=main.getTemp()-273.15;
        int roundedValue = (int)Math.rint(temp);
        String temp1 = Integer.toString(roundedValue);
        Temperature.setText(temp1+"°C");
        nameOfCity.setText(name);
        weatherState.setText(weatherType);
        int resourseID = getResources().getIdentifier(mIcon,"drawable",getPackageName());
        weatherIcon.setImageResource(resourseID);
    }
}