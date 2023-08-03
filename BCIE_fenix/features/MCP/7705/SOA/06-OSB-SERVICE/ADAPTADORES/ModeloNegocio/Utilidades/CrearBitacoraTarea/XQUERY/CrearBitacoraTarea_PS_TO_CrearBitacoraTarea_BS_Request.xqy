xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CrearBitacoraTareaMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/CrearBitacoraTarea/V1/Schema/CrearBitacoraTareaMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/top/CrearBitacoraTarea";
(:: import schema at "../XSD/CrearBitacoraTarea_table.xsd" ::)

declare namespace cre = "http://www.bcie.org/CrearBitacoraTareaBO";

declare variable $requestCrearBitacoraTareaMessage as element() (:: schema-element(ns1:requestCrearBitacoraTareaMessage) ::) external;

declare function local:func($requestCrearBitacoraTareaMessage as element() (:: schema-element(ns1:requestCrearBitacoraTareaMessage) ::)) as element() (:: schema-element(ns2:TbiTareaOperacionCollection) ::) {
    <ns2:TbiTareaOperacionCollection>
        <ns2:TbiTareaOperacion>
            <ns2:id></ns2:id>
            <ns2:idOperacion>{fn:data($requestCrearBitacoraTareaMessage/ns1:BitacoraInput/cre:idOperacion)}</ns2:idOperacion>
            <ns2:nombreOperacion>{fn:data($requestCrearBitacoraTareaMessage/ns1:BitacoraInput/cre:nombreOperacion)}</ns2:nombreOperacion>
            <ns2:idProceso>{fn:data($requestCrearBitacoraTareaMessage/ns1:BitacoraInput/cre:idProceso)}</ns2:idProceso>
            <ns2:nombreProceso>{fn:data($requestCrearBitacoraTareaMessage/ns1:BitacoraInput/cre:nombreProceso)}</ns2:nombreProceso>
            <ns2:instanciaProceso>{fn:data($requestCrearBitacoraTareaMessage/ns1:BitacoraInput/cre:instanciaProceso)}</ns2:instanciaProceso>
            <ns2:instanciaProcesoSiguiente>{fn:data($requestCrearBitacoraTareaMessage/ns1:BitacoraInput/cre:idProcesoSiguiente)}</ns2:instanciaProcesoSiguiente>
            <ns2:banEsProceso>{
              if(fn:data($requestCrearBitacoraTareaMessage/ns1:BitacoraInput/cre:esProceso)=true()) then 1
              else 0
            }</ns2:banEsProceso>
            <ns2:idTarea>{fn:data($requestCrearBitacoraTareaMessage/ns1:BitacoraInput/cre:idTarea)}</ns2:idTarea>
            <ns2:nombreTarea>{fn:data($requestCrearBitacoraTareaMessage/ns1:BitacoraInput/cre:nombreTarea)}</ns2:nombreTarea>
            <ns2:instanciaTarea>{fn:data($requestCrearBitacoraTareaMessage/ns1:BitacoraInput/cre:instanciaTarea)}</ns2:instanciaTarea>
            <ns2:usuario>{fn:data($requestCrearBitacoraTareaMessage/ns1:BitacoraInput/cre:usuario)}</ns2:usuario>
            <ns2:nombreUsuario>{fn:data($requestCrearBitacoraTareaMessage/ns1:BitacoraInput/cre:nombreUsuario)}</ns2:nombreUsuario>
            <ns2:banEsFinActividad>{
              if(fn:data($requestCrearBitacoraTareaMessage/ns1:BitacoraInput/cre:esFinActividad)=true()) then 1
              else 0
            }</ns2:banEsFinActividad>
            <ns2:fechaRegistro>{fn:data($requestCrearBitacoraTareaMessage/ns1:BitacoraInput/cre:fechaRegistro)}</ns2:fechaRegistro>
            <ns2:tiempoProceso>{fn:data($requestCrearBitacoraTareaMessage/ns1:BitacoraInput/cre:tiempoProceso)}</ns2:tiempoProceso>
            <ns2:tiempoTarea>{fn:data($requestCrearBitacoraTareaMessage/ns1:BitacoraInput/cre:tiempoTarea)}</ns2:tiempoTarea>
            <ns2:string01>{fn:data($requestCrearBitacoraTareaMessage/ns1:BitacoraInput/cre:string01)}</ns2:string01>
            <ns2:string02>{fn:data($requestCrearBitacoraTareaMessage/ns1:BitacoraInput/cre:string02)}</ns2:string02>
            <ns2:string03>{fn:data($requestCrearBitacoraTareaMessage/ns1:BitacoraInput/cre:string03)}</ns2:string03>
            <ns2:raroc>{fn:data($requestCrearBitacoraTareaMessage/ns1:BitacoraInput/cre:raroc)}</ns2:raroc>
            <ns2:banFinalizado>{
              if(fn:data($requestCrearBitacoraTareaMessage/ns1:BitacoraInput/cre:finalizado)=true()) then 1
              else 0
            }</ns2:banFinalizado>
        </ns2:TbiTareaOperacion>
    </ns2:TbiTareaOperacionCollection>
};

local:func($requestCrearBitacoraTareaMessage)
