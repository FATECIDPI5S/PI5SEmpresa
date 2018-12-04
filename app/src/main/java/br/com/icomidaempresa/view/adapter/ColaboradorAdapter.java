package br.com.icomidaempresa.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import br.com.icomidaempresa.model.Colaborador;
import br.com.icomidaempresa.R;

public class ColaboradorAdapter extends RecyclerView.Adapter<ColaboradorAdapter.ViewHolder> {

    private final List<Colaborador> mListColaboradores;

    public ColaboradorAdapter(List<Colaborador> mListColaboradores) {
        this.mListColaboradores = mListColaboradores;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_colaborador_item, parent, false);
        return new ColaboradorAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mColaborador = mListColaboradores.get(position);
        holder.tvCVColaboradorTitulo.setText(holder.mColaborador.getCPF());
        holder.tvCVColaboradorSubtitulo.setText(holder.mColaborador.getNome());
        holder.imgbColaboradorEditar.setOnClickListener(view -> editarItem(holder.mColaborador, position));
        holder.imgbColaboradorDeletar.setOnClickListener(view -> deletarItem(position));
        holder.imgbMostrarColaborador.setOnClickListener(view -> verificarDescricao(holder.imgbMostrarColaborador, holder.glColaboradorDescricao));
        holder.tvColaboradorNome.setText(holder.mColaborador.getNome());
        holder.tvColaboradorRG.setText(holder.mColaborador.getRG());
        holder.tvColaboradorCPF.setText(holder.mColaborador.getCPF());
        holder.tvColaboradorEndereco.setText(holder.mColaborador.getEndereco());
        holder.tvColaboradorTelefone.setText(holder.mColaborador.getTelefone());
        holder.tvColaboradorCelular.setText(holder.mColaborador.getCelular());
        holder.tvColaboradorEmail.setText(holder.mColaborador.getEmail());
        holder.tvColaboradorTipo.setText(holder.mColaborador.getTipo());
    }

    @Override
    public int getItemCount() {
        return mListColaboradores != null ? mListColaboradores.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView tvCVColaboradorTitulo;
        public final TextView tvCVColaboradorSubtitulo;
        public final ImageButton imgbColaboradorEditar;
        public final ImageButton imgbColaboradorDeletar;
        public final ImageButton imgbMostrarColaborador;
        public final GridLayout glColaboradorDescricao;
        public final TextView tvColaboradorNome;
        public final TextView tvColaboradorRG;
        public final TextView tvColaboradorCPF;
        public final TextView tvColaboradorEndereco;
        public final TextView tvColaboradorTelefone;
        public final TextView tvColaboradorCelular;
        public final TextView tvColaboradorEmail;
        public final TextView tvColaboradorTipo;
        public Colaborador mColaborador;

        public ViewHolder(@NonNull View view) {
            super(view);
            tvCVColaboradorTitulo = view.findViewById(R.id.tvCVColaboradorTitulo);
            tvCVColaboradorSubtitulo = view.findViewById(R.id.tvCVColaboradorSubtitulo);
            imgbColaboradorEditar = view.findViewById(R.id.imgbColaboradorEditar);
            imgbColaboradorDeletar = view.findViewById(R.id.imgbColaboradorDeletar);
            imgbMostrarColaborador = view.findViewById(R.id.imgbMostrarColaborador);
            glColaboradorDescricao = view.findViewById(R.id.glColaboradorDescricao);
            tvColaboradorNome = view.findViewById(R.id.tvColaboradorNome);
            tvColaboradorRG = view.findViewById(R.id.tvColaboradorRG);
            tvColaboradorCPF = view.findViewById(R.id.tvColaboradorCPF);
            tvColaboradorEndereco = view.findViewById(R.id.tvColaboradorEndereco);
            tvColaboradorTelefone = view.findViewById(R.id.tvColaboradorTelefone);
            tvColaboradorCelular = view.findViewById(R.id.tvColaboradorCelular);
            tvColaboradorEmail = view.findViewById(R.id.tvColaboradorEmail);
            tvColaboradorTipo = view.findViewById(R.id.tvColaboradorTipo);
        }
    }

    private void editarItem(Colaborador colaborador, int position) {
        colaborador.setNome("Alterei esse aqui XD");
        notifyItemChanged(position, colaborador);
    }

    private void deletarItem(int position) {
        mListColaboradores.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mListColaboradores.size());
    }

    private void verificarDescricao(ImageButton imgbMostrarColaboradorAUX, GridLayout glColaboradorDescricaoAUX) {
        if (glColaboradorDescricaoAUX.getVisibility() == View.VISIBLE) {
            imgbMostrarColaboradorAUX.setImageResource(R.drawable.ic_keyboard_arrow_down_black_50dp);
            glColaboradorDescricaoAUX.setVisibility(View.GONE);
        } else if (glColaboradorDescricaoAUX.getVisibility() == View.GONE) {
            imgbMostrarColaboradorAUX.setImageResource(R.drawable.ic_keyboard_arrow_up_black_50dp);
            glColaboradorDescricaoAUX.setVisibility(View.VISIBLE);
        }
    }
}
