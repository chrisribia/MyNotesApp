package com.example.mynotesapp.activity.editor;

public interface editorView {
    void showProgress();
    void hideProgress();
    void onaddSuccess(String message);
    void onAddError(String message);
}
