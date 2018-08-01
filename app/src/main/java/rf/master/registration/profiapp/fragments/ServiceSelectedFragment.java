package rf.master.registration.profiapp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import rf.master.registration.profiapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ServiceSelectedFragment extends Fragment {


    public ServiceSelectedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_service_selected, container, false);
    }

}
