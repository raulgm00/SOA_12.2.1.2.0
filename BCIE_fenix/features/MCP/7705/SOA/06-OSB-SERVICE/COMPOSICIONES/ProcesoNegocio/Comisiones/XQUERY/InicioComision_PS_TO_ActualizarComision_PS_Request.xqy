xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ComisionMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/Comision/V1/Schema/ComisionMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/bpmn/bpmnProcess/ComisionesProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PA02/ComisionesProcess.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com = "http://www.bcie.org/ComisionBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1";

declare variable $InicioComisiones as element() (:: schema-element(ns1:InicioComisiones) ::) external;

declare function local:func($InicioComisiones as element() (:: schema-element(ns1:InicioComisiones) ::)) as element() (:: schema-element(ns2:ActualizarComisionRequest) ::) {
    <ns2:ActualizarComisionRequest>
    {
    for $parameter in $InicioComisiones/ns1:Header/ns3:ParameterType
    where fn:data($parameter/ns3:parameterName) = 'IDCOMISION' return
        <ns2:Comision>
            <com:idComision>{fn:data($parameter/ns3:parameterValue)}</com:idComision>
            <com:subEstadoTCC>
                <atr:id>21</atr:id>
            </com:subEstadoTCC>
        </ns2:Comision>
    }
    </ns2:ActualizarComisionRequest>
};

local:func($InicioComisiones)
