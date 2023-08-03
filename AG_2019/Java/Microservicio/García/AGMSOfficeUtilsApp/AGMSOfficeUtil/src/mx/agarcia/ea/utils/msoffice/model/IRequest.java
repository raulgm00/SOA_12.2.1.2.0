package mx.agarcia.ea.utils.msoffice.model;

import java.io.Serializable;

import java.util.Base64;

import javax.xml.transform.Source;


public  abstract class IRequest implements Serializable {

	public abstract Header getHeader();

	public abstract void setHeader(Header paramHeader);

	public abstract byte[] getDocument();

	public abstract void setDocument(byte[] document);

}
