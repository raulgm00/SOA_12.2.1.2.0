xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace des="http://www.bcie.org/DesembolsoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd"::)

declare namespace des1 = "http://www.bcie.org/DesembolsoBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare variable $outConsultarDesembolsoById.response as element() (:: schema-element(des:ConsultarDesembolsosByIdResponse) ::) external;

declare function local:funcXq_crearactualizardetalletransaccion($outConsultarDesembolsoById.response as element() (:: schema-element(des:ConsultarDesembolsosByIdResponse) ::)) as element() (:: schema-element(des:CrearActualizarDetalleTransaccionRequest) ::) {
    <des:CrearActualizarDetalleTransaccionRequest>
        <des:TransaccionDesembolso>
            <des1:id></des1:id>
            <des1:idDesembolso>{fn:data($outConsultarDesembolsoById.response/des:ContratoDesembolso/des1:idDesembolso)}</des1:idDesembolso>
            <des1:plataforma>ARE</des1:plataforma>
            <des1:operacion>registrarUtilizacion</des1:operacion>
            <des1:fechaRegistro></des1:fechaRegistro>
            <des1:estatus>true</des1:estatus>
            <des1:detalleTransaccion>
            {
            for $utilizacion in $outConsultarDesembolsoById.response/des:ContratoDesembolso/des1:Utilizacion
              return
                for $parametro in $utilizacion/* 
                return
            if (fn:name($parametro) = "lin:NumeroAsignacion")then
                <des1:Parameters>
                    <com:name>numeroAsignacion</com:name>
                    <com:value>{fn:data(string($parametro))}</com:value>
                    <com:id></com:id>
                    <com:agrupador>{fn:data(string($utilizacion/lin:NumeroAsignacion))}</com:agrupador>
                </des1:Parameters>
            else if (fn:name($parametro) = "lin:MontoAsignado")then
                    <des1:Parameters>
                    <com:name>importe</com:name>
                        <com:value>{fn:data(string($parametro))}</com:value>
                    <com:id></com:id>
                    <com:agrupador>{fn:data(string($utilizacion/lin:NumeroAsignacion))}</com:agrupador>
                </des1:Parameters>
            else()    
               } 
            </des1:detalleTransaccion>
        </des:TransaccionDesembolso>
    </des:CrearActualizarDetalleTransaccionRequest>
};

local:funcXq_crearactualizardetalletransaccion($outConsultarDesembolsoById.response)
