package vuk.todorovic.shoppinglist;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ShowListActivity extends AppCompatActivity {
    private ShoppingList shoppingList;
    private ArrayList<Article> articles;
    ListView lvArticleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list);

        // Get intent data
        try {
            shoppingList = (ShoppingList) getIntent().getSerializableExtra("shoppingList");
        } catch (Exception e) {
            Toast.makeText(this, "Data error", Toast.LENGTH_LONG).show();
        }

        // Populate ListView with data
        lvArticleList = findViewById(R.id.lvArticleList);
        articles = shoppingList.getArticles();

        CustomArticleListAdapter adapter = new CustomArticleListAdapter(articles, this);
        lvArticleList.setAdapter(adapter);
    }
}