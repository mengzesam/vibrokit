package com.mzs.vibrokit.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mzs.vibrokit.BR;
import com.mzs.vibrokit.R;
import com.mzs.vibrokit.model.OnediskModel;
import com.mzs.vibrokit.databinding.FragmentOnediskBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_onedisk#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_onedisk extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnediskModel mModel;
    private FragmentOnediskBinding mBinding;

    public Fragment_onedisk() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_onedisk.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_onedisk newInstance(String param1, String param2) {
        Fragment_onedisk fragment = new Fragment_onedisk();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_onedisk, container, false);
        mBinding= DataBindingUtil.inflate(inflater,R.layout.fragment_onedisk,
                container,false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mModel=new ViewModelProvider(requireActivity()).get(OnediskModel.class);
        mBinding.setLifecycleOwner(getViewLifecycleOwner());
        mBinding.setVariable(BR.viewmodel,mModel);
        mBinding.listOnedisk.setAdapter(mModel.getAdapter());
    }
}