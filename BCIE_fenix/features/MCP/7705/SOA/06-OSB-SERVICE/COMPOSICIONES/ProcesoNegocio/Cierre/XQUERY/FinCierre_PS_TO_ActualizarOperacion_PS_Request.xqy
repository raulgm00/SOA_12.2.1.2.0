xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/OperacionMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Operacion/V1/Schema/OperacionMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/bpmn/bpmnProcess/CierreProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PC08/CierreProcess.xsd" ::)

declare namespace con = "http://www.bcie.org/ContratoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace dec = "http://www.bcie.org/DeclaracionJuradaBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace reg = "http://www.bcie.org/ReglaBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare namespace ter = "http://www.bcie.org/TerminoBO";

declare namespace con1 = "http://www.bcie.org/CondicionBO";

declare namespace ns4 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";

declare namespace ns5 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1";

declare variable $FinCierreRequest as element() (:: schema-element(ns1:FinCierre) ::) external;

declare function local:func($FinCierreRequest as element() (:: schema-element(ns1:FinCierre) ::)) as element() (:: schema-element(ns2:ActualizarOperacionRequest) ::) {
    <ns2:ActualizarOperacionRequest>
        <ns2:Operacion>
            <ope:idOperacion>{fn:data($FinCierreRequest/ns1:Header/ns3:Operacion/ns4:CodigoOperacion)}</ope:idOperacion>
            <ope:estado>
                <cat:Id>27</cat:Id>
                <cat:Descripcion>Cerrada</cat:Descripcion>
                <cat:DescripcionCorta>Cerrada</cat:DescripcionCorta>
                <cat:estatus>1</cat:estatus>
                <cat:codigoExterno>0</cat:codigoExterno>
            </ope:estado>
        </ns2:Operacion>
    </ns2:ActualizarOperacionRequest>
};

local:func($FinCierreRequest)
