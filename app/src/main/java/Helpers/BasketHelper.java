package Helpers;

import java.util.ArrayList;

import DataModel.Basket;
import DataModel.Product;

/**
 * Created by Ali on 8/28/2015.
 */
public class BasketHelper {

    private static ArrayList<Basket> baskets;

    public static void addToBasket(Basket basket) {

        if (isProductExistInBasket(basket.product)) {
            // product already exist in basket
            for (Basket tempbasket : baskets) {
                if (tempbasket.product.productId == basket.product.productId) {
                    tempbasket.count += basket.count;
                    return;
                }
            }
        } else {
            // product don't exist in basket
            baskets.add(basket);
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
}
