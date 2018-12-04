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

        Colaborador colaborador1 = new Colaborador();
        colaborador1.setNome("Francisco Mateus Thomas Figueiredo");
        colaborador1.setRG("28.164.029-4");
        colaborador1.setCPF("716.242.496-12");
        colaborador1.setEndereco("Praça Barão do Rio Branco, 552, Campina, Belém-PA, 66015-060");
        colaborador1.setTelefone("(91) 3923-8214");
        colaborador1.setCelular("(91) 98440-3850");
        colaborador1.setEmail("franciscomateusthomasfigueiredo-74@supergasbras.com.br");
        colaborador1.setTipo("Garçom");

        Colaborador colaborador2 = new Colaborador();
        colaborador2.setNome("Filipe Joaquim Almeida");
        colaborador2.setRG("15.323.289-4");
        colaborador2.setCPF("307.476.008-72");
        colaborador2.setEndereco("Rua Antônio Vacilotto, 599, Jardim Oliveira Camargo, Indaiatuba-SP, 13340-712");
        colaborador2.setTelefone("(19) 2802-5895");
        colaborador2.setCelular("(19) 99486-0321");
        colaborador2.setEmail("filipejoaquimalmeida__filipejoaquimalmeida@sobraer.com.br");
        colaborador2.setTipo("Cozinha");

        List<Colaborador> colaboradores = new ArrayList<>();
        colaboradores.add(colaborador1);
        colaboradores.add(colaborador2);
        colaboradores.add(colaborador1);
        colaboradores.add(colaborador2);
        colaboradores.add(colaborador1);
        colaboradores.add(colaborador2);
        colaboradores.add(colaborador1);
        colaboradores.add(colaborador2);
        colaboradores.add(colaborador1);
        colaboradores.add(colaborador2);
        colaboradores.add(colaborador1);
        colaboradores.add(colaborador2);

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
