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
            Toast.makeText(this, "Will be done in chekpoint 3", Toast.LENGTH_LONG).show();
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
        shoppingList2.addArticle("Pencil", true);
        shoppingList2.addArticle("Books", false);
        shoppingList2.addArticle("Scissors", true);
        shoppingLists.add(shoppingList2);

        ShoppingList shoppingList3 = new ShoppingList("Home improvement shopping", true);
        shoppingList3.addArticle("Sofa", true);
        shoppingList3.addArticle("Painting Picasso", false);
        shoppingList3.addArticle("Painting Rembrant", true);
        shoppingLists.add(shoppingList3);

        ShoppingList shoppingList4 = new ShoppingList("Holiday gift shopping", false);
        shoppingList4.addArticle("Cabbage", true);
        shoppingList4.addArticle("Milk", false);
        shoppingList4.addArticle("Carrots", true);
        shoppingList4.addArticle("Steak", false);
        shoppingList4.addArticle("Vinegar", true);
        shoppingLists.add(shoppingList4);

        ShoppingList shoppingList5 = new ShoppingList("Farmers' market shopping", true);
        shoppingList5.addArticle("Cabbage", true);
        shoppingList5.addArticle("Milk", false);
        shoppingList5.addArticle("Carrots", true);
        shoppingList5.addArticle("Steak", false);
        shoppingList5.addArticle("Vinegar", true);
        shoppingLists.add(shoppingList5);

        ShoppingList shoppingList6 = new ShoppingList("Pet supplies shopping", false);
        shoppingList6.addArticle("Cabbage", true);
        shoppingList6.addArticle("Milk", false);
        shoppingList6.addArticle("Carrots", true);
        shoppingList6.addArticle("Steak", false);
        shoppingList6.addArticle("Vinegar", true);
        shoppingLists.add(shoppingList6);

        ShoppingList shoppingList7 = new ShoppingList("Office supplies shopping", true);
        shoppingList7.addArticle("Cabbage", true);
        shoppingList7.addArticle("Milk", false);
        shoppingList7.addArticle("Carrots", true);
        shoppingList7.addArticle("Steak", false);
        shoppingList7.addArticle("Vinegar", true);
        shoppingLists.add(shoppingList7);

        ShoppingList shoppingList8 = new ShoppingList("Baby essentials shopping", false);
        shoppingList8.addArticle("Cabbage", true);
        shoppingList8.addArticle("Milk", false);
        shoppingList8.addArticle("Carrots", true);
        shoppingList8.addArticle("Steak", false);
        shoppingList8.addArticle("Vinegar", true);
        shoppingLists.add(shoppingList8);

        ShoppingList shoppingList9 = new ShoppingList("Fitness and health shopping", true);
        shoppingList9.addArticle("Cabbage", true);
        shoppingList9.addArticle("Milk", false);
        shoppingList9.addArticle("Carrots", true);
        shoppingList9.addArticle("Steak", false);
        shoppingList9.addArticle("Vinegar", true);
        shoppingLists.add(shoppingList9);

        ShoppingList shoppingList10 = new ShoppingList("Gardening supplies shopping", false);
        shoppingList10.addArticle("Cabbage", true);
        shoppingList10.addArticle("Milk", false);
        shoppingList10.addArticle("Carrots", true);
        shoppingList10.addArticle("Steak", false);
        shoppingList10.addArticle("Vinegar", true);
        shoppingLists.add(shoppingList10);

        ShoppingList shoppingList11 = new ShoppingList("Party supplies shopping", true);
        shoppingList11.addArticle("Cabbage", true);
        shoppingList11.addArticle("Milk", false);
        shoppingList11.addArticle("Carrots", true);
        shoppingList11.addArticle("Steak", false);
        shoppingList11.addArticle("Vinegar", true);
        shoppingLists.add(shoppingList11);

        ShoppingList shoppingList12 = new ShoppingList("Clothing and accessories shopping", true);
        shoppingList12.addArticle("Cabbage", true);
        shoppingList12.addArticle("Milk", false);
        shoppingList12.addArticle("Carrots", true);
        shoppingList12.addArticle("Steak", false);
        shoppingList12.addArticle("Vinegar", true);
        shoppingLists.add(shoppingList12);

        ShoppingList shoppingList13 = new ShoppingList("Home decor shopping", true);
        shoppingList13.addArticle("Cabbage", true);
        shoppingList13.addArticle("Milk", false);
        shoppingList13.addArticle("Carrots", true);
        shoppingList13.addArticle("Steak", false);
        shoppingList13.addArticle("Vinegar", true);
        shoppingLists.add(shoppingList13);

        ShoppingList shoppingList14 = new ShoppingList("Technology shopping", true);
        shoppingList14.addArticle("Cabbage", true);
        shoppingList14.addArticle("Milk", false);
        shoppingList14.addArticle("Carrots", true);
        shoppingList14.addArticle("Steak", false);
        shoppingList14.addArticle("Vinegar", true);
        shoppingLists.add(shoppingList14);

        ShoppingList shoppingList15 = new ShoppingList("Car maintenance shopping", true);
        shoppingList15.addArticle("Cabbage", true);
        shoppingList15.addArticle("Milk", false);
        shoppingList15.addArticle("Carrots", true);
        shoppingList15.addArticle("Steak", false);
        shoppingList15.addArticle("Vinegar", true);
        shoppingLists.add(shoppingList15);

        CustomShoppingListAdapter adapter = new CustomShoppingListAdapter(shoppingLists, this);
        lvShoppingLists.setAdapter(adapter);
    }
}