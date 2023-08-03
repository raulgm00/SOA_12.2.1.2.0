xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DeclaracionJuradaMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/DeclaracionJurada/V1/Schema/DeclaracionJuradaMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/top/ActualizarDeclaracionJurada";
(:: import schema at "../XSD/ActualizarDeclaracionJurada_table.xsd" ::)

declare namespace dec = "http://www.bcie.org/DeclaracionJuradaBO";

declare variable $ActualizarDeclaracionJuradaRequest as element() (:: schema-element(ns1:ActualizarDeclaracionJuradaRequest) ::) external;

declare function local:func($ActualizarDeclaracionJuradaRequest as element() (:: schema-element(ns1:ActualizarDeclaracionJuradaRequest) ::)) as element() (:: schema-element(ns2:DeclaracionCollection) ::) {
    <ns2:DeclaracionCollection>
        <ns2:Declaracion>
            <ns2:id>{fn:data($ActualizarDeclaracionJuradaRequest/ns1:DeclaracionJurada/dec:idDeclaracion)}</ns2:id>
            {
                if ($ActualizarDeclaracionJuradaRequest/ns1:DeclaracionJurada/dec:estadoDeclaracion/dec:codigoEstadoDeclaracion)
                then <ns2:estadoDeclaracion>{fn:data($ActualizarDeclaracionJuradaRequest/ns1:DeclaracionJurada/dec:estadoDeclaracion/dec:codigoEstadoDeclaracion)}</ns2:estadoDeclaracion>
                else ()
            }
            {
                if ($ActualizarDeclaracionJuradaRequest/ns1:DeclaracionJurada/dec:estadoDeclaracion/dec:nombreEstadoDeclaracion)
                then <ns2:descripcionDeclaracion>{fn:data($ActualizarDeclaracionJuradaRequest/ns1:DeclaracionJurada/dec:estadoDeclaracion/dec:nombreEstadoDeclaracion)}</ns2:descripcionDeclaracion>
                else ()
            }
            {
                if ($ActualizarDeclaracionJuradaRequest/ns1:DeclaracionJurada/dec:estadoBusqueda/dec:codigoEstadoBusqueda)
                then <ns2:estadoBusquedaContrapartes>{fn:data($ActualizarDeclaracionJuradaRequest/ns1:DeclaracionJurada/dec:estadoBusqueda/dec:codigoEstadoBusqueda)}</ns2:estadoBusquedaContrapartes>
                else ()
            }
            {
                if ($ActualizarDeclaracionJuradaRequest/ns1:DeclaracionJurada/dec:estadoBusqueda/dec:nombreEstadoBusqueda)
                then <ns2:descripcionBusqueda>{fn:data($ActualizarDeclaracionJuradaRequest/ns1:DeclaracionJurada/dec:estadoBusqueda/dec:nombreEstadoBusqueda)}</ns2:descripcionBusqueda>
                else ()
            }
            {
                if ($ActualizarDeclaracionJuradaRequest/ns1:DeclaracionJurada/dec:calificacionDeRiesgo/dec:codigoCalificacionDeRiesgo)
                then <ns2:nivelRiesgo>{fn:data($ActualizarDeclaracionJuradaRequest/ns1:DeclaracionJurada/dec:calificacionDeRiesgo/dec:codigoCalificacionDeRiesgo)}</ns2:nivelRiesgo>
                else ()
            }
            {
                if ($ActualizarDeclaracionJuradaRequest/ns1:DeclaracionJurada/dec:calificacionDeRiesgo/dec:nombreCalificacionDeRiesgo)
                then <ns2:descripcionRiesgo>{fn:data($ActualizarDeclaracionJuradaRequest/ns1:DeclaracionJurada/dec:calificacionDeRiesgo/dec:nombreCalificacionDeRiesgo)}</ns2:descripcionRiesgo>
                else ()
            }
          
          
            {
                if ($ActualizarDeclaracionJuradaRequest/ns1:DeclaracionJurada/dec:fechaFirmaDeclaracion)
                then <ns2:fechaFirmaDeclaracion>{fn:data($ActualizarDeclaracionJuradaRequest/ns1:DeclaracionJurada/dec:fechaFirmaDeclaracion)}</ns2:fechaFirmaDeclaracion>
                else ()
            }
            {
                if ($ActualizarDeclaracionJuradaRequest/ns1:DeclaracionJurada/dec:fechaVencimiento)
                then <ns2:fechaVencimiento>{fn:data($ActualizarDeclaracionJuradaRequest/ns1:DeclaracionJurada/dec:fechaVencimiento)}</ns2:fechaVencimiento>
                else ()
            }
            {
                if ($ActualizarDeclaracionJuradaRequest/ns1:DeclaracionJurada/dec:comentarioDeclaracion)
                then <ns2:comentarioDeclaracion>{fn:data($ActualizarDeclaracionJuradaRequest/ns1:DeclaracionJurada/dec:comentarioDeclaracion)}</ns2:comentarioDeclaracion>
                else ()
            }
            {
                if ($ActualizarDeclaracionJuradaRequest/ns1:DeclaracionJurada/dec:comentarioBusqueda)
                then <ns2:comentarioBusqueda>{fn:data($ActualizarDeclaracionJuradaRequest/ns1:DeclaracionJurada/dec:comentarioBusqueda)}</ns2:comentarioBusqueda>
                else ()
            }
            {
                if ($ActualizarDeclaracionJuradaRequest/ns1:DeclaracionJurada/dec:codigoExternoDeclaracion)
                then <ns2:idDeclaracion>{fn:data($ActualizarDeclaracionJuradaRequest/ns1:DeclaracionJurada/dec:codigoExternoDeclaracion)}</ns2:idDeclaracion>
                else ()
            }
         
            <ns2:seElevaOtraInstancia>{
                if (string($ActualizarDeclaracionJuradaRequest/ns1:DeclaracionJurada/dec:elevarAOtraInstancia)='')
                then 0
                else (if(fn:data($ActualizarDeclaracionJuradaRequest/ns1:DeclaracionJurada/dec:elevarAOtraInstancia)=true())
                then 1
                else 0)
            }</ns2:seElevaOtraInstancia>
            {
                if ($ActualizarDeclaracionJuradaRequest/ns1:DeclaracionJurada/dec:tipoOrigen)
                then <ns2:tipoOrigen>{fn:data($ActualizarDeclaracionJuradaRequest/ns1:DeclaracionJurada/dec:tipoOrigen)}</ns2:tipoOrigen>
                else ()
            }
            {
                if ($ActualizarDeclaracionJuradaRequest/ns1:DeclaracionJurada/dec:tipoSeguimiento)
                then <ns2:tipoSeguimiento>{fn:data($ActualizarDeclaracionJuradaRequest/ns1:DeclaracionJurada/dec:tipoSeguimiento)}</ns2:tipoSeguimiento>
                else ()
            }
            {
                if ($ActualizarDeclaracionJuradaRequest/ns1:DeclaracionJurada/dec:instanciaProceso)
                then <ns2:instanciaProceso>{fn:data($ActualizarDeclaracionJuradaRequest/ns1:DeclaracionJurada/dec:instanciaProceso)}</ns2:instanciaProceso>
                else ()
            }
           <ns2:esSoloLectura>{
                if (string($ActualizarDeclaracionJuradaRequest/ns1:DeclaracionJurada/dec:esSoloLectura)='')
                then 0
                else (if(fn:data($ActualizarDeclaracionJuradaRequest/ns1:DeclaracionJurada/dec:esSoloLectura)=true())
                then 1
                else 0)
            }</ns2:esSoloLectura>
               
            <ns2:banEstatus>{
                if (string($ActualizarDeclaracionJuradaRequest/ns1:DeclaracionJurada/dec:status)='')
                then 0
                else (if(fn:data($ActualizarDeclaracionJuradaRequest/ns1:DeclaracionJurada/dec:status)=true())
                then 1
                else 0)
            }</ns2:banEstatus>
        </ns2:Declaracion>
    </ns2:DeclaracionCollection>
};

local:func($ActualizarDeclaracionJuradaRequest)
