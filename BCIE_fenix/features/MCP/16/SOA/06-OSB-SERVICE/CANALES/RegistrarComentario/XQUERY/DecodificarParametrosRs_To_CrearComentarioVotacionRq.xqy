xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DecodificarParametrosMO";
(:: import schema at "../../../MDS/Resources/ComponentesComunes/Utilidades/DecodificarParametros/V1/Schema/DecodificarParametrosMO.xsd" ::)
declare namespace ns2="http://www.bice.org/CrearComentarioVotacion";
(:: import schema at "../../RegistrarComentario/XSD/CrearComentarioVotacion_JSON.xsd" ::)

declare namespace tns="http://www.w3.org/2005/xquery-local-functions";

declare variable $DecodificarParametrosResponse as element() (:: schema-element(ns1:DecodificarParametrosResponse) ::) external;

declare function tns:func($DecodificarParametrosResponse as element() (:: schema-element(ns1:DecodificarParametrosResponse) ::)) as element() (:: schema-element(ns2:CrearComentarioVotacionRequest) ::) {
    <ns2:CrearComentarioVotacionRequest>
    {
      for $paramtetro in $DecodificarParametrosResponse/ns1:Parametros/ns1:Parametro
      return
       
       if(fn:data($paramtetro/ns1:Nombre = 'idUsuario'))then
          <ns2:idUsuario>{fn:data($paramtetro/ns1:Valor)}</ns2:idUsuario>
       else if(fn:data($paramtetro/ns1:Nombre = 'idVotacion'))then
        <ns2:idVotacion>{fn:data($paramtetro/ns1:Valor)}</ns2:idVotacion>
       else if(fn:data($paramtetro/ns1:Nombre = 'nombreUsuario'))then   
        <ns2:nombreUsuario>{fn:data($paramtetro/ns1:Valor)}</ns2:nombreUsuario>
       else if(fn:data($paramtetro/ns1:Nombre = 'comentarios'))then
        <ns2:comentarios>{fn:data($paramtetro/ns1:Valor)}</ns2:comentarios>
       else ()
    }
        
    </ns2:CrearComentarioVotacionRequest>
};

tns:func($DecodificarParametrosResponse)
