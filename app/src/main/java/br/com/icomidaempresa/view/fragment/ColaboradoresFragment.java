package br.com.icomidaempresa.view.fragment;


import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import br.com.icomidaempresa.model.Colaborador;
import br.com.icomidaempresa.view.activity.ColaboradoresActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.icomidaempresa.R;
import br.com.icomidaempresa.view.adapter.ColaboradorAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ColaboradoresFragment extends Fragment {
    View view;
    FloatingActionButton fabColaboradores;

    public ColaboradoresFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_colaborador_list, container, false);

        RecyclerView rvColaborador = view.findViewById(R.id.rvColaborador);

        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        rvColaborador.setLayoutManager(layoutManager);

        List<Colaborador> colaboradores = new ArrayList<>();
        for (int i = 0; i < 11; i++)
        {
            Colaborador colaborador = new Colaborador();
            colaborador.setNome("Colaborador " + String.valueOf(i));

            String rg = "";
            for (int j = 1; j <= 10; j++){
                rg += String.valueOf(j);
            }
            colaborador.setRG(rg);
            String cpf = "";
            for (int k = 10; k >= 0; k--){
                cpf += String.valueOf(k);
            }
            colaborador.setCPF(cpf);
            colaborador.setEndereco("Praça Barão do Rio Branco, 552, Campina, Belém-PA, 66015-060");
            colaborador.setTelefone("(91) 3923-8214");
            colaborador.setCelular("(91) 98440-3850");
            colaborador.setEmail("franciscomateusthomasfigueiredo-74@supergasbras.com.br");
            colaborador.setTipo("Garçom");
            colaboradores.add(colaborador);
        }
        rvColaborador.setAdapter(new ColaboradorAdapter(colaboradores));
        rvColaborador.addItemDecoration(new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL));

        fabColaboradores = view.findViewById(R.id.fabColaboradores);
        fabColaboradores.setOnClickListener(this::abrirColaboradoresActivity);

        return view;
    }

    private void abrirColaboradoresActivity(View view) {
        startActivity(new Intent(getContext(), ColaboradoresActivity.class));
    }
}