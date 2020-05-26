package com.example.tdcodelab.presentation.controller;

import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.tdcodelab.Constants;
import com.example.tdcodelab.Singletons;
import com.example.tdcodelab.presentation.model.Pokemon;
import com.example.tdcodelab.presentation.model.RestPokemonResponse;
import com.example.tdcodelab.presentation.view.MainActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainController {

    private SharedPreferences sharedPreferences;
    private Gson gson;
    private MainActivity view;

    public MainController(MainActivity mainActivity, Gson gson, SharedPreferences sharedPreferences)
    {
        this.view = mainActivity;
        this.gson = gson;
        this.sharedPreferences = sharedPreferences;

    }
    public MainController() {
    }

    public void onStart() {

        List<Pokemon> pokemonList = getData();

        if(pokemonList != null ){
            makeApiCall();
            view.showList (pokemonList);
        }
        else{
            makeApiCall();
        }
    }

    private void makeApiCall() {
        Call<RestPokemonResponse> call = Singletons.getPokeApi().getPokemonResponse();
        call.enqueue(new Callback<RestPokemonResponse>() {

            @Override
            public void onResponse(Call<RestPokemonResponse> call, Response<RestPokemonResponse> response) {
                if(response.isSuccessful() && response.body() != null){
                    List<Pokemon> pokemonList = response.body().getResults();
                    savedList(pokemonList);
                    view.showList(pokemonList);
                }
                else{
                    view.showError();
                }
            }

            @Override
            public void onFailure(Call<RestPokemonResponse> call, Throwable t) {
                view.showError();
            }
        });
    }

    private void savedList(List<Pokemon> pokemonList) {
        String jsonString = gson.toJson(pokemonList);

        sharedPreferences
                .edit()
                .putString(Constants.KEY_POKEMON_LIST, jsonString)
                .apply();

        Toast.makeText(view.getApplicationContext(), "List saved", Toast.LENGTH_SHORT).show();
    }

    private List<Pokemon> getData() {

        String jsonPokemon = sharedPreferences.getString(Constants.KEY_POKEMON_LIST , null);

        if(jsonPokemon == null) {
            return null;
        }
        else {
            Type listType = new TypeToken<List<Pokemon>>(){}.getType();
            return gson.fromJson(jsonPokemon,  listType);
        }
    }

    public void onItemClick(Pokemon pokemon){

     }
     public void onButtonAClick(){

     }
     public void onButtonBClick(){

     }
}

