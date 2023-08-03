xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DeclaracionJuradaMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/DeclaracionJurada/V1/Schema/DeclaracionJuradaMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/top/CrearDeclaracionJurada";
(:: import schema at "../XSD/CrearDeclaracionJurada_table.xsd" ::)

declare namespace dec = "http://www.bcie.org/DeclaracionJuradaBO";

declare variable $CrearDeclaracionJuradaRequest as element() (:: schema-element(ns1:CrearDeclaracionJuradaRequest) ::) external;

declare function local:func($CrearDeclaracionJuradaRequest as element() (:: schema-element(ns1:CrearDeclaracionJuradaRequest) ::)) as element() (:: schema-element(ns2:DeclaracionCollection) ::) {
    <ns2:DeclaracionCollection>
        <ns2:Declaracion>
           <ns2:estadoDeclaracion>{fn:data($CrearDeclaracionJuradaRequest/ns1:DeclaracionJurada/dec:estadoDeclaracion/dec:codigoEstadoDeclaracion)}</ns2:estadoDeclaracion>
            <ns2:descripcionDeclaracion>{fn:data($CrearDeclaracionJuradaRequest/ns1:DeclaracionJurada/dec:estadoDeclaracion/dec:nombreEstadoDeclaracion)}</ns2:descripcionDeclaracion>
            <ns2:estadoBusquedaContrapartes>{fn:data($CrearDeclaracionJuradaRequest/ns1:DeclaracionJurada/dec:estadoBusqueda/dec:codigoEstadoBusqueda)}</ns2:estadoBusquedaContrapartes>
            <ns2:descripcionBusqueda>{fn:data($CrearDeclaracionJuradaRequest/ns1:DeclaracionJurada/dec:estadoBusqueda/dec:nombreEstadoBusqueda)}</ns2:descripcionBusqueda>
            <ns2:nivelRiesgo>{fn:data($CrearDeclaracionJuradaRequest/ns1:DeclaracionJurada/dec:calificacionDeRiesgo/dec:codigoCalificacionDeRiesgo)}</ns2:nivelRiesgo>
            <ns2:descripcionRiesgo>{fn:data($CrearDeclaracionJuradaRequest/ns1:DeclaracionJurada/dec:calificacionDeRiesgo/dec:nombreCalificacionDeRiesgo)}</ns2:descripcionRiesgo>
            <ns2:fechaRegistro>{fn:current-dateTime()}</ns2:fechaRegistro>
            {
                if ($CrearDeclaracionJuradaRequest/ns1:DeclaracionJurada/dec:fechaFirmaDeclaracion)
                then <ns2:fechaFirmaDeclaracion>{fn:data($CrearDeclaracionJuradaRequest/ns1:DeclaracionJurada/dec:fechaFirmaDeclaracion)}</ns2:fechaFirmaDeclaracion>
                else ()
            }
            {
                if ($CrearDeclaracionJuradaRequest/ns1:DeclaracionJurada/dec:fechaVencimiento)
                then <ns2:fechaVencimiento>{fn:data($CrearDeclaracionJuradaRequest/ns1:DeclaracionJurada/dec:fechaVencimiento)}</ns2:fechaVencimiento>
                else ()
            }
            {
                if ($CrearDeclaracionJuradaRequest/ns1:DeclaracionJurada/dec:comentarioDeclaracion)
                then <ns2:comentarioDeclaracion>{fn:data($CrearDeclaracionJuradaRequest/ns1:DeclaracionJurada/dec:comentarioDeclaracion)}</ns2:comentarioDeclaracion>
                else ()
            }
            {
                if ($CrearDeclaracionJuradaRequest/ns1:DeclaracionJurada/dec:comentarioBusqueda)
                then <ns2:comentarioBusqueda>{fn:data($CrearDeclaracionJuradaRequest/ns1:DeclaracionJurada/dec:comentarioBusqueda)}</ns2:comentarioBusqueda>
                else ()
            }
           {
                if ($CrearDeclaracionJuradaRequest/ns1:DeclaracionJurada/dec:codigoExternoDeclaracion)
                then <ns2:idDeclaracion>{fn:data($CrearDeclaracionJuradaRequest/ns1:DeclaracionJurada/dec:codigoExternoDeclaracion)}</ns2:idDeclaracion>
                else ()
            }
            
                <ns2:seElevaOtraInstancia>{
                if (string($CrearDeclaracionJuradaRequest/ns1:DeclaracionJurada/dec:elevarAOtraInstancia)='')
                then 0
                else (if(fn:data($CrearDeclaracionJuradaRequest/ns1:DeclaracionJurada/dec:elevarAOtraInstancia)=true())
                then 1
                else 0)
            }</ns2:seElevaOtraInstancia>
     
            {
                if ($CrearDeclaracionJuradaRequest/ns1:DeclaracionJurada/dec:tipoOrigen)
                then <ns2:tipoOrigen>{fn:data($CrearDeclaracionJuradaRequest/ns1:DeclaracionJurada/dec:tipoOrigen)}</ns2:tipoOrigen>
                else ()
            }
            {
                if ($CrearDeclaracionJuradaRequest/ns1:DeclaracionJurada/dec:tipoSeguimiento)
                then <ns2:tipoSeguimiento>{fn:data($CrearDeclaracionJuradaRequest/ns1:DeclaracionJurada/dec:tipoSeguimiento)}</ns2:tipoSeguimiento>
                else ()
            }
            {
                if ($CrearDeclaracionJuradaRequest/ns1:DeclaracionJurada/dec:instanciaProceso)
                then <ns2:instanciaProceso>{fn:data($CrearDeclaracionJuradaRequest/ns1:DeclaracionJurada/dec:instanciaProceso)}</ns2:instanciaProceso>
                else ()
            }
            
          <ns2:esSoloLectura>{
                if (string($CrearDeclaracionJuradaRequest/ns1:DeclaracionJurada/dec:esSoloLectura)='')
                then 0
                else (if(fn:data($CrearDeclaracionJuradaRequest/ns1:DeclaracionJurada/dec:esSoloLectura)=true())
                then 1
                else 0)
            }</ns2:esSoloLectura>
            <ns2:banEstatus>{
                if (string($CrearDeclaracionJuradaRequest/ns1:DeclaracionJurada/dec:status)='')
                then 0
                else (if(fn:data($CrearDeclaracionJuradaRequest/ns1:DeclaracionJurada/dec:status)=true())
                then 1
                else 0)
            }</ns2:banEstatus>
        </ns2:Declaracion>
    </ns2:DeclaracionCollection>
};

local:func($CrearDeclaracionJuradaRequest)
