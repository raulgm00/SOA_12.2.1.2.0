xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/bpmn/bpmnProcess/ImplPCTProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PA11/ImpPCTProcess.xsd" ::)

declare namespace ns4 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";

declare variable $InicioImplementacionPCT as element() (:: schema-element(ns2:InicioImplementacionPCT) ::) external;

declare function local:func($InicioImplementacionPCT as element() (:: schema-element(ns2:InicioImplementacionPCT) ::)) as element() (:: schema-element(ns1:ConsultarLineaCreditoFormalizacionRequest) ::) {
    <ns1:ConsultarLineaCreditoFormalizacionRequest>
        <ns1:idOperacion>{fn:data($InicioImplementacionPCT/ns2:Header/ns3:Operacion/ns4:CodigoOperacion)}</ns1:idOperacion>
    </ns1:ConsultarLineaCreditoFormalizacionRequest>
};

local:func($InicioImplementacionPCT)
