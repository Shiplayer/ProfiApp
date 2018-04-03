package rf.master.registration.profiapp;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import rf.master.registration.profiapp.store.StoreViewModel;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getCanonicalName();
    private BottomNavigationView mNavigation;
    private View mIncludeChooser;
    private FragmentManager mFragmentManager;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
                switch (item.getItemId()) {
                    case R.id.navigation_search:
                        showChooser();
                        navigateToFragment(new SearchFragment());
                        Log.w(TAG, getResources().getString(R.string.menu_search_name));
                        return true;
                    case R.id.navigation_favorites:
                        Log.w(TAG, getResources().getString(R.string.menu_favorites_name));
                        return true;
                    case R.id.navigation_orders:
                        Log.w(TAG, getResources().getString(R.string.menu_orders_name));
                        return true;
                    case R.id.navigation_account:
                        Log.w(TAG, getResources().getString(R.string.menu_account_name));
                        return true;
                }
                return false;
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View.OnClickListener listener = new HandleChooseButtons();

        findViewById(R.id.choose_man).setOnClickListener(listener);
        findViewById(R.id.choose_woman).setOnClickListener(listener);
        findViewById(R.id.choose_child).setOnClickListener(listener);

        mNavigation = findViewById(R.id.navigation);
        mIncludeChooser = findViewById(R.id.include_chooser);
        mFragmentManager = getSupportFragmentManager();

        mNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mIncludeChooser.setVisibility(View.VISIBLE);


        if(mFragmentManager.getFragments().size() == 0){
            mOnNavigationItemSelectedListener.onNavigationItemSelected(mNavigation.getMenu().getItem(0));
        }

    }

    public void hiddenChooser(){
        mIncludeChooser.setVisibility(View.INVISIBLE);
        mNavigation.setVisibility(View.VISIBLE);
    }
    public void showChooser() {
        mIncludeChooser.setVisibility(View.VISIBLE);
        mNavigation.setVisibility(View.INVISIBLE);
    }

    private class HandleChooseButtons implements View.OnClickListener{
        final String TAG = HandleChooseButtons.class.getSimpleName();

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.choose_man:
                    Log.w(TAG, "chose man");
                    break;
                case R.id.choose_woman:
                    Log.w(TAG, "chose woman");
                    break;
                case R.id.choose_child:
                    Log.w(TAG, "chose child");
                    break;
                default:
                    break;
            }
            hiddenChooser();
        }
    }

    private void navigateToFragment(Fragment fragment){
        mFragmentManager.beginTransaction().add(R.id.fragmentContainer, fragment).commit();
        Log.w(TAG, "size list of Fragments: " + mFragmentManager.getFragments().size());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    protected void onResume() {
        super.onResume();
    }
}
