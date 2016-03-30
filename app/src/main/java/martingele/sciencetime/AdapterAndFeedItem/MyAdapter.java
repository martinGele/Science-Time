package martingele.sciencetime.AdapterAndFeedItem;

import android.content.Context;
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

/**
 * Created by rishabh on 26-02-2016.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    ArrayList<FeedItem> feedItems;
    Context context;


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

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {

            case 1:
                View view = LayoutInflater.from(context).inflate(R.layout.custum_row_news_item, parent, false);
                MyViewHolder holder = new MyViewHolder(view);

                return holder;
            case 2:
                View view1 = LayoutInflater.from(context).inflate(R.layout.custom_tow_item1, parent, false);
                MyViewHolder holder1 = new MyViewHolder(view1);

                return holder1;

        }
        return null;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        FeedItem current = feedItems.get(position);
        holder.Title.setText(current.getTitle());
        holder.Description.setText(current.getDescription());
        holder.Date.setText(current.getPubDate());
        Picasso.with(context).load(current.getThumbnailUrl()).resize(90, 90).into(holder.Thumbnail);


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
