package com.example.dietamacro;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class ModificaDatiFragment extends Fragment {

    public ModificaDatiFragment() {
        // Required empty public constructor
    }

    public static ModificaDatiFragment newInstance() {

        return new ModificaDatiFragment();
    }

    private EditText editText;
    private String newnome;
    private int newcarbo, newproteine, newgrassi;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_modifica_dati, container, false);

        Bundle args = getArguments();

        String nomeTitolo = args.getString("titolo");
        editText = view.findViewById(R.id.testoDettaglio);
        editText.setHint(nomeTitolo);

        String nomeCarboidrati = args.getString("carbo");
        editText = view.findViewById(R.id.testoDettaglioCarboidrati);
        editText.setHint(nomeCarboidrati);

        String nomeProteine = args.getString("proteine");
        editText = view.findViewById(R.id.testoDettaglioProteine);
        editText.setHint(nomeProteine);

        String nomeGrassi = args.getString("grassi");
        editText = view.findViewById(R.id.testoDettaglioGrassi);
        editText.setHint(nomeGrassi);

        String id = args.getString("id");

        int calorie = (Integer.parseInt(nomeCarboidrati)*4) + (Integer.parseInt(nomeProteine)*4) + (Integer.parseInt(nomeGrassi)*7);

        saveData(view, id, nomeTitolo, Integer.parseInt(nomeCarboidrati), Integer.parseInt(nomeGrassi), Integer.parseInt(nomeProteine), calorie);

        return view;
    }

    public void saveData (View view, String id, String titolo, int carbo, int grassi, int proteine, int calorie) {
        Button button = view.findViewById(R.id.saveBottone);

        button.setOnClickListener(view1 -> {
            FirebaseFirestore db = FirebaseFirestore.getInstance();

            EditText nuovoTitolo = view.findViewById(R.id.testoDettaglio);
            EditText nuovoProteine = view.findViewById(R.id.testoDettaglioProteine);
            EditText nuovoGrassi = view.findViewById(R.id.testoDettaglioGrassi);
            EditText nuovoCarboidrati = view.findViewById(R.id.testoDettaglioCarboidrati);

            String nuovoTitolo1 = nuovoTitolo.getText().toString();
            String nuovoProteine1 = nuovoProteine.getText().toString();
            String nuovoGrassi1 = nuovoGrassi.getText().toString();
            String nuovoCarboidrati1 = nuovoCarboidrati.getText().toString();

            int a = carbo, b = grassi, c = proteine;
            if (nuovoCarboidrati1 != null) {
                try {
                    a = Integer.parseInt(nuovoCarboidrati1);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
            if (nuovoGrassi1 != null) {
                try {
                    b = Integer.parseInt(nuovoGrassi1);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
            if (nuovoProteine1 != null) {
                try {
                    c = Integer.parseInt(nuovoProteine1);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }

            int calorie1 = (a*4) + (b*7) + (c*4);

            Map<String, Object> updates = new HashMap<>();
            if (nuovoTitolo1.equals("")){
                updates.put("nome", titolo);
            } else {
                updates.put("nome", nuovoTitolo1);
            }
            if (nuovoCarboidrati1 == null){
                updates.put("carboidrati", carbo);
            } else {
                updates.put("carboidrati", a);
            }
            if (nuovoProteine1 == null){
                updates.put("proteine", proteine);
            } else {
                updates.put("proteine", c);
            }
            if (nuovoGrassi1 == null){
                updates.put("grassi", grassi);
            } else {
                updates.put("grassi", b);
            }
            updates.put("calorie", calorie1);

            db.collection("listaCibi").document(id)
                    .update(updates)
                    .addOnSuccessListener(aVoid -> {
                        Toast toast = Toast.makeText(requireContext(), "Succesfully modify", Toast.LENGTH_SHORT);
                        toast.show();
                        requireActivity().getSupportFragmentManager().popBackStack();
                    })
                    .addOnFailureListener(e -> {
                        Log.w("Firestore", "Error updating document", e);
                    });

        });

    }
}