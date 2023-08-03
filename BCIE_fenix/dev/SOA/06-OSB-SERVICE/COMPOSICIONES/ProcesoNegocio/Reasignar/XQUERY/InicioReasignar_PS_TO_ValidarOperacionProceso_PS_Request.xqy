xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ValidarOperacionProcesoMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/Utilidades/ValidarOperacionProceso/V1/Schema/ValidarOperacionProcesoMO.xsd" ::)
declare namespace ns1="http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/Comun/Header/V1/HeaderOPN.xsd" ::)
declare namespace val = "http://www.bcie.org/ValidarOperacionProcesoBO";

declare namespace ns4 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";

declare variable $InicioReasignar as element() (:: element(*, ns1:Header) ::) external;

declare function local:func($InicioReasignar as element() (:: element(*, ns1:Header) ::)) as element() (:: schema-element(ns2:requestValidarOperacionProceso) ::) {
    <ns2:requestValidarOperacionProceso>
        <ns2:ValidarProcesoOperacionInput>
            <val:idOperacion>{fn:data($InicioReasignar/ns1:Operacion/ns4:CodigoOperacion)}</val:idOperacion>
            <val:idProceso>21</val:idProceso></ns2:ValidarProcesoOperacionInput>
    </ns2:requestValidarOperacionProceso>
};

local:func($InicioReasignar)
