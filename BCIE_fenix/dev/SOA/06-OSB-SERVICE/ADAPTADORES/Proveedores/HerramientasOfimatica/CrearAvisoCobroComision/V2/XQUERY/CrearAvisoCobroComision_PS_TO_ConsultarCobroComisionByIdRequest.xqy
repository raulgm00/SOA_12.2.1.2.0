xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ComisionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/Comision/V1/Schema/ComisionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarCobroComisionById";
(:: import schema at "../XSD/ConsultarCobroComisionById.xsd" ::)

declare variable $CrearAvisoCobroComision as element() (:: schema-element(ns1:CrearAvisoCobroComisionV2Request) ::) external;

declare function local:func($CrearAvisoCobroComision as element() (:: schema-element(ns1:CrearAvisoCobroComisionV2Request) ::)) as element() (:: schema-element(ns2:ConsultarCobroComisionByIdInput) ::) {
    <ns2:ConsultarCobroComisionByIdInput>
        <ns2:ID_OPERACION>{fn:data($CrearAvisoCobroComision/ns1:idOperacion)}</ns2:ID_OPERACION>
        <ns2:ID_COMISION>{fn:data($CrearAvisoCobroComision/ns1:idComision)}</ns2:ID_COMISION>
    </ns2:ConsultarCobroComisionByIdInput>
};

local:func($CrearAvisoCobroComision)
