xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DecodificarParametrosMO";
(:: import schema at "../../../MDS/Resources/ComponentesComunes/Utilidades/DecodificarParametros/V1/Schema/DecodificarParametrosMO.xsd" ::)
declare namespace ns2="http://www.bcie.org/DocumentoMO";
(:: import schema at "../../../MDS/Resources/ComponentesComunes/DominioDocumento/Documento/V1/Schema/DocumentoMO.xsd" ::)

declare namespace tns="http://www.w3.org/2005/xquery-local-functions";

declare variable $DecodificarParametrosResponse as element() (:: schema-element(ns1:DecodificarParametrosResponse) ::) external;

declare function tns:func($DecodificarParametrosResponse as element() (:: schema-element(ns1:DecodificarParametrosResponse) ::)) as element() (:: schema-element(ns2:ObtenerDocumentoOnBaseRequest) ::) {
    <ns2:ObtenerDocumentoOnBaseRequest>
    {
      for $paramtetro in $DecodificarParametrosResponse/ns1:Parametros/ns1:Parametro
      return
       
       if(fn:data($paramtetro/ns1:Nombre = 'idExterno'))then
         <ns2:idExterno>{fn:data($paramtetro/ns1:Valor)}</ns2:idExterno>
       else if(fn:data($paramtetro/ns1:Nombre = 'tipoArchivo'))then
        <ns2:tipoArchivo>{fn:data($paramtetro/ns1:Valor)}</ns2:tipoArchivo>
        else()
      }
    </ns2:ObtenerDocumentoOnBaseRequest>
};

tns:func($DecodificarParametrosResponse)
