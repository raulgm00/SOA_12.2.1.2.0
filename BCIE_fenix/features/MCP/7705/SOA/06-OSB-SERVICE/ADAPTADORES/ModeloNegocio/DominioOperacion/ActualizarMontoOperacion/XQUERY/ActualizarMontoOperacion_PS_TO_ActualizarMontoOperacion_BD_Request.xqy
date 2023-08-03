xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/OperacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Operacion/V1/Schema/OperacionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ActualizarMontoOperacion";
(:: import schema at "../XSD/ActualizarMontoOperacion.xsd" ::)

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare variable $RequestActualizarMonto as element() (:: schema-element(ns1:ActualizarMontoOperacionRequest) ::) external;

declare function local:func($RequestActualizarMonto as element() (:: schema-element(ns1:ActualizarMontoOperacionRequest) ::)) as element() (:: schema-element(ns2:ActualizarMontoOperacionInput) ::) {
    <ns2:ActualizarMontoOperacionInput>
        <ns2:P_MONTO>{fn:data($RequestActualizarMonto/ns1:MontoOperacion/ope:monto)}</ns2:P_MONTO>
        {
            if ($RequestActualizarMonto/ns1:MontoOperacion/ope:idTcaTipoMonedaMontoOperacion)
            then <ns2:P_MONEDA>{fn:data($RequestActualizarMonto/ns1:MontoOperacion/ope:idTcaTipoMonedaMontoOperacion)}</ns2:P_MONEDA>
            else ()
        }
        <ns2:P_ID_OPERACION>{fn:data($RequestActualizarMonto/ns1:MontoOperacion/ope:idOperacion)}</ns2:P_ID_OPERACION>
        <ns2:P_ID_TCA_TIPO_MONTO>{fn:data($RequestActualizarMonto/ns1:MontoOperacion/ope:IdTcaTipoMonto)}</ns2:P_ID_TCA_TIPO_MONTO>
    </ns2:ActualizarMontoOperacionInput>
};

local:func($RequestActualizarMonto)
