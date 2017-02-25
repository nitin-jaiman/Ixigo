package ixigo.nitin.com.ixigo.Utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import ixigo.nitin.com.ixigo.Model.Appendix;
import ixigo.nitin.com.ixigo.Model.Fares;

/**
 * Created by apple on 23/02/17.
 */


/**
 * This class has methods which will take data and convert it into a presentable String
 */
public class DataPresenter {

    Appendix appendix;
    
    public DataPresenter(Appendix appendix){
        
        this.appendix=appendix;
        
    }
    
    /**
     *
     * @param start
     * @param end
     * @return if minutes are 0 show only hours as there is no need to show 0 minutes
     */
    public String getDuration(Long start,Long end){

        Long durationMiliSeconds=end-start;

        int hours= (int) (durationMiliSeconds/(long)(1000*60*60));

        System.out.println(durationMiliSeconds+"    -    "+(long)(1000*60*60));

        int minutes= (int) (durationMiliSeconds%(long)(1000*60*60));

        if(minutes!=0) {
            return hours + "h" + " " + minutes + "m";
        }else{

            return String.valueOf(hours).concat(" hours");
        }



    }


    /**
     * this methods take long seconds and returns time in HH:mm format
     * @param dateInMili
     * @return
     */
    public String getFormatedDate(long dateInMili){

        Date date = new Date(dateInMili);
        DateFormat formatter = new SimpleDateFormat("HH:mm");
        String dateFormatted = formatter.format(date);

        return dateFormatted;

    }



    /**
     * Appendix object contains static data from api
     * we have stored this data in hashmap
     * we will use slug as key and get values
     * @param code
     * @return String - name of fligh airlines
     */
    public String getFlightName(String code){


        return appendix.getAirlines().get(code);
    }

    /**
     * get minimum fare
     * @param fares
     * @return
     */
    public String getMiniumFare(Fares[] fares ){

        int minimum=Integer.MAX_VALUE;

        if(fares.length>0){

            for(int i=0;i<fares.length;i++){

                if(fares[i].getFare()<minimum){

                    minimum=fares[i].getFare();

                }

            }

        }

        if(minimum!=Integer.MAX_VALUE) {
            return String.valueOf(minimum);
        }else{

            return null;
        }
    }


    /**
     * get full city name from city code
     * @param slugDep
     * @param slugAriv
     * @return
     */
    public String getFlightCities(String slugDep,String slugAriv){



        return appendix.getAirports().get(slugDep).concat(" - ").concat(appendix.getAirports().get(slugAriv));

    }

    /**
     * get flight class
     * @param airlineCode
     * @param flightClass
     * @return
     */
    public String getFlightClass(String airlineCode,String flightClass){



        return appendix.getAirlines().get(airlineCode).concat(" - ").concat(flightClass);

    }
    
}
