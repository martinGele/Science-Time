package martingele.sciencetime.activities;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import martingele.sciencetime.AdapterAndFeedItem.CustomAdapterForNavigationDrawer;
import martingele.sciencetime.R;
import martingele.sciencetime.rss_readers.ReadRssHealth;


public class HealthNewsActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {


    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    RecyclerView recyclerView;
    ProgressBar bar;
    Handler handler;
    private ListView listView;

    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_news);


        gettingTheListViewForTheNavigationDrawerAndBindingItems();
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

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_layout);
        swipeRefreshLayout.setOnRefreshListener(this);

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
        final ReadRssHealth readRssAllNews = new ReadRssHealth(this, recyclerView, bar);
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
        System.exit(0);

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


            return true;
        }


        return super.onOptionsItemSelected(item);
    }


    public void gettingTheListViewForTheNavigationDrawerAndBindingItems() {

        String[] themes = {"Top Science", "Top News", "Health", "Technology", "Environment", "Society", "Most Popular"};
        int[] images = {R.drawable.ic_action_all_news,
                R.drawable.ic_action_name_top_news,
                R.drawable.ic_action_health,
                R.drawable.ic_action_technology,
                R.drawable.ic_action_enviorment,
                R.drawable.ic_action_society,
                R.drawable.ic_action_popular};
        //custom adapter for the listview with image and text
        CustomAdapterForNavigationDrawer adapter = new CustomAdapterForNavigationDrawer(this, themes, images);
        listView = (ListView) findViewById(R.id.drawer_listview);
        listView.setDivider(null);
        listView.setDividerHeight(20);
        listView.setAdapter(adapter);
        View footerView = ((LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.footer_layout, null, false);
        listView.addFooterView(footerView);
        footerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
                startActivity(intent);
                finish();
                System.exit(0);
            }
        });
        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Intent intent = new Intent(HealthNewsActivity.this, TopScienceNewsActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        Intent intent1 = new Intent(HealthNewsActivity.this, TopNewsActivity.class);
                        startActivity(intent1);

                        break;
                    case 2:
                        Intent intent2 = new Intent(HealthNewsActivity.this, HealthNewsActivity.class);
                        startActivity(intent2);
                        break;

                    case 3:
                        Intent intent3 = new Intent(HealthNewsActivity.this, TechnologyNewsActivity.class);
                        startActivity(intent3);
                        break;
                    case 4:
                        Intent intent4 = new Intent(HealthNewsActivity.this, EnviormentNewsActivity.class);
                        startActivity(intent4);
                        break;
                    case 5:
                        Intent intent5 = new Intent(HealthNewsActivity.this, SocietyNewsActivity.class);
                        startActivity(intent5);
                        break;
                    case 6:
                        Intent intent6 = new Intent(HealthNewsActivity.this, MostPopularNewsActivity.class);
                        startActivity(intent6);
                        break;

                    default:
                        break;
                }
            }
        });


    }

    public void setingUpTheToolbarAndNavigationDrawer() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Health News");
        getSupportActionBar().setHomeButtonEnabled(true);


        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        listView.bringToFront();
        mDrawerLayout.requestLayout();


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


    @Override
    public void onRefresh() {


        ReadRssHealth read = new ReadRssHealth(this, recyclerView, bar);
        read.execute();


        if (swipeRefreshLayout.isRefreshing()) {

            swipeRefreshLayout.setRefreshing(false);
        }


    }

}