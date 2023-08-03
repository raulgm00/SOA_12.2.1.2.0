xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ConsultarParametrosEnBitacoraProcesoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/ConsultarParametrosEnBitacoraProceso/V1/Schema/ConsultarParametrosEnBitacoraProcesoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarParametrosEnBitacoraProceso_DB";
(:: import schema at "../XSD/ConsultarParametrosEnBitacoraProceso_DB.xsd" ::)

declare namespace con = "http://www.bcie.org/ConsultarParametrosEnBitacoraProcesoBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ConsultarParametrosEnBitacoraProceso_DBOutputCollection as element() (:: schema-element(ns1:ConsultarParametrosEnBitacoraProceso_DBOutputCollection) ::) external;

declare function local:func($ConsultarParametrosEnBitacoraProceso_DBOutputCollection as element() (:: schema-element(ns1:ConsultarParametrosEnBitacoraProceso_DBOutputCollection) ::)) as element() (:: schema-element(ns2:responseConsultarParametrosEnBitacoraProcesoMessage) ::) {
    <ns2:responseConsultarParametrosEnBitacoraProcesoMessage>
        <ns2:Parametros>
            <con:idProcesoOperacion>{fn:data($ConsultarParametrosEnBitacoraProceso_DBOutputCollection/ns1:ConsultarParametrosEnBitacoraProceso_DBOutput/ns1:tr_ID_TBI_PROCESO_OPERACION)}</con:idProcesoOperacion>
            <con:idProceso>{fn:data($ConsultarParametrosEnBitacoraProceso_DBOutputCollection/ns1:ConsultarParametrosEnBitacoraProceso_DBOutput/ns1:tp_ID_PROCESO)}</con:idProceso>
            <con:nombreProceso>{fn:data($ConsultarParametrosEnBitacoraProceso_DBOutputCollection/ns1:ConsultarParametrosEnBitacoraProceso_DBOutput/ns1:tp_NOMBRE_PROCESO)}</con:nombreProceso>
            <con:parameter1>{fn:data($ConsultarParametrosEnBitacoraProceso_DBOutputCollection/ns1:ConsultarParametrosEnBitacoraProceso_DBOutput/ns1:tp_STRING01)}</con:parameter1>
            <con:parameter2>{fn:data($ConsultarParametrosEnBitacoraProceso_DBOutputCollection/ns1:ConsultarParametrosEnBitacoraProceso_DBOutput/ns1:tp_STRING02)}</con:parameter2>
            <con:parameter3>{fn:data($ConsultarParametrosEnBitacoraProceso_DBOutputCollection/ns1:ConsultarParametrosEnBitacoraProceso_DBOutput/ns1:tp_STRING03)}</con:parameter3>
        </ns2:Parametros>
        <ns2:Resultado>
            <res:result>OK</res:result>
            {if (count($ConsultarParametrosEnBitacoraProceso_DBOutputCollection/ns1:ConsultarParametrosEnBitacoraProceso_DBOutput)> 0)then
            <res:message>Operacion Exitosa</res:message>
            else
            <res:message>No existen registros</res:message>
            }
            <res:error>
                <err:errorCode></err:errorCode>
                <err:errorDescription></err:errorDescription>
                <err:errorType></err:errorType>
            </res:error>
        </ns2:Resultado>
    </ns2:responseConsultarParametrosEnBitacoraProcesoMessage>
};

local:func($ConsultarParametrosEnBitacoraProceso_DBOutputCollection)