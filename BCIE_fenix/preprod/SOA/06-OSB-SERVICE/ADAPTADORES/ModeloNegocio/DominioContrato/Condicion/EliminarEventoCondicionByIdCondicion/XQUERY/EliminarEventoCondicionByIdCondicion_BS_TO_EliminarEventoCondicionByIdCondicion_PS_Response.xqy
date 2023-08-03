xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/EliminarEventoCondicionByidCondicion_BS";
(:: import schema at "../XSD/EliminarEventoCondicionByidCondicion_BS.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $EliminarEventoCondicionByidCondicion_BSInput as element() (:: schema-element(ns1:EliminarEventoCondicionByidCondicion_BSInput) ::) external;

declare function local:func($EliminarEventoCondicionByidCondicion_BSInput as element() (:: schema-element(ns1:EliminarEventoCondicionByidCondicion_BSInput) ::)) as element() (:: schema-element(ns2:EliminarEventoCondicionByIdCondicionResponse) ::) {
    <ns2:EliminarEventoCondicionByIdCondicionResponse>
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>Los registros se eliminaron correctamente</res:message>
            <res:error>
                <err:errorCode></err:errorCode>
                <err:errorDescription></err:errorDescription>
                <err:errorType></err:errorType>
            </res:error>
        </ns2:Resultado>
    </ns2:EliminarEventoCondicionByIdCondicionResponse>
};

local:func($EliminarEventoCondicionByidCondicion_BSInput)
