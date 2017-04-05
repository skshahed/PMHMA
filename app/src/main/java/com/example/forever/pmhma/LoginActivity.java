package com.example.forever.pmhma;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private EditText emailET;
    private EditText passwordET;
    private TextView showEmailTV,showPasTV;
    private SharedPreferences userPreference;
    private SharedPreferences.Editor editor;
    private UserAuthentication userAuthentication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailET = (EditText) findViewById(R.id.loginEmail);
        passwordET = (EditText) findViewById(R.id.loginPassword);

        userAuthentication = new UserAuthentication(this);

        userPreference = getSharedPreferences("User Authenticate",MODE_PRIVATE);
        editor = userPreference.edit();
    }

    public void goSignUp(View view) {
        Intent intent = new Intent(this,SignUpActivity.class);
        startActivity(intent);
    }
}
