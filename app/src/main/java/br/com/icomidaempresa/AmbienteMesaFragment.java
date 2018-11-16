package br.com.icomidaempresa;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class AmbienteMesaFragment extends Fragment {

    public AmbienteMesaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ambiente_mesa, container, false);

        FloatingActionButton fabAmbienteMesa = (FloatingActionButton) view.findViewById(R.id.fabAmbienteMesa);
        fabAmbienteMesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getContext(), AmbienteMesaActivity.class));
            }
        });

        return view;
    }
}
