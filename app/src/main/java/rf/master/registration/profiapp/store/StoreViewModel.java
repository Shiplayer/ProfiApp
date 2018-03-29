package rf.master.registration.profiapp.store;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import java.util.List;

import rf.master.registration.profiapp.data.entity.Store;

/**
 * Created by Shiplayer on 29.03.18.
 */

public class StoreViewModel extends ViewModel {
    private StoreLiveData storeLiveData;
    public LiveData<List<Store>> getStoreLiveData(Context context){
        if(storeLiveData == null)
            storeLiveData = new StoreLiveData(context);
        return storeLiveData;
    }
}
