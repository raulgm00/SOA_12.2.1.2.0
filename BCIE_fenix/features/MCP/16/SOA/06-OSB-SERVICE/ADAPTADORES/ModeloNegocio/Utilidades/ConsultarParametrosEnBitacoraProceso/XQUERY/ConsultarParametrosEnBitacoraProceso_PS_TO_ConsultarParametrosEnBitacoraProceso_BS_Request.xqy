xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ConsultarParametrosEnBitacoraProcesoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/ConsultarParametrosEnBitacoraProceso/V1/Schema/ConsultarParametrosEnBitacoraProcesoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarParametrosEnBitacoraProceso_DB";
(:: import schema at "../XSD/ConsultarParametrosEnBitacoraProceso_DB.xsd" ::)

declare variable $requestConsultarParametrosEnBitacoraProcesoMessage as element() (:: schema-element(ns1:requestConsultarParametrosEnBitacoraProcesoMessage) ::) external;

declare function local:func($requestConsultarParametrosEnBitacoraProcesoMessage as element() (:: schema-element(ns1:requestConsultarParametrosEnBitacoraProcesoMessage) ::)) as element() (:: schema-element(ns2:ConsultarParametrosEnBitacoraProceso_DBInput) ::) {
    <ns2:ConsultarParametrosEnBitacoraProceso_DBInput>
        <ns2:IDOPERACION>{fn:data($requestConsultarParametrosEnBitacoraProcesoMessage/ns1:idOperacionProceso)}</ns2:IDOPERACION>
        <ns2:IDPROCESO>{fn:data($requestConsultarParametrosEnBitacoraProcesoMessage/ns1:idProceso)}</ns2:IDPROCESO>
    </ns2:ConsultarParametrosEnBitacoraProceso_DBInput>
};

local:func($requestConsultarParametrosEnBitacoraProcesoMessage)