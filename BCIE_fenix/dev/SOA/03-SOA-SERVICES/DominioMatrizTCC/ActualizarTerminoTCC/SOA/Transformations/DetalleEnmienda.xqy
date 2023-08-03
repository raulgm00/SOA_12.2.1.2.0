xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace mat="http://www.bcie.org/MatrizTCCMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioContrato/MatrizTCC/V1/Schema/MatrizTCCMO.xsd" ::)
declare namespace ter="http://www.bcie.org/TerminoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioContrato/Termino/V1/Schema/TerminoMO.xsd" ::)

declare namespace mat1 = "http://www.bcie.org/MatrizTCCBO";

declare namespace ter1 = "http://www.bcie.org/TerminoBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare variable $inputVariable.request as element() (:: schema-element(mat:ActualizarTCCRequest) ::) external;
declare variable $outConsultarTerminoActualizado.response as element() (:: schema-element(ter:ConsultarTerminoResponse) ::) external;

declare function local:funcDetalleenmienda($inputVariable.request as element() (:: schema-element(mat:ActualizarTCCRequest) ::), 
                                           $outConsultarTerminoActualizado.response as element() (:: schema-element(ter:ConsultarTerminoResponse) ::)) 
                                           as element() (:: schema-element(mat:ActualizarDetalleEnmiendaRequest) ::) {
    <mat:ActualizarDetalleEnmiendaRequest>
        <mat:detalleEnmienda>            
            <mat1:tipoTCC>{fn:data($inputVariable.request/mat:Tipo)}</mat1:tipoTCC>
            <mat1:idTCC>{fn:data($outConsultarTerminoActualizado.response/ter:Termino/ter1:idTermino)}</mat1:idTCC>      
            {
              if (fn:data($outConsultarTerminoActualizado.response/ter:Termino/ter1:subEstadoTCC/atr:id) = 16)
                then
                    <mat1:accion>AGREGAR</mat1:accion>
                else if (fn:data($outConsultarTerminoActualizado.response/ter:Termino/ter1:subEstadoTCC/atr:id) = 17)
                  then 
                    <mat1:accion>DISPENSAR</mat1:accion>
                else if (fn:data($outConsultarTerminoActualizado.response/ter:Termino/ter1:subEstadoTCC/atr:id) = 18)
                  then
                    <mat1:accion>MODIFICAR</mat1:accion>
                else if(fn:data($outConsultarTerminoActualizado.response/ter:Termino/ter1:subEstadoTCC/atr:id) = 19)
                  then
                    <mat1:accion>ELIMINAR</mat1:accion>
                  else()
            }            
        </mat:detalleEnmienda>
    </mat:ActualizarDetalleEnmiendaRequest>
};

local:funcDetalleenmienda($inputVariable.request, $outConsultarTerminoActualizado.response)
