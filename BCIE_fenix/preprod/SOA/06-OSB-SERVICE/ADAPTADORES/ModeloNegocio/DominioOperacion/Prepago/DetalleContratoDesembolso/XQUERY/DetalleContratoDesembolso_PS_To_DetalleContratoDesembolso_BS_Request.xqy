xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/PrepagoMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Prepago/V1/Schema/PrepagoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/DetalleContratoDesembolso";
(:: import schema at "../XSD/DetalleContratoDesembolso_sp.xsd" ::)

declare variable $request as element() (:: schema-element(ns1:DetalleContratoDesembolsoRequest) ::) external;

declare function local:func($request as element() (:: schema-element(ns1:DetalleContratoDesembolsoRequest) ::)) as element() (:: schema-element(ns2:InputParameters) ::) {
    <ns2:InputParameters>
        {
            if ($request/ns1:P_ID_OPERACION)
            then <ns2:P_ID_OPERACION>{fn:data($request/ns1:P_ID_OPERACION)}</ns2:P_ID_OPERACION>
            else ()
        }
        {
            if ($request/ns1:P_ID_RESOLUCION)
            then <ns2:P_ID_RESOLUCION>{fn:data($request/ns1:P_ID_RESOLUCION)}</ns2:P_ID_RESOLUCION>
            else ()
        }
        {
            if ($request/ns1:P_ID_MONEDA)
            then <ns2:P_ID_MONEDA>{fn:data($request/ns1:P_ID_MONEDA)}</ns2:P_ID_MONEDA>
            else ()
        }
        {
            if ($request/ns1:P_ID_PREPAGO)
            then <ns2:P_ID_PREPAGO>{fn:data($request/ns1:P_ID_PREPAGO)}</ns2:P_ID_PREPAGO>
            else ()
        }
        {
            if ($request/ns1:P_F_RENOVACION)
            then <ns2:P_F_RENOVACION>{fn:data($request/ns1:P_F_RENOVACION)}</ns2:P_F_RENOVACION>
            else ()
        }
        {
            if ($request/ns1:P_F_PREPAGO)
            then <ns2:P_F_PREPAGO>{fn:data($request/ns1:P_F_PREPAGO)}</ns2:P_F_PREPAGO>
            else ()
        }
        {
            if ($request/ns1:P_SIN_TRE_PREPAGO_CONTRATO)
            then <ns2:P_SIN_TRE_PREPAGO_CONTRATO>{fn:data($request/ns1:P_SIN_TRE_PREPAGO_CONTRATO)}</ns2:P_SIN_TRE_PREPAGO_CONTRATO>
            else ()
        } 
    </ns2:InputParameters>
};

local:func($request)