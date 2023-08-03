xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ValidarFechaVencimiento_DB";
(:: import schema at "../XSD/ValidarFechaVencimiento_DB_sp.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $OutputParameters as element() (:: schema-element(ns1:OutputParameters) ::) external;

declare function local:func($OutputParameters as element() (:: schema-element(ns1:OutputParameters) ::)) as element() (:: schema-element(ns2:ValidarFechaVencimientoResponse) ::) {
    <ns2:ValidarFechaVencimientoResponse>        
        <ns2:resultado>
            <res:result>{if(fn:data($OutputParameters/ns1:P_CODIGO_RES/text()) = '1') then 'OK' else 'ERROR'}</res:result>
            <res:message>{
              if (fn:data($OutputParameters/ns1:P_CODIGO_RES/text()) = '0') 
              then 'OK'
              else fn:data($OutputParameters/ns1:P_MENSAJE/text())
              }
          </res:message>
          <res:error>
                <err:errorCode>{fn:data($OutputParameters/ns1:P_CODIGO_RES)}</err:errorCode>
                <err:errorDescription>{fn:data($OutputParameters/ns1:P_TIPO_RES)}</err:errorDescription>
                <err:errorType>{fn:data($OutputParameters/ns1:P_MENSAJE)}</err:errorType>
            </res:error>
        </ns2:resultado>
    </ns2:ValidarFechaVencimientoResponse>
};

local:func($OutputParameters)