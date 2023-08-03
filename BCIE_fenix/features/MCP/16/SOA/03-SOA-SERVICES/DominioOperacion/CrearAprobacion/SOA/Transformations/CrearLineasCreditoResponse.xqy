xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace apr="http://www.bcie.org/AprobacionMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioOperacion/Aprobacion/V1/Schema/AprobacionMO.xsd" ::)
declare namespace lin="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)

declare namespace con = "http://www.bcie.org/CondicionBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com = "http://www.bcie.org/ComisionBO";

declare namespace apr1 = "http://www.bcie.org/AprobacionBO";

declare namespace ter = "http://www.bcie.org/TerminoBO";

declare namespace lin1 = "http://www.bcie.org/LineaCreditoBO";

declare variable $inputVariable.CrearAprobacionRequest as element() (:: schema-element(apr:CrearAprobacionRequest) ::) external;
declare variable $outInvokeCrearLineasCredito.CrearLineaCreditoResponse as element() (:: schema-element(lin:CrearLineaCreditoResponse) ::) external;

declare function local:funcCrearlineascreditoresponse($inputVariable.CrearAprobacionRequest as element() (:: schema-element(apr:CrearAprobacionRequest) ::), 
                                                      $outInvokeCrearLineasCredito.CrearLineaCreditoResponse as element() (:: schema-element(lin:CrearLineaCreditoResponse) ::)) 
                                                      as element() (:: schema-element(apr:CrearAprobacionRequest) ::) {
    <apr:CrearAprobacionRequest>
        <apr:Aprobacion>
            <apr1:idAprobacion>{fn:data($inputVariable.CrearAprobacionRequest/apr:Aprobacion/apr1:idAprobacion)}</apr1:idAprobacion>
            <apr1:idOperacion>{fn:data($inputVariable.CrearAprobacionRequest/apr:Aprobacion/apr1:idOperacion)}</apr1:idOperacion>
            {
                if ($inputVariable.CrearAprobacionRequest/apr:Aprobacion/apr1:idContrato)
                then <apr1:idContrato>{fn:data($inputVariable.CrearAprobacionRequest/apr:Aprobacion/apr1:idContrato)}</apr1:idContrato>
                else ()
            }
        {
        for $LineaCredito in $outInvokeCrearLineasCredito.CrearLineaCreditoResponse/lin:LineaCredito
        return
            <apr1:LineaCredito>
                 <lin1:idLineaCredito>{fn:data($LineaCredito/lin1:idLineaCredito)}</lin1:idLineaCredito>
            </apr1:LineaCredito>
        }
        </apr:Aprobacion>
    </apr:CrearAprobacionRequest>
};

local:funcCrearlineascreditoresponse($inputVariable.CrearAprobacionRequest, $outInvokeCrearLineasCredito.CrearLineaCreditoResponse)
