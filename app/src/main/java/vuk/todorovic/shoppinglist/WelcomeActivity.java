package vuk.todorovic.shoppinglist;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class WelcomeActivity extends AppCompatActivity {
    String username;
    TextView tvUsername;
    Button btnNewList;
    Button btnSeeMyLists;
    ListView lvShoppingLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        username = "user_name";
        tvUsername = findViewById(R.id.tvUsername);
        btnNewList = findViewById(R.id.btnNewList);
        btnSeeMyLists = findViewById(R.id.btnSeeMyLists);

        // Postavi natpis na username korisnika dobijen preko intenta
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            username = extras.getString("username");
        }

        tvUsername.setText(username);


        // Klik na New List dugme
        btnNewList.setOnClickListener(view_param -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder
                    .setTitle(getString(R.string.alert_dialog_title))
                    .setMessage(getString(R.string.alert_dialog_message))
                    .setCancelable(false)
                    .setPositiveButton("Yes", (dialog, id) -> {
                        Intent intent = new Intent(WelcomeActivity.this, NewListActivity.class);
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
            Toast.makeText(this, "Will be done in chekpoint 2", Toast.LENGTH_LONG).show();
        });

        // Populateovanje liste
        lvShoppingLists = findViewById(R.id.lvShoppingLists);
        ArrayList<ShoppingList> shoppingLists = new ArrayList<>();

        // Fake data
        ShoppingList shoppingList1 = new ShoppingList("Weekly grocery shopping", true);
        shoppingList1.addArticle("Cabbage", true);
        shoppingList1.addArticle("Milk", false);
        shoppingList1.addArticle("Carrots", true);
        shoppingList1.addArticle("Steak", false);
        shoppingList1.addArticle("Vinegar", true);
        shoppingLists.add(shoppingList1);

        ShoppingList shoppingList2 = new ShoppingList("Back-to-school supplies", false);
        shoppingList1.addArticle("Pencil", true);
        shoppingList1.addArticle("Books", false);
        shoppingList1.addArticle("Scissors", true);
        shoppingLists.add(shoppingList2);

        ShoppingList shoppingList3 = new ShoppingList("Home improvement shopping", true);
        shoppingList1.addArticle("Sofa", true);
        shoppingList1.addArticle("Painting Picasso", false);
        shoppingList1.addArticle("Painting Rembrant", true);
        shoppingLists.add(shoppingList3);

        ShoppingList shoppingList4 = new ShoppingList("Holiday gift shopping", false);
        shoppingList1.addArticle("Cabbage", true);
        shoppingList1.addArticle("Milk", false);
        shoppingList1.addArticle("Carrots", true);
        shoppingList1.addArticle("Steak", false);
        shoppingList1.addArticle("Vinegar", true);
        shoppingLists.add(shoppingList4);

        ShoppingList shoppingList5 = new ShoppingList("Farmers' market shopping", true);
        shoppingList1.addArticle("Cabbage", true);
        shoppingList1.addArticle("Milk", false);
        shoppingList1.addArticle("Carrots", true);
        shoppingList1.addArticle("Steak", false);
        shoppingList1.addArticle("Vinegar", true);
        shoppingLists.add(shoppingList5);

        ShoppingList shoppingList6 = new ShoppingList("Pet supplies shopping", false);
        shoppingList1.addArticle("Cabbage", true);
        shoppingList1.addArticle("Milk", false);
        shoppingList1.addArticle("Carrots", true);
        shoppingList1.addArticle("Steak", false);
        shoppingList1.addArticle("Vinegar", true);
        shoppingLists.add(shoppingList6);

        ShoppingList shoppingList7 = new ShoppingList("Office supplies shopping", true);
        shoppingList1.addArticle("Cabbage", true);
        shoppingList1.addArticle("Milk", false);
        shoppingList1.addArticle("Carrots", true);
        shoppingList1.addArticle("Steak", false);
        shoppingList1.addArticle("Vinegar", true);
        shoppingLists.add(shoppingList7);

        ShoppingList shoppingList8 = new ShoppingList("Baby essentials shopping", false);
        shoppingList1.addArticle("Cabbage", true);
        shoppingList1.addArticle("Milk", false);
        shoppingList1.addArticle("Carrots", true);
        shoppingList1.addArticle("Steak", false);
        shoppingList1.addArticle("Vinegar", true);
        shoppingLists.add(shoppingList8);

        ShoppingList shoppingList9 = new ShoppingList("Fitness and health shopping", true);
        shoppingList1.addArticle("Cabbage", true);
        shoppingList1.addArticle("Milk", false);
        shoppingList1.addArticle("Carrots", true);
        shoppingList1.addArticle("Steak", false);
        shoppingList1.addArticle("Vinegar", true);
        shoppingLists.add(shoppingList9);

        ShoppingList shoppingList10 = new ShoppingList("Gardening supplies shopping", false);
        shoppingList1.addArticle("Cabbage", true);
        shoppingList1.addArticle("Milk", false);
        shoppingList1.addArticle("Carrots", true);
        shoppingList1.addArticle("Steak", false);
        shoppingList1.addArticle("Vinegar", true);
        shoppingLists.add(shoppingList10);

        ShoppingList shoppingList11 = new ShoppingList("Party supplies shopping", true);
        shoppingList1.addArticle("Cabbage", true);
        shoppingList1.addArticle("Milk", false);
        shoppingList1.addArticle("Carrots", true);
        shoppingList1.addArticle("Steak", false);
        shoppingList1.addArticle("Vinegar", true);
        shoppingLists.add(shoppingList11);

        ShoppingList shoppingList12 = new ShoppingList("Clothing and accessories shopping", true);
        shoppingList1.addArticle("Cabbage", true);
        shoppingList1.addArticle("Milk", false);
        shoppingList1.addArticle("Carrots", true);
        shoppingList1.addArticle("Steak", false);
        shoppingList1.addArticle("Vinegar", true);
        shoppingLists.add(shoppingList12);

        ShoppingList shoppingList13 = new ShoppingList("Home decor shopping", true);
        shoppingList1.addArticle("Cabbage", true);
        shoppingList1.addArticle("Milk", false);
        shoppingList1.addArticle("Carrots", true);
        shoppingList1.addArticle("Steak", false);
        shoppingList1.addArticle("Vinegar", true);
        shoppingLists.add(shoppingList13);

        ShoppingList shoppingList14 = new ShoppingList("Technology shopping", true);
        shoppingList1.addArticle("Cabbage", true);
        shoppingList1.addArticle("Milk", false);
        shoppingList1.addArticle("Carrots", true);
        shoppingList1.addArticle("Steak", false);
        shoppingList1.addArticle("Vinegar", true);
        shoppingLists.add(shoppingList14);

        ShoppingList shoppingList15 = new ShoppingList("Car maintenance shopping", true);
        shoppingList1.addArticle("Cabbage", true);
        shoppingList1.addArticle("Milk", false);
        shoppingList1.addArticle("Carrots", true);
        shoppingList1.addArticle("Steak", false);
        shoppingList1.addArticle("Vinegar", true);
        shoppingLists.add(shoppingList15);

        CustomShoppingListAdapter adapter = new CustomShoppingListAdapter(shoppingLists, this);
        lvShoppingLists.setAdapter(adapter);
    }
}