xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace doc="http://www.bcie.org/DocumentoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioDocumento/Documento/V1/Schema/DocumentoMO.xsd" ::)
declare namespace gen="http://www.bcie.org/GenerarArchivo";
(:: import schema at "../XSD/Recuperacion.xsd" ::)

declare variable $ListaAdjuntos as element() (:: element(*, gen:ListaAdjuntosType) ::) external;

declare function local:funcCargardocumentofenix_ps_to_eliminardocumentofenix_ps_request($ListaAdjuntos as element() (:: element(*, gen:ListaAdjuntosType) ::)) as element() (:: schema-element(doc:EliminarDocumentoFenixRequest) ::) {
    <doc:EliminarDocumentoFenixRequest>
        {
            for $idAdjunto in $ListaAdjuntos/gen:idAdjunto
            return 
            <doc:idAdjunto>{fn:data($idAdjunto)}</doc:idAdjunto>
        }
        
    </doc:EliminarDocumentoFenixRequest>
};

local:funcCargardocumentofenix_ps_to_eliminardocumentofenix_ps_request($ListaAdjuntos)
