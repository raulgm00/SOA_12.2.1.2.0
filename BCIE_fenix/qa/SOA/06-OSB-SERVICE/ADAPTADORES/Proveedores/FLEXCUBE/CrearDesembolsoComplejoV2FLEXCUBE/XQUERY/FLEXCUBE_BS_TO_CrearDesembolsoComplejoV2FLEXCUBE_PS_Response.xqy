xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace m="http://org/bcie/ws/middle/FLEXCUBE.wsdl";
declare namespace ns2="http://org/bcie/ws/middle/FLEXCUBE.wsdl/types/";
(:: import schema at "../../WSDL/FLEXCUBE_WSDL.wsdl" ::)
declare namespace ns1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V2/Schema/DesembolsoMOV2.xsd" ::)


declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace des = "http://www.bcie.org/DesembolsoBOV2";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $crearLoanComplejoResponse as element() (:: element(m:crearLoanComplejoResponse) ::) external;

declare variable $referencia as xs:string external;

declare function local:func($crearLoanComplejoResponse as element() (:: element(m:crearLoanComplejoResponse) ::),$referencia as xs:string) as element() (:: schema-element(ns1:CrearDesembolsoComplejoV2FLEXCUBEResponse) ::) {
    <ns1:CrearDesembolsoComplejoV2FLEXCUBEResponse>
    <ns1:ContratoDesembolso>
            <des:idDesembolso></des:idDesembolso>
            <des:idFacturador>{fn:data($crearLoanComplejoResponse/codigoContrato_out)}</des:idFacturador>
            <des:referencia>{$referencia}</des:referencia>
        </ns1:ContratoDesembolso>
        <ns1:Resultado>
            <res:result>{
            if(string($crearLoanComplejoResponse/codigoResultado_out) = '0') then 'OK' else 'ERROR'}</res:result>
            <res:message>{
            if(string($crearLoanComplejoResponse/codigoResultado_out) = '0') then 'Registro exitoso'
            else fn:data($crearLoanComplejoResponse/mensaje_out)
            }</res:message>
        </ns1:Resultado>
    </ns1:CrearDesembolsoComplejoV2FLEXCUBEResponse>
};

local:func($crearLoanComplejoResponse,$referencia)
