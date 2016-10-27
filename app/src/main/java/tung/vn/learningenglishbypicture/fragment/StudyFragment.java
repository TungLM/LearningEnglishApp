package tung.vn.learningenglishbypicture.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import tung.vn.learningenglishbypicture.R;

/**
 * Created by seven64 on 10/27/2016.
 */

public class StudyFragment extends Fragment {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_study, container, false);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        tabLayout = (TabLayout) view.findViewById(R.id.tab);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
        return view;
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        Alphabet alphabet = new Alphabet();
        alphabet.setContext(getActivity());
        alphabet.setTaget("alphabet");
        viewPagerAdapter.addFragment(alphabet, "Alphabet");
        Numbers numbers = new Numbers();
        numbers.setContext(getActivity());
        viewPagerAdapter.addFragment(numbers, "Numbers");
        viewPager.setAdapter(viewPagerAdapter);
    }
    private void setupTabIcons() {
        TextView tabOne = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.custom_tab, null);
        tabOne.setText(getString(R.string.title_bar1));
        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.icon1, 0, 0);
        tabLayout.getTabAt(0).setCustomView(tabOne);

        TextView tabTwo = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.custom_tab, null);
        tabTwo.setText(R.string.title_bar2);
        tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.icon2, 0, 0);
        tabLayout.getTabAt(1).setCustomView(tabTwo);
//
//        TextView tabThree = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
//        tabThree.setText("Numbers");
//        tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.ic_tab_contacts, 0, 0);
//        tabLayout.getTabAt(2).setCustomView(tabThree);
    }
}
class ViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> mTitle = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
    public void addFragment(Fragment fragment, String title) {
        fragmentList.add(fragment);
        mTitle.add(title);
    }
}
