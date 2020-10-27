package com.uac.registroedificio;

import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class VisitanteAdapter extends RecyclerView.Adapter<VisitanteAdapter.VisitanteViewHolder> implements Filterable{

    private List<Visitante> listaVisitantes;
    private VisitanteListener vl;
    private List<Visitante> listaTodosVisitantes;


    public VisitanteAdapter(List<Visitante> listaVisitantes, VisitanteListener vl) {
        this.vl = vl;
        this.listaVisitantes = listaVisitantes;
        listaTodosVisitantes = new ArrayList<>();
        listaTodosVisitantes.addAll(listaVisitantes);
    }

    @NonNull
    @Override
    public VisitanteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.visitante_item, parent, false);
        return new VisitanteViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull VisitanteViewHolder holder, int position) {

        Visitante v = listaVisitantes.get(position);

        holder.textViewNombre.setText(v.getNombre());
        holder.textViewApt.setText(v.getApartamento());
        holder.textViewDate.setText(v.getFechaHora());
    }

    @Override
    public int getItemCount() {
        return listaVisitantes.size();
    }


    @Override
    public Filter getFilter() {
        return filtro;
    }

    Filter filtro = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Visitante> listaFiltrada = new ArrayList<>();

            if(constraint == null) {
                listaFiltrada.addAll(listaTodosVisitantes);
            } else {
                for (Visitante v : listaTodosVisitantes) {
                    if(v.getApartamento().contains(constraint.toString())) {
                        listaFiltrada.add(v);
                    }
                }
            }

            FilterResults fr = new FilterResults();
            fr.values = listaFiltrada;
            return fr;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            listaVisitantes.clear();
            listaVisitantes.addAll((Collection<? extends Visitante>) results.values);
            notifyDataSetChanged();
        }
    };


    public class VisitanteViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {

        public CardView visitItem;
        public TextView textViewNombre;
        public TextView textViewApt;
        public TextView textViewDate;

        public VisitanteViewHolder(View view){
            super(view);
            visitItem = view.findViewById(R.id.VisitItemCard);
            textViewNombre = view.findViewById(R.id.nombreItemTxt);
            textViewApt = view.findViewById(R.id.aptItemText);
            textViewDate = view.findViewById(R.id.fechaHoraItemTxt);

            visitItem.setOnCreateContextMenuListener(this);
        }



        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.add(Menu.NONE, 1, 0, "Editar");
            menu.add(Menu.NONE, 2, 1, "Eliminar");

            vl.itemSeleccionado(listaVisitantes.get(getLayoutPosition()));
        }
    }

    public void actualizarLista(ArrayList<Visitante> newList) {
        Log.i("NUEVA LISTA", newList.toString());

        listaVisitantes = new ArrayList<>();
        listaVisitantes.addAll(newList);


        notifyDataSetChanged();
    }


    public void eliminaVisitante(Visitante v) {
        this.listaVisitantes.remove(v);
        notifyDataSetChanged();
    }
}
