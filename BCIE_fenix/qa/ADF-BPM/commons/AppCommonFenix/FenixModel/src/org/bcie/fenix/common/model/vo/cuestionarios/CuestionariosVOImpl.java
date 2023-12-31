package org.bcie.fenix.common.model.vo.cuestionarios;

import java.util.HashMap;
import java.util.Map;

import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;
import oracle.jbo.server.ViewObjectImpl;

import org.bcie.fenix.common.model.vo.QPreguntaVOImpl;
import org.bcie.fenix.common.model.vo.cuestionarios.common.CuestionariosVO;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Wed Jun 09 23:50:36 CDT 2021
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CuestionariosVOImpl extends ViewObjectImpl implements CuestionariosVO {
    
    
    private static ADFLogger logger = null;
    /**
     * This is the default constructor (do not remove).
     */
    public CuestionariosVOImpl() {
        if (logger == null) {
            logger = ADFLogger.createADFLogger(QPreguntaVOImpl.class);
        }
    }

    public String invocaCuestionariosVo(Integer p_Bandera, Integer p_IdOperacion, Integer p_IdProceso) {
        
        // Declaraci�n de un Mapa de "String". Puede ser de cualquier otro Elemento u Objeto (float, Boolean, Object, ...)
        String mess = "en proceso";
        this.setp_Bandera(p_Bandera);
        this.setp_IdOperacion(p_IdOperacion);
        this.setp_IdProceso(p_IdProceso);
        this.executeQuery();
        System.out.println("=====================================================================================");
        System.out.println("-- PROCESO " + mess + " Bandera: " + p_Bandera + " Id Operacion: " + p_IdOperacion + "Id Proceso: " + p_IdProceso);
        System.out.println("=====================================================================================");
        logger.warning("cantidad de registros :" + this.getEstimatedRowCount());
        
        //Validar que existen datos en la ejecucu�n de View Criterial
        if (this.getEstimatedRowCount() > 0) {
            logger.warning("Se encontraron " + this.getEstimatedRowCount() + " registros");
            //Iteraci�n de View Criterial:
            RowSetIterator iter = this.createRowSetIterator(null);
            desempaquetarColecion(iter);
            
        } else {
            logger.warning("No se encontraron registros");
            mess = "Fail";
            
        }
        
        
        return mess;
    }
    
    
    public void desempaquetarColecion(RowSetIterator iter) {
        while (iter.hasNext()) {
            Row r = iter.next();
            imprimirRow(r);
        
        }
    }
    
    public void imprimirRow(Row fila) {
        System.out.println("==================== OBJ BD ===================================");
        System.out.println("Id = " + fila.getAttribute("IdPregunta"));
        System.out.println("Pregunta = " + fila.getAttribute("Pregunta"));
        System.out.println("Respuesta = " + fila.getAttribute("Respuesta"));
        System.out.println("Justificacion = " + fila.getAttribute("Justificacion"));
        System.out.println("Evidencia = " + fila.getAttribute("Evidencia"));
        System.out.println("IdCatPregunta = " + fila.getAttribute("IdCatPregunta"));
        System.out.println("IdCuestionario = " + fila.getAttribute("IdCuestionario"));
        System.out.println("Usuario = " + fila.getAttribute("Usuario"));
        System.out.println("IdDocumento = " + fila.getAttribute("IdDocumento"));
        System.out.println("IdTipoPregunta = " + fila.getAttribute("IdTipoPregunta"));
        System.out.println("IdResponsable = " + fila.getAttribute("IdResponsable"));
        System.out.println("NumOrden = " + fila.getAttribute("NumOrden"));
        System.out.println("Responsable = " + fila.getAttribute("Responsable"));
        System.out.println("Opciones = " + fila.getAttribute("Opciones"));
        System.out.println("NombreDocumento = " + fila.getAttribute("NombreDocumento"));
        //System.out.println(" = " + fila.getAttribute(""));
        System.out.println("===============================================================");

    }
    

    /**
     * Returns the bind variable value for p_Bandera.
     * @return bind variable value for p_Bandera
     */
    public Integer getp_Bandera() {
        return (Integer) getNamedWhereClauseParam("p_Bandera");
    }

    /**
     * Sets <code>value</code> for bind variable p_Bandera.
     * @param value value to bind as p_Bandera
     */
    public void setp_Bandera(Integer value) {
        setNamedWhereClauseParam("p_Bandera", value);
    }

    /**
     * Returns the bind variable value for p_IdOperacion.
     * @return bind variable value for p_IdOperacion
     */
    public Integer getp_IdOperacion() {
        return (Integer) getNamedWhereClauseParam("p_IdOperacion");
    }

    /**
     * Sets <code>value</code> for bind variable p_IdOperacion.
     * @param value value to bind as p_IdOperacion
     */
    public void setp_IdOperacion(Integer value) {
        setNamedWhereClauseParam("p_IdOperacion", value);
    }

    /**
     * Returns the bind variable value for p_IdProceso.
     * @return bind variable value for p_IdProceso
     */
    public Integer getp_IdProceso() {
        return (Integer) getNamedWhereClauseParam("p_IdProceso");
    }

    /**
     * Sets <code>value</code> for bind variable p_IdProceso.
     * @param value value to bind as p_IdProceso
     */
    public void setp_IdProceso(Integer value) {
        setNamedWhereClauseParam("p_IdProceso", value);
    }


}

