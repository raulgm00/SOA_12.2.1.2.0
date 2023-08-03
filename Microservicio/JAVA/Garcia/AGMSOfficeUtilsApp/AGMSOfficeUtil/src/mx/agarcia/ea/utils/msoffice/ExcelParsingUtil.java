package mx.agarcia.ea.utils.msoffice;

import java.io.ByteArrayInputStream;
import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.io.OutputStream;

import java.text.SimpleDateFormat;

import java.util.Date;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import mx.agarcia.ea.utils.Base64Util;
import mx.agarcia.ea.utils.msoffice.model.ExcelData;
import mx.agarcia.ea.utils.msoffice.model.ExcelRow;

import mx.agarcia.ea.utils.msoffice.model.Request;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


//import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/*
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
*/
@WebService(name="ExcelParsingUtil", serviceName="ExcelParsingUtilService", portName="ExcelParsingUtilPort")
public class ExcelParsingUtil {

    static SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd_HHmmss");

    @WebMethod
    public ExcelData parse(@WebParam(name="request") Request request) throws MSOfficeUtilException{
        ExcelData document = new ExcelData();
        
        if(  request.getDocument() != null ){
            try {
                
                String path = downloadFile(request);
                readContent(path, document);


                //sampleData(document);


                /*} catch (InvalidFormatException e) {
                e.printStackTrace();*/ 
            } catch (Exception e) {
                document.setCode( "UTL-0010" );
                document.setMessage( "No es posible hacer parsing del Excel" );
                document.setDetails( e.getLocalizedMessage() );
                e.printStackTrace();
            }
            
        } else {
                document.setCode( "UTL-0011" );
                document.setMessage( "Excel no tiene contenido" );
                document.setDetails( "0 bytes" );
            }
        


        return document;
    }
    

    private String downloadFile(Request request) throws Exception {
        LoggerUtil.debug("[ExcelParsingUtil]", "Recibiendo data...");
        byte[] decodedBytes = request.getDocument();
            //Base64Util.decodeBase64(  request.getDocument()  );
        
        ByteArrayInputStream bais = new ByteArrayInputStream( decodedBytes );

        LoggerUtil.debug("[ExcelParsingUtil]", " ______ Descargando  archivo de tamaño " + (request.getDocument().length * 8) + " bytes");

        String filePath = "/u01/oracle/agtempfiles/AGEXF-" + sdFormat.format( new Date() ) + ".xlsx";
        //String filePath = "/Proyectos/AGEAI/downloadFiles/AGEXF-" + sdFormat.format( new Date() ) + ".xlsx";
        
        OutputStream out = new FileOutputStream( filePath );

        // Transfer bytes from in to out
        byte[] buf = new byte[1024];
        int len;
        while ((len = bais.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        bais.close();
        out.close();        
        
        LoggerUtil.debug("[ExcelParsingUtil]", " ______ Descargado  archivo en " + filePath);
        
        return filePath;
        
    }

    private void readContent(String filePath, ExcelData document) throws Exception {
        LoggerUtil.debug("[ExcelParsingUtil]", "Preparando lectura de documento desde "+filePath+" ...");

        OPCPackage pkg = OPCPackage.open(filePath);
        XSSFWorkbook workbook = new XSSFWorkbook( pkg );

        boolean xlsx = true;
        //FileInputStream inputFS = new FileInputStream(filePath);
        //Workbook workbook = WorkbookFactory.create(inputFS);
        //Workbook workbook = new XSSFWorkbook(bais);

        //OPCPackage pkg;
        //pkg = OPCPackage.open( bais );
        //XSSFWorkbook wb = new XSSFWorkbook(pkg);

        //pkg.close();
        /*POIFSFileSystem fs = new POIFSFileSystem( bais );
                HSSFWorkbook wb = new HSSFWorkbook(fs.getRoot(), true);*/

        /*
                POIFSFileSystem fs = new POIFSFileSystem( bais );
                HSSFWorkbook wb = new HSSFWorkbook(fs);
                HSSFSheet sheet = wb.getSheetAt(0);*/
        /*Workbook workbook = new XSSFWorkbook(bais);
                            Sheet sheet = workbook.getSheetAt(0);

                   */
        //Workbook workbook = new XSSFWorkbook( bais );

        Sheet sheet = workbook.getSheetAt(0);
        LoggerUtil.debug("[ExcelParsingUtil]",
                         "Preparando lectura de documento ..." + workbook.getSheetName(0) + "-- filas: " +
                         sheet.getLastRowNum());
        Row row;
        Cell cell;
        ExcelRow excelRow;
        for (int i = 0; i < sheet.getLastRowNum(); i++) {
            row = sheet.getRow(i);
            
            if( row != null ){
                LoggerUtil.debug("[ExcelParsingUtil]", " Fila " + i + ": No Celdas=" + row.getLastCellNum());

                excelRow = new ExcelRow();

                for (int j = 0; j <= row.getLastCellNum(); j++) {
                    cell = row.getCell(j);
                    if (cell != null) {
                        if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                            LoggerUtil.debug("[ExcelParsingUtil]",
                                             " \t Celda[" + cell.getCellType() + "] " + j + "=<CELL_TYPE_STRING>" +
                                             cell.getStringCellValue());
                            excelRow.addData(Integer.valueOf(j), cell.getStringCellValue(), cell.getCellType()); //

                        } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                            LoggerUtil.debug("[ExcelParsingUtil]",
                                             " \t Celda[" + cell.getCellType() + "] " + j + "=<CELL_TYPE_NUMERIC>" +
                                             String.valueOf(cell.getNumericCellValue() ));
                            excelRow.addData(Integer.valueOf(j), String.valueOf(cell.getNumericCellValue()),
                                             cell.getCellType()); //

                        } else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
                            LoggerUtil.debug("[ExcelParsingUtil]",
                                             " \t Celda[" + cell.getCellType() + "] " + j + "=<CELL_TYPE_BOOLEAN>" +
                                             cell.getBooleanCellValue());
                            excelRow.addData(Integer.valueOf(j), String.valueOf(cell.getBooleanCellValue()),
                                             cell.getCellType()); //

                        } else {
                            LoggerUtil.debug("[ExcelParsingUtil]",
                                             " \t Celda[" + cell.getCellType() + "] " + j + "=<NOIDENTIFICADO>" +
                                             cell.getCellType());

                        }
                    } /*else {
                                    LoggerUtil.debug("[ExcelParsingUtil]"," \t Celda "+ j +"=<CELDA_NULL>" + cell );

                                }*/

                }

                document.addRow(excelRow);
                
            }

        }

        document.setSuccess(Boolean.TRUE);
        document.setMessage("Excel leído correctamente");

        String details = "";
        for (int i = 0; i < workbook.getNumberOfNames(); i++) {
            details = details + ", " + workbook.getNameAt(i).toString();
        }
        //details += ". Tamaño: " + (request.getDocument().length * 8) + " bytes";
        details += ". Fecha Envío: " + new Date();

        document.setDetails("Cantidad de hojas: " + workbook.getNumberOfSheets() + ". Info: " + details);
    }
    
    
    


    private void sampleData(ExcelData document) {
        //ExcelData excelData = new ExcelData();
        int i = 0;
        ExcelRow row = new ExcelRow();
        row.addData(Integer.valueOf(i++), "EVDESC"); //0
        row.addData(Integer.valueOf(i++), "EVTIPO"); //1
        row.addData(Integer.valueOf(i++), "FECINI"); //2
        row.addData(Integer.valueOf(i++), "FECFIN"); //3
        row.addData(Integer.valueOf(i++), "EVRAZO"); //4
        row.addData(Integer.valueOf(i++), "EVNIVE"); //5
        row.addData(Integer.valueOf(i++), "EVPROV"); //6
        row.addData(Integer.valueOf(i++), "EVSTYLE"); //7
        row.addData(Integer.valueOf(i++), "EVSTOR"); //8
        row.addData(Integer.valueOf(i++), "EVSKU"); //9
        row.addData(Integer.valueOf(i++), "EVPREC"); //10
        row.addData(Integer.valueOf(i++), "EVPORC"); //11
        row.addData(Integer.valueOf(i++), "EVPRPL"); //12
        row.addData(Integer.valueOf(i++), "EVTREC"); //13

        document.addRow(row);

        i = 0;
        row = new ExcelRow();
        row.addData(Integer.valueOf("0"), "LTS170 DICIEMBRE ENERO"); //0
        row.addData(Integer.valueOf("1"), "R"); //1
        row.addData(Integer.valueOf("2"), "50219"); //2

        row.addData(Integer.valueOf("4"), "3"); //4
        row.addData(Integer.valueOf("5"), "C"); //5

        row.addData(Integer.valueOf("12"), "1"); //12
        row.addData(Integer.valueOf("13"), "1"); //13
        document.addRow(row);


        for (int j = 13; j <= 50; j++) {
            i = 0;
            row = new ExcelRow();
            row.addData(9, "20557" + j); //9
            row.addData(10, "50"); //10
            row.addData(13, "3"); //13
            document.addRow(row);
        }
    }
        
}
