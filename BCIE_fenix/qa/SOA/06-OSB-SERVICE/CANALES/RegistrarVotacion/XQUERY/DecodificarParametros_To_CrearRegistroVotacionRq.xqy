xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/CrearRegistroVotacionBO";
(:: import schema at "../XSD/CrearRegistroVotacion_JSON.xsd" ::)
declare namespace ns1="http://www.bcie.org/DecodificarParametrosMO";
(:: import schema at "../../../MDS/Resources/ComponentesComunes/Utilidades/DecodificarParametros/V1/Schema/DecodificarParametrosMO.xsd" ::)

declare namespace tns="http://www.w3.org/2005/xquery-local-functions";

declare variable $DecodificarParametrosResponse as element() (:: schema-element(ns1:DecodificarParametrosResponse) ::) external;

declare function tns:func($DecodificarParametrosResponse as element() (:: schema-element(ns1:DecodificarParametrosResponse) ::)) as element() (:: schema-element(ns2:CrearRegistroVotacionRequest) ::) {
    <ns2:CrearRegistroVotacionRequest>
        <ns2:registroVotacion>
          {
            for $paramtetro in $DecodificarParametrosResponse/ns1:Parametros/ns1:Parametro
            return 
              if(fn:data($paramtetro/ns1:Nombre = 'idTipoDecision'))then
                    <ns2:idTipoDecision>{fn:data($paramtetro/ns1:Valor)}</ns2:idTipoDecision>
              else if(fn:data($paramtetro/ns1:Nombre = 'idUsuario'))then
                    <ns2:idUsuario>{fn:data($paramtetro/ns1:Valor)}</ns2:idUsuario>
              else if(fn:data($paramtetro/ns1:Nombre = 'loginUsuario'))then      
                    <ns2:loginUsuario>{fn:data($paramtetro/ns1:Valor)}</ns2:loginUsuario>
              else if(fn:data($paramtetro/ns1:Nombre = 'comentario'))then      
                    <ns2:comentario>{fn:data($paramtetro/ns1:Valor)}</ns2:comentario>
              else if(fn:data($paramtetro/ns1:Nombre = 'nombreUsuario'))then      
                    <ns2:nombreUsuario>{fn:data($paramtetro/ns1:Valor)}</ns2:nombreUsuario>
              else ()
          }
        </ns2:registroVotacion>
    </ns2:CrearRegistroVotacionRequest>
};

tns:func($DecodificarParametrosResponse)
