package ixigo.nitin.com.ixigo.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by apple on 23/02/17.
 */

/**
 * Model class for Flight object
 */
public class FlightModel
{
    private Fares[] fares;

    private String destinationCode;

    private String airlineCode;

    private Long arrivalTime;

    private Long departureTime;

    private String originCode;

    private String flightClass;

    public String getFlightClass() {
        return flightClass;
    }

    @JsonProperty("class")
    public void setFlightClass(String flightClass) {
        this.flightClass = flightClass;
    }

    public Fares[] getFares ()
    {
        return fares;
    }

    public void setFares (Fares[] fares)
    {
        this.fares = fares;
    }

    public String getDestinationCode ()
    {
        return destinationCode;
    }

    public void setDestinationCode (String destinationCode)
    {
        this.destinationCode = destinationCode;
    }

    public String getAirlineCode ()
    {
        return airlineCode;
    }

    public void setAirlineCode (String airlineCode)
    {
        this.airlineCode = airlineCode;
    }

    public Long getArrivalTime ()
    {
        return arrivalTime;
    }

    public void setArrivalTime (Long arrivalTime)
    {
        this.arrivalTime = arrivalTime;
    }

    public Long getDepartureTime ()
    {
        return departureTime;
    }

    public void setDepartureTime (Long departureTime)
    {
        this.departureTime = departureTime;
    }

    public String getOriginCode ()
    {
        return originCode;
    }

    public void setOriginCode (String originCode)
    {
        this.originCode = originCode;
    }



    @Override
    public String toString()
    {
        return "ClassPojo [fares = "+fares+", destinationCode = "+destinationCode+", airlineCode = "+airlineCode+", arrivalTime = "+arrivalTime+", departureTime = "+departureTime+", originCode = "+originCode+"]";
    }
}
