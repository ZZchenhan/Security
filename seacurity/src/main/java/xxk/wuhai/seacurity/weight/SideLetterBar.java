package xxk.wuhai.seacurity.weight;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import xxk.wuhai.seacurity.R;


public class SideLetterBar extends View {
    public   List<String> b = new ArrayList<>();

    private int choose = -1;
    private Paint paint = new Paint();
    private boolean showBg = false;
    private OnLetterChangedListener onLetterChangedListener;
    private TextView overlay;
    int singleHeight = 0;
    public SideLetterBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        singleHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,15,getContext().getResources().getDisplayMetrics());
    }

    public SideLetterBar(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SideLetterBar(Context context) {
        this(context,null);
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
        int height = getHeight();
        int width = getWidth();
        paint.setAntiAlias(true);
        if (showBg) {
            canvas.drawColor(Color.TRANSPARENT);
        }
        if(b.size() == 0){
            return;
        }
        Bitmap bitmap = ((BitmapDrawable)this.getResources().getDrawable(R.mipmap.ic_contact_search_draw)).getBitmap();
        canvas.drawBitmap(bitmap,0,singleHeight,paint);
        for (int i = 1; i < b.size(); i++) {
            paint.setColor(getResources().getColor(R.color.cp_gray));
            paint.setTextSize(getResources().getDimension(R.dimen.side_letter_bar_letter_size));
            paint.setColor(getResources().getColor(R.color.cp_gray));
            if (i == choose) {
                paint.setColor(getResources().getColor(R.color.cp_gray_deep));
//                paint.setFakeBoldText(true);  //加粗
            }
            float xPos = width / 2 - paint.measureText(b.get(i)) / 2;
            float yPos = singleHeight * (i+1) + singleHeight;
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
        final int c = (int) (y / (singleHeight * (b.size())) * b.size());
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
                            if(b.get(c).equals("")){
                                overlay.setText("搜索");
                            }else{
                                overlay.setText( b.get(c));
                            }
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
                            if(b.get(c).equals("")){
                                //overlay.setText("搜索");
                                overlay.setVisibility(GONE);
                            }else{
                                overlay.setVisibility(VISIBLE);
                                overlay.setText( b.get(c));
                            }
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
        this.b.clear();
        this.b.add(0,"");
        this.b.add(1,"#");
        this.b.addAll(b);
        invalidate();
    }
}
