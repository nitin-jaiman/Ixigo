package ixigo.nitin.com.ixigo.CustomViews;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

import java.util.Hashtable;

/**
 * Created by Ishaan on 18/02/2016.
 */
public class CustomEditTextView extends EditText {



    public CustomEditTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public CustomEditTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomEditTextView(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf =  getTypeFace(getContext(),"Exo-Light" );
          //  Typeface tf = Typeface.createFromAsset(getContext().getAssets(),  "fonts/Exo-Light.ttf");
            setTypeface(tf);
        }
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