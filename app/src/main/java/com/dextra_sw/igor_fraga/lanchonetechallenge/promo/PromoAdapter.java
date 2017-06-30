package com.dextra_sw.igor_fraga.lanchonetechallenge.promo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dextra_sw.igor_fraga.lanchonetechallenge.R;
import com.dextra_sw.igor_fraga.lanchonetechallenge.model.Promotional;

import java.util.List;

/**
 * Created by intercont on 30/06/17.
 */

public class PromoAdapter extends RecyclerView.Adapter<PromoAdapter.PromoAdapterViewHolder>{

    private final Context mContext;
    List<Promotional> listPromo;


    public PromoAdapter(Context mContext, List<Promotional> listPromo) {
        this.mContext = mContext;
        this.listPromo = listPromo;
    }

    @Override
    public PromoAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater
                .from(mContext)
                .inflate(R.layout.list_item_promos, viewGroup, false);

        view.setFocusable(true);
        return new PromoAdapter.PromoAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PromoAdapterViewHolder holder, int position) {
        Promotional promo = listPromo.get(position);

        holder.promoName.setText(promo.getName());
        holder.promoDescription.setText(promo.getDescription());
    }


    @Override
    public int getItemCount() {
        return listPromo.size();
    }

    public void swapPromoList(List<Promotional> listPromo){
        if(listPromo != null){
            this.listPromo = listPromo;
            notifyDataSetChanged();
        }
    }

    public class PromoAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final TextView promoName;
        final TextView promoDescription;

        public PromoAdapterViewHolder(View itemView) {
            super(itemView);

            promoName = (TextView) itemView.findViewById(R.id.promo_name);
            promoDescription = (TextView) itemView.findViewById(R.id.promo_description);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
        }
    }
}
