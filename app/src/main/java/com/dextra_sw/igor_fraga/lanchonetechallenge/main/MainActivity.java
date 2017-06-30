package com.dextra_sw.igor_fraga.lanchonetechallenge.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.dextra_sw.igor_fraga.lanchonetechallenge.R;
import com.dextra_sw.igor_fraga.lanchonetechallenge.cart.CartActivity;
import com.dextra_sw.igor_fraga.lanchonetechallenge.model.Sanduicheira;
import com.dextra_sw.igor_fraga.lanchonetechallenge.model.Sandwich;
import com.dextra_sw.igor_fraga.lanchonetechallenge.promo.PromoActivity;
import com.dextra_sw.igor_fraga.lanchonetechallenge.sandwichdetails.SandwichDetailsActivity;

public class MainActivity extends AppCompatActivity implements
        LanchoneteAdapter.LanchoneteAdapterOnClickHandler{

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int CART_CHECKOUT_REQUEST_CODE = 50;
    LanchoneteAdapter mLanchoneteAdapter;

    RecyclerView mRecyclerView;
    private MainPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Attach RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_lanchonete);

        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        mRecyclerView.setLayoutManager(layoutManager);


        //init presenter
        presenter = new MainPresenterImpl(this);
        presenter.loadIngredientsData();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int selectedId = item.getItemId();

        if(selectedId == R.id.promo_list){
            Intent promoIntent = new Intent(this, PromoActivity.class);
            startActivity(promoIntent);
        }

        if(selectedId == R.id.cart_checkout){
            Intent cartIntent = new Intent(this, CartActivity.class);

            Sanduicheira sanduicheira = new Sanduicheira();
            sanduicheira.setAllSandwiches(mLanchoneteAdapter.getSandwichList());

            cartIntent.putExtra("allSandwiches", sanduicheira);
            startActivityForResult(cartIntent, CART_CHECKOUT_REQUEST_CODE);
        }
        

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(Sandwich selectedSandwich) {
        Intent detailsIntent = new Intent(this, SandwichDetailsActivity.class);

        detailsIntent.putExtra("selectedSandwich", selectedSandwich);
        startActivity(detailsIntent);
    }
}
