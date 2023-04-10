package vuk.todorovic.shoppinglist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class ShowListActivity extends AppCompatActivity {
    private ArrayList<Article> articles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list);

        // Get intent data
        try {
            articles = (ArrayList<Article>) getIntent().getSerializableExtra("articles");
            String testText = articles.get(0).getName();
            Toast.makeText(this, testText, Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(this, "Data error", Toast.LENGTH_LONG).show();
        }


    }
}