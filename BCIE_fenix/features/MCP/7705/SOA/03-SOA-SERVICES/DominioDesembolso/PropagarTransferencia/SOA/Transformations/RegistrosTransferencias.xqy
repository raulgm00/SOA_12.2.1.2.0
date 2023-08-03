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

declare variable $AplicarCompensacionDesembolso_InputVariable.request as element() (:: schema-element(des:ConsolidarFlujoCajaRequest) ::) external;

declare function local:funcRegistrostransferencias($AplicarCompensacionDesembolso_InputVariable.request as element() (:: schema-element(des:ConsolidarFlujoCajaRequest) ::)) as element() (:: schema-element(www:tablaCorreo) ::) {
        <www:RegistroTransferencias>
        {
        for $registroTransferencia in $AplicarCompensacionDesembolso_InputVariable.request/des:ContratoDesembolso/des1:Transferencia
        return
        concat("<tr><td>",$registroTransferencia/des1:NumeroReserva,"</td>","<td>",$registroTransferencia/des1:numeroCuenta,"</td>","<td>",concat($AplicarCompensacionDesembolso_InputVariable.request/des:ContratoDesembolso/des1:idDesembolso,$registroTransferencia/des1:idTransferencia),"</td>","<td>",$registroTransferencia/des1:Monto/com:importe,"</td>","<td>ELIMINADO</td>","<td>OK</td>")
        }
        </www:RegistroTransferencias>
};

local:funcRegistrostransferencias($AplicarCompensacionDesembolso_InputVariable.request)
