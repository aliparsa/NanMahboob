package partopars.irdevelopers.nanmahboob;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import Helpers.FinalValuesHelepr;
import Helpers.RtlSupportHelper;
import Helpers.SharedPrefHelper;
import Views.EditTextFont;
import partopars.irdevelopers.nanmahboob.R;

public class RegisterActivity extends ActionBarActivity {

    private Context context;
    EditTextFont name;
    EditTextFont tel;
    EditTextFont address;
    EditTextFont email;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        context=this;

        RtlSupportHelper.forceRTLIfSupported((Activity) context);

        name = (EditTextFont) findViewById(R.id.rig_name);
        tel = (EditTextFont) findViewById(R.id.rig_tel);
        address = (EditTextFont) findViewById(R.id.rig_address);
        email = (EditTextFont) findViewById(R.id.rig_email);

        name.setText(SharedPrefHelper.read(context, FinalValuesHelepr.NAME));
        tel.setText(SharedPrefHelper.read(context, FinalValuesHelepr.TEL));
        address.setText(SharedPrefHelper.read(context, FinalValuesHelepr.ADDRESS));
        email.setText(SharedPrefHelper.read(context, FinalValuesHelepr.EMAIL));



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_rigester) {
            SharedPrefHelper.write(context,FinalValuesHelepr.NAME,name.getText().toString());
            SharedPrefHelper.write(context,FinalValuesHelepr.TEL,tel.getText().toString());
            SharedPrefHelper.write(context,FinalValuesHelepr.ADDRESS,address.getText().toString());
            SharedPrefHelper.write(context,FinalValuesHelepr.EMAIL,email.getText().toString());

            ((RegisterActivity) context).finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
