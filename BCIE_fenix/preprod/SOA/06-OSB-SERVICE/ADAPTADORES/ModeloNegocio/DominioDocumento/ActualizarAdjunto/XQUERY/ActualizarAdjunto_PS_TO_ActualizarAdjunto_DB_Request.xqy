xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DocumentoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDocumento/Documento/V1/Schema/DocumentoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/top/ActualizarAdjunto";
(:: import schema at "../XSD/ActualizarAdjunto_table.xsd" ::)

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare variable $ActualizarAdjuntoRequest as element() (:: schema-element(ns1:ActualizarAdjuntoRequest) ::) external;

declare function local:func($ActualizarAdjuntoRequest as element() (:: schema-element(ns1:ActualizarAdjuntoRequest) ::)) as element() (:: schema-element(ns2:AdjuntoCollection) ::) {
    <ns2:AdjuntoCollection>
        <ns2:Adjunto>
            <ns2:idAdjunto>{fn:data($ActualizarAdjuntoRequest/ns1:Documento/doc:idAdjunto)}</ns2:idAdjunto>
        
            {
                if ($ActualizarAdjuntoRequest/ns1:Documento/doc:idExterno)
                then <ns2:idOnbase>{fn:data($ActualizarAdjuntoRequest/ns1:Documento/doc:idExterno)}</ns2:idOnbase>
                else ()
            }
        </ns2:Adjunto>
    </ns2:AdjuntoCollection>
};

local:func($ActualizarAdjuntoRequest)
