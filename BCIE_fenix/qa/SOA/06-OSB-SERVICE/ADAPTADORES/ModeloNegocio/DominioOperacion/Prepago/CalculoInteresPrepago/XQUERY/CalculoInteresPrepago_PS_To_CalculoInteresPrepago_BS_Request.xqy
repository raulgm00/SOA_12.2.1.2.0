xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/PrepagoMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Prepago/V1/Schema/PrepagoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/CalculoInteresPrepago";
(:: import schema at "../XSD/CalculoInteresPrepago_sp.xsd" ::)

declare variable $CalculoInteresPrepago as element() (:: schema-element(ns1:CalculoInteresPrepagoRequest) ::) external;

declare function local:func($CalculoInteresPrepago as element() (:: schema-element(ns1:CalculoInteresPrepagoRequest) ::)) as element() (:: schema-element(ns2:InputParameters) ::) {
    <ns2:InputParameters>
        {
            if ($CalculoInteresPrepago/ns1:P_PREPAGO)
            then <ns2:P_PREPAGO>{fn:data($CalculoInteresPrepago/ns1:P_PREPAGO)}</ns2:P_PREPAGO>
            else ()
        }
    </ns2:InputParameters>
};

local:func($CalculoInteresPrepago)