xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/GestionCobroMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioCliente/GestionCobro/V1/Schema/GestionCobroMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/GenerarLoteReciboPago";
(:: import schema at "../XSD/GenerarLoteReciboPago_sp.xsd" ::)

declare variable $GenerarLoteReciboPagoRequest as element() (:: schema-element(ns1:GenerarLoteReciboPagoRequest) ::) external;

declare function local:func($GenerarLoteReciboPagoRequest as element() (:: schema-element(ns1:GenerarLoteReciboPagoRequest) ::)) as element() (:: schema-element(ns2:InputParameters) ::) {
    <ns2:InputParameters>
        <ns2:P_FECHA_INICIO>{fn:data($GenerarLoteReciboPagoRequest/ns1:fechaInicio)}</ns2:P_FECHA_INICIO>
        <ns2:P_FECHA_FIN>{fn:data($GenerarLoteReciboPagoRequest/ns1:fechaFin)}</ns2:P_FECHA_FIN>
    </ns2:InputParameters>
};

local:func($GenerarLoteReciboPagoRequest)
