package mx.agarcia.ea.utils.msoffice;

import java.io.ByteArrayOutputStream;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import mx.agarcia.ea.utils.msoffice.model.ExcelCell;
import mx.agarcia.ea.utils.msoffice.model.ExcelData;
import mx.agarcia.ea.utils.msoffice.model.ExcelFile;
import mx.agarcia.ea.utils.msoffice.model.ExcelRow;
import mx.agarcia.ea.utils.msoffice.model.Request;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@WebService(name="ExcelGeneratorUtil", serviceName="ExcelGeneratorUtilService", portName="ExcelGeneratorUtilPort")
public class ExcelGeneratorUtil {
    
    @WebMethod
    public ExcelFile generate(@WebParam(name="data")ExcelData request) throws MSOfficeUtilException{
        
        LoggerUtil.info("[ExcelGeneratorUtil]", "Iniciando generación de Excel: " + request.getFileName()   );

        ExcelFile excel = new ExcelFile();
        excel.setSuccess( Boolean.FALSE );
        
        try{
            
        Workbook workbook = new XSSFWorkbook();
        CreationHelper createHelper = workbook.getCreationHelper();
        Sheet sheet = workbook.createSheet("AGSOA");
    
        if( request != null && request.getRows() != null && request.getRows().size() > 0 ){
            Row excelRow;
            Cell excelCell;
            int numCell = 0;
            for( ExcelRow row : request.getRows() ){
                excelRow = sheet.createRow(0);
                numCell = 0;
                for( ExcelCell cell : row.getCells() ){
                    if( numCell != cell.getCellNumber() ){
                        LoggerUtil.info("[ExcelGeneratorUtil]", "Posición de Celda no coincide: NumRefCell=" + 
                                                                numCell + ", NumSendedCell" + cell.getCellNumber());
                    
                    }
                    
                    excelCell = excelRow.createCell( numCell );
                    excelCell.setCellValue( cell.getData() );
                    
                    numCell++;
                }
            }
            
            ByteArrayOutputStream byteAOU = new ByteArrayOutputStream();
            sheet.autoSizeColumn(  0 );
            workbook.write( byteAOU );
            excel.setDocument( byteAOU.toByteArray() );
            LoggerUtil.info("[ExcelGeneratorUtil]", "Excel " + request.getFileName() + " generado correctamente con " + 
                                                    request.getRows().size() +
                                                    " filas");

            
            excel.setSuccess( Boolean.TRUE );
            excel.setCode( "MSUT-0000" );
            excel.setMessage( "Archivo generado correctamente" );
        } else {
            excel.setCode( "MSUT-0001" );
            excel.setMessage( "No existen datos para generar el archivo" );
        }
            
        } catch(Exception e){
            LoggerUtil.error("[ExcelGeneratorUtil]", "No es posible generar Excel", e);
            excel.setCode( "MSUT-0002" );
            excel.setMessage( "No es posible generar Excel. " + e.getMessage() );
        }
        
        return excel;
    }
}
