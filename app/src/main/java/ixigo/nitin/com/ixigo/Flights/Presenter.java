package ixigo.nitin.com.ixigo.Flights;

import org.json.JSONObject;

/**
 * Created by apple on 22/02/17.
 */

/**
 * This interfac will be implemented by Activity
 * We are doing sepration of concerns by making these Interface
 * To make codebase on a clean architecture
 */
public interface Presenter {


    /**
     * Will be called by implementation of interactor from async thread of retrofit once the data is ready
     */
    public void onDataSuccesFullyLoaded(JSONObject jsonObject);


}
