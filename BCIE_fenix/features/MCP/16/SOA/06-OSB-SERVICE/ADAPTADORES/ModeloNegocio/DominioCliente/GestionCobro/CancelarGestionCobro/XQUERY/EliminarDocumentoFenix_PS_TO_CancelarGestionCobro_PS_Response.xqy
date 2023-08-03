xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DocumentoMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioDocumento/Documento/V1/Schema/DocumentoMO.xsd" ::)
declare namespace ns2="http://www.bcie.org/GestionCobroMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioCliente/GestionCobro/V1/Schema/GestionCobroMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $EliminarDocumentoFenixResponse as element() (:: schema-element(ns1:EliminarDocumentoFenixResponse) ::) external;

declare function local:func($EliminarDocumentoFenixResponse as element() (:: schema-element(ns1:EliminarDocumentoFenixResponse) ::)) as element() (:: schema-element(ns2:CancelarGestionCobroResponse) ::) {
    <ns2:CancelarGestionCobroResponse>
        <ns2:Resultado>
            <res:result>{fn:data($EliminarDocumentoFenixResponse/ns1:Resultado/res:result)}</res:result>
            <res:message>{fn:data($EliminarDocumentoFenixResponse/ns1:Resultado/res:message)}</res:message>
        </ns2:Resultado>
    </ns2:CancelarGestionCobroResponse>
};

local:func($EliminarDocumentoFenixResponse)
