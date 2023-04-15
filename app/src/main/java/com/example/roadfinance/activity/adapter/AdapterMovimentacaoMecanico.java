package com.example.roadfinance.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.roadfinance.R;
import com.example.roadfinance.activity.model.MovimentacaoMecanico;

import java.util.List;

public class AdapterMovimentacaoMecanico extends RecyclerView.Adapter<AdapterMovimentacaoMecanico.MyViewHolder> {

    List<MovimentacaoMecanico> movimentacoesMecanico;
    Context context;

    public AdapterMovimentacaoMecanico(List<MovimentacaoMecanico> movimentacoesMecanico, Context context) {
        this.movimentacoesMecanico = movimentacoesMecanico;
        this.context = context;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_movimentacao_mecanico,
                parent, false);
        return new MyViewHolder(itemLista);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        MovimentacaoMecanico movimentacao_mecanico = movimentacoesMecanico.get(position);

        holder.placa.setText(movimentacao_mecanico.getPlaca());
        holder.valor.setText(String.valueOf(movimentacao_mecanico.getValor()));
        holder.tipo.setText(movimentacao_mecanico.getTipo());
    }


    @Override
    public int getItemCount() {
        return movimentacoesMecanico.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView placa, valor, tipo;

        public MyViewHolder(View itemView) {
            super(itemView);

            placa = itemView.findViewById(R.id.textAdapterPlaca);
            valor = itemView.findViewById(R.id.textAdapterValor);
            tipo = itemView.findViewById(R.id.textAdapterTipo);
        }

    }

}
