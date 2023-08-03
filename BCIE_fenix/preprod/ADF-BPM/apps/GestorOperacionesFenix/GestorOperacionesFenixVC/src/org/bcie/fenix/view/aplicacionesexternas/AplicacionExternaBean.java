package org.bcie.fenix.view.aplicacionesexternas;

import java.io.Serializable;

import java.util.ArrayList;

import oracle.adf.share.logging.ADFLogger;

import oracle.binding.OperationBinding;

import oracle.jbo.Row;
import oracle.jbo.domain.DBSequence;

import org.apache.myfaces.trinidad.model.ModelUtils;

import org.bcie.fenix.common.FenixConstants;
import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;
import org.bcie.fenix.view.gestoroperaciones.GestorOperacionesBean;

public class AplicacionExternaBean implements Serializable {
    @SuppressWarnings("compatibility")
    private static final long serialVersionUID = 1L;
    private static final String NOMBRE_SERVLET = "aplicacionexterna";
    private static final String PARAMETRO_ID_APP_EXTERNA = ("?" + FenixConstants.SERVLET_APP_EXTERNA_ID + "=");
    private static final String PARAMETRO_CODIGO_OPERACION =
        ("&" + FenixConstants.SERVLET_APP_EXTERNA_CODIGO_OPERACION + "=");
    private static final String PARAMETRO_CODIGO_CLIENTE =
        ("&" + FenixConstants.SERVLET_APP_EXTERNA_CODIGO_CLIENTE + "=");
    private static final String DESTINO_BLANK = "_blank";
    private static final String DESTINO_PARENT = "_parent";
    private static final Integer NO_REQUIERE = 0;
    protected static ADFLogger logger = null;

    public AplicacionExternaBean() {
        super();

        if (logger == null) {
            logger = ADFLogger.createADFLogger(AplicacionExternaBean.class);
        }
    }

    @SuppressWarnings({ "unchecked", "oracle.jdeveloper.java.nested-assignment" })
    public void prepararUrlAppsExternas() {
        logger.log(ADFLogger.WARNING, "Entrando en prepararUrlsAppsExternas.");
        GestorOperacionesBean gestorOperacionesBean =
            (GestorOperacionesBean) JSFUtils.resolveExpression("#{pageFlowScope.GestorOperacionesManagedBean}");
        ArrayList listaAplicaciones = new ArrayList();
        Row[] rows = obtenerAppExternasActivas();

        for (Row row : rows) {
            DBSequence idSeq = (DBSequence) row.getAttribute("Id");
            String nombre = (String) row.getAttribute("DescripcionCorta");
            Integer requiereNuevaVentana = (Integer) row.getAttribute("RequiereNuevaVentana");
            String destino = DESTINO_BLANK;
            
            if (null != requiereNuevaVentana && requiereNuevaVentana.compareTo(NO_REQUIERE) == 0) {
                destino = DESTINO_PARENT;
            }

            // URL del servlet de AplicacionesExternas
            StringBuilder sb = new StringBuilder();
            sb.append(NOMBRE_SERVLET);
            sb.append(PARAMETRO_ID_APP_EXTERNA).append("" + idSeq.getValue());
            sb.append(PARAMETRO_CODIGO_OPERACION).append("" + gestorOperacionesBean.getIdOperacion());
            sb.append(PARAMETRO_CODIGO_CLIENTE).append(gestorOperacionesBean.getIdCliente());
            String url = sb.toString();

            listaAplicaciones.add(new AplicacionExterna(idSeq.getValue(), nombre, url, destino));

            // Usar log solo en dev
            //logger.log(ADFLogger.WARNING, "URL app externa: " + url);
        }

        gestorOperacionesBean.setAplicacionesCM(ModelUtils.toCollectionModel(listaAplicaciones));
        
    }

    @SuppressWarnings("unchecked")
    private Row[] obtenerAppExternasActivas() {
        logger.log(ADFLogger.WARNING, "Entrando en obtenerAppExternasActivas.");
        Row[] rows = new Row[0];

        // Filtramos las aplicaciones activas
        OperationBinding operationBinding = ADFUtils.findOperation("obtenerAppExternasActivas");
        operationBinding.execute();

        if (!operationBinding.getErrors().isEmpty()) {
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        } else {
            rows = (Row[]) operationBinding.getResult();
        }
        return rows;
    }
}
