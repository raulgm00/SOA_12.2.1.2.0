xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DocumentoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDocumento/Documento/V1/Schema/DocumentoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/EliminarDocumentoFenix";
(:: import schema at "../XSD/EliminarDocumentoFenix_sp.xsd" ::)

declare variable $EliminarDocumentoFenixRequest as element() (:: schema-element(ns1:EliminarDocumentoFenixRequest) ::) external;

declare function local:func($EliminarDocumentoFenixRequest as element() (:: schema-element(ns1:EliminarDocumentoFenixRequest) ::)) as element() (:: schema-element(ns2:InputParameters) ::) {
    <ns2:InputParameters>


        <ns2:P_ADJUNTOS>
        {
        for $id in $EliminarDocumentoFenixRequest/ns1:idAdjunto
        return
            <ns2:P_ADJUNTOS_ITEM>
                <ns2:ID_ADJUNTO>{fn:data($id)}</ns2:ID_ADJUNTO>
            </ns2:P_ADJUNTOS_ITEM>
            }
        </ns2:P_ADJUNTOS>
    </ns2:InputParameters>
};

local:func($EliminarDocumentoFenixRequest)
