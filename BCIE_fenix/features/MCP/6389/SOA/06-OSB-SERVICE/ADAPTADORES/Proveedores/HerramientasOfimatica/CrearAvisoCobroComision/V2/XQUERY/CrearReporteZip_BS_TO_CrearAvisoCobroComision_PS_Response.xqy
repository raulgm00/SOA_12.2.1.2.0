xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ComisionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/Comision/V1/Schema/ComisionMO.xsd" ::)
declare namespace ns1="http://www.bcie.org/herramientaOfismatica/Reporte";
(:: import schema at "../../../CrearReporteZip/XSD/Reporte.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $reporte as element() (:: schema-element(ns1:Reporte) ::) external;

declare function local:func($reporte as element() (:: schema-element(ns1:Reporte) ::)) as element() (:: schema-element(ns2:CrearAvisoCobroComisionV2Response) ::) {
    <ns2:CrearAvisoCobroComisionV2Response>
        <ns2:documentoCobroComision>{fn:data($reporte/ns1:Archivo/ns1:content)}</ns2:documentoCobroComision>
    </ns2:CrearAvisoCobroComisionV2Response>
};

local:func($reporte)
