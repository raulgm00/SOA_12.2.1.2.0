xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ValidarLimiteLineaFinaciera_DB";
(:: import schema at "../XSD/ValidarLimiteLineaFinaciera_DB_sp.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace reg = "http://www.bcie.org/ReglaBO";

declare variable $OutputParameters as element() (:: schema-element(ns1:OutputParameters) ::) external;

declare function local:func($OutputParameters as element() (:: schema-element(ns1:OutputParameters) ::)) as element() (:: schema-element(ns2:ValidarLimiteLineaFinacieraResponse) ::) {
    <ns2:ValidarLimiteLineaFinacieraResponse>
        <ns2:Regla>
            <reg:Estado>
                <cat:Id></cat:Id>
                <cat:Descripcion></cat:Descripcion>
                <cat:DescripcionCorta>
                {if (fn:data($OutputParameters/ns1:P_CODIGO_RES/text())= 0)
                then 'CUMPLIDA'
                else 'NO_CUMPLIDA'
                }
                </cat:DescripcionCorta>
                <cat:estatus></cat:estatus>
                <cat:codigoExterno></cat:codigoExterno>
            </reg:Estado>
        </ns2:Regla>
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>{fn:data($OutputParameters/ns1:P_MENSAJE)}</res:message>
            <res:error>
                <err:errorCode>{fn:data($OutputParameters/ns1:P_CODIGO_RES)}</err:errorCode>
                <err:errorDescription></err:errorDescription>
                <err:errorType>{fn:data($OutputParameters/ns1:P_TIPO_RES)}</err:errorType>
            </res:error>
        </ns2:Resultado>
    </ns2:ValidarLimiteLineaFinacieraResponse>
};

local:func($OutputParameters)
