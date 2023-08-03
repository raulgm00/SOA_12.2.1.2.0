xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace lin="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)

declare namespace con = "http://www.bcie.org/CondicionBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com = "http://www.bcie.org/ComisionBO";

declare namespace ter = "http://www.bcie.org/TerminoBO";

declare namespace lin1 = "http://www.bcie.org/LineaCreditoBO";

declare variable $inputVariable.request as element() (:: schema-element(lin:PropagarFuenteRequest) ::) external;

declare function local:funcPropagarfuente_to_propagarfuente_sb_request($inputVariable.request as element() (:: schema-element(lin:PropagarFuenteRequest) ::)) as element() (:: schema-element(lin:PropagarFuenteRequest) ::) {
    <lin:PropagarFuenteRequest>
        <lin:LineaCredito>
            {
                if ($inputVariable.request/lin:LineaCredito/lin1:idLineaCredito)
                then <lin1:idLineaCredito>{fn:data($inputVariable.request/lin:LineaCredito/lin1:idLineaCredito)}</lin1:idLineaCredito>
                else ()
            }
            <lin1:NumeroLineaCredito>{fn:data($inputVariable.request/lin:LineaCredito/lin1:NumeroLineaCredito)}</lin1:NumeroLineaCredito>
            <lin1:Descripcion>{fn:data($inputVariable.request/lin:LineaCredito/lin1:Descripcion)}</lin1:Descripcion>
            <lin1:MontoLinea>{fn:data($inputVariable.request/lin:LineaCredito/lin1:MontoLinea)}</lin1:MontoLinea>
            <lin1:CodigoAsignacion>{fn:data($inputVariable.request/lin:LineaCredito/lin1:CodigoAsignacion)}</lin1:CodigoAsignacion>
            <lin1:DescripcionAsignacion>{fn:data($inputVariable.request/lin:LineaCredito/lin1:DescripcionAsignacion)}</lin1:DescripcionAsignacion>            {
                for $Fuente in $inputVariable.request/lin:LineaCredito/lin1:Fuente
                return 
                <lin1:Fuente>
                    <lin1:idFuente>{fn:data($Fuente/lin1:idFuente)}</lin1:idFuente>
                    <lin1:idLineaCredito>{fn:data($Fuente/lin1:idLineaCredito)}</lin1:idLineaCredito>
                    <lin1:idLineaPasiva>{fn:data($Fuente/lin1:idLineaPasiva)}</lin1:idLineaPasiva>
                    <lin1:codigoLineaPasiva>{fn:data($Fuente/lin1:codigoLineaPasiva)}</lin1:codigoLineaPasiva>
                    <lin1:Descripcion>{fn:data($Fuente/lin1:Descripcion)}</lin1:Descripcion>
                    <lin1:FechaObtenido>{fn:data($Fuente/lin1:FechaObtenido)}</lin1:FechaObtenido>
                    <lin1:MontoAsignado>{fn:data($Fuente/lin1:MontoAsignado)}</lin1:MontoAsignado>
                    <lin1:NumeroAsignacion>{fn:data($Fuente/lin1:NumeroAsignacion)}</lin1:NumeroAsignacion>
                    <lin1:Comentario>{fn:data($Fuente/lin1:Comentario)}</lin1:Comentario>
                    <lin1:idContrato>{fn:data($Fuente/lin1:idContrato)}</lin1:idContrato>
                    <lin1:FechaRegistro>{fn:data($Fuente/lin1:FechaRegistro)}</lin1:FechaRegistro>
                    <lin1:Estado>{fn:data($Fuente/lin1:Estado)}</lin1:Estado>
                </lin1:Fuente>
            }
        </lin:LineaCredito>
    </lin:PropagarFuenteRequest>
};

local:funcPropagarfuente_to_propagarfuente_sb_request($inputVariable.request)
