xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/GestionCobroMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioCliente/GestionCobro/V1/Schema/GestionCobroMO.xsd" ::)

declare variable $ObtenerAvisoCobroRequest as element() (:: schema-element(ns1:ObtenerAvisoCobroRequest) ::) external;

declare function local:func($ObtenerAvisoCobroRequest as element() (:: schema-element(ns1:ObtenerAvisoCobroRequest) ::)) as element() (:: schema-element(ns1:ConsultarGestionCobroRequest) ::) {
    <ns1:ConsultarGestionCobroRequest>
        <ns1:idEjecucion>{fn:data($ObtenerAvisoCobroRequest/ns1:idContrato)}</ns1:idEjecucion>
    </ns1:ConsultarGestionCobroRequest>
};

local:func($ObtenerAvisoCobroRequest)
