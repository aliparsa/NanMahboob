package Helpers;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.Toast;

import Views.TextViewFont;
import partopars.irdevelopers.nanmahboob.R;

/**
 * Created by Ali on 9/1/2015.
 */
public class ToastHelper {
    public static void makeTextLong(Context context , String message){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        TextViewFont textViewFont = (TextViewFont) inflater.inflate(R.layout.toast, null);
        Toast toast = new Toast(context);
        textViewFont.setText(message);
        toast.setView(textViewFont);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();
    }

    public static void makeTextShort(Context context , String message){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        TextViewFont textViewFont = (TextViewFont) inflater.inflate(R.layout.toast, null);
        Toast toast = new Toast(context);
        textViewFont.setText(message);
        toast.setView(textViewFont);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }
}
