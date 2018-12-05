package br.com.icomidaempresa.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
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
import br.com.icomidaempresa.R;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firebaseAuth = FirebaseAuth.getInstance();

        if (VerificarStatusLogin() != null){
            abrirHome();
        }

    }

    public void abrirHome(){
        finish();
        startActivity(new Intent(this, MainActivity.class));
    }

    public void ValidarLogin(View v){
        try {
            EditText edtEmail = findViewById(R.id.edtEmail);
            EditText edtSenha = findViewById(R.id.edtSenha);

            firebaseAuth.signInWithEmailAndPassword(edtEmail.getText().toString().trim(), edtSenha.getText().toString().trim())
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            FirebaseUser user = VerificarStatusLogin();
                            Toast.makeText(LoginActivity.this,  user.getEmail() + ", bem vindo novamente!", Toast.LENGTH_SHORT).show();
                            abrirHome();
                        } else {
                            Toast.makeText(LoginActivity.this,  "Usuário ou senha inválidos!", Toast.LENGTH_SHORT).show();
                        }
                    });
        } catch (Exception ex) {

        }
    }

    public FirebaseUser VerificarStatusLogin(){
        return firebaseAuth.getCurrentUser();
    }


    public void abrirSignUp(View v){
        startActivity(new Intent(this, SignUpActivity.class));
    }
}
