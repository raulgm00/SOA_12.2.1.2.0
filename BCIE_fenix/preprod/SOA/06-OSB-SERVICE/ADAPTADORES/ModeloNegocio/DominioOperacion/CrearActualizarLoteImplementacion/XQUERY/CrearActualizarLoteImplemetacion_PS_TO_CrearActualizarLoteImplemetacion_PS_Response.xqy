xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ImplementacionPctMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/implementacionPCT/V1/Schema/ImplementacionPctMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/top/CrearActualizarLoteImplementacion";
(:: import schema at "../XSD/CrearActualizarLoteImplementacion_table.xsd" ::)


declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace imp = "http://www.bcie.org/ImplementacionPctBO";

declare variable $CrearActualizarLoteImplementacionResponse as element() (:: schema-element(ns2:LoteImplementacionCollection) ::) external;

declare function local:func($CrearActualizarLoteImplementacionResponse as element() (:: schema-element(ns2:LoteImplementacionCollection) ::)) as element() (:: schema-element(ns1:CrearActualizarLoteImplementacionResponse) ::) {
    <ns1:CrearActualizarLoteImplementacionResponse>
        <ns1:LoteImplementacion>
            <imp:idLote>{fn:data($CrearActualizarLoteImplementacionResponse/ns2:LoteImplementacion/ns2:id)}</imp:idLote>
        </ns1:LoteImplementacion>
        <ns1:Resultado>
            <res:result>OK</res:result>
            <res:message>Operación exitosa</res:message>
        </ns1:Resultado>
    </ns1:CrearActualizarLoteImplementacionResponse>
};

local:func($CrearActualizarLoteImplementacionResponse)
