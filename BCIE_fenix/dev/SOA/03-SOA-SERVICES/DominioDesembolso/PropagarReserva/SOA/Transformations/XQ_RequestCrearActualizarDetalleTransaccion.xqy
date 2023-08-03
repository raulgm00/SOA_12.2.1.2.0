xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace des="http://www.bcie.org/DesembolsoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)

declare namespace des1 = "http://www.bcie.org/DesembolsoBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare variable $transferencia.response as element() (:: schema-element(des:ConsultarTransferenciasBancariasByIdResponse) ::) external;

declare function local:funcXq_requestcrearactualizardetalletransaccion($transferencia.response as element() (:: schema-element(des:ConsultarTransferenciasBancariasByIdResponse) ::)) as element() (:: schema-element(des:CrearActualizarDetalleTransaccionRequest) ::) {
    <des:CrearActualizarDetalleTransaccionRequest>
        <des:TransaccionDesembolso>
            <des1:id></des1:id>
            <des1:idDesembolso>{fn:data($transferencia.response/des:ContratoDesembolso/des1:idDesembolso)}</des1:idDesembolso>
            <des1:plataforma>MFC</des1:plataforma>
            <des1:operacion>registrarReserva</des1:operacion>
            <des1:fechaRegistro></des1:fechaRegistro>
            <des1:estatus>true</des1:estatus>
            <des1:detalleTransaccion>
            {
            if(string($transferencia.response/des:ContratoDesembolso/des1:Transferencia/des1:idTransferencia ) != '')
            then 
                <des1:Parameters>
                    <com:name>transferencia</com:name>
                    <com:value>{fn:data($transferencia.response/des:ContratoDesembolso/des1:Transferencia/des1:idTransferencia)}</com:value>
                    <com:id></com:id>
                    <com:agrupador>{fn:data($transferencia.response/des:ContratoDesembolso/des1:Transferencia/des1:idTransferencia)}</com:agrupador>
                </des1:Parameters>
            else()}
            {
            if(string($transferencia.response/des:ContratoDesembolso/des1:Transferencia/des1:Monto[com:tipo/cat:DescripcionCorta='TRANSFERENCIA_BANCARIA']/com:importe)!='')
            then
               <des1:Parameters>
                    <com:name>importe</com:name>
                    <com:value>{fn:data($transferencia.response/des:ContratoDesembolso/des1:Transferencia/des1:Monto[com:tipo/cat:DescripcionCorta='TRANSFERENCIA_BANCARIA']/com:importe)}</com:value>
                    <com:id></com:id>
                    <com:agrupador>{fn:data($transferencia.response/des:ContratoDesembolso/des1:Transferencia/des1:idTransferencia)}</com:agrupador>
                </des1:Parameters>
            else()            
            }
                       {
            if(string($transferencia.response/des:ContratoDesembolso/des1:Transferencia/des1:Monto[com:tipo/cat:DescripcionCorta='TRANSFERENCIA_BANCARIA']/com:moneda/cat:codigoExterno)!='')
            then
               <des1:Parameters>
                    <com:name>moneda</com:name>
                    <com:value>{fn:data($transferencia.response/des:ContratoDesembolso/des1:Transferencia/des1:Monto[com:tipo/cat:DescripcionCorta='TRANSFERENCIA_BANCARIA']/com:moneda/cat:codigoExterno)}</com:value>
                    <com:id></com:id>
                    <com:agrupador>{fn:data($transferencia.response/des:ContratoDesembolso/des1:Transferencia/des1:idTransferencia)}</com:agrupador>
                </des1:Parameters>
            else()            
            }
            {
            if(string($transferencia.response/des:ContratoDesembolso/des1:Transferencia/des1:numeroCuenta)!='')
            then
               <des1:Parameters>
                    <com:name>numeroCuenta</com:name>
                    <com:value>{fn:data($transferencia.response/des:ContratoDesembolso/des1:Transferencia/des1:numeroCuenta)}</com:value>
                    <com:id></com:id>
                    <com:agrupador>{fn:data($transferencia.response/des:ContratoDesembolso/des1:Transferencia/des1:idTransferencia)}</com:agrupador>
                </des1:Parameters>
            else()            
            }
            {
            if(string($transferencia.response/des:ContratoDesembolso/des1:fechaDisponibilidadFondos)!='')
            then
               <des1:Parameters>
                    <com:name>fechaDisponibilidadFondos</com:name>
                    <com:value>{fn:data($transferencia.response/des:ContratoDesembolso/des1:fechaDisponibilidadFondos)}</com:value>
                    <com:id></com:id>
                    <com:agrupador>{fn:data($transferencia.response/des:ContratoDesembolso/des1:Transferencia/des1:idTransferencia)}</com:agrupador>
                </des1:Parameters>
            else()            
            }
            

            </des1:detalleTransaccion>
        </des:TransaccionDesembolso>
    </des:CrearActualizarDetalleTransaccionRequest>
};

local:funcXq_requestcrearactualizardetalletransaccion($transferencia.response)
