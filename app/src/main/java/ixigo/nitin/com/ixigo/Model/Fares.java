package ixigo.nitin.com.ixigo.Model;

/**
 * Created by apple on 23/02/17.
 */

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Model class for fare object
 */
public class Fares implements Parcelable
{
    private Integer fare;

    private Integer providerId;

    public Integer getFare ()
    {
        return fare;
    }

    public void setFare (Integer fare)
    {
        this.fare = fare;
    }

    public Integer getProviderId ()
    {
        return providerId;
    }

    public void setProviderId (Integer providerId)
    {
        this.providerId = providerId;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [fare = "+fare+", providerId = "+providerId+"]";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}

