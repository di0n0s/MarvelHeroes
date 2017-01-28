package net.opentrends.marvelheroes;

import android.graphics.Bitmap;

/**
 * Created by sfcar on 27/01/2017.
 */

public class MarvelHeroe {

    private int mId;
    private String mName;
    private String mDescription;
    private boolean mImage; //Hay que cambiarlo luego

    public MarvelHeroe() {
    }

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public boolean getImage() {
        return mImage;
    } //Hay que cambiarlo luego

    public void setImage(boolean image) {
        mImage = image;
    } //Hay que cambiarlo luego
}


