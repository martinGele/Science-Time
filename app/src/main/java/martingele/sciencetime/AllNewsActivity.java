package martingele.sciencetime;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

public class AllNewsActivity extends AppCompatActivity {


    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    RecyclerView recyclerView;
    ProgressBar bar;

    private ListView listView;
    private String[] drawerListViewItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //invoking the async task
        readTheRss();

        setingUpTheToolbarAndNavigationDrawer();

        gettingTheListViewForTheNavigationDrawerAndBindingItems();


    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mActionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

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

    // i had to write down this code cause on back pressed it lead to the starting activity which ckecks up the connection and lead to main activity
    @Override
    public void onBackPressed() {

        super.onBackPressed();
        finish();

    }
   /*
    @Override
    protected void onDestroy() {
        android.os.Process.killProcess(android.os.Process.myPid());
        super.onDestroy();
    }
*/
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


    public void setingUpTheToolbarAndNavigationDrawer() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_opened, R.string.drawer_close) {

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
        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);


    }

    public void gettingTheListViewForTheNavigationDrawerAndBindingItems() {
        drawerListViewItems = getResources().getStringArray(R.array.items);
        listView = (ListView) findViewById(R.id.drawer_listview);
        listView.setDivider(null);
        listView.setDividerHeight(13);
        listView.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_items, drawerListViewItems));
    }


}