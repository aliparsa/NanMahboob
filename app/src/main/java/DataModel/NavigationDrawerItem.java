package DataModel;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import Intefaces.CallBackAsync;
import Intefaces.IListViewItem;
import partopars.irdevelopers.nanmahboob.R;

/**
 * Created by Ali on 8/25/2015.
 */
public class NavigationDrawerItem implements IListViewItem {

    String title;

    public NavigationDrawerItem(String title){
        this.title=title;
    }

    @Override
    public View getView(Context context, View oldView) {

        if (oldView == null || !(oldView.getTag() instanceof NavigationDrawerItem)) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            oldView = inflater.inflate(R.layout.item_navigation_drawer, null);
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
        holder.navigationDrawerItem = this;

        if (holder.title == null)
            holder.title = (TextView) view.findViewById(R.id.title);

//        if (holder.imageView == null)
//            holder.imageView = (ImageView) view.findViewById(R.id.newsImage);

        // fill
        holder.title.setText(this.title);

    }

    public class Holder {
        public NavigationDrawerItem  navigationDrawerItem;
        TextView title;
    }
}
