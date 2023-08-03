xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ConsultarProgramacion_db";
(:: import schema at "../XSD/ConsultarProgramacion_db_sp.xsd" ::)

declare namespace pyd="http://org/bcie/ws/middle/PYD/PYD.wsdl";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare variable $ConsultarProgramacionPYDRequest as element() (:: schema-element(ns1:ConsultarProgramacionPYDRequest) ::) external;

declare function local:func($ConsultarProgramacionPYDRequest as element() (:: schema-element(ns1:ConsultarProgramacionPYDRequest) ::)) as element() (:: schema-element(ns2:InputParameters) ::) {
    <ns2:InputParameters>
        <ns2:LINEA>{fn:data($ConsultarProgramacionPYDRequest/ns1:LineaCredito/lin:NumeroLineaCredito)}</ns2:LINEA>
        <ns2:FECHA>{fn:data($ConsultarProgramacionPYDRequest/ns1:LineaCredito/lin:FechaValor)}</ns2:FECHA>
        <ns2:MONEDA>{fn:data($ConsultarProgramacionPYDRequest/ns1:LineaCredito/lin:Monto/com:moneda/cat:codigoExterno)}</ns2:MONEDA>
        <ns2:FONDO>{fn:data($ConsultarProgramacionPYDRequest/ns1:LineaCredito/lin:Fondo)}</ns2:FONDO>
    </ns2:InputParameters>
};

local:func($ConsultarProgramacionPYDRequest)
