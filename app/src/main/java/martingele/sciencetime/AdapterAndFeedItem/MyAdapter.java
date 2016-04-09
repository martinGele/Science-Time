package martingele.sciencetime.AdapterAndFeedItem;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import martingele.sciencetime.R;
import martingele.sciencetime.activities.PopUpActivity;
import martingele.sciencetime.animations.AnimatonUtility;

/**
 * Created by rishabh on 26-02-2016.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    ArrayList<FeedItem> feedItems;
    Context context;
    // public static FeedItem current;


    private int previousPosition = 0;


    public MyAdapter(Context context, ArrayList<FeedItem> feedItems) {
        this.feedItems = feedItems;
        this.context = context;
    }


    /**
     * this method proved me that i know this
     * shit FIRST WE TAKE THE FEED ITEM CLASS AND THAN WE TAKE THE FEED ITEMS FROM THE ARRAY LIST
     * AND THAN WE CHECK FOR THE ITEM IN THE ARRAY LIST IF IT'S PRESANT we go with the first view
     * if its not we set up the other view
     */
    @Override
    public int getItemViewType(int position) {
        FeedItem current1 = feedItems.get(position);

        if (current1.getThumbnailUrl() != null) {

            return 1;
        } else if (current1.getThumbnailUrl() == null) {

            return 2;
        }

        return position;
    }

    //in this view holder i bind two views, one in case there is no picture provided from the XML
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {

            case 1:
                View view = LayoutInflater.from(context).inflate(R.layout.custum_row_news_item_with_picture, parent, false);
                MyViewHolder holder = new MyViewHolder(view);

                return holder;
            case 2:
                View view1 = LayoutInflater.from(context).inflate(R.layout.custom_row_without_picture, parent, false);
                MyViewHolder holder1 = new MyViewHolder(view1);

                return holder1;

        }
        return null;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final FeedItem current = feedItems.get(position);
        holder.Title.setText(current.getTitle());
        holder.Description.setText(current.getDescription());
        holder.Date.setText(current.getPubDate());
        Picasso.with(context).load(current.getThumbnailUrl()).resize(90, 90).into(holder.Thumbnail);

        //this will handle clicks from the recycleView

        if (position > previousPosition) { // We are scrolling DOWN

            AnimatonUtility.animate(holder, true);

        } else { // We are scrolling UP
            AnimatonUtility.animate(holder, false);


        }
        previousPosition = position;


        //setting click listiner on the card from the
        // recycle view to push link to the other
        // view where it will be handled on webview

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, PopUpActivity.class);
                intent.putExtra("Description", current.getDescription());
                intent.putExtra("Link", current.getLink());
                intent.putExtra("Image", current.getThumbnailUrl());
                intent.putExtra("Title", current.getTitle());

                context.startActivity(intent);
            }
        });


    }


    @Override
    public int getItemCount() {
        try {

            return feedItems.size();
        } catch (Exception e) {

            Log.d("Cannot create feed", "Cannot catch feeitems");
        }
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView Title, Description, Date;
        ImageView Thumbnail;
        CardView cardView;


        public MyViewHolder(View itemView) {
            super(itemView);
            Title = (TextView) itemView.findViewById(R.id.title_text);
            Description = (TextView) itemView.findViewById(R.id.description_text);
            Date = (TextView) itemView.findViewById(R.id.date_text);
            Thumbnail = (ImageView) itemView.findViewById(R.id.thumb_img);
            cardView = (CardView) itemView.findViewById(R.id.cardview);
        }
    }


}
