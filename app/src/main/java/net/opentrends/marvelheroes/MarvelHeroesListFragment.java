package net.opentrends.marvelheroes;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MarvelHeroesListFragment extends Fragment {
    //Fragment para el listado de heroes
    private EditText mLookingForEditText;

    private RecyclerView mMarvelHeroeRecyclerView;
    private MarvelHeroeAdapter mMarvelHeroeAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_marvel_heroes_list, container, false); //Inflamos el fragment

        mLookingForEditText = (EditText) view.findViewById(R.id.looking_for_name);
        mLookingForEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //if(s.length() >= 3){
                    mMarvelHeroeAdapter.getFilter().filter(s.toString());
                //} else {

                //}
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mMarvelHeroeRecyclerView = (RecyclerView) view.findViewById(R.id.heroe_recycler_view); //Cargamos el RecyclerView
        mMarvelHeroeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity())); //Configuramos el LayoutManager de manera Vertical

        updateUI();

        return view;
    }

    private void updateUI(){
        MarvelHeroeSingleton marvelHeroeSingleton = MarvelHeroeSingleton.getInstance(getActivity()); //Cargamos una instancia del Singleton
        List<MarvelHeroe> marvelHeroes = marvelHeroeSingleton.getMarvelHeroes(); //Cargamos los heroes sobre el List

        mMarvelHeroeAdapter = new MarvelHeroeAdapter(marvelHeroes); //Cargamos una nueva instancia del adaptador con el list
        mMarvelHeroeRecyclerView.setAdapter(mMarvelHeroeAdapter); //Modificamos el RecyclerView añadiendo el adapter
    }

    private class MarvelHeroeHolder extends RecyclerView.ViewHolder implements View.OnClickListener{//Creamos el ViewHolder

        private CheckBox mMHImageView; //Cambiar luego!!!!!!
        private TextView mNameTextView;
        private TextView mDescriptionTextView;

        private MarvelHeroe mMarvelHeroe;

        public MarvelHeroeHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);//Configuramos MarvelHeroeHolder como receptor de los eventos táctiles

            mMHImageView = (CheckBox) itemView.findViewById(R.id.list_item_marvel_heroe_image_view);
            mNameTextView = (TextView) itemView.findViewById(R.id.list_item_marvel_heroe_name_text_view);
            mDescriptionTextView = (TextView) itemView.findViewById(R.id.list_item_marvel_heroe_description_text_view); //Conectamos los widgets

        }

        public void bindMarvelHeroe(MarvelHeroe marvelHeroe){ //Bindeamos cada objeto de Heroe con la vista
            mMarvelHeroe = marvelHeroe;
            mMHImageView.setChecked(marvelHeroe.getImage()); //CAMBIAR LUEGO!!
            mNameTextView.setText(mMarvelHeroe.getName());
            mDescriptionTextView.setText(mMarvelHeroe.getDescription());
        }

        @Override
        public void onClick(View v) {
            Intent intent = MarvelHeroeActivity.newIntent(getActivity(), mMarvelHeroe.getId()); //Llamamos al método newIntent para generar un intent explicito pasandole el contexto y el ID del Heroe
            startActivity(intent); //Abre una nueva MarvelHeroeActivity

        }

    }

    private class MarvelHeroeAdapter extends RecyclerView.Adapter<MarvelHeroeHolder> implements Filterable{ //Implementamos Filterable para hacer la búsqueda del heroe

        private List<MarvelHeroe> mMarvelHeroes;
        private List<MarvelHeroe> mMarvelHeroesFilter; //List filtrado
        private CustomFilter mCustomFilter;

        public MarvelHeroeAdapter(List<MarvelHeroe> marvelHeroes) {
            mMarvelHeroes = marvelHeroes;
            mMarvelHeroesFilter = new ArrayList<>(); //Creamos un nuevo list de heroes filtrados
            mMarvelHeroesFilter.addAll(marvelHeroes); //Los añadimos
            mCustomFilter = new CustomFilter(mMarvelHeroeAdapter);
        }

        @Override
        public MarvelHeroeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity()); //Cargamos el "inflador, es decir la activity
            View view = layoutInflater.inflate(R.layout.list_item_marvel_heroe, parent, false); //Creamos una vista. Desplegamos un layout que contiene una sola vista
            return new MarvelHeroeHolder(view); //Devolvemos la vista encapsulada en el ViewHolder
        }

        @Override
        public void onBindViewHolder(MarvelHeroeHolder holder, int position) {
            MarvelHeroe marvelHeroe = mMarvelHeroesFilter.get(position); //Guarda en marvelHeroe el heroe del listado respecto a la posición que le pasamos
            holder.bindMarvelHeroe(marvelHeroe); //Enlazamos una vista de un ViewHolder a un objeto de heroe

        }

        @Override
        public int getItemCount() {
            return mMarvelHeroesFilter.size(); //Devuelve el tamaño de la lista (filtrada o no)
        }

        @Override
        public Filter getFilter() {
            return mCustomFilter;
        }
    }

    public class CustomFilter extends Filter{

        private MarvelHeroeAdapter mMarvelHeroeAdapter;


        private CustomFilter(MarvelHeroeAdapter marvelHeroeAdapter) {
            super();
            mMarvelHeroeAdapter = marvelHeroeAdapter;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            mMarvelHeroeAdapter.mMarvelHeroesFilter.clear();
            final FilterResults results = new FilterResults();
            if(constraint.length() < 3){
                mMarvelHeroeAdapter.mMarvelHeroesFilter.addAll(mMarvelHeroeAdapter.mMarvelHeroes);
            } else {
                final String filterPattern = constraint.toString().toLowerCase().trim();
                for (final MarvelHeroe marvelHeroe : mMarvelHeroeAdapter.mMarvelHeroes){
                    if(marvelHeroe.getName().toLowerCase().contains(filterPattern)){
                        mMarvelHeroeAdapter.mMarvelHeroesFilter.add(marvelHeroe);
                    }
                }
            }
            results.values = mMarvelHeroeAdapter.mMarvelHeroesFilter;
            results.count = mMarvelHeroeAdapter.mMarvelHeroesFilter.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            MarvelHeroeSingleton marvelHeroeSingleton = MarvelHeroeSingleton.getInstance(getActivity());
            List<MarvelHeroe> marvelHeroes = marvelHeroeSingleton.getMarvelHeroes();

            if (mMarvelHeroeAdapter == null){
                mMarvelHeroeAdapter = new MarvelHeroeAdapter(marvelHeroes);
                mMarvelHeroeRecyclerView.setAdapter(mMarvelHeroeAdapter);
            }
            mMarvelHeroeAdapter.notifyDataSetChanged(); //Notifica los cambios que se hayan producido.

        }
    }



}


