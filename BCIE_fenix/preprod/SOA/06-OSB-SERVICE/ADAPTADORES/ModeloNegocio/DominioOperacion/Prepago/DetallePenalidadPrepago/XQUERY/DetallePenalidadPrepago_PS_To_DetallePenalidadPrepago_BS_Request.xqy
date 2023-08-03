xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/PrepagoMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Prepago/V1/Schema/PrepagoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/DetallePenalidadPrepago";
(:: import schema at "../XSD/DetallePenalidadPrepago_sp.xsd" ::)

declare variable $request as element() (:: schema-element(ns1:DetallePenalidadPrepagoRequest) ::) external;

declare function local:func($request as element() (:: schema-element(ns1:DetallePenalidadPrepagoRequest) ::)) as element() (:: schema-element(ns2:InputParameters) ::) {
    <ns2:InputParameters>
        {
            if ($request/ns1:P_IDPREPAGO)
            then <ns2:P_IDPREPAGO>{fn:data($request/ns1:P_IDPREPAGO)}</ns2:P_IDPREPAGO>
            else ()
        }
        {
            if ($request/ns1:P_IDOPERACION)
            then <ns2:P_IDOPERACION>{fn:data($request/ns1:P_IDOPERACION)}</ns2:P_IDOPERACION>
            else ()
        }
    </ns2:InputParameters>
};

local:func($request)