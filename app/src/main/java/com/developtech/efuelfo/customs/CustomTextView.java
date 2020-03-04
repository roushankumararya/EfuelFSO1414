package com.developtech.efuelfo.customs;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;

import com.developtech.efuelfo.R;


public class CustomTextView extends android.support.v7.widget.AppCompatTextView {
    public CustomTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);

    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);

    }

    public CustomTextView(Context context) {
        super(context);
        init(null);
    }

    private void init(AttributeSet attrs) {
        if (!isInEditMode()) {
            String fontName = null;
            if (attrs != null) {
                TypedArray a = getContext().obtainStyledAttributes(attrs,
                        R.styleable.CustomViews);
                fontName = a.getString(R.styleable.CustomViews_fontName);
                a.recycle();
            }
            if (fontName == null) {
                fontName = "light.ttf";
            }
            Typeface myTypeface = Typeface.createFromAsset(getContext()
                    .getAssets(), "fonts/" + fontName);
            setTypeface(myTypeface);

        }
    }
}

