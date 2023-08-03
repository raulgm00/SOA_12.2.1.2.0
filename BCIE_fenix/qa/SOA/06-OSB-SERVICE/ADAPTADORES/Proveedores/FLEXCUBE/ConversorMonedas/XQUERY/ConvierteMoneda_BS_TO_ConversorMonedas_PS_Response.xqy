xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ConversorMonedasMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/ConversorMonedas/V1/Schema/ConversorMonedasMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ConvierteMoneda";
(:: import schema at "../XSD/ConvierteMoneda_sp.xsd" ::)


declare namespace res = "http://www.bcie.org/ResultBO";
declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $FLEXCUBE_conversionMonedas as element() (:: schema-element(ns2:OutputParameters) ::) external;

declare function local:func($FLEXCUBE_conversionMonedas as element() (:: schema-element(ns2:OutputParameters) ::)) as element() (:: schema-element(ns1:ConversorMonedasResponse) ::) {
    <ns1:ConversorMonedasResponse>
        <ns1:montoConvertido>{fn:data($FLEXCUBE_conversionMonedas/ns2:MONTO_CONVERTIO)}</ns1:montoConvertido>
    <ns1:Resultado>
       <res:result>{
       if(fn:string-length(fn:data($FLEXCUBE_conversionMonedas/ns2:MENSAJE))=0) then ("OK")
            else("ERROR")
            }
       </res:result>
       <res:message>{
            if (fn:string-length(fn:data($FLEXCUBE_conversionMonedas/ns2:MENSAJE))=0) then ("Operación exitosa")
            else (fn:data($FLEXCUBE_conversionMonedas/ns2:MENSAJE))
            } </res:message>
       <res:error>
           <err:errorCode></err:errorCode>
           <err:errorDescription></err:errorDescription>
           <err:errorType></err:errorType>
       </res:error>
   </ns1:Resultado>
       
       
     
    </ns1:ConversorMonedasResponse>
};

local:func($FLEXCUBE_conversionMonedas)