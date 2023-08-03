xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace mfc="http://org/bcie/ws/middle/MFC/MFC.wsdl";

declare namespace ns2="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $crearReservaFondosResponse as element() (:: element(mfc:crearReservaFondosResponse) ::) external;

declare function local:func($crearReservaFondosResponse as element() (:: element(mfc:crearReservaFondosResponse) ::)) as element() (:: schema-element(ns2:CrearReservaFondosMFCResponse) ::) {
    <ns2:CrearReservaFondosMFCResponse>
        <ns2:NumeroReserva>{fn:data($crearReservaFondosResponse/numeroReserva_out)}</ns2:NumeroReserva>
        <ns2:Resultado>
            <res:result>{
            if (string($crearReservaFondosResponse/codigoResultado_out)= '0') then 'OK'
            else 'ERROR'}</res:result>
            <res:message>{
            if($crearReservaFondosResponse/codigoResultado_out !=0) then fn:data($crearReservaFondosResponse/mensajeError_out)
            else 'Operación Exitosa'
            }</res:message>
        </ns2:Resultado>
    </ns2:CrearReservaFondosMFCResponse>
};

local:func($crearReservaFondosResponse)
