package vuk.todorovic.shoppinglist;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class ShowListActivity extends AppCompatActivity {
    private ShoppingList shoppingList;
    private ArrayList<Article> articles;
    ListView lvArticleList;
    TextView tvListTitle;
    EditText etArticleName;
    Button btnAddNewArticle;
    Button btnRefresh;
    String title;
    boolean shared;
    CustomArticleListAdapter adapter;
    ArrayList<Article> tasks = new ArrayList<>();
    HttpHelper http_helper;
    DatabaseHelper database_helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        database_helper = new DatabaseHelper(this);
        http_helper = new HttpHelper();

        btnAddNewArticle = findViewById(R.id.btnAddNewArticle);
        btnRefresh = findViewById(R.id.btnRefresh);

        tvListTitle = findViewById(R.id.tvListTitle);
        title = getIntent().getStringExtra("title");
        tvListTitle.setText(title);
        shared = getIntent().getBooleanExtra("shared", false);

        if (shared) {
            btnRefresh.setVisibility(View.VISIBLE);
        } else {
            btnRefresh.setVisibility(View.GONE);
        }

        adapter = new CustomArticleListAdapter(this);
        lvArticleList = findViewById(R.id.lvArticleList);
        lvArticleList.setAdapter(adapter);

        etArticleName = findViewById(R.id.etArticleName);

        tasks = database_helper.loadTasks(title);

        for (int i = 0; i < tasks.size(); i++) {
            adapter.addItem(tasks.get(i));
        }


        btnAddNewArticle.setOnClickListener(view -> {
            String articleName = etArticleName.getText().toString();
            if (articleName.isEmpty()) {

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Error");
                builder.setMessage("Title empty!");
                builder.setPositiveButton("Ok", null);
                AlertDialog dialog = builder.create();
                dialog.show();

            } else {

                String rand_id = generateRandomString(16);

                database_helper.addTask(articleName, title, rand_id, 0);

                if (shared) {
                    http_helper.addTask(articleName, title, rand_id);
                }

                tasks = database_helper.loadTasks(title);
                adapter.clearTasks();

                for (int i = 0; i < tasks.size(); i++) {
                    adapter.addItem(tasks.get(i));
                }
            }
        });

        btnRefresh.setOnClickListener(view -> {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.clearTasks();
                        }
                    });
                    try {

                        HttpHelper http_helper = new HttpHelper();
                        JSONArray all_tasks = http_helper.getJSONArrayFromURL("https://piars-server.cyclic.app/tasks/" + title);

                        for (int i = 0; i < all_tasks.length(); i++) {

                            JSONObject jsonObject = all_tasks.getJSONObject(i);
                            boolean check = jsonObject.getBoolean("done");
                            String taskId = jsonObject.getString("taskId");
                            String name = jsonObject.getString("name");
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    adapter.addItem(new Article(title, taskId, name, check));
                                }
                            });
                        }
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == android.R.id.home) {
            // Handle the home button click
//            Intent intent = new Intent(this, MainActivity.class);
//            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent event) {
//
//        if (event.getAction() == MotionEvent.ACTION_DOWN) {
//
//            View v = getCurrentFocus();
//
//            if (v instanceof EditText) {
//
//                Rect outRect = new Rect();
//                v.getGlobalVisibleRect(outRect);
//
//                if (!outRect.contains((int) event.getRawX(), (int) event.getRawY())) {
//
//                    v.clearFocus();
//                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
//
//                }
//            }
//        }
//
//        return super.dispatchTouchEvent(event);
//
//    }
    public static String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append((char) ('a' + random.nextInt(26)));
        }
        return sb.toString();

    }
}