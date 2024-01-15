package com.example.dietamacro;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


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


        Bundle args = getArguments();

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) requireActivity()).setSupportActionBar(toolbar);

        ((AppCompatActivity) requireActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(v -> {
            //tasto per tornare indietro
            getActivity().getSupportFragmentManager().popBackStack();
        });

        String nome = args.getString("titoloPrincipale");
        TextView testoPrincipale = view.findViewById(R.id.testoDettaglio);
        testoPrincipale.setText(nome);

        String nome2 = args.getString("carboidrati");
        TextView carboidrati = view.findViewById(R.id.testoDettaglioCarboidrati);
        carboidrati.setText("Carboidrati "+ nome2 + "g");

        String nome3 = args.getString("proteine");
        TextView proteine = view.findViewById(R.id.testoDettaglioProteine);
        proteine.setText("Proteine "+ nome3 + "g");

        String nome4 = args.getString("grassi");
        TextView grassi = view.findViewById(R.id.testoDettaglioGrassi);
        grassi.setText("Grassi "+ nome4 + "g");

        return view;
    }

}