xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace m="http://org/bcie/ws/middle/MFC/MFC.wsdl";

declare namespace ns2="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $eliminarReservaFondosResponse as element() (:: element(m:eliminarReservaFondosResponse) ::) external;

declare function local:func($eliminarReservaFondosResponse as element() (:: element(m:eliminarReservaFondosResponse) ::)) as element() (:: schema-element(ns2:EliminarReservaFondosMFCResponse) ::) {
    <ns2:EliminarReservaFondosMFCResponse>
        <ns2:Resultado>
            <res:result>{
            if ($eliminarReservaFondosResponse/codigoResultado_out = 0)then 'OK' else 'ERROR'
            }</res:result>
            <res:message>{
            if($eliminarReservaFondosResponse/codigoResultado_out != 0)then fn:data($eliminarReservaFondosResponse/mensajeError_out)else 'Eliminación Exitosa'
            }</res:message>
        </ns2:Resultado>
    </ns2:EliminarReservaFondosMFCResponse>
};

local:func($eliminarReservaFondosResponse)
