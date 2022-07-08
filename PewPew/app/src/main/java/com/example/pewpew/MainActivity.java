package com.example.pewpew;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {
    public static  boolean isLeftPressed = false;
    public static boolean isRightPressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GameView gameView = new GameView(this);
        LinearLayout gameLayout = (LinearLayout) findViewById(R.id.gameLayout);
        gameLayout.addView(gameView);
        Button leftButton = (Button) findViewById(R.id.leftButton);
        Button rightButton = (Button) findViewById(R.id.rightButton);
        leftButton.setOnTouchListener(this);
        rightButton.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View button, MotionEvent motion) {
        switch(button.getId()) {
            case R.id.leftButton:
                switch (motion.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        isLeftPressed = true;
                        break;
                    case MotionEvent.ACTION_UP:
                        isLeftPressed = false;
                        break;
                }
                break;
            case R.id.rightButton:
                switch (motion.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        isRightPressed = true;
                        break;
                    case MotionEvent.ACTION_UP:
                        isRightPressed = false;
                        break;
                }
                break;
        }
        return true;
    }
}