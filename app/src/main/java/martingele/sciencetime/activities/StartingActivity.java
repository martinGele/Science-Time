package martingele.sciencetime.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import martingele.sciencetime.R;

public class StartingActivity extends AppCompatActivity {

    static ConnectivityManager cm;
    AlertDialog dailog;
    AlertDialog.Builder build;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting);

        checkConncetion();
    }


    public void checkConncetion() {


        cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        build = new AlertDialog.Builder(this);

        if (cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                .isConnectedOrConnecting()
                || cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
                .isConnectedOrConnecting()// if connection is
// there screen goes
// to next screen
// else shows
// message toast
                ) {
            Log.e("cm value", "" + cm.getAllNetworkInfo().toString());
            Toast.makeText(StartingActivity.this, "net! ", 2000)
                    .show();
            Thread mythread = new Thread() {
                public void run() {
                    try {

                        sleep(4000);

                    } catch (Exception e) {
                    } finally {
                        Intent intent = new Intent(StartingActivity.this,
                                AllNewsActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            };
            mythread.start();
        } else {

            build.setMessage("This application requires Internet connection.Would you connect to            internet ?");
            build.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // TODO Auto-generated method stub
                    finish();
                    startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));

                }
            });
            build.setNegativeButton("No", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // TODO Auto-generated method stub
                    build.setMessage("Are sure you want to exit?");
                    build.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // TODO Auto-generated method stub
                            finish();
                        }
                    });
                    build.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // TODO Auto-generated method stub
                            finish();
                            Intent intent = new Intent(StartingActivity.this,
	  StartingActivity.class);
                            startActivity(intent);

                            dialog.dismiss();

                        }
                    });
                    dailog = build.create();
                    dailog.show();
                }
            });
            dailog = build.create();
            dailog.show();

        }


    }

}