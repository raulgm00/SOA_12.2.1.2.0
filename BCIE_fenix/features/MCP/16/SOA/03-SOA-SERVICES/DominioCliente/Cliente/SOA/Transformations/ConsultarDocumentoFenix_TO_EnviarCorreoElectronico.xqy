xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace cor="http://www.bcie.org/CorreoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/Utilidades/EnviarCorreoElectronico/V1/Schema/CorreoElectronicoMO.xsd" ::)
declare namespace doc="http://www.bcie.org/DocumentoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioDocumento/Documento/V1/Schema/DocumentoMO.xsd" ::)

declare namespace cor1 = "http://www.bcie.org/CorreoBO";

declare namespace doc1 = "http://www.bcie.org/DocumentoBO";

declare variable $DoctosPDFFenix.response as element() (:: schema-element(doc:ConsultarDocumentosFenixResponse) ::) external;

declare function local:funcConsultardocumentofenix_to_enviarcorreoelectronico2($DoctosPDFFenix.response as element() (:: schema-element(doc:ConsultarDocumentosFenixResponse) ::)) as element() (:: schema-element(cor:enviarCorreoElectronicoRequest) ::) {
    <cor:enviarCorreoElectronicoRequest>
        <cor:CorreoElectronico>
            {
            for $docto in $DoctosPDFFenix.response/doc:Documento
            return
            <cor1:attachment>
                <cor1:name>{string(fn:data($docto/doc1:filename))}</cor1:name>
                <cor1:type>{fn:data($docto/doc1:mime_type)}</cor1:type>
                <cor1:content>{fn:data($docto/doc1:content)}</cor1:content>
            </cor1:attachment>
            }
        </cor:CorreoElectronico>
    </cor:enviarCorreoElectronicoRequest>
};

local:funcConsultardocumentofenix_to_enviarcorreoelectronico2($DoctosPDFFenix.response)
