package br.com.caelum.cadastro.converter;

import org.json.JSONException;
import org.json.JSONStringer;

import java.util.List;

import br.com.caelum.cadastro.modelo.Aluno;

/**
 * Created by android5243 on 26/09/15.
 */
public class AlunoConverter {

    public String toJSON(List<Aluno> alunos){
        JSONStringer jsonStringer = new JSONStringer();

        try {
            jsonStringer.object();
            jsonStringer.key("list").array();
            jsonStringer.object();
            jsonStringer.key("aluno").array();

            for (Aluno aluno : alunos){
                jsonStringer.object()
                        .key("id").value(aluno.getId())
                        .key("nome").value(aluno.getNome())
                        .key("telefone").value(aluno.getTelefone())
                        .key("endereco").value(aluno.getEndereco())
                        .key("site").value(aluno.getSite())
                        .key("nota").value(aluno.getNota());
                jsonStringer.endObject();
            }


            jsonStringer.endArray();
            jsonStringer.endObject();
            jsonStringer.endArray();
            jsonStringer.endObject();

            return jsonStringer.toString();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
