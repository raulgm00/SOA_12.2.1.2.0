package mx.agarcia.ea.jda.core.mediation;

import java.util.concurrent.Future;

import javax.ejb.Local;
import mx.agarcia.ea.jda.APIException;
import mx.agarcia.ea.jda.core.model.IResponse;
import mx.agarcia.ea.jda.core.model.Request;

/**
 * 
 * @author Sol4IT Mexico 2019 - Todos los derechos reservados
 * @category Almacenes Garcia / Ceteris- JDA API
 */
@Local
public abstract interface ConnectProxyLocal {
	public abstract IResponse process(Request paramRequest) throws APIException;
}
