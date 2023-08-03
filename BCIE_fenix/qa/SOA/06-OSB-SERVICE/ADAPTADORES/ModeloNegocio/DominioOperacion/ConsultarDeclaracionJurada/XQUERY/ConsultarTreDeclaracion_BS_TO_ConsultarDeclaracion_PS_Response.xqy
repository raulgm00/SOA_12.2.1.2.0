xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/DeclaracionJuradaMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/DeclaracionJurada/V1/Schema/DeclaracionJuradaMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarTreDeclaracionOperacion_DB";
(:: import schema at "../XSD/ConsultarTreDeclaracionOperacion_DB.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace dec = "http://www.bcie.org/DeclaracionJuradaBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ConsultarTreDeclaracionOperacion_Output as element() (:: schema-element(ns1:ConsultarTreDeclaracionOperacion_DBOutputCollection) ::) external;

declare function local:func($ConsultarTreDeclaracionOperacion_Output as element() (:: schema-element(ns1:ConsultarTreDeclaracionOperacion_DBOutputCollection) ::)) as element() (:: schema-element(ns2:ConsultarDeclaracionJuradaResponse) ::) {
    <ns2:ConsultarDeclaracionJuradaResponse>
        {
            for $ConsultarTreDeclaracionOperacion_DBOutput in $ConsultarTreDeclaracionOperacion_Output/ns1:ConsultarTreDeclaracionOperacion_DBOutput
            return 
            <ns2:DeclaracionJurada>
                <dec:idDeclaracion>{fn:data($ConsultarTreDeclaracionOperacion_DBOutput/ns1:ID)}</dec:idDeclaracion>
                <dec:codigoExternoDeclaracion>{fn:data($ConsultarTreDeclaracionOperacion_DBOutput/ns1:ID_DECLARACION)}</dec:codigoExternoDeclaracion>
                <dec:estadoDeclaracion>
                    <dec:codigoEstadoDeclaracion>{fn:data($ConsultarTreDeclaracionOperacion_DBOutput/ns1:ESTADO_DECLARACION)}</dec:codigoEstadoDeclaracion>
                    <dec:nombreEstadoDeclaracion>{fn:data($ConsultarTreDeclaracionOperacion_DBOutput/ns1:DESCRIPCION_DECLARACION)}</dec:nombreEstadoDeclaracion>
                    <dec:EstadoNoObjecion></dec:EstadoNoObjecion>
                    <dec:nombreEstadoNoObjecion></dec:nombreEstadoNoObjecion>
                </dec:estadoDeclaracion>
                <dec:estadoBusqueda>
                    <dec:codigoEstadoBusqueda>{fn:data($ConsultarTreDeclaracionOperacion_DBOutput/ns1:ESTADO_BUSQUEDA_CONTRAPARTES)}</dec:codigoEstadoBusqueda>
                    <dec:nombreEstadoBusqueda>{fn:data($ConsultarTreDeclaracionOperacion_DBOutput/ns1:DESCRIPCION_BUSQUEDA)}</dec:nombreEstadoBusqueda>
                </dec:estadoBusqueda>
                <dec:calificacionDeRiesgo>
                    <dec:codigoCalificacionDeRiesgo>{fn:data($ConsultarTreDeclaracionOperacion_DBOutput/ns1:NIVEL_RIESGO)}</dec:codigoCalificacionDeRiesgo>
                    <dec:nombreCalificacionDeRiesgo>{fn:data($ConsultarTreDeclaracionOperacion_DBOutput/ns1:DESCRIPCION_RIESGO)}</dec:nombreCalificacionDeRiesgo>
                </dec:calificacionDeRiesgo>
                <dec:fechaRegistro>{fn:data($ConsultarTreDeclaracionOperacion_DBOutput/ns1:FECHA_REGISTRO)}</dec:fechaRegistro>
                <dec:fechaFirmaDeclaracion>{fn:data($ConsultarTreDeclaracionOperacion_DBOutput/ns1:FECHA_FIRMA_DECLARACION)}</dec:fechaFirmaDeclaracion>
                <dec:fechaVencimiento>{fn:data($ConsultarTreDeclaracionOperacion_DBOutput/ns1:FECHA_VENCIMIENTO)}</dec:fechaVencimiento>
                <dec:comentarioDeclaracion>{fn:data($ConsultarTreDeclaracionOperacion_DBOutput/ns1:COMENTARIO_DECLARACION)}</dec:comentarioDeclaracion>
                <dec:comentarioBusqueda>{fn:data($ConsultarTreDeclaracionOperacion_DBOutput/ns1:COMENTARIO_BUSQUEDA)}</dec:comentarioBusqueda>
                <dec:elevarAOtraInstancia>{if(fn:data($ConsultarTreDeclaracionOperacion_DBOutput/ns1:SE_ELEVA_OTRA_INSTANCIA)=1) then true() else false()}</dec:elevarAOtraInstancia>
                <dec:tipoOrigen>{fn:data($ConsultarTreDeclaracionOperacion_DBOutput/ns1:TIPO_ORIGEN)}</dec:tipoOrigen>
                <dec:tipoSeguimiento>{fn:data($ConsultarTreDeclaracionOperacion_DBOutput/ns1:TIPO_SEGUIMIENTO)}</dec:tipoSeguimiento>
                <dec:instanciaProceso>{fn:data($ConsultarTreDeclaracionOperacion_DBOutput/ns1:INSTANCIA_PROCESO)}</dec:instanciaProceso>
                <dec:esSoloLectura>{if(fn:data($ConsultarTreDeclaracionOperacion_DBOutput/ns1:ES_SOLO_LECTURA)=1) then true() else false()}</dec:esSoloLectura>
                <dec:status>{if(fn:data($ConsultarTreDeclaracionOperacion_DBOutput/ns1:BAN_ESTATUS)=1) then true() else false()}</dec:status></ns2:DeclaracionJurada>
        }
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>La consulta se ha realizado exitosamente</res:message>
        </ns2:Resultado>
    </ns2:ConsultarDeclaracionJuradaResponse>
};

local:func($ConsultarTreDeclaracionOperacion_Output)
