package org.bcie.fenix.view.gestordesembolsos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.text.DateFormat;

import java.io.InputStreamReader;
import java.io.OutputStream;

import java.io.StringWriter;

import java.math.BigDecimal;

import java.sql.SQLException;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import java.util.Map;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import java.util.concurrent.TimeUnit;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import javax.faces.validator.ValidatorException;

import javax.servlet.http.HttpServletResponse;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.XMLGregorianCalendar;

import javax.xml.namespace.QName;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.logging.ADFLogger;

import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputComboboxListOfValues;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectItem;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelHeader;
import oracle.adf.view.rich.component.rich.layout.RichPanelLabelAndMessage;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.util.ResetUtils;

import oracle.binding.AttributeBinding;

import org.bcie.fenix.common.FenixConstants;
import org.bcie.fenix.common.utils.JSFUtils;
import org.bcie.fenix.common.view.beans.FenixActionBean;
import org.bcie.fenix.common.model.vo.FT05VOImpl;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.igf.ids.util.Base64;

import oracle.javatools.resourcebundle.BundleFactory;

import oracle.jbo.Key;
import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;
import oracle.jbo.ViewObject;
import oracle.jbo.domain.BlobDomain;
import oracle.jbo.domain.Timestamp;
import oracle.jbo.uicli.binding.JUCtrlHierBinding;
import oracle.jbo.uicli.binding.JUCtrlListBinding;

import org.apache.myfaces.trinidad.model.CollectionModel;
import org.apache.myfaces.trinidad.model.UploadedFile;

import org.apache.myfaces.trinidad.render.ExtendedRenderKitService;
import org.apache.myfaces.trinidad.util.Service;
import org.apache.poi.util.IOUtils;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.bcie.condicionmo.ConsultarCumplimientoCondicionResponseType;
import org.bcie.condicionservice.Condicion12BndQSService;
import org.bcie.desembolso.DesembolsoPT;
import org.bcie.desembolso.DesembolsoPTSOAP12BindingQSService;
import org.bcie.desembolsomo.GeneraReportePlanAmortizacionRequestType;
import org.bcie.desembolsomo.GeneraReportePlanAmortizacionResponseType;
import org.bcie.fenix.common.FenixConstants;
import org.bcie.fenix.common.model.utils.IWsdlLocation;
import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;
import org.bcie.fenix.common.view.beans.FenixActionBean;

public class CondicionesFinancierasActionBean extends FenixActionBean {

    private RichPanelGroupLayout contenedorSecPrincipal;
    private RichPanelGroupLayout contenedorSecTasa;
    private RichPanelLabelAndMessage contenedorNumCuotas;
    private RichPanelLabelAndMessage contenedorNumCuotasInteres;
    private RichPanelLabelAndMessage contenedorNumRevisionTaza;
    private RichPanelLabelAndMessage contenedorNumRevisionTazaI;
    private RichPanelLabelAndMessage contenedorNumRevisionesSpread;
    private RichPanelGroupLayout contenedorSecInteres;
    private RichPanelGroupLayout contenedorSpreadVariable;
    private RichPanelFormLayout contenedorDG1;
    private RichPanelGroupLayout contenedorMora;
    private RichPanelGroupLayout contenedorAllSpreadVariable;
    private RichPanelGroupLayout contenedorDescargarCapital;
    private RichPanelFormLayout pflCheckBoxUIC;
    private RichPanelGroupLayout contenedorCodigoDescripcionSpread;
    private RichInputComboboxListOfValues bindingDescripcionSpreadInput;
    private RichPanelGroupLayout contenedorSecTasa2;
    private RichInputText limiteTasaMinimaUIC;
    private RichInputText limiteTasaMaximaUIC;
    private RichPanelHeader contenedorAllSpreadVariableNew;
    private RichPanelHeader contenedorAllInteresNew;
    private RichPanelHeader contenedorAllCapitalNew;
    private RichOutputText initValidaciones;

    public CondicionesFinancierasActionBean() {
        super();
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }

    }

    public static final String BUNDLE = "view.GestorDesembolsosFenixVCBundle";

    public static ADFLogger logger = null;
    private RichTable calendarioComplejoTable;
    private RichTable detalleCalendarioTable;
    private UploadedFile _file;
    private Long idCondicionFinanciera = null;
    private String extencionExcel = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    private Long idCalendarioComplejo = null;
    private String path = null;
    private Boolean esErrorValidacion = Boolean.FALSE;
    final Integer tcaEstadoAlVencimiento = 4;

    public void parseFile(java.io.InputStream file) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(file));
        String strLine = "";
        StringTokenizer st = null;
        int lineNumber = 0, tokenNumber = 0;
        Row rw = null;

        CollectionModel _tableModel = (CollectionModel) detalleCalendarioTable.getValue();
        JUCtrlHierBinding _adfTableBinding = (JUCtrlHierBinding) _tableModel.getWrappedData();
        DCIteratorBinding it = _adfTableBinding.getDCIteratorBinding();

        try {
            while ((strLine = reader.readLine()) != null) {
                lineNumber++;
                if (lineNumber > 1) {
                    rw = it.getNavigatableRowIterator().createRow();
                    rw.setNewRowState(Row.STATUS_INITIALIZED);
                    it.getNavigatableRowIterator().insertRow(rw);
                }
                st = new StringTokenizer(strLine, ",");
                while (st.hasMoreTokens()) {
                    tokenNumber++;

                    String theToken = st.nextToken();
                    System.out.println("Line # " + lineNumber + ", Token # " + tokenNumber + ", Token : " + theToken);
                    if (lineNumber > 1) {
                        switch (tokenNumber) {
                        case 1:
                            rw.setAttribute("Fechapago", theToken);
                        case 3:
                            rw.setAttribute("Montopago", theToken);
                        }
                    }
                }
                tokenNumber = 0;
            }
        } catch (IOException e) {
            FacesContext fctx = FacesContext.getCurrentInstance();
        } catch (Exception e) {
            FacesContext fctx = FacesContext.getCurrentInstance();
            fctx.addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR AL LEER EL ARCHIVO.", e.getMessage()));
        }
    }

    public List onSuggestProductoFlex2(String string) {
        logger.warning(" |*| Inside onSuggestProductoFlex" + string);
        ArrayList<SelectItem> resultItems = new ArrayList<SelectItem>();
        ;
        SelectItem element = null;
        //BindingContainer bindings2 = ADFUtils.getBindingContainer();
        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();
        JUCtrlListBinding list = (JUCtrlListBinding) bindings.get("Descripcion1");

        Row[] values = list.getAllRowsInRange();
        if (null != values) {
            for (Row obj : values) {
                String value = (String) obj.getAttribute("Descripcion");
                if (null != string && null != value) {
                    if (value.toUpperCase().contains(string.toUpperCase())) {
                        element = new SelectItem(value);
                        resultItems.add(element);
                    }
                }
            }
        } else {
            element = new SelectItem("");
            resultItems.add(element);
        }
        return resultItems;
    }


    public List onSuggestProductoFlex(String string) {
        logger.warning("*Inf, Into method onSuggestProductoFlex");
        ArrayList<SelectItem> resultItems = new ArrayList<SelectItem>();
        ;
        SelectItem element = null;

        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();
        JUCtrlListBinding list = (JUCtrlListBinding) bindings.get("Descripcion1");

        RowSetIterator iter = list.getListRowSetIterator();
        iter.reset();
        Row row = null;
        int contador = 0;

        logger.warning("*Inf, Num de cuentas recuperado para la operacion : " + list.getAllRowsInRange().length);
        if (null != iter) {

            for (int i = 0; i <= list.getAllRowsInRange().length; i++) {
                logger.warning("*Inf, Iterando el row " + i + "...");
                row = iter.getRowAtRangeIndex(i);
                if (null != row) {
                    String value = (String) row.getAttribute("Descripcion");
                    if (null != string && null != value) {
                        if (value.toUpperCase().contains(string.toUpperCase())) {
                            contador = contador + 1;
                            element = new SelectItem(value);
                            resultItems.add(element);
                        }
                    } else {
                        logger.warning("*Inf, Important! String " + string + " valor en lista " + value);
                    }
                }
            }

        } else {
            logger.warning("*Inf, Important! Iterador NULL");
            element = new SelectItem("");
            resultItems.add(element);
        }
        iter.closeRowSetIterator();
        logger.warning("*Inf, Numero de coincidencias encontradas: " + contador);
        return resultItems;
    }


    public void guardarCalendarioInteres(ValueChangeEvent value) {
        logger.log(ADFLogger.WARNING, "Inicia metodo guardarCalendarioInteres");

        Long idCondicion = null;
        if (null != JSFUtils.resolveExpression("#{pageFlowScope.idCondicionFinanciera}")) {
            idCondicion = new Long(JSFUtils.resolveExpression("#{pageFlowScope.idCondicionFinanciera}").toString());
        } else {
            logger.warning("IdContrato es NULL");
        }

        logger.warning("IdCondicionFinanciera para guardar documento: " + idCondicion);
        String tipoPago = "INTERES";
        InputStream inputStream = null;
        InputStream in = null;
        FacesContext fc = FacesContext.getCurrentInstance();
        UploadedFile file = (UploadedFile) value.getNewValue();
        BlobDomain fileDataValidar = null;
        BlobDomain fileDataGuardar = null;
        Boolean esValido = null;
        String nombreArchivo = null;
        String extencionArchivo = null;

        if (idCondicion != null) {
            if (file != null && file.getLength() > 0) {
                try {
                    UploadedFile fileVal = (UploadedFile) value.getNewValue();
                    logger.log(ADFLogger.WARNING, "Validamos que es archivo EXCEL");
                    if (fileVal.getContentType().equals(extencionExcel)) {

                        nombreArchivo = fileVal.getFilename();
                        extencionArchivo = fileVal.getContentType();

                        inputStream = fileVal.getInputStream();
                        in = fileVal.getInputStream();
                        fileDataValidar = newCalendarioBlodDomain(inputStream);
                        fileDataGuardar = newCalendarioBlodDomain(in);

                        esValido = validarArchivo(fileDataValidar, tipoPago);

                        logger.warning("EsValido: " + esValido);
                        if (null != esValido) {
                            if (esValido) {
                                if (value.getNewValue() != null) {
                                    Boolean esError = Boolean.FALSE;
                                    OperationBinding ob = executeOperation("setCalendarioComplejo");
                                    ob.getParamsMap().put("idCondicionFinanciera", idCondicion);
                                    ob.getParamsMap().put("filename", fileVal.getFilename());
                                    ob.getParamsMap().put("mimeType", fileVal.getContentType());
                                    ob.getParamsMap().put("tipoPago", tipoPago);
                                    ob.getParamsMap().put("content", fileDataGuardar);
                                    esError = (Boolean) ob.execute();
                                    if (null == esError || esError) {
                                        logger.warning("El archivo no se guardó correctamente.");
                                        JSFUtils.addFacesErrorMessage(getStringFromBundle("CONDICION_FINANCIERA_ERROR_ADJUNTAR"));
                                        ResetUtils.reset(value.getComponent());
                                    } else {
                                        logger.warning("Archivo guardado exitosamente.");
                                        //nombreArchivo = nombreArchivo.concat("." + extencionArchivo);
                                        CondicionesFinancierasBean condicionFinanciera =
                                            (CondicionesFinancierasBean) JSFUtils.resolveExpression("#{pageFlowScope.CondicionFinancieraBean}");

                                        logger.warning("Nombre de archivo: " + nombreArchivo);
                                        if (null != condicionFinanciera) {
                                            condicionFinanciera.setExisteArchivoInteres(Boolean.TRUE);
                                            condicionFinanciera.setNombreArchivoExcelInteres(nombreArchivo);
                                        } else {
                                            logger.warning("Instancia de Bean es NULL:");
                                        }
                                        ResetUtils.reset(value.getComponent());
                                    }
                                }
                            } else {
                                logger.warning("El archivo NO cumple con el formato.");
                                JSFUtils.addFacesErrorMessage(getStringFromBundle("CONDICION_FINANCIERA_MSG_15"));
                                ResetUtils.reset(value.getComponent());
                            }
                        } else {
                            logger.warning("Ocurrio un error al validar el archivo.");
                            JSFUtils.addFacesErrorMessage(getStringFromBundle("CONDICION_FINANCIERA_ERROR_CALENDARIO"));
                            ResetUtils.reset(value.getComponent());
                        }
                    } else {
                        JSFUtils.addFacesErrorMessage(getStringFromBundle("CONDICION_FINANCIERA_MSG_15"));
                        ResetUtils.reset(value.getComponent());
                    }
                } catch (Exception e) {
                    logger.warning("Ocurrio un error al adjuntar el archivo.", e);
                    JSFUtils.addFacesErrorMessage(getStringFromBundle("CONDICION_FINANCIERA_ERROR_CALENDARIO"));
                    ResetUtils.reset(value.getComponent());
                }
            } else {
                logger.log(ADFLogger.WARNING, "ERROR NO HAY DOCUMENTO QUE GUARDAR");
                JSFUtils.addFacesErrorMessage(getStringFromBundle("CONDICION_FINANCIERA_ERROR_CALENDARIO"));
                ResetUtils.reset(value.getComponent());
            }
        } else {
            logger.log(ADFLogger.WARNING, "ERROR ID_CONDICION_SEQUENCE NULL");
            JSFUtils.addFacesErrorMessage(getStringFromBundle("CONDICION_FINANCIERA_ERROR_CALENDARIO"));
            ResetUtils.reset(value.getComponent());
        }

        logger.log(ADFLogger.WARNING, "Termina metodo guardarCalendarioInteres");
    }

    public void guardarCalendarioCapital(ValueChangeEvent value) {
        logger.log(ADFLogger.WARNING, "Inicia metodo guardarCalendarioInteres.");

        Long idCondicion = null;
        if (null != JSFUtils.resolveExpression("#{pageFlowScope.idCondicionFinanciera}")) {
            idCondicion = new Long(JSFUtils.resolveExpression("#{pageFlowScope.idCondicionFinanciera}").toString());
        } else {
            logger.warning("IdContrato es NULL");
        }

        logger.warning("IdCondicionFinanciera para guardar documento: " + idCondicion);
        String tipoPago = "CAPITAL";
        InputStream inputStream = null;
        InputStream in = null;
        FacesContext fc = FacesContext.getCurrentInstance();
        UploadedFile file = (UploadedFile) value.getNewValue();
        BlobDomain fileDataValidar = null;
        BlobDomain fileDataGuardar = null;
        Boolean esValido = null;
        String nombreArchivo = null;
        String extencionArchivo = null;

        if (idCondicion != null) {
            if (file != null && file.getLength() > 0) {
                try {
                    UploadedFile fileVal = (UploadedFile) value.getNewValue();
                    logger.log(ADFLogger.WARNING, "Validamos que es archivo EXCEL");
                    if (fileVal.getContentType().equals(extencionExcel)) {

                        nombreArchivo = fileVal.getFilename();
                        extencionArchivo = fileVal.getContentType();

                        inputStream = fileVal.getInputStream();
                        in = fileVal.getInputStream();
                        fileDataValidar = newCalendarioBlodDomain(inputStream);
                        fileDataGuardar = newCalendarioBlodDomain(in);

                        esValido = validarArchivo(fileDataValidar, tipoPago);

                        logger.warning("EsValido: " + esValido);
                        if (null != esValido) {
                            if (esValido) {
                                if (value.getNewValue() != null) {
                                    Boolean esError = Boolean.FALSE;
                                    OperationBinding ob = executeOperation("setCalendarioComplejo");
                                    ob.getParamsMap().put("idCondicionFinanciera", idCondicion);
                                    ob.getParamsMap().put("filename", fileVal.getFilename());
                                    ob.getParamsMap().put("mimeType", fileVal.getContentType());
                                    ob.getParamsMap().put("tipoPago", tipoPago);
                                    ob.getParamsMap().put("content", fileDataGuardar);
                                    esError = (Boolean) ob.execute();
                                    if (null == esError || esError) {
                                        logger.warning("El archivo no se guardó correctamente.");
                                        JSFUtils.addFacesErrorMessage(getStringFromBundle("CONDICION_FINANCIERA_ERROR_ADJUNTAR"));
                                        ResetUtils.reset(value.getComponent());
                                    } else {
                                        logger.warning("Archivo guardado exitosamente.");
                                        //nombreArchivo = nombreArchivo.concat("." + extencionArchivo);
                                        CondicionesFinancierasBean condicionFinanciera =
                                            (CondicionesFinancierasBean) JSFUtils.resolveExpression("#{pageFlowScope.CondicionFinancieraBean}");

                                        logger.warning("Nombre de archivo: " + nombreArchivo);
                                        if (null != condicionFinanciera) {
                                            condicionFinanciera.setExisteArchivoCapital(Boolean.TRUE);
                                            condicionFinanciera.setNombreArchivoExcelCapital(nombreArchivo);
                                            logger.warning("Nombre de archivo recuperado: " +
                                                           condicionFinanciera.getNombreArchivoExcelCapital());
                                        } else {
                                            logger.warning("Instancia de Bean es NULL:");
                                        }
                                        ResetUtils.reset(value.getComponent());
                                    }
                                }
                            } else {
                                logger.warning("El archivo NO cumple con el formato.");
                                JSFUtils.addFacesErrorMessage(getStringFromBundle("CONDICION_FINANCIERA_MSG_15"));
                                ResetUtils.reset(value.getComponent());
                            }
                        } else {
                            logger.warning("Ocurrio un error al validar el archivo.");
                            JSFUtils.addFacesErrorMessage(getStringFromBundle("CONDICION_FINANCIERA_ERROR_CALENDARIO"));
                            ResetUtils.reset(value.getComponent());
                        }
                    } else {
                        JSFUtils.addFacesErrorMessage(getStringFromBundle("CONDICION_FINANCIERA_MSG_15"));
                        ResetUtils.reset(value.getComponent());
                    }
                } catch (Exception e) {
                    logger.warning("Ocurrio un error al adjuntar el archivo.", e);
                    JSFUtils.addFacesErrorMessage(getStringFromBundle("CONDICION_FINANCIERA_ERROR_CALENDARIO"));
                    ResetUtils.reset(value.getComponent());
                }
            } else {
                logger.log(ADFLogger.WARNING, "ERROR NO HAY DOCUMENTO QUE GUARDAR");
                JSFUtils.addFacesErrorMessage(getStringFromBundle("CONDICION_FINANCIERA_ERROR_CALENDARIO"));
                ResetUtils.reset(value.getComponent());
            }
        } else {
            logger.log(ADFLogger.WARNING, "ERROR ID_CONDICION_SEQUENCE NULL");
            JSFUtils.addFacesErrorMessage(getStringFromBundle("CONDICION_FINANCIERA_ERROR_CALENDARIO"));
            ResetUtils.reset(value.getComponent());
        }

        logger.warning("Termina metodo guardarCalendarioCapital");
    }

    public Boolean validarArchivo(BlobDomain fileData, String tipoPago) {
        logger.warning("Inicia metodo validarArchivo");
        Boolean esValido = Boolean.FALSE;

        OperationBinding ob = executeOperation("validarArchivoExcel");
        ob.getParamsMap().put("fileData", fileData);
        ob.getParamsMap().put("tipoPago", tipoPago);
        esValido = (Boolean) ob.execute();

        logger.warning("EsValido: " + esValido);
        logger.warning("Termina metodo validarArchivo");
        return esValido;
    }

    public void registrarCondicionesFinancieras(ActionEvent actionEvent) {
        logger.log(ADFLogger.WARNING, " --- Inside registrarCondicionesFinancieras ---");
        CondicionesFinancierasBean condicionFinanciera =
            (CondicionesFinancierasBean) JSFUtils.resolveExpression("#{pageFlowScope.CondicionFinancieraBean}");
        idCondicionFinanciera = condicionFinanciera.getIdCondicionFinanciera();
        condicionFinanciera.validarRegistroDesembolso();
        //TODO aqui se implementa la validacion de las revlas de negocio junto con la llamada a los servicios
        if (1 == 1) {
            if (idCondicionFinanciera != null) {
                logger.log(ADFLogger.WARNING, "Validamos que es archivo EXCEL");
                if (idCondicionFinanciera != null) {
                    OperationBinding ob = executeOperation("setCondicionesFinancieras");
                    ob.getParamsMap().put("idCondicionFinanciera", idCondicionFinanciera);
                    ob.execute();
                }
            }
        }
    }

    public void downloadDocumentoInteres(FacesContext facesContext, OutputStream outputStream) throws IOException {
        logger.log(ADFLogger.WARNING, "--- Inside downloadDocumentoCapital");
        //TODO getCalendarioDocumento(idCondicion, tipoPago)
        String path = "C://Doc//Calendario_amortizacion";
        File filed = new File(path.toString());
        FileInputStream fis;
        byte[] b;
        try {
            fis = new FileInputStream(filed);
            int n;
            while ((n = fis.available()) > 0) {
                b = new byte[n];
                int result = fis.read(b);
                outputStream.write(b, 0, b.length);
                if (result == -1)
                    break;
            }
        } catch (IOException e) {
            logger.warning("ERROR: ", e);
        }
        outputStream.flush();
    }

    public void downloadDocumentoCapital(FacesContext facesContext, OutputStream outputStream) throws IOException {
        logger.log(ADFLogger.WARNING, "--- Inside downloadDocumentoCapital");
        //TODO getCalendarioDocumento(idCondicion, tipoPago)
        String path = "C://Doc//Calendario_amortizacion";
        File filed = new File(path.toString());
        FileInputStream fis;
        byte[] b;
        try {
            fis = new FileInputStream(filed);
            int n;
            while ((n = fis.available()) > 0) {
                b = new byte[n];
                int result = fis.read(b);
                outputStream.write(b, 0, b.length);
                if (result == -1)
                    break;
            }
        } catch (IOException e) {
            logger.warning("ERROR: ", e);
        }
        outputStream.flush();
    }

    /************METODOS DE DESCARGA DE DOCUMENTOS***************/
    public void descargarDocumentoCalendarioComplejo(FacesContext facesContext, OutputStream outputStream) {
        logger.warning("Inicia metodo descargarDocumentoCapital");
        Long idCondicionFinancieraTF = null;
        String tipoInvocacion = null;
        String nombreArchivo = null;
        Row row = null;
        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();
        AttributeBinding attr = (AttributeBinding) bindings.getControlBinding("Content");
        BlobDomain blob = null;
        Map<String, Object> params = new HashMap<String, Object>();
        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();

        if (null != JSFUtils.resolveExpression("#{pageFlowScope.idCondicionFinanciera}")) {
            idCondicionFinancieraTF =
                new Long(JSFUtils.resolveExpression("#{pageFlowScope.idCondicionFinanciera}").toString());
        } else {
            logger.warning("IdContrato es NULL");
        }

        try {
            tipoInvocacion = JSFUtils.resolveExpression("#{pageFlowScope.tipoInvocacion}").toString();
            ;
        } catch (Exception e) {
            logger.warning("ERROR al recuperar el tipoInvocacion de TF.", e.getMessage());
        }

        logger.warning("idCondicionFinanciera: " + idCondicionFinancieraTF + ", tipoInvocacion: " + tipoInvocacion);
        params.put("idCondicionFinanciera", idCondicionFinancieraTF);
        params.put("tipoPago", tipoInvocacion);
        try {
            row = execute(params, "recuperarRegistroCalendarioComplejo");
        } catch (Exception e) {
            logger.warning("ERROR al ejecutar OPERBINDING recuperarRegistroCalendarioComplejo.", e);
        }

        if (null != row) {
            try {
                blob = (BlobDomain) row.getAttribute("Content");
            } catch (Exception e) {
                logger.warning("ERROR al reciperar el atributo COntent.", e.getMessage());
            }

            try {
                nombreArchivo = (String) row.getAttribute("Filename");
            } catch (Exception e) {
                logger.warning("ERROR al recuperar el nombre de archivo.", e.getMessage());
            }

            logger.warning("Nombre archivo recuperado de row: " + nombreArchivo);
            try {
                response.reset(); // Some JSF component library or some Filter might have set some headers in the buffer beforehand. We want to get rid of them, else it may collide.
                response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                response.setHeader("Content-Disposition", "attachment;filename=" + nombreArchivo);
            } catch (Exception e) {
                logger.warning("ERROR al ejecutar response.", e);
            }

            if (null != blob) {
                logger.warning("Descargando BLOBDOMAIN.");
                try { // copy the data from the blobDomain to the output stream
                    InputStream in = blob.getInputStream();

                    byte[] buf = new byte[1024];
                    int count;
                    while ((count = in.read(buf)) >= 0) {
                        outputStream.write(buf, 0, count);
                    }

                    in.close();
                    outputStream.flush();
                    outputStream.close();
                    blob.closeInputStream();
                    facesContext.responseComplete();
                } catch (IOException e) {
                    logger.warning("ERROR al descargar el documento.", e);
                }
            } else {
                logger.warning("El objeto blob es NULL.");
            }
        } else {
            logger.warning("No existe registro de CalendarioComplejo " + tipoInvocacion);
        }
        logger.warning("Termina metodo descargarDocumentoCapital");
    }

    public void getFormatoInteresPath() {
        logger.log(ADFLogger.WARNING, " --- Inside getFormatoInteresPath");
        String tipoPago = FenixConstants.PATH_CALENDARIO_COMPLEJO_INTERES;

        try {
            OperationBinding operationBinding = executeOperation("getValorWsdl");
            operationBinding.getParamsMap().put("llave", tipoPago);
            operationBinding.execute();
            if (operationBinding.getResult() != null) {
                setPath((String) operationBinding.getResult());
                logger.warning("PATH_DOCUMENTO_INTERES:" + path);
                if (!operationBinding.getErrors().isEmpty()) {
                    logger.log(ADFLogger.ERROR,
                               "Error al Retornar getFormatoInteresPath " + operationBinding.getErrors());
                }
            }
        } catch (Exception e) {
            logger.log(ADFLogger.WARNING, "Error en getFormatoInteresPath" + e.getClass() + ":" + e.getMessage());
        }
    }

    public void getFormatoCapitalPath() {
        logger.warning(" --- Inside getFormatoCapitalPath");
        BindingContainer bindings = getBindings();
        String tipoPago = FenixConstants.PATH_CALENDARIO_COMPLEJO_CAPITAL;

        try {
            OperationBinding operationBinding = executeOperation("getValorWsdl");
            operationBinding.getParamsMap().put("llave", tipoPago);
            operationBinding.execute();
            if (operationBinding.getResult() != null) {
                setPath((String) operationBinding.getResult());
                logger.warning("PATH_DOCUMENTO_CAPITAL: " + path);
                if (!operationBinding.getErrors().isEmpty()) {
                    logger.log(ADFLogger.ERROR,
                               "Error al Retornar getFormatoCapitalPath " + operationBinding.getErrors());
                }
            }
        } catch (Exception e) {
            logger.log(ADFLogger.WARNING, "Error en getFormatoCapitalPath" + e.getClass() + ":" + e.getMessage());
        }
    }

    public void descargaFormatoCapital(FacesContext facesContext, OutputStream outputStream) {
        logger.warning("Inicia metodo descargaFormatoCapital.");

        getFormatoCapitalPath();
        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
        String pathTest = getPath().toString();

        String nombreArchivo = null;
        logger.warning("Path de descarga: " + pathTest);
        File filed = new File(pathTest);
        nombreArchivo = filed.getName();
        String errorMsg = null;

        logger.warning("Nombre archivo: " + nombreArchivo);
        try {
            response.reset(); // Some JSF component library or some Filter might have set some headers in the buffer beforehand. We want to get rid of them, else it may collide.
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment;filename=" + nombreArchivo);
            response.setCharacterEncoding("UTF-8");
        } catch (Exception e) {
            logger.warning("ERROR al ejecutar response.", e);
            errorMsg = getStringFromBundle("ERROR_DESCARGA_FORMATO");
        }

        if (null == errorMsg) {
            FileInputStream fis = null;
            ;
            try {
                fis = new FileInputStream(filed);
            } catch (FileNotFoundException e) {
                logger.warning("No se pudo descargar el archivo.", e);
                errorMsg = getStringFromBundle("ERROR_FORMATO_NO_EXISTE");
            }
            byte[] b = new byte[2048];

            if (null == errorMsg) {
                //                try {
                //                    int n;
                //                    while ((n = fis.read(b)) > 0) {
                //                        outputStream.write(b,0,n);
                //                    }
                //                } catch (Exception e) {
                //                    logger.warning("No se pudo realizar la descarga del archivo.", e);
                //                    errorMsg = getStringFromBundle("ERROR_DESCARGA_FORMATO");
                //                }

                try {
                    int n;
                    while ((n = fis.available()) > 0) {
                        b = new byte[n];
                        int result = fis.read(b);
                        outputStream.write(b, 0, b.length);

                        if (result == -1)
                            break;

                    }
                } catch (Exception e) {
                    logger.warning("No se pudo realizar la descarga del archivo.", e);
                    errorMsg = getStringFromBundle("ERROR_DESCARGA_FORMATO");
                }

                if (null == errorMsg) {
                    try {
                        outputStream.flush();
                        fis.close();
                        outputStream.close();
                    } catch (Exception e) {
                        logger.warning("Error: ", e);
                        errorMsg = getStringFromBundle("ERROR_DESCARGA_FORMATO");
                    }
                }
            }
        }
        facesContext.responseComplete();
        logger.warning("Termina metodo descargaFormatoCapital");
    }

    public void descargaFormatoInteres(FacesContext facesContext, OutputStream outputStream) {
        logger.warning("Inicia metodo descargaFormatoInteres");

        getFormatoInteresPath();
        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
        String pathTest = getPath().toString();
        String nombreArchivo = null;
        logger.warning("Path de descarga: " + pathTest);
        File filed = new File(pathTest);
        nombreArchivo = filed.getName();
        String errorMsg = null;

        logger.warning("Nombre archivo: " + nombreArchivo);
        try {
            response.reset(); // Some JSF component library or some Filter might have set some headers in the buffer beforehand. We want to get rid of them, else it may collide.
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment;filename=" + nombreArchivo);
            response.setCharacterEncoding("UTF-8");
        } catch (Exception e) {
            logger.warning("ERROR al ejecutar response.", e);
            errorMsg = getStringFromBundle("ERROR_DESCARGA_FORMATO");
        }

        if (null == errorMsg) {
            FileInputStream fis = null;
            ;
            try {
                fis = new FileInputStream(filed);
            } catch (FileNotFoundException e) {
                logger.warning("No se pudo descargar el archivo.", e);
                errorMsg = getStringFromBundle("ERROR_FORMATO_NO_EXISTE");
            }
            byte[] b = new byte[2048];

            if (null == errorMsg) {
                //                try {
                //                    int n;
                //                    while ((n = fis.read(b)) > 0) {
                //                        outputStream.write(b,0,n);
                //                    }
                //                } catch (Exception e) {
                //                    logger.warning("No se pudo realizar la descarga del archivo.", e);
                //                    errorMsg = getStringFromBundle("ERROR_DESCARGA_FORMATO");
                //                }

                try {
                    int n;
                    while ((n = fis.available()) > 0) {
                        b = new byte[n];
                        int result = fis.read(b);
                        outputStream.write(b, 0, b.length);

                        if (result == -1)
                            break;

                    }
                } catch (Exception e) {
                    logger.warning("No se pudo realizar la descarga del archivo.", e);
                    errorMsg = getStringFromBundle("ERROR_DESCARGA_FORMATO");
                }

                if (null == errorMsg) {
                    try {
                        outputStream.flush();
                        fis.close();
                        outputStream.close();
                    } catch (Exception e) {
                        logger.warning("Error: ", e);
                        errorMsg = getStringFromBundle("ERROR_DESCARGA_FORMATO");
                    }
                }
            }
        }
        facesContext.responseComplete();
        logger.warning("Termina metodo descargaFormatoInteres");
    }

    public void descargarDocumentoFormato(FacesContext facesContext, OutputStream outputStream) throws IOException {
        logger.log(ADFLogger.WARNING, "--- Inside descargarDocumentoFormato, path:" + getPath().toString());
        String pathTest = "C://Calendario";
        File filed = new File(pathTest.toString());
        FileInputStream fis;
        byte[] b;
        try {
            fis = new FileInputStream(filed);
            int n;
            while ((n = fis.available()) > 0) {
                b = new byte[n];
                int result = fis.read(b);
                outputStream.write(b, 0, b.length);
                if (result == -1)
                    break;
            }
        } catch (IOException e) {
            logger.warning("ERROR: ", e);
        }
        outputStream.flush();
    }


    public void validarReglasNegocio(ActionEvent actionEvent) {
         logger.warning("Inside validarReglasNegocio.");
          
          if(!validarTermino406()){
            return;
          } 
            
         logger.warning("idDesembolso: " + JSFUtils.resolveExpression("#{pageFlowScope.idDesembolso}"));
         logger.warning("idTareaBPM: " + JSFUtils.resolveExpression("#{pageFlowScope.idTareaBPM}"));
         logger.warning("idProcesoBPM: " + JSFUtils.resolveExpression("#{pageFlowScope.pIdProcesoBPM}"));
         logger.warning("instanciaTareaBPM: " + JSFUtils.resolveExpression("#{pageFlowScope.pInstanciaTareaBPM}"));

         List listaReglasValidar = new ArrayList();
         List<String> listaErrores = new ArrayList<String>();
         Map<String, Object> params = new HashMap<String, Object>();
         Map mapaReglasValidadas = new HashMap();
         String mensajeErrorValidacion = null;
         String instanciaTarea = null;
         Long idDesembolso = null;
         Integer pIdTarea = null;
         Integer pIdProceso = null;
         Integer especificadoFechas = 1;
         Integer especificadoPlazo = 2;
         
         Integer especificacionFechas = (null == ADFUtils.getBoundAttributeValue("IdTcaEspecificacionFecha") || ADFUtils.getBoundAttributeValue("IdTcaEspecificacionFecha").toString().equals("")) ? null 
                                       : Integer.parseInt(ADFUtils.getBoundAttributeValue("IdTcaEspecificacionFecha").toString());
         
         if (null != JSFUtils.resolveExpression("#{pageFlowScope.idDesembolso}")) {
             idDesembolso = new Long(JSFUtils.resolveExpression("#{pageFlowScope.idDesembolso}").toString());
         } else {
             logger.warning("IdDesembolso NULL");
         }

         if (JSFUtils.resolveExpression("#{pageFlowScope.idTareaBPM}") != null) {
             pIdTarea = Integer.parseInt(JSFUtils.resolveExpression("#{pageFlowScope.idTareaBPM}").toString());
         } else {
             logger.warning("IdTareaBPM NULL");
         }

         if (JSFUtils.resolveExpression("#{pageFlowScope.pIdProcesoBPM}") != null) {
             pIdProceso = Integer.parseInt(JSFUtils.resolveExpression("#{pageFlowScope.pIdProcesoBPM}").toString());
         } else {
             logger.warning("IdProcesoBPM NULL");
         }

         if (JSFUtils.resolveExpression("#{pageFlowScope.pInstanciaTareaBPM}") != null) {
             instanciaTarea = JSFUtils.resolveExpression("#{pageFlowScope.pInstanciaTareaBPM}").toString();
         } else {
             logger.warning("pInstanciaTareaBPM NULL");
         }

         logger.log(ADFLogger.WARNING, "---DATOS_LLAMADO_VALIDAR_REGLAS---");
         logger.warning("pIdTarea: " + pIdTarea);
         logger.warning("idInstancia: " + instanciaTarea);
         logger.warning("pIdProceso: " + pIdProceso);
         logger.warning("idDesembolso: " + idDesembolso);
         
         actualizarTazaReferencia();
         AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorSecTasa2());

         logger.warning("Asignando listado de reglas a validar por servicio para iniciar el proceso de Desembolso");

         //EX04 : RN_02 : MSG_04
         //El contrato de desembolso debe encontrarse en la programación del mes vigente.
         listaReglasValidar.add(FenixConstants.ID_REGLA_TRANSACCION_PROGRAMACION);

         //EX05 : RN_03 : MSG_05
         //Para un cliente diferente de sector público con garantía soberana el valor de SCR recomendado o vigente no debe ser mayor 5 para poder iniciar el proceso de desembolso.
         listaReglasValidar.add(FenixConstants.ID_REGLA_TRANSACCION_SCR_INICIO_PROCESO_DESEMBOLSO);

         //EX06 : RN_04 : MSG_06
         //Para un cliente de sector privado, no se podrá realizar el registro del contrato de desembolso si se excede el límite de mora técnica y no se cuenta una excepción registrada.
         //En caso de un cliente de sector público, no se podrá realizar el registro del contrato de desembolso si se excede el límite de mora técnica, los días en mora son igual o mayor
         //a 30 y no se cuenta con una excepción registrada. Por el contrario, si se excede el límite de mora técnica pero los días en mora son menores a 30, se permitirá realizar el registro del desembolso.
         listaReglasValidar.add(FenixConstants.ID_REGLA_TRANSACCION_MORA);

         //EX10 : RN_08 : MSG_10
         //Solo se podrá realizar el registro del desembolso cuando la hora actual del sistema, sea previa a la hora de cierre de banca de la moneda del contrato de desembolso indicando la posibilidad de que el contrato de desembolso no sea aplicado.
         //Moneda local ? 1.5 horas antes del cierre para la moneda
         //USD ? 1.5 horas antes del cierre para la moneda
         listaReglasValidar.add(FenixConstants.ID_REGLA_TRANSACCION_PROXIMA_HORA_CIERRE_MONEDA);

         //EX12 : RN_10 : MSG_12
         //Se podrá realizar el registro del desembolso solo si se cuenta con las condiciones correspondientes cumplidas tanto para la solicitud, el contrato de desembolso, generales y periódicas.
         listaReglasValidar.add(FenixConstants.ID_REGLA_TRANSACCION_CONDICIONES);
         
         //EX13 : RN11 : MSG_13
         //Se podrá realizar el registro del contrato de desembolso solo si la línea de crédito cuenta con monto disponible para cubrir el monto del contrato de desembolso.
         
         //EX14 : RN12 : MSG_14
         //Para un cliente diferente de sector público con garantía soberana se podrá registrar el contrato de desembolso para un cliente con un SCR recomendado o vigente igual a 5 solo cuando se cuente con una excepción otorgada.
         listaReglasValidar.add(FenixConstants.ID_REGLA_TRANSACCION_SCR_CONTINUAR_PROCESO_DESEMBOLSO);
         
         //EX17 - RN21 : MSG_20
         //Para poder aprobar el contrato de desembolso se debe verificar que la suma del monto de los contratos de desembolso en dólares (incluyendo el actual)) para el grupo económico y el país del cliente sea menor o igual al
         //límite configurado en el aplicativo de límites. De lo contrario no iniciará el proceso a menos que se cuente con la excepción otorgada a la solicitud.
         listaReglasValidar.add(FenixConstants.ID_REGLA_TRANSACCION_LIMITES);
         
         //EX18 : RN_22 : MSG_21
         //Se podrá registra el contrato de desembolso solo si la suma de los contratos de desembolsos registrados y desembolsados + los contratos en tránsito + el desembolso actual (solo se deben considerar los contratos con el destino
         //Capital de Trabajo) es menor o igual al límite configurado en Flexcube para la línea de crédito con el destino Capital de trabajo.
         //listaReglasValidar.add(FenixConstants.ID_REGLA_TRANSACCION_TOTAL_CONTRATO_DESEMBOLSO); //NO APLICA PARA SERVICIO
         
         //EX19 : RN_23 : MSG_22
         //Para poder registrar el contrato de desembolso la frecuencia de pago de interés debe ser menor o igual al límite configurado en UMIPYME para el programa y producto del contrato de desembolso.
         //EX20 : RN_24 : MSG_23
         //Para poder registrar el contrato de desembolso el tipo plazo del componente de interés configurado para el programa y producto del contrato de desembolso debe coincidir con el configurado en el aplicativo de UMIPYME.
         listaReglasValidar.add(FenixConstants.ID_REGLA_TRANSACCION_COMPONENTE_INTERES);
         
         //EX21 : RN25 : MSG_24
         //Para poder registrar el contrato de desembolso el plazo de capital del contrato debe ser menor o igual al plazo máximo configurado en UMIPYME para el programa y destino.
         listaReglasValidar.add(FenixConstants.ID_REGLA_TRANSACCION_PLAZO_CAPITAL_MAXIMO);

         //EX22 : RN26 : MSG_25
         //Para poder registrar  el contrato de desembolso el periodo de gracia del contrato debe ser menor o igual al periodo de gracia máximo configurado en UMIPYME para el programa y destino.
         listaReglasValidar.add(FenixConstants.ID_REGLA_TRANSACCION_PERIODO_GRACIA);

         //EX23 : RN27 : MSG_26
         //Para poder registrar el contrato de desembolso el plazo de capital del contrato debe ser mayor o igual al plazo mínimo configurado en UMIPYME para el programa y destino.
         listaReglasValidar.add(FenixConstants.ID_REGLA_TRANSACCION_PLAZO_CAPITAL_MINIMO);

         //EX24 : RN28 : MSG_27
         //Cuando la suma del importe (cartera + desembolsos en tránsito) de la línea de crédito para el programa del contrato de desembolso exceda el porcentaje límite configurado en FLEXCUBE para desembolsar al programa no se podrá aprobar el contrato de desembolso.
         listaReglasValidar.add(FenixConstants.ID_REGLA_TRANSACCION_TOTAL_LINEA_CREDITO);

         if (listaReglasValidar != null && idDesembolso != null && instanciaTarea != null && pIdTarea != null &&  pIdProceso != null) {
             /*
             params.put("listaReglas", listaReglasValidar);
             params.put("idContratoDesembolso", idDesembolso);
             params.put("instanciaTarea", instanciaTarea);
             params.put("idTarea", pIdTarea);
             params.put("idProceso", pIdProceso);
             try {
                 logger.warning("Invocando metodo obtenerReglasNegocio");
                 mapaReglasValidadas = execute(params, "obtenerReglasNegocio");
             } catch (Exception e) {
                 logger.warning("ERROR al ejecutar metodo obtenerReglasNegocio.", e);
             }
             */
             BindingContainer bindings = getBindings();
             OperationBinding operationBinding = null;
             String operError = null;
                            
             try{
                 logger.warning("Asignando parametros de entrada de metodo");
                 operationBinding =  bindings.getOperationBinding("obtenerReglasNegocio"); //
                 operationBinding.getParamsMap().put("listaReglas", listaReglasValidar);
                 operationBinding.getParamsMap().put("idContratoDesembolso", idDesembolso);
                 operationBinding.getParamsMap().put("instanciaTarea", instanciaTarea);
                 operationBinding.getParamsMap().put("idTarea", pIdTarea);
                 operationBinding.getParamsMap().put("idProceso", pIdProceso);
                 mapaReglasValidadas=(HashMap)operationBinding.execute();
                 }catch(Exception e){
                  logger.warning("Error al aplicar la validacion de reglas!");
                 }
                         
             if (!operationBinding.getErrors().isEmpty()) {
                 operError = operationBinding.getErrors().toString();
                 logger.warning("Error Operation Bindings: " + operError);           
             }else{
             }

             if (operError == null){
                 if(!mapaReglasValidadas.isEmpty() && mapaReglasValidadas.size() > 0) {
                 logger.warning("Obteniendo resultados de mapa de reglas ya validadas.");
                 mensajeErrorValidacion = obtenerEstadoReglasValidadas(mapaReglasValidadas, FenixConstants.ID_REGLA_TRANSACCION_PROGRAMACION);
                 if (null != mensajeErrorValidacion) {
                     logger.warning("Añadiendo listado de mensajes.ID_REGLA_TRANSACCION_PROGRAMACION");
                     //verificamos si existe este error en la lista previo a agregarlo
                     if(!listaErrores.contains(mensajeErrorValidacion))
                     {
                         listaErrores.add(mensajeErrorValidacion);
                     }
                 }
                 mensajeErrorValidacion = obtenerEstadoReglasValidadas(mapaReglasValidadas, FenixConstants.ID_REGLA_TRANSACCION_SCR_INICIO_PROCESO_DESEMBOLSO);
                 if (null != mensajeErrorValidacion) {
                     logger.warning("Añadiendo listado de mensajes.ID_REGLA_TRANSACCION_SCR_INICIO_PROCESO_DESEMBOLSO");
                     //verificamos si existe este error en la lista previo a agregarlo
                     if(!listaErrores.contains(mensajeErrorValidacion))
                     {
                         listaErrores.add(mensajeErrorValidacion);
                     }
                 }
                 mensajeErrorValidacion = obtenerEstadoReglasValidadas(mapaReglasValidadas, FenixConstants.ID_REGLA_TRANSACCION_MORA);
                 if (null != mensajeErrorValidacion) {
                     logger.warning("Añadiendo listado de mensajes.ID_REGLA_TRANSACCION_MORA");
                     //verificamos si existe este error en la lista previo a agregarlo
                     if(!listaErrores.contains(mensajeErrorValidacion))
                     {
                         listaErrores.add(mensajeErrorValidacion);
                     }
                 }
                 mensajeErrorValidacion = obtenerEstadoReglasValidadas(mapaReglasValidadas, FenixConstants.ID_REGLA_TRANSACCION_PROXIMA_HORA_CIERRE_MONEDA);
                 if (null != mensajeErrorValidacion) {
                     logger.warning("Añadiendo listado de mensajes.ID_REGLA_TRANSACCION_PROXIMA_HORA_CIERRE_MONEDA");
                     //verificamos si existe este error en la lista previo a agregarlo
                     if(!listaErrores.contains(mensajeErrorValidacion))
                     {
                         listaErrores.add(mensajeErrorValidacion);
                     }
                 }
                 mensajeErrorValidacion = obtenerEstadoReglasValidadas(mapaReglasValidadas, FenixConstants.ID_REGLA_TRANSACCION_CONDICIONES);
                 if (null != mensajeErrorValidacion) {
                     logger.warning("Añadiendo listado de mensajes.ID_REGLA_TRANSACCION_CONDICIONES");
                     //verificamos si existe este error en la lista previo a agregarlo
                     if(!listaErrores.contains(mensajeErrorValidacion))
                     {
                         listaErrores.add(mensajeErrorValidacion);
                     }
                 }
                 mensajeErrorValidacion = obtenerEstadoReglasValidadas(mapaReglasValidadas, FenixConstants.ID_REGLA_TRANSACCION_SCR_CONTINUAR_PROCESO_DESEMBOLSO);
                 if (null != mensajeErrorValidacion) {
                     logger.warning("Añadiendo listado de mensajes.ID_REGLA_TRANSACCION_SCR_CONTINUAR_PROCESO_DESEMBOLSO");
                     //verificamos si existe este error en la lista previo a agregarlo
                     if(!listaErrores.contains(mensajeErrorValidacion))
                     {
                         listaErrores.add(mensajeErrorValidacion);
                     }
                 }
                 mensajeErrorValidacion = obtenerEstadoReglasValidadas(mapaReglasValidadas, FenixConstants.ID_REGLA_TRANSACCION_LIMITES);
                 if (null != mensajeErrorValidacion) {
                     logger.warning("Añadiendo listado de mensajes.ID_REGLA_TRANSACCION_LIMITES");
                     //verificamos si existe este error en la lista previo a agregarlo
                     if(!listaErrores.contains(mensajeErrorValidacion))
                     {
                         listaErrores.add(mensajeErrorValidacion);
                     }
                 }
                 mensajeErrorValidacion = obtenerEstadoReglasValidadas(mapaReglasValidadas, FenixConstants.ID_REGLA_TRANSACCION_COMPONENTE_INTERES);
                 if (null != mensajeErrorValidacion) {
                     logger.warning("Añadiendo listado de mensajes.ID_REGLA_TRANSACCION_COMPONENTE_INTERES");
                     //verificamos si existe este error en la lista previo a agregarlo
                     if(!listaErrores.contains(mensajeErrorValidacion))
                     {
                         listaErrores.add(mensajeErrorValidacion);
                     }
                 }
                 mensajeErrorValidacion = obtenerEstadoReglasValidadas(mapaReglasValidadas, FenixConstants.ID_REGLA_TRANSACCION_PLAZO_CAPITAL_MAXIMO);
                 if (null != mensajeErrorValidacion) {
                     logger.warning("Añadiendo listado de mensajes.ID_REGLA_TRANSACCION_PLAZO_CAPITAL_MAXIMO");
                     //verificamos si existe este error en la lista previo a agregarlo
                     if(!listaErrores.contains(mensajeErrorValidacion))
                     {
                         listaErrores.add(mensajeErrorValidacion);
                     }
                 }
                 mensajeErrorValidacion = obtenerEstadoReglasValidadas(mapaReglasValidadas, FenixConstants.ID_REGLA_TRANSACCION_PERIODO_GRACIA);
                 if (null != mensajeErrorValidacion) {
                     logger.warning("Añadiendo listado de mensajes.ID_REGLA_TRANSACCION_PERIODO_GRACIA");
                     //verificamos si existe este error en la lista previo a agregarlo
                     if(!listaErrores.contains(mensajeErrorValidacion))
                     {
                         listaErrores.add(mensajeErrorValidacion);
                     }
                 }
                 mensajeErrorValidacion = obtenerEstadoReglasValidadas(mapaReglasValidadas, FenixConstants.ID_REGLA_TRANSACCION_PLAZO_CAPITAL_MINIMO);
                 if (null != mensajeErrorValidacion) {
                     logger.warning("Añadiendo listado de mensajes.ID_REGLA_TRANSACCION_PLAZO_CAPITAL_MINIMO");
                     //verificamos si existe este error en la lista previo a agregarlo
                     if(!listaErrores.contains(mensajeErrorValidacion))
                     {
                         listaErrores.add(mensajeErrorValidacion);
                     }
                 }
                 mensajeErrorValidacion = obtenerEstadoReglasValidadas(mapaReglasValidadas, FenixConstants.ID_REGLA_TRANSACCION_TOTAL_LINEA_CREDITO);
                 if (null != mensajeErrorValidacion) {
                     logger.warning("Añadiendo listado de mensajes.ID_REGLA_TRANSACCION_TOTAL_LINEA_CREDITO");
                     //verificamos si existe este error en la lista previo a agregarlo
                     if(!listaErrores.contains(mensajeErrorValidacion))
                     {
                         listaErrores.add(mensajeErrorValidacion);
                     }
                 }
             } else {
                 logger.warning("No se obtivieron reglas validadas por servicio, por posible inactivacion.");
                 //listaErrores.add(getStringFromBundle("ERROR_REGLAS_NO_VALIDADAS_POR_SERVICIO"));
                 //listaErrores.add(operError); //En este caso se repite por segunda vez el mensaje que se muestra en la lista
             }
         }else{
             listaErrores.add(getStringFromBundle("ERROR_REGLAS_NO_VALIDADAS_POR_SERVICIO") + operError);
         }
       //Se omiten las validaciones ya que se ocupa el metodo validarFechasInicioDesembolsoTotalidadRecursos()     
/*           //Reglas a validar que no son por servicio.
             // EX07 : RN_18 MSG_16
             // Para poder registrar el contrato de desembolso la fecha efectiva
             // debe ser menor o igual a la fecha máxima para iniciar desembolsos (T102).
             mensajeErrorValidacion = validarFechaEfectivaTermino();
             if (null != mensajeErrorValidacion) {
                                      //verificamos si existe este error en la lista previo a agregarlo
                     if(!listaErrores.contains(mensajeErrorValidacion))
                     {
                         listaErrores.add(mensajeErrorValidacion);
                     }
             }
             
             //EX08 : RN06 : MSG_17
             //Para registrar el contrato de desembolso la fecha efectiva debe ser menor a la fecha para desembolsar la totalidad de los recursos, este valor deben ser tomado de Flexcube.
             mensajeErrorValidacion = validarFechaEfectivaMaxima();
             if (null != mensajeErrorValidacion) {
                                      //verificamos si existe este error en la lista previo a agregarlo
                     if(!listaErrores.contains(mensajeErrorValidacion))
                     {
                         listaErrores.add(mensajeErrorValidacion);
                     }
             }
*/             
             //EX09 : RN_07 : MSG_09
             //Se podrá realizar el registro de un contrato de desembolso solo cuando dicho contrato se encuentre en el estado: Aprobado por gerente y Fondos reservados.
             mensajeErrorValidacion = validarEstadoContrato();
             if (null != mensajeErrorValidacion) {
                 //verificamos si existe este error en la lista previo a agregarlo
                 if(!listaErrores.contains(mensajeErrorValidacion))
                 {
                     listaErrores.add(mensajeErrorValidacion);
                 }
             }
             
             //Si existen errores se muestran en pantalla, si no es asi se valida "Origen de Transferencia" para despues Propagar el Desembolso.
             if (listaErrores.size() > 0) {
                 mostrarListaErrores(listaErrores);
             } else {

                 if(especificacionFechas != null){
                     
                     logger.warning("Validando Fecha Inicio desembolsos y Fecha de desembolso de la totalidad de recursos.");
                     List<String> listaErroresFecha = new ArrayList<String>();
                     listaErroresFecha = validarFechasInicioDesembolsoTotalidadRecursos();
                     if(listaErroresFecha.size()>0){
                             for(String mensajeFechas : listaErroresFecha){
                                 logger.warning("Obteniendo errores de validacion de fechas.");
                                 JSFUtils.addFacesErrorMessage(mensajeFechas);
                             }
                         }
                     else{
                             if(especificacionFechas.compareTo(especificadoPlazo) == 0) {//plazos
                                  logger.warning("*inf, se especifico un calculo por plasos, Calculando Fechas...");
                                  Boolean fechasAsociadas = getFechasParaRegistrarContratoByPlazos();
                                  logger.warning("Se asociaron fechas al contrato? :"+fechasAsociadas);
                             
                                      if(fechasAsociadas)
                                          validarOrigenTransferencia();
                                       else
                                           JSFUtils.addFacesErrorMessage("Error no se pudo obtener la fecha del Primer dia de pago y fecha de vencimiento");
                             
                             }else if(especificacionFechas.compareTo(especificadoFechas) == 0){
                                logger.warning("*inf, se especifico un calculo por fechas, inicia registro");
                                 validarOrigenTransferencia();
                                   
                                    /*Boolean seAsociaronPlazosPago = getPlazoPago();
                                    Boolean seAsociaronPlazosPeriodoGracia = getPlazoPeriodoGracia();
                                    
                                      if(seAsociaronPlazosPago && seAsociaronPlazosPeriodoGracia)
                                       validarOrigenTransferencia();
                                      else
                                        JSFUtils.addFacesErrorMessage("Error no se pudo calcular los plazos de la condicion financiera");
                                 */
                                    
                             }else{                                  
                                logger.warning("*Error, valor de la variable especificacionFechas: "+especificacionFechas);
                                JSFUtils.addFacesErrorMessage("Error no se reconoce el tipo de especificacion por fechas o plazos");        
                             }
                            
                         }
         
                                   

                 }else{
                     logger.warning("*Error, especificacionFechas es resuelta a null");
                     JSFUtils.addFacesErrorMessage("Se requiere un tipo de especificacion por fechas o plazos");
                 }
             }
         }
     }
    
    
    
    public Boolean validarTermino406(){            
           logger.warning("*Inf, inicia metodo validarTermino406");
           Boolean respuesta = Boolean.FALSE;
           Map datos = new HashMap();
           
           BindingContainer binding = getBindings();
           OperationBinding operBinding = null;                
           
           try{
               
               CondicionesFinancierasBean condicionesFinancierasBean = null;
                         condicionesFinancierasBean = (CondicionesFinancierasBean)JSFUtils.resolveExpression("#{pageFlowScope.CondicionFinancieraBean}");
                                 
               
               Long idOperacion =  condicionesFinancierasBean.getIdOperacion();
               operBinding =  binding.getOperationBinding("validarTermino406");
               operBinding.getParamsMap().put("idOperacion", idOperacion);                
               operBinding.execute();
               
           }catch(Exception e){
             JSFUtils.addFacesErrorMessage("Error al validar termino 406 ->"+e.getMessage());
           }
           
           if (!operBinding.getErrors().isEmpty()) {
              logger.warning("*Error, OperationBinding crearContratoDesembolso devuelve errores ->"+operBinding.getErrors());
              JSFUtils.addFacesErrorMessage("Error: "+operBinding.getErrors());
           }else{
           
           if(operBinding.getResult() !=null){
                   datos = (Map)operBinding.getResult();
                   respuesta = (Boolean)datos.get("respuesta");
                   Long clietneEnMora = (Long)datos.get("clietneEnMora");
                  
                   if(!respuesta){
                       JSFUtils.addFacesErrorMessage("El cliente "+clietneEnMora+" se encuentra en Mora y forma parte del Grupo Solidaroio: ");   
                   }                        
           }
           
            logger.warning("*Inf, termina el metodo validarTermino406" +respuesta );
           } 
          return respuesta;
       }

    
    
    
    public String obtenerEstadoReglasValidadas(Map mapaReglasValidadas, Long idReglaPorValidar) {
        logger.warning("Inside obtenerEstadoReglasValidadas.");
        
        String descripcionEstadoRegla = null;
        String mensajeError = null;

        try {
            descripcionEstadoRegla = (String) mapaReglasValidadas.get(idReglaPorValidar);
        } catch (Exception e) {
            logger.warning("ERROR al recibir la validacion de la Regla de negocio de limites.", e);
            mensajeError = getStringFromBundle("ERROR_REGLAS_NO_VALIDADAS_POR_SERVICIO");
        }

        if (null != descripcionEstadoRegla) {
            logger.warning("Evaluando estado de regla.");
            if (descripcionEstadoRegla.equalsIgnoreCase(getStringFromBundle("ESTADO_VALIDACION_REGLA_CUMPLIDA")) ||
                descripcionEstadoRegla.equalsIgnoreCase(getStringFromBundle("ESTADO_VALIDACION_REGLA_EXCEPTUADA"))) {
                logger.warning("Regla validada correctamente.");
            }
            else if(descripcionEstadoRegla.equalsIgnoreCase(getStringFromBundle("ESTADO_VALIDACION_REGLA_ERROR_FLEXCUBE")))
            {
                mensajeError = getStringFromBundle("ERROR_ESTADO_FLEXCUBE");
            }
            else {
                logger.warning("La validacion de la regla no se cumplio.");
                if (FenixConstants.ID_REGLA_TRANSACCION_LIMITES.compareTo(idReglaPorValidar) == 0) {
                    mensajeError = getStringFromBundle("ERROR_LIMITE_PAIS_GRUPO_ECONOMICO_MSG");
                }
                if (FenixConstants.ID_REGLA_TRANSACCION_TOTAL_CONTRATO_DESEMBOLSO.compareTo(idReglaPorValidar) == 0) {
                    mensajeError = getStringFromBundle("ERROR_TOTAL_CONTRATO_DESEMBOLSO_MSG");
                }
                if (FenixConstants.ID_REGLA_TRANSACCION_PROGRAMACION.compareTo(idReglaPorValidar) == 0) {
                    mensajeError = getStringFromBundle("ERROR_PROGRAMACION_MSG");
                }
                if (FenixConstants.ID_REGLA_TRANSACCION_SCR_INICIO_PROCESO_DESEMBOLSO.compareTo(idReglaPorValidar) == 0) {
                    mensajeError = getStringFromBundle("ERROR_SCR_MSG");
                }
                if (FenixConstants.ID_REGLA_TRANSACCION_MORA.compareTo(idReglaPorValidar) == 0) {
                    mensajeError = getStringFromBundle("ERROR_MORA_MSG");
                }
                if (FenixConstants.ID_REGLA_TRANSACCION_CONDICIONES.compareTo(idReglaPorValidar) == 0) {
                    mensajeError = getStringFromBundle("ERROR_CONDICIONES_MSG");
                }
                if (FenixConstants.ID_REGLA_TRANSACCION_SCR_CONTINUAR_PROCESO_DESEMBOLSO.compareTo(idReglaPorValidar) == 0) {
                    mensajeError = getStringFromBundle("ERROR_SCR_EXCEPCION_MSG");
                }
                if (FenixConstants.ID_REGLA_TRANSACCION_UMIPYME.compareTo(idReglaPorValidar) == 0) {
                    mensajeError = getStringFromBundle("ERROR_UMIPYME_MSG");
                }
                if (FenixConstants.ID_REGLA_TRANSACCION_PRESENTACION_F1.compareTo(idReglaPorValidar) == 0) {
                    mensajeError = getStringFromBundle("ERROR_F1_MSG");
                }
                if (FenixConstants.ID_REGLA_TRANSACCION_AJUSTE_TASA.compareTo(idReglaPorValidar) == 0) {
                    mensajeError = getStringFromBundle("ERROR_AJUSTE_TASA_MSG");
                }
                if (FenixConstants.ID_REGLA_TRANSACCION_MOMENTO_COBRO.compareTo(idReglaPorValidar) == 0) {
                    mensajeError = getStringFromBundle("ERROR_MOMENTO_COBRO_MSG");
                }
                if (FenixConstants.ID_REGLA_TRANSACCION_COMPONENTE_INTERES.compareTo(idReglaPorValidar) == 0) {
                    mensajeError = getStringFromBundle("ERROR_COMPONENTE_INTERES_MSG");
                }
                if (FenixConstants.ID_REGLA_TRANSACCION_PLAZO_CAPITAL_MAXIMO.compareTo(idReglaPorValidar) == 0) {
                    mensajeError = getStringFromBundle("ERROR_PLAZO_CAPITAL_MAXIMO_MSG");
                }
                if (FenixConstants.ID_REGLA_TRANSACCION_PERIODO_GRACIA.compareTo(idReglaPorValidar) == 0) {
                    mensajeError = getStringFromBundle("ERROR_PERIODO_GRACIA_MSG");
                }
                if (FenixConstants.ID_REGLA_TRANSACCION_PLAZO_CAPITAL_MINIMO.compareTo(idReglaPorValidar) == 0) {
                    mensajeError = getStringFromBundle("ERROR_PLAZO_CAPITAL_MINIMO_MSG");
                }
                if (FenixConstants.ID_REGLA_TRANSACCION_TOTAL_LINEA_CREDITO.compareTo(idReglaPorValidar) == 0) {
                    mensajeError = getStringFromBundle("ERROR_TOTAL_LINEA_CREDITO_MSG");
                }
                if (FenixConstants.ID_REGLA_TRANSACCION_LAFT.compareTo(idReglaPorValidar) == 0) {
                    mensajeError = getStringFromBundle("ERROR_LAFT_MSG");
                }
            }
        } else {
            
            logger.warning("descripcionEstadoRegla es NULL, No se pudo obtener el estado de la regla de negocio.");
            /*
             * Se inactiva codigo para atender incidencia FNXII-5571, debido a que el servicio de validacion solo devuelve
             * las reglas activas
            if (!getEsErrorValidacion()) {
                setEsErrorValidacion(Boolean.TRUE);
                mensajeError = getStringFromBundle("ERROR_REGLAS_NO_VALIDADAS_POR_SERVICIO");
            }
            */
            logger.warning("Regla no encontrada por posible inactivacion: " + idReglaPorValidar);
        }
        logger.warning("Termina metodo obtenerEstadoReglasValidadas");
        return mensajeError;
    }


    public Boolean esOrigenTransferenciaCuentasBcie() {
        logger.warning("Inf, inicia metodo origenTransferenciaCuentasBcie");
        String origenTransferencia = "";
        Boolean respuesta = null;
        try {
            BindingContainer binding = ADFUtils.getBindingContainer();
            OperationBinding operBinding = binding.getOperationBinding("getContratoSeleccionado");
            Row fila = (Row) operBinding.execute();

            if (fila != null) {

                origenTransferencia =
                    (null == fila.getAttribute("OrigenTranferenciaCliente")) ? null :
                    (String) fila.getAttribute("OrigenTranferenciaCliente");

                logger.warning("origenTransferencia recuperado del contrato->" + origenTransferencia);

                if (origenTransferencia.equalsIgnoreCase("DIRECTO_CLIENTE")) {
                    respuesta = Boolean.FALSE;
                } else if (origenTransferencia.equalsIgnoreCase("SIN_SALIDA_FONDOOS")) {
                    respuesta = Boolean.FALSE;
                } else if (origenTransferencia.equalsIgnoreCase("CUENTAS_BCIE")) {
                    respuesta = Boolean.TRUE;
                } else {
                    logger.warning("***Error no se pudo identificar el origen de la transferencia");
                }

            } else {
                logger.warning("***Error, No se pudo recuperar el origen de la transferencia");
            }

            if (!operBinding.getErrors().isEmpty()) {
                logger.warning("OperationBinding getContratoSeleccionado devuelve errores");
                JSFUtils.addFacesErrorMessage("Error. ->" + operBinding.getErrors().toString());
            }

        } catch (Exception e) {
            logger.warning("***Error al consultar los datos del contrato de desembolso " + e);
            JSFUtils.addFacesErrorMessage("Error no se pudo recuperar las cuentas del cliente");
        }
        logger.warning("Inf, termina metodo origenTransferenciaCuentasBcie");
        return respuesta;
    }

    public void obtenerBHQ() {
        logger.log(ADFLogger.WARNING, "---Inside validarFT");

        Long idContratoSeleccionado = null;
        if (JSFUtils.resolveExpression("#{pageFlowScope.idDesembolso}") != null) {
            idContratoSeleccionado = (Long) JSFUtils.resolveExpression("#{pageFlowScope.idDesembolso}");
        }
        try {
            BindingContext bindingctx = BindingContext.getCurrent();
            BindingContainer binding = bindingctx.getCurrentBindingsEntry();
            DCBindingContainer bindingsImpl = (DCBindingContainer) binding;
            DCIteratorBinding dciter = bindingsImpl.findIteratorBinding("FT05VO1Iterator");
            FT05VOImpl vo = (FT05VOImpl) dciter.getViewObject();
            vo.validarFT(idContratoSeleccionado, null, null);
        } catch (Exception e) {
            logger.log(ADFLogger.WARNING, "---ERROR validarFT");
        }
    }

    public void validarOrigenTransferencia() {
        logger.warning("Inside validarOrigenTransferencia.");

        String origenTranferenciaCliente =
            (ADFUtils.getBoundAttributeValue("OrigenTranferenciaCliente") == null) ? null :
            ADFUtils.getBoundAttributeValue("OrigenTranferenciaCliente").toString();
        logger.warning("origenTranferenciaCliente: " + origenTranferenciaCliente);

        //Se llama servicio propagarContratoDesembolso no importando el "Origen de Transferencia" en espera de confirmacion para el tipo de Tranferencia de la fuente directa al cliente.
        propagarContratoDesembolso();
    }

    public void propagarContratoDesembolso() {
        logger.warning("Inside propagarContratoDesembolso.");

        Long idContratoSeleccionado = null;

        if (null != JSFUtils.resolveExpression("#{pageFlowScope.idDesembolso}")) {
            idContratoSeleccionado = new Long(JSFUtils.resolveExpression("#{pageFlowScope.idDesembolso}").toString());
        } else {
            logger.warning("IdDesembolso NULL");
        }

        if (idContratoSeleccionado != null) {
            try {
                BindingContainer binding = ADFUtils.getBindingContainer();
                OperationBinding operBinding = binding.getOperationBinding("propagarContratoDesembolso");
                operBinding.getParamsMap().put("idContratoDesembolso", idContratoSeleccionado);
                operBinding.execute();

                if (operBinding.getResult() != null) {
                    String codigoBHQ = (String) operBinding.getResult();
                    logger.warning("codigoBHQ: " + codigoBHQ);
                    actualizarPayloadElement("ContratoFlexAux", codigoBHQ);
                    
                    generarReportePlanAmortizacion(idContratoSeleccionado, codigoBHQ);
                    
                    logger.warning("Invocando metodo propagarSinTransferencias.");
                    propagarSinTransferencias();
                } else {
                    JSFUtils.addFacesErrorMessage(getStringFromBundle("CONDICION_FINANCIERA_MSG_03"));
                }

            } catch (Exception e) {
                logger.log(ADFLogger.WARNING, "ERROR propagarContratoDesembolso", e);
            }
        }
    }
    
    public void actualizarPayloadElement(String psElementName, Object poValue) {
      AttributeBinding attributeBinding = null;
    
      attributeBinding = ADFUtils.findControlBinding(psElementName);
      
      if(attributeBinding!=null)
          attributeBinding.setInputValue(poValue);
      
    }

    public void propagarSinTransferencias() {
        logger.warning("Inicia metodo propagarSinTransferencias.");
        Long idContratoSeleccionado = null;

        if (null != JSFUtils.resolveExpression("#{pageFlowScope.idDesembolso}")) {
            idContratoSeleccionado = new Long(JSFUtils.resolveExpression("#{pageFlowScope.idDesembolso}").toString());
        } else {
            logger.warning("IdDesembolso NULL");
        }
        if (null != idContratoSeleccionado) {
            BindingContainer binding = ADFUtils.getBindingContainer();
            OperationBinding operBinding = binding.getOperationBinding("propagarSinTransferencias");
            operBinding.getParamsMap().put("idContrato", idContratoSeleccionado);
            operBinding.execute();
            if (!operBinding.getErrors().isEmpty()) {
                logger.warning("Se obtuvieron errores");
            } else {
                logger.warning("Se ejecuto la propagacion de manera correcta");
            }
        } else {
            logger.warning("Error al obtener el id contrato");
        }
        logger.warning("Termina metodo propagarSinTransferencias.");
    }
    public void generarReportePlanAmortizacion(ActionEvent actionEvent) 
    {
        Long idContratoSeleccionado = null;
        AttributeBinding contratoFlexcube;
        contratoFlexcube = ADFUtils.findControlBinding("ContratoFlexcube").getInputValue() != null ? ADFUtils.findControlBinding("ContratoFlexcube") : ADFUtils.findControlBinding("ContratoFlexAux");
        
        String codigoBHQ = contratoFlexcube.getInputValue() != null ? contratoFlexcube.getInputValue().toString() : null;

        if (null != JSFUtils.resolveExpression("#{pageFlowScope.idDesembolso}")) {
            idContratoSeleccionado = new Long(JSFUtils.resolveExpression("#{pageFlowScope.idDesembolso}").toString());
        } else {
            logger.warning("IdDesembolso NULL");
        }
        
        if(idContratoSeleccionado != null && codigoBHQ != null)
        {
            generarReportePlanAmortizacion(idContratoSeleccionado, codigoBHQ);
        }
        
    }
    
    private void generarReportePlanAmortizacion(Long idContatoDesembolso, String codigoBHQ)
    {
        logger.warning("Inicia metodo generarReportePlanAmortizacion.");
        logger.warning("idContatoDesembolso: " + idContatoDesembolso);
        logger.warning("codigoBHQ: " + codigoBHQ);

        String mensajeError = null;
        Boolean esError = Boolean.FALSE;
        
        CondicionesFinancierasBean condicionesBean = null;
        condicionesBean = (CondicionesFinancierasBean) JSFUtils.resolveExpression("#{pageFlowScope.CondicionFinancieraBean}");
        
        GeneraReportePlanAmortizacionRequestType request = new GeneraReportePlanAmortizacionRequestType();
        GeneraReportePlanAmortizacionResponseType response = null;
        
        try 
        {
            DesembolsoPTSOAP12BindingQSService desembolsoService = this.initDesembolsoService();
            DesembolsoPT desembolsoPT = desembolsoService.getDesembolsoPTSOAP12BindingQSPort();
            
            request.setIdDesembolso(idContatoDesembolso);
            request.setIdFacturador(codigoBHQ);
            
            org.bcie.desembolsomo.ObjectFactory obfact = new org.bcie.desembolsomo.ObjectFactory();
            
            JAXBElement<Boolean> jaxbCargarDocumento = obfact.createGeneraReportePlanAmortizacionRequestTypeCargarDocumento(false);
            request.setCargarDocumento(jaxbCargarDocumento);
            
            JAXBElement<Long> jaxbidOperacion = obfact.createGeneraReportePlanAmortizacionRequestTypeIdOperacion(condicionesBean.getIdOperacion());
            request.setIdOperacion(jaxbidOperacion);
            
            String mensaje = "";
            StringWriter xmlEntrada = null;
            StringWriter xmlSalida = null;
            
            mensaje = "Invocando Servicio - Generar Reporte Plan Amortizacion-";
            try {
                logger.log(ADFLogger.WARNING, ">HNWS" + writeXMLRequest(request, request.getClass()));
            } catch (Exception ex) {
                ;
            }
            response = desembolsoPT.generarReportePlanAmortizacion(request);
            mensaje = "Invocando Servicio - Aviso de cobro comision -";
            try {
                logger.log(ADFLogger.WARNING, ">HNWS" + writeXMLRequest(response, response.getClass()));
            } catch (Exception ex) {
                ;
            }

            if (response.getResultado().getResult().value() == "ERROR") {
                mensajeError = response.getResultado().getMessage();
                mensajeError = mensajeError.concat(response.getResultado().getError().getErrorDescription());
                esError = Boolean.TRUE;
            }
            else if(!response.getDocumento().isEmpty())
            {
                String result = response.getResultado().getResult().value().toString();
                String messageResult = response.getResultado().getMessage().toString();
                logger.log(ADFLogger.WARNING, "result: " + result);
                logger.log(ADFLogger.WARNING, "messageResult: " + messageResult);
                
                Integer cantidad =response.getDocumento().size();
                logger.log(ADFLogger.WARNING, "cantidad: " + cantidad);
                
                String document= response.getDocumento().toString();
                logger.log(ADFLogger.WARNING, "document: " + document);
                
                
                Base64 codec = new Base64();
                String documento64 = codec.encode(response.getDocumento().get(0).getContent());
                
                FacesContext facesContext = FacesContext.getCurrentInstance();
                ExtendedRenderKitService service = Service.getRenderKitService(facesContext, ExtendedRenderKitService.class);
                
                service.addScript(facesContext, "b64toBlob('data:application/pdf;base64,"+documento64+"');");
            }
            
                    
        } catch (Exception e) 
        {
            logger.log(ADFLogger.ERROR, "Error al generar el plan de Amortizacion " + e.getClass() + ":" + e.getMessage());
            esError = Boolean.TRUE;
            mensajeError = mensajeError.concat(e.getMessage());

        } finally {
            if (esError) 
            {
                JSFUtils.addFacesErrorMessage(mensajeError);
            }
        }
        logger.warning("Termina metodo generarReportePlanAmortizacion.");
    }
    
    private DesembolsoPTSOAP12BindingQSService initDesembolsoService() {
        DCIteratorBinding dcItteratorBindings = ADFUtils.findIterator("ConfiguracionVOIterator");
        ViewObject configuracionVoImpl = dcItteratorBindings.getViewObject();
        Row row = configuracionVoImpl.getRow(new Key(new Object[] { IWsdlLocation.DESEMBOLSO }));
        String wsdl = row == null ? null : (String) row.getAttribute("Valor");

        return IWsdlLocation.Service.getInstance(DesembolsoPTSOAP12BindingQSService.class, wsdl);
    }
    
    public StringWriter writeXMLRequest(Object request, Class requestClass) throws JAXBException {
        StringWriter writer = new StringWriter();
        JAXBContext context = JAXBContext.newInstance(requestClass);
        Marshaller m = context.createMarshaller();
        m.marshal(new JAXBElement(new QName(requestClass.getName()), requestClass, request), writer);
        return writer;
    }
    
    public void descargarDocumentoInteres(ActionEvent actionEvent) {
        // TODO
        logger.log(ADFLogger.WARNING, "--- Inside descargarDocumentoInteres");
    }

    public void descargarDocumentoCapital(ActionEvent actionEvent) {
        // TODO
        logger.log(ADFLogger.WARNING, "--- Inside descargarDocumentoCapital");
    }

    public UploadedFile getFile() {
        return _file;
    }

    public void setFile(UploadedFile file) {
        _file = file;
    }

    public OperationBinding executeOperation(String operation) {
        OperationBinding createParam = getBindingsCont().getOperationBinding(operation);
        return createParam;
    }

    public BindingContainer getBindingsCont() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }

    private BlobDomain newCalendarioBlodDomain(InputStream in) throws SQLException, IOException {
        logger.log(ADFLogger.WARNING, "Inside newCalendarioBlodDomain");
        BlobDomain loBlob = new BlobDomain();
        OutputStream out = loBlob.getBinaryOutputStream();
        writeInputStreamToWriter(in, out);
        in.close();
        out.close();
        return loBlob;
    }

    private static void writeInputStreamToWriter(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[8192];
        int charsRead = 0;
        while ((charsRead = in.read(buffer, 0, 8192)) != -1) {
            out.write(buffer, 0, charsRead);
        }
    }


    public void cambioEspecificacionFechas(ValueChangeEvent valueChangeEvent) {
        logger.warning("Inf, EspecificacionFechas Value : " + valueChangeEvent.getNewValue());
        CondicionesFinancierasBean condicionesFinancierasBean = null;
        condicionesFinancierasBean =
            (CondicionesFinancierasBean) JSFUtils.resolveExpression("#{pageFlowScope.CondicionFinancieraBean}");
        if (null != condicionesFinancierasBean) {
            if (null != valueChangeEvent.getNewValue()) {
                Integer especificacionFecha = (Integer) valueChangeEvent.getNewValue();
                switch (especificacionFecha) {
                case 1:
                    //fechas
                    condicionesFinancierasBean.setEspecificadoFechasVisible(true);
                    condicionesFinancierasBean.setEspecificadoPlazosVisible(false);

                    condicionesFinancierasBean.setMostrarSoloCalendarioSencillo(Boolean.FALSE);
                    
                    //Limpiamos los campos
                    ADFUtils.setBoundAttributeValue("FrecuenciaPlazo", 0);
                    ADFUtils.setBoundAttributeValue("FrecuenciaPeriodoGracia", 0);
                    
                    //Actualizar componentes
                    AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorDG1());
                    AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorSecPrincipal());
                    //AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorSecInteres());
                    AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorSecTasa());
                    AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorSecTasa2());
                    AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorSpreadVariable());
                    break;
                case 2:
                    //Plazos
                    condicionesFinancierasBean.setEspecificadoFechasVisible(false);
                    condicionesFinancierasBean.setEspecificadoPlazosVisible(true);

                    ADFUtils.setBoundAttributeValue("IdTcaTipoCalendario1", 2);
                    condicionesFinancierasBean.setMostrarSoloCalendarioSencillo(Boolean.TRUE);

                    condicionesFinancierasBean.setCalendarioSencilloVisible(Boolean.TRUE);
                    condicionesFinancierasBean.setCalendarioComplejoVisible(Boolean.FALSE);

                    //Limpiamos los campos
                    ADFUtils.setBoundAttributeValue("FechaVencimiento", null);
                    ADFUtils.setBoundAttributeValue("FechaPrimerPagoCapital", null);
                    ADFUtils.setBoundAttributeValue("FechaProximoPagoInteres", null);
                    ADFUtils.setBoundAttributeValue("FechaProximaRevisionTasaI", null);
                    
                    //Actualizar componentes
                    AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorDG1());
                    AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorSecPrincipal());
                    //AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorSecInteres());
                    AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorSecTasa());
                    AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorSecTasa2());
                    AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorSpreadVariable());
                    
                    
                    //Limpiamos los campos
                    
                    break;
                default:
                    break;
                }
                logger.warning("* ValueChangeEvent es " + valueChangeEvent.getNewValue());
                AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorDG1());
            } else {
                logger.warning("* ValueChangeEvent es null");
            }
        } else {
            logger.warning("*Instancia bean es null");
        }
    }


    public void cambioTipoTaza(ValueChangeEvent valueChangeEvent) {
        CondicionesFinancierasBean condicionesBean = null;
        condicionesBean =
            (CondicionesFinancierasBean) JSFUtils.resolveExpression("#{pageFlowScope.CondicionFinancieraBean}");
        if (null != condicionesBean) {
            if (null != valueChangeEvent.getNewValue()) {
                Integer tasa = (Integer) valueChangeEvent.getNewValue();
                ADFUtils.setBoundAttributeValue("IdTcaTipoTasaDesembolso", tasa);
                condicionesBean.setIdTipoTasaMora(tasa);
                logger.warning("Valor obtenido idTipoTasaMora en ValueChangeListener: " +
                               condicionesBean.getIdTipoTasaMora());

                JSFUtils.storeOnSession("IdTcaTipoTasaDesembolso", tasa);
                System.out.println("IdTcaTipoTasaDesembolso ---> " + tasa);

                switch (tasa) {
                case 1:
                    //Especial
                    condicionesBean.setTasaEspecialVisible(true);
                    condicionesBean.setTasaFijaVisible(false);
                    condicionesBean.setTasaVariableVisible(false);
                    condicionesBean.setSeccionMoraVisible(false);
                    AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorSecTasa());
                    AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorSecTasa2());
                    break;
                case 2:
                    //Fija
                    condicionesBean.setTasaFijaVisible(true);
                    condicionesBean.setTasaVariableVisible(false);
                    condicionesBean.setTasaEspecialVisible(false);
                    condicionesBean.setSeccionMoraVisible(true);
                    AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorSecTasa());
                    AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorSecTasa2());
                    break;
                case 3:
                    //Variable
                    condicionesBean.setTasaVariableVisible(true);
                    condicionesBean.setTasaFijaVisible(false);
                    condicionesBean.setTasaEspecialVisible(false);
                    condicionesBean.setSeccionMoraVisible(true);
                    AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorSecTasa());
                    AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorSecTasa2());
                    break;
                }
                logger.warning(" * ValueChangeEvent es " + valueChangeEvent.getNewValue());
                condicionesBean.cargarVistaProductoOperacion("cambioValorTaza");
            } else {
                logger.warning(" * ValueChangeEvent es null *");
            }
        } else {
            logger.warning("*Instancia bean es null");
        }
    }

    private String getStringFromBundle(String psKey) {
        ResourceBundle resourceBundle = BundleFactory.getBundle(BUNDLE);
        String txt = null;
        if (null != resourceBundle) {
            txt = resourceBundle.getString(psKey);
        }
        return txt;
    }

    public void codigoTasaReferenciaValueChange(ValueChangeEvent valueChangeEvent) {
        logger.warning("Inside codigoTasaReferenciaValueChange.");
        logger.warning("valueNewCodigoSpreadReferencia: " + valueChangeEvent.getNewValue());

        AttributeBinding bindingSpreadTasa;
        bindingSpreadTasa = ADFUtils.findControlBinding("SpreadI");
        AttributeBinding bindingTasaTotal;
        bindingTasaTotal = ADFUtils.findControlBinding("TasaTotalI");
        AttributeBinding bindingValorTasaReferenciaSpread;
        bindingValorTasaReferenciaSpread = ADFUtils.findControlBinding("ValorTasaReferenciaSpread");
        BigDecimal valorTasa = (BigDecimal) ADFUtils.getBoundAttributeValue("ValorTasaI");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        String descripcionTasa = null;
        String codigo = null;
        BigDecimal valorActual = null;

        descripcionTasa = (String) valueChangeEvent.getNewValue();

        if (descripcionTasa == null || descripcionTasa.equals("")) {
            return;
        }

        logger.warning("valor codigoTasaReferenciaSpread recibido: " + descripcionTasa);

        if (Character.isDigit(descripcionTasa.charAt(0))) {

            if (Character.isDigit(descripcionTasa.charAt(1))) {

                String subCadena = descripcionTasa.substring(2, 4);
                if (subCadena.equals(". ")) {
                    logger.warning("La cadena es parte de un listado saneando cadena... ");
                    descripcionTasa = descripcionTasa.substring(4);
                    logger.warning("Nuevo valor de la cadena: " + descripcionTasa);
                }

            } else {

                String subCadena = descripcionTasa.substring(1, 3);
                if (subCadena.equals(". ")) {
                    logger.warning("La cadena es parte de un listado saneando cadena... ");
                    descripcionTasa = descripcionTasa.substring(3);
                    logger.warning("Nuevo valor de la cadena: " + descripcionTasa);
                }
            }


        }


        operationBinding = bindings.getOperationBinding("obtenerDatosSpreadReferencia");
        operationBinding.getParamsMap().put("descripcionTasa", descripcionTasa);
        operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        } else if (operationBinding.getResult() != null) {
            Map<String, Object> resultado = new HashMap<String, Object>();
            resultado = (HashMap<String, Object>) operationBinding.getResult();

            valorActual = (BigDecimal) resultado.get("ValorActual");
            codigo = (String) resultado.get("Codigo");

            logger.warning("valorActual: " + valorActual);
            logger.warning("codigo: " + codigo);

            bindingSpreadTasa.setInputValue(valorActual);
            bindingValorTasaReferenciaSpread.setInputValue(valorActual);

            asignaBindingCodigoSpread(codigo);
        }

        if ((valorActual != null) && (valorTasa != null)) {
            bindingTasaTotal.setInputValue(calcularTasaTotal(valorActual, valorTasa));
        }
    }

    public void tasaReferenciaValueChange(ValueChangeEvent valueChangeEvent) {
        logger.warning("Inside tasaReferenciaValueChange.");
        logger.warning("valueNewCodigoTasaReferencia: " + valueChangeEvent.getNewValue());

        AttributeBinding bindingValorTasa;
        bindingValorTasa = ADFUtils.findControlBinding("ValorTasa");
        AttributeBinding bindingTasaTotal;
        bindingTasaTotal = ADFUtils.findControlBinding("TasaTotal");
        BigDecimal spreadTasa = (BigDecimal) ADFUtils.getBoundAttributeValue("SpreadTasa");
        BigDecimal valorTasaReferenciaSpread = (BigDecimal) ADFUtils.getBoundAttributeValue("ValorTasaReferenciaSpread");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        String descripcionTasa = null;
        String codigo = null;
        BigDecimal valorActual = null;
        CondicionesFinancierasBean condicionesFinancierasBean = null;
        condicionesFinancierasBean =
            (CondicionesFinancierasBean) JSFUtils.resolveExpression("#{pageFlowScope.CondicionFinancieraBean}");

        descripcionTasa = (String) valueChangeEvent.getNewValue();

        if (descripcionTasa == null || descripcionTasa.equals("")) {

            BigDecimal valorTasaSpread =
                (null == ADFUtils.getBoundAttributeValue("SpreadTasa")) ? null :
                (BigDecimal) ADFUtils.getBoundAttributeValue("SpreadTasa");

            if (valorTasaSpread != null) {
                ADFUtils.setBoundAttributeValue("TasaTotal", valorTasaSpread);
            } else {
                ADFUtils.setBoundAttributeValue("TasaTotal", null);
            }
        } else {
            if (null != condicionesFinancierasBean.getEsOnSuggestTasaReferencia() &&
                condicionesFinancierasBean.getEsOnSuggestTasaReferencia()) {
                logger.warning("Saneando numeracion a la cadena: " + descripcionTasa);
                descripcionTasa = descripcionTasa.substring(descripcionTasa.indexOf(" ") + 1);
                logger.warning("Cadena saneada: " + descripcionTasa);
                condicionesFinancierasBean.setEsOnSuggestTasaReferencia(null);
            }
        }
        Timestamp fechaEfectiva = getFechaEfectivaParaConFinan();

        if (fechaEfectiva == null) {
            logger.warning("La fecha efectiva es resuelta a null");
        }

        operationBinding = bindings.getOperationBinding("obtenerValorTasaReferencia");
        operationBinding.getParamsMap().put("descripcionTasa", descripcionTasa);
        operationBinding.getParamsMap().put("fechaEfectiva", fechaEfectiva);
        operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        } else if (operationBinding.getResult() != null) {
            Map<String, Object> resultado = new HashMap<String, Object>();
            resultado = (HashMap<String, Object>) operationBinding.getResult();

            valorActual = (BigDecimal) resultado.get("ValorActual");
            codigo = (String) resultado.get("Codigo");

            logger.warning("valorActual: " + valorActual);
            logger.warning("codigo: " + codigo);

            if (valorActual != null)
                ADFUtils.setBoundAttributeValue("TasaTotal", valorActual);

            bindingValorTasa.setInputValue(valorActual);
            asignaBindingCodigoTasa(codigo);
        }

        if (valorTasaReferenciaSpread != null) {
            if ((valorActual != null) && (valorTasaReferenciaSpread != null)) {
                bindingTasaTotal.setInputValue(calcularTasaTotal(valorActual, valorTasaReferenciaSpread));
            }
        } else {
            if ((valorActual != null) && (spreadTasa != null)) {
                bindingTasaTotal.setInputValue(calcularTasaTotal(valorActual, spreadTasa));
            }
        }
    }
    
    /*
     * Josue
     * */
    
    public void tasaReferenciaValueChangeI(ValueChangeEvent valueChangeEvent) {
        logger.warning("Inside tasaReferenciaValueChangeI.");
        logger.warning("valueNewCodigoTasaReferencia: " + valueChangeEvent.getNewValue());

        AttributeBinding bindingValorTasa;
        bindingValorTasa = ADFUtils.findControlBinding("ValorTasaI");
        AttributeBinding bindingTasaTotal;
        bindingTasaTotal = ADFUtils.findControlBinding("TasaTotalI");
        BigDecimal spreadTasa = (BigDecimal) ADFUtils.getBoundAttributeValue("SpreadI");
        BigDecimal valorTasaReferenciaSpread = (BigDecimal) ADFUtils.getBoundAttributeValue("ValorTasaReferenciaSpread");//Binding del componente Spread Variable
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        String descripcionTasa = null;
        String codigo = null;
        BigDecimal valorActual = null;
        CondicionesFinancierasBean condicionesFinancierasBean = null;
        condicionesFinancierasBean = (CondicionesFinancierasBean) JSFUtils.resolveExpression("#{pageFlowScope.CondicionFinancieraBean}");

        descripcionTasa = (String) valueChangeEvent.getNewValue();

        if (descripcionTasa == null || descripcionTasa.equals("")) {

            BigDecimal valorTasaSpread =
                (null == ADFUtils.getBoundAttributeValue("SpreadI")) ? null :
                (BigDecimal) ADFUtils.getBoundAttributeValue("SpreadI");

            if (valorTasaSpread != null) {
                ADFUtils.setBoundAttributeValue("TasaTotalI", valorTasaSpread);
            } else {
                ADFUtils.setBoundAttributeValue("TasaTotalI", null);
            }
        } else {
            if (null != condicionesFinancierasBean.getEsOnSuggestTasaReferencia() &&
                condicionesFinancierasBean.getEsOnSuggestTasaReferencia()) {
                logger.warning("Saneando numeracion a la cadena: " + descripcionTasa);
                descripcionTasa = descripcionTasa.substring(descripcionTasa.indexOf(" ") + 1);
                logger.warning("Cadena saneada: " + descripcionTasa);
                condicionesFinancierasBean.setEsOnSuggestTasaReferencia(null);
            }
        }
        Timestamp fechaEfectiva = getFechaEfectivaParaConFinan();

        if (fechaEfectiva == null) {
            logger.warning("La fecha efectiva es resuelta a null");
        }

        operationBinding = bindings.getOperationBinding("obtenerValorTasaReferencia");
        operationBinding.getParamsMap().put("descripcionTasa", descripcionTasa);
        operationBinding.getParamsMap().put("fechaEfectiva", fechaEfectiva);
        operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        } else if (operationBinding.getResult() != null) {
            Map<String, Object> resultado = new HashMap<String, Object>();
            resultado = (HashMap<String, Object>) operationBinding.getResult();

            valorActual = (BigDecimal) resultado.get("ValorActual");
            codigo = (String) resultado.get("Codigo");

            logger.warning("valorActual: " + valorActual);
            logger.warning("codigo: " + codigo);

            if (valorActual != null)
                ADFUtils.setBoundAttributeValue("TasaTotalI", valorActual);

            bindingValorTasa.setInputValue(valorActual);
            asignaBindingCodigoTasaI(codigo);
        }

        if (valorTasaReferenciaSpread != null) {
            if ((valorActual != null) && (valorTasaReferenciaSpread != null)) {
                bindingTasaTotal.setInputValue(calcularTasaTotal(valorActual, valorTasaReferenciaSpread));
            }
        } else {
            if ((valorActual != null) && (spreadTasa != null)) {
                bindingTasaTotal.setInputValue(calcularTasaTotal(valorActual, spreadTasa));
            }
        }
    }
    
    /*
     * */

    public void valorTasaReferenciaValueChange(ValueChangeEvent valueChangeEvent) {
        logger.warning("Inside valorTasaReferenciaValueChange.");
        logger.warning("valueNew: " + valueChangeEvent.getNewValue());

        AttributeBinding bindingTasaTotal;
        bindingTasaTotal = ADFUtils.findControlBinding("TasaTotal");
        BigDecimal valorTasa = (BigDecimal) valueChangeEvent.getNewValue();
        BigDecimal spreadTasa = (BigDecimal) ADFUtils.getBoundAttributeValue("SpreadTasa");
        BigDecimal valorTasaReferenciaSpread =
            (BigDecimal) ADFUtils.getBoundAttributeValue("ValorTasaReferenciaSpread");

        logger.warning("valorTasaReferenciaSpread: " + valorTasaReferenciaSpread);

        if (valorTasaReferenciaSpread != null) {
            bindingTasaTotal.setInputValue(calcularTasaTotal(valorTasa, valorTasaReferenciaSpread));
        }

        if ((valorTasaReferenciaSpread == null) && (spreadTasa != null)) {
            bindingTasaTotal.setInputValue(calcularTasaTotal(valorTasa, spreadTasa));
        }
    }

    /*
     * VK
     * */
    public void valorTasaReferenciaValueChangeI(ValueChangeEvent valueChangeEvent) {
        logger.warning("Inside valorTasaReferenciaValueChangeI.");
        logger.warning("valueNew: " + valueChangeEvent.getNewValue());

        AttributeBinding bindingTasaTotal;
        bindingTasaTotal = ADFUtils.findControlBinding("TasaTotalI");
        BigDecimal valorTasa = (BigDecimal) valueChangeEvent.getNewValue();
        BigDecimal spreadTasa = (BigDecimal) ADFUtils.getBoundAttributeValue("SpreadI");
        BigDecimal valorTasaReferenciaSpread =
            (BigDecimal) ADFUtils.getBoundAttributeValue("ValorTasaReferenciaSpread");

        logger.warning("valorTasaReferenciaSpread: " + valorTasaReferenciaSpread);

        if (valorTasaReferenciaSpread != null) {
            bindingTasaTotal.setInputValue(calcularTasaTotal(valorTasa, valorTasaReferenciaSpread));
        }

        if ((valorTasaReferenciaSpread == null) && (spreadTasa != null)) {
            bindingTasaTotal.setInputValue(calcularTasaTotal(valorTasa, spreadTasa));
        }
    }
    /*
     * */

    public void calcularNumCuotasPrincipalIdFrecuencia(ValueChangeEvent valueChangeEvent) {
        logger.warning("*Inf, Inicia el metodo calcularNumCuotasPrincipalByIdFrecuencia");

        Integer frecuencia =
            (valueChangeEvent.getNewValue() == null || valueChangeEvent.getNewValue().equals("")) ? null :
            Integer.parseInt(valueChangeEvent.getNewValue().toString());


        Integer especificacionFechas =
            (null == ADFUtils.getBoundAttributeValue("IdTcaEspecificacionFecha") ||
             ADFUtils.getBoundAttributeValue("IdTcaEspecificacionFecha").toString().equals("")) ? null :
            Integer.parseInt(ADFUtils.getBoundAttributeValue("IdTcaEspecificacionFecha").toString());

        if (especificacionFechas == 2) {
            logger.warning("Inf, calculando por plazos...");
            calcularCuotasSecPrincipalByFrecuenciaPorPlazo(frecuencia);
        } else {
            logger.warning("Inf, calculando por fechas...");
            calcularNumCuotasPrincipalByFrecuenciaFechas(frecuencia);
        }

        asigranDiaPagoExceptoVencimientoPorFecha();

        logger.warning("*Inf, Termina el metodo calcularNumCuotasPrincipalIdFrecuencia");
    }


    /**
     * Con este metodo al cambiar la frecuencia de capital recuperamos los valores necesarios
     * para calcular el numero de coutas de la seccion principal de condiciones fiancieras siempre y cuando
     * el campo de -Especificacion de fechas sea por FECHAS. y calculamos las cuotas mediante el metodo
     * calcularCuotasOperBiding
     *
     * @author Carlos O.
     * @since 23/01/2017
     */
    public void calcularNumCuotasPrincipalByFrecuenciaFechas(Integer frecuencia) {
        logger.warning("*Inf, Inicia el metodo calcularNumCuotasPrincipalByFrecuenciaFechas");

        java.sql.Timestamp fechaInicio =
            (null == ADFUtils.getBoundAttributeValue("FechaPrimerPagoCapital")) ? null :
            (java.sql.Timestamp) ADFUtils.getBoundAttributeValue("FechaPrimerPagoCapital");

        java.sql.Timestamp fechaFin =
            (null == ADFUtils.getBoundAttributeValue("FechaVencimiento")) ? null :
            (java.sql.Timestamp) ADFUtils.getBoundAttributeValue("FechaVencimiento");

        Integer IdTcaFrecuenciaPagoCapital =
            (null == ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPagoCapital") ||
             ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPagoCapital").toString().equals("")) ? null :
            (Integer) ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPagoCapital");

        try
        {
            calcularCuotasOperBiding(fechaInicio, fechaFin, frecuencia, IdTcaFrecuenciaPagoCapital, "principal");
        } catch (Exception ex) 
        {
            logger.warning("Inf, Ha ocurrido un error durante la ejecucion de calcularCuotasOperBiding: " + ex.getMessage());
        }

        asigranDiaPagoExceptoVencimientoPorFecha();

        logger.warning("*Inf, Termina el metodo calcularNumCuotasPrincipalByFrecuenciaFechas");
    }


    public void calcularCuotasSecPrincipalByFrecuenciaPorPlazo(Integer FrecuenciaPagoCapital) {
        logger.warning("*Inf, Inicia el metodo calcularCuotasPorPlazos");

        Integer FrecuenciaPlazo =
            (null == ADFUtils.getBoundAttributeValue("FrecuenciaPlazo") ||
             ADFUtils.getBoundAttributeValue("FrecuenciaPlazo").toString().equals("")) ? null :
            Integer.parseInt(ADFUtils.getBoundAttributeValue("FrecuenciaPlazo").toString());

        Integer IdTcaFrecuenciaPagoCapital =
            (null == ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPagoCapital") ||
             ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPagoCapital").toString().equals("")) ? null :
            Integer.parseInt(ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPagoCapital").toString());

        IdTcaFrecuenciaPlazo =
            (null == ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPlazo") ||
             ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPlazo").toString().equals("")) ? null :
            Integer.parseInt(ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPlazo").toString());

        /***    recuperando el periodo de gracia       ****/


        Integer frecuenciaPeriodoGracia =
            (null == ADFUtils.getBoundAttributeValue("FrecuenciaPeriodoGracia") ||
             ADFUtils.getBoundAttributeValue("FrecuenciaPeriodoGracia").toString().equals("")) ? null :
            Integer.parseInt(ADFUtils.getBoundAttributeValue("FrecuenciaPeriodoGracia").toString());

        Integer IdTcaFrecuenciaPeriodoGracia =
            (null == ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPeriodoGra") ||
             ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPeriodoGra").toString().equals("")) ? null :
            Integer.parseInt(ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPeriodoGra").toString());


        Integer tiempoDiasFrecuenciaPago = null;
        Integer tiempoDiasFrecuenciaPlazo = null;
        Integer tiempoDiasPeriodoGracia = null;
        Integer Cuotas = null;

        if (FrecuenciaPagoCapital != null && IdTcaFrecuenciaPagoCapital != null) {
            tiempoDiasFrecuenciaPago = obtenerTiempoEnDias(FrecuenciaPagoCapital, IdTcaFrecuenciaPagoCapital);
        } else {
            logger.warning("*Inf, parametros requeridos null");
            logger.warning("*Inf, FrecuenciaPagoCapital : " + FrecuenciaPagoCapital);
            logger.warning("*Inf, IdTcaFrecuenciaPagoCapital : " + IdTcaFrecuenciaPagoCapital);
        }

        if (FrecuenciaPlazo != null && IdTcaFrecuenciaPlazo != null) {
            tiempoDiasFrecuenciaPlazo = obtenerTiempoEnDias(FrecuenciaPlazo, IdTcaFrecuenciaPlazo);
        } else {
            logger.warning("*Inf, parametros requeridos null");
            logger.warning("*Inf, FrecuenciaPlazo : " + FrecuenciaPlazo);
            logger.warning("*Inf, IdTcaFrecuenciaPlazo : " + IdTcaFrecuenciaPlazo);
        }


        if (frecuenciaPeriodoGracia != null && IdTcaFrecuenciaPeriodoGracia != null) {
            tiempoDiasPeriodoGracia = obtenerTiempoEnDias(frecuenciaPeriodoGracia, IdTcaFrecuenciaPeriodoGracia);
        } else {
            logger.warning("*Inf, parametros periodo de gracia null");
            logger.warning("*Inf, frecuenciaPeriodoGracia : " + frecuenciaPeriodoGracia);
            logger.warning("*Inf, IdTcaFrecuenciaPeriodoGracia : " + IdTcaFrecuenciaPeriodoGracia);
        }


        if (tiempoDiasFrecuenciaPlazo != null && tiempoDiasPeriodoGracia != null) {
            logger.warning("*Inf, Restando periodo de Gracia a plazo...  resultado: " + tiempoDiasFrecuenciaPlazo);
            tiempoDiasFrecuenciaPlazo = (tiempoDiasFrecuenciaPlazo - tiempoDiasPeriodoGracia);
        }

        if (tiempoDiasFrecuenciaPago != null && tiempoDiasFrecuenciaPlazo != null) {
            if (tiempoDiasFrecuenciaPago.compareTo(tiempoDiasFrecuenciaPlazo) != 1) {
                Cuotas = tiempoDiasFrecuenciaPlazo / tiempoDiasFrecuenciaPago;
                ADFUtils.setBoundAttributeValue("NumeroCuotasPagoCapital", Cuotas);
                getFechasCuotasCapital(Cuotas, frecuenciaPeriodoGracia, IdTcaFrecuenciaPeriodoGracia, IdTcaFrecuenciaPagoCapital, FrecuenciaPagoCapital,FrecuenciaPlazo, IdTcaFrecuenciaPlazo);
            } else {
                ADFUtils.setBoundAttributeValue("NumeroCuotasPagoCapital", null);
                logger.warning("***Error, El numero frecuencia es menor que el plazo no se calculara las cuotas");
            }
            //AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorNumCuotas());
        } else {
            logger.warning("*Inf, parametros requeridos null");
            logger.warning("*Inf, tiempoDiasFrecuenciaPago : " + tiempoDiasFrecuenciaPago);
            logger.warning("*Inf, tiempoDiasFrecuenciaPlazo : " + tiempoDiasFrecuenciaPlazo);
        }
        logger.warning("Numero de Cuotas por plazo calculado : " + Cuotas);

        logger.warning("*Inf, Termina el metodo calcularCuotasSecPrincipalByFrecuenciaPorPlazo");
    }


    /**
     * toma el idTca de la frecuencia de capital para recuperar los valores necesarios
     * para calcular el numero de coutas de la seccion principal de condiciones fiancieras siempre y cuando
     * el campo de -Especificacion de fechas sea por FECHAS. y calculamos las cuotas mediante el metodo
     * calcularCuotasOperBiding
     *
     * @author Carlos O.
     * @since 30/03/2017
     */
    public void calcularNumCuotasPrincipalByIdTcaFrecuencia(Integer IdTcaFrecuenciaPagoCapital) {
        logger.warning("*Inf, Inicia el metodo calcularNumCuotasPrincipalByIdTcaFrecuencia");
        CondicionesFinancierasBean condicionesFinancierasBean = null;
        condicionesFinancierasBean =
            (CondicionesFinancierasBean) JSFUtils.resolveExpression("#{pageFlowScope.CondicionFinancieraBean}");


        Integer frecuencia =
            (null == ADFUtils.getBoundAttributeValue("FrecuenciaPagoCapital")) ? null :
            (Integer) ADFUtils.getBoundAttributeValue("FrecuenciaPagoCapital");

        java.sql.Timestamp fechaInicio =
            (null == ADFUtils.getBoundAttributeValue("FechaPrimerPagoCapital")) ? null :
            (java.sql.Timestamp) ADFUtils.getBoundAttributeValue("FechaPrimerPagoCapital");

        java.sql.Timestamp fechaFin =
            (null == ADFUtils.getBoundAttributeValue("FechaVencimiento")) ? null :
            (java.sql.Timestamp) ADFUtils.getBoundAttributeValue("FechaVencimiento");


        if (IdTcaFrecuenciaPagoCapital != null) {
            if (IdTcaFrecuenciaPagoCapital.compareTo(tcaEstadoAlVencimiento) == 0) {
                logger.warning("*Inf, numero de coutas alVencimiento es 1");

                if (fechaInicio != null && fechaFin != null) {
                    ADFUtils.setBoundAttributeValue("NumeroCuotasPagoCapital", 1);
                } else {
                    logger.warning("*Inf, fecha de primerPago y vencimiento son requeridas no se calculara el numero de cuotas");
                    ADFUtils.setBoundAttributeValue("NumeroCuotasPagoCapital", null);
                }
                condicionesFinancierasBean.setFrecInteresVisible(Boolean.FALSE);
            } else {
                condicionesFinancierasBean.setFrecInteresVisible(Boolean.TRUE);
                try
                {
                    calcularCuotasOperBiding(fechaInicio, fechaFin, frecuencia, IdTcaFrecuenciaPagoCapital, "principal");
                }
                catch(Exception ex)
                {
                    logger.warning("Inf, Ha ocurrido un error durante la ejecucion de calcularCuotasOperBiding: " + ex.getMessage()); 
                }
            }

        } else {
            logger.warning("*Inf, IdTcaFrecuenciaPagoCapital es requerido no se calcularan las cuotas");
            ADFUtils.setBoundAttributeValue("NumeroCuotasPagoCapital", null);
        }
        //AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorNumCuotas());

        asigranDiaPagoExceptoVencimientoPorFecha();

        logger.warning("*Inf, Termina el metodo calcularNumCuotasPrincipalByIdFrecuencia");
    }


    /**
     * Con este metodo al cambiar la Fecha del Primer Pago de Capital recuperamos los valores necesarios
     * para calcular el numero de coutas de la seccion principal de condiciones fiancieras siempre y cuando
     * el campo de -Especificacion de fechas sea por FECHAS. y calculamos las cuotas mediante el metodo
     * calcularCuotasOperBiding
     *
     * @author Carlos O.
     * @since 23/01/2017
     */
    public void calcularNumCuotasPrincipalByFechaIni(ValueChangeEvent valueChangeEvent) {
        logger.warning("Inf, Inicia el metodo calcularNumCuotasPrincipalByFechaIni");

        java.sql.Timestamp fechaInicio = (java.sql.Timestamp) valueChangeEvent.getNewValue();
        logger.warning("*Inf, fecha inicio: " + fechaInicio);
        if (validarFechaSolicitudFchaPagoCapital(fechaInicio)) {
            java.sql.Timestamp fechaFin =
                (null == ADFUtils.getBoundAttributeValue("FechaVencimiento")) ? null :
                (java.sql.Timestamp) ADFUtils.getBoundAttributeValue("FechaVencimiento");

            Integer frecuencia =
                (null == ADFUtils.getBoundAttributeValue("FrecuenciaPagoCapital")) ? null :
                (Integer) ADFUtils.getBoundAttributeValue("FrecuenciaPagoCapital");

            Integer IdTcaFrecuenciaPagoCapital =
                (null == ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPagoCapital") ||
                 ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPagoCapital").toString().equals("")) ? null :
                (Integer) ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPagoCapital");


            if (IdTcaFrecuenciaPagoCapital == null) {

                logger.warning("*Inf, IdTcaFrecuenciaPagoCapital es resuelto a NULL no se calcularan coutas");
                ADFUtils.setBoundAttributeValue("NumeroCuotasPagoCapital", null);
                //AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorNumCuotas());

            } else if (IdTcaFrecuenciaPagoCapital.compareTo(tcaEstadoAlVencimiento) == 0) {
                logger.warning("*Inf, numero de coutas alVencimiento es 1");

                if (fechaInicio != null && fechaFin != null) {
                    ADFUtils.setBoundAttributeValue("NumeroCuotasPagoCapital", 1);
                    //AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorNumCuotas());
                } else {
                    logger.warning("*Inf, fecha de primerPago y vencimiento son requeridas no se calculara el numero de cuotas");
                    ADFUtils.setBoundAttributeValue("NumeroCuotasPagoCapital", null);
                    //AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorNumCuotas());
                }

            } else {
                logger.warning("*Inf, fecha inicio, HEREE: " + fechaInicio);
                try
                {
                    calcularCuotasOperBiding(fechaInicio, fechaFin, frecuencia, IdTcaFrecuenciaPagoCapital, "principal");
                }
                catch(Exception ex)
                {
                    logger.warning("Inf, Ha ocurrido un error durante la ejecucion de calcularCuotasOperBiding: " + ex.getMessage()); 
                }
            }


            ADFUtils.setBoundAttributeValue("FechaPrimerPagoCapital", fechaInicio);
            asigranDiaPagoExceptoVencimientoPorFecha();

        } else {
            logger.warning("La fecha del primer pago de capital no puede ser menor al de la solicitud");
        }
        logger.warning("Inf, Termina el metodo calcularNumCuotasPrincipalByFechaIni");
    }

    /**
     * Con este metodo al cambiar la "fecha de vencimiento" recuperamos los valores necesarios para calcular el numero de coutas de la
     * seccion principal de condiciones fiancieras siempre y cuando el campo de -Especificacion de fechas
     * sea por FECHAS. y calculamos las cuotas mediante el metodo calcularCuotasOperBiding
     *
     * @author Carlos O.
     * @since 20/01/2017
     */
    public void calcularNumCuotasPrincipalByFechaFin(ValueChangeEvent valueChangeEvent) {
        logger.warning("Inf, Inicia el metodo calcularNumCuotasPrincipalByFechaFin");

        java.sql.Timestamp fechaFin = (java.sql.Timestamp) valueChangeEvent.getNewValue();

        java.sql.Timestamp fechaInicio =
            (null == ADFUtils.getBoundAttributeValue("FechaPrimerPagoCapital")) ? null :
            (java.sql.Timestamp) ADFUtils.getBoundAttributeValue("FechaPrimerPagoCapital");

        Integer frecuencia =
            (null == ADFUtils.getBoundAttributeValue("FrecuenciaPagoCapital")) ? null :
            (Integer) ADFUtils.getBoundAttributeValue("FrecuenciaPagoCapital");

        Integer IdTcaFrecuenciaPagoCapital =
            (null == ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPagoCapital") ||
             ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPagoCapital").equals("")) ? null :
            Integer.parseInt(ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPagoCapital").toString());


        if (IdTcaFrecuenciaPagoCapital == null) {
            logger.warning("*Inf, IdTcaFrecuenciaPagoCapital resuelto a NULL no se calcularan coutas");
            ADFUtils.setBoundAttributeValue("NumeroCuotasPagoCapital", null);
            //AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorNumCuotas());

        } else if (IdTcaFrecuenciaPagoCapital.compareTo(tcaEstadoAlVencimiento) == 0) {
            logger.warning("*Inf, numero de coutas alVencimiento es 1");

            if (fechaInicio != null && fechaFin != null) {
                ADFUtils.setBoundAttributeValue("NumeroCuotasPagoCapital", 1);
                //AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorNumCuotas());
            } else {
                logger.warning("*Inf, fecha de primerPago y vencimiento son requeridas no se calculara el numero de cuotas");
                ADFUtils.setBoundAttributeValue("NumeroCuotasPagoCapital", null);
                //AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorNumCuotas());
            }

        } else {
            try
            {
                calcularCuotasOperBiding(fechaInicio, fechaFin, frecuencia, IdTcaFrecuenciaPagoCapital, "principal");
            }
            catch(Exception ex)
            {
                logger.warning("Inf, Ha ocurrido un error durante la ejecucion de calcularCuotasOperBiding: " + ex.getMessage()); 
            }
        }
        
        ADFUtils.setBoundAttributeValue("FechaVencimiento", fechaFin);

        calcularNumCuotasInteres(null);
        calcularNumRevisionesTaza(null);
        calcularNumRevisionesSpread(null);
        calcularCuotasSecTazaPorPlazoI(null);
        
        asigranDiaPagoExceptoVencimientoPorFecha();
        
        
        //Se actualizan los paneles
        AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorAllSpreadVariableNew());
        AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorAllInteresNew());
        AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorAllCapitalNew());

        logger.warning("Inf, Termina el metodo calcularNumCuotasPrincipalByFechaFin");
    }


    /**
     * Con este metodo al cambiar la FECHA DEL PROXIMO PAGO DE INTERES recuperamos los valores necesarios
     * para calcular el numero de coutas de la seccion principal de condiciones fiancieras siempre y cuando
     * el campo de -Especificacion de fechas sea por FECHAS. y calculamos las cuotas mediante el metodo
     * calcularCuotasOperBiding
     *
     * @author Carlos O.
     * @since 23/01/2017
     */
    public void calcularNumCuotasInteres(ValueChangeEvent valueChangeEvent) {
        logger.warning("Inf, Inicia el metodo calcularNumCuotasInteres");
        java.sql.Timestamp fechaInicio = null;

        if (valueChangeEvent != null) {
            fechaInicio = (java.sql.Timestamp) valueChangeEvent.getNewValue();
        } else {
            fechaInicio =
                (null == ADFUtils.getBoundAttributeValue("FechaProximoPagoInteres")) ? null :
                (java.sql.Timestamp) ADFUtils.getBoundAttributeValue("FechaProximoPagoInteres");
        }

        java.sql.Timestamp fechaFin =
            (null == ADFUtils.getBoundAttributeValue("FechaVencimiento")) ? null :
            (java.sql.Timestamp) ADFUtils.getBoundAttributeValue("FechaVencimiento");

        Integer FrecuenciaPagoInteres =
            (null == ADFUtils.getBoundAttributeValue("FrecuenciaPagoInteres")) ? null :
            (Integer) ADFUtils.getBoundAttributeValue("FrecuenciaPagoInteres");

        Integer IdTcaFrecuenciaPagoInteres =
            (null == ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPagoInteres") ||
             ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPagoInteres").equals("")) ? null :
            Integer.parseInt(ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPagoInteres").toString());


        if (IdTcaFrecuenciaPagoInteres != null) {
            if (IdTcaFrecuenciaPagoInteres.compareTo(tcaEstadoAlVencimiento) == 0) {
                logger.warning("*Inf, numero de coutas alVencimiento es 1");

                if (fechaInicio != null && fechaFin != null) {
                    ADFUtils.setBoundAttributeValue("NumeroCuotasPagoInteres", 1);
                    ADFUtils.setBoundAttributeValue("FrecuenciaPagoInteres", 1);
                    //AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorNumCuotasInteres());
                } else {
                    logger.warning("*Inf, fecha de primerPago y vencimiento son requeridas no se calculara el numero de cuotas");
                    ADFUtils.setBoundAttributeValue("NumeroCuotasPagoInteres", null);
                    //AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorNumCuotasInteres());
                }

            } else {
                try
                {
                    calcularCuotasOperBiding(fechaInicio, fechaFin, FrecuenciaPagoInteres, IdTcaFrecuenciaPagoInteres,
                                             "interes");
                }
                catch(Exception ex)
                {
                    logger.warning("Inf, Ha ocurrido un error durante la ejecucion de calcularCuotasOperBiding: " + ex.getMessage()); 
                }
            }
        } else {
            logger.warning("*Inf, IdTcaFrecuenciaPagoInteres es resuelto a Null no se calcularan coutas ");
            ADFUtils.setBoundAttributeValue("NumeroCuotasPagoInteres", null);
            //AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorNumCuotasInteres());
        }


        logger.warning("Inf, Termina el metodo calcularNumCuotasInteres");
    }

    /**
     * Con este metodo al cambiar la IdTcaFrecuenciaPagoInteres recuperamos los valores necesarios
     * para calcular el numero de coutas de la seccion principal de condiciones fiancieras siempre y cuando
     * el campo de -Especificacion de fechas sea por FECHAS. y calculamos las cuotas mediante el metodo
     * calcularCuotasOperBiding
     *
     * @author Carlos O.
     * @since 30/03/2017
     */
    public void calcularNumCuotasInteresByIdTcaFrecuencia(Integer IdTcaFrecuenciaPagoInteres) {
        logger.warning("Inf, Inicia el metodo calcularNumCuotasInteres");
        java.sql.Timestamp fechaInicio = null;


        fechaInicio =
            (null == ADFUtils.getBoundAttributeValue("FechaProximoPagoInteres")) ? null :
            (java.sql.Timestamp) ADFUtils.getBoundAttributeValue("FechaProximoPagoInteres");


        java.sql.Timestamp fechaFin =
            (null == ADFUtils.getBoundAttributeValue("FechaVencimiento")) ? null :
            (java.sql.Timestamp) ADFUtils.getBoundAttributeValue("FechaVencimiento");

        Integer FrecuenciaPagoInteres =
            (null == ADFUtils.getBoundAttributeValue("FrecuenciaPagoInteres")) ? null :
            (Integer) ADFUtils.getBoundAttributeValue("FrecuenciaPagoInteres");


        if (IdTcaFrecuenciaPagoInteres != null) {
            if (IdTcaFrecuenciaPagoInteres.compareTo(tcaEstadoAlVencimiento) == 0) {
                logger.warning("*Inf, numero de coutas alVencimiento es 1");

                if (fechaInicio != null && fechaFin != null) {
                    ADFUtils.setBoundAttributeValue("NumeroCuotasPagoInteres", 1);
                    ADFUtils.setBoundAttributeValue("FrecuenciaPagoInteres", 1);
                    //AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorNumCuotasInteres());
                } else {
                    logger.warning("*Inf, fecha de primerPago y vencimiento son requeridas no se calculara el numero de cuotas");
                    ADFUtils.setBoundAttributeValue("NumeroCuotasPagoInteres", null);
                    //AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorNumCuotasInteres());
                }

            } else {
                CondicionesFinancierasBean condicionesFinancierasBean = null;
                condicionesFinancierasBean =
                    (CondicionesFinancierasBean) JSFUtils.resolveExpression("#{pageFlowScope.CondicionFinancieraBean}");
                condicionesFinancierasBean.setFrecInteresVisible(Boolean.TRUE);
                //AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorNumCuotasInteres());
                try
                {
                    calcularCuotasOperBiding(fechaInicio, fechaFin, FrecuenciaPagoInteres, IdTcaFrecuenciaPagoInteres,
                                             "interes");
                }
                catch(Exception ex)
                {
                    logger.warning("Inf, Ha ocurrido un error durante la ejecucion de calcularCuotasOperBiding: " + ex.getMessage()); 
                }
            }
        } else {
            logger.warning("*Inf, IdTcaFrecuenciaPagoInteres es resuelto a Null no se calcularan coutas ");
            ADFUtils.setBoundAttributeValue("NumeroCuotasPagoInteres", null);
            //AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorNumCuotasInteres());
        }


        logger.warning("Inf, Termina el metodo calcularNumCuotasInteres");
    }


    public void calcularNumCuotasInteresByFrecuencia(ValueChangeEvent valueChangeEvent) {
        logger.warning("Inf, Inicia el metodo calcularNumCuotasInteresByFrecuencia");

        Integer FrecuenciaPagoInteres =
            (valueChangeEvent.getNewValue() == null || valueChangeEvent.getNewValue().equals("")) ? null :
            Integer.parseInt(valueChangeEvent.getNewValue().toString());

        Integer especificacionFechas =
            (null == ADFUtils.getBoundAttributeValue("IdTcaEspecificacionFecha") ||
             ADFUtils.getBoundAttributeValue("IdTcaEspecificacionFecha").toString().equals("")) ? null :
            Integer.parseInt(ADFUtils.getBoundAttributeValue("IdTcaEspecificacionFecha").toString());

        if (especificacionFechas == 2) {
            logger.warning("Inf, calculando por plazos...");
            calcularCuotasSecInteresByFrecuenciaPlazos(FrecuenciaPagoInteres);
        } else {
            logger.warning("Inf, calculando por fechas...");
            calcularNumCuotasInteresByFrecuenciaFechas(FrecuenciaPagoInteres);
        }


        logger.warning("Inf, Termina el metodo calcularNumCuotasInteresByFrecuencia");
    }


    public void calcularNumCuotasInteresByFrecuenciaFechas(Integer FrecuenciaPagoInteres) {
        logger.warning("Inf, Inicia el metodo calcularNumCuotasInteresByFrecuenciaFechas");


        java.sql.Timestamp fechaInicio =
            (null == ADFUtils.getBoundAttributeValue("FechaProximoPagoInteres")) ? null :
            (java.sql.Timestamp) ADFUtils.getBoundAttributeValue("FechaProximoPagoInteres");


        java.sql.Timestamp fechaFin =
            (null == ADFUtils.getBoundAttributeValue("FechaVencimiento")) ? null :
            (java.sql.Timestamp) ADFUtils.getBoundAttributeValue("FechaVencimiento");

        Integer IdTcaFrecuenciaPagoInteres =
            (null == ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPagoInteres") ||
             ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPagoInteres").equals("")) ? null :
            Integer.parseInt(ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPagoInteres").toString());


        if (IdTcaFrecuenciaPagoInteres != null) {
            if (IdTcaFrecuenciaPagoInteres.compareTo(tcaEstadoAlVencimiento) == 0) {
                logger.warning("*Inf, numero de coutas alVencimiento es 1");

                if (fechaInicio != null && fechaFin != null) {
                    ADFUtils.setBoundAttributeValue("NumeroCuotasPagoInteres", 1);
                    ADFUtils.setBoundAttributeValue("FrecuenciaPagoInteres", 1);
                    //AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorNumCuotasInteres());
                } else {
                    logger.warning("*Inf, fecha de primerPago y vencimiento son requeridas no se calculara el numero de cuotas");
                    ADFUtils.setBoundAttributeValue("NumeroCuotasPagoInteres", null);
                    //AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorNumCuotasInteres());
                }

            } else {
                try
                {
                    calcularCuotasOperBiding(fechaInicio, fechaFin, FrecuenciaPagoInteres, IdTcaFrecuenciaPagoInteres,
                                             "interes");
                }
                catch(Exception ex)
                {
                    logger.warning("Inf, Ha ocurrido un error durante la ejecucion de calcularCuotasOperBiding: " + ex.getMessage()); 
                }
            }
        } else {
            logger.warning("*Inf, IdTcaFrecuenciaPagoInteres es resuelto a NULL no se calculara el numero de cuotas");
            ADFUtils.setBoundAttributeValue("NumeroCuotasPagoInteres", null);
            //AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorNumCuotasInteres());
        }

        logger.warning("Inf, Termina el metodo calcularNumCuotasInteresByFrecuenciaFechas");
    }


    public void calcularCuotasSecInteresByFrecuenciaPlazos(Integer FrecuenciaPagoInteres) {
        logger.warning("*Inf, Inicia el metodo calcularCuotasSecInteresByFrecuenciaPlazos frecuencia: " +
                       FrecuenciaPagoInteres);


        Integer IdTcaFrecuenciaPlazo =
            (null == ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPlazo") ||
             ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPlazo").toString().equals("")) ? null :
            Integer.parseInt(ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPlazo").toString());

        Integer IdTcaFrecuenciaPagoInteres =
            (null == ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPagoInteres") ||
             ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPagoInteres").toString().equals("")) ? null :
            Integer.parseInt(ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPagoInteres").toString());

        Integer FrecuenciaPlazo =
            (null == ADFUtils.getBoundAttributeValue("FrecuenciaPlazo") ||
             ADFUtils.getBoundAttributeValue("FrecuenciaPlazo").toString().equals("")) ? null :
            Integer.parseInt(ADFUtils.getBoundAttributeValue("FrecuenciaPlazo").toString());


        if (IdTcaFrecuenciaPlazo == null || IdTcaFrecuenciaPagoInteres == null || FrecuenciaPlazo == null ||
            FrecuenciaPagoInteres == null) {
            ADFUtils.setBoundAttributeValue("NumeroCuotasPagoInteres", null);
            //AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorNumCuotasInteres());
            return;
        }

        if (IdTcaFrecuenciaPagoInteres != null) {
            if (IdTcaFrecuenciaPagoInteres.compareTo(tcaEstadoAlVencimiento) == 0) {

                if (FrecuenciaPlazo != null && IdTcaFrecuenciaPlazo != null) {
                    ADFUtils.setBoundAttributeValue("NumeroCuotasPagoInteres", 1);
                    ADFUtils.setBoundAttributeValue("FrecuenciaPagoInteres", 1);
                    //AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorNumCuotasInteres());
                } else {
                    logger.warning("*Inf, fecha de primerPago y vencimiento son requeridas no se calculara el numero de cuotas");
                    ADFUtils.setBoundAttributeValue("NumeroCuotasPagoInteres", null);
                    //AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorNumCuotasInteres());
                }
                return;
            }
        } else {
            logger.warning("*Inf, IdTcaFrecuenciaPagoInteres es resuelto a NULL no se calcularan cuotas");
            return;
        }


        Integer tiempoDiasFrecuencia = null;
        Integer tiempoDiasFrecuenciaPlazo = null;
        Integer Cuotas = null;

        if (FrecuenciaPagoInteres != null && IdTcaFrecuenciaPagoInteres != null) {
            tiempoDiasFrecuencia = obtenerTiempoEnDias(FrecuenciaPagoInteres, IdTcaFrecuenciaPagoInteres);
        } else {
            logger.warning("*Inf, parametros requeridos null");
            logger.warning("*Inf, FrecuenciaPagoInteres : " + FrecuenciaPagoInteres);
            logger.warning("*Inf, IdTcaFrecuenciaPagoInteres : " + IdTcaFrecuenciaPagoInteres);
        }

        if (FrecuenciaPlazo != null && IdTcaFrecuenciaPlazo != null) {
            tiempoDiasFrecuenciaPlazo = obtenerTiempoEnDias(FrecuenciaPlazo, IdTcaFrecuenciaPlazo);
        } else {
            logger.warning("*Inf, parametros requeridos null");
            logger.warning("*Inf, FrecuenciaPlazo : " + FrecuenciaPlazo);
            logger.warning("*Inf, IdTcaFrecuenciaPlazo : " + IdTcaFrecuenciaPlazo);
        }

        if (tiempoDiasFrecuencia != null && tiempoDiasFrecuenciaPlazo != null) {
            if (tiempoDiasFrecuencia.compareTo(tiempoDiasFrecuenciaPlazo) != 1) {
                if (tiempoDiasFrecuencia.compareTo(0) == 1 && tiempoDiasFrecuenciaPlazo.compareTo(0) == 1) {
                    Cuotas = tiempoDiasFrecuenciaPlazo / tiempoDiasFrecuencia;
                    ADFUtils.setBoundAttributeValue("NumeroCuotasPagoInteres", Cuotas);
                } else {
                    logger.warning("***ERROR, TiempoDiasFrecuencia o tiempoDiasFrecuenciaPlazo son cero (0). No se calculara cuota.");
                }
            } else {
                ADFUtils.setBoundAttributeValue("NumeroCuotasPagoInteres", null);
                logger.warning("***Error, El numero frecuencia es menor que el plazo no se calculara las cuotas");
            }
            //AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorNumCuotasInteres());
        } else {
            logger.warning("*Inf, parametros requeridos null");
            logger.warning("*Inf, tiempoDiasFrecuenciaInteres : " + tiempoDiasFrecuencia);
            logger.warning("*Inf, tiempoDiasFrecuenciaPlazo : " + tiempoDiasFrecuenciaPlazo);
        }
        logger.warning("Numero de Cuotas por plazo calculado : " + Cuotas);

        logger.warning("*Inf, Termina el metodo calcularCuotasSecInteresByFrecuenciaPlazos");
    }


    /**
     * Con este metodo al cambiar la FECHA DEL PROXIMO PAGO DE INTERES recuperamos los valores necesarios
     * para calcular el numero de coutas de la seccion principal de condiciones fiancieras siempre y cuando
     * el campo de -Especificacion de fechas sea por FECHAS. y calculamos las cuotas mediante el metodo
     * calcularCuotasOperBiding
     *
     * @author Carlos O.
     * @since 23/01/2017
     */
    public void calcularNumRevisionesTaza(ValueChangeEvent valueChangeEvent) {
        logger.warning("Inf, Inicia el metodo calcularNumRevisionesTaza");
        java.sql.Timestamp fechaInicio = null;

        if (valueChangeEvent != null) {
            fechaInicio = (java.sql.Timestamp) valueChangeEvent.getNewValue();
        } else {
            fechaInicio =
                (null == ADFUtils.getBoundAttributeValue("FechaProximaRevisionTasa")) ? null :
                (java.sql.Timestamp) ADFUtils.getBoundAttributeValue("FechaProximaRevisionTasa");
        }

        java.sql.Timestamp fechaFin =
            (null == ADFUtils.getBoundAttributeValue("FechaVencimiento")) ? null :
            (java.sql.Timestamp) ADFUtils.getBoundAttributeValue("FechaVencimiento");

        Integer FrecuenciaRevisionTasa =
            (null == ADFUtils.getBoundAttributeValue("FrecuenciaRevisionTasa")) ? null :
            (Integer) ADFUtils.getBoundAttributeValue("FrecuenciaRevisionTasa");

        Integer IdTcaFrecuenciaRevTasa =
            (null == ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaRevTasa") ||
             ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaRevTasa").equals("")) ? null :
            Integer.parseInt(ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaRevTasa").toString());

        try
        {
            calcularCuotasOperBiding(fechaInicio, fechaFin, FrecuenciaRevisionTasa, IdTcaFrecuenciaRevTasa, "taza");
        }
        catch(Exception ex)
        {
            logger.warning("Inf, Ha ocurrido un error durante la ejecucion de calcularCuotasOperBiding: " + ex.getMessage()); 
        }


        logger.warning("Inf, Termina el metodo calcularNumRevisionesTaza");
    }

    /**
     * Con este metodo al cambiar la FECHA DEL PROXIMO PAGO DE INTERES recuperamos los valores necesarios
     * para calcular el numero de coutas de la seccion principal de condiciones fiancieras siempre y cuando
     * el campo de -Especificacion de fechas sea por FECHAS. y calculamos las cuotas mediante el metodo
     * calcularCuotasOperBiding
     *
     * @author Josue O.
     * @since 23/01/2017
     */
    public void calcularNumRevisionesTazaI(ValueChangeEvent valueChangeEvent) {
        logger.warning("Inf, Inicia el metodo calcularNumRevisionesTazaI");
        java.sql.Timestamp fechaInicio = null;

        if (valueChangeEvent != null) {
            fechaInicio = (java.sql.Timestamp) valueChangeEvent.getNewValue();
        } else {
            fechaInicio =
                (null == ADFUtils.getBoundAttributeValue("FechaProximaRevisionTasaI")) ? null :
                (java.sql.Timestamp) ADFUtils.getBoundAttributeValue("FechaProximaRevisionTasa");
        }

        java.sql.Timestamp fechaFin =
            (null == ADFUtils.getBoundAttributeValue("FechaVencimiento")) ? null :
            (java.sql.Timestamp) ADFUtils.getBoundAttributeValue("FechaVencimiento");

        Integer FrecuenciaRevisionTasa =
            (null == ADFUtils.getBoundAttributeValue("FrecuenciaRevisionTasaI")) ? null :
            (Integer) ADFUtils.getBoundAttributeValue("FrecuenciaRevisionTasaI");

        Integer IdTcaFrecuenciaRevTasa =
            (null == ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaRevTasaI") ||
             ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaRevTasaI").equals("")) ? null :
            Integer.parseInt(ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaRevTasaI").toString());

        try
        {
            calcularCuotasOperBiding(fechaInicio, fechaFin, FrecuenciaRevisionTasa, IdTcaFrecuenciaRevTasa, "tazaI");
        }
        catch(Exception ex)
        {
            logger.warning("Inf, Ha ocurrido un error durante la ejecucion de calcularCuotasOperBiding: " + ex.getMessage()); 
        }


        logger.warning("Inf, Termina el metodo calcularNumRevisionesTaza");
    }

    public void calcularNumRevisionesTazaByIdTcaFrecuenciaRev(Integer IdTcaFrecuenciaRevTasa) {
        logger.warning("Inf, Inicia el metodo calcularNumRevisionesTazaByIdTcaFrecuenciaRevTasa");

        java.sql.Timestamp fechaInicio =
            (null == ADFUtils.getBoundAttributeValue("FechaProximaRevisionTasa")) ? null :
            (java.sql.Timestamp) ADFUtils.getBoundAttributeValue("FechaProximaRevisionTasa");


        java.sql.Timestamp fechaFin =
            (null == ADFUtils.getBoundAttributeValue("FechaVencimiento")) ? null :
            (java.sql.Timestamp) ADFUtils.getBoundAttributeValue("FechaVencimiento");

        Integer FrecuenciaRevisionTasa =
            (null == ADFUtils.getBoundAttributeValue("FrecuenciaRevisionTasa")) ? null :
            (Integer) ADFUtils.getBoundAttributeValue("FrecuenciaRevisionTasa");

        try
        {
            calcularCuotasOperBiding(fechaInicio, fechaFin, FrecuenciaRevisionTasa, IdTcaFrecuenciaRevTasa, "taza");
        }
        catch(Exception ex)
        {
            logger.warning("Inf, Ha ocurrido un error durante la ejecucion de calcularCuotasOperBiding: " + ex.getMessage()); 
        }

        logger.warning("Inf, Termina el metodo calcularNumRevisionesTazaByIdTcaFrecuenciaRevTasa");
    }
    
    /*
     * Josue
     * */
    
    public void calcularNumRevisionesTazaByIdTcaFrecuenciaRevI(Integer IdTcaFrecuenciaRevTasa) {
        logger.warning("Inf, Inicia el metodo calcularNumRevisionesTazaByIdTcaFrecuenciaRevTasa");

        java.sql.Timestamp fechaInicio =
            (null == ADFUtils.getBoundAttributeValue("FechaProximaRevisionTasaI")) ? null :
            (java.sql.Timestamp) ADFUtils.getBoundAttributeValue("FechaProximaRevisionTasaI");


        java.sql.Timestamp fechaFin =
            (null == ADFUtils.getBoundAttributeValue("FechaVencimiento")) ? null :
            (java.sql.Timestamp) ADFUtils.getBoundAttributeValue("FechaVencimiento");

        Integer FrecuenciaRevisionTasa =
            (null == ADFUtils.getBoundAttributeValue("FrecuenciaRevisionTasaI")) ? null :
            (Integer) ADFUtils.getBoundAttributeValue("FrecuenciaRevisionTasaI");

        try
        {
            calcularCuotasOperBiding(fechaInicio, fechaFin, FrecuenciaRevisionTasa, IdTcaFrecuenciaRevTasa, "tazaI");
        }
        catch(Exception ex)
        {
            logger.warning("Inf, Ha ocurrido un error durante la ejecucion de calcularCuotasOperBiding: " + ex.getMessage()); 
        }

        logger.warning("Inf, Termina el metodo calcularNumRevisionesTazaByIdTcaFrecuenciaRevTasa");
    }
    
    /*
     * 
     * */

    public void calcularNumRevisionesSpreadByIdTcaFrecuenciaRev(Integer IdTcaFrecuenciaRevSpread) {
        logger.warning("Inf, Inicia el metodo calcularNumRevisionesTazaByIdTcaFrecuenciaRevTasa");

        CondicionesFinancierasBean condicionesFinancierasBean = null;
        condicionesFinancierasBean =
            (CondicionesFinancierasBean) JSFUtils.resolveExpression("#{pageFlowScope.CondicionFinancieraBean}");

        java.sql.Timestamp fechaInicio =
            (null == ADFUtils.getBoundAttributeValue("FechaProximaRevisionSpread")) ? null :
            (java.sql.Timestamp) ADFUtils.getBoundAttributeValue("FechaProximaRevisionSpread");


        java.sql.Timestamp fechaFin =
            (null == ADFUtils.getBoundAttributeValue("FechaVencimiento")) ? null :
            (java.sql.Timestamp) ADFUtils.getBoundAttributeValue("FechaVencimiento");

        Integer FrecuenciaRevisionSpread =
            (null == ADFUtils.getBoundAttributeValue("FrecuenciaRevisionSpread")) ? null :
            (Integer) ADFUtils.getBoundAttributeValue("FrecuenciaRevisionSpread");


        if (IdTcaFrecuenciaRevSpread == null) {
            logger.warning("*Inf, IdTcaFrecuenciaRevSpread es resuelto a null no se calcularan cuotas");

        } else if (IdTcaFrecuenciaRevSpread.compareTo(tcaEstadoAlVencimiento) == 0) {
            logger.warning("*Inf, tcaEstado seleccionado es al vencimeto establecioendo numero de cuotas a 1");

            ADFUtils.setBoundAttributeValue("NumeroRevisionesSpread", 1);
            ADFUtils.setBoundAttributeValue("FrecuenciaRevisionSpread", 1);
            condicionesFinancierasBean.setFrecRevSpreadVisible(Boolean.FALSE);

            //AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorNumRevisionesSpread());
            //AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorSpreadVariable());
        } else {
            condicionesFinancierasBean.setFrecRevSpreadVisible(Boolean.TRUE);
            //AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorSpreadVariable());
            try
            {
                calcularCuotasOperBiding(fechaInicio, fechaFin, FrecuenciaRevisionSpread, IdTcaFrecuenciaRevSpread,
                                         "spread");
            }
            catch(Exception ex)
            {
                logger.warning("Inf, Ha ocurrido un error durante la ejecucion de calcularCuotasOperBiding: " + ex.getMessage()); 
            }
        }

        logger.warning("Inf, Termina el metodo calcularNumRevisionesTazaByIdTcaFrecuenciaRevTasa");
    }


    /**
     * Con este metodo al cambiar la FECHA DE REVICION DEL SPREAD recuperamos los valores necesarios
     * para calcular el numero de coutas de la seccion principal de condiciones fiancieras siempre y cuando
     * el campo de -Especificacion de fechas sea por FECHAS. y calculamos las cuotas mediante el metodo
     * calcularCuotasOperBiding
     *
     * @author Carlos O.
     * @since 23/01/2017
     */
    public void calcularNumRevisionesSpread(ValueChangeEvent valueChangeEvent) {
        logger.warning("Inf, Inicia el metodo calcularNumRevisionesSpread");
        java.sql.Timestamp fechaInicio = null;

        if (valueChangeEvent != null) {
            fechaInicio = (java.sql.Timestamp) valueChangeEvent.getNewValue();
        } else {
            fechaInicio =
                (null == ADFUtils.getBoundAttributeValue("FechaProximaRevisionSpread")) ? null :
                (java.sql.Timestamp) ADFUtils.getBoundAttributeValue("FechaProximaRevisionSpread");
        }

        java.sql.Timestamp fechaFin =
            (null == ADFUtils.getBoundAttributeValue("FechaVencimiento")) ? null :
            (java.sql.Timestamp) ADFUtils.getBoundAttributeValue("FechaVencimiento");

        Integer FrecuenciaRevisionSpread =
            (null == ADFUtils.getBoundAttributeValue("FrecuenciaRevisionSpread")) ? null :
            (Integer) ADFUtils.getBoundAttributeValue("FrecuenciaRevisionSpread");

        Integer IdTcaFrecuenciaRevSpread =
            (null == ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaRevSpread") ||
             ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaRevSpread").equals("")) ? null :
            Integer.parseInt(ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaRevSpread").toString());

        try
        {
            calcularCuotasOperBiding(fechaInicio, fechaFin, FrecuenciaRevisionSpread, IdTcaFrecuenciaRevSpread, "spread");
        }
        catch(Exception ex)
        {
            logger.warning("Inf, Ha ocurrido un error durante la ejecucion de calcularCuotasOperBiding: " + ex.getMessage()); 
        }


        logger.warning("Inf, Termina el metodo calcularNumRevisionesSpread");
    }


    /**
     * Este metodo ejecuta calcularCoutasPrincipal en CondicionesFinancierasVoImpl que invoca a un servicio
     * del WSDL_DESEMBOLSO operacion "calcular cuotas" y despues el resultado lo setea en el atributo decuotas
     * despues aqui solo se actualiza el fragmento que solicito el calculo.(este metodo solo se utiliza cuando
     * el campo de Especificacion de fechas tiene un valor de "Fechas").
     *
     * @author Carlos O.
     * @since 23/01/2017
     */

    public void calcularCuotasOperBiding(java.sql.Timestamp fechaInicio, java.sql.Timestamp fechaFin,
                                         Integer frecuencia, Integer IdTcaFrecuenciaPagoCapital, String tipoCuota) {
        logger.warning("Inf, Inicia calcularCuotasOperBiding ");
        CondicionesFinancierasBean condicionesFinancierasBean = null;
        condicionesFinancierasBean =
            (CondicionesFinancierasBean) JSFUtils.resolveExpression("#{pageFlowScope.CondicionFinancieraBean}");

        logger.warning("Inf, fechaInicio: " + fechaInicio);
        logger.warning("Inf, fechaFin: " + fechaFin);
        logger.warning("Inf, frecuencia: " + frecuencia);
        logger.warning("Inf, IdTcaFrecuenciaPagoCapital: " + IdTcaFrecuenciaPagoCapital);
        logger.warning("Inf, IdCondicionFinanciera: " + condicionesFinancierasBean.getIdCondicionFinanciera());
        logger.warning("Inf, tipoCuota: " + tipoCuota);
        
        Boolean respuesta = Boolean.FALSE;
        BindingContainer binding = ADFUtils.getBindingContainer();
        OperationBinding operBinding = binding.getOperationBinding("calcularCoutasPrincipal");
        operBinding.getParamsMap().put("fechaInicio", fechaInicio);
        operBinding.getParamsMap().put("fechaTermino", fechaFin);
        operBinding.getParamsMap().put("IdTcaFrecuencia", IdTcaFrecuenciaPagoCapital);
        operBinding.getParamsMap().put("frecuencia", frecuencia);
        operBinding.getParamsMap().put("tipoCuota", tipoCuota);
        operBinding.getParamsMap().put("IdCondicionFinanciera", condicionesFinancierasBean.getIdCondicionFinanciera());
        
        try
        {
            logger.warning("Inf, Inicia el llamado al calculo de cuotas  binding"); 
            respuesta = (Boolean) operBinding.execute();
            logger.warning("Inf, Finaliza el llamado al calculo de cuotas binding");
        }
        catch(Exception ex)
        {
            respuesta = Boolean.FALSE;
            logger.warning("Inf, Ha ocurrido un error durante la ejecucion del calculo de cuotas: " + ex.getMessage()); 
        }

        if (!operBinding.getErrors().isEmpty()) {
            logger.warning("***Error, al ejecutar operation Binding calcularNumCuotasPrincipal " +
                           operBinding.getErrors());
        }

        switch (tipoCuota) {
        case "principal":
            logger.warning("*Inf, Refrescando contenedor Cuotas principal en vista");
            //AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorNumCuotas());
            AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorAllSpreadVariableNew());
            AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorAllInteresNew());
            AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorAllCapitalNew());
            break;
        case "interes":
            logger.warning("*Inf, Refrescando contenedor Cuotas interes en vista");
            //AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorNumCuotasInteres());
            AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorAllSpreadVariableNew());
            AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorAllInteresNew());
            break;
        case "taza":
            logger.warning("*Inf, Refrescando contenedor revisiones taza en vista");
            //AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorNumRevisionTaza());
            AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorAllSpreadVariableNew());
            AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorAllInteresNew());
            break;
        case "spread":
            logger.warning("*Inf, Refrescando contenedor revisiones spread en vista");
            //AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorNumRevisionesSpread());
            AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorAllSpreadVariableNew());
            AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorAllInteresNew());
            break;
        case "tazaI":
            logger.warning("*Inf, Refrescando contenedor revisiones taza en componente Interes");
            //AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorNumRevisionTazaI());
            AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorAllSpreadVariableNew());
            AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorAllInteresNew());
            break;
        }

        logger.warning("Inf, Termina el metodo calcularCuotasOperBiding");
    }


    Integer IdTcaFrecuenciaPlazo;

    public void recalcularCuotasPorPlazo(ValueChangeEvent valueChangeEvent) {
        logger.warning("*Inf, Inicia el metodo recalcularCuotasPorPlazo");

        IdTcaFrecuenciaPlazo = null;

        try {
            IdTcaFrecuenciaPlazo =
                (null == valueChangeEvent.getNewValue() || valueChangeEvent.getNewValue().toString().equals("")) ?
                null : Integer.parseInt(valueChangeEvent.getNewValue().toString());
        } catch (Exception e) {
            logger.warning("*Inf, Important! ha ocurrido un error al caster IdTcaFrecuenciaPlazo: ->" +
                           IdTcaFrecuenciaPlazo + "<- Error:" + e);
        }


        ADFUtils.setBoundAttributeValue("IdTcaFrecuenciaPlazo", IdTcaFrecuenciaPlazo);
        asignarAlinearDiaPagoExceptoVencimiento();

        try {
            calcularCuotasSecPrincipalPorPlazo(null);
            calcularCuotasSecInteresPorPlazo(null);
            calcularCuotasSecTazaPorPlazo(null);
            calcularCuotasSecSpreadPorPlazo(null);
            calcularCuotasSecTazaPorPlazoI(null);
        } catch (Exception e) {
            logger.warning("***Error, recalcularCuotasPorPlazo : " + e);
        }
        logger.warning("*Inf, termina el metodo recalcularCuotasPorPlazo");
        calcularFechasByPlazosTareaRealizarAjustes();
    }
    
    Integer IdTcaTipoRedondeo;
    public void setIdTcaTipoRedondeo(ValueChangeEvent valueChangeEvent) {
        logger.warning("*Inf, Inicia el metodo setIdTcaTipoRedondeo");
        IdTcaTipoRedondeo = null;

        try {
            IdTcaTipoRedondeo =
                (null == valueChangeEvent.getNewValue() || valueChangeEvent.getNewValue().toString().equals("")) ?
                null : Integer.parseInt(valueChangeEvent.getNewValue().toString());
        } catch (Exception e) {
            logger.warning("*Inf, Important! ha ocurrido un error al caster IdTcaTipoRedondeo: ->" +
                           IdTcaTipoRedondeo + "<- Error:" + e);
        }
        
        ADFUtils.setBoundAttributeValue("IdTcaTipoRedondeo", IdTcaTipoRedondeo);
    }

    public void getCuotasSecPrincipalPlazobyFrecPerGracia(ValueChangeEvent valueChangeEvent) {
        logger.warning("*Inf, Inicia el metodo getCuotasSecPrincipalPlazobyFrecPerGracia");

        Integer IdTcaFrecuenciaPagoCapital = null;
        Integer TcaFrecuenciaPlazo = null;
        Integer tiempoDiasFrecuenciaPago = null;
        Integer tiempoDiasFrecuenciaPlazo = null;
        Integer tiempoDiasPeriodoGracia = null;
        Integer Cuotas = null;

        final Integer alVencimiento = 4;

        IdTcaFrecuenciaPagoCapital =
            (null == ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPagoCapital") ||
             ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPagoCapital").toString().equals("")) ? null :
            Integer.parseInt(ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPagoCapital").toString());

        TcaFrecuenciaPlazo =
            (null == ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPlazo") ||
             ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPlazo").toString().equals("")) ? null :
            Integer.parseInt(ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPlazo").toString());

        Integer FrecuenciaPagoCapital =
            (null == ADFUtils.getBoundAttributeValue("FrecuenciaPagoCapital") ||
             ADFUtils.getBoundAttributeValue("FrecuenciaPagoCapital").toString().equals("")) ? null :
            Integer.parseInt(ADFUtils.getBoundAttributeValue("FrecuenciaPagoCapital").toString());

        Integer FrecuenciaPlazo =
            (null == ADFUtils.getBoundAttributeValue("FrecuenciaPlazo") ||
             ADFUtils.getBoundAttributeValue("FrecuenciaPlazo").toString().equals("")) ? null :
            Integer.parseInt(ADFUtils.getBoundAttributeValue("FrecuenciaPlazo").toString());

        Integer frecuenciaPeriodoGracia =
            (null == valueChangeEvent.getNewValue() || valueChangeEvent.getNewValue().toString().equals("")) ? null :
            Integer.parseInt(valueChangeEvent.getNewValue().toString());

        Integer IdTcaFrecuenciaPeriodoGracia =
            (null == ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPeriodoGra") ||
             ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPeriodoGra").toString().equals("")) ? null :
            Integer.parseInt(ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPeriodoGra").toString());


        if (FrecuenciaPagoCapital != null && IdTcaFrecuenciaPagoCapital != null) {
            tiempoDiasFrecuenciaPago = obtenerTiempoEnDias(FrecuenciaPagoCapital, IdTcaFrecuenciaPagoCapital);
        } else {
            logger.warning("*Inf, parametros requeridos null");
            logger.warning("*Inf, FrecuenciaPagoCapital : " + FrecuenciaPagoCapital);
            logger.warning("*Inf, IdTcaFrecuenciaPagoCapital : " + IdTcaFrecuenciaPagoCapital);
        }

        if (FrecuenciaPlazo != null && TcaFrecuenciaPlazo != null) {
            tiempoDiasFrecuenciaPlazo = obtenerTiempoEnDias(FrecuenciaPlazo, TcaFrecuenciaPlazo);
        } else {
            logger.warning("*Inf, parametros requeridos null");
            logger.warning("*Inf, FrecuenciaPlazo : " + FrecuenciaPlazo);
            logger.warning("*Inf, IdTcaFrecuenciaPlazo : " + TcaFrecuenciaPlazo);
        }

        // Restamos el periodo de gracia al plazo

        if (frecuenciaPeriodoGracia != null && IdTcaFrecuenciaPeriodoGracia != null) {
            tiempoDiasPeriodoGracia = obtenerTiempoEnDias(frecuenciaPeriodoGracia, IdTcaFrecuenciaPeriodoGracia);
        } else {
            logger.warning("*Inf, parametros periodo de gracia null");
            logger.warning("*Inf, frecuenciaPeriodoGracia : " + frecuenciaPeriodoGracia);
            logger.warning("*Inf, IdTcaFrecuenciaPeriodoGracia : " + IdTcaFrecuenciaPeriodoGracia);
        }

        if (tiempoDiasFrecuenciaPlazo != null && tiempoDiasPeriodoGracia != null) {
            logger.warning("*Inf, Restando periodo de Gracia a plazo...  resultado: " + tiempoDiasFrecuenciaPlazo);
            tiempoDiasFrecuenciaPlazo = (tiempoDiasFrecuenciaPlazo - tiempoDiasPeriodoGracia);

        } else if (tiempoDiasFrecuenciaPlazo == null && tiempoDiasPeriodoGracia != null) {
            logger.warning("*Inf, plazo no especificado se tomara el periodo de gracia " + tiempoDiasPeriodoGracia +
                           " dias");
            tiempoDiasFrecuenciaPlazo = tiempoDiasPeriodoGracia;
        } else {
            logger.warning("*Inf, No se sumara el periodo de gracia");
            logger.warning("*Inf, tiempoDiasFrecuenciaPlazo: " + tiempoDiasFrecuenciaPlazo);
            logger.warning("*Inf, tiempoDiasPeriodoGracia: " + tiempoDiasPeriodoGracia);
        }

        //Calculamos las cuotas


        if (IdTcaFrecuenciaPagoCapital != null && IdTcaFrecuenciaPagoCapital.compareTo(alVencimiento) == 0) {
            logger.warning("*Inf, La frecuencia de pago de capital es al vencimiento, cuotas = 1");
            ADFUtils.setBoundAttributeValue("NumeroCuotasPagoCapital", 1);
        } else {

            if (tiempoDiasFrecuenciaPago != null && tiempoDiasFrecuenciaPlazo != null) {
                if (tiempoDiasFrecuenciaPago.compareTo(tiempoDiasFrecuenciaPlazo) != 1) {
                    Cuotas = tiempoDiasFrecuenciaPlazo / tiempoDiasFrecuenciaPago;
                    logger.warning("********************Inicia calculo de fechas de cuotas del capital");
                    getFechasCuotasCapital(Cuotas, frecuenciaPeriodoGracia, IdTcaFrecuenciaPeriodoGracia, IdTcaFrecuenciaPagoCapital, FrecuenciaPagoCapital,FrecuenciaPlazo, TcaFrecuenciaPlazo);
                    ADFUtils.setBoundAttributeValue("NumeroCuotasPagoCapital", Cuotas);
                    
                    logger.warning("FechaPrimerPagoCapital " + ADFUtils.getBoundAttributeValue("FechaPrimerPagoCapital"));
                    logger.warning("FechaVencimiento " + ADFUtils.getBoundAttributeValue("FechaVencimiento"));
                } else {
                    ADFUtils.setBoundAttributeValue("NumeroCuotasPagoCapital", null);
                    logger.warning("***Error, El numero frecuencia es menor que el plazo no se calculara las cuotas");
                }
                AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorAllCapitalNew());
                //AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorNumCuotas());
            } else {
                logger.warning("*Inf, parametros requeridos null");
                logger.warning("*Inf, tiempoDiasFrecuenciaPago : " + tiempoDiasFrecuenciaPago);
                logger.warning("*Inf, tiempoDiasFrecuenciaPlazo : " + tiempoDiasFrecuenciaPlazo);
            }

        }

        logger.warning("Numero de Cuotas por plazo calculado : " + Cuotas);
        logger.warning("*Inf, termina el metodo getCuotasSecPrincipalPlazobyFrecPerGracia");
    }

    public void getCuotasSecPrincipalPlazobyidTcaFrecPerGracia(ValueChangeEvent valueChangeEvent) {
        logger.warning("*Inf, Inicia el metodo getCuotasSecPrincipalPlazobyidTcaFrecPerGracia");

        Integer IdTcaFrecuenciaPagoCapital = null;
        Integer TcaFrecuenciaPlazo = null;
        Integer tiempoDiasFrecuenciaPago = null;
        Integer tiempoDiasFrecuenciaPlazo = null;
        Integer tiempoDiasPeriodoGracia = null;
        Integer Cuotas = null;

        final Integer alVencimiento = 4;

        IdTcaFrecuenciaPagoCapital =
            (null == ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPagoCapital") ||
             ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPagoCapital").toString().equals("")) ? null :
            Integer.parseInt(ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPagoCapital").toString());

        TcaFrecuenciaPlazo =
            (null == ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPlazo") ||
             ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPlazo").toString().equals("")) ? null :
            Integer.parseInt(ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPlazo").toString());

        Integer FrecuenciaPagoCapital =
            (null == ADFUtils.getBoundAttributeValue("FrecuenciaPagoCapital") ||
             ADFUtils.getBoundAttributeValue("FrecuenciaPagoCapital").toString().equals("")) ? null :
            Integer.parseInt(ADFUtils.getBoundAttributeValue("FrecuenciaPagoCapital").toString());

        Integer FrecuenciaPlazo =
            (null == ADFUtils.getBoundAttributeValue("FrecuenciaPlazo") ||
             ADFUtils.getBoundAttributeValue("FrecuenciaPlazo").toString().equals("")) ? null :
            Integer.parseInt(ADFUtils.getBoundAttributeValue("FrecuenciaPlazo").toString());

        Integer frecuenciaPeriodoGracia =
            (null == ADFUtils.getBoundAttributeValue("FrecuenciaPeriodoGracia")) ? null :
            (Integer) ADFUtils.getBoundAttributeValue("FrecuenciaPeriodoGracia");

        Integer IdTcaFrecuenciaPeriodoGracia =
            (null == valueChangeEvent.getNewValue() || valueChangeEvent.getNewValue().toString().equals("")) ? null :
            Integer.parseInt(valueChangeEvent.getNewValue().toString());


        if (FrecuenciaPagoCapital != null && IdTcaFrecuenciaPagoCapital != null) {
            tiempoDiasFrecuenciaPago = obtenerTiempoEnDias(FrecuenciaPagoCapital, IdTcaFrecuenciaPagoCapital);
        } else {
            logger.warning("*Inf, parametros requeridos null");
            logger.warning("*Inf, FrecuenciaPagoCapital : " + FrecuenciaPagoCapital);
            logger.warning("*Inf, IdTcaFrecuenciaPagoCapital : " + IdTcaFrecuenciaPagoCapital);
        }

        if (FrecuenciaPlazo != null && TcaFrecuenciaPlazo != null) {
            tiempoDiasFrecuenciaPlazo = obtenerTiempoEnDias(FrecuenciaPlazo, TcaFrecuenciaPlazo);
        } else {
            logger.warning("*Inf, parametros requeridos null");
            logger.warning("*Inf, FrecuenciaPlazo : " + FrecuenciaPlazo);
            logger.warning("*Inf, IdTcaFrecuenciaPlazo : " + TcaFrecuenciaPlazo);
        }

        // Restamos el periodo de gracia al plazo

        if (frecuenciaPeriodoGracia != null && IdTcaFrecuenciaPeriodoGracia != null) {
            tiempoDiasPeriodoGracia = obtenerTiempoEnDias(frecuenciaPeriodoGracia, IdTcaFrecuenciaPeriodoGracia);
        } else {
            logger.warning("*Inf, parametros periodo de gracia null");
            logger.warning("*Inf, frecuenciaPeriodoGracia : " + frecuenciaPeriodoGracia);
            logger.warning("*Inf, IdTcaFrecuenciaPeriodoGracia : " + IdTcaFrecuenciaPeriodoGracia);
        }

        if (tiempoDiasFrecuenciaPlazo != null && tiempoDiasPeriodoGracia != null) {
            logger.warning("*Inf, Restando periodo de Gracia a plazo...  resultado: " + tiempoDiasFrecuenciaPlazo);
            tiempoDiasFrecuenciaPlazo = (tiempoDiasFrecuenciaPlazo - tiempoDiasPeriodoGracia);

        } else if (tiempoDiasFrecuenciaPlazo == null && tiempoDiasPeriodoGracia != null) {
            logger.warning("*Inf, plazo no especificado se tomara el periodo de gracia " + tiempoDiasPeriodoGracia +
                           " dias");
            tiempoDiasFrecuenciaPlazo = tiempoDiasPeriodoGracia;
        } else {
            logger.warning("*Inf, No se sumara el periodo de gracia");
            logger.warning("*Inf, tiempoDiasFrecuenciaPlazo: " + tiempoDiasFrecuenciaPlazo);
            logger.warning("*Inf, tiempoDiasPeriodoGracia: " + tiempoDiasPeriodoGracia);
        }

        //Calculamos las cuotas

        if (IdTcaFrecuenciaPagoCapital != null && IdTcaFrecuenciaPagoCapital.compareTo(alVencimiento) == 0) {
            logger.warning("*Inf, La frecuencia de pago de capital es al vencimiento, cuotas = 1");
            ADFUtils.setBoundAttributeValue("NumeroCuotasPagoCapital", 1);
        } else {

            if (tiempoDiasFrecuenciaPago != null && tiempoDiasFrecuenciaPlazo != null) {
                if (tiempoDiasFrecuenciaPago.compareTo(tiempoDiasFrecuenciaPlazo) != 1) {
                    Cuotas = tiempoDiasFrecuenciaPlazo / tiempoDiasFrecuenciaPago;
                    ADFUtils.setBoundAttributeValue("NumeroCuotasPagoCapital", Cuotas);
                    logger.warning("**********************Se llama al getfechasCuotasCapital");
                    getFechasCuotasCapital(Cuotas, frecuenciaPeriodoGracia, IdTcaFrecuenciaPeriodoGracia, IdTcaFrecuenciaPagoCapital, FrecuenciaPagoCapital,FrecuenciaPlazo, TcaFrecuenciaPlazo);
                } else {
                    ADFUtils.setBoundAttributeValue("NumeroCuotasPagoCapital", null);
                    logger.warning("***Error, El numero frecuencia es menor que el plazo no se calculara las cuotas");
                }
                AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorAllCapitalNew());
                //AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorNumCuotas());
            } else {
                logger.warning("*Inf, parametros requeridos null");
                logger.warning("*Inf, tiempoDiasFrecuenciaPago : " + tiempoDiasFrecuenciaPago);
                logger.warning("*Inf, tiempoDiasFrecuenciaPlazo : " + tiempoDiasFrecuenciaPlazo);

            }

        }
        logger.warning("Numero de Cuotas por plazo calculado : " + Cuotas);

        logger.warning("*Inf, termina el metodo getCuotasSecPrincipalPlazobyidTcaFrecPerGracia");
    }


    public void calcularCuotasSecPrincipalPorPlazo(ValueChangeEvent valueChangeEvent) {
        logger.warning("*Inf, Inicia el metodo calcularCuotasPorPlazos");

        CondicionesFinancierasBean condicionesFinancierasBean = null;
        condicionesFinancierasBean =
            (CondicionesFinancierasBean) JSFUtils.resolveExpression("#{pageFlowScope.CondicionFinancieraBean}");

        Integer especificacionFechas =
            (null == ADFUtils.getBoundAttributeValue("IdTcaEspecificacionFecha") ||
             ADFUtils.getBoundAttributeValue("IdTcaEspecificacionFecha").toString().equals("")) ? null :
            Integer.parseInt(ADFUtils.getBoundAttributeValue("IdTcaEspecificacionFecha").toString());

        System.out.println("especificacionFechas ----> " + especificacionFechas);
        Integer IdTcaFrecuenciaPagoCapital = null;

        if (valueChangeEvent == null) {

            IdTcaFrecuenciaPagoCapital =
                (null == ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPagoCapital") ||
                 ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPagoCapital").toString().equals("")) ? null :
                Integer.parseInt(ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPagoCapital").toString());

        } else {

            System.out.println("IdTcaFrecuenciaPagoCapital ----> " + valueChangeEvent.getNewValue().toString());

            IdTcaFrecuenciaPagoCapital =
                (null == valueChangeEvent.getNewValue() || valueChangeEvent.getNewValue().toString().equals("")) ?
                null : Integer.parseInt(valueChangeEvent.getNewValue().toString());
        }

        IdTcaFrecuenciaPlazo =
            (null == ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPlazo") ||
             ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPlazo").toString().equals("")) ? null :
            Integer.parseInt(ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPlazo").toString());


        if (IdTcaFrecuenciaPagoCapital != null) {
            ADFUtils.setBoundAttributeValue("IdTcaFrecuenciaPagoCapital", IdTcaFrecuenciaPagoCapital);
            if (IdTcaFrecuenciaPagoCapital.compareTo(tcaEstadoAlVencimiento) == 0) {
                logger.warning("*Inf, idTcaEstado seleccionado alVencimiento");
                ADFUtils.setBoundAttributeValue("FrecuenciaPagoCapital", 1);
                ADFUtils.setBoundAttributeValue("NumeroCuotasPagoCapital", 1);
                condicionesFinancierasBean.setFrecPrincipalVisible(Boolean.FALSE);
            } else {
                condicionesFinancierasBean.setFrecPrincipalVisible(Boolean.TRUE);
            }
        }

        if (especificacionFechas == 2) {
            Integer FrecuenciaPagoCapital =
                (null == ADFUtils.getBoundAttributeValue("FrecuenciaPagoCapital") ||
                 ADFUtils.getBoundAttributeValue("FrecuenciaPagoCapital").toString().equals("")) ? null :
                Integer.parseInt(ADFUtils.getBoundAttributeValue("FrecuenciaPagoCapital").toString());

            Integer FrecuenciaPlazo =
                (null == ADFUtils.getBoundAttributeValue("FrecuenciaPlazo") ||
                 ADFUtils.getBoundAttributeValue("FrecuenciaPlazo").toString().equals("")) ? null :
                Integer.parseInt(ADFUtils.getBoundAttributeValue("FrecuenciaPlazo").toString());


            Integer frecuenciaPeriodoGracia =
                (null == ADFUtils.getBoundAttributeValue("FrecuenciaPeriodoGracia")) ? null :
                (Integer) ADFUtils.getBoundAttributeValue("FrecuenciaPeriodoGracia");

            Integer IdTcaFrecuenciaPeriodoGracia =
                (null == ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPeriodoGra") ||
                 ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPeriodoGra").toString().equals("")) ? null :
                Integer.parseInt(ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPeriodoGra").toString());

            if (IdTcaFrecuenciaPagoCapital != null &&
                IdTcaFrecuenciaPagoCapital.compareTo(tcaEstadoAlVencimiento) == 0) {
                logger.warning("*Inf, numero de coutas alVencimiento es 1");

                if (FrecuenciaPlazo != null && IdTcaFrecuenciaPlazo != null) {
                    ADFUtils.setBoundAttributeValue("NumeroCuotasPagoCapital", 1);
                    //AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorNumCuotas());
                    getFechasCuotasCapital(1, frecuenciaPeriodoGracia, IdTcaFrecuenciaPeriodoGracia, IdTcaFrecuenciaPagoCapital, FrecuenciaPagoCapital,FrecuenciaPlazo, IdTcaFrecuenciaPlazo);
                } else {
                    logger.warning("*Inf, fecha de FrecuenciaPlazo y IdTcaFrecuenciaPlazo son requeridas no se calculara el numero de cuotas");
                    ADFUtils.setBoundAttributeValue("NumeroCuotasPagoCapital", null);
                    //AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorNumCuotas());
                }
            } else {

                Integer tiempoDiasFrecuenciaPago = null;
                Integer tiempoDiasFrecuenciaPlazo = null;
                Integer tiempoDiasPeriodoGracia = null;
                Integer Cuotas = null;

                if (FrecuenciaPagoCapital != null && IdTcaFrecuenciaPagoCapital != null) {
                    tiempoDiasFrecuenciaPago = obtenerTiempoEnDias(FrecuenciaPagoCapital, IdTcaFrecuenciaPagoCapital);
                } else {
                    logger.warning("*Inf, parametros requeridos null");
                    logger.warning("*Inf, FrecuenciaPagoCapital : " + FrecuenciaPagoCapital);
                    logger.warning("*Inf, IdTcaFrecuenciaPagoCapital : " + IdTcaFrecuenciaPagoCapital);
                }

                if (FrecuenciaPlazo != null && IdTcaFrecuenciaPlazo != null) {
                    tiempoDiasFrecuenciaPlazo = obtenerTiempoEnDias(FrecuenciaPlazo, IdTcaFrecuenciaPlazo);
                } else {
                    logger.warning("*Inf, parametros requeridos null");
                    logger.warning("*Inf, FrecuenciaPlazo : " + FrecuenciaPlazo);
                    logger.warning("*Inf, IdTcaFrecuenciaPlazo : " + IdTcaFrecuenciaPlazo);
                }

                //exepcion calculo de frecuencia meses plasos anios
                if (IdTcaFrecuenciaPagoCapital != null & IdTcaFrecuenciaPlazo != null) {
                    if (IdTcaFrecuenciaPagoCapital == 2 & IdTcaFrecuenciaPlazo == 3) {
                        tiempoDiasFrecuenciaPlazo = obtenerTiempoEnDias(FrecuenciaPlazo, 4);
                        logger.warning("----->Inf,exeption  frecuencia meses plazos anios recalculando tiempo");
                    }
                }
                // restamos el periodo de gracia al plazo

                if (frecuenciaPeriodoGracia != null && IdTcaFrecuenciaPeriodoGracia != null) {
                    tiempoDiasPeriodoGracia =
                        obtenerTiempoEnDias(frecuenciaPeriodoGracia, IdTcaFrecuenciaPeriodoGracia);
                } else {
                    logger.warning("*Inf, parametros periodo de gracia null");
                    logger.warning("*Inf, frecuenciaPeriodoGracia : " + frecuenciaPeriodoGracia);
                    logger.warning("*Inf, IdTcaFrecuenciaPeriodoGracia : " + IdTcaFrecuenciaPeriodoGracia);
                }

                if (tiempoDiasFrecuenciaPlazo != null && tiempoDiasPeriodoGracia != null) {
                    logger.warning("*Inf, Restando periodo de Gracia a plazo...  resultado: " +
                                   tiempoDiasFrecuenciaPlazo);
                    tiempoDiasFrecuenciaPlazo = (tiempoDiasFrecuenciaPlazo - tiempoDiasPeriodoGracia);
                } else {
                    logger.warning("*Inf, No se sumara el periodo de gracia");
                    logger.warning("*Inf, tiempoDiasFrecuenciaPlazo: " + tiempoDiasFrecuenciaPlazo);
                    logger.warning("*Inf, tiempoDiasPeriodoGracia: " + tiempoDiasPeriodoGracia);
                }

                //Calculamos las cuotas

                if (tiempoDiasFrecuenciaPago != null && tiempoDiasFrecuenciaPlazo != null) {
                    if (tiempoDiasFrecuenciaPago.compareTo(tiempoDiasFrecuenciaPlazo) != 1) {
                        Cuotas = tiempoDiasFrecuenciaPlazo / tiempoDiasFrecuenciaPago;
                    } else {
                        ADFUtils.setBoundAttributeValue("NumeroCuotasPagoCapital", null);
                        logger.warning("***Error, El numero frecuencia es menor que el plazo no se calculara las cuotas");
                    }
                } else {
                    logger.warning("*Inf, parametros requeridos null");
                    logger.warning("*Inf, tiempoDiasFrecuenciaPago : " + tiempoDiasFrecuenciaPago);
                    logger.warning("*Inf, tiempoDiasFrecuenciaPlazo : " + tiempoDiasFrecuenciaPlazo);
                }

                ADFUtils.setBoundAttributeValue("NumeroCuotasPagoCapital", Cuotas);
                //AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorNumCuotas());
                logger.warning("Numero de Cuotas por plazo calculado : " + Cuotas);
                getFechasCuotasCapital(Cuotas, frecuenciaPeriodoGracia, IdTcaFrecuenciaPeriodoGracia, IdTcaFrecuenciaPagoCapital, FrecuenciaPagoCapital,FrecuenciaPlazo, IdTcaFrecuenciaPlazo);
            }

        } else {
            logger.warning("*Inf, El valor de Especificacion de Fechas es por Fechas caluclando para un idTcaFrecuencia: " +
                           IdTcaFrecuenciaPagoCapital);
            calcularNumCuotasPrincipalByIdTcaFrecuencia(IdTcaFrecuenciaPagoCapital);
        }
        logger.warning("*Inf, Termina el metodo calcularCuotasSecPrincipalPorPlazo");
    }


    public void calcularCuotasSecInteresPorPlazo(ValueChangeEvent valueChangeEvent) {
        logger.warning("*Inf, Inicia el metodo calcularCuotasSecInteresPorPlazo");

        CondicionesFinancierasBean condicionesFinancierasBean = null;
        condicionesFinancierasBean =
            (CondicionesFinancierasBean) JSFUtils.resolveExpression("#{pageFlowScope.CondicionFinancieraBean}");

        Integer especificacionFechas =
            (null == ADFUtils.getBoundAttributeValue("IdTcaEspecificacionFecha") ||
             ADFUtils.getBoundAttributeValue("IdTcaEspecificacionFecha").toString().equals("")) ? null :
            Integer.parseInt(ADFUtils.getBoundAttributeValue("IdTcaEspecificacionFecha").toString());

        Integer IdTcaFrecuenciaPagoInteres = null;


        if (valueChangeEvent == null) {
            IdTcaFrecuenciaPagoInteres =
                (null == ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPagoInteres") ||
                 ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPagoInteres").toString().equals("")) ? null :
                Integer.parseInt(ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPagoInteres").toString());
        } else {

            IdTcaFrecuenciaPagoInteres =
                (null == valueChangeEvent.getNewValue() || valueChangeEvent.getNewValue().toString().equals("")) ?
                null : Integer.parseInt(valueChangeEvent.getNewValue().toString());
            System.out.println("IdTcaFrecuenciaPagoInteres --> " + IdTcaFrecuenciaPagoInteres);


            IdTcaFrecuenciaPlazo =
                (null == ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPlazo") ||
                 ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPlazo").toString().equals("")) ? null :
                Integer.parseInt(ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPlazo").toString());
        }


        if (especificacionFechas == 2) {


            Integer FrecuenciaPagoInteres =
                (null == ADFUtils.getBoundAttributeValue("FrecuenciaPagoInteres") ||
                 ADFUtils.getBoundAttributeValue("FrecuenciaPagoInteres").toString().equals("")) ? null :
                Integer.parseInt(ADFUtils.getBoundAttributeValue("FrecuenciaPagoInteres").toString());

            Integer FrecuenciaPlazo =
                (null == ADFUtils.getBoundAttributeValue("FrecuenciaPlazo") ||
                 ADFUtils.getBoundAttributeValue("FrecuenciaPlazo").toString().equals("")) ? null :
                Integer.parseInt(ADFUtils.getBoundAttributeValue("FrecuenciaPlazo").toString());


            if (IdTcaFrecuenciaPagoInteres != null) {
                logger.warning("*Inf, verificando tcaFrecuenciaInteres: " + IdTcaFrecuenciaPagoInteres);
                ADFUtils.setBoundAttributeValue("IdTcaFrecuenciaPagoInteres", IdTcaFrecuenciaPagoInteres);
                Integer Cuotas = null;
                if (IdTcaFrecuenciaPagoInteres.compareTo(tcaEstadoAlVencimiento) == 0) {

                    if (IdTcaFrecuenciaPagoInteres != null) {
                        ADFUtils.setBoundAttributeValue("NumeroCuotasPagoInteres", 1);
                        ADFUtils.setBoundAttributeValue("FrecuenciaPagoInteres", 1);
                        condicionesFinancierasBean.setFrecInteresVisible(Boolean.FALSE);
                        Cuotas = 1;
                    } else {
                        logger.warning("*Inf, IdTcaFrecuenciaPagoInteres son requeridas no se calculara el numero de cuotas");
                        ADFUtils.setBoundAttributeValue("NumeroCuotasPagoInteres", null);

                    }
                    //AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorNumCuotasInteres());
                } else {
                    condicionesFinancierasBean.setFrecInteresVisible(Boolean.TRUE);

                    Integer tiempoDiasFrecuencia = null;
                    Integer tiempoDiasFrecuenciaPlazo = null;

                    if (FrecuenciaPagoInteres != null && IdTcaFrecuenciaPagoInteres != null) {
                        tiempoDiasFrecuencia = obtenerTiempoEnDias(FrecuenciaPagoInteres, IdTcaFrecuenciaPagoInteres);
                    } else {
                        logger.warning("*Inf, parametros requeridos null");
                        logger.warning("*Inf, FrecuenciaPagoInteres : " + FrecuenciaPagoInteres);
                        logger.warning("*Inf, IdTcaFrecuenciaPagoInteres : " + IdTcaFrecuenciaPagoInteres);
                    }

                    if (FrecuenciaPlazo != null && IdTcaFrecuenciaPlazo != null) {
                        tiempoDiasFrecuenciaPlazo = obtenerTiempoEnDias(FrecuenciaPlazo, IdTcaFrecuenciaPlazo);
                    } else {
                        logger.warning("*Inf, parametros requeridos null");
                        logger.warning("*Inf, FrecuenciaPlazo : " + FrecuenciaPlazo);
                        logger.warning("*Inf, IdTcaFrecuenciaPlazo : " + IdTcaFrecuenciaPlazo);
                    }

                    //exepcion calculo de frecuencia meses plasos anios
                    if (IdTcaFrecuenciaPagoInteres != null & IdTcaFrecuenciaPlazo != null) {
                        if (IdTcaFrecuenciaPagoInteres == 2 & IdTcaFrecuenciaPlazo == 3) {
                            tiempoDiasFrecuenciaPlazo = obtenerTiempoEnDias(FrecuenciaPlazo, 4);
                            logger.warning("----->Inf,exeption  frecuencia meses plazos anios recalculando tiempo");
                        }
                    }

                    if (tiempoDiasFrecuencia != null && tiempoDiasFrecuenciaPlazo != null) {
                        if (tiempoDiasFrecuencia.compareTo(tiempoDiasFrecuenciaPlazo) != 1) {
                            if (tiempoDiasFrecuencia.compareTo(0) == 1 && tiempoDiasFrecuenciaPlazo.compareTo(0) == 1) {
                                Cuotas = tiempoDiasFrecuenciaPlazo / tiempoDiasFrecuencia;
                                ADFUtils.setBoundAttributeValue("NumeroCuotasPagoInteres", Cuotas);
                            } else {
                                logger.warning("***ERROR, TiempoDiasFrecuencia o tiempoDiasFrecuenciaPlazo son cero (0). No se calculara cuota.");
                            }
                        } else {
                            ADFUtils.setBoundAttributeValue("NumeroCuotasPagoInteres", null);
                            logger.warning("***Error, El numero frecuencia es menor que el plazo no se calculara las cuotas");
                        }
                        //AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorNumCuotasInteres());
                    } else {
                        logger.warning("*Inf, parametros requeridos null");
                        logger.warning("*Inf, tiempoDiasFrecuenciaInteres : " + tiempoDiasFrecuencia);
                        logger.warning("*Inf, tiempoDiasFrecuenciaPlazo : " + tiempoDiasFrecuenciaPlazo);
                    }
                    logger.warning("Numero de Cuotas por plazo calculado : " + Cuotas);
                }
                
                //Si las cuotas calculadas en el interes no son null, se envia a calcular a plazos las fechas de proximo pago interes
                if(Cuotas != null)
                {
                    Date fechaEfectiva = obtenerFechaEstimadaAdesembolsar();
                    java.sql.Timestamp fechaDisponibilidadFondos = null;
                    Date fechaDisponibilidadFondosDate = null;
                    Date fechaProxPagInteres = null;
                    Date fechaVencimiento = null;
                    
                    fechaDisponibilidadFondos = obtenerFechaDisponibilidadFondos();

                    if (fechaDisponibilidadFondos != null) {
                        fechaDisponibilidadFondosDate = new Date(fechaDisponibilidadFondos.getTime());
                        fechaEfectiva = fechaDisponibilidadFondosDate;
                    }
                    
                    fechaVencimiento =
                        (null == ADFUtils.getBoundAttributeValue("FechaVencimiento")) ? null :
                        (Date) ADFUtils.getBoundAttributeValue("FechaVencimiento");
                    
                    if (IdTcaFrecuenciaPagoInteres.compareTo(tcaEstadoAlVencimiento) == 0) 
                    {
                        fechaProxPagInteres = fechaVencimiento;
                    }
                    else
                    {
                        fechaProxPagInteres = addTiempo(fechaEfectiva, FrecuenciaPagoInteres, IdTcaFrecuenciaPagoInteres);
                    }
                    
                    if(fechaVencimiento != null && fechaProxPagInteres != null)
                    {
                        CondicionesFinancierasBean condicionesBean = null;
                        condicionesBean = (CondicionesFinancierasBean) JSFUtils.resolveExpression("#{pageFlowScope.CondicionFinancieraBean}");
                                
                        if (condicionesBean.getLecturaIFI() != null && condicionesBean.getLecturaIFI()) {
                            //Fechas Temporales para evitar error, no se usan realmente
                            Date fechaPrimerPagoCapital = fechaProxPagInteres;
                            Date fechaProxRevTasa = fechaProxPagInteres;
                            Date fechaProxRevSpread = fechaProxPagInteres;
                            
                            Map fechasAlineadas =
                                                alinearFechasPagoRevicionYVencimiento(fechaPrimerPagoCapital, fechaProxPagInteres, fechaProxRevTasa,
                                                                                      fechaProxRevSpread, fechaVencimiento);
                            
                            fechaProxPagInteres = (Date) fechasAlineadas.get("fechaProxPagInteres");
                        } else {
                            logger.warning("*Inf, la operacion no es IFI no se realizaran alineaciones");
                        }
                        //Se asignan las fechas a los viewObjects
                        ADFUtils.setBoundAttributeValue("FechaProximoPagoInteres", fechaProxPagInteres);
                    }
                } //Termina el calculo de fechas
            } else {
                logger.warning("*Inf, IdTcaFrecuenciaPagoInteres es requerido no se calcularan las cuotas");
                ADFUtils.setBoundAttributeValue("NumeroCuotasPagoInteres", null);
                //AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorNumCuotasInteres());
            }

        } else {
            logger.warning("*Inf, El valor de Especificacion de Fechas es por Fechas calculando...");

            calcularNumCuotasInteresByIdTcaFrecuencia(IdTcaFrecuenciaPagoInteres);

        }
        logger.warning("*Inf, Termina el metodo calcularCuotasSecInteresPorPlazo");
    }


    public void calcularSecTazaByFrecuencia(ValueChangeEvent valueChangeEvent) {
        logger.warning("Inf, Inicia el metodo calcularSecTazaByFrecuencia");

        Integer FrecuenciaRevisionTasa =
            (null == valueChangeEvent.getNewValue() || valueChangeEvent.getNewValue().toString().equals("")) ? null :
            Integer.parseInt(valueChangeEvent.getNewValue().toString());

        Integer especificacionFechas =
            (null == ADFUtils.getBoundAttributeValue("IdTcaEspecificacionFecha") ||
             ADFUtils.getBoundAttributeValue("IdTcaEspecificacionFecha").toString().equals("")) ? null :
            Integer.parseInt(ADFUtils.getBoundAttributeValue("IdTcaEspecificacionFecha").toString());

        if (especificacionFechas == 2) {
            logger.warning("Inf, calculando revisiones tasa por Plazos...");
            calcularCuotasSecTazaByFrecuenciaPlazo(FrecuenciaRevisionTasa);
        } else {
            logger.warning("Inf, calculando revisiones tasa por Fechas...");
            calcularSecTazaByFrecuenciaFechas(FrecuenciaRevisionTasa);
        }
        logger.warning("Inf, Inicia el metodo calcularSecTazaByFrecuencia");
    }


    public void calcularSecTazaByFrecuenciaFechas(Integer FrecuenciaRevisionTasa) {
        logger.warning("Inf, Inicia el metodo calcularSecTazaByFrecuenciaFechas");

        java.sql.Timestamp fechaInicio =
            (null == ADFUtils.getBoundAttributeValue("FechaProximaRevisionTasaI")) ? null :
            (java.sql.Timestamp) ADFUtils.getBoundAttributeValue("FechaProximaRevisionTasaI");

        java.sql.Timestamp fechaFin =
            (null == ADFUtils.getBoundAttributeValue("FechaVencimiento")) ? null :
            (java.sql.Timestamp) ADFUtils.getBoundAttributeValue("FechaVencimiento");

        Integer IdTcaFrecuenciaRevTasa =
            (null == ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaRevTasa") ||
             ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaRevTasa").equals("")) ? null :
            Integer.parseInt(ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaRevTasa").toString());

        try
        {
            calcularCuotasOperBiding(fechaInicio, fechaFin, FrecuenciaRevisionTasa, IdTcaFrecuenciaRevTasa, "taza");
        }
        catch(Exception ex)
        {
            logger.warning("Inf, Ha ocurrido un error durante la ejecucion de calcularCuotasOperBiding: " + ex.getMessage()); 
        }


        logger.warning("Inf, Termina el metodo calcularSecTazaByFrecuenciaFechas");

    }


    public void calcularCuotasSecTazaByFrecuenciaPlazo(Integer FrecuenciaRevisionTasa) {
        logger.warning("*Inf, Inicia el metodo calcularCuotasSecTazaByFrecuenciaPlazo");

        Integer IdTcaFrecuenciaRevTasa =
            (null == ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaRevTasa") ||
             ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaRevTasa").toString().equals("")) ? null :
            Integer.parseInt(ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaRevTasa").toString());

        Integer IdTcaFrecuenciaPlazo =
            (null == ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPlazo") ||
             ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPlazo").toString().equals("")) ? null :
            Integer.parseInt(ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPlazo").toString());

        Integer FrecuenciaPlazo =
            (null == ADFUtils.getBoundAttributeValue("FrecuenciaPlazo") ||
             ADFUtils.getBoundAttributeValue("FrecuenciaPlazo").toString().equals("")) ? null :
            Integer.parseInt(ADFUtils.getBoundAttributeValue("FrecuenciaPlazo").toString());


        if (IdTcaFrecuenciaRevTasa == null || IdTcaFrecuenciaPlazo == null || FrecuenciaPlazo == null ||
            FrecuenciaRevisionTasa == null) {
            ADFUtils.setBoundAttributeValue("NumeroRevisionesTasa", null);
            //AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorNumRevisionTaza());
        }


        Integer tiempoDiasFrecuencia = null;
        Integer tiempoDiasFrecuenciaPlazo = null;
        Integer Cuotas = null;

        if (FrecuenciaRevisionTasa != null && IdTcaFrecuenciaRevTasa != null) {
            tiempoDiasFrecuencia = obtenerTiempoEnDias(FrecuenciaRevisionTasa, IdTcaFrecuenciaRevTasa);
        } else {
            logger.warning("*Inf, parametros requeridos null");
            logger.warning("*Inf, FrecuenciaRevisionTasa : " + FrecuenciaRevisionTasa);
            logger.warning("*Inf, IdTcaFrecuenciaRevTasa : " + IdTcaFrecuenciaRevTasa);
        }

        if (FrecuenciaPlazo != null && IdTcaFrecuenciaPlazo != null) {
            tiempoDiasFrecuenciaPlazo = obtenerTiempoEnDias(FrecuenciaPlazo, IdTcaFrecuenciaPlazo);
        } else {
            logger.warning("*Inf, parametros requeridos null");
            logger.warning("*Inf, FrecuenciaPlazo : " + FrecuenciaPlazo);
            logger.warning("*Inf, IdTcaFrecuenciaPlazo : " + IdTcaFrecuenciaPlazo);
        }

        if (tiempoDiasFrecuencia != null && tiempoDiasFrecuenciaPlazo != null) {
            if (tiempoDiasFrecuencia.compareTo(tiempoDiasFrecuenciaPlazo) != 1) {
                if (tiempoDiasFrecuencia.compareTo(0) == 1 && tiempoDiasFrecuenciaPlazo.compareTo(0) == 1) {
                    Cuotas = tiempoDiasFrecuenciaPlazo / tiempoDiasFrecuencia;
                    //las de revision se valida que si son mayor que 1 se le resta 1
                    if(Cuotas > 1)
                    {
                        Cuotas = Cuotas - 1;
                        logger.warning("----->Inf,Cuotas de Revision Tasa Corregidas: " + Cuotas);
                    }
                    ADFUtils.setBoundAttributeValue("NumeroRevisionesTasa", Cuotas);
                } else {
                    logger.warning("***ERROR, TiempoDiasFrecuencia o tiempoDiasFrecuenciaPlazo son cero (0). No se calculara cuota.");
                }
            } else {
                ADFUtils.setBoundAttributeValue("NumeroRevisionesTasa", null);
                logger.warning("***Error, El numero frecuencia es menor que el plazo no se calculara las cuotas");
            }
            //AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorNumRevisionTaza());
        } else {
            logger.warning("*Inf, parametros requeridos null");
            logger.warning("*Inf, tiempoDiasFrecuenciaTaza : " + tiempoDiasFrecuencia);
            logger.warning("*Inf, tiempoDiasFrecuenciaPlazo : " + tiempoDiasFrecuenciaPlazo);
        }
        logger.warning("Numero de Cuotas por plazo calculado : " + Cuotas);

        logger.warning("*Inf, Termina el metodo calcularCuotasSecTazaByFrecuenciaPlazo");
    }

    /*
     * Josue O
     * */
    
    public void calcularSecTazaByFrecuenciaI(ValueChangeEvent valueChangeEvent) {
        logger.warning("Inf, Inicia el metodo calcularSecTazaByFrecuencia");

        Integer FrecuenciaRevisionTasa =
            (null == valueChangeEvent.getNewValue() || valueChangeEvent.getNewValue().toString().equals("")) ? null :
            Integer.parseInt(valueChangeEvent.getNewValue().toString());

        Integer especificacionFechas =
            (null == ADFUtils.getBoundAttributeValue("IdTcaEspecificacionFecha") ||
             ADFUtils.getBoundAttributeValue("IdTcaEspecificacionFecha").toString().equals("")) ? null :
            Integer.parseInt(ADFUtils.getBoundAttributeValue("IdTcaEspecificacionFecha").toString());

        if (especificacionFechas == 2) {
            logger.warning("Inf, calculando revisiones tasa por Plazos...");
            calcularCuotasSecTazaByFrecuenciaPlazoI(FrecuenciaRevisionTasa);
        } else {
            logger.warning("Inf, calculando revisiones tasa por Fechas...");
            calcularSecTazaByFrecuenciaFechasI(FrecuenciaRevisionTasa);
        }
        
        
        /*try
        {
            calcularCuotasSecTazaPorPlazoI(valueChangeEvent);
        }
        catch(Exception ex)
        {
            logger.warning("Inf, Error recalculando cuotas de interes");
        }*/
        logger.warning("Inf, Inicia el metodo calcularSecTazaByFrecuencia");
    }


    public void calcularSecTazaByFrecuenciaFechasI(Integer FrecuenciaRevisionTasa) {
        logger.warning("Inf, Inicia el metodo calcularSecTazaByFrecuenciaFechasI");

        java.sql.Timestamp fechaInicio =
            (null == ADFUtils.getBoundAttributeValue("FechaProximaRevisionTasaI")) ? null :
            (java.sql.Timestamp) ADFUtils.getBoundAttributeValue("FechaProximaRevisionTasaI");

        java.sql.Timestamp fechaFin =
            (null == ADFUtils.getBoundAttributeValue("FechaVencimiento")) ? null :
            (java.sql.Timestamp) ADFUtils.getBoundAttributeValue("FechaVencimiento");

        Integer IdTcaFrecuenciaRevTasa =
            (null == ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaRevTasaI") ||
             ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaRevTasaI").equals("")) ? null :
            Integer.parseInt(ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaRevTasaI").toString());

        try
        {
            calcularCuotasOperBiding(fechaInicio, fechaFin, FrecuenciaRevisionTasa, IdTcaFrecuenciaRevTasa, "tazaI");
        }
        catch(Exception ex)
        {
            logger.warning("Inf, Ha ocurrido un error durante la ejecucion de calcularCuotasOperBiding: " + ex.getMessage()); 
        }

        logger.warning("Inf, Termina el metodo calcularSecTazaByFrecuenciaFechas");

    }

    public void calcularCuotasSecTazaByFrecuenciaPlazoI(Integer FrecuenciaRevisionTasa) {
        logger.warning("*Inf, Inicia el metodo calcularCuotasSecTazaByFrecuenciaPlazo");

        Integer IdTcaFrecuenciaRevTasa =
            (null == ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaRevTasaI") ||
             ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaRevTasaI").toString().equals("")) ? null :
            Integer.parseInt(ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaRevTasaI").toString());

        //Combobox componente Principal
        Integer IdTcaFrecuenciaPlazo =
            (null == ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPlazo") ||
             ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPlazo").toString().equals("")) ? null :
            Integer.parseInt(ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPlazo").toString());

        //input componente Principal
        Integer FrecuenciaPlazo =
            (null == ADFUtils.getBoundAttributeValue("FrecuenciaPlazo") ||
             ADFUtils.getBoundAttributeValue("FrecuenciaPlazo").toString().equals("")) ? null :
            Integer.parseInt(ADFUtils.getBoundAttributeValue("FrecuenciaPlazo").toString());


        if (IdTcaFrecuenciaRevTasa == null || IdTcaFrecuenciaPlazo == null || FrecuenciaPlazo == null ||
            FrecuenciaRevisionTasa == null) {
            ADFUtils.setBoundAttributeValue("NumeroRevisionesTasaI", null);
            //AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorNumRevisionTazaI());
        }


        Integer tiempoDiasFrecuencia = null;
        Integer tiempoDiasFrecuenciaPlazo = null;
        Integer Cuotas = null;

        if (FrecuenciaRevisionTasa != null && IdTcaFrecuenciaRevTasa != null) {
            tiempoDiasFrecuencia = obtenerTiempoEnDias(FrecuenciaRevisionTasa, IdTcaFrecuenciaRevTasa);
        } else {
            logger.warning("*Inf, parametros requeridos null");
            logger.warning("*Inf, FrecuenciaRevisionTasaI : " + FrecuenciaRevisionTasa);
            logger.warning("*Inf, IdTcaFrecuenciaRevTasaI : " + IdTcaFrecuenciaRevTasa);
        }

        if (FrecuenciaPlazo != null && IdTcaFrecuenciaPlazo != null) {
            tiempoDiasFrecuenciaPlazo = obtenerTiempoEnDias(FrecuenciaPlazo, IdTcaFrecuenciaPlazo);
        } else {
            logger.warning("*Inf, parametros requeridos null");
            logger.warning("*Inf, FrecuenciaPlazo : " + FrecuenciaPlazo);
            logger.warning("*Inf, IdTcaFrecuenciaPlazo : " + IdTcaFrecuenciaPlazo);
        }

        if (tiempoDiasFrecuencia != null && tiempoDiasFrecuenciaPlazo != null) {
            if (tiempoDiasFrecuencia.compareTo(tiempoDiasFrecuenciaPlazo) != 1) {
                if (tiempoDiasFrecuencia.compareTo(0) == 1 && tiempoDiasFrecuenciaPlazo.compareTo(0) == 1) {
                    Cuotas = tiempoDiasFrecuenciaPlazo / tiempoDiasFrecuencia;
                    if(Cuotas > 1)
                    {
                        Cuotas = Cuotas - 1;
                        logger.warning("----->Inf,Cuotas de Revision Tasa Corregidas: " + Cuotas);
                    }
                    ADFUtils.setBoundAttributeValue("NumeroRevisionesTasaI", Cuotas);
                } else {
                    logger.warning("***ERROR, TiempoDiasFrecuencia o tiempoDiasFrecuenciaPlazo son cero (0). No se calculara cuota.");
                }
            } else {
                ADFUtils.setBoundAttributeValue("NumeroRevisionesTasaI", null);
                logger.warning("***Error, El numero frecuencia es menor que el plazo no se calculara las cuotas");
            }
            //AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorNumRevisionTaza());
        } else {
            logger.warning("*Inf, parametros requeridos null");
            logger.warning("*Inf, tiempoDiasFrecuenciaTaza : " + tiempoDiasFrecuencia);
            logger.warning("*Inf, tiempoDiasFrecuenciaPlazo : " + tiempoDiasFrecuenciaPlazo);
        }
        logger.warning("Numero de Cuotas por plazo calculado : " + Cuotas);

        logger.warning("*Inf, Termina el metodo calcularCuotasSecTazaByFrecuenciaPlazo");
    }
    
    
    public void calcularCuotasSecTazaPorPlazo(ValueChangeEvent valueChangeEvent) {
        logger.warning("*Inf, Inicia el metodo calcularCuotasSecTazaPorPlazo");

        Integer especificacionFechas =
            (null == ADFUtils.getBoundAttributeValue("IdTcaEspecificacionFecha") ||
             ADFUtils.getBoundAttributeValue("IdTcaEspecificacionFecha").toString().equals("")) ? null :
            Integer.parseInt(ADFUtils.getBoundAttributeValue("IdTcaEspecificacionFecha").toString());
        
        System.out.println("especificacionFechas --> "+especificacionFechas);
        Integer IdTcaFrecuenciaRevTasa = null;

        if (valueChangeEvent == null) {
            IdTcaFrecuenciaRevTasa =
                (null == ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaRevTasa") ||
                 ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaRevTasa").toString().equals("")) ? null :
                Integer.parseInt(ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaRevTasa").toString());
        } else {

            System.out.println("IdTcaFrecuenciaRevTasa --> " + valueChangeEvent.getNewValue().toString());
            IdTcaFrecuenciaRevTasa =
                (null == valueChangeEvent.getNewValue() || valueChangeEvent.getNewValue().toString().equals("")) ?
                null : Integer.parseInt(valueChangeEvent.getNewValue().toString());


            IdTcaFrecuenciaPlazo =
                (null == ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPlazo") ||
                 ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPlazo").toString().equals("")) ? null :
                Integer.parseInt(ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPlazo").toString());

        }

        if (especificacionFechas == 2) {

            Integer FrecuenciaRevisionTasa =
                (null == ADFUtils.getBoundAttributeValue("FrecuenciaRevisionTasa") ||
                 ADFUtils.getBoundAttributeValue("FrecuenciaRevisionTasa").toString().equals("")) ? null :
                Integer.parseInt(ADFUtils.getBoundAttributeValue("FrecuenciaRevisionTasa").toString());

            Integer FrecuenciaPlazo =
                (null == ADFUtils.getBoundAttributeValue("FrecuenciaPlazo") ||
                 ADFUtils.getBoundAttributeValue("FrecuenciaPlazo").toString().equals("")) ? null :
                Integer.parseInt(ADFUtils.getBoundAttributeValue("FrecuenciaPlazo").toString());

            Integer tiempoDiasFrecuencia = null;
            Integer tiempoDiasFrecuenciaPlazo = null;
            Integer Cuotas = null;

            if (IdTcaFrecuenciaRevTasa != null) {
                ADFUtils.setBoundAttributeValue("IdTcaFrecuenciaRevTasa", IdTcaFrecuenciaRevTasa);
            }

            if (FrecuenciaRevisionTasa != null && IdTcaFrecuenciaRevTasa != null) {
                tiempoDiasFrecuencia = obtenerTiempoEnDias(FrecuenciaRevisionTasa, IdTcaFrecuenciaRevTasa);
            } else {
                logger.warning("*Inf, parametros requeridos null");
                logger.warning("*Inf, FrecuenciaRevisionTasa : " + FrecuenciaRevisionTasa);
                logger.warning("*Inf, IdTcaFrecuenciaRevTasa : " + IdTcaFrecuenciaRevTasa);
            }

            if (FrecuenciaPlazo != null && IdTcaFrecuenciaPlazo != null) {
                tiempoDiasFrecuenciaPlazo = obtenerTiempoEnDias(FrecuenciaPlazo, IdTcaFrecuenciaPlazo);
            } else {
                logger.warning("*Inf, parametros requeridos null");
                logger.warning("*Inf, FrecuenciaPlazo : " + FrecuenciaPlazo);
                logger.warning("*Inf, IdTcaFrecuenciaPlazo : " + IdTcaFrecuenciaPlazo);
            }


            //exepcion calculo de frecuencia meses plasos anios
            if (null != IdTcaFrecuenciaRevTasa && null != IdTcaFrecuenciaPlazo) {
                if (IdTcaFrecuenciaRevTasa.compareTo(2) == 0 && IdTcaFrecuenciaPlazo.compareTo(3) == 0) {
                    tiempoDiasFrecuenciaPlazo = obtenerTiempoEnDias(FrecuenciaPlazo, 4);
                    logger.warning("----->Inf,exeption  frecuencia meses plazos anios recalculando tiempo");
                }
            }

            if (tiempoDiasFrecuencia != null && tiempoDiasFrecuenciaPlazo != null) {
                if (tiempoDiasFrecuencia.compareTo(tiempoDiasFrecuenciaPlazo) != 1) {
                    if (tiempoDiasFrecuencia.compareTo(0) == 1 && tiempoDiasFrecuenciaPlazo.compareTo(0) == 1) {
                        Cuotas = tiempoDiasFrecuenciaPlazo / tiempoDiasFrecuencia;
                        
                        //las de revision se valida que si son mayor que 1 se le resta 1
                        if(Cuotas > 1)
                        {
                            Cuotas = Cuotas - 1;
                            logger.warning("----->Inf,Cuotas de Revision Tasa Corregidas: " + Cuotas);
                        }
                        ADFUtils.setBoundAttributeValue("NumeroRevisionesTasa", Cuotas);
                    } else {
                        logger.warning("***ERROR, TiempoDiasFrecuencia o tiempoDiasFrecuenciaPlazo son cero (0). No se calculara cuota.");
                    }
                } else {
                    ADFUtils.setBoundAttributeValue("NumeroRevisionesTasa", null);
                    logger.warning("***Error, El numero frecuencia es menor que el plazo no se calculara las cuotas");
                }
                //AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorNumRevisionTaza());
            } else {
                logger.warning("*Inf, parametros requeridos null");
                logger.warning("*Inf, tiempoDiasFrecuenciaTaza : " + tiempoDiasFrecuencia);
                logger.warning("*Inf, tiempoDiasFrecuenciaPlazo : " + tiempoDiasFrecuenciaPlazo);
            }
            logger.warning("Numero de Cuotas por plazo calculado : " + Cuotas);
        } else {
            logger.warning("*Inf, El valor de Especificacion de Fechas es por Fechas calculando....");
            calcularNumRevisionesTazaByIdTcaFrecuenciaRev(IdTcaFrecuenciaRevTasa);
        }
        logger.warning("*Inf, Termina el metodo calcularCuotasSecTazaPorPlazo");
    }
    
    /*
     * Josue
     * */
    
    public void calcularCuotasSecTazaPorPlazoI(ValueChangeEvent valueChangeEvent) {
        logger.warning("*Inf, Inicia el metodo calcularCuotasSecTazaPorPlazoI");

        Integer especificacionFechas =
            (null == ADFUtils.getBoundAttributeValue("IdTcaEspecificacionFecha") ||
             ADFUtils.getBoundAttributeValue("IdTcaEspecificacionFecha").toString().equals("")) ? null :
            Integer.parseInt(ADFUtils.getBoundAttributeValue("IdTcaEspecificacionFecha").toString());
        
        System.out.println("especificacionFechas --> "+especificacionFechas);
        Integer IdTcaFrecuenciaRevTasa = null;

        if (valueChangeEvent == null) {
            IdTcaFrecuenciaRevTasa =
                (null == ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaRevTasaI") ||
                 ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaRevTasaI").toString().equals("")) ? null :
                Integer.parseInt(ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaRevTasaI").toString());
        } else {

            System.out.println("IdTcaFrecuenciaRevTasa --> " + valueChangeEvent.getNewValue().toString());
            IdTcaFrecuenciaRevTasa =
                (null == valueChangeEvent.getNewValue() || valueChangeEvent.getNewValue().toString().equals("")) ?
                null : Integer.parseInt(valueChangeEvent.getNewValue().toString());

            //ComboBox Plazo de componente Principal
            IdTcaFrecuenciaPlazo =
                (null == ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPlazo") ||
                 ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPlazo").toString().equals("")) ? null :
                Integer.parseInt(ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPlazo").toString());

        }

        if (especificacionFechas == 2) {

            Integer FrecuenciaRevisionTasa =
                (null == ADFUtils.getBoundAttributeValue("FrecuenciaRevisionTasaI") ||
                 ADFUtils.getBoundAttributeValue("FrecuenciaRevisionTasaI").toString().equals("")) ? null :
                Integer.parseInt(ADFUtils.getBoundAttributeValue("FrecuenciaRevisionTasaI").toString());
            
            //input Plazo de componente Principal
            Integer FrecuenciaPlazo =
                (null == ADFUtils.getBoundAttributeValue("FrecuenciaPlazo") ||
                 ADFUtils.getBoundAttributeValue("FrecuenciaPlazo").toString().equals("")) ? null :
                Integer.parseInt(ADFUtils.getBoundAttributeValue("FrecuenciaPlazo").toString());

            Integer tiempoDiasFrecuencia = null;
            Integer tiempoDiasFrecuenciaPlazo = null;
            Integer Cuotas = null;

            if (IdTcaFrecuenciaRevTasa != null) {
                ADFUtils.setBoundAttributeValue("IdTcaFrecuenciaRevTasaI", IdTcaFrecuenciaRevTasa);
            }

            if (FrecuenciaRevisionTasa != null && IdTcaFrecuenciaRevTasa != null) {
                tiempoDiasFrecuencia = obtenerTiempoEnDias(FrecuenciaRevisionTasa, IdTcaFrecuenciaRevTasa);
            } else {
                logger.warning("*Inf, parametros requeridos null");
                logger.warning("*Inf, FrecuenciaRevisionTasa : " + FrecuenciaRevisionTasa);
                logger.warning("*Inf, IdTcaFrecuenciaRevTasa : " + IdTcaFrecuenciaRevTasa);
            }

            if (FrecuenciaPlazo != null && IdTcaFrecuenciaPlazo != null) {
                tiempoDiasFrecuenciaPlazo = obtenerTiempoEnDias(FrecuenciaPlazo, IdTcaFrecuenciaPlazo);
            } else {
                logger.warning("*Inf, parametros requeridos null");
                logger.warning("*Inf, FrecuenciaPlazo : " + FrecuenciaPlazo);
                logger.warning("*Inf, IdTcaFrecuenciaPlazo : " + IdTcaFrecuenciaPlazo);
            }


            //exepcion calculo de frecuencia meses plasos anios
            if (null != IdTcaFrecuenciaRevTasa && null != IdTcaFrecuenciaPlazo) {
                if (IdTcaFrecuenciaRevTasa.compareTo(2) == 0 && IdTcaFrecuenciaPlazo.compareTo(3) == 0) {
                    tiempoDiasFrecuenciaPlazo = obtenerTiempoEnDias(FrecuenciaPlazo, 4);
                    logger.warning("----->Inf,exeption  frecuencia meses plazos anios recalculando tiempo");
                }
            }

            if (tiempoDiasFrecuencia != null && tiempoDiasFrecuenciaPlazo != null) {
                if (tiempoDiasFrecuencia.compareTo(tiempoDiasFrecuenciaPlazo) != 1) {
                    if (tiempoDiasFrecuencia.compareTo(0) == 1 && tiempoDiasFrecuenciaPlazo.compareTo(0) == 1) {
                        Cuotas = tiempoDiasFrecuenciaPlazo / tiempoDiasFrecuencia;
                        //las de revision se valida que si son mayor que 1 se le resta 1
                        if(Cuotas > 1)
                        {
                            Cuotas = Cuotas - 1;
                            logger.warning("----->Inf,Cuotas de Revision Interes Corregidas: " + Cuotas);
                        }
                        
                        ADFUtils.setBoundAttributeValue("NumeroRevisionesTasaI", Cuotas);
                    } else {
                        logger.warning("***ERROR, TiempoDiasFrecuencia o tiempoDiasFrecuenciaPlazo son cero (0). No se calculara cuota.");
                    }
                } else {
                    ADFUtils.setBoundAttributeValue("NumeroRevisionesTasaI", null);
                    logger.warning("***Error, El numero frecuencia es menor que el plazo no se calculara las cuotas");
                }
                //AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorNumRevisionTaza());
            } else {
                logger.warning("*Inf, parametros requeridos null");
                logger.warning("*Inf, tiempoDiasFrecuenciaTaza : " + tiempoDiasFrecuencia);
                logger.warning("*Inf, tiempoDiasFrecuenciaPlazo : " + tiempoDiasFrecuenciaPlazo);
            }
            logger.warning("Numero de Cuotas por plazo calculado : " + Cuotas);
            
            //Si las cuotas calculadas en el rev de interes no son null, se envia a calcular a plazos las fechas de revision de interes
            if(Cuotas != null)
            {
                Date fechaEfectiva = obtenerFechaEstimadaAdesembolsar();
                java.sql.Timestamp fechaDisponibilidadFondos = null;
                Date fechaDisponibilidadFondosDate = null;
                Date fechaProxRevTasa = null;
                Date fechaVencimiento = null;
                CondicionesFinancierasBean condicionesBean = null;
                condicionesBean = (CondicionesFinancierasBean) JSFUtils.resolveExpression("#{pageFlowScope.CondicionFinancieraBean}");
                
                fechaDisponibilidadFondos = obtenerFechaDisponibilidadFondos();

                if (fechaDisponibilidadFondos != null) {
                    fechaDisponibilidadFondosDate = new Date(fechaDisponibilidadFondos.getTime());
                    fechaEfectiva = fechaDisponibilidadFondosDate;
                }
                
                fechaVencimiento =
                    (null == ADFUtils.getBoundAttributeValue("FechaVencimiento")) ? null :
                    (Date) ADFUtils.getBoundAttributeValue("FechaVencimiento");
                
                if (condicionesBean.getTasaVariableVisible()) {
                    logger.warning("*Inf, calculando fechaPrimerRevicionTasa...");

                    try {
                        fechaProxRevTasa = addTiempo(fechaEfectiva, FrecuenciaRevisionTasa, IdTcaFrecuenciaRevTasa);
                    } catch (Exception e) {
                        JSFUtils.addFacesErrorMessage("ha ocurrido un error al calcular la fecha de primera revicion de tasa");
                    }
                    logger.warning("*Inf, fechaProxRevTasa calculada ->" + fechaProxRevTasa);
                }
                
                if(fechaVencimiento != null && fechaProxRevTasa != null)
                {
                            
                    if (condicionesBean.getLecturaIFI() != null && condicionesBean.getLecturaIFI()) 
                    {
                        //Fechas Temporales para evitar error, no se usan realmente
                        Date fechaPrimerPagoCapital = fechaProxRevTasa;
                        Date fechaProxPagInteres = fechaProxRevTasa;
                        Date fechaProxRevSpread = fechaProxRevTasa;
                        
                        Map fechasAlineadas =
                                            alinearFechasPagoRevicionYVencimiento(fechaPrimerPagoCapital, fechaProxPagInteres, fechaProxRevTasa,
                                                                                  fechaProxRevSpread, fechaVencimiento);
                        
                        fechaProxRevTasa = (Date) fechasAlineadas.get("fechaProxRevTasa");
                    } else {
                        logger.warning("*Inf, la operacion no es IFI no se realizaran alineaciones");
                    }
                    //Se asignan las fechas a los viewObjects
                    ADFUtils.setBoundAttributeValue("FechaProximaRevisionTasaI", fechaProxRevTasa);
                }
            } //Termina el calculo de fechas
            
        } else {
            logger.warning("*Inf, El valor de Especificacion de Fechas es por Fechas calculando....");
            calcularNumRevisionesTazaByIdTcaFrecuenciaRevI(IdTcaFrecuenciaRevTasa);
        }
        logger.warning("*Inf, Termina el metodo calcularCuotasSecTazaPorPlazo");
    }
    
    /*
     * */


    public void calcularCuotasSecSpreadPorPlazo(ValueChangeEvent valueChangeEvent) {
        logger.warning("*Inf, Inicia el metodo calcularCuotasSecSpreadPorPlazo");

        CondicionesFinancierasBean condicionesFinancierasBean = null;
        condicionesFinancierasBean =
            (CondicionesFinancierasBean) JSFUtils.resolveExpression("#{pageFlowScope.CondicionFinancieraBean}");

        Integer especificacionFechas =
            (null == ADFUtils.getBoundAttributeValue("IdTcaEspecificacionFecha") ||
             ADFUtils.getBoundAttributeValue("IdTcaEspecificacionFecha").toString().equals("")) ? null :
            Integer.parseInt(ADFUtils.getBoundAttributeValue("IdTcaEspecificacionFecha").toString());

        Integer IdTcaFrecuenciaRevSpread = null;

        if (valueChangeEvent == null) {
            IdTcaFrecuenciaRevSpread =
                (null == ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaRevSpread") ||
                 ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaRevSpread").toString().equals("")) ? null :
                Integer.parseInt(ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaRevSpread").toString());
        } else {

            System.out.println("IdTcaFrecuenciaRevSpread --> " + valueChangeEvent.getNewValue().toString());
            IdTcaFrecuenciaRevSpread =
                (null == valueChangeEvent.getNewValue() || valueChangeEvent.getNewValue().toString().equals("")) ?
                null : Integer.parseInt(valueChangeEvent.getNewValue().toString());

            IdTcaFrecuenciaPlazo =
                (null == ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPlazo") ||
                 ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPlazo").toString().equals("")) ? null :
                Integer.parseInt(ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPlazo").toString());
        }

        if (especificacionFechas == 2) {

            Integer FrecuenciaRevisionSpread =
                (null == ADFUtils.getBoundAttributeValue("FrecuenciaRevisionSpread") ||
                 ADFUtils.getBoundAttributeValue("FrecuenciaRevisionSpread").toString().equals("")) ? null :
                Integer.parseInt(ADFUtils.getBoundAttributeValue("FrecuenciaRevisionSpread").toString());

            Integer FrecuenciaPlazo =
                (null == ADFUtils.getBoundAttributeValue("FrecuenciaPlazo") ||
                 ADFUtils.getBoundAttributeValue("FrecuenciaPlazo").toString().equals("")) ? null :
                Integer.parseInt(ADFUtils.getBoundAttributeValue("FrecuenciaPlazo").toString());
            
            Integer Cuotas = null;
            
            if (IdTcaFrecuenciaRevSpread == null) {
                ADFUtils.setBoundAttributeValue("NumeroRevisionesSpread", null);
                condicionesFinancierasBean.setFrecRevSpreadVisible(Boolean.TRUE);
                AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorSpreadVariable());
            } else if (IdTcaFrecuenciaRevSpread.compareTo(tcaEstadoAlVencimiento) == 0) {
                logger.warning("*Inf, tcaEstado seleccionado es al vencimeto establecioendo numero de cuotas a 1");
                ADFUtils.setBoundAttributeValue("IdTcaFrecuenciaRevSpread", IdTcaFrecuenciaRevSpread);
                ADFUtils.setBoundAttributeValue("NumeroRevisionesSpread", 1);
                ADFUtils.setBoundAttributeValue("FrecuenciaRevisionSpread", 1);
                condicionesFinancierasBean.setFrecRevSpreadVisible(Boolean.FALSE);
                Cuotas = 1;

                //AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorNumRevisionesSpread());
                AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorSpreadVariable());
            } else {
                condicionesFinancierasBean.setFrecRevSpreadVisible(Boolean.TRUE);
                AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorSpreadVariable());

                Integer tiempoDiasFrecuencia = null;
                Integer tiempoDiasFrecuenciaPlazo = null;

                if (FrecuenciaRevisionSpread != null && IdTcaFrecuenciaRevSpread != null) {
                    tiempoDiasFrecuencia = obtenerTiempoEnDias(FrecuenciaRevisionSpread, IdTcaFrecuenciaRevSpread);
                } else {
                    logger.warning("*Inf, parametros requeridos null");
                    logger.warning("*Inf, FrecuenciaRevisionSpread : " + FrecuenciaRevisionSpread);
                    logger.warning("*Inf, IdTcaFrecuenciaRevSpread : " + IdTcaFrecuenciaRevSpread);
                }

                if (FrecuenciaPlazo != null && IdTcaFrecuenciaPlazo != null) {
                    tiempoDiasFrecuenciaPlazo = obtenerTiempoEnDias(FrecuenciaPlazo, IdTcaFrecuenciaPlazo);
                } else {
                    logger.warning("*Inf, parametros requeridos null");
                    logger.warning("*Inf, FrecuenciaPlazo : " + FrecuenciaPlazo);
                    logger.warning("*Inf, IdTcaFrecuenciaPlazo : " + IdTcaFrecuenciaPlazo);
                }

                //exepcion calculo de frecuencia meses plasos anios
                if (IdTcaFrecuenciaRevSpread != null & IdTcaFrecuenciaPlazo != null) {
                    if (IdTcaFrecuenciaRevSpread == 2 & IdTcaFrecuenciaPlazo == 3) {
                        tiempoDiasFrecuenciaPlazo = obtenerTiempoEnDias(FrecuenciaPlazo, 4);
                        logger.warning("----->Inf,exeption  frecuencia meses plazos anios recalculando tiempo");
                    }
                }

                if (tiempoDiasFrecuencia != null && tiempoDiasFrecuenciaPlazo != null) {
                    if (tiempoDiasFrecuencia.compareTo(tiempoDiasFrecuenciaPlazo) != 1) {
                        if (tiempoDiasFrecuencia.compareTo(0) == 1 && tiempoDiasFrecuenciaPlazo.compareTo(0) == 1) {
                            Cuotas = tiempoDiasFrecuenciaPlazo / tiempoDiasFrecuencia;
                            
                            //las de revision se valida que si son mayor que 1 se le resta 1
                            if(Cuotas > 1)
                            {
                                Cuotas = Cuotas - 1;
                                logger.warning("----->Inf,Cuotas de Revision Spread Corregidas: " + Cuotas);
                            }
                            ADFUtils.setBoundAttributeValue("NumeroRevisionesSpread", Cuotas);
                        } else {
                            logger.warning("***ERROR, TiempoDiasFrecuencia o tiempoDiasFrecuenciaPlazo son cero (0). No se calculara cuota.");
                        }
                    } else {
                        ADFUtils.setBoundAttributeValue("NumeroRevisionesSpread", null);
                        logger.warning("***Error, El numero frecuencia es menor que el plazo no se calculara las cuotas");
                    }
                    //AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorNumRevisionesSpread());
                } else {
                    logger.warning("*Inf, parametros requeridos null");
                    logger.warning("*Inf, tiempoDiasFrecuenciaSpread : " + tiempoDiasFrecuencia);
                    logger.warning("*Inf, tiempoDiasFrecuenciaPlazo : " + tiempoDiasFrecuenciaPlazo);
                }
                logger.warning("Numero de Cuotas por plazo calculado : " + Cuotas);
            }
            
            //Si las cuotas calculadas en el spread no son null, se envia a calcular a plazos las fechas de spread
            if(Cuotas != null)
            {
                Date fechaEfectiva = obtenerFechaEstimadaAdesembolsar();
                java.sql.Timestamp fechaDisponibilidadFondos = null;
                Date fechaDisponibilidadFondosDate = null;
                Date fechaProxRevSpread = null;
                Date fechaVencimiento = null;
                CondicionesFinancierasBean condicionesBean = null;
                condicionesBean = (CondicionesFinancierasBean) JSFUtils.resolveExpression("#{pageFlowScope.CondicionFinancieraBean}");
                
                fechaDisponibilidadFondos = obtenerFechaDisponibilidadFondos();

                if (fechaDisponibilidadFondos != null) {
                    fechaDisponibilidadFondosDate = new Date(fechaDisponibilidadFondos.getTime());
                    fechaEfectiva = fechaDisponibilidadFondosDate;
                }
                
                fechaVencimiento =
                    (null == ADFUtils.getBoundAttributeValue("FechaVencimiento")) ? null :
                    (Date) ADFUtils.getBoundAttributeValue("FechaVencimiento");
                
                if (condicionesBean.getMostrarSpreadVariable()) 
                {
                    logger.warning("*Inf, calculando fechaPrimerRevisionSpread...");

                    try {
                        fechaProxRevSpread = addTiempo(fechaEfectiva, FrecuenciaRevisionSpread, IdTcaFrecuenciaRevSpread);
                    } catch (Exception e) {
                        JSFUtils.addFacesErrorMessage("ha ocurrido un error al calcular la fecha de primer revision de spreed");
                    }

                    logger.warning("*Inf, fechaProxRevSpread calculada ->" + fechaProxRevSpread);

                }
                
                if(fechaVencimiento != null && fechaProxRevSpread != null)
                {                                
                    if (condicionesBean.getLecturaIFI() != null && condicionesBean.getLecturaIFI()) {
                        //Fechas Temporales para evitar error, no se usan realmente
                        Date fechaPrimerPagoCapital = fechaProxRevSpread;
                        Date fechaProxRevTasa = fechaProxRevSpread;
                        Date fechaProxPagInteres = fechaProxRevSpread;
                        
                        Map fechasAlineadas =
                                            alinearFechasPagoRevicionYVencimiento(fechaPrimerPagoCapital, fechaProxPagInteres, fechaProxRevTasa,
                                                                                  fechaProxRevSpread, fechaVencimiento);
                        
                        fechaProxRevSpread = (Date) fechasAlineadas.get("fechaProxRevSpread");
                    } else {
                        logger.warning("*Inf, la operacion no es IFI no se realizaran alineaciones");
                    }
                    //Se asignan las fechas a los viewObjects
                    ADFUtils.setBoundAttributeValue("FechaProximaRevisionSpread", fechaProxRevSpread);
                }
            } //Termina el calculo de fechas


        } else {
            logger.warning("*Inf, El valor de Especificacion de Fechas es por Fechas no se calcularan las cuotas por plazo");
            calcularNumRevisionesSpreadByIdTcaFrecuenciaRev(IdTcaFrecuenciaRevSpread);
        }
        logger.warning("*Inf, Termina el metodo calcularCuotasSecSpreadPorPlazo");
    }

    public Integer obtenerTiempoEnDias(Integer frecuencia, Integer tiempoReal) {
        logger.warning("*Inf, Inicia metodo obtenerTiempoEnDias");
        Integer tiempoDias = null;

        switch (tiempoReal) {
        case 1:
            tiempoDias = frecuencia; //Dias
            break;
        case 2:
            tiempoDias = frecuencia * 30; //meses
            break;
        case 3:
            tiempoDias = frecuencia * 365; //años
            break;
        case 4:
            tiempoDias = frecuencia * 360; // exepcion años
            break;

        }
        logger.warning("*Inf, termina metodo obtenerTiempoEnDias tiempo calculado : " + tiempoDias);
        return tiempoDias;
    }

    @SuppressWarnings("unchecked")
    public void recuperarIdProductoFlex(ValueChangeEvent valueChangeEvent) {
        logger.warning("Inf, Inicia el metodo recuperarIdProductoFlex");
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        String id = valueChangeEvent.getNewValue() == null ? null : valueChangeEvent.getNewValue().toString();
        logger.warning("*Inf,---- id : " + id);

        CondicionesFinancierasBean condicionesFinancierasBean = null;
        condicionesFinancierasBean =
            (CondicionesFinancierasBean) JSFUtils.resolveExpression("#{pageFlowScope.CondicionFinancieraBean}");

        try {
            if (id != null) {
                BindingContainer binding = ADFUtils.getBindingContainer();
                OperationBinding operBinding = binding.getOperationBinding("consultarSpreadVariableById");
                operBinding.getParamsMap().put("id", id);
                Boolean respuesta = (Boolean) operBinding.execute();
                logger.warning("Valor obtenido en consultarSpreadVariableById: " + respuesta);
                condicionesFinancierasBean.setMostrarSpreadVariable(respuesta);
                contenedorAllSpreadVariableNew.setVisible(respuesta);
                AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorAllSpreadVariableNew());
                
                //Si no se muestra la seccion de spread variable al cambiar de producto deben limpiarse la tasa de referencia y el valor de la tasa de referencia
                if(!respuesta)
                {
                    //Limpiamos los campos
                    ADFUtils.setBoundAttributeValue("DescripcionSpread2", null);   
                    ADFUtils.setBoundAttributeValue("CodigoTasaReferenciaSpread", null);
                    ADFUtils.setBoundAttributeValue("ValorTasaReferenciaSpread", null);
                }
                
                if (!operBinding.getErrors().isEmpty()) {
                    logger.warning("***Error, al ejecutar operation Binding consultarSpreadVariableById " +
                                   operBinding.getErrors());
                }


                Long idCondicion = null;
                Long idLineaCredito=null;
                logger.warning("IdCondicionFinanciera --> " + condicionesFinancierasBean.getIdCondicionFinanciera());
                logger.warning("IdLineaCredito --> " + condicionesFinancierasBean.getIdLineaCredito());
                if (null != condicionesFinancierasBean.getIdCondicionFinanciera() && null != condicionesFinancierasBean.getIdLineaCredito()) {
                    idCondicion = condicionesFinancierasBean.getIdCondicionFinanciera();
                    idLineaCredito = condicionesFinancierasBean.getIdLineaCredito();
                   
                } else {
                    logger.warning("IdCondicionFinanciera es NULL");
                }

            } else {
                logger.warning("El id obtenido en recuperarIdByDescripcion es null, el valor de consultarSpreadVariableById es false");
                condicionesFinancierasBean.setMostrarSpreadVariable(Boolean.FALSE);
                contenedorAllSpreadVariableNew.setVisible(Boolean.FALSE);
                AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorAllSpreadVariableNew());
            }
        } catch (Exception e) {
            logger.warning("***Error, al ejecutar operation Binding consultarSpreadVariableById " + e);
        }

        logger.warning("*Inf, termina el metodo recuperarIdProductoFlex");
    }


    //El valor que se guardara en BD es el codigo que acompaña a la descripcion de la Tasa
    public void asignaBindingCodigoTasa(String codigo) {
        logger.warning("Inside asignaBindingCodigoTasa.");

        AttributeBinding bindingCodigoTasaReferencia;
        bindingCodigoTasaReferencia = ADFUtils.findControlBinding("CodigoTasaReferencia");

        bindingCodigoTasaReferencia.setInputValue(codigo);

        logger.warning("CodigoTasaReferencia: " + codigo);
    }
    
    //El valor que se guardara en BD es el codigo que acompaña a la descripcion de la Tasa
        public void asignaBindingCodigoTasaI(String codigo) {
            logger.warning("Inside asignaBindingCodigoTasaI.");

            AttributeBinding bindingCodigoTasaReferencia;
            bindingCodigoTasaReferencia = ADFUtils.findControlBinding("CodigoTasaReferenciaI");

            bindingCodigoTasaReferencia.setInputValue(codigo);

            logger.warning("CodigoTasaReferenciaI: " + codigo);
        }

    //El valor que se guardara en BD es el codigo que acompaña a la descripcion del Spread
    public void asignaBindingCodigoSpread(String codigo) {
        logger.warning("Inside asignaBindingCodigoSpread.");

        AttributeBinding bindingCodigoTasaReferencia;
        bindingCodigoTasaReferencia = ADFUtils.findControlBinding("CodigoTasaReferenciaSpread");

        bindingCodigoTasaReferencia.setInputValue(codigo);

        logger.warning("CodigoTasaReferenciaSpread: " + codigo);
    }

    public void valorSpreadValueChange(ValueChangeEvent valueChangeEvent) {
        logger.warning("Inside valorSpreadValueChange.");
        logger.warning("valueNew: " + valueChangeEvent.getNewValue());

        AttributeBinding bindingTasaTotal;
        bindingTasaTotal = ADFUtils.findControlBinding("TasaTotal");
        AttributeBinding bindingSpreadTasa;
        bindingSpreadTasa = ADFUtils.findControlBinding("SpreadTasa");
        BigDecimal valorTasaSpread = (BigDecimal) valueChangeEvent.getNewValue();
        BigDecimal valorTasa = (BigDecimal) ADFUtils.getBoundAttributeValue("ValorTasa");
        BigDecimal valorTasaReferenciaSpread =
            (BigDecimal) ADFUtils.getBoundAttributeValue("ValorTasaReferenciaSpread");

        logger.warning("valorTasa: " + valorTasa);

        if (valorTasaReferenciaSpread != null) {
            bindingSpreadTasa.setInputValue(valorTasaReferenciaSpread);
        } else {
            if (valorTasa != null) {
                bindingTasaTotal.setInputValue(calcularTasaTotal(valorTasaSpread, valorTasa));
            } else {
                if (valorTasaSpread != null)
                    ADFUtils.setBoundAttributeValue("TasaTotal", valorTasaSpread);

            }
        }

    }
    
    /*
     * VK
     * */
    
    public void valorSpreadValueChangeI(ValueChangeEvent valueChangeEvent) {
        logger.warning("Inside valorSpreadValueChangeI.");
        logger.warning("valueNew: " + valueChangeEvent.getNewValue());

        AttributeBinding bindingTasaTotal;
        bindingTasaTotal = ADFUtils.findControlBinding("TasaTotalI");
        AttributeBinding bindingSpreadTasa;
        bindingSpreadTasa = ADFUtils.findControlBinding("SpreadI");
        BigDecimal valorTasaSpread = (BigDecimal) valueChangeEvent.getNewValue();
        BigDecimal valorTasa = (BigDecimal) ADFUtils.getBoundAttributeValue("ValorTasaI");
        BigDecimal valorTasaReferenciaSpread =
            (BigDecimal) ADFUtils.getBoundAttributeValue("ValorTasaReferenciaSpread");

        logger.warning("valorTasa: " + valorTasa);

        if (valorTasaReferenciaSpread != null) {
            bindingSpreadTasa.setInputValue(valorTasaReferenciaSpread);
        } else {
            if (valorTasa != null) {
                bindingTasaTotal.setInputValue(calcularTasaTotal(valorTasaSpread, valorTasa));
            } else {
                if (valorTasaSpread != null)
                    ADFUtils.setBoundAttributeValue("TasaTotalI", valorTasaSpread);

            }
        }

    }
    /*
     * */

    public BigDecimal calcularTasaTotal(BigDecimal tasaReferencia, BigDecimal valorSpread) {
        logger.warning("Inside calcularTasaTotal.");

        BigDecimal tasaTotal = null;
        tasaReferencia = (tasaReferencia == null) ? BigDecimal.ZERO : tasaReferencia;
        valorSpread = (tasaReferencia == null) ? BigDecimal.ZERO : valorSpread;

        tasaTotal = tasaReferencia.add(valorSpread);

        logger.warning("tasaTotal: " + tasaTotal);

        return tasaTotal;
    }
    
    public List onSuggestTasaReferencia4(String string){
        logger.warning("*** Into method onSuggestTasaReferencia4");
        ArrayList<SelectItem> resultItems = new ArrayList<SelectItem>();
        SelectItem element = null;

        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();
        JUCtrlListBinding list = (JUCtrlListBinding) bindings.get("DescripcionTasa");

        Row[] values = list.getAllRowsInRange();
        if (null != values) {
            for (Row obj : values) {
                String value = (String) obj.getAttribute("CodigoTasaReferencia");
                if (null != string && null != value) {
                    if (value.toUpperCase().contains(string.toUpperCase())) {
                        element = new SelectItem(value);
                        resultItems.add(element);
                    }
                }
            }
        } else {
            element = new SelectItem("");
            resultItems.add(element);
        }

        return resultItems;
    }
    
    public List onSuggestTasaReferencia3(String string){
        logger.warning("*** Into method onSuggestTasaReferencia3");
        ArrayList<SelectItem> resultItems = new ArrayList<SelectItem>();
        SelectItem element = null;

        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();
        JUCtrlListBinding list = (JUCtrlListBinding) bindings.get("DescripcionTasa");

        Row[] values = list.getAllRowsInRange();
        if (null != values) {
            for (Row obj : values) {
                String value = (String) obj.getAttribute("CodigoTasaReferencia");
                if (null != string && null != value) {
                    if (value.toUpperCase().contains(string.toUpperCase())) {
                        element = new SelectItem(value);
                        resultItems.add(element);
                    }
                }
            }
        } else {
            element = new SelectItem("");
            resultItems.add(element);
        }

        return resultItems;
    }

    public List onSuggestTasaReferencia2(String string) {
        logger.warning("*** Into method onSuggestTasaReferencia");
        ArrayList<SelectItem> resultItems = new ArrayList<SelectItem>();
        ;
        SelectItem element = null;

        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();
        JUCtrlListBinding list = (JUCtrlListBinding) bindings.get("DescripcionTasa");

        Row[] values = list.getAllRowsInRange();
        if (null != values) {
            for (Row obj : values) {
                String value = (String) obj.getAttribute("CodigoTasaReferencia");
                if (null != string && null != value) {
                    if (value.toUpperCase().contains(string.toUpperCase())) {
                        element = new SelectItem(value);
                        resultItems.add(element);
                    }
                }
            }
        } else {
            element = new SelectItem("");
            resultItems.add(element);
        }

        return resultItems;
    }


    public List onSuggestTasaReferencia(String string) {
        logger.warning("*Inf, Into method onSuggestTasaReferencia");
        ArrayList<SelectItem> resultItems = new ArrayList<SelectItem>();
        SelectItem element = null;
        CondicionesFinancierasBean condicionesFinancierasBean = null;
        condicionesFinancierasBean =
            (CondicionesFinancierasBean) JSFUtils.resolveExpression("#{pageFlowScope.CondicionFinancieraBean}");

        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();
        JUCtrlListBinding list = (JUCtrlListBinding) bindings.get("DescripcionTasa");

        RowSetIterator iter = list.getListRowSetIterator();
        iter.reset();
        Row row = null;
        int coincidencias = 0;

        logger.warning("*Inf, Num de cuentas recuperado para la operacion : " + list.getAllRowsInRange().length);
        if (null != iter) {

            for (int i = 0; i < list.getAllRowsInRange().length; i++) {
                row = iter.getRowAtRangeIndex(i);
                if (null != row) {
                    String value = (String) row.getAttribute("CodigoTasaReferencia");
                    if (null != string && null != value) {

                        if (value.toUpperCase().contains(string.toUpperCase())) {
                            coincidencias = coincidencias + 1;
                            element = new SelectItem(coincidencias + " " + value);
                            resultItems.add(element);
                            condicionesFinancierasBean.setEsOnSuggestTasaReferencia(Boolean.TRUE);
                        }
                    } else {
                        logger.warning("*Inf, Important! String " + string + " valor en lista " + value);
                    }
                }
            }

        } else {
            logger.warning("*Inf, Important! Iterador NULL");
            element = new SelectItem("");
            resultItems.add(element);
        }

        iter.closeRowSetIterator();
        logger.warning("*Inf, Numero de coincidencias encontradas " + coincidencias);
        return resultItems;
    }
    public List obtenerTasaReferenciaPorDescripcion(String descripcion) {
            logger.warning("Dentro obtenerTasaReferenciaPorDescripcion onSuggest descripcion: " + descripcion);
            List<SelectItem> resultItems = new ArrayList<SelectItem>();
            Row[] rowList = null;
            BindingContainer bindings = ADFUtils.getBindingContainer();
            DCBindingContainer bc = (DCBindingContainer) getBindings();
            try {
                OperationBinding operationBinding = bindings.getOperationBinding("obtenerDescripcionTasaReferencia");
                operationBinding.getParamsMap().put("codigo",descripcion);
                operationBinding.execute();
                if(operationBinding.getErrors().isEmpty()) {
                    // Asignación para Monto aprobado
                    rowList = (Row[])operationBinding.getResult();
                    for(Row row : rowList){
                        resultItems.add(new SelectItem(row.getAttribute("CodigoTasaReferencia")));
                    }
                } else {
                    logger.severe("Error al ejecutar el operation binding obtenerDescripcionTasaReferencia");
                }
               
            } catch (Exception e) {
                logger.severe("Error en obtenerTasaReferenciaPorDescripcion", e);
            }
            logger.warning("Fuera obtenerTasaReferenciaPorDescripcion");
            return resultItems;
        }
    


    public List onSuggestSpreadReferencia(String string) {
        logger.warning("*** Into method onSuggestSpreadReferencia");
        ArrayList<SelectItem> resultItems = new ArrayList<SelectItem>();
        int contador = 0;
        SelectItem element = null;

        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();
        JUCtrlListBinding list = (JUCtrlListBinding) bindings.get("DescripcionTasa1");

        Row[] values = list.getAllRowsInRange();
        if (null != values) {
            for (Row obj : values) {
                String value = (String) obj.getAttribute("CodigoTasaReferenciaSpread");
                if (null != string && null != value) {
                    if (value.toUpperCase().contains(string.toUpperCase())) {
                        contador = contador + 1;
                        element = new SelectItem(contador + ". " + value);
                        resultItems.add(element);
                    }
                }
            }
        } else {
            element = new SelectItem("");
            resultItems.add(element);
        }

        return resultItems;
    }
    
    public List obtenerSpreadReferenciaPorDescripcion(String descripcion) {
            logger.warning("Dentro obtenerSpreadReferenciaPorDescripcion onSuggest descripcion: " + descripcion);
            List<SelectItem> resultItems = new ArrayList<SelectItem>();
            Row[] rowList = null;
            BindingContainer bindings = ADFUtils.getBindingContainer();
            DCBindingContainer bc = (DCBindingContainer) getBindings();
            try {
                OperationBinding operationBinding = bindings.getOperationBinding("obtenerDescripcionSpreadReferencia");
                operationBinding.getParamsMap().put("codigo",descripcion);
                operationBinding.execute();
                if(operationBinding.getErrors().isEmpty()) {
                    // Asignación para Monto aprobado
                    rowList = (Row[])operationBinding.getResult();
                    for(Row row : rowList){
                        resultItems.add(new SelectItem(row.getAttribute("CodigoTasaReferenciaSpread")));
                    }
                } else {
                    logger.severe("Error al ejecutar el operation binding obtenerDescripcionSpreadReferencia");
                }
               
            } catch (Exception e) {
                logger.severe("Error en obtenerSpreadReferenciaPorDescripcion", e);
            }
            logger.warning("Fuera obtenerSpreadReferenciaPorDescripcion");
            return resultItems;
        }
    
    public void asignarAlinearDiaPagoExceptoVencimiento(){
        logger.warning("Inicia metodo asignarAlinearDiaPagoExceptoVencimiento");
        Integer frecuenciaPlazo = null;
        Integer tipoFrecuenciaPlazo = null;

        try {
            frecuenciaPlazo = (Integer) ADFUtils.getBoundAttributeValue("FrecuenciaPlazo");
        } catch (Exception e) {
            logger.warning("ERROR al castear FrecuenciaPlazo.", e);
        }

        try {
            if (null != ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPlazo") &&
                !ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPlazo").toString().trim().isEmpty()) {

                tipoFrecuenciaPlazo =
                    Integer.valueOf(ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPlazo").toString());
            } else {
                logger.warning("IdTcaFrecuenciaPlazo es nulo o vacio.");
            }
        } catch (Exception e) {
            logger.warning("ERROR al castear IdTcaFrecuenciaPlazo.", e);
        }

        logger.warning("Tipo frecuencia de plazo: " + tipoFrecuenciaPlazo + ", Frecuencia: " + frecuenciaPlazo);
        if (null != tipoFrecuenciaPlazo && null != frecuenciaPlazo) {
            switch (tipoFrecuenciaPlazo) {
            case 1:
                logger.warning("Evaluando frecuencia en días.");
                if (!(frecuenciaPlazo.compareTo(365) == 1)) {
                    logger.warning("La frecuencia es menor o igual a 365 días.");
                    ADFUtils.setBoundAttributeValue("SePuedeAlinearDiaPago1", 1);
                    ADFUtils.setBoundAttributeValue("ExceptoVencimiento1", 1);
                } else {
                    logger.warning("La frecuencia es mayor a 365 días.");
                    /* FNXII-6809.
                    ADFUtils.setBoundAttributeValue("SePuedeAlinearDiaPago1", 0);
                    ADFUtils.setBoundAttributeValue("ExceptoVencimiento1", 0);
                    */
                }
                break;
            case 2:
                logger.warning("Evaluando frecuencia en meses.");
                if (!(frecuenciaPlazo.compareTo(12) == 1)) {
                    logger.warning("La frecuencia es menor o igual a 12 meses.");
                    ADFUtils.setBoundAttributeValue("SePuedeAlinearDiaPago1", 1);
                    ADFUtils.setBoundAttributeValue("ExceptoVencimiento1", 1);
                } else {
                    logger.warning("La frecuencia es mayor a 12 meses.");
                    /* FNXII-6809.
                    ADFUtils.setBoundAttributeValue("SePuedeAlinearDiaPago1", 0);
                    ADFUtils.setBoundAttributeValue("ExceptoVencimiento1", 0);
                    */
                }
                break;
            case 3:
                logger.warning("Evaluando frecuencia en años.");
                if (!(frecuenciaPlazo.compareTo(1) == 1)) {
                    logger.warning("La frecuencia es menor o igual a 1 año.");
                    ADFUtils.setBoundAttributeValue("SePuedeAlinearDiaPago1", 1);
                    ADFUtils.setBoundAttributeValue("ExceptoVencimiento1", 1);
                } else {
                    logger.warning("La frecuencia es mayor a 1 año.");
                    /* FNXII-6809.
                    ADFUtils.setBoundAttributeValue("SePuedeAlinearDiaPago1", 0);
                    ADFUtils.setBoundAttributeValue("ExceptoVencimiento1", 0);
                    */
                }
                break;
            default:
                logger.warning("REfrescando Form de checkBox.");
                AdfFacesContext.getCurrentInstance().addPartialTarget(getPflCheckBoxUIC());
            }
        } else {
            logger.warning("Datos requeridos NULL.");
            /* FNXII-6809.
            ADFUtils.setBoundAttributeValue("SePuedeAlinearDiaPago1", 0);
            ADFUtils.setBoundAttributeValue("ExceptoVencimiento1", 0);
            AdfFacesContext.getCurrentInstance().addPartialTarget(getPflCheckBoxUIC());
            */
        }

        logger.warning("AlinearDiaPago: " + ADFUtils.getBoundAttributeValue("SePuedeAlinearDiaPago1") +
                       ", ExceptoVencimiento: " + ADFUtils.getBoundAttributeValue("ExceptoVencimiento1"));
        logger.warning("Termina metodo asignarAlinearDiaPagoExceptoVencimiento");
    }

    public void asigranDiaPagoExceptoVencimientoPorFecha() {
        logger.warning("Inicia metodo asigranDiaPagoExceptoVencimientoPorFecha.");
        java.sql.Timestamp fechaPrimerPagoCapital = null;
        java.sql.Timestamp fechaVencimiento = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String fechaInicio = null;
        String fechaFin = null;

        try {
            fechaPrimerPagoCapital = (java.sql.Timestamp) ADFUtils.getBoundAttributeValue("FechaPrimerPagoCapital");
        } catch (Exception e) {
            logger.warning("ERROR al obtener BoundAttribute de FechaPrimerPagoCapital", e);
        }

        try {
            fechaVencimiento = (java.sql.Timestamp) ADFUtils.getBoundAttributeValue("FechaVencimiento");
        } catch (Exception e) {
            logger.warning("ERROR al obtener BoundAttribute de FechaVencimiento", e);
        }

        logger.warning("FechaInicioCapital: " + fechaPrimerPagoCapital + ", FechaVencimiento: " + fechaVencimiento);
        if (null != fechaPrimerPagoCapital && null != fechaVencimiento) {
            logger.warning("Parseando fechas.");
            fechaInicio = eliminarTiempoFecha(fechaPrimerPagoCapital.toString());
            fechaFin = eliminarTiempoFecha(fechaVencimiento.toString());
            Date firstParsedDate = null;
            Date secondParsedDate = null;
            ;

            try {
                firstParsedDate = dateFormat.parse(fechaInicio);
                secondParsedDate = dateFormat.parse(fechaFin);
            } catch (Exception e) {
                logger.warning("ERROR al castear las fechas: ", e);
            }

            if (null != secondParsedDate && null != firstParsedDate) {
                logger.warning("Realizando el calculo.");
                long diff = secondParsedDate.getTime() - firstParsedDate.getTime();

                logger.warning("Diferencia: " + diff);
                Long numeroDias = numeroDias = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                logger.warning("Numero de dias de diferencia: " + numeroDias);

                if (!(numeroDias.compareTo(365L) == 1)) {
                    logger.warning("La frecuencia es menor o igual a 365 días.");
                    ADFUtils.setBoundAttributeValue("SePuedeAlinearDiaPago1", 1);
                    ADFUtils.setBoundAttributeValue("ExceptoVencimiento1", 1);
                } else {
                    logger.warning("La frecuencia es mayor a 365 días.");
                    /* FNXII-6809.
                    ADFUtils.setBoundAttributeValue("SePuedeAlinearDiaPago1", 0);
                    ADFUtils.setBoundAttributeValue("ExceptoVencimiento1", 0);
                    */
                }
                AdfFacesContext.getCurrentInstance().addPartialTarget(getPflCheckBoxUIC());
            }
        }
        logger.warning("Termina metodo asigranDiaPagoExceptoVencimientoPorFecha.");
    }

    public String eliminarTiempoFecha(String fechaStr) {
        logger.warning("Inicia metodo eliminarTiempoFecha.");

        fechaStr = fechaStr.substring(0, 10);

        logger.warning("Termina metodo eliminarTiempoFecha.");
        return fechaStr;
    }

    public Boolean getFechasParaRegistrarContratoByPlazos() {
        logger.warning("*Inf, Inicia metodo getFechasParaRegistrarContratoByPlazos.");
        Boolean respuesta = Boolean.FALSE;
        Date fechaEfectiva = null;
        Date fechaPrimerPagoCapial = null;
        Date fechaProxPagInteres = null;
        Date fechaProxRevTasa = null;
        Date fechaProxRevSpread = null;
        Date fechaVencimiento = null;
        Map fechaDatos = new HashMap();
        Boolean ocurrioErrorCalculo;
        java.sql.Timestamp fechaDisponibilidadFondos = null;
        Date fechaDisponibilidadFondosDate = null;
        java.sql.Timestamp fechaTransferenciaRecursos = null;
        Date fechaTransferenciaRecursosDate = null;
        Date fechaEstimadaDesembolsar = obtenerFechaEstimadaAdesembolsar();
        Boolean oringenTransferenciaCuentasBcie = esOrigenTransferenciaCuentasBcie();
        Map fechasAlineadas = new HashMap();

        CondicionesFinancierasBean condicionesBean = null;
        condicionesBean =
            (CondicionesFinancierasBean) JSFUtils.resolveExpression("#{pageFlowScope.CondicionFinancieraBean}");

        final Integer alVencimiento = 4;

        if (oringenTransferenciaCuentasBcie != null) {
            if (oringenTransferenciaCuentasBcie) {
                logger.warning("*Inf, se identifico un origen de transferencia a CUENTAS BCIE ");
                fechaDisponibilidadFondos = obtenerFechaDisponibilidadFondos();

                if (fechaDisponibilidadFondos != null) {
                    fechaDisponibilidadFondosDate = new Date(fechaDisponibilidadFondos.getTime());
                    fechaEfectiva = fechaDisponibilidadFondosDate;
                }
                else if(fechaEstimadaDesembolsar != null)
                {
                   fechaEfectiva = fechaEstimadaDesembolsar; 
                }
                else {
                    JSFUtils.addFacesErrorMessage("Error al calcular fechas por especificacion de plazos, El contrato no cuenta con fecha de disponibilidad de fondos");
                    return respuesta;
                }

            } else {
                logger.warning("*Inf, se identifico un origen de transferencia a DIRECTO A CLIENTE ");
                fechaTransferenciaRecursos = obtenerFechaTransferenciaRecursos();

                if (fechaTransferenciaRecursos != null) {
                    fechaTransferenciaRecursosDate = new Date(fechaTransferenciaRecursos.getTime());
                    fechaEfectiva = fechaTransferenciaRecursosDate;
                }
                else if(fechaEstimadaDesembolsar != null)
                {
                   fechaEfectiva = fechaEstimadaDesembolsar; 
                }
                else {
                    JSFUtils.addFacesErrorMessage("Error, no se pudo recuperar la fecha en transferencia recursos, para un origen de transferencias directo a cliente");
                    return respuesta;
                }

            }
            ;
        } else {
            logger.warning("*** Error, no se pudo recuperar el origen de la transferencia");
            JSFUtils.addFacesErrorMessage("Error, no se pudo recuperar el origen de la transferencia");
            return respuesta;
        }


        /***  Obtenemos la Frecuencia de pago de capital  ***/
        Integer frecuencia =
            (null == ADFUtils.getBoundAttributeValue("FrecuenciaPagoCapital")) ? null :
            (Integer) ADFUtils.getBoundAttributeValue("FrecuenciaPagoCapital");

        Integer IdTcaFrecuenciaPagoCapital =
            (null == ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPagoCapital") ||
             ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPagoCapital").toString().equals("")) ? null :
            Integer.parseInt(ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPagoCapital").toString());

        /***  Obtenemos la Frecuencia de pago de Interes  ***/
        Integer frecuenciaPagoInteres =
            (null == ADFUtils.getBoundAttributeValue("FrecuenciaPagoInteres")) ? null :
            (Integer) ADFUtils.getBoundAttributeValue("FrecuenciaPagoInteres");

        Integer idTcaFrecuenciaPagoInteres =
            (null == ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPagoInteres") ||
             ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPagoInteres").toString().equals("")) ? null :
            Integer.parseInt(ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPagoInteres").toString());

        /***  Obtenemos la Frecuencia de pago de tasa  ***/
        Integer frecuenciaRevisionTasa =
            (null == ADFUtils.getBoundAttributeValue("FrecuenciaRevisionTasaI")) ? null :
            (Integer) ADFUtils.getBoundAttributeValue("FrecuenciaRevisionTasaI");

        Integer idTcaFrecuenciaRevTasa =
            (null == ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaRevTasaI") ||
             ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaRevTasaI").toString().equals("")) ? null :
            Integer.parseInt(ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaRevTasaI").toString());

        /***  Obtenemos la Frecuencia de pago de spread  ***/
        Integer frecuenciaRevisionSpread =
            (null == ADFUtils.getBoundAttributeValue("FrecuenciaRevisionSpread")) ? null :
            (Integer) ADFUtils.getBoundAttributeValue("FrecuenciaRevisionSpread");

        Integer idTcaFrecuenciaRevSpread =
            (null == ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaRevSpread") ||
             ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaRevSpread").toString().equals("")) ? null :
            Integer.parseInt(ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaRevSpread").toString());

        /***  Obtenemos el Plazo  ***/
        Integer FrecuenciaPlazo =
            (null == ADFUtils.getBoundAttributeValue("FrecuenciaPlazo")) ? null :
            Integer.parseInt(ADFUtils.getBoundAttributeValue("FrecuenciaPlazo").toString());

        Integer IdTcaFrecuenciaPlazo =
            (null == ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPlazo") ||
             ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPlazo").toString().equals("")) ? null :
            Integer.parseInt(ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPlazo").toString());

        /***  Obtenemos el Periodo de gracia  ***/

        Integer frecuenciaPeriodoGracia =
            (null == ADFUtils.getBoundAttributeValue("FrecuenciaPeriodoGracia")) ? null :
            (Integer) ADFUtils.getBoundAttributeValue("FrecuenciaPeriodoGracia");

        Integer IdTcaFrecuenciaPeriodoGracia =
            (null == ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPeriodoGra") ||
             ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPeriodoGra").toString().equals("")) ? null :
            Integer.parseInt(ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPeriodoGra").toString());


        logger.warning("*Inf, fecha efectiva : " + fechaEfectiva);
        logger.warning("*Inf, FrecuenciaPlazo: " + FrecuenciaPlazo);
        logger.warning("*Inf, IdTcaFrecuenciaPlazo: " + IdTcaFrecuenciaPlazo);

        /**  Calculamos las fechas de pago revicion y vencimiento **/
        if (fechaEfectiva != null) {

            logger.warning("*Inf, calculando fechaPrimerPagoCapial....");

            fechaDatos =
                calcularFechaEfecMasFrecMasPeriGrac("Fecha de primer pago de capital", fechaEfectiva, frecuencia,
                                                    IdTcaFrecuenciaPagoCapital, frecuenciaPeriodoGracia,
                                                    IdTcaFrecuenciaPeriodoGracia, FrecuenciaPlazo,
                                                    IdTcaFrecuenciaPlazo);
            ocurrioErrorCalculo = (Boolean) fechaDatos.get("ocurrioErrorCalculo");

            if (ocurrioErrorCalculo) {
                JSFUtils.addFacesErrorMessage("Ha ocurrido un error al calcular la fecha de Primer Pago de Capial");
            } else {
                fechaPrimerPagoCapial = (Date) fechaDatos.get("fechaCalculada");
                logger.warning("*Inf, fecha de fechaPrimerPagoCapial calculada ->" + fechaPrimerPagoCapial);
            }

            logger.warning("*Inf, calculando fechaProximorPagoInteres...");

            try {
                fechaProxPagInteres = addTiempo(fechaEfectiva, frecuenciaPagoInteres, idTcaFrecuenciaPagoInteres);
            } catch (Exception e) {
                JSFUtils.addFacesErrorMessage("ha ocurrido un error al calcular la fecha de proximo pago de interes");
            }
            logger.warning("*Inf, fechaProxPagInteres calculada ->" + fechaProxPagInteres);


            if (condicionesBean.getTasaVariableVisible()) {

                logger.warning("*Inf, calculando fechaPrimerRevicionTasa...");

                try {
                    fechaProxRevTasa = addTiempo(fechaEfectiva, frecuenciaRevisionTasa, idTcaFrecuenciaRevTasa);
                } catch (Exception e) {
                    JSFUtils.addFacesErrorMessage("ha ocurrido un error al calcular la fecha de primera revicion de tasa");
                }
                logger.warning("*Inf, fechaProxRevTasa calculada ->" + fechaProxRevTasa);
            }

            if (condicionesBean.getMostrarSpreadVariable()) {
                logger.warning("*Inf, calculando fechaPrimerRevisionSpread...");

                try {
                    fechaProxRevSpread = addTiempo(fechaEfectiva, frecuenciaRevisionSpread, frecuenciaRevisionSpread);
                } catch (Exception e) {
                    JSFUtils.addFacesErrorMessage("ha ocurrido un error al calcular la fecha de primer revision de spreed");
                }

                logger.warning("*Inf, fechaProxRevSpread calculada ->" + fechaProxRevSpread);

            }

            if (FrecuenciaPlazo != null && IdTcaFrecuenciaPlazo != null) {

                logger.warning("*Inf, calculando fechaVencimiento....");
                Date fechaEfetivaMasPlazo = addTiempo(fechaEfectiva, FrecuenciaPlazo, IdTcaFrecuenciaPlazo);
                //Date fechaVencimiento = addTiempo(fechaEfetivaMasPlazo, frecuenciaPeriodoGracia, IdTcaFrecuenciaPeriodoGracia); se retira por FNXII-6252
                fechaVencimiento = fechaEfetivaMasPlazo;
                logger.warning("*Inf, fechaVencimiento recuperada: " + fechaVencimiento);

            } else {
                logger.warning("*Error, Importante! no se pudo calcular la fecha de Vencimiento");
                JSFUtils.addFacesErrorMessage("Error se requiere un plazo para calcular la fecha de vencimiento");
            }

            logger.warning("*Inf, operacion IFI? :" + condicionesBean.getLecturaIFI());
            if (condicionesBean.getLecturaIFI() != null && condicionesBean.getLecturaIFI()) {

                fechasAlineadas =
                    alinearFechasPagoRevicionYVencimiento(fechaPrimerPagoCapial, fechaProxPagInteres, fechaProxRevTasa,
                                                          fechaProxRevSpread, fechaVencimiento);

                fechaPrimerPagoCapial = (Date) fechasAlineadas.get("fechaPrimerPagoCapial");
                fechaProxPagInteres = (Date) fechasAlineadas.get("fechaProxPagInteres");

                if (condicionesBean.getTasaVariableVisible()) {
                    fechaProxRevTasa = (Date) fechasAlineadas.get("fechaProxRevTasa");
                }

                if (condicionesBean.getMostrarSpreadVariable()) {
                    fechaProxRevSpread = (Date) fechasAlineadas.get("fechaProxRevSpread");
                }

                fechaVencimiento = (Date) fechasAlineadas.get("fechaVencimiento");


            } else {
                logger.warning("*Inf, la operacion no es IFI no se realizaran alineaciones");
            }

            logger.warning("*Inf, Asociando fechas al contrato de desembolso ...");
            respuesta =
                asociarFechasAlContrato(fechaPrimerPagoCapial, fechaProxPagInteres, fechaProxRevTasa,
                                        fechaProxRevSpread, fechaVencimiento);


        } else {
            logger.warning("*Error, la fecha efectiva es resuelta a null, no se realizara el calculo de fechas");
            JSFUtils.addFacesErrorMessage("Error fecha efectiva resuelta a null");
        }

        logger.warning("*Inf, Termina metodo getFechasParaRegistrarContratoByPlazos.");
        return respuesta;
    }

    public Date addTiempo(Date fecha, Integer frecuencia, Integer idTca) {
        logger.warning("*Inf, Inicia metodo addTiempo.");
        logger.warning("para fecha->" + fecha);
        logger.warning("a frecuencia->" + frecuencia);
        logger.warning("para idTca->" + idTca);
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        logger.warning("Instancia de cal: " + cal.getTime());
        final int DIAS = 1, MESES = 2, ANOS = 3; 
        
        try {
        logger.warning("Inicia Switch");
            switch (idTca) {
            case DIAS:
                logger.warning("*Inf, agregando a ->" + fecha + " ->" + frecuencia + " dias");
                cal.add(Calendar.DATE, frecuencia);
                break;
            case MESES:
                logger.warning("*Inf, agregando a ->" + fecha + " ->" + frecuencia + " meses"); 
                cal.add(Calendar.MONTH, frecuencia);
                break;
            case ANOS:
                logger.warning("*Inf, agregando a ->" + fecha + " ->" + frecuencia + " anos"); 
                cal.add(Calendar.YEAR, frecuencia);
                break;
            }
            logger.warning("Finaliza Switch");

        } catch (Exception e) {
            logger.warning("*Inf, ha ocurrido un error en el metodo addTiempo: ", e);
        }

        logger.warning("*Inf, nueva fecha: " + cal.getTime());
        logger.warning("*Inf, Termina metodo addTiempo.");
        return cal.getTime();
    }

    private Boolean frecuenciaAlVencimiento(String frecuencia)
    {
        Boolean alVencimiento = false;
        
        Integer IdTcaFrecuenciaPago = (null == ADFUtils.getBoundAttributeValue(frecuencia) || ADFUtils.getBoundAttributeValue(frecuencia).toString().equals("")) ? null
                                                 : Integer.parseInt(ADFUtils.getBoundAttributeValue(frecuencia).toString());
        if(IdTcaFrecuenciaPago != null)
		{
			alVencimiento = IdTcaFrecuenciaPago == 4;
		}
        return alVencimiento;
    }
    

    public Map alinearFechasPagoRevicionYVencimiento(Date fechaPrimerPagoCapial, Date fechaProxPagInteres,
                                                     Date fechaProxRevTasa, Date fechaProxRevSpread,
                                                     Date fechaVencimiento) {

        logger.warning("*Inf, inicia metodo alinearFechasPagoRevicionYVencimiento");
        logger.warning("*Inf, fechaPrimerPagoCapial: " + fechaPrimerPagoCapial);
        logger.warning("*Inf, fechaProxPagInteres: " + fechaProxPagInteres);
        logger.warning("*Inf, fechaProxRevTasa: " + fechaProxRevTasa);
        logger.warning("*Inf, fechaProxRevSpread: " + fechaProxRevSpread);
        logger.warning("*Inf, fechaVencimiento: " + fechaVencimiento);

        Map fechas = new HashMap();
        Integer diaPagoCliente = null;
        Boolean alinearDiaPago = Boolean.FALSE;
        Boolean exceptoAlVencimiento = Boolean.FALSE;
        CondicionesFinancierasBean condicionesBean = null;
        condicionesBean =
            (CondicionesFinancierasBean) JSFUtils.resolveExpression("#{pageFlowScope.CondicionFinancieraBean}");

        try {
            alinearDiaPago =
                (null == ADFUtils.getBoundAttributeValue("SePuedeAlinearDiaPago1")) ? Boolean.FALSE :
                (Boolean) ADFUtils.getBoundAttributeValue("SePuedeAlinearDiaPago1");

            exceptoAlVencimiento =
                (null == ADFUtils.getBoundAttributeValue("ExceptoVencimiento1")) ? Boolean.FALSE :
                (Boolean) ADFUtils.getBoundAttributeValue("ExceptoVencimiento1");
        } catch (Exception e) {
            logger.warning("Ha ocurrido un error al recuperar los datos de alineacion ->", e);
        }
            
        logger.warning("*Inf, alinearDiaPago: "+alinearDiaPago);
        logger.warning("*Inf, exceptoAlVencimiento: "+exceptoAlVencimiento);
    
        if(alinearDiaPago){
                                                                                                            
            diaPagoCliente = (condicionesBean.getDiaPagoCliente() != null) ? condicionesBean.getDiaPagoCliente() : 0 ;//obtenerDiaPagoCliente();
                
            logger.warning("diaPagoCliente obtenido: "+diaPagoCliente);
            if(diaPagoCliente != null){
                
                if(!frecuenciaAlVencimiento("IdTcaFrecuenciaPagoCapital"))
                {
                    logger.warning("*Inf, Alineando fechaPrimerPagoCapial...");                
                    fechaPrimerPagoCapial = alinearFechaDia(fechaPrimerPagoCapial,diaPagoCliente);                            
                }

                if(!frecuenciaAlVencimiento("IdTcaFrecuenciaPagoInteres"))
                {
                    logger.warning("*Inf, Alineando fechaProxPagInteres...");
                    fechaProxPagInteres   = alinearFechaDia(fechaProxPagInteres,diaPagoCliente);                            
                }

                if(!frecuenciaAlVencimiento("IdTcaFrecuenciaRevTasa"))
                {
                    logger.warning("*Inf, Alineando fechaProxRevTasa...");               
                    fechaProxRevTasa      = alinearFechaDia(fechaProxRevTasa,diaPagoCliente);                            
                }

                if (condicionesBean.getMostrarSpreadVariable()) {
                    logger.warning("*Inf, Alineando fechaProxRevSpread...");
                    fechaProxRevSpread = alinearFechaDia(fechaProxRevSpread, diaPagoCliente);
                }
                if (!exceptoAlVencimiento) {
                    logger.warning("*Inf, Alineando fechaVencimiento...");
                    fechaVencimiento = alinearFechaDia(fechaVencimiento, diaPagoCliente);
                } else {
                    logger.warning("*Inf, no se solicito alinear fecha vencimiento a dia pago cliente");
                }

            } else {
                JSFUtils.addFacesErrorMessage("Error no se pudo recuperar el dia de pago del cliente");
            }
        } else {
            logger.warning("*Inf, no se solicito alinear fecha primer pago no se realizaran alineaciones");
        }

        fechas.put("fechaPrimerPagoCapial", fechaPrimerPagoCapial);
        fechas.put("fechaProxPagInteres", fechaProxPagInteres);
        fechas.put("fechaProxRevTasa", fechaProxRevTasa);
        fechas.put("fechaProxRevSpread", fechaProxRevSpread);
        fechas.put("fechaVencimiento", fechaVencimiento);

        logger.warning("*Inf, termina metodo alinearFechasPagoRevicionYVencimiento");
        return fechas;
    }

    public Date alinearFechaDia(Date fechaAAlinear, Integer diaDeAlineacion) {

        if (fechaAAlinear == null || diaDeAlineacion == null) {
            logger.warning("*Inf, parametros requeridos resultos a null no se realizara la alineacion");
            return fechaAAlinear;
        }

        final int MES = 2;

        Calendar fechaCalendar = Calendar.getInstance();
        fechaCalendar.setTime(fechaAAlinear);
        int diaFechaAlinearInt = fechaCalendar.get(Calendar.DAY_OF_MONTH);

        Integer diaFechaAlinearInteger = new Integer(diaFechaAlinearInt);

        logger.warning("*Inf, dia pago cliente: " + diaDeAlineacion + " diaPrimerPago: " + diaFechaAlinearInt);

        if (diaFechaAlinearInteger.compareTo(diaDeAlineacion) == 1) {
            logger.warning("*Inf, dia pago cliente ha pasado agregando mes a fecha..");
            fechaAAlinear = addTiempo(fechaAAlinear, 1, MES);
            fechaCalendar.setTime(fechaAAlinear);
            logger.warning("*Inf, nueva fecha : " + fechaAAlinear);
        }
        logger.warning("*Inf, alineando fecha primer pago capital a dia pago cliente...");

        fechaCalendar.set(Calendar.DAY_OF_MONTH, diaDeAlineacion);
        fechaAAlinear = fechaCalendar.getTime();

        return fechaAAlinear;
    }

    public Map calcularFechaEfecMasFrecMasPeriGrac(String tipoFecha, Date fechaEfectiva, Integer frecuencia,
                                                   Integer tcaFrecuencia, Integer frecPeriodGrac,
                                                   Integer tcaFrecPerGrac, Integer frecuenciaPlazo,
                                                   Integer tcaFrecuenciaPlazo) {

        logger.warning("*Inf, Inicia metodo calcularFechaEfecMasFrecMasPeriGrac");
        Map calculoDeFecha = new HashMap();
        calculoDeFecha.put("ocurrioErrorCalculo", Boolean.FALSE);
        Date fechaCalculada = null;
        final Integer alVencimiento = 4;


        if (frecuencia == null || tcaFrecuencia == null) {
            logger.warning("*Error, parametros requeridos resueltos a null");
            logger.warning("frecuencia: " + frecuencia);
            logger.warning("tcaFrecuencia: " + tcaFrecuencia);
            calculoDeFecha.put("ocurrioErrorCalculo", Boolean.TRUE);
            JSFUtils.addFacesErrorMessage("Error, la frecuencia es requerida para el calculo de la " + tipoFecha);
        } else {

            logger.warning("frecPeriodGrac: " + frecPeriodGrac);
            logger.warning("tcaFrecPerGrac: " + tcaFrecPerGrac);

            if (frecPeriodGrac == null || tcaFrecPerGrac == null) {
                logger.warning("*Error, parametros requeridos resueltos a null");

                calculoDeFecha.put("ocurrioErrorCalculo", Boolean.TRUE);
                JSFUtils.addFacesErrorMessage("Error, el periodo de gracia es requerido para el calculo de la " +
                                              tipoFecha);
            } else {


                try {
                    if (tcaFrecuencia.compareTo(alVencimiento) == 0) {
                        logger.warning("*Inf, el IdTcaFrecuencia es al vencimiento fechaCalculada = fechaEfectiva + Plazo + periodo de gracia");
                        Date fechaEfectivaMasPlazo = addTiempo(fechaEfectiva, frecuenciaPlazo, tcaFrecuenciaPlazo);
                        fechaCalculada = addTiempo(fechaEfectivaMasPlazo, frecPeriodGrac, tcaFrecPerGrac);
                        logger.warning("*Inf, " + tipoFecha + " caculada: " + fechaCalculada);
                    } else {
                        Date fechaefetivaMasFrecuencia = addTiempo(fechaEfectiva, frecuencia, tcaFrecuencia);
                        fechaCalculada = addTiempo(fechaefetivaMasFrecuencia, frecPeriodGrac, tcaFrecPerGrac);
                        logger.warning("*Inf, " + tipoFecha + " caculada: " + fechaCalculada);
                    }
                } catch (Exception e) {
                    calculoDeFecha.put("ocurrioErrorCalculo", Boolean.TRUE);
                    logger.warning("*Ha ocurrido un error al cacular la fecha ->", e);
                    logger.warning("Datos utilizados para el calulo fechaEfectiva: " + fechaEfectiva + " frecuencia: " +
                                   frecuencia + " tcaFrecuencia: " + tcaFrecuencia + " frecPeriodGrac: " +
                                   frecPeriodGrac + "tcaFrecPerGrac: " + tcaFrecPerGrac + "frecuenciaPlazo: " +
                                   frecuenciaPlazo + " tcaFrecuenciaPlazo: " + tcaFrecuenciaPlazo);
                }

            }
        }
        calculoDeFecha.put("fechaCalculada", fechaCalculada);

        logger.warning("*Inf, termina metodo calcularFechaEfecMasFrecMasPeriGrac");
        return calculoDeFecha;
    }


    @SuppressWarnings("unchecked")
    public Boolean asociarFechasAlContrato(Date fechaPrimerPagoCapial, Date fechaProxPagInteres, Date fechaProxRevTasa,
                                           Date fechaProxRevSpread, Date fechaVencimiento) {
        logger.warning("*Inf, Inicia metodo asociarFechasAlContrato.");
        Boolean respuesta = Boolean.FALSE;

        logger.warning("*Inf, validando fecha primer pago menor a fecha vencimiento..");
        if (fechaPrimerPagoCapial == null || fechaVencimiento == null) {
            logger.warning("Error, parametros requeridos resueltos a null");
            logger.warning("fechaPrimerPagoCapial: " + fechaPrimerPagoCapial);
            logger.warning("fechaVencimiento: " + fechaVencimiento);
            JSFUtils.addFacesErrorMessage("Error, la fecha de primer pago de capital y vencimiento no pueden ser nulas");
        } else {

            if (fechaPrimerPagoCapial.compareTo(fechaVencimiento) == 1) {
                logger.warning("*Error, la fecha de primer pago de capital no puede ser mayor a la fecha de vencimiento");
                JSFUtils.addFacesErrorMessage("Error, la fecha de primer pago de capital no puede ser mayor a la fecha de vencimiento");
            } else {


                try {
                    BindingContainer binding = ADFUtils.getBindingContainer();
                    OperationBinding operBinding = binding.getOperationBinding("asignarFechasPagoRevicionYVencimiento");
                    operBinding.getParamsMap().put("fechaPrimerPagoCapial", fechaPrimerPagoCapial);
                    operBinding.getParamsMap().put("fechaProximoPagoInteres", fechaProxPagInteres);
                    operBinding.getParamsMap().put("fechaProximaRevisionTasa", fechaProxRevTasa);
                    operBinding.getParamsMap().put("fechaProximaRevisionSpread", fechaProxRevSpread);
                    operBinding.getParamsMap().put("fechaVencimiento", fechaVencimiento);
                    operBinding.execute();

                    if (!operBinding.getErrors().isEmpty()) {
                        logger.warning("***Error, operationBinding getContratoSeleccionado " + operBinding.getErrors());
                        JSFUtils.addFacesErrorMessage("Error, " + operBinding.getErrors());
                    } else {

                        respuesta = (Boolean) operBinding.getResult();
                    }

                } catch (Exception e) {
                    logger.warning("***Error, al consultar los datos del desembolso ", e);

                }
                
                
                //Se actualizan las fechas de los nuevos componentes y calendarios
                try
                {
                    CondicionesFinancierasBean condicionesBean = null;
                    condicionesBean = (CondicionesFinancierasBean) JSFUtils.resolveExpression("#{pageFlowScope.CondicionFinancieraBean}");
                
                    ADFUtils.setBoundAttributeValue("FechaVencimiento", fechaVencimiento);
                    ADFUtils.setBoundAttributeValue("FechaPrimerPagoCapital", fechaPrimerPagoCapial);
                    ADFUtils.setBoundAttributeValue("FechaProximoPagoInteres", fechaProxPagInteres);
                    ADFUtils.setBoundAttributeValue("FechaProximaRevisionTasaI", fechaProxRevTasa);
                    ADFUtils.setBoundAttributeValue("FechaProximaRevisionSpread", fechaProxRevSpread);
                    
                    
                    Long idCondicionFinanciera = condicionesBean.getIdCondicionFinanciera();
                    if(idCondicionFinanciera!=null){
                        System.out.println("idCondicionFinanciera --> "+idCondicionFinanciera);
        
                        Map hash_map = new HashMap();
                        hash_map.put("idCondicionFinanciera",idCondicionFinanciera);
                        System.out.println("Mappings : " + hash_map); 
                        
                        BindingContainer bindings = getBindings();
                        OperationBinding guardar = bindings.getOperationBinding("guardarCalendarioComponente");
                        guardar.getParamsMap().put("map", hash_map);
                        guardar.execute();
                    }                    
                }
                catch(Exception ex)
                {
                    logger.warning("***Error, al guardar el calendario componente de los datos del desembolso ", ex);
                }
                
            }
        }

        logger.warning("*Inf, Termina metodo asociarFechasAlContrato.");
        return respuesta;
    }

    public java.sql.Timestamp obtenerFechaDisponibilidadFondos() {
        logger.warning("*Inf, Inicia metodo obtenerDatosContrato");
        Row fila = null;
        java.sql.Timestamp fechaDisponibilidadFondos = null;

        try {
            BindingContainer binding = ADFUtils.getBindingContainer();
            OperationBinding operBinding = binding.getOperationBinding("getContratoSeleccionado");
            operBinding.execute();

            if (!operBinding.getErrors().isEmpty()) {
                logger.warning("***Error, operationBinding getContratoSeleccionado " + operBinding.getErrors());
                JSFUtils.addFacesErrorMessage("Error, " + operBinding.getErrors());
            } else {

                if (operBinding.getResult() != null) {

                    fila = (Row) operBinding.getResult();

                    fechaDisponibilidadFondos =
                        (null == fila.getAttribute("FechaDisponibilidadFondos")) ? null :
                        (java.sql.Timestamp) fila.getAttribute("FechaDisponibilidadFondos");

                    logger.warning("*Inf, fechaDisponibilidadFondos recuperada del contrato->" +
                                   fechaDisponibilidadFondos);
                } else {
                    logger.warning("*** Error, No se pudo recuperar un current en contrato desembolso");
                }
            }
        } catch (Exception e) {
            logger.warning("***Error, al consultar los datos del desembolso ", e);

        }
        logger.warning("*Inf, Termina metodo obtenerDatosContrato");
        return fechaDisponibilidadFondos;
    }


    public Boolean validarFechaSolicitudFchaPagoCapital(java.sql.Timestamp fechaPagoCapital) {
        logger.warning("*Inf, Inicia metodo validarFechaSolicitudFchaPagoCapital");
        Boolean respuesta = Boolean.FALSE;
        Row fila = null;
        java.sql.Timestamp fechaSolicitud = null;

        if (fechaPagoCapital == null)
            return respuesta;

        try {
            BindingContainer binding = ADFUtils.getBindingContainer();
            OperationBinding operBinding = binding.getOperationBinding("getSolicitudCurrent");
            operBinding.execute();

            if (!operBinding.getErrors().isEmpty()) {
                logger.warning("***Error, operationBinding getSolicitudCurrent " + operBinding.getErrors());
                JSFUtils.addFacesErrorMessage("Error no se pudo validar la fecha de primer pago capital mayor a la fecha de la solicitud");
                JSFUtils.addFacesErrorMessage("Error, " + operBinding.getErrors());
            } else {

                if (operBinding.getResult() != null) {

                    fila = (Row) operBinding.getResult();

                    fechaSolicitud =
                        (null == fila.getAttribute("FechaSolicitud")) ? null :
                        (java.sql.Timestamp) fila.getAttribute("FechaSolicitud");

                    logger.warning("*Inf,  fechaSolicitud: " + fechaSolicitud + " fechaPagoCapital:" +
                                   fechaPagoCapital);
                    if (fechaSolicitud != null && fechaPagoCapital != null) {
                        if (fechaSolicitud.compareTo(fechaPagoCapital) != 1) {
                            respuesta = Boolean.TRUE;
                            logger.warning("*Inf, ok validacion correcta fecha pago capita mayor a fecha solicitud");
                        } else {
                            JSFUtils.addFacesErrorMessage("la fecha del primer pago de capital no puede ser menor a la de la solicitud");
                        }
                    } else {
                        JSFUtils.addFacesErrorMessage("Error no se pudo validar la fecha de primer pago capital mayor a la fecha de la solicitud");
                    }
                } else {
                    JSFUtils.addFacesErrorMessage("Error no se pudo validar la fecha de primer pago capital mayor a la fecha de la solicitud");
                    logger.warning("*** Error, No se pudo recuperar la solicitud current");
                }
            }
        } catch (Exception e) {
            logger.warning("***Error, al consultar los datos del desembolso ", e);
            JSFUtils.addFacesErrorMessage("Error no se pudo validar la fecha de primer pago capital mayor a la fecha de la solicitud ->" +
                                          e);
        }
        logger.warning("*Inf, Termina metodo validarFechaSolicitudFchaPagoCapital");
        return respuesta;
    }


    public java.sql.Timestamp obtenerFechaTransferenciaRecursos() {
        logger.warning("*Inf, Inicia metodo obtenerFechaTransferenciaRecursos");
        java.sql.Timestamp fechaTransferenciaRecursos = null;
        logger.warning("idDesembolso: " + JSFUtils.resolveExpression("#{pageFlowScope.idDesembolso}"));
        Long idContratoDes = null;

        try {

            idContratoDes =
                (JSFUtils.resolveExpression("#{pageFlowScope.idDesembolso}") == null) ? null :
                Long.parseLong(JSFUtils.resolveExpression("#{pageFlowScope.idDesembolso}").toString());

        } catch (Exception e) {
            logger.warning("Error al castear el contrato Desembolso->", e);
        }

        try {
            BindingContainer binding = ADFUtils.getBindingContainer();
            OperationBinding operBinding = binding.getOperationBinding("obtenerFechatransferenciaRecursos");
            operBinding.getParamsMap().put("idContrato", idContratoDes);
            operBinding.execute();

            if (!operBinding.getErrors().isEmpty()) {
                logger.warning("***Error, al obtener Fecha transferencia Recursos " + operBinding.getErrors());
                JSFUtils.addFacesErrorMessage("Error operbinding, " + operBinding.getErrors());
            } else {

                if (operBinding.getResult() != null) {

                    fechaTransferenciaRecursos = (java.sql.Timestamp) operBinding.getResult();
                    logger.warning("*Inf, fechaTransferenciaRecursos recuperada ->" + fechaTransferenciaRecursos);

                } else {
                    logger.warning("*** Error, No se pudo recuperar un current en contrato desembolso");
                }
            }
        } catch (Exception e) {
            logger.warning("***Error, al consultar los datos del desembolso ", e);

        }
        logger.warning("*Inf, Termina metodo obtenerDatosContrato");
        return fechaTransferenciaRecursos;
    }

    public Integer obtenerDiaPagoCliente() {
        logger.warning("*Inf, Termina metodo obtenerDiaPagoCliente");
        Integer diaPagoCliente = null;

        try {
            BindingContainer binding = ADFUtils.getBindingContainer();
            OperationBinding operBinding = binding.getOperationBinding("obtenerDiaPagoCliente");
            operBinding.execute();

            if (!operBinding.getErrors().isEmpty()) {
                logger.warning("*Error, operationBinding obtenerDiaPagoCliente ->" +
                               operBinding.getErrors().toString());
                JSFUtils.addFacesErrorMessage("Error, " + operBinding.getErrors());
            } else {

                if (operBinding.getResult() != null) {
                    diaPagoCliente = (Integer) operBinding.getResult();
                    logger.warning("*Inf, dia pago cliente recuperado ->" + diaPagoCliente);
                    try{
                        logger.warning("***nuevo e: " + diaPagoCliente);
                        if(diaPagoCliente == null){
                            logger.warning("***diaPagoCliente es nulo");
                            diaPagoCliente = 0;    
                        }
//                        ADFUtils.setBoundAttributeValue("DiaPago", diaPagoCliente);
//                        String XdiaPagoCliente = ADFUtils.getBoundAttributeValue("DiaPago").toString();
//                        logger.warning("***nuevo s: " + XdiaPagoCliente);
                    } catch (Exception ex) {
                        logger.warning("***Error nuevo: ", ex);
                    }
                } else {
                    logger.warning("*** Error obtenerDiaPagoCliente, No se pudo recuperar un current en contrato desembolso");
                }
            }
        } catch (Exception e) {
            logger.warning("***Error obtenerDiaPagoCliente, al consultar los datos del desembolso ", e);
            diaPagoCliente = 0;    
        }


        logger.warning("*Inf, Termina metodo obtenerDiaPagoCliente");
        return diaPagoCliente;
    }


    /*** --------------- Accesors --------------- ****/


    public void setIdCondicionFinanciera(Long idCondicionFinanciera) {
        this.idCondicionFinanciera = idCondicionFinanciera;
    }

    public Long getIdCondicionFinanciera() {
        return idCondicionFinanciera;
    }

    public void setCalendarioComplejoTable(RichTable calendarioComplejoTable) {
        this.calendarioComplejoTable = calendarioComplejoTable;
    }

    public RichTable getCalendarioComplejoTable() {
        return calendarioComplejoTable;
    }

    public void setDetalleCalendarioTable(RichTable detalleCalendarioTable) {
        this.detalleCalendarioTable = detalleCalendarioTable;
    }

    public RichTable getDetalleCalendarioTable() {
        return detalleCalendarioTable;
    }

    public void setIdCalendarioComplejo(Long idCalendarioComplejo) {
        this.idCalendarioComplejo = idCalendarioComplejo;
    }

    public Long getIdCalendarioComplejo() {
        return idCalendarioComplejo;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setEsErrorValidacion(Boolean esErrorValidacion) {
        this.esErrorValidacion = esErrorValidacion;
    }

    public Boolean getEsErrorValidacion() {
        return esErrorValidacion;
    }

    public void setContenedorSecPrincipal(RichPanelGroupLayout contenedorSecPrincipal) {
        this.contenedorSecPrincipal = contenedorSecPrincipal;
    }

    public RichPanelGroupLayout getContenedorSecPrincipal() {
        return contenedorSecPrincipal;
    }

    public void setContenedorSecTasa(RichPanelGroupLayout contenedorSecTasa) {
        this.contenedorSecTasa = contenedorSecTasa;
    }

    public RichPanelGroupLayout getContenedorSecTasa() {
        return contenedorSecTasa;
    }

    public void setContenedorNumCuotas(RichPanelLabelAndMessage contenedorNumCuotas) {
        this.contenedorNumCuotas = contenedorNumCuotas;
    }

    public RichPanelLabelAndMessage getContenedorNumCuotas() {
        return contenedorNumCuotas;
    }

    public void setContenedorNumCuotasInteres(RichPanelLabelAndMessage contenedorNumCuotasInteres) {
        this.contenedorNumCuotasInteres = contenedorNumCuotasInteres;
    }

    public RichPanelLabelAndMessage getContenedorNumCuotasInteres() {
        return contenedorNumCuotasInteres;
    }

    public void setContenedorNumRevisionTaza(RichPanelLabelAndMessage contenedorNumRevisionTaza) {
        this.contenedorNumRevisionTaza = contenedorNumRevisionTaza;
    }

    public RichPanelLabelAndMessage getContenedorNumRevisionTaza() {
        return contenedorNumRevisionTaza;
    }
    
    public void setContenedorNumRevisionTazaI(RichPanelLabelAndMessage contenedorNumRevisionTazaI) {
        this.contenedorNumRevisionTazaI = contenedorNumRevisionTazaI;
    }

    public RichPanelLabelAndMessage getContenedorNumRevisionTazaI() {
        return contenedorNumRevisionTazaI;
    }

    public void setContenedorNumRevisionesSpread(RichPanelLabelAndMessage contenedorNumRevisionesSpread) {
        this.contenedorNumRevisionesSpread = contenedorNumRevisionesSpread;
    }

    public RichPanelLabelAndMessage getContenedorNumRevisionesSpread() {
        return contenedorNumRevisionesSpread;
    }


    public void setContenedorSecInteres(RichPanelGroupLayout contenedorSecInteres) {
        this.contenedorSecInteres = contenedorSecInteres;
    }

    public RichPanelGroupLayout getContenedorSecInteres() {
        return contenedorSecInteres;
    }

    public void setContenedorSpreadVariable(RichPanelGroupLayout contenedorSpreadVariable) {
        this.contenedorSpreadVariable = contenedorSpreadVariable;
    }

    public RichPanelGroupLayout getContenedorSpreadVariable() {
        return contenedorSpreadVariable;
    }

    public void setContenedorDG1(RichPanelFormLayout contenedorDG1) {
        this.contenedorDG1 = contenedorDG1;
    }

    public RichPanelFormLayout getContenedorDG1() {
        return contenedorDG1;
    }

    public void setContenedorMora(RichPanelGroupLayout contenedorMora) {
        this.contenedorMora = contenedorMora;
    }

    public RichPanelGroupLayout getContenedorMora() {
        logger.warning("Inicia metodo getContenedorMora");
        Boolean esVisible = this.poseeMora();
        logger.warning("Fin metodo getContenedorMora");
        return contenedorMora;
    }


    public Boolean poseeMora(){
        logger.warning("Inicia metodo poseeMora");
        String ProductoFlex = "";
        
        logger.warning("verificar si el campo IdProductoFlexcube es nulo");
        if(ADFUtils.getBoundAttributeValue("IdProductoFlexcube") != null)
        {
            logger.warning("el campo IdProductoFlexcube no es nulo: " + ProductoFlex);
            ProductoFlex = (String)ADFUtils.getBoundAttributeValue("IdProductoFlexcube");
            logger.warning("el campo IdProductoFlexcube no es nulo: " + ProductoFlex);
        }
        else
        {
            logger.warning("el campo IdProductoFlexcube es nulo");
        }
        logger.warning("ProductoFlex: " + ProductoFlex);
        Boolean siPoseeMora = Boolean.FALSE;

        OperationBinding ob = executeOperation("poseeMora");
        ob.getParamsMap().put("ProductoFlex", ProductoFlex); 
        siPoseeMora = (Boolean) ob.execute();

        logger.warning("siPoseeMora: " + siPoseeMora);
        logger.warning("Termina metodo poseeMora");
        return siPoseeMora;
    }
    public void setContenedorAllSpreadVariable(RichPanelGroupLayout contenedorAllSpreadVariable) {
        this.contenedorAllSpreadVariable = contenedorAllSpreadVariable;
    }

    public RichPanelGroupLayout getContenedorAllSpreadVariable() {
        return contenedorAllSpreadVariable;
    }

    public String invocarDescargarFormatoCapital() {
        logger.warning("Inicia metodo invocarDescargarFormatoCapital");
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();
        OutputStream output = null;
        try {
            output = response.getOutputStream();
        } catch (Exception e) {
            logger.warning("ERROR al obtener el OutputStream.", e);
        }

        logger.warning("Invocando metodo descargaFormatoCapital.");
        descargaFormatoCapital(fc, output);
        logger.warning("Termina metodo invocarDescargarFormatoCapital");
        return null;
    }

    public void setContenedorDescargarCapital(RichPanelGroupLayout contenedorDescargarCapital) {
        this.contenedorDescargarCapital = contenedorDescargarCapital;
    }

    public RichPanelGroupLayout getContenedorDescargarCapital() {
        return contenedorDescargarCapital;
    }

    public void cambiarFrecuenciaPlazo(ValueChangeEvent valueChangeEvent) {
        logger.warning("Inicia metodo cambiarFrecuenciaPlazo.");
        Integer frecuenciaPlazo = null;

        try {
            frecuenciaPlazo = (Integer) valueChangeEvent.getNewValue();
        } catch (Exception e) {
            logger.warning("ERROR al recuperar valor de Frecuencia: ", e);
        }

        logger.warning("FrecuenciaPlazo: " + frecuenciaPlazo);
        ADFUtils.setBoundAttributeValue("FrecuenciaPlazo", frecuenciaPlazo);

        asignarAlinearDiaPagoExceptoVencimiento();

        try {
            calcularCuotasSecPrincipalPorPlazo(null);
            calcularCuotasSecInteresPorPlazo(null);
            calcularCuotasSecTazaPorPlazo(null);
            calcularCuotasSecSpreadPorPlazo(null);
            calcularCuotasSecTazaPorPlazoI(null);
        } catch (Exception e) {
            logger.warning("***Error, recalcularCuotasPorPlazo : ", e);
        }

        calcularFechasByPlazosTareaRealizarAjustes();

        logger.warning("Termina metodo cambiarFrecuenciaPlazo.");
    }

    public String validarEstadoContrato() {
        logger.warning("Inside validarEstadoContrato.");

        String result = null;
        Integer idTcaEstado = (Integer) ADFUtils.getBoundAttributeValue("IdTcaEstado");

        if (idTcaEstado != null) {
            if (idTcaEstado.compareTo(FenixConstants.ESTADO_CONTRATO_DESEMBOLSO_APROBADO) == 0 ||
                idTcaEstado.compareTo(FenixConstants.ESTADO_CONTRATO_DESEMBOLSO_FONDOS) == 0) {
                logger.warning("Estado contrato desembolso adecuado para Registrar.");
            } else {
                result = getStringFromBundle("CONDICION_FINANCIERA_MSG_09");
                ;
            }
        }

        return result;
    }

    public void mostrarListaErrores(List<String> listaErrores) {
        logger.warning("Inside mostarListaErrores.");

        for (String mensaje : listaErrores) {
            JSFUtils.addFacesErrorMessage(mensaje);
        }
    }

    public void setPflCheckBoxUIC(RichPanelFormLayout pflCheckBoxUIC) {
        this.pflCheckBoxUIC = pflCheckBoxUIC;
    }

    public RichPanelFormLayout getPflCheckBoxUIC() {
        return pflCheckBoxUIC;
    }

    public String validarFechaEfectivaTermino() {
        logger.warning("Inside validarFechaEfectivaTermino.");

        CondicionesFinancierasBean condicionesBean = null;
        condicionesBean =
            (CondicionesFinancierasBean) JSFUtils.resolveExpression("#{pageFlowScope.CondicionFinancieraBean}");
        String respuesta = null;

        if (null != condicionesBean.getIdOperacion()) {
            try {
                BindingContainer binding = ADFUtils.getBindingContainer();
                OperationBinding operBinding = binding.getOperationBinding("validarFechaEfectivaTermino");
                operBinding.getParamsMap().put("idOperacion", condicionesBean.getIdOperacion());
                operBinding.getParamsMap().put("idContrato", condicionesBean.getIdDesembolso());
                respuesta = (String) operBinding.execute();

                if (!operBinding.getErrors().isEmpty()) {
                    logger.warning("Se detecto problemas al ejecutar el metodo de validarFechaEfectivaTermino");
                }

            } catch (Exception e) {
                logger.log(ADFLogger.WARNING, "ERROR validarFechaEfectivaTermino");
            }
        } else {
            logger.warning("Se detecto problemas con los parametros del metodo");
        }

        return respuesta;
    }

    public String validarFechaEfectivaMaxima() {
        logger.warning("Inside validarFechaEfectivaMaxima.");

        CondicionesFinancierasBean condicionesBean = null;
        condicionesBean =
            (CondicionesFinancierasBean) JSFUtils.resolveExpression("#{pageFlowScope.CondicionFinancieraBean}");
        String respuesta = null;

        if (null != condicionesBean.getIdDesembolso()) {
            try {
                BindingContainer binding = ADFUtils.getBindingContainer();
                OperationBinding operBinding = binding.getOperationBinding("validarFEfectivaFMaxima");
                operBinding.getParamsMap().put("idContrato", condicionesBean.getIdDesembolso());
                operBinding.getParamsMap().put("linea", condicionesBean.getIdLineaCredito());
                operBinding.getParamsMap().put("idSolicitud", condicionesBean.getIdSolicitud());
                respuesta = (String) operBinding.execute();

                if (!operBinding.getErrors().isEmpty()) {
                    logger.warning("Se detecto problemas al ejecutar el metodo de validarFEfectivaFMaxima");
                }

            } catch (Exception e) {
                logger.log(ADFLogger.WARNING, "ERROR validarFechaEfectivaMaxima");
            }
        } else {
            logger.warning("Se detecto problemas con los parametros del metodo");
        }

        return respuesta;
    }

    public void calcularSecSpreadByFrecuencia(ValueChangeEvent valueChangeEvent) {
        logger.warning("Inf, Inicia el metodo calcularSecSpreadByFrecuencia");

        Integer FrecuenciaRevisionSpread =
            (null == valueChangeEvent.getNewValue() || valueChangeEvent.getNewValue().toString().equals("")) ? null :
            Integer.parseInt(valueChangeEvent.getNewValue().toString());

        Integer especificacionFechas =
            (null == ADFUtils.getBoundAttributeValue("IdTcaEspecificacionFecha") ||
             ADFUtils.getBoundAttributeValue("IdTcaEspecificacionFecha").toString().equals("")) ? null :
            Integer.parseInt(ADFUtils.getBoundAttributeValue("IdTcaEspecificacionFecha").toString());

        if (especificacionFechas == 2) {
            logger.warning("Inf, calculando revisiones spread por Plazos...");
            calcularCuotasSecSpreadByFrecuenciaPlazo(FrecuenciaRevisionSpread);
        } else {
            logger.warning("Inf, calculando revisiones spread por Fechas...");
            calcularSecSpreadByFrecuenciaFechas(FrecuenciaRevisionSpread);
        }
        logger.warning("Inf, termina el metodo calcularSecTazaByFrecuencia");
    }

    public void calcularCuotasSecSpreadByFrecuenciaPlazo(Integer FrecuenciaRevisionSpread) {
        logger.warning("*Inf, Inicia el metodo calcularCuotasSecSpreadByFrecuenciaPlazo");

        Integer IdTcaFrecuenciaRevSpread =
            (null == ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaRevSpread") ||
             ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaRevSpread").toString().equals("")) ? null :
            Integer.parseInt(ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaRevSpread").toString());

        Integer IdTcaFrecuenciaPlazo =
            (null == ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPlazo") ||
             ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPlazo").toString().equals("")) ? null :
            Integer.parseInt(ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPlazo").toString());

        Integer FrecuenciaPlazo =
            (null == ADFUtils.getBoundAttributeValue("FrecuenciaPlazo") ||
             ADFUtils.getBoundAttributeValue("FrecuenciaPlazo").toString().equals("")) ? null :
            Integer.parseInt(ADFUtils.getBoundAttributeValue("FrecuenciaPlazo").toString());

        Integer tiempoDiasFrecuencia = null;
        Integer tiempoDiasFrecuenciaPlazo = null;
        Integer Cuotas = null;

        if (FrecuenciaRevisionSpread != null && IdTcaFrecuenciaRevSpread != null) {
            tiempoDiasFrecuencia = obtenerTiempoEnDias(FrecuenciaRevisionSpread, IdTcaFrecuenciaRevSpread);
        } else {
            logger.warning("*Inf, parametros requeridos null");
            logger.warning("*Inf, FrecuenciaRevisionSpread : " + FrecuenciaRevisionSpread);
            logger.warning("*Inf, IdTcaFrecuenciaRevSpread : " + IdTcaFrecuenciaRevSpread);
        }

        if (FrecuenciaPlazo != null && IdTcaFrecuenciaPlazo != null) {
            tiempoDiasFrecuenciaPlazo = obtenerTiempoEnDias(FrecuenciaPlazo, IdTcaFrecuenciaPlazo);
        } else {
            logger.warning("*Inf, parametros requeridos null");
            logger.warning("*Inf, FrecuenciaPlazo : " + FrecuenciaPlazo);
            logger.warning("*Inf, IdTcaFrecuenciaPlazo : " + IdTcaFrecuenciaPlazo);
        }

        if (tiempoDiasFrecuencia != null && tiempoDiasFrecuenciaPlazo != null) {
            if (tiempoDiasFrecuencia.compareTo(tiempoDiasFrecuenciaPlazo) != 1) {
                Cuotas = tiempoDiasFrecuenciaPlazo / tiempoDiasFrecuencia;
                if(Cuotas > 1)
                {
                    Cuotas = Cuotas - 1;
                    logger.warning("----->Inf,Cuotas de Revision Tasa Corregidas: " + Cuotas);
                }
                ADFUtils.setBoundAttributeValue("NumeroRevisionesSpread", Cuotas);
            } else {
                ADFUtils.setBoundAttributeValue("NumeroRevisionesSpread", null);
                logger.warning("***Error, El numero frecuencia es menor que el plazo no se calculara las cuotas");
            }
            //AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorNumRevisionTaza());
        } else {
            logger.warning("*Inf, parametros requeridos null");
            logger.warning("*Inf, tiempoDiasFrecuenciaTaza : " + tiempoDiasFrecuencia);
            logger.warning("*Inf, tiempoDiasFrecuenciaPlazo : " + tiempoDiasFrecuenciaPlazo);
        }
        logger.warning("Numero de Cuotas por plazo calculado : " + Cuotas);

        logger.warning("*Inf, Termina el metodo calcularCuotasSecTazaByFrecuenciaPlazo");
    }

    public void calcularSecSpreadByFrecuenciaFechas(Integer FrecuenciaRevisionSpread) {
        logger.warning("Inf, Inicia el metodo calcularSecSpreadByFrecuenciaFechas");

        java.sql.Timestamp fechaInicio =
            (null == ADFUtils.getBoundAttributeValue("FechaProximaRevisionSpread")) ? null :
            (java.sql.Timestamp) ADFUtils.getBoundAttributeValue("FechaProximaRevisionSpread");

        java.sql.Timestamp fechaFin =
            (null == ADFUtils.getBoundAttributeValue("FechaVencimiento")) ? null :
            (java.sql.Timestamp) ADFUtils.getBoundAttributeValue("FechaVencimiento");

        Integer IdTcaFrecuenciaRevSpread =
            (null == ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaRevSpread") ||
             ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaRevSpread").equals("")) ? null :
            Integer.parseInt(ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaRevSpread").toString());

        try
        {
            calcularCuotasOperBiding(fechaInicio, fechaFin, FrecuenciaRevisionSpread, IdTcaFrecuenciaRevSpread, "spread");
        }
        catch(Exception ex)
        {
            logger.warning("Inf, Ha ocurrido un error durante la ejecucion de calcularCuotasOperBiding: " + ex.getMessage()); 
        }
    }


    public Boolean getPlazoPago() {
        logger.warning("*Inf, Inicia el metodo getPlazoPago");
        Boolean respuesta = Boolean.FALSE;
        Date fechaDeInicio = null;
        Date fechaDeTermino = null;

        /**  Recuperamos las fechas de las cuales tomaremos el rango **/

        java.sql.Timestamp fechaInicio =
            (null == ADFUtils.getBoundAttributeValue("FechaPrimerPagoCapital")) ? null :
            (java.sql.Timestamp) ADFUtils.getBoundAttributeValue("FechaPrimerPagoCapital");

        java.sql.Timestamp fechaFinal =
            (null == ADFUtils.getBoundAttributeValue("FechaVencimiento")) ? null :
            (java.sql.Timestamp) ADFUtils.getBoundAttributeValue("FechaVencimiento");

        logger.warning("*Inf, fecha de primer pago capital: " + fechaInicio);
        logger.warning("*Inf, fecha de vencimiento: " + fechaFinal);

        if (fechaInicio == null || fechaFinal == null) {
            logger.warning("***Error, la fecha de primer pago capital y la fecha vencimiento son requeridas");
            JSFUtils.addFacesErrorMessage("Error, la fecha de primer pago capital y la fecha vencimiento son requeridas");
            return respuesta;
        }

        fechaDeInicio = new Date(fechaInicio.getTime());
        fechaDeTermino = new Date(fechaFinal.getTime());

        if (fechaDeInicio.compareTo(fechaDeTermino) == 1) {
            JSFUtils.addFacesErrorMessage("Error, la fecha de primer pago capital no puede ser mayor a la fecha vencimiento");
            return respuesta;
        }

        /**  Calulamos el Plazo entre Fechas **/

        Map datosPlazo = (Map) calcularPlazosEntreFechas(fechaDeInicio, fechaDeTermino);
        Integer frecuanciaPlazo = (Integer) datosPlazo.get("frecuanciaPlazo");
        Integer idTcaPlazo = (Integer) datosPlazo.get("idTcaPlazo");

        if (frecuanciaPlazo != null && idTcaPlazo != null) {

            /**  Insetamos el Plazo en BD **/
            respuesta = asociarPlazoPagoAContrato(frecuanciaPlazo, idTcaPlazo);

        } else {
            logger.warning("***Error, la fecha de primer pago capital y la fecha vencimiento son requeridas");
        }

        logger.warning("*Inf, Termina el metodo getPlazoPago");
        return respuesta;
    }

    public Boolean getPlazoPeriodoGracia() {
        logger.warning("*Inf, Inicia metodo getPlazoPeriodoGracia");
        Boolean respuesta = Boolean.FALSE;
        Date fechaDeInicio = null;
        Date fechaDeTermino = null;

        /**  Recuperamos las fechas de las cuales tomaremos el rango **/

        Date fechaInicio = obtenerFechaEstimadaAdesembolsar();

        java.sql.Timestamp fechaFinal =
            (null == ADFUtils.getBoundAttributeValue("FechaPrimerPagoCapital")) ? null :
            (java.sql.Timestamp) ADFUtils.getBoundAttributeValue("FechaPrimerPagoCapital");


        if (fechaInicio == null || fechaFinal == null) {
            logger.warning("***Error, la fecha de primer pago capital y la fecha vencimiento son requeridas");
            logger.warning("*Inf, fecha de primer pago capital: " + fechaInicio);
            logger.warning("*Inf, fecha de estimada a desembolsar: " + fechaFinal);
            return respuesta;
        }
        fechaDeInicio = fechaInicio;
        fechaDeTermino = new Date(fechaFinal.getTime());

        if (fechaDeInicio.compareTo(fechaDeTermino) == 1) {
            logger.warning("***Error, la fecha Estimada a desembolsar no puede ser mayor a la fecha de primer pago capital");
            logger.warning("*Inf, fecha de primer pago capital: " + fechaDeInicio);
            logger.warning("*Inf, fecha de estimada a desembolsar: " + fechaDeTermino);
            JSFUtils.addFacesErrorMessage("Error, la fecha de primer pago de capital no puede ser menor a la fecha estimada a desembolsar.");
            return respuesta;
        }

        logger.warning("*Inf, fecha de primer pago capital: " + fechaDeInicio);
        logger.warning("*Inf, fecha de estimada a desembolsar: " + fechaDeTermino);

        /**  Calulamos el Plazo entre Fechas **/

        Map datosPlazo = (Map) calcularPlazosEntreFechas(fechaDeInicio, fechaDeTermino);
        Integer frecuanciaPlazo = (Integer) datosPlazo.get("frecuanciaPlazo");
        Integer idTcaPlazo = (Integer) datosPlazo.get("idTcaPlazo");

        if (frecuanciaPlazo != null && idTcaPlazo != null) {

            /**  Insetamos el Plazo en BD **/
            respuesta = asociarPlazoPeriodoGraciaAContrato(frecuanciaPlazo, idTcaPlazo);

        } else {
            logger.warning("***Error, la fecha de primer pago capital y la fecha vencimiento son requeridas");
        }

        logger.warning("*Inf, Termina metodo getPlazoPeriodoGracia");
        return respuesta;
    }


    public Map calcularPlazosEntreFechas(Date fechaDeInicio, Date fechaDeTermino) {
        logger.warning("*Inf, Inicia el metodo calcularPlazosEntreFechas");

        if (fechaDeInicio == null || fechaDeTermino == null) {
            JSFUtils.addFacesErrorMessage("Error, al calcular el plazo entre fechas");
        }


        int tcaDias = 1, tcaMeses = 2, tcaAnios = 3;
        Integer frecuanciaPlazo = null;
        Integer idTcaPlazo = null;
        Map datosPlazos = new HashMap();

        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        String fecha1 = formato.format(fechaDeInicio);
        String fecha2 = formato.format(fechaDeTermino);

        String fechaInic[] = fecha1.split("-");
        String fechaFina[] = fecha2.split("-");
        int[] fechaIni = new int[fechaInic.length];
        int[] fechaFin = new int[fechaFina.length];

        for (int i = 0; i < fechaInic.length; i++) {
            fechaIni[i] = Integer.parseInt(fechaInic[i]);
        }
        for (int i = 0; i < fechaFina.length; i++) {
            fechaFin[i] = Integer.parseInt(fechaFina[i]);
        }

        /** --------- Se revisan las fechas para especificar un plazo por anos ------------**/

        if ((fechaIni[0] == fechaFin[0]) && (fechaIni[1] == fechaFin[1])) {
            logger.warning("*Inf, Se realizara un calculo por anios");

            if (fechaIni[2] != fechaFin[2]) {
                if (fechaIni[2] < fechaFin[2]) {
                    int numAnios = (fechaFin[2] - fechaIni[2]);
                    logger.warning("*Inf, El plazo es de " + numAnios + " año");
                    frecuanciaPlazo = new Integer(numAnios);
                    idTcaPlazo = new Integer(tcaAnios);
                } else {
                    logger.warning("*Inf, la fecha de inicio no puede ser mayor a la fecha de final");
                    JSFUtils.addFacesErrorMessage("Error, la fecha de inicio no puede ser mayor a la fecha de final");
                }
            } else {
                logger.warning("*Inf, las fechas son iguales");
            }
            /** --------- Se revisan las fechas para especificar un plazo por meses ------------**/
        } else if (fechaIni[0] == fechaFin[0]) {
            logger.warning("*Inf, Se realizara un calculo por meses");
            if (fechaIni[2] != fechaFin[2]) {
                if (fechaIni[2] < fechaFin[2]) {

                    int mesesDeAos = (fechaFin[2] - fechaIni[2]) * 12;
                    int meses = (fechaFin[1] - fechaIni[1]);
                    meses = mesesDeAos + meses;

                    logger.warning("*Inf, El plazo es de " + meses + " meses");
                    frecuanciaPlazo = new Integer(meses);
                    idTcaPlazo = new Integer(tcaMeses);
                } else {
                    logger.warning("*Inf, la fecha de inicio no puede ser mayor a la fecha de final");
                    JSFUtils.addFacesErrorMessage("Error, la fecha de inicio no puede ser mayor a la fecha de final");
                }
            } else {
                if (fechaIni[1] < fechaFin[1]) {
                    int meses = (fechaFin[1] - fechaIni[1]);
                    logger.warning("*Inf, El plazo es de " + meses + " meses");
					//JURBINA@15/07/2020 Se agrega la asignacion de los valores calculados de plazo a las variables utilizadas para el map   
					frecuanciaPlazo = new Integer(meses);
					idTcaPlazo = new Integer(tcaMeses);
                } else {
                    logger.warning("*Inf, la fecha de inicio no puede ser mayor a la fecha de final");
                    JSFUtils.addFacesErrorMessage("Error, la fecha de inicio no puede ser mayor a la fecha de final");
                }
            }
        } else {
            logger.warning("*Inf, Se realizara un calculo por dias");
            try {
                Date fechaIniciall = formato.parse(fechaIni[0] + "-" + fechaIni[1] + "-" + fechaIni[2]);
                Date fechaFinall = formato.parse(fechaFin[0] + "-" + fechaFin[1] + "-" + fechaFin[2]);
                int dias = (int) ((fechaFinall.getTime() - fechaIniciall.getTime()) / 86400000);
                logger.warning("*Inf, El pazo es de " + dias + " dias");

                frecuanciaPlazo = new Integer(dias);
                idTcaPlazo = new Integer(tcaDias);

            } catch (Exception e) {
                logger.warning("*Inf, Ha ocurrido un error al obtener el plazo en dias ->", e);
                JSFUtils.addFacesErrorMessage("Ha ocurrido un error al obtener el plazo en dias " + e);
            }
        }

        datosPlazos.put("frecuanciaPlazo", frecuanciaPlazo);
        datosPlazos.put("idTcaPlazo", idTcaPlazo);

        logger.warning("*Inf, Termina el metodo calcularPlazosEntreFechas");
        return datosPlazos;
    }


    @SuppressWarnings("unchecked")
    public Boolean asociarPlazoPagoAContrato(Integer frecuanciaPlazo, Integer idTcaPlazo) {
        logger.warning("*Inf, Inicia metodo asociarPlazoPagoAContrato.");
        Boolean respuesta = Boolean.FALSE;

        logger.warning("*Inf, frecuanciaPlazo: " + frecuanciaPlazo);
        logger.warning("*Inf, idTcaPlazo: " + idTcaPlazo);

        if (frecuanciaPlazo == null || idTcaPlazo == null) {
            logger.warning("***Error, la frecuanciaPlazo y el idTcaPlazo son requeridos");
            JSFUtils.addFacesErrorMessage("Error no se pudo recuperar el plazo de pago");
        }

        try {
            BindingContainer binding = ADFUtils.getBindingContainer();
            OperationBinding operBinding = binding.getOperationBinding("asignarPlazoPago");
            operBinding.getParamsMap().put("frecuenciaPlazo", frecuanciaPlazo);
            operBinding.getParamsMap().put("idTcaFrecuenciaPlazo", idTcaPlazo);
            operBinding.execute();

            if (!operBinding.getErrors().isEmpty()) {
                logger.warning("***Error, operationBinding asignarPlazosPrimerPagoYVencimiento " +
                               operBinding.getErrors());
                JSFUtils.addFacesErrorMessage("Error, " + operBinding.getErrors());
            } else {
                respuesta = (Boolean) operBinding.getResult();
            }

        } catch (Exception e) {
            logger.warning("***Error, al consultar los datos del desembolso ", e);

        }

        logger.warning("*Inf, Termina metodo asociarPlazoPagoAContrato.");
        return respuesta;
    }

    @SuppressWarnings("unchecked")
    public Boolean asociarPlazoPeriodoGraciaAContrato(Integer frecuanciaPlazo, Integer idTcaPlazo) {
        logger.warning("*Inf, Inicia metodo asociarPlazoPeriodoGraciaAContrato.");
        Boolean respuesta = Boolean.FALSE;

        logger.warning("*Inf, frecuanciaPlazo: " + frecuanciaPlazo);
        logger.warning("*Inf, idTcaPlazo: " + idTcaPlazo);

        if (frecuanciaPlazo == null || idTcaPlazo == null) {
            logger.warning("***Error, la frecuanciaPlazo y el idTcaPlazo son requeridos");
            JSFUtils.addFacesErrorMessage("Error no se pudo recuperar el plazo del periodo de gracia");
        }

        try {
            BindingContainer binding = ADFUtils.getBindingContainer();
            OperationBinding operBinding = binding.getOperationBinding("asignarPlazoPeriodoGracia");
            operBinding.getParamsMap().put("frecuenciaPlazo", frecuanciaPlazo);
            operBinding.getParamsMap().put("idTcaFrecuenciaPlazo", idTcaPlazo);
            operBinding.execute();

            if (!operBinding.getErrors().isEmpty()) {
                logger.warning("***Error, operationBinding asignarPlazosPrimerPagoYVencimiento " +
                               operBinding.getErrors());
                JSFUtils.addFacesErrorMessage("Error, " + operBinding.getErrors());
            } else {
                respuesta = (Boolean) operBinding.getResult();
            }

        } catch (Exception e) {
            logger.warning("***Error, al consultar los datos del desembolso ", e);

        }

        logger.warning("*Inf, Termina metodo asociarPlazoPeriodoGraciaAContrato.");
        return respuesta;
    }

    public Date obtenerFechaEstimadaAdesembolsar() {
        logger.warning("*Inf, Inicia metodo obtenerFechaEstimadaAdesembolsar");
        Row fila = null;
        Date fechaEstimadaAdesembolsar = null;

        try {
            BindingContainer binding = ADFUtils.getBindingContainer();
            OperationBinding operBinding = binding.getOperationBinding("getContratoSeleccionado");
            operBinding.execute();

            if (!operBinding.getErrors().isEmpty()) {
                logger.warning("***Error, operationBinding getContratoSeleccionado " + operBinding.getErrors());
                JSFUtils.addFacesErrorMessage("Error, " + operBinding.getErrors());
            } else {

                if (operBinding.getResult() != null) {

                    fila = (Row) operBinding.getResult();

                    oracle.jbo.domain.Date fechaEstimadaDes =
                        (null == fila.getAttribute("FechaEstimadaDesembolsar")) ? null :
                        (oracle.jbo.domain.Date) fila.getAttribute("FechaEstimadaDesembolsar");

                    if (fechaEstimadaDes != null) {
                        fechaEstimadaAdesembolsar = fechaEstimadaDes.getValue();
                    }

                    logger.warning("*Inf, FechaEstimadaAdesembolsar recuperada del contrato->" +
                                   fechaEstimadaAdesembolsar);
                } else {
                    logger.warning("*** Error, No se pudo recuperar un current en contrato desembolso");
                }
            }
        } catch (Exception e) {
            logger.warning("***Error, al consultar los datos del desembolso ", e);

        }
        logger.warning("*Inf, Termina metodo obtenerFechaEstimadaAdesembolsar");
        return fechaEstimadaAdesembolsar;
    }

    public Boolean asociarFechasPagoPorCalendarioComplejo(Long idCondicionFinanciera) {
        logger.warning("*Inf, inicia metodo asociarFechasPagoPorCalendarioComplejo");
        Boolean respuesta = null;

        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operBinding = null;

        try {
            operBinding = bindings.getOperationBinding("recuperarFechasMayoMenorCalendarioComplejo");
            operBinding.getParamsMap().put("idCondicionFinanciera", idCondicionFinanciera);
            operBinding.execute();

            if (!operBinding.getErrors().isEmpty()) {
                logger.warning("OperationBinding recuperarFechasMayoMenorCalendarioComplejo devuelve errores->" +
                               operBinding.getErrors());
                JSFUtils.addFacesErrorMessage("Error al calcular fecha de primer pago capital y fecha vencimiento " +
                                              operBinding.getErrors());
            } else {
                respuesta = (Boolean) operBinding.getResult();
            }

        } catch (Exception e) {
            logger.warning("Ha ocurrido un error en el metodo asociarFechasPagoPorCalendarioComplejo ", e);
        }
        logger.warning("*Inf, termina metodo asociarFechasPagoPorCalendarioComplejo");
        return respuesta;
    }

    public List<String> validarFechasInicioDesembolsoTotalidadRecursos() {
        CondicionesFinancierasBean condicionFinanciera =
            (CondicionesFinancierasBean) JSFUtils.resolveExpression("#{pageFlowScope.CondicionFinancieraBean}");
        logger.warning("Inicia metodo validarFechasInicioDesembolsoTotalidadRecursos");
        logger.warning("Linea");
        logger.warning("Inicia metodo validarFechasInicioDesembolsoTotalidadRecursos");
        List<String> listaErrores = new ArrayList<String>();
        Map<String, Object> params = new HashMap<String, Object>();
        Long lineaCredito = null;
        Long operacion = null;
        try {
            if (null != condicionFinanciera.getIdLineaCredito()) {
                lineaCredito = condicionFinanciera.getIdLineaCredito();
            } else {
                logger.warning("No existe registro de linea de credito");
            }
            if (null != condicionFinanciera.getIdOperacion()) {
                operacion = condicionFinanciera.getIdOperacion();
            } else {
                logger.warning("No existe registro de operacion");
            }


        } catch (Exception e) {
            logger.warning("ERROR al obtener el idLineaCredito.", e);
        }

        if (null != operacion && null != lineaCredito) {
            params.put("idOperacion", operacion);
            params.put("idLinea", lineaCredito);
            logger.warning("Invocando metodo para validar las fechas de inicio de desembolsos y desembolso de la totalidad de los recursos");
            try {
                listaErrores = execute(params, "recuperaFechasTerminosLineaDesembolso");
                logger.warning("Registros de la lista de errores: " + listaErrores.size());
            } catch (Exception e) {
                logger.warning("Error al ejecutar el operationBinding para validar las fechas de terminos.", e);
                listaErrores.add(getStringFromBundle("ERROR_REGLAS_NO_VALIDADAS_POR_SERVICIO"));
            }
        } else {
            logger.warning("IdOperacion es NULL.");
            listaErrores.add(getStringFromBundle("ERROR_REGLAS_NO_VALIDADAS_POR_SERVICIO"));
        }

        logger.warning("Termina metodo validarFechasInicioDesembolsoTotalidadRecursos");
        return listaErrores;
    }

    public void setContenedorSecTasa2(RichPanelGroupLayout contenedorSecTasa2) {
        this.contenedorSecTasa2 = contenedorSecTasa2;
    }

    public RichPanelGroupLayout getContenedorSecTasa2() {
        return contenedorSecTasa2;
    }

    public static void main(String[] args) {
        validarNumeroMayorCeroConNulosTest();
    }

    /**
     * Prueba unitaria de validacion de numero mayores a cero
     * aceptando valores vacios o nulos.
     * @param args
     */
    private static void validarNumeroMayorCeroConNulosTest() {
        Double valorPrueba = Double.valueOf(-0.1);
        Double constante = 0.1;
        for (int i = 0; i < 13; i++) {
            System.out.println("resultado para valor [" + valorPrueba + "]: " +
                               validarNumeroMayorCeroConNulos(valorPrueba.toString()));
            valorPrueba = (valorPrueba + constante);
        }
        System.out.println("resultado para valor [0]: " + validarNumeroMayorCeroConNulos("0"));
        System.out.println("resultado para valor [null]: " + validarNumeroMayorCeroConNulos(null));
        System.out.println("resultado para valor [vacio]: " + validarNumeroMayorCeroConNulos(""));
        System.out.println("resultado para valor [1]: " + validarNumeroMayorCeroConNulos("1"));
        System.out.println("resultado para valor [10]: " + validarNumeroMayorCeroConNulos("10"));
        System.out.println("resultado para valor [100]: " + validarNumeroMayorCeroConNulos("100"));
        System.out.println("resultado para valor [1000]: " + validarNumeroMayorCeroConNulos("1000"));
    }

    public static boolean validarNumeroMayorCeroConNulos(String valor) {
        boolean numeroValido = Boolean.FALSE;

        if (null != valor && !valor.isEmpty()) {

            boolean esCero = Boolean.FALSE;
            try {
                BigDecimal numero = new BigDecimal(valor);

                if (numero.compareTo(new BigDecimal(0)) == 0) {
                    esCero = Boolean.TRUE;
                }
            } catch (Exception e) {
                // No es necesario manejar esta excepcion
            }

            if (!esCero) {
                String regex = "^([1-9]|([0-9]+(\\.[0-9]+)))\\d*$";

                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(valor);
                if (!matcher.matches()) {
                    numeroValido = Boolean.FALSE;
                } else {
                    numeroValido = Boolean.TRUE;
                }
            }
        } else {
            //Se aceptan valores nulos
            numeroValido = Boolean.TRUE;
        }

        return numeroValido;
    }

    public void validarLimiteTasaMinima(ValueChangeEvent valueChangeEvent) {
        if (valueChangeEvent.getNewValue() != null) {
            String newValue = valueChangeEvent.getNewValue().toString();

            if (!validarNumeroMayorCeroConNulos(newValue)) {
                logger.warning("Numero no valido");
                if (null != getLimiteTasaMinimaUIC()) {
                    getLimiteTasaMinimaUIC().resetValue();
                    ADFUtils.setBoundAttributeValue("LimiteTasaMinima", null);
                    AdfFacesContext.getCurrentInstance().addPartialTarget(getLimiteTasaMinimaUIC());
                } else {
                    logger.warning("Componente limiteTasaMinimaUIC es nulo.");
                }

                JSFUtils.addFacesErrorMessage(getStringFromBundle("INGRESAR_VALORES_MAYOR_CERO"));
            } else {
                logger.warning("Numero valido");
            }
        }
    }

    public void validarLimiteTasaMaxima(ValueChangeEvent valueChangeEvent) {
        if (valueChangeEvent.getNewValue() != null) {
            String newValue = valueChangeEvent.getNewValue().toString();

            if (!validarNumeroMayorCeroConNulos(newValue)) {
                logger.warning("Numero no valido");
                if (null != getLimiteTasaMaximaUIC()) {
                    getLimiteTasaMaximaUIC().resetValue();
                    ADFUtils.setBoundAttributeValue("LimiteTasaMaxima", null);
                    AdfFacesContext.getCurrentInstance().addPartialTarget(getLimiteTasaMaximaUIC());
                } else {
                    logger.warning("Componente limiteTasaMaximaUIC es nulo.");
                }

                JSFUtils.addFacesErrorMessage(getStringFromBundle("INGRESAR_VALORES_MAYOR_CERO"));
            } else {
                logger.warning("Numero valido");
            }
        }
    }

    public Timestamp getFechaEfectivaParaConFinan() {
        logger.warning("*Inf, inicia metodo obtenerFechaParaTasaEspecial");
        Timestamp fechaEfectiva = null;

        CondicionesFinancierasBean condicionFinanciera =
            (CondicionesFinancierasBean) JSFUtils.resolveExpression("#{pageFlowScope.CondicionFinancieraBean}");

        if (condicionFinanciera.getIdTareaBPM().compareTo(new Integer("140")) == 0) {
            logger.warning("recuperando fechaEfectiva para Envio al cobro...");
            fechaEfectiva = getFechaEfectivaEnvioCobro();
        } else {
            logger.warning("recuperando fechaEfectiva para desembolsos...");
            fechaEfectiva = getFechaEfectivaParaEnDesembolsos();
        }

        logger.warning("*Inf, fechaEfectiva recuperada: " + fechaEfectiva);
        return fechaEfectiva;
    }


    public Timestamp getFechaEfectivaEnvioCobro() {
        Timestamp fechaEfectiva = null;
        java.sql.Timestamp fecha = null;
        try {
            BindingContainer binding = ADFUtils.getBindingContainer();
            OperationBinding operBinding = binding.getOperationBinding("getContratoSeleccionado");
            Row fila = (Row) operBinding.execute();

            if (fila != null) {

                fecha =
                    (fila.getAttribute("FechaEfectiva") == null) ? null :
                    (java.sql.Timestamp) fila.getAttribute("FechaEfectiva");

                if (fecha != null)
                    fechaEfectiva = new Timestamp(fecha.getTime());

            } else {
                logger.warning("***Error, No se pudo recuperar el origen de la transferencia");
            }

            if (!operBinding.getErrors().isEmpty()) {
                logger.warning("OperationBinding getContratoSeleccionado devuelve errores");
                JSFUtils.addFacesErrorMessage("Error. ->" + operBinding.getErrors().toString());
            }

        } catch (Exception e) {
            logger.warning("***Error al consultar los datos del contrato de desembolso " + e);
            JSFUtils.addFacesErrorMessage("Error no se pudo recuperar las cuentas del cliente");
        }

        return fechaEfectiva;
    }


    public Timestamp getFechaEfectivaParaEnDesembolsos() {

        String origenTrans =
            (null == ADFUtils.getBoundAttributeValue("OrigenTranferenciaCliente")) ? "" :
            (String) ADFUtils.getBoundAttributeValue("OrigenTranferenciaCliente");

        Timestamp fechaFuentesExt =
            (ADFUtils.getBoundAttributeValue("Fecha") != null) ? (Timestamp) ADFUtils.getBoundAttributeValue("Fecha") :
            null;

        Timestamp fechaDispRecursos =
            (ADFUtils.getBoundAttributeValue("FechaDisponibilidadFondos") != null) ?
            new Timestamp((java.sql.Timestamp) ADFUtils.getBoundAttributeValue("FechaDisponibilidadFondos")) : null;
        Timestamp fechaEfectiva = null;

        logger.warning("*Inf, origenTrans: " + origenTrans);
        logger.warning("*Inf, fechaFuentesExt: " + fechaFuentesExt);
        logger.warning("*Inf, fechaDispRecursos: " + fechaDispRecursos);

        if (origenTrans.equals("DIRECTO_CLIENTE")) {
            fechaEfectiva = fechaFuentesExt;
        } else if (origenTrans.equals("CUENTAS_BCIE")) {
            fechaEfectiva = fechaDispRecursos;
        }

        logger.warning("*Inf, termina metodo obtenerFechaParaTasaEspecial");
        return fechaEfectiva;
    }

    public void obtenerFechaFlex() {
        logger.warning("*Inf, inicia metodo obtenerFechaFlex");
        try {
            BindingContainer bindings = getBindings();
            OperationBinding operBinding = bindings.getOperationBinding("obtenerFechaFlex");
            Date fechaFlex = (Date) operBinding.execute();
        } catch (Exception e) {
            logger.log(ADFLogger.ERROR, " ***Error en el OperationBinding de obtenerFechaFlex" + e);
        }
        logger.warning("*Inf, termina metodo obtenerFechaFlex");
    }


    public void obtenerFechaParaTasaEspecial() {
        logger.warning("*Inf, inicia metodo obtenerFechaParaTasaEspecial");

        try {

            Integer IdTcaTipoTasaDesembolso =
                (ADFUtils.getBoundAttributeValue("IdTcaTipoTasaDesembolso") == null) ? null :
                (Integer) ADFUtils.getBoundAttributeValue("IdTcaTipoTasaDesembolso");

            if (IdTcaTipoTasaDesembolso != null && IdTcaTipoTasaDesembolso.compareTo(1) == 0) {

                String origenTrans =
                    (ADFUtils.getBoundAttributeValue("OrigenTranferenciaCliente") == null) ? "" :
                    (String) ADFUtils.getBoundAttributeValue("OrigenTranferenciaCliente");

                java.sql.Timestamp fechaFuentesExt = (java.sql.Timestamp) ADFUtils.getBoundAttributeValue("Fecha");
                java.sql.Timestamp fechaDispRecursos =
                    (java.sql.Timestamp) ADFUtils.getBoundAttributeValue("FechaDisponibilidadFondos");

                logger.warning("*Inf, origenTrans: " + origenTrans);
                logger.warning("*Inf, fechaFuentesExt: " + fechaFuentesExt);
                logger.warning("*Inf, fechaDispRecursos: " + fechaDispRecursos);

                if (origenTrans.equals("DIRECTO_CLIENTE")) {
                    ADFUtils.setBoundAttributeValue("FechaProximoPagoInteres", fechaFuentesExt);
                } else if (origenTrans.equals("CUENTAS_BCIE")) {
                    ADFUtils.setBoundAttributeValue("FechaProximoPagoInteres", fechaDispRecursos);
                }
            } else {
                logger.warning("*Inf, tipo de tasa difete de especial");
            }

        } catch (Exception e) {
            logger.warning("*Inf, Ha ocurrido una exception en obtenerFechaParaTasaEspecial()");
        }

        logger.warning("*Inf, termina metodo obtenerFechaParaTasaEspecial");
    }


    public void actualizarTazaReferencia() {
        logger.warning("*Inf, inicia metodo actualizarTazaReferencia");

        try {
            BindingContainer binding = ADFUtils.getBindingContainer();
            OperationBinding operBinding = binding.getOperationBinding("asignarValorTransient");
            operBinding.getParamsMap().put("filaRecuperada", null);
            operBinding.execute();


            if (!operBinding.getErrors().isEmpty()) {
                logger.warning("OperationBinding actualizarTazaReferencia devuelve errores ->" +
                               operBinding.getErrors().toString());
                JSFUtils.addFacesErrorMessage("Error. ->" + operBinding.getErrors().toString());
            }

        } catch (Exception e) {
            logger.warning("***Error al consultar los datos del contrato de desembolso ", e);
            JSFUtils.addFacesErrorMessage("Error no se pudo recuperar las cuentas del cliente");
        }

        logger.warning("*Inf, termina metodo actualizarTazaReferencia");
    }


    public void calcularFechasByPlazosTareaRealizarAjustes() {
        logger.warning("*Inf, inicia metodo calcularFechasByPlazosTareaRealizarAjustes");

        Integer pIdTarea = null;
        final Integer REALIZAR_AJUSTES_DESEMBOLSO = new Integer(151);

        if (JSFUtils.resolveExpression("#{pageFlowScope.idTareaBPM}") != null) {
            pIdTarea = Integer.parseInt(JSFUtils.resolveExpression("#{pageFlowScope.idTareaBPM}").toString());
        } else {
            logger.warning("IdTareaBPM NULL");
        }

        if (pIdTarea.compareTo(REALIZAR_AJUSTES_DESEMBOLSO) == 0) {
            logger.warning("*Inf, tareaBpm realizar ajustes a desembolso");
            getFechasParaRegistrarContratoByPlazos();
        }


        logger.warning("*Inf, termina metodo calcularFechasByPlazosTareaRealizarAjustes");
    }

    public Integer getValueSelectTcaFrecuencia(String value) {
        int idTcaFrecuencia = 0;
        switch (value) {
        case "Días":
            idTcaFrecuencia = 1;
            break;
        case "Meses":
            idTcaFrecuencia = 2;
            break;
        case "Años":
            idTcaFrecuencia = 3;
            break;
        case "Al Vencimiento":
            idTcaFrecuencia = 4;
            break;
        }
        System.out.println("idTcaFrecuencia --> " + idTcaFrecuencia);
        return idTcaFrecuencia;
    }

    public RichInputText getLimiteTasaMinimaUIC() {
        return limiteTasaMinimaUIC;
    }

    public void setLimiteTasaMinimaUIC(RichInputText limiteTasaMinimaUIC) {
        this.limiteTasaMinimaUIC = limiteTasaMinimaUIC;
    }

    public RichInputText getLimiteTasaMaximaUIC() {
        return limiteTasaMaximaUIC;
    }

    public void setLimiteTasaMaximaUIC(RichInputText limiteTasaMaximaUIC) {
        this.limiteTasaMaximaUIC = limiteTasaMaximaUIC;
    }

    public void setContenedorAllSpreadVariableNew(RichPanelHeader contenedorAllSpreadVariableNew) {
        this.contenedorAllSpreadVariableNew = contenedorAllSpreadVariableNew;
    }

    public RichPanelHeader getContenedorAllSpreadVariableNew() {
        return contenedorAllSpreadVariableNew;
    }
    
    public void setContenedorAllInteresNew(RichPanelHeader contenedorAllInteresNew) {
        this.contenedorAllInteresNew = contenedorAllInteresNew;
    }

    public RichPanelHeader getContenedorAllInteresNew() {
        return contenedorAllInteresNew;
    }
    
    public void setContenedorAllCapitalNew(RichPanelHeader contenedorAllCapitalNew) {
        this.contenedorAllCapitalNew = contenedorAllCapitalNew;
    }

    public RichPanelHeader getContenedorAllCapitalNew() {
        return contenedorAllCapitalNew;
    }

    public void setInitValidaciones(RichOutputText initValidaciones) {
        this.initValidaciones = initValidaciones;
    }

    public RichOutputText getInitValidaciones() {
        logger.warning("*Inf, method binding getInitValidaciones");
        obtenerFechaParaTasaEspecial();
        return initValidaciones;
    }
    
    private RichSelectOneChoice initDescripcionProducto;
    
    public void setInitDescripcionProducto(RichSelectOneChoice initDescripcionProducto) {
        this.initDescripcionProducto = initDescripcionProducto;
    }
    
    public RichSelectOneChoice getInitDescripcionProducto() {
        logger.warning("*Inicia, method binding getInitDescripcionProducto"); 
        try {
            BindingContainer binding = ADFUtils.getBindingContainer();
            OperationBinding operBinding = binding.getOperationBinding("setDescripcionProducto");
            operBinding.execute(); 
            if (!operBinding.getErrors().isEmpty()) {
                logger.warning("Error al ejecutar operBinding setDescripcionProducto: " + operBinding.getErrors().toString());
            }
            else
            {  
                try{
                        if (ADFUtils.getBoundAttributeValue("DescripcionProducto") !=null )
                        {
                            logger.warning("Inicia DescripcionProducto");
                            String prod = ADFUtils.getBoundAttributeValue("DescripcionProducto").toString();
                            logger.warning("DescripcionProducto: " + prod);
                            logger.warning("Termina DescripcionProducto");
                            
                            logger.warning("Inicia DescripcionProducto2");  
                            ADFUtils.setBoundAttributeValue("DescripcionProducto2", prod);
                            ADFUtils.setBoundAttributeValue("DescripcionProducto", prod);
                            logger.warning("Termina DescripcionProducto2");
                        } 
                    }
                catch(Exception exp){
                    logger.log(ADFLogger.WARNING, "Error DescripcionProducto", exp);
                }
                

            }
        } catch (Exception e) {
            logger.log(ADFLogger.WARNING, "Error en setear la Descripcion del Producto.", e);
        }
        logger.warning("*Inf, method binding getInitDescripcionProducto = " );
        logger.warning("*Finaliza, method binding getInitDescripcionProducto");
        return initDescripcionProducto;
    }
    
    private void getFechasCuotasCapital(Integer Cuotas, Integer frecuenciaPeriodoGracia, Integer IdTcaFrecuenciaPeriodoGracia, Integer IdTcaFrecuenciaPagoCapital, Integer FrecuenciaPagoCapital,
                                        Integer FrecuenciaPlazo, Integer IdTcaFrecuenciaPlazo)
    {
        logger.warning("***Inicio de getFechasCuotasCapital");
        //Si las cuotas calculadas en el capital no son null, se envia a calcular a plazos las fechas de vencimiento y primer pago capital
        if(Cuotas != null)
        {
            Date fechaEfectiva = obtenerFechaEstimadaAdesembolsar();
            java.sql.Timestamp fechaDisponibilidadFondos = null;
            Date fechaDisponibilidadFondosDate = null;
            Boolean ocurrioErrorCalculo = false;
            Date fechaPrimerPagoCapital = null;
            Date fechaVencimiento = null;
            
            fechaDisponibilidadFondos = obtenerFechaDisponibilidadFondos();

            if (fechaDisponibilidadFondos != null) {
                fechaDisponibilidadFondosDate = new Date(fechaDisponibilidadFondos.getTime());
                fechaEfectiva = fechaDisponibilidadFondosDate;
            }
            
            frecuenciaPeriodoGracia = frecuenciaPeriodoGracia != null && IdTcaFrecuenciaPeriodoGracia != null ? frecuenciaPeriodoGracia: 0;
            IdTcaFrecuenciaPeriodoGracia = IdTcaFrecuenciaPeriodoGracia != null ?  IdTcaFrecuenciaPeriodoGracia : 1;
            
            Map fechaDatos = calcularFechaEfecMasFrecMasPeriGrac("Fecha de primer pago de capital", fechaEfectiva, FrecuenciaPagoCapital,
                                                                IdTcaFrecuenciaPagoCapital, frecuenciaPeriodoGracia,
                                                                IdTcaFrecuenciaPeriodoGracia, FrecuenciaPlazo,
                                                                IdTcaFrecuenciaPlazo);
            ocurrioErrorCalculo = (Boolean) fechaDatos.get("ocurrioErrorCalculo");

            if (!ocurrioErrorCalculo) 
            {
                fechaPrimerPagoCapital = (Date) fechaDatos.get("fechaCalculada");
                logger.warning("*Inf, fecha de fechaPrimerPagoCapial calculada ->" + fechaPrimerPagoCapital);
            }
            
            if (FrecuenciaPlazo != null && IdTcaFrecuenciaPlazo != null) {
                logger.warning("*Inf, calculando fechaVencimiento....");
                Date fechaEfetivaMasPlazo = addTiempo(fechaEfectiva, FrecuenciaPlazo, IdTcaFrecuenciaPlazo);
                fechaVencimiento = fechaEfetivaMasPlazo;
                logger.warning("*Inf, fechaVencimiento recuperada: " + fechaVencimiento);

            }
            
            if(fechaVencimiento != null && fechaPrimerPagoCapital != null)
            {
                CondicionesFinancierasBean condicionesBean = null;
                condicionesBean = (CondicionesFinancierasBean) JSFUtils.resolveExpression("#{pageFlowScope.CondicionFinancieraBean}");
                        
                if (condicionesBean.getLecturaIFI() != null && condicionesBean.getLecturaIFI()) {
                    //Fechas Temporales para evitar error, no se usan realmente
                    Date fechaProxPagInteres = fechaPrimerPagoCapital;
                    Date fechaProxRevTasa = fechaPrimerPagoCapital;
                    Date fechaProxRevSpread = fechaPrimerPagoCapital;
                    
                    Map fechasAlineadas =
                                        alinearFechasPagoRevicionYVencimiento(fechaPrimerPagoCapital, fechaProxPagInteres, fechaProxRevTasa,
                                                                              fechaProxRevSpread, fechaVencimiento);
                    
                    fechaPrimerPagoCapital = (Date) fechasAlineadas.get("fechaPrimerPagoCapial");
                    fechaVencimiento = (Date) fechasAlineadas.get("fechaVencimiento");
                } else {
                    logger.warning("*Inf, la operacion no es IFI no se realizaran alineaciones");
                }
                //Se asignan las fechas a los viewObjects
                ADFUtils.setBoundAttributeValue("FechaVencimiento", fechaVencimiento);
                ADFUtils.setBoundAttributeValue("FechaPrimerPagoCapital", fechaPrimerPagoCapital);
            }
        } //Termina el calculo de fechas
        logger.warning("***Fin de getFechasCuotasCapital");
    }
}
