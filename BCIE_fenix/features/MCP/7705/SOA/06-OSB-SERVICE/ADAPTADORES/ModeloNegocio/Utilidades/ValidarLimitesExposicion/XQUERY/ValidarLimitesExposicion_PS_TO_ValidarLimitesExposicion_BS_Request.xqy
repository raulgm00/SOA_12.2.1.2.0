xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://org/bcie/ws/middle/LIMITES.wsdl";
(:: import schema at "../../../../Proveedores/LimitesExposicion/XSD/XMLSchema_Limites.xsd" ::)
declare namespace ns1="http://www.bcie.org/ValidarLimitesExposicionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/ValidarLimitesExposicion/V1/Schema/ValidarLimitesExposicionMO.xsd" ::)

declare variable $requestValidarLimitesExposicionMessage as element() (:: schema-element(ns1:requestValidarLimitesExposicionMessage) ::) external;

declare function local:func($requestValidarLimitesExposicionMessage as element() (:: schema-element(ns1:requestValidarLimitesExposicionMessage) ::)) as element() (:: schema-element(ns2:ValidarLimitesRequest) ::) {
    <ns2:ValidarLimitesRequest>
        <ns2:pais>{fn:data($requestValidarLimitesExposicionMessage/ns1:pais)}</ns2:pais>
        <ns2:codigoCliente>{fn:data($requestValidarLimitesExposicionMessage/ns1:idCliente)}</ns2:codigoCliente>
        <ns2:grupoEmpresarial>{fn:data($requestValidarLimitesExposicionMessage/ns1:grupoEmpresarial)}</ns2:grupoEmpresarial>
        <ns2:moneda>{fn:data($requestValidarLimitesExposicionMessage/ns1:moneda)}</ns2:moneda>
        <ns2:monto>{fn:data($requestValidarLimitesExposicionMessage/ns1:monto)}</ns2:monto>
        <ns2:sectorInstitucional>{fn:data($requestValidarLimitesExposicionMessage/ns1:sectorInstitucional)}</ns2:sectorInstitucional>
        <ns2:tipoGarantia>{fn:data($requestValidarLimitesExposicionMessage/ns1:tipoGarantia)}</ns2:tipoGarantia>
        <ns2:destino>{fn:data($requestValidarLimitesExposicionMessage/ns1:destino)}</ns2:destino>
        <ns2:revolvente>{fn:data($requestValidarLimitesExposicionMessage/ns1:revolvente)}</ns2:revolvente>
    </ns2:ValidarLimitesRequest>
};

local:func($requestValidarLimitesExposicionMessage)
