xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/CrearBitacoraProcesoMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/Utilidades/BitacoraProceso/V1/Schema/BitacoraProcesoMO.xsd" ::)
declare namespace ns1="http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/Comun/Header/V1/HeaderOPN.xsd" ::)


declare namespace cre = "http://www.bcie.org/CrearBitacoraProcesoBO";

declare namespace ns4 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";

declare namespace ns5 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1";

declare namespace ns6 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Proceso/V1";

declare variable $InicioReasignar as element() (:: element(*, ns1:Header) ::) external;

declare function local:func($InicioReasignar as element() (:: element(*, ns1:Header) ::)) as element() (:: schema-element(ns2:requestCrearBitacoraProcesoMessage) ::) {
    <ns2:requestCrearBitacoraProcesoMessage>
        <ns2:BitacoraInput>
            <cre:idOperacion>{fn:data($InicioReasignar/ns1:Operacion/ns4:CodigoOperacion)}</cre:idOperacion>
            <cre:nombreOperacion>{fn:data($InicioReasignar/ns1:Operacion/ns4:NombreOperacion)}</cre:nombreOperacion>
            <cre:idProceso>21</cre:idProceso>
            <cre:nombreProceso>PU01</cre:nombreProceso>
            <cre:esProceso>{true()}</cre:esProceso>
            <cre:usuario>{fn:data($InicioReasignar/ns1:Operacion/ns4:ResponsableOperacion)}</cre:usuario>
            <cre:esFinActividad>{false()}</cre:esFinActividad>
            <cre:fechaRegistro>{fn:current-dateTime()}</cre:fechaRegistro>
           
            </ns2:BitacoraInput>
    </ns2:requestCrearBitacoraProcesoMessage>
};

local:func($InicioReasignar)