xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CrearBitacoraProcesoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/BitacoraProceso/V1/Schema/BitacoraProcesoMO.xsd" ::)
declare namespace ns2="http://www.bcie.org/UsuarioMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/Usuario/V1/Schema/UsuarioMO.xsd" ::)

declare namespace usu = "http://www.bcie.org/UsuarioBO";

declare namespace cre = "http://www.bcie.org/CrearBitacoraProcesoBO";

declare variable $requestCrearBitacoraProceso as element() (:: schema-element(ns1:requestCrearBitacoraProcesoMessage) ::) external;

declare function local:func($requestCrearBitacoraProceso as element() (:: schema-element(ns1:requestCrearBitacoraProcesoMessage) ::)) as element() (:: schema-element(ns2:ConsultarAtributosUsuarioRequest) ::) {
    <ns2:ConsultarAtributosUsuarioRequest>
        <ns2:listaNombres>
            <usu:nombreUsuario>{fn:data($requestCrearBitacoraProceso/ns1:BitacoraInput/cre:usuario)}</usu:nombreUsuario>
        </ns2:listaNombres>
    </ns2:ConsultarAtributosUsuarioRequest>
};

local:func($requestCrearBitacoraProceso)