xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/CrearBitacoraProcesoMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/Utilidades/BitacoraProceso/V1/Schema/BitacoraProcesoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/bpmn/bpmnProcess/PrepagoProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PA12/PrepagoProcess.xsd" ::)

declare namespace cre = "http://www.bcie.org/CrearBitacoraProcesoBO";

declare namespace ns4 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";
declare namespace ns6 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1";

declare namespace ns5 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Proceso/V1";

declare variable $InicioPrepago as element() (:: schema-element(ns1:InicioPrepago) ::) external;

declare function local:func($InicioPrepago as element() (:: schema-element(ns1:InicioPrepago) ::)) as element() (:: schema-element(ns2:requestCrearBitacoraProcesoMessage) ::) {
    <ns2:requestCrearBitacoraProcesoMessage>
        <ns2:BitacoraInput>
            <cre:id></cre:id>
            <cre:idOperacion>{fn:data($InicioPrepago/ns1:Header/ns3:Operacion/ns4:CodigoOperacion)}</cre:idOperacion>
            <cre:nombreOperacion>{fn:data($InicioPrepago/ns1:Header/ns3:Operacion/ns4:NombreOperacion)}</cre:nombreOperacion>
            <cre:idProceso>26</cre:idProceso>
            <cre:nombreProceso>PA12</cre:nombreProceso>
            <cre:esProceso>{fn:true()}</cre:esProceso>
            <cre:usuario>{fn:data($InicioPrepago/ns1:Header/ns3:Operacion/ns4:ResponsableOperacion)}</cre:usuario>
            <cre:nombreUsuario></cre:nombreUsuario>
            <cre:esFinActividad>{fn:false()}</cre:esFinActividad>
            <cre:fechaRegistro>{fn:current-dateTime()}</cre:fechaRegistro>
            <cre:finalizado>{fn:false()}</cre:finalizado>
            {if(fn:count($InicioPrepago/ns1:Header/ns6:ParameterType) > 0)then
            <cre:string01>{fn:concat($InicioPrepago/ns1:Header/ns6:ParameterType[1]/ns6:parameterName,'-', $InicioPrepago/ns1:Header/ns6:ParameterType[1]/ns6:parameterValue[1])}</cre:string01>
            else()
            }
            {if(fn:count($InicioPrepago/ns1:Header/ns6:ParameterType) > 1)then
            <cre:string02>{fn:concat($InicioPrepago/ns1:Header/ns6:ParameterType[2]/ns6:parameterName,'-', $InicioPrepago/ns1:Header/ns6:ParameterType[2]/ns6:parameterValue)}</cre:string02>
            else()
            }
            {if(fn:count($InicioPrepago/ns1:Header/ns6:ParameterType) > 2)then
            <cre:string03>{fn:concat($InicioPrepago/ns1:Header/ns6:ParameterType[3]/ns6:parameterName,'-', $InicioPrepago/ns1:Header/ns6:ParameterType[3]/ns6:parameterValue)}</cre:string03>
            else()
            }
        </ns2:BitacoraInput>
    </ns2:requestCrearBitacoraProcesoMessage>
};

local:func($InicioPrepago)
