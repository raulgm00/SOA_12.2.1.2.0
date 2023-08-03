xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)
declare namespace ns2="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)

declare namespace tns="http://www.w3.org/2005/xquery-local-functions";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace reg = "http://www.bcie.org/ReglaBO";

declare namespace con = "http://www.bcie.org/CondicionBO";

declare variable $ConsultarTransaccionCondicionResponse as element() (:: schema-element(ns1:ConsultarTransaccionCondicionResponse) ::) external;

declare function tns:func($ConsultarTransaccionCondicionResponse as element() (:: schema-element(ns1:ConsultarTransaccionCondicionResponse) ::)) as element() (:: schema-element(ns2:CrearActualizarSolicitudDesembolsoRequest) ::) {
    <ns2:CrearActualizarSolicitudDesembolsoRequest>
        <ns2:SolicitudDesembolso>
            <des:idDesembolso>{fn:data($ConsultarTransaccionCondicionResponse/ns1:TransaccionCondicion[1]/con:Solicitud/des:idDesembolso)}</des:idDesembolso>
            <des:estado>
                <cat:Id>20</cat:Id>
            </des:estado>
        </ns2:SolicitudDesembolso>
    </ns2:CrearActualizarSolicitudDesembolsoRequest>
};

tns:func($ConsultarTransaccionCondicionResponse)
