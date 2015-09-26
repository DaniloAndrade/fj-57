package br.com.caelum.cadastro.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.caelum.cadastro.R;
import br.com.caelum.cadastro.modelo.Aluno;

/**
 * Created by android5243 on 26/09/15.
 */
public class AlunoAdapterForListView extends BaseAdapter {

    private final LayoutInflater inflater;
    private final List<Aluno> alunos;

    public AlunoAdapterForListView(LayoutInflater inflater, List<Aluno> alunos) {
        this.inflater = inflater;
        this.alunos = alunos;
    }

    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Object getItem(int position) {
        return alunos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return alunos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Context context = inflater.getContext();
        Aluno aluno = alunos.get(position);

        View view = inflater.inflate(R.layout.item_aluno, parent, false);

        TextView nome = (TextView) view.findViewById(R.id.item_nome);
        ImageView foto = (ImageView) view.findViewById(R.id.item_foto);


        nome.setText(aluno.getNome());
        String caminhoFoto = aluno.getCaminhoFoto();
        Bitmap fotoAluno = null;
        if(caminhoFoto!=null && !caminhoFoto.isEmpty()) {
           fotoAluno = BitmapFactory.decodeFile(caminhoFoto);
            fotoAluno = Bitmap.createScaledBitmap(fotoAluno,150,250,true);
        }else {
            fotoAluno = BitmapFactory
                    .decodeResource(context.getResources(), R.drawable.ic_no_image);
            fotoAluno = Bitmap.createScaledBitmap(fotoAluno,100,100,true);
        }

        foto.setImageBitmap(fotoAluno);

        if(position % 2 == 0){
            view.setBackgroundColor(context.getResources().getColor(R.color.linha_par));
        }else {
            view.setBackgroundColor(context.getResources().getColor(R.color.linha_impar));
        }
        return view;
    }
}
