package com.example.game2048;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button smallButton, normalButton, hugeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        smallButton = (Button) findViewById(R.id.smallButton);
        smallButton.setOnClickListener(this);

        normalButton = (Button) findViewById(R.id.normalButton);
        normalButton.setOnClickListener(this);

        hugeButton = (Button) findViewById(R.id.hugeButton);
        hugeButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}
