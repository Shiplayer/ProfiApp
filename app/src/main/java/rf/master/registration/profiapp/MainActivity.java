package rf.master.registration.profiapp;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import rf.master.registration.profiapp.store.StoreViewModel;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getCanonicalName();
    private StoreViewModel viewModel;
    private RecyclerView mMainList;
    private StoreAdapter mStoreAdapter;
    private View mIncludeChooser;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
                switch (item.getItemId()) {
                    case R.id.navigation_search:
                        showChooser();
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

        mMainList = findViewById(R.id.rv_cards);
        mIncludeChooser = findViewById(R.id.include_chooser);
        mMainList.setLayoutManager(new LinearLayoutManager(this));
        viewModel = ViewModelProviders.of(this).get(StoreViewModel.class);
        mStoreAdapter = new StoreAdapter();
        mMainList.setAdapter(mStoreAdapter);
        mMainList.setHasFixedSize(true);
        mIncludeChooser.setVisibility(View.VISIBLE);
        View.OnClickListener listener = new HandleChooseButtons();
        findViewById(R.id.choose_man).setOnClickListener(listener);
        findViewById(R.id.choose_woman).setOnClickListener(listener);
        findViewById(R.id.choose_child).setOnClickListener(listener);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    public void hiddenChooser(){
        mIncludeChooser.setVisibility(View.INVISIBLE);
    }
    public void showChooser() {
        mIncludeChooser.setVisibility(View.VISIBLE);
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
    protected void onResume() {
        super.onResume();
        viewModel.getStoreLiveData(getApplicationContext()).observe(this, mStoreAdapter::changeStories);
    }
}
