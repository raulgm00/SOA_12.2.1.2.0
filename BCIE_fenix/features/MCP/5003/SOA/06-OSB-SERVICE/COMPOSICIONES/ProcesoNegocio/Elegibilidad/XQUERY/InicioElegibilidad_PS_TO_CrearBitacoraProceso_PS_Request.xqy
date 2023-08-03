xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/CrearBitacoraProcesoMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/Utilidades/BitacoraProceso/V1/Schema/BitacoraProcesoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/bpmn/bpmnProcess/ElegibilidadProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PC01/ElegibilidadProcess.xsd" ::)

declare namespace cre = "http://www.bcie.org/CrearBitacoraProcesoBO";

declare namespace ns4 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";

declare namespace ns5 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Tarea/V1";

declare namespace ns6 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1";

declare namespace ns7 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Proceso/V1";

declare variable $InicioElegibilidad as element() (:: schema-element(ns1:InicioElegibilidad) ::) external;

declare function local:func($InicioElegibilidad as element() (:: schema-element(ns1:InicioElegibilidad) ::)) as element() (:: schema-element(ns2:requestCrearBitacoraProcesoMessage) ::) {
    <ns2:requestCrearBitacoraProcesoMessage>
        <ns2:BitacoraInput>
            <cre:idOperacion>{fn:data($InicioElegibilidad/ns1:Header/ns3:Operacion/ns4:CodigoOperacion)}</cre:idOperacion>
            <cre:nombreOperacion>{fn:data($InicioElegibilidad/ns1:Header/ns3:Operacion/ns4:NombreOperacion)}</cre:nombreOperacion>
            <cre:idProceso>1</cre:idProceso>
            <cre:nombreProceso>PC01</cre:nombreProceso>
            <cre:idProcesoSiguiente></cre:idProcesoSiguiente>
            <cre:esProceso>{true()}</cre:esProceso>
            <cre:idTarea>{fn:data($InicioElegibilidad/ns1:Header/ns3:Tarea/ns5:CodigoTarea)}</cre:idTarea>
            <cre:nombreTarea></cre:nombreTarea>
            <cre:instanciaTarea></cre:instanciaTarea>
            <cre:usuario>{fn:data($InicioElegibilidad/ns1:Header/ns3:Operacion/ns4:ResponsableOperacion)}</cre:usuario>
            <cre:nombreUsuario></cre:nombreUsuario>
            <cre:esFinActividad>{false()}</cre:esFinActividad>
            <cre:fechaRegistro>{fn:current-dateTime()}</cre:fechaRegistro>
            <cre:tiempoProceso></cre:tiempoProceso>
            <cre:tiempoTarea></cre:tiempoTarea>
            <cre:raroc></cre:raroc>
            <cre:finalizado>{false()}</cre:finalizado>
            {
            if(count($InicioElegibilidad/ns1:Header/ns6:ParameterType)>0)then
            <cre:string01>{fn:concat($InicioElegibilidad/ns1:Header/ns6:ParameterType[1]/ns6:parameterName,'-', $InicioElegibilidad/ns1:Header/ns6:ParameterType[1]/ns6:parameterValue[1])}</cre:string01>
            else()}
            {
            if(count($InicioElegibilidad/ns1:Header/ns6:ParameterType)>1)then
            <cre:string02>{fn:concat($InicioElegibilidad/ns1:Header/ns6:ParameterType[2]/ns6:parameterName,'-', $InicioElegibilidad/ns1:Header/ns6:ParameterType[2]/ns6:parameterValue)}</cre:string02>
            else()}
            {
            if(count($InicioElegibilidad/ns1:Header/ns6:ParameterType)>2)then
            <cre:string03>{fn:concat($InicioElegibilidad/ns1:Header/ns6:ParameterType[3]/ns6:parameterName,'-', $InicioElegibilidad/ns1:Header/ns6:ParameterType[3]/ns6:parameterValue)}</cre:string03>
            else()}
            <cre:string02></cre:string02>
            <cre:string03></cre:string03>
        </ns2:BitacoraInput>
    </ns2:requestCrearBitacoraProcesoMessage>
};

local:func($InicioElegibilidad)