package com.jag.movies.Utils;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

/**
 * Created by joela on 31/01/2017.
 */

public class NestedScrollViewForHorizontalList extends NestedScrollView {

    private float xDistance, yDistance, lastX, lastY;

    public NestedScrollViewForHorizontalList(Context context) {
        super(context);
    }

    public NestedScrollViewForHorizontalList(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NestedScrollViewForHorizontalList(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                xDistance = yDistance = 0f;
                lastX = ev.getX();
                lastY = ev.getY();
                computeScroll();
                break;

            case MotionEvent.ACTION_MOVE:
                final float curX = ev.getX();
                final float curY = ev.getY();
                xDistance += Math.abs(curX - lastX);
                yDistance += Math.abs(curY - lastY);
                lastX = curX;
                lastY = curY;

                if (xDistance > yDistance) {
                    return false;
                }
        }


        return super.onInterceptTouchEvent(ev);
    }
}
