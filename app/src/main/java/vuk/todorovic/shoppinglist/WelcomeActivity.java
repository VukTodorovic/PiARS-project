package vuk.todorovic.shoppinglist;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class WelcomeActivity extends AppCompatActivity {
    String username;
    TextView tvUsername;
    Button btnNewList;
    Button btnSeeMyLists;

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
            Intent intent = new Intent(WelcomeActivity.this, NewListActivity.class);
            startActivity(intent);
        });

        // Klik na See My Lists dugme
        btnSeeMyLists.setOnClickListener(view_param -> {
            Toast.makeText(this, "Will be done in chekpoint 2", Toast.LENGTH_LONG).show();
        });

    }
}