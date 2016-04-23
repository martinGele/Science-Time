package martingele.sciencetime.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import martingele.sciencetime.R;

public class PopUpActivity extends AppCompatActivity {

    TextView description_pop_up;
    ImageView imageViewPopUp;
    Button readButton;
    TextView title_text;
    ImageButton x_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up);
        CreatePopUpScreen();

        Intent i = getIntent();
        String description = i.getStringExtra("Description");
        final String link = i.getStringExtra("Link");
        String image = i.getStringExtra("Image");
        String title = i.getStringExtra("Title");

        //set description
        description_pop_up = (TextView) findViewById(R.id.description_pop_up);
        description_pop_up.setText(description);

        //set image
        // imageViewPopUp = (ImageView) findViewById(R.id.image_pop_up);
        // Picasso.with(this).load(image).fit().into(imageViewPopUp);

        //set title
        title_text = (TextView) findViewById(R.id.text_title1);
        title_text.setText(title);


        readButton = (Button) findViewById(R.id.button_read_story);
        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(PopUpActivity.this, NewsDetailsActivity.class);
                intent.putExtra("Link", link);
                startActivity(intent);
            }
        });


        x_button = (ImageButton) findViewById(R.id.x);
        x_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    public void CreatePopUpScreen() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.widthPixels;


        getWindow().setLayout((int) (width * .9), (int) (height * 1.2));


    }


}



