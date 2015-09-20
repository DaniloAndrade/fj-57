package br.com.caelum.cadastro;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.caelum.cadastro.context.action.ContextActionBar;
import br.com.caelum.cadastro.dao.AlunoDAO;
import br.com.caelum.cadastro.modelo.Aluno;

import static android.widget.AdapterView.*;


public class ListaAlunosActivity extends ActionBarActivity {

    public static final String ALUNO_SELECIONADO = "alunoSelecionado";
    private ListView listViewAlunos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        listViewAlunos = (ListView) findViewById(R.id.list_alunos);
        addListenerListView(listViewAlunos);
        registerForContextMenu(listViewAlunos);


        View botaoNovo = findViewById(R.id.lista_alunos_floating_button);
        botaoNovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
                startActivity(intent);
            }
        });
    }

    private void addListenerListView(ListView listViewAlunos) {
        listViewAlunos.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Aluno alunoSelecionado = (Aluno) parent.getItemAtPosition(position);
                Log.i("CLICK ITEM LIST", "Clicou no aluno: " + alunoSelecionado.toString());
                Toast.makeText(
                        ListaAlunosActivity.this, "Clicou no aluno: " + parent.getItemAtPosition(position).toString(),
                        Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
                intent.putExtra(ALUNO_SELECIONADO,alunoSelecionado);
                startActivity(intent);
            }
        });

        listViewAlunos.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                Aluno alunoSelecionado = (Aluno) parent.getItemAtPosition(position);
//                Log.i("CLICK LONGO ITEM LIST", "Clicou longo no aluno: " + alunoSelecionado.toString());
//                Toast.makeText(
//                        ListaAlunosActivity.this, "Clicou longo no aluno: " + alunoSelecionado.toString(),
//                        Toast.LENGTH_SHORT).show();


                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarAlunos();
    }

    public void carregarAlunos() {
        AlunoDAO alunoDAO = new AlunoDAO(this);
        List<Aluno> alunos = alunoDAO.getLista();
        ArrayAdapter<Aluno> arrayAdapter =
                new ArrayAdapter<Aluno>(this,android.R.layout.simple_list_item_1,alunos);
        listViewAlunos.setAdapter(arrayAdapter);
        alunoDAO.close();
    }

    @Override
    public void onCreateContextMenu(final ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {

        AdapterContextMenuInfo contextMenuInfo = (AdapterContextMenuInfo) menuInfo;
        final Aluno aluno = (Aluno) listViewAlunos.getItemAtPosition(contextMenuInfo.position);

        super.onCreateContextMenu(menu, v, menuInfo);
        final MenuItem deletar = menu.add("Deletar");



        aluno.executeSeTemTelefone(new Aluno.ListenerForAluno() {
            @Override
            public void execute() {
                final MenuItem ligar = menu.add("Ligar");
                final MenuItem sms = menu.add("SMS");
                configuraAcaoParaLigar(aluno, ligar);
                configuraAcaoParaSMS(aluno, sms);
            }
        });

        aluno.executeSeTemEndereco(new Aluno.ListenerForAluno() {
            @Override
            public void execute() {
                final MenuItem mapa = menu.add("Mapa");
                configuraAcaoParaMapa(aluno, mapa);
            }
        });

        aluno.executeSeTemSite(new Aluno.ListenerForAluno() {
            @Override
            public void execute() {
                final MenuItem site = menu.add("Site");
                configuraAcaoParaSite(aluno, site);
            }
        });
        configuraListenerParaDeletar(aluno, deletar);







    }

    private void configuraAcaoParaSMS(Aluno aluno, MenuItem sms) {
        Intent intentParaSms = new Intent(Intent.ACTION_VIEW);
        intentParaSms.setData(Uri.parse("sms:" + aluno.getTelefone()));
        sms.setIntent(intentParaSms);
    }

    private void configuraAcaoParaMapa(Aluno aluno, MenuItem mapa) {
        Intent intentParaSms = new Intent(Intent.ACTION_VIEW);
        intentParaSms.setData(Uri.parse("geo:0,0?z=14&q=" + aluno.getEndereco()));
        mapa.setIntent(intentParaSms);
    }


    private void configuraAcaoParaSite(Aluno aluno, MenuItem site) {
        Intent intentParaSite = new Intent(Intent.ACTION_VIEW);
        String siteAluno = aluno.getSite();
        if(!siteAluno.startsWith("http://")){
            siteAluno = "http://"+siteAluno;
        }
        intentParaSite.setData(Uri.parse(siteAluno));
        site.setIntent(intentParaSite);
    }

    private void configuraAcaoParaLigar(Aluno aluno, MenuItem ligar) {
        Intent intentParaLigar= new Intent(Intent.ACTION_CALL);
        intentParaLigar.setData(Uri.parse("tel:" + aluno.getTelefone()));
        ligar.setIntent(intentParaLigar);
    }

    private void configuraListenerParaDeletar(final Aluno aluno, MenuItem deletar) {
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                new AlertDialog.Builder(ListaAlunosActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Deletar")
                        .setMessage("Deseja mesmo deletar?")
                        .setPositiveButton("Quero",new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                AlunoDAO alunoDAO = new AlunoDAO(ListaAlunosActivity.this);
                                alunoDAO.deletar(aluno);

                                carregarAlunos();
                                alunoDAO.close();
                            }
                        }).setNegativeButton("Não",null).show();


                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lista_alunos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
