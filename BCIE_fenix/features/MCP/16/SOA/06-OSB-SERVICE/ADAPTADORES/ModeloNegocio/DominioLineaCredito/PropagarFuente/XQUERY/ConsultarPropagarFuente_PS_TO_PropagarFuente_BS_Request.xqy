xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)

declare namespace con = "http://www.bcie.org/CondicionBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com = "http://www.bcie.org/ComisionBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace ter = "http://www.bcie.org/TerminoBO";

declare variable $ConsultarLineaCreditoFuenteResponse as element() (:: schema-element(ns1:ConsultarLineaCreditoFuenteResponse) ::) external;

declare function local:func($ConsultarLineaCreditoFuenteResponse as element() (:: schema-element(ns1:ConsultarLineaCreditoFuenteResponse) ::)) as element() (:: schema-element(ns1:PropagarFuenteRequest) ::) {
    <ns1:PropagarFuenteRequest>
        <ns1:LineaCredito>
            {
                if ($ConsultarLineaCreditoFuenteResponse/ns1:lineaCredito/lin:idLineaCredito)
                then <lin:idLineaCredito>{fn:data($ConsultarLineaCreditoFuenteResponse/ns1:lineaCredito/lin:idLineaCredito)}</lin:idLineaCredito>
                else ()
            }
            <lin:NumeroLineaCredito>{fn:data($ConsultarLineaCreditoFuenteResponse/ns1:lineaCredito/lin:NumeroLineaCredito)}</lin:NumeroLineaCredito>
            <lin:Descripcion>{fn:data($ConsultarLineaCreditoFuenteResponse/ns1:lineaCredito/lin:Descripcion)}</lin:Descripcion>
            <lin:MontoLinea>{fn:data($ConsultarLineaCreditoFuenteResponse/ns1:lineaCredito/lin:MontoLinea)}</lin:MontoLinea>
            <lin:EsRevolvente></lin:EsRevolvente>
            <lin:CodigoAsignacion>{fn:data($ConsultarLineaCreditoFuenteResponse/ns1:lineaCredito/lin:CodigoAsignacion)}</lin:CodigoAsignacion>

            {
                for $Fuente in $ConsultarLineaCreditoFuenteResponse/ns1:lineaCredito/lin:Fuente
                return 
                <lin:Fuente>
                    <lin:idFuente>{fn:data($Fuente/lin:idFuente)}</lin:idFuente>
                    <lin:idLineaCredito>{fn:data($Fuente/lin:idLineaCredito)}</lin:idLineaCredito>
                    <lin:idLineaPasiva>{ if (string($Fuente/lin:FechaObtenido)='')then ()else    fn:data($Fuente/lin:idLineaPasiva)}</lin:idLineaPasiva>
                    <lin:codigoLineaPasiva>{                                 
                    fn:data($Fuente/lin:codigoLineaPasiva)                    
                    }</lin:codigoLineaPasiva>
                    <lin:Descripcion>{fn:data($Fuente/lin:Descripcion)}</lin:Descripcion>
                    <lin:FechaObtenido>{fn:data($Fuente/lin:FechaObtenido)}</lin:FechaObtenido>
                    <lin:MontoAsignado>{fn:data($Fuente/lin:MontoAsignado)}</lin:MontoAsignado>
                    <lin:NumeroAsignacion>{fn:data($Fuente/lin:NumeroAsignacion)}</lin:NumeroAsignacion>
                    <lin:Comentario>{fn:data($Fuente/lin:Comentario)}</lin:Comentario>
                    <lin:idContrato>{fn:data($Fuente/lin:idContrato)}</lin:idContrato>
                    <lin:FechaRegistro>{fn:data($Fuente/lin:FechaRegistro)}</lin:FechaRegistro>
                    <lin:Estado>{fn:data($Fuente/lin:Estado)}</lin:Estado>
                </lin:Fuente>
            }
        </ns1:LineaCredito>
    </ns1:PropagarFuenteRequest>
};

local:func($ConsultarLineaCreditoFuenteResponse)
