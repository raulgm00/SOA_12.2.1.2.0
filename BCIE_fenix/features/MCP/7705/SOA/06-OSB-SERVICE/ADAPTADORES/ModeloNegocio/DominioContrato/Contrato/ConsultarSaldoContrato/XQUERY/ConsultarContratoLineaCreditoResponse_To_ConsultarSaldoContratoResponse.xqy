xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ContratoMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Contrato/V1/Schema/ContratoMO.xsd" ::)
declare namespace ns2="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)

declare namespace con = "http://www.bcie.org/ContratoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace reg = "http://www.bcie.org/ReglaBO";

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare namespace ter = "http://www.bcie.org/TerminoBO";

declare namespace con1 = "http://www.bcie.org/CondicionBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ConsultarSaldoContratoResponse as element() (:: schema-element(ns1:ConsultarSaldoContratoResponse) ::) external;
declare variable $ConsultarLineaCreditoResponse as element() (:: schema-element(ns2:ConsultarLineaCreditoResponse) ::) external;
declare variable $Resultado as xs:string external;

declare function local:func($ConsultarSaldoContratoResponse as element() (:: schema-element(ns1:ConsultarSaldoContratoResponse) ::), 
                            $ConsultarLineaCreditoResponse as element() (:: schema-element(ns2:ConsultarLineaCreditoResponse) ::),
                            $Resultado) 
                            as element() (:: schema-element(ns1:ConsultarSaldoContratoResponse) ::) {
    <ns1:ConsultarSaldoContratoResponse>
    { if($Resultado ='OK') then
        <ns1:Contrato>
            <con:idContrato>{fn:data($ConsultarLineaCreditoResponse/ns2:clienteContrato/con:idContrato)}</con:idContrato>
            <con:idOperacion>{fn:data($ConsultarLineaCreditoResponse/ns2:clienteContrato/con:idOperacion)}</con:idOperacion>
            <con:fechaFirma>{fn:data($ConsultarLineaCreditoResponse/ns2:clienteContrato/con:fechaFirma)}</con:fechaFirma>
            <con:fechaVigencia>{fn:data($ConsultarLineaCreditoResponse/ns2:clienteContrato/con:fechaVigencia)}</con:fechaVigencia>
            <con:MontoEscriturado>{fn:data($ConsultarLineaCreditoResponse/ns2:clienteContrato/con:MontoEscriturado)}</con:MontoEscriturado>
            <con:cuentaCliente>
                {
                    for $cuentaCliente in $ConsultarLineaCreditoResponse/ns2:clienteContrato/con:cuentaCliente/con:cuentaCliente
                    return 
                    <con:cuentaCliente>{fn:data($ConsultarLineaCreditoResponse/ns2:clienteContrato/con:cuentaCliente/con:cuentaCliente)}</con:cuentaCliente>
                }
            </con:cuentaCliente>
            <con:instanciaProceso>{fn:data($ConsultarLineaCreditoResponse/ns2:clienteContrato/con:instanciaProceso)}</con:instanciaProceso>
            { for $lineaCredito in $ConsultarSaldoContratoResponse/ns1:Contrato/con:LineaCredito
            where $lineaCredito/lin:idLineaCredito = $ConsultarLineaCreditoResponse/ns2:clienteContrato/con:LineaCredito/lin:idLineaCredito
            and count($lineaCredito/lin:ContratoDesembolso) > 0
            return
            <con:LineaCredito>
                {$lineaCredito/*}
            </con:LineaCredito>
            }
        </ns1:Contrato>
        else()
    } 
    {
      if($Resultado ='OK') then
        <ns1:Resultado>
            <res:result>OK</res:result>
            <res:message>Consulta Exitosa</res:message>
        </ns1:Resultado>
      else
         <ns1:Resultado>
            <res:result>ERROR</res:result>
            <res:message>{$Resultado}</res:message>
        </ns1:Resultado>
    }
    </ns1:ConsultarSaldoContratoResponse>
};

local:func($ConsultarSaldoContratoResponse, $ConsultarLineaCreditoResponse,$Resultado)
