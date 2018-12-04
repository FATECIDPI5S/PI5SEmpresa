package br.com.icomidaempresa.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.recyclerview.widget.RecyclerView;
import br.com.icomidaempresa.R;
import br.com.icomidaempresa.model.Produto;

public class ProdutoAdapter extends RecyclerView.Adapter<ProdutoAdapter.ViewHolder> {

    private final List<Produto> mListPodutos;

    public ProdutoAdapter(List<Produto> items) {
        mListPodutos = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_produto_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mProduto = mListPodutos.get(position);
        holder.tvProdutoCodigo.setText(holder.mProduto.getCodigo());
        holder.tvProdutoNome.setText(holder.mProduto.getNome());
        holder.imgbProdutoEditar.setOnClickListener(view -> editarItem(holder.mProduto, position));
        holder.imgbProdutoDeletar.setOnClickListener(view -> deletarItem(position));
    }

    @Override
    public int getItemCount() {
        return mListPodutos != null ? mListPodutos.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView tvProdutoCodigo;
        public final TextView tvProdutoNome;
        public final AppCompatImageButton imgbProdutoEditar;
        public final AppCompatImageButton imgbProdutoDeletar;
        public Produto mProduto;

        public ViewHolder(@NonNull View view) {
            super(view);
            tvProdutoCodigo = view.findViewById(R.id.tvProdutoCodigo);
            tvProdutoNome = view.findViewById(R.id.tvProdutoNome);
            imgbProdutoEditar = view.findViewById(R.id.imgbProdutoEditar);
            imgbProdutoDeletar = view.findViewById(R.id.imgbProdutoDeletar);
        }
    }

    private void editarItem(Produto produto, int position) {
        produto.setNome("Alterei esse aqui XD");
        notifyItemChanged(position, produto);
    }

    private void deletarItem(int position) {
        mListPodutos.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mListPodutos.size());
    }
}
