package com.example.game2048;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnPlay, btnHelp, btnExit;
    private Context mContext;
    private Activity mActivity;
    private RelativeLayout mRelativeLayout;
    private PopupWindow mPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = getApplicationContext();
        mActivity = MainActivity.this;
        mRelativeLayout = findViewById(R.id.relativeLayout);

        btnPlay = findViewById(R.id.btnStart);
        btnPlay.setOnClickListener(this);

        btnHelp = findViewById(R.id.btnHelp);
        btnHelp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                showPopupWindow(mRelativeLayout);
            }
        });

        btnExit = findViewById(R.id.btnExit);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });


    }

    public void showPopupWindow(View view) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_window, null);

        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        Button bt = popupView.findViewById(R.id.btnRestart);
        bt.setText("PLAY NOW!");
        bt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //Toast.makeText(this, "Clicked button", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, NormalGame.class);
        startActivity(intent);
    }
}
