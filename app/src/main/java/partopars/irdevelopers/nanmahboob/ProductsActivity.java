package partopars.irdevelopers.nanmahboob;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

import Adapter.ListViewObjectAdapter;
import DataModel.Group;
import DataModel.Product;
import Helpers.ProductsHealper;
import Helpers.RamHelper;
import Helpers.RtlSupportHelper;
import Intefaces.CallBack;
import Intefaces.CallBackProduct;

public class ProductsActivity extends ActionBarActivity {

    private Context context;
    private Group group;
    private ListView listView;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        context=this;
        
        RtlSupportHelper.forceRTLIfSupported((Activity) context);

        listView = (ListView) findViewById(R.id.listView);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.sweep_refresh_layout);


        group = RamHelper.group;

        if (group == null) {
            // start main activity and finisah this activity
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

        showLoading();
        ProductsHealper.getProducts(context, new CallBackProduct() {
            @Override
            public void onSuccess(ArrayList<Product> products) {
                hideLoading();
                listView.setAdapter(new ListViewObjectAdapter<Product>(context, ProductsHealper.getProductsOfGroup(products,group)));
            }

            @Override
            public void onError(String errorMessage) {
                hideLoading();

            }
        });




        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                ProductsHealper.syncOnline(context, new CallBackProduct() {
                    @Override
                    public void onSuccess(ArrayList<Product> products) {

                        listView.setAdapter(new ListViewObjectAdapter<Product>(context, ProductsHealper.getProductsOfGroup(products,group)));
                        hideLoading();
                    }

                    @Override
                    public void onError(String errorMessage) {
                        hideLoading();

                    }
                });


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

    public void showLoading() {
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
            }
        });
    }

    public void hideLoading() {

        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

}
