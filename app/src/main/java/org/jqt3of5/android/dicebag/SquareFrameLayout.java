package org.jqt3of5.android.dicebag;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by Brittany on 7/16/2017.
 */

public class SquareFrameLayout extends FrameLayout {
    public SquareFrameLayout(@NonNull Context context) {
        super(context);
    }

    public SquareFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareFrameLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int min = Math.max(widthMeasureSpec, heightMeasureSpec);
        super.onMeasure(min, min);
    }
}
