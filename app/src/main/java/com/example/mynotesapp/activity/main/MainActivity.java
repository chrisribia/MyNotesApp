package com.example.mynotesapp.activity.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.mynotesapp.R;
import com.example.mynotesapp.activity.editor.EditActivity;
import com.example.mynotesapp.model.Note;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity implements mainView {
    FloatingActionButton fab;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;


    mainPresenter presenter;
    MainAdapter adapter;
    MainAdapter.ItemClickListener itemClickListener;

    List<Note> note;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipeRefreshLayout = findViewById(R.id.swipe_refresh);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        presenter = new mainPresenter(this);
        presenter.getData();
        swipeRefreshLayout.setOnRefreshListener(
                ()-> presenter.getData());

        itemClickListener = ((view, position) -> {
         String title = note.get(position).getTitle();
            Toast.makeText(this, title, Toast.LENGTH_SHORT).show();

        });


        fab = findViewById(R.id.add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, EditActivity.class));
            }
        });

    }

    @Override
    public void showLoading() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {


        swipeRefreshLayout.setRefreshing(false);

    }

    @Override
    public void onGetResult(List<Note> notes) {
    adapter = new MainAdapter(notes, this, itemClickListener);
    adapter.notifyDataSetChanged();
    recyclerView.setAdapter(adapter);
    note = notes;
    }

    @Override
    public void onErrorLoading(String message) {

    }
}
