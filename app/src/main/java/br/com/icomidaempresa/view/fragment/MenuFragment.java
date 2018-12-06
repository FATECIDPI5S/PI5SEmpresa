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

        Produto produto1 = new Produto();
        produto1.setCodigo("12345ABCDE");
        produto1.setNome("X-Bacon");
        produto1.setStatus(true);

        Produto produto2 = new Produto();
        produto2.setCodigo("23456BCDEF");
        produto2.setNome("X-Tudo");
        produto2.setStatus(false);

        List<Produto> produtos = new ArrayList<>();
        produtos.add(produto1);
        produtos.add(produto2);
        produtos.add(produto1);
        produtos.add(produto2);
        produtos.add(produto1);
        produtos.add(produto2);
        produtos.add(produto1);
        produtos.add(produto2);
        produtos.add(produto1);
        produtos.add(produto2);
        produtos.add(produto1);
        produtos.add(produto2);
        rvProduto.setAdapter(new MenuAdapter(produtos));

        rvProduto.addItemDecoration(
                new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL));

        return view;
    }
}
