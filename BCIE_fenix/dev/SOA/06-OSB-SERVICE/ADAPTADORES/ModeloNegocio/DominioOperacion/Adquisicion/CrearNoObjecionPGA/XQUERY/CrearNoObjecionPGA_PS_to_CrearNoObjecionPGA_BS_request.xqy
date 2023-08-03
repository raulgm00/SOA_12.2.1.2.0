xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/AdquisicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Adquisicion/V1/Schema/AdquisicionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/CrearNoObjecionPGA";
(:: import schema at "../XSD/CrearNoObjecionPGA_sp.xsd" ::) 

declare variable $request as element() (:: schema-element(ns1:CrearNoObjecionPGA_Request) ::) external;

declare function local:func($request as element() (:: schema-element(ns1:CrearNoObjecionPGA_Request) ::)) as element() (:: schema-element(ns2:InputParameters) ::) {
    <ns2:InputParameters>
        <ns2:PNID_OPERACION>{fn:data($request/ns1:PNID_OPERACION)}</ns2:PNID_OPERACION>
        <ns2:PVNUMERO_CORRESPONDENCIA>{fn:data($request/ns1:PVNUMERO_CORRESPONDENCIA)}</ns2:PVNUMERO_CORRESPONDENCIA>
        <ns2:P_DOCUMENTOS>
            {
                for $P_DOCUMENTOS_ITEM in $request/ns1:P_DOCUMENTOS/ns1:P_DOCUMENTOS_ITEM
                return 
                <ns2:P_DOCUMENTOS_ITEM>{fn:data($request/ns1:P_DOCUMENTOS/ns1:P_DOCUMENTOS_ITEM)}</ns2:P_DOCUMENTOS_ITEM>
            }
        </ns2:P_DOCUMENTOS>
    </ns2:InputParameters>
};

local:func($request)
