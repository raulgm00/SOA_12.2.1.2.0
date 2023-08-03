package mx.agarcia.ea.utils.msoffice.model;

import java.io.Serializable;

import java.text.DecimalFormat;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class ExcelRow implements Serializable  {
    
    @SuppressWarnings("compatibility:-304120133553350633")
    private static final long serialVersionUID = 1L;
    
    @XmlAttribute(name="num")
    private Integer num = 0;
    
    @XmlAttribute(name="sheetNum")
    private Integer sheetNum = 0;

    
    @XmlElement(name="cell")
    private List<ExcelCell> cells = new ArrayList<ExcelCell>();
    
    public void addData(Integer cellNum, String data ){
        ExcelCell cell = new ExcelCell();
        cell.setCellNumber(cellNum);
        cell.setData( data );
        
        cells.add( cell );
    }
    public void addData(Integer cellNum, String data, int type ){
        
        /*
        if( type==0 ){
            DecimalFormat df2 = new DecimalFormat( "####" );
            data = df2.format( Double.parseDouble( data ) );
        }*/
        
        ExcelCell cell = new ExcelCell();
        cell.setCellNumber(cellNum);
        cell.setData( data );
        cell.setType( String.valueOf( type ) );
        cells.add( cell );
    }
    

    public void setCells(List<ExcelCell> cells) {
        this.cells = cells;
    }

    public List<ExcelCell> getCells() {
        return cells;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getNum() {
        return num;
    }

    public void setSheetNum(Integer sheetNum) {
        this.sheetNum = sheetNum;
    }

    public Integer getSheetNum() {
        return sheetNum;
    }
}
