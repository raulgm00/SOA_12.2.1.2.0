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

declare variable $crearDeclaracionJuradaRequest as element() (:: schema-element(ns1:CrearDeclaracionJuradaRequest) ::) external;

declare function local:func($crearDeclaracionJuradaRequest as element() (:: schema-element(ns1:CrearDeclaracionJuradaRequest) ::)) as element() (:: schema-element(ns2:NuevaDeclaracionRequest) ::) {
    <ns2:NuevaDeclaracionRequest>
        <ns2:codigoIntervencion>{fn:data($crearDeclaracionJuradaRequest/ns1:Operacion/ope:idOperacion)}</ns2:codigoIntervencion>
        <ns2:sectorInstitucional>{fn:data($crearDeclaracionJuradaRequest/ns1:Operacion/ope:cliente/cli:sector/cat:Id)}</ns2:sectorInstitucional>
        <ns2:tipoPersona>{fn:data($crearDeclaracionJuradaRequest/ns1:Operacion/ope:cliente/cli:tipoPersona/cat:codigoExterno)}</ns2:tipoPersona>
        <ns2:ejecutivo>{fn:data($crearDeclaracionJuradaRequest/ns1:Operacion/ope:cliente/cli:ejecutivo)}</ns2:ejecutivo>
        <ns2:titulo>{fn:concat($crearDeclaracionJuradaRequest/ns1:Operacion/ope:nombre,' - ',$crearDeclaracionJuradaRequest/ns1:Operacion/ope:cliente/cli:razonSocial)}</ns2:titulo>
        <ns2:fechaRecepcion>{fn:data($crearDeclaracionJuradaRequest/ns1:DeclaracionJurada/dec:fechaFirmaDeclaracion)}</ns2:fechaRecepcion>
    </ns2:NuevaDeclaracionRequest>
};

local:func($crearDeclaracionJuradaRequest)
