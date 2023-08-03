package org.bcie.fenix.common.model.utils;

import java.lang.reflect.InvocationTargetException;

import java.net.MalformedURLException;
import java.net.URL;

import oracle.adf.share.logging.ADFLogger;

public interface IWsdlLocation {


    public static final String CONFIGURACION_ITERATOR = "ConfiguracionVOIterator";

    public static final String APROBACION = "WSDL_APROBACION";
    public static final String CLIENTE = "WSDL_CLIENTE";
    public static final String COMISION = "WSDL_COMISION";
    public static final String ADQUISICION = "WSDL_ADQUISICION";
    public static final String DECLARACION_JURADA = "WSDL_DECLARACION_JURADA";
    public static final String DOCUMENTO = "WSDL_DOCUMENTO";
    public static final String DOCPOP = "URL_DOCPOP";
    public static final String EVALUACION = "WSDL_EVALUACION";
    public static final String LINEA_CREDITO = "WSDL_LINEA_CREDITO";
    public static final String OPERACION = "WSDL_OPERACION";
    public static final String SOLICITUD_APROBACION = "WSDL_SOLICITUD_APROBACION";
    public static final String USUARIO = "WSDL_USUARIO";
    public static final String PROCESOS_ALTERNOS = "WSDL_PROCESOS_ALTERNOS";
    public static final String PROCESOS_PRINCIPALES = "WSDL_PROCESOS_PRINCIPALES";
    public static final String CONDICION = "WSDL_CONDICION";
    public static final String CONDICION2 = "WSDL_CONDICION2";
    public static final String CONTRATO = "WSDL_CONTRATO";
    public static final String MATRIZ = "WSDL_MATRIZ";
    public static final String CUESTIONARIO = "WSDL_CUESTIONARIO";
    public static final String IMPORTES = "WSDL_IMPORTES";
 // public static final String DESEMBOLSO_UTILIDADES = "WSDL_DESEMBOLSO_UTILIDADES"; la url del servicio esta mal se debe usar -> DESEMBOLSO =  WSDL_DESEMBOLSO
    public static final String REGLA_TAREA = "WSDL_REGLA_TAREA";
    public static final String DESEMBOLSO = "WSDL_DESEMBOLSO";
    public static final String RECUPERACION = "WSDL_RECUPERACION";
    public static final String OBTENER_URL = "WSDL_OBTENER_URL";
    public static final String REPORTE_PREPAGO = "WSDL_REPORTE_PREPAGO";
    public static final String IMPLEMENTACION_PCT = "WSDL_IMPLEMENTACIONPCT";
    public static final String PROCESOS_UTILIDAD = "WSDL_PROCESOS_UTILIDAD";
    public static final String CORREO_ELECTRONICO = "WSDL_ENVIAR_CORREO";
    public static final String CREAR_ACTUALIZAR_BIT_DOC = "WSDL_CREAR_ACTUALIZAR_BIT_DOC";
    public static final String WSDL_CONSULTAR_CON_FIN = "WSDL_CONSULTAR_CON_FIN";
    public static final String SEGURIDAD = "WSDL_SEGURIDAD";
    
    public static final String CONFIGURACION_PROCESO = "WSDL_CONFIGURACION_PROCESO";
    public static class Service {
        private static ADFLogger logger = ADFLogger.createADFLogger(Service.class.getClass()); ;

        @SuppressWarnings({ "unchecked", "oracle.jdeveloper.java.insufficient-catch-block" })
        public static <T> T getInstance(Class<T> clazz, String wsdl) {
            T service = null;
            try {
                URL url = new URL(wsdl);
                Class[] types = { URL.class };
                service = clazz.getDeclaredConstructor(types).newInstance(url);
            } catch (MalformedURLException e) {
                logger.log(ADFLogger.WARNING, e.getMessage());
                e.printStackTrace();
                try {
                    service = clazz.newInstance();
                } catch (IllegalAccessException ex) {
                    logger.log(ADFLogger.ERROR, ex.getMessage());
                    e.printStackTrace();
                } catch (InstantiationException ex) {
                    logger.log(ADFLogger.ERROR, ex.getMessage());
                    e.printStackTrace();
                }
            } catch (IllegalAccessException e) {
                logger.log(ADFLogger.ERROR, e.getMessage());
                e.printStackTrace();
            } catch (InstantiationException e) {
                logger.log(ADFLogger.ERROR, e.getMessage());
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                logger.log(ADFLogger.ERROR, e.getMessage());
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                logger.log(ADFLogger.ERROR, e.getMessage());
                e.printStackTrace();
            }
            return service;
        }
    }
}
