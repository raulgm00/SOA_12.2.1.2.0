xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace cor="http://www.bcie.org/CorreoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/EnviarCorreoElectronico/V1/Schema/CorreoElectronicoMO.xsd" ::)

declare namespace cor1 = "http://www.bcie.org/CorreoBO";

declare variable $enviarCorreoElectronicoRequest as element() (:: schema-element(cor:enviarCorreoElectronicoRequest) ::) external;
declare variable $Index as xs:int external;

declare function local:func($enviarCorreoElectronicoRequest as element() (:: schema-element(cor:enviarCorreoElectronicoRequest) ::),
                            $Index as xs:int) as text() {
    text {$enviarCorreoElectronicoRequest/cor:CorreoElectronico/cor1:attachment[cor1:content/text() != ''][$Index]/cor1:content}
};

local:func($enviarCorreoElectronicoRequest,$Index)