xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace lin="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)

declare namespace lin1 = "http://www.bcie.org/LineaCreditoBO";

declare variable $CrearLineaCredito_Input.CrearLineaCreditoIn as element() (:: schema-element(lin:CrearLineaCreditoRequest) ::) external;
declare variable $CrearLineaCredito_OutputVariable.CrearLineaCreditoResponse as element() (:: schema-element(lin:CrearLineaCreditoResponse) ::) external;

declare function local:funcCrearlineacreditoinput_to_creartrelineacreditoaprobacionrequest($CrearLineaCredito_Input.CrearLineaCreditoIn as element() (:: schema-element(lin:CrearLineaCreditoRequest) ::), 
                                                                                           $CrearLineaCredito_OutputVariable.CrearLineaCreditoResponse as element() (:: schema-element(lin:CrearLineaCreditoResponse) ::)) 
                                                                                           as element() (:: schema-element(lin:CrearTreLineaCreditoAprobacionRequest) ::) {
        <lin:CrearTreLineaCreditoAprobacionRequest>
        {
            for $LineaCredito in $CrearLineaCredito_OutputVariable.CrearLineaCreditoResponse/lin:LineaCredito
            return 
            <lin:TreLineaCreditoAprobacion>
                <lin1:idLineaCredito>{fn:data($LineaCredito/lin1:idLineaCredito)}</lin1:idLineaCredito>
                <lin1:idAprobacion>{fn:data($CrearLineaCredito_Input.CrearLineaCreditoIn/lin:idAprobacion)}</lin1:idAprobacion>
            </lin:TreLineaCreditoAprobacion>
        }
    </lin:CrearTreLineaCreditoAprobacionRequest>
};

local:funcCrearlineacreditoinput_to_creartrelineacreditoaprobacionrequest($CrearLineaCredito_Input.CrearLineaCreditoIn, $CrearLineaCredito_OutputVariable.CrearLineaCreditoResponse)
