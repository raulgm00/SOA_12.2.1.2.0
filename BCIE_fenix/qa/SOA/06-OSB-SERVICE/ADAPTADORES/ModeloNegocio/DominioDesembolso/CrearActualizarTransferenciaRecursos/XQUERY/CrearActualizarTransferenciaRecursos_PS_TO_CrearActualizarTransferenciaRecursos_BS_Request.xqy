xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/top/CrearActualizarTransferenciaRecursos";
(:: import schema at "../XSD/CrearActualizarTransferenciaRecursos_table.xsd" ::)

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare variable $CrearActualizarTransferenciaRecursosRequest as element() (:: schema-element(ns1:CrearActualizarTransferenciaRecursosRequest) ::) external;

declare function local:func($CrearActualizarTransferenciaRecursosRequest as element() (:: schema-element(ns1:CrearActualizarTransferenciaRecursosRequest) ::)) as element() (:: schema-element(ns2:TransferenciaRecursosCollection) ::) {
    <ns2:TransferenciaRecursosCollection>
        <ns2:TransferenciaRecursos>
            <ns2:id>{fn:data($CrearActualizarTransferenciaRecursosRequest/ns1:TransferenciaRecursos/des:idTransferencia)}</ns2:id>
            <ns2:idTreLineaPasiva>{fn:data($CrearActualizarTransferenciaRecursosRequest/ns1:TransferenciaRecursos/des:idLineaPasiva)}</ns2:idTreLineaPasiva>
            <ns2:montoDesembolsado>{fn:data($CrearActualizarTransferenciaRecursosRequest/ns1:TransferenciaRecursos/des:Monto/com:importe)}</ns2:montoDesembolsado>
            <ns2:fecha>{fn:data($CrearActualizarTransferenciaRecursosRequest/ns1:TransferenciaRecursos/des:fecha)}</ns2:fecha>
            <ns2:idTcaFormaTransferencia>{fn:data($CrearActualizarTransferenciaRecursosRequest/ns1:TransferenciaRecursos/des:FormaTransferencia/cat:Id)}</ns2:idTcaFormaTransferencia>
            <ns2:idBancoTransferencia>{fn:data($CrearActualizarTransferenciaRecursosRequest/ns1:TransferenciaRecursos/des:idBanco)}</ns2:idBancoTransferencia>
            <ns2:nombreBancoTransferencia>{fn:data($CrearActualizarTransferenciaRecursosRequest/ns1:TransferenciaRecursos/des:nombreBanco)}</ns2:nombreBancoTransferencia>
            <ns2:numeroCuenta>{fn:data($CrearActualizarTransferenciaRecursosRequest/ns1:TransferenciaRecursos/des:numeroCuenta)}</ns2:numeroCuenta>
            <ns2:nombreCuenta>{fn:data($CrearActualizarTransferenciaRecursosRequest/ns1:TransferenciaRecursos/des:nombreCuenta)}</ns2:nombreCuenta>
            <ns2:banEstatus>{if (fn:data($CrearActualizarTransferenciaRecursosRequest/ns1:TransferenciaRecursos/des:estatus)= true()) then 1 else 0}</ns2:banEstatus>
            <ns2:fechaRegistro>{if (fn:data($CrearActualizarTransferenciaRecursosRequest/ns1:TransferenciaRecursos/des:idTransferencia/text()) = '') then fn:current-date() else()}</ns2:fechaRegistro>
            <ns2:bhqTransferenciaFt05>{fn:data($CrearActualizarTransferenciaRecursosRequest/ns1:TransferenciaRecursos/des:idFacturador)}</ns2:bhqTransferenciaFt05>
            <ns2:fechaEfectivaFt05>{fn:data($CrearActualizarTransferenciaRecursosRequest/ns1:TransferenciaRecursos/des:fechaEfectiva)}</ns2:fechaEfectivaFt05>
    
        </ns2:TransferenciaRecursos>
    </ns2:TransferenciaRecursosCollection>
};

local:func($CrearActualizarTransferenciaRecursosRequest)
