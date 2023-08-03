xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace m="http://org/bcie/ws/middle/FLEXCUBE.wsdl";

declare namespace ns2="http://org/bcie/ws/middle/FLEXCUBE.wsdl/types/";
(:: import schema at "../../WSDL/FLEXCUBE_WSDL.wsdl" ::)
declare namespace ns1="http://www.bcie.org/ClienteMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioCliente/Cliente/V1/Schema/ClienteMO.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ConsultarClienteFLEXCUBERequest as element() (:: schema-element(ns1:ConsultarClienteFLEXCUBERequest) ::) external;
declare variable $consultarClienteResponse as element() (:: element(*, ns2:ContratoTypeUser) ::) external;

declare function local:func($ConsultarClienteFLEXCUBERequest as element() (:: schema-element(ns1:ConsultarClienteFLEXCUBERequest) ::), 
                            $consultarClienteResponse as element(m:consultarClienteResponse) (:: element(m:consultarClienteResponse) ::)) 
                            as element() (:: schema-element(ns1:ConsultarClienteFLEXCUBEResponse) ::) {
    <ns1:ConsultarClienteFLEXCUBEResponse>
        <ns1:Cliente>
            <cli:idCliente>{fn:data($ConsultarClienteFLEXCUBERequest/cli:idCliente)}</cli:idCliente>
            {
                if ($ConsultarClienteFLEXCUBERequest/cli:idFacturador)
                then <cli:idFacturador>{fn:data($ConsultarClienteFLEXCUBERequest/cli:idFacturador)}</cli:idFacturador>
                else ()
            }
            <cli:razonSocial>{fn:data($consultarClienteResponse/razonSocial_out)}</cli:razonSocial>
            <cli:abreviatura>{fn:data($consultarClienteResponse/abreviatura_out)}</cli:abreviatura>
            <cli:tipoIdentificacion>{fn:data($consultarClienteResponse/tipoIdentificacion_out)}</cli:tipoIdentificacion>
            <cli:numeroIdentificacion>{fn:data($consultarClienteResponse/identificacion_out)}</cli:numeroIdentificacion>
            <cli:direccion>{fn:data($consultarClienteResponse/direccion_out)}</cli:direccion>
            <cli:telefono>{fn:data($consultarClienteResponse/telefono_out)}</cli:telefono>
            <cli:fax>{fn:data($consultarClienteResponse/fax_out)}</cli:fax>
            <cli:email></cli:email>
            <cli:ciudad>{fn:data($consultarClienteResponse/ciudad_out)}</cli:ciudad>
            <cli:grupoEmpresarial>{fn:data($consultarClienteResponse/grupoEmpresarial_out)}</cli:grupoEmpresarial>
            <cli:ejecutivo>{fn:data($consultarClienteResponse/ejecutivo_out)}</cli:ejecutivo>
            <cli:mora>
                <cli:dias>{fn:data($consultarClienteResponse/diasMora_out)}</cli:dias>
                <cli:monto>{fn:data($consultarClienteResponse/montoMora_out)}</cli:monto>
            </cli:mora>
        </ns1:Cliente>
        <ns1:Resultado>
           <res:result>{
           if (fn:string-length(fn:data($consultarClienteResponse/mensaje_out))=0)then ('OK')
           else ('ERROR')
           }</res:result>
           <res:message>{
           if (fn:string-length(fn:data($consultarClienteResponse/mensaje_out))> 0)then
           (fn:data($consultarClienteResponse/mensaje_out))
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
