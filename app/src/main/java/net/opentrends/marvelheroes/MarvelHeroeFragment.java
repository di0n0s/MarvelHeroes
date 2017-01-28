package net.opentrends.marvelheroes;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class MarvelHeroeFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_MARVELHEROE_ID = "marvel_heroe_id"; //Clave del argumento del Fragment¿¿?¿?

    private MarvelHeroe mMarvelHeroe;
    private CheckBox mMHImageView; //Cambiar luego
    private TextView mMHNameTextView;
    private TextView mMHDescriptionTextView;
    private Button mDetailButton;
    private Button mWikiButton;
    private Button mComicsButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int marvelHeroId = (int) getArguments().getSerializable(ARG_MARVELHEROE_ID);//Recuperamos los argumentos del fragment
        mMarvelHeroe = MarvelHeroeSingleton.getInstance(getActivity()).getMarvelHeroe(marvelHeroId); //Recuperado, lo utilizamos para buscar el Crime en el Singleton

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_marvel_heroe, container, false); //false --> no se va a añadir la vista despleagada a la del padre, sino al código del Activity
        //Inflamos la vista

        mMHImageView = (CheckBox) view.findViewById(R.id.marvel_heroe_image_view);//Conectamos los widgets CAMBIAR LUEGO!!!!!
        mMHImageView.setChecked(mMarvelHeroe.getImage()); //CAMBIAR LUEGGGOOOOOOO!!!!

        mMHNameTextView = (TextView) view.findViewById(R.id.marvel_heroe_name_text_view);
        mMHNameTextView.setText(mMarvelHeroe.getName());//Cargas el nombre del Heroe

        mMHDescriptionTextView = (TextView) view.findViewById(R.id.marvel_heroe_description_text_view);
        mMHDescriptionTextView.setText(mMarvelHeroe.getDescription());

        mDetailButton = (Button) view.findViewById(R.id.detail_button);
        mWikiButton = (Button) view.findViewById(R.id.wiki_button);
        mComicsButton = (Button) view.findViewById(R.id.comics_button);


        return view;
    }

    @Override
    public void onClick(View v) {
        String message = "Hola";
        switch (v.getId()) {
            case R.id.detail_button:
                Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                break;
            case R.id.wiki_button:
                Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                break;
            case R.id.comics_button:
                Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                break;
        }

    }

    public static MarvelHeroeFragment newInstance(int marvelHeroId){
        Bundle args = new Bundle();
        args.putInt(ARG_MARVELHEROE_ID, marvelHeroId); //Creamos un conjunto de argumentos

        MarvelHeroeFragment fragment = new MarvelHeroeFragment(); //Creamos una instancia del fragment
        fragment.setArguments(args); //Añadimos los argumentos
        return fragment; //Devolvemos el fragment

    }
}
