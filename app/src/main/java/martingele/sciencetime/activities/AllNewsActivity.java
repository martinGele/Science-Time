package martingele.sciencetime.activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.ProgressBar;

import martingele.sciencetime.AdapterAndFeedItem.CustomAdapterForNavigationDrawer;
import martingele.sciencetime.R;
import martingele.sciencetime.rss_readers.ReadRssAllNews;

public class AllNewsActivity extends AppCompatActivity  {


    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    RecyclerView recyclerView;
    ProgressBar bar;
    Handler handler;

    private ListView listView;
    private String[] drawerListViewItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        handler = new Handler();

        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                //invoking the async task
                readTheRss();

            }
        };

        handler.postDelayed(runnable, 1000);


        //all the methods for the navigation drawer
        setingUpTheToolbarAndNavigationDrawer();
        // basic navigation drawer items
        gettingTheListViewForTheNavigationDrawerAndBindingItems();



    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mActionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    // run ReadRssAllNews, the parser
    public void readTheRss() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        bar = (ProgressBar) findViewById(R.id.progressBar);
        final ReadRssAllNews readRssAllNews = new ReadRssAllNews(this, recyclerView, bar);
        readRssAllNews.execute();

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mActionBarDrawerToggle.syncState();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_all_news, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (mActionBarDrawerToggle.onOptionsItemSelected(item))
            return true;

        if (id == R.id.source) {

            Intent intent = new Intent(AllNewsActivity.this, PopUpActivity.class);
            startActivity(intent);

            return true;
        }


        return super.onOptionsItemSelected(item);
    }


    public void setingUpTheToolbarAndNavigationDrawer() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("All News");
        getSupportActionBar().setHomeButtonEnabled(true);


        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);


        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_opened, R.string.drawer_close) {
            /*
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (getSupportActionBar() != null)
                    getSupportActionBar().setTitle(R.string.drawer_opened);
            }
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                if (getSupportActionBar() != null)

                getSupportActionBar().setTitle(R.string.drawer_close);
            }
        };
        */

        };
        mActionBarDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);


    }

    public void gettingTheListViewForTheNavigationDrawerAndBindingItems() {

        String[] themes = {"All News", "Top News"};
        int[] images = {R.drawable.ic_action_all_news, R.drawable.ic_action_name_top_news};
        //custom adapter for the listview with image and text
        CustomAdapterForNavigationDrawer adapter = new CustomAdapterForNavigationDrawer(this, themes, images);

        drawerListViewItems = getResources().getStringArray(R.array.items);
        listView = (ListView) findViewById(R.id.drawer_listview);
        listView.setDivider(null);
        listView.setDividerHeight(13);
        listView.setAdapter(adapter);

    }


}