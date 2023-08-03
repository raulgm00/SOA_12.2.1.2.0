xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/sp/UpsertTransaccionCondicion";
(:: import schema at "../XSD/UpsertTransaccionCondicion_sp.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $OutputParameters as element() (:: schema-element(ns1:OutputParameters) ::) external;

declare function local:func($OutputParameters as element() (:: schema-element(ns1:OutputParameters) ::)) as element() (:: schema-element(ns2:UpsertTransaccionCondicionResponse) ::) {
    <ns2:UpsertTransaccionCondicionResponse>
        <ns2:idAgrupador>{fn:data($OutputParameters/ns1:P_AGRUPADOR)}</ns2:idAgrupador>
        {if($OutputParameters/ns1:P_CODIGO = '0')then
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>Operacion realizada exitosamente</res:message>
        </ns2:Resultado>
        else
          <ns2:Resultado>
            <res:result>ERROR</res:result>
            <res:message>{fn:data($OutputParameters/ns1:P_MENSAJE)}</res:message>
        </ns2:Resultado>
          }  
    </ns2:UpsertTransaccionCondicionResponse>
};

local:func($OutputParameters)
