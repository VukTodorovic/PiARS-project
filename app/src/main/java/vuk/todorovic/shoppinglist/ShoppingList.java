package vuk.todorovic.shoppinglist;

import java.io.Serializable;
import java.util.ArrayList;

public class ShoppingList implements Serializable {
    private String title;
    private boolean shared;

    private ArrayList<Article> articles;

    public ShoppingList(String title, boolean shared) {
        this.title = title;
        this.shared = shared;
        this.articles = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean getShared() {
        return shared;
    }

    public void setShared(boolean shared) {
        this.shared = shared;
    }

    public void addArticle(Article article) {
        articles.add(article);
    }

    public void addArticle(String name, boolean done) {
        Article article = new Article(name, done);
        articles.add(article);
    }

    public ArrayList<Article> getArticles() {
        return articles;
    }
}
