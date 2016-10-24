package tung.vn.learningenglishbypicture.adapter;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import tung.vn.learningenglishbypicture.MainActivity;
import tung.vn.learningenglishbypicture.R;
import tung.vn.learningenglishbypicture.common.Utility;
import tung.vn.learningenglishbypicture.model.Alphab;

/**
 * Created by seven64 on 6/8/2016.
 */
public class BaseAdapter extends RecyclerView.Adapter<BaseAdapter.MyViewHolder> {
    private Context mContext;
    private List<Alphab> alphabList;
    int[] audios = new int[]{R.raw.a, R.raw.a1, R.raw.a2, R.raw.b, R.raw.c, R.raw.d, R.raw.d1,
            R.raw.e, R.raw.e1, R.raw.g, R.raw.h, R.raw.i, R.raw.k, R.raw.l, R.raw.m,
            R.raw.n, R.raw.o, R.raw.o1, R.raw.o2, R.raw.p, R.raw.q, R.raw.r, R.raw.s, R.raw.t,
            R.raw.u, R.raw.u1, R.raw.v, R.raw.x, R.raw.y};

    int[] audiosEL = new int[]{R.raw.el_a, R.raw.el_b, R.raw.el_c, R.raw.el_d,
            R.raw.el_e, R.raw.el_f, R.raw.el_g, R.raw.el_h, R.raw.el_i, R.raw.el_j, R.raw.el_k, R.raw.el_l, R.raw.el_m,
            R.raw.el_n, R.raw.el_o, R.raw.el_p, R.raw.el_q, R.raw.el_r, R.raw.el_s, R.raw.el_t,
            R.raw.el_u, R.raw.el_v, R.raw.el_w, R.raw.el_x, R.raw.el_y, R.raw.el_z};

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView nameVn;
        public TextView nameEl;
        public ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            nameVn = (TextView) view.findViewById(R.id.name_vn);
            nameEl = (TextView) view.findViewById(R.id.name_el);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
//            overflow = (ImageView) view.findViewById(R.id.overflow);
        }
    }

    public BaseAdapter(Context mContext, List<Alphab> alphabs) {
        this.mContext = mContext;
        this.alphabList = alphabs;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return alphabList.size();
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Alphab album = alphabList.get(position);
        Log.e("TungLM", "audio " + album.getmAudio());
        holder.title.setText(album.getName());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            holder.title.setTextColor(mContext.getResources().getColor(album.getColor(), null));
        } else {
            holder.title.setTextColor(mContext.getResources().getColor(album.getColor()));
        }
        holder.nameVn.setText(album.getNameVn());
        holder.nameEl.setText(album.getNameEL());

        // loading album cover using Glide library
        Glide.with(mContext).load(album.getThumbnail()).into(holder.thumbnail);

        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("TungLM", "click");
                if (album.getmAudio() == null) {
                    if ("Tiếng Việt".equalsIgnoreCase(Locale.getDefault().getDisplayLanguage())) {
                        Utility.playBeep(audios[position]);
//                    Utility.speechName(1);
                    } else {
                        Utility.playBeep(audiosEL[position]);
                    }
                } else {
                    playAudioFromUrl(album.getmAudio());
                }
            }
        });
    }

    private void playAudioFromUrl(String url) {
        Log.e("TungLM", "playAudioFromUrl");
        MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
