package com.elysino.androidtask.contract;

import android.content.Intent;

import com.elysino.androidtask.datamodel.Temp;


public interface HomeContract {
    interface View{
        void showProgressBar();
        void dismissProgressBar();
        void moveToNextScreen(Intent intent,Boolean isFinish);
        Boolean isActive();
        void updateUI(Temp temp, String name, String weatherType, String mIcon);
    }

    interface Presenter{
        void start(String name, android.view.View layout);
        String updateWeatherIcon(int id);
    }
}
