package partopars.irdevelopers.nanmahboob;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

import Adapter.ListViewObjectAdapter;
import DataModel.Group;
import DataModel.Product;
import Helpers.DataLoaderHelper;
import Helpers.RamHelper;
import Helpers.RtlSupportHelper;
import Intefaces.CallBack;
import Intefaces.CallBackYes;
import partopars.irdevelopers.nanmahboob.R;

public class ProductsActivity extends ActionBarActivity {

    private Context context;
    private Group group;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        context=this;
        
        RtlSupportHelper.forceRTLIfSupported((Activity) context);

        listView = (ListView) findViewById(R.id.listView);


        group = RamHelper.group;

        DataLoaderHelper.syncProducts(context, new CallBack() {
            @Override
            public void onSuccess() {
                ArrayList<Product> products = new ArrayList<Product>();
                for (Product product : DataLoaderHelper.products) {
                    if (product.groupId == group.groupId)
                        products.add(product);
                }
                listView.setAdapter(new ListViewObjectAdapter<Product>(context, products));
            }

            @Override
            public void onError() {

            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_products, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
