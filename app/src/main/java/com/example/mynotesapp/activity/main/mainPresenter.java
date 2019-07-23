package com.example.mynotesapp.activity.main;

import com.example.mynotesapp.api.ApiClient;
import com.example.mynotesapp.api.ApiInterface;
import com.example.mynotesapp.model.Note;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class mainPresenter {
    private mainView view;


    public mainPresenter(mainView view){
        this.view = view;
    }

    void getData(){

        view.showLoading();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Note>> call = apiInterface.getNotes();
        call.enqueue(new Callback<List<Note>>() {
            @Override
            public void onResponse(Call<List<Note>> call, Response<List<Note>> response) {
               view.hideLoading();
               if (response.isSuccessful() && response.body() != null){
                   view.onGetResult(response.body());
               }
            }

            @Override
            public void onFailure(Call<List<Note>> call, Throwable t) {
                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());
            }
        });
    }

}
