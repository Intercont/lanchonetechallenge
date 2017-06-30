package com.dextra_sw.igor_fraga.lanchonetechallenge.cart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dextra_sw.igor_fraga.lanchonetechallenge.R;
import com.dextra_sw.igor_fraga.lanchonetechallenge.model.Sanduicheira;

public class CartActivity extends AppCompatActivity {

    CartAdapter mCartAdapter;
    RecyclerView mRecyclerView;
    CartPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        //Attach RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_cart);

        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        mRecyclerView.setLayoutManager(layoutManager);

        Intent mainIntent = getIntent();
        Sanduicheira sanduicheira = mainIntent.getParcelableExtra("allSandwiches");

        //init presenter
        presenter = new CartPresenterImpl(this, sanduicheira.getAllSandwiches());
        presenter.loadCartData();
    }
}
