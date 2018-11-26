package br.com.icomidaempresa.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import br.com.icomidaempresa.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void abrirHome(View v){
        finish();
        startActivity(new Intent(this, MainActivity.class));
    }

    public void abrirSignUp(View v){
        startActivity(new Intent(this, SignUpActivity.class));
    }
}
