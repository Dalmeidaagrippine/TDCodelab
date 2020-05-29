package com.example.tdcodelab.presentation.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tdcodelab.R;
import com.squareup.picasso.Picasso;

public class PokemonDetails extends AppCompatActivity {

    TextView textPokemonName;
    ImageView showPokemonImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pokemon_details);
        textPokemonName = (TextView) findViewById(R.id.name);
        showPokemonImage = (ImageView) findViewById(R.id.pokeImage);

        Intent intent = getIntent();
        String nameRecovery = intent.getStringExtra("pokemonName");
        String urlRecovery = intent.getStringExtra("pokemonUrl");
        String ID = String.valueOf(this.getID(urlRecovery));

        Log.d("ID: ", ID);

        textPokemonName.setText(nameRecovery);
        Picasso.get().load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + ID + ".png").into(showPokemonImage);

    }

    public int getID(String url){
        String[] urlParsed = url.split("/");
        return Integer.parseInt(urlParsed[urlParsed.length - 1]);
    }
}