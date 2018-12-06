package br.com.icomidaempresa.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;
import br.com.icomidaempresa.R;
import br.com.icomidaempresa.model.Administrador;
import br.com.icomidaempresa.model.Colaborador;

public class ColaboradoresActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    FirebaseDatabase iComidaDb = FirebaseDatabase.getInstance();
    FirebaseUser user;
    DatabaseReference tbColaboradores = iComidaDb.getReference("funcionario");
    DatabaseReference tbColaboradoresAdmin = iComidaDb.getReference("admin_funcionario");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colaboradores);
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
    }

    public void CadastrarColaboradores(View v){
        try {
            EditText edtNome = findViewById(R.id.edtNome);
            EditText edtRg = findViewById(R.id.edtRG);
            EditText edtCpf = findViewById(R.id.edtCPF);
            EditText edtEndereco = findViewById(R.id.edtEndereco);
            EditText edtTelefone = findViewById(R.id.edtTelefone);
            EditText edtCelular = findViewById(R.id.edtCelular);
            EditText edtEmail = findViewById(R.id.edtEmail);
            EditText edtSenha = findViewById(R.id.edtSenha);
            EditText edtSenhaNovamente = findViewById(R.id.edtSenhaNovamente);

            String
                    nome = edtNome.getText().toString(),
                    rg = edtRg.getText().toString(),
                    cpf = edtCpf.getText().toString(),
                    endereco = edtEndereco.getText().toString(),
                    telefone = edtTelefone.getText().toString(),
                    celular = edtCelular.getText().toString(),
                    email = edtEmail.getText().toString(),
                    senha = edtSenha.getText().toString(),
                    senhaNovamente = edtSenhaNovamente.getText().toString();

            Colaborador colaborador = new Colaborador();
            colaborador.setNome(nome);
            colaborador.setRG(rg);
            colaborador.setCPF(cpf);
            colaborador.setEndereco(endereco);
            colaborador.setTelefone(telefone);
            colaborador.setCelular(celular);
            colaborador.setEmail(email);
            String keyColaboradores = tbColaboradores.push().getKey();
            tbColaboradores.child(keyColaboradores).setValue(colaborador);
            Toast.makeText(this, "Colaborador cadastrado com sucesso!", Toast.LENGTH_LONG).show();
            tbColaboradoresAdmin.child(pegarAdminKey()).setValue(keyColaboradores);
            Toast.makeText(this, "Colaborador vinculado a empresa!", Toast.LENGTH_LONG).show();
            /*firebaseAuth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener(this, task -> {
                if (task.isSuccessful()) {

                } else {
                    Toast.makeText(this, "Usuário já cadastrado no sistema!", Toast.LENGTH_LONG).show();
                }
            });*/


        } catch (Exception ex) {
            Log.d("COLABORADORES", ex.getMessage());
        }
    }

    public void voltar(View v){
        finish();
    }

    private String pegarAdminKey() {
        SharedPreferences preferences = getSharedPreferences("admin_preferences", MODE_PRIVATE);
        return preferences.getString("adminKey", "");
    }
}
