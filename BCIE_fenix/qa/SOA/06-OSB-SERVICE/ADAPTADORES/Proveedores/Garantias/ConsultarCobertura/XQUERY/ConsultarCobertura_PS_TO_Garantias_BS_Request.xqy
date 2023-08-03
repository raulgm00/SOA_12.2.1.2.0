xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)

declare namespace gar="http://org/bcie/ws/middle/GARANTIAS.wsdl";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";



declare variable $ConsultarCoberturaRequest as element() (:: schema-element(ns1:ConsultarCoberturaRequest) ::) external;

declare function local:func($ConsultarCoberturaRequest as element() (:: schema-element(ns1:ConsultarCoberturaRequest) ::))as element() (:: element(gar:consultacobertura) ::) {
    <gar:consultacobertura>
         <numerolineacredito>{fn:data($ConsultarCoberturaRequest/ns1:LineaCredito/lin:NumeroLineaCredito)}</numerolineacredito>
    </gar:consultacobertura>

};

local:func($ConsultarCoberturaRequest)
