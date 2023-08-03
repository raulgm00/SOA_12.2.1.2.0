package www.bcie.org.service;

import java.io.ByteArrayOutputStream;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;


import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javax.jws.WebParam;
import javax.jws.WebService;

import javax.naming.InitialContext;
import javax.jws.WebMethod;
import javax.naming.NamingException;


import javax.sql.DataSource;
import javax.xml.ws.BindingType;
import javax.xml.ws.soap.SOAPBinding;

import org.apache.poi.hssf.usermodel.DVConstraint;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFPalette;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;


import org.apache.xerces.impl.dv.util.Base64;
import www.bcie.org.javabeans.PreguntaEvidenciaType;


@WebService(name="ObtenerCuestionario", targetNamespace="http://www.bcie.com/Cuestionario/ObtenerCuestionario", portName="Cuestionario12Bnd", serviceName="CrearExcel", wsdlLocation = "WEB-INF/wsdl/CrearExcel.wsdl")
@BindingType(SOAPBinding.SOAP12HTTP_BINDING)
public class CuestionarioExcelBS {
    
    private static Connection conn = null;
    private static PreparedStatement ps1 = null;
    private static PreparedStatement ps2 = null;
    private static ResultSet rs1 = null;
    private static ResultSet rs2 = null;
          
    
    @WebMethod
    public byte[] crearExcel(@WebParam(name = "cuestionario") List<PreguntaEvidenciaType> preguntasLst,
                                @WebParam(name = "idOperacion") String numOperacion) throws FileNotFoundException{
            
            
            List<PreguntaEvidenciaType> principioLst = new ArrayList<>();
            List<PreguntaEvidenciaType> criterioLst = new ArrayList<>();
            
             HSSFWorkbook hssWorkBook= new HSSFWorkbook();
             Workbook workbook = hssWorkBook;
                 
             
             Sheet sheet = workbook.createSheet("Data Validation");
                
                 
            CellStyle style = workbook.createCellStyle();
            Font cellFont = workbook.createFont();
            cellFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
            style.setFont(cellFont);
            style.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM); 
            style.setBorderRight(HSSFCellStyle.BORDER_MEDIUM); 
            style.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM); 
            style.setBorderTop(HSSFCellStyle.BORDER_MEDIUM); 
            style.setFillBackgroundColor(HSSFColor.BLUE.index);
            style.setFillForegroundColor(colorMostaza(hssWorkBook).getIndex());
            style.setFillPattern(CellStyle.SOLID_FOREGROUND);
            style.setAlignment(CellStyle.ALIGN_CENTER);
                 
             //Se aplica cabecera del archivo
             
             Map<String, Object[]> data = new TreeMap<String, Object[]>();
             data.put("1", new Object[] {"Pregunta", "Respuesta", "Justificacion/Responsable", "Evidencia"});
             
             //Se itera la cabecera para setarlo a la hoja
             Set<String> keyset = data.keySet();
             int rownum = 0;
             for (String key : keyset)
             {
             
             Row row = sheet.createRow(rownum++);
             Object [] objArr = data.get(key);
             int cellnum = 0;
             for (Object obj : objArr)
             {
              
             Cell cell = row.createCell(cellnum++);
             cell.setCellStyle(style);
             
             if(obj instanceof String)
                 cell.setCellValue((String)obj);
             else if(obj instanceof Integer)
                 cell.setCellValue((Integer)obj);
             }
             Cell cell6=row.createCell(9);
                    cell6.setCellValue(numOperacion);
             }
             
             //SE INVOCA METODO PARA OBTENER EL PRODUCTO        
             String noProducto = obtenerProducto(numOperacion);
             //SE INVOCA METODO PARA OBTENER PRINCIPIOS QUE PERTENECEN A LA VERSION ACTIVA        
             principioLst = obtenerPrincipios(noProducto,numOperacion);
             
             //SE INVOCA METODO PARA OBTENER CRITERIOS QUE PERTENECEN A LA VERSION ACTIVA
             criterioLst = obtenerCriterios(noProducto,numOperacion);
             
             //INICIA CREACION DE EXCEL
                      //Se crean las Listas
                      
                 if(principioLst.size() != 0){
                                          
                      int contPregunta = 0;
                      int contPrinci = 1;
                      
                     Integer contadorHojaOculta = 1;
                      
                      for(PreguntaEvidenciaType principio : principioLst){
                            int rowPrincipio ;
                            int rowCriterio ;
                            int contador ;
                            rowPrincipio = rownum++;
                            contador = 0;
                            int contCrit = 1;
                     
                          for(PreguntaEvidenciaType criterio : criterioLst ){
                              contPregunta = 0;
                              
                              rowCriterio = rownum++;
                              int no_pregunta=1;
                              
                              for(PreguntaEvidenciaType preguntas : preguntasLst){
                                  List<String> lstOpcion = new ArrayList<>();
                                  
                                  lstOpcion = preguntas.getOpciones();
                                  PreguntaEvidenciaType pregunta= new PreguntaEvidenciaType(preguntas.getIdPregunta(),preguntas.getPregunta(), preguntas.getRespuesta(), preguntas.getJustificacion(), preguntas.getEvidencia(), preguntas.getFilename(),lstOpcion);
                                
                                  if(preguntas.getSeccion().equalsIgnoreCase(principio.getSeccion())){
                                          
                                      if(preguntas.getSubSeccion().equalsIgnoreCase(criterio.getSubSeccion())){
                                            
                                            
                                          contPregunta++;
                                          int cont =0;    
                                             
                                             
                                                 // generando renglon "pregunta"
                                               if("" != preguntas.getPregunta().toString()){
                                                 Row row = sheet.createRow(rownum++);
                                                 Cell cell1= row.createCell(0);
                                                 cell1.setCellValue(principio.getNumOrdenPrincipio()+"."+criterio.getNumOrdenCriterio()+"."+no_pregunta+" "+preguntas.getPregunta());
                                                 cell1.setCellStyle(combinaColorCeldas(workbook, cont));

                                                 Cell cell3=row.createCell(1);
                                                 cell3.setCellStyle(combinaColorCeldas(workbook, cont));
                                                 cell3.setCellValue(preguntas.getRespuesta());
                                              
                                                 Cell cell2=row.createCell(2);
                                                 cell2.setCellValue(preguntas.getJustificacion());
                                                 cell2.setCellStyle(combinaColorCeldas(workbook, cont));                                
                                                 
                                              
                                                 //Se agrega valor de columna evidencia, donde se muestra el nombre del documento
                                                 Cell cell4= row.createCell(3);
                                                 cell4.setCellValue(preguntas.getFilename());
                                                 cell4.setCellStyle(combinaColorCeldas(workbook, cont));
                                                 
                                              
                                                 Cell cell5=row.createCell(4);
                                                 cell5.setCellValue(preguntas.getIdPregunta());
                                                 //cell4.setCellStyle(combinaColorCeldas(workbook, cont));
                                                 cell5.setCellStyle(ocultarCelda(workbook));
                                              
                                              if("4".equals(preguntas.getTipoPregunta().toString()) ||
                                                 "3".equals(preguntas.getTipoPregunta().toString())){
                                                 if (lstOpcion!=null) {
                                                     if (lstOpcion.size()>0) {
                                                          crearComboExcel(workbook, sheet ,rownum-1,lstOpcion, preguntas.getIdPregunta(), contadorHojaOculta++);
                                                     }
                                                }
                                              }
                                                 cont++;
                                                 no_pregunta++;
                                               }
                                        }
                                      }//fin del If
                              }//fin del for preguntas
                              
                              if(contPregunta > 0){
                                  creaSubCabeceras(sheet, rowCriterio++, principio.getNumOrdenPrincipio()+"."+criterio.getNumOrdenCriterio()+" "+ criterio.getSubSecDescripcion(),workbook,hssWorkBook);
                                  sheet.addMergedRegion(new CellRangeAddress(rowCriterio-1,rowCriterio-1,0,3));
                                  contador++;
                                  contCrit++;
                              }else{
                                    rownum--;
                                }
                              contPregunta = 0;
                                     
                          } //fin del for criterio
                          
                     if(contador > 0){
                         creaCabeceras(sheet, rowPrincipio++,  principio.getNumOrdenPrincipio()+"."+principio.getSecDescripcion(),workbook,hssWorkBook);
                         sheet.addMergedRegion(new CellRangeAddress(rowPrincipio-1,rowPrincipio-1,0,3));
                         contPrinci++;
                     }else{
                         rownum--;
                     }
                     
                          
                      
                 }

                     sheet.autoSizeColumn(0);
                     sheet.autoSizeColumn(1);
                     sheet.autoSizeColumn(2);
                     sheet.autoSizeColumn(3);
                     sheet.autoSizeColumn(4);
                     
                     sheet.setColumnHidden(4, true);
                     sheet.setColumnHidden(9, true);
                      
                      //TERMINA CREACION DE EXCEL
                 }
            
             ByteArrayOutputStream fileOut = new ByteArrayOutputStream();
            
                                 
               try {
                         
                              workbook.write(fileOut);
                              fileOut.close();
                              return fileOut.toByteArray();
                   
                      } catch (IOException e) {
                              //e.printStackTrace();
                              return null;
                      }
                 
         
        }  

        
/*        private List <String>  creaListaRespuestaOpc(String lstOpciones){
            List<String> listOpc= new ArrayList<String>();
            
            if(lstOpciones != null && !lstOpciones.equals("")){
                String[] parts = lstOpciones.split(",");
                
                if(parts.length != 0 ){
                    for(int i=0; i<parts.length; i++){
                        listOpc.add(parts[i]);
                    }
                }
            }
            return listOpc;
        }*/
        
/*        private void comboBoxRespuesta(Sheet sheet,int pocision, List <String> opciones){
        
        CellRangeAddressList addressList = new CellRangeAddressList(pocision, pocision, 1, 1);
		DVConstraint dvConstraint = DVConstraint
				.createExplicitListConstraint( opciones.toArray(new String[opciones.size()]));
		DataValidation dataValidation = new HSSFDataValidation(addressList,
				dvConstraint);
		dataValidation.setSuppressDropDownArrow(false);
                
		sheet.addValidationData(dataValidation);
        }*/
        
        private void crearComboExcel(Workbook workbook, Sheet sheet,int pocision, List <String> opciones, Long idPregunta, Integer contadorHojaOculta){
        String cadenaConcatenada = opciones.get(0);
        List <String> listaSeparada = creaListaRespuestaOpc(cadenaConcatenada);
        Sheet oculto = workbook.createSheet("hoja" + idPregunta);
            for (int i = 0, length= listaSeparada.size(); i < length; i++) {
               String name = listaSeparada.get(i);
               Row row = oculto.createRow(i);
               Cell cell = row.createCell(0);
               cell.setCellValue(name);
             }
             Name namedCell =  workbook.createName();
                namedCell.setNameName("hoja" + idPregunta);
             namedCell.setRefersToFormula("hoja" + idPregunta+"!$A$1:$A$" + listaSeparada.size());
             DVConstraint constraint = DVConstraint.createFormulaListConstraint("hoja" + idPregunta);
             CellRangeAddressList addressList = new CellRangeAddressList(pocision, pocision, 1, 1);
             HSSFDataValidation validation = new HSSFDataValidation(addressList, constraint);
             workbook.setSheetHidden(contadorHojaOculta, true);
             sheet.addValidationData(validation);
             
    }
        private void comboBoxRespuestaMultiple(Sheet sheet,int pocision,String[] respuesta){
        
        CellRangeAddressList addressList = new CellRangeAddressList(pocision, pocision, 1, 1);
		DVConstraint dvConstraint = DVConstraint
				.createExplicitListConstraint(respuesta);
		DataValidation dataValidation = new HSSFDataValidation(addressList,
				dvConstraint);
		dataValidation.setSuppressDropDownArrow(false);
		sheet.addValidationData(dataValidation);
        }
         private void creaCabeceraOperacion(Sheet sheet,int pocision,String mensaje,Workbook workbook,HSSFWorkbook  HSSFworkbook){
            
        Row rowSector = sheet.createRow(pocision);
        Cell cellSector= rowSector.createCell(0);
        cellSector.setCellValue(mensaje);
        cellSector.setCellStyle(getEstiloOperacion(workbook,HSSFworkbook));
        
        }
        
        private void creaCabeceras(Sheet sheet,int pocision,String mensaje,Workbook workbook,HSSFWorkbook  HSSFworkbook){
            
        Row rowSector = sheet.createRow(pocision);
        Cell cellSector= rowSector.createCell(0);
        cellSector.setCellValue(mensaje);
        cellSector.setCellStyle(getEstiloTitulo(workbook,HSSFworkbook));
        
        }
        
        private void creaSubCabeceras(Sheet sheet,int pocision,String mensaje,Workbook workbook,HSSFWorkbook  HSSFworkbook){
            
        Row rowSector = sheet.createRow(pocision);
        Cell cellSector= rowSector.createCell(0);
        cellSector.setCellValue(mensaje);
        cellSector.setCellStyle(getEstiloSubTitulo(workbook,HSSFworkbook));
        
        }
        
       private CellStyle getEstiloOperacion(Workbook workbook,HSSFWorkbook  HSSFworkbook) {
        final CellStyle cellStyle = workbook.createCellStyle();
        final Font cellFont = workbook.createFont();
        cellFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        cellStyle.setFont(cellFont);
        cellStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
        cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
        return cellStyle;
    }   
     private CellStyle getEstiloTitulo(Workbook workbook,HSSFWorkbook  HSSFworkbook) {
        final CellStyle cellStyle = workbook.createCellStyle();
        final Font cellFont = workbook.createFont();
        cellFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        cellStyle.setFont(cellFont);
        cellStyle.setFillForegroundColor(colorRGB(HSSFworkbook).getIndex());
        cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
        return cellStyle;
    }

     private CellStyle getEstiloSubTitulo(Workbook workbook,HSSFWorkbook  HSSFworkbook) {
        final CellStyle cellStyle = workbook.createCellStyle();
        final Font cellFont = workbook.createFont();
        cellFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        cellFont.setColor(IndexedColors.WHITE.getIndex());
        cellStyle.setFont(cellFont);
        cellStyle.setFillForegroundColor(colorRGBAzul(HSSFworkbook).getIndex());
        cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
        return cellStyle;
    }

     private CellStyle combinaColorCeldas(Workbook workbook, int indice){
         
        final CellStyle cellStyle = workbook.createCellStyle();
            if ( (indice % 2) == 0)
                {
                   cellStyle.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
                }else{
                  cellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
                     }

        cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
        return cellStyle;
     
     }
     
     private HSSFColor colorRGB(HSSFWorkbook  workbook){
     
         HSSFPalette palette = workbook.getCustomPalette();    
         
       return palette.findSimilarColor( 254,  239,  37); 
     }
     
     private HSSFColor colorRGBAzul(HSSFWorkbook  workbook){
     
         HSSFPalette palette = workbook.getCustomPalette();    
         
       return palette.findSimilarColor( 4,  15,  77); 
     }

      private HSSFColor colorMostaza(HSSFWorkbook  workbook){
     
         HSSFPalette palette = workbook.getCustomPalette();    
         
       return palette.findSimilarColor( 255,  199,  65); 
     }
      
       private CellStyle ocultarCelda(Workbook workbook){
       
            final CellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setHidden(false);
            cellStyle.setWrapText(true);
            cellStyle.setLocked(true);
            return cellStyle;
       }  
       
       
    private List<PreguntaEvidenciaType> obtenerPrincipios(String VAR_ID_PRODUCTO, String VAR_ID_OPERACION){
        List<PreguntaEvidenciaType> listaPrincipios = null;
        try{
            listaPrincipios = new ArrayList<>();
            conn = getConnection();
             String sqlPrincipio = 
                "Select fT_Prn.id As id_Principio\n" +  
                "         , fT_Prn.Descripcion As Descripcion_Principio\n" +                 
                "         , nvl(fCfg.Num_Orden,1) As Num_Orden_Principio\n" +                     
                "      From fenix.Cuestionario fCst\n" +                
                "      Join fenix.Pregunta fPrg on fCst.id_Cuestionario = fPrg.id_Cuestionario\n" +                 
                "      Join fenix.cat_Preguntas fT_Prg on fT_Prg.id = fPrg.id_Cat_Pregunta\n" +                 
                "      Join fenix.cat_Principio fT_Prn On fT_Prn.Id = fT_Prg.Id_Principio \n" +                 
                " Left Join (\n" +                 
                "                Select fR_Prn_Ver.id_Version\n" +                 
                "                     , fR_Prn_Ver.id_Principio\n" +                  
                "                     , fR_Prd_Prn.Num_Orden\n" +                 
                "                  From fenix.tre_Principio_Version fR_Prn_Ver\n" +                     
                "            Inner Join fenix.tre_Producto_Principio fR_Prd_Prn\n" +                 
                "                    On fR_Prd_Prn.id_Principio = fR_Prn_Ver.Id_Principio\n" +                 
                "                   And fR_Prd_Prn.id_Producto = " + VAR_ID_PRODUCTO + "\n" +                
                "           ) fCfg\n" +                 
                "        On fCfg.id_Version = fCst.id_Version_Cuestionario\n" +           
                "       And fCfg.id_Principio = fT_Prg.Id_Principio\n" +               
                "     Where id_Operacion = "+ VAR_ID_OPERACION + " \n" +                
                "  Group by fT_Prn.id\n" +               
                "         , fT_Prn.Descripcion\n" +       
                "         , fCfg.Num_Orden\n" +               
                "  Order by fCfg.Num_Orden";
            
            ps1 = conn.prepareStatement(sqlPrincipio);
            
            rs1 = ps1.executeQuery();
            
            while (rs1.next()){
                
                PreguntaEvidenciaType preguntaEvidenciaType = new PreguntaEvidenciaType();
                
                preguntaEvidenciaType.setSeccion(rs1.getString(1)); ;
                preguntaEvidenciaType.setSecDescripcion(rs1.getString(2));
                preguntaEvidenciaType.setNumOrdenPrincipio(rs1.getString(3));
                listaPrincipios.add(preguntaEvidenciaType);
            }
        }catch(Exception exception){                                                                                                                        
             exception.printStackTrace();
     } finally {
             
          try {
                 if (conn != null) {
                   //conn.rollback();
                   conn.close();
                 }
               }
               catch (Exception e) {
                 e.printStackTrace();
               }
     }
        return listaPrincipios;
    }
    
    private List<PreguntaEvidenciaType> obtenerCriterios(String VAR_ID_PRODUCTO,String VAR_ID_OPERACION){
     List<PreguntaEvidenciaType> listaCriterios = null;
     try{
         listaCriterios = new ArrayList<>();
         conn = getConnection();
          String sqlCriterio = 
             "Select fT_Crt.id As id_Criterio\n" +
             "         , fT_Crt.Descripcion As Descripcion_Criterio\n" + 
             "         , nvl(fCfg.Num_Orden,1) As Num_Orden_Criterio\n" + 
             "      From fenix.Cuestionario fCst\n" + 
             "      Join fenix.Pregunta fPrg on fCst.id_Cuestionario = fPrg.id_Cuestionario\n" + 
             "      Join fenix.cat_Preguntas fT_Prg on fT_Prg.id = fPrg.id_Cat_Pregunta\n" + 
             "      Join fenix.cat_Criterio fT_Crt On fT_Crt.Id = fT_Prg.Id_Criterio\n" +
             "Left Join (\n" + 
             "                Select fR_Crt_Ver.id_Version\n" + 
             "                     , fR_Crt_Ver.id_Criterio\n" +
             "                     , fR_Prd_Crt.Num_Orden\n" + 
             "                  From fenix.tre_Criterio_Version fR_Crt_Ver\n" + 
             "            Inner Join fenix.tre_Producto_Criterio fR_Prd_Crt\n" + 
             "                    On fR_Prd_Crt.id_Criterio = fR_Crt_Ver.Id_Criterio\n" + 
             "                   And fR_Prd_Crt.id_Producto = " + VAR_ID_PRODUCTO  + " \n" + 
             "              Order by fR_Crt_Ver.id_Version, fR_Prd_Crt.Num_Orden\n" + 
             "           ) fCfg\n" + 
             "        On fCfg.id_Version = fCst.id_Version_Cuestionario\n" +
             "       And fCfg.id_Criterio = fT_Crt.Id\n" +
             "     Where id_Operacion = " +  VAR_ID_OPERACION + " \n" + 
             "  Group by fT_Crt.id\n" +
             "         , fT_Crt.Descripcion\n" + 
             "         , fCfg.Num_Orden\n" + 
             "  Order by fCfg.Num_Orden";
         ps2 = conn.prepareStatement(sqlCriterio);
         
         rs2 = ps2.executeQuery();
         
         while (rs2.next()){
             
             PreguntaEvidenciaType preguntaEvidenciaType = new PreguntaEvidenciaType();
             
             preguntaEvidenciaType.setSubSeccion(rs2.getString(1)); ;
             preguntaEvidenciaType.setSubSecDescripcion(rs2.getString(2));
             preguntaEvidenciaType.setNumOrdenCriterio(rs2.getString(3));
             listaCriterios.add(preguntaEvidenciaType);
         }
     }catch(Exception exception){                                                                                                                        
          exception.printStackTrace();
    } finally {
          
       try {
              if (conn != null) {
                //conn.rollback();
                conn.close();
              }
            }
            catch (Exception e) {
              e.printStackTrace();
            }
    }
     return listaCriterios;
    }
    
    private String obtenerProducto(String VAR_ID_OPERACION){
        String noProducto=null;
        try{
         
         conn = getConnection();
          String sqlProducto = 
             "SELECT ID_PRODUCTO \n"+
             "FROM FENIX.OPERACION \n"+
             "WHERE ID_OPERACION ="+ VAR_ID_OPERACION+" ";
         ps2 = conn.prepareStatement(sqlProducto);
         
         rs2 = ps2.executeQuery();
          
         
         while (rs2.next()){
             
             PreguntaEvidenciaType preguntaEvidenciaType = new PreguntaEvidenciaType();    
             preguntaEvidenciaType.setIdProducto(rs2.getString(1)); 
             noProducto = preguntaEvidenciaType.getIdProducto();
         }
     }catch(Exception exception){                                                                                                                        
          exception.printStackTrace();
    } finally {
          
       try {
              if (conn != null) {
                //conn.rollback();
                conn.close();
              }
            }
            catch (Exception e) {
              e.printStackTrace();
            }
    }
     
     return noProducto;
    }
    
    private Connection getConnection(){
     try{
         InitialContext context = new InitialContext();
         DataSource datasource = (DataSource) context.lookup("jdbc/OperacionesDB");
         conn = datasource.getConnection();
     }catch(NamingException nex){
         nex.printStackTrace();
         }catch(SQLException e){
             e.printStackTrace();
         }
    return conn;
         }
    
    private List <String>  creaListaRespuestaOpc(String lstOpciones){
                 List<String> listOpc= new ArrayList<String>();
                if(lstOpciones != null && !lstOpciones.equals("")){
                    System.out.println(lstOpciones);
                    String[] parts = lstOpciones.split(",");
                    
                    if(parts.length != 0 ){
                        for(int i=0; i<parts.length; i++){
                            System.out.println("Parts : " + parts[i]);
                            listOpc.add(parts[i]);
                        }
                     }
                 }
                
                 return listOpc;
            }
   
    
}