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

import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;

public class RegisterFragment extends Fragment {

    Button btnRegister;
    EditText etUsername, etPassword, etEmail;
    DatabaseHelper database_helper;


    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database_helper = new DatabaseHelper(getActivity());
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
        etEmail = view.findViewById(R.id.etEmail);


//        btnRegister.setOnClickListener(view_param -> {
//            // Register user
//            String inputUsername = etUsername.getText().toString();
//
//            Intent intent = new Intent(requireActivity(), WelcomeActivity.class);
//            intent.putExtra("username", inputUsername);
//            startActivity(intent);
//        });
        btnRegister.setOnClickListener(view_param -> {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        String registrationUrl = "https://piars-server.cyclic.app/users";
                        JSONObject userJson = new JSONObject();
                        userJson.put("username", etUsername.getText().toString());
                        userJson.put("password", etPassword.getText().toString());
                        userJson.put("email", etEmail.getText().toString());

                        HttpHelper http_helper = new HttpHelper();
                        boolean registrationSuccessful = http_helper.postJSONObjectFromURL(registrationUrl, userJson);

                        if (registrationSuccessful) {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent = new Intent(getActivity(), WelcomeActivity.class);

                                    Bundle bundle = new Bundle();
                                    bundle.putString("username", etUsername.getText().toString());

                                    intent.putExtras(bundle);
                                    startActivity(intent);
                                }
                            });
                        } else {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getActivity().getApplicationContext(), "Username already exists!", Toast.LENGTH_SHORT).show();
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
}