xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/SolicitudAprobacionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/SolicitudAprobacion/V1/Schema/SolicitudAprobacionMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarDesAproByIdNivelAprobacion";
(:: import schema at "../XSD/ConsultarDesAproByIdNivelAprobacion.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare variable $ConsultarDecisionAprobacionResponse as element() (:: schema-element(ns1:ConsultarDesAproByIdNivelAprobacionOutputCollection) ::) external;

declare function local:func($ConsultarDecisionAprobacionResponse as element() (:: schema-element(ns1:ConsultarDesAproByIdNivelAprobacionOutputCollection) ::)) as element() (:: schema-element(ns2:ConsultarDecisionAprobacionByIdAprobacionResponse) ::) {
    <ns2:ConsultarDecisionAprobacionByIdAprobacionResponse>
    {
    for $i in $ConsultarDecisionAprobacionResponse/ns1:ConsultarDesAproByIdNivelAprobacionOutput
    return
        <ns2:CatalogoAprobacion>
            <cat:Id>{fn:data($i/ns1:ID)}</cat:Id>
            <cat:Descripcion>{fn:data($i/ns1:DESCRIPCION)}</cat:Descripcion>
            <cat:DescripcionCorta>{fn:data($i/ns1:DESCRIPCION_CORTA)}</cat:DescripcionCorta>
            <cat:codigoExterno>{fn:data($i/ns1:COD_EXTERNO)}</cat:codigoExterno>             
        </ns2:CatalogoAprobacion>
        }
    </ns2:ConsultarDecisionAprobacionByIdAprobacionResponse>
};

local:func($ConsultarDecisionAprobacionResponse)
