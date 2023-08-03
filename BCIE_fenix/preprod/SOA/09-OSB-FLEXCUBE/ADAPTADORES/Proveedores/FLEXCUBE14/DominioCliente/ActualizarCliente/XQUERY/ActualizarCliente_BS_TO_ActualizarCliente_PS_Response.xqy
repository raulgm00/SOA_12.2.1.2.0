xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/FLEXCUBE14/ClienteMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/Flexcube14/DominioCliente/Cliente/V1/Schema/ClienteMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/actualizarCliente_DB";
(:: import schema at "../XSD/actualizarCliente_DB_sp.xsd" ::)

declare namespace res = "http://www.bcie.org/FLEXCUBE14/ResultBO";

declare namespace err = "http://www.bcie.org/FLEXCUBE14/ErrorBO";

declare variable $ClienteResponse as element() (:: schema-element(ns2:OutputParameters) ::) external;

declare function local:func($ClienteResponse as element() (:: schema-element(ns2:OutputParameters) ::)) as element() (:: schema-element(ns1:ActualizarClienteResponse) ::) {
    <ns2:ActualizarClienteResponse>
    {
        if($ClienteResponse/ns2:CODIGO_RESULTADO/text() = "0")
        then
        <ns1:Response>
          <ns1:Resultado>
            <res:result>OK</res:result>
            <res:message>{'La actualizacion se ha realizado correctamente'}</res:message>
          </ns1:Resultado>
        </ns1:Response>
        else if($ClienteResponse/ns2:CODIGO_RESULTADO/text() eq "1" or  $ClienteResponse/ns2:CODIGO_RESULTADO/text() eq "2")
        then
        <ns1:Response>
          <ns1:Codigo_Cliente/>
          <ns1:Resultado>
            <res:result>ERROR</res:result>
                <res:message>{fn:data($ClienteResponse/ns2:MENSAJE)}</res:message>
                <res:error>
                    <err:errorCode>{fn:data($ClienteResponse/ns2:CODIGO_RESULTADO)}</err:errorCode>
                    <err:errorDescription>{fn:data($ClienteResponse/ns2:MENSAJE)}</err:errorDescription>
                    <err:errorType>{fn:data($ClienteResponse/ns2:TIPO_RESULTADO)}</err:errorType>
                </res:error>
          </ns1:Resultado>
        </ns1:Response>
        else()
      }
       </ns2:ActualizarClienteResponse>
};

local:func($ClienteResponse)