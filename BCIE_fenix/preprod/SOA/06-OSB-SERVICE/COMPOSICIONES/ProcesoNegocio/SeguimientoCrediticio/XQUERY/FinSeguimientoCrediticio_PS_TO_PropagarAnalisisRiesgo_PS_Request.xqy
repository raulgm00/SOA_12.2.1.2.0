xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ClienteMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/DominioCliente/Cliente/V1/Schema/ClienteMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/bpmn/bpmnProcess/SegCrediticioProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PA07/SegCrediticioProcess.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace ns4 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Cliente/V1";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";

declare variable $FinSeguimientoCrediticio as element() (:: schema-element(ns1:FinSeguimientoCrediticio) ::) external;

declare function local:func($FinSeguimientoCrediticio as element() (:: schema-element(ns1:FinSeguimientoCrediticio) ::)) as element() (:: schema-element(ns2:PropagarAnalisisRiesgoRequest) ::) {
    <ns2:PropagarAnalisisRiesgoRequest>
        <ns2:Cliente>
            <cli:idCliente>{fn:data($FinSeguimientoCrediticio/ns1:Header/ns3:Cliente/ns4:IdCliente)}</cli:idCliente>
            <cli:idFacturador>{fn:data($FinSeguimientoCrediticio/ns1:Header/ns3:Cliente/ns4:IdFlexCube)}</cli:idFacturador>
            <cli:SRC>
                <cat:Id></cat:Id>
                <cat:Descripcion></cat:Descripcion>
                <cat:DescripcionCorta></cat:DescripcionCorta>
                <cat:estatus></cat:estatus>
                <cat:codigoExterno></cat:codigoExterno>
            </cli:SRC>
            <cli:perspectiva>
                <cat:Id></cat:Id>
                <cat:Descripcion></cat:Descripcion>
                <cat:DescripcionCorta></cat:DescripcionCorta>
                <cat:estatus></cat:estatus>
                <cat:codigoExterno></cat:codigoExterno>
            </cli:perspectiva>
        </ns2:Cliente>
        <ns2:observacion>{' '}</ns2:observacion>
        <ns2:usuarioModifico></ns2:usuarioModifico>
        <ns2:usuarioValido></ns2:usuarioValido>
    </ns2:PropagarAnalisisRiesgoRequest>
};

local:func($FinSeguimientoCrediticio)
