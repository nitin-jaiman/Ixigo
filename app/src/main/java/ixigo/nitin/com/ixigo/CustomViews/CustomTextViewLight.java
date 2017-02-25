package ixigo.nitin.com.ixigo.CustomViews;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import java.util.Hashtable;


public class CustomTextViewLight extends TextView {

    Context context;
    public CustomTextViewLight(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context=context;
        init();
    }

    public CustomTextViewLight(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomTextViewLight(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf =  getTypeFace(getContext(),"Exo-Light" );
            setTypeface(tf);

        }
    }


    @Override
    public void setText(CharSequence text, BufferType type) {
        /*
        if(text!=null)
            if (text.length() > 0) {
                text = String.valueOf(text.charAt(0)).toUpperCase() + text.subSequence(1, text.length());
            }
        */
        super.setText(text, type);

        //super.setText(text.toString().toUpperCase(), type);


    }


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