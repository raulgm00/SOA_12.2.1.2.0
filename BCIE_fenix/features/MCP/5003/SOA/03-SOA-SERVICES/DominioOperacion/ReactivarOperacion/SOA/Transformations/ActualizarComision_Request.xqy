xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace com1="http://www.bcie.org/ComisionMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioLineaCredito/Comision/V1/Schema/ComisionMO.xsd" ::)
declare namespace com="http://xmlns.oracle.com/bpmn/bpmnProcess/ComisionesProcess";
(:: import schema at "oramds:/apps/Resources/BPM/Schema/PA02/ComisionesProcess.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com2 = "http://www.bcie.org/ComisionBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace ns1 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1";

declare namespace ns2 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1";

declare variable $InComisiones.parameters as element() (:: schema-element(com:InicioComisiones) ::) external;

declare function local:funcActualizarcomision_request($InComisiones.parameters as element() (:: schema-element(com:InicioComisiones) ::)) as element() (:: schema-element(com1:ActualizarComisionRequest) ::) {
    <com1:ActualizarComisionRequest>
    {
    for $parameterType in $InComisiones.parameters/com:Header/ns1:ParameterType
    where $parameterType/ns1:parameterName = 'IDCOMISION' 
    return
        <com1:Comision>
            <com2:idComision>{xs:integer(fn:data($parameterType/ns1:parameterValue))}</com2:idComision>
            <com2:idOperacion>{fn:data($InComisiones.parameters/com:Header/ns2:Operacion/ns3:CodigoOperacion)}</com2:idOperacion>
            <com2:subEstadoTCC>
                <atr:id>2</atr:id>
            </com2:subEstadoTCC>
        </com1:Comision>
        }
    </com1:ActualizarComisionRequest>
};

local:funcActualizarcomision_request($InComisiones.parameters)
