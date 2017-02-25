package ixigo.nitin.com.ixigo.Flights.Adapter;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import ixigo.nitin.com.ixigo.CustomViews.CustomTextView;
import ixigo.nitin.com.ixigo.Exception.NoFareFoundException;
import ixigo.nitin.com.ixigo.Model.Appendix;
import ixigo.nitin.com.ixigo.Model.Fares;
import ixigo.nitin.com.ixigo.Model.FlightModel;
import ixigo.nitin.com.ixigo.R;
import ixigo.nitin.com.ixigo.Utils.DataPresenter;

/**
 * Created by apple on 23/02/17.
 */

public class FlightDataAdapter extends RecyclerView.Adapter<FlightDataAdapter.GatewayViewHolder>  {


    ArrayList<FlightModel> flightModels;
    Appendix appendix;
    Context context;
    DataPresenter dataPresenter;

    /**
     * Constructor of FlightDataAdapter
     * We need appendix object to get static info from dataPresenter
     * @param context
     * @param flightModels
     * @param appendix
     */
    public FlightDataAdapter(Context context,ArrayList<FlightModel> flightModels, Appendix appendix){

        this.context=context;
        this.flightModels=flightModels;
        this.appendix=appendix;
        dataPresenter=new DataPresenter(appendix);

    }

    @Override
    public GatewayViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.flight_item, parent, false);


        GatewayViewHolder dataObjectHolder = new GatewayViewHolder(view);


        return dataObjectHolder;
    }

    /**
     * We are Populating view in this method
     * this method is recyclable hence it yields better performance than listview
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(GatewayViewHolder holder, int position) {


        final FlightModel flightModel=flightModels.get(position); // We have used arraylist so that we can access index in O(1) time complexity
                                                            // Unlike linkedList's O(n)
        String flightName=dataPresenter.getFlightName(flightModel.getAirlineCode());
        String duration=dataPresenter.getDuration(flightModel.getDepartureTime(),flightModel.getArrivalTime());
        String cost=dataPresenter.getMiniumFare(flightModel.getFares());
        String departureTime=dataPresenter.getFormatedDate(flightModel.getDepartureTime());
        String arrivalTime=dataPresenter.getFormatedDate(flightModel.getArrivalTime());

        final OnItemClickListener onItemClickListener= (OnItemClickListener) context;

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onItemClickListener.onClick(flightModel); // sends a callback to activity to display model bottom sheets
                //containing providers

            }
        });

        if(flightName!=null) {
            holder.flightName.setText(flightName);
        }

        if(duration!=null){

            holder.flightDur.setText(duration);
        }

        if(cost!=null){

            holder.flightcost.setText("₹".concat(cost));

        }else{
            //TODO: Throw NoFareFoundException

            throw new NoFareFoundException("No Fare Found");

        }

        if(departureTime!=null&&arrivalTime!=null){

            holder.flightDept.setText(departureTime);
            holder.flightArival.setText(arrivalTime);

        }

    }





    @Override
    public int getItemCount() {
        return flightModels.size();
    }

    public static class GatewayViewHolder extends RecyclerView.ViewHolder {

        public CardView cardView;
        public CustomTextView flightName;
        public CustomTextView flightArival;
        public CustomTextView flightDept;
        public CustomTextView flightDur;
        public CustomTextView flightcost;



        GatewayViewHolder(View itemView) {
            super(itemView);

            cardView = (CardView) itemView.findViewById(R.id.cardView);
            flightName = (CustomTextView) itemView.findViewById(R.id.textViewFlighName);
            flightArival = (CustomTextView) itemView.findViewById(R.id.textViewArv);
            flightDept =(CustomTextView) itemView.findViewById(R.id.textViewDep);
            flightDur =(CustomTextView) itemView.findViewById(R.id.textViewDur);
            flightcost =(CustomTextView) itemView.findViewById(R.id.textViewCost);

        }
    }


    public interface OnItemClickListener{


        public void onClick(FlightModel flightModel);


    }

}
