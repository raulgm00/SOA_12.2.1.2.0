xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/FinalizarOperacionProcesoMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/Utilidades/FinalizarOperacionProceso/V1/Schema/FinalizarOperacionProcesoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/bpmn/bpmnProcess/LeccionesAprendidasProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PA17/LeccionesAprendidasProcess.xsd" ::)

declare namespace fin = "http://www.bcie.org/FinalizarOperacionProcesoBO";

declare namespace ns4 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";

declare variable $FinLeccionesAprendidas as element() (:: schema-element(ns1:FinLeccionesAprendidas) ::) external;

declare function local:func($FinLeccionesAprendidas as element() (:: schema-element(ns1:FinLeccionesAprendidas) ::)) as element() (:: schema-element(ns2:requestFinalizarOperacionProcesoMessage) ::) {
    <ns2:requestFinalizarOperacionProcesoMessage>
        <ns2:BitacoraInput>
            <fin:idOperacion>{fn:data($FinLeccionesAprendidas/ns1:Header/ns3:Operacion/ns4:CodigoOperacion)}</fin:idOperacion>
            <fin:idProceso>38</fin:idProceso>
        </ns2:BitacoraInput>
    </ns2:requestFinalizarOperacionProcesoMessage>
};

local:func($FinLeccionesAprendidas)
