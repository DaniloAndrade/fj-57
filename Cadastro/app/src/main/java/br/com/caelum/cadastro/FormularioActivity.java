package br.com.caelum.cadastro;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;

import br.com.caelum.cadastro.dao.AlunoDAO;
import br.com.caelum.cadastro.helper.FormularioHelper;
import br.com.caelum.cadastro.modelo.Aluno;

public class FormularioActivity extends AppCompatActivity {

    private static final int CAMERA_REQUEST_CODE = 21222;
    private FormularioHelper helper;

    private String localArquivoFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        helper = new FormularioHelper(this);
        Aluno alunoSelecionado = (Aluno) getIntent().getSerializableExtra(ListaAlunosActivity.ALUNO_SELECIONADO);

        if(alunoSelecionado != null){
            helper.colocaNoFormulario(alunoSelecionado);
        }

        ImageView fotoButton = helper.getFotoButton();
        fotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                localArquivoFoto = getExternalFilesDir(null) + "/" + System.currentTimeMillis() + ".jpg";
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(localArquivoFoto)));
                startActivityForResult(intent, CAMERA_REQUEST_CODE);

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST_CODE){
            if (resultCode == Activity.RESULT_OK){
                helper.carregarImagem(this.localArquivoFoto);
            }else {
                this.localArquivoFoto = null;
            }

        }
    }
}
