xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DocumentoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDocumento/Documento/V1/Schema/DocumentoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ConsultarDocumentosOnBase";
(:: import schema at "../XSD/ConsultarDocumentosOnBase_sp.xsd" ::)

declare variable $ConsultarDocumentosRequest as element() (:: schema-element(ns1:ConsultaDocumentosRequest) ::) external;

declare function local:func($ConsultarDocumentosRequest as element() (:: schema-element(ns1:ConsultaDocumentosRequest) ::)) as element() (:: schema-element(ns2:InputParameters) ::) {
    <ns2:InputParameters>
        {
            if ($ConsultarDocumentosRequest/ns1:idOperacion)
            then <ns2:P_ID_OPERACION>{fn:data($ConsultarDocumentosRequest/ns1:idOperacion)}</ns2:P_ID_OPERACION>
            else ()
        }
        {
            if ($ConsultarDocumentosRequest/ns1:idCliente)
            then <ns2:P_IDCLIENTE>{fn:data($ConsultarDocumentosRequest/ns1:idCliente)}</ns2:P_IDCLIENTE>
            else ()
        }
        {
            if ($ConsultarDocumentosRequest/ns1:tipoDocumento)
            then <ns2:P_TIPODOCUMENTO>{fn:data($ConsultarDocumentosRequest/ns1:tipoDocumento)}</ns2:P_TIPODOCUMENTO>
            else ()
        }
        {
            if ($ConsultarDocumentosRequest/ns1:idTarea)
            then <ns2:P_IDTAREA>{fn:data($ConsultarDocumentosRequest/ns1:idTarea)}</ns2:P_IDTAREA>
            else ()
        }
        {
            if ($ConsultarDocumentosRequest/ns1:instanciaProceso)
            then <ns2:P_INSTANCIAPROCESO>{fn:data($ConsultarDocumentosRequest/ns1:instanciaProceso)}</ns2:P_INSTANCIAPROCESO>
            else ()
        }
    </ns2:InputParameters>
};

local:func($ConsultarDocumentosRequest)