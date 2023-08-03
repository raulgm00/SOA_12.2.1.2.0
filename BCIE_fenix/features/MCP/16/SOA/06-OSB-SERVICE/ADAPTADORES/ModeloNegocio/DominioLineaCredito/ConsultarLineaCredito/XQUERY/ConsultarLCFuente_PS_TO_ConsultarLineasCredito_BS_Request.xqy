xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarLineaCredito";
(:: import schema at "../XSD/ConsultarLineaCredito.xsd" ::)

declare variable $ConsultarLineaCreditoFuenteRequest as element() (:: schema-element(ns1:ConsultarLineaCreditoFuenteRequest) ::) external;

declare function local:func($ConsultarLineaCreditoFuenteRequest as element() (:: schema-element(ns1:ConsultarLineaCreditoFuenteRequest) ::)) as element() (:: schema-element(ns2:ConsultarLineaCreditoInput) ::) {
    <ns2:ConsultarLineaCreditoInput>
        { if (string-length(fn:data($ConsultarLineaCreditoFuenteRequest/ns1:idLineaCredito/text())) > 0)
        then 
        <ns2:ID_LC>{fn:data($ConsultarLineaCreditoFuenteRequest/ns1:idLineaCredito)}</ns2:ID_LC>
        else ()
        }
        { if (string-length(fn:data($ConsultarLineaCreditoFuenteRequest/ns1:numero_linea/text())) > 0)
        then 
        <ns2:NUMERO_LINEA>{fn:data($ConsultarLineaCreditoFuenteRequest/ns1:numero_linea)}</ns2:NUMERO_LINEA>
        else ()
        }
    </ns2:ConsultarLineaCreditoInput>
};

local:func($ConsultarLineaCreditoFuenteRequest)
