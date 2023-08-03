xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/sp/dbConsultarCondiciones";
(:: import schema at "../XSD/dbConsultarCondiciones_sp.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $OutputParameters as element() (:: schema-element(ns1:OutputParameters) ::) external;

declare function local:func($OutputParameters as element() (:: schema-element(ns1:OutputParameters) ::)) as element() (:: schema-element(ns2:ConsultarCondicionesResponse) ::) {
 let $condiciones := $OutputParameters/ns1:PL_CONDICIONES/ns1:PL_CONDICIONES_ITEM
 let $listaCondiciones :=  for $condicion in $condiciones 
    return <PL_CONDICIONES_ITEM>{data($condicion)}</PL_CONDICIONES_ITEM>

 return
    <ns2:ConsultarCondicionesResponse> 
        <ns2:PL_CONDICIONES>{$listaCondiciones}</ns2:PL_CONDICIONES> 
        <ns2:PVCODIGORESPUESTA>{fn:data($OutputParameters/ns1:PVCODIGORESPUESTA)}</ns2:PVCODIGORESPUESTA>
        <ns2:PVMENSAJERESPUESTA>{fn:data($OutputParameters/ns1:PVMENSAJERESPUESTA)}</ns2:PVMENSAJERESPUESTA>
     </ns2:ConsultarCondicionesResponse>
};

local:func($OutputParameters)
