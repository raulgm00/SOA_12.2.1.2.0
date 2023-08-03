xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/GestionCobroMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioCliente/GestionCobro/V1/Schema/GestionCobroMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarFondosAvisoCobro";
(:: import schema at "../../ConsultarFondosAvisoCobro/XSD/ConsultarFondosAvisoCobro.xsd" ::)

declare variable $ConsultarParametrosAvisoCobroRequest as element() (:: schema-element(ns2:ConsultarParametrosAvisoCobroRequest) ::) external;

declare function local:func($ConsultarParametrosAvisoCobroRequest as element() (:: schema-element(ns2:ConsultarParametrosAvisoCobroRequest) ::)) as element() (:: schema-element(ns1:ConsultarFondosAvisoCobroInput) ::) {
    <ns1:ConsultarFondosAvisoCobroInput>
        <ns1:MIS_CODE></ns1:MIS_CODE>
    </ns1:ConsultarFondosAvisoCobroInput>
};

local:func($ConsultarParametrosAvisoCobroRequest)
