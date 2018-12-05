package br.com.icomidaempresa.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import br.com.icomidaempresa.R;
import br.com.icomidaempresa.model.Administrador;

public class SignUpActivity extends AppCompatActivity {
    private static final String TAG = "SignUpActivity";

    private FirebaseAuth firebaseAuth;
    FirebaseDatabase iComidaDb = FirebaseDatabase.getInstance();
    DatabaseReference tbAdmin = iComidaDb.getReference("administrador");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        firebaseAuth = FirebaseAuth.getInstance();

    }

    public void CadastrarUsuario(View v){
        try{
            EditText edtNome = findViewById(R.id.edtNome);
            EditText edtEmail = findViewById(R.id.edtEmail);
            EditText edtSenha = findViewById(R.id.edtSenha);
            EditText edtSenhaNovament = findViewById(R.id.edtSenhaNovamente);

            String nome = edtNome.getText().toString().trim();
            String email = edtEmail.getText().toString().trim();
            String senha = edtSenha.getText().toString().trim();
            String senhaNovamente = edtSenha.getText().toString().trim();


            if (!senha.equals(senhaNovamente)) {
                Toast.makeText(this, "As senhas informadas são diferentes", Toast.LENGTH_LONG).show();
                return;
            }

            firebaseAuth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener(this, task -> {
                if (task.isSuccessful()){
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    Administrador administrador = new Administrador();
                    administrador.setNome(nome);
                    administrador.setEmail(user.getEmail());
                    tbAdmin.push().setValue(administrador);
                    Toast.makeText(this, "Usuario cadastrado com sucesso!", Toast.LENGTH_LONG).show();
                    finish();
                    startActivity(new Intent(this, LoginActivity.class));
                }
                else {
                    Toast.makeText(this, "Usuário já cadastrado no sistema!", Toast.LENGTH_LONG).show();
                }
            });
        } catch (Exception ex) {

        }
    }



}
