xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace des="http://www.bcie.org/DesembolsoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace pro="http:/www.bcie.org/PropagarTransferenciaXSD";
(:: import schema at "../Schemas/PropagarTransferenciaXSD.xsd" ::)
declare namespace des1 = "http://www.bcie.org/DesembolsoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";


declare namespace her = "http://www.bcie.org/HerramientaCEBO";
declare variable $ConsolidarFlujoCaja_InputVariable.ConsolidarFlujoCajaIn as element() (:: schema-element(des:ConsolidarFlujoCajaRequest) ::) external;

declare function local:funcConsolidaflujocajain_to_tablacorreo($ConsolidarFlujoCaja_InputVariable.ConsolidarFlujoCajaIn as element() (:: schema-element(des:ConsolidarFlujoCajaRequest) ::)) as element() (:: schema-element(pro:tablaCorreo) ::) {

<pro:tablaCorreo>
        {
            for $ContratoDesembolso in $ConsolidarFlujoCaja_InputVariable.ConsolidarFlujoCajaIn/des:ContratoDesembolso
            for $registroTransferencia in $ContratoDesembolso/des1:Transferencia
            return 
            <pro:tablaTransferencias>
                <pro:idTransferencia>{fn:data($registroTransferencia/des1:idTransferencia)}</pro:idTransferencia>
                <pro:numeroReserva>{fn:data($registroTransferencia/des1:NumeroReserva)}</pro:numeroReserva>
                <pro:numeroCuenta>{fn:data($registroTransferencia/des1:numeroCuenta)}</pro:numeroCuenta>
                <pro:referencia>{fn:data(concat($ContratoDesembolso/des1:idDesembolso,$registroTransferencia/des1:idTransferencia))}</pro:referencia>
                <pro:monto>{fn:data($registroTransferencia/des1:Monto/com:importe)}</pro:monto>
                {
                if($registroTransferencia/des1:esConsolidacionPadre/text() != '1')then
                <pro:operacion>ELIMINAR</pro:operacion>
                else
                 <pro:operacion>CREAR</pro:operacion>
                }
                <pro:estatus>ERROR</pro:estatus>
            </pro:tablaTransferencias>
        }
    </pro:tablaCorreo>
};

local:funcConsolidaflujocajain_to_tablacorreo($ConsolidarFlujoCaja_InputVariable.ConsolidarFlujoCajaIn)
