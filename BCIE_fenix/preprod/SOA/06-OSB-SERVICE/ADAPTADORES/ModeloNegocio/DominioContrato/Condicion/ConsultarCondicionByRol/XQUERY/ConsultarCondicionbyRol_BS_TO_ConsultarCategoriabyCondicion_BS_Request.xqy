xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)
declare namespace ns3="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)

declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarCategoriaByCondicion";
(:: import schema at "../../ConsultarCategoriaByCondicion/XSD/ConsultarCategoriaByCondicion.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/consultarCondicionByRol";
(:: import schema at "../XSD/consultarCondicionByRol.xsd" ::)

declare variable $consultarCondicionByRolOutputCollection as element() (:: schema-element(ns1:consultarCondicionByRolOutputCollection) ::) external;

declare variable $ConsultarCondicionByRolRequest as element() (:: schema-element(ns3:ConsultarCondicionByRolRequest) ::) external;

declare function local:func($consultarCondicionByRolOutputCollection as element() (:: schema-element(ns1:consultarCondicionByRolOutputCollection) ::),
$ConsultarCondicionByRolRequest as element() (:: schema-element(ns3:ConsultarCondicionByRolRequest) ::)
) as element() (:: schema-element(ns2:ConsultarCategoriaByCondicionInput) ::) {
    <ns2:ConsultarCategoriaByCondicionInput>
        <ns2:P_OPERACION>{fn:data($ConsultarCondicionByRolRequest/ns3:idOperacion)}</ns2:P_OPERACION>
        
    </ns2:ConsultarCategoriaByCondicionInput>
};

local:func($consultarCondicionByRolOutputCollection,$ConsultarCondicionByRolRequest)