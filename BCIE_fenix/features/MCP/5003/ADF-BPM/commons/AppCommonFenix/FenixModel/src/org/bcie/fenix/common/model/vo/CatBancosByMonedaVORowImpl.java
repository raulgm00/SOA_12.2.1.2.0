package org.bcie.fenix.common.model.vo;

import oracle.jbo.server.ViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Wed Oct 26 21:08:05 CDT 2016
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CatBancosByMonedaVORowImpl extends ViewRowImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    public enum AttributesEnum {
        Cliente,
        Banco,
        ClaveNombreBanco;
        private static AttributesEnum[] vals = null;
        private static final int firstIndex = 0;

        public int index() {
            return CatBancosByMonedaVORowImpl.AttributesEnum.firstIndex() + ordinal();
        }

        public static final int firstIndex() {
            return firstIndex;
        }

        public static int count() {
            return CatBancosByMonedaVORowImpl.AttributesEnum.firstIndex() +
                   CatBancosByMonedaVORowImpl.AttributesEnum.staticValues().length;
        }

        public static final AttributesEnum[] staticValues() {
            if (vals == null) {
                vals = CatBancosByMonedaVORowImpl.AttributesEnum.values();
            }
            return vals;
        }
    }


    public static final int CLIENTE = AttributesEnum.Cliente.index();
    public static final int BANCO = AttributesEnum.Banco.index();
    public static final int CLAVENOMBREBANCO = AttributesEnum.ClaveNombreBanco.index();

    /**
     * This is the default constructor (do not remove).
     */
    public CatBancosByMonedaVORowImpl() {
    }


    /**
     * Gets the attribute value for the calculated attribute Cliente.
     * @return the Cliente
     */
    public String getCliente() {
        return (String) getAttributeInternal(CLIENTE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Cliente.
     * @param value value to set the  Cliente
     */
    public void setCliente(String value) {
        setAttributeInternal(CLIENTE, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Banco.
     * @return the Banco
     */
    public String getBanco() {
        return (String) getAttributeInternal(BANCO);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Banco.
     * @param value value to set the  Banco
     */
    public void setBanco(String value) {
        setAttributeInternal(BANCO, value);
    }


    /**
     * Gets the attribute value for the calculated attribute ClaveNombreBanco.
     * @return the ClaveNombreBanco
     */
    public String getClaveNombreBanco() {
        return (String) getAttributeInternal(CLAVENOMBREBANCO);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute ClaveNombreBanco.
     * @param value value to set the  ClaveNombreBanco
     */
    public void setClaveNombreBanco(String value) {
        setAttributeInternal(CLAVENOMBREBANCO, value);
    }


}

