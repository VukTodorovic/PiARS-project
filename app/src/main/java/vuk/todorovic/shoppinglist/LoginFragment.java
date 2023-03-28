package vuk.todorovic.shoppinglist;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginFragment extends Fragment {
    EditText etUsername, etPassword;
    Button btnLogin;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etUsername = view.findViewById(R.id.etUsername);
        etPassword = view.findViewById(R.id.etPassword);
        btnLogin = view.findViewById(R.id.btnLogin);


        btnLogin.setOnClickListener(view_param -> {
            // Verify login credentials
            String testUsername = "admin";
            String testPassword = "admin";

            String inputUsername = etUsername.getText().toString();
            String inputPassword = etPassword.getText().toString();

            if(inputUsername.equals(testUsername) && inputPassword.equals(testPassword)) {
                Intent intent = new Intent(requireActivity(), WelcomeActivity.class);
                intent.putExtra("username", inputUsername);
                startActivity(intent);
            }
            else {
                Toast.makeText(requireActivity(), getResources().getString(R.string.invalid_credentials), Toast.LENGTH_SHORT).show();
            }
        });
    }
}


