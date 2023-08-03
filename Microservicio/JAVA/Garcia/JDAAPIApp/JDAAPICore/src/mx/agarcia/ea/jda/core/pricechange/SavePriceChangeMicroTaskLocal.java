package mx.agarcia.ea.jda.core.pricechange;

import javax.ejb.Local;

import javax.jws.WebParam;

import mx.agarcia.ea.jda.APIException;

@Local
public interface SavePriceChangeMicroTaskLocal {
    
    public  String saveEvent( @WebParam(name = "eventID") String eventID, @WebParam(name = "priceChangeData")String priceChangeData)
    throws APIException;
}
