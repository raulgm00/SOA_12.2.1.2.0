xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ConsultarConfiguracion";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/ConsultarConfiguracion/V1/Schema/ConsultarConfiguracionMO.xsd" ::)
declare namespace ns1="http://www.bcie.org/ObtenerURLMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/ObtenerURL/V1/Schema/ObtenerURLMO.xsd" ::)

declare namespace com = "http://www.bcie.org/CommonBO";

declare variable $ObtenerURLRequest as element() (:: schema-element(ns1:ObtenerURLRequest) ::) external;

declare function local:func($ObtenerURLRequest as element() (:: schema-element(ns1:ObtenerURLRequest) ::)) as element() (:: schema-element(ns2:ConsultarConfiguracionRequest) ::) {
    <ns2:ConsultarConfiguracionRequest>
        <ns2:Parametro>
            <com:name>{
              dvmtr:lookup('MDS/Resources/ComponentesComunes/Common/V1/DVM/ReportesCodes','NombreReporte',fn:data($ObtenerURLRequest/ns1:URL),'Ubicacion','')
            }</com:name>
        </ns2:Parametro>
    </ns2:ConsultarConfiguracionRequest>
};

local:func($ObtenerURLRequest)
