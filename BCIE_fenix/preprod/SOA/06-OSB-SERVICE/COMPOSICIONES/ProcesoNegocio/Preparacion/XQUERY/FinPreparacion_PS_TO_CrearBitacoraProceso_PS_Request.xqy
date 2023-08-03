xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/CrearBitacoraProcesoMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/Utilidades/BitacoraProceso/V1/Schema/BitacoraProcesoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/bpmn/bpmnProcess/PreparacionProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PC02/PreparacionProcess.xsd" ::)

declare namespace cre = "http://www.bcie.org/CrearBitacoraProcesoBO";

declare namespace ns4 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";

declare namespace ns5 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Tarea/V1";

declare namespace ns6 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Proceso/V1";

declare variable $FinPreparacion as element() (:: schema-element(ns1:FinPreparacion) ::) external;

declare function local:func($FinPreparacion as element() (:: schema-element(ns1:FinPreparacion) ::)) as element() (:: schema-element(ns2:requestCrearBitacoraProcesoMessage) ::) {
    <ns2:requestCrearBitacoraProcesoMessage>
        <ns2:BitacoraInput>
            <cre:id></cre:id>
            <cre:idOperacion>{fn:data($FinPreparacion/ns1:Header/ns3:Operacion/ns4:CodigoOperacion)}</cre:idOperacion>
            <cre:nombreOperacion>{fn:data($FinPreparacion/ns1:Header/ns3:Operacion/ns4:NombreOperacion)}</cre:nombreOperacion>
            <cre:idProceso>2</cre:idProceso>
            <cre:nombreProceso>PC02</cre:nombreProceso>
            <cre:instanciaProceso>{fn:data($FinPreparacion/ns1:Header/ns3:Proceso/ns6:InstanciaProceso)}</cre:instanciaProceso>
            <cre:idProcesoSiguiente></cre:idProcesoSiguiente>
             <cre:esProceso>{true()}</cre:esProceso>
            <cre:idTarea></cre:idTarea>
            <cre:nombreTarea></cre:nombreTarea>
            <cre:instanciaTarea></cre:instanciaTarea>
            <cre:usuario>{fn:data($FinPreparacion/ns1:Header/ns3:Operacion/ns4:ResponsableOperacion)}</cre:usuario>
            <cre:nombreUsuario></cre:nombreUsuario>
                  <cre:esFinActividad>{true()}</cre:esFinActividad>
            <cre:fechaRegistro>{fn:current-dateTime()}</cre:fechaRegistro>
            <cre:tiempoProceso></cre:tiempoProceso>
            <cre:tiempoTarea></cre:tiempoTarea>
            <cre:raroc></cre:raroc>
            <cre:string01></cre:string01>
            <cre:string02></cre:string02>
            <cre:string03></cre:string03>
        </ns2:BitacoraInput>
    </ns2:requestCrearBitacoraProcesoMessage>
};

local:func($FinPreparacion)