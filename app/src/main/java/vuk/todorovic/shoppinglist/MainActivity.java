package vuk.todorovic.shoppinglist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Fragments
        MainFragment mainFragment = new MainFragment();
        // LoginFragment loginFragment = new LoginFragment();
        // RegisterFragment registerFragment = new RegisterFragment();

        // Start service
        Intent serviceIntent = new Intent(this, DatabaseSyncService.class);
        startService(serviceIntent);

        // Load MainFragment into Frame Layout
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.flFragment, mainFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}