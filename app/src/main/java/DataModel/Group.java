package DataModel;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import Intefaces.IListViewItem;
import partopars.irdevelopers.nanmahboob.R;

/**
 * Created by Ali on 8/26/2015.
 */
public class Group implements IListViewItem{

    int groupId;
    String groupName;
    String groupImage;
    Bitmap loadedImage;


    public Group(int groupId, String groupName, String groupImage) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.groupImage = groupImage;
    }

    public static ArrayList<Group> getArrayListFromJsonArray(JSONArray jsonArray) {
        ArrayList<Group> groups = new ArrayList<Group>();
        for (int i = 0; i < jsonArray.length();i++){
            try {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                Group group = new Group(jsonObject.getInt("groupId"), jsonObject.getString("groupName"), jsonObject.getString("groupImage"));
                groups.add(group);

            } catch (Exception e) {
                e.printStackTrace();
            }


        }
        return groups;
    }

    @Override
    public View getView(Context context, View oldView) {
        if (oldView == null || !(oldView.getTag() instanceof Group)) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            oldView = inflater.inflate(R.layout.item_group, null);
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
        holder.group = this;

        if (holder.title == null)
            holder.title = (TextView) view.findViewById(R.id.item_group_name);

        if (holder.imageView == null)
            holder.imageView = (ImageView) view.findViewById(R.id.item_group_image);

        if (holder.progressBar == null)
            holder.progressBar = (ProgressBar) view.findViewById(R.id.item_group_progressbar);

        // fill
        holder.title.setText(this.groupName);
        if (loadedImage!=null){
            holder.imageView.setImageBitmap(loadedImage);
            holder.progressBar.setVisibility(View.GONE);
        }else {

            DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
            .cacheInMemory(true)
            .cacheOnDisk(true)
            .build();

            ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
            .defaultDisplayImageOptions(defaultOptions)
            .build();

            ImageLoader.getInstance().init(config);
            ImageLoader imageLoader = ImageLoader.getInstance();
            imageLoader.init(ImageLoaderConfiguration.createDefault(context));
            imageLoader.displayImage(this.groupImage, holder.imageView, new ImageLoadingListener() {
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
        public Group  group;
        TextView title;
        ImageView imageView;
        ProgressBar progressBar;
    }
}
