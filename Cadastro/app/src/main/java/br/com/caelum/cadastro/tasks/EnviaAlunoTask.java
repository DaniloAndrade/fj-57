package br.com.caelum.cadastro.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.List;

import br.com.caelum.cadastro.converter.AlunoConverter;
import br.com.caelum.cadastro.dao.AlunoDAO;
import br.com.caelum.cadastro.modelo.Aluno;
import br.com.caelum.cadastro.support.WebClient;

/**
 * Created by android5243 on 26/09/15.
 */
public class EnviaAlunoTask extends AsyncTask<Object,Object,String> {

    private Context context;
    private ProgressDialog progressDialog;

    public EnviaAlunoTask(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(Object... params) {

        AlunoDAO dao = new AlunoDAO(context);
        List<Aluno> alunos = dao.getLista();
        dao.close();

        String json = new AlunoConverter().toJSON(alunos);

        String response = null;
        response = new WebClient().post(json);
        return response;
    }

    @Override
    protected void onPostExecute(String resposta) {
        super.onPostExecute(resposta);
        progressDialog.dismiss();
        Toast.makeText(context,resposta,Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPreExecute() {
        progressDialog = ProgressDialog.show(context, "Aguarde...", "Envio de dados para web", true, true);
    }
}
