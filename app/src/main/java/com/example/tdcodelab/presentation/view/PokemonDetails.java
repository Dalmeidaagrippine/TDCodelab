package com.example.tdcodelab.presentation.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tdcodelab.R;

public class PokemonDetails extends AppCompatActivity {

    TextView textPokemonName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pokemon_details);
        textPokemonName = (TextView) findViewById(R.id.name);
        Intent intent = getIntent();
        String nameRecovery = intent.getStringExtra("pokemonName");
        String urlRecovery = intent.getStringExtra("pokemonUrl");
        textPokemonName.setText(nameRecovery);
    }
}