xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/OperacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Operacion/V1/Schema/OperacionMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ActualizarMontoOperacion";
(:: import schema at "../XSD/ActualizarMontoOperacion.xsd" ::)

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ResponseActualizarMonto as element() (:: schema-element(ns1:ActualizarMontoOperacionInput) ::) external;

declare function local:func($ResponseActualizarMonto as element() (:: schema-element(ns1:ActualizarMontoOperacionInput) ::)) as element() (:: schema-element(ns2:ActualizarMontoOperacionResponse) ::) {
    <ns2:ActualizarMontoOperacionResponse>
        <ns2:MontoOperacion>
            <ope:idOperacion>{fn:data($ResponseActualizarMonto/ns1:P_ID_OPERACION)}</ope:idOperacion>
            <ope:IdTcaTipoMonto>{fn:data($ResponseActualizarMonto/ns1:P_ID_TCA_TIPO_MONTO)}</ope:IdTcaTipoMonto></ns2:MontoOperacion>
        <ns2:Resultado>
            <res:result>Ok</res:result>
            <res:message>Actualización Realizada Exitosamente</res:message>

        </ns2:Resultado>
    </ns2:ActualizarMontoOperacionResponse>
};

local:func($ResponseActualizarMonto)
