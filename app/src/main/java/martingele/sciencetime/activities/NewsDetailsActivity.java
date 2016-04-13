package martingele.sciencetime.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

import martingele.sciencetime.R;

public class NewsDetailsActivity extends AppCompatActivity {

    WebView web;
    ProgressBar bar;
    public Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        web = (WebView) findViewById(R.id.web_view_details);


        bar = (ProgressBar) findViewById(R.id.progressBarDetail);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_detail);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_action_back);


        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.share) {

                    shareTextUrl();
                }
                return true;
            }
        });
        DealingWIthWebView();


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(NewsDetailsActivity.this, TopScienceNewsActivity.class);
                startActivity(intent);
            }
        });


    }


    //webView object and all the methods that are invoked
    public void DealingWIthWebView() {
        web.getSettings().setJavaScriptEnabled(true);
        web.getSettings().setLoadWithOverviewMode(true);
        web.setInitialScale(150);
        web.setWebChromeClient(new WebChromeClient() {

            public void onProgressChanged(WebView view, int progress) {


                if (progress < 100 && bar.getVisibility() == ProgressBar.GONE) {
                    bar.setVisibility(ProgressBar.VISIBLE);

                }
                bar.setProgress(progress);
                if (progress == 100) {
                    bar.setVisibility(ProgressBar.GONE);

                }
            }
        });

        bundle = getIntent().getExtras();
        web.loadUrl(bundle.getString("Link"));


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_details, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.share) {

            return true;
        }


        return super.onOptionsItemSelected(item);
    }


    private void shareTextUrl() {
        Intent share = new Intent(android.content.Intent.ACTION_SEND);
        share.setType("text/plain");
        share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

        // Add data to the intent, the receiving app will decide
        // what to do with it.
        share.putExtra(Intent.EXTRA_SUBJECT, "Title Of The Post");
        share.putExtra(Intent.EXTRA_TEXT, bundle.getString("Link"));

        startActivity(Intent.createChooser(share, "Share link!"));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(NewsDetailsActivity.this, TopScienceNewsActivity.class);
        startActivity(intent);
    }


}
