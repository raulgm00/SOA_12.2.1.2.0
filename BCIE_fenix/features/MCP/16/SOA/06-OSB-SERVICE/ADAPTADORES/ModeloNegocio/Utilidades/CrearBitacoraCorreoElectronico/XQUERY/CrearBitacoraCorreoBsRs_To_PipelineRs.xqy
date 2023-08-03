xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns3="http://www.bcie.org/CorreoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/EnviarCorreoElectronico/V1/Schema/CorreoElectronicoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/top/InsertaBiCorreoElectronico";
(:: import schema at "../XSD/InsertaBiCorreoElectronico_table.xsd" ::)

declare namespace ns1="http://www.w3.org/2005/xquery-local-functions";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $TbiEnvioCorreo as element() (:: schema-element(ns2:TbiEnvioCorreo) ::) external;

declare function ns1:func($TbiEnvioCorreo as element() (:: schema-element(ns2:TbiEnvioCorreo) ::)) as element() (:: schema-element(ns3:crearBitacoraCorreoElectronicoResponse) ::) {
    <ns3:crearBitacoraCorreoElectronicoResponse>
        <ns3:BitacoraId>{fn:data($TbiEnvioCorreo/ns2:TbiErrorEnvioCorreo[1]/ns2:id)}</ns3:BitacoraId>
        <ns3:Resultado>
            <res:result>OK</res:result>
            <res:message>La insercion se ha realizado correctamente</res:message>
        </ns3:Resultado>
    </ns3:crearBitacoraCorreoElectronicoResponse>
};

ns1:func($TbiEnvioCorreo)
