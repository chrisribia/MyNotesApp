package com.example.mynotesapp.activity.editor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mynotesapp.R;
import com.example.mynotesapp.api.ApiClient;
import com.example.mynotesapp.api.ApiInterface;
import com.example.mynotesapp.model.Note;
import com.thebluealliance.spectrum.SpectrumPalette;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditActivity extends AppCompatActivity implements editorView{
    private EditText ed_title,ed_note;
    ProgressDialog progressDialog;
    ApiInterface apiInterface;
    SpectrumPalette palette;
    EditorPresenter editorPresenter;

    int color;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        ed_title = findViewById(R.id.title);
        ed_note = findViewById(R.id.note);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait ...");
        palette = findViewById(R.id.pallete);
        editorPresenter = new EditorPresenter(this);


        palette.setOnColorSelectedListener(
                clr -> color = clr
        );

        palette.setSelectedColor(getResources().getColor(R.color.white));
        color = getResources().getColor(R.color.white);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_editor,menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       switch (item.getItemId()){
           case R.id.save:

               String title = ed_title.getText().toString().trim();
               String  note = ed_note.getText().toString().trim();
               int  color = this.color;

               if (title.isEmpty()){
                   ed_title.setError("Please enter a title...");
               }
               else if (note.isEmpty()){
                   ed_note.setError("please enter a note ..");
               }else {
                   editorPresenter.saveNotes(title, note, color);
               }


               return true;
               default:

                   return super.onOptionsItemSelected(item);
       }

    }

    @Override
    public void showProgress() {
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.hide();

    }

    @Override
    public void onaddSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onAddError(String message) {

        Toast.makeText(this, message, Toast.LENGTH_LONG).show();

    }
}
