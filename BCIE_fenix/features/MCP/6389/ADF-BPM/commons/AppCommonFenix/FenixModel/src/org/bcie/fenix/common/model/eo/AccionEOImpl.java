package org.bcie.fenix.common.model.eo;

import oracle.jbo.Key;
import oracle.jbo.server.EntityDefImpl;
import oracle.jbo.server.EntityImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Wed Jun 15 19:43:56 CDT 2016
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class AccionEOImpl extends EntityImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    public enum AttributesEnum {
        Id,
        IdCliente,
        IdOperacion,
        Accion,
        IdEstadoAccion,
        IdTcaCategoriaAccion,
        IdTcaRolAsignado,
        FechaRegistro,
        BanEstatus,
        FechaAtencion,
        OperacionEO,
        ObservacionesAccionEO,
        SeguimientoAccionEO,
        ClientesEO;
        private static AttributesEnum[] vals = null;
        private static final int firstIndex = 0;

        public int index() {
            return AttributesEnum.firstIndex() + ordinal();
        }

        public static final int firstIndex() {
            return firstIndex;
        }

        public static int count() {
            return AttributesEnum.firstIndex() + AttributesEnum.staticValues().length;
        }

        public static final AttributesEnum[] staticValues() {
            if (vals == null) {
                vals = AttributesEnum.values();
            }
            return vals;
        }
    }


    public static final int ID = AttributesEnum.Id.index();
    public static final int IDCLIENTE = AttributesEnum.IdCliente.index();
    public static final int IDOPERACION = AttributesEnum.IdOperacion.index();
    public static final int ACCION = AttributesEnum.Accion.index();
    public static final int IDESTADOACCION = AttributesEnum.IdEstadoAccion.index();
    public static final int IDTCACATEGORIAACCION = AttributesEnum.IdTcaCategoriaAccion.index();
    public static final int IDTCAROLASIGNADO = AttributesEnum.IdTcaRolAsignado.index();
    public static final int FECHAREGISTRO = AttributesEnum.FechaRegistro.index();
    public static final int BANESTATUS = AttributesEnum.BanEstatus.index();
    public static final int FECHAATENCION = AttributesEnum.FechaAtencion.index();
    public static final int OPERACIONEO = AttributesEnum.OperacionEO.index();
    public static final int OBSERVACIONESACCIONEO = AttributesEnum.ObservacionesAccionEO.index();
    public static final int SEGUIMIENTOACCIONEO = AttributesEnum.SeguimientoAccionEO.index();
    public static final int CLIENTESEO = AttributesEnum.ClientesEO.index();

    /**
     * This is the default constructor (do not remove).
     */
    public AccionEOImpl() {
    }

    /**
     * @param id key constituent

     * @return a Key object based on given key constituents.
     */
    public static Key createPrimaryKey(Long id) {
        return new Key(new Object[] { id });
    }

    /**
     * @return the definition object for this instance class.
     */
    public static synchronized EntityDefImpl getDefinitionObject() {
        return EntityDefImpl.findDefObject("org.bcie.fenix.common.model.eo.AccionEO");
    }


}

