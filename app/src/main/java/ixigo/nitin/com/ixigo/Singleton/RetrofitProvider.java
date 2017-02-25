package ixigo.nitin.com.ixigo.Singleton;

import ixigo.nitin.com.ixigo.RetrofitConverters.ToStringConverterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by apple on 22/02/17.
 */

/**
 * Singleton class to provide retrofit instance
 */
public class RetrofitProvider {


    public static Retrofit retrofit;

    /**
     * if null make a new Retrofit Object
     * otherwise return static object
     * @return
     */
    public static synchronized Retrofit providesRetrofit() {


            if (retrofit != null) {

                return retrofit;
            } else {

                Retrofit retrofitTemp = new Retrofit.Builder()
                        .baseUrl("https://firebasestorage.googleapis.com/v0/b/gcm-test-6ab64.appspot.com/o/")
                        //.addConverterFactory(JacksonConverterFactory.create())
                        .addConverterFactory(new ToStringConverterFactory())
                        .build();

                retrofit = retrofitTemp;
                return retrofit;
            }

    }

}
