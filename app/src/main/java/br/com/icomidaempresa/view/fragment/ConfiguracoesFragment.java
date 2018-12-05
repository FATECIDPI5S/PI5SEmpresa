package br.com.icomidaempresa.view.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import androidx.fragment.app.Fragment;
import br.com.icomidaempresa.R;
import br.com.icomidaempresa.view.activity.LoginActivity;
import br.com.icomidaempresa.view.activity.MainActivity;

public class ConfiguracoesFragment extends Fragment {
    private FirebaseAuth firebaseAuth;
    public ConfiguracoesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_configuracoes, container, false);
        Button btnSair = view.findViewById(R.id.btnSair);

        firebaseAuth = FirebaseAuth.getInstance();

        btnSair.setOnClickListener(v -> {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user != null) {
                firebaseAuth.signOut();
                this.getActivity().finish();
                startActivity( new Intent(getContext(), LoginActivity.class));
            }
        });
        return view;
    }
}
