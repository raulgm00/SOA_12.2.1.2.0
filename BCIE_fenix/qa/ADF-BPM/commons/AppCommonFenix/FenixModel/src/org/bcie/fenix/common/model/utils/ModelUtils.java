package org.bcie.fenix.common.model.utils;

import java.io.StringWriter;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.Row;

public class ModelUtils
{
  public static final String MSG_WS_EXCEPTION = ">HNWS NO ES POSIBLE OBTENER XML DE SERVICIO: ";
  public static final String MSG_WS_PREFIX = ">HNWS ";
  public static final String MSG_WS_START = ">HNWS Invocando Servicio::: ";
  public static final String MSG_WS_START_TIME = ">HNWS Hora de inicio:";
  public static final String MSG_WS_END = ">HNWS Respuesta Servicio::: ";
  public static final String MSG_WS_END_TIME = ">HNWS Hora de termino:";
  public static final String MSG_WS_END_TIME_SEC = ">HNWS Tiempo de proceso en segundos:";
  
  public ModelUtils()
  {
    super();
  }
  
  public static StringWriter writeXMLRequest(Object request, Class requestClass) throws JAXBException {
      StringWriter writer = new StringWriter();
      JAXBContext context = JAXBContext.newInstance(requestClass);
      Marshaller m = context.createMarshaller();
      m.marshal(new JAXBElement(new QName(requestClass.getName()), requestClass, request), writer);
      return writer;
  }
  
  public static Date logStartWS(ADFLogger logger,Object poWSObject, String psServiceName)
  {
    Date horaInicio = new GregorianCalendar().getTime();
    try
    {
      printWSMessage(logger, poWSObject, MSG_WS_START, MSG_WS_START_TIME, psServiceName, horaInicio);
    }
    catch (Exception e)
    {
      logger.log(ADFLogger.WARNING, ((new StringBuilder())
                                      .append(MSG_WS_EXCEPTION).append(psServiceName)
                                      .append(e.getClass()).append(e.getMessage())).toString()
                );
    }
    return horaInicio;
  }
  
  public static void logEndWS(ADFLogger logger,Object poWSObject,  String psServiceName, Date pdHoraInicio)
  {
    
    Date horaTermino = new GregorianCalendar().getTime();
    try
    {
      Long tiempoProcesoSegundos = (horaTermino.getTime() - pdHoraInicio.getTime()) / 1000;
      logger.log(ADFLogger.WARNING, 
                 "Inicia escritura de XML Response en el log " + 
                 tiempoProcesoSegundos + 
                 " Objeto: " + poWSObject.toString());
      printWSMessage(logger, poWSObject, MSG_WS_END, MSG_WS_END_TIME, psServiceName, horaTermino);
        
      horaTermino = new GregorianCalendar().getTime();
      tiempoProcesoSegundos = (horaTermino.getTime() - pdHoraInicio.getTime()) / 1000;
      logger.log(ADFLogger.WARNING, MSG_WS_END_TIME_SEC + tiempoProcesoSegundos);
    }
    catch (Exception e)
    {
      logger.log(ADFLogger.WARNING, ((new StringBuilder())
                                      .append(MSG_WS_EXCEPTION).append(psServiceName)
                                      .append(e.getClass()).append(e.getMessage())).toString()
                );
    }

  }
  
  private static void printWSMessage(ADFLogger logger,Object poWSObject,String psMsgInit, String psMsgTime, String psServiceName, Date pdTime)
    throws JAXBException
  {
    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    
    logger.log(ADFLogger.WARNING, psMsgInit + psServiceName);
    logger.log(ADFLogger.WARNING, psMsgTime + 
                                  " - Objeto: " + poWSObject.toString() +
                                  " - " +
                                  dateFormat.format(pdTime.getTime()));
    /*
    logger.log(ADFLogger.WARNING, 
               MSG_WS_PREFIX + 
               " Objeto: " + poWSObject.toString() + 
               " " +
               writeXMLRequest(poWSObject, poWSObject.getClass()).toString());
    */
    new Thread(new PrintWSMessageRunnable(MSG_WS_PREFIX, poWSObject)).start();
  }
  
    private static void printWSMessageDocumentos(ADFLogger logger,Object poWSObject,String psMsgInit, String psMsgTime, String psServiceName, Date pdTime)
      throws JAXBException
    {
      SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
      
      logger.log(ADFLogger.WARNING, psMsgInit + psServiceName);
      logger.log(ADFLogger.WARNING, psMsgTime + 
                                    " - Objeto: " + poWSObject.toString() +
                                    " - " +
                                    dateFormat.format(pdTime.getTime()));
      /*
      logger.log(ADFLogger.WARNING, 
                 MSG_WS_PREFIX + 
                 " Objeto: " + poWSObject.toString() + 
                 " " +
                 writeXMLRequest(poWSObject, poWSObject.getClass()).toString());
      */
      new Thread(new PrintWSResponseDocsRunnable(MSG_WS_PREFIX, poWSObject)).start();
    }
    
    public static void logEndWSDocs(ADFLogger logger,Object poWSObject,  String psServiceName, Date pdHoraInicio)
    {
      
      Date horaTermino = new GregorianCalendar().getTime();
      try
      {
        Long tiempoProcesoSegundos = (horaTermino.getTime() - pdHoraInicio.getTime()) / 1000;
        logger.log(ADFLogger.WARNING, 
                   "Inicia escritura de XML Response en el log " + 
                   tiempoProcesoSegundos + 
                   " Objeto: " + poWSObject.toString());
        printWSMessageDocumentos(logger, poWSObject, MSG_WS_END, MSG_WS_END_TIME, psServiceName, horaTermino);
          
        horaTermino = new GregorianCalendar().getTime();
        tiempoProcesoSegundos = (horaTermino.getTime() - pdHoraInicio.getTime()) / 1000;
        logger.log(ADFLogger.WARNING, MSG_WS_END_TIME_SEC + tiempoProcesoSegundos);
      }
      catch (Exception e)
      {
        logger.log(ADFLogger.WARNING, ((new StringBuilder())
                                        .append(MSG_WS_EXCEPTION).append(psServiceName)
                                        .append(e.getClass()).append(e.getMessage())).toString()
                  );
      }

    }
    
  public static String getString(Object ... poObjects)
  {
    StringBuilder sb = new StringBuilder();
    
    for (Object o : poObjects)
    {
      sb.append(o);
    }
    
    return sb.toString();
  }
  
  public static String[] convertListStringToArray(List<String> lista) {
        String[] stringArray = new String[0];

        if (null != lista) {
            stringArray = new String[lista.size()];
            stringArray = lista.toArray(stringArray);
        }

        return stringArray;
    }
    
    public static Row[] convertListRowToArray(List<Row> lista) {
        Row[] rowArray = new Row[0];

        if (null != lista) {
            rowArray = new Row[lista.size()];
            rowArray = lista.toArray(rowArray);
        }

        return rowArray;
    }
}

