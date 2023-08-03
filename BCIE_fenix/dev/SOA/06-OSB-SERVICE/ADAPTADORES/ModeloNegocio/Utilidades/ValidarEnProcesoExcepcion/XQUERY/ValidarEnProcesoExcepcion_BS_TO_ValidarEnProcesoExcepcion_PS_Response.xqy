xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ValidarEnProcesoExcepcionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/ValidarEnProcesoExcepcion/V1/Schema/ValidarEnProcesoExcepcionMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ValidarEnProcesoExcepcion_DB";
(:: import schema at "../XSD/ValidarEnProcesoExcepcion_DB.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ValidarEnProcesoExcepcion_DBOutputCollection as element() (:: schema-element(ns1:ValidarEnProcesoExcepcion_DBOutputCollection) ::) external;

declare function local:func($ValidarEnProcesoExcepcion_DBOutputCollection as element() (:: schema-element(ns1:ValidarEnProcesoExcepcion_DBOutputCollection) ::)) as element() (:: schema-element(ns2:ValidarEnProcesoExcepcionResponse) ::) {
    <ns2:ValidarEnProcesoExcepcionResponse>
        <ns2:EnProceso>{fn:data($ValidarEnProcesoExcepcion_DBOutputCollection/ns1:ValidarEnProcesoExcepcion_DBOutput/ns1:ENPROCESO)}</ns2:EnProceso>
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>Validación Exitosa</res:message>
        </ns2:Resultado>
    </ns2:ValidarEnProcesoExcepcionResponse>
};

local:func($ValidarEnProcesoExcepcion_DBOutputCollection)
