package br.com.icomidaempresa;


import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class ColaboradoresFragment extends Fragment {

    GridLayout glColaboradorDescricao;
    ImageButton imgbMostrarDescricao;

    public ColaboradoresFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_colaboradores, container, false);

        FloatingActionButton fabColaboradores = (FloatingActionButton) view.findViewById(R.id.fabColaboradores);
        glColaboradorDescricao = (GridLayout) view.findViewById(R.id.glColaboradorDescricao);
        imgbMostrarDescricao = (ImageButton) view.findViewById(R.id.imgbMostrarDescricao);

        glColaboradorDescricao.setVisibility(View.GONE);

        imgbMostrarDescricao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(glColaboradorDescricao.getVisibility() == View.VISIBLE){
                    esconderDescricao();
                }else if(glColaboradorDescricao.getVisibility() == View.GONE){
                    mostrarDescricao();
                }
            }
        });

        fabColaboradores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ColaboradoresActivity.class));
            }
        });

        return view;
    }

    private void esconderDescricao(){
        imgbMostrarDescricao.setImageResource(R.drawable.ic_keyboard_arrow_down_black_50dp);
        glColaboradorDescricao.setVisibility(View.GONE);
    }

    private void mostrarDescricao(){
        imgbMostrarDescricao.setImageResource(R.drawable.ic_keyboard_arrow_up_black_50dp);
        glColaboradorDescricao.setVisibility(View.VISIBLE);
    }
}
