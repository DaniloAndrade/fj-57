package br.com.caelum.cadastro.helper;

import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;

import br.com.caelum.cadastro.FormularioActivity;
import br.com.caelum.cadastro.R;
import br.com.caelum.cadastro.modelo.Aluno;

/**
 * Created by android5243 on 12/09/15.
 */
public class FormularioHelper {


    private FormularioActivity formularioActivity;
    private Aluno aluno;
    private final EditText email;
    private final RatingBar nota;
    private final EditText site;
    private final EditText endereco;
    private final EditText telefone;
    private final EditText nome;

    public FormularioHelper(FormularioActivity formularioActivity) {
        this.formularioActivity = formularioActivity;
        nome = (EditText) formularioActivity.findViewById(R.id.formulario_nome);
        telefone = (EditText) formularioActivity.findViewById(R.id.formulario_telefone);
        endereco = (EditText) formularioActivity.findViewById(R.id.formulario_endereco);
        email = (EditText) formularioActivity.findViewById(R.id.formulario_email);

        site = (EditText) formularioActivity.findViewById(R.id.formulario_site);
        nota = (RatingBar) formularioActivity.findViewById(R.id.formulario_nota);
        aluno = new Aluno();
    }

    public Aluno pegaAlunoDoFormulario(){
        aluno.setNome(nome.getText().toString());
        aluno.setTelefone(telefone.getText().toString());
        aluno.setEndereco(endereco.getText().toString());
        aluno.setEmail(email.getText().toString());
        aluno.setSite(site.getText().toString());
        aluno.setNota((double) nota.getRating());

        return aluno;
    }

    public boolean estaValido() {
        //Adicionar outras validações

        return !nome.getText().toString().isEmpty();
    }

    public void mostrarErros() {
        nome.setError("Campo nome não pode ser vazio");
    }

    public void colocaNoFormulario(Aluno alunoSelecionado) {
        nome.setText(alunoSelecionado.getNome());
        telefone.setText(alunoSelecionado.getTelefone());
        endereco.setText(alunoSelecionado.getEndereco());
        email.setText(alunoSelecionado.getEmail());

        site.setText(alunoSelecionado.getSite());
        nota.setRating(alunoSelecionado.getNota().floatValue());
        aluno = alunoSelecionado;
    }
}
