package com.swathisai.jathakalise;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by CYKULTECH02 on 24-Sep-16.
 */
public class CustomTextView extends TextView {

    public CustomTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomTextView(Context context) {
        super(context);
        init();
    }

    public void init() {

        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/abc.ttf");
        setTypeface(tf ,1);

    }
}