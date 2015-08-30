package partopars.irdevelopers.nanmahboob;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import org.json.JSONObject;

import java.util.ArrayList;

import Adapter.ListViewObjectAdapter;
import DataModel.Group;
import Helpers.FirstRunHelper;
import Helpers.GroupsHelper;
import Helpers.ObjectToJsonConvertor;
import Helpers.ProductsHealper;
import Helpers.RamHelper;
import Helpers.RtlSupportHelper;
import Intefaces.CallBack;
import Intefaces.CallBackGroup;

public class MainActivity extends AppCompatActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    Context context;
    ListView listView;
    private NavigationDrawerFragment mNavigationDrawerFragment;
    //private CharSequence mTitle;
    private boolean productsPage = false;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;


        listView = (ListView) findViewById(R.id.listView);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.sweep_refresh_layout);
        RtlSupportHelper.forceRTLIfSupported((Activity) context);
        prepareActionbar();
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        //     mTitle = getTitle();

        FirstRunHelper.init(context);





        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                hideLoading();
                GroupsHelper.syncOnline(context, new CallBackGroup() {
                    @Override
                    public void onSuccess(ArrayList<Group> groups) {
                        listView.setAdapter(new ListViewObjectAdapter<Group>(context, groups));
                        hideLoading();
                    }

                    @Override
                    public void onError(String errorMessage) {
                        hideLoading();
                    }
                });
            }
        });


        showLoading();
        GroupsHelper.getGroups(context, new CallBackGroup() {
            @Override
            public void onSuccess(ArrayList<Group> groups) {
                listView.setAdapter(new ListViewObjectAdapter<Group>(context, groups));
                hideLoading();
            }

            @Override
            public void onError(String errorMessage) {
                hideLoading();
            }
        });



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final Group group = ((Group.Holder) view.getTag()).group;
                RamHelper.group = group;
                Intent intent = new Intent(context, ProductsActivity.class);
                startActivity(intent);
            }
        });


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
