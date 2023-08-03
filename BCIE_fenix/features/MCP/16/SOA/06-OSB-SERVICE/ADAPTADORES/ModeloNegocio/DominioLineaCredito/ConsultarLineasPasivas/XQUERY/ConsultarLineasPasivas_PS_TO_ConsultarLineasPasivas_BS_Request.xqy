xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarLineasPasivas";
(:: import schema at "../XSD/ConsultarLineasPasivas.xsd" ::)

declare variable $ConsultarLineasPasivasRequest as element() (:: schema-element(ns1:ConsultarLineasPasivasRequest) ::) external;
declare variable $index as xs:integer external;

declare function local:func($ConsultarLineasPasivasRequest as element() (:: schema-element(ns1:ConsultarLineasPasivasRequest) ::), 
                            $index as xs:integer) 
                            as element() (:: schema-element(ns2:ConsultarLineasPasivasInput) ::) {
    <ns2:ConsultarLineasPasivasInput>
        <ns2:LineasPasivas>{fn:data($ConsultarLineasPasivasRequest/ns1:idLineaPasiva[$index])}</ns2:LineasPasivas>
    </ns2:ConsultarLineasPasivasInput>
};

local:func($ConsultarLineasPasivasRequest, $index)
