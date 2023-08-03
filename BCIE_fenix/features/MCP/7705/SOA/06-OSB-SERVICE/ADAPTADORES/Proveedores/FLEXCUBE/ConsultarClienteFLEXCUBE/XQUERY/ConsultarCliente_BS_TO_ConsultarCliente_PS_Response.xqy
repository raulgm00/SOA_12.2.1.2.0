xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ClienteMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioCliente/Cliente/V1/Schema/ClienteMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ConsultarCliente";
(:: import schema at "../XSD/ConsultarCliente_sp.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ConsultarClienteFLEXCUBERequest as element() (:: schema-element(ns1:ConsultarClienteFLEXCUBERequest) ::) external;
declare variable $consultarClienteResponse as element() (:: schema-element(ns2:OutputParameters) ::) external;

declare function local:func($ConsultarClienteFLEXCUBERequest as element() (:: schema-element(ns1:ConsultarClienteFLEXCUBERequest) ::), 
                            $consultarClienteResponse as element() (:: schema-element(ns2:OutputParameters) ::)) 
                            as element() (:: schema-element(ns1:ConsultarClienteFLEXCUBEResponse) ::) {
    <ns1:ConsultarClienteFLEXCUBEResponse>
        <ns1:Cliente>
            <cli:idCliente>{fn:data($ConsultarClienteFLEXCUBERequest/cli:idCliente)}</cli:idCliente>
            {
                if ($ConsultarClienteFLEXCUBERequest/cli:idFacturador)
                then <cli:idFacturador>{fn:data($ConsultarClienteFLEXCUBERequest/cli:idFacturador)}</cli:idFacturador>
                else ()
            }
            <cli:razonSocial>{fn:data($consultarClienteResponse/ns2:RAZON_SOCIAL)}</cli:razonSocial>
            <cli:abreviatura>{fn:data($consultarClienteResponse/ns2:ABREVIATURA)}</cli:abreviatura>
            <cli:tipoIdentificacion>{fn:data($consultarClienteResponse/ns2:TIPO_IDENTIFICACION)}</cli:tipoIdentificacion>
            <cli:numeroIdentificacion>{fn:data($consultarClienteResponse/ns2:IDENTIFICACION)}</cli:numeroIdentificacion>
            <cli:direccion>{fn:data($consultarClienteResponse/ns2:DIRECCION)}</cli:direccion>
            <cli:telefono>{fn:data($consultarClienteResponse/ns2:TELEFONO)}</cli:telefono>
            <cli:fax>{fn:data($consultarClienteResponse/ns2:FAX)}</cli:fax>
            <cli:email></cli:email>
            <cli:ciudad>{fn:data($consultarClienteResponse/ns2:CIUDAD)}</cli:ciudad>
            <cli:grupoEmpresarial>{fn:data($consultarClienteResponse/ns2:GRUPO_EMPRESARIAL)}</cli:grupoEmpresarial>
            <cli:ejecutivo>{fn:data($consultarClienteResponse/ns2:EJECUTIVO)}</cli:ejecutivo>
            <cli:mora>
                <cli:dias>{fn:data($consultarClienteResponse/ns2:DIAS_MORA)}</cli:dias>
                <cli:monto>{fn:data($consultarClienteResponse/ns2:MONTO_MORA)}</cli:monto>
            </cli:mora>
        </ns1:Cliente>
        <ns1:Resultado>
           <res:result>{
           if (fn:string-length(fn:data($consultarClienteResponse/ns2:MENSAJE))=0)then ('OK')
           else ('ERROR')
           }</res:result>
           <res:message>{
           if (fn:string-length(fn:data($consultarClienteResponse/ns2:MENSAJE))> 0)then
           (fn:data($consultarClienteResponse/ns2:MENSAJE))
           else ('Operacion Exitosa')
           }</res:message>       
            <res:error>
                <err:errorCode></err:errorCode>
                <err:errorDescription></err:errorDescription>
                <err:errorType></err:errorType>
            </res:error>
        </ns1:Resultado>
    </ns1:ConsultarClienteFLEXCUBEResponse>
};

local:func($ConsultarClienteFLEXCUBERequest, $consultarClienteResponse)