xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ClienteMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioCliente/Cliente/V1/Schema/ClienteMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/sp/consultarReserva";
(:: import schema at "../XSD/consultarReserva_sp.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $REP_consultarReservaResponse as element() (:: schema-element(ns1:OutputParameters) ::) external;

declare function local:func($REP_consultarReservaResponse as element() (:: schema-element(ns1:OutputParameters) ::)) as element() (:: schema-element(ns2:ConsultarClienteREPResponse) ::) {
    <ns2:ConsultarClienteREPResponse>
        <ns2:Cliente>
            <cli:reserva>
                <cli:importeReserva>{fn:data($REP_consultarReservaResponse/ns1:LISTA_BALANCE/ns1:LISTA_BALANCE_ITEM/ns1:BALANCE)}</cli:importeReserva>
                <cli:tipoReserva>{fn:data($REP_consultarReservaResponse/ns1:LISTA_BALANCE/ns1:LISTA_BALANCE_ITEM/ns1:COMPONENTE)}</cli:tipoReserva>
            </cli:reserva>
        </ns2:Cliente>
        <ns2:Resultado>
            <res:result>{
            if(fn:string-length(fn:data($REP_consultarReservaResponse/ns1:MENSAJE))=0) then ("OK")
            else("ERROR")
            }
            </res:result>
            <res:message>{
            if (fn:string-length(fn:data($REP_consultarReservaResponse/ns1:MENSAJE))=0) then ("Operación exitosa")
            else (fn:data($REP_consultarReservaResponse/ns1:MENSAJE))
            }</res:message>
        </ns2:Resultado>
    </ns2:ConsultarClienteREPResponse>
};

local:func($REP_consultarReservaResponse)