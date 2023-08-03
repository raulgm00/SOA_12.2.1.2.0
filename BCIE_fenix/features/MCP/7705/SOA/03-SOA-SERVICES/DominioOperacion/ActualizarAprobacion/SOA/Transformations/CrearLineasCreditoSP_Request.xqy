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

declare variable $inputVariable.ActualizarAprobacionRequest as element() (:: schema-element(apr:ActualizarAprobacionRequest) ::) external;

declare function local:funcCrearlineascreditosp_request($inputVariable.ActualizarAprobacionRequest as element() (:: schema-element(apr:ActualizarAprobacionRequest) ::)) as element() (:: schema-element(lin:ActualizarLineaCreditoAprobacionRequest) ::) {
    <lin:ActualizarLineaCreditoAprobacionRequest>
        <lin:Aprobacion>
            <apr1:idAprobacion>{fn:data($inputVariable.ActualizarAprobacionRequest/apr:Aprobacion/apr1:idAprobacion)}</apr1:idAprobacion>
            <apr1:idContrato>{fn:data($inputVariable.ActualizarAprobacionRequest/apr:Aprobacion/apr1:idContrato)}</apr1:idContrato>
            {
            for $LineaCredito in $inputVariable.ActualizarAprobacionRequest/apr:Aprobacion/apr1:LineaCredito
            return
            <apr1:LineaCredito>
                <lin1:idLineaCredito>{fn:data($LineaCredito/lin1:idLineaCredito)}</lin1:idLineaCredito>
                <lin1:NumeroLineaCredito>{fn:data($LineaCredito/lin1:NumeroLineaCredito)}</lin1:NumeroLineaCredito>
                <lin1:Descripcion>{fn:data($LineaCredito/lin1:Descripcion)}</lin1:Descripcion>
                {
                  if ($LineaCredito/lin1:IdTipoMonedaMontoLinea)
                  then <lin1:IdTipoMonedaMontoLinea>{fn:data($LineaCredito/lin1:IdTipoMonedaMontoLinea)}</lin1:IdTipoMonedaMontoLinea>
                  else ()
                }
                <lin1:MontoLinea>{fn:data($LineaCredito/lin1:MontoLinea)}</lin1:MontoLinea>
                {
                    if ($LineaCredito/lin1:Estado)
                    then <lin1:Estado>{fn:data($LineaCredito/lin1:Estado)}</lin1:Estado>
                    else ()
                }
            </apr1:LineaCredito>
            }
        </lin:Aprobacion>
    </lin:ActualizarLineaCreditoAprobacionRequest>
};

local:funcCrearlineascreditosp_request($inputVariable.ActualizarAprobacionRequest)
