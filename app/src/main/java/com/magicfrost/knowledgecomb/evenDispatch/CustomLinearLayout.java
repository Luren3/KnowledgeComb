package com.magicfrost.knowledgecomb.evenDispatch;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;

/**
 * Created by MagicFrost.
 */
public class CustomLinearLayout extends LinearLayout {

    public CustomLinearLayout(Context context) {
        this(context,null);
    }

    public CustomLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.e("CustomLinearLayout","Down");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("CustomLinearLayout","Move");
                return true;
            case MotionEvent.ACTION_UP:
                Log.e("CustomLinearLayout","Up");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.e("CustomLinearLayout","Cancel");
                break;
        }
        return super.onInterceptTouchEvent(event);
    }
}
