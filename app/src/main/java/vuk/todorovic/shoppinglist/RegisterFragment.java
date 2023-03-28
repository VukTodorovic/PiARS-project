package vuk.todorovic.shoppinglist;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterFragment extends Fragment {

    Button btnRegister;
    EditText etUsername, etPassword;


    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnRegister = view.findViewById(R.id.btnRegister);
        etUsername = view.findViewById(R.id.etUsername);
        etPassword = view.findViewById(R.id.etPassword);


        btnRegister.setOnClickListener(view_param -> {
            // Register user
            String inputUsername = etUsername.getText().toString();

            Intent intent = new Intent(requireActivity(), WelcomeActivity.class);
            intent.putExtra("username", inputUsername);
            startActivity(intent);
        });
    }
}