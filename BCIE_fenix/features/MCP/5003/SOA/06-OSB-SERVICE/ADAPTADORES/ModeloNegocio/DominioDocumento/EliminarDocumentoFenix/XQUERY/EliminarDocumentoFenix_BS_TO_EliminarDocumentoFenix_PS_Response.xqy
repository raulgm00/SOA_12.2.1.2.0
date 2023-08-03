xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/DocumentoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDocumento/Documento/V1/Schema/DocumentoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/sp/EliminarDocumentoFenix";
(:: import schema at "../XSD/EliminarDocumentoFenix_sp.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $OutputParameters as element() (:: schema-element(ns1:OutputParameters) ::) external;

declare function local:func($OutputParameters as element() (:: schema-element(ns1:OutputParameters) ::)) as element() (:: schema-element(ns2:EliminarDocumentoFenixResponse) ::) {
    <ns2:EliminarDocumentoFenixResponse>
        <ns2:Resultado>
            <res:result>{fn:data($OutputParameters/ns1:P_RESULTADO)}</res:result>
            <res:message>Operación realizada correctamente</res:message>
        </ns2:Resultado>
    </ns2:EliminarDocumentoFenixResponse>
};

local:func($OutputParameters)
