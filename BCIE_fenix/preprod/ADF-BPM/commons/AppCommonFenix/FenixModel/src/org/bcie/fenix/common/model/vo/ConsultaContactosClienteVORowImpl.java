package org.bcie.fenix.common.model.vo;

import oracle.jbo.server.ViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Wed Dec 07 17:37:17 CST 2016
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class ConsultaContactosClienteVORowImpl extends ViewRowImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    public enum AttributesEnum {
        IdContactosClientes,
        IdCliente,
        IdContacto,
        RecibeAvisoCobro;
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


    public static final int IDCONTACTOSCLIENTES = AttributesEnum.IdContactosClientes.index();
    public static final int IDCLIENTE = AttributesEnum.IdCliente.index();
    public static final int IDCONTACTO = AttributesEnum.IdContacto.index();
    public static final int RECIBEAVISOCOBRO = AttributesEnum.RecibeAvisoCobro.index();

    /**
     * This is the default constructor (do not remove).
     */
    public ConsultaContactosClienteVORowImpl() {
    }
}

