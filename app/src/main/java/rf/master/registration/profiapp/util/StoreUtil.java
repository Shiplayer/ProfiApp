package rf.master.registration.profiapp.util;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import rf.master.registration.profiapp.data.entity.Store;

/**
 * Created by Shiplayer on 29.03.18.
 */

public class StoreUtil {
    private static final String TAG = StoreUtil.class.getSimpleName();
    private static String[] RANDOM_FIRST_NAME = {"Kaitlin", "Garnett", "Moe", "Tiger", "Fletcher"};
    private static String[] RANDOM_LAST_NAME = {"Marcia", "Cecil", "Phyliss", "Cass", "Michaela"};
    private static String[] RANDOM_FAKE_NAME_STORIES = {"The Devil is a Girl", "The Chosen Ones sister", "List of Random Names"};

    public static List<Store> getRandomListStories(Resources resources, int count){
        List<Store> list = new ArrayList<>();
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < count; i++){
            list.add(new Store(resources, RANDOM_FAKE_NAME_STORIES[random.nextInt(RANDOM_FAKE_NAME_STORIES.length)],
                    RANDOM_FIRST_NAME[random.nextInt(RANDOM_FIRST_NAME.length)], RANDOM_LAST_NAME[random.nextInt(RANDOM_LAST_NAME.length)],
                    random.nextFloat() * 10 % 5, random.nextFloat() * 10 % 5));
            stringBuilder.append(list.get(i)).append("\n");
        }

        Log.w(TAG, "stories = " + stringBuilder.toString());
        return list;
    }
}
