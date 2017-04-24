package com.example.mauriciogodinez.tourguideapp;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CategoriasActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener, CategoriasAdapter.ListItemClickListener{
    private static final String TAG = "TourGuide";
    private List<CategoriasRow> categorias;

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        categorias = createList();

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_categorias);
        mRecyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(dividerItemDecoration);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        RecyclerView.Adapter mAdapter = new CategoriasAdapter(categorias, this);
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onListItemClick(int itemClicked){
        CategoriasRow fila = categorias.get(itemClicked);
        String zonas = fila.getTitleRow();
        Context c = CategoriasActivity.this;
        Toast.makeText(c, zonas, Toast.LENGTH_SHORT).show();

        Intent i = new Intent(c, ZonasActivity.class);
        i.putExtra("categoria", zonas);
        startActivity(i);
    }

    private List<CategoriasRow> createList() {

        List<CategoriasRow> result = new ArrayList<>();

        result.add(new CategoriasRow(R.drawable.cdmx_1,
                getResources().getString(R.string.titulo_museos),
                getResources().getString(R.string.contenido_museos),
                4));
        result.add(new CategoriasRow(R.drawable.cdmx_2,
                getResources().getString(R.string.titulo_gastronomia),
                getResources().getString(R.string.contenido_gastronomia),
                3));
        result.add(new CategoriasRow(R.drawable.cdmx_1,
                getResources().getString(R.string.titulo_vida_nocturna),
                getResources().getString(R.string.contenido_vida_nocturna),
                5));
        result.add(new CategoriasRow(R.drawable.cdmx_2,
                getResources().getString(R.string.titulo_hoteles),
                getResources().getString(R.string.contenido_hoteles),
                5));
        result.add(new CategoriasRow(R.drawable.cdmx_2,
                getResources().getString(R.string.titulo_festividades),
                getResources().getString(R.string.contenido_festividades),
                4));
        result.add(new CategoriasRow(R.drawable.cdmx_2,
                getResources().getString(R.string.titulo_arquitectura),
                getResources().getString(R.string.contenido_arquitectura),
                2));
        result.add(new CategoriasRow(R.drawable.cdmx_2,
                getResources().getString(R.string.titulo_compras),
                getResources().getString(R.string.contenido_compras),
                3));
        return result;

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.categorias, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_camera:
                // Handle the camera action
                break;
            case R.id.nav_gallery:

                break;
            case R.id.nav_slideshow:

                break;
            case R.id.nav_manage:

                break;
            case R.id.nav_share:

                break;
            case R.id.nav_send:

                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
