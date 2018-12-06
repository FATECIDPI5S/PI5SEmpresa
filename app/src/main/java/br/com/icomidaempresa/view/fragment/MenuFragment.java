package br.com.icomidaempresa.view.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import br.com.icomidaempresa.R;
import br.com.icomidaempresa.model.Produto;
import br.com.icomidaempresa.view.adapter.MenuAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends Fragment {

    public MenuFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_list, container, false);

        RecyclerView rvProduto = view.findViewById(R.id.rvMenu);

        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        rvProduto.setLayoutManager(layoutManager);

        List<Produto> produtos = new ArrayList<>();
        for (int i = 1; i <= 11; i ++){
            Produto produto = new Produto();
            String codigoProduto = "";
            for(int j = 1; j <= 10; j++){
                codigoProduto += String.valueOf(j);
            }
            produto.setCodigo(codigoProduto);
            produto.setNome("Produto " + String.valueOf(i));

            if ((i % 2) == 0)
                produto.setStatus(true);
            else
                produto.setStatus(false);

            produtos.add(produto);
        }

        rvProduto.setAdapter(new MenuAdapter(produtos));
        rvProduto.addItemDecoration(
                new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL));

        return view;
    }
}
