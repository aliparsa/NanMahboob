package Helpers;

import java.util.ArrayList;

import DataModel.Basket;
import DataModel.Product;

/**
 * Created by Ali on 8/28/2015.
 */
public class BasketHelper {

    private static ArrayList<Basket> baskets = new ArrayList<Basket>();

    public static void addToBasket(Product product,  int count) {

        if (isProductExistInBasket(product)) {
            // product already exist in basket
            for (Basket tempbasket : baskets) {
                if (tempbasket.product.productId == product.productId) {
                    tempbasket.count += count;
                    return;
                }
            }
        } else {
            // product don't exist in basket
            baskets.add(new Basket(product,count));
        }


    }

    public static ArrayList<Basket> getBaskets() {
        return baskets;
    }

    private static boolean isProductExistInBasket(Product product) {
        if (baskets == null || baskets.size() < 1) {
            return false;
        }

        for (Basket basket : baskets) {
            if (basket.product.productId == product.productId) {
                return true;
            }
        }
        return false;
    }

    public static void clearBasket() {
        baskets.clear();
    }

    public static void removeFromBasket(Product product){
        if (isProductExistInBasket(product)){
            for (Basket basket : baskets) {
                if (basket.product.productId == product.productId) {
                    if ( basket.count>1 )
                        basket.count-=1;
                    else
                        baskets.remove(basket);
                }
            }
        }
    }


}
