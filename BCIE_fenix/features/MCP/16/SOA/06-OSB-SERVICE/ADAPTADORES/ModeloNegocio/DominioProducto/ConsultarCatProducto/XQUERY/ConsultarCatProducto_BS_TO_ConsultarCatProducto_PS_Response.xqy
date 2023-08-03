xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ProductoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioProducto/Producto/V1/Schema/ProductoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarCatProducto";
(:: import schema at "../XSD/ConsultarCatProducto.xsd" ::)

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare variable $ConsultarCatProductOutput as element() (:: schema-element(ns1:ConsultarCatProductoOutputCollection) ::) external;

declare function local:func($ConsultarCatProductOutput as element() (:: schema-element(ns1:ConsultarCatProductoOutputCollection) ::)) as element() (:: schema-element(ns2:ConsultarCatProductoResponse) ::) {
    <ns2:ConsultarCatProductoResponse>
        {
            for $ConsultarCatProductoOutput in $ConsultarCatProductOutput/ns1:ConsultarCatProductoOutput
            return 
            <ns2:Producto>
                <pro:idProducto>{fn:data($ConsultarCatProductoOutput/ns1:ID)}</pro:idProducto>
                <pro:descripcion>{fn:data($ConsultarCatProductoOutput/ns1:DESCRIPCION)}</pro:descripcion></ns2:Producto>
        }
    </ns2:ConsultarCatProductoResponse>
};

local:func($ConsultarCatProductOutput)