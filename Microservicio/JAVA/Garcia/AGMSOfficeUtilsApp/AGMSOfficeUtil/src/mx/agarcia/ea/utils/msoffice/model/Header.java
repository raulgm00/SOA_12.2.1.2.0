package mx.agarcia.ea.utils.msoffice.model;

import java.io.Serializable;
import java.util.HashMap;


public class Header implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private HashMap<String, String> parameters = new HashMap<String, String>();

	private String interfaceId;

	public HashMap<String, String> getParameters() {
		return this.parameters;
	}

	public void setParameters(HashMap<String, String> parameters) {
		this.parameters = parameters;
	}


	public String getInterfaceId() {
		return this.interfaceId;
	}

	public void setInterfaceId(String interfaceId) {
		this.interfaceId = interfaceId;
	}
}
