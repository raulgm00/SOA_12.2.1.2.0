xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace com="http://www.bcie.org/ComisionMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioLineaCredito/Comision/V1/Schema/ComisionMO.xsd" ::)
declare namespace lin="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)

declare namespace con = "http://www.bcie.org/CondicionBO";

declare namespace com2 = "http://www.bcie.org/ComisionBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace com1 = "http://www.bcie.org/CommonBO";

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace reg = "http://www.bcie.org/ReglaBO";

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare namespace ter = "http://www.bcie.org/TerminoBO";

declare namespace lin1 = "http://www.bcie.org/LineaCreditoBO";

declare namespace con1 = "http://www.bcie.org/ContratoBO";

declare variable $outConsultarFechaCargo.response as element() (:: schema-element(com:ConsultarFechaCargoResponse) ::) external;
declare variable $outConsultarFlexcube.response as element() (:: schema-element(lin:ConsultarFLEXCUBEResponse) ::) external;
declare variable $consultarLineaCreditoByIdLineaCredito_OutputVariable.response as element() (:: schema-element(lin:ConsultarLineaCreditoByIdLineaCreditoResponse) ::) external;

declare function local:funcXq_requestactualizarcontrato($outConsultarFechaCargo.response as element() (:: schema-element(com:ConsultarFechaCargoResponse) ::), 
                                                        $outConsultarFlexcube.response as element() (:: schema-element(lin:ConsultarFLEXCUBEResponse) ::), 
                                                        $consultarLineaCreditoByIdLineaCredito_OutputVariable.response as element() (:: schema-element(lin:ConsultarLineaCreditoByIdLineaCreditoResponse) ::)) 
                                                        as element() (:: schema-element(lin:ActualizarCommitmentRequest) ::) {
    <lin:ActualizarCommitmentRequest>
        <lin:LineaCredito>
            <lin1:idLineaCredito>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:contrato/con1:LineaCredito/lin1:idLineaCredito)}</lin1:idLineaCredito>
            <lin1:NumeroLineaCredito>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:contrato/con1:LineaCredito/lin1:NumeroLineaCredito)}</lin1:NumeroLineaCredito>
            <lin1:Flexcube>
                <lin1:id>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:contrato/con1:LineaCredito/lin1:Flexcube/lin1:id)}</lin1:id>
            </lin1:Flexcube>
            {
                if ($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:contrato/con1:LineaCredito/lin1:Fondo)
                then <lin1:Fondo>{fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:contrato/con1:LineaCredito/lin1:Fondo)}</lin1:Fondo>
                else ()
            }
            <lin1:MontoLinea>
            {
            ( 
             fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:contrato/con1:LineaCredito/lin1:Monto[com1:tipo/cat:DescripcionCorta='MONTO_AMPLIACION']/com1:importe)+
             fn:data($consultarLineaCreditoByIdLineaCredito_OutputVariable.response/lin:Operacion/ope:contrato/con1:LineaCredito/lin1:MontoLinea)
             )-
             fn:data($outConsultarFlexcube.response/lin:TipoContrato/lin1:LineaCredito/lin1:MontoLinea)
            }
            
            
            </lin1:MontoLinea>
            <lin1:FechaValor>{fn:data($outConsultarFechaCargo.response/com:fechaCargo)}</lin1:FechaValor>
        </lin:LineaCredito>
    </lin:ActualizarCommitmentRequest>
};

local:funcXq_requestactualizarcontrato($outConsultarFechaCargo.response, $outConsultarFlexcube.response, $consultarLineaCreditoByIdLineaCredito_OutputVariable.response)
