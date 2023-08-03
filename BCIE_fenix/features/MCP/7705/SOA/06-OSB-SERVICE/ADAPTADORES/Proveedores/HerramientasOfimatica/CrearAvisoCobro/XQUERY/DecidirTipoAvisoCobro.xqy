xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/GestionCobroMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioCliente/GestionCobro/V1/Schema/GestionCobroMO.xsd" ::)

declare namespace ges = "http://www.bcie.org/GestionCobroBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";


declare variable $GeneraReporteAvisoCobroRequest as element() (:: schema-element(ns1:GeneraReporteAvisoCobroRequest) ::) external;

declare function local:func($GeneraReporteAvisoCobroRequest as element() (:: schema-element(ns1:GeneraReporteAvisoCobroRequest) ::)) as xs:string {
    if (fn:count($GeneraReporteAvisoCobroRequest/ns1:AvisoCobro/ges:aviso/ges:avisoOperacion/ges:informacionPago/ges:detallePago/ges:ContratoDesembolso)>0) then
        "DETALLADO"
    else if ((fn:count($GeneraReporteAvisoCobroRequest/ns1:AvisoCobro/ges:aviso/ges:avisoOperacion/ges:informacionPago/ges:detallePago/ges:Mora)>0) and count($GeneraReporteAvisoCobroRequest/ns1:AvisoCobro/ges:aviso/ges:avisoOperacion/ges:informacionPago/ges:detallePago/ges:Mora[des:referencia !=''])) then
        "MORA_DETALLADO"
    else if (fn:count($GeneraReporteAvisoCobroRequest/ns1:AvisoCobro/ges:aviso/ges:avisoOperacion/ges:informacionPago/ges:detallePago/ges:Mora)>0) then
        "MORA"
    else
        "CONSOLIDADO"
};

local:func($GeneraReporteAvisoCobroRequest)
