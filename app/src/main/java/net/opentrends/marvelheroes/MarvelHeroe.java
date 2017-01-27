package net.opentrends.marvelheroes;

/**
 * Created by sfcar on 27/01/2017.
 */

public class MarvelHeroe {

    private int mId;
    private String mName;
    private String mDescription;
    private String mImage;

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

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }
}


