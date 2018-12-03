package br.com.icomidaempresa.view.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import br.com.icomidaempresa.R;
import br.com.icomidaempresa.model.Empresa;
import br.com.icomidaempresa.view.activity.EmpresaActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class EmpresaFragment extends Fragment {
    GridLayout glEmpresaDescricao;
    FloatingActionButton fabEmpresa;
    ImageButton imgbMostrarEmpresa;
    ImageButton imgbAlterarEmpresa;
    TextView tvCVEmpresaTitulo;
    TextView tvCVEmpresaSubtitulo;
    TextView tvRazaoSocial;
    TextView tvNomeFantasia;
    TextView tvCNPJ;
    TextView tvIE;
    TextView tvEndereco;
    TextView tvTelefone;
    TextView tvCelular;

    public EmpresaFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_empresa, container, false);

        glEmpresaDescricao = view.findViewById(R.id.glEmpresaDescricao);
        fabEmpresa = view.findViewById(R.id.fabEmpresa);
        imgbMostrarEmpresa = view.findViewById(R.id.imgbMostrarEmpresa);
        imgbAlterarEmpresa = view.findViewById(R.id.imgbAlterarEmpresa);
        tvCVEmpresaTitulo = view.findViewById(R.id.tvCVEmpresaTitulo);
        tvCVEmpresaSubtitulo = view.findViewById(R.id.tvCVEmpresaSubtitulo);
        tvRazaoSocial = view.findViewById(R.id.tvRazaoSocial);
        tvNomeFantasia = view.findViewById(R.id.tvNomeFantasia);
        tvCNPJ = view.findViewById(R.id.tvCNPJ);
        tvIE = view.findViewById(R.id.tvIE);
        tvEndereco = view.findViewById(R.id.tvEndereco);
        tvTelefone = view.findViewById(R.id.tvTelefone);
        tvCelular = view.findViewById(R.id.tvCelular);

        fabEmpresa.setOnClickListener(this::abrirEmpresaActivity);
        imgbMostrarEmpresa.setOnClickListener(this::verificarDescricao);
        imgbAlterarEmpresa.setOnClickListener(this::alterarEmpresa);

        popularEmpresa();

        return view;
    }

    private void alterarEmpresa(View view) {
        startActivity(new Intent(getContext(), EmpresaActivity.class));
    }

    private void verificarDescricao(View view) {
        if (glEmpresaDescricao.getVisibility() == View.VISIBLE) {
            imgbMostrarEmpresa.setImageResource(R.drawable.ic_keyboard_arrow_down_black_50dp);
            glEmpresaDescricao.setVisibility(View.GONE);
        } else if (glEmpresaDescricao.getVisibility() == View.GONE) {
            imgbMostrarEmpresa.setImageResource(R.drawable.ic_keyboard_arrow_up_black_50dp);
            glEmpresaDescricao.setVisibility(View.VISIBLE);
        }
    }

    private void abrirEmpresaActivity(View view) {
        startActivity(new Intent(getContext(), EmpresaActivity.class));
    }

    private void popularEmpresa() {
        Empresa empresa = new Empresa();
        empresa.setRazaoSocial("FATEC RESTAURANT LTDA");
        empresa.setNomeFantasia("Restaurante FATEC");
        empresa.setCNPJ("00.755.140/0001-70");
        empresa.setIE("061.811.922.200");
        empresa.setEndereco("R. Dom Pedro I, 65 - Cidade Nova I, Indaiatuba - SP, 13334-100");
        empresa.setTelefone("(19) 3885-1923");
        empresa.setCelular("(19) 99170-7070");

        tvCVEmpresaTitulo.setText(empresa.getNomeFantasia());
        tvCVEmpresaSubtitulo.setText(empresa.getCNPJ());
        tvRazaoSocial.setText(empresa.getRazaoSocial());
        tvNomeFantasia.setText(empresa.getNomeFantasia());
        tvCNPJ.setText(empresa.getCNPJ());
        tvIE.setText(empresa.getIE());
        tvEndereco.setText(empresa.getEndereco());
        tvTelefone.setText(empresa.getTelefone());
        tvCelular.setText(empresa.getCelular());
    }
}
