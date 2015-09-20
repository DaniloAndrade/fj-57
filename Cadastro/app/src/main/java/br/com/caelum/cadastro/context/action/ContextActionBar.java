package br.com.caelum.cadastro.context.action;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;

import br.com.caelum.cadastro.ListaAlunosActivity;
import br.com.caelum.cadastro.dao.AlunoDAO;
import br.com.caelum.cadastro.modelo.Aluno;

/**
 * Created by android5243 on 19/09/15.
 */
public class ContextActionBar implements ActionMode.Callback {

    private final ListaAlunosActivity activity;
    private final Aluno aluno;

    public ContextActionBar(ListaAlunosActivity activity, Aluno aluno) {
        this.activity = activity;
        this.aluno = aluno;
    }

    @Override
    public boolean onCreateActionMode(final ActionMode actionMode, Menu menu) {
        MenuItem deletar = menu.add("Deletar");
        MenuItem ligar = menu.add("Ligar");
        menu.add("SMS");





        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                new AlertDialog.Builder(activity)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Deletar")
                        .setMessage("Deseja mesmo deletar?")
                        .setPositiveButton("Quero", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                AlunoDAO alunoDAO = new AlunoDAO(activity);
                                alunoDAO.deletar(aluno);
                                alunoDAO.close();

                                activity.carregarAlunos();
                                actionMode.finish();
                            }
                        }).setNegativeButton("Não", null).show();


                return false;
            }
        });



        return false;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
        return false;
    }

    @Override
    public void onDestroyActionMode(ActionMode actionMode) {

    }
}
