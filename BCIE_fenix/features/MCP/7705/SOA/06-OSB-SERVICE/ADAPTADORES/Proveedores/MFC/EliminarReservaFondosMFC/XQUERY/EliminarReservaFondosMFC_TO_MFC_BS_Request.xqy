xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace mfc="http://org/bcie/ws/middle/MFC/MFC.wsdl";

declare namespace ns1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)

declare variable $EliminarReservaFondosMFCRequest as element() (:: schema-element(ns1:EliminarReservaFondosMFCRequest) ::) external;

declare function local:func($EliminarReservaFondosMFCRequest as element() (:: schema-element(ns1:EliminarReservaFondosMFCRequest) ::)) as element() (:: element(mfc:eliminarReservaFondos) ::) {
    <mfc:eliminarReservaFondos>
    <numeroReserva>{fn:data($EliminarReservaFondosMFCRequest/ns1:NumeroReserva)}</numeroReserva>
    </mfc:eliminarReservaFondos>
};

local:func($EliminarReservaFondosMFCRequest)
