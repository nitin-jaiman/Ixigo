package ixigo.nitin.com.ixigo.CustomViews;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.RadioButton;

import java.util.Hashtable;

/**
 * Created by nikhi on 1/22/2016.
 */
public class CustomRadioButton extends RadioButton {

    public CustomRadioButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public CustomRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomRadioButton(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf =  getTypeFace(getContext(),"Exo-Light" );
            //  Typeface tf = Typeface.createFromAsset(getContext().getAssets(),  "fonts/Exo-Light.ttf");
            setTypeface(tf);
        }
       // setDividerColor(Color.parseColor("#f0b50b"));
    }

 /*   public void setDividerColor(@ColorInt int color) {
        try {
            Field fDividerDrawable = CompoundButton.class.getDeclaredField("mButtonTintList");
            fDividerDrawable.setAccessible(true);
            Drawable d = (Drawable) fDividerDrawable.get(this);
            d.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
            //d.setBounds(3,3,3,3);
            d.invalidateSelf();
            postInvalidate(); // Drawable is dirty
        }
        catch (Exception e) {

        }
    }*/


    public static final String TYPEFACE_FOLDER = "fonts";
    public static final String TYPEFACE_EXTENSION = ".ttf";

    private static Hashtable<String, Typeface> sTypeFaces = new Hashtable<String, Typeface>(
            4);

    public static Typeface getTypeFace(Context context, String fileName) {
        Typeface tempTypeface = sTypeFaces.get(fileName);

        if (tempTypeface == null) {

            String fontPath = new StringBuilder(TYPEFACE_FOLDER).append('/').append(fileName).append(TYPEFACE_EXTENSION).toString();
            tempTypeface = Typeface.createFromAsset(context.getAssets(), fontPath);
            sTypeFaces.put(fileName, tempTypeface);
        }

        return tempTypeface;
    }

}