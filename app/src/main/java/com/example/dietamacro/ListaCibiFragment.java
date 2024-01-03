package com.example.dietamacro;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;


public class ListaCibiFragment extends Fragment {

    public ListaCibiFragment() {
        // Required empty public constructor
    }

    public static ListaCibiFragment newInstance() {
        return new ListaCibiFragment();
    }

    String[] dati = {"item1", "item2", "item3", "item4", "item5", "item6", "item7", "item8", "item9", "item10", "item11", "item12", "item13", "item14", "item15", "item16", "item17", "item18", "item19", "item20", "item21", "item22"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_lista_cibi, container, false);

        ListView lista = view.findViewById(R.id.listaCibo);

        ArrayAdapter<String> adapater = new ArrayAdapter<> (
                requireContext(), android.R.layout.simple_list_item_1, dati);

        lista.setAdapter(adapater);

        lista.setOnItemClickListener((parent, view1, i, id) -> {
            DettaglioCibo dettaglioFragment = new DettaglioCibo();
            Bundle args = new Bundle();
            args.putString("nome", dati[i]);
            dettaglioFragment.setArguments(args);

            requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, dettaglioFragment)
                .addToBackStack(null)
                .commit();
        });

        return view;
    }
}