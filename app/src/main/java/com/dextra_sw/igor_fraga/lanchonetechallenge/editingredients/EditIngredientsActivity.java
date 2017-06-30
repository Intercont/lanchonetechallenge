package com.dextra_sw.igor_fraga.lanchonetechallenge.editingredients;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.dextra_sw.igor_fraga.lanchonetechallenge.R;
import com.dextra_sw.igor_fraga.lanchonetechallenge.model.Ingredient;
import com.dextra_sw.igor_fraga.lanchonetechallenge.model.Sandwich;
import com.dextra_sw.igor_fraga.lanchonetechallenge.selectingredientslist.SelectIngredientsActivity;

import java.util.ArrayList;

public class EditIngredientsActivity extends AppCompatActivity {

    Sandwich sandwichDetails;

    private static final int INGREDIENTS_SELECTION_REQUEST_CODE = 43;
    EditIngredientsAdapter mEditIngredientsAdapter;
    RecyclerView mRecyclerView;
    private EditIngredientsPresenter presenter;
    boolean hasAddedIngredient;

    Button btnSaveEditIngredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_ingredients);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_edit_ingredients);
        btnSaveEditIngredients = (Button) findViewById(R.id.btn_save_edit_ingredients);

        btnSaveEditIngredients.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent data = new Intent();
                if(hasAddedIngredient) {
                    data.putExtra("sandwichDetailsPersonalized", sandwichDetails);
                }
                data.putExtra("hasAddedIngredient", hasAddedIngredient);
                setResult(Activity.RESULT_OK, data);
                finish();
            }
        });

        FloatingActionButton fabButton = (FloatingActionButton) findViewById(R.id.floating_button_add_ingredient);

        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hasAddedIngredient = true;
                Intent addTaskIntent = new Intent(EditIngredientsActivity.this, SelectIngredientsActivity.class);
                startActivityForResult(addTaskIntent, INGREDIENTS_SELECTION_REQUEST_CODE);
            }
        });

        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        mRecyclerView.setLayoutManager(layoutManager);

        presenter = new EditIngredientsPresenterImpl(this);

        Intent sandwichDetailsIntent = getIntent();
        sandwichDetails = sandwichDetailsIntent.getParcelableExtra("sandwichDetails");

        //Finally initializing our adapter
        mEditIngredientsAdapter = new EditIngredientsAdapter(this);
        mEditIngredientsAdapter.swapEditIngredientsList(sandwichDetails.getIngredients());

        //Adding adapter to recyclerview
        mRecyclerView.setAdapter(mEditIngredientsAdapter);

//        presenter.loadIngredientsList(Integer.parseInt(sandwichDetailsIntent.getStringExtra("id")));
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Ingredient addedIngredient = data.getParcelableExtra("addedIngredient");
        //verify if ingredient already exists, if so, increment his value
        sandwichDetails.setIngredients((ArrayList<Ingredient>) presenter.addIngredientToTheList(addedIngredient,
                sandwichDetails.getIngredients()));
        mEditIngredientsAdapter.swapEditIngredientsList(sandwichDetails.getIngredients());

//        presenter.addIngredientToTheList(addedIngredient);
    }
}
