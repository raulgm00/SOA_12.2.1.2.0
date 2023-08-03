xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/OperacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Operacion/V1/Schema/OperacionMO.xsd" ::)


declare function local:func() as element() (:: schema-element(ns1:EliminarMontoOperacionResponse) ::) {
    <ns1:EliminarMontoOperacionResponse>
         <ns1:Resultado>
            <ns1:result>OK</ns1:result>
            <ns1:message>La eliminación se ha realizado correctamente</ns1:message>
        </ns1:Resultado>
      </ns1:EliminarMontoOperacionResponse>
};

local:func()
