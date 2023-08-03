xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ProductoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioProducto/Producto/V1/Schema/ProductoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarProductoByIdOperacion";
(:: import schema at "../XSD/ConsultarProductoByIdOperacion.xsd" ::)

declare variable $ProductoRequest as element() (:: schema-element(ns1:ConsultarProductoByIdOperacionRequest) ::) external;

declare function local:func($ProductoRequest as element() (:: schema-element(ns1:ConsultarProductoByIdOperacionRequest) ::)) as element() (:: schema-element(ns2:ConsultarProductoByIdOperacionInput) ::) {
    <ns2:ConsultarProductoByIdOperacionInput>
        <ns2:idOperacion>{fn:data($ProductoRequest/ns1:idOperacion)}</ns2:idOperacion>
    </ns2:ConsultarProductoByIdOperacionInput>
};

local:func($ProductoRequest)
