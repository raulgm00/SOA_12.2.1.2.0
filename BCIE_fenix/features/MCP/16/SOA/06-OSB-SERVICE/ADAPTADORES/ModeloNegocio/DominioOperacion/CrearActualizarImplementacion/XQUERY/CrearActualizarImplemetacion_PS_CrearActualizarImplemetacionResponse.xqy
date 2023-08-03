xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ImplementacionPctMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/implementacionPCT/V1/Schema/ImplementacionPctMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/top/CrearActualizarImplementacion";
(:: import schema at "../XSD/CrearActualizarImplementacion_table.xsd" ::)

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace imp = "http://www.bcie.org/ImplementacionPctBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $outCrearActualizarImplementcion as element() (:: schema-element(ns1:ImplementacionCollection) ::) external;

declare function local:func($outCrearActualizarImplementcion as element() (:: schema-element(ns1:ImplementacionCollection) ::)) as element() (:: schema-element(ns2:CrearActualizarImplementacionResponse) ::) {
    <ns2:CrearActualizarImplementacionResponse>
        <ns2:Implementacon>
            <imp:idLote>{fn:data($outCrearActualizarImplementcion/ns1:Implementacion/ns1:id)}</imp:idLote>
        </ns2:Implementacon>
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>Operación exitosa</res:message>
        </ns2:Resultado>
    </ns2:CrearActualizarImplementacionResponse>
};

local:func($outCrearActualizarImplementcion)