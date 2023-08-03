xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DeclaracionJuradaMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/DeclaracionJurada/V1/Schema/DeclaracionJuradaMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarDeclaracionJuradaByIdDeclaracion";
(:: import schema at "../XSD/ConsultarDeclaracionJuradaByIdDeclaracion.xsd" ::)

declare namespace dec = "http://www.bcie.org/DeclaracionJuradaBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $Declaracion as element() (:: schema-element(ns2:ConsultarDeclaracionJuradaByIdDeclaracionOutputCollection) ::) external;

declare function local:func($Declaracion as element() (:: schema-element(ns2:ConsultarDeclaracionJuradaByIdDeclaracionOutputCollection) ::)) as element() (:: schema-element(ns1:ConsultarDeclaracionJuradaByIdDeclaracionResponse) ::) {
    <ns1:ConsultarDeclaracionJuradaByIdDeclaracionResponse>
        <ns1:DeclaracionJurada>
            <dec:idDeclaracion>{fn:data($Declaracion/ns2:ConsultarDeclaracionJuradaByIdDeclaracionOutput/ns2:ID)}</dec:idDeclaracion>
            <dec:codigoExternoDeclaracion>{fn:data($Declaracion/ns2:ConsultarDeclaracionJuradaByIdDeclaracionOutput/ns2:ID_DECLARACION)}</dec:codigoExternoDeclaracion>
        <dec:estadoDeclaracion>
                <dec:codigoEstadoDeclaracion>{fn:data($Declaracion/ns2:ConsultarDeclaracionJuradaByIdDeclaracionOutput/ns2:ESTADO_DECLARACION)}</dec:codigoEstadoDeclaracion>
                <dec:nombreEstadoDeclaracion>{fn:data($Declaracion/ns2:ConsultarDeclaracionJuradaByIdDeclaracionOutput/ns2:DESCRIPCION_DECLARACION)}</dec:nombreEstadoDeclaracion>
                <dec:EstadoNoObjecion></dec:EstadoNoObjecion>
            </dec:estadoDeclaracion>
            <dec:estadoBusqueda>
                <dec:codigoEstadoBusqueda>{fn:data($Declaracion/ns2:ConsultarDeclaracionJuradaByIdDeclaracionOutput/ns2:ESTADO_BUSQUEDA_CONTRAPARTES)}</dec:codigoEstadoBusqueda>
                <dec:nombreEstadoBusqueda>{fn:data($Declaracion/ns2:ConsultarDeclaracionJuradaByIdDeclaracionOutput/ns2:DESCRIPCION_BUSQUEDA)}</dec:nombreEstadoBusqueda>
            </dec:estadoBusqueda>
            <dec:calificacionDeRiesgo>
                <dec:codigoCalificacionDeRiesgo>{fn:data($Declaracion/ns2:ConsultarDeclaracionJuradaByIdDeclaracionOutput/ns2:NIVEL_RIESGO)}</dec:codigoCalificacionDeRiesgo>
                <dec:nombreCalificacionDeRiesgo>{fn:data($Declaracion/ns2:ConsultarDeclaracionJuradaByIdDeclaracionOutput/ns2:DESCRIPCION_RIESGO)}</dec:nombreCalificacionDeRiesgo>
            </dec:calificacionDeRiesgo>
            <dec:fechaRegistro>{fn:data($Declaracion/ns2:ConsultarDeclaracionJuradaByIdDeclaracionOutput/ns2:FECHA_REGISTRO)}</dec:fechaRegistro>
            <dec:fechaFirmaDeclaracion>{fn:data($Declaracion/ns2:ConsultarDeclaracionJuradaByIdDeclaracionOutput/ns2:FECHA_FIRMA_DECLARACION)}</dec:fechaFirmaDeclaracion>
            <dec:fechaVencimiento>{fn:data($Declaracion/ns2:ConsultarDeclaracionJuradaByIdDeclaracionOutput/ns2:FECHA_VENCIMIENTO)}</dec:fechaVencimiento>
            <dec:comentarioDeclaracion>{fn:data($Declaracion/ns2:ConsultarDeclaracionJuradaByIdDeclaracionOutput/ns2:COMENTARIO_DECLARACION)}</dec:comentarioDeclaracion>
            <dec:comentarioBusqueda>{fn:data($Declaracion/ns2:ConsultarDeclaracionJuradaByIdDeclaracionOutput/ns2:COMENTARIO_BUSQUEDA)}</dec:comentarioBusqueda>
         <dec:elevarAOtraInstancia>{
              if(xs:string($Declaracion/ns2:ConsultarDeclaracionJuradaByIdDeclaracionOutput/ns2:SE_ELEVA_OTRA_INSTANCIA)!='') then
                (
                  if(fn:data($Declaracion/ns2:ConsultarDeclaracionJuradaByIdDeclaracionOutput/ns2:SE_ELEVA_OTRA_INSTANCIA)=1) then
                    true()
                  else
                    false()
                )
              else
                false()
            }</dec:elevarAOtraInstancia>
            <dec:tipoOrigen>{fn:data($Declaracion/ns2:ConsultarDeclaracionJuradaByIdDeclaracionOutput/ns2:TIPO_ORIGEN)}</dec:tipoOrigen>
            <dec:tipoSeguimiento>{fn:data($Declaracion/ns2:ConsultarDeclaracionJuradaByIdDeclaracionOutput/ns2:TIPO_SEGUIMIENTO)}</dec:tipoSeguimiento>
            <dec:instanciaProceso>{fn:data($Declaracion/ns2:ConsultarDeclaracionJuradaByIdDeclaracionOutput/ns2:INSTACIA_PROCESO)}</dec:instanciaProceso>
           <dec:esSoloLectura>{
              if(xs:string($Declaracion/ns2:ConsultarDeclaracionJuradaByIdDeclaracionOutput/ns2:ES_SOLO_LECTURA)!='') then
                (
                  if(fn:data($Declaracion/ns2:ConsultarDeclaracionJuradaByIdDeclaracionOutput/ns2:ES_SOLO_LECTURA)=1) then
                    true()
                  else
                    false()
                )
              else
                false()
            }</dec:esSoloLectura>
            <dec:status>{
              if(xs:string($Declaracion/ns2:ConsultarDeclaracionJuradaByIdDeclaracionOutput/ns2:BAN_ESTATUS)!='') then
                (
                  if(fn:data($Declaracion/ns2:ConsultarDeclaracionJuradaByIdDeclaracionOutput/ns2:BAN_ESTATUS)=1) then
                    true()
                  else
                    false()
                )
              else
                false()
            }</dec:status>
        </ns1:DeclaracionJurada>
        {
            if (fn:data($Declaracion/ns2:ConsultarDeclaracionJuradaByIdDeclaracionOutput/ns2:ID)) then
                <ns1:Resultado>
                    <res:result>OK</res:result>
                    <res:message>Consulta Exitosa</res:message>
                </ns1:Resultado>
            else
                <ns1:Resultado>
                    <res:result>ERROR</res:result>
                    <res:message>Consulta sin resultado</res:message>
                </ns1:Resultado>
        }
    </ns1:ConsultarDeclaracionJuradaByIdDeclaracionResponse>
};

local:func($Declaracion)
