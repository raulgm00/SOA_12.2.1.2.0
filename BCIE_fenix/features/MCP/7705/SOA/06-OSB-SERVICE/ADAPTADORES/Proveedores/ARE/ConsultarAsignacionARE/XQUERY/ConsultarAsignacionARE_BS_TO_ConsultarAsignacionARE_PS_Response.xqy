xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace are="http://org/bcie/ws/middle/ARE/ARE.wsdl";

declare namespace typ="http://org/bcie/ws/middle/ARE/ARE.wsdl/types/";
(:: import schema at "../../WSDL/ARE.wsdl" ::)
declare namespace ns2="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)

declare namespace con = "http://www.bcie.org/CondicionBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com = "http://www.bcie.org/ComisionBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace ter = "http://www.bcie.org/TerminoBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $consultarAsignacionResponse as element() (:: element(are:consultarAsignacionResponse) ::) external;

declare function local:func($consultarAsignacionResponse as element(are:consultarAsignacionResponse) (:: element(are:consultarAsignacionResponse) ::)) as element() (:: schema-element(ns2:ConsultarAsignacionAREResponse) ::) {
    <ns2:ConsultarAsignacionAREResponse>
    {
    for $lineaARE in $consultarAsignacionResponse/asignacion_out/typ:listaLineas
    return
        <ns2:LineaCredito>
            <lin:idLineaCredito></lin:idLineaCredito>
            <lin:NumeroLineaCredito>{fn:data($lineaARE/typ:array)}</lin:NumeroLineaCredito>
            <lin:Descripcion>{fn:data($consultarAsignacionResponse/asignacion_out/typ:descripcion)}</lin:Descripcion>
            <lin:MontoLinea>{fn:data($consultarAsignacionResponse/asignacion_out/typ:montoTotal)}</lin:MontoLinea>
            <lin:CodigoAsignacion>{fn:data($consultarAsignacionResponse/asignacion_out/typ:codigoAsignacion)}</lin:CodigoAsignacion>
            {
            for $fuenteARE in $consultarAsignacionResponse/asignacion_out/typ:listaDetalles/typ:array
            return
            <lin:Fuente>
                <lin:idFuente></lin:idFuente>
                <lin:idLineaCredito></lin:idLineaCredito>
                <lin:idLineaPasiva> </lin:idLineaPasiva>
                <lin:codigoLineaPasiva>{fn:data($fuenteARE/typ:codigoLineaPasiva)}</lin:codigoLineaPasiva>
                <lin:Descripcion></lin:Descripcion>
                <lin:FechaObtenido>{fn:data($fuenteARE/typ:fechaObtenido)}</lin:FechaObtenido>
                <lin:MontoAsignado>{fn:data($fuenteARE/typ:montoAsignado)}</lin:MontoAsignado>
                <lin:NumeroAsignacion>{fn:data($fuenteARE/typ:numeroAsignacion)}</lin:NumeroAsignacion>
                <lin:Comentario>{fn:data($fuenteARE/typ:comentarios)}</lin:Comentario>
                <lin:idContrato></lin:idContrato>
                <lin:FechaRegistro></lin:FechaRegistro> 
                <lin:Estado></lin:Estado>
            </lin:Fuente>
            }
        </ns2:LineaCredito>
        }
        <ns2:Resultado>
            <res:result>{
            if($consultarAsignacionResponse/codigoResultado_out =0) then ("OK")
            else("ERROR")
            }
            </res:result>
            <res:message>{
            if (fn:string-length($consultarAsignacionResponse/mensajeError_out) = 0) then ("Operación exitosa")
            else (fn:data($consultarAsignacionResponse/mensajeError_out))
            }</res:message>
            <res:error>
                <err:errorCode></err:errorCode>
                <err:errorDescription></err:errorDescription>
                <err:errorType></err:errorType>
            </res:error>
        </ns2:Resultado>
    </ns2:ConsultarAsignacionAREResponse>
};

local:func($consultarAsignacionResponse)
