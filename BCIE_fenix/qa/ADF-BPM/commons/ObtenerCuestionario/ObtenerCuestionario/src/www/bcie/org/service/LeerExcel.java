package www.bcie.org.service;



import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import javax.xml.ws.BindingType;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.soap.SOAPBinding;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import www.bcie.org.javabeans.PreguntaEvidenciaType;
import www.bcie.org.javabeans.Reporte;

@WebService(portName = "Cuestionario12Bnd", targetNamespace="http://www.bcie.com/Cuestionario/LeerExcel", name="LeerExcel", serviceName="LeerExcel")
@BindingType(SOAPBinding.SOAP12HTTP_BINDING)
public class LeerExcel {

 /*   public static void main (String args[]){
        try {
            LeerExcel lee= new LeerExcel();
            
            lee.LeerDatos(new FileInputStream("C:\\Users\\luis-morales\\Documents\\PDF_BCIE\\XLCellDropDown2.xls"));
        } catch (IOException ex) {
            Logger.getLogger(LeerExcel.class.getName()).log(Level.SEVERE, null, ex);
        }
    } */

    @WebMethod
    public Reporte LeerDatos(@WebParam(name = "archivo")byte[] arrayBytes) throws FileNotFoundException, IOException { 
       
       InputStream archivo = new ByteArrayInputStream(arrayBytes);
       List<String> opciones = new ArrayList<String>();      
       PreguntaEvidenciaType pregunta = null;
           
  /*     try{
           FileOutputStream fos = new FileOutputStream(pathArchivo);
           BufferedOutputStream bos = new BufferedOutputStream(fos);
           bos.write(excelByte);
           bos.close();
           
           System.out.println("Recibiendo archivo: " + pathArchivo);
           
       }catch(IOException ioE){
           System.err.println(ioE);
           throw new WebServiceException(ioE);
       } */
       
        try {
            
            //POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream("C:\\Users\\luis-morales\\Documents\\PDF_BCIE\\XLCellDropDown2.xls"));
            POIFSFileSystem fs = new POIFSFileSystem(archivo);
            HSSFWorkbook wb = new HSSFWorkbook(fs);
            HSSFSheet sheet = wb.getSheetAt(0); 
            HSSFRow row;
            HSSFCell cell;

    int rows; // No of rows
    rows = sheet.getPhysicalNumberOfRows();
            
    System.out.println("Numero de filas: " + rows);

    int cols = 0; // No of columns
    int tmp = 0;

            if(rows < 0){
                
                return null;
            }
            List<PreguntaEvidenciaType> preguntasLst= new ArrayList<PreguntaEvidenciaType>();
    // This trick ensures that we get the data properly even if it doesn't start from first few rows
    for(int i = 0; i < rows; i++) {
       
        row = sheet.getRow(i);
        
        
        if(row != null) {
            if(sheet.getRow(i).getCell(4)!=null){                
                 
                 opciones.add(sheet.getRow(i).getCell(1).toString());
                 //StringBuilder sb = new StringBuilder();  
                 
                /* for(String respuesta : opciones){
                            StringBuilder append = sb.append(respuesta);                      
                        } */
                System.out.println("valor del registro: "+sheet.getRow(i).getCell(0)+" "+sheet.getRow(i).getCell(1)+
                        " "+sheet.getRow(i).getCell(2)+" "+Long.valueOf((int)sheet.getRow(i).getCell(4).getNumericCellValue()));
                 String  respuesta = opciones.get(opciones.size() -1);
                 pregunta= new PreguntaEvidenciaType(Long.valueOf((int)sheet.getRow(i).getCell(4).getNumericCellValue()), sheet.getRow(i).getCell(0).toString(), respuesta, sheet.getRow(i).getCell(2).toString(),new Long(0), null, null);
            //pregunta = new PreguntaEvidenciaType(sheet.getRow(i).getCell(0).toString(), sheet.getRow(i).getCell(4).toString(), sheet.getRow(i).getCell(2).toString());
            preguntasLst.add(pregunta);
          
            }
        }
    }
            
            

            System.out.println("Tamaño de la lista: "+preguntasLst.size());
            System.out.println("operacion: "+sheet.getRow(0).getCell(9).toString());
            
          Reporte reporte= new Reporte();
            reporte.setPreguntasLst(preguntasLst);
            reporte.setOperacion(sheet.getRow(0).getCell(9).toString());
            
            return reporte; 
    } catch(Exception ioe) {
        ioe.printStackTrace();
        return null;
    }
    }
}