package com.example.perfectmovie;

import java.util.List;

public class MovieList {
    private int total;
    private List<Movie> items;

    public MovieList(int total, List<Movie> items) {
        this.total = total;
        this.items = items;
    }

    public int getTotal() {
        return total;
    }

    public List<Movie> getItems() {
        return items;
    }
}
