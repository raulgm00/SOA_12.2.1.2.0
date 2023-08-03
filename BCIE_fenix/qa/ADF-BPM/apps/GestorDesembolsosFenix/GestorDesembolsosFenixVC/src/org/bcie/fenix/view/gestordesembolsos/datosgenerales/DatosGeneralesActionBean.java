package org.bcie.fenix.view.gestordesembolsos.datosgenerales;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.model.BindingContext;
import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.binding.AttributeBinding;
import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;

public class DatosGeneralesActionBean implements Serializable {
    @SuppressWarnings("compatibility:6291612935089248965")
    private static final long serialVersionUID = 1L;
    private static ADFLogger logger = null;
    private RichSelectOneChoice destinoUI;
    private RichSelectOneChoice modalidadUI;
    private RichSelectOneChoice actividadF1UI;
    private RichSelectOneChoice actividadUI;
    private RichSelectOneChoice areaUI;
    private RichSelectOneChoice ejeUI;
    private RichSelectOneChoice proyectoUI;
    private RichSelectOneChoice programaUI;
    
    private Map catalogoPrograma;
    private RichOutputText iniciativaUI;


    public DatosGeneralesActionBean() {
        super();
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }

    public void cambioPrograma(ValueChangeEvent valueChangeEvent) {
        
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        
        logger.warning("programa: " + valueChangeEvent.getNewValue());
        String valor = null;
        valor = (String) valueChangeEvent.getNewValue();
        Boolean actualizaProyecto = Boolean.FALSE;
        AttributeBinding esIntermediario;
        esIntermediario = ADFUtils.findControlBinding("EsIntermediario");
        actualizaProyecto = (Boolean) esIntermediario.getInputValue();
        if(actualizaProyecto){
                precargaActividadF1(valor);
                AdfFacesContext.getCurrentInstance().addPartialTarget(destinoUI);
                AdfFacesContext.getCurrentInstance().addPartialTarget(modalidadUI);
                AdfFacesContext.getCurrentInstance().addPartialTarget(actividadF1UI);
                AdfFacesContext.getCurrentInstance().addPartialTarget(actividadUI);
                AdfFacesContext.getCurrentInstance().addPartialTarget(iniciativaUI);
                AdfFacesContext.getCurrentInstance().addPartialTarget(areaUI);
                AdfFacesContext.getCurrentInstance().addPartialTarget(ejeUI);
                AdfFacesContext.getCurrentInstance().addPartialTarget(proyectoUI);
            }
    }

    public void cambioActividadF1(ValueChangeEvent valueChangeEvent) {
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        logger.warning("programa: " + valueChangeEvent.getNewValue());
        Integer valor = null;
        valor = (Integer) valueChangeEvent.getNewValue();
        Boolean actualizaProyecto = Boolean.FALSE;
        AttributeBinding esIntermediario;
        esIntermediario = ADFUtils.findControlBinding("EsIntermediario");
        actualizaProyecto = (Boolean) esIntermediario.getInputValue();
        if(actualizaProyecto){
        precargaActividad(valor);
        AdfFacesContext.getCurrentInstance().addPartialTarget(actividadUI);
        AdfFacesContext.getCurrentInstance().addPartialTarget(iniciativaUI);
        AdfFacesContext.getCurrentInstance().addPartialTarget(areaUI);
        AdfFacesContext.getCurrentInstance().addPartialTarget(ejeUI);
        AdfFacesContext.getCurrentInstance().addPartialTarget(proyectoUI);
        }
    }

    public void cambioActividad(ValueChangeEvent valueChangeEvent) {
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        logger.warning("ActividadF1: " + valueChangeEvent.getNewValue());
        Integer valor = null;
        valor = (Integer) valueChangeEvent.getNewValue();
        Boolean actualizaProyecto = Boolean.FALSE;
        AttributeBinding esIntermediario;
        esIntermediario = ADFUtils.findControlBinding("EsIntermediario");
        actualizaProyecto = (Boolean) esIntermediario.getInputValue();
        if(actualizaProyecto){
        precargaArea(valor);
        AdfFacesContext.getCurrentInstance().addPartialTarget(iniciativaUI);
        AdfFacesContext.getCurrentInstance().addPartialTarget(areaUI);
        AdfFacesContext.getCurrentInstance().addPartialTarget(ejeUI);
        AdfFacesContext.getCurrentInstance().addPartialTarget(proyectoUI);
        }
    }

    public void cambioArea(ValueChangeEvent valueChangeEvent) {
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        logger.warning("Actividad: " + valueChangeEvent.getNewValue());
        Integer valor = null;
        valor = (Integer) valueChangeEvent.getNewValue();
        Boolean actualizaProyecto = Boolean.FALSE;
        AttributeBinding esIntermediario;
        esIntermediario = ADFUtils.findControlBinding("EsIntermediario");
        actualizaProyecto = (Boolean) esIntermediario.getInputValue();
        if(actualizaProyecto){
        precargaEje(valor);
        AdfFacesContext.getCurrentInstance().addPartialTarget(ejeUI);
        AdfFacesContext.getCurrentInstance().addPartialTarget(proyectoUI);
        }
    }

    public void cambioEje(ValueChangeEvent valueChangeEvent) {
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        logger.warning("Area: " + valueChangeEvent.getNewValue());
        Integer valor = null;
        valor = (Integer) valueChangeEvent.getNewValue();
        Boolean actualizaProyecto = Boolean.FALSE;
        AttributeBinding esIntermediario;
        esIntermediario = ADFUtils.findControlBinding("EsIntermediario");
        actualizaProyecto = (Boolean) esIntermediario.getInputValue();
        if(actualizaProyecto){
        precargaProyecto(valor);   
        AdfFacesContext.getCurrentInstance().addPartialTarget(proyectoUI);
        }
    }

    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }

    public void setDestinoUI(RichSelectOneChoice destinoUI) {
        this.destinoUI = destinoUI;
    }

    public RichSelectOneChoice getDestinoUI() {
        return destinoUI;
    }

    public void cambioAPrograma(ValueChangeEvent valueChangeEvent) {
        logger.warning("programa: " + valueChangeEvent.getNewValue());
        // Add event code here...

        AdfFacesContext.getCurrentInstance().addPartialTarget(destinoUI);
    }


    public RichSelectOneChoice getModalidadUI() {
        return modalidadUI;
    }

    public void setModalidadUI(RichSelectOneChoice modalidadUI) {
        this.modalidadUI = modalidadUI;
    }

    public RichSelectOneChoice getActividadF1UI() {
        return actividadF1UI;
    }

    public void setActividadF1UI(RichSelectOneChoice actividadF1UI) {
        this.actividadF1UI = actividadF1UI;
    }

    public RichSelectOneChoice getActividadUI() {
        return actividadUI;
    }

    public void setActividadUI(RichSelectOneChoice actividadUI) {
        this.actividadUI = actividadUI;
    }

    public RichSelectOneChoice getAreaUI() {
        return areaUI;
    }

    public void setAreaUI(RichSelectOneChoice areaUI) {
        this.areaUI = areaUI;
    }

    public RichSelectOneChoice getEjeUI() {
        return ejeUI;
    }

    public void setEjeUI(RichSelectOneChoice ejeUI) {
        this.ejeUI = ejeUI;
    }

    public RichSelectOneChoice getProyectoUI() {
        return proyectoUI;
    }

    public void setProyectoUI(RichSelectOneChoice proyectoUI) {
        this.proyectoUI = proyectoUI;
    }

    public void precargaActividadF1(String programa) {
       Map resultado=new HashMap<String, Object>();
        Boolean registro = Boolean.FALSE;
        Boolean actualizaActividad = Boolean.FALSE;
        Integer idActividadF1 = null;
        Boolean actualizaIniciativa = Boolean.FALSE;
        Integer idIniciativa = null;
        Integer idActividad = null;
        Boolean actualizaArea = Boolean.FALSE;
        Integer idArea = null;
        Boolean actualizaEje = Boolean.FALSE;
        Integer idEje = null;
        Boolean actualizaProyecto = Boolean.FALSE;
        Integer idproyecto = null;

        AttributeBinding idActividadEconomicaF1;
        idActividadEconomicaF1 = ADFUtils.findControlBinding("IdActividadEconomicaF1");
        idActividadEconomicaF1.setInputValue(null);
        AttributeBinding idIniciativaEstrategica;
        idIniciativaEstrategica = ADFUtils.findControlBinding("IdIniciativaEstrategica");
        idIniciativaEstrategica.setInputValue(null);
        AttributeBinding idActividadEconomica;
        idActividadEconomica = ADFUtils.findControlBinding("IdActividadEconomica");
        idActividadEconomica.setInputValue(null);
        AttributeBinding idAreaFocalizacion;
        idAreaFocalizacion = ADFUtils.findControlBinding("IdAreaFocalizacion");
        idAreaFocalizacion.setInputValue(null);
        AttributeBinding idEjeEstrategico;
        idEjeEstrategico = ADFUtils.findControlBinding("IdEjeEstrategico");
        idEjeEstrategico.setInputValue(null);
        AttributeBinding idProyectoMunicipal;
        idProyectoMunicipal = ADFUtils.findControlBinding("IdProyectoMunicipal");
        idProyectoMunicipal.setInputValue(null);

        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("criterioActividadEconomicaF1");
        operationBinding.getParamsMap().put("codigoPrograma", programa);
        //operationBinding.execute();
        resultado = (HashMap<String, Object>) operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            JSFUtils.addFacesErrorMessage("Error al obtener los datos, error al ejecutar el metodo intente más tarde");
        } else {
       //     resultado = (HashMap<String, Object>) operationBinding.getResult();
           logger.warning("registro: " + resultado.get("registro"));
            
            logger.warning("se obtuvieron datos " +(Boolean) resultado.get("registro"));
            logger.warning("ActualizaActividadF1 " +(Boolean) resultado.get("actualizaActividadF1"));
            logger.warning("ActualizaIniciativa " +(Boolean) resultado.get("actualizaIniciativa"));
            logger.warning("ActualizaArea " +(Boolean) resultado.get("actualizaArea"));
            logger.warning("ActualizaEje " +(Boolean) resultado.get("actualizaEje"));
            logger.warning("ActualiaProyecto " +(Boolean) resultado.get("actualizaProyecto"));
            logger.warning("actividadF1 " +(Integer) resultado.get("actividadF1"));
            logger.warning("actividad " +(Integer) resultado.get("actividad"));
            logger.warning("idIniciativa " +(Integer) resultado.get("iniciativa"));
            logger.warning("idArea " +(Integer) resultado.get("area"));
            logger.warning("idEje " +(Integer) resultado.get("eje"));
            logger.warning("idProyecto " +(Integer) resultado.get("proyecto"));
            
            if (null != (Boolean) resultado.get("registro")) {
                registro = (Boolean) resultado.get("registro");
                if (registro) {
                    actualizaActividad = (Boolean) resultado.get("actualizaActividadF1");
                    actualizaIniciativa = (Boolean) resultado.get("actualizaIniciativa");
                    actualizaArea = (Boolean) resultado.get("actualizaArea");
                    actualizaEje = (Boolean) resultado.get("actualizaEje");
                    actualizaProyecto = (Boolean) resultado.get("actualizaProyecto");

                    if (actualizaActividad) {
                        idActividadF1 = (Integer) resultado.get("actividadF1");
                        idActividadEconomicaF1.setInputValue(idActividadF1);
                        if (actualizaIniciativa) {
                            idIniciativa = (Integer) resultado.get("iniciativa");
                            idActividad = (Integer) resultado.get("actividad");
                            idIniciativaEstrategica.setInputValue(idIniciativa);
                            idActividadEconomica.setInputValue(idActividad);
                            if (actualizaArea) {
                                idArea = (Integer) resultado.get("area");
                                idAreaFocalizacion.setInputValue(idArea);
                                if (actualizaEje) {
                                    idEje = (Integer) resultado.get("eje");
                                    idEjeEstrategico.setInputValue(idEje);
                                    if (actualizaProyecto) {
                                        idproyecto = (Integer) resultado.get("proyecto");
                                        idProyectoMunicipal.setInputValue(idproyecto);
                                    }
                                }
                            }
                        }
                    }
                } else {
                    JSFUtils.addFacesErrorMessage("Error no se encontraron los datos al cargar la actividad F1");
                }
            } else {
                JSFUtils.addFacesErrorMessage("Error no se encontraron los datos");
            }
        }
    }

    public void precargaActividad(Integer valor) {
        logger.warning("Inicio method precargaActividad" );
        Map resultado = new HashMap();
        Boolean registro = Boolean.FALSE;
        String programa = null;
        Boolean actualizaIniciativa = Boolean.FALSE;
        Integer idIniciativa = null;
        Integer idActividad = null;
        Boolean actualizaArea = Boolean.FALSE;
        Integer idArea = null;
        Boolean actualizaEje = Boolean.FALSE;
        Integer idEje = null;
        Boolean actualizaProyecto = Boolean.FALSE;
        Integer idproyecto = null;

        AttributeBinding idProgramaOperacion;
        idProgramaOperacion = ADFUtils.findControlBinding("ProgramaOperacion");
        programa = (String) idProgramaOperacion.getInputValue();
        logger.warning("precargaActividad programa: " + programa );
        
        AttributeBinding idIniciativaEstrategica;
        idIniciativaEstrategica = ADFUtils.findControlBinding("IdIniciativaEstrategica");
        idIniciativaEstrategica.setInputValue(null);
        AttributeBinding idActividadEconomica;
        idActividadEconomica = ADFUtils.findControlBinding("IdActividadEconomica");
        idActividadEconomica.setInputValue(null);
        AttributeBinding idAreaFocalizacion;
        idAreaFocalizacion = ADFUtils.findControlBinding("IdAreaFocalizacion");
        idAreaFocalizacion.setInputValue(null);
        AttributeBinding idEjeEstrategico;
        idEjeEstrategico = ADFUtils.findControlBinding("IdEjeEstrategico");
        idEjeEstrategico.setInputValue(null);
        AttributeBinding idProyectoMunicipal;
        idProyectoMunicipal = ADFUtils.findControlBinding("IdProyectoMunicipal");
        idProyectoMunicipal.setInputValue(null);

        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("criterioIniciativa");
        operationBinding.getParamsMap().put("codigoPrograma", programa);
        operationBinding.getParamsMap().put("actividadF1", valor);
        operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            JSFUtils.addFacesErrorMessage("Error al obtener los datos, intente más tarde");
        } else {
            resultado = (HashMap<String, Object>) operationBinding.getResult();
            if (null != (Boolean) resultado.get("registro")) {
                registro = (Boolean) resultado.get("registro");
                if (registro) {
                    actualizaIniciativa = (Boolean) resultado.get("actualizaIniciativa");
                    actualizaArea = (Boolean) resultado.get("actualizaArea");
                    actualizaEje = (Boolean) resultado.get("actualizaEje");
                    actualizaProyecto = (Boolean) resultado.get("actualizaProyecto");

                    if (actualizaIniciativa) {
                        idIniciativa = (Integer) resultado.get("iniciativa");
                        idActividad = (Integer) resultado.get("actividad");
                        idIniciativaEstrategica.setInputValue(idIniciativa);
                        idActividadEconomica.setInputValue(idActividad);
                        if (actualizaArea) {
                            idArea = (Integer) resultado.get("area");
                            idAreaFocalizacion.setInputValue(idArea);
                            if (actualizaEje) {
                                idEje = (Integer) resultado.get("eje");
                                idEjeEstrategico.setInputValue(idEje);
                                if (actualizaProyecto) {
                                    idproyecto = (Integer) resultado.get("proyecto");
                                    idProyectoMunicipal.setInputValue(idproyecto);
                                }
                            }
                        }
                    }
                } else {
                    JSFUtils.addFacesErrorMessage("Error no se encontraron los datos");
                }
            } else {
                JSFUtils.addFacesErrorMessage("Error no se encontraron los datos");
            }
        }
        logger.warning("Fin method precargaActividad" );
    }

    public void precargaArea(Integer valor) {
        logger.warning("Inicio method precargaArea" );
        Map resultado = new HashMap();
        Boolean registro = Boolean.FALSE;
        String programa = null;
        Integer actividadF1 = null;
        Integer idIniciativa = null;
        Boolean actualizaArea = Boolean.FALSE;
        Integer idArea = null;
        Boolean actualizaEje = Boolean.FALSE;
        Integer idEje = null;
        Boolean actualizaProyecto = Boolean.FALSE;
        Integer idproyecto = null;

        AttributeBinding idProgramaOperacion;
        idProgramaOperacion = ADFUtils.findControlBinding("ProgramaOperacion");
        programa = (String) idProgramaOperacion.getInputValue();
        logger.warning("precargaArea programa: " + programa );
        
        AttributeBinding idActividadEconomicaF1;
        idActividadEconomicaF1 = ADFUtils.findControlBinding("IdActividadEconomicaF1");
        actividadF1 = (Integer) idActividadEconomicaF1.getInputValue();

        AttributeBinding idIniciativaEstrategica;
        idIniciativaEstrategica = ADFUtils.findControlBinding("IdIniciativaEstrategica");
        idIniciativaEstrategica.setInputValue(null);
        AttributeBinding idAreaFocalizacion;
        idAreaFocalizacion = ADFUtils.findControlBinding("IdAreaFocalizacion");
        idAreaFocalizacion.setInputValue(null);
        AttributeBinding idEjeEstrategico;
        idEjeEstrategico = ADFUtils.findControlBinding("IdEjeEstrategico");
        idEjeEstrategico.setInputValue(null);
        AttributeBinding idProyectoMunicipal;
        idProyectoMunicipal = ADFUtils.findControlBinding("IdProyectoMunicipal");
        idProyectoMunicipal.setInputValue(null);

        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("criterioArea");
        operationBinding.getParamsMap().put("codigoPrograma", programa);
        operationBinding.getParamsMap().put("actividadF1", actividadF1);
        operationBinding.getParamsMap().put("actividad", valor);
        operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            JSFUtils.addFacesErrorMessage("Error al obtener los datos, intente más tarde");
        } else {
            resultado = (HashMap<String, Object>) operationBinding.getResult();
            if (null != (Boolean) resultado.get("registro")) {
                registro = (Boolean) resultado.get("registro");
                if (registro) {
                    actualizaArea = (Boolean) resultado.get("actualizaArea");
                    actualizaEje = (Boolean) resultado.get("actualizaEje");
                    actualizaProyecto = (Boolean) resultado.get("actualizaProyecto");

                    idIniciativa = (Integer) resultado.get("iniciativa");
                    idIniciativaEstrategica.setInputValue(idIniciativa);
                    if (actualizaArea) {
                        idArea = (Integer) resultado.get("area");
                        idAreaFocalizacion.setInputValue(idArea);
                        if (actualizaEje) {
                            idEje = (Integer) resultado.get("eje");
                            idEjeEstrategico.setInputValue(idEje);
                            if (actualizaProyecto) {
                                idproyecto = (Integer) resultado.get("proyecto");
                                idProyectoMunicipal.setInputValue(idproyecto);
                            }
                        }
                    }
                } else {
                    JSFUtils.addFacesErrorMessage("Error no se encontraron los datos");
                }
            } else {
                JSFUtils.addFacesErrorMessage("Error no se encontraron los datos");
            }
        }
        logger.warning("Fin method precargaArea" );
    }

    public void precargaEje(Integer valor) {
        logger.warning("Inicio method precargaEje" );
        Map resultado = new HashMap();
        Boolean registro = Boolean.FALSE;
        String programa = null;
        Integer actividadF1 = null;
        Integer idIniciativa = null;
        Integer idArea = null;
        Boolean actualizaEje = Boolean.FALSE;
        Integer idEje = null;
        Boolean actualizaProyecto = Boolean.FALSE;
        Integer idproyecto = null;

        AttributeBinding idProgramaOperacion;
        idProgramaOperacion = ADFUtils.findControlBinding("ProgramaOperacion");
        programa = (String) idProgramaOperacion.getInputValue();
        logger.warning("precargaEje programa: " + programa );
        
        AttributeBinding idActividadEconomicaF1;
        idActividadEconomicaF1 = ADFUtils.findControlBinding("IdActividadEconomicaF1");
        actividadF1 = (Integer) idActividadEconomicaF1.getInputValue();

        AttributeBinding idIniciativaEstrategica;
        idIniciativaEstrategica = ADFUtils.findControlBinding("IdActividadEconomica");
        idIniciativa = (Integer) idIniciativaEstrategica.getInputValue();

        AttributeBinding idEjeEstrategico;
        idEjeEstrategico = ADFUtils.findControlBinding("IdEjeEstrategico");
        idEjeEstrategico.setInputValue(null);
        AttributeBinding idProyectoMunicipal;
        idProyectoMunicipal = ADFUtils.findControlBinding("IdProyectoMunicipal");
        idProyectoMunicipal.setInputValue(null);

        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("criterioEje");
        operationBinding.getParamsMap().put("codigoPrograma", programa);
        operationBinding.getParamsMap().put("actividadF1", actividadF1);
        operationBinding.getParamsMap().put("iniciativa", idIniciativa);
        operationBinding.getParamsMap().put("area", valor);
        operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            JSFUtils.addFacesErrorMessage("Error no se encontraron los datos");
        } else {
            resultado = (HashMap<String, Object>) operationBinding.getResult();
            if (null != (Boolean) resultado.get("registro")) {
                registro = (Boolean) resultado.get("registro");
                if (registro) {
                    actualizaEje = (Boolean) resultado.get("actualizaEje");
                    actualizaProyecto = (Boolean) resultado.get("actualizaProyecto");

                    if (actualizaEje) {
                        idEje = (Integer) resultado.get("eje");
                        idEjeEstrategico.setInputValue(idEje);
                        if (actualizaProyecto) {
                            idproyecto = (Integer) resultado.get("proyecto");
                            idProyectoMunicipal.setInputValue(idproyecto);
                        }
                    }
                } else {
                    JSFUtils.addFacesErrorMessage("Error no se encontraron los datos");
                }
            } else {
                JSFUtils.addFacesErrorMessage("Error no se encontraron los datos");
            }
        }
        logger.warning("Fin method precargaEje" );
    }

    public void precargaProyecto(Integer valor) {
        logger.warning("Inicio method precargaProyecto" );
        Map resultado = new HashMap();
        Boolean registro = Boolean.FALSE;
        String programa = null;
        Integer actividadF1 = null;
        Integer idIniciativa = null;
        Integer idArea = null;
        Boolean actualizaProyecto = Boolean.FALSE;
        Integer idproyecto = null;

        AttributeBinding idProgramaOperacion;
        idProgramaOperacion = ADFUtils.findControlBinding("ProgramaOperacion");
        programa = (String) idProgramaOperacion.getInputValue();
        logger.warning("precargaProyecto programa: " + programa );
        
        AttributeBinding idActividadEconomicaF1;
        idActividadEconomicaF1 = ADFUtils.findControlBinding("IdActividadEconomicaF1");
        actividadF1 = (Integer) idActividadEconomicaF1.getInputValue();

        AttributeBinding idIniciativaEstrategica;
        idIniciativaEstrategica = ADFUtils.findControlBinding("IdActividadEconomica");
        idIniciativa = (Integer) idIniciativaEstrategica.getInputValue();

        AttributeBinding idAreaFocalizacion;
        idAreaFocalizacion = ADFUtils.findControlBinding("IdAreaFocalizacion");
        idArea = (Integer) idAreaFocalizacion.getInputValue();

        AttributeBinding idProyectoMunicipal;
        idProyectoMunicipal = ADFUtils.findControlBinding("IdProyectoMunicipal");
        idProyectoMunicipal.setInputValue(null);

        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("criterioProyecto");
        operationBinding.getParamsMap().put("codigoPrograma", programa);
        operationBinding.getParamsMap().put("actividadF1", actividadF1);
        operationBinding.getParamsMap().put("iniciativa", idIniciativa);
        operationBinding.getParamsMap().put("area", idArea);
        operationBinding.getParamsMap().put("eje", valor);
        operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            JSFUtils.addFacesErrorMessage("Error no se encontraron los datos");
        } else {
            resultado = (HashMap<String, Object>) operationBinding.getResult();
            if (null != (Boolean) resultado.get("registro")) {
                registro = (Boolean) resultado.get("registro");
                if (registro) {
                    actualizaProyecto = (Boolean) resultado.get("actualizaProyecto");
                    if (actualizaProyecto) {
                        idproyecto = (Integer) resultado.get("proyecto");
                        idProyectoMunicipal.setInputValue(idproyecto);
                    }
                } else {
                    JSFUtils.addFacesErrorMessage("Error no se encontraron los datos");
                }
            } else {
                JSFUtils.addFacesErrorMessage("Error no se encontraron los datos");
            }
        }
        logger.warning("Fin method precargaProyecto" );
    }
    
    public void actualizarDatos(Integer opcion) {
        Map resultado = new HashMap();
        Boolean registro = Boolean.FALSE;

        Boolean actualizaProyecto = Boolean.FALSE;
        Integer valida = null;

        AttributeBinding esIntermediario;
        esIntermediario = ADFUtils.findControlBinding("EsIntermediario");
        actualizaProyecto = (Boolean) esIntermediario.getInputValue();

      
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("validarCampos");
        operationBinding.getParamsMap().put("aplicaProyecto", actualizaProyecto);
        operationBinding.getParamsMap().put("valida", opcion);

        operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            JSFUtils.addFacesErrorMessage("Error no se encontraron los datos");
        } else {
            resultado = (HashMap<String, Object>) operationBinding.getResult();
            if (null != (Boolean) resultado.get("valida")) {
                registro = (Boolean) resultado.get("registro");
                if ((Boolean) resultado.get("actualiza")) {
                    logger.warning("Se actualizo correctamente");
                } else {
                    JSFUtils.addFacesErrorMessage("Error no se encontraron los datos");
                }
            } else {
                JSFUtils.addFacesErrorMessage("EError no se encontraron los datos");
            }
        }
    }

    public Map getCatalogoPrograma() {
        return catalogoPrograma;
    }

    public void setCatalogoPrograma(Map catalogoPrograma) {
        this.catalogoPrograma = catalogoPrograma;
    }

    public void setIniciativaUI(RichOutputText iniciativaUI) {
        this.iniciativaUI = iniciativaUI;
    }

    public RichOutputText getIniciativaUI() {
        return iniciativaUI;
    }

    public void guardarDatos(ActionEvent actionEvent) {
        Integer novalida=1;
        actualizarDatos(novalida);
        // Add event code here...
    }

    public void guardarValidar(ActionEvent actionEvent) {
        // Add event code here...
        Integer novalida=2;
        actualizarDatos(novalida);
    }

    public void aplicarFiltroProgramaPorIdTasaValueChangeListener(ValueChangeEvent valueChangeEvent) {
        logger.warning("Entra en aplicarFiltroProgramaPorIdTasaValueChangeListener.");
        BindingContainer bindings = null;
        OperationBinding operationBinding = null;
        Integer nuevoTipoTasa = null;
        Boolean cambioFiltro = Boolean.FALSE;
        try{
            
            valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
            logger.warning("tipo de tasa nueva : " + valueChangeEvent.getNewValue());
            nuevoTipoTasa = (Integer) valueChangeEvent.getNewValue();
            
            bindings = getBindings();
            operationBinding = bindings.getOperationBinding("aplicarCambioFiltroProgramaPorTipoTasa");
            operationBinding.getParamsMap().put("idTipoTasaDesembolso", nuevoTipoTasa);  
            operationBinding.execute();
            
            if(operationBinding.getErrors().isEmpty()){
                if(null != operationBinding.getResult()){
                    cambioFiltro = (Boolean)operationBinding.getResult();
                    if(!cambioFiltro){
                        logger.warning("El cambio de la tasa para filtrar el programa no se aplico.");
                    }else{
                        logger.warning("Cambio de tasa se aplico correctamente.");
                    }
                }else{
                    logger.warning("Error, el valor de retorno es nulo.");
                }
            }else{
                logger.warning("Se obtuvieron errores al aplicar el cambio de tasa.");
                JSFUtils.addFacesErrorMessage("Error." + operationBinding.getErrors());
            }
        }catch(Exception e){
            logger.warning("Error en aplicarFiltroProgramaPorIdTasaValueChangeListener.", e);
        }
    }
    
    
    public RichSelectOneChoice getProgramaUI() {
        logger.warning("Inicio getProgramaUI"); 
        return programaUI;
    }

    public void setProgramaUI(RichSelectOneChoice programaUI) {
        logger.warning("Inicio setProgramaUI"); 
        try{ 
            logger.warning("asigno setProgramaUI"); 
            this.programaUI = programaUI;
        }catch(Exception e){
            logger.warning("Error en setProgramaUI.", e);
        }
        logger.warning("Fin setProgramaUI"); 
    }

}
