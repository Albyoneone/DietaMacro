package com.example.dietamacro;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AggiungiDatiFragment extends Fragment {

    public AggiungiDatiFragment() {
        // Required empty public constructor
    }

    public static AggiungiDatiFragment newInstance() {
        return new AggiungiDatiFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_aggiungi_dati, container, false);


        Button button = view.findViewById(R.id.saveBottone);
        button.setOnClickListener(view1 -> {
            addData(view);
        });

        return view;
    }

    public void addData (View view){
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        EditText nameEdit = view.findViewById(R.id.editTextNome);
        EditText CarboidratiEdit = view.findViewById(R.id.editTextCarboidrati);
        EditText ProteineEdit = view.findViewById(R.id.editTextProteine);
        EditText GrassiEdit = view.findViewById(R.id.editTextGrassi);

        String nameEdit1 = nameEdit.getText().toString();
        String CarboidratiEdit1 = CarboidratiEdit.getText().toString();
        String ProteineEdit1 = ProteineEdit.getText().toString();
        String GrassiEdit1 = GrassiEdit.getText().toString();

        int carboidratiValue = Integer.parseInt(CarboidratiEdit1);
        int proteineValue = Integer.parseInt(ProteineEdit1);
        int grassiValue = Integer.parseInt(GrassiEdit1);
        int calorie = (carboidratiValue*4) + (proteineValue*4) + (grassiValue*7);


        Map<String, Object> listaCibi = new HashMap<>();
        listaCibi.put("nome", nameEdit1);
        listaCibi.put("calorie", calorie);
        listaCibi.put("grassi", grassiValue);
        listaCibi.put("proteine", proteineValue);
        listaCibi.put("carboidrati", carboidratiValue);

        db.collection("listaCibi")
                .add(listaCibi)
                .addOnSuccessListener(documentReference -> {
                        Toast toast = Toast.makeText(requireContext(), "Succesfully added", Toast.LENGTH_SHORT);
                        toast.show();
                        requireActivity().getSupportFragmentManager().popBackStack();
                        })
                .addOnFailureListener(e ->
                        Log.w("MyApp", "Error adding document", e
                        ));
    };
}