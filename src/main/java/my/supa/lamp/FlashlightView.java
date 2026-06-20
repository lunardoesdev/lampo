package my.supa.lamp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

public class FlashlightView extends View {

    private boolean on;
    private final Paint circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint boltPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Path boltPath = new Path();

    public FlashlightView(Context context, AttributeSet attrs) {
        super(context, attrs);
        circlePaint.setStyle(Paint.Style.FILL);
        boltPaint.setStyle(Paint.Style.FILL);
    }

    public void setOn(boolean on) {
        if (this.on != on) {
            this.on = on;
            invalidate();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float cx = getWidth() / 2f;
        float cy = getHeight() / 2f;
        float size = Math.min(getWidth(), getHeight()) * 0.6f;
        float half = size / 2f;

        circlePaint.setColor(on ? Color.rgb(255, 214, 0) : Color.rgb(117, 117, 117));
        float r = half * 0.45f;
        canvas.drawCircle(cx, cy, r, circlePaint);

        boltPaint.setColor(on ? Color.rgb(200, 160, 0) : Color.rgb(80, 80, 80));
        float bw = half * 0.3f;
        float bh = half * 0.55f;
        boltPath.rewind();
        boltPath.moveTo(cx + bw, cy - bh);
        boltPath.lineTo(cx - bw * 0.5f, cy - bh * 0.3f);
        boltPath.lineTo(cx + bw * 0.3f, cy + bh * 0.1f);
        boltPath.lineTo(cx - bw, cy + bh);
        boltPath.close();
        canvas.drawPath(boltPath, boltPaint);
    }
}
