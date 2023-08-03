xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/GestionCobroBO";
(:: import schema at "../../../../../../../MDS/Resources/ComponentesComunes/DominioCliente/GestionCobro/V1/Schema/GestionCobroBO.xsd" ::)
declare namespace ns2="http://www.bcie.org/GestionCobroMO";
(:: import schema at "../../../../../../../MDS/Resources/ComponentesComunes/DominioCliente/GestionCobro/V1/Schema/GestionCobroMO.xsd" ::)

declare namespace  ges="http://www.bcie.org/GestionCobroBO";
declare namespace ges1="http://www.bcie.org/GestionCobroBO";

declare variable $AvisoCobro as element() external;

declare function local:func($AvisoCobro as element()) as element() (:: schema-element(ns2:GenerarAvisoCobroV2Request) ::) {
    <ns2:GenerarAvisoCobroV2Request>
        <ns2:AvisoCobro>
            {
              $AvisoCobro/ges:aviso
            }
        </ns2:AvisoCobro>
    </ns2:GenerarAvisoCobroV2Request>
};

local:func($AvisoCobro)
