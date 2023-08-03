xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace reg = "http://www.bcie.org/ReglaBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare variable $inActualizarSolicitudDesembolso as element() (:: schema-element(ns2:ConsultarSolicitudDesembolsoResponse) ::) external;

declare function local:func($inActualizarSolicitudDesembolso as element() (:: schema-element(ns2:ConsultarSolicitudDesembolsoResponse) ::)) as element() (:: schema-element(ns2:CrearActualizarSolicitudDesembolsoRequest) ::) {
    <ns2:CrearActualizarSolicitudDesembolsoRequest>
        <ns2:SolicitudDesembolso>
            <des:idDesembolso>{fn:data($inActualizarSolicitudDesembolso/ns2:SolicitudDesembolso/des:idDesembolso)}</des:idDesembolso>
            <des:estado>
                <cat:Id>9</cat:Id>
            </des:estado>
        </ns2:SolicitudDesembolso>
    </ns2:CrearActualizarSolicitudDesembolsoRequest>
};

local:func($inActualizarSolicitudDesembolso)
