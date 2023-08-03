xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/AdquisicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Adquisicion/V1/Schema/AdquisicionMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/sp/CrearNoObjecionPGA";
(:: import schema at "../XSD/CrearNoObjecionPGA_sp.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $response as element() (:: schema-element(ns1:OutputParameters) ::) external;

declare function local:func($response as element() (:: schema-element(ns1:OutputParameters) ::)) as element() (:: schema-element(ns2:CrearNoObjecionPGA_Response) ::) {
    <ns2:CrearNoObjecionPGA_Response>
        <ns2:PNID_NOOBJECION>{fn:data($response/ns1:PNID_NOOBJECION)}</ns2:PNID_NOOBJECION>
        <ns2:PNID_ADQUISICION>{fn:data($response/ns1:PNID_ADQUISICION)}</ns2:PNID_ADQUISICION> 
        <ns2:resultado>
            <res:result>{
            if(string($response/ns1:P_CODIGO) = '0')then 'OK'
            else 'OK'
            }</res:result>
            <res:message>{fn:data($response/ns1:P_MENSAJE)}</res:message>
            <res:error>
                <err:errorCode>{fn:data($response/ns1:P_CODIGO)}</err:errorCode>
                <err:errorDescription>{fn:data($response/ns1:P_MENSAJE)}</err:errorDescription>
                <err:errorType>{fn:data($response/ns1:P_ESTADO)}</err:errorType>
            </res:error>
        </ns2:resultado>
    </ns2:CrearNoObjecionPGA_Response>
};

local:func($response)