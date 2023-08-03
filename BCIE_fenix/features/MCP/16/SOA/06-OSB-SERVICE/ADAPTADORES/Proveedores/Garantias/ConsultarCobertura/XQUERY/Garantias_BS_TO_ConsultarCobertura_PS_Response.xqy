xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)
declare namespace m="http://org/bcie/ws/middle/GARANTIAS.wsdl";
declare namespace ns1="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $consultacoberturaResponse as element(m:consultacoberturaResponse) external;

declare function local:func($consultacoberturaResponse as element(m:consultacoberturaResponse)) as element() (:: schema-element(ns1:ConsultarCoberturaResponse) ::) {
    <ns1:ConsultarCoberturaResponse>
        <ns1:Garantias>
            <lin:porcentajeExigido>{fn:data($consultacoberturaResponse/porcentajeexigido_out)}</lin:porcentajeExigido>
            <lin:monto>{fn:data($consultacoberturaResponse/monto_out)}</lin:monto>
        </ns1:Garantias>
        {
          if(string-length($consultacoberturaResponse/mensajeerror_out)=0)then
        <ns1:Resultado>
            <res:result>OK</res:result>
            <res:message>Consulta realizada correctamente</res:message>
        </ns1:Resultado>
        else 
          <ns1:Resultado>
            <res:result>ERROR</res:result>
            <res:message>{fn:data($consultacoberturaResponse/mensajeerror_out)}</res:message>
          </ns1:Resultado>
        }
    </ns1:ConsultarCoberturaResponse>
};

local:func($consultacoberturaResponse)
