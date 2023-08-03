xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/TerminoMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Termino/V1/Schema/TerminoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarTerminoByIdOperacion";
(:: import schema at "../XSD/ConsultarTerminoByIdOperacion.xsd" ::)

declare namespace ter = "http://www.bcie.org/TerminoBO";

declare variable $CrearTerminoRequest as element() (:: schema-element(ns1:CrearTerminoRequest) ::) external;

declare function local:func($CrearTerminoRequest as element() (:: schema-element(ns1:CrearTerminoRequest) ::)) as element() (:: schema-element(ns2:ConsultarTerminoByIdOperacionInput) ::) {
    <ns2:ConsultarTerminoByIdOperacionInput>
        <ns2:idOperacion>{fn:data($CrearTerminoRequest/ns1:Termino/ter:operacion)}</ns2:idOperacion>
        <ns2:idTcaTermino>{fn:data($CrearTerminoRequest/ns1:Termino/ter:tipoTermino/ter:idCatTermino)}</ns2:idTcaTermino>
    </ns2:ConsultarTerminoByIdOperacionInput>
};

local:func($CrearTerminoRequest)
