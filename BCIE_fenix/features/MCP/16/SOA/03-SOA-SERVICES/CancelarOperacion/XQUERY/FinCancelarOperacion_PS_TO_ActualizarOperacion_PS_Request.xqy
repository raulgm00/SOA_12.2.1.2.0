xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/OperacionMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Operacion/V1/Schema/OperacionMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/bpmn/bpmnProcess/CancelarOperacionProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PA05/CancelarOperacionProcess.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace dec = "http://www.bcie.org/DeclaracionJuradaBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace ns4 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";

declare namespace ns5 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1";

declare variable $FinCancelarOperacion as element() (:: schema-element(ns1:FinCancelarOperacion) ::) external;

declare function local:func($FinCancelarOperacion as element() (:: schema-element(ns1:FinCancelarOperacion) ::)) as element() (:: schema-element(ns2:ActualizarOperacionRequest) ::) {
    <ns2:ActualizarOperacionRequest>
        <ns2:Operacion>
            <ope:idOperacion>{fn:data($FinCancelarOperacion/ns1:Header/ns3:Operacion/ns4:CodigoOperacion)}</ope:idOperacion>     
            {
            for $parameter in $FinCancelarOperacion/ns1:Header/ns5:ParameterType
            where $parameter/ns5:parameterName = 'TIPO_SOLICITUD' return
            <ope:estado>
                <cat:Id>{if(fn:data($parameter/ns5:parameterValue)= '1')
                then(2)
                else(4)}</cat:Id>
            </ope:estado>
            }
        </ns2:Operacion>
    </ns2:ActualizarOperacionRequest>
};

local:func($FinCancelarOperacion)
