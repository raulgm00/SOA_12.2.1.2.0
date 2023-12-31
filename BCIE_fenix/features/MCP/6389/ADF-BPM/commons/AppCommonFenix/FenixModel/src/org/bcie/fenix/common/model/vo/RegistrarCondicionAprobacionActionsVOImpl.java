package org.bcie.fenix.common.model.vo;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeFactory;

import oracle.adf.share.ADFContext;
import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.Key;
import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;
import oracle.jbo.domain.Array;
import oracle.jbo.domain.Number;
import oracle.jbo.server.ViewObjectImpl;

import org.bcie.catalogobo.Catalogo;
import org.bcie.condicionbo.CategoriaCondicion;
import org.bcie.condicionbo.Condicion;
import org.bcie.condicionbo.ObservacionCondicion;
import org.bcie.contratomo.ConsultarPlazoCondicionResponseType;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Mon Dec 14 18:42:59 CST 2015
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class RegistrarCondicionAprobacionActionsVOImpl
  extends ViewObjectImpl
{
    /**
     * Log de la aplicacion
     */
    private static ADFLogger logger = ADFLogger.createADFLogger(RegistrarCondicionAprobacionActionsVOImpl.class);
    
  /**
   * This is the default constructor (do not remove).
   */
  public RegistrarCondicionAprobacionActionsVOImpl()
  {
  }
  
  public void actualizarCondicionActionVO(Row poRowCondicion, Integer idTareBpm)
  {
    Row row = 
        obtenerRowCondicionAction((Integer)poRowCondicion.getAttribute(RegistrarCondicionAprobacionVORowImpl.ID));
    
    if (null != row)
    {
        modificarCondicion(row,poRowCondicion, idTareBpm);
    }
    else
    {
        agregarCondicion(poRowCondicion, idTareBpm);
    }
  }
  
  public void eliminarCondicion(Integer piId)
  {
    Row row = obtenerRowCondicionAction(piId);
    
    if (null != row)
    {
      row.setAttribute(RegistrarCondicionAprobacionActionsVORowImpl.ELIMINADO, Boolean.TRUE);
    }
  }
  
    public void eliminarNuevaCondicion(Integer piId)
    {
      logger.warning("Id de Registro a Eliminar en Actions: " + piId);
      Row row = null;
      try{
          row = obtenerRowCondicionAction(piId);    
      }catch(Exception e){
          logger.severe("Error al buscar el registro por Id en Actions", e);
      }
      
      if(row != null){
          logger.warning("Registro encontrado en Actions");
          this.setCurrentRow(row);
          this.removeCurrentRow();        
      }else{
          logger.warning("Registro no encontrado en Actions");
      }
    }

  private void agregarCondicion(Row poRowCondicion, Integer idTareBpm)
  {
    RegistrarCondicionAprobacionActionsVORowImpl newRow = (RegistrarCondicionAprobacionActionsVORowImpl)this.createRow();
    
    newRow.setAttribute(RegistrarCondicionAprobacionActionsVORowImpl.ID, poRowCondicion.getAttribute(RegistrarCondicionAprobacionVORowImpl.ID));
    //newRow.setAttribute(RegistrarCondicionAprobacionActionsVORowImpl.IDCOMISION, poRowCondicion.getAttribute(RegistrarCondicionAprobacionVORowImpl.IDCOMISION));
    newRow.setAttribute(RegistrarCondicionAprobacionActionsVORowImpl.NOMBRE, poRowCondicion.getAttribute(RegistrarCondicionAprobacionVORowImpl.NOMBRE));
    newRow.setAttribute(RegistrarCondicionAprobacionActionsVORowImpl.DESCRIPCION, poRowCondicion.getAttribute(RegistrarCondicionAprobacionVORowImpl.DESCRIPCION));
    
    newRow.setAttribute(RegistrarCondicionAprobacionActionsVORowImpl.TIPOCONTROLID, poRowCondicion.getAttribute(RegistrarCondicionAprobacionVORowImpl.TIPOCONTROLID));
    newRow.setAttribute(RegistrarCondicionAprobacionActionsVORowImpl.PLAZO, poRowCondicion.getAttribute(RegistrarCondicionAprobacionVORowImpl.PLAZO));
    newRow.setAttribute(RegistrarCondicionAprobacionActionsVORowImpl.TIPOPLAZOID, poRowCondicion.getAttribute(RegistrarCondicionAprobacionVORowImpl.TIPOPLAZOID));
    newRow.setAttribute(RegistrarCondicionAprobacionActionsVORowImpl.FECHAFINAL, poRowCondicion.getAttribute(RegistrarCondicionAprobacionVORowImpl.FECHAFINAL));
    
    newRow.setAttribute(RegistrarCondicionAprobacionActionsVORowImpl.OBSERVACIONES, poRowCondicion.getAttribute(RegistrarCondicionAprobacionVORowImpl.OBSERVACIONES));
    newRow.setAttribute(RegistrarCondicionAprobacionActionsVORowImpl.IDOBSERVACION, poRowCondicion.getAttribute(RegistrarCondicionAprobacionVORowImpl.IDOBSERVACION));
    
    newRow.setAttribute(RegistrarCondicionAprobacionActionsVORowImpl.IDCATEGORIACONDICIONLIST, poRowCondicion.getAttribute(RegistrarCondicionAprobacionVORowImpl.IDCATEGORIACONDICIONLIST));
    newRow.setAttribute(RegistrarCondicionAprobacionActionsVORowImpl.IDEVENTOCONDICIONLIST, poRowCondicion.getAttribute(RegistrarCondicionAprobacionVORowImpl.IDEVENTOCONDICIONLIST));
    
    newRow.setAttribute(RegistrarCondicionAprobacionActionsVORowImpl.IDTAREABPM, poRowCondicion.getAttribute(RegistrarCondicionAprobacionVORowImpl.IDTAREABPM));
    
    if(idTareBpm != null){
        newRow.setAttribute(RegistrarCondicionAprobacionActionsVORowImpl.IDTAREABPM, idTareBpm);
    }
    
    newRow.setAttribute(RegistrarCondicionAprobacionActionsVORowImpl.ELIMINADO, Boolean.FALSE);
    
    this.insertRow(newRow);
  }
  
  private void modificarCondicion(Row poRowCondicionAction, Row poRowCondicion, Integer idTareBpm)
  {
    poRowCondicionAction.setAttribute(RegistrarCondicionAprobacionActionsVORowImpl.ID, poRowCondicion.getAttribute(RegistrarCondicionAprobacionVORowImpl.ID));
    poRowCondicionAction.setAttribute(RegistrarCondicionAprobacionActionsVORowImpl.IDCONDICION, poRowCondicion.getAttribute(RegistrarCondicionAprobacionVORowImpl.IDCONDICION));
    poRowCondicionAction.setAttribute(RegistrarCondicionAprobacionActionsVORowImpl.NOMBRE, poRowCondicion.getAttribute(RegistrarCondicionAprobacionVORowImpl.NOMBRE));
    poRowCondicionAction.setAttribute(RegistrarCondicionAprobacionActionsVORowImpl.DESCRIPCION, poRowCondicion.getAttribute(RegistrarCondicionAprobacionVORowImpl.DESCRIPCION));
    
    poRowCondicionAction.setAttribute(RegistrarCondicionAprobacionActionsVORowImpl.TIPOCONTROLID, poRowCondicion.getAttribute(RegistrarCondicionAprobacionVORowImpl.TIPOCONTROLID));
    poRowCondicionAction.setAttribute(RegistrarCondicionAprobacionActionsVORowImpl.PLAZO, poRowCondicion.getAttribute(RegistrarCondicionAprobacionVORowImpl.PLAZO));
    poRowCondicionAction.setAttribute(RegistrarCondicionAprobacionActionsVORowImpl.TIPOPLAZOID, poRowCondicion.getAttribute(RegistrarCondicionAprobacionVORowImpl.TIPOPLAZOID));
    poRowCondicionAction.setAttribute(RegistrarCondicionAprobacionActionsVORowImpl.FECHAFINAL, poRowCondicion.getAttribute(RegistrarCondicionAprobacionVORowImpl.FECHAFINAL));
    
    poRowCondicionAction.setAttribute(RegistrarCondicionAprobacionActionsVORowImpl.OBSERVACIONES, poRowCondicion.getAttribute(RegistrarCondicionAprobacionVORowImpl.OBSERVACIONES));
    poRowCondicionAction.setAttribute(RegistrarCondicionAprobacionActionsVORowImpl.IDOBSERVACION, poRowCondicion.getAttribute(RegistrarCondicionAprobacionVORowImpl.IDOBSERVACION));
    
    poRowCondicionAction.setAttribute(RegistrarCondicionAprobacionActionsVORowImpl.IDCATEGORIACONDICIONLIST, poRowCondicion.getAttribute(RegistrarCondicionAprobacionVORowImpl.IDCATEGORIACONDICIONLIST));
    poRowCondicionAction.setAttribute(RegistrarCondicionAprobacionActionsVORowImpl.IDEVENTOCONDICIONLIST, poRowCondicion.getAttribute(RegistrarCondicionAprobacionVORowImpl.IDEVENTOCONDICIONLIST));
    
    poRowCondicionAction.setAttribute(RegistrarCondicionAprobacionActionsVORowImpl.IDTAREABPM, poRowCondicion.getAttribute(RegistrarCondicionAprobacionVORowImpl.IDTAREABPM));
    
    if(idTareBpm != null){
        poRowCondicionAction.setAttribute(RegistrarCondicionAprobacionActionsVORowImpl.IDTAREABPM, idTareBpm);    
    }
      
    poRowCondicionAction.setAttribute(RegistrarCondicionAprobacionActionsVORowImpl.MODIFICADO, Boolean.TRUE);
    
  }

  public Row obtenerRowCondicionAction (Integer piId)
  {
    return this.getRow((new Key(new Object[]{piId})));
  }
  
  public Integer getNewId()
  {
    return (this.getRowCount()+1);
  }


  public void cargarCondiciones(ConsultarPlazoCondicionResponseType plazoCondicionResponse)
  {
    if(null!= plazoCondicionResponse.getListaCondicion())
    {
      int i = 1;
      for(Condicion condicion: plazoCondicionResponse.getListaCondicion())
      {
        agregarRowCondicion(condicion, i);
        i++;
      }
    }
  }

  private void agregarRowCondicion(Condicion condicion, Integer piId)
  {
    RegistrarCondicionAprobacionActionsVORowImpl newRow = (RegistrarCondicionAprobacionActionsVORowImpl) this.createRow();
  
    newRow.setAttribute(RegistrarCondicionAprobacionActionsVORowImpl.ID, piId);
    newRow.setAttribute(RegistrarCondicionAprobacionActionsVORowImpl.IDCONDICION, condicion.getIdCondicion());
    newRow.setAttribute(RegistrarCondicionAprobacionActionsVORowImpl.NOMBRE, condicion.getNombre());
    newRow.setAttribute(RegistrarCondicionAprobacionActionsVORowImpl.DESCRIPCION, condicion.getDescripcion());
    
    logger.warning("El Id de Tipo de Control que proviene de WS es: " + condicion.getTipoCondicion());
    logger.warning("Se asigna por defecto el Id de Tipo Control 1 " +
                   "correspondiente a Evento para la tarea de Actualizar TCC del Proceso de Aprobacion");
    //Se asigna por defecto el tipo de control evento
    newRow.setAttribute(RegistrarCondicionAprobacionActionsVORowImpl.TIPOCONTROLID, new oracle.jbo.domain.Number(1));    
    
    newRow.setAttribute(RegistrarCondicionAprobacionActionsVORowImpl.PLAZO, condicion.getPlazo());
    
    ObservacionCondicion observaciones = null;
    if(condicion.getObservaciones() != null &&
        condicion.getObservaciones().size() > 0){
        observaciones = condicion.getObservaciones().get(0);
  
        if(observaciones != null){
            if(observaciones.getId() > 0){
                newRow.setAttribute(RegistrarCondicionAprobacionActionsVORowImpl.IDOBSERVACION, new Number(observaciones.getId()));
            }
      
            if(observaciones.getObservacion() != null){
                newRow.setAttribute(RegistrarCondicionAprobacionActionsVORowImpl.OBSERVACIONES, observaciones.getObservacion().trim()); 
            }
        }
    }
    
    if(condicion.getFrecuenciaPlazo() != null){
        newRow.setAttribute(RegistrarCondicionAprobacionActionsVORowImpl.TIPOPLAZOID, condicion.getFrecuenciaPlazo().getId());    
    }
    
    newRow.setAttribute(RegistrarCondicionAprobacionActionsVORowImpl.ELIMINADO, Boolean.FALSE);
    
    if(null != condicion.getFechaFinal())
      newRow.setAttribute(RegistrarCondicionAprobacionVORowImpl.FECHAFINAL, condicion.getFechaFinal().toGregorianCalendar().getTime());
    
      List<Number> categoriasCondList = null;
      if(condicion.getCategoriaCondicion() != null &&
         condicion.getCategoriaCondicion().size() > 0){
          
          for(CategoriaCondicion cat : condicion.getCategoriaCondicion()){
              
              if(cat != null &&
                 cat.getId() > 0){
                  if(categoriasCondList == null){
                      categoriasCondList = new ArrayList<Number>();
                  }
                  categoriasCondList.add(new Number(cat.getId()));
              }
          }
          
          if(categoriasCondList != null){
              newRow.setAttribute(RegistrarCondicionAprobacionActionsVORowImpl.IDCATEGORIACONDICIONLIST, new Array(categoriasCondList));
          }
      }
      
      List<Number> eventosCondList = null;
      if(condicion.getEventoCondicion() != null &&
         condicion.getEventoCondicion().size() > 0){
          
          for(Catalogo evento : condicion.getEventoCondicion()){
              if(evento != null &&
                 evento.getId() > 0){
                  if(eventosCondList == null){
                      eventosCondList = new ArrayList<Number>();
                  }
                  eventosCondList.add(new Number(evento.getId()));
              }
          }
          
          if(eventosCondList != null){
              newRow.setAttribute(RegistrarCondicionAprobacionActionsVORowImpl.IDEVENTOCONDICIONLIST, new Array(eventosCondList));
          }
      }
    
    this.insertRow(newRow);
  }

  public void setCondicionesResquest(List<Condicion> listCondicion, Integer idTareaBpm)
  {
    RowSetIterator rs = this.createRowSetIterator(null);
    rs.reset();
     
    logger.warning("Cantidad de Registros de Condicion: " + getEstimatedRowCount());
    
    while (rs.hasNext()) {
         RegistrarCondicionAprobacionActionsVORowImpl row = 
             (RegistrarCondicionAprobacionActionsVORowImpl) rs.next();
        //TODO Vlidar si esta aliminada y no tienen id de DB no debera de formar parte del payload
        Condicion condicion = generarCondicion (row, idTareaBpm);
      
        listCondicion.add(condicion);
    }

    rs.closeRowSetIterator();
  }

    @SuppressWarnings("unchecked")
    private Condicion generarCondicion(RegistrarCondicionAprobacionActionsVORowImpl row, Integer idTareaBpm)
  { 
    //TODO Verificar cambios en el WS, los campos requeridos para la creacion de condiciones e impacto en pantalla
      
    Condicion newCondicion = new Condicion();
    
    Catalogo fechaFrecuenciaPlazo = null;
    Catalogo controlCondicion = null;
    
    if(null!=row.getAttribute(RegistrarCondicionAprobacionActionsVORowImpl.IDCONDICION))
      newCondicion.setIdCondicion(((oracle.jbo.domain.Number)row.getAttribute(RegistrarCondicionAprobacionActionsVORowImpl.IDCONDICION)).longValue());
    if(null!=row.getAttribute(RegistrarCondicionAprobacionActionsVORowImpl.NOMBRE))
      newCondicion.setNombre((String)row.getAttribute(RegistrarCondicionAprobacionActionsVORowImpl.NOMBRE));
    if(null!=row.getAttribute(RegistrarCondicionAprobacionActionsVORowImpl.DESCRIPCION))
      newCondicion.setDescripcion((String)row.getAttribute(RegistrarCondicionAprobacionActionsVORowImpl.DESCRIPCION));
    if(null!=row.getAttribute(RegistrarCondicionAprobacionActionsVORowImpl.PLAZO))
      newCondicion.setPlazo((Integer)row.getAttribute(RegistrarCondicionAprobacionActionsVORowImpl.PLAZO));
    if(null!=row.getAttribute(RegistrarCondicionAprobacionActionsVORowImpl.ELIMINADO))
      newCondicion.setEstado(!((Boolean)row.getAttribute(RegistrarCondicionAprobacionActionsVORowImpl.ELIMINADO)));
    
    try
    {
      if(null!=row.getAttribute(RegistrarCondicionAprobacionActionsVORowImpl.FECHAFINAL))
      {
        GregorianCalendar cFechaAprobacion = new GregorianCalendar();
        cFechaAprobacion.setTime(((oracle.jbo.domain.Date)row.getAttribute(RegistrarCondicionAprobacionActionsVORowImpl.FECHAFINAL)).getValue());
        newCondicion.setFechaFinal(DatatypeFactory.newInstance().newXMLGregorianCalendar(cFechaAprobacion));
      }
    }
    catch(Exception ex)
    {
      logger.severe("Error al construir objeto de Condicion para WS", ex);
      //logger.log(ADFLogger.ERROR,ex.getClass() + ":" + ex.getMessage());
    }
    
    if(null!=row.getAttribute(RegistrarCondicionAprobacionActionsVORowImpl.TIPOPLAZOID))
    {   
      fechaFrecuenciaPlazo = new Catalogo();
      fechaFrecuenciaPlazo.setId(((oracle.jbo.domain.Number)row.getAttribute(RegistrarCondicionAprobacionActionsVORowImpl.TIPOPLAZOID)).longValue());
    }
    newCondicion.setFrecuenciaPlazo(fechaFrecuenciaPlazo);
    
    //Asignacion obsoleta de Categoria de la Condicion
    /*
    if(null!=row.getAttribute(RegistrarCondicionAprobacionActionsVORowImpl.CATEGORIAID))
    {
      categoriaCondicion.setId(((oracle.jbo.domain.Number)row.getAttribute(RegistrarCondicionAprobacionActionsVORowImpl.CATEGORIAID)).longValue());
    }
    */

    if(null!=row.getAttribute(RegistrarCondicionAprobacionActionsVORowImpl.TIPOCONTROLID))
    {  
      controlCondicion = new Catalogo();
      controlCondicion.setId(((oracle.jbo.domain.Number)row.getAttribute(RegistrarCondicionAprobacionActionsVORowImpl.TIPOCONTROLID)).longValue());
    }
    newCondicion.setControlCondicion(controlCondicion);
    
    Array eventosList = null;
    eventosList = (Array) row.getAttribute(RegistrarCondicionAprobacionActionsVORowImpl.IDEVENTOCONDICIONLIST);
    
    logger.warning("Obtiene Array de Id de Eventos Condicion: " + eventosList);
    if(eventosList != null){
        Catalogo eventoCondicion = null; 
        for(Number id : (List<Number>)eventosList.getList()){
            eventoCondicion = new Catalogo();
            eventoCondicion.setId(id.longValue());
            if(eventoCondicion != null &&
               newCondicion.getEventoCondicion() != null){
                newCondicion.getEventoCondicion().add(eventoCondicion);        
            }
        }
    }
    
    Array categoriasList = null;
    categoriasList = (Array) row.getAttribute(RegistrarCondicionAprobacionActionsVORowImpl.IDCATEGORIACONDICIONLIST);
    
    logger.warning("Obtiene Array de Id de Categorias Condicion: " + categoriasList);
    if(categoriasList != null){
        
        CategoriaCondicion categoriaCondicion = null;
        for(Number id : (List<Number>)categoriasList.getList()){
            categoriaCondicion = new CategoriaCondicion();    
            categoriaCondicion.setId(id.longValue());
            if(categoriaCondicion != null &&
               newCondicion.getCategoriaCondicion() != null){
                newCondicion.getCategoriaCondicion().add(categoriaCondicion);
            }
        }
    }
    
    ObservacionCondicion catObservacion = null;
    Number idObservacion = null;
    String observacion = null;
    Integer intIdTareaBpm = null;
    
    idObservacion = (Number) row.getAttribute(RegistrarCondicionAprobacionActionsVORowImpl.IDOBSERVACION);
    observacion = (String) row.getAttribute(RegistrarCondicionAprobacionActionsVORowImpl.OBSERVACIONES);
    intIdTareaBpm = (Integer) row.getAttribute(RegistrarCondicionAprobacionActionsVORowImpl.IDTAREABPM);
    
    if(idTareaBpm != null){
        intIdTareaBpm = idTareaBpm;
    }
    
    if(idObservacion != null ||
       observacion != null){
        
        if(idObservacion != null){
            if(observacion == null){
                observacion = "";
            }
        }
        
        catObservacion = new ObservacionCondicion();
        if(idObservacion != null){
            catObservacion.setId(idObservacion.longValue());    
        }
        
        if(observacion != null){
            observacion = observacion.trim();
            catObservacion.setObservacion(observacion);
        }
        
        String usuarioLogin = ADFContext.getCurrent().getSecurityContext().getUserName();
        catObservacion.setLoginUsuario(usuarioLogin);
        
        String nombre = ADFContext.getCurrent().getSecurityContext().getUserProfile().getDisplayName();
        if(nombre == null ||
           "".equals(nombre)){
            nombre = ADFContext.getCurrent().getSecurityContext().getUserProfile().getName();
        }
        
        catObservacion.setNombreUsuario(nombre);
        
        Catalogo catTareaBpm = null;
        if(intIdTareaBpm != null){
            catTareaBpm = new Catalogo();
            catTareaBpm.setId(new Long(intIdTareaBpm));
            catObservacion.setTareaBPM(catTareaBpm);    
        }
        
        catObservacion.setEsPrincipal(true);
        
        newCondicion.getObservaciones().add(catObservacion);
    }

    if(newCondicion.getEventoCondicion() != null){
        logger.warning("Cantidad de Nodos Eventos Condicion: " + newCondicion.getEventoCondicion().size());    
    }
    if(newCondicion.getCategoriaCondicion() != null){
        logger.warning("Cantidad de Nodos Categorias Condicion: " + newCondicion.getCategoriaCondicion().size());    
    }

    return newCondicion;
  }
  
    public void asignarIdCategoriaCondicionList(RegistrarCondicionAprobacionVORowImpl rowCondicion,
                                                List<Number> pCategoriaCondList){
        
        if(rowCondicion != null){
            Row row = 
                obtenerRowCondicionAction((Integer)rowCondicion.getAttribute(RegistrarCondicionAprobacionVORowImpl.ID));
            if(row != null){
                
                Array array = null;
                try{
                    array = new Array(pCategoriaCondList);
                    row.setAttribute(RegistrarCondicionAprobacionActionsVORowImpl.IDCATEGORIACONDICIONLIST, array);    
                }catch(Exception e){
                    logger.severe("Error al asignar lista de categoria de condiciones", e);
                }
            }else{
                logger.severe("Error Registro de Condicion Aprobacion Actions no encontrado");
            }
        }else{
            logger.severe("Error el registro de Condicion es NULL");
        }
    }
    
    public void asignarIdEventoCondicionList(RegistrarCondicionAprobacionVORowImpl rowCondicion,
                                             List<Number> pEventosCondList){
        
        if(rowCondicion != null){
            Row row = 
                obtenerRowCondicionAction((Integer)rowCondicion.getAttribute(RegistrarCondicionAprobacionVORowImpl.ID));
            if(row != null){
                Array array = null;
                try{
                    array = new Array(pEventosCondList);
                    row.setAttribute(RegistrarCondicionAprobacionActionsVORowImpl.IDEVENTOCONDICIONLIST, array);    
                }catch(Exception e){
                    logger.severe("Error al asignar lista de eventos de condiciones", e);
                }
            }else{
                logger.severe("Error Registro de Condicion Aprobacion Actions no encontrado");
            }
        }else{
            logger.severe("Error el registro de Condicion es NULL");
        }
    }
}

