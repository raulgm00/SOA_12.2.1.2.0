xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarCargoByProducto_DB";
(:: import schema at "../XSD/ConsultarCargoByProducto_DB.xsd" ::)

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare variable $ConsultarCargoByProductoRequest as element() (:: schema-element(ns1:ConsultarCargoByProductoRequest) ::) external;

declare function local:func($ConsultarCargoByProductoRequest as element() (:: schema-element(ns1:ConsultarCargoByProductoRequest) ::)) as element() (:: schema-element(ns2:ConsultarCargoByProducto_DBInput) ::) {
    <ns2:ConsultarCargoByProducto_DBInput>
        <ns2:PRODUCTO>{fn:data($ConsultarCargoByProductoRequest/ns1:Desembolso/des:producto/pro:idProducto)}</ns2:PRODUCTO></ns2:ConsultarCargoByProducto_DBInput>
};

local:func($ConsultarCargoByProductoRequest)
