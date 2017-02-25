package ixigo.nitin.com.ixigo.Flights;

import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ixigo.nitin.com.ixigo.CustomViews.CustomTextView;
import ixigo.nitin.com.ixigo.Flights.Adapter.FlightDataAdapter;
import ixigo.nitin.com.ixigo.Flights.Adapter.ProviderDataAdapter;
import ixigo.nitin.com.ixigo.Model.Appendix;
import ixigo.nitin.com.ixigo.Model.Fares;
import ixigo.nitin.com.ixigo.Model.FlightModel;
import ixigo.nitin.com.ixigo.R;
import ixigo.nitin.com.ixigo.Utils.DataPresenter;

public class FlightsActivity extends AppCompatActivity implements Presenter ,FlightDataAdapter.OnItemClickListener {


    ArrayList<FlightModel> flightModelList;
    Appendix appendix;


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.recyclerViewFlightProvider)
    RecyclerView recyclerViewFlightProvider;


    @BindView(R.id.textViewCities)
    CustomTextView cities;
    @BindView(R.id.textViewDuration)
    CustomTextView duration;
    @BindView(R.id.textViewClass)
    CustomTextView flightClass;
    @BindView(R.id.textViewTimes)
    CustomTextView flightTimings;

    FlightDataAdapter flightDataAdapter;

    DataPresenter dataPresenter;


    private BottomSheetBehavior mBottomSheetBehavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flights);
        ButterKnife.bind(this); //initialize butterknife
        /**
         * initialize appendix and interactor layer
         */
        appendix=new Appendix();
        Interactor interactor=new InteractorImlp(this);
        interactor.onLoadData(); //call for load data


        /**
         * set layout param for recycler views
         */
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); //flight recyclerview
        recyclerViewFlightProvider.setLayoutManager(new LinearLayoutManager(this)); //provider recyclerview


        initializeBottomSheet();



    }

    /**
     * Initialize model bottom sheet view
     */
    private void initializeBottomSheet() {
        View bottomSheet = findViewById( R.id.bottom_sheet );

        mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);

        mBottomSheetBehavior.setPeekHeight(0);
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        mBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    mBottomSheetBehavior.setPeekHeight(0);
                }
            }

            @Override
            public void onSlide(View bottomSheet, float slideOffset) {
            }
        });
    }

    /**
     * when data is successfully loaded deserialize flightmodellist
     * make appendix
     * sortDataAccordingToPrice - by default flights will populate according to price sorting
     * set adapter of recycler view
     * @param jsonObject
     */
    @Override
    public void onDataSuccesFullyLoaded(JSONObject jsonObject) {

        System.out.println(jsonObject.toString());


        ObjectMapper mapper = new ObjectMapper();

        try {


            flightModelList = mapper.readValue(String.valueOf(jsonObject.getJSONArray("flights")), new TypeReference<List<FlightModel>>(){});

            fillAirlinesData(jsonObject.getJSONObject("appendix").getJSONObject("airlines"));
            fillAirportsData(jsonObject.getJSONObject("appendix").getJSONObject("airports"));
            fillProvidersData(jsonObject.getJSONObject("appendix").getJSONObject("providers"));

            sortDataAccordingToPrice();


            dataPresenter=new DataPresenter(appendix); // Initialize dataPresenter

            flightDataAdapter=new FlightDataAdapter(this,flightModelList,appendix);

            recyclerView.setAdapter(flightDataAdapter);

            //appendix.setAirlines((HashMap<String, Object>) mapper.readValue(String.valueOf(jsonObject.getJSONObject("appendix").getJSONObject("airlines")), new TypeReference<Map<String, Object>>(){}));
           // System.out.println("size "+flightModelList.size());



        } catch (JSONException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * sort data according to price
     */
    @OnClick(R.id.buttonCheap)
    public void onClickCheap(){

        sortDataAccordingToPrice();

    }

    /**
     * sort data according to departure time
     */
    @OnClick(R.id.buttonEarly)
    public void onClickEarly(){

        sortDataAccordingToDepartureTime();

    }

    /**
     * Custom comparator is used for sorting
     */
    private void sortDataAccordingToPrice(){


       Comparator<FlightModel> comparator= new Comparator<FlightModel>() {
           @Override
           public int compare(FlightModel o1, FlightModel o2) {

               Integer price1=getMiniumFare(o1.getFares());
               Integer price2=getMiniumFare(o2.getFares());

               if(price1!=null&&price2!=null){

                  if(price1>price2){

                      return 1;
                  }else if(price1<price2){

                      return -1;
                  }else{

                      return 0;
                  }


               }else {

                   return 0;
               }
               }



           @Override
           public boolean equals(Object obj) {
               return false;
           }
       };


        Collections.sort(flightModelList,comparator);

        if(flightDataAdapter!=null){

            flightDataAdapter.notifyDataSetChanged();

        }

    }

    /**
     * Custom comparator is used for sorting
     */
    private void sortDataAccordingToDepartureTime(){


       Comparator<FlightModel> comparator= new Comparator<FlightModel>() {
           @Override
           public int compare(FlightModel o1, FlightModel o2) {

               Long time1=o1.getDepartureTime();
               Long time2=o2.getDepartureTime();

               if(time1!=null&&time2!=null){

                  if(time1>time2){

                      return 1;
                  }else if(time1<time2){

                      return -1;
                  }else{

                      return 0;
                  }


               }else {

                   return 0;
               }
               }



           @Override
           public boolean equals(Object obj) {
               return false;
           }
       };


        Collections.sort(flightModelList,comparator);

        if(flightDataAdapter!=null){

            flightDataAdapter.notifyDataSetChanged();

        }

    }


    /**
     * make appendix object
     * @param airlines
     */
    private void fillAirlinesData(JSONObject airlines){

        if(airlines.length()>0){

           HashMap<String,String> hashMap = appendix.getAirlines();

            Iterator<String> iterator= airlines.keys();

            while (iterator.hasNext()){

                String key=iterator.next();
                try {
                    hashMap.put(key, (String) airlines.get(key));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        }


    }

    /**
     * make appendix object
     * @param airports
     */
    private void fillAirportsData(JSONObject airports){

        if(airports.length()>0){

           HashMap<String,String> hashMap = appendix.getAirports();

            Iterator<String> iterator= airports.keys();

            while (iterator.hasNext()){

                String key=iterator.next();
                try {
                    hashMap.put(key, (String) airports.get(key));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        }


    }

    /**
     * make appendix object
     * @param providers
     */
    private void fillProvidersData(JSONObject providers){

        if(providers.length()>0){

           HashMap<String,String> hashMap = appendix.getProviders();

            Iterator<String> iterator= providers.keys();

            while (iterator.hasNext()){

                String key=iterator.next();
                try {
                    hashMap.put(key, (String) providers.get(key));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        }


    }


    /**
     * get minimum fare from fares
     * COMPLEXITY - O(n)
     * @param fares
     * @return
     */
    private Integer getMiniumFare(Fares[] fares ){

        int minimum=Integer.MAX_VALUE;

        if(fares.length>0){

            for(int i=0;i<fares.length;i++){

                if(fares[i].getFare()<minimum){

                    minimum=fares[i].getFare();

                }

            }

        }

        if(minimum!=Integer.MAX_VALUE) {
            return minimum;
        }else{

            return null;
        }
    }


    /**
     * populate providers recyclerview in Modal Bottom Sheet
     * @param flightModel
     */
    @Override
    public void onClick(FlightModel flightModel) {


        if(dataPresenter!=null) {
            cities.setText(dataPresenter.getFlightCities(flightModel.getOriginCode(),flightModel.getDestinationCode()));
            flightClass.setText(dataPresenter.getFlightClass(flightModel.getAirlineCode(),flightModel.getFlightClass()));
            flightTimings.setText(dataPresenter.getFormatedDate(flightModel.getDepartureTime()).concat(" to ").concat(dataPresenter.getFormatedDate(flightModel.getDepartureTime())));
            duration.setText(dataPresenter.getDuration(flightModel.getDepartureTime(),flightModel.getArrivalTime()));

        }

        ProviderDataAdapter providerDataAdapter=new ProviderDataAdapter(appendix,flightModel.getFares(),this);

        recyclerViewFlightProvider.setAdapter(providerDataAdapter);
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);


    }


}
