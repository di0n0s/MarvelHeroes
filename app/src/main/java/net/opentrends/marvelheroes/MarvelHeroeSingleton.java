package net.opentrends.marvelheroes;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sfcar on 27/01/2017.
 */
public class MarvelHeroeSingleton {
    //Almacenamos la lista de heroes en un singleton

    private static MarvelHeroeSingleton sMarvelHeroeSingleton;
    private List<MarvelHeroe> mMarvelHeroes;

    public static MarvelHeroeSingleton getInstance(Context context) {//Context para la BBDD
        if(sMarvelHeroeSingleton==null){
            sMarvelHeroeSingleton = new MarvelHeroeSingleton(context); //Si no hay una instancia la crea
        }
        return sMarvelHeroeSingleton;
    }

    private MarvelHeroeSingleton(Context context) { //Constructor privado, solo se puede acceder desde getInstance
        mMarvelHeroes = new ArrayList<>();
        for(int i = 0; i < 100; i++){
            MarvelHeroe mh = new MarvelHeroe();
            mh.setName("MarvelHeroe #"+ i);
            mh.setDescription("Description #"+ i);
            mh.setImage(true);
            mMarvelHeroes.add(mh);
        }
    }

    public List<MarvelHeroe> getMarvelHeroes() { //Acceder al listado de Heroes
        return mMarvelHeroes;
    }

    public MarvelHeroe getMarvelHeroe(int id){ //Devuelve los heroe con el ID indicado
        for(MarvelHeroe marvelHeroe : mMarvelHeroes){
            if(marvelHeroe.getId() == id){
                return marvelHeroe;
            }
        } return null;
    }

}
