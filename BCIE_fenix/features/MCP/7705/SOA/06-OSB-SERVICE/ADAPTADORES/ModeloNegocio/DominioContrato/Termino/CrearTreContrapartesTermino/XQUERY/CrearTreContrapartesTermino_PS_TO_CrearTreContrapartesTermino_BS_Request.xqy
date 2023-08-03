xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/TerminoMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Termino/V1/Schema/TerminoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/top/CrearTreContrapartesTermino_DB";
(:: import schema at "../XSD/CrearTreContrapartesTermino_DB_table.xsd" ::)

declare variable $CrearTreContrapartesTerminoRequest as element() (:: schema-element(ns1:CrearTreContrapartesTerminoRequest) ::) external;

declare function local:func($CrearTreContrapartesTerminoRequest as element() (:: schema-element(ns1:CrearTreContrapartesTerminoRequest) ::)) as element() (:: schema-element(ns2:TreContrapartesTerminoCollection) ::) {
    <ns2:TreContrapartesTerminoCollection>
        <ns2:TreContrapartesTermino>
            <ns2:id></ns2:id>
            {
                if ($CrearTreContrapartesTerminoRequest/ns1:idTermino)
                then <ns2:idTermino>{fn:data($CrearTreContrapartesTerminoRequest/ns1:idTermino)}</ns2:idTermino>
                else ()
            }
            {
                if ($CrearTreContrapartesTerminoRequest/ns1:idCliente)
                then <ns2:idCliente>{fn:data($CrearTreContrapartesTerminoRequest/ns1:idCliente)}</ns2:idCliente>
                else ()
            }
        </ns2:TreContrapartesTermino>
    </ns2:TreContrapartesTerminoCollection>
};

local:func($CrearTreContrapartesTerminoRequest)
