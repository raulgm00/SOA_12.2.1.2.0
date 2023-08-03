xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ProductoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioProducto/Producto/V1/Schema/ProductoMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare function local:func() as element() (:: schema-element(ns1:ConsultarProductoByIdOperacionResponse) ::) {
    <ns1:ConsultarProductoByIdOperacionResponse>
        <ns1:Resultado>
            <res:error>
                <err:errorCode>ERROR</err:errorCode>
                <err:errorDescription>No existe registro en la Base de Datos</err:errorDescription>
                <err:errorType></err:errorType>
            </res:error>
        </ns1:Resultado>
    </ns1:ConsultarProductoByIdOperacionResponse>
};

local:func()
