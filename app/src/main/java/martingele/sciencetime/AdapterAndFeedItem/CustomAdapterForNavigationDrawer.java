package martingele.sciencetime.AdapterAndFeedItem;


import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import martingele.sciencetime.R;

public class CustomAdapterForNavigationDrawer extends ArrayAdapter<String> {

    Context c;
    String[] resource;
    int[] images;
    LayoutInflater inflater;

    public CustomAdapterForNavigationDrawer(Context context, String[] resource, int[] images) {
        super(context, R.layout.drawer_list_items, resource);
        this.c = context;
        this.resource = resource;
        this.images = images;

    }

    //this class will hold the views
    public class ViewHolder {


        TextView nameOfTheme;
        ImageView imageViewTheme;


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.drawer_list_items, null);
        }
        //otherwise
        ViewHolder holder = new ViewHolder();
        holder.nameOfTheme = (TextView) convertView.findViewById(R.id.text_drawer);
        holder.imageViewTheme = (ImageView) convertView.findViewById(R.id.image_list);

        //seting up the text font

        holder.nameOfTheme.setTypeface(Typeface.createFromAsset(c.getAssets(), "Roboto-Black.ttf"));
        holder.nameOfTheme.setText(resource[position]);

        holder.imageViewTheme.setImageResource(images[position]);

        return convertView;


    }
}