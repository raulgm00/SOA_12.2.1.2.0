package mx.agarcia.ea.utils.msoffice.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class ExcelData implements Serializable {
    
    @SuppressWarnings("compatibility:-7173989633602059126")
    private static final long serialVersionUID = 1L;
    
    private Boolean success = Boolean.FALSE;
    private String code;
    private String message;
    private String details;
    
    
    private String fileName;
    private String requester;
    
    
    
    @XmlAttribute(name="rowsCount")
    private Integer rowsCount = 0;
    
    @XmlElement(name="row")
    private List<ExcelRow> rows = new ArrayList<ExcelRow>();

    @XmlAttribute(name="fileType")
    private Integer fileType = 0;

    public void addRow(ExcelRow data ){
        
        data.setNum(rowsCount ++ );
        rows.add( data );
    }
    
    public void setRows(List<ExcelRow> rows) {
        this.rows = rows;
    }




    public List<ExcelRow> getRows() {
        return rows;
    }

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

    public void setFileType(Integer fileType) {
        this.fileType = fileType;
    }

    public Integer getFileType() {
        return fileType;
    }
    

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setRequester(String requester) {
        this.requester = requester;
    }

    public String getRequester() {
        return requester;
    }
}
