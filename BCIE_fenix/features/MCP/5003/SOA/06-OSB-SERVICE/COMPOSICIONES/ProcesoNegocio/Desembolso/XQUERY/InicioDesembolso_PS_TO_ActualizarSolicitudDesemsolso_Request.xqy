xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace reg = "http://www.bcie.org/ReglaBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare variable $inCrearActualizarSolicitudDesembolso as element() (:: schema-element(ns1:ConsultarSolicitudDesembolsoResponse) ::) external;

declare function local:func($inCrearActualizarSolicitudDesembolso as element() (:: schema-element(ns1:ConsultarSolicitudDesembolsoResponse) ::)) as element() (:: schema-element(ns1:CrearActualizarSolicitudDesembolsoRequest) ::) {
    <ns1:CrearActualizarSolicitudDesembolsoRequest>
        <ns1:SolicitudDesembolso>
            <des:idDesembolso>{fn:data($inCrearActualizarSolicitudDesembolso/ns1:SolicitudDesmebolso/des:idDesembolso)}</des:idDesembolso>
            <des:estado>
                <cat:Id>8</cat:Id>
            </des:estado>
        </ns1:SolicitudDesembolso>
    </ns1:CrearActualizarSolicitudDesembolsoRequest>
};

local:func($inCrearActualizarSolicitudDesembolso)
