xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/DecodificarParametrosMO";
(:: import schema at "../../../MDS/Resources/ComponentesComunes/Utilidades/DecodificarParametros/V1/Schema/DecodificarParametrosMO.xsd" ::)
declare namespace ns1="http://www.bcie.org/DocumentoMO";
(:: import schema at "../../../MDS/Resources/ComponentesComunes/DominioDocumento/Documento/V1/Schema/DocumentoMO.xsd" ::)

declare namespace tns="http://www.w3.org/2005/xquery-local-functions";

declare variable $ObtenerDocumentoOnBaseRequest as element() (:: schema-element(ns1:ObtenerDocumentoOnBaseRequest) ::) external;

declare function tns:func($ObtenerDocumentoOnBaseRequest as element() (:: schema-element(ns1:ObtenerDocumentoOnBaseRequest) ::)) as element() (:: schema-element(ns2:DecodificarParametrosRequest) ::) {
    <ns2:DecodificarParametrosRequest>
        <ns2:Parametros>
        {
          if(fn:data($ObtenerDocumentoOnBaseRequest/ns1:idExterno))then
            <ns2:Parametro>
                <ns2:Nombre>idExterno</ns2:Nombre>
                <ns2:Valor>{fn:data($ObtenerDocumentoOnBaseRequest/ns1:idExterno)}</ns2:Valor>
            </ns2:Parametro>
          else()
        }
        {
          if(fn:data($ObtenerDocumentoOnBaseRequest/ns1:tipoArchivo)) then
            <ns2:Parametro>
                <ns2:Nombre>tipoArchivo</ns2:Nombre>
                <ns2:Valor>{fn:data($ObtenerDocumentoOnBaseRequest/ns1:tipoArchivo)}</ns2:Valor>
            </ns2:Parametro>
          else()
        }
        </ns2:Parametros>
    </ns2:DecodificarParametrosRequest>
};

tns:func($ObtenerDocumentoOnBaseRequest)
