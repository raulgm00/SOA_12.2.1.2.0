package mx.agarcia.ea.utils.msoffice.model;

import java.io.Serializable;


public class Request implements Serializable {
    
	private static final long serialVersionUID = 1L;
	private Header header;
        
	private byte[] document;

	public Request() {
		this.header = new Header();
	}

	public Header getHeader() {
		return this.header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}


  
    public byte[] getDocument() {
        return document;
    }

   
    public void setDocument(byte[] document) {

        this.document = document;
    }
}
