xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/DecodificarParametrosMO";
(:: import schema at "../../../MDS/Resources/ComponentesComunes/Utilidades/DecodificarParametros/V1/Schema/DecodificarParametrosMO.xsd" ::)
declare namespace ns1="http://www.bice.org/CrearComentarioVotacion";
(:: import schema at "../XSD/CrearComentarioVotacion_JSON.xsd" ::)

declare namespace tns="http://www.w3.org/2005/xquery-local-functions";

declare variable $CrearComentarioVotacionRequest as element() (:: schema-element(ns1:CrearComentarioVotacionRequest) ::) external;

declare function tns:func($CrearComentarioVotacionRequest as element() (:: schema-element(ns1:CrearComentarioVotacionRequest) ::)) as element() (:: schema-element(ns2:DecodificarParametrosRequest) ::) {
    <ns2:DecodificarParametrosRequest>
        <ns2:Parametros>
        {if(fn:data($CrearComentarioVotacionRequest/ns1:idUsuario)) then
            <ns2:Parametro>
                <ns2:Nombre>idUsuario</ns2:Nombre>
                <ns2:Valor>{fn:data($CrearComentarioVotacionRequest/ns1:idUsuario)}</ns2:Valor>
            </ns2:Parametro>
          else()
        }
        {
          if(fn:data($CrearComentarioVotacionRequest/ns1:idVotacion))then 
             <ns2:Parametro>
                <ns2:Nombre>idVotacion</ns2:Nombre>
                <ns2:Valor>{fn:data($CrearComentarioVotacionRequest/ns1:idVotacion)}</ns2:Valor>
            </ns2:Parametro>
          else()
        }
        {
          if(fn:data($CrearComentarioVotacionRequest/ns1:nombreUsuario)) then
             <ns2:Parametro>
                <ns2:Nombre>nombreUsuario</ns2:Nombre>
                <ns2:Valor>{fn:data($CrearComentarioVotacionRequest/ns1:nombreUsuario)}</ns2:Valor>
            </ns2:Parametro>
          else()
        }
        {
          if (fn:data($CrearComentarioVotacionRequest/ns1:comentarios)) then
             <ns2:Parametro>
                <ns2:Nombre>comentarios</ns2:Nombre>
                <ns2:Valor>{fn:data($CrearComentarioVotacionRequest/ns1:comentarios)}</ns2:Valor>
            </ns2:Parametro>
          else ()
        }
        </ns2:Parametros>
    </ns2:DecodificarParametrosRequest>
};

tns:func($CrearComentarioVotacionRequest)
