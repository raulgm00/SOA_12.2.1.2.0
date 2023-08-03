xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CrearBitacoraProcesoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/BitacoraProceso/V1/Schema/BitacoraProcesoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarBitacoraProcesoByIdIOperacion_DB";
(:: import schema at "../XSD/ConsultarBitacoraProcesoByIdIOperacion_DB.xsd" ::)

declare variable $ConsultarBitacoraProcesoRequest as element() (:: schema-element(ns1:consultarBitacoraProcesoRequest) ::) external;

declare function local:func($ConsultarBitacoraProcesoRequest as element() (:: schema-element(ns1:consultarBitacoraProcesoRequest) ::)) as element() (:: schema-element(ns2:ConsultarBitacoraProcesoByIdIOperacion_DBInput) ::) {
    <ns2:ConsultarBitacoraProcesoByIdIOperacion_DBInput>
        <ns2:IDOPERACION>{fn:data($ConsultarBitacoraProcesoRequest/ns1:idOperacion)}</ns2:IDOPERACION>
        <ns2:IDPROCESO>{fn:data($ConsultarBitacoraProcesoRequest/ns1:idProceso)}</ns2:IDPROCESO>
        <ns2:IDTIPO>{fn:data($ConsultarBitacoraProcesoRequest/ns1:idTipo)}</ns2:IDTIPO>
        <ns2:TIPO>{fn:data($ConsultarBitacoraProcesoRequest/ns1:Tipo)}</ns2:TIPO>
    </ns2:ConsultarBitacoraProcesoByIdIOperacion_DBInput>
};

local:func($ConsultarBitacoraProcesoRequest)
