xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ConsultarVotacionesMO";
(:: import schema at "../../../../MDS/Canales/Votacion/ConsultarVotaciones/V1/Schema/ConsultarVotacionesMO.xsd" ::)
declare namespace ns1="http://www.bcie.org/DecodificarParametrosMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/Utilidades/DecodificarParametros/V1/Schema/DecodificarParametrosMO.xsd" ::)

declare namespace tns="http://www.w3.org/2005/xquery-local-functions";

declare variable $DecodificarParametrosResponse as element() (:: schema-element(ns1:DecodificarParametrosResponse) ::) external;

declare function tns:func($DecodificarParametrosResponse as element() (:: schema-element(ns1:DecodificarParametrosResponse) ::)) as element() (:: schema-element(ns2:ConsultarVotacionesRequest) ::) {
    <ns2:ConsultarVotacionesRequest>
    {
      for $paramtetro in $DecodificarParametrosResponse/ns1:Parametros/ns1:Parametro
      return
       
       if(fn:data($paramtetro/ns1:Nombre = 'Usuario'))then
          <ns2:Usuario>{fn:data($paramtetro/ns1:Valor)}</ns2:Usuario>
      else if(fn:data($paramtetro/ns1:Nombre = 'historial'))then
          <ns2:historial>{fn:data($paramtetro/ns1:Valor)}</ns2:historial>
      else if(fn:data($paramtetro/ns1:Nombre = 'aprobacionCliente'))then
          <ns2:aprobacionCliente>{fn:data($paramtetro/ns1:Valor)}</ns2:aprobacionCliente>
      else()
    }
    
    </ns2:ConsultarVotacionesRequest>
};

tns:func($DecodificarParametrosResponse)
