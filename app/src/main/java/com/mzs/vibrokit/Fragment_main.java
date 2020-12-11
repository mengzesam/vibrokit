package com.mzs.vibrokit;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import androidx.navigation.Navigation;
import static com.mzs.vibrokit.R.*;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_main#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_main extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_main() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_main.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_main newInstance(String param1, String param2) {
        Fragment_main fragment = new Fragment_main();
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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((ImageButton) view.findViewById(R.id.button_trial_weight)).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Navigation.findNavController(view).navigate(R.id.action_fragment_main_to_trial_weight);
                        getActivity().setTitle(string.trial_weight);
                    }
                });
        ((ImageButton) view.findViewById(R.id.button_onedisk)).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Navigation.findNavController(view).navigate(R.id.action_fragment_main_to_onedisk);
                        getActivity().setTitle(string.onedisk);
                    }
                });
        ((ImageButton) view.findViewById(R.id.button_twodisk)).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Navigation.findNavController(view).navigate(R.id.action_fragment_main_to_twodisk);
                        getActivity().setTitle(string.twodisk);
                    }
                });
        ((ImageButton) view.findViewById(R.id.button_harmonic)).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Navigation.findNavController(view).navigate(R.id.action_fragment_main_to_harmonic);
                        getActivity().setTitle(string.harmonic);
                    }
                });
        ((ImageButton) view.findViewById(R.id.button_decomposition)).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Navigation.findNavController(view).navigate(R.id.action_fragment_main_to_decomposition);
                        getActivity().setTitle(string.decomposition);
                    }
                });
        ((ImageButton) view.findViewById(R.id.button_vector_operation)).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Navigation.findNavController(view).navigate(R.id.action_fragment_main_to_vector_operation);
                        getActivity().setTitle(string.vector_operation);
                    }
                });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(string.app_name);
    }
}