package br.com.icomidaempresa.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import br.com.icomidaempresa.R;
import br.com.icomidaempresa.model.Empresa;
import br.com.icomidaempresa.view.activity.EmpresaActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class EmpresaFragment extends Fragment {
    private FirebaseDatabase mFirebaseDatabase;
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
    String empresaKeyVinculada;

    public EmpresaFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_empresa, container, false);

        mFirebaseDatabase = FirebaseDatabase.getInstance();

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

        verificarEmpresaVinculada();
        AdicionarEmpresaListener();

        return view;
    }

    private void alterarEmpresa(View view) {
        Intent intent = new Intent(getContext(), EmpresaActivity.class);
        intent.putExtra("update", true);
        intent.putExtra("empresaKey", empresaKeyVinculada);
        intent.putExtra("tvRazaoSocial", tvRazaoSocial.getText());
        intent.putExtra("tvNomeFantasia", tvNomeFantasia.getText());
        intent.putExtra("tvCNPJ", tvCNPJ.getText());
        intent.putExtra("tvIE", tvIE.getText());
        intent.putExtra("tvEndereco", tvEndereco.getText());
        intent.putExtra("tvTelefone", tvTelefone.getText());
        intent.putExtra("tvCelular", tvCelular.getText());
        startActivity(intent);
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

    private void verificarEmpresaVinculada() {
        DatabaseReference mAdminEmpresaDatabaseReference = mFirebaseDatabase.getReference().child("admin_empresa");
        mAdminEmpresaDatabaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if (dataSnapshot.getKey().equals(pegarAdminKey())) {
                    empresaKeyVinculada = dataSnapshot.getValue().toString();
                    fabEmpresa.hide();
                } else {
                    fabEmpresa.show();
                }
            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if (dataSnapshot.getKey().equals(pegarAdminKey())) {
                    empresaKeyVinculada = dataSnapshot.getValue().toString();
                    fabEmpresa.hide();
                } else {
                    fabEmpresa.show();
                }
            }
            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {}
            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {}
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });
    }

    private String pegarAdminKey() {
        SharedPreferences preferences = this.getActivity().getSharedPreferences("admin_preferences", Context.MODE_PRIVATE);
        return preferences.getString("adminKey", "");
    }

    private void AdicionarEmpresaListener() {
        DatabaseReference mEmpresaDatabaseReference = mFirebaseDatabase.getReference().child("empresa");
        mEmpresaDatabaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if (dataSnapshot.getKey().equals(empresaKeyVinculada)) {
                    Empresa empresa = dataSnapshot.getValue(Empresa.class);
                    atualizarCardViewEmpresa(empresa);
                }
            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if (dataSnapshot.getKey().equals(empresaKeyVinculada)) {
                    Empresa empresa = dataSnapshot.getValue(Empresa.class);
                    atualizarCardViewEmpresa(empresa);
                }
            }
            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {}
            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {}
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });
    }

    private void atualizarCardViewEmpresa(Empresa empresa) {
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
