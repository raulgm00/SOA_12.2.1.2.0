xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ComisionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/Comision/V1/Schema/ComisionMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarFechaCargo";
(:: import schema at "../XSD/ConsultarFechaCargo.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $consultarFechaCargoOutput as element() (:: element(*, ns1:ConsultarFechaCargoOutputCollection) ::) external;

declare function local:func($consultarFechaCargoOutput as element() (:: element(*, ns1:ConsultarFechaCargoOutputCollection) ::)) as element() (:: schema-element(ns2:ConsultarFechaCargoResponse) ::) {
    <ns2:ConsultarFechaCargoResponse>
        <ns2:fechaCargo>{fn:data($consultarFechaCargoOutput/ns1:ConsultarFechaCargoOutput/ns1:FECHA_CALENDARIO)}</ns2:fechaCargo>
        <ns2:Resultado>
            <res:result>OK</res:result>
        </ns2:Resultado>
    </ns2:ConsultarFechaCargoResponse>
};

local:func($consultarFechaCargoOutput)
