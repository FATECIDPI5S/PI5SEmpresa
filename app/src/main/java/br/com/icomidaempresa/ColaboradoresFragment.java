package br.com.icomidaempresa;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class ColaboradoresFragment extends Fragment {

    public ColaboradoresFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_colaboradores, container, false);

        FloatingActionButton fabColaboradores = (FloatingActionButton) view.findViewById(R.id.fabColaboradores);
        fabColaboradores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ColaboradoresActivity.class));
            }
        });

        return view;
    }
}
