xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns3="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/CalcularPlazoCondiciones";
(:: import schema at "../XSD/CalcularPlazoCondiciones_sp.xsd" ::)

declare namespace ns1="http://www.w3.org/2005/xquery-local-functions";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $OutputParameters as element() (:: schema-element(ns2:OutputParameters) ::) external;

declare function ns1:func($OutputParameters as element() (:: schema-element(ns2:OutputParameters) ::)) as element() (:: schema-element(ns3:CalcularPlazoCondicionesResponse) ::) {
    <ns3:CalcularPlazoCondicionesResponse>
        {
            if ($OutputParameters/ns2:P_CODIGO = '0' ) then
                <ns3:Estado>{fn:data($OutputParameters/ns2:P_ESTADO)}</ns3:Estado>
            else
                ()
        }
        
        {
            if ($OutputParameters/ns2:P_CODIGO = '0' ) then
                <ns3:Resultado>
                  <res:result>OK</res:result>
                  <res:message>PROCEDIMIENTO EXITOSO</res:message>
                </ns3:Resultado>
            else
                <ns3:Resultado>
                    <res:result>ERROR</res:result>
                    <res:message>{fn:data($OutputParameters/ns2:P_MENSAJE)}</res:message>
                </ns3:Resultado>
        }
        
    </ns3:CalcularPlazoCondicionesResponse>
};

ns1:func($OutputParameters)
