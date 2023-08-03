xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ImplementacionPctMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/DominioOperacion/implementacionPCT/V1/Schema/ImplementacionPctMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/bpmn/bpmnProcess/ImplPCTProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PA11/ImpPCTProcess.xsd" ::)

declare namespace ns4 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";

declare namespace ns5 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1";

declare variable $InicioImplementacionPCT as element() (:: schema-element(ns1:InicioImplementacionPCT) ::) external;

declare function local:func($InicioImplementacionPCT as element() (:: schema-element(ns1:InicioImplementacionPCT) ::)) as element() (:: schema-element(ns2:ConsultarImplementacionLoteRequest) ::) {
    <ns2:ConsultarImplementacionLoteRequest>
        <ns2:idOperacion>{fn:data($InicioImplementacionPCT/ns1:Header/ns3:Operacion/ns4:CodigoOperacion)}</ns2:idOperacion>
        <ns2:idLote>{fn:data($InicioImplementacionPCT/ns1:Header/ns5:ParameterType[ns5:parameterName = 'ID_LOTE']/ns5:parameterValue)}</ns2:idLote>
    </ns2:ConsultarImplementacionLoteRequest>
};

local:func($InicioImplementacionPCT)
