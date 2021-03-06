package br.com.icomidaempresa.view.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.fragment.app.Fragment;
import br.com.icomidaempresa.R;
import br.com.icomidaempresa.view.activity.AmbienteActivity;
import br.com.icomidaempresa.view.activity.MesaActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class AmbienteMesaFragment extends Fragment {

    FloatingActionButton fabAmbienteMesa;
    Button btnAmbiente;
    Button btnMesa;

    public AmbienteMesaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ambiente_mesa, container, false);

        fabAmbienteMesa  = view.findViewById(R.id.fabAmbienteMesa);
        btnAmbiente = view.findViewById(R.id.btnAmbiente);
        btnMesa = view.findViewById(R.id.btnMesa);

        btnAmbiente.setVisibility(View.GONE);
        btnMesa.setVisibility(View.GONE);

        fabAmbienteMesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnAmbiente.getVisibility() == View.VISIBLE){
                    esconderBotoes();
                }else if(btnAmbiente.getVisibility() == View.GONE){
                    mostrarBotoes();
                }
            }
        });

        btnAmbiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                esconderBotoes();
                startActivity(new Intent(getContext(), AmbienteActivity.class));
            }
        });

        btnMesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                esconderBotoes();
                startActivity(new Intent(getContext(), MesaActivity.class));
            }
        });

        return view;
    }

    public void esconderBotoes(){
        fabAmbienteMesa.setImageResource(R.drawable.ic_add_white_24dp);
        btnAmbiente.setVisibility(View.GONE);
        btnMesa.setVisibility(View.GONE);
    }

    public void mostrarBotoes(){
        fabAmbienteMesa.setImageResource(R.drawable.ic_clear_white_24dp);
        btnAmbiente.setVisibility(View.VISIBLE);
        btnMesa.setVisibility(View.VISIBLE);
    }
}
