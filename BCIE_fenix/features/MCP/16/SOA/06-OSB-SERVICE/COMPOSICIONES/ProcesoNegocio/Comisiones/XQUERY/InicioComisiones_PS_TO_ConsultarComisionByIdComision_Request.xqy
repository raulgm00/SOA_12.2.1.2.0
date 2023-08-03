xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ComisionMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/Comision/V1/Schema/ComisionMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/bpmn/bpmnProcess/ComisionesProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PA02/ComisionesProcess.xsd" ::)

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1";

declare variable $InicioComisiones as element() (:: schema-element(ns1:InicioComisiones) ::) external;

declare function local:func($InicioComisiones as element() (:: schema-element(ns1:InicioComisiones) ::)) as element() (:: schema-element(ns2:ConsultarComisionRequest) ::) {

    <ns2:ConsultarComisionRequest>
    {
    for $parameters in $InicioComisiones/ns1:Header/ns3:ParameterType
    where fn:data($parameters/ns3:parameterName) = 'IDCOMISION'
    return
        <ns2:idComision>{fn:data($parameters/ns3:parameterValue)}</ns2:idComision>
    }    
    </ns2:ConsultarComisionRequest>
};

local:func($InicioComisiones)
