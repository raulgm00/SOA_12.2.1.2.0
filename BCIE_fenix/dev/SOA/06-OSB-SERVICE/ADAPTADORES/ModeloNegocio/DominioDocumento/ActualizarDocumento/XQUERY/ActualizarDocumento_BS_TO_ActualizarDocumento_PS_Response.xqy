xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/DocumentoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDocumento/Documento/V1/Schema/DocumentoMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $message as xs:string external;

declare function local:func($message as xs:string) as element() (:: schema-element(ns2:ActualizarDocumentoResponse) ::) {
    <ns2:ActualizarDocumentoResponse>
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>Actualización Exitosa</res:message>
          
        </ns2:Resultado>
    </ns2:ActualizarDocumentoResponse>
};

local:func($message)
