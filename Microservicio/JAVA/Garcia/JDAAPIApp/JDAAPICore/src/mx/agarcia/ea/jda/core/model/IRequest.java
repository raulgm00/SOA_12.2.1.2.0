package mx.agarcia.ea.jda.core.model;

import java.io.Serializable;
import javax.xml.transform.Source;

/**
 * 
 * @author Sol4IT Mexico 2019 - Todos los derechos reservados
 * @category Almacenes Garcia / Ceteris- JDA API
 */
public abstract interface IRequest extends Serializable {
	public abstract Header getHeader();

	public abstract void setHeader(Header paramHeader);

	public abstract Source getPayload();

	public abstract void setPayload(Source paramSource);
}
