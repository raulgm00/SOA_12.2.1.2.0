package mx.agarcia.ea.jda.core.model;

import java.util.Iterator;

import javax.xml.transform.Source;

/**
 * 
 * @author Sol4IT Mexico 2019 - Todos los derechos reservados
 * @category Almacenes Garcia / Ceteris- JDA API
 */
public class Request implements IRequest {
	private static final long serialVersionUID = 1L;
	private Header header;
	private Source payload;

	public Request() {
		this.header = new Header();
	}

	public Header getHeader() {
		return this.header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public Source getPayload() {
		return this.payload;
	}

	public void setPayload(Source payload) {
		this.payload = payload;
	}
        
    public void print(){
        System.out.println( "*******       REQUEST       *******" );    
        System.out.println( "*******       Header       *******" );    
        Iterator itHead = this.getHeader().getParameters().keySet().iterator();
        String key;
        while( itHead.hasNext() ){
            key = (String)itHead.next();
            System.out.println( "        Param<"+ key +">=" + this.getHeader().getParameters().get( key )  );    
        
        }


        System.out.println( "*******       BODY       *******" );    
        System.out.println( "*******       END REQUEST       *******" );    
    }
}
