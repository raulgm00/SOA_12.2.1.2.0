package mx.agarcia.ea.jda.core.pricechange;

import javax.ejb.Remote;

import javax.jws.WebParam;

import mx.agarcia.ea.jda.APIException;

@Remote
public interface SavePriceChangeMicroTaskRemote {
   
    public  String saveEvent( @WebParam(name = "eventID") String eventID, @WebParam(name = "priceChangeData")String priceChangeData)
    throws APIException;
}
