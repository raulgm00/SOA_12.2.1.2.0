xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ComisionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/Comision/V1/Schema/ComisionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarProductoComisionByIdLineaCredito_DB";
(:: import schema at "../XSD/ConsultarProductoComisionByIdLineaCredito_DB.xsd" ::)

declare variable $ConsultarComisionByIdLineaCreditoRequest as element() (:: schema-element(ns1:ConsultarComisionByIdLineaCreditoRequest) ::) external;

declare function local:func($ConsultarComisionByIdLineaCreditoRequest as element() (:: schema-element(ns1:ConsultarComisionByIdLineaCreditoRequest) ::)) as element() (:: schema-element(ns2:ConsultarProductoComisionByIdLineaCredito_DBInput) ::) {
    <ns2:ConsultarProductoComisionByIdLineaCredito_DBInput>
        <ns2:ID_LINEA>{fn:data($ConsultarComisionByIdLineaCreditoRequest/ns1:idLineaCredito)}</ns2:ID_LINEA>
        <ns2:CODIGO_PRODUCTO>{fn:data($ConsultarComisionByIdLineaCreditoRequest/ns1:idProductoFlexcube)}</ns2:CODIGO_PRODUCTO>
        <ns2:TIPO_TASA>{fn:data($ConsultarComisionByIdLineaCreditoRequest/ns1:tipoTasa)}</ns2:TIPO_TASA>
        <ns2:COMPONENTE>{fn:data($ConsultarComisionByIdLineaCreditoRequest/ns1:componentePrincipal)}</ns2:COMPONENTE>
    </ns2:ConsultarProductoComisionByIdLineaCredito_DBInput>
};

local:func($ConsultarComisionByIdLineaCreditoRequest)
