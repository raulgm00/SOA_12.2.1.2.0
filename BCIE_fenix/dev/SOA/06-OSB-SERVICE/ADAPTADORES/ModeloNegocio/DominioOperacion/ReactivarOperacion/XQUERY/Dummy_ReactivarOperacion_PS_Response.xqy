xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/OperacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Operacion/V1/Schema/OperacionMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";


declare function local:func() as element() (:: schema-element(ns1:ReactivarOperacionResponse) ::) {
    <ns1:ReactivarOperacionResponse>
        <ns1:Resultado>
            <res:result>OK</res:result>
            <res:message>Dummy reactivar operación ejecutado satisfactoriamente</res:message>
        </ns1:Resultado>
    </ns1:ReactivarOperacionResponse>
};

local:func()
