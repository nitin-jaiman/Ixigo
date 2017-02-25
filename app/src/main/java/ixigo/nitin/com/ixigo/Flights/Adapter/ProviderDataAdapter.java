package ixigo.nitin.com.ixigo.Flights.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import ixigo.nitin.com.ixigo.CustomViews.CustomButton;
import ixigo.nitin.com.ixigo.CustomViews.CustomTextView;
import ixigo.nitin.com.ixigo.Model.Appendix;
import ixigo.nitin.com.ixigo.Model.Fares;
import ixigo.nitin.com.ixigo.R;

/**
 * Created by apple on 23/02/17.
 */

public class ProviderDataAdapter extends RecyclerView.Adapter<ProviderDataAdapter.GatewayViewHolder>  {

    Appendix appendix;
    Fares[] fares;
    Context context;


    /**
     * Constructor of ProviderDataAdapter
     * @param appendix
     * @param fares
     * @param context
     */
    public ProviderDataAdapter(Appendix appendix,Fares[] fares, Context context){

        this.appendix=appendix;
        this.fares=fares;
        this.context=context;

    }


    @Override
    public GatewayViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.provider_item, parent, false);


        GatewayViewHolder dataObjectHolder = new GatewayViewHolder(view);


        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(GatewayViewHolder holder, int position) {

        Fares f=fares[position];

        holder.providerCost.setText("₹".concat(String.valueOf(f.getFare())));
        holder.providerName.setText(appendix.getProviders().get(String.valueOf(f.getProviderId())));
        holder.bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context,"Redirect to booking module",Toast.LENGTH_LONG).show();

            }
        });

        Glide.with(context).load("https://logo.clearbit.com/"+appendix.getProviders().get(String.valueOf(f.getProviderId()))+".com").crossFade().diskCacheStrategy(DiskCacheStrategy.ALL)
                .fitCenter()
                .into(holder.providerLogo);
    }

    @Override
    public int getItemCount() {
        return fares.length;
    }

    public static class GatewayViewHolder extends RecyclerView.ViewHolder {

        public CardView cardView;
        public CustomTextView providerName;
        public CustomTextView providerCost;
        public CustomButton bookButton;
        public ImageView providerLogo;




        GatewayViewHolder(View itemView) {
            super(itemView);

            cardView = (CardView) itemView.findViewById(R.id.cardView);
            providerName = (CustomTextView) itemView.findViewById(R.id.textViewProviderName);
            providerCost = (CustomTextView) itemView.findViewById(R.id.textViewProviderCost);
            bookButton =(CustomButton) itemView.findViewById(R.id.buttonBook);
            providerLogo= (ImageView) itemView.findViewById(R.id.imageViewLogo);


        }
    }

}
