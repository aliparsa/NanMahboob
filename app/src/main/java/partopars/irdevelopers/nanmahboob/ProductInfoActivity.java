package partopars.irdevelopers.nanmahboob;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.Toast;

import DataModel.Product;
import Helpers.BasketHelper;
import Helpers.RamHelper;
import Helpers.RtlSupportHelper;
import Views.ButtonFont;
import Views.TextViewFont;
import partopars.irdevelopers.nanmahboob.R;

public class ProductInfoActivity extends AppCompatActivity {
    Context context;
    ImageView imageView;
    TextViewFont textViewName;
    TextViewFont textViewDes;
    TextViewFont textViewPrice;
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
        textViewPrice = (TextViewFont) findViewById(R.id.product_price);


        product = RamHelper.product;

        if (product != null) {
            ((Activity) context).setTitle("بازگشت");
            imageView.setImageBitmap(product.loadedImage);
            textViewName.setText(product.productName);
            textViewDes.setText(product.productDes);
            textViewPrice.setText(product.productPrice);
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

        if (id == R.id.action_add_to_basket) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.add_to_basket_dialog, null);

            final AlertDialog.Builder builder = new AlertDialog.Builder(context);
            final NumberPicker np = (NumberPicker) view.findViewById(R.id.numberPicker);
            ButtonFont add = (ButtonFont) view.findViewById(R.id.btn_add);

            String[] nums = new String[100];
            for(int i=0; i<nums.length; i++)
                nums[i] = Integer.toString(i+1);

            np.setMinValue(1);
            np.setMaxValue(100);
            np.setWrapSelectorWheel(false);
            np.setDisplayedValues(nums);
            np.setValue(1);

            builder.setView(view);
            final AlertDialog alertDialog = builder.show();

            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    BasketHelper.addToBasket(product, np.getValue());
                    alertDialog.dismiss();
                    Toast.makeText(context,product.productName+"\n"+" به سبد خرید افزوده شد " ,Toast.LENGTH_SHORT).show();
                }
            });



        }

        return super.onOptionsItemSelected(item);
    }
}
