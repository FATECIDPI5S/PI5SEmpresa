package br.com.icomidaempresa.view.fragment;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import br.com.icomidaempresa.R;

public class ConfiguracoesFragment extends Fragment {

    public ConfiguracoesFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_configuracoes, container, false);

        Spinner spLanguage = view.findViewById(R.id.spLanguage);
        spLanguage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    Locale locale;
                    switch (position) {
                        case 1:
                            locale = new Locale("pt");
                            break;
                        case 2:
                            locale = new Locale("en");
                            break;
                        case 3:
                            locale = new Locale("es");
                            break;
                        default:
                            locale = new Locale("pt");
                    }
                    Locale.setDefault(locale);
                    Configuration config = getContext().getResources().getConfiguration();
                    config.locale = locale;
                    getContext().getResources().updateConfiguration(config, getContext().getResources().getDisplayMetrics());
                    getActivity().recreate();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        return view;
    }
}
