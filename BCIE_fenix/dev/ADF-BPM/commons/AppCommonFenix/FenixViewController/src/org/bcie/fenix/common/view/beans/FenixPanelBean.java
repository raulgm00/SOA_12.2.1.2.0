package org.bcie.fenix.common.view.beans;

import java.io.Serializable;

import oracle.adf.share.logging.ADFLogger;
import org.apache.myfaces.trinidad.event.DisclosureEvent;

public class FenixPanelBean implements Serializable
{
    
    @SuppressWarnings("compatibility:-871412813913987725")
    private static final long serialVersionUID = -3772298628710862923L;

    private static ADFLogger logger = null;
    
    /* Propiedades para manejo de eventos de acordeones*/
    private Boolean panelDocumentosActivated;
    private Boolean panelComentariosActivated;
    private Boolean panelDetalleOperacionActivated;
    private Boolean panelDatosTareaActivated;
    private Boolean panelMatrizTccActivated;
    private Boolean panelAdquisicionesActivated;
    private Boolean panelDerivadosImplicitosActivated;

    public FenixPanelBean()
    {
        super();

        if (logger == null)
        {
            logger = ADFLogger.createADFLogger(this.getClass());
        }

        panelDocumentosActivated = Boolean.TRUE; //TODO esto debe de resolverse de manra que el TF funciona de manara deferida
        panelComentariosActivated = Boolean.FALSE;
        panelDetalleOperacionActivated = Boolean.FALSE;
        panelDatosTareaActivated = Boolean.FALSE;
        panelMatrizTccActivated = Boolean.FALSE;
        panelDerivadosImplicitosActivated= Boolean.FALSE;
        setPanelAdquisicionesActivated(Boolean.FALSE);
    }

    public Boolean getPanelDocumentosActivated()
    {
        return panelDocumentosActivated;
    }

    public Boolean getPanelComentariosActivated()
    {
        return panelComentariosActivated;
    }

    public Boolean getPanelDetalleOperacionActivated()
    {
        return panelDetalleOperacionActivated;
    }

    public Boolean getPanelDatosTareaActivated()
    {
        return panelDatosTareaActivated;
    }

    public Boolean getPanelMatrizTccActivated() {
        return panelMatrizTccActivated;
    }

    /**
     * M�todo que controla la activaci�n del panel de Documentos
     * @param disclosureEvent
     */
    public void panelDocumentosDisclosureListener(DisclosureEvent disclosureEvent)
    {
        long startTime = System.currentTimeMillis();
        logger.warning("Inicia tiempo:" + startTime);
        
        logger.warning("Documents Disclosure");
        panelDocumentosActivated = true;
        
        long endTime = System.currentTimeMillis();
        logger.warning("Termina tiempo:" + endTime);
        logger.warning("Tiempo diferencia comentarios "
        + (endTime - startTime)/1000 + " segundos");
    }

    /**
     * M�todo que controla la activaci�n del panel de Comentarios
     * @param disclosureEvent
     */
    public void panelComentariosDisclosureListener(DisclosureEvent disclosureEvent)
    {
        long startTime = System.currentTimeMillis();
        logger.warning("Inicia tiempo:" + startTime);
        
        logger.warning("Comments Disclosure");
        panelComentariosActivated = Boolean.TRUE;
        
        long endTime = System.currentTimeMillis();
        logger.warning("Termina tiempo:" + endTime);
        logger.warning("Tiempo diferencia comentarios "
        + (endTime - startTime)/1000 + " segundos");
    }

    /**
     * M�todo que controla la activaci�n del panel de Detalle de la Operaci�n
     * @param disclosureEvent
     */
    public void panelDetalleOperacionDisclosureListener(DisclosureEvent disclosureEvent)
    {
        long startTime = System.currentTimeMillis();
        logger.warning("Inicia tiempo:" + startTime);
        
        logger.warning("Detail Disclosure");
        panelDetalleOperacionActivated = Boolean.TRUE;
        
        long endTime = System.currentTimeMillis();
        logger.warning("Termina tiempo:" + endTime);
        logger.warning("Tiempo diferencia comentarios "
        + (endTime - startTime)/1000 + " segundos");
    }
    
    /**
     * M�todo que controla la activaci�n del panel de Detalle de la Operaci�n del Gestor de operaciones
     * @param disclosureEvent
     */
    public void panelDetalleOperacionGestorDisclosureListener(DisclosureEvent disclosureEvent)
    {
        long startTime = System.currentTimeMillis();
        logger.warning("Inicia tiempo:" + startTime);
        
        logger.warning("Detail Disclosure");
        panelDetalleOperacionActivated = !panelDetalleOperacionActivated;
        
        long endTime = System.currentTimeMillis();
        logger.warning("Termina tiempo:" + endTime);
        logger.warning("Tiempo diferencia comentarios "
        + (endTime - startTime)/1000 + " segundos");
    }

    /**
     * M�todo que controla la activaci�n del panel de Datos de la tarea
     * @param disclosureEvent
     */
    public void panelDatosTareaDisclosureListener(DisclosureEvent disclosureEvent)
    {
        long startTime = System.currentTimeMillis();
        logger.warning("Inicia tiempo:" + startTime);
        
        logger.warning("Task data Disclosure");
        panelDetalleOperacionActivated = Boolean.TRUE;
        
        long endTime = System.currentTimeMillis();
        logger.warning("Termina tiempo:" + endTime);
        logger.warning("Tiempo diferencia comentarios "
        + (endTime - startTime)/1000 + " segundos");
    }
    
    /**
     * M�todo que controla la activaci�n del panel de Matriz TCC
     * @param disclosureEvent
     */
    public void panelMatrizTccDisclosureListener(DisclosureEvent disclosureEvent)
    {
        long startTime = System.currentTimeMillis();
        logger.warning("Inicia tiempo:" + startTime);
        
        logger.warning("TCC data Disclosure");
        panelMatrizTccActivated = Boolean.TRUE;
        
        long endTime = System.currentTimeMillis();
        logger.warning("Termina tiempo:" + endTime);
        logger.warning("Tiempo diferencia comentarios "
        + (endTime - startTime)/1000 + " segundos");
    }
    
    /**
     * M�todo que controla la activaci�n del panel de Adquisiciones de la Operaci�n del Gestor de operaciones
     * @param disclosureEvent
     */
    public void panelAdquisicionesDisclosureListener(DisclosureEvent disclosureEvent)
    {
        long startTime = System.currentTimeMillis();
        logger.warning("Inicia tiempo:" + startTime);
        
        logger.warning("Adquisiciones Disclosure");
        panelAdquisicionesActivated = !panelAdquisicionesActivated;
        
        long endTime = System.currentTimeMillis();
        logger.warning("Termina tiempo:" + endTime);
        logger.warning("Tiempo diferencia comentarios "
        + (endTime - startTime)/1000 + " segundos");
    }

    /**
     * @Feature20
     * @Método que controla la activación del panel de Formulario de Derivados Implicitos
     * @param disclosureEvent
     */
    public void panelDerivadosImplicitosDisclosureListener(DisclosureEvent disclosureEvent)
    {
        long startTime = System.currentTimeMillis();
        logger.warning("Inicia tiempo:" + startTime);
        
        logger.warning("Formulario de Derivados Implicitos Disclosure");
        panelDerivadosImplicitosActivated = true;
        
        long endTime = System.currentTimeMillis();
        logger.warning("Termina tiempo:" + endTime);
        logger.warning("Tiempo diferencia Formulario de Derivados Implicitos "
        + (endTime - startTime)/1000 + " segundos");
    }

    public void setPanelDocumentosActivated(Boolean panelDocumentosActivated) {
        this.panelDocumentosActivated = panelDocumentosActivated;
    }

    public void setPanelComentariosActivated(Boolean panelComentariosActivated) {
        this.panelComentariosActivated = panelComentariosActivated;
    }

    public void setPanelDetalleOperacionActivated(Boolean panelDetalleOperacionActivated) {
        this.panelDetalleOperacionActivated = panelDetalleOperacionActivated;
    }

    public void setPanelDatosTareaActivated(Boolean panelDatosTareaActivated) {
        this.panelDatosTareaActivated = panelDatosTareaActivated;
    }

    public void setPanelMatrizTccActivated(Boolean panelMatrizTccActivated) {
        this.panelMatrizTccActivated = panelMatrizTccActivated;
    }

    public Boolean getPanelAdquisicionesActivated() {
        return panelAdquisicionesActivated;
    }

    public void setPanelAdquisicionesActivated(Boolean panelAdquisicionesActivated) {
        this.panelAdquisicionesActivated = panelAdquisicionesActivated;
    }

    //Feature20
    public void setPanelDerivadosImplicitosActivated(Boolean panelDerivadosImplicitosActivated) {
        this.panelDerivadosImplicitosActivated = panelDerivadosImplicitosActivated;
    }

    public Boolean getPanelDerivadosImplicitosActivated() {
        return panelDerivadosImplicitosActivated;
    }
}
