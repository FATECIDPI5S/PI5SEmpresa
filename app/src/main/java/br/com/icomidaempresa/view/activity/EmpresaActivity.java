package br.com.icomidaempresa.view.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
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
    }

    public void CadastrarEmpresa(View v) {
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

            mEmpresaDatabaseReference.push().setValue(empresa);
            mAdminEmpresaDatabaseReference.child(pegarAdminKey()).setValue(empresa.getCNPJ());

            finish();
        } catch (Exception ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private String pegarAdminKey() {
        SharedPreferences preferences = getSharedPreferences("admin_preferences", MODE_PRIVATE);
        return preferences.getString("adminKey", "");
    }

    public void voltar(View v) {
        finish();
    }
}