package camzon.com;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import camzon.com.activity.AnimationFragment;
import camzon.com.activity.FragmentDrawer;
import camzon.com.activity.HomeFragment;
import camzon.com.activity.MusicFragment;
import camzon.com.activity.GameFragment;
import camzon.com.activity.FunnyFragment;
import camzon.com.activity.TechnologyFragment;
import camzon.com.activity.SportFragment;
import camzon.com.activity.TutorialFragment;

public class MainActivity extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener {
    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;
    private FrameLayout frameLayout;
    private HomeFragment home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        frameLayout = (FrameLayout) findViewById(R.id.container_body);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        drawerFragment = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);

        if (savedInstanceState == null){
            initScreen();
        }else{
            home = (HomeFragment) getSupportFragmentManager().getFragments().get(0);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_search){
            Toast.makeText(getApplicationContext(), "Search action is selected!", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }
    private void displayView(int position) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        switch (position) {
            case 0:
                fragment = new HomeFragment();
                title = getString(R.string.title_home);
                break;
            case 1:
                fragment = new FunnyFragment();
                title = getString(R.string.title_funny);
                break;
            case 2:
                fragment = new MusicFragment();
                title = getString(R.string.title_music);
                break;
            case 3:
                fragment = new TechnologyFragment();
                title = getString(R.string.title_technology);
                break;
            case 4:
                fragment = new AnimationFragment();
                title = getString(R.string.title_animation);
                break;
            case 5:
                fragment = new TutorialFragment();
                title = getString(R.string.title_tutorial);
                break;
            case 6:
                fragment = new SportFragment();
                title = getString(R.string.title_sport);
                break;
            case 7:
                fragment = new GameFragment();
                title = getString(R.string.title_game);
                break;
            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();
            // set the toolbar title
            getSupportActionBar().setTitle(title);
        }
    }
    private void initScreen() {
        home = new HomeFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.addToBackStack(null);
        transaction.add(R.id.container_body, home).commit();
    }
}