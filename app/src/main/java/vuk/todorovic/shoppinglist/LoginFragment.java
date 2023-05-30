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
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;


public class LoginFragment extends Fragment {
    EditText etUsername, etPassword;
    Button btnLogin;
    DatabaseHelper database_helper;

    public LoginFragment() {
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
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etUsername = view.findViewById(R.id.etUsername);
        etPassword = view.findViewById(R.id.etPassword);
        btnLogin = view.findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(view_param -> {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        String loginUrl = "https://piars-server.cyclic.app/login";
                        JSONObject userJson = new JSONObject();
                        userJson.put("username", etUsername.getText().toString());
                        userJson.put("password", etPassword.getText().toString());

                        HttpHelper http_helper = new HttpHelper();
                        boolean login_success = http_helper.postJSONObjectFromURL(loginUrl, userJson);

                        if(login_success){
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
                        }else {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(requireActivity(), getResources().getString(R.string.invalid_credentials), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }catch (IOException | JSONException e){
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
        });
    }
}


