xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ConsultarOperacionMO";
(:: import schema at "../../../../MDS/Canales/Votacion/ConsultarDetalleVotacion/V1/Schema/ConsultarOperacionMO.xsd" ::)
declare namespace ns2="http://www.bcie.org/DecodificarParametrosMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/Utilidades/DecodificarParametros/V1/Schema/DecodificarParametrosMO.xsd" ::)

declare namespace tns="http://www.w3.org/2005/xquery-local-functions";

declare namespace con = "http://www.bcie.org/ConsultarOperacionBO";

declare variable $requestConsultarOperacion as element() (:: schema-element(ns1:requestConsultarOperacionMessage) ::) external;

declare function tns:func($requestConsultarOperacion as element() (:: schema-element(ns1:requestConsultarOperacionMessage) ::)) as element() (:: schema-element(ns2:DecodificarParametrosRequest) ::) {
    <ns2:DecodificarParametrosRequest>
        <ns2:Parametros>
        {
          if(fn:data($requestConsultarOperacion/ns1:operacion/con:idVotacion)) then
            <ns2:Parametro>
                <ns2:Nombre>idVotacion</ns2:Nombre>
                <ns2:Valor>{fn:data($requestConsultarOperacion/ns1:operacion/con:idVotacion)}</ns2:Valor>
            </ns2:Parametro>
          else()
        }
        {
          if(fn:data($requestConsultarOperacion/ns1:operacion/con:nivelAprobacion)) then 
             <ns2:Parametro>
                <ns2:Nombre>nivelAprobacion</ns2:Nombre>
                <ns2:Valor>{fn:data($requestConsultarOperacion/ns1:operacion/con:nivelAprobacion)}</ns2:Valor>
            </ns2:Parametro>
          else ()
        }
        {
          if(fn:data($requestConsultarOperacion/ns1:operacion/con:idUsuarioReunion)) then
             <ns2:Parametro>
                <ns2:Nombre>idUsuarioReunion</ns2:Nombre>
                <ns2:Valor>{fn:data($requestConsultarOperacion/ns1:operacion/con:idUsuarioReunion)}</ns2:Valor>
            </ns2:Parametro>
          else()
        }
        </ns2:Parametros>
    </ns2:DecodificarParametrosRequest>
};

tns:func($requestConsultarOperacion)
