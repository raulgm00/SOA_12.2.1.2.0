package org.bcie.fenix.view.consolidadocargopenalidadprepago;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import java.io.UnsupportedEncodingException;

import java.net.URLEncoder;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.faces.context.FacesContext;

import javax.faces.event.ActionEvent;

import javax.servlet.http.HttpServletResponse;

import oracle.adf.model.BindingContext;
import oracle.adf.share.logging.ADFLogger;

import oracle.binding.BindingContainer;

import oracle.binding.OperationBinding;

import org.apache.myfaces.trinidad.context.Agent;
import org.apache.myfaces.trinidad.context.RequestContext;

import org.bcie.fenix.common.utils.JSFUtils;

public class ConsolidadoCargoPenalidadPrepagoActionBean {

    private static ADFLogger logger = null;
    SimpleDateFormat formato = new SimpleDateFormat("ddMMyyyy", new Locale("es_ES"));
    private final String REPORTE_AVISO_FILE_NAME_ZIP = "InformesPrepago_" + formato.format(new Date()) + ".zip";

    public ConsolidadoCargoPenalidadPrepagoActionBean() {
        super();
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }

    public void reporteAvisoFileDownloadActionListener(FacesContext facesContext, OutputStream outputStream) {
        logger.log(ADFLogger.WARNING, "INTO reporteAvisoFileDownloadActionListener.");
        Long pIdPrepago = null;
        Map<String, Object> reporteAviso = new HashMap<String, Object>();
        byte[] aviso = new byte[256];
        byte[] reporte = new byte[256];
        String nombreArchivoUno = null;
        String nombreArchivoDos = null;
        
        List<byte[]> files = new ArrayList<byte[]>();
        List<String> nombres = new ArrayList<String>();
        
        ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(outputStream));
        logger.log(ADFLogger.WARNING, "Tamaño de la lista." + files.size());

        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();
        try {
            if (null != JSFUtils.resolveExpression("#{pageFlowScope.pIdPrepago}")) {
                pIdPrepago = Long.valueOf(JSFUtils.resolveExpression("#{pageFlowScope.pIdPrepago}").toString());
                logger.log(ADFLogger.WARNING, "Valor de Id Linea de credito.." + pIdPrepago);
            }

            applyIE11Fix(REPORTE_AVISO_FILE_NAME_ZIP);
            OperationBinding operationBinding = bindings.getOperationBinding("consultarGenerarAviso");
            operationBinding.getParamsMap().put("idPrepago", pIdPrepago);
            operationBinding.execute();
            if (null != operationBinding.getResult()) {
                reporteAviso = (Map) operationBinding.getResult();
                logger.log(ADFLogger.WARNING, "Numero de registros en el Map :" + reporteAviso.size());
                aviso = (byte[])reporteAviso.get("aviso");
                reporte = (byte[])reporteAviso.get("reporte");
                nombreArchivoUno = (String)reporteAviso.get("nombreArchivoUno");
                nombreArchivoDos = (String)reporteAviso.get("nombreArchivoDos");
            } else {
                logger.log(ADFLogger.WARNING, "El mapa viene vacio.");
            }

            files.add(aviso);
            files.add(reporte);
            nombres.add(nombreArchivoUno);
            nombres.add(nombreArchivoDos);


            if (aviso != null && aviso.length > 0) {
                logger.log(ADFLogger.WARNING, "Entra a escribir el reporte");
                int nombrePdf = 0;
                for (byte[] file : files) {
                    zos.putNextEntry(new ZipEntry(nombres.get(nombrePdf) + ".pdf"));
                    zos.write(file);
                    nombrePdf++;
                }
            }

        } catch (IOException ioex) {
            ioex.printStackTrace();
        } finally {
            flushAndCloseOutputStream(zos);
        }

        facesContext.responseComplete();
    }

    private void flushAndCloseOutputStream(OutputStream output) {
        flushOutputStream(output);
        closeOutputStream(output);
    }

    private void flushOutputStream(OutputStream output) {
        try {
            if (output != null)
                output.flush();
        } catch (IOException ioex) {
            ioex.printStackTrace();
        }
    }

    private void closeOutputStream(OutputStream output) {
        try {
            if (output != null)
                output.close();
        } catch (IOException ioex) {
            ioex.printStackTrace();
        }
    }

    private void applyIE11Fix(String docName) {

        RequestContext requestCtx = RequestContext.getCurrentInstance();
        Agent agent = requestCtx.getAgent();
        String version = agent.getAgentVersion();
        String browser = agent.getAgentName();

        if (browser != null && "ie".equals(browser) && version != null && version.startsWith("11")) {

            HttpServletResponse resp =
                (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            String finalName = docName;

            try {
                finalName = URLEncoder.encode(docName, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            resp.setHeader("content-disposition", "attachment; filename=\"" + finalName + "\"");
            resp.setContentType("application/octet-stream");
        }
    }

    public String getREPORTE_AVISO_FILE_NAME_ZIP() {
        return REPORTE_AVISO_FILE_NAME_ZIP;
    }
}
