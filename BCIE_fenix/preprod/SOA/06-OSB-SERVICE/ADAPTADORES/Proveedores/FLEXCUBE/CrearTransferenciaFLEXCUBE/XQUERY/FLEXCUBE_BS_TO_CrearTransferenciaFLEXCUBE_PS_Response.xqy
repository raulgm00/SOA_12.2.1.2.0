xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace m="http://org/bcie/ws/middle/FLEXCUBE.wsdl";
declare namespace typ="http://org/bcie/ws/middle/FLEXCUBE.wsdl/types/";
(:: import schema at "../../WSDL/FLEXCUBE_WSDL.wsdl" ::)

declare namespace ns2="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $crearTransferenciaResponse as element() (:: element(m:crearTransferenciaResponse) ::) external;

declare function local:func($crearTransferenciaResponse as element() (:: element(m:crearTransferenciaResponse) ::)) as element() (:: schema-element(ns2:CrearTransferenciaFLEXCUBEResponse) ::) {
    <ns2:CrearTransferenciaFLEXCUBEResponse>
        <ns2:ContratoDesembolso>
            <des:Transferencia>
                <des:idTransferencia></des:idTransferencia>
                <des:idDesembolso></des:idDesembolso>
                <des:idFacturador>{fn:data($crearTransferenciaResponse/codigoContrato_out)}</des:idFacturador>
            </des:Transferencia>
        </ns2:ContratoDesembolso>
        <ns2:Resultado>
            <res:result>{
            if (string($crearTransferenciaResponse/codigoResultado_out) = '0')then 'OK' else 'ERROR'
            }</res:result>
            <res:message>{
             if (string($crearTransferenciaResponse/codigoResultado_out) = '0')then 'Registro exitoso'
             else string(fn:data($crearTransferenciaResponse/mensaje_out))
            }</res:message>
        </ns2:Resultado>
    </ns2:CrearTransferenciaFLEXCUBEResponse>
};

local:func($crearTransferenciaResponse)
