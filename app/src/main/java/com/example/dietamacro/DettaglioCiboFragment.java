package com.example.dietamacro;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class DettaglioCiboFragment extends Fragment {

    public DettaglioCiboFragment() {
        // Required empty public constructor
    }

    public static DettaglioCiboFragment newInstance() {

        return new DettaglioCiboFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dettaglio_cibo, container, false);


        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) requireActivity()).setSupportActionBar(toolbar);

        ((AppCompatActivity) requireActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(v -> {
            //tasto per tornare indietro
            getActivity().getSupportFragmentManager().popBackStack();
        });



        return view;
    }
}