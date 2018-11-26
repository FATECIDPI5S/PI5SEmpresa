package br.com.icomidaempresa.view.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.fragment.app.Fragment;
import br.com.icomidaempresa.R;
import br.com.icomidaempresa.view.activity.EmpresaActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class EmpresaFragment extends Fragment {

    GridLayout glEmpresaDescricao;
    ImageButton imgbMostrarDescricao;

    public EmpresaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_empresa, container, false);

        FloatingActionButton fabEmpresa =  view.findViewById(R.id.fabEmpresa);
        glEmpresaDescricao =  view.findViewById(R.id.glEmpresaDescricao);
        imgbMostrarDescricao =  view.findViewById(R.id.imgbMostrarDescricao);

        glEmpresaDescricao.setVisibility(View.GONE);

        imgbMostrarDescricao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(glEmpresaDescricao.getVisibility() == View.VISIBLE){
                    esconderDescricao();
                }else if(glEmpresaDescricao.getVisibility() == View.GONE){
                    mostrarDescricao();
                }
            }
        });

        fabEmpresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), EmpresaActivity.class));
            }
        });

        return view;
    }

    public void esconderDescricao(){
        imgbMostrarDescricao.setImageResource(R.drawable.ic_keyboard_arrow_down_black_50dp);
        glEmpresaDescricao.setVisibility(View.GONE);
    }

    public void mostrarDescricao(){
        imgbMostrarDescricao.setImageResource(R.drawable.ic_keyboard_arrow_up_black_50dp);
        glEmpresaDescricao.setVisibility(View.VISIBLE);
    }
}
