package Helpers;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import DataModel.Group;
import Intefaces.CallBack;
import Intefaces.CallBackAsync;
import Intefaces.CallBackGroup;

/**
 * Created by Ali on 8/28/2015.
 */
public class GroupsHelper {
    public static ArrayList<Group> groups;
//    public static Integer serverLastUpdateGroup;

    public static void getGroups(final Context context, final CallBackGroup callBack) {

        if(SharedPrefHelper.contain(context,FinalValuesHelepr.GROUPS)){
            //read from shared pref
            try {
                JSONArray jsonArray = new JSONArray(SharedPrefHelper.read(context, FinalValuesHelepr.GROUPS));
                groups = Group.getArrayListFromJsonArray(jsonArray);
                if (groups.size()>1){
                    // it's ok we can return it !
                    callBack.onSuccess(groups);
                }else{
                    // we have groups in shared pref but it is empty ! so we try to load it online
                    syncOnline(context,callBack);
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }else{
            // try to load online because shared pref is empty
            syncOnline(context,callBack);
        }
    }



    public static void syncOnline(final Context context, final CallBackGroup callBack) {
        new HttpHelper().post(context, ServerAddress.funcFile, "tag=groups", new CallBackAsync() {
            @Override
            public void onSuccessFinish(String result) {
                try {
                    JSONArray jsonArray = new JSONArray(result);
                    SharedPrefHelper.write(context, FinalValuesHelepr.GROUPS, result);
                    groups = Group.getArrayListFromJsonArray(jsonArray);
                    callBack.onSuccess(groups);

                } catch (JSONException e) {
                    e.printStackTrace();
                    callBack.onError("error 12316165");
                }
            }

            @Override
            public void onError(String errorMessage) {
                callBack.onError("error 165432135");

            }
        });
    }


    public static void syncSilent(final Context context) {
        new HttpHelper().post(context, ServerAddress.funcFile, "tag=groups", new CallBackAsync() {
            @Override
            public void onSuccessFinish(String result) {
                try {
                    JSONArray jsonArray = new JSONArray(result);
                    SharedPrefHelper.write(context, FinalValuesHelepr.GROUPS, result);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String errorMessage) {

            }
        });
    }

}
