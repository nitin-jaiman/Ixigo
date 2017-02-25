package ixigo.nitin.com.ixigo.Model;

import java.util.HashMap;

/**
 * Created by apple on 23/02/17.
 */

/**
 * Hashmap is used because we would need a lot of searching by key and its complexity is O(1) in average case
 */
public class Appendix {

    private HashMap<String,String> airlines=new HashMap<String,String>();
    private HashMap<String,String> airports=new HashMap<String,String>();
    private HashMap<String,String> providers=new HashMap<String,String>();

    public HashMap<String, String> getAirlines() {
        return airlines;
    }

    public void setAirlines(HashMap<String, String> airlines) {
        this.airlines = airlines;
    }

    public HashMap<String, String> getAirports() {
        return airports;
    }

    public void setAirports(HashMap<String, String> airports) {
        this.airports = airports;
    }

    public HashMap<String, String> getProviders() {
        return providers;
    }

    public void setProviders(HashMap<String, String> providers) {
        this.providers = providers;
    }
}
