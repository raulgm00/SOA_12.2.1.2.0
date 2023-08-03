xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/consultarCondicionByRol";
(:: import schema at "../XSD/ConsultarCondicionByIdEvento.xsd" ::)

declare variable $ConsultarCondicionByIdEventoRequest as element() (:: schema-element(ns1:ConsultarCondicionByIdEventoRequest) ::) external;

declare function local:func($ConsultarCondicionByIdEventoRequest as element() (:: schema-element(ns1:ConsultarCondicionByIdEventoRequest) ::)) as element() (:: schema-element(ns2:consultarCondicionByRolInput) ::) {
    <ns2:consultarCondicionByRolInput>
        <ns2:P_ID_OPERACION>{fn:data($ConsultarCondicionByIdEventoRequest/ns1:idOperacion)}</ns2:P_ID_OPERACION>
        <ns2:P_TCA_EVENTO_CONDICION>{fn:data($ConsultarCondicionByIdEventoRequest/ns1:idEventoCondicion)}</ns2:P_TCA_EVENTO_CONDICION>
        <ns2:P_ID_ROL>{fn:data($ConsultarCondicionByIdEventoRequest/ns1:idRol)}</ns2:P_ID_ROL>
        <ns2:ID_TCA_TIPO_DESEMBOLSO>{fn:data($ConsultarCondicionByIdEventoRequest/ns1:idTipoDesembolso)}</ns2:ID_TCA_TIPO_DESEMBOLSO>
    </ns2:consultarCondicionByRolInput>
};

local:func($ConsultarCondicionByIdEventoRequest)
