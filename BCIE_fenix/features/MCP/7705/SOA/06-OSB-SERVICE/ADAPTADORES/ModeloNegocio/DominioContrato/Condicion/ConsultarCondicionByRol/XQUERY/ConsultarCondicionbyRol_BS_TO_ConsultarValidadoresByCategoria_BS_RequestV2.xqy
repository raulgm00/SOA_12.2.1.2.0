xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)
declare namespace ns3="http://www.bcie.org/CondicionMO";
(:: import schema at "../../ConsultarValidacionCondicionByRol/XSD/CondicionMOV2.xsd" ::)

declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarValidadoresbyCategoria";
(:: import schema at "../XSD/ConsultarValidadoresbyCategoriaV2.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/consultarCondicionByRol";
(:: import schema at "../XSD/consultarCondicionByRolV2.xsd" ::)

declare variable $consultarCondicionByRolOutputCollection as element() (:: schema-element(ns1:consultarCondicionByRolOutputCollection) ::) external;

declare variable $ConsultarCondicionByRolRequest as element() (:: schema-element(ns3:ConsultarCondicionByRolRequest) ::) external;

declare function local:func($consultarCondicionByRolOutputCollection as element() (:: schema-element(ns1:consultarCondicionByRolOutputCollection) ::),
$ConsultarCondicionByRolRequest as element() (:: schema-element(ns3:ConsultarCondicionByRolRequest) ::)) as element() (:: schema-element(ns2:ConsultarValidadoresbyCategoriaInput) ::) {
    <ns2:ConsultarValidadoresbyCategoriaInput>
        <ns2:P_OPERACION>{fn:data($ConsultarCondicionByRolRequest/ns3:idOperacion)}</ns2:P_OPERACION>
        <ns2:P_ID_CONDICION>{fn:data($ConsultarCondicionByRolRequest/ns3:idCondicion)}</ns2:P_ID_CONDICION>

    </ns2:ConsultarValidadoresbyCategoriaInput>
};

local:func($consultarCondicionByRolOutputCollection,$ConsultarCondicionByRolRequest)