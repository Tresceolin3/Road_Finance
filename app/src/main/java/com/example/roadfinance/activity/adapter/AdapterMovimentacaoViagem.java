package com.example.roadfinance.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.recyclerview.widget.RecyclerView;

import com.example.roadfinance.R;
import com.example.roadfinance.activity.model.MovimentacaoViagem;

import java.util.List;

public class AdapterMovimentacaoViagem  extends RecyclerView.Adapter<AdapterMovimentacaoViagem.MyViewHolder>{

    List<MovimentacaoViagem> movimentacoesViagem;
    Context context;



    public AdapterMovimentacaoViagem(List<MovimentacaoViagem> movimentacoesViagem, Context context) {
        this.movimentacoesViagem = movimentacoesViagem;
        this.context = context;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_movimentacao_viagem,
                parent, false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        MovimentacaoViagem movimentacao_viagem = movimentacoesViagem.get(position);

        holder.origem.setText(movimentacao_viagem.getOrigem());
        holder.valor.setText(String.valueOf(movimentacao_viagem.getValorFrete()));
        holder.destino.setText(movimentacao_viagem.getDestino());


    }

    @Override
    public int getItemCount() {
        return movimentacoesViagem.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        EditText origem, valor, destino;

        public MyViewHolder(View itemView) {
            super(itemView);

            origem = itemView.findViewById(R.id.textAdapterOrigem);
            valor = itemView.findViewById(R.id.textAdapterValorFrete);
            destino = itemView.findViewById(R.id.textAdapterDestino);
        }

    }

}
