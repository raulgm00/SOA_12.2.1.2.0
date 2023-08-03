xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace m="http://org/bcie/ws/middle/REP.wsdl";
declare namespace typ="http://org/bcie/ws/middle/REP.wsdl/types/";
declare namespace ns1="http://org/bcie/ws/middle/REP.wsdl";
(:: import schema at "../../XSD/REP_CUSTOM.xsd" ::)
declare namespace ns2="http://www.bcie.org/ClienteMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioCliente/Cliente/V1/Schema/ClienteMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare variable $REP_consultarReservaResponse as element(m:consultarReservaResponse ) (:: element(m:consultarReservaResponse ) ::) external;

declare function local:func($REP_consultarReservaResponse as element(m:consultarReservaResponse ) (:: element(m:consultarReservaResponse ) ::)) as element() (:: schema-element(ns2:ConsultarClienteREPResponse) ::) {
    <ns2:ConsultarClienteREPResponse>
        <ns2:Cliente>
            <cli:reserva>
                <cli:importeReserva>{fn:data($REP_consultarReservaResponse/listaBalance_out/typ:BalanceTypeUser/typ:balance)}</cli:importeReserva>
                <cli:tipoReserva>{fn:data($REP_consultarReservaResponse/listaBalance_out/typ:BalanceTypeUser/typ:componente)}</cli:tipoReserva>
            </cli:reserva>
        </ns2:Cliente>
        <ns2:Resultado>
            <res:result>{
            if(fn:string-length(fn:data($REP_consultarReservaResponse/mensaje_out))=0) then ("OK")
            else("ERROR")
            }
            </res:result>
            <res:message>{
            if (fn:string-length(fn:data($REP_consultarReservaResponse/mensaje_out))=0) then ("Operación exitosa")
            else (fn:data($REP_consultarReservaResponse/mensaje_out))
            }</res:message>
        </ns2:Resultado>
    </ns2:ConsultarClienteREPResponse>
};

local:func($REP_consultarReservaResponse)