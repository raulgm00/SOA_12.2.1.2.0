xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/TerminoMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Termino/V1/Schema/TerminoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarTreTermino_DB";
(:: import schema at "../XSD/ConsultarTreTermino_DB.xsd" ::)

declare variable $ConsultarTreTerminoRequest as element() (:: schema-element(ns1:ConsultarTreTerminoRequest) ::) external;

declare function local:func($ConsultarTreTerminoRequest as element() (:: schema-element(ns1:ConsultarTreTerminoRequest) ::)) as element() (:: schema-element(ns2:ConsultarTreTermino_DBInput) ::) {
    <ns2:ConsultarTreTermino_DBInput>
        <ns2:idOperacion>{fn:data($ConsultarTreTerminoRequest/ns1:idOperacion)}</ns2:idOperacion>
        <ns2:descripcionCorta>{fn:data($ConsultarTreTerminoRequest/ns1:descripcionCorta)}</ns2:descripcionCorta>
        <ns2:idLineaCredito>{fn:data($ConsultarTreTerminoRequest/ns1:idLineaCredito)}</ns2:idLineaCredito>
        <ns2:idOperacion1>{fn:data($ConsultarTreTerminoRequest/ns1:idOperacion)}</ns2:idOperacion1>
        <ns2:descripcionCorta1>{fn:data($ConsultarTreTerminoRequest/ns1:descripcionCorta)}</ns2:descripcionCorta1>
        <ns2:idLineaCredito1>{fn:data($ConsultarTreTerminoRequest/ns1:idLineaCredito)}</ns2:idLineaCredito1>
          
         
    </ns2:ConsultarTreTermino_DBInput>
};

local:func($ConsultarTreTerminoRequest)
