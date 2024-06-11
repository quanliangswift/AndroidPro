package com.example.myapplication3.model;

public class SwipeItem {
    private String id;
    private boolean isSwiped;

    public SwipeItem(String id, boolean isSwiped) {
        this.id = id;
        this.isSwiped = isSwiped;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isSwiped() {
        return isSwiped;
    }

    public void setSwiped(boolean swiped) {
        isSwiped = swiped;
    }
}
