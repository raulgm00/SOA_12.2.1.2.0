package org.bcie.fenix.view.ambiental;

import java.io.Serializable;

import java.util.Arrays;
import java.util.List;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;

public class ObtenerValores implements Serializable {
    @SuppressWarnings("compatibility:5691160533748942212")
    private static final long serialVersionUID = 1L;
    private static final List<String> ProductosPCT = Arrays.asList("16", "17", "18", "19", "21", "22", "23");
    private static final List<String> ProductosLGC = Arrays.asList("3", "26");
    private static final List<String> Tareas= Arrays.asList("28", "32","34", "35", "36", "37", "38", "152");
    //private static final List<String> TareasPC03_ANALISIS= Arrays.asList("28", "32");
    //private static final List<String> TareasPC06_EVALUACION= Arrays.asList("34", "35", "36", "37", "38");
    //private static final List<String> TareasPC06_DESEMBOLSO=Arrays.asList("152");
    /* ===============  VARIABLES   =================*/
    private static ADFLogger logger = null;
    String idOperacion = "";
    String idProducto = "";
    String estadoBPM = "";
    String rolEjecutivo = "";
    String tarea = "";
    Boolean esEditable = false;
    boolean banOperacion = false;
    boolean banProducto = false;
    boolean banEstadoBPM = false;
    boolean banRol = false;
    boolean banTarea = false;

    public ObtenerValores() {
        super();
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }

    /**
     * Método empleado para la obtencion de los valores de los parametros del TaskFlow
     * @author : S&P Solutions
     * @param  :
     * @version: v1.0
     * @Fecha  : 03/11/2019
     */
    public void valoresParametros() {
        logger.warning("==== Se ejecuta: valoresParametros  =========");
        //Obtener Id de Operacion
        idOperacion =
            JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}") != null ?
            String.valueOf(JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}")) : "";
        
        if (!(idOperacion.trim().isEmpty())) {
            banOperacion = true;
        }
        logger.warning("==== Se recupera idOperacion:" + idOperacion + " valor de banOperacion:" + banOperacion);
        //Obtener  Id de producto
        idProducto =
            JSFUtils.resolveExpression("#{pageFlowScope.pIdProducto}") != null ?
            String.valueOf(JSFUtils.resolveExpression("#{pageFlowScope.pIdProducto}")) : "";
        
        // banProducto = validarIdProducto(idProducto);
        logger.warning("==== Se recupera idProducto:" + idProducto + " valor de banProducto:" + banProducto);
        //Obtener Estado Tarea de BPM
        estadoBPM =
            JSFUtils.resolveExpression("#{pageFlowScope.pEstadoBpm}") != null ?
            (String) JSFUtils.resolveExpression("#{pageFlowScope.pEstadoBpm}") : "";
        
        banEstadoBPM = validarEstado(estadoBPM);
        logger.warning("==== Se recupera estadoBPM:" + estadoBPM + " valor de banEstadoBPM:" + banEstadoBPM);
        //Obtener Tarea
        tarea =
            JSFUtils.resolveExpression("#{pageFlowScope.pTarea}") != null ?
            String.valueOf(JSFUtils.resolveExpression("#{pageFlowScope.pTarea}")) : "";
        
        //Obtener Rol de Ejecutivo
        rolEjecutivo =
            JSFUtils.resolveExpression("#{pageFlowScope.pRolEjecutivo}") != null ?
            String.valueOf( JSFUtils.resolveExpression("#{pageFlowScope.pRolEjecutivo}")): "";
        
        // banRol = validarRolEjecutivo(rolEjecutivo);
        logger.warning("==== Se recupera rolEjecutivo:" + rolEjecutivo + " valor de banRol:" + banRol);

        if (banEstadoBPM) {
            setEditable(idProducto, tarea);
        } else {
            logger.warning("==== Se establece valor  esEditable:" + false);
            AdfFacesContext.getCurrentInstance().getPageFlowScope().put("pEditable", false);
        }
        consultarPorIdOperacion();
    }

    /**
     * Método empleado para validar valores de los parametros del TaskFlow y establecer si se edita o no la interfaz
     * @author : S&P Solutions
     * @param  :
     * @version: v1.0
     * @Fecha  : 03/11/2019
     */
    public void setEditable(String idProducto,String tarea) {
        boolean bandera = false;
        //Casos de producto
        if (ProductosPCT.contains(idProducto)) { //Caso PCT
            logger.warning("==== Operacion: PCT ===");
            if (Tareas.contains(tarea)) { //Caso PCT
                logger.warning("==== ID de tarea valida  ===");
                bandera = true;
            } 
        } else if (ProductosLGC.contains(idProducto)) { //Caso LGC
            logger.warning("==== Operacion: LGC ===");
            if (Tareas.contains(tarea)) { 
                logger.warning("==== ID de tarea valida  ===");
                bandera = true;
            } 
        } else { //Cualquier otro producto
            logger.warning("==== Operacion: Otra ===");
            if (Tareas.contains(tarea)) {
                logger.warning("==== ID de tarea valida  ===");
                bandera = true;
            } 
        }
        esEditable = bandera;
        logger.warning("==== Se establece valor  esEditable:" + esEditable);
        AdfFacesContext.getCurrentInstance().getPageFlowScope().put("pEditable", esEditable);
        Boolean valor = (Boolean) AdfFacesContext.getCurrentInstance().getPageFlowScope().get("pEditable");
        logger.warning("==== Se lee valor  esEditable:" + valor);

    }

    /**
     * Método empleado para validar el Id de Producto
     * @author : S&P Solutions
     * @param  : String
     * @return : boolean
     * @version: v1.0
     * @Fecha  : 03/11/2019
     */

    public boolean validarIdProducto(String idProducto) {
        boolean ban = false;
        if (!(idProducto.trim().isEmpty())) {
            ban = true;
        }

        return ban;
    }


    /**
     * Método empleado para validar el estado de la tarea BPM
     * @author : S&P Solutions
     * @param  : String
     * @return : boolean
     * @version: v1.0
     * @Fecha  : 03/11/2019
     */

    public boolean validarEstado(String estado) {
        return estado.trim().isEmpty() ? false : (estado.equals("COMPLETED") ? false : true);
    }


    /**
     * Método empleado para validar el rol del ejecutivo
     * @author : S&P Solutions
     * @param  : String
     * @return : boolean
     * @version: v1.0
     * @Fecha  : 03/11/2019
     */

    public boolean validarRolEjecutivo(String rolEjecutivo) {
        boolean ban = false;
        if (!(rolEjecutivo.trim().isEmpty())) {
            ban = true;
        }

        return ban;
    }


    /**
     * Método empleado para consultar la informacion de la operacion y poblar VO
     * @author : S&P Solutions
     * @return : boolean
     * @version: v1.0
     * @Fecha  : 03/11/2019
     */

    public void consultarPorIdOperacion() {
        logger.warning("==== Se ejecuta consultarPorIdOperacion para CA Editable   ====");
        DCIteratorBinding tcaCategoriaAmbiental = null;

        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding opFiltrar = bindings.getOperationBinding("setpidOperacion");
        opFiltrar.getParamsMap().put("value", Integer.parseInt(idOperacion));
        opFiltrar.execute();
        if (!opFiltrar.getErrors().isEmpty()) {
            // Manejo de errores
        }
        tcaCategoriaAmbiental = ADFUtils.getDCBindingContainer().findIteratorBinding("ClasificacionAmbientalEditableVOIterator");
        tcaCategoriaAmbiental.executeQuery();

    }
}
