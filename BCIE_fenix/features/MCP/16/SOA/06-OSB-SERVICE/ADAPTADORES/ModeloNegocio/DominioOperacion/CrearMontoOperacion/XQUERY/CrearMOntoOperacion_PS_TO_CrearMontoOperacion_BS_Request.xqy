xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/OperacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Operacion/V1/Schema/OperacionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/top/CrearMontoOperacion";
(:: import schema at "../XSD/CrearMontoOperacion_table.xsd" ::)

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare variable $requestMontoOperacion as element() (:: schema-element(ns1:CrearMontoOperacionRequest) ::) external;

declare function local:func($requestMontoOperacion as element() (:: schema-element(ns1:CrearMontoOperacionRequest) ::)) as element() (:: schema-element(ns2:MontoOperacionCollection) ::) {
    <ns2:MontoOperacionCollection>
        <ns2:MontoOperacion>
            {
                if ($requestMontoOperacion/ns1:MontoOperacion/ope:idOperacion)
                then <ns2:idOperacion>{fn:data($requestMontoOperacion/ns1:MontoOperacion/ope:idOperacion)}</ns2:idOperacion>
                else ()
            }
            {
                if ($requestMontoOperacion/ns1:MontoOperacion/ope:IdTcaTipoMonto)
                then <ns2:idTcaTipoMonto>{fn:data($requestMontoOperacion/ns1:MontoOperacion/ope:IdTcaTipoMonto)}</ns2:idTcaTipoMonto>
                else ()
            }
            <ns2:monto>{if (xs:string($requestMontoOperacion/ns1:MontoOperacion/ope:monto)= '') then 0
            else fn:data($requestMontoOperacion/ns1:MontoOperacion/ope:monto)
            }</ns2:monto>
            <ns2:fechaRegistro>{fn:current-dateTime()}</ns2:fechaRegistro>
            <ns2:banEstatus>1</ns2:banEstatus>
			{
                if ($requestMontoOperacion/ns1:MontoOperacion/ope:idTcaTipoMonedaMontoOperacion)
                then <ns2:idTcaTipoMoneda>{fn:data($requestMontoOperacion/ns1:MontoOperacion/ope:idTcaTipoMonedaMontoOperacion)}</ns2:idTcaTipoMoneda>
                else ()
            }
        </ns2:MontoOperacion>
    </ns2:MontoOperacionCollection>
};

local:func($requestMontoOperacion)
