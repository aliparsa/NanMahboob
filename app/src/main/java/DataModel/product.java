package DataModel;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import Helpers.ImageLoaderHelper;
import Intefaces.IListViewItem;
import partopars.irdevelopers.nanmahboob.R;

/**
 * Created by Ali on 8/26/2015.
 */
public class Product implements IListViewItem {
    public int productId;
    public String productName;
    public String productDes;
    public String productImage;
    public int groupId;
    public Bitmap loadedImage;

    public Product(int productId, String productName, String productDes, String productImage, int groupId) {
        this.productId = productId;
        this.productName = productName;
        this.productDes = productDes;
        this.productImage = productImage;
        this.groupId = groupId;
    }



    public static ArrayList<Product> getArrayListFromJsonArray(JSONArray jsonArray) {
        ArrayList<Product> products  = new ArrayList<Product>();
        for (int i = 0; i < jsonArray.length();i++){
            try {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                Product product = new Product(jsonObject.getInt("productId"), jsonObject.getString("productName"), jsonObject.getString("productDes"),jsonObject.getString("productImage"),jsonObject.getInt("groupId"));
                products.add(product);

            } catch (Exception e) {
                e.printStackTrace();
            }


        }
        return products;
    }

    @Override
    public View getView(Context context, View oldView) {
        if (oldView == null || !(oldView.getTag() instanceof Product)) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            oldView = inflater.inflate(R.layout.item_product, null);
            final Holder holder = new Holder();
            oldView.setTag(holder);
            getItem(context, holder, oldView);
            return oldView;
        } else {
            Holder holder = (Holder) oldView.getTag();
            getItem(context, holder, oldView);
            return oldView;
        }
    }

    @Override
    public void setSelected(boolean flag) {

    }

    private void getItem(final Context context, final Holder holder, View view) {
        holder.product = this;

        if (holder.title == null)
            holder.title = (TextView) view.findViewById(R.id.item_product_name);

        if (holder.imageView == null)
            holder.imageView = (ImageView) view.findViewById(R.id.item_product_image);

        if (holder.progressBar == null)
            holder.progressBar = (ProgressBar) view.findViewById(R.id.item_product_progressbar);

        // fill
        holder.title.setText(this.productName);
        if (loadedImage!=null){
            holder.imageView.setImageBitmap(loadedImage);
            holder.progressBar.setVisibility(View.GONE);
        }else {

            ImageLoaderHelper.load(context, this.productImage, holder.imageView, new ImageLoadingListener() {
                @Override
                public void onLoadingStarted(String s, View view) {
                    holder.progressBar.setVisibility(View.VISIBLE);

                }

                @Override
                public void onLoadingFailed(String s, View view, FailReason failReason) {
                    holder.progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onLoadingComplete(String s, View view, Bitmap bitmap) {
                    loadedImage = bitmap;
                    holder.imageView.setImageBitmap(bitmap);
                    holder.progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onLoadingCancelled(String s, View view) {
                    holder.progressBar.setVisibility(View.GONE);
                }
            });
        }

    }
    public class Holder {
        public Product  product;
        TextView title;
        ImageView imageView;
        ProgressBar progressBar;
    }
}
