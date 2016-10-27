package tung.vn.learningenglishbypicture.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import tung.vn.learningenglishbypicture.R;
import tung.vn.learningenglishbypicture.model.NavDrawerItem;

/**
 * Created by seven64 on 10/26/2016.
 */

public class NavigationDrawerAdapter extends RecyclerView.Adapter<NavigationDrawerAdapter.MyViewHolder> {
    List<NavDrawerItem> mData = Collections.emptyList();
    private LayoutInflater inflater;
    private Context mContext;

    // Constructor
    public NavigationDrawerAdapter(Context context, List<NavDrawerItem> data) {
        this.mContext = context;
        inflater = LayoutInflater.from(context);
        this.mData = data;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.nav_drawer_row, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        NavDrawerItem current = mData.get(position);
        holder.title.setText(current.getTitle());

    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
        }
    }
}

