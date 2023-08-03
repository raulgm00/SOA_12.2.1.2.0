xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace pro="http:/www.bcie.org/PropagarTransferenciaXSD";
(:: import schema at "../Schemas/PropagarTransferenciaXSD.xsd" ::)

declare variable $tablaCorreo as element() (:: schema-element(pro:tablaCorreo) ::) external;

declare function local:funcEstatusok_tablacorreo($tablaCorreo as element() (:: schema-element(pro:tablaCorreo) ::)) as element() (:: schema-element(pro:tablaCorreo) ::) {
    <pro:tablaCorreo>
        {
            for $tablaTransferencias in $tablaCorreo/pro:tablaTransferencias
            return 
            <pro:tablaTransferencias>
                <pro:idTransferencia>{fn:data($tablaTransferencias/pro:idTransferencia)}</pro:idTransferencia>
                <pro:numeroReserva>{fn:data($tablaTransferencias/pro:numeroReserva)}</pro:numeroReserva>
                <pro:numeroCuenta>{fn:data($tablaTransferencias/pro:numeroCuenta)}</pro:numeroCuenta>
                <pro:referencia>{fn:data($tablaTransferencias/pro:referencia)}</pro:referencia>
                <pro:monto>{fn:data($tablaTransferencias/pro:monto)}</pro:monto>
                <pro:operacion>{fn:data($tablaTransferencias/pro:operacion)}</pro:operacion>
                 {
                if($tablaTransferencias/pro:operacion/text() != 'CREAR')then
                <pro:estatus>OK</pro:estatus>
                 else
                  <pro:estatus>{fn:data($tablaTransferencias/pro:estatus)}</pro:estatus>
                 }
            </pro:tablaTransferencias>
        }
    </pro:tablaCorreo>
};

local:funcEstatusok_tablacorreo($tablaCorreo)
