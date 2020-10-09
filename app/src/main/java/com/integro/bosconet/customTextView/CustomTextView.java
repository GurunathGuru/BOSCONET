package com.integro.bosconet.customTextView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import androidx.appcompat.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.integro.bosconet.R;

public class CustomTextView extends AppCompatTextView {
    private static final String TAG = "TextView";

    public CustomTextView(Context context) {
        super(context);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCustomFont(context, attrs);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setCustomFont(context, attrs);
    }

    private void setCustomFont(Context ctx, AttributeSet attrs) {
        TypedArray a = ctx.obtainStyledAttributes(attrs, R.styleable.CustomTextView);
        String customFont = a.getString(R.styleable.CustomTextView_fonts);
        setCustomFont(ctx, customFont);
        a.recycle();
    }

    public boolean setCustomFont(Context ctx, String fontName) {
        Typeface typeface = null;

        if (fontName.contentEquals(getContext().getString(R.string.font_Title))) {
            typeface = Typeface.createFromAsset(ctx.getAssets(), "PlayfairDisplay-Regular.ttf");
            setTypeface(typeface);

        } else if (fontName.contentEquals(getContext().getString(R.string.font_Description))) {
            typeface = Typeface.createFromAsset(ctx.getAssets(), "EBGaramond-Regular.ttf");
            setTypeface(typeface);
        } else if (fontName.contentEquals(getContext().getString(R.string.font_Heading))) {
            typeface = Typeface.createFromAsset(ctx.getAssets(), "impact.ttf");
            setTypeface(typeface);
        }
        return true;
    }
}
