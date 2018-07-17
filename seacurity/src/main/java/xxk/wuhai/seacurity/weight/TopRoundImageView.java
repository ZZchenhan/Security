package xxk.wuhai.seacurity.weight;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ImageView;

/**
 * Created by 86936 on 2018/3/12.
 * 圆角矩形
 */

public class TopRoundImageView extends ImageView {
    float width,height;
    float roundSize =4;
    public TopRoundImageView(Context context) {
        this(context,null);
    }

    public TopRoundImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TopRoundImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        roundSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,roundSize,context.getResources().getDisplayMetrics());
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        width = getWidth();
        height = getHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (width > roundSize && height > roundSize) {
            Path path = new Path();
            path.moveTo(0,roundSize);
            path.quadTo(0,0,roundSize,0);
            path.lineTo(width-roundSize,0);
            path.quadTo(width,0,width,roundSize);
            path.lineTo(width,height);
            path.lineTo(0,height);
            path.lineTo(0,roundSize);
            path.close();
            canvas.clipPath(path);
        }
        super.onDraw(canvas);
    }
}
