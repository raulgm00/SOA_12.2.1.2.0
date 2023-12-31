package org.bcie.fenix.common.model.vo;

import java.sql.Timestamp;

import java.util.List;

import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.Key;
import oracle.jbo.Row;
import oracle.jbo.server.ViewObjectImpl;

import org.bcie.fenix.common.model.vo.common.VencimientoPlazoVO;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Mon Dec 14 01:47:10 CST 2015
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class VencimientoPlazoVOImpl extends ViewObjectImpl implements VencimientoPlazoVO {
    
    private static ADFLogger logger = null;
    
    /**
     * This is the default constructor (do not remove).
     */
    public VencimientoPlazoVOImpl() {
        
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }
    
    public void agregarVencimiento(Long idTipoDeVencimiento, Long idTipoDeFecha, Long idPlazo, String tipoDeVencimiento,
                                   String tipoDeFecha, String tipoPlazo, String plazo, Timestamp fechaInicio, 
                                   Timestamp fechaVencimiento) {
        // Creamos row
        Row row = this.createRow();
        row.setAttribute("Id", Integer.valueOf(this.getRowCount() + 1));
        
        // Asignaci�n de atributos
        row = mappingRow(row, null, idTipoDeVencimiento, idTipoDeFecha, idPlazo, tipoDeVencimiento, tipoDeFecha, 
                         tipoPlazo, plazo, fechaInicio, fechaVencimiento);
        
        // Insertamos row al final de la tabla
        this.last();
        this.next();
        this.insertRow(row); 
    }
    
    public void modificarVencimiento(Integer id, Long idVencimiento, Long idTipoDeVencimiento, Long idTipoDeFecha, 
                                     Long idPlazo, String tipoDeVencimiento, String tipoDeFecha, String tipoPlazo, 
                                     String plazo, Timestamp fechaInicio, Timestamp fechaVencimiento) {
        logger.log(ADFLogger.TRACE, "Inside modificarVencimiento.");
        Row vencimientoRow = null;
        
        // Obtenemos row por Id
        vencimientoRow = this.getRow(new Key(new Object[] {id}));
        
        // Modificamos datos
        if(vencimientoRow != null) {
            vencimientoRow = mappingRow(vencimientoRow, idVencimiento, idTipoDeVencimiento, idTipoDeFecha, idPlazo, 
                                    tipoDeVencimiento, tipoDeFecha, tipoPlazo, plazo, fechaInicio, fechaVencimiento);
        }
    }
    
    public void eliminarVencimiento(Integer id) {
        logger.log(ADFLogger.TRACE, "Inside eliminarVencimiento.");

        // Obtenemos y eliminamos row por Id
        if((id != null) && (this.getRow(new Key(new Object[] {id})) != null))
            this.getRow(new Key(new Object[] {id})).remove();
    }
    
    public void eliminarVencimientoAgregados(List idsVencimientosEliminados) {
        logger.log(ADFLogger.TRACE, "Inside eliminarVencimientoAgregados.");
        for(Object idVencimiento : idsVencimientosEliminados) {
            Long idVencimientoLong = (Long)idVencimiento;
            Row[] rowVencimiento =this.getFilteredRows("IdVencimiento",idVencimientoLong);
            for (Row row : rowVencimiento) {
                this.setCurrentRow(row);
                this.removeCurrentRow();
            }
        }
    }
    
    public void agregarVencimientosEliminados(List idsVencimientosEliminados) {
        logger.log(ADFLogger.TRACE, "Inside agregarVencimientosEliminados.");
        
        // Insertamos en el VO los vencimientos eliminados, con Estado en false
        for(Object idVencimiento : idsVencimientosEliminados) {
            
            // Creamos row
            Row row = this.createRow();
            row.setAttribute("Id", Integer.valueOf(this.getRowCount() + 1));
            row.setAttribute("IdVencimiento", idVencimiento);
            row.setAttribute("Estado", Boolean.FALSE);
                        
            // Insertamos row al final de la tabla
            this.last();
            this.next();
            this.insertRow(row); 
        }      
    }
    
    private Row mappingRow(Row row, Long idVencimiento, Long idTipoDeVencimiento, Long idTipoDeFecha, Long idPlazo, 
                           String tipoDeVencimiento, String tipoDeFecha, String tipoPlazo, String plazo, 
                           Timestamp fechaInicio, Timestamp fechaVencimiento) {
        // Asignaci�n de atributos
        row.setAttribute("IdVencimiento", idVencimiento);
        row.setAttribute("IdTipoDeVencimiento", idTipoDeVencimiento);
        row.setAttribute("IdTipoDeFecha", idTipoDeFecha);
        row.setAttribute("IdPlazo", idPlazo);
        row.setAttribute("TipoDeVencimiento", tipoDeVencimiento);
        row.setAttribute("TipoDeFecha", tipoDeFecha);
        row.setAttribute("TipoPlazo", tipoPlazo);
        row.setAttribute("Plazo", plazo);
        row.setAttribute("FechaInicio", fechaInicio);
        row.setAttribute("FechaVencimiento", fechaVencimiento);
        row.setAttribute("Estado", Boolean.TRUE); // Estado es true por default para modificar y agregar.
        
        return row;
    }

    /**
     * [KB:14513]
     * Inserta una copia (al final) de los datos del Vencimiento encontrados en base de datos en este objeto de memoria.
     * @param rowVencimientoLC Vencimiento encontrado en base de datos.
     * @return Fila reci�n ingresada a esta VO.
     */
    public Row insertCopyOf(VencimientosLineaCreditoVORowImpl rowVencimientoLC) {
        // Creamos row
        Row row = this.createRow();
        row.setAttribute("Id", Integer.valueOf(this.getRowCount() + 1));
        
        // Asignaci�n de atributos
        row = mappingRow(row, rowVencimientoLC.getId(), rowVencimientoLC.getIdTipoVencimiento(), rowVencimientoLC.getIdTipoFecha(), 
                         rowVencimientoLC.getIdTipoPlazo(), rowVencimientoLC.getTipoVencimiento(), rowVencimientoLC.getTipoFecha(), 
                         rowVencimientoLC.getTipoPlazo(), String.valueOf(rowVencimientoLC.getPlazo()), rowVencimientoLC.getFechaInicio(), 
                         rowVencimientoLC.getFechaVencimiento());
        
        // Insertamos row al final de la tabla
        this.last();
        this.next();
        this.insertRow(row); 
        
        return row;
    }
}

