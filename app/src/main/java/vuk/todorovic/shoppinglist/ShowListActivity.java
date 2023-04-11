package vuk.todorovic.shoppinglist;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ShowListActivity extends AppCompatActivity {
    private ShoppingList shoppingList;
    private ArrayList<Article> articles;
    ListView lvArticleList;
    TextView tvListTitle;
    Button btnAddArticle;
    EditText etArticleName;

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

        // Set shopping list title
        tvListTitle = findViewById(R.id.tvListTitle);
        tvListTitle.setText(shoppingList.getTitle());

        // Populate ListView with data
        lvArticleList = findViewById(R.id.lvArticleList);
        articles = shoppingList.getArticles();
        CustomArticleListAdapter adapter = new CustomArticleListAdapter(articles, this);
        lvArticleList.setAdapter(adapter);

        // Handle onclick for adding new Article
        btnAddArticle = findViewById(R.id.btnAddNewArticle);
        etArticleName = findViewById(R.id.etArticleName);
        btnAddArticle.setOnClickListener(view -> {
            String articleName = etArticleName.getText().toString();
            if(!articleName.isEmpty()){
                Article article = new Article(articleName, false);
                articles.add(0, article);
                adapter.notifyDataSetChanged();
            }
            Toast.makeText(this, getResources().getString(R.string.name_validation_error), Toast.LENGTH_SHORT).show();
        });
    }
}