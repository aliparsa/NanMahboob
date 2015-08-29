package DataModel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import Intefaces.IListViewItem;
import partopars.irdevelopers.nanmahboob.R;

/**
 * Created by Ali on 8/28/2015.
 */
public class Basket implements IListViewItem {
    public Product product;
    public int count;

    public Basket(Product product, int count) {
        this.product = product;
        this.count = count;
    }

    @Override
    public View getView(Context context, View oldView) {
        if (oldView == null || !(oldView.getTag() instanceof Basket)) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            oldView = inflater.inflate(R.layout.item_basket, null);
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
        holder.basket = this;

        if (holder.name == null)
            holder.name = (TextView) view.findViewById(R.id.basket_item_name);

        if (holder.price == null)
            holder.price = (TextView) view.findViewById(R.id.basket_item_price);

        if (holder.count == null)
            holder.count = (TextView) view.findViewById(R.id.basket_item_count);

        if (holder.sumPrice == null)
            holder.sumPrice = (TextView) view.findViewById(R.id.basket_item_sum_price);


//        // fill
        holder.name.setText(this.product.productName);
        holder.count.setText(this.count+"");
        holder.price.setText(this.product.productPrice);
        holder.sumPrice.setText((Integer.parseInt(this.product.productPrice)*count)+"");
    }

    public class Holder {
        public Basket  basket;
        TextView name;
        TextView count;
        TextView price;
        TextView sumPrice;

    }
}
