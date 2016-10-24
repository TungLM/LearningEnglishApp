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
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tung.vn.learningenglishbypicture.ApplicationApp;
import tung.vn.learningenglishbypicture.R;
import tung.vn.learningenglishbypicture.adapter.BaseAdapter;
import tung.vn.learningenglishbypicture.common.Constant;
import tung.vn.learningenglishbypicture.common.Utility;
import tung.vn.learningenglishbypicture.model.Alphab;

/**
 * Created by seven64 on 6/8/2016.
 */
public class Numbers extends Fragment {
    private RecyclerView recyclerView;
    private BaseAdapter adapter;
    private List<Alphab> alphabList;

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
        Utility.stopBG();
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
        List<Integer> lstColor = Arrays.asList(colors);
        Collections.shuffle(lstColor);

        int[] images = new int[]{
                R.mipmap.n1, R.mipmap.n2, R.mipmap.n3, R.mipmap.n4, R.mipmap.n5, R.mipmap.n6, R.mipmap.n7
                , R.mipmap.n8, R.mipmap.n9, R.mipmap.n10};

        int[] numbName = new int[]{
                R.string.numb1, R.string.numb2, R.string.numb3, R.string.numb4, R.string.numb5, R.string.numb6, R.string.numb7, R.string.numb8, R.string.numb9, R.string.numb10};

        int[] numbs = new int[]{
                R.string.n1, R.string.n2, R.string.n3, R.string.n4, R.string.n5, R.string.n6, R.string.n7, R.string.n8, R.string.n9, R.string.n10};
        String[] audio = new String[]{Constant.URL_ONE, Constant.URL_TWO, Constant.URL_THREE, Constant.URL_FOUR,
                Constant.URL_FIVE, Constant.URL_SIX, Constant.URL_SEVEN, Constant.URL_EIGHT, Constant.URL_NINE, Constant.URL_TEN};

        int[] pronunNumb = new int[]{
                R.string.pron1, R.string.pron2, R.string.pron3, R.string.pron4, R.string.pron5, R.string.pron6, R.string.pron7, R.string.pron8, R.string.pron9, R.string.pron10};
        Alphab a;
        for (int i = 0; i < images.length; i++) {
            a = new Alphab(getString(numbs[i]), getString(pronunNumb[i]), images[i], lstColor.get(i), getString(numbName[i]), audio[i]);
            alphabList.add(a);
        }
        adapter.notifyDataSetChanged();
    }
}
