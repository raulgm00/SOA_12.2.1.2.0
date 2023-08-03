package org.bcie.fenix.view.gestordesembolsos;

import java.io.Serializable;
import oracle.adf.share.logging.ADFLogger;
import org.bcie.fenix.common.utils.JSFUtils;
import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;
import oracle.adf.model.BindingContext;
import oracle.jbo.Row;

public class CoberturasBean implements Serializable {
    @SuppressWarnings("compatibility:-577242889177156535")
    private static final long serialVersionUID = 1L;
    
    public static ADFLogger logger = null;
    
    private Long idcontratoDesembolso;
    private String contratoDesembolso;

    public CoberturasBean() {
        if (logger == null){
            logger = ADFLogger.createADFLogger(CoberturasBean.class);
        }   
    }
    
    public void precargaInformacion() {
        logger.log(ADFLogger.WARNING, " ****** Inicia el metodo de precarga para el TAB COBERTURAS");

        setIdcontratoDesembolso((null == JSFUtils.resolveExpression("#{pageFlowScope.idcontratoDesembolso}")) ? null :
                                 new Long(JSFUtils.resolveExpression("#{pageFlowScope.idcontratoDesembolso}").toString()));
        
        setContratoDesembolso(obtenerBHQContratoDesembolso());
        //setContratoDesembolso("BHQBVMC093560001"); //dato de prueba
        
        cargarCoberturas();
        
        logger.log(ADFLogger.WARNING, "idcontratoDesembolso: " + getIdcontratoDesembolso());
        logger.log(ADFLogger.WARNING, "contratoDesembolso: " + getContratoDesembolso());
        
        
        logger.warning("Reconsulta de datos generales");
        cargaDatos();
        logger.log(ADFLogger.WARNING, " ****** Termina el metodo de precarga para el TAB COBERTURAS ");  
    }
    
    private String obtenerBHQContratoDesembolso() {
        logger.warning("Inicia el metodo obtenerBHQContratoDesembolso");
        Row currentRow = null;
        String contratoFlexcube = null;
        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();
        OperationBinding operationBinding = null;
        
        try{        
            operationBinding = bindings.getOperationBinding("getContratoSeleccionado");
            operationBinding.execute();
        }catch(Exception e){
            logger.warning("Error, error al ejecutar operBinding getContratoSeleccionado ",e);
        }
            
        if (!operationBinding.getErrors().isEmpty()) {
           logger.warning("Error ejecutar operBinding getContratoSeleccionado ->"+operationBinding.getErrors());
        }else{
             currentRow = (Row) operationBinding.getResult();    
            
            if (null != currentRow) {
                contratoFlexcube = (String)currentRow.getAttribute("ContratoFlexcube");
                logger.warning("ok, contratoFlexcube recuperado de contrato cuerent :"+contratoFlexcube);
            }else{
                logger.warning("Error, no se pudo recuperar el contrato cuerrent");                
            }
        }        
       
        logger.warning("termina el metodo obtenerBHQContratoDesembolso contratoFlexcube recuperado: "+contratoFlexcube);
        return contratoFlexcube;
    }

    @SuppressWarnings("unchecked")
    public void cargarCoberturas() {
        logger.warning("Inicia el metodo cargarCoberturas");
        double TInicio, TFin, tiempo; //Variables para determinar el tiempo de ejecución
        TInicio = System.currentTimeMillis(); //Tomamos la hora en que inicio el algoritmo y la almacenamos en la variable inicio
        
        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();
        OperationBinding operationBinding = bindings.getOperationBinding("obtenerCoberturasPorContrato");
        operationBinding.getParamsMap().put("contratoDesembolso", getContratoDesembolso());
        operationBinding.execute();
        
        if (!operationBinding.getErrors().isEmpty()) {
           logger.warning("Error al obtener los datos, error al ejecutar el metodo intente más tarde");
        }
        
        TFin = System.currentTimeMillis(); //Tomamos la hora en que finalizó el algoritmo y la almacenamos en la variable T
        tiempo = (TFin - TInicio)/ 1000; //Calculamos los milisegundos de diferencia
        logger.warning("termina el metodo cargarCoberturas con una duracion de: "+tiempo+" segundos");
    }
    
    public void cargaDatos() {
        logger.warning("inicia el metodo cargaDatos");
        double TInicio, TFin, tiempo; //Variables para determinar el tiempo de ejecución
        TInicio = System.currentTimeMillis(); //Tomamos la hora en que inicio el algoritmo y la almacenamos en la variable inicio
        
        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();                
         OperationBinding operationBinding = null;
         try{  
             operationBinding = bindings.getOperationBinding("consultarDatosActivo");
             operationBinding.execute();
         }catch(Exception e){
             logger.warning("Error al ejecutar operBinding consultarDatosActivo ->",e);
         }
        
         if (!operationBinding.getErrors().isEmpty()) {
           logger.warning("Error al ejecutar operBinding consultarDatosActivo-> "+operationBinding.getErrors());
         }
        
        TFin = System.currentTimeMillis(); //Tomamos la hora en que finalizó el algoritmo y la almacenamos en la variable T
        tiempo = (TFin - TInicio)/ 1000; //Calculamos los milisegundos de diferencia 
        logger.warning("Termina el metodo cargaDatos con una duracion de: "+tiempo+" segundos");
    }

    public String getContratoDesembolso() {
        return contratoDesembolso;
    }

    public void setContratoDesembolso(String contratoDesembolso) {
        this.contratoDesembolso = contratoDesembolso;
    }

    public Long getIdcontratoDesembolso() {
        return idcontratoDesembolso;
    }

    public void setIdcontratoDesembolso(Long idcontratoDesembolso) {
        this.idcontratoDesembolso = idcontratoDesembolso;
    }
}
