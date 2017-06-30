package com.dextra_sw.igor_fraga.lanchonetechallenge.selectingredientslist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dextra_sw.igor_fraga.lanchonetechallenge.R;
import com.dextra_sw.igor_fraga.lanchonetechallenge.editingredients.EditIngredientsAdapter;
import com.dextra_sw.igor_fraga.lanchonetechallenge.model.Ingredient;

public class SelectIngredientsActivity extends AppCompatActivity implements SelectIngredientsAdapter.SelectIngredientsAdapterOnClickHandler {

    public SelectIngredientsAdapter mSelectIngredientsAdapter;
    public EditIngredientsAdapter mEditIngredientsAdapter;
    RecyclerView mRecyclerView;
    private SelectIngredientsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_ingredients);

        mEditIngredientsAdapter = new EditIngredientsAdapter(this);

        //Attach RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_select_ingredient_edit);

        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        mRecyclerView.setLayoutManager(layoutManager);

        presenter = new SelectIngredientsPresenterImpl(this);
        presenter.loadIngredientsList();


    }


    @Override
    public void onClick(Ingredient ingredient) {
        Intent data = new Intent();
        data.putExtra("addedIngredient",ingredient);
        setResult(Activity.RESULT_OK, data);
        finish();
    }
}
