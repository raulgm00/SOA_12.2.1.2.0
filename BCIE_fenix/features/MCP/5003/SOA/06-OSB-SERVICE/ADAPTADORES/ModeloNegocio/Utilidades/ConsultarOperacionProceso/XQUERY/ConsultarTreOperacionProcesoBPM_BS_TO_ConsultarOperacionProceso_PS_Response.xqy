xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ConsultarOperacionEnProcesoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/ConsultarOperacionEnProceso/V1/Schema/ConsultarOperacionEnProcesoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarTreOperacionProcesoBPM";
(:: import schema at "../XSD/ConsultarTreOperacionProcesoBPM.xsd" ::)

declare namespace con = "http://www.bcie.org/ConsultarOperacionEnProcesoBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $TreOperacionProcesoBpmCollection as element() (:: schema-element(ns1:ConsultarTreOperacionProcesoBPMOutputCollection) ::) external;

declare function local:func($TreOperacionProcesoBpmCollection as element() (:: schema-element(ns1:ConsultarTreOperacionProcesoBPMOutputCollection) ::)) as element() (:: schema-element(ns2:ConsultarOperacionEnProcesoResponse) ::) {
    <ns2:ConsultarOperacionEnProcesoResponse>
    {
    for $TreOperacionProceso in $TreOperacionProcesoBpmCollection/ns1:ConsultarTreOperacionProcesoBPMOutput[1]
    return
        <ns2:Proceso>
            {
                if ($TreOperacionProceso/ns1:ID_OPERACION)
                then <con:idOperacion>{fn:data($TreOperacionProceso/ns1:ID_OPERACION)}</con:idOperacion>
                else ()
            }
            {
                if ($TreOperacionProceso/ns1:ID_PROCESO)
                then <con:idProceso>{fn:data($TreOperacionProceso/ns1:ID_PROCESO)}</con:idProceso>
                else ()
            }
            {
                if ($TreOperacionProceso/ns1:BAN_ESTATUS)
                then <con:estatus>{fn:data($TreOperacionProceso/ns1:BAN_ESTATUS)}</con:estatus>
                else ()
            }
        </ns2:Proceso>
        }
        <ns2:Resultado>
          {
          if (string($TreOperacionProcesoBpmCollection/ns1:ConsultarTreOperacionProcesoBPMOutput/ns1:ID_OPERACION)='')then
            <res:result>ERROR</res:result>
          else
            <res:result>OK</res:result>
          }
            
            {if (string($TreOperacionProcesoBpmCollection/ns1:ConsultarTreOperacionProcesoBPMOutput/ns1:ID_OPERACION)='')then
            <res:message>No existen registros</res:message>
            else
             <res:message>Consulta realizada existosamente</res:message>
            }
        </ns2:Resultado>
    </ns2:ConsultarOperacionEnProcesoResponse>
};

local:func($TreOperacionProcesoBpmCollection)
