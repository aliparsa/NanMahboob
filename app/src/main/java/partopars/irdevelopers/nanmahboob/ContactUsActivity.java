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

public class ContactUsActivity extends ActionBarActivity {

    private Context context;

    EditTextFont name;
    EditTextFont tel;
    EditTextFont message;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        context=this;

        RtlSupportHelper.forceRTLIfSupported((Activity) context);

        name = (EditTextFont) findViewById(R.id.rig_name);
        tel = (EditTextFont) findViewById(R.id.rig_tel);
        message = (EditTextFont) findViewById(R.id.body_message);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contact_us, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_contact_us) {
            //TODO send message ????

            ((ContactUsActivity) context).finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
