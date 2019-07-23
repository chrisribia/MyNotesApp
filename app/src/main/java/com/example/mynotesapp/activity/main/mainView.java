package com.example.mynotesapp.activity.main;

import com.example.mynotesapp.model.Note;

import java.util.List;

public interface mainView {
    void showLoading();
    void hideLoading();
    void onGetResult(List<Note> notes);
    void onErrorLoading(String message);
}
