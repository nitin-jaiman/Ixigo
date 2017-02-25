package ixigo.nitin.com.ixigo.Exception;

/**
 * Created by apple on 23/02/17.
 */


/**
 * Custom Exception In case no fare is found in json
 * Unchecked Exception because this can happen on runtime
 * we can't check on compile time
 */
public class NoFareFoundException extends RuntimeException {

       public NoFareFoundException(String msg){


           super(msg);

       }


}
