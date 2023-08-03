xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ImplementacionPctMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/DominioOperacion/implementacionPCT/V1/Schema/ImplementacionPctMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/bpmn/bpmnProcess/ImplPCTProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PA11/ImpPCTProcess.xsd" ::)

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace imp = "http://www.bcie.org/ImplementacionPctBO";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1";

declare variable $CrearActualizarLoteImplementacion as element() (:: schema-element(ns1:FinImplementacionPCT) ::) external;

declare function local:func($CrearActualizarLoteImplementacion as element() (:: schema-element(ns1:FinImplementacionPCT) ::)) as element() (:: schema-element(ns2:CrearActualizarLoteImplementacionRequest) ::) {
    <ns2:CrearActualizarLoteImplementacionRequest>
        <ns2:LoteImplementacion>
            <imp:enProceso>{fn:false()}</imp:enProceso>
            <imp:loteImpementacion>
                <imp:idLote>{fn:data($CrearActualizarLoteImplementacion/ns1:Header/ns3:ParameterType/ns3:parameterValue)}</imp:idLote>
            </imp:loteImpementacion>
        </ns2:LoteImplementacion>
    </ns2:CrearActualizarLoteImplementacionRequest>
};

local:func($CrearActualizarLoteImplementacion)
