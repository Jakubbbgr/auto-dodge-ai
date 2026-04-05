package com.example.autododge.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.SystemClock;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements Runnable {
    private Thread gameThread;
    private boolean isRunning;
    private SurfaceHolder holder;
    private Paint paint;
    private long lastFrameTime;
    private int screenWidth;
    private int screenHeight;

    // Game entities
    private Circle player;
    private List<Circle> enemies; // List of enemies
    private List<Circle> bullets; // List of bullets

    public GameView(Context context) {
        super(context);
        holder = getHolder();
        paint = new Paint();
        enemies = new ArrayList<>();
        bullets = new ArrayList<>();
        player = new Circle(screenWidth / 2, screenHeight / 2, 20);
        isRunning = true;
    }

    @Override
    public void run() {
        while (isRunning) {
            long startTime = SystemClock.elapsedRealtime();
            update();
            draw();
            long frameTime = SystemClock.elapsedRealtime() - startTime;
            if (frameTime < 1000 / 60) {
                try {
                    Thread.sleep(1000 / 60 - frameTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void update() {
        // Update player and enemies
        player.update(); // Move player based on input
        for (Circle enemy : enemies) {
            enemy.update();
        }
        for (Circle bullet : bullets) {
            bullet.update();
        }
    }

    private void draw() {
        if (holder.getSurface().isValid()) {
            Canvas canvas = holder.lockCanvas();
            canvas.drawColor(Color.BLACK); // Clear screen
            paint.setColor(Color.CYAN);
            canvas.drawCircle(player.x, player.y, player.radius, paint);

            paint.setColor(Color.RED);
            for (Circle enemy : enemies) {
                canvas.drawCircle(enemy.x, enemy.y, enemy.radius, paint);
            }

            paint.setColor(Color.YELLOW);
            for (Circle bullet : bullets) {
                canvas.drawCircle(bullet.x, bullet.y, bullet.radius, paint);
            }

            holder.unlockCanvasAndPost(canvas);
        }
    }

    public void pause() {
        isRunning = false;
        while (true) {
            try {
                gameThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            break;
        }
    }

    public void resume() {
        isRunning = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    static class Circle {
        float x, y;
        float radius;

        Circle(float x, float y, float radius) {
            this.x = x;
            this.y = y;
            this.radius = radius;
        }

        void update() {
            // Update position logic here
        }
    }
}
