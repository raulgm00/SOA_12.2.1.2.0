xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ConsultarVotosEmitidosMO";
(:: import schema at "../../../../MDS/Canales/Votacion/ConsultarVotosEmitidos/V1/Schema/ConsultarVotosEmitidosMO.xsd" ::)
declare namespace ns1="http://www.bcie.org/DecodificarParametrosMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/Utilidades/DecodificarParametros/V1/Schema/DecodificarParametrosMO.xsd" ::)

declare namespace tns="http://www.w3.org/2005/xquery-local-functions";

declare namespace con = "http://www.bcie.org/ConsultarVotosEmitidosBO";

declare variable $DecodificarParametrosResponse as element() (:: schema-element(ns1:DecodificarParametrosResponse) ::) external;

declare function tns:func($DecodificarParametrosResponse as element() (:: schema-element(ns1:DecodificarParametrosResponse) ::)) as element() (:: schema-element(ns2:requestConsultarVotosEmitidosMessage) ::) {
    <ns2:requestConsultarVotosEmitidosMessage>
        <ns2:solicitudAprobacion>
         {
          for $paramtetro in $DecodificarParametrosResponse/ns1:Parametros/ns1:Parametro
          return
        
          if(fn:data($paramtetro/ns1:Nombre = 'idSolicitudAprobacion'))then
              <con:idSolicitudAprobacion>{fn:data($paramtetro/ns1:Valor)}</con:idSolicitudAprobacion>
          
          else if(fn:data($paramtetro/ns1:Nombre = 'loginUsuario'))then
            <con:loginUsuario>{fn:data($paramtetro/ns1:Valor)}</con:loginUsuario>
          else()
        }      
        </ns2:solicitudAprobacion>
    </ns2:requestConsultarVotosEmitidosMessage>
};

tns:func($DecodificarParametrosResponse)
