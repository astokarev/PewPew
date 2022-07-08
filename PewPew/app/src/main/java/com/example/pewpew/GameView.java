package com.example.pewpew;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.pewpew.model.Asteroid;
import com.example.pewpew.model.Ship;

import java.util.ArrayList;

public class GameView extends SurfaceView implements Runnable {

    public static int maxX = 20; // размер по горизонтали
    public static int maxY = 28; // размер по вертикали
    public static float unitW = 0;
    public static float unitH = 0;

    private ArrayList<Asteroid> asteroids = new ArrayList<>();
    ArrayList<Asteroid> toRemove = new ArrayList<Asteroid>();
    private final int ASTEROID_INTERVAL = 50;
    private int currentTime = 0;

    private boolean firstTime = true;
    private boolean gameRunning = true;
    private Ship ship;
    private Thread gameThread = null;
    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;

    public GameView(Context context) {
        super(context);
        surfaceHolder = getHolder();
        paint = new Paint();

        // инициализируем поток
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while (gameRunning) {
            update();
            draw();
            checkIfNewAsteroid();
            asteroids.removeAll(toRemove);
            toRemove.removeAll(toRemove);
            control();
        }
    }

    private void update() {
        if (!firstTime) {
            ship.update();
            for (Asteroid asteroid : asteroids) {
                asteroid.update();
            }


            }
    }

    private void checkIfNewAsteroid(){
        if(currentTime >= ASTEROID_INTERVAL){
            Asteroid asteroid = new Asteroid(getContext());
            asteroids.add(asteroid);
            currentTime = 0;
     /*       if(asteroids.toArray().length>3) {
                asteroids.removeAll(toRemove);
            }*/
        }else{
            currentTime ++;
        }

        for(Asteroid asteroid: asteroids){ // рисуем врагов
           if(asteroid.y >= maxY){
               toRemove.add(asteroid);
           }
        }

    }

    private void draw() {
        if (surfaceHolder.getSurface().isValid()) {

            if (firstTime) {
                firstTime = false;
                unitW = surfaceHolder.getSurfaceFrame().width() / maxX;
                unitH = surfaceHolder.getSurfaceFrame().height() / maxY;
                ship = new Ship(getContext());
            }

            canvas = surfaceHolder.lockCanvas();
            canvas.drawColor(Color.BLACK);

            ship.drow(paint, canvas);

            for(Asteroid asteroid: asteroids){ // рисуем врагов
                asteroid.drow(paint, canvas);
            }



            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    private void control() { // пауза на 17 миллисекунд
       try {
            gameThread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
