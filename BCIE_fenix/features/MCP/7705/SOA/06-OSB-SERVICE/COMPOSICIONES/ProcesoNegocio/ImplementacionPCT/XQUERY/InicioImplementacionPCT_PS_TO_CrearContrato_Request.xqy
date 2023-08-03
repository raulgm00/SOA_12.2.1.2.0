xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns3="http://www.bcie.org/ContratoMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/DominioContrato/Contrato/V1/Schema/ContratoMO.xsd" ::)
declare namespace ns1="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/bpmn/bpmnProcess/ImplPCTProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PA11/ImpPCTProcess.xsd" ::)

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace ter = "http://www.bcie.org/TerminoBO";

declare namespace con1 = "http://www.bcie.org/CondicionBO";

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare namespace con = "http://www.bcie.org/ContratoBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare namespace reg = "http://www.bcie.org/ReglaBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace ns5 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1";

declare namespace ns4 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";

declare variable $outConsultarContratoLineaCredito as element() (:: schema-element(ns1:ConsultarLineaCreditoFormalizacionResponse) ::) external;
declare variable $InicioImplementacionPCT as element() (:: schema-element(ns2:InicioImplementacionPCT) ::) external;

declare function local:func($outConsultarContratoLineaCredito as element() (:: schema-element(ns1:ConsultarLineaCreditoFormalizacionResponse) ::), 
                            $InicioImplementacionPCT as element() (:: schema-element(ns2:InicioImplementacionPCT) ::)) 
                            as element() (:: schema-element(ns3:CrearContratoRequest) ::) {
    <ns3:CrearContratoRequest>
            <ns3:Contrato>
              <con:idContrato></con:idContrato>
              <con:idOperacion>{fn:data($InicioImplementacionPCT/ns2:Header/ns4:Operacion/ns5:CodigoOperacion)}</con:idOperacion>
              <con:fechaFirma>{fn:data($outConsultarContratoLineaCredito/ns1:clienteContrato[fn:count($outConsultarContratoLineaCredito/ns1:clienteContrato)]/con:fechaFirma)}</con:fechaFirma>
              <con:fechaVigencia>{fn:data($outConsultarContratoLineaCredito/ns1:clienteContrato[fn:count($outConsultarContratoLineaCredito/ns1:clienteContrato)]/con:fechaVigencia)}</con:fechaVigencia>
              <con:MontoEscriturado>{fn:data($outConsultarContratoLineaCredito/ns1:clienteContrato[fn:count($outConsultarContratoLineaCredito/ns1:clienteContrato)]/con:MontoEscriturado)}</con:MontoEscriturado>
              <con:cuentaCliente>
                  {
                      for $cuentaCliente in $outConsultarContratoLineaCredito/ns1:clienteContrato[fn:count($outConsultarContratoLineaCredito/ns1:clienteContrato)]/con:cuentaCliente/con:cuentaCliente
                      return 
                      <con:cuentaCliente>{fn:data($outConsultarContratoLineaCredito/ns1:clienteContrato[fn:count($outConsultarContratoLineaCredito/ns1:clienteContrato)]/con:cuentaCliente/con:cuentaCliente)}</con:cuentaCliente>
                  }
              </con:cuentaCliente>
              <con:instanciaProceso></con:instanciaProceso>
            </ns3:Contrato>
  </ns3:CrearContratoRequest>
};

local:func($outConsultarContratoLineaCredito, $InicioImplementacionPCT)
