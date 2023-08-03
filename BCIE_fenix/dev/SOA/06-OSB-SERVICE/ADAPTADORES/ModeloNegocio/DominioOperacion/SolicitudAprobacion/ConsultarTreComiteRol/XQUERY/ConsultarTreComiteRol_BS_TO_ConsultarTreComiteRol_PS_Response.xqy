xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/SolicitudAprobacionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/SolicitudAprobacion/V1/Schema/SolicitudAprobacionMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarTreComiteRol";
(:: import schema at "../XSD/ConsultarTreComiteRol.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ConsultarTreComiteRolOutputCollection as element() (:: schema-element(ns1:ConsultarTreComiteRolOutputCollection) ::) external;

declare function local:func($ConsultarTreComiteRolOutputCollection as element() (:: schema-element(ns1:ConsultarTreComiteRolOutputCollection) ::)) as element() (:: schema-element(ns2:ConsultarTreComiteRolResponse) ::) {
    <ns2:ConsultarTreComiteRolResponse>
    {
        for $i in $ConsultarTreComiteRolOutputCollection/ns1:ConsultarTreComiteRolOutput
        return
        <ns2:idRol>{fn:data($i/ns1:ID)}</ns2:idRol>
      
      
      }  <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>Consulta correcta</res:message>
        </ns2:Resultado>
    </ns2:ConsultarTreComiteRolResponse>
};

local:func($ConsultarTreComiteRolOutputCollection)
