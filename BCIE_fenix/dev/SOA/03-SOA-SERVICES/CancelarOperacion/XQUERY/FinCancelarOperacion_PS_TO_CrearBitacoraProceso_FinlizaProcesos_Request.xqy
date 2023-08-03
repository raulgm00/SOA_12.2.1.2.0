xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CrearBitacoraProcesoMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/Utilidades/BitacoraProceso/V1/Schema/BitacoraProcesoMO.xsd" ::)

declare namespace cre = "http://www.bcie.org/CrearBitacoraProcesoBO";

declare variable $consultarBitacoraProcesoResponse as element() (:: schema-element(ns1:consultarBitacoraProcesoResponse) ::) external;

declare function local:func($consultarBitacoraProcesoResponse as element() (:: schema-element(ns1:consultarBitacoraProcesoResponse) ::)) as element() (:: schema-element(ns1:requestCrearBitacoraProcesoMessage) ::) {
    <ns1:requestCrearBitacoraProcesoMessage>
        <ns1:BitacoraInput>
            <cre:id></cre:id>
            <cre:idOperacion>{fn:data($consultarBitacoraProcesoResponse/ns1:Bitacora/cre:idOperacion)}</cre:idOperacion>
            <cre:nombreOperacion>{fn:data($consultarBitacoraProcesoResponse/ns1:Bitacora/cre:nombreOperacion)}</cre:nombreOperacion>
            <cre:idProceso>{fn:data($consultarBitacoraProcesoResponse/ns1:Bitacora/cre:idProceso)}</cre:idProceso>
            <cre:nombreProceso>{fn:data($consultarBitacoraProcesoResponse/ns1:Bitacora/cre:nombreProceso)}</cre:nombreProceso>
            <cre:instanciaProceso>{fn:data($consultarBitacoraProcesoResponse/ns1:Bitacora/cre:instanciaTarea)}</cre:instanciaProceso>
            <cre:idProcesoSiguiente></cre:idProcesoSiguiente>
            <cre:esProceso>{true()}</cre:esProceso>
            <cre:idTarea>{fn:data($consultarBitacoraProcesoResponse/ns1:Bitacora/cre:idTarea)}</cre:idTarea>
            <cre:nombreTarea>{fn:data($consultarBitacoraProcesoResponse/ns1:Bitacora/cre:nombreTarea)}</cre:nombreTarea>
            <cre:instanciaTarea></cre:instanciaTarea>
            <cre:usuario>{fn:data($consultarBitacoraProcesoResponse/ns1:Bitacora/cre:usuario)}</cre:usuario>
            <cre:nombreUsuario>{fn:data($consultarBitacoraProcesoResponse/ns1:Bitacora/cre:nombreUsuario)}</cre:nombreUsuario>
            <cre:esFinActividad>{true()}</cre:esFinActividad>
            <cre:fechaRegistro>{fn:current-dateTime()}</cre:fechaRegistro>
            <cre:tiempoProceso></cre:tiempoProceso>
            <cre:tiempoTarea></cre:tiempoTarea>
            <cre:raroc></cre:raroc>
            <cre:finalizado>{true()}</cre:finalizado>
            <cre:string01></cre:string01>
            <cre:string02></cre:string02>
            <cre:string03></cre:string03>
        </ns1:BitacoraInput>
    </ns1:requestCrearBitacoraProcesoMessage>
};

local:func($consultarBitacoraProcesoResponse)
