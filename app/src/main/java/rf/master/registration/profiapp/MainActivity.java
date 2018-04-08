package rf.master.registration.profiapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import rf.master.registration.profiapp.adapters.ItemChooseAdapter;
import rf.master.registration.profiapp.fragments.AccountFragment;
import rf.master.registration.profiapp.fragments.FavoriteFragment;
import rf.master.registration.profiapp.fragments.OrderFragment;
import rf.master.registration.profiapp.fragments.SearchFragment;
import rf.master.registration.profiapp.util.ItemCatalogUtil;

public class MainActivity extends AppCompatActivity implements FavoriteFragment.OnFragmentInteractionListener{

    private static final String TAG = MainActivity.class.getCanonicalName();
    private static final int RESULT_CODE_FROM_LOGIN = 1001;
    public static final String INTENT_FILTER_DATA_TYPE = "rf.master.registration.profiapp/killer";

    private BottomNavigationView mNavigation;
    private View mIncludeChooser;
    private FragmentManager mFragmentManager;
    private LinkedList<Fragment> mHistoryOfFragments;
    private MessagesReceiver mMessagesReceiver;
    private RecyclerView mCategoryItems;
    private ItemChooseAdapter mItemAdapter;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
                switch (item.getItemId()) {
                    case R.id.navigation_search:
                        showChooser();
                        navigateToFragment(new SearchFragment());
                        Log.w(TAG, getResources().getString(R.string.menu_search_name));
                        return true;
                    case R.id.navigation_favorites:
                        navigateToFragment(new FavoriteFragment());
                        Log.w(TAG, getResources().getString(R.string.menu_favorites_name));
                        return true;
                    case R.id.navigation_orders:
                        navigateToFragment(new OrderFragment());
                        Log.w(TAG, getResources().getString(R.string.menu_orders_name));
                        return true;
                    case R.id.navigation_account:
                        navigateToFragment(new AccountFragment());
                        Log.w(TAG, getResources().getString(R.string.menu_account_name));
                        return true;
                }
                return false;
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mHistoryOfFragments = new LinkedList<>();
        mMessagesReceiver = new MessagesReceiver();
        registerReceiver(mMessagesReceiver, IntentFilter.create("kill", INTENT_FILTER_DATA_TYPE));

        startActivityForResult(new Intent(this, LoginActivity.class), RESULT_CODE_FROM_LOGIN);

        setContentView(R.layout.activity_main);
        View.OnClickListener listener = new HandleChooseButtons();

        findViewById(R.id.choose_man).setOnClickListener(listener);
        findViewById(R.id.choose_woman).setOnClickListener(listener);
        findViewById(R.id.choose_child).setOnClickListener(listener);

        mNavigation = findViewById(R.id.navigation);
        mIncludeChooser = findViewById(R.id.include_chooser);
        mFragmentManager = getSupportFragmentManager();
        mCategoryItems = findViewById(R.id.rv_list_catalog);

        mItemAdapter = new ItemChooseAdapter();

        mNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mIncludeChooser.setVisibility(View.VISIBLE);


        if(mFragmentManager.getFragments().size() == 0){
            mOnNavigationItemSelectedListener.onNavigationItemSelected(mNavigation.getMenu().getItem(0));
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.w(TAG, requestCode + " " + resultCode + " ");
        if(data != null)
            Log.w(TAG, String.valueOf(data.getBooleanExtra(LoginActivity.IS_LOGIN_EXTRA, false)));
        else
            Log.w(TAG, "return is null");
    }

    public void hiddenChooser(){
        mIncludeChooser.setVisibility(View.INVISIBLE);
        mNavigation.setVisibility(View.VISIBLE);
    }

    public void showChooser() {
        mIncludeChooser.setVisibility(View.VISIBLE);
        mNavigation.setVisibility(View.INVISIBLE);
    }

    public void chooserItemSelected(){
        mIncludeChooser.setVisibility(View.INVISIBLE);
        mCategoryItems.setVisibility(View.VISIBLE);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        Log.w(TAG, uri.toString());
    }

    private class HandleChooseButtons implements View.OnClickListener{
        final String TAG = HandleChooseButtons.class.getSimpleName();

        @Override
        public void onClick(View v) {
            String choose;
            switch (v.getId()){
                case R.id.choose_man:
                    choose = "Мужчина";
                    break;
                case R.id.choose_woman:
                    choose = "Женщина";
                    break;
                case R.id.choose_child:
                    choose = "Дети";
                    break;
                default:
                    return;
            }
            mItemAdapter.setData(ItemCatalogUtil.getItemCatalogNames(choose));
        }
    }

    private void navigateToFragment(Fragment fragment){
        mFragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment).addToBackStack(null).commit();
        Log.w(TAG, "size list of Fragments: " + mFragmentManager.getFragments().size());
        List<Fragment> fragments = mFragmentManager.getFragments();
        for(Fragment fr : fragments){
            Log.w(TAG, String.valueOf(fr.getActivity().getTitle()));
        }
    }

    @Override
    public void onBackPressed() {
        /*if(!mHistoryOfFragments.isEmpty()) {
            for(Fragment fragment : mHistoryOfFragments)
                Log.w(TAG, "onBackPressed: " + fragment.getActivity().getTitle());
            mFragmentManager.beginTransaction().detach(mHistoryOfFragments.getLast());
            mHistoryOfFragments.pop();
            mFragmentManager.beginTransaction().replace(R.id.fragmentContainer, mHistoryOfFragments.getLast()).commit();
        } else*/
            super.onBackPressed();
    }

    protected void onResume() {
        super.onResume();
    }

    public final class MessagesReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mMessagesReceiver);
    }
}
