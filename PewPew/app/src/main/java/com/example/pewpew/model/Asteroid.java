package com.example.pewpew.model;

import android.content.Context;

import com.example.pewpew.GameView;
import com.example.pewpew.R;

import java.util.Random;

public class Asteroid extends SpaceBody {
    private int radius = 2; // радиус
    private float minSpeed = (float) 0.1; // минимальная скорость
    private float maxSpeed = (float) 0.5; // максимальная скорость

    public Asteroid(Context context) {
        Random random = new Random();

        bitmapId = R.drawable.asteroid;
        y=0;
        x = random.nextInt(GameView.maxX) - radius;
        size = radius*2;
        speed = minSpeed + (maxSpeed - minSpeed) * random.nextFloat();

        init(context);
    }
    @Override
    public void update() {
        y += speed;
    }
}
