package ixigo.nitin.com.ixigo.Repository;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by apple on 22/02/17.
 */


/**
 * Retrofit Repository Interface
 */
public interface FlightDataRepository {

    @GET
    public Call<String> getFlightData(@Url String url);

}
