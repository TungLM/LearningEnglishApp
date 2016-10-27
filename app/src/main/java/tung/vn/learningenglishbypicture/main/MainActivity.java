package tung.vn.learningenglishbypicture.main;

import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import tung.vn.learningenglishbypicture.R;
import tung.vn.learningenglishbypicture.fragment.Alphabet;
import tung.vn.learningenglishbypicture.fragment.FragmentDrawer;
import tung.vn.learningenglishbypicture.fragment.Numbers;
import tung.vn.learningenglishbypicture.fragment.StudyFragment;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener, FragmentDrawer.FragmentDrawerListener{
    private static final String TAG = "MainActivity1";
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private static TextToSpeech tts;
    private int[] tabIcons = {
            R.mipmap.ic_tab_favourite,
            R.mipmap.ic_tab_call,
            R.mipmap.ic_tab_contacts
    };
    private AdView mAdView;
private FragmentDrawer fragmentDrawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("TTS", "onCreate");
        setContentView(R.layout.main_activity);
        /*mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        // open ads
        mAdView.loadAd(adRequest);*/


        tts = new TextToSpeech(this, this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        Display back button bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        viewPager = (ViewPager) findViewById(R.id.viewpager);
//        setupViewPager(viewPager);
//        tabLayout = (TabLayout) findViewById(R.id.tabs);
//        tabLayout.setupWithViewPager(viewPager);
//        setupTabIcons();
        fragmentDrawer = (FragmentDrawer) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        fragmentDrawer.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);
        fragmentDrawer.setListener(this);

        displayView(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Log.d(TAG, "onOptionsItemSelected.id " + id);
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupTabIcons() {
        TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabOne.setText(getString(R.string.title_bar1));
        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.icon1, 0, 0);
        tabLayout.getTabAt(0).setCustomView(tabOne);

        TextView tabTwo = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabTwo.setText(R.string.title_bar2);
        tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.icon2, 0, 0);
        tabLayout.getTabAt(1).setCustomView(tabTwo);
//
//        TextView tabThree = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
//        tabThree.setText("Numbers");
//        tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.ic_tab_contacts, 0, 0);
//        tabLayout.getTabAt(2).setCustomView(tabThree);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        Alphabet alphabet = new Alphabet();
        alphabet.setContext(this);
        alphabet.setTaget("alphabet");
        adapter.addFrag(alphabet, "Alphabet");
        Numbers numbers = new Numbers();
        numbers.setContext(this);
        adapter.addFrag(numbers, "Numbers");
//        adapter.addFrag(new Alphabet(), "THREE");
//        adapter.addFrag(new Alphabet(), "FOUR");
//        adapter.addFrag(new Alphabet(), "FIVE");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onInit(int status) {
        Log.e("TTS", "onInit");
        if (status == TextToSpeech.SUCCESS) {

            int result = tts.setLanguage(Locale.ENGLISH);

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            } else {
                Log.e("TTS", "setEnabled");
//                btnSpeak.setEnabled(true);
//                speakOut();
            }

        } else {
            Log.e("TTS", "Initilization Failed!");
        }
    }

    public static void speakOut(String txt) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tts.speak(txt, TextToSpeech.QUEUE_FLUSH, null, "a");
        } else {
            tts.speak(txt, TextToSpeech.QUEUE_FLUSH, null);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    @Override
    protected void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
        tts.shutdown();
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }

    private void displayView (int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new StudyFragment();
                break;
            default:
                break;
        }
        if(fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container_body, fragment).commit();
        }
    }
}

class ViewPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager manager) {
        super(manager);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addFrag(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }

}
