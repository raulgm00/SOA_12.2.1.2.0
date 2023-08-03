xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://xmlns.oracle.com/bpmn/bpmnProcess/AprobacionProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PC04/AprobacionProcess.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/bpmn/bpmnProcess/SegCrediticioProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PA07/SegCrediticioProcess.xsd" ::)

declare namespace ns4 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";

declare namespace ns5 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Cliente/V1";

declare namespace ns6 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Tarea/V1";

declare namespace ns7 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Proceso/V1";

declare namespace ns8 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1";

declare namespace ns9 = "http://www.bcie.org/EqipoTrabajo/V1";

declare variable $FinSeguimientoCrediticio as element() (:: schema-element(ns1:FinSeguimientoCrediticio) ::) external;

declare function local:func($FinSeguimientoCrediticio as element() (:: schema-element(ns1:FinSeguimientoCrediticio) ::)) as element() (:: schema-element(ns2:InicioAprobacionCliente) ::) {
    <ns2:InicioAprobacionCliente>
        <ns2:Header>
            <ns3:Cliente>
                <ns5:IdCliente>{fn:data($FinSeguimientoCrediticio/ns1:Header/ns3:Cliente/ns5:IdCliente)}</ns5:IdCliente>
                <ns5:IdFlexCube>{fn:data($FinSeguimientoCrediticio/ns1:Header/ns3:Cliente/ns5:IdFlexCube)}</ns5:IdFlexCube>
                <ns5:RazonSocial>{fn:data($FinSeguimientoCrediticio/ns1:Header/ns3:Cliente/ns5:RazonSocial)}</ns5:RazonSocial>
                <ns5:ResponsableCliente>{fn:data($FinSeguimientoCrediticio/ns1:equipoTrabajo/ns9:equipoTrabajo[ns9:IdRol = 10][1]/ns9:usuario)}</ns5:ResponsableCliente>
            </ns3:Cliente>
            <ns8:ParameterType>
                <ns8:parameterName>ID_TIPO_APROBACION</ns8:parameterName>
                <ns8:parameterValue>3</ns8:parameterValue>
            </ns8:ParameterType>
            <ns8:ParameterType>
                <ns8:parameterName>INSTANCIA_SEGUIMIENTO</ns8:parameterName>
                <ns8:parameterValue>{fn:data($FinSeguimientoCrediticio/ns1:Header/ns3:Proceso/ns7:InstanciaProceso)}</ns8:parameterValue>
            </ns8:ParameterType>
        </ns2:Header>
    </ns2:InicioAprobacionCliente>
};

local:func($FinSeguimientoCrediticio)
