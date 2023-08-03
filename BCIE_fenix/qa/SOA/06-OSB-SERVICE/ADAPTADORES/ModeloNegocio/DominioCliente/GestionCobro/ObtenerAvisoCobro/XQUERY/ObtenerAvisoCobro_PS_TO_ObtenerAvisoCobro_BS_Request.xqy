xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/GestionCobroMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioCliente/GestionCobro/V1/Schema/GestionCobroMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ObtenerAvisoCobro";
(:: import schema at "../XSD/ObtenerAvisoCobro_sp.xsd" ::)

declare variable $ObtenerAvisoCobroRequest as element() (:: schema-element(ns1:ObtenerAvisoCobroRequest) ::) external;

declare function local:func($ObtenerAvisoCobroRequest as element() (:: schema-element(ns1:ObtenerAvisoCobroRequest) ::)) as element() (:: schema-element(ns2:InputParameters) ::) {
    <ns2:InputParameters>
        <ns2:P_IDAVISO>{fn:data($ObtenerAvisoCobroRequest/ns1:idContrato)}</ns2:P_IDAVISO>
    </ns2:InputParameters>
};

local:func($ObtenerAvisoCobroRequest)
