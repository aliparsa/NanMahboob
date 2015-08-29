package partopars.irdevelopers.nanmahboob;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import DataModel.Product;
import Helpers.RamHelper;
import Helpers.RtlSupportHelper;
import Views.TextViewFont;
import partopars.irdevelopers.nanmahboob.R;

public class ProductInfoActivity extends AppCompatActivity {
    Context context;
    ImageView imageView;
    TextViewFont textViewName;
    TextViewFont textViewDes;
    Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_info);
        context = this;


        RtlSupportHelper.forceRTLIfSupported((Activity) context);

        imageView = (ImageView) findViewById(R.id.product_image);
        textViewName = (TextViewFont) findViewById(R.id.product_name);
        textViewDes = (TextViewFont) findViewById(R.id.product_des);


        product = RamHelper.product;

        if (product != null) {
            ((Activity) context).setTitle(product.productName);
            imageView.setImageBitmap(product.loadedImage);
            textViewName.setText(product.productName);
            textViewDes.setText(product.productDes);
        }else {

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_product_info, menu);
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
            Intent intent=new Intent(context,BasketActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
