package com.example.perfectmovie;

import java.util.List;

public class StaffList {
    private int total;
    private List<Staff> items;

    public StaffList(int total, List<Staff> items) {
        this.total = total;
        this.items = items;
    }

    public int getTotal() {
        return total;
    }

    public List<Staff> getItems() {
        return items;
    }
}
