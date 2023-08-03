xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace linMO="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarFondoById_BS";
(:: import schema at "../XSD/ConsultarFondoById_BS.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare variable $ConsultarFondoRequest as element() (:: schema-element(linMO:ConsultarFondoRequest) ::) external;

declare function local:ConsultarFondoTransform($ConsultarFondoRequest as element() (:: schema-element(linMO:ConsultarFondoRequest) ::)) as element() (:: schema-element(ns1:ConsultarFondoById_BSInput) ::) {
    <ns1:ConsultarFondoById_BSInput>
        <ns1:fondo>{fn:data($ConsultarFondoRequest/linMO:fondo/cat:Id)}</ns1:fondo>
    </ns1:ConsultarFondoById_BSInput>
};

local:ConsultarFondoTransform($ConsultarFondoRequest)
