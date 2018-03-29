package rf.master.registration.profiapp;

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

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getCanonicalName();
    private TextView mTextMessage;
    private RecyclerView mMainList;
    private static String[] RANDOM_FIRST_NAME = {"Kaitlin", "Garnett", "Moe", "Tiger", "Fletcher"};
    private static String[] RANDOM_LAST_NAME = {"Marcia", "Cecil", "Phyliss", "Cass", "Michaela"};
    private static String[] RANDOM_FAKE_NAME_STORIES = {"The Devil is a Girl", "The Chosen Ones sister", "List of Random Names"};

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

        mTextMessage = (TextView) findViewById(R.id.message);
        mMainList = findViewById(R.id.rv_cards);
        mMainList.setLayoutManager(new LinearLayoutManager(this));
        mMainList.setAdapter(new StoreAdapter(getRandomListStories(20)));
        mMainList.setHasFixedSize(true);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private List<Store> getRandomListStories(int count){
        List<Store> list = new ArrayList<>();
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < count; i++){
            list.add(new Store(getResources(), RANDOM_FAKE_NAME_STORIES[random.nextInt(RANDOM_FAKE_NAME_STORIES.length)],
                    RANDOM_FIRST_NAME[random.nextInt(RANDOM_FIRST_NAME.length)], RANDOM_LAST_NAME[random.nextInt(RANDOM_LAST_NAME.length)],
                    random.nextFloat() * 10 % 5, random.nextFloat() * 10 % 5));
            stringBuilder.append(list.get(i)).append("\n");
        }

        Log.w(TAG, "stories = " + stringBuilder.toString());
        return list;
    }

}
