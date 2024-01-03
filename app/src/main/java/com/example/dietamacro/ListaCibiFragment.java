package com.example.dietamacro;

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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_lista_cibi, container, false);

        ListView listView = view.findViewById(R.id.listaCibo);

        List<String> dati = Arrays.asList("Patate", "Cipolla", "Carote");

        ArrayAdapter<String> adapater = new ArrayAdapter<> (
                requireContext(), android.R.layout.simple_list_item_1, dati);

        listView.setAdapter(adapater);
        return view;
    }
}