xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace flex="http://org/bcie/ws/middle/FLEXCUBE.wsdl";

declare namespace ns1="http://org/bcie/ws/middle/FLEXCUBE.wsdl/types/";
(:: import schema at "../../WSDL/FLEXCUBE_WSDL.wsdl" ::)
declare namespace ns2="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $crearLoanResponse as element() (:: element(flex:crearLoanResponse) ::) external;

declare function local:func($crearLoanResponse as element(flex:crearLoanResponse) (:: element(flex:crearLoanResponse) ::)) as element() (:: schema-element(ns2:CrearPrestamoFLEXCUBEResponse) ::) {
    <ns2:CrearPrestamoFLEXCUBEResponse>
        <ns2:codigoContrato>{fn:data($crearLoanResponse/codigoContrato_out)}</ns2:codigoContrato>
        <ns2:Resultado>
            <res:result>{
            if ($crearLoanResponse/codigoResultado_out = '0')
            then ('OK')
            else ('ERROR')
            }</res:result>
            <res:message>{
            if (fn:string-length($crearLoanResponse/mensaje_out) = 0)
            then ("Operación exitosa")
            else (fn:data($crearLoanResponse/mensaje_out/text()))
            }</res:message>
        </ns2:Resultado>
    </ns2:CrearPrestamoFLEXCUBEResponse>
};

local:func($crearLoanResponse)