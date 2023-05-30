package vuk.todorovic.shoppinglist;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class WelcomeActivity extends AppCompatActivity {
    String username;
    String init;
    TextView tvUsername;
    Button btnNewList;
    ListView lvShoppingLists;
    CustomShoppingListAdapter adapter;
    DatabaseHelper database_helper;
    ArrayList<ShoppingList> all_lists = new ArrayList<>();
    ArrayList<ShoppingList> my_lists = new ArrayList<>();
    Button btnSeeMyLists;
    Button btnSeeAllLists;
    Button btnSeeSharedLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        username = "user_name";
        tvUsername = findViewById(R.id.tvUsername);

        lvShoppingLists = findViewById(R.id.lvShoppingLists);

        btnNewList = findViewById(R.id.btnNewList);

        btnSeeMyLists = findViewById(R.id.btnSeeMyLists);

        btnSeeAllLists = findViewById(R.id.btnSeeAllLists);

        btnSeeSharedLists = findViewById(R.id.btnSeeSharedLists);

        // Postavi natpis na username korisnika dobijen preko intenta
        init = getIntent().getStringExtra("username");
        if (init != null) {
            username = init;
        }
        tvUsername.setText(username);

        // Postavi adapter i ucitaj liste iz baze
        adapter = new CustomShoppingListAdapter(this, username);
        lvShoppingLists.setAdapter(adapter);

        database_helper = new DatabaseHelper(this);
        all_lists = database_helper.loadLists(username);

        for(int i = 0; i < all_lists.size(); i++){
            adapter.addItem(all_lists.get(i));
        }


        // Klik na New List dugme
        btnNewList.setOnClickListener(view_param -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder
                    .setTitle(getString(R.string.alert_dialog_title))
                    .setMessage(getString(R.string.alert_dialog_message))
                    .setCancelable(false)
                    .setPositiveButton("Yes", (dialog, id) -> {
                        Intent intent = new Intent(WelcomeActivity.this, NewListActivity.class);
                        intent.putExtra("username", username);
                        startActivity(intent);
                        dialog.dismiss();
                    })
                    .setNegativeButton("No", (dialog, id) -> {
                        dialog.dismiss(); // Zatvori dijalog
                    });
            AlertDialog alert = builder.create();
            alert.show();
        });

        // Klik na See My Lists dugme
        btnSeeMyLists.setOnClickListener(view_param -> {
            my_lists = database_helper.loadMyLists(username);

            adapter.clearAllItems();

            for (int i = 0; i < my_lists.size(); i++) {
                adapter.addItem(my_lists.get(i));
            }
        });

        // Klik na See Shared Lists dugme
        btnSeeSharedLists.setOnClickListener(view_param -> {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        String getListUrl = "https://piars-server.cyclic.app/lists";

                        HttpHelper http_helper = new HttpHelper();
                        JSONArray shared_lists = http_helper.getJSONArrayFromURL(getListUrl);

                        if (shared_lists.length() > 0) {

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    adapter.clearAllItems();
                                }
                            });

                            for (int i = 0; i < shared_lists.length(); i++) {
                                JSONObject jsonObject = shared_lists.getJSONObject(i);
                                String name = jsonObject.getString("name");
                                boolean shared = jsonObject.getBoolean("shared");
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        adapter.addItem(new ShoppingList(username, name, shared));
                                    }
                                });
                            }
                        }
                    }catch (IOException | JSONException e){
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
        });

        // Klik na See All Lists dugme
        btnSeeAllLists.setOnClickListener(view_param -> {
            all_lists = database_helper.loadLists(username);

            adapter.clearAllItems();

            for (int i = 0; i < all_lists.size(); i++){
                adapter.addItem(all_lists.get(i));
            }
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


    @Override
    protected void onResume() {

        super.onResume();

        all_lists = database_helper.loadLists(username);
        my_lists = database_helper.loadMyLists(username);

        adapter.clearAllItems();

        for (int i = 0; i < all_lists.size(); i++) {
            adapter.addItem(all_lists.get(i));
        }

    }

}