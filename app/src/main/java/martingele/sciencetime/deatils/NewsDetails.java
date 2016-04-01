package martingele.sciencetime.deatils;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

import martingele.sciencetime.AllNewsActivity;
import martingele.sciencetime.R;

public class NewsDetails extends AppCompatActivity {

    WebView web;
    ProgressBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        web = (WebView) findViewById(R.id.web_view_details);

        bar = (ProgressBar) findViewById(R.id.progressBarDetail);
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


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_detail);
        toolbar.setNavigationIcon(R.drawable.ic_action_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewsDetails.this, AllNewsActivity.class);
                startActivity(intent);
            }
        });
        Bundle bundle = getIntent().getExtras();
        web.loadUrl(bundle.getString("Link"));


    }
}