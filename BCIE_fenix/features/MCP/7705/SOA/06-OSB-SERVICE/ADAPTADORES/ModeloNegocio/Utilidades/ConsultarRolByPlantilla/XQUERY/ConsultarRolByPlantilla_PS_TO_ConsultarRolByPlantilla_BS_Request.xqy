xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CorreoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/EnviarCorreoElectronico/V1/Schema/CorreoElectronicoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarRolByPlantilla";
(:: import schema at "../XSD/ConsultarRolByPlantilla.xsd" ::)

declare variable $ConsultaRolByPlantillaRequest as element() (:: schema-element(ns1:ConsultaRolByPlantillaRequest) ::) external;

declare function local:func($ConsultaRolByPlantillaRequest as element() (:: schema-element(ns1:ConsultaRolByPlantillaRequest) ::)) as element() (:: schema-element(ns2:ConsultarRolByPlantillaInput) ::) {
    <ns2:ConsultarRolByPlantillaInput>
        <ns2:idPlantilla>{fn:data($ConsultaRolByPlantillaRequest/ns1:idPlantilla)}</ns2:idPlantilla>
    </ns2:ConsultarRolByPlantillaInput>
};

local:func($ConsultaRolByPlantillaRequest)
