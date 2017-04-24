package com.example.mauriciogodinez.tourguideapp;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ZonasActivity extends AppCompatActivity implements ZonasAdapter.ListItemClickListener{
    private static final String TAG = "TourGuide";
    private Toast mToast;

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zonas);
        Intent categorias = getIntent();
        String zonas = categorias.getStringExtra("categorias");

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.container_condesa);
        mRecyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(mRecyclerView);

        RecyclerView.Adapter mAdapter = new ZonasAdapter(createListCondesa(), this);
        mRecyclerView.setAdapter(mAdapter);



        RecyclerView mRecyclerView2 = (RecyclerView) findViewById(R.id.container_polanco);
        mRecyclerView2.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView2.setLayoutManager(mLayoutManager2);

        SnapHelper snapHelper2 = new LinearSnapHelper();
        snapHelper2.attachToRecyclerView(mRecyclerView2);

        RecyclerView.Adapter mAdapter2 = new ZonasAdapter(createListPolanco(), this);
        mRecyclerView2.setAdapter(mAdapter2);



        RecyclerView mRecyclerView3 = (RecyclerView) findViewById(R.id.container_centro_historico);
        mRecyclerView3.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager3 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView3.setLayoutManager(mLayoutManager3);

        SnapHelper snapHelper3 = new LinearSnapHelper();
        snapHelper3.attachToRecyclerView(mRecyclerView3);

        RecyclerView.Adapter mAdapter3 = new ZonasAdapter(createListCentro(), this);
        mRecyclerView3.setAdapter(mAdapter3);



        RecyclerView mRecyclerView4 = (RecyclerView) findViewById(R.id.container_roma);
        mRecyclerView4.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager4 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView4.setLayoutManager(mLayoutManager4);

        SnapHelper snapHelper4 = new LinearSnapHelper();
        snapHelper4.attachToRecyclerView(mRecyclerView4);

        RecyclerView.Adapter mAdapter4 = new ZonasAdapter(createListRoma(), this);
        mRecyclerView4.setAdapter(mAdapter4);



        RecyclerView mRecyclerView5 = (RecyclerView) findViewById(R.id.container_zona_rosa_reforma);
        mRecyclerView5.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager5 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView5.setLayoutManager(mLayoutManager5);

        SnapHelper snapHelper5 = new LinearSnapHelper();
        snapHelper5.attachToRecyclerView(mRecyclerView5);

        RecyclerView.Adapter mAdapter5 = new ZonasAdapter(createListReforma(), this);
        mRecyclerView5.setAdapter(mAdapter5);



        RecyclerView mRecyclerView6 = (RecyclerView) findViewById(R.id.container_sur);
        mRecyclerView6.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager6 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView6.setLayoutManager(mLayoutManager6);

        SnapHelper snapHelper6 = new LinearSnapHelper();
        snapHelper6.attachToRecyclerView(mRecyclerView6);

        RecyclerView.Adapter mAdapter6 = new ZonasAdapter(createListSur(), this);
        mRecyclerView6.setAdapter(mAdapter6);

    }

    private List<ZonasRow> createListCentro() {

        List<ZonasRow> result = new ArrayList<>();

        result.add(new ZonasRow(R.drawable.cdmx_1,
                getResources().getString(R.string.museo_centro_alameda),
                getResources().getString(R.string.contenido_museos),
                4));
        result.add(new ZonasRow(R.drawable.cdmx_2,
                getResources().getString(R.string.museo_centro_bellas_artes),
                getResources().getString(R.string.contenido_gastronomia),
                3));
        result.add(new ZonasRow(R.drawable.cdmx_1,
                getResources().getString(R.string.museo_centro_casa_ahuizotle),
                getResources().getString(R.string.contenido_vida_nocturna),
                5));
        result.add(new ZonasRow(R.drawable.cdmx_2,
                getResources().getString(R.string.museo_centro_chopo),
                getResources().getString(R.string.contenido_hoteles),
                5));
        result.add(new ZonasRow(R.drawable.cdmx_2,
                getResources().getString(R.string.museo_centro_españa),
                getResources().getString(R.string.contenido_festividades),
                4));
        result.add(new ZonasRow(R.drawable.cdmx_2,
                getResources().getString(R.string.museo_centro_franz_mayer),
                getResources().getString(R.string.contenido_arquitectura),
                2));
        result.add(new ZonasRow(R.drawable.cdmx_2,
                getResources().getString(R.string.museo_centro_map),
                getResources().getString(R.string.contenido_compras),
                3));
        result.add(new ZonasRow(R.drawable.cdmx_2,
                getResources().getString(R.string.museo_centro_numismatico),
                getResources().getString(R.string.contenido_compras),
                3));
        result.add(new ZonasRow(R.drawable.cdmx_2,
                getResources().getString(R.string.museo_centro_san_ildefonso),
                getResources().getString(R.string.contenido_compras),
                3));
        result.add(new ZonasRow(R.drawable.cdmx_2,
                getResources().getString(R.string.museo_centro_tolerancia),
                getResources().getString(R.string.contenido_compras),
                3));
        return result;

    }

    private List<ZonasRow> createListRoma() {

        List<ZonasRow> result = new ArrayList<>();

        result.add(new ZonasRow(R.drawable.cdmx_1,
                getResources().getString(R.string.museo_roma_juguete_antiguo),
                getResources().getString(R.string.contenido_museos),
                4));
        result.add(new ZonasRow(R.drawable.cdmx_2,
                getResources().getString(R.string.museo_roma_lamm),
                getResources().getString(R.string.contenido_gastronomia),
                3));
        result.add(new ZonasRow(R.drawable.cdmx_1,
                getResources().getString(R.string.museo_roma_modo),
                getResources().getString(R.string.contenido_vida_nocturna),
                5));
        result.add(new ZonasRow(R.drawable.cdmx_2,
                getResources().getString(R.string.museo_roma_monclova),
                getResources().getString(R.string.contenido_hoteles),
                5));
        result.add(new ZonasRow(R.drawable.cdmx_2,
                getResources().getString(R.string.museo_roma_muca),
                getResources().getString(R.string.contenido_festividades),
                4));
        result.add(new ZonasRow(R.drawable.cdmx_2,
                getResources().getString(R.string.museo_roma_omr),
                getResources().getString(R.string.contenido_arquitectura),
                2));

        return result;

    }

    private List<ZonasRow> createListCondesa() {

        List<ZonasRow> result = new ArrayList<>();

        result.add(new ZonasRow(R.drawable.cdmx_1,
                getResources().getString(R.string.museo_condesa_barragan),
                getResources().getString(R.string.contenido_museos),
                4));
        result.add(new ZonasRow(R.drawable.cdmx_2,
                getResources().getString(R.string.museo_condesa_gam),
                getResources().getString(R.string.contenido_gastronomia),
                3));
        result.add(new ZonasRow(R.drawable.cdmx_1,
                getResources().getString(R.string.museo_condesa_kurimanzutto),
                getResources().getString(R.string.contenido_vida_nocturna),
                5));
        result.add(new ZonasRow(R.drawable.cdmx_2,
                getResources().getString(R.string.museo_condesa_labor),
                getResources().getString(R.string.contenido_hoteles),
                5));

        return result;

    }

    private List<ZonasRow> createListReforma() {

        List<ZonasRow> result = new ArrayList<>();

        result.add(new ZonasRow(R.drawable.cdmx_1,
                getResources().getString(R.string.museo_reforma_antropologia),
                getResources().getString(R.string.contenido_museos),
                4));
        result.add(new ZonasRow(R.drawable.cdmx_2,
                getResources().getString(R.string.museo_reforma_arredondo),
                getResources().getString(R.string.contenido_gastronomia),
                3));
        result.add(new ZonasRow(R.drawable.cdmx_1,
                getResources().getString(R.string.museo_reforma_contemporaneo),
                getResources().getString(R.string.contenido_vida_nocturna),
                5));
        result.add(new ZonasRow(R.drawable.cdmx_2,
                getResources().getString(R.string.museo_reforma_eco),
                getResources().getString(R.string.contenido_hoteles),
                5));
        result.add(new ZonasRow(R.drawable.cdmx_2,
                getResources().getString(R.string.museo_reforma_mam),
                getResources().getString(R.string.contenido_festividades),
                4));
        result.add(new ZonasRow(R.drawable.cdmx_2,
                getResources().getString(R.string.museo_reforma_patricia),
                getResources().getString(R.string.contenido_arquitectura),
                2));

        return result;

    }

    private List<ZonasRow> createListPolanco() {

        List<ZonasRow> result = new ArrayList<>();

        result.add(new ZonasRow(R.drawable.cdmx_1,
                getResources().getString(R.string.museo_polanco_jumex),
                getResources().getString(R.string.contenido_museos),
                4));
        result.add(new ZonasRow(R.drawable.cdmx_2,
                getResources().getString(R.string.museo_polanco_saps),
                getResources().getString(R.string.contenido_gastronomia),
                3));
        result.add(new ZonasRow(R.drawable.cdmx_1,
                getResources().getString(R.string.museo_polanco_soumaya),
                getResources().getString(R.string.contenido_vida_nocturna),
                5));

        return result;

    }

    private List<ZonasRow> createListSur() {

        List<ZonasRow> result = new ArrayList<>();

        result.add(new ZonasRow(R.drawable.cdmx_1,
                getResources().getString(R.string.museo_sur_anahuacalli),
                getResources().getString(R.string.contenido_museos),
                4));
        result.add(new ZonasRow(R.drawable.cdmx_2,
                getResources().getString(R.string.museo_sur_carrillo_gil),
                getResources().getString(R.string.contenido_gastronomia),
                3));
        result.add(new ZonasRow(R.drawable.cdmx_1,
                getResources().getString(R.string.museo_sur_estudio),
                getResources().getString(R.string.contenido_vida_nocturna),
                5));
        result.add(new ZonasRow(R.drawable.cdmx_2,
                getResources().getString(R.string.museo_sur_olmedo),
                getResources().getString(R.string.contenido_hoteles),
                5));
        result.add(new ZonasRow(R.drawable.cdmx_2,
                getResources().getString(R.string.museo_sur_soma),
                getResources().getString(R.string.contenido_festividades),
                4));
        result.add(new ZonasRow(R.drawable.cdmx_2,
                getResources().getString(R.string.museo_sur_universitario),
                getResources().getString(R.string.contenido_arquitectura),
                2));

        return result;

    }


    @Override
    public void onListItemClick(int clickedItemIndex, String text) {
        Context vhContext = getApplicationContext();
        if(mToast != null){
            mToast.cancel();
        }

        mToast = Toast.makeText(vhContext, "click imagen " + text, Toast.LENGTH_SHORT);
        mToast.show();

        Intent intent = new Intent(vhContext, UbicacionActivity.class);
        intent.putExtra("ubicacion", text);
        startActivity(intent);
    }
}

