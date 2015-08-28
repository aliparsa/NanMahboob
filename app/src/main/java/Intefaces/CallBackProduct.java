package Intefaces;


import java.util.ArrayList;

import DataModel.Group;
import DataModel.Product;

/**
 * Created by aliparsa on 8/10/2014.
 */

public interface CallBackProduct {

    public void onSuccess(ArrayList<Product> products);

    public void onError(String errorMessage);


}
