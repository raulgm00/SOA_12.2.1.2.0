package mx.agarcia.ea.utils.msoffice.model;

import java.io.Serializable;

public class ExcelFile implements Serializable{
    @SuppressWarnings("compatibility:-3641215709376333870")
    private static final long serialVersionUID = 1L;
    
    
    private Boolean success = Boolean.FALSE;
    private String code;
    private String message;
    private String details;

    private byte[] document;


    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDetails() {
        return details;
    }

    public void setDocument(byte[] document) {
        this.document = document;
    }

    public byte[] getDocument() {
        return document;
    }

}
