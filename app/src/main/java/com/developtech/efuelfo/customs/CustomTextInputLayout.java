package com.developtech.efuelfo.customs;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.design.widget.TextInputLayout;
import android.util.AttributeSet;
import android.widget.TextView;

import com.developtech.efuelfo.R;

import java.lang.reflect.Field;


public class CustomTextInputLayout extends TextInputLayout {
    public CustomTextInputLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);

    }

    Context context;

    public CustomTextInputLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init(attrs);


    }

    public CustomTextInputLayout(Context context) {
        super(context);
        this.context = context;
        init(null);
    }

    private void init(AttributeSet attrs) {
        if (!isInEditMode()) {
            if (attrs != null) {
                TypedArray a = getContext().obtainStyledAttributes(attrs,
                        R.styleable.CustomViews);
                String fontName = a.getString(R.styleable.CustomViews_fontName);
                if (fontName == null) {
                    fontName = "light.ttf";
                }
                if (fontName != null) {
                    Typeface myTypeface = Typeface.createFromAsset(getContext()
                            .getAssets(), "fonts/" + fontName);
                    setTypeface(myTypeface);
                }

                a.recycle();
            }

        }
    }

    @Override
    public void setError(CharSequence error) {

        super.setError(error);


        if (isErrorEnabled()) {
            try {
                TextInputLayout in = this;
                Field f = CustomTextInputLayout.this.getClass().getSuperclass().getDeclaredField("mErrorView");
                f.setAccessible(true);
                TextView titleTextView = (TextView) f.get((TextInputLayout) this);
                titleTextView.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/" + context.getString(R.string.font_light)));


                //                f1.setAccessible(true);
                //                TextView titleTextView = (TextView) f1.get(getClass().getSuperclass());
                //                titleTextView.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/" + context.getString(R.string.font_light)));
            } catch (Exception e) {
                System.err.println(e);
                e.printStackTrace();
            }
        }

    }
}
