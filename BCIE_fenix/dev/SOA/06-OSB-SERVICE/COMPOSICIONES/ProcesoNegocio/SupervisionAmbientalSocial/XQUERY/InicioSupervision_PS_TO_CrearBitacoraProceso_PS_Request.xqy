xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/CrearBitacoraProcesoMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/Utilidades/BitacoraProceso/V1/Schema/BitacoraProcesoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/bpmn/bpmnProcess/SupervisionAmbientalSocialProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PA18/SupervisionAmbientalSocialProcess.xsd" ::)

declare namespace cre = "http://www.bcie.org/CrearBitacoraProcesoBO";

declare namespace ns4 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";

declare namespace ns5 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Proceso/V1";

declare namespace ns6 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1";

declare variable $InicioSupervision as element() (:: schema-element(ns1:InicioSupervision) ::) external;

declare function local:func($InicioSupervision as element() (:: schema-element(ns1:InicioSupervision) ::)) as element() (:: schema-element(ns2:requestCrearBitacoraProcesoMessage) ::) {
    <ns2:requestCrearBitacoraProcesoMessage>
        <ns2:BitacoraInput>
            <cre:id></cre:id>
            <cre:idOperacion>{fn:data($InicioSupervision/ns1:Header/ns3:Operacion/ns4:CodigoOperacion)}</cre:idOperacion>
            <cre:nombreOperacion>{fn:data($InicioSupervision/ns1:Header/ns3:Operacion/ns4:NombreOperacion)}</cre:nombreOperacion>
            <cre:idProceso>39</cre:idProceso>
            <cre:nombreProceso>PA18</cre:nombreProceso>
            <cre:esProceso>{fn:true()}</cre:esProceso>
            <cre:usuario>{fn:data($InicioSupervision/ns1:Header/ns3:Operacion/ns4:ResponsableOperacion)}</cre:usuario>
            <cre:nombreUsuario></cre:nombreUsuario>
            <cre:esFinActividad>{fn:false()}</cre:esFinActividad>
            <cre:fechaRegistro>{fn:current-date()}</cre:fechaRegistro>
            {
            if(count($InicioSupervision/ns1:Header/ns6:ParameterType/ns6:parameterName)>0)then
            <cre:string01>{fn:concat($InicioSupervision/ns1:Header/ns6:ParameterType[1]/ns6:parameterName,'-', $InicioSupervision/ns1:Header/ns6:ParameterType[1]/ns6:parameterValue[1])}</cre:string01>
            else()}
            {
            if(count($InicioSupervision/ns1:Header/ns6:ParameterType/ns6:parameterName)>1)then
            <cre:string02>{fn:concat($InicioSupervision/ns1:Header/ns6:ParameterType[2]/ns6:parameterName,'-', $InicioSupervision/ns1:Header/ns6:ParameterType[2]/ns6:parameterValue)}</cre:string02>
            else()}
            {
            if(count($InicioSupervision/ns1:Header/ns6:ParameterType/ns6:parameterName)>2)then
            <cre:string03>{fn:concat($InicioSupervision/ns1:Header/ns6:ParameterType[3]/ns6:parameterName,'-', $InicioSupervision/ns1:Header/ns6:ParameterType[3]/ns6:parameterValue)}</cre:string03>
            else()}           
        </ns2:BitacoraInput>
    </ns2:requestCrearBitacoraProcesoMessage>
};

local:func($InicioSupervision)
