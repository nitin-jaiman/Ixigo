package ixigo.nitin.com.ixigo.Flights;

import org.json.JSONException;
import org.json.JSONObject;

import ixigo.nitin.com.ixigo.Repository.FlightDataRepository;
import ixigo.nitin.com.ixigo.Singleton.RetrofitProvider;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by apple on 22/02/17.
 */

/**
 * Implementation of Interactor interface
 */
public class InteractorImlp implements Interactor {


    Presenter presenter;

    public InteractorImlp(Presenter presenter){

        this.presenter=presenter;

    }

    /**
     * Async. load data
     */
    @Override
    public void onLoadData() {

        FlightDataRepository flightDataRepository = RetrofitProvider.providesRetrofit().create(FlightDataRepository.class);
        Call<String> call= flightDataRepository.getFlightData("ixigoandroidchallenge%2Fsample_flight_api_response.json?alt=media&token=d8005801-7878-4f57-a769-ac24133326d6");

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                JSONObject jsonData = null;
                if(response.isSuccessful()&&response.body()!=null){

                    try {
                        jsonData=new JSONObject(response.body());
                    } catch (JSONException e) {  //Checked Exception Handled
                        e.printStackTrace(); //TODO: Code for retry
                    }

                }


                if(presenter!=null&&jsonData!=null){

                    presenter.onDataSuccesFullyLoaded(jsonData); // data is ready, pass it on to presenter

                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable throwable) {

                throwable.printStackTrace();

                //TODO: handle failure call by snackbar

            }
        });
    }
}
