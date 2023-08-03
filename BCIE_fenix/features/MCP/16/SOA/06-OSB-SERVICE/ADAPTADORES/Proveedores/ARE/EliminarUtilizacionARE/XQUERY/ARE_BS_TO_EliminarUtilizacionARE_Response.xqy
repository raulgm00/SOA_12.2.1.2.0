xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace m="http://org/bcie/ws/middle/ARE/ARE.wsdl";
declare namespace ns2="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $eliminarUtilizacionResponse as element() (:: element(m:eliminarUtilizacionResponse) ::) external;

declare function local:func($eliminarUtilizacionResponse as element(m:eliminarUtilizacionResponse) (:: element(m:eliminarUtilizacionResponse) ::)) as element() (:: schema-element(ns2:EliminarUtilizacionAREResponse) ::) {
    <ns2:EliminarUtilizacionAREResponse>
         <ns2:Resultado>
            {
                if (fn:data($eliminarUtilizacionResponse/codigoResultado_out)= 0) then
            <res:result>OK</res:result>
             else  <res:result>ERROR</res:result>
        }
         {
                if (fn:string-length(string(fn:data($eliminarUtilizacionResponse/mensajeError_out)))= 0) then
            <res:message>Eliminacion Exitosa</res:message>
             else  <res:message>{fn:data($eliminarUtilizacionResponse/mensajeError_out)}</res:message>
        }
        </ns2:Resultado> 
    </ns2:EliminarUtilizacionAREResponse>
};

local:func($eliminarUtilizacionResponse)
