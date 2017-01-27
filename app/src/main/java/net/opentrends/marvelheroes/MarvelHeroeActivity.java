package net.opentrends.marvelheroes;

import android.support.v4.app.Fragment;

/**
 * Created by sfcar on 27/01/2017.
 */

public class MarvelHeroeActivity extends SingleFragmentActivity {

    private static final String EXTRA_MARVELHEROE_ID = "net.opentrends.marvelheroes.marvelheroe_id"; //Clave del Extra

    @Override
    protected Fragment createFragment() {

        int marvelHeroeId = (int) getIntent().getSerializableExtra(EXTRA_MARVELHEROE_ID);
        

        return null;
    }
}
