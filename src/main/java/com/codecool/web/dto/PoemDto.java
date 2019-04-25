package com.codecool.web.dto;

import com.codecool.web.model.Poem;

import java.util.List;

public class PoemDto {
    private final Poem poem;

    public PoemDto(Poem poem) {
        this.poem = poem;
    }

    public Poem getPoem() {
        return poem;
    }
}
