xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)

declare namespace pyd="http://org/bcie/ws/middle/PYD/PYD.wsdl";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare variable $ConsultarProgramacionPYDRequest as element() (:: schema-element(ns1:ConsultarProgramacionPYDRequest) ::) external;

declare function local:func($ConsultarProgramacionPYDRequest as element() (:: schema-element(ns1:ConsultarProgramacionPYDRequest) ::))as element() (:: element(pyd:consultarProgramacion) ::) {
    
    <pyd:consultarProgramacion>
         <linea>{fn:data($ConsultarProgramacionPYDRequest/ns1:LineaCredito/lin:NumeroLineaCredito)}</linea>
         <fecha>{fn:data($ConsultarProgramacionPYDRequest/ns1:LineaCredito/lin:FechaValor)}</fecha>
         <!-- moneda de la linea-->
         <moneda>{fn:data($ConsultarProgramacionPYDRequest/ns1:LineaCredito/lin:Monto/com:moneda/cat:codigoExterno)}</moneda>
         <fondo>{fn:data($ConsultarProgramacionPYDRequest/ns1:LineaCredito/lin:Fondo)}</fondo>
      </pyd:consultarProgramacion>

    
};

local:func($ConsultarProgramacionPYDRequest)
