package com.example.mynotesapp.activity.editor;

import android.widget.Toast;

import com.example.mynotesapp.api.ApiClient;
import com.example.mynotesapp.api.ApiInterface;
import com.example.mynotesapp.model.Note;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditorPresenter {

    private  editorView view;
    public EditorPresenter(editorView view ){
        this.view = view;
    }

     void saveNotes(final String title,final String note,final  int color) {
        view.showProgress();
       ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Note> call = apiInterface.saveNote(title,note,color);
        call.enqueue(new Callback<Note>() {
            @Override
            public void onResponse(Call<Note> call, Response<Note> response) {
                view.hideProgress();
                Boolean res = response.body().getSuccess();
                if (res){
                    view.onaddSuccess(response.body().getMessage());

                }else{

                    view.onAddError(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<Note> call, Throwable t) {
                view.hideProgress();
                view.onAddError(t.getMessage());
            }
        });
    }
}
