xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/PrepagoMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Prepago/V1/Schema/PrepagoMO.xsd" ::)

declare namespace cor = "http://www.bcie.org/CorreoBO";

declare variable $PlanArmotizacionRequest as element() (:: schema-element(ns1:PlanArmotizacionRequest) ::) external;

declare function local:func($PlanArmotizacionRequest as element() (:: schema-element(ns1:PlanArmotizacionRequest) ::)) as element() (:: schema-element(ns1:ExtraerReportePrepagoRequest) ::) {
    <ns1:ExtraerReportePrepagoRequest>
        <ns1:idPrepago>{fn:data($PlanArmotizacionRequest/ns1:Tags/cor:valor)}</ns1:idPrepago>
    </ns1:ExtraerReportePrepagoRequest>
};

local:func($PlanArmotizacionRequest)
