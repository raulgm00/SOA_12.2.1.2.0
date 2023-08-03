xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ValidarCondiciones";
(:: import schema at "../XSD/ValidarCondiciones_sp.xsd" ::)

declare namespace con = "http://www.bcie.org/CondicionBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $OutputParameters as element() (:: schema-element(ns1:OutputParameters) ::) external;

declare function local:func($OutputParameters as element() (:: schema-element(ns1:OutputParameters) ::)) as element() (:: schema-element(ns2:ValidarCondicionResponse) ::) {
    <ns2:ValidarCondicionResponse>
        <ns2:ValidacionCondicion>
          <con:idCondicion>{fn:data($OutputParameters/ns1:P_CONDICION)}</con:idCondicion>
          <con:esValidador>{ if (fn:data($OutputParameters/ns1:P_ES_VALIDADOR)= 1 )then true() else false()
          }</con:esValidador>
         <con:estado>{ if (fn:data($OutputParameters/ns1:P_ESTADO)=1) then true() else false ()
         }</con:estado>
        </ns2:ValidacionCondicion>
        {if($OutputParameters/ns1:P_CODE = '0')then
            <ns2:Resultado>
              <res:result>OK</res:result>
              <res:message>Validación realizada exitosamente</res:message>
            </ns2:Resultado>
        else 
            <ns2:Resultado>
              <res:result>ERROR</res:result>
              <res:message>{fn:data($OutputParameters/ns1:P_MENSAJE)}</res:message>
            </ns2:Resultado>
        }
    </ns2:ValidarCondicionResponse>
};

local:func($OutputParameters)
