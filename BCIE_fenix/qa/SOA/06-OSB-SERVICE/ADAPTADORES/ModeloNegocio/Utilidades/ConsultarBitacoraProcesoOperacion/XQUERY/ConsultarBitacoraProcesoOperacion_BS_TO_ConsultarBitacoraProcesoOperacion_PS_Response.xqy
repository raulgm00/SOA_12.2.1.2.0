xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/CrearBitacoraProcesoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/BitacoraProceso/V1/Schema/BitacoraProcesoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarBitacoraProcesoOperacion";
(:: import schema at "../XSD/ConsultarBitacoraProcesoOperacion.xsd" ::)

declare namespace cre = "http://www.bcie.org/CrearBitacoraProcesoBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ConsultarBitacoraProcesoOperacionOutputCollection as element() (:: schema-element(ns1:ConsultarBitacoraProcesoOperacionOutputCollection) ::) external;

declare function local:func($ConsultarBitacoraProcesoOperacionOutputCollection as element() (:: schema-element(ns1:ConsultarBitacoraProcesoOperacionOutputCollection) ::)) as element() (:: schema-element(ns2:ConsultarBitacoraProcesoOperacionResponse) ::) {

    let $finalizados:= $ConsultarBitacoraProcesoOperacionOutputCollection/ns1:ConsultarBitacoraProcesoOperacionOutput[ns1:INSTANCIA_PROCESO!=''][ns1:BAN_ES_PROCESO= 1][ns1:BAN_FINALIZADO= 1]
    let $activos:=if (count($finalizados/ns1:ID)=0) then
                  $ConsultarBitacoraProcesoOperacionOutputCollection/ns1:ConsultarBitacoraProcesoOperacionOutput[ns1:INSTANCIA_PROCESO!=''][ns1:BAN_ES_PROCESO= 1][ns1:BAN_FINALIZADO= 0]
                  else
                  $ConsultarBitacoraProcesoOperacionOutputCollection/ns1:ConsultarBitacoraProcesoOperacionOutput[ns1:INSTANCIA_PROCESO!=''][ns1:INSTANCIA_PROCESO!=$finalizados/ns1:INSTANCIA_PROCESO][ns1:BAN_ES_PROCESO= 1]
    return
    <ns2:ConsultarBitacoraProcesoOperacionResponse>
    {
    for $bitacora in $activos
    return
        <ns2:Bitacora>
            <cre:id>{fn:data($bitacora/ns1:ID)}</cre:id>
            <cre:idOperacion>{fn:data($bitacora/ns1:ID_OPERACION)}</cre:idOperacion>
            <cre:nombreOperacion>{fn:data($bitacora/ns1:NOMBRE_OPERACION)}</cre:nombreOperacion>
            <cre:idCliente></cre:idCliente>
            <cre:razonSocial></cre:razonSocial>
            <cre:idProceso>{fn:data($bitacora/ns1:ID_PROCESO)}</cre:idProceso>
            <cre:nombreProceso>{fn:data($bitacora/ns1:NOMBRE_PROCESO)}</cre:nombreProceso>
            <cre:instanciaProceso>{fn:data($bitacora/ns1:INSTANCIA_PROCESO)}</cre:instanciaProceso>
            <cre:idProcesoSiguiente>{fn:data($bitacora/ns1:INSTANCIA_PROCESO_SIGUIENTE)}</cre:idProcesoSiguiente>
            <cre:esProceso>
            {
            if (fn:data($bitacora/ns1:BAN_ES_PROCESO)= 1)
            then
              true()
            else
              false()
            }
            </cre:esProceso>
            <cre:idTarea>{fn:data($bitacora/ns1:ID_TAREA)}</cre:idTarea>
            <cre:nombreTarea>{fn:data($bitacora/ns1:NOMBRE_TAREA)}</cre:nombreTarea>
            <cre:instanciaTarea>{fn:data($bitacora/ns1:INSTANCIA_TAREA)}</cre:instanciaTarea>
            <cre:usuario>{fn:data($bitacora/ns1:USUARIO)}</cre:usuario>
            <cre:nombreUsuario>{fn:data($bitacora/ns1:NOMBRE_USUARIO)}</cre:nombreUsuario>
            <cre:esFinActividad>
            {
            if (fn:data($bitacora/ns1:BAN_ES_FIN_ACTIVIDAD)= 1)
            then
              true()
            else
              false()
            }
            </cre:esFinActividad>
            <cre:fechaRegistro>{fn:data($bitacora/ns1:FECHA_REGISTRO)}</cre:fechaRegistro>
            <cre:tiempoProceso>{fn:data($bitacora/ns1:TIEMPO_PROCESO)}</cre:tiempoProceso>
            <cre:tiempoTarea>{fn:data($bitacora/ns1:TIEMPO_TAREA)}</cre:tiempoTarea>
            <cre:raroc>{fn:data($bitacora/ns1:RAROC)}</cre:raroc>
            <cre:finalizado>
            {
            if (fn:data($bitacora/ns1:BAN_FINALIZADO)= 1)
            then
              true()
            else
              false()
            }
            </cre:finalizado>
            <cre:string01>{fn:data($bitacora/ns1:STRING01)}</cre:string01>
            <cre:string02>{fn:data($bitacora/ns1:STRING02)}</cre:string02>
            <cre:string03>{fn:data($bitacora/ns1:STRING03)}</cre:string03>
        </ns2:Bitacora>
        }
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>Consulta Exitosa</res:message>
        </ns2:Resultado>
    </ns2:ConsultarBitacoraProcesoOperacionResponse>
};

local:func($ConsultarBitacoraProcesoOperacionOutputCollection)
