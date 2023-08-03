xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ConsultaDesembolsoById";
(:: import schema at "../XSD/ConsultaDesembolsoById_sp.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";


declare function local:func() as element() (:: schema-element(ns2:ConsultarDesembolsosByIdResponse) ::) {
    <ns2:ConsultarDesembolsosByIdResponse>
    <ns2:Resultado>
        <res:result>OK</res:result>
        <res:message>Consulta sin resultados</res:message>
    </ns2:Resultado>
    </ns2:ConsultarDesembolsosByIdResponse>
};

local:func()