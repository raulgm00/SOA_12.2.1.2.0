xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)
declare namespace ns2="http://www.bcie.org/OperacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Operacion/V1/Schema/OperacionMO.xsd" ::)

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare variable $ConsultarLineaCreditoByIdLineaCreditoResponse as element() (:: schema-element(ns1:ConsultarLineaCreditoByIdLineaCreditoResponse) ::) external;

declare function local:func($ConsultarLineaCreditoByIdLineaCreditoResponse as element() (:: schema-element(ns1:ConsultarLineaCreditoByIdLineaCreditoResponse) ::)) as element() (:: schema-element(ns2:ConsultarOperacionByIdOperacionRequest) ::) {
    <ns2:ConsultarOperacionByIdOperacionRequest>
        <ns2:idOperacion>{fn:data($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion/ope:idOperacion)}</ns2:idOperacion>
        <ns2:nivelDetalle>OPERACION</ns2:nivelDetalle>
        <ns2:infoDetalleCliente>{false()}</ns2:infoDetalleCliente>
    </ns2:ConsultarOperacionByIdOperacionRequest>
};

local:func($ConsultarLineaCreditoByIdLineaCreditoResponse)
