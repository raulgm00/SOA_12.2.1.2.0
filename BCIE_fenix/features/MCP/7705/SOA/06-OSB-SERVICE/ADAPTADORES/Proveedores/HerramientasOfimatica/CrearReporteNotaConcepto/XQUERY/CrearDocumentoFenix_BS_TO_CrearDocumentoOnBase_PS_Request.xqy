xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/DocumentoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDocumento/Documento/V1/Schema/DocumentoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/sp/CargarDocumentoFenix";
(:: import schema at "../../../../ModeloNegocio/DominioDocumento/CargarDocumentoFenix/XSD/CargarDocumentoFenix_sp.xsd" ::)

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare variable $outCrearDocumento as element() (:: schema-element(ns1:OutputParameters) ::) external;

declare function local:func($outCrearDocumento as element() (:: schema-element(ns1:OutputParameters) ::)) as element() (:: schema-element(ns2:CargarDocumentoRequest) ::) {
    <ns2:CargarDocumentoRequest>
        <ns2:Documentos>
           <doc:Documento>
                {
                    if ($outCrearDocumento/ns1:P_ADJUNTO)
                    then <doc:idAdjunto>{fn:data($outCrearDocumento/ns1:P_ADJUNTO)}</doc:idAdjunto>
                    else ()
                }
            </doc:Documento>
        </ns2:Documentos>
    </ns2:CargarDocumentoRequest>
};

local:func($outCrearDocumento)
