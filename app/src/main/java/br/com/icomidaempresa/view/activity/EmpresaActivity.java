package br.com.icomidaempresa.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;
import br.com.icomidaempresa.R;
import br.com.icomidaempresa.model.Empresa;

public class EmpresaActivity extends AppCompatActivity {
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mEmpresaDatabaseReference;
    private DatabaseReference mAdminEmpresaDatabaseReference;

    EditText edtRazaoSocial;
    EditText edtNomeFantasia;
    EditText edtCNPJ;
    EditText edtIE;
    EditText edtEndereco;
    EditText edtTelefone;
    EditText edtCelular;
    Button btnAdicionar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empresa);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mEmpresaDatabaseReference = mFirebaseDatabase.getReference().child("empresa");
        mAdminEmpresaDatabaseReference = mFirebaseDatabase.getReference().child("admin_empresa");

        edtRazaoSocial = findViewById(R.id.edtRazaoSocial);
        edtNomeFantasia = findViewById(R.id.edtNomeFantasia);
        edtCNPJ = findViewById(R.id.edtCNPJ);
        edtIE = findViewById(R.id.edtIE);
        edtEndereco = findViewById(R.id.edtEndereco);
        edtTelefone = findViewById(R.id.edtTelefone);
        edtCelular = findViewById(R.id.edtCelular);
        btnAdicionar = findViewById(R.id.btnAdicionar);

        Intent intent = getIntent();
        Boolean update = intent.getBooleanExtra("update",false);
        if(update){
            prepararUpdate(intent);
        }else{
            btnAdicionar.setOnClickListener(view -> cadastrarEmpresa());
        }
    }

    public void cadastrarEmpresa() {
        try {
            String RazaoSocial = edtRazaoSocial.getText().toString();
            String NomeFantasia = edtNomeFantasia.getText().toString();
            String CNPJ = edtCNPJ.getText().toString();
            String IE = edtIE.getText().toString();
            String Endereco = edtEndereco.getText().toString();
            String Telefone = edtTelefone.getText().toString();
            String Celular = edtCelular.getText().toString();

            Empresa empresa = new Empresa();
            empresa.setRazaoSocial(RazaoSocial);
            empresa.setNomeFantasia(NomeFantasia);
            empresa.setCNPJ(CNPJ);
            empresa.setIE(IE);
            empresa.setEndereco(Endereco);
            empresa.setTelefone(Telefone);
            empresa.setCelular(Celular);

            String keyEmpresa = mEmpresaDatabaseReference.push().getKey();
            mAdminEmpresaDatabaseReference.child(pegarAdminKey()).setValue(keyEmpresa);
            mEmpresaDatabaseReference.child(keyEmpresa).setValue(empresa);

            finish();
        } catch (Exception ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private String pegarAdminKey() {
        SharedPreferences preferences = getSharedPreferences("admin_preferences", MODE_PRIVATE);
        return preferences.getString("adminKey", "");
    }

    private void prepararUpdate(Intent intent){
        setTitle(R.string.title_activity_empresa_alterar);
        String empresaKey = intent.getStringExtra("empresaKey");
        String tvRazaoSocial = intent.getStringExtra("tvRazaoSocial");
        String tvNomeFantasia = intent.getStringExtra("tvNomeFantasia");
        String tvCNPJ = intent.getStringExtra("tvCNPJ");
        String tvIE = intent.getStringExtra("tvIE");
        String tvEndereco = intent.getStringExtra("tvEndereco");
        String tvTelefone = intent.getStringExtra("tvTelefone");
        String tvCelular = intent.getStringExtra("tvCelular");
        edtRazaoSocial.setText(tvRazaoSocial);
        edtNomeFantasia.setText(tvNomeFantasia);
        edtCNPJ.setText(tvCNPJ);
        edtIE.setText(tvIE);
        edtEndereco.setText(tvEndereco);
        edtTelefone.setText(tvTelefone);
        edtCelular.setText(tvCelular);
        btnAdicionar.setText(R.string.btnAlterar);
        btnAdicionar.setOnClickListener(view -> alterarEmpresa(empresaKey));
    }

    private void alterarEmpresa(String empresaKey){
        String RazaoSocial = edtRazaoSocial.getText().toString();
        String NomeFantasia = edtNomeFantasia.getText().toString();
        String CNPJ = edtCNPJ.getText().toString();
        String IE = edtIE.getText().toString();
        String Endereco = edtEndereco.getText().toString();
        String Telefone = edtTelefone.getText().toString();
        String Celular = edtCelular.getText().toString();

        Empresa empresa = new Empresa();
        empresa.setRazaoSocial(RazaoSocial);
        empresa.setNomeFantasia(NomeFantasia);
        empresa.setCNPJ(CNPJ);
        empresa.setIE(IE);
        empresa.setEndereco(Endereco);
        empresa.setTelefone(Telefone);
        empresa.setCelular(Celular);

        mEmpresaDatabaseReference.child(empresaKey).setValue(empresa);
        finish();
    }

    public void voltar(View v) {
        finish();
    }
}