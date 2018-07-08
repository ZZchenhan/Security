package xxk.wuhai.seacurity.weight;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import xxk.wuhai.seacurity.R;


public class SideLetterBar extends View {
    public  static List<String> b = new ArrayList<>();
    static {
        b.add("a");
        b.add("b");
        b.add("c");
        b.add("d");
        b.add("e");
        b.add("f");
        b.add("g");
        b.add("h");
        b.add("i");
        b.add("j");
        b.add("k");
        b.add("l");
        b.add("m");
        b.add("n");
        b.add("o");
        b.add("p");
        b.add("q");
        b.add("r");
        b.add("s");
        b.add("t");
        b.add("u");
        b.add("v");
        b.add("w");
        b.add("x");
        b.add("y");
        b.add("z");
    }
    private int choose = -1;
    private Paint paint = new Paint();
    private boolean showBg = false;
    private OnLetterChangedListener onLetterChangedListener;
    private TextView overlay;

    public SideLetterBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public SideLetterBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SideLetterBar(Context context) {
        super(context);
    }

    /**
     * 设置悬浮的textview
     * @param overlay
     */
    public void setOverlay(TextView overlay){
        this.overlay = overlay;
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (showBg) {
            canvas.drawColor(Color.TRANSPARENT);
        }
        if(b.size() == 0){
            return;
        }
        int height = getHeight();
        int width = getWidth();
        int singleHeight = height / b.size();
        for (int i = 0; i < b.size(); i++) {
            paint.setTextSize(getResources().getDimension(R.dimen.side_letter_bar_letter_size));
            paint.setColor(getResources().getColor(R.color.cp_gray));
            paint.setAntiAlias(true);
            if (i == choose) {
                paint.setColor(getResources().getColor(R.color.cp_gray_deep));
//                paint.setFakeBoldText(true);  //加粗
            }
            float xPos = width / 2 - paint.measureText(b.get(i)) / 2;
            float yPos = singleHeight * i + singleHeight;
            canvas.drawText(b.get(i), xPos, yPos, paint);
            paint.reset();
        }

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        final int action = event.getAction();
        final float y = event.getY();
        final int oldChoose = choose;
        final OnLetterChangedListener listener = onLetterChangedListener;
        final int c = (int) (y / getHeight() * b.size());

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                showBg = true;
                if (oldChoose != c && listener != null) {
                    if (c >= 0 && c < b.size()) {
                        listener.onLetterChanged( b.get(c));
                        choose = c;
                        invalidate();
                        if (overlay != null){
                            overlay.setVisibility(VISIBLE);
                            overlay.setText( b.get(c));
                        }
                    }
                }

                break;
            case MotionEvent.ACTION_MOVE:
                if (oldChoose != c && listener != null) {
                    if (c >= 0 && c < b.size()) {
                        listener.onLetterChanged( b.get(c));
                        choose = c;
                        invalidate();
                        if (overlay != null){
                            overlay.setVisibility(VISIBLE);
                            overlay.setText( b.get(c));
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                showBg = false;
                choose = -1;
                invalidate();
                if (overlay != null){
                    overlay.setVisibility(GONE);
                }
                break;
        }
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    public void setOnLetterChangedListener(OnLetterChangedListener onLetterChangedListener) {
        this.onLetterChangedListener = onLetterChangedListener;
    }

    public interface OnLetterChangedListener {
        void onLetterChanged(String letter);
    }

    public List<String> getB() {
        return b;
    }

    public void setB(List<String> b) {
        this.b = b;
    }
}
