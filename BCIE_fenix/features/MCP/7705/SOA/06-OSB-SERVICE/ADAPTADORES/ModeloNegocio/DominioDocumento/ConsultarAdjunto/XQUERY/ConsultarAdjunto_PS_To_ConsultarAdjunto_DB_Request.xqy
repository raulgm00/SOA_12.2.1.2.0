xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DocumentoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDocumento/Documento/V1/Schema/DocumentoMO.xsd" ::)
declare namespace ns11="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ConsultarAdjunto";
(:: import schema at "../XSD/ConsultarAdjunto_sp.xsd" ::)

declare variable $ConsultarAdjuntoByIdAdjunto as element() (:: schema-element(ns1:ConsultarAdjuntoByIdAdjuntoRequest) ::) external;

declare function local:func($ConsultarAdjuntoByIdAdjunto as element() (:: schema-element(ns1:ConsultarAdjuntoByIdAdjuntoRequest) ::)) as element() (:: schema-element(ns11:InputParameters) ::) {
    <ns1:InputParameters>
        {
            if ($ConsultarAdjuntoByIdAdjunto/ns1:idAdjunto)
            then <ns11:PID_ADJUNTO>{fn:data($ConsultarAdjuntoByIdAdjunto/ns1:idAdjunto)}</ns11:PID_ADJUNTO>
            else ()
        }
    </ns1:InputParameters>
      
};

local:func($ConsultarAdjuntoByIdAdjunto)
