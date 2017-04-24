package com.example.mauriciogodinez.tourguideapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ZonasAdapter extends RecyclerView.Adapter<ZonasAdapter.ViewHolders> {
    private List<ZonasRow> categoriaList;

    final private ZonasAdapter.ListItemClickListener mOnClickListener;

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex, String text);
    }

    @Override
    public ZonasAdapter.ViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        Context vhContext = parent.getContext();
        int layoutIdForListItem = R.layout.card_row_zona;
        LayoutInflater inflater = LayoutInflater.from(vhContext);
        final boolean shouldAttachToParentImmediately = false;

        View itemView = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);

        return new ZonasAdapter.ViewHolders(itemView);
    }

    public ZonasAdapter(List<ZonasRow> categoriaList, ZonasAdapter.ListItemClickListener listener) {
        mOnClickListener = listener;
        this.categoriaList = categoriaList;
    }

    @Override
    public void onBindViewHolder(ZonasAdapter.ViewHolders holder, int position) {
        ZonasRow ci = categoriaList.get(position);
        final String mensaje = ci.getTitleRow();

        holder.vImagenCategoria.setImageResource((ci.getImagenRow()));
        holder.vTituloCategoria.setText(ci.getTitleRow());
        holder.vContenidoCaterogia.setText(ci.getContentRow());
        holder.vNumeroCategoria.setText(ci.getNumberRow().toString());

    }

    @Override
    public int getItemCount() {
        return categoriaList.size();
    }

    public class ViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView vImagenCategoria;
        private TextView vTituloCategoria;
        private TextView vContenidoCaterogia;
        private TextView vNumeroCategoria;

        public ViewHolders(View itemView) {
            super(itemView);

            vImagenCategoria = (ImageView) itemView.findViewById(R.id.image_zona);
            vTituloCategoria = (TextView) itemView.findViewById(R.id.text_view_title_zona);
            vContenidoCaterogia = (TextView) itemView.findViewById(R.id.text_view_content_zona);
            vNumeroCategoria = (TextView) itemView.findViewById(R.id.text_view_numero_zona);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            String text = vTituloCategoria.getText().toString();
            mOnClickListener.onListItemClick(clickedPosition, text);

        }
    }
}
