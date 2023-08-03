xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ContratoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Contrato/V1/Schema/ContratoMO.xsd" ::)
declare namespace ns1="http://www.bcie.org/OperacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Operacion/V1/Schema/OperacionMO.xsd" ::)

declare namespace con = "http://www.bcie.org/ContratoBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare variable $ConsultarOperacionResponse as element() (:: schema-element(ns1:ConsultarOperacionResponse) ::) external;
declare variable $counter as xs:integer external;

declare function local:func($ConsultarOperacionResponse as element() (:: schema-element(ns1:ConsultarOperacionResponse) ::), 
                            $counter as xs:integer) 
                            as element() (:: schema-element(ns2:ConsultarSaldoContratoRequest) ::) {
    <ns2:ConsultarSaldoContratoRequest>
        <ns2:idContrato>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:contrato[$counter]/con:idContrato)}</ns2:idContrato>
    </ns2:ConsultarSaldoContratoRequest>
};

local:func($ConsultarOperacionResponse, $counter)
