xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ConsultarOperacionMO";
(:: import schema at "../../../../MDS/Canales/Votacion/ConsultarDetalleVotacion/V1/Schema/ConsultarOperacionMO.xsd" ::)
declare namespace ns1="http://www.bcie.org/DecodificarParametrosMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/Utilidades/DecodificarParametros/V1/Schema/DecodificarParametrosMO.xsd" ::)

declare namespace tns="http://www.w3.org/2005/xquery-local-functions";

declare namespace con = "http://www.bcie.org/ConsultarOperacionBO";

declare variable $DecodificarParametrosRs as element() (:: schema-element(ns1:DecodificarParametrosResponse) ::) external;

declare function tns:func($DecodificarParametrosRs as element() (:: schema-element(ns1:DecodificarParametrosResponse) ::)) as element() (:: schema-element(ns2:requestConsultarOperacionMessage) ::) {
    <ns2:requestConsultarOperacionMessage>
        <ns2:operacion>
        {
          for $paramtetro in $DecodificarParametrosRs/ns1:Parametros/ns1:Parametro
           return
       
            if(fn:data($paramtetro/ns1:Nombre = 'idVotacion'))then
               <con:idVotacion>{fn:data($paramtetro/ns1:Valor)}</con:idVotacion>
            else if(fn:data($paramtetro/ns1:Nombre = 'nivelAprobacion'))then
               <con:nivelAprobacion>{fn:data($paramtetro/ns1:Valor)}</con:nivelAprobacion>
            else if(fn:data($paramtetro/ns1:Nombre = 'idUsuarioReunion'))then   
            <con:idUsuarioReunion>{fn:data($paramtetro/ns1:Valor)}</con:idUsuarioReunion>
            else()
        }
        </ns2:operacion>
    </ns2:requestConsultarOperacionMessage>
};

tns:func($DecodificarParametrosRs)
