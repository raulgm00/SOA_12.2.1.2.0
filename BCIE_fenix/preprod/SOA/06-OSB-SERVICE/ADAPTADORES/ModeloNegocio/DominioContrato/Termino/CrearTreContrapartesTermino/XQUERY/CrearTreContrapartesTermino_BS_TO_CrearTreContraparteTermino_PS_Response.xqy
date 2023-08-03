xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/TerminoMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Termino/V1/Schema/TerminoMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $idTreContraparteTermino as xs:int external;

declare function local:func($idTreContraparteTermino as xs:int) as element() (:: schema-element(ns1:CrearTreContrapartesTerminoResponse) ::) {
    <ns1:CrearTreContrapartesTerminoResponse>
        <ns1:idTreContrapartesTermino>{fn:data($idTreContraparteTermino)}</ns1:idTreContrapartesTermino>
        <ns1:Resultado>
            <res:result>OK</res:result>
            <res:message>La creación se ha realizado exitosamente</res:message>
            <res:error>
                <err:errorCode></err:errorCode>
                <err:errorDescription></err:errorDescription>
                <err:errorType></err:errorType>
            </res:error>
        </ns1:Resultado>
    </ns1:CrearTreContrapartesTerminoResponse>
};

local:func($idTreContraparteTermino)
