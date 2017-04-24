package com.example.mauriciogodinez.tourguideapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;


public class CategoriasAdapter extends RecyclerView.Adapter<CategoriasViewHolder> {

    private List<CategoriasRow> categoriaList;
    private Context vhContext;
    private CategoriasRow ci;
    private Toast mToast;

    static public ListItemClickListener mOnClickListener;

    public interface ListItemClickListener{
        void onListItemClick(int clickedItemIndex);
    }

    @Override
    public CategoriasViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        vhContext = viewGroup.getContext();
        int layoutIdForListItem = R.layout.categoria_row;
        LayoutInflater inflater = LayoutInflater.from(vhContext);
        final boolean shouldAttachToParentImmediately = false;

        View itemView = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);

        return new CategoriasViewHolder(itemView);
    }

    public CategoriasAdapter(List<CategoriasRow> categoriaList, ListItemClickListener listener) {
        mOnClickListener = listener;
        this.categoriaList = categoriaList;
    }

    @Override
    public int getItemCount() {
        return categoriaList.size();
    }

    @Override
    public void onBindViewHolder(final CategoriasViewHolder contactViewHolder, int position) {
        ci = categoriaList.get(position);
        final String mensaje = ci.getTitleRow();
        final Integer stars = ci.getNumberRow();

        contactViewHolder.vImagenCategoria.setImageResource((ci.getImagenRow()));
        contactViewHolder.vTituloCategoria.setText(ci.getTitleRow());
        contactViewHolder.vContenidoCaterogia.setText(ci.getContentRow());
        contactViewHolder.vNumeroCategoria.setText(ci.getNumberRow().toString());

        contactViewHolder.vImagenCategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mToast != null){
                    mToast.cancel();
                }
                mToast = Toast.makeText(vhContext, "click imagen " + mensaje, Toast.LENGTH_SHORT);
                mToast.show();
//                Intent i = new Intent(vhContext, ZonasActivity.class);
//                vhContext.startActivity(i);
            }
        });

        contactViewHolder.vNumeroCategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mToast != null){
                    mToast.cancel();
                }
                ci.setNumberRow(stars + 1);
                contactViewHolder.vNumeroCategoria.setText(ci.getNumberRow().toString());
                mToast = Toast.makeText(vhContext, "click estrellas " + mensaje + ci.getNumberRow(), Toast.LENGTH_SHORT);
                mToast.show();
            }
        });

    }
}