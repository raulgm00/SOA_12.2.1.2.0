xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/NotificarCancelarOperacionMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/Utilidades/NotificarCancelarOperacion/V1/Schema/NotificarCancelarOperacionMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/bpmn/bpmnProcess/CancelarOperacionProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PA05/CancelarOperacionProcess.xsd" ::)

declare namespace not = "http://www.bcie.org/NotificarCancelarOperacionBO";

declare namespace ns4 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";

declare variable $FinCancelarOperacion as element() (:: schema-element(ns1:FinCancelarOperacion) ::) external;

declare function local:func($FinCancelarOperacion as element() (:: schema-element(ns1:FinCancelarOperacion) ::)) as element() (:: schema-element(ns2:requestNotificarCancelarOperacion) ::) {
    <ns2:requestNotificarCancelarOperacion>
        <ns2:Operacion>
            <not:idOperacion>{fn:data($FinCancelarOperacion/ns1:Header/ns3:Operacion/ns4:CodigoOperacion)}</not:idOperacion>
        </ns2:Operacion>
    </ns2:requestNotificarCancelarOperacion>
};

local:func($FinCancelarOperacion)
