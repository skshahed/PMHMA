package com.example.forever.pmhma;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button goLoginBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goLoginBtn = (Button) findViewById(R.id.goLogin);
    }

    public void goLogin(View view) {
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);

    }
}
