xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace m="http://org/bcie/ws/middle/ARE/ARE.wsdl";
declare namespace ns2="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $registrarUtilizacionResponse as element() (:: element(m:registrarUtilizacionResponse) ::) external;

declare function local:func($registrarUtilizacionResponse as element(m:registrarUtilizacionResponse) (:: element(m:registrarUtilizacionResponse) ::)) as element() (:: schema-element(ns2:RegistrarUtilizacionAREResponse) ::) {
    <ns2:RegistrarUtilizacionAREResponse>
        <ns2:Resultado>
          {
          if (fn:data($registrarUtilizacionResponse/codigoResultado_out)= 0) then
            <res:result>OK</res:result>
             else  <res:result>ERROR</res:result>
          }
          {
          if (fn:string-length(string(fn:data($registrarUtilizacionResponse/mensajeError_out)))= 0) then
            <res:message>Registro Exitoso</res:message>
             else  <res:message>{fn:data($registrarUtilizacionResponse/mensajeError_out)}</res:message>
        }
        </ns2:Resultado>
    </ns2:RegistrarUtilizacionAREResponse>
};

local:func($registrarUtilizacionResponse)
