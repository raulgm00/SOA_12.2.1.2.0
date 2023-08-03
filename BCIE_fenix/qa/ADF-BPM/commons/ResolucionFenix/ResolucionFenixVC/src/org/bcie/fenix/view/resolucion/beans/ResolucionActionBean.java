package org.bcie.fenix.view.resolucion.beans;

import java.text.MessageFormat;

import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.nav.RichButton;

import oracle.binding.OperationBinding;

import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;

public class ResolucionActionBean {
    private RichButton verDocumentoButtonUIC;
        
        private static ADFLogger logger = null;
        
        public ResolucionActionBean() {
            super();
            if(null == logger){
                logger = ADFLogger.createADFLogger(this.getClass());
            }
        }
        
        
        /**
         * M&eacute;todo para recuperar la URL de la resoluci&oacute;n de aprobaci&oacute;n
         * @return URL de resoluci&oacute;n
         */
        @SuppressWarnings("unchecked")
        public String getUrlLotusNoteResolucion() {
            logger.warning("Entra en getUrlLotusNoteResolucion.");
            ResolucionBean resolucionBean = (ResolucionBean) JSFUtils.resolveExpression("#{pageFlowScope.resolucionBean}");
            String numeroResolucion = null;
            String urlLotusNoteResolucion = null;
            
            numeroResolucion = resolucionBean.getNumeroResolucion();
            logger.warning("Numero de resolucion obtenida : " + numeroResolucion);
            
            if(numeroResolucion != null){
                numeroResolucion = formatResolucionToUrl(numeroResolucion);
                
                OperationBinding urlBinding = ADFUtils.findOperation("getValorWsdl");
                Object urlObject = urlBinding.execute();
                String urlResolucion = (String)urlObject;
                logger.warning("urlResolucion : " + urlResolucion);
                urlLotusNoteResolucion = MessageFormat.format(urlResolucion, numeroResolucion);
                
                verDocumentoButtonUIC.setDestination(urlLotusNoteResolucion);
            }
            logger.warning("Url Lotus : " + urlLotusNoteResolucion);
            return urlLotusNoteResolucion;
        }
        
        private String formatResolucionToUrl(String resolucion){
            if(resolucion != null)
                resolucion = resolucion.replace("/", "-");
            return resolucion;
        }

        public void setVerDocumentoButtonUIC(RichButton verDocumentoButtonUIC) {
            this.verDocumentoButtonUIC = verDocumentoButtonUIC;
        }

        public RichButton getVerDocumentoButtonUIC() {
            return verDocumentoButtonUIC;
        }
}
