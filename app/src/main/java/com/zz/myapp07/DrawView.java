package com.zz.myapp07;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class DrawView extends View {
    private Paint p = new Paint();
    private int y = 0, dy=5;
    public DrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        p.setColor(Color.BLACK);
        canvas.drawCircle(100, y, 130.5f, p);
        y += dy;
        y %= getHeight();
        invalidate();
    }

    //what i added
    //make it so when the button is clicked, the pink thing shoots something horizontally
    //what i added

    public int getDy() {
        return dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }
}
