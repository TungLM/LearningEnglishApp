package tung.vn.learningenglishbypicture.fragment;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import tung.vn.learningenglishbypicture.ApplicationApp;
import tung.vn.learningenglishbypicture.R;
import tung.vn.learningenglishbypicture.adapter.BaseAdapter;
import tung.vn.learningenglishbypicture.common.Utility;
import tung.vn.learningenglishbypicture.model.Alphab;

/**
 * Created by seven64 on 6/8/2016.
 */
public class Alphabet extends Fragment {
    private RecyclerView recyclerView;
    private BaseAdapter adapter;
    private List<Alphab> alphabList;

    public String getTaget() {
        return taget;
    }

    public void setTaget(String taget) {
        this.taget = taget;
    }

    private String taget = null;

    @Override
    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    private static Context context;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Utility.stopBG();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.alphabet_fragment, container, false);
        recyclerView = (RecyclerView) root.findViewById(R.id.recycler_view);
        alphabList = new ArrayList<>();
        adapter = new BaseAdapter(context, alphabList);
        int orientation = ApplicationApp.getAppContext().getResources().getConfiguration().orientation;
        int spanCount = 3;
        int spacing = 5;
        if (orientation == 1) {
            spanCount = 2;
            spacing = 8;
        }
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, spanCount);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(spanCount, dpToPx(spacing), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        prepareAlbums();
        return root;
    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    private void prepareAlbums() {

        Integer[] colors = new Integer[]{
                R.color.a,
                R.color.a1,
                R.color.a2,
                R.color.b,
                R.color.c,
                R.color.d,
                R.color.d1,
                R.color.e,
                R.color.e1,
                R.color.g,
                R.color.h, R.color.i, R.color.k, R.color.l, R.color.m, R.color.n, R.color.o,
                R.color.o1, R.color.o2, R.color.p, R.color.q, R.color.r, R.color.s, R.color.t, R.color.u,
                R.color.u1, R.color.v, R.color.x, R.color.y};

        int[] covers = new int[]{
                R.mipmap.a,
                R.mipmap.a2,
                R.mipmap.a3,
                R.mipmap.b,
                R.mipmap.c,
                R.mipmap.d,
                R.mipmap.d1,
                R.mipmap.e,
                R.mipmap.e1,
                R.mipmap.g,
                R.mipmap.h, R.mipmap.i, R.mipmap.k, R.mipmap.l, R.mipmap.m, R.mipmap.n, R.mipmap.o,
                R.mipmap.o1, R.mipmap.o2, R.mipmap.p, R.mipmap.q, R.mipmap.r, R.mipmap.s, R.mipmap.t, R.mipmap.u,
                R.mipmap.u1, R.mipmap.v, R.mipmap.x, R.mipmap.y};

        int[] name = new int[] {R.string.a, R.string.a1, R.string.a2, R.string.b, R.string.c,
                R.string.d, R.string.d1, R.string.e, R.string.e1, R.string.g, R.string.h, R.string.i,
                R.string.k, R.string.l, R.string.m, R.string.n, R.string.o, R.string.o1, R.string.o2,
                R.string.p, R.string.q, R.string.r, R.string.s, R.string.t, R.string.u, R.string.u1,
                R.string.v, R.string.x, R.string.y};

        int[] nameEL = new int[] {R.string.aa, R.string.aa1, R.string.aa2, R.string.bb, R.string.cc,
                R.string.dd, R.string.dd1, R.string.ee, R.string.ee1, R.string.gg, R.string.hh, R.string.ii,
                R.string.kk, R.string.ll, R.string.mm, R.string.nn, R.string.oo, R.string.oo1, R.string.oo2,
                R.string.pp, R.string.qq, R.string.rr, R.string.ss, R.string.tt, R.string.uu, R.string.uu1,
                R.string.vv, R.string.xx, R.string.yy};

        int[] nameVN = new int[] {R.string.aaa, R.string.aaa1, R.string.aaa2, R.string.bbb, R.string.ccc,
                R.string.ddd, R.string.ddd1, R.string.eee, R.string.eee1, R.string.ggg, R.string.hhh, R.string.iii,
                R.string.kkk, R.string.lll, R.string.mmm, R.string.nnn, R.string.ooo, R.string.ooo1, R.string.ooo2,
                R.string.ppp, R.string.qqq, R.string.rrr, R.string.sss, R.string.ttt, R.string.uuu, R.string.uuu1,
                R.string.vvv, R.string.xxx, R.string.yyy};

        /*ENGLISH*/
        int[] coversEL = new int[]{
                R.mipmap.t,
                R.mipmap.el_b,
                R.mipmap.el_c,
                R.mipmap.el_d,
                R.mipmap.el_e,
                R.mipmap.el_f,
                R.mipmap.el_g,
                R.mipmap.el_h, R.mipmap.k, R.mipmap.el_j, R.mipmap.el_k, R.mipmap.el_l, R.mipmap.el_m, R.mipmap.el_n, R.mipmap.el_o,
                R.mipmap.el_p, R.mipmap.el_q, R.mipmap.el_r, R.mipmap.el_s, R.mipmap.el_t, R.mipmap.u,
                R.mipmap.el_v, R.mipmap.el_w, R.mipmap.el_x, R.mipmap.el_y, R.mipmap.el_z};
        int[] namesEL = new int[] {R.string.a, R.string.b, R.string.c,
                R.string.d, R.string.e, R.string.f, R.string.g, R.string.h, R.string.i, R.string.j,
                R.string.k, R.string.l, R.string.m, R.string.n, R.string.o,
                R.string.p, R.string.q, R.string.r, R.string.s, R.string.t, R.string.u, R.string.v,
                R.string.w, R.string.x, R.string.y, R.string.z};

        int[] meanEL = new int[] {R.string.el_a, R.string.el_b, R.string.el_c,
                R.string.el_d, R.string.el_e, R.string.el_f, R.string.el_g, R.string.el_h, R.string.el_i, R.string.el_j,
                R.string.el_k, R.string.el_l, R.string.el_m, R.string.el_n, R.string.el_o,
                R.string.el_p, R.string.el_q, R.string.el_r, R.string.el_s, R.string.el_t, R.string.el_u, R.string.el_v,
                R.string.el_w, R.string.el_x, R.string.el_y, R.string.el_z};

        int[] pronunce = new int[] {R.string.pron_a, R.string.pron_b, R.string.pron_c,
                R.string.pron_d, R.string.pron_e, R.string.pron_f, R.string.pron_g, R.string.pron_h, R.string.pron_i, R.string.pron_j,
                R.string.pron_k, R.string.pron_l, R.string.pron_m, R.string.pron_n, R.string.pron_o,
                R.string.pron_p, R.string.pron_q, R.string.pron_r, R.string.pron_s, R.string.pron_t, R.string.pron_u, R.string.pron_v,
                R.string.pron_w, R.string.pron_x, R.string.pron_y, R.string.pron_z};

        List<Integer> lstColor = Arrays.asList(colors);
        Collections.shuffle(lstColor);
        Alphab a;

        if ("Tiếng Việt".equalsIgnoreCase(Locale.getDefault().getDisplayLanguage())) {
            Utility.playBG(R.raw.alphabet_vn);
            for (int i = 0; i < covers.length; i++) {
                a = new Alphab(getString(name[i]), getString(nameEL[i]), covers[i], lstColor.get(i), getString(nameVN[i]), null);
                alphabList.add(a);
            }
        } else {
            Utility.playBG(R.raw.alphabet_el);
            for (int i = 0; i < coversEL.length; i++) {
                a = new Alphab(getString(namesEL[i]), getString(pronunce[i]), coversEL[i], lstColor.get(i), getString(meanEL[i]), null);
                alphabList.add(a);
            }
        }

        adapter.notifyDataSetChanged();
    }
}
