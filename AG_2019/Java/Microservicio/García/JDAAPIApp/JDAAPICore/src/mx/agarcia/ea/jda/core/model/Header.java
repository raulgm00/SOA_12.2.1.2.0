package mx.agarcia.ea.jda.core.model;

import java.io.Serializable;
import java.util.HashMap;

/**
 * 
 * @author Sol4IT Mexico 2019 - Todos los derechos reservados
 * @category Almacenes Garcia / Ceteris- JDA API
 */
public class Header implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private HashMap<String, String> parameters = new HashMap();
	private String source;
	private String destiny;
	private String interfaceId;

	public HashMap<String, String> getParameters() {
		return this.parameters;
	}

	public void setParameters(HashMap<String, String> parameters) {
		this.parameters = parameters;
	}

	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestiny() {
		return this.destiny;
	}

	public void setDestiny(String destiny) {
		this.destiny = destiny;
	}

	public String getInterfaceId() {
		return this.interfaceId;
	}

	public void setInterfaceId(String interfaceId) {
		this.interfaceId = interfaceId;
	}
}
