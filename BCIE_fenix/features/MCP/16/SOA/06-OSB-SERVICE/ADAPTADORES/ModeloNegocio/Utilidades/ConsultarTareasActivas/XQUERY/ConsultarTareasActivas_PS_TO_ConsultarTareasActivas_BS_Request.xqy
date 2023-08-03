xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ConsultarTareasActivasMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/ConsultarTareasActivas/V1/Schema/ConsultarTareasActivasMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarTareasActivas_DB";
(:: import schema at "../XSD/ConsultarTareasActivas_DB.xsd" ::)

declare namespace con = "http://www.bcie.org/ConsultarTareasActivasBO";

declare variable $requestConsultarTareasActivas as element() (:: schema-element(ns1:requestConsultarTareasActivas) ::) external;

declare function local:func($requestConsultarTareasActivas as element() (:: schema-element(ns1:requestConsultarTareasActivas) ::)) as element() (:: schema-element(ns2:ConsultarTareasActivas_DBInput) ::) {
    <ns2:ConsultarTareasActivas_DBInput>
        <ns2:idOperacion>{fn:data($requestConsultarTareasActivas/ns1:Operacion/con:idOperacion)}</ns2:idOperacion>
    </ns2:ConsultarTareasActivas_DBInput>
};

local:func($requestConsultarTareasActivas)
