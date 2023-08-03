xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CrearBitacoraReglasDesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/CrearBitacoraReglasDesembolso/V1/Schema/CrearBitacoraReglasDesembolsoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/top/CrearBitacoraReglasDesembolso";
(:: import schema at "../XSD/CrearBitacoraReglasDesembolso_table.xsd" ::)

declare namespace cre = "http://www.bcie.org/CrearBitacoraReglasDesembolsoBO";

declare variable $CrearBitacoraReglasDesembolsoRequest as element() (:: schema-element(ns1:CrearBitacoraReglasDesembolsoRequest) ::) external;

declare function local:func($CrearBitacoraReglasDesembolsoRequest as element() (:: schema-element(ns1:CrearBitacoraReglasDesembolsoRequest) ::)) as element() (:: schema-element(ns2:TbiReglaDesembolsoCollection) ::) {
    <ns2:TbiReglaDesembolsoCollection>
        <ns2:TbiReglaDesembolso>
            <ns2:id>{fn:data($CrearBitacoraReglasDesembolsoRequest/ns1:BitacoraReglasDesembolso/cre:Id)}</ns2:id>
            <ns2:instanciaProceso>{fn:data($CrearBitacoraReglasDesembolsoRequest/ns1:BitacoraReglasDesembolso/cre:InstanciaProceso)}</ns2:instanciaProceso>
            <ns2:idTreReglaDesembolso>{fn:data($CrearBitacoraReglasDesembolsoRequest/ns1:BitacoraReglasDesembolso/cre:IdTreReglaDesembolso)}</ns2:idTreReglaDesembolso>
            <ns2:idTcaTarea>{fn:data($CrearBitacoraReglasDesembolsoRequest/ns1:BitacoraReglasDesembolso/cre:IdTarea)}</ns2:idTcaTarea>
            <ns2:descripcion>{fn:data($CrearBitacoraReglasDesembolsoRequest/ns1:BitacoraReglasDesembolso/cre:Descripcion)}</ns2:descripcion>
            <ns2:fechaRegistro>{fn:current-date()}</ns2:fechaRegistro>
            <ns2:estado>{fn:data($CrearBitacoraReglasDesembolsoRequest/ns1:BitacoraReglasDesembolso/cre:Estatus)}</ns2:estado>
        </ns2:TbiReglaDesembolso>
    </ns2:TbiReglaDesembolsoCollection>
};

local:func($CrearBitacoraReglasDesembolsoRequest)
