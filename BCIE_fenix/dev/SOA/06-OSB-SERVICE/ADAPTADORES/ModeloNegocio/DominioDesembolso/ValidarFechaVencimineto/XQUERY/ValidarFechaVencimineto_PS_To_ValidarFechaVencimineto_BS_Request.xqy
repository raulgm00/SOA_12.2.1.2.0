xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ValidarFechaVencimiento_DB";
(:: import schema at "../XSD/ValidarFechaVencimiento_DB_sp.xsd" ::)

declare variable $ValidarFechaVencimientoRequest as element() (:: schema-element(ns1:ValidarFechaVencimientoRequest) ::) external;

declare function ns1:func($ValidarFechaVencimientoRequest as element() (:: schema-element(ns1:ValidarFechaVencimientoRequest) ::)) as element() (:: schema-element(ns2:InputParameters) ::) {
    <ns2:InputParameters>
        <ns2:P_LINEA>{fn:data($ValidarFechaVencimientoRequest/ns1:lineaVencimiento)}</ns2:P_LINEA>
		<ns2:P_FECHA>{fn:data($ValidarFechaVencimientoRequest/ns1:fechaVencimiento)}</ns2:P_FECHA>
    </ns2:InputParameters>
};

ns1:func($ValidarFechaVencimientoRequest)