xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace apr="http://www.bcie.org/AprobacionMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioOperacion/Aprobacion/V1/Schema/AprobacionMO.xsd" ::)
declare namespace lin="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)

declare namespace con = "http://www.bcie.org/CondicionBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com = "http://www.bcie.org/ComisionBO";

declare namespace ter = "http://www.bcie.org/TerminoBO";

declare namespace lin1 = "http://www.bcie.org/LineaCreditoBO";

declare namespace apr1 = "http://www.bcie.org/AprobacionBO";

declare variable $inputVariable.CrearAprobacionRequest as element() (:: schema-element(apr:CrearAprobacionRequest) ::) external;

declare function local:funcCrearlineascreeditorequest($inputVariable.CrearAprobacionRequest as element() (:: schema-element(apr:CrearAprobacionRequest) ::)) as element() (:: schema-element(lin:CrearLineaCreditoRequest) ::) {
    <lin:CrearLineaCreditoRequest>
    {
    for $LineaCredito in $inputVariable.CrearAprobacionRequest/apr:Aprobacion/apr1:LineaCredito
    return
        <lin:LineaCredito>
            {
                if ($inputVariable.CrearAprobacionRequest/apr:Aprobacion/apr1:idContrato)
                then <lin1:idContrato>{fn:data($inputVariable.CrearAprobacionRequest/apr:Aprobacion/apr1:idContrato)}</lin1:idContrato>
                else ()
            }
            <lin1:NumeroLineaCredito>{fn:data($LineaCredito/lin1:NumeroLineaCredito)}</lin1:NumeroLineaCredito>
            <lin1:Descripcion>{fn:data($LineaCredito/lin1:Descripcion)}</lin1:Descripcion>
            {
                if ($LineaCredito/lin1:IdTipoMonedaMontoLinea)
                then <lin1:IdTipoMonedaMontoLinea>{fn:data($LineaCredito/lin1:IdTipoMonedaMontoLinea)}</lin1:IdTipoMonedaMontoLinea>
                else ()
            }
            <lin1:MontoLinea>{fn:data($LineaCredito/lin1:MontoLinea)}</lin1:MontoLinea>

        </lin:LineaCredito>
        }
    </lin:CrearLineaCreditoRequest>
};

local:funcCrearlineascreeditorequest($inputVariable.CrearAprobacionRequest)
