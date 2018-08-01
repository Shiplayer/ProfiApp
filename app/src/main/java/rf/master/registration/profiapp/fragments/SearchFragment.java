package rf.master.registration.profiapp.fragments;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import rf.master.registration.profiapp.R;
import rf.master.registration.profiapp.ServiceActivity;
import rf.master.registration.profiapp.adapters.StoreAdapter;
import rf.master.registration.profiapp.data.entity.Store;
import rf.master.registration.profiapp.store.StoreViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {
    private final static String TAG = SearchFragment.class.getSimpleName();
    private final static String KEY_SCROLL_Y = "ScrollY";


    private StoreViewModel viewModel;
    private RecyclerView mMainList;
    private StoreAdapter mStoreAdapter;
    private Context mContext;


    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getActivity().setTitle("Search fragment");
        mMainList = getView().findViewById(R.id.rv_cards);
        mMainList.setLayoutManager(new LinearLayoutManager(mContext));
        viewModel = ViewModelProviders.of(getActivity()).get(StoreViewModel.class);
        mStoreAdapter = new StoreAdapter((Store store) -> {
            Intent intent = new Intent(getContext(), ServiceActivity.class);
            Bundle bundle = new Bundle();
            bundle.putParcelable("Store", store);
            SearchFragment.this.startActivity(intent);
        });
        mMainList.setAdapter(mStoreAdapter);
        mMainList.setHasFixedSize(true);



        if(savedInstanceState != null){
            mMainList.setScrollY(savedInstanceState.getInt(KEY_SCROLL_Y));
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        //Log.w(TAG, mMainList + " " + mMainList.getScrollY());
        outState.putInt(KEY_SCROLL_Y, mMainList.getScrollY());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.getStoreLiveData(getContext()).observe(SearchFragment.this, mStoreAdapter::changeStories);
    }

    public interface HandleOnClickListener {
        void startActivity(Store store);
    }
}
