xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ProductoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioProducto/Producto/V1/Schema/ProductoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarCatProducto";
(:: import schema at "../XSD/ConsultarCatProducto.xsd" ::)

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare variable $ConsultarCatProductoRequest as element() (:: schema-element(ns2:ConsultarCatProductoRequest) ::) external;

declare function local:func($ConsultarCatProductoRequest as element() (:: schema-element(ns2:ConsultarCatProductoRequest) ::)) as element() (:: schema-element(ns1:ConsultarCatProductoInput) ::) {
    <ns1:ConsultarCatProductoInput>
        <ns1:RequiereLAFT>{
                if (string($ConsultarCatProductoRequest/ns2:Producto/pro:reglas/pro:requiereLaft)='')
                then 0
                else (if(fn:data($ConsultarCatProductoRequest/ns2:Producto/pro:reglas/pro:requiereLaft)=true())
                then 1
                else 0)
            }</ns1:RequiereLAFT>
      
      <ns1:OficRealizaAnalisisLAFT>{
                if (string($ConsultarCatProductoRequest/ns2:Producto/pro:reglas/pro:OFICrealizaAnalisisLAFT)='')
                then 0
                else (if(fn:data($ConsultarCatProductoRequest/ns2:Producto/pro:reglas/pro:OFICrealizaAnalisisLAFT)=true())
                then 1
                else 0)
            }</ns1:OficRealizaAnalisisLAFT>
   
    </ns1:ConsultarCatProductoInput>
};

local:func($ConsultarCatProductoRequest)