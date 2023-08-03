xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns0="http://www.bcie.org/ClienteMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioCliente/Cliente/V1/Schema/ClienteMO.xsd" ::)
declare namespace ges="http://www.bcie.org/GestionCobroMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioCliente/GestionCobro/V1/Schema/GestionCobroMO.xsd" ::)
declare namespace ges1="http://xmlns.oracle.com/bpmn/bpmnProcess/GestionCobroProcess";
(:: import schema at "oramds:/apps/Resources/BPM/Schema/PC07/GestionCobroProcess.xsd" ::)

declare namespace ns2 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1";

declare namespace ns1 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";

declare namespace ns4 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Tarea/V1";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Cliente/V1";

declare namespace ns5 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Proceso/V1";

declare namespace ns6 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace ges2 = "http://www.bcie.org/GestionCobroBO";

declare namespace com = "http://www.bcie.org/CommonBO";


declare variable $outInvokeConsultarClienteById.response as element() (:: schema-element(ns0:ConsultarClienteResponse) ::) external;
declare variable $outInvokeGenerarArchivoFenixV2.GenerarArchivosFenixV2Response as element() (:: schema-element(ges:GenerarArchivosFenixV2Response) ::) external;

declare function local:funcXqiniciargestioncobrov2($outInvokeConsultarClienteById.response as element() (:: schema-element(ns0:ConsultarClienteResponse) ::), 
                                                   $outInvokeGenerarArchivoFenixV2.GenerarArchivosFenixV2Response as element() (:: schema-element(ges:GenerarArchivosFenixV2Response) ::)) 
                                                   as element() (:: schema-element(ges1:InicioGestionCobro) ::) {
    <ges1:InicioGestionCobro>
        <ges1:Header>
            <ns1:Cliente>
                <ns3:IdCliente>{fn:data($outInvokeConsultarClienteById.response/ns0:Cliente/cli:idCliente)}</ns3:IdCliente>
                <ns3:RazonSocial>{fn:data($outInvokeConsultarClienteById.response/ns0:Cliente/cli:razonSocial)}</ns3:RazonSocial>
                <ns3:ResponsableCliente>{fn:data($outInvokeConsultarClienteById.response/ns0:Cliente/cli:responsableCliente)}</ns3:ResponsableCliente>
            </ns1:Cliente>
            <ns6:ParameterType>
                    <ns6:parameterName>TIPO_INICIO</ns6:parameterName>
                    <ns6:parameterValue>AUTOMATICO</ns6:parameterValue>
            </ns6:ParameterType>
            {
                for $Parameters in $outInvokeGenerarArchivoFenixV2.GenerarArchivosFenixV2Response/ges:Parameters
                return 
                <ns6:ParameterType>
                    <ns6:parameterName>{fn:data($Parameters/com:name)}</ns6:parameterName>
                    <ns6:parameterValue>{fn:data($Parameters/com:value)}</ns6:parameterValue>
                </ns6:ParameterType>
            }
        </ges1:Header>
    </ges1:InicioGestionCobro>
};

local:funcXqiniciargestioncobrov2($outInvokeConsultarClienteById.response, $outInvokeGenerarArchivoFenixV2.GenerarArchivosFenixV2Response)
