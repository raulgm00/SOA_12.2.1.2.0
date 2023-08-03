xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/sp/CrearCommitment";
(:: import schema at "../XSD/CrearCommitment_sp.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $crearLoanResponse as element() (:: schema-element(ns1:OutputParameters) ::) external;

declare function local:func($crearLoanResponse as element() (:: schema-element(ns1:OutputParameters) ::)) as element() (:: schema-element(ns2:CrearPrestamoFLEXCUBEResponse) ::) {
    <ns2:CrearPrestamoFLEXCUBEResponse>
        <ns2:codigoContrato>{fn:data($crearLoanResponse/ns1:CODIGO_CONTRATO)}</ns2:codigoContrato>
        <ns2:Resultado>
            <res:result>{
            if ($crearLoanResponse/ns1:CODIGO_RESULTADO = '0')
            then ('OK')
            else ('ERROR')
            }</res:result>
            <res:message>{
            if (fn:string-length($crearLoanResponse/ns1:MENSAJE) = 0)
            then ("Operación exitosa")
            else (fn:data($crearLoanResponse/ns1:MENSAJE/text()))
            }</res:message>
        </ns2:Resultado>
    </ns2:CrearPrestamoFLEXCUBEResponse>
};

local:func($crearLoanResponse)