package org.bcie.fenix.common.model.eo;

import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.Key;
import oracle.jbo.RowInconsistentException;
import oracle.jbo.server.EntityDefImpl;
import oracle.jbo.server.EntityImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Thu Aug 10 11:28:30 CDT 2017
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class SaldoPreinvDesembolsoEOImpl extends EntityImpl {
    
    private static ADFLogger logger = ADFLogger.createADFLogger(SaldoPreinvDesembolsoEOImpl.class);
    
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    public enum AttributesEnum {
        Id,
        IdContratoDesembolso,
        CodigoPreinversion,
        Monto,
        TasaInteres,
        MontoTotal,
        FechaRegistro,
        BanEstatus,
        ContratoDesembolsoEO;
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
    public static final int IDCONTRATODESEMBOLSO = AttributesEnum.IdContratoDesembolso.index();
    public static final int CODIGOPREINVERSION = AttributesEnum.CodigoPreinversion.index();
    public static final int MONTO = AttributesEnum.Monto.index();
    public static final int TASAINTERES = AttributesEnum.TasaInteres.index();
    public static final int MONTOTOTAL = AttributesEnum.MontoTotal.index();
    public static final int FECHAREGISTRO = AttributesEnum.FechaRegistro.index();
    public static final int BANESTATUS = AttributesEnum.BanEstatus.index();
    public static final int CONTRATODESEMBOLSOEO = AttributesEnum.ContratoDesembolsoEO.index();

    /**
     * This is the default constructor (do not remove).
     */
    public SaldoPreinvDesembolsoEOImpl() {
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
        return EntityDefImpl.findDefObject("org.bcie.fenix.common.model.eo.SaldoPreinvDesembolsoEO");
    }
    
    public void lock() {
        try {
            super.lock();
        } catch (RowInconsistentException e) {
            logger.warning("RowInconsistentException ContratoDesembolsoEO");
            refresh(REFRESH_WITH_DB_ONLY_IF_UNCHANGED | REFRESH_CONTAINEES);
            super.lock();
        }
    }
}

