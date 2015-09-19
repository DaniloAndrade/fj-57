package br.com.caelum.cadastro.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.caelum.cadastro.R;
import br.com.caelum.cadastro.modelo.Aluno;

/**
 * Created by danilo on 14/09/15.
 */
public class AlunoAdapter extends RecyclerView.Adapter<AlunoAdapter.AlunoViewHolder> {


    private List<Aluno> alunos;

    public AlunoAdapter(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    @Override
    public AlunoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        AlunoViewHolder holder = new AlunoViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(AlunoViewHolder holder, int position) {
        holder.alunoNome.setText(alunos.get(position).getNome());
        holder.alunoEmail.setText(alunos.get(position).getEmail());

    }

    @Override
    public int getItemCount() {
        return alunos.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class AlunoViewHolder extends RecyclerView.ViewHolder{

        CardView cv;
        TextView alunoNome;
        TextView alunoEmail;
        ImageView alunoFoto;


        public AlunoViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.card_aluno);
            alunoNome = (TextView) itemView.findViewById(R.id.nome_aluno);
            alunoEmail = (TextView) itemView.findViewById(R.id.email_aluno);

            alunoFoto = (ImageView) itemView.findViewById(R.id.imagem_aluno);
        }
    }
}
