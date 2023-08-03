xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://org/bcie/ws/middle/LAACTIVOS.wsdl";
(:: import schema at "../../XSD/LAACTIVOS_Schema.xsd" ::)
declare namespace ns1="http://www.bcie.org/DeclaracionJuradaMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/DeclaracionJurada/V1/Schema/DeclaracionJuradaMO.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace dec = "http://www.bcie.org/DeclaracionJuradaBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare variable $DuplicarDeclaracionJuradaRequest as element() (:: schema-element(ns1:DuplicarDeclaracionJuradaRequest) ::) external;

declare function local:func($DuplicarDeclaracionJuradaRequest as element() (:: schema-element(ns1:DuplicarDeclaracionJuradaRequest) ::)) as element() (:: schema-element(ns2:DuplicarDeclaracionRequest) ::) {
    <ns2:DuplicarDeclaracionRequest>
        <ns2:codigoDeclaracion>{fn:data($DuplicarDeclaracionJuradaRequest/ns1:DeclaracionJurada/dec:codigoExternoDeclaracion)}</ns2:codigoDeclaracion>
       {
                if ($DuplicarDeclaracionJuradaRequest/ns1:duplicarDeclaracion)
                then  <ns2:DuplicarRespuestas>1</ns2:DuplicarRespuestas>
                else  <ns2:DuplicarRespuestas>0</ns2:DuplicarRespuestas>
            }
        <ns2:NumeroEmpleado>{fn:data($DuplicarDeclaracionJuradaRequest/ns1:Operacion/ope:cliente/cli:ejecutivo)}</ns2:NumeroEmpleado>
    </ns2:DuplicarDeclaracionRequest>
};

local:func($DuplicarDeclaracionJuradaRequest)
