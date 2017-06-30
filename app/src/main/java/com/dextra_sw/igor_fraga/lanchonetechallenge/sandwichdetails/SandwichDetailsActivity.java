package com.dextra_sw.igor_fraga.lanchonetechallenge.sandwichdetails;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.dextra_sw.igor_fraga.lanchonetechallenge.R;
import com.dextra_sw.igor_fraga.lanchonetechallenge.data.Config;
import com.dextra_sw.igor_fraga.lanchonetechallenge.data.LanchoneteSingleton;
import com.dextra_sw.igor_fraga.lanchonetechallenge.editingredients.EditIngredientsActivity;
import com.dextra_sw.igor_fraga.lanchonetechallenge.model.Sandwich;

public class SandwichDetailsActivity extends AppCompatActivity {

    private static final String TAG = SandwichDetailsActivity.class.getSimpleName();
    private static final int SANDWICH_DETAILS_REQUEST_CODE = 10;

    Sandwich sandwichDetails;
    SandwichDetailsPresenter presenter;

    private NetworkImageView picture;
    private TextView mealName;
    private TextView ingredients;
    private TextView mealPrice;
    private Button changeIngredients;
    private Button addToCart;

    int editedTimes = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sandwich_details);

        sandwichDetails = new Sandwich();
        presenter = new SandwichDetailsPresenterImpl(this);

        Intent parentIntent = getIntent();
        sandwichDetails = parentIntent.getParcelableExtra("selectedSandwich");

        mealName = (TextView) findViewById(R.id.details_meal_name);
        ingredients = (TextView) findViewById(R.id.details_ingredients);
        mealPrice = (TextView) findViewById(R.id.details_meal_price);
        picture = (NetworkImageView) findViewById(R.id.details_photo);
        changeIngredients = (Button) findViewById(R.id.btn_details_change_ingredients);
        addToCart = (Button) findViewById(R.id.btn_details_add_to_cart);

        changeIngredients.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent editIngredients = new Intent(SandwichDetailsActivity.this, EditIngredientsActivity.class);
                editIngredients.putExtra("sandwichDetails", sandwichDetails);
                startActivityForResult(editIngredients, SANDWICH_DETAILS_REQUEST_CODE);
            }
        });

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Enviar requisição PUT para o servidor com os dados do pedido
                presenter.addToCart(Config.ORDERS_URL + sandwichDetails.getId(), sandwichDetails);
                //TODO Atualizar tela/objeto do carrinho
            }
        });

        populateFields();
    }

    private void populateFields() {
        if(editedTimes == 0) {
            ImageLoader imageLoader = LanchoneteSingleton.getInstance(this).getImageLoader();
            String imageUrl = sandwichDetails.getImageUrl();
            picture.setImageUrl(imageUrl, imageLoader);
            mealName.setText(sandwichDetails.getName());
        }

        mealPrice.setText(this.getString(R.string.format_price, sandwichDetails.getPrice()));
        ingredients.setText(sandwichDetails.getIngredientsListed());
    }

    private void customizedSandwichTitle() {
        mealName.setText(mealName.getText() + " " + getString(R.string.special_for_you));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Sandwich receivedSandwich = data.getParcelableExtra("sandwichDetailsPersonalized");
        boolean hasBeenEdited = data.getBooleanExtra("hasAddedIngredient", false);

        if(hasBeenEdited) {
            sandwichDetails = presenter.calculateNewSandwichValues(receivedSandwich);
            //update title to custom sandwich
            populateFields();
            if (editedTimes == 0) {
                customizedSandwichTitle();
                editedTimes++;
            }
        }
    }
}
