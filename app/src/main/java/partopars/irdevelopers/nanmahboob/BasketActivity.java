package partopars.irdevelopers.nanmahboob;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import Adapter.ListViewObjectAdapter;
import DataModel.Basket;
import Helpers.BasketHelper;
import Helpers.RtlSupportHelper;
import Views.TextViewFont;

public class BasketActivity extends AppCompatActivity {
Context context;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);
         context = this;
        RtlSupportHelper.forceRTLIfSupported(this);
        getSupportActionBar().setTitle("سبد خرید شما");

        listView = (ListView) findViewById(R.id.listView);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View header = inflater.inflate(R.layout.item_basket_header, null);
        View footer = inflater.inflate(R.layout.item_basket_footer, null);

        listView.addHeaderView(header);
        listView.addFooterView(footer);
        listView.setAdapter(new ListViewObjectAdapter<Basket>(context, BasketHelper.getBaskets()));

        TextViewFont sumAll = (TextViewFont) footer.findViewById(R.id.sum_all);
        TextViewFont priceAll = (TextViewFont) footer.findViewById(R.id.price_all);

        sumAll.setText(BasketHelper.getCountAll()+"");
        priceAll.setText(BasketHelper.getPriceAll()+"");




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_basket, menu);
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
