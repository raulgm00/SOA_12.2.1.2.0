xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/bpmn/bpmnProcess/DesembolsoProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PC06/DesembolsoProcess.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1";

declare variable $InicioDesembolso as element() (:: schema-element(ns1:InicioDesembolso) ::) external;

declare function local:func($InicioDesembolso as element() (:: schema-element(ns1:InicioDesembolso) ::)) as element() (:: schema-element(ns2:CrearActualizarDesembolsosRequest) ::) {
    <ns2:CrearActualizarDesembolsosRequest>
        <ns2:ContratoDesembolso>
            <des:idDesembolso>{fn:data($InicioDesembolso/ns1:Header/ns3:ParameterType[ns3:parameterName = 'ID_DESEMBOLSO']/ns3:parameterValue)}</des:idDesembolso>
           <des:estado>
                <cat:Id>14</cat:Id>
            </des:estado>
        </ns2:ContratoDesembolso>
    </ns2:CrearActualizarDesembolsosRequest>
};

local:func($InicioDesembolso)
