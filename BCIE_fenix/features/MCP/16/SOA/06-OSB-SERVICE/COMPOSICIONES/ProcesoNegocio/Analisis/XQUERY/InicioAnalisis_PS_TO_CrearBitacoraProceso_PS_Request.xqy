xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/CrearBitacoraProcesoMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/Utilidades/BitacoraProceso/V1/Schema/BitacoraProcesoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/bpmn/bpmnProcess/AnalisisProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PC03/AnalisisProcess.xsd" ::)

declare namespace cre = "http://www.bcie.org/CrearBitacoraProcesoBO";

declare namespace ns4 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";

declare namespace ns5 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Tarea/V1";

declare namespace ns6 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1";

declare namespace ns7 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Proceso/V1";

declare variable $InicioAnalisis as element() (:: schema-element(ns1:InicioAnalisis) ::) external;

declare function local:func($InicioAnalisis as element() (:: schema-element(ns1:InicioAnalisis) ::)) as element() (:: schema-element(ns2:requestCrearBitacoraProcesoMessage) ::) {
    <ns2:requestCrearBitacoraProcesoMessage>
        <ns2:BitacoraInput>
            <cre:idOperacion>{fn:data($InicioAnalisis/ns1:Header/ns3:Operacion/ns4:CodigoOperacion)}</cre:idOperacion>
            <cre:nombreOperacion>{fn:data($InicioAnalisis/ns1:Header/ns3:Operacion/ns4:NombreOperacion)}</cre:nombreOperacion>
            <cre:idProceso>3</cre:idProceso>
            <cre:nombreProceso>PC03</cre:nombreProceso>
            <cre:idProcesoSiguiente></cre:idProcesoSiguiente>
            <cre:esProceso>{true()}</cre:esProceso>
            <cre:idTarea></cre:idTarea>
            <cre:nombreTarea></cre:nombreTarea>
            <cre:instanciaTarea></cre:instanciaTarea>
            <cre:usuario>{fn:data($InicioAnalisis/ns1:Header/ns3:Operacion/ns4:ResponsableOperacion)}</cre:usuario>
            <cre:nombreUsuario></cre:nombreUsuario>
            <cre:esFinActividad>{false()}</cre:esFinActividad>
            <cre:fechaRegistro>{fn:current-dateTime()}</cre:fechaRegistro>
            <cre:tiempoProceso></cre:tiempoProceso>
            <cre:tiempoTarea></cre:tiempoTarea>
            <cre:raroc></cre:raroc>
            {
            if(count($InicioAnalisis/ns1:Header/ns6:ParameterType)>0)then
            <cre:string01>{fn:concat($InicioAnalisis/ns1:Header/ns6:ParameterType[1]/ns6:parameterName,'-', $InicioAnalisis/ns1:Header/ns6:ParameterType[1]/ns6:parameterValue[1])}</cre:string01>
            else()}
            {
            if(count($InicioAnalisis/ns1:Header/ns6:ParameterType)>1)then
            <cre:string02>{fn:concat($InicioAnalisis/ns1:Header/ns6:ParameterType[2]/ns6:parameterName,'-', $InicioAnalisis/ns1:Header/ns6:ParameterType[2]/ns6:parameterValue)}</cre:string02>
            else()}
            {
            if(count($InicioAnalisis/ns1:Header/ns6:ParameterType)>2)then
            <cre:string03>{fn:concat($InicioAnalisis/ns1:Header/ns6:ParameterType[3]/ns6:parameterName,'-', $InicioAnalisis/ns1:Header/ns6:ParameterType[3]/ns6:parameterValue)}</cre:string03>
            else()}
        </ns2:BitacoraInput>
    </ns2:requestCrearBitacoraProcesoMessage>
};

local:func($InicioAnalisis)