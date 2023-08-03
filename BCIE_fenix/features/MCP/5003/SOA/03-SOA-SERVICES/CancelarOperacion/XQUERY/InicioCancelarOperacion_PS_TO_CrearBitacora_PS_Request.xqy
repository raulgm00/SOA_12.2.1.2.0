xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/CrearBitacoraProcesoMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/Utilidades/BitacoraProceso/V1/Schema/BitacoraProcesoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/bpmn/bpmnProcess/CancelarOperacionProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PA05/CancelarOperacionProcess.xsd" ::)

declare namespace cre = "http://www.bcie.org/CrearBitacoraProcesoBO";

declare namespace ns4 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";

declare namespace ns5 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Proceso/V1";

declare variable $inCancelarOperacion as element() (:: schema-element(ns1:InicioCancelarOperacion) ::) external;

declare function local:func($inCancelarOperacion as element() (:: schema-element(ns1:InicioCancelarOperacion) ::)) as element() (:: schema-element(ns2:requestCrearBitacoraProcesoMessage) ::) {
    <ns2:requestCrearBitacoraProcesoMessage>
        <ns2:BitacoraInput>
            <cre:id></cre:id>
            <cre:idOperacion>{fn:data($inCancelarOperacion/ns1:Header/ns3:Operacion/ns4:CodigoOperacion)}</cre:idOperacion>
            <cre:nombreOperacion>{fn:data($inCancelarOperacion/ns1:Header/ns3:Operacion/ns4:NombreOperacion)}</cre:nombreOperacion>
            <cre:idProceso>11</cre:idProceso>
            <cre:nombreProceso>PA05</cre:nombreProceso>
            <cre:instanciaProceso>{fn:data($inCancelarOperacion/ns1:Header/ns3:Proceso/ns5:InstanciaProceso)}</cre:instanciaProceso>
            <cre:esProceso>{true()}</cre:esProceso>
            <cre:usuario>{fn:data($inCancelarOperacion/ns1:Header/ns3:Operacion/ns4:ResponsableOperacion)}</cre:usuario>
            <cre:nombreUsuario></cre:nombreUsuario>
            <cre:esFinActividad>{false()}</cre:esFinActividad>
            <cre:fechaRegistro>{fn:current-dateTime()}</cre:fechaRegistro>
        </ns2:BitacoraInput>
    </ns2:requestCrearBitacoraProcesoMessage>
};

local:func($inCancelarOperacion)