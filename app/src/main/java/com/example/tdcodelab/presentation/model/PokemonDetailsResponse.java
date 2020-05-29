package com.example.tdcodelab.presentation.model;

import java.util.List;

public class PokemonDetailsResponse {

    private Integer count;
    private String next;
    private List<PokemonDetails> results;

    public Integer getCount() {
        return count;
    }

    public String getNext() {
        return next;
    }

    public List<PokemonDetails> getResults() {
        return results;
    }
}
