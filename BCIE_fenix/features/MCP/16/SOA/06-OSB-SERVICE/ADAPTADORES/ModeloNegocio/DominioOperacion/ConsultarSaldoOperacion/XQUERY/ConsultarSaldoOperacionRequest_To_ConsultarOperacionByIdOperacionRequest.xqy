xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/OperacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Operacion/V1/Schema/OperacionMO.xsd" ::)

declare variable $ConsultarSaldoOperacionRequest as element() (:: schema-element(ns1:ConsultarSaldoOperacionRequest) ::) external;

declare function local:func($ConsultarSaldoOperacionRequest as element() (:: schema-element(ns1:ConsultarSaldoOperacionRequest) ::)) as element() (:: schema-element(ns1:ConsultarOperacionByIdOperacionRequest) ::) {
    <ns1:ConsultarOperacionByIdOperacionRequest>
        <ns1:idOperacion>{fn:data($ConsultarSaldoOperacionRequest/ns1:idOperacion)}</ns1:idOperacion>
        <ns1:nivelDetalle>OPERACION</ns1:nivelDetalle>
        <ns1:infoDetalleCliente>true()</ns1:infoDetalleCliente>
    </ns1:ConsultarOperacionByIdOperacionRequest>
};

local:func($ConsultarSaldoOperacionRequest)
