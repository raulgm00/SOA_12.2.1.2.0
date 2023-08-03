xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ConsultarCumplimientoCondiciones";
(:: import schema at "../XSD/ConsultarCumplimientoCondiciones_sp.xsd" ::)

declare namespace con = "http://www.bcie.org/CondicionBO";

declare variable $ConsultarCumplimientoCondicionesRequest as element() (:: schema-element(ns1:ConsultarCumplimientoCondicionesRequest) ::) external;

declare function local:func($ConsultarCumplimientoCondicionesRequest as element() (:: schema-element(ns1:ConsultarCumplimientoCondicionesRequest) ::)) as element() (:: schema-element(ns2:InputParameters) ::) {
    <ns2:InputParameters>
        <ns2:P_ID_CONDICION>
          {for $condicion in $ConsultarCumplimientoCondicionesRequest/ns1:Condicion
          return
            <ns2:P_ID_CONDICION_ITEM>{fn:data($condicion/con:idCondicion)}</ns2:P_ID_CONDICION_ITEM>
          }  
        </ns2:P_ID_CONDICION>
    </ns2:InputParameters>
};

local:func($ConsultarCumplimientoCondicionesRequest)
