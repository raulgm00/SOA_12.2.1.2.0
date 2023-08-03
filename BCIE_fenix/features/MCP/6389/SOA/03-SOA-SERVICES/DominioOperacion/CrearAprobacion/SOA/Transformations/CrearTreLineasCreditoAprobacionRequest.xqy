xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace apr="http://www.bcie.org/AprobacionMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioOperacion/Aprobacion/V1/Schema/AprobacionMO.xsd" ::)
declare namespace lin="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)

declare namespace lin1 = "http://www.bcie.org/LineaCreditoBO";

declare namespace apr1 = "http://www.bcie.org/AprobacionBO";

declare variable $inputVariable.CrearAprobacionRequest as element() (:: schema-element(apr:CrearAprobacionRequest) ::) external;

declare function local:funcCreartrelineascreditoaprobacionrequest($inputVariable.CrearAprobacionRequest as element() (:: schema-element(apr:CrearAprobacionRequest) ::)) as element() (:: schema-element(lin:CrearTreLineaCreditoAprobacionRequest) ::) {
    <lin:CrearTreLineaCreditoAprobacionRequest>
    {
    for $LineaCredito in $inputVariable.CrearAprobacionRequest/apr:Aprobacion/apr1:LineaCredito
    return
        <lin:TreLineaCreditoAprobacion>
            <lin1:idLineaCredito>{fn:data($LineaCredito/lin1:idLineaCredito)}</lin1:idLineaCredito>
            <lin1:idAprobacion>{fn:data($inputVariable.CrearAprobacionRequest/apr:Aprobacion/apr1:idAprobacion)}</lin1:idAprobacion>
        </lin:TreLineaCreditoAprobacion>
    }
    </lin:CrearTreLineaCreditoAprobacionRequest>
};

local:funcCreartrelineascreditoaprobacionrequest($inputVariable.CrearAprobacionRequest)
