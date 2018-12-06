package br.com.icomidaempresa.view.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;
import br.com.icomidaempresa.R;
import br.com.icomidaempresa.model.Produto;

public class ProdutosActivity extends AppCompatActivity {
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mProdutoDatabaseReference;
    DatabaseReference mProdutoAdicionalDatabaseReference;
    DatabaseReference mAdminProdutoDatabaseReference;
    DatabaseReference mAdminProdutoAdicionalDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produtos);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mProdutoDatabaseReference = mFirebaseDatabase.getReference("produto");
        mAdminProdutoDatabaseReference = mFirebaseDatabase.getReference("admin_produto");
        mProdutoAdicionalDatabaseReference = mFirebaseDatabase.getReference("produto_adicional");
        mAdminProdutoAdicionalDatabaseReference = mFirebaseDatabase.getReference("admin_produto_adicional");
    }

    public void cadastrarProduto(View v) {
        try {
            EditText edtCodigo = findViewById(R.id.edtCodigo);
            EditText edtNome = findViewById(R.id.edtNome);
            EditText edtPrecoVenda = findViewById(R.id.edtPrecoVenda);
            CheckBox cbProdutoAdicional = findViewById(R.id.cbProdutoAdicional);

            String codigo = edtCodigo.getText().toString();
            String nome = edtNome.getText().toString();
            Double precoVenda = Double.parseDouble(edtPrecoVenda.getText().toString());
            Boolean produtoAdicional = cbProdutoAdicional.isChecked();

            Produto produto = new Produto();
            produto.setCodigo(codigo);
            produto.setNome(nome);
            produto.setPreco(precoVenda);
            produto.setStatus(true);

            if (!produtoAdicional) {
                String keyProduto = mProdutoDatabaseReference.push().getKey();
                mProdutoDatabaseReference.child(keyProduto).setValue(produto);
                Toast.makeText(this, "Produto cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                mAdminProdutoDatabaseReference.child(pegarAdminKey()).child(keyProduto).setValue(true);
                Toast.makeText(this, "Produto vinculado a empresa!", Toast.LENGTH_SHORT).show();
            } else {
                String keyProdutoAdicional = mProdutoAdicionalDatabaseReference.push().getKey();
                mProdutoAdicionalDatabaseReference.child(keyProdutoAdicional).setValue(produto);
                Toast.makeText(this, "Produto Adicional cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                mAdminProdutoAdicionalDatabaseReference.child(pegarAdminKey()).child(keyProdutoAdicional).setValue(true);
                Toast.makeText(this, "Produto Adicional vinculado a empresa!", Toast.LENGTH_SHORT).show();
            }
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