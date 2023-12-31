package org.bcie.fenix.common.model.vo;

import java.sql.Timestamp;

import java.util.Date;

import java.util.HashMap;
import java.util.Map;

import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.Key;
import oracle.jbo.NameValuePairs;
import oracle.jbo.Row;
import oracle.jbo.ViewCriteria;
import oracle.jbo.server.SequenceImpl;
import oracle.jbo.server.ViewObjectImpl;

import org.bcie.fenix.common.model.FenixModelConstants;
import org.bcie.fenix.common.model.vo.common.AdquisicionVO;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Tue Nov 08 18:30:01 CST 2016
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class AdquisicionVOImpl extends ViewObjectImpl implements AdquisicionVO {
    /**
     * This is the default constructor (do not remove).
     */
    
    private static ADFLogger logger = null;
    
    public AdquisicionVOImpl() {
        if(logger == null){
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }
    
    public void actualizarAdquisicion(){
       logger.warning("Entra a actualizarAdquisicion");
       
        oracle.jbo.domain.Number idAdquisicion = null;
        NameValuePairs nvpAdquisicion = null;
        SequenceImpl seqAdquisicion = null;
        logger.warning("Continua porceso actualizarAdquisicion 1");

        /*seqAdquisicion = new SequenceImpl("ADQUISICION_SEQ", getDBTransaction());
        idAdquisicion = seqAdquisicion.getSequenceNumber();
      */
        
        Row adquisicionRow = getRow(new Key(new Object[] { 3L }));
        adquisicionRow.setAttribute("IdOperacion", 500010L);
        adquisicionRow.setAttribute("NormativaEspecifica", "Daniel");
        adquisicionRow.setAttribute("IdTcaTipoNormativaAplicada", 1);
        adquisicionRow.setAttribute("IdTcaTipoAdquisicion", 1);
        adquisicionRow.setAttribute("BanEstatus", 1);
        adquisicionRow.setAttribute("FechaRegistro", new Date());
        
        //this.createAndInitRow(nvpAdquisicion);
        logger.warning("Continua proceso actualizarAdquisicion 4");
                
        getDBTransaction().commit();
        logger.warning("Continua porceso actualizarAdquisicion 5 (se hace commit a la VO)");
       
    }
    public Map aplicarNoobjecion(Long idAdquisicion){
        logger.warning("Ingresa metodo aplicarNoobjecionVO");
        Map resultado = new HashMap<String, Object>();
        logger.warning("Valores a ingresar");
        logger.warning("idAdquisicion " + idAdquisicion);
        long startTime = System.currentTimeMillis();
        logger.warning("Tiempo de inicio de la busqueda de la adquisicion: " + startTime);
        if(null!=idAdquisicion){
            setvarId(idAdquisicion);
            executeQuery();
                logger.warning("Ingresa if principal");
                Integer tipo=null;
                Integer normativa=null;
                Integer modalidad=null;
                Integer previa=null;
                Row adquisicionRow = null;
                adquisicionRow = getRowAtRangeIndex(0);
                if(null!=adquisicionRow){
                logger.warning("IdTcaTipoNormativaAplicada " +(Integer)adquisicionRow.getAttribute("IdTcaTipoNormativaAplicada"));
                if(null!=(Integer)adquisicionRow.getAttribute("IdTcaTipoNormativaAplicada")){
                        normativa=(Integer)adquisicionRow.getAttribute("IdTcaTipoNormativaAplicada");
                        if(normativa.compareTo(FenixModelConstants.NORMATIVA_BCIE)==0){
                                logger.warning("IdTcaTipoAdquisicion " +(Integer)adquisicionRow.getAttribute("IdTcaTipoAdquisicion"));
                                logger.warning("CuentaConAdquisicionPrevia " +(Integer)adquisicionRow.getAttribute("CuentaConAdquisicionPrevia"));
                                if(null!=(Integer)adquisicionRow.getAttribute("IdTcaTipoAdquisicion")){
                                        tipo=(Integer)adquisicionRow.getAttribute("IdTcaTipoAdquisicion");
                                        if(tipo.compareTo(FenixModelConstants.ADQUISICION_PGA)!=0){
                                                logger.warning("se muestra la no objecion");
                                                resultado.put("resultado", Boolean.TRUE);
                                                logger.warning("IdTcaTipoModalidadProceso " +(Integer)adquisicionRow.getAttribute("IdTcaTipoModalidadProceso"));
                                                if(null!=(Integer)adquisicionRow.getAttribute("IdTcaTipoModalidadProceso")){
                                                        logger.warning("modalidad a enviar: "+(Integer)adquisicionRow.getAttribute("IdTcaTipoModalidadProceso"));
                                                        modalidad=(Integer)adquisicionRow.getAttribute("IdTcaTipoModalidadProceso");
                                                        resultado.put("modalidad", modalidad);
                                                } else {
                                                    resultado.put("resultado", Boolean.FALSE);
                                                    resultado.put("modalidad", 0);
                                                }
                                            //    String CuentaConAdquisicionPrevia = adquisicionRow.getAttribute("CuentaConAdquisicionPrevia").toString();
                                                logger.warning("Valor CuentaConAdquisicionPrevia: " + previa );
                                                if(null== (Integer)adquisicionRow.getAttribute("CuentaConAdquisicionPrevia")){
                                                        logger.warning("Ingresa al if");
                                                        resultado.put("previa", Boolean.FALSE);
                                                    }
                                                else{
                                                        previa=(Integer)adquisicionRow.getAttribute("CuentaConAdquisicionPrevia");
                                                        logger.warning("Ingresa al else");
                                                    if(previa.compareTo(1) == 0){
                                                            logger.warning("Ingresa al if CuentaConAdquisicionPrevia = 1 ");
                                                            resultado.put("previa", Boolean.TRUE);
                                                        }
                                                    else{
                                                            logger.warning("Ingresa al else CuentaConAdquisicionPrevia = 0 ");
                                                            resultado.put("previa", Boolean.FALSE);
                                                        }
                                                    }
                                        
                                            }
                                        else{
                                                logger.warning("se muestra la no objecion para adquisicion de tipo PGA");
                                                resultado.put("resultado", Boolean.TRUE);
                                                logger.warning("IdTcaTipoModalidadProceso " +(Integer)adquisicionRow.getAttribute("IdTcaTipoModalidadProceso"));
                                                if(null!=(Integer)adquisicionRow.getAttribute("IdTcaTipoModalidadProceso")){
                                                        logger.warning("modalidad a enviar: "+(Integer)adquisicionRow.getAttribute("IdTcaTipoModalidadProceso"));
                                                        modalidad=(Integer)adquisicionRow.getAttribute("IdTcaTipoModalidadProceso");
                                                        resultado.put("modalidad", 99);
                                                } else {
                                                    //resultado.put("resultado", Boolean.FALSE);
                                                    resultado.put("modalidad", 99);
                                                }
                                                //    String CuentaConAdquisicionPrevia = adquisicionRow.getAttribute("CuentaConAdquisicionPrevia").toString();
                                                logger.warning("Valor CuentaConAdquisicionPrevia: " + previa );
                                                if(null== (Integer)adquisicionRow.getAttribute("CuentaConAdquisicionPrevia")){
                                                        logger.warning("Ingresa al if");
                                                        resultado.put("previa", Boolean.FALSE);
                                                    }
                                                else{
                                                        previa=(Integer)adquisicionRow.getAttribute("CuentaConAdquisicionPrevia");
                                                        logger.warning("Ingresa al else");
                                                    if(previa.compareTo(1) == 0){
                                                            logger.warning("Ingresa al if CuentaConAdquisicionPrevia = 1 ");
                                                            resultado.put("previa", Boolean.TRUE);
                                                        }
                                                    else{
                                                            logger.warning("Ingresa al else CuentaConAdquisicionPrevia = 0 ");
                                                            resultado.put("previa", Boolean.FALSE);
                                                        }
                                                    }
                                            }
                                        
                                    }
                            }
                        else{
                                resultado.put("resultado", Boolean.FALSE);
                                resultado.put("modalidad", 0);
                            }
                    }


                }
            
            }
        long endTime = System.currentTimeMillis();
        logger.warning("Tiempo de fin de la busqueda de la adquisicion: " + endTime);
        logger.warning("Tiempo diferencia "
        + (endTime - startTime)/1000 + " segundos");
        return resultado;
        }
        
     /*   public void eliminarTreEvidenciaSolicitud(Integer idTreEvidenciaSolicitud) {
        logger.log(ADFLogger.TRACE, "Inside eliminarTreEvidenciaSolicitud.");

        logger.warning("Ingresa al metodo eliminarTreEvidenciaSolicitud de la VO TreEvidenciaSolicitud");
        logger.warning("idTreEvidenciaSolicitud---->"+ idTreEvidenciaSolicitud);
        Row row = null;
        //executeQuery();
        row = getRow(new Key(new Object[]{Long.parseLong(idTreEvidenciaSolicitud.toString())}));

        if(row != null){
            logger.warning("Metodo eliminarTreEvidenciaSolicitud------Ingresa al If");
            row.remove();
            logger.warning("Metodo eliminarTreEvidenciaSolicitud------Aplica row.remove");
            getDBTransaction().commit();
            logger.warning("Metodo eliminarTreEvidenciaSolicitud------Hace el commit");
        }
        logger.warning("Metodo eliminarTreEvidenciaSolicitud------Fin metodo eliminarTreEvidenciaSolicitud");


    */

    /**
     * Returns the variable value for varId.
     * @return variable value for varId
     */
    public Long getvarId() {
        return (Long) ensureVariableManager().getVariableValue("varId");
    }

    /**
     * Sets <code>value</code> for variable varId.
     * @param value value to bind as varId
     */
    public void setvarId(Long value) {
        ensureVariableManager().setVariableValue("varId", value);
    }
    
    private Row cargaNoObjecionSeleccionada(Long idAdquisicion){
                logger.warning("Entra a consulta  idObjecion: " + idAdquisicion);
                Row resultado = null;

                try{
                    ViewCriteria viewCriteria =this.getViewCriteriaManager().getViewCriteria("AdquisicionVOCriteria");
                    viewCriteria.ensureVariableManager().setVariableValue("varId", idAdquisicion);
                    this.applyViewCriteria(viewCriteria);
                    this.executeQuery();
                    //resultado = (this.getEstimatedRowCount() > 0) ? Boolean.TRUE : Boolean.FALSE;
                    
                    if(this.getEstimatedRowCount() > 0){
                    logger.warning("Existe row"); 
                    resultado=getRowAtRangeIndex(0);
                    }
        
    }catch(Exception ex){
        logger.log(ADFLogger.ERROR, "Error en a consulta  idObjecion ", ex);
    }finally{
        //This takes care of removing the applied VC.
       this.getViewCriteriaManager().removeApplyViewCriteriaName("AdquisicionVOCriteria");
    }
    return resultado;
    }
}

