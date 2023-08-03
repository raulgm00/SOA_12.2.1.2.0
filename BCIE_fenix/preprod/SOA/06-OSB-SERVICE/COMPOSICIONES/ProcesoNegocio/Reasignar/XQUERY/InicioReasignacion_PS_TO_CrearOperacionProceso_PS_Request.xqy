xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/CrearOperacionProcesoMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/Utilidades/CrearOperacionProceso/V1/Schema/CrearOperacionProcesoMO.xsd" ::)
declare namespace ns1="http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/Comun/Header/V1/HeaderOPN.xsd" ::)

declare namespace cre = "http://www.bcie.org/CrearOperacionProcesoBO";

declare namespace ns4 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";
declare variable $InicioReasignar as element() (:: element(*, ns1:Header) ::) external;

declare function local:func($InicioReasignar as element() (:: element(*, ns1:Header) ::)) as element() (:: schema-element(ns2:requestCrearOperacionProcesoMessage) ::) {
    <ns2:requestCrearOperacionProcesoMessage>
        <ns2:OperacionProcesoInput>
            <cre:idOperacion>{fn:data($InicioReasignar/ns1:Operacion/ns4:CodigoOperacion)}</cre:idOperacion>
            <cre:idProceso>21</cre:idProceso></ns2:OperacionProcesoInput>
    </ns2:requestCrearOperacionProcesoMessage>
};

local:func($InicioReasignar)
