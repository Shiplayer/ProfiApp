package rf.master.registration.profiapp;

import android.arch.lifecycle.ViewModelProvider;
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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import rf.master.registration.profiapp.data.entity.Store;
import rf.master.registration.profiapp.store.StoreLiveData;
import rf.master.registration.profiapp.store.StoreViewModel;
import rf.master.registration.profiapp.util.StoreUtil;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getCanonicalName();
    private StoreViewModel viewModel;
    private TextView mTextMessage;
    private RecyclerView mMainList;
    private StoreAdapter mStoreAdapter;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_search:
                    Snackbar.make(item.getActionView(), R.string.title_home, 100).show();
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_favorites:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_orders:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
                case R.id.navigation_account:
                    mTextMessage.setText(R.string.menu_account_name);
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = findViewById(R.id.message);
        mMainList = findViewById(R.id.rv_cards);
        mMainList.setLayoutManager(new LinearLayoutManager(this));
        viewModel = ViewModelProviders.of(this).get(StoreViewModel.class);
        mStoreAdapter = new StoreAdapter();
        mMainList.setAdapter(mStoreAdapter);
        mMainList.setHasFixedSize(true);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.getStoreLiveData(getApplicationContext()).observe(this, mStoreAdapter::changeStories);
    }
}
