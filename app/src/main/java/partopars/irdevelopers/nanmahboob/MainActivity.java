package partopars.irdevelopers.nanmahboob;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.method.CharacterPickerDialog;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import Adapter.ListViewObjectAdapter;
import DataModel.Group;
import Helpers.HttpHelper;
import Helpers.RamHelper;
import Helpers.RtlSupportHelper;
import Helpers.ServerAddress;
import Intefaces.CallBackAsync;
import Intefaces.ListViewItemINTERFACE;

public class MainActivity extends AppCompatActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    Context context;
    ListView listView;


    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;



    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        listView = (ListView) findViewById(R.id.listView);
// Load Groups
        new HttpHelper().post(context, ServerAddress.funcFile, "tag=groups", new CallBackAsync() {
            @Override
            public void onSuccessFinish(String result) {
            //    Toast.makeText(context, result, Toast.LENGTH_LONG).show();
                try {
                    JSONArray jsonArray = new JSONArray(result);
                    ArrayList<Group> groups = Group.getArrayListFromJsonArray(jsonArray);
                    listView.setAdapter(new ListViewObjectAdapter<Group>(context, groups));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String errorMessage) {
                Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show();
            }
        });

// Load Products
        new HttpHelper().post(context, ServerAddress.funcFile, "tag=products", new CallBackAsync() {
            @Override
            public void onSuccessFinish(String result) {
                //TODO Make Json
            }

            @Override
            public void onError(String errorMessage) {

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Group group = ((Group.Holder) view.getTag()).group;
                RamHelper.group = group;
//TODO Start product activity
                Intent intent = new Intent(context, ProductsActivity.class);
                startActivity(intent);
            }
        });


        RtlSupportHelper.forceRTLIfSupported((Activity) context);

        //getSupportActionBar().setIcon(R.drawable.ic_drawer2);

        prepareActionbar();


        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        fragmentManager.beginTransaction()
//                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
//                .commit();
    }


    //----------------------------------------------------------------------------------------------
    private void prepareActionbar() {
        try {

            final ViewGroup customActionBar = (ViewGroup) getLayoutInflater().inflate(R.layout.actionbar_nav,
                    null);
            final ActionBar actionBar = getSupportActionBar();
            getSupportActionBar().setHomeButtonEnabled(false);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayShowCustomEnabled(true);
            getSupportActionBar().setIcon(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
            ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,
                    ActionBar.LayoutParams.MATCH_PARENT);
            getSupportActionBar().setCustomView(customActionBar, layoutParams);
            Toolbar parent = (Toolbar) customActionBar.getParent();
            parent.setContentInsetsAbsolute(0, 0);


            ImageView navigationDrawerOpenClose = (ImageView) customActionBar.findViewById(R.id.ac_nav);
            navigationDrawerOpenClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mNavigationDrawerFragment.isDrawerOpen())
                        mNavigationDrawerFragment.mDrawerLayout.closeDrawer(Gravity.RIGHT);
                    else
                        mNavigationDrawerFragment.mDrawerLayout.openDrawer(Gravity.RIGHT);
                }
            });
//
//            ImageView icon = (ImageView) customActionBar.findViewById(R.id.ac_icon);
//            icon.setVisibility(View.GONE);
//
//            TextView title = (TextView) customActionBar.findViewById(R.id.ac_title);
//
//
//
//            LinearLayout nav = (LinearLayout) customActionBar.findViewById(R.id.ac_nav_layout);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
