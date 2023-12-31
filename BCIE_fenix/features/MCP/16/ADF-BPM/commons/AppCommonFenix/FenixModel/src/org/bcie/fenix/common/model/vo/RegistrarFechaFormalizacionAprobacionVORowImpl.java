package org.bcie.fenix.common.model.vo;

import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;
import oracle.jbo.server.ViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Mon Dec 14 21:00:24 CST 2015
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class RegistrarFechaFormalizacionAprobacionVORowImpl
  extends ViewRowImpl {
  /**
   * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
   */
  public enum AttributesEnum
  {
        Id,
        TipoFechaInicioId,
        FechaInicio,
        Plazo,
        TipoPlazoId,
        IdTermino,
        Estado,
        TcaTipoFrecuenciaNVO,
        TipoFechaInicioAprobacionLOV;
        private static AttributesEnum[] vals = null;
        private static final int firstIndex = 0;

    public int index()
    {
      return AttributesEnum.firstIndex() + ordinal();
    }

    public static final int firstIndex()
    {
      return firstIndex;
    }

    public static int count()
    {
      return AttributesEnum.firstIndex() + AttributesEnum.staticValues().length;
    }

    public static final AttributesEnum[] staticValues()
    {
      if (vals == null)
      {
        vals = AttributesEnum.values();
      }
      return vals;
    }
  }


    public static final int ID = AttributesEnum.Id.index();
    public static final int TIPOFECHAINICIOID = AttributesEnum.TipoFechaInicioId.index();
    public static final int FECHAINICIO = AttributesEnum.FechaInicio.index();
    public static final int PLAZO = AttributesEnum.Plazo.index();
    public static final int TIPOPLAZOID = AttributesEnum.TipoPlazoId.index();
    public static final int IDTERMINO = AttributesEnum.IdTermino.index();
    public static final int ESTADO = AttributesEnum.Estado.index();
    public static final int TCATIPOFRECUENCIANVO = AttributesEnum.TcaTipoFrecuenciaNVO.index();
    public static final int TIPOFECHAINICIOAPROBACIONLOV = AttributesEnum.TipoFechaInicioAprobacionLOV.index();

    /**
     * This is the default constructor (do not remove).
     */
  public RegistrarFechaFormalizacionAprobacionVORowImpl()
  {
  }
}

