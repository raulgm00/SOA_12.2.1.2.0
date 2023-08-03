xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/consultarCondicionByRol";
(:: import schema at "../XSD/consultarCondicionByRol.xsd" ::)

declare variable $ConsultarCondicionByRolRequest as element() (:: schema-element(ns1:ConsultarCondicionByRolRequest) ::) external;

declare function local:func($ConsultarCondicionByRolRequest as element() (:: schema-element(ns1:ConsultarCondicionByRolRequest) ::)) as element() (:: schema-element(ns2:consultarCondicionByRolInput) ::) {
    <ns2:consultarCondicionByRolInput>
        <ns2:P_ID_OPERACION>{if ((string($ConsultarCondicionByRolRequest/ns1:Agrupador)='' or  string($ConsultarCondicionByRolRequest/ns1:Agrupador)='0' )
        )then
         fn:data($ConsultarCondicionByRolRequest/ns1:idOperacion)
        else ()
       }</ns2:P_ID_OPERACION>
       <ns2:P_ID_ROL>{ if (string($ConsultarCondicionByRolRequest/ns1:idRol)='0' or string($ConsultarCondicionByRolRequest/ns1:idRol)='') 
        then ()else
        fn:data($ConsultarCondicionByRolRequest/ns1:idRol)
        }</ns2:P_ID_ROL>
        <ns2:P_AGRUPADOR>{fn:data($ConsultarCondicionByRolRequest/ns1:Agrupador)}</ns2:P_AGRUPADOR>
        <ns2:P_ID_OPERACION>{fn:data($ConsultarCondicionByRolRequest/ns1:idOperacion)}</ns2:P_ID_OPERACION>
        <ns2:P_AGRUPADOR>{fn:data($ConsultarCondicionByRolRequest/ns1:Agrupador)}</ns2:P_AGRUPADOR>
        <ns2:P_ID_ROL>{ if (string($ConsultarCondicionByRolRequest/ns1:idRol)='0' or string($ConsultarCondicionByRolRequest/ns1:idRol)='') 
        then ()else
        fn:data($ConsultarCondicionByRolRequest/ns1:idRol)
        }</ns2:P_ID_ROL>
    </ns2:consultarCondicionByRolInput>
};

local:func($ConsultarCondicionByRolRequest)