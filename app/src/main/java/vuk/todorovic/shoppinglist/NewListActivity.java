package vuk.todorovic.shoppinglist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class NewListActivity extends AppCompatActivity {

//    Button ok;
    Button btnSave;
    EditText etListTitle;
//    TextView title_text;
    String saved_title = "";
    RadioGroup rgShareList;
    DatabaseHelper database_helper;
    String username;
    int shared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        ok = findViewById(R.id.button_new_list_ok);
        btnSave = findViewById(R.id.btnSave);
        etListTitle = findViewById(R.id.etListTitle);
        rgShareList = findViewById(R.id.rgShareList);
//        title_text = findViewById(R.id.text_new_list_title);
        username = getIntent().getStringExtra("username");


        rgShareList.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int selectedId = rgShareList.getCheckedRadioButtonId();

                RadioButton radioButton = findViewById(selectedId);

                String selectedText = radioButton.getText().toString();

                if (selectedText.equals("Yes")) {
                    shared = 1;
                } else {
                    shared = 0;
                }
            }
        });

        database_helper = new DatabaseHelper(this);

        // Klik na Save dugme
        btnSave.setOnClickListener(view_param -> {
            String titleText = etListTitle.getText().toString();
            if (titleText.equals("")) {

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Error");
                builder.setMessage("Title empty!");
                builder.setPositiveButton("Ok", null);
                AlertDialog dialog = builder.create();
                dialog.show();

            } else if (shared == 1) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            String addListUrl = "https://piars-server.cyclic.app/lists";
                            JSONObject list = new JSONObject();
                            list.put("name", titleText);
                            list.put("creator", username);
                            list.put("shared", (shared == 1));

                            HttpHelper http_helper = new HttpHelper();
                            boolean create_success = http_helper.postJSONObjectFromURL(addListUrl, list);

                            if (create_success && database_helper.addList(titleText, username, shared)) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        finish();
                                    }
                                });
                            } else {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(), "List cannot be created!", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }

                        } catch (IOException | JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
                thread.start();
            } else if (shared == 0) {
                if (database_helper.addList(titleText, username, shared)) {
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "List cannot be created!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

//    @Override
//    public void onClick(View view) {
//
//        if (view.getId() == R.id.button_new_list_ok) {
//
//            if (title.getText().toString().isEmpty()) {
//
//                AlertDialog.Builder builder = new AlertDialog.Builder(this);
//                builder.setTitle("Error");
//                builder.setMessage("Title empty!");
//                builder.setPositiveButton("Ok", null);
//                AlertDialog dialog = builder.create();
//                dialog.show();
//
//            } else {
//
//                saved_title = title.getText().toString();
//                title_text.setText(saved_title);
//                title.setText("");
//
//            }
//        }
//
//        if (view.getId() == R.id.button_new_list_save) {
//
//            if (title_text.getText().toString().equals("")) {
//
//                AlertDialog.Builder builder = new AlertDialog.Builder(this);
//                builder.setTitle("Error");
//                builder.setMessage("Title empty!");
//                builder.setPositiveButton("Ok", null);
//                AlertDialog dialog = builder.create();
//                dialog.show();
//
//            } else if (shared == 1) {
//                Thread thread = new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
//                            String addListUrl = "http://192.168.0.27:3000/lists";
//                            JSONObject list = new JSONObject();
//                            list.put("name", title_text.getText().toString());
//                            list.put("creator", username);
//                            list.put("shared", (shared == 1));
//
//                            HttpHelper http_helper = new HttpHelper();
//                            boolean create_success = http_helper.postJSONObjectFromURL(addListUrl, list);
//
//                            if (create_success && database_helper.addList(title_text.getText().toString(), username, shared)) {
//                                runOnUiThread(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        finish();
//                                    }
//                                });
//                            } else {
//                                runOnUiThread(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        Toast.makeText(getApplicationContext(), "List cannot be created!", Toast.LENGTH_SHORT).show();
//                                    }
//                                });
//                            }
//
//                        } catch (IOException | JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });
//                thread.start();
//            } else if (shared == 0) {
//                if (database_helper.addList(title_text.getText().toString(), username, shared)) {
//                    finish();
//                } else {
//                    Toast.makeText(getApplicationContext(), "List cannot be created!", Toast.LENGTH_SHORT).show();
//                }
//            }
//        }
//    }

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

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent event) {
//
//        if (event.getAction() == MotionEvent.ACTION_DOWN) {
//
//            View v = getCurrentFocus();
//
//            if (v instanceof EditText) {
//
//                Rect outRect = new Rect();
//                v.getGlobalVisibleRect(outRect);
//
//                if (!outRect.contains((int) event.getRawX(), (int) event.getRawY())) {
//
//                    v.clearFocus();
//                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
//
//                }
//            }
//        }
//
//        return super.dispatchTouchEvent(event);
//
//    }
}