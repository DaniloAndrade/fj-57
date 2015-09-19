package br.com.caelum.cadastro;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.io.Serializable;

import br.com.caelum.cadastro.dao.AlunoDAO;
import br.com.caelum.cadastro.helper.FormularioHelper;
import br.com.caelum.cadastro.modelo.Aluno;

public class FormularioActivity extends ActionBarActivity {

    private FormularioHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        helper = new FormularioHelper(this);
        Aluno alunoSelecionado = (Aluno) getIntent().getSerializableExtra(ListaAlunosActivity.ALUNO_SELECIONADO);

        if(alunoSelecionado != null){
            helper.colocaNoFormulario(alunoSelecionado);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_formulario, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_formulario_ok:


                if(helper.estaValido()){
                    AlunoDAO alunoDAO = new AlunoDAO(this);
                    Aluno aluno = helper.pegaAlunoDoFormulario();
                    alunoDAO.adicionaOuAltera(aluno);
                    alunoDAO.close();

                    finish();
                } else{
                    helper.mostrarErros();
                }




                return false;

            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
