xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)

declare variable $NotificarEliminacionEvidenciaRequest as element() (:: schema-element(ns1:NotificarEliminacionEvidenciaRequest) ::) external;

declare function local:func($NotificarEliminacionEvidenciaRequest as element() (:: schema-element(ns1:NotificarEliminacionEvidenciaRequest) ::)) as element() (:: schema-element(ns1:ConsultarCondicionValidacionByRolRequest) ::) {
    <ns1:ConsultarCondicionValidacionByRolRequest>
        <ns1:idOperacion>{fn:data($NotificarEliminacionEvidenciaRequest/ns1:idOperacion)}</ns1:idOperacion>
        <ns1:agrupador>{fn:data($NotificarEliminacionEvidenciaRequest/ns1:idAgrupador)}</ns1:agrupador>
    </ns1:ConsultarCondicionValidacionByRolRequest>
};

local:func($NotificarEliminacionEvidenciaRequest)
