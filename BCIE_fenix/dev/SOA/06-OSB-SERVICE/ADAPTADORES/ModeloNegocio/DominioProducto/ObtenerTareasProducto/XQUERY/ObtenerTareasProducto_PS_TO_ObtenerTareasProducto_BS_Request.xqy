xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ProductoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioProducto/Producto/V1/Schema/ProductoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ObtenerTareasProducto_BS";
(:: import schema at "../XSD/ObtenerTareasProducto_BS_sp.xsd" ::)

declare variable $ObtenerTareasProductoRequest as element() (:: schema-element(ns1:ObtenerTareasProductoRequest) ::) external;

declare function local:func($ObtenerTareasProductoRequest as element() (:: schema-element(ns1:ObtenerTareasProductoRequest) ::)) as element() (:: schema-element(ns2:InputParameters) ::) {
    <ns2:InputParameters>
        {
            if ($ObtenerTareasProductoRequest/ns1:IdProducto)
            then <ns2:P_IDPRODUCTO>{fn:data($ObtenerTareasProductoRequest/ns1:IdProducto)}</ns2:P_IDPRODUCTO>
            else ()
        }
        {
            if ($ObtenerTareasProductoRequest/ns1:IdProceso)
            then <ns2:P_IDPROCESO>{fn:data($ObtenerTareasProductoRequest/ns1:IdProceso)}</ns2:P_IDPROCESO>
            else ()
        }
    </ns2:InputParameters>
};

local:func($ObtenerTareasProductoRequest)
