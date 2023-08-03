xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/bpmn/bpmnProcess/CondicionesProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PA03/CondicionesProcess.xsd" ::)

declare namespace con = "http://www.bcie.org/CondicionBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace reg = "http://www.bcie.org/ReglaBO";

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1";

declare variable $FinCondiciones as element() (:: schema-element(ns1:FinCondiciones) ::) external;

declare function local:func($FinCondiciones as element() (:: schema-element(ns1:FinCondiciones) ::)) as element() (:: schema-element(ns2:UpsertTransaccionCondicionRequest) ::) {
    <ns2:UpsertTransaccionCondicionRequest>
        <ns2:transaccionCondicion>
            <con:enProceso>{fn:false()}</con:enProceso>
            <con:agrupador>{fn:data($FinCondiciones/ns1:Header/ns3:ParameterType[ns3:parameterName='AGRUPADOR']/ns3:parameterValue)}</con:agrupador>
        </ns2:transaccionCondicion>
    </ns2:UpsertTransaccionCondicionRequest>
};

local:func($FinCondiciones)
