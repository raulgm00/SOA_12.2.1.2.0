xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare variable $ConsultarDesembolsosByIdResponse as element() (:: schema-element(ns1:ConsultarDesembolsosByIdResponse) ::) external;

declare function local:func($ConsultarDesembolsosByIdResponse as element() (:: schema-element(ns1:ConsultarDesembolsosByIdResponse) ::)) as element() (:: schema-element(ns1:CrearActualizarDesembolsosRequest) ::) {
    <ns1:CrearActualizarDesembolsosRequest>
        <ns1:ContratoDesembolso>
            <des:idDesembolso>{fn:data($ConsultarDesembolsosByIdResponse/ns1:ContratoDesembolso/des:idDesembolso)}</des:idDesembolso>
            <des:estado>
                <cat:Id>22</cat:Id>
            </des:estado>
            <des:fechaRegistro></des:fechaRegistro>
        </ns1:ContratoDesembolso>
    </ns1:CrearActualizarDesembolsosRequest>
};

local:func($ConsultarDesembolsosByIdResponse)
