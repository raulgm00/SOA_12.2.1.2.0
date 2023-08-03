xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ConsultaDesembolsoById";
(:: import schema at "../XSD/ConsultaDesembolsoById_sp.xsd" ::)

declare variable $ConsultarDesembolsosByIdRequest as element() (:: schema-element(ns1:ConsultarDesembolsosByIdRequest) ::) external;

declare function local:func($ConsultarDesembolsosByIdRequest as element() (:: schema-element(ns1:ConsultarDesembolsosByIdRequest) ::)) as element() (:: schema-element(ns2:InputParameters) ::) {
    <ns2:InputParameters>
   
        <ns2:P_ID_DESEMBOLSO>
            {
        for $consulta in $ConsultarDesembolsosByIdRequest/ns1:idContratoDesembolso
        return
                <ns2:P_ID_DESEMBOLSO_ITEM>{fn:data($consulta)}</ns2:P_ID_DESEMBOLSO_ITEM>
            }
        </ns2:P_ID_DESEMBOLSO>
    </ns2:InputParameters>
};

local:func($ConsultarDesembolsosByIdRequest)
