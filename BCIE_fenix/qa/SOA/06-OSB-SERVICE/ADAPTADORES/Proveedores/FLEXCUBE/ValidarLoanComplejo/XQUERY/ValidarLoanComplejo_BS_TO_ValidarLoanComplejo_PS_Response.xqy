xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V2/Schema/DesembolsoMOV2.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ValidarLoanComplejo";
(:: import schema at "../XSD/ValidarLoanComplejo_sp.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ValidarLoanComplejoOutput as element() (:: schema-element(ns1:OutputParameters) ::) external;

declare function local:func($ValidarLoanComplejoOutput as element() (:: schema-element(ns1:OutputParameters) ::)) as element() (:: schema-element(ns2:ValidarLoanComplejoResponse) ::) {
    <ns2:ValidarLoanComplejoResponse>
        <ns2:Resultado>
            <res:result>{if(string($ValidarLoanComplejoOutput/ns1:VALIDAR_LOAN_COMPLEJO) = '') then 'OK' else 'ERROR'}</res:result>
            <res:message>{if(string($ValidarLoanComplejoOutput/ns1:VALIDAR_LOAN_COMPLEJO) = '') then 'Registro exitoso' else fn:data($ValidarLoanComplejoOutput/ns1:VALIDAR_LOAN_COMPLEJO)}</res:message>
            <res:error>
                <err:errorCode></err:errorCode>
                <err:errorDescription></err:errorDescription>
                <err:errorType></err:errorType>
            </res:error>
        </ns2:Resultado>
    </ns2:ValidarLoanComplejoResponse>
};

local:func($ValidarLoanComplejoOutput)