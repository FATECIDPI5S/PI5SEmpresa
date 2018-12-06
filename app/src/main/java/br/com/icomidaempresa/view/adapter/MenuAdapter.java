package br.com.icomidaempresa.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import br.com.icomidaempresa.model.Produto;
import br.com.icomidaempresa.R;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {

    private final List<Produto> mListPodutos;

    public MenuAdapter(List<Produto> mListPodutos) {
        this.mListPodutos = mListPodutos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_menu_item, parent, false);
        return new MenuAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Produto produto = mListPodutos.get(position);
        holder.tvProdutoCodigo.setText(produto.getCodigo());
        holder.tvProdutoNome.setText(produto.getNome());
        holder.imgbAlterarStatusProduto.setOnClickListener(view -> editarItem(holder.imgbAlterarStatusProduto, produto, position));
        popularImagem(holder.imgbAlterarStatusProduto, produto.getStatus());
    }

    @Override
    public int getItemCount() {
        return mListPodutos != null ? mListPodutos.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView tvProdutoCodigo;
        public final TextView tvProdutoNome;
        public final TextView tvProdutoPreco;
        public final ImageButton imgbAlterarStatusProduto;

        public ViewHolder(@NonNull View view) {
            super(view);
            tvProdutoCodigo = view.findViewById(R.id.tvProdutoCodigo);
            tvProdutoNome = view.findViewById(R.id.tvProdutoNome);
            tvProdutoPreco = view.findViewById(R.id.tvProdutoPreco);
            imgbAlterarStatusProduto = view.findViewById(R.id.imgbAlterarStatusProduto);
        }
    }

    private void popularImagem(ImageButton imageButton, Boolean status) {
        if (status) {
            imageButton.setImageResource(R.drawable.ic_check_box_black_24dp);
        } else {
            imageButton.setImageResource(R.drawable.ic_check_box_outline_blank_black_24dp);
        }
    }

    private void editarItem(ImageButton imageButton, Produto produto, int position) {
        if (produto.getStatus()) {
            produto.setStatus(false);
            imageButton.setImageResource(R.drawable.ic_check_box_outline_blank_black_24dp);
        } else {
            produto.setStatus(true);
            imageButton.setImageResource(R.drawable.ic_check_box_black_24dp);
        }
        notifyItemChanged(position);
    }
}
