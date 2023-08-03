xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ConsultarComisionByMomentoCobro_DB";
(:: import schema at "../XSD/ConsultarComisionByMomentoCobro_DB_sp.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare variable $ConsultarComisionByMomentoCobroRequest as element() (:: schema-element(ns1:ConsultarComisionByMomentoCobroRequest) ::) external;

declare function local:func($ConsultarComisionByMomentoCobroRequest as element() (:: schema-element(ns1:ConsultarComisionByMomentoCobroRequest) ::)) as element() (:: schema-element(ns2:InputParameters) ::) {
    <ns2:InputParameters>
    <ns2:P_ID_MOMENTO_COBRO>
    {
      for $idMomentoCobro in $ConsultarComisionByMomentoCobroRequest/ns1:idMomentoCobro
            return
             <ns2:P_ID_MOMENTO_COBRO_ITEM>{fn:data($idMomentoCobro/cat:Id)}</ns2:P_ID_MOMENTO_COBRO_ITEM>
    }
    </ns2:P_ID_MOMENTO_COBRO>
        <ns2:P_ID_OPERACION>
            <ns2:P_ID_MOMENTO_COBRO_ITEM>{fn:data($ConsultarComisionByMomentoCobroRequest/ns1:idOperacion)}</ns2:P_ID_MOMENTO_COBRO_ITEM>
        </ns2:P_ID_OPERACION>
    </ns2:InputParameters>
};

local:func($ConsultarComisionByMomentoCobroRequest)
