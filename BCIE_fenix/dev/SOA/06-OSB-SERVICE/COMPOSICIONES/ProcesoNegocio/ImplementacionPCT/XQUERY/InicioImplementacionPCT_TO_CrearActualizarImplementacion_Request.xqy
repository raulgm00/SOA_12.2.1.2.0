xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ImplementacionPctMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/DominioOperacion/implementacionPCT/V1/Schema/ImplementacionPctMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/bpmn/bpmnProcess/ImplPCTProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PA11/ImpPCTProcess.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace imp = "http://www.bcie.org/ImplementacionPctBO";

declare namespace ns4 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";

declare variable $InicioImplementacionPCT as element() (:: schema-element(ns1:InicioImplementacionPCT) ::) external;

declare function local:func($InicioImplementacionPCT as element() (:: schema-element(ns1:InicioImplementacionPCT) ::)) as element() (:: schema-element(ns2:CrearActualizarImplementacionRequest) ::) {
    <ns2:CrearActualizarImplementacionRequest>
        <ns2:Implementacion>
            <imp:nombre>{concat('Gestionado cliente ',fn:data($InicioImplementacionPCT/ns1:Header/ns3:Operacion/ns4:CodigoOperacion))}</imp:nombre>
            <imp:monto>
                <com:importe>0</com:importe>
            </imp:monto>
            <imp:estatus>{true()}</imp:estatus>
            <imp:tipoProceso>ABREVIADO</imp:tipoProceso>
            <imp:loteImpementacion>
                <imp:idLote>{fn:data($InicioImplementacionPCT/ns1:Header/ns3:Operacion/ns4:CodigoOperacion)}</imp:idLote>
            </imp:loteImpementacion>
        </ns2:Implementacion>
    </ns2:CrearActualizarImplementacionRequest>
};

local:func($InicioImplementacionPCT)
