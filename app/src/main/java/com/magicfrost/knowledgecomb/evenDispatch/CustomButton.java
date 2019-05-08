package com.magicfrost.knowledgecomb.evenDispatch;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import androidx.appcompat.widget.AppCompatButton;

/**
 * Created by MagicFrost.
 */
public class CustomButton extends AppCompatButton {

    public CustomButton(Context context) {
        this(context,null);
    }

    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.e("CustomButton","Down");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("CustomButton","Move");
                break;
            case MotionEvent.ACTION_UP:
                Log.e("CustomButton","Up");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.e("CustomButton","Cancel");
                break;
        }
        return super.onTouchEvent(event);
    }
}
