xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace lin="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace lin1 = "http://www.bcie.org/LineaCreditoBO";

declare variable $outConsultarAsignacionARE.response as element() (:: schema-element(lin:ConsultarAsignacionAREResponse) ::) external;
declare variable $outConsultarLineaCreditoFuente.response as element() (:: schema-element(lin:ConsultarLineaCreditoFuenteResponse) ::) external;

declare function local:funcXq_crearactualizarfuentes_request($outConsultarAsignacionARE.response as element() (:: schema-element(lin:ConsultarAsignacionAREResponse) ::), 
                                                             $outConsultarLineaCreditoFuente.response as element() (:: schema-element(lin:ConsultarLineaCreditoFuenteResponse) ::)) 
                                                             as element() (:: schema-element(lin:CrearActualizarFuentesRequest) ::) {
    <lin:CrearActualizarFuentesRequest>
            {
        for $FuentesFenix in $outConsultarLineaCreditoFuente.response/lin:lineaCredito/lin1:Fuente
        return
        <lin:Fuentes>
            <lin1:idFuente>{fn:data($FuentesFenix/lin1:idFuente)}</lin1:idFuente>
            <lin1:Estado>{false()}</lin1:Estado>
        </lin:Fuentes>
        }
    {    
    for $fuentesARE in $outConsultarAsignacionARE.response/lin:LineaCredito/lin1:Fuente
    let $idFuente:= if (string($fuentesARE/lin1:NumeroAsignacion) != '')then $outConsultarLineaCreditoFuente.response/lin:lineaCredito/lin1:Fuente[lin1:NumeroAsignacion = $fuentesARE/lin1:NumeroAsignacion]/lin1:idFuente/text()
    else(0)
    where $fuentesARE/lin1:MontoAsignado != 0
    return
    
        <lin:Fuentes>
            <lin1:idFuente>{
            if (fn:string-length(string($idFuente))> 0 and $idFuente != 0 )then $idFuente
            else()
            }</lin1:idFuente>
            <lin1:idLineaCredito>{fn:data($outConsultarLineaCreditoFuente.response/lin:lineaCredito/lin1:idLineaCredito)}</lin1:idLineaCredito>
            <lin1:idLineaPasiva>{fn:data($fuentesARE/lin1:codigoLineaPasiva)}</lin1:idLineaPasiva>
            <lin1:FechaObtenido>{fn:data($fuentesARE/lin1:FechaObtenido)}</lin1:FechaObtenido>
            <lin1:MontoAsignado>{fn:data($fuentesARE/lin1:MontoAsignado)}</lin1:MontoAsignado>
            <lin1:NumeroAsignacion>{fn:data($fuentesARE/lin1:NumeroAsignacion)}</lin1:NumeroAsignacion>
            <lin1:Comentario>{fn:data($fuentesARE/lin1:Comentario)}</lin1:Comentario>
            <lin1:Estado>{true()}</lin1:Estado>
        </lin:Fuentes>
        }
    </lin:CrearActualizarFuentesRequest>
};

local:funcXq_crearactualizarfuentes_request($outConsultarAsignacionARE.response, $outConsultarLineaCreditoFuente.response)
