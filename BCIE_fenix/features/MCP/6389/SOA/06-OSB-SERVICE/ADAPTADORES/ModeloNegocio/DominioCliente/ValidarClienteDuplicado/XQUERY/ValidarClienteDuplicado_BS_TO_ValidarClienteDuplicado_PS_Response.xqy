xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ClienteMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioCliente/Cliente/V1/Schema/ClienteMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ValidarClienteDuplicado";
(:: import schema at "../XSD/ValidarClienteDuplicado_sp.xsd" ::)

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $OutputParameters as element() (:: schema-element(ns1:OutputParameters) ::) external;

declare function local:func($OutputParameters as element() (:: schema-element(ns1:OutputParameters) ::)) as element() (:: schema-element(ns2:ValidarClienteDuplicadoResponse) ::) {
    <ns2:ValidarClienteDuplicadoResponse>
        {
            for $Row in $OutputParameters/ns1:RECORDSET/ns1:Row
            return 
            <ns2:Cliente>
                <cli:razonSocial>{fn:data($Row/ns1:Column[@name="RAZON_SOCIAL"])}</cli:razonSocial>
                <cli:abreviatura>{fn:data($Row/ns1:Column[@name="ABREVIATURA"])}</cli:abreviatura></ns2:Cliente>
        }
        <ns2:Resultado>
        { if (
            count($OutputParameters/ns1:RECORDSET/ns1:Row/ns1:Column[@name="VALOR"])=0 or fn:data($OutputParameters/ns1:RECORDSET/ns1:Row/ns1:Column[@name="VALOR"])='RAZONSOCIAL')  then
            <res:result>OK</res:result>
            else
            <res:result>ERROR</res:result>
            }
             {
            if (
            fn:data($OutputParameters/ns1:RECORDSET/ns1:Row/ns1:Column[@name="VALOR"])='ABREVIATURA') then
            <res:message>La abreviatura ya se encuentra almacenada</res:message>
            else 
            if (
            fn:data($OutputParameters/ns1:RECORDSET/ns1:Row/ns1:Column[@name="VALOR"])='RAZONSOCIAL') then
            <res:message>Se han encontrado las siguientes coincidencias para el cliente que desea agregar</res:message>
            
             else  <res:message></res:message>
            
             }
            
           
            <res:error>
                <err:errorCode></err:errorCode>
                <err:errorDescription></err:errorDescription>
                <err:errorType></err:errorType>
            </res:error>
        </ns2:Resultado>
    </ns2:ValidarClienteDuplicadoResponse>
};

local:func($OutputParameters)
