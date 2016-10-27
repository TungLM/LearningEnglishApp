package tung.vn.learningenglishbypicture.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import tung.vn.learningenglishbypicture.R;
import tung.vn.learningenglishbypicture.adapter.NavigationDrawerAdapter;
import tung.vn.learningenglishbypicture.model.NavDrawerItem;

/**
 * Created by seven64 on 10/26/2016.
 */

public class FragmentDrawer extends Fragment {
    public void setListener(FragmentDrawerListener listener) {
        this.listener = listener;
    }

    private RecyclerView recyclerView;
    private FragmentDrawerListener listener;
    private NavigationDrawerAdapter adapter;
    private String[] arrTitles = null;
    private View containerView;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;

    public interface FragmentDrawerListener {
        void onDrawerItemSelected(View view, int position);
    }

    private List<NavDrawerItem> getData() {
        Log.d("TungLM", "getData");
        List<NavDrawerItem> data = new ArrayList<>();
        NavDrawerItem navDrawerItem = null;
        for (int i = 0; i < arrTitles.length; i++) {
            navDrawerItem = new NavDrawerItem();
            navDrawerItem.setTitle(arrTitles[i]);
            data.add(navDrawerItem);
        }
        return data;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("TungLM", "onCreate");
        arrTitles = getActivity().getResources().getStringArray(R.array.nav_drawer_labels);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("TungLM", "onCreateView");
        View view = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.drawerList);
        adapter = new NavigationDrawerAdapter(getActivity(), getData());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Log.d("TungLM", "onClick");
                listener.onDrawerItemSelected(view, position);
                mDrawerLayout.closeDrawer(containerView);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        return view;
    }

    public void setUp(int fragmentId, DrawerLayout drawerLayout, final Toolbar toolbar) {
        containerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                Log.d("TungLM", "onDrawerOpened");
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                toolbar.setAlpha(1 - slideOffset / 2);
            }
        };
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });
    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private ClickListener listener;
        // when click will call onInterceptTouchEvent method then use gestureDetector to detect click or long press
        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {
            this.listener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
//                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
//                    if (child != null && clickListener != null) {
//                        clickListener.onClick(child, recyclerView.getChildLayoutPosition(child));
//                    }
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    super.onLongPress(e);
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && listener != null && gestureDetector.onTouchEvent(e)) {
                listener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

}


