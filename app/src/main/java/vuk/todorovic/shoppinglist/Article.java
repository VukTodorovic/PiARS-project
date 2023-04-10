package vuk.todorovic.shoppinglist;

import java.io.Serializable;

public class Article implements Serializable {
    private String name;
    private boolean done;

    public Article(String name, boolean done) {
        this.name = name;
        this.done = done;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
