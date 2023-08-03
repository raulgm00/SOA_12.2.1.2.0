xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/OperacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Operacion/V1/Schema/OperacionMO.xsd" ::)
declare namespace ns2="http://www.bcie.org/ValidarOperacionProcesoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/ValidarOperacionProceso/V1/Schema/ValidarOperacionProcesoMO.xsd" ::)

declare namespace val = "http://www.bcie.org/ValidarOperacionProcesoBO";

declare variable $inValidar as element() (:: schema-element(ns1:ConsultarOperacionByIdOperacionRequest) ::) external;

declare function local:func($inValidar as element() (:: schema-element(ns1:ConsultarOperacionByIdOperacionRequest) ::)) as element() (:: schema-element(ns2:requestValidarOperacionProceso) ::) {
    <ns2:requestValidarOperacionProceso>
        <ns2:ValidarProcesoOperacionInput>
            <val:idOperacion>{fn:data($inValidar/ns1:idOperacion)}</val:idOperacion>
            <val:idProceso>7</val:idProceso></ns2:ValidarProcesoOperacionInput></ns2:requestValidarOperacionProceso>
};

local:func($inValidar)