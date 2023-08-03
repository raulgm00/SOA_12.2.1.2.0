xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)
declare namespace ns2="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)

declare namespace con = "http://www.bcie.org/CondicionBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare variable $inConsultarDesembolso as element() (:: schema-element(ns1:ConsultarTransaccionCondicionResponse) ::) external;
declare variable $numeroContrato as xs:string external;

declare function local:func($inConsultarDesembolso as element() (:: schema-element(ns1:ConsultarTransaccionCondicionResponse) ::)) as element() (:: schema-element(ns2:ConsultarDesembolsosByIdRequest) ::) {
    <ns2:ConsultarDesembolsosByIdRequest>
      {if(fn:string-length(xs:string(fn:data($inConsultarDesembolso/ns1:TransaccionCondicion[1]/con:ContratoDesembolso/des:idDesembolso))) > 0) then 
        <ns2:idContratoDesembolso>{fn:data($inConsultarDesembolso/ns1:TransaccionCondicion[1]/con:ContratoDesembolso/des:idDesembolso)}</ns2:idContratoDesembolso>
       else(
        <ns2:idContratoDesembolso>{fn:data($numeroContrato)}</ns2:idContratoDesembolso> 
        )      
      }
    
        
    </ns2:ConsultarDesembolsosByIdRequest>
};

local:func($inConsultarDesembolso)
