xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/DocumentoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDocumento/Documento/V1/Schema/DocumentoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/top/ActualizarAdjunto";
(:: import schema at "../XSD/ActualizarAdjunto_table.xsd" ::)

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $AdjuntoCollection as element() (:: schema-element(ns1:AdjuntoCollection) ::) external;

declare function local:func($AdjuntoCollection as element() (:: schema-element(ns1:AdjuntoCollection) ::)) as element() (:: schema-element(ns2:ActualizarAdjuntoResponse) ::) {
    <ns2:ActualizarAdjuntoResponse>
        <ns2:Documento>
        
            {
                if ($AdjuntoCollection/ns1:Adjunto/ns1:idOnbase)
                then <doc:idExterno>{fn:data($AdjuntoCollection/ns1:Adjunto/ns1:idOnbase)}</doc:idExterno>
                else ()
            }
           
            <doc:idAdjunto>{fn:data($AdjuntoCollection/ns1:Adjunto/ns1:idAdjunto)}</doc:idAdjunto>
          
        </ns2:Documento>
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>Actualizado Correctamente</res:message>
         
        </ns2:Resultado>
    </ns2:ActualizarAdjuntoResponse>
};

local:func($AdjuntoCollection)
