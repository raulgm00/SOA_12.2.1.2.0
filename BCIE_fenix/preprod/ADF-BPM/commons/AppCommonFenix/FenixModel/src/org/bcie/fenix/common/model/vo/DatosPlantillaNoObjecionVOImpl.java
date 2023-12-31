package org.bcie.fenix.common.model.vo;

import java.io.IOException;
import java.io.InputStream;

import java.io.OutputStream;

import java.math.BigDecimal;

import java.sql.ResultSet;

import java.sql.SQLException;

import java.sql.Timestamp;

import java.text.DateFormat;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import java.util.Map;

import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.JboException;
import oracle.jbo.Key;
import oracle.jbo.Row;
import oracle.jbo.domain.BlobDomain;
import oracle.jbo.server.ViewObjectImpl;
import oracle.jbo.server.ViewRowImpl;
import oracle.jbo.server.ViewRowSetImpl;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.bcie.fenix.common.model.am.FenixAMImpl;
import org.bcie.fenix.common.model.am.FenixAdquisicionAMImpl;
import org.bcie.fenix.common.model.vo.common.DatosPlantillaNoObjecionVO;

// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Wed Jul 04 11:22:38 CDT 2018
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class DatosPlantillaNoObjecionVOImpl extends ViewObjectImpl implements DatosPlantillaNoObjecionVO {
    
    private static ADFLogger logger = null;
    

    public DatosPlantillaNoObjecionVOImpl() {
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }
    
    
    public Boolean validarArchivoExcel(BlobDomain fileData, Map datosOperaciones){
        logger.warning("Inicia metodo validarArchivoExcel");
        Boolean resultado = Boolean.FALSE;
        Map mapaDatos = new HashMap();
        
          String tipoPlantilla = (datosOperaciones.get("tipoPlantilla") != null)
                               ? (String)datosOperaciones.get("tipoPlantilla") : "";
          
        
            Long idNoObjecion = (datosOperaciones.get("idNoObjecion") != null)
                               ? (Long)datosOperaciones.get("idNoObjecion") : null;
        
            switch(tipoPlantilla){
            case "AVISO" :
                cargarDatosPlantillaAviso(fileData);
            break;
            case "INFORME_RESULTADOS" :                            
                cargarDatosPlantillaInformeResultados(fileData, idNoObjecion);
            break;
            case "CONTRATO" :
                cargarDatosPlantillaContrato(fileData, idNoObjecion);
            break;
            
            }
      
       logger.warning("termina metodo validarArchivoExcel");
       return resultado;
    }

    public void cargarDatosPlantillaInformeResultados(BlobDomain fileData, Long idNoObjecion){
        logger.warning("inicia metodo cargarDatosPlantillaInformeResultados idNoObjecion: "+idNoObjecion);
        InputStream in = null;
        XSSFWorkbook workbook = null;
        XSSFSheet sheet = null;
        String nombre = "";
        String nacionalidad = "";
        BigDecimal monto = null;
        
        FenixAMImpl fenixAMImpl = null;
        fenixAMImpl = (FenixAMImpl) this.getRootApplicationModule();
        FenixAdquisicionAMImpl adquisiscionesAMImpl = null;
        
        adquisiscionesAMImpl = (FenixAdquisicionAMImpl)fenixAMImpl.getFenixAdquisicionAM();
        adquisiscionesAMImpl.getOferentesVO().limpiarVO();
        //adquisiscionesAMImpl.getOferentesVO().eliminarOferentesByNoObjecion(idNoObjecion);
        
        try{
          in = fileData.getBinaryStream();
          workbook = new XSSFWorkbook(in);
          sheet = workbook.getSheetAt(0);
        }catch(Exception e){
            logger.warning("ha ocurrido una Excepcion en metodo validarArchivoExcel() ->", e);
        }
        
        Iterator<org.apache.poi.ss.usermodel.Row> rowIterator = sheet.iterator();
        org.apache.poi.ss.usermodel.Row row;
        
        int numFila = 0;
        logger.warning("Iterando celdas del documento");
        
        try{
            while (rowIterator.hasNext()) {
                row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                 numFila = numFila +1;
                logger.warning("------------ fila numero : "+numFila+" -------------------"); 
                //se obtiene las celdas por fila
                 Cell cell; int numColumna = 0;
                //se recorre cada celda
                
                 while (cellIterator.hasNext()) {
                     // se obtiene la celda en específico y se la imprime
                     numColumna = numColumna +1;
                     cell = cellIterator.next();
                     
                     if(numFila >= 4){
                         logger.warning("fila: "+numFila+" columna: "+numColumna+" valorCelda: "+getCellValueString(cell)+" | ");
                         
                         if(numColumna == 1){
                              nombre = getCellValueString(cell);
                          }
                            
                                 
                         if(numColumna == 2){
                              nacionalidad = getCellValueString(cell);
                         }
                         /*se retira por actualizacion a peticion de FNXII-7290
                         if(numColumna == 5){
                             String montoString = getCellValueString(cell);
                             monto = (montoString.equals("null") || montoString.isEmpty()) ? null : new BigDecimal(montoString);
                          }*/                                                
                     }
    
                  }
                
                if(numFila >= 4){                            
                    //cargarOferentesByExcel(idNoObjecion, nombre, nacionalidad, monto);
                    cargarOferentesByExcel(idNoObjecion, nombre, nacionalidad);                   
                }
                
              }
        
        }catch(Exception e){
            logger.warning("ha ocurrido un error al iterar las celdas del archivo ->", e);            
             throw new JboException("Ha ocurrido un error al cargar los datos del archivo");
        }
        
        
        
        
        
        logger.warning("termina metodo cargarDatosPlantillaInformeResultados");
    }
    
    
    public Row cargarOferentesByExcel(Long idNoObjecion, String nombre, String nacionalidad){
        logger.warning("Inicia metodo cargarOferentesByExcel");
        logger.warning("idNoObjecion: "+idNoObjecion);
        logger.warning("nombre: "+nombre);
        logger.warning("nacionalidad: "+nacionalidad);
        Row rowAdjudicatario = null;                        
                
        if(idNoObjecion == null || nombre.isEmpty() || nombre.equals("null") || nacionalidad.isEmpty() || nacionalidad.equals("null") ){            
            logger.warning("Inf, no se recibieron todos los parametros , no se insertara fila");
        }else{
            
            logger.warning("ok cargando Fila...");  
            
            FenixAMImpl fenixAMImpl = null;
            fenixAMImpl = (FenixAMImpl) this.getRootApplicationModule();
            FenixAdquisicionAMImpl adquisiscionesAMImpl = null;
            
            adquisiscionesAMImpl = (FenixAdquisicionAMImpl)fenixAMImpl.getFenixAdquisicionAM();
                       
            adquisiscionesAMImpl = (FenixAdquisicionAMImpl)fenixAMImpl.getFenixAdquisicionAM();
            Row oferenteRow = adquisiscionesAMImpl.getNoObjecionVO().crearRowOferenteByExcel(idNoObjecion, nombre, nacionalidad);

            // rowAdjudicatario =  adquisiscionesAMImpl.getNoObjecionVO().adjudicarOferenteByExcel(oferenteRow, nacionalidad, monto);
            // adquisiscionesAMImpl.getOferentesVO().eliminarOferente(oferenteRow);
            
            
        }
                
        logger.warning("termina metodo cargarOferentesByExcel");
        return rowAdjudicatario;
    }
    


    public void cargarDatosPlantillaContrato(BlobDomain fileData, Long idNoObjecion){
        logger.warning("inicia metodo cargarDatosPlantillaContrato");
        
        InputStream in = null;
        XSSFWorkbook workbook = null;
        XSSFSheet sheet = null;
        String nombre = "";
        String nacionalidad = "";
        BigDecimal monto = null;
        
        
        try{
          in = fileData.getBinaryStream();
          workbook = new XSSFWorkbook(in);
          sheet = workbook.getSheetAt(0);
        }catch(Exception e){
            logger.warning("ha ocurrido una Excepcion en metodo validarArchivoExcel() ->", e);
        }
        
        Iterator<org.apache.poi.ss.usermodel.Row> rowIterator = sheet.iterator();
        org.apache.poi.ss.usermodel.Row row;
        
        int numFila = 0;
        logger.warning("Iterando celdas del documento");
        
        try{
            
            while (rowIterator.hasNext()) {
                row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                 numFila = numFila +1;
                logger.warning("------------ fila numero : "+numFila+" -------------------"); 
                //se obtiene las celdas por fila
                 Cell cell; int numColumna = 0;
                //se recorre cada celda
                
                 while (cellIterator.hasNext()) {
                     // se obtiene la celda en específico y se la imprime
                     numColumna = numColumna +1;
                     cell = cellIterator.next();
                     
                     if(numFila >= 4){
                         logger.warning("fila: "+numFila+" columna: "+numColumna+" valorCelda: "+getCellValueString(cell)+" | ");
                         
                         if(numColumna == 1){
                              nombre = getCellValueString(cell);
                          }
                            
                                 
                         if(numColumna == 2){
                              nacionalidad = getCellValueString(cell);
                         }
                         
                         if(numColumna == 5){
                             String montoString = getCellValueString(cell);
                             monto = (montoString.equals("null") || montoString.isEmpty()) ? null : new BigDecimal(montoString);
                          }
                                                  
                     }
            
                  }
                
                  if(numFila >= 4)
                      verificarExisteAdjudicatario(idNoObjecion, nombre, nacionalidad, monto);
                    // cargarContratosAdjudicatarios(nombre, nacionalidad, monto);
              }
            
        
        }catch(Exception e){
        logger.warning("ha ocurrido un error al iterar las celdas del archivo ->", e);
        }
        
        logger.warning("termina metodo cargarDatosPlantillaContrato");
    }


    public void verificarExisteAdjudicatario(Long idNoObjecion, String contNombre, String contNacionalidad, BigDecimal contMonto){
        logger.warning("inicia metodo verificarRowsContrato");
      
        FenixAMImpl fenixAMImpl = null;
        fenixAMImpl = (FenixAMImpl) this.getRootApplicationModule();
        FenixAdquisicionAMImpl adquisiscionesAMImpl = null;        
        adquisiscionesAMImpl = (FenixAdquisicionAMImpl)fenixAMImpl.getFenixAdquisicionAM();
        Row nuevoAdjudicatario = null;
        Long idNuevoAdjudicatario = null;
        Long idAdjudicatario = null;
        String adjNombre = null;
        String adjNacionalidad = null;
        BigDecimal adjMonto = null;
      
        if(contNombre.isEmpty() || contNombre.equals("null") || contNacionalidad.isEmpty() || contNacionalidad.equals("null") || contMonto == null){        
             logger.warning("No se recuperaron todos los parametros no se tomara en cuenta el registro");
        }else{
                                       
            Row[] rowsAdjudicatarioContratos = adquisiscionesAMImpl.getAdjudicatariosContratoVO().getAllRowsInRange();
    
            Boolean coincidenciaRow = Boolean.FALSE;
            
            for(Row rowAdj : rowsAdjudicatarioContratos){                            
                
                 idAdjudicatario = (rowAdj.getAttribute("Id") == null) ? null :(Long)rowAdj.getAttribute("Id");
                 adjNombre = (rowAdj.getAttribute("Nombre") == null) ? "" :(String)rowAdj.getAttribute("Nombre");
                 adjNacionalidad = (rowAdj.getAttribute("Nacionalidad") == null) ? "" : (String)rowAdj.getAttribute("Nacionalidad");
                 adjMonto = (rowAdj.getAttribute("Monto") == null) ? new BigDecimal(0) : (BigDecimal)rowAdj.getAttribute("Monto");          
             
                 logger.warning("Iteracion idAdjudicatario"+idAdjudicatario+" nombre: "+adjNombre);
             
                    if(adjNombre.equals(contNombre) && adjNacionalidad.equals(contNacionalidad) && (adjMonto.compareTo(contMonto) == 0)){
                        logger.warning("ok el adjudicatario id: "+idAdjudicatario+" nombre:"+contNombre+" se encontro en la tabla de contratados");
                        cargarContratosAdjudicatarios(contNombre, contNacionalidad, contMonto, idAdjudicatario );                                                                       
                        adquisiscionesAMImpl.getAdjudicatariosContratoVO().eliminarAdjudicatarioContrato(rowAdj);  
                        break;
                    }else{
                        logger.warning("no coincide el adjudicatario "+contNombre+" no se agregara a contratados");
                    }
            }
            
            if(!coincidenciaRow){            
                // logger.warning("se agregara oferente - adjudicara y contratara");
                // nuevoAdjudicatario = cargarOferentesByExcel(idNoObjecion,  contNombre,  contNacionalidad,  contMonto);                 
                // idNuevoAdjudicatario = (null == nuevoAdjudicatario.getAttribute("Id")) ? null :(Long)nuevoAdjudicatario.getAttribute("Id");              
            }
                                                  
        }
        
                            
        logger.warning("termina metodo verificarRowsContrato");
    }


    public void cargarContratosAdjudicatarios( String nombre, String nacionalidad, BigDecimal monto,Long id){
        logger.warning("Inicia metodo cargarContratosAdjudicatarios");
        
        logger.warning("nombre: "+nombre);
        logger.warning("nacionalidad: "+nacionalidad);
        logger.warning("monto: "+monto);
        
                
        if(nombre.isEmpty() || nombre.equals("null") || nacionalidad.isEmpty() || nacionalidad.equals("null") || monto == null){
            logger.warning("Inf, no se recibieron todos los parametros , no se insertara fila");
        }else{
            
            logger.warning("ok cargando Fila...");  
            
            FenixAMImpl fenixAMImpl = null;
            fenixAMImpl = (FenixAMImpl) this.getRootApplicationModule();
            FenixAdquisicionAMImpl adquisiscionesAMImpl = null;
            
            adquisiscionesAMImpl = (FenixAdquisicionAMImpl)fenixAMImpl.getFenixAdquisicionAM();
            adquisiscionesAMImpl.getContratadosAdjudicatariosVO().cargarContratados(nombre,nacionalidad,monto,id );

        }
                
        logger.warning("termina metodo cargarContratosAdjudicatarios");
    }
    
  
    public void verificarRowsContrato(){
        logger.warning("inicia metodo verificarRowsContrato");
      
        FenixAMImpl fenixAMImpl = null;
        fenixAMImpl = (FenixAMImpl) this.getRootApplicationModule();
        FenixAdquisicionAMImpl adquisiscionesAMImpl = null;
        
        adquisiscionesAMImpl = (FenixAdquisicionAMImpl)fenixAMImpl.getFenixAdquisicionAM();
      
          Row[] rowsAdjudicatarioContratos = adquisiscionesAMImpl.getAdjudicatariosContratoVO().getAllRowsInRange();
          Row[] rowsContratadosAdjudicatarios = adquisiscionesAMImpl.getContratadosAdjudicatariosVO().getAllRowsInRange();
        
        int contador = 100;
        
        for(Row rowAdj : rowsAdjudicatarioContratos){
          
            Boolean coincidenciaRow = Boolean.FALSE;
            
            contador = contador +1;
            
            Long idAdjudicatario = (rowAdj.getAttribute("Id") == null) ? null :(Long)rowAdj.getAttribute("Id");
            String adjNombre = (rowAdj.getAttribute("Nombre") == null) ? "" :(String)rowAdj.getAttribute("Nombre");
            String adjNacionalidad = (rowAdj.getAttribute("Nacionalidad") == null) ? "" : (String)rowAdj.getAttribute("Nacionalidad");
            BigDecimal adjMonto = (rowAdj.getAttribute("Monto") == null) ? new BigDecimal(0) : (BigDecimal)rowAdj.getAttribute("Monto");
          
                for(Row rowCont : rowsContratadosAdjudicatarios){
                  
                    String contNombre = (rowCont.getAttribute("Nombre") == null) ? "" : (String)rowCont.getAttribute("Nombre");
                    String contNacionalidad = (rowCont.getAttribute("Nacionalidad") == null) ? "" :(String)rowCont.getAttribute("Nacionalidad");
                    BigDecimal contMonto = (rowCont.getAttribute("Monto") == null) ? new BigDecimal(0) : (BigDecimal)rowCont.getAttribute("Monto");
                   
                    if(adjNombre.equals(contNombre) && adjNacionalidad.equals(contNacionalidad) && (adjMonto.compareTo(contMonto) == 0)){
                        logger.warning("ok el adjudicatario "+rowCont.getAttribute("Nombre")+" se encontro en la tabla de contratados");
                        coincidenciaRow = Boolean.TRUE;
                        break;
                    }
                }
            
            
            if(!coincidenciaRow){
                logger.warning("no se encontro en la tabla de contratos el adjudicatario "+adjNombre+", aregando row...");
               //cargarContratosAdjudicatarios(adjNombre, adjNacionalidad, adjMonto, new Long(contador));                     
            }else{
                Row rowAdjudicatario = adquisiscionesAMImpl.getAdjudicatariosContratoVO().getRow(new Key(new Object[] { idAdjudicatario }));
                adquisiscionesAMImpl.getAdjudicatariosContratoVO().eliminarAdjudicatarioContrato(rowAdjudicatario);
            }
            
        }
        
        //adquisiscionesAMImpl.getAdjudicatariosContratoVO().limpiarVO();
                
        logger.warning("termina metodo verificarRowsContrato");
    }

    public void cargarDatosPlantillaAviso(BlobDomain fileData){
        logger.warning("Inicia metodo cargarDatosPlantillaAviso");
        
        InputStream in = null;
        XSSFWorkbook workbook = null;
        XSSFSheet sheet = null;
        
        String fechaPublicacion = "";
        String fechaInicioDispDoctoBase = "";
        String fechaFinDispDoctoBase = "";
        String fechaRecepcionPropuesta = "";
        String lugarObtenerDoctoBase = "";
        String correoInformacionAdicional = "";
        String dirPresentacionPropuesta = "";
        
        try{
          in = fileData.getBinaryStream();
          workbook = new XSSFWorkbook(in);
          sheet = workbook.getSheetAt(0);
        }catch(Exception e){
            logger.warning("ha ocurrido una Excepcion en metodo validarArchivoExcel() ->", e);
        }
        
        Iterator<org.apache.poi.ss.usermodel.Row> rowIterator = sheet.iterator();
        org.apache.poi.ss.usermodel.Row row;
        
        int numFila = 0;
        logger.warning("Iterando celdas del documento");
       
        
        try{
            while (rowIterator.hasNext()) {
                row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                 numFila = numFila +1;
                logger.warning("------------ fila numero : "+numFila+" -------------------"); 
                //se obtiene las celdas por fila
                 Cell cell; int numColumna = 0;
                //se recorre cada celda
                 while (cellIterator.hasNext()) {
                     // se obtiene la celda en específico y se la imprime
                     numColumna = numColumna +1;
                     cell = cellIterator.next();
                     
                     logger.warning("fila: "+numFila+" columna: "+numColumna+" valorCelda: "+getCellValueString(cell)+" | ");
                     
                     if(numFila == 3 && numColumna == 2)
                        fechaPublicacion = ""+cell.getDateCellValue();
                    
                      if(numFila == 4 && numColumna == 2)
                        fechaInicioDispDoctoBase = ""+cell.getDateCellValue();
                     
                      if(numFila == 5 && numColumna == 2)
                        fechaFinDispDoctoBase = ""+cell.getDateCellValue();
                     
                      if(numFila == 6 && numColumna == 2)
                        fechaRecepcionPropuesta = ""+cell.getDateCellValue();
                     
                      if(numFila == 7 && numColumna == 2)
                        lugarObtenerDoctoBase = ""+cell.getStringCellValue();
                     
                      if(numFila == 8 && numColumna == 2)
                        correoInformacionAdicional = ""+cell.getStringCellValue();
                         
                      if(numFila == 9 && numColumna == 2)
                        dirPresentacionPropuesta = ""+cell.getStringCellValue(); 
                }
            }
            
            
            logger.warning("fechaPublicacion: "+fechaPublicacion);
            logger.warning("fechaInicioDispDoctoBase: "+fechaInicioDispDoctoBase);
            logger.warning("fechaFinDispDoctoBase: "+fechaFinDispDoctoBase);
            logger.warning("fechaRecepcionPropuesta: "+fechaRecepcionPropuesta);
            logger.warning("lugarObtenerDoctoBase: "+lugarObtenerDoctoBase);
            logger.warning("correoInformacionAdicional: "+correoInformacionAdicional);
            logger.warning("dirPresentacionPropuesta: "+dirPresentacionPropuesta);
            
        }catch(Exception e){
            logger.warning("ha ocurrido un error al iterar las celdas del archivo ->", e);
        }
        
        FenixAMImpl fenixAMImpl = null;
        fenixAMImpl = (FenixAMImpl) this.getRootApplicationModule();
        FenixAdquisicionAMImpl adquisiscionesAMImpl = null;
        adquisiscionesAMImpl = (FenixAdquisicionAMImpl)fenixAMImpl.getFenixAdquisicionAM();
        adquisiscionesAMImpl.getNoObjecionVO().setDatosFormNoObjecion(getTimestampOfString(fechaPublicacion), getTimestampOfString(fechaInicioDispDoctoBase),
                                                                      getTimestampOfString(fechaFinDispDoctoBase), getTimestampOfString(fechaRecepcionPropuesta),
                                                                      lugarObtenerDoctoBase, correoInformacionAdicional,
                                                                      dirPresentacionPropuesta);
        
        logger.warning("termina metodo cargarDatosPlantillaAviso");
    }


    public Timestamp getTimestampOfString(String fecha){
        
        java.sql.Timestamp timeStampDate = null;
        if(fecha != null){
            try {
                  Date date = new Date(fecha);
                  timeStampDate = new Timestamp(date.getTime());
                
                } catch (Exception e) {
                  logger.warning("Exception al parsear fecha :", e);
                }
        }
        
        return timeStampDate;
    }

    public String getCellValueString(Cell cell){
        
    String cellValue = "";
    
         switch (cell.getCellType()) {
            case XSSFCell.CELL_TYPE_STRING:
                 cellValue = ""+cell.getStringCellValue();
            break;
            case XSSFCell.CELL_TYPE_NUMERIC:
                 cellValue = ""+cell.getNumericCellValue();
            break;
            case XSSFCell.CELL_TYPE_BLANK:
                 cellValue = ""+cell.getDateCellValue();
            break;
         
               
           }
         
      return cellValue;
    } 
   
    public Boolean validarArchivoExcel2(BlobDomain fileData, String tipoPago){
        logger.warning("Inicia metodo validarArchivoExcel");
        Boolean resultado = null;
        InputStream in = null;
        
        if(null == fileData){
            logger.warning("Parametros requeridos NULL.");
            logger.warning("fileData: " + fileData);
            return resultado;
        }
        
        try {
            in = fileData.getBinaryStream();
            XSSFWorkbook workbook = new XSSFWorkbook(in);
            XSSFSheet sheet = workbook.getSheetAt(0);
            int rowsCount = sheet.getFirstRowNum();
            int lastRowIndex = sheet.getLastRowNum();
            
            logger.warning("Filas encontradas en el excel: " + rowsCount);
            if(rowsCount == 0){ //Evaluar encabezados de hoja.
                for (int i = 0; i == rowsCount; i++) {
                    logger.warning("Validando encabezados del archivo.");
                    int j = 0;
                    int colCounts;
                    XSSFRow row = sheet.getRow(i);
                    
                    try {
                        colCounts = row.getLastCellNum();
                    } catch (NullPointerException e) {
                        colCounts = 1;
                    }
                    
                    logger.warning("Columnas en el archivo: " + colCounts);
                    if(tipoPago.equalsIgnoreCase("CAPITAL")){
                        if(colCounts-1 <= 1){
                            String[] excel_data = new String[colCounts + 1];
                            for (int k = 0; k < colCounts; k++) {
                                j = j + 1;
                                try {
                                    XSSFCell cell = row.getCell(k);
                                    switch (cell.getCellType()) {
                                    case XSSFCell.CELL_TYPE_STRING:
                                        excel_data[j] = cell.getRichStringCellValue().getString();
                                        logger.log(ADFLogger.WARNING, "El valor de celda es STRING.");
                                        logger.log(ADFLogger.WARNING, "DATA_STRING: " + excel_data[j]);
                                        logger.warning("Row de hoja: " + row.getRowNum() + ", Columna de hoja: " + cell.getColumnIndex());
                                        
                                        switch(cell.getColumnIndex()){
                                        case 0:
                                            if(null != excel_data[j] && excel_data[j].equals("Fecha (dd/mm/yyyy)")){
                                                resultado = Boolean.TRUE;
                                            }else{
                                                logger.warning("Formato incorrecto. Primer columna diferente.");
                                                resultado = Boolean.FALSE;
                                            }
                                            break;
                                        case 1:
                                            if(null != excel_data[j] && excel_data[j].equals("Monto")){
                                                resultado = Boolean.TRUE;
                                            }else{
                                                logger.warning("Formato incorrecto. Segunda columna diferente.");
                                                resultado = Boolean.FALSE;
                                            }
                                            break;
                                        default:
                                            
                                        }
                                        
                                        if(i > 0){
                                            logger.warning("Evaluando datos STRING en filas que NO son encabezados.");
                                            resultado = Boolean.FALSE;
                                        }
                                        
                                        break;
                                    case XSSFCell.CELL_TYPE_NUMERIC:
                                        logger.warning("EL valor de la celda es NUMERICO.");
                                        resultado = Boolean.FALSE;
                                        break;
                                    default:
                                        //logger.log(ADFLogger.WARNING, "Celda NULL");
                                    }
                                } catch (Exception e) {
                                    //logger.log(ADFLogger.WARNING, "ERRROR al crear row de celda excel");
                                    excel_data[j] = "";
                                }
                            }
                        }else{
                            logger.warning("El archivo contiene mas columnas.");
                            resultado = Boolean.FALSE;
                        }
                    }else{
                        if(colCounts-1 < 1){
                            String[] excel_data = new String[colCounts + 1];
                            for (int k = 0; k < colCounts; k++) {
                                j = j + 1;
                                try {
                                    XSSFCell cell = row.getCell(k);
                                    switch (cell.getCellType()) {
                                    case XSSFCell.CELL_TYPE_STRING:
                                        excel_data[j] = cell.getRichStringCellValue().getString();
                                        logger.log(ADFLogger.WARNING, "El valor de celda es STRING.");
                                        logger.log(ADFLogger.WARNING, "DATA_STRING: " + excel_data[j]);
                                        logger.warning("Row de hoja: " + row.getRowNum() + ", Columna de hoja: " + cell.getColumnIndex());
                                        
                                        if(null != excel_data[j] && excel_data[j].equals("Fecha (dd/mm/yyyy)")){
                                            resultado = Boolean.TRUE;
                                        }else{
                                            logger.warning("Formato incorrecto. Primer columna diferente.");
                                            resultado = Boolean.FALSE;
                                        }
                                        
                                        if(i > 0){
                                            logger.warning("Evaluando datos STRING en filas que NO son encabezados.");
                                            resultado = Boolean.FALSE;
                                        }
                                        
                                        break;
                                    case XSSFCell.CELL_TYPE_NUMERIC:
                                        logger.warning("EL valor de la celda es NUMERICO.");
                                        resultado = Boolean.FALSE;
                                        break;
                                    default:
                                        //logger.log(ADFLogger.WARNING, "Celda NULL");
                                    }
                                } catch (Exception e) {
                                    //logger.log(ADFLogger.WARNING, "ERRROR al crear row de celda excel");
                                    excel_data[j] = "";
                                }
                            }
                        }else{
                            logger.warning("El archivo contiene mas columnas.");
                            resultado = Boolean.FALSE;
                        }
                    }
                }
            }
            
            workbook.close();
        } catch (IOException e) {
            logger.log(ADFLogger.WARNING, " ERROR al validar contenido de hoja de calculo.", e);
            resultado = null;
            return resultado;
        }

        try {
            in.close();
        } catch (Exception e) {
            logger.warning("ERROR al cerrar el inputStream.", e);
            resultado = null;
        }
        
        
        logger.warning("Termina metodo validarArchivoExcel");
        return resultado;
    }



    /**
     * executeQueryForCollection - overridden for custom java data source support.
     */
    @Override
    protected void executeQueryForCollection(Object qc, Object[] params, int noUserParams) {
        super.executeQueryForCollection(qc, params, noUserParams);
    }

    /**
     * hasNextForCollection - overridden for custom java data source support.
     */
    @Override
    protected boolean hasNextForCollection(Object qc) {
        boolean bRet = super.hasNextForCollection(qc);
        return bRet;
    }

    /**
     * createRowFromResultSet - overridden for custom java data source support.
     */
    @Override
    protected ViewRowImpl createRowFromResultSet(Object qc, ResultSet resultSet) {
        ViewRowImpl value = super.createRowFromResultSet(qc, resultSet);
        return value;
    }

    /**
     * getQueryHitCount - overridden for custom java data source support.
     */
    @Override
    public long getQueryHitCount(ViewRowSetImpl viewRowSet) {
        long value = super.getQueryHitCount(viewRowSet);
        return value;
    }

    /**
     * getCappedQueryHitCount - overridden for custom java data source support.
     */
    @Override
    public long getCappedQueryHitCount(ViewRowSetImpl viewRowSet, Row[] masterRows, long oldCap, long cap) {
        long value = super.getCappedQueryHitCount(viewRowSet, masterRows, oldCap, cap);
        return value;
    }
}

