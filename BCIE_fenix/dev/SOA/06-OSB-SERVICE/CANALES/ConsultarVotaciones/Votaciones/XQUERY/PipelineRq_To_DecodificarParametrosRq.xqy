xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ConsultarVotacionesMO";
(:: import schema at "../../../../MDS/Canales/Votacion/ConsultarVotaciones/V1/Schema/ConsultarVotacionesMO.xsd" ::)
declare namespace ns2="http://www.bcie.org/DecodificarParametrosMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/Utilidades/DecodificarParametros/V1/Schema/DecodificarParametrosMO.xsd" ::)

declare namespace tns="http://www.w3.org/2005/xquery-local-functions";

declare variable $ConsultarVotacionesRequest as element() (:: schema-element(ns1:ConsultarVotacionesRequest) ::) external;

declare function tns:func($ConsultarVotacionesRequest as element() (:: schema-element(ns1:ConsultarVotacionesRequest) ::)) as element() (:: schema-element(ns2:DecodificarParametrosRequest) ::) {
    <ns2:DecodificarParametrosRequest>
        <ns2:Parametros>
        {
          if(fn:data($ConsultarVotacionesRequest/ns1:Usuario))then
        
            <ns2:Parametro>
                <ns2:Nombre>Usuario</ns2:Nombre>
                <ns2:Valor>{fn:data($ConsultarVotacionesRequest/ns1:Usuario)}</ns2:Valor>
            </ns2:Parametro>
          else()
        }
        {
          if(fn:data($ConsultarVotacionesRequest/ns1:historial))then
            <ns2:Parametro>
                <ns2:Nombre>historial</ns2:Nombre>
                <ns2:Valor>{fn:data($ConsultarVotacionesRequest/ns1:historial)}</ns2:Valor>
            </ns2:Parametro>
          else()
        }
        {
          if(fn:data($ConsultarVotacionesRequest/ns1:aprobacionCliente)) then
            <ns2:Parametro>
                <ns2:Nombre>aprobacionCliente</ns2:Nombre>
                <ns2:Valor>{fn:data($ConsultarVotacionesRequest/ns1:aprobacionCliente)}</ns2:Valor>
            </ns2:Parametro>
          else ()
        }
        </ns2:Parametros>
    </ns2:DecodificarParametrosRequest>
};

tns:func($ConsultarVotacionesRequest)
