xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/bpmn/bpmnProcess/DesembolsoProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PC06/DesembolsoProcess.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace reg = "http://www.bcie.org/ReglaBO";

declare variable $FinValidacionAsignacion as element() (:: schema-element(ns1:FinValidacionAsignacion) ::) external;
declare variable $numeroContrato as xs:string external;

declare function local:func($FinValidacionAsignacion as element() (:: schema-element(ns1:FinValidacionAsignacion) ::)) as element() (:: schema-element(ns2:CrearActualizarSolicitudDesembolsoRequest) ::) {
    <ns2:CrearActualizarSolicitudDesembolsoRequest>
        <ns2:SolicitudDesembolso>
            <des:idDesembolso>{$numeroContrato}</des:idDesembolso>
            <des:estado>
                <cat:Id>12</cat:Id>
            </des:estado>
        </ns2:SolicitudDesembolso>
    </ns2:CrearActualizarSolicitudDesembolsoRequest>
};

local:func($FinValidacionAsignacion)
