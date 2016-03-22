package camzon.com.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import camzon.com.R;
import camzon.com.app.AppController;
import camzon.com.model.Home;

/**
 * Created by Thuon on 3/22/2016.
 */
public class AdapterHome extends RecyclerView.Adapter<AdapterHome.MyViewHolder> {
    private Activity activity;
    private List<Home> homes;
    Home home;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    private ClickListener clickListener;

    public AdapterHome(Activity activity, List<Home> homes) {
        this.activity = activity;
        this.homes = homes;
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        NetworkImageView image;
        TextView title, description,time;

        public MyViewHolder(View itemView) {
            super(itemView);
            image = (NetworkImageView) itemView.findViewById(R.id.image);
            title = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.description);
            time = (TextView) itemView.findViewById(R.id.time);
            itemView.setClickable(true);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            if (clickListener != null) {
                clickListener.itemClicked(v, getLayoutPosition());
            }
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_home, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(v);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();

        home = homes.get(position);
        holder.image.setImageUrl(home.getImage(), imageLoader);
        holder.title.setText(home.getTitle());
        holder.description.setText(home.getDescription());
        holder.time.setText(home.getTime());
    }

    @Override
    public int getItemCount() {
        return homes.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
    public interface ClickListener {
        public void itemClicked(View view, int position);
    }
    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }
}
