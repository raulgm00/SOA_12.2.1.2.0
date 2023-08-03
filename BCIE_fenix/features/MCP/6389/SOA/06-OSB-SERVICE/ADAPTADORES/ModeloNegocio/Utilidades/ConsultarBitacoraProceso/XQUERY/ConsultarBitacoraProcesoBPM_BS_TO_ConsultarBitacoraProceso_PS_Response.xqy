xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/CrearBitacoraProcesoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/BitacoraProceso/V1/Schema/BitacoraProcesoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarBitacoraProcesoByIdIOperacion_DB";
(:: import schema at "../XSD/ConsultarBitacoraProcesoByIdIOperacion_DB.xsd" ::)

declare namespace cre = "http://www.bcie.org/CrearBitacoraProcesoBO";

declare variable $consultarBitacoraProcesoOut as element() (:: schema-element(ns1:ConsultarBitacoraProcesoByIdIOperacion_DBOutputCollection) ::) external;

declare function local:func($consultarBitacoraProcesoOut as element() (:: schema-element(ns1:ConsultarBitacoraProcesoByIdIOperacion_DBOutputCollection) ::)) as element() (:: schema-element(ns2:consultarBitacoraProcesoResponse) ::) {
    <ns2:consultarBitacoraProcesoResponse>
        {
            for $ConsultarBitacoraProcesoByIdIOperacion_DBOutput in $consultarBitacoraProcesoOut/ns1:ConsultarBitacoraProcesoByIdIOperacion_DBOutput
            return 
            <ns2:Bitacora>
                <cre:id>{fn:data($ConsultarBitacoraProcesoByIdIOperacion_DBOutput/ns1:ID)}</cre:id>
                <cre:idOperacion>{fn:data($ConsultarBitacoraProcesoByIdIOperacion_DBOutput/ns1:ID_OPERACION)}</cre:idOperacion>
                <cre:nombreOperacion>{fn:data($ConsultarBitacoraProcesoByIdIOperacion_DBOutput/ns1:NOMBRE_OPERACION)}</cre:nombreOperacion>
                <cre:idProceso>{fn:data($ConsultarBitacoraProcesoByIdIOperacion_DBOutput/ns1:ID_PROCESO)}</cre:idProceso>
                <cre:nombreProceso>{fn:data($ConsultarBitacoraProcesoByIdIOperacion_DBOutput/ns1:NOMBRE_PROCESO)}</cre:nombreProceso>
                <cre:esProceso>{if(fn:string($ConsultarBitacoraProcesoByIdIOperacion_DBOutput/ns1:BAN_ES_PROCESO)='1') then'true' else('false')}</cre:esProceso>
                <cre:string01>{fn:data($ConsultarBitacoraProcesoByIdIOperacion_DBOutput/ns1:STRING01)}</cre:string01>
                <cre:string02>{fn:data($ConsultarBitacoraProcesoByIdIOperacion_DBOutput/ns1:STRING02)}</cre:string02>
                <cre:string03>{fn:data($ConsultarBitacoraProcesoByIdIOperacion_DBOutput/ns1:STRING03)}</cre:string03>
               
                 </ns2:Bitacora>
        }
    </ns2:consultarBitacoraProcesoResponse>
};

local:func($consultarBitacoraProcesoOut)
