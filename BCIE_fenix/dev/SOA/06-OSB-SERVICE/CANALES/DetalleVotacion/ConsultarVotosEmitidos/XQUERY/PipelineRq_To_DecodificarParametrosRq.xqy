xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ConsultarVotosEmitidosMO";
(:: import schema at "../../../../MDS/Canales/Votacion/ConsultarVotosEmitidos/V1/Schema/ConsultarVotosEmitidosMO.xsd" ::)
declare namespace ns2="http://www.bcie.org/DecodificarParametrosMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/Utilidades/DecodificarParametros/V1/Schema/DecodificarParametrosMO.xsd" ::)

declare namespace tns="http://www.w3.org/2005/xquery-local-functions";

declare namespace con = "http://www.bcie.org/ConsultarVotosEmitidosBO";

declare variable $ConsultarVotosEmitidos as element() (:: schema-element(ns1:requestConsultarVotosEmitidosMessage) ::) external;

declare function tns:func($ConsultarVotosEmitidos as element() (:: schema-element(ns1:requestConsultarVotosEmitidosMessage) ::)) as element() (:: schema-element(ns2:DecodificarParametrosRequest) ::) {
    <ns2:DecodificarParametrosRequest>
        <ns2:Parametros>
        {
          if(fn:data($ConsultarVotosEmitidos/ns1:solicitudAprobacion/con:idSolicitudAprobacion)) then
            <ns2:Parametro>
                <ns2:Nombre>idSolicitudAprobacion</ns2:Nombre>
                <ns2:Valor>{fn:data($ConsultarVotosEmitidos/ns1:solicitudAprobacion/con:idSolicitudAprobacion)}</ns2:Valor>
            </ns2:Parametro>
          else ()
        }
        {
          if(fn:data($ConsultarVotosEmitidos/ns1:solicitudAprobacion/con:loginUsuario)) then
            <ns2:Parametro>
                <ns2:Nombre>loginUsuario</ns2:Nombre>
                <ns2:Valor>{fn:data($ConsultarVotosEmitidos/ns1:solicitudAprobacion/con:loginUsuario)}</ns2:Valor>
            </ns2:Parametro>
          else ()
        }
        </ns2:Parametros>
    </ns2:DecodificarParametrosRequest>
};

tns:func($ConsultarVotosEmitidos)
