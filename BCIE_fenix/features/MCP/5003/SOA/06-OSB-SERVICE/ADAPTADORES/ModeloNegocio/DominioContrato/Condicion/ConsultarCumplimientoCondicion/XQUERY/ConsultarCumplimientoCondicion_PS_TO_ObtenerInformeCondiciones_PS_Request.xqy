xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)

declare variable $ConsultarCumplimientoCondicionRequest as element() (:: schema-element(ns1:ConsultarCumplimientoCondicionRequest) ::) external;

declare function local:func($ConsultarCumplimientoCondicionRequest as element() (:: schema-element(ns1:ConsultarCumplimientoCondicionRequest) ::)) as element() (:: schema-element(ns1:ObtenerInformeCondicionesRequest) ::) {
    <ns1:ObtenerInformeCondicionesRequest>
        <ns1:idOperacion>{fn:data($ConsultarCumplimientoCondicionRequest/ns1:idOperacion)}</ns1:idOperacion>
    </ns1:ObtenerInformeCondicionesRequest>
};

local:func($ConsultarCumplimientoCondicionRequest)
