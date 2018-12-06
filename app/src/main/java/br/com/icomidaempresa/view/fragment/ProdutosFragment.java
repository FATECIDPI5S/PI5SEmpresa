package br.com.icomidaempresa.view.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import br.com.icomidaempresa.R;
import br.com.icomidaempresa.model.Produto;
import br.com.icomidaempresa.view.activity.ProdutosActivity;
import br.com.icomidaempresa.view.adapter.ProdutoAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProdutosFragment extends Fragment {

    public ProdutosFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_produto_list, container, false);

        RecyclerView rvProduto = view.findViewById(R.id.rvProduto);

        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        rvProduto.setLayoutManager(layoutManager);

        List<Produto> produtos = new ArrayList<>();
        for (int i = 1; i <= 10; i ++){
            Produto produto = new Produto();
            String codigoProduto = "";

            for(int j = 1; j <= 9; j++){
                codigoProduto += String.valueOf(j);
            }
            produto.setCodigo(codigoProduto);
            produto.setNome("Produto " + String.valueOf(i));
            produtos.add(produto);
        }

        rvProduto.setAdapter(new ProdutoAdapter(produtos));
        rvProduto.addItemDecoration(
                new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL));

        FloatingActionButton fabProdutos = view.findViewById(R.id.fabProdutos);
        fabProdutos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ProdutosActivity.class));
            }
        });

        return view;
    }
}
