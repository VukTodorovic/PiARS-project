package vuk.todorovic.shoppinglist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class NewListActivity extends AppCompatActivity {

    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_list);


        btnSave = findViewById(R.id.btnSave);

        // Klik na Save dugme
        btnSave.setOnClickListener(view_param -> {
            finish();
        });
    }
}