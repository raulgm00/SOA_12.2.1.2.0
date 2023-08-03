xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace des="http://www.bcie.org/DesembolsoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd"::)

declare namespace www="http:/www.bcie.org/ConsolidarFlujoCaja";
(:: import schema at "../Schemas/ConsolidarFlujoCaja.xsd" ::)

declare namespace des1 = "http://www.bcie.org/DesembolsoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare variable $ConsultarDetalleTransaccionByIdDesembolso_OutputVariable.response as element() (:: schema-element(des:ConsultarDetalleTransaccionResponse) ::) external;
declare variable $inputAplicarDesembolso.ConsolidarFlujoCajaIn as element() (:: schema-element(des:ConsolidarFlujoCajaRequest) ::) external;

declare function local:funcRegistrotransferenciaserror($ConsultarDetalleTransaccionByIdDesembolso_OutputVariable.response as element() (:: schema-element(des:ConsultarDetalleTransaccionResponse) ::), 
                                                       $inputAplicarDesembolso.ConsolidarFlujoCajaIn as element() (:: schema-element(des:ConsolidarFlujoCajaRequest) ::)) 
                                                       as element() (:: schema-element(www:RegistroTransferencias) ::) {
    <RegistroTransferencias>
    {
        for $registroTransferencia in $inputAplicarDesembolso.ConsolidarFlujoCajaIn/des:ContratoDesembolso/des1:Transferencia
        for $registroDetalleTransaccion in $ConsultarDetalleTransaccionByIdDesembolso_OutputVariable.response/des:TransaccionDesembolso/des1:detalleTransaccion/des1:Parameters[com:name='transferencia']
        return
        if($registroDetalleTransaccion/com:value/text() != $registroTransferencia/des1:idTransferencia/text())
        then
        concat("<tr><td>",$registroTransferencia/des1:NumeroReserva,"</td>","<td>",$registroTransferencia/des1:numeroCuenta,"</td>","<td>",concat($inputAplicarDesembolso.ConsolidarFlujoCajaIn/des:ContratoDesembolso/des1:idDesembolso,$registroTransferencia/des1:idTransferencia),"</td>","<td>",$registroTransferencia/des1:Monto/com:importe,"</td>","<td>ELIMINADO</td>","<td>OK</td>")
        else
        concat("<tr><td>",$registroTransferencia/des1:NumeroReserva,"</td>","<td>",$registroTransferencia/des1:numeroCuenta,"</td>","<td>",concat($inputAplicarDesembolso.ConsolidarFlujoCajaIn/des:ContratoDesembolso/des1:idDesembolso,$registroTransferencia/des1:idTransferencia),"</td>","<td>",$registroTransferencia/des1:Monto/com:importe,"</td>","<td>ELIMINADO</td>","<td>ERROR</td>")
        }
    </RegistroTransferencias>
};

local:funcRegistrotransferenciaserror($ConsultarDetalleTransaccionByIdDesembolso_OutputVariable.response, $inputAplicarDesembolso.ConsolidarFlujoCajaIn)
