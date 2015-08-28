package Helpers;

import android.content.Context;

/**
 * Created by Ali on 8/28/2015.
 */
public class FirstRunHelper {

    public  static void init(Context context){
        if (!SharedPrefHelper.contain(context, "lastUpdateGroups"))
            SharedPrefHelper.write(context, "lastUpdateGroups", "0");

        if (!SharedPrefHelper.contain(context, "lastUpdateProducts"))
            SharedPrefHelper.write(context, "lastUpdateProducts", "0");

        GroupsHelper.syncSilent(context);

        ProductsHealper.syncSilent(context);
    }
}
