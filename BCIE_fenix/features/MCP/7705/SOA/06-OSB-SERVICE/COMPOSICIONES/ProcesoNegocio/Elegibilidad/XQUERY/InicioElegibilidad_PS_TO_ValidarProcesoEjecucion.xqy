xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ValidarOperacionProcesoMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/Utilidades/ValidarOperacionProceso/V1/Schema/ValidarOperacionProcesoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/bpmn/bpmnProcess/ElegibilidadProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PC01/ElegibilidadProcess.xsd" ::)

declare namespace val = "http://www.bcie.org/ValidarOperacionProcesoBO";

declare namespace ns4 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";

declare variable $InicioElegibilidad as element() (:: schema-element(ns1:InicioElegibilidad) ::) external;

declare function local:func($InicioElegibilidad as element() (:: schema-element(ns1:InicioElegibilidad) ::)) as element() (:: schema-element(ns2:requestValidarOperacionProceso) ::) {
    <ns2:requestValidarOperacionProceso>
        <ns2:ValidarProcesoOperacionInput>
            <val:idOperacion>{fn:data($InicioElegibilidad/ns1:Header/ns3:Operacion/ns4:CodigoOperacion)}</val:idOperacion>
            <val:idProceso>1</val:idProceso>
        </ns2:ValidarProcesoOperacionInput>
    </ns2:requestValidarOperacionProceso>
};

local:func($InicioElegibilidad)
