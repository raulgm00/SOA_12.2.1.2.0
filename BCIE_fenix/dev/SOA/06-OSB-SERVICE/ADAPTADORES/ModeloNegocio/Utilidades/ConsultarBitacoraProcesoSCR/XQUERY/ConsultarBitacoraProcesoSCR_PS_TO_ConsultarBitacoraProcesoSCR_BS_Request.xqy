xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CrearBitacoraProcesoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/BitacoraProceso/V1/Schema/BitacoraProcesoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarBitacoraProcesoSCR";
(:: import schema at "../XSD/ConsultarBitacoraProcesoSCR.xsd" ::)

declare variable $consultarBitacoraProcesoSCRRequest as element() (:: schema-element(ns1:consultarBitacoraProcesoSCRRequest) ::) external;

declare function local:func($consultarBitacoraProcesoSCRRequest as element() (:: schema-element(ns1:consultarBitacoraProcesoSCRRequest) ::)) as element() (:: schema-element(ns2:ConsultarBitacoraProcesoSCRInput) ::) {
    <ns2:ConsultarBitacoraProcesoSCRInput>
        <ns2:idOperacion>{fn:data($consultarBitacoraProcesoSCRRequest/ns1:idOperacion)}</ns2:idOperacion>
    </ns2:ConsultarBitacoraProcesoSCRInput>
};

local:func($consultarBitacoraProcesoSCRRequest)
