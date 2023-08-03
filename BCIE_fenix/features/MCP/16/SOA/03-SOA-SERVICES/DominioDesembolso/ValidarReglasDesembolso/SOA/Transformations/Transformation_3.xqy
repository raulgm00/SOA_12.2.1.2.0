xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace lin="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)

declare namespace con = "http://www.bcie.org/ContratoBO";

declare namespace lin1 = "http://www.bcie.org/LineaCreditoBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare variable $OutConsultarLineaCreditoByIdLineaCredito2.response as element() (:: schema-element(lin:ConsultarLineaCreditoByIdLineaCreditoResponse) ::) external;

declare function local:funcTransformation_3($OutConsultarLineaCreditoByIdLineaCredito2.response as element() (:: schema-element(lin:ConsultarLineaCreditoByIdLineaCreditoResponse) ::)) as element() (:: schema-element(lin:ConsultarFLEXCUBERequest) ::) {
    <lin:ConsultarFLEXCUBERequest>
        <lin:codigoContrato>{fn:data($OutConsultarLineaCreditoByIdLineaCredito2.response/lin:Operacion/ope:contrato/con:LineaCredito/lin1:Flexcube/lin1:id)}</lin:codigoContrato>
        <lin:nivelDetalle>LINEA</lin:nivelDetalle>
    </lin:ConsultarFLEXCUBERequest>
};

local:funcTransformation_3($OutConsultarLineaCreditoByIdLineaCredito2.response)
