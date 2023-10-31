package com.zz.myapp07;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class DrawView extends View {
    private Paint p = new Paint();
    private int y = 0, dy=10;
    private int ballX = 0, balldX=70, ballY=0;
    boolean isShooting=false;
    RectF target1, bullet, target2, target3;
    int targets_hit = 0;
    Bitmap bulletImg1, bulletImg2, bulletImg3;
    public DrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        target1 = new RectF(.7f*getWidth(),.1f*getWidth(),.9f*getWidth(),.3f*getWidth());
        target2 = new RectF(.7f*getWidth(),.5f*getWidth(),.9f*getWidth(),.7f*getWidth());
        target3 = new RectF(.7f*getWidth(),.9f*getWidth(),.9f*getWidth(),1.1f*getWidth());
        bullet = new RectF(0,0,.05f*getWidth(),.05f*getWidth());
        bulletImg1 = BitmapFactory.decodeResource(getResources(),R.drawable.img);
        bulletImg2 = BitmapFactory.decodeResource(getResources(),R.drawable.img);
        bulletImg3 = BitmapFactory.decodeResource(getResources(),R.drawable.img);
        target1.offsetTo(target1.left,.8f*getHeight());
        target2.offsetTo(target2.left,.55f*getHeight());
        target3.offsetTo(target3.left,.2f*getHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        p.setColor(Color.BLACK);
        canvas.drawBitmap(bulletImg1,null,target1,p);
        canvas.drawBitmap(bulletImg2,null,target2,p);
        canvas.drawBitmap(bulletImg3,null,target3,p);
        canvas.drawCircle(150, y, 130.5f, p);
        if(isShooting){
            bullet.offset(balldX,0);
            canvas.drawCircle(bullet.centerX(),bullet.centerY(),bullet.width()/2f,p);
            p.setTextSize(100);
            canvas.drawText("Boom!!!", .5f*getWidth(), .5f*getHeight(), p);
            if(bullet.right>getWidth())isShooting=false;
            if(RectF.intersects(bullet,target1)){
                target1.setEmpty();
                targets_hit += 1;
            }
            else if(RectF.intersects(bullet,target2)){
                target2.setEmpty();
                targets_hit += 1;
            }
            else if(RectF.intersects(bullet,target3)){
                target3.setEmpty();
                targets_hit += 1;
            }
        }

        y += dy;
        y %= getHeight();
        invalidate();
    }

    public int getBalldX() {
        return balldX;
    }

    public void setBalldX(int balldX) {
        this.balldX = balldX;
    }

    public int getDy() {
        return dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_DOWN && !isShooting){
            isShooting=true;
            bullet.offsetTo(150,y);
//            ballX=150;
//            ballY=y;
        }

        return true;
    }

}
