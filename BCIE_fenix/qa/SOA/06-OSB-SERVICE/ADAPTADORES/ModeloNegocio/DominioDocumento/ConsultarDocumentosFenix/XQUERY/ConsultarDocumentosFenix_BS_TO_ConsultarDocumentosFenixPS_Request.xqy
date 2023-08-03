xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DocumentoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDocumento/Documento/V1/Schema/DocumentoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ConsultarDocumentosFenix";
(:: import schema at "../XSD/ConsultarDocumentosFenix_db.xsd" ::)

declare variable $ConsultarDocumentosFenixRequest as element() (:: schema-element(ns1:ConsultarDocumentosFenixRequest) ::) external;

declare function local:func($ConsultarDocumentosFenixRequest as element() (:: schema-element(ns1:ConsultarDocumentosFenixRequest) ::)) as element() (:: schema-element(ns2:InputParameters) ::) {
    <ns2:InputParameters>
        {
            if ($ConsultarDocumentosFenixRequest/ns1:idCliente)
            then <ns2:P_ID_CLIENTE>{fn:data($ConsultarDocumentosFenixRequest/ns1:idCliente)}</ns2:P_ID_CLIENTE>
            else ()
        }
        {
            if ($ConsultarDocumentosFenixRequest/ns1:idOperacion)
            then <ns2:P_ID_OPERACION>{fn:data($ConsultarDocumentosFenixRequest/ns1:idOperacion)}</ns2:P_ID_OPERACION>
            else ()
        }
        <ns2:P_CONTENT>{if(fn:string($ConsultarDocumentosFenixRequest/ns1:Content) = 'true')then 1 else(0)}</ns2:P_CONTENT>
        {
            if ($ConsultarDocumentosFenixRequest/ns1:idFlujo)
            then <ns2:P_ID_FLUJO>{fn:data($ConsultarDocumentosFenixRequest/ns1:idFlujo)}</ns2:P_ID_FLUJO>
            else ()
        }
    </ns2:InputParameters>
};

local:func($ConsultarDocumentosFenixRequest)
