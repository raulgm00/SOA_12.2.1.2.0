xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DeclaracionJuradaMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/DeclaracionJurada/V1/Schema/DeclaracionJuradaMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/top/CrearDeclaracionOperacion";
(:: import schema at "../XSD/CrearDeclaracionOperacion_table.xsd" ::)

declare variable $CrearDeclaracionOperacionRequest as element() (:: schema-element(ns1:CrearDeclaracionOperacionRequest) ::) external;

declare function local:func($CrearDeclaracionOperacionRequest as element() (:: schema-element(ns1:CrearDeclaracionOperacionRequest) ::)) as element() (:: schema-element(ns2:TreDeclaracionOperacionCollection) ::) {
    <ns2:TreDeclaracionOperacionCollection>
        <ns2:TreDeclaracionOperacion>
            <ns2:id></ns2:id>
            {
                if ($CrearDeclaracionOperacionRequest/ns1:idOperacion)
                then <ns2:idOperacion>{fn:data($CrearDeclaracionOperacionRequest/ns1:idOperacion)}</ns2:idOperacion>
                else ()
            }
            {
                if ($CrearDeclaracionOperacionRequest/ns1:idDeclaracion)
                then <ns2:idDeclaracion>{fn:data($CrearDeclaracionOperacionRequest/ns1:idDeclaracion)}</ns2:idDeclaracion>
                else ()
            }
            <ns2:esVigente>1</ns2:esVigente>
        </ns2:TreDeclaracionOperacion>
    </ns2:TreDeclaracionOperacionCollection>
};

local:func($CrearDeclaracionOperacionRequest)
