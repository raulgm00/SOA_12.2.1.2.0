xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)

declare variable $PropagarTransSinSalidaRecursosRequest as element() (:: schema-element(ns1:PropagarTransSinSalidaRecursosRequest) ::) external;

declare function local:func($PropagarTransSinSalidaRecursosRequest as element() (:: schema-element(ns1:PropagarTransSinSalidaRecursosRequest) ::)) as element() (:: schema-element(ns1:PropagarTransferenciaRecursosRequest) ::) {
    <ns1:PropagarTransferenciaRecursosRequest>
        <ns1:idDesembolso>{fn:data($PropagarTransSinSalidaRecursosRequest/ns1:idDesembolso)}</ns1:idDesembolso>
    </ns1:PropagarTransferenciaRecursosRequest>
};

local:func($PropagarTransSinSalidaRecursosRequest)
