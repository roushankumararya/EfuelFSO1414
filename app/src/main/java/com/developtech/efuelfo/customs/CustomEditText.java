package com.developtech.efuelfo.customs;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.AttributeSet;

import com.developtech.efuelfo.R;


public class CustomEditText extends android.support.v7.widget.AppCompatEditText {

    public static final String XML_NAMESPACE_ANDROID = "http://schemas.android.com/apk/res/android";

    public CustomEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        init(attrs);

    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(attrs);


    }

    public CustomEditText(Context context) {
        super(context);

        init(null);

    }

    private void init(AttributeSet attrs) {
        if (!isInEditMode()) {

            if (attrs != null) {
                TypedArray a = getContext().obtainStyledAttributes(attrs,
                        R.styleable.CustomViews);
                String fontName = a.getString(R.styleable.CustomViews_fontName);
                if(fontName==null){

                    fontName = "regular.ttf";
                }
                if (fontName != null) {
                    Typeface myTypeface = Typeface.createFromAsset(getContext()
                            .getAssets(), "fonts/" + fontName);
                    setTypeface(myTypeface);
                }
                a.recycle();

            }
        }

        InputFilter[] filterArray = new InputFilter[2];
        int maxLength = attrs.getAttributeIntValue(XML_NAMESPACE_ANDROID, "maxLength", 50);

        filterArray[0] = new EmojiExcludeFilter();
        filterArray[1] = new InputFilter.LengthFilter(maxLength);

        setFilters(filterArray);

    }


    private class EmojiExcludeFilter implements InputFilter {

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            for (int i = start; i < end; i++) {
                int type = Character.getType(source.charAt(i));
                if (type == Character.SURROGATE || type == Character.OTHER_SYMBOL) {
                    return "";
                }
            }
            return null;
        }
    }
}

