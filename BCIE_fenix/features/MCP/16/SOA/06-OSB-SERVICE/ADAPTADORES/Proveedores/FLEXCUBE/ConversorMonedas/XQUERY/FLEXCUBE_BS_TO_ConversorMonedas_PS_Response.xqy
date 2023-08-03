xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)
declare namespace m="http://org/bcie/ws/middle/FLEXCUBE.wsdl";
declare namespace ns2="http://org/bcie/ws/middle/FLEXCUBE.wsdl/types/";
declare namespace ns1="http://www.bcie.org/ConversorMonedasMO";

(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/ConversorMonedas/V1/Schema/ConversorMonedasMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";
declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $FLEXCUBE_conversionMonedas as element(m:convierteMonedaResponse) external;

declare function local:func($FLEXCUBE_conversionMonedas as element(m:convierteMonedaResponse)) as element() (:: schema-element(ns1:ConversorMonedasResponse) ::) {
    <ns1:ConversorMonedasResponse>
        <ns1:montoConvertido>{fn:data($FLEXCUBE_conversionMonedas/montoConvertio_out)}</ns1:montoConvertido>
    <ns1:Resultado>
       <res:result>{
       if(fn:string-length(fn:data($FLEXCUBE_conversionMonedas/mensaje_out))=0) then ("OK")
            else("ERROR")
            }
       </res:result>
       <res:message>{
            if (fn:string-length(fn:data($FLEXCUBE_conversionMonedas/mensaje_out))=0) then ("Operación exitosa")
            else (fn:data($FLEXCUBE_conversionMonedas/mensaje_out))
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