xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V2/Schema/DesembolsoMOV2.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ConsultarDesembolsoByIdV2";
(:: import schema at "../XSD/ConsultarDesembolsoByIdV2_sp.xsd" ::)

declare variable $ConsultarDesembolsosByIdV2Request as element() (:: schema-element(ns1:ConsultarDesembolsosByIdV2Request) ::) external;

declare function local:func($ConsultarDesembolsosByIdV2Request as element() (:: schema-element(ns1:ConsultarDesembolsosByIdV2Request) ::)) as element() (:: schema-element(ns2:InputParameters) ::) {
    <ns2:InputParameters>
        <ns2:P_ID_DESEMBOLSO>
            {
                for $idContratoDesembolso in $ConsultarDesembolsosByIdV2Request/ns1:idContratoDesembolso
                return 
                <ns2:P_ID_DESEMBOLSO_ITEM>{$idContratoDesembolso/text()}</ns2:P_ID_DESEMBOLSO_ITEM>
            }
        </ns2:P_ID_DESEMBOLSO>
    </ns2:InputParameters>
};

local:func($ConsultarDesembolsosByIdV2Request)
