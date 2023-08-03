xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/bpmn/bpmnProcess/DesembolsoProcess";
(:: import schema at "../../../../../MDS/Resources/BPM/Schema/PC06/DesembolsoProcess.xsd" ::)

declare namespace ns6 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Tarea/V1";

declare namespace ns5 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Cliente/V1";

declare namespace ns8 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1";

declare namespace ns7 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Proceso/V1";

declare namespace ns4 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";

declare variable $InicioCancelarInstanciaRequest as element() (:: schema-element(ns1:InicioCancelarInstanciaRequest) ::) external;

declare function local:func($InicioCancelarInstanciaRequest as element() (:: schema-element(ns1:InicioCancelarInstanciaRequest) ::)) as element() (:: schema-element(ns2:InicioCancelarInstancia) ::) {
    <ns2:InicioCancelarInstancia>
        <ns2:Header>
           <ns3:Operacion>
                <ns4:CodigoOperacion>{fn:data($InicioCancelarInstanciaRequest/ns1:Header/ns3:Operacion/ns4:CodigoOperacion)}</ns4:CodigoOperacion>
                <ns4:ResponsableOperacion>{fn:data($InicioCancelarInstanciaRequest/ns1:Header/ns3:Operacion/ns4:ResponsableOperacion)}</ns4:ResponsableOperacion>
            </ns3:Operacion>
            <ns3:Proceso>
                <ns7:CodigoProceso>11</ns7:CodigoProceso>
            </ns3:Proceso>
            <ns8:ParameterType>
                <ns8:parameterName>ID_SOLICITUD</ns8:parameterName>
                <ns8:parameterValue>{fn:data($InicioCancelarInstanciaRequest/ns1:Header/ns8:ParameterType[ns8:parameterName = 'ID_SOLICITUD']/ns8:parameterValue)}</ns8:parameterValue>
            </ns8:ParameterType>
            <ns8:ParameterType>
                <ns8:parameterName>TIPO_DESEMBOLSO</ns8:parameterName>
                <ns8:parameterValue>2</ns8:parameterValue>
            </ns8:ParameterType>
        </ns2:Header>
    </ns2:InicioCancelarInstancia>
};

local:func($InicioCancelarInstanciaRequest)
