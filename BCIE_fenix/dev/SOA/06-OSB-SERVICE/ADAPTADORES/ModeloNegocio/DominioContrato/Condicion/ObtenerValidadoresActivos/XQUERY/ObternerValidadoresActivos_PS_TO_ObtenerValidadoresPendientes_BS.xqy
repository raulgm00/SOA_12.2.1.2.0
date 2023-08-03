xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ObtenerValidadoresActivos";
(:: import schema at "../XSD/ObtenerValidadoresActivos.xsd" ::)

declare variable $ObtenerValidadoresPendientes as element() (:: schema-element(ns1:ObtenerValidadoresActivosRequest) ::) external;

declare function local:func($ObtenerValidadoresPendientes as element() (:: schema-element(ns1:ObtenerValidadoresActivosRequest) ::)) as element() (:: schema-element(ns2:ObtenerValidadoresActivosInput) ::) {
    <ns2:ObtenerValidadoresActivosInput>
        <ns2:idAgrupador>{fn:data($ObtenerValidadoresPendientes/ns1:idAgrupador)}</ns2:idAgrupador>
        <ns2:idOperacion>{fn:data($ObtenerValidadoresPendientes/ns1:idOperacion)}</ns2:idOperacion>
    </ns2:ObtenerValidadoresActivosInput>
};

local:func($ObtenerValidadoresPendientes)