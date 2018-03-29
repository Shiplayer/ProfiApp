package rf.master.registration.profiapp.store;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.content.res.Resources;

import java.util.List;

import rf.master.registration.profiapp.data.entity.Store;
import rf.master.registration.profiapp.util.StoreUtil;

/**
 * Created by Shiplayer on 29.03.18.
 */

public class StoreLiveData extends LiveData<List<Store>>{
    private boolean mIsFetchingAll = false;
    private Resources res;

    public StoreLiveData(Context context) {
        res = context.getResources();
    }

    @Override
    protected void onActive() {
        super.onActive();
        getAllStories();
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        res = null;
    }

    public void getAllStories() {
        if(!mIsFetchingAll){
            mIsFetchingAll = true;
            List<Store> list = StoreUtil.getRandomListStories(res, 20);
            postValue(list);
        }
    }
}
