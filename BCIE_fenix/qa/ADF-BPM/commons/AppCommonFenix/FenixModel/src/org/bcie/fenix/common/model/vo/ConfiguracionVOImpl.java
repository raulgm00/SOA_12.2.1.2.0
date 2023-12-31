package org.bcie.fenix.common.model.vo;

import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.Key;
import oracle.jbo.Row;
import oracle.jbo.server.ViewObjectImpl;

import org.bcie.fenix.common.model.vo.common.ConfiguracionVO;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Thu Jan 28 17:29:05 CST 2016
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class ConfiguracionVOImpl
  extends ViewObjectImpl implements ConfiguracionVO {
    
    private static ADFLogger logger = null;
    
  /**
   * This is the default constructor (do not remove).
   */
  public ConfiguracionVOImpl()
  {
      if (logger == null) {
          logger = ADFLogger.createADFLogger(this.getClass());
      }
  }
  
  public Row getValor(String llave) {
      return this.getRow(new Key(new Object[]{llave}));
  }
  
    public String getValorWsdl(String llave) {
  
        if(this.getRow(new Key(new Object[]{llave})) != null)
            return (String)this.getRow(new Key(new Object[]{llave})).getAttribute("Valor");
        else
            return null;
    }
    
    public String getCuentaPuenteDesembolso() {
        logger.warning("Inicia metodo getCuentaPuenteDesembolso");
        double TInicio, TFin, tiempo; //Variables para determinar el tiempo de ejecuci�n
        TInicio = System.currentTimeMillis(); //Tomamos la hora en que inicio el algoritmo y la almacenamos en la variable inicio

        String cuentaPuente = null;
        String llaveCuentaPuente = "CUENTA_PUENTE_DESEMBOLSO";
        cuentaPuente = (String) this.getRow(new Key(new Object[] { llaveCuentaPuente })).getAttribute("Valor");

        TFin = System.currentTimeMillis(); //Tomamos la hora en que finaliz� el algoritmo y la almacenamos en la variable T
        tiempo = (TFin - TInicio) / 1000; //Calculamos los milisegundos de diferencia
        logger.warning("Termina metodo getCuentaPuenteDesembolso con una duracion de: " + tiempo + " segundos");
        return cuentaPuente;
    }
}

