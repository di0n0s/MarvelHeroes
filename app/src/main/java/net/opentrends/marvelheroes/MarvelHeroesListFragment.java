package net.opentrends.marvelheroes;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MarvelHeroesListFragment extends Fragment {
    //Fragment para el listado de heroes

    private RecyclerView mMarvelHeroeRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_marvel_heroes_list, container, false); //Inflamos el fragment

        mMarvelHeroeRecyclerView = (RecyclerView) view.findViewById(R.id.heroe_recycler_view); //Cargamos el RecyclerView
        mMarvelHeroeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity())); //Configuramos el LayoutManager de manera Vertical



        return inflater.inflate(R.layout.fragment_marvel_heroes_list, container, false);
    }

    private void updateUI(){
        MarvelHeroeSingleton marvelHeroeSingleton = MarvelHeroeSingleton.getInstance(getActivity()); //Cargamos una instancia del Singleton
        List<MarvelHeroe> marvelHeroes = marvelHeroeSingleton.getMarvelHeroes(); //Cargamos los heroes sobre el List

    }

    private class MarvelHeroeHolder extends RecyclerView.ViewHolder implements View.OnClickListener{//Creamos el ViewHolder

        private ImageView mMHImageView;
        private TextView mNameTextView;
        private TextView mDescriptionTextView;

        private MarvelHeroe mMarvelHeroe;

        public MarvelHeroeHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);//Configuramos MarvelHeroeHolder como receptor de los eventos táctiles

            mMHImageView = (ImageView) itemView.findViewById(R.id.list_item_marvel_heroe_image_view);
            mNameTextView = (TextView) itemView.findViewById(R.id.list_item_marvel_heroe_name_text_view);
            mDescriptionTextView = (TextView) itemView.findViewById(R.id.list_item_marvel_heroe_description_text_view); //Conectamos los widgets

        }

        public void bindMarvelHeroe(MarvelHeroe marvelHeroe){ //Bindeamos cada objeto de Heroe con la vista
            mMarvelHeroe = marvelHeroe;
            //mMHImageView
            mNameTextView.setText(mMarvelHeroe.getName());
            mDescriptionTextView.setText(mMarvelHeroe.getDescription());

        }

        @Override
        public void onClick(View v) {

        }



    }



}


