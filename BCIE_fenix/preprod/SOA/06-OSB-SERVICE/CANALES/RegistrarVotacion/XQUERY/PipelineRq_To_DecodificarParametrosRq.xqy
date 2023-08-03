xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CrearRegistroVotacionBO";
(:: import schema at "../XSD/CrearRegistroVotacion_JSON.xsd" ::)
declare namespace ns2="http://www.bcie.org/DecodificarParametrosMO";
(:: import schema at "../../../MDS/Resources/ComponentesComunes/Utilidades/DecodificarParametros/V1/Schema/DecodificarParametrosMO.xsd" ::)

declare namespace tns="http://www.w3.org/2005/xquery-local-functions";

declare variable $CrearRegistroVotacionRequest as element() (:: schema-element(ns1:CrearRegistroVotacionRequest) ::) external;

declare function tns:func($CrearRegistroVotacionRequest as element() (:: schema-element(ns1:CrearRegistroVotacionRequest) ::)) as element() (:: schema-element(ns2:DecodificarParametrosRequest) ::) {
    <ns2:DecodificarParametrosRequest>
        <ns2:Parametros>
        {
          if(fn:data($CrearRegistroVotacionRequest/ns1:registroVotacion/ns1:idTipoDecision)) then
            <ns2:Parametro>
                <ns2:Nombre>idTipoDecision</ns2:Nombre>
                <ns2:Valor>{fn:data($CrearRegistroVotacionRequest/ns1:registroVotacion/ns1:idTipoDecision)}</ns2:Valor>
            </ns2:Parametro>
          else()
        }
        {
          if(fn:data($CrearRegistroVotacionRequest/ns1:registroVotacion/ns1:idUsuario)) then
              <ns2:Parametro>
                <ns2:Nombre>idUsuario</ns2:Nombre>
                <ns2:Valor>{fn:data($CrearRegistroVotacionRequest/ns1:registroVotacion/ns1:idUsuario)}</ns2:Valor>
            </ns2:Parametro>
          else ()
        }
        {
          if(fn:data($CrearRegistroVotacionRequest/ns1:registroVotacion/ns1:loginUsuario)) then
              <ns2:Parametro>
                <ns2:Nombre>loginUsuario</ns2:Nombre>
                <ns2:Valor>{fn:data($CrearRegistroVotacionRequest/ns1:registroVotacion/ns1:loginUsuario)}</ns2:Valor>
            </ns2:Parametro>
          else()
        }
        {
          if(fn:data($CrearRegistroVotacionRequest/ns1:registroVotacion/ns1:comentario))then 
              <ns2:Parametro>
                <ns2:Nombre>comentario</ns2:Nombre>
                <ns2:Valor>{fn:data($CrearRegistroVotacionRequest/ns1:registroVotacion/ns1:comentario)}</ns2:Valor>
            </ns2:Parametro>
          else ()
        }
        {
          if(fn:data($CrearRegistroVotacionRequest/ns1:registroVotacion/ns1:nombreUsuario)) then
              <ns2:Parametro>
                <ns2:Nombre>nombreUsuario</ns2:Nombre>
                <ns2:Valor>{fn:data($CrearRegistroVotacionRequest/ns1:registroVotacion/ns1:nombreUsuario)}</ns2:Valor>
            </ns2:Parametro>
          else ()
        }
        </ns2:Parametros>
    </ns2:DecodificarParametrosRequest>
};

tns:func($CrearRegistroVotacionRequest)
