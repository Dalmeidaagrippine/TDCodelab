package com.example.tdcodelab;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String BASE_URL = "https://pokeapi.co";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private SharedPreferences sharedPreferences;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences ("application_mobile", Context.MODE_PRIVATE);
            gson = new GsonBuilder()
                .setLenient()
                .create();

        makeApiCall();
    }

    private void showList(List<Pokemon> pokemonList) {
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new Adapter(pokemonList);
        recyclerView.setAdapter(mAdapter);
    }

    private void makeApiCall() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        PokeApi pokeApi = retrofit.create(PokeApi.class);

        Call<RestPokemonResponse> call = pokeApi.getPokemonResponse();
        call.enqueue(new Callback<RestPokemonResponse>() {

            @Override
            public void onResponse(Call<RestPokemonResponse> call, Response<RestPokemonResponse> response) {
                if(response.isSuccessful() && response.body() != null){
                    List<Pokemon> pokemonList = response.body().getResults();
                    savedList(pokemonList);
                    showList(pokemonList);
                }
                else{
                    showError();
                }
            }

            @Override
            public void onFailure(Call<RestPokemonResponse> call, Throwable t) {
                showError();
            }
        });
    }

    private void savedList(List<Pokemon> pokemonList) {
        String jsonString = gson.toJson(pokemonList);

            sharedPreferences
                .edit()
                .putString("jsonPokemonList", jsonString)
                .apply();
    }

    private void showError() {
        Toast.makeText(getApplicationContext(), "API Error", Toast.LENGTH_SHORT).show();
    }
}