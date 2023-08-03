xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://org/bcie/ws/middle/LAACTIVOS.wsdl";
(:: import schema at "../../XSD/LAACTIVOS_Schema.xsd" ::)
declare namespace ns1="http://www.bcie.org/DeclaracionJuradaMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/DeclaracionJurada/V1/Schema/DeclaracionJuradaMO.xsd" ::)

declare namespace dec = "http://www.bcie.org/DeclaracionJuradaBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare variable $ActualizarDeclaracionJuradaRequest as element() (:: schema-element(ns1:ActualizarDeclaracionJuradaRequest) ::) external;

declare function local:func($ActualizarDeclaracionJuradaRequest as element() (:: schema-element(ns1:ActualizarDeclaracionJuradaRequest) ::)) as element() (:: schema-element(ns2:ActualizarDeclaracionRequest) ::) {
    <ns2:ActualizarDeclaracionRequest>
        <ns2:codigoIntervencion>{fn:data($ActualizarDeclaracionJuradaRequest/ns1:Operacion/ope:idOperacion)}</ns2:codigoIntervencion>
        <ns2:codigoDeclaracion>{fn:data($ActualizarDeclaracionJuradaRequest/ns1:DeclaracionJurada/dec:codigoExternoDeclaracion)}</ns2:codigoDeclaracion>
        <ns2:ejecutivo>{fn:data($ActualizarDeclaracionJuradaRequest/ns1:Operacion/ope:cliente/cli:ejecutivo)}</ns2:ejecutivo>
        <ns2:estadoDeclaracion>{fn:data($ActualizarDeclaracionJuradaRequest/ns1:DeclaracionJurada/dec:estadoDeclaracion/dec:codigoEstadoDeclaracion)}</ns2:estadoDeclaracion>
        <ns2:fechaRecepcion>{fn:data($ActualizarDeclaracionJuradaRequest/ns1:DeclaracionJurada/dec:fechaFirmaDeclaracion)}</ns2:fechaRecepcion>
        <ns2:plantilla>0</ns2:plantilla>
        <ns2:EstadoBusqueda>{fn:data($ActualizarDeclaracionJuradaRequest/ns1:DeclaracionJurada/dec:estadoBusqueda/dec:codigoEstadoBusqueda)}</ns2:EstadoBusqueda>
        <ns2:JustificacionDeclaracion>{fn:data($ActualizarDeclaracionJuradaRequest/ns1:DeclaracionJurada/dec:comentarioDeclaracion)}</ns2:JustificacionDeclaracion>
        <ns2:JustificacionBusqueda>{fn:data($ActualizarDeclaracionJuradaRequest/ns1:DeclaracionJurada/dec:comentarioBusqueda)}</ns2:JustificacionBusqueda>
    </ns2:ActualizarDeclaracionRequest>
};

local:func($ActualizarDeclaracionJuradaRequest)
