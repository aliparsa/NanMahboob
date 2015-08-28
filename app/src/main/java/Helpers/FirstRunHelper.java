package Helpers;

import android.content.Context;

/**
 * Created by Ali on 8/28/2015.
 */
public class FirstRunHelper {

    public  static void init(Context context){

        if (!SharedPrefHelper.contain(context,FinalValuesHelepr.NAME)) {
            SharedPrefHelper.write(context, FinalValuesHelepr.NAME, "");
            SharedPrefHelper.write(context, FinalValuesHelepr.TEL, "");
            SharedPrefHelper.write(context, FinalValuesHelepr.ADDRESS,"");
            SharedPrefHelper.write(context, FinalValuesHelepr.EMAIL,"");
        }
        GroupsHelper.syncSilent(context);

        ProductsHealper.syncSilent(context);
    }
}
