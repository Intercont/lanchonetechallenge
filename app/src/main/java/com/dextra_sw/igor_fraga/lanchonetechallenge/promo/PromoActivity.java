package com.dextra_sw.igor_fraga.lanchonetechallenge.promo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dextra_sw.igor_fraga.lanchonetechallenge.R;

public class PromoActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    PromoAdapter mPromoAdapter;
    PromoPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promo);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_promotions);

        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        mRecyclerView.setLayoutManager(layoutManager);

        presenter = new PromoPresenterImpl(this);
        presenter.loadPromoData();
    }
}
