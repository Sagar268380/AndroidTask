package com.elysino.androidtask.pressenter;

import android.view.View;

import com.elysino.androidtask.BuildConfig;
import com.elysino.androidtask.contract.HomeContract;
import com.elysino.androidtask.datamodel.Main;
import com.elysino.androidtask.retrofit.RetrofitClient;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePresenter implements HomeContract.Presenter {
    HomeContract.View homeView;

    public HomePresenter(HomeContract.View view) {
        this.homeView = homeView;
    }

    @Override
    public void start(String name,View layout) {
        getWeatherForNewCity(name,layout);
    }

    void getWeatherForNewCity(String cityName, View layout){
        if (homeView != null && homeView.isActive()) {
            homeView.showProgressBar();
        }
        homeView.showProgressBar();
        Call call = RetrofitClient.getInstance().getMyApi().getWeather(cityName, BuildConfig.API_KEY);
        call.enqueue(new Callback<Main>() {
            @Override
            public void onResponse(Call<Main> call, Response<Main> response) {
                if(response.code()==404){
                    if (homeView != null && homeView.isActive()) {
                        homeView.dismissProgressBar();
                    }
                    Snackbar snackbar = Snackbar
                            .make(layout, "City Not Found", Snackbar.LENGTH_LONG);
                    snackbar.show();
                    return;
                }
                else if(!(response.isSuccessful())){
                    if (homeView != null && homeView.isActive()) {
                        homeView.dismissProgressBar();
                    }
                    Snackbar snackbar = Snackbar
                            .make(layout, "City Not Found", Snackbar.LENGTH_LONG);
                    snackbar.show();
                   return;
                }
                if (response!=null && response.isSuccessful()){
                    if (homeView != null && homeView.isActive()) {
                        homeView.dismissProgressBar();
                    }
                    Main tempData=response.body();
                    String mIcon = updateWeatherIcon(tempData.getWeather().get(0).getId());
                    homeView.updateUI(tempData.getTemp(),tempData.getName(),tempData.getWeather().get(0).getMain(),mIcon);
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                if (homeView != null && homeView.isActive()) {
                    homeView.dismissProgressBar();
                }
            }
        });
    }

    @Override
    public String updateWeatherIcon(int condition) {
        if(condition >= 0 && condition <= 300)
        {
            return "thunderstrom1";
        }
        else if(condition >= 300 && condition <= 500)
        {
            return "lightrain";
        }
        else if(condition >= 500 && condition <= 600)
        {
            return "shower";
        }
        else if(condition >= 600 && condition <= 700)
        {
            return "snow2";
        }
        else if(condition >= 701 && condition <= 771)
        {
            return "fog";
        }
        else if(condition >= 772 && condition <= 800)
        {
            return "overcast";
        }
        else if(condition == 800)
        {
            return "sunny";
        }
        else if(condition >= 801 && condition <= 804)
        {
            return "cloudy";
        }
        else if(condition >= 900 && condition <= 902)
        {
            return "thunderstrom1";
        }
        else if(condition == 903)
        {
            return "snow1";
        }
        else if(condition == 904)
        {
            return "sunny";
        }
        else if(condition >= 905 && condition <= 1000)
        {
            return "thunderstrom2";
        }

        return "dunno";
    }
}
