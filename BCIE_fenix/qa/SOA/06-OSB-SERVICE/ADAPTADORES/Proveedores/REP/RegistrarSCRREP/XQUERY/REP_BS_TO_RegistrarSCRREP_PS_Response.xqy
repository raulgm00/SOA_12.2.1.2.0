xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace m="http://org/bcie/ws/middle/REP.wsdl";

declare namespace ns1="http://org/bcie/ws/middle/REP.wsdl";
(:: import schema at "../../XSD/REP_CUSTOM.xsd" ::)
declare namespace ns2="http://www.bcie.org/ClienteMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioCliente/Cliente/V1/Schema/ClienteMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $REP_registrarCalificacionResponse as element(m:registrarCalificacionResponse ) (:: element(m:registrarCalificacionResponse ) ::) external;

declare function local:func($REP_registrarCalificacionResponse as element(m:registrarCalificacionResponse ) (:: element(m:registrarCalificacionResponse ) ::)) as element() (:: schema-element(ns2:RegistrarSCRREPResponse) ::) {
    <ns2:RegistrarSCRREPResponse>
        <ns2:codigoResultadoOut>{fn:data($REP_registrarCalificacionResponse/codigoresultado_out)}</ns2:codigoResultadoOut>
        <ns2:tipoResultadoOut>{fn:data($REP_registrarCalificacionResponse/tiporesultado_out)}</ns2:tipoResultadoOut>
        <ns2:mensajeOut>{fn:data($REP_registrarCalificacionResponse/mensaje_out)}</ns2:mensajeOut>
        <ns2:Resultado>
            <res:result>{
            if(fn:string-length(fn:data($REP_registrarCalificacionResponse/mensaje_out))=0) then ("OK")
            else("ERROR")
            }
            </res:result>
            <res:message>{
            if (fn:string-length(fn:data($REP_registrarCalificacionResponse/mensaje_out))=0) then ("Operación exitosa")
            else (fn:data($REP_registrarCalificacionResponse/mensaje_out))
            }</res:message>
            <res:error>
                <err:errorCode></err:errorCode>
                <err:errorDescription></err:errorDescription>
                <err:errorType></err:errorType>
            </res:error>
        </ns2:Resultado>
    </ns2:RegistrarSCRREPResponse>
};

local:func($REP_registrarCalificacionResponse)