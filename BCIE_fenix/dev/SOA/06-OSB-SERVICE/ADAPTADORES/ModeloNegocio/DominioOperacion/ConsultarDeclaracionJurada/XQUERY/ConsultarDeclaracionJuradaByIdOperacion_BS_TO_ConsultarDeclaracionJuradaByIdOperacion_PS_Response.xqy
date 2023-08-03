xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/DeclaracionJuradaMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/DeclaracionJurada/V1/Schema/DeclaracionJuradaMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarDeclaracionByIdOperacion";
(:: import schema at "../XSD/ConsultarDeclaracionJuradaByIdOperacion.xsd" ::)

declare namespace dec = "http://www.bcie.org/DeclaracionJuradaBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";


declare variable $DeclaracionJurada as element() (:: schema-element(ns1:ConsultarDeclaracionByIdOperacionOutputCollection) ::) external;

declare function local:func($DeclaracionJurada as element() (:: schema-element(ns1:ConsultarDeclaracionByIdOperacionOutputCollection) ::)) as element() (:: schema-element(ns2:ConsultarDeclaracionJuradaResponse) ::) {
    <ns2:ConsultarDeclaracionJuradaResponse>{
        for $DeclaracionJurada in $DeclaracionJurada/ns1:ConsultarDeclaracionByIdOperacionOutput
        order by $DeclaracionJurada/ns1:CODIGO_DECLARACION ascending
        return
        <ns2:DeclaracionJurada>            
            <dec:codigoExternoDeclaracion>{fn:data($DeclaracionJurada/ns1:CODIGO_DECLARACION)}</dec:codigoExternoDeclaracion>          
              <dec:idOperacion>{
                     if (not(fn:data($DeclaracionJurada/ns1:CODIGO_REFERENCIA)))
                        then(fn:empty($DeclaracionJurada/ns1:CODIGO_REFERENCIA))
                         else(fn:data($DeclaracionJurada/ns1:CODIGO_REFERENCIA))               
                           }            
             </dec:idOperacion>
            <dec:titulo>{fn:data($DeclaracionJurada/ns1:TITULO_DECLARACION)}</dec:titulo>            
            <dec:estadoDeclaracion>
                <dec:codigoEstadoDeclaracion>{fn:data($DeclaracionJurada/ns1:CODIGO_ESTADO_DECLARACION)}</dec:codigoEstadoDeclaracion>
                <dec:nombreEstadoDeclaracion>{fn:data($DeclaracionJurada/ns1:ESTADO_DECLARACION)}</dec:nombreEstadoDeclaracion>
            </dec:estadoDeclaracion>
            <dec:estadoBusqueda>
                <dec:codigoEstadoBusqueda>{fn:data($DeclaracionJurada/ns1:CODIGO_ESTADO_BUSQUEDA)}</dec:codigoEstadoBusqueda>
                <dec:nombreEstadoBusqueda>{fn:data($DeclaracionJurada/ns1:ESTADO_BUSQUEDA)}</dec:nombreEstadoBusqueda>
            </dec:estadoBusqueda>
            <dec:calificacionDeRiesgo>
                <dec:codigoCalificacionDeRiesgo>{fn:data($DeclaracionJurada/ns1:CODIGO_TIPO_RIESGO)}</dec:codigoCalificacionDeRiesgo>
                <dec:nombreCalificacionDeRiesgo>{fn:data($DeclaracionJurada/ns1:NIVEL_RIESGO)}</dec:nombreCalificacionDeRiesgo>
            </dec:calificacionDeRiesgo>
            <dec:fechaFirmaDeclaracion>{fn:data($DeclaracionJurada/ns1:FECHA_FIRMA)}</dec:fechaFirmaDeclaracion>
            <dec:fechaVencimiento>{fn:data($DeclaracionJurada/ns1:FECHA_VENCIMIENTO)}</dec:fechaVencimiento>
            <dec:comentarioDeclaracion>{fn:data($DeclaracionJurada/ns1:JUSTIFICACION_DECLARACION)}</dec:comentarioDeclaracion>
            <dec:comentarioBusqueda>{fn:data($DeclaracionJurada/ns1:JUSTIFICACION_BUSQUEDA)}</dec:comentarioBusqueda>                            
        </ns2:DeclaracionJurada>
        
        }
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>Consulta realizada exitosamente</res:message>
        </ns2:Resultado>
    </ns2:ConsultarDeclaracionJuradaResponse>
};

local:func($DeclaracionJurada)
