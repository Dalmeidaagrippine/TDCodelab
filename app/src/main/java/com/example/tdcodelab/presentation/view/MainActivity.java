package com.example.tdcodelab.presentation.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tdcodelab.R;
import com.example.tdcodelab.Singletons;
import com.example.tdcodelab.presentation.controller.MainController;
import com.example.tdcodelab.presentation.model.Pokemon;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private MainController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

           controller = new MainController(
                   this,
                   Singletons.getGson(),
                   getSharedPreferences ("application_mobile", Context.MODE_PRIVATE)
           );
        controller.onStart();
    }

    public void showList(List<Pokemon> pokemonList) {
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new Adapter(pokemonList, new Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(Pokemon item) {
                navigateToDetails(item);
            }
        });
        recyclerView.setAdapter(mAdapter);
    }

    private void navigateToDetails(Pokemon item){
        Intent intent = new Intent(MainActivity.this, PokemonDetails.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("pokemonName", item.getName());
        intent.putExtra("pokemonUrl", item.getUrl());
        MainActivity.this.startActivity(intent);


    }
    public void showError() {
        Toast.makeText(getApplicationContext(), "API Error", Toast.LENGTH_SHORT).show();
    }
}