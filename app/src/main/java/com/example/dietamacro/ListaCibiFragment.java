package com.example.dietamacro;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;


public class ListaCibiFragment extends Fragment {

    public ListaCibiFragment() {
        // Required empty public constructor
    }

    public static ListaCibiFragment newInstance() {
        return new ListaCibiFragment();
    }

    private ArrayList<Cibo> adapterNome = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_cibi, container, false);

        if(adapterNome.isEmpty()) {
            loadData(view);
        } else {
            setupAdapter(view);
        }

        aggiungiAlimento(view);

        return view;
    }

    public void loadData(View view) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("listaCibi")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot foodList : task.getResult()) {
                            Cibo cibo = foodList.toObject(Cibo.class);
                            cibo.setId(foodList.getId());
                            adapterNome.add(cibo);
                        }
                        setupAdapter(view);
                    } else {
                        Log.w("mioFirestore", "Error getting documents.", task.getException());
                    }
                });
    }

    private void setupAdapter(View view) {
        ListView lista = view.findViewById(R.id.listaCibo);

        ArrayAdapter<Cibo> adapater = new ArrayAdapter<> (
                requireContext(), android.R.layout.simple_list_item_1, adapterNome
        );

        lista.setAdapter(adapater);

        lista.setOnItemClickListener((parent, view1, i, id) -> {
            DettaglioCiboFragment dettaglioFragment = new DettaglioCiboFragment();
            Bundle args = new Bundle();

            args.putString("titoloPrincipale", adapterNome.get(i).getNome());
            args.putString("carboidrati", String.valueOf(adapterNome.get(i).getCarboidrati()));
            args.putString("proteine", String.valueOf(adapterNome.get(i).getProteine()));
            args.putString("grassi", String.valueOf(adapterNome.get(i).getGrassi()));
            args.putString("id", String.valueOf(adapterNome.get(i).getId()));
            dettaglioFragment.setArguments(args);

            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_layout, dettaglioFragment)
                    .addToBackStack(null)
                    .commit();
        });
    }

    public void aggiungiAlimento (View view) {
        Button button = view.findViewById(R.id.aggiungiBottone);
        button.setOnClickListener(view1 -> {

            AggiungiDatiFragment aggiungiDatiFragment = new AggiungiDatiFragment();
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_layout, aggiungiDatiFragment)
                    .addToBackStack(null)
                    .commit();
        });
    }
}