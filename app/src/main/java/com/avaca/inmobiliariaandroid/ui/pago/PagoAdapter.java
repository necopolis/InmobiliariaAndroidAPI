package com.avaca.inmobiliariaandroid.ui.pago;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.avaca.inmobiliariaandroid.R;
import com.avaca.inmobiliariaandroid.modelo.Pago;
import com.avaca.inmobiliariaandroid.request.ApiClient;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class PagoAdapter extends RecyclerView.Adapter<PagoAdapter.MiViewHolder> {

    private LayoutInflater Inflater;
    private Context context;
    private ArrayList<Pago> pagosList;
    private View root;
    private ApiClient api;

    public PagoAdapter(View root, ArrayList<Pago> pagos) {
        this.root = root;
        this.Inflater = LayoutInflater.from(root.getContext());
        this.context = root.getContext();
        this.pagosList = pagos;
        //this.api = ApiClient.getApi();
    }

    @NonNull
    @Override
    public PagoAdapter.MiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = Inflater.inflate(R.layout.item_pago, parent, false);
        return new PagoAdapter.MiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PagoAdapter.MiViewHolder holder, int position) {
        Pago pago = pagosList.get(position);

        //DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        //LocalDate fecha=LocalDate.parse(pago.getFechaPago(), formatter);
        String[] aux=pago.getFechaPago().split("T");
        holder.codigo.setText("Codigo de Pago: "+pago.getId());
        holder.numerodePago.setText("Número de Pago: "+pago.getNumeroPago());
        holder.codigoContrato.setText("Código Contrato: "+pago.getContratoId());
        holder.Monto.setText("Importe: "+pago.getImporte());
        holder.fechaPago.setText("Fecha de Pago: "+aux[0].toString());
    }

    @Override
    public int getItemCount() {
        return pagosList.size();
    }

    public class MiViewHolder extends RecyclerView.ViewHolder {
        private TextView codigo;
        private TextView numerodePago;
        private TextView codigoContrato;
        private TextView Monto;
        private TextView fechaPago;


        public MiViewHolder(@NonNull View itemView) {
            super(itemView);
            codigo = itemView.findViewById(R.id.tvCodigoPagoDetalles);
            numerodePago = itemView.findViewById(R.id.tvNumPagoDetalles);
            codigoContrato = itemView.findViewById(R.id.tvCodigoContratoPagoDetalles);
            Monto = itemView.findViewById(R.id.tvImportePagoDetalles);
            fechaPago = itemView.findViewById(R.id.tvFechaDePagoDetalles);
        }
    }


}
