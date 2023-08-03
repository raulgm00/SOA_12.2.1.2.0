xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/DocumentoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDocumento/Documento/V1/Schema/DocumentoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/sp/CargarDocumentoFenix";
(:: import schema at "../XSD/CargarDocumentoFenix_sp.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $OutputParameters as element() (:: schema-element(ns1:OutputParameters) ::) external;

declare function local:func($OutputParameters as element() (:: schema-element(ns1:OutputParameters) ::)) as element() (:: schema-element(ns2:CargarDocumentoFenixResponse) ::) {
    <ns2:CargarDocumentoFenixResponse>
        <ns2:Adjunto>{fn:data($OutputParameters/ns1:P_ADJUNTO)}</ns2:Adjunto>
        {
            if($OutputParameters/ns1:P_STATUS = 1) then   
            <ns2:Resultado>
                <res:result>OK</res:result>
                <res:message>Documento creado Correctamente</res:message>
            </ns2:Resultado>
            else
             <ns2:Resultado>
                <res:result>ERROR</res:result>
                <res:message>No se Puedo cargar su documento</res:message>   
             </ns2:Resultado>          
        }
    </ns2:CargarDocumentoFenixResponse>
};

local:func($OutputParameters)