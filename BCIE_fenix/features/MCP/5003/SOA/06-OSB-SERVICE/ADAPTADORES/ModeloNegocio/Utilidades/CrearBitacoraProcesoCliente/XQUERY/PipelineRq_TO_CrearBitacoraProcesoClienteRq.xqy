xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CrearBitacoraProcesoClienteMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/CrearBitacoraProcesoCliente/V1/Schema/CrearBitacoraProcesoClienteMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/top/CrearBitacoraProcesoCliente_DB";
(:: import schema at "../XSD/CrearBitacoraProcesoCliente_DB_table.xsd" ::)

declare namespace cre = "http://www.bcie.org/CrearBitacoraProcesoClienteBO";

declare variable $requestCrearBitacoraProcesoClienteMessage as element() (:: schema-element(ns1:requestCrearBitacoraProcesoClienteMessage) ::) external;
declare variable $usuario as xs:string external;
declare function local:func($requestCrearBitacoraProcesoClienteMessage as element() (:: schema-element(ns1:requestCrearBitacoraProcesoClienteMessage) ::),$usuario as xs:string) as element() (:: schema-element(ns2:TbiProcesoClienteCollection) ::) {
    <ns2:TbiProcesoClienteCollection>
        <ns2:TbiProcesoCliente>
            <ns2:id> </ns2:id>
            <ns2:idCliente>{fn:data($requestCrearBitacoraProcesoClienteMessage/ns1:BitacoraInput/cre:idCliente)}</ns2:idCliente>
            <ns2:razonSocial>{fn:data($requestCrearBitacoraProcesoClienteMessage/ns1:BitacoraInput/cre:razonSocial)}</ns2:razonSocial>
            <ns2:idProceso>{fn:data($requestCrearBitacoraProcesoClienteMessage/ns1:BitacoraInput/cre:idProceso)}</ns2:idProceso>
            <ns2:nombreProceso>{fn:data($requestCrearBitacoraProcesoClienteMessage/ns1:BitacoraInput/cre:nombreProceso)}</ns2:nombreProceso>
            <ns2:instanciaProceso>{fn:data($requestCrearBitacoraProcesoClienteMessage/ns1:BitacoraInput/cre:instanciaProceso)}</ns2:instanciaProceso>
            <ns2:instanciaProcesoSiguiente>{fn:data($requestCrearBitacoraProcesoClienteMessage/ns1:BitacoraInput/cre:idProcesoSiguiente)}</ns2:instanciaProcesoSiguiente>
            <ns2:banEsProceso>{
            if (fn:data($requestCrearBitacoraProcesoClienteMessage/ns1:BitacoraInput/cre:esProceso) = true())
            then(1)
            else(0)}</ns2:banEsProceso>
            <ns2:idTarea>{fn:data($requestCrearBitacoraProcesoClienteMessage/ns1:BitacoraInput/cre:idTarea)}</ns2:idTarea>
            <ns2:nombreTarea>{fn:data($requestCrearBitacoraProcesoClienteMessage/ns1:BitacoraInput/cre:nombreTarea)}</ns2:nombreTarea>
            <ns2:instanciaTarea>{fn:data($requestCrearBitacoraProcesoClienteMessage/ns1:BitacoraInput/cre:instanciaTarea)}</ns2:instanciaTarea>
            <ns2:usuario>{fn:data($requestCrearBitacoraProcesoClienteMessage/ns1:BitacoraInput/cre:usuario)}</ns2:usuario>
            <ns2:nombreUsuario>{fn:data($usuario)}</ns2:nombreUsuario>
            <ns2:banEsFinActividad>{
            if (fn:data($requestCrearBitacoraProcesoClienteMessage/ns1:BitacoraInput/cre:esFinActividad) = true())
            then(1)
            else(0)
            }</ns2:banEsFinActividad>
            <ns2:fechaRegistro>{fn:current-dateTime()}</ns2:fechaRegistro>
            <ns2:tiempoProceso>{fn:data($requestCrearBitacoraProcesoClienteMessage/ns1:BitacoraInput/cre:tiempoProceso)}</ns2:tiempoProceso>
            <ns2:tiempoTarea>{fn:data($requestCrearBitacoraProcesoClienteMessage/ns1:BitacoraInput/cre:tiempoTarea)}</ns2:tiempoTarea>
            <ns2:scr>{fn:data($requestCrearBitacoraProcesoClienteMessage/ns1:BitacoraInput/cre:scr)}</ns2:scr>
            <ns2:string01>{fn:data($requestCrearBitacoraProcesoClienteMessage/ns1:BitacoraInput/cre:string01)}</ns2:string01>
            <ns2:string02>{fn:data($requestCrearBitacoraProcesoClienteMessage/ns1:BitacoraInput/cre:string02)}</ns2:string02>
            <ns2:string03>{fn:data($requestCrearBitacoraProcesoClienteMessage/ns1:BitacoraInput/cre:string03)}</ns2:string03>
            <ns2:banFinalizado>{
            if (fn:data($requestCrearBitacoraProcesoClienteMessage/ns1:BitacoraInput/cre:finalizado) = true())
            then(1)
            else(0)
            }</ns2:banFinalizado>
            <ns2:idInicio>{fn:data($requestCrearBitacoraProcesoClienteMessage/ns1:BitacoraInput/cre:idInicio)}</ns2:idInicio>
        </ns2:TbiProcesoCliente>
    </ns2:TbiProcesoClienteCollection>
};

local:func($requestCrearBitacoraProcesoClienteMessage , $usuario)
