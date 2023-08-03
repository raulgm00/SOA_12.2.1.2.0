xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CrearBitacoraMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/CrearBitacora/V1/Schema/CrearBitacoraMO.xsd" ::)
declare namespace ns2="http://www.bcie.org/CrearBitacoraProcesoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/BitacoraProceso/V1/Schema/BitacoraProcesoMO.xsd" ::)

declare namespace cre = "http://www.bcie.org/CrearBitacoraProcesoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace cre1 = "http://www.bcie.org/CrearBitacoraBO";

declare variable $CrearBitacoraRequest as element() (:: schema-element(ns1:CrearBitacoraRequest) ::) external;

declare function local:func($CrearBitacoraRequest as element() (:: schema-element(ns1:CrearBitacoraRequest) ::)) as element() (:: schema-element(ns2:requestCrearBitacoraProcesoMessage) ::) {
    <ns2:requestCrearBitacoraProcesoMessage>
        <ns2:BitacoraInput>
            <cre:idOperacion>{fn:data($CrearBitacoraRequest/ns1:BitacoraInput/cre1:idNegocio/cat:Id)}</cre:idOperacion>
            <cre:nombreOperacion>{fn:data($CrearBitacoraRequest/ns1:BitacoraInput/cre1:idNegocio/cat:Descripcion)}</cre:nombreOperacion>
            <cre:idProceso>{fn:data($CrearBitacoraRequest/ns1:BitacoraInput/cre1:idProceso)}</cre:idProceso>
            <cre:nombreProceso>{fn:data($CrearBitacoraRequest/ns1:BitacoraInput/cre1:nombreProceso)}</cre:nombreProceso>
            <cre:instanciaProceso>{fn:data($CrearBitacoraRequest/ns1:BitacoraInput/cre1:instanciaProceso)}</cre:instanciaProceso>
            <cre:idProcesoSiguiente>{fn:data($CrearBitacoraRequest/ns1:BitacoraInput/cre1:idProcesoSiguiente)}</cre:idProcesoSiguiente>
            <cre:esProceso>{fn:data($CrearBitacoraRequest/ns1:BitacoraInput/cre1:esProceso)}</cre:esProceso>
            <cre:idTarea>{fn:data($CrearBitacoraRequest/ns1:BitacoraInput/cre1:idTarea)}</cre:idTarea>
            <cre:nombreTarea>{fn:data($CrearBitacoraRequest/ns1:BitacoraInput/cre1:nombreTarea)}</cre:nombreTarea>
            <cre:instanciaTarea>{fn:data($CrearBitacoraRequest/ns1:BitacoraInput/cre1:instanciaTarea)}</cre:instanciaTarea>
            <cre:usuario>{fn:data($CrearBitacoraRequest/ns1:BitacoraInput/cre1:usuario)}</cre:usuario>
            <cre:nombreUsuario>{fn:data($CrearBitacoraRequest/ns1:BitacoraInput/cre1:nombreUsuario)}</cre:nombreUsuario>
            <cre:esFinActividad>{fn:data($CrearBitacoraRequest/ns1:BitacoraInput/cre1:esFinActividad)}</cre:esFinActividad>
            <cre:fechaRegistro>{fn:data($CrearBitacoraRequest/ns1:BitacoraInput/cre1:fechaRegistro)}</cre:fechaRegistro>
            <cre:tiempoProceso>{fn:data($CrearBitacoraRequest/ns1:BitacoraInput/cre1:tiempoProceso)}</cre:tiempoProceso>
            <cre:tiempoTarea>{fn:data($CrearBitacoraRequest/ns1:BitacoraInput/cre1:tiempoTarea)}</cre:tiempoTarea>
            <cre:raroc>{fn:data($CrearBitacoraRequest/ns1:BitacoraInput/cre1:raroc)}</cre:raroc>
            <cre:finalizado>{fn:data($CrearBitacoraRequest/ns1:BitacoraInput/cre1:finalizado)}</cre:finalizado>
            <cre:string01>{fn:data($CrearBitacoraRequest/ns1:BitacoraInput/cre1:string01)}</cre:string01>
            <cre:string02>{fn:data($CrearBitacoraRequest/ns1:BitacoraInput/cre1:string02)}</cre:string02>
            <cre:string03>{fn:data($CrearBitacoraRequest/ns1:BitacoraInput/cre1:string03)}</cre:string03>
        </ns2:BitacoraInput>
    </ns2:requestCrearBitacoraProcesoMessage>
};

local:func($CrearBitacoraRequest)
