xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CrearActualizarOperacionProcesoMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/Utilidades/CrearActualizarOperacionProceso/V1/Schema/CrearActualizarOperacionProcesoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/bpmn/bpmnProcess/AdministrarOperacionProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PA16/AdministrarOperacionProcess.xsd" ::)

declare namespace cre = "http://www.bcie.org/CrearActualizarOperacionProcesoBO";

declare namespace ns4 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";

declare variable $IdOperacion as xs:string external;

declare function local:func($IdOperacion as xs:string) as element() (:: schema-element(ns1:CrearActualizarOperacionProcesoRequest) ::) {
    <ns1:CrearActualizarOperacionProcesoRequest>
        <ns1:OperacionProceso>
            <cre:idOperacion>{fn:data($IdOperacion)}</cre:idOperacion>
            <cre:idProceso>37</cre:idProceso>
            <cre:estatus>true</cre:estatus>
        </ns1:OperacionProceso>
    </ns1:CrearActualizarOperacionProcesoRequest>
};

local:func($IdOperacion)
