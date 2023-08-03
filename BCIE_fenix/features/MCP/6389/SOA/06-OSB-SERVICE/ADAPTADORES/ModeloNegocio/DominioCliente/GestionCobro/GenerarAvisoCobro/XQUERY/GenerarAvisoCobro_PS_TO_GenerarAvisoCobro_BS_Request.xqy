xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/GestionCobroBO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioCliente/GestionCobro/V1/Schema/GestionCobroBO.xsd" ::)
declare namespace ns2="http://www.bcie.org/GestionCobroMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioCliente/GestionCobro/V1/Schema/GestionCobroMO.xsd" ::)

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare variable $AvisoCobro as element() (:: schema-element(ns1:AvisoCobro) ::) external;

declare function local:func($AvisoCobro as element() (:: schema-element(ns1:AvisoCobro) ::)) as element() (:: schema-element(ns2:GenerarAvisoCobroRequest) ::) {
    <ns2:GenerarAvisoCobroRequest>
        <ns2:AvisoCobro>
        {
        for $aviso in $AvisoCobro/ns1:aviso
        return
           $aviso
            }
        </ns2:AvisoCobro>
    </ns2:GenerarAvisoCobroRequest>
};

local:func($AvisoCobro)
