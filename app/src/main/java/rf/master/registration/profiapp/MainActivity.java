package rf.master.registration.profiapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private RecyclerView mMainList;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_search:
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

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}