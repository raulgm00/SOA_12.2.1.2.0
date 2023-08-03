xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CrearBitacoraClienteMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/CrearBitacoraCliente/V1/Schema/CrearBitacoraClienteMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/top/CrearBitacoraCliente";
(:: import schema at "../XSD/CrearBitacoraCliente_table.xsd" ::)

declare namespace cre = "http://www.bcie.org/CrearBitacoraClienteBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare variable $CrearBitacoraClienteRequest as element() (:: schema-element(ns1:CrearBitacoraClienteRequest) ::) external;

declare function local:func($CrearBitacoraClienteRequest as element() (:: schema-element(ns1:CrearBitacoraClienteRequest) ::)) as element() (:: schema-element(ns2:TbiClienteCollection) ::) {
    <ns2:TbiClienteCollection>
        <ns2:TbiCliente>
            <ns2:id></ns2:id>
            <ns2:idCliente>{fn:data($CrearBitacoraClienteRequest/ns1:BitacoraCliente/cre:Cliente/cli:idCliente)}</ns2:idCliente>
            {
                if ($CrearBitacoraClienteRequest/ns1:BitacoraCliente/cre:Cliente/cli:idFacturador)
                then <ns2:idFlexcube>{fn:data($CrearBitacoraClienteRequest/ns1:BitacoraCliente/cre:Cliente/cli:idFacturador)}</ns2:idFlexcube>
                else ()
            }
            <ns2:tipoAccion>{fn:data($CrearBitacoraClienteRequest/ns1:BitacoraCliente/cre:tipoAccion)}</ns2:tipoAccion>
            <ns2:loginSolicitante>{fn:data($CrearBitacoraClienteRequest/ns1:BitacoraCliente/cre:Cliente/cli:ejecutivo)}</ns2:loginSolicitante>
            <ns2:fechaRegistro>{fn:current-date()}</ns2:fechaRegistro>
            {
                if ($CrearBitacoraClienteRequest/ns1:BitacoraCliente/cre:Cliente/cli:revisadoAprobacion)
                then <ns2:loginAprobador>{fn:data($CrearBitacoraClienteRequest/ns1:BitacoraCliente/cre:Cliente/cli:revisadoAprobacion)}</ns2:loginAprobador>
                else ()
            }
            {
            if ($CrearBitacoraClienteRequest/ns1:BitacoraCliente/cre:resolucion)
            then
            <ns2:resolucion>{fn:data($CrearBitacoraClienteRequest/ns1:BitacoraCliente/cre:resolucion)}</ns2:resolucion>
            else()
            }
            {if ($CrearBitacoraClienteRequest/ns1:BitacoraCliente/cre:fechaResolucion)
            then <ns2:fechaResolucion>{fn:data($CrearBitacoraClienteRequest/ns1:BitacoraCliente/cre:fechaResolucion)}</ns2:fechaResolucion>
            else()}
        </ns2:TbiCliente>
    </ns2:TbiClienteCollection>
};

local:func($CrearBitacoraClienteRequest)
