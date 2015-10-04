package br.com.caelum.cadastro;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import br.com.caelum.cadastro.fragment.DetalhesProvaFragment;
import br.com.caelum.cadastro.fragment.ListaProvasFragment;
import br.com.caelum.cadastro.modelo.Prova;

public class ProvasActivity extends AppCompatActivity {

    private TipoDispositivo tipoDispositivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provas);

        tipoDispositivo = TipoDispositivo
                .valueOf(getResources().getString(R.string.tipo_dispositivo));

        tipoDispositivo.configuraFragment(this);
    }

    public void selecionaProva(Prova prova) {
        tipoDispositivo.exibeProva(this,prova);
    }


    enum TipoDispositivo{
        SMARTPHONE {
            @Override
            void configuraFragment(ProvasActivity provasActivity) {
                FragmentTransaction fragmentTransaction = provasActivity
                        .getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.provas_view, new ListaProvasFragment());
                fragmentTransaction.commit();
            }

            @Override
            void exibeProva(ProvasActivity provasActivity, Prova prova) {
                FragmentTransaction fragmentTransaction = provasActivity
                        .getSupportFragmentManager().beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putSerializable("Prova", prova);

                DetalhesProvaFragment detalhesProvaFragment = new DetalhesProvaFragment();
                detalhesProvaFragment.setArguments(bundle);

                fragmentTransaction.replace(R.id.provas_view, detalhesProvaFragment)
                .addToBackStack(null);
                fragmentTransaction.commit();
            }
        },
        TABLET {
            @Override
            void configuraFragment(ProvasActivity provasActivity) {
                FragmentTransaction fragmentTransaction = provasActivity
                        .getSupportFragmentManager().beginTransaction();
                fragmentTransaction
                        .replace(R.id.provas_lista, new ListaProvasFragment())
                        .replace(R.id.provas_detalhes, new DetalhesProvaFragment());

                fragmentTransaction.commit();
            }

            @Override
            void exibeProva(ProvasActivity provasActivity, Prova prova) {
                FragmentManager supportFragmentManager = provasActivity
                        .getSupportFragmentManager();

                DetalhesProvaFragment detalhesProvaFragment = (DetalhesProvaFragment)
                        supportFragmentManager.findFragmentById(R.id.provas_detalhes);
                detalhesProvaFragment.populaCamposComDados(prova);
            }


        };


         abstract void configuraFragment(ProvasActivity provasActivity) ;

         abstract void exibeProva(ProvasActivity provasActivity, Prova prova);
    }

}
