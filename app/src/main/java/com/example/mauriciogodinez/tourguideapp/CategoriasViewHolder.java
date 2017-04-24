package com.example.mauriciogodinez.tourguideapp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import static com.example.mauriciogodinez.tourguideapp.CategoriasAdapter.mOnClickListener;

public class CategoriasViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    protected ImageView vImagenCategoria;
    protected TextView vTituloCategoria;
    protected TextView vContenidoCaterogia;
    protected TextView vNumeroCategoria;

    public CategoriasViewHolder(View itemView) {
        super(itemView);

        vImagenCategoria = (ImageView) itemView.findViewById(R.id.image_row);
        vTituloCategoria = (TextView) itemView.findViewById(R.id.text_view_title_row);
        vContenidoCaterogia = (TextView) itemView.findViewById(R.id.text_view_content_row);
        vNumeroCategoria = (TextView) itemView.findViewById(R.id.text_view_number_stars_row);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int clickedPosition = getAdapterPosition();
        mOnClickListener.onListItemClick(clickedPosition);
    }
}




