xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/CrearBitacoraProcesoMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/Utilidades/BitacoraProceso/V1/Schema/BitacoraProcesoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/bpmn/bpmnProcess/EvaluacionesProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PA10/EvaluacionProcess.xsd" ::)

declare namespace cre = "http://www.bcie.org/CrearBitacoraProcesoBO";

declare namespace ns4 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";

declare namespace ns5 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Tarea/V1";

declare namespace ns6 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1";

declare namespace ns7 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Proceso/V1";

declare variable $InicioEvaluacion as element() (:: schema-element(ns1:InicioEvaluacion) ::) external;

declare function local:func($InicioEvaluacion as element() (:: schema-element(ns1:InicioEvaluacion) ::)) as element() (:: schema-element(ns2:requestCrearBitacoraProcesoMessage) ::) {
    <ns2:requestCrearBitacoraProcesoMessage>
        <ns2:BitacoraInput>
            <cre:idOperacion>{fn:data($InicioEvaluacion/ns1:Header/ns3:Operacion/ns4:CodigoOperacion)}</cre:idOperacion>
            <cre:nombreOperacion>{fn:data($InicioEvaluacion/ns1:Header/ns3:Operacion/ns4:NombreOperacion)}</cre:nombreOperacion>
            <cre:idProceso>6</cre:idProceso>
            <cre:nombreProceso>PA10</cre:nombreProceso>
            <cre:idProcesoSiguiente></cre:idProcesoSiguiente>
            <cre:esProceso>{true()}</cre:esProceso>
            <cre:idTarea></cre:idTarea>
            <cre:nombreTarea></cre:nombreTarea>
            <cre:instanciaTarea></cre:instanciaTarea>
            <cre:usuario>{fn:data($InicioEvaluacion/ns1:Header/ns3:Operacion/ns4:ResponsableOperacion)}</cre:usuario>
            <cre:nombreUsuario></cre:nombreUsuario>
            <cre:esFinActividad>{false()}</cre:esFinActividad>
            <cre:fechaRegistro>{fn:current-dateTime()}</cre:fechaRegistro>
            <cre:tiempoProceso></cre:tiempoProceso>
            <cre:tiempoTarea></cre:tiempoTarea>
            <cre:raroc></cre:raroc>
            <cre:finalizado>{false()}</cre:finalizado>
            {
            if(count($InicioEvaluacion/ns1:Header/ns6:ParameterType)>0)then
            <cre:string01>{fn:concat($InicioEvaluacion/ns1:Header/ns6:ParameterType[1]/ns6:parameterName,'-', $InicioEvaluacion/ns1:Header/ns6:ParameterType[1]/ns6:parameterValue[1])}</cre:string01>
            else()}
            {
            if(count($InicioEvaluacion/ns1:Header/ns6:ParameterType)>1)then
            <cre:string02>{fn:concat($InicioEvaluacion/ns1:Header/ns6:ParameterType[2]/ns6:parameterName,'-', $InicioEvaluacion/ns1:Header/ns6:ParameterType[2]/ns6:parameterValue)}</cre:string02>
            else()}
            {
            if(count($InicioEvaluacion/ns1:Header/ns6:ParameterType)>2)then
            <cre:string03>{fn:concat($InicioEvaluacion/ns1:Header/ns6:ParameterType[3]/ns6:parameterName,'-', $InicioEvaluacion/ns1:Header/ns6:ParameterType[3]/ns6:parameterValue)}</cre:string03>
            else()}
        </ns2:BitacoraInput>
    </ns2:requestCrearBitacoraProcesoMessage>
};

local:func($InicioEvaluacion)