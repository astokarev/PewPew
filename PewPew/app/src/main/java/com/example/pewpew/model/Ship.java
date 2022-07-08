package com.example.pewpew.model;

import android.content.Context;

import com.example.pewpew.GameView;
import com.example.pewpew.MainActivity;
import com.example.pewpew.R;

public class Ship extends SpaceBody {
    public Ship(Context context) {
        bitmapId = R.drawable.ship;
        size = 5;
        x=7;
        y= GameView.maxY - size - 1;
        speed = (float) 0.2;

        init(context);
    }
    @Override
    public void update() {
        if(MainActivity.isLeftPressed && x >= 0){
            x -= speed;
        }
        if(MainActivity.isRightPressed && x <= GameView.maxX - 5){
            x += speed;
        }
    }
}
