package Helpers;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import DataModel.Group;
import DataModel.Product;
import Intefaces.CallBack;
import Intefaces.CallBackAsync;

/**
 * Created by Ali on 8/26/2015.
 */
public class DataLoaderHelper {

    public static ArrayList<Group> groups;
    public static ArrayList<Product> products;
    public static Integer serverLastUpdateGroup;
    public static Integer serverLastUpdateProducts;

    public static void getGroups(final Context context, final CallBack callBack) {
        new HttpHelper().post(context, ServerAddress.funcFile, "tag=groups", new CallBackAsync() {
            @Override
            public void onSuccessFinish(String result) {
                try {
                    JSONArray jsonArray = new JSONArray(result);
                    SharedPrefHelper.write(context, "groups", result);
                    groups = Group.getArrayListFromJsonArray(jsonArray);
                    callBack.onSuccess();
                    SharedPrefHelper.write(context, "lastUpdateGroups", serverLastUpdateGroup + "");

                } catch (JSONException e) {
                    e.printStackTrace();
                    callBack.onError();
                }
            }

            @Override
            public void onError(String errorMessage) {
                callBack.onError();

            }
        });
    }

    public static void getProducts(final Context context, final CallBack callBack) {
        new HttpHelper().post(context, ServerAddress.funcFile, "tag=products", new CallBackAsync() {
            @Override
            public void onSuccessFinish(String result) {
                JSONArray jsonArray = null;
                try {
                    jsonArray = new JSONArray(result);
                    SharedPrefHelper.write(context, "products", result);
                    products = Product.getArrayListFromJsonArray(jsonArray);
                    callBack.onSuccess();
                    SharedPrefHelper.write(context, "lastUpdateProducts", serverLastUpdateProducts + "");

                } catch (JSONException e) {
                    e.printStackTrace();
                    callBack.onError();
                }

            }

            @Override
            public void onError(String errorMessage) {
                callBack.onError();

            }
        });
    }

    public static void syncGroups(final Context context, final CallBack callBack) {
        new HttpHelper().post(context, ServerAddress.funcFile, "tag=lastUpdateGroups", new CallBackAsync() {
            @Override
            public void onSuccessFinish(String LastUpdateGroup) {
                Integer localLastUpdate = Integer.parseInt(SharedPrefHelper.read(context, "lastUpdateGroups"));
                serverLastUpdateGroup = Integer.parseInt(LastUpdateGroup);

                if (serverLastUpdateGroup > localLastUpdate) {
                    getGroups(context, callBack);
                } else {
                    /// local load groups

                    try {
                        JSONArray jsonArray = new JSONArray(SharedPrefHelper.read(context, "groups"));
                        groups = Group.getArrayListFromJsonArray(jsonArray);
                        callBack.onSuccess();

                    } catch (JSONException e) {
                        e.printStackTrace();
                        callBack.onError();
                    }
                }

            }

            @Override
            public void onError(String errorMessage) {
                callBack.onError();

            }
        });
    }

    public static void syncProducts(final Context context, final CallBack callBack) {

        new HttpHelper().post(context, ServerAddress.funcFile, "tag=lastUpdateProducts", new CallBackAsync() {
            @Override
            public void onSuccessFinish(String LastUpdateProduct) {
                Integer localLastUpdate = Integer.parseInt(SharedPrefHelper.read(context, "lastUpdateProducts"));
                serverLastUpdateProducts = Integer.parseInt(LastUpdateProduct);

                if (serverLastUpdateProducts > localLastUpdate) {
                    getProducts(context, callBack);
                } else {
                    /// local load products

                    try {
                        JSONArray jsonArray = new JSONArray(SharedPrefHelper.read(context, "products"));
                        products = Product.getArrayListFromJsonArray(jsonArray);
                        callBack.onSuccess();
                    } catch (JSONException e) {
                        e.printStackTrace();
                        callBack.onError();
                    }

                }
            }

            @Override
            public void onError(String errorMessage) {
            callBack.onError();
            }
        });
    }

    public static void syncGroupsOnline(final Context context, final CallBack callBack) {
        new HttpHelper().post(context, ServerAddress.funcFile, "tag=groups", new CallBackAsync() {
            @Override
            public void onSuccessFinish(String result) {
                try {
                    JSONArray jsonArray = new JSONArray(result);
                    SharedPrefHelper.write(context, "groups", result);
                    groups = Group.getArrayListFromJsonArray(jsonArray);
                    callBack.onSuccess();

                } catch (JSONException e) {
                    e.printStackTrace();
                    callBack.onError();
                }
            }

            @Override
            public void onError(String errorMessage) {
                callBack.onError();

            }
        });
    }

    public static void syncProductsOnline(final Context context, final CallBack callBack) {
        new HttpHelper().post(context, ServerAddress.funcFile, "tag=products", new CallBackAsync() {
            @Override
            public void onSuccessFinish(String result) {
                try {
                    JSONArray jsonArray = new JSONArray(result);
                    SharedPrefHelper.write(context, "products", result);
                    products = Product.getArrayListFromJsonArray(jsonArray);
                    callBack.onSuccess();

                } catch (JSONException e) {
                    e.printStackTrace();
                    callBack.onError();
                }
            }

            @Override
            public void onError(String errorMessage) {
                callBack.onError();

            }
        });
    }
}
