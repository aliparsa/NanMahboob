package Helpers;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import DataModel.Group;
import DataModel.Product;
import Intefaces.CallBack;
import Intefaces.CallBackAsync;
import Intefaces.CallBackProduct;

/**
 * Created by Ali on 8/26/2015.
 */
public class ProductsHealper {

    public static ArrayList<Product> products;
//    public static Integer serverLastUpdateProducts;
    private static final String PRODUCTS = "products";



    public static void getProducts(final Context context, final CallBackProduct callBack) {


        if(SharedPrefHelper.contain(context,PRODUCTS)){
            //read from shared pref
            try {
                JSONArray jsonArray = new JSONArray(SharedPrefHelper.read(context,PRODUCTS));
                products = Product.getArrayListFromJsonArray(jsonArray);
                if (products.size()>1){
                    // it's ok we can return it !
                    callBack.onSuccess(products);
                }else{
                    // we have it in shared pref but it is empty ! so we try to load it online
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

//    public static void syncProducts(final Context context, final CallBack callBack) {
//
//        new HttpHelper().post(context, ServerAddress.funcFile, "tag=lastUpdateProducts", new CallBackAsync() {
//            @Override
//            public void onSuccessFinish(String LastUpdateProduct) {
//                Integer localLastUpdate = Integer.parseInt(SharedPrefHelper.read(context, "lastUpdateProducts"));
//                serverLastUpdateProducts = Integer.parseInt(LastUpdateProduct);
//
//                if (serverLastUpdateProducts > localLastUpdate) {
//                    getProducts(context, callBack);
//                } else {
//                    /// local load products
//
//                    try {
//                        JSONArray jsonArray = new JSONArray(SharedPrefHelper.read(context, "products"));
//                        products = Product.getArrayListFromJsonArray(jsonArray);
//                        callBack.onSuccess();
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                        callBack.onError();
//                    }
//
//                }
//            }
//
//            @Override
//            public void onError(String errorMessage) {
//            callBack.onError();
//            }
//        });
//    }

    public static void syncOnline(final Context context, final CallBackProduct callBack) {
        new HttpHelper().post(context, ServerAddress.funcFile, "tag=products", new CallBackAsync() {
            @Override
            public void onSuccessFinish(String result) {
                try {
                    JSONArray jsonArray = new JSONArray(result);
                    SharedPrefHelper.write(context, PRODUCTS, result);
                    products = Product.getArrayListFromJsonArray(jsonArray);
                    callBack.onSuccess(products);

                } catch (JSONException e) {
                    e.printStackTrace();
                    callBack.onError("error 184754151dfd");
                }
            }

            @Override
            public void onError(String errorMessage) {
                callBack.onError("error dfg3df24");

            }
        });
    }

    public static void syncSilent(final Context context) {
        new HttpHelper().post(context, ServerAddress.funcFile, "tag=products", new CallBackAsync() {
            @Override
            public void onSuccessFinish(String result) {
                try {
                    JSONArray jsonArray = new JSONArray(result);
                    SharedPrefHelper.write(context, FinalValuesHelepr.PRODUCTS, result);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String errorMessage) {

            }
        });
    }

    public static ArrayList<Product> getProductsOfGroup(ArrayList<Product> allProducts, Group group){
        ArrayList<Product> tempProducts = new ArrayList<Product>();
        for (Product product : products) {
            if (product.groupId == group.groupId)
                tempProducts.add(product);
        }
        return tempProducts;
    }
}
