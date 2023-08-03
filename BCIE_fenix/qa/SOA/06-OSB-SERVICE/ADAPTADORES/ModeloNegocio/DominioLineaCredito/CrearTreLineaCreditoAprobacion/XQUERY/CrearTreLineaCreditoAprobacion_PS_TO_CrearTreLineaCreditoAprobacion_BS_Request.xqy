xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/top/CrearTreLineaCreditoAprobacion";
(:: import schema at "../XSD/CrearTreLineaCreditoAprobacion_table.xsd" ::)

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare variable $CrearTreLineaCreditoAprobacionRequest as element() (:: schema-element(ns1:CrearTreLineaCreditoAprobacionRequest) ::) external;

declare function local:func($CrearTreLineaCreditoAprobacionRequest as element() (:: schema-element(ns1:CrearTreLineaCreditoAprobacionRequest) ::)) as element() (:: schema-element(ns2:TreLineaCreditoAprobacionCollection) ::) {
    <ns2:TreLineaCreditoAprobacionCollection>
    {
    for $TreLinea in $CrearTreLineaCreditoAprobacionRequest/ns1:TreLineaCreditoAprobacion
    return
        <ns2:TreLineaCreditoAprobacion>
          <ns2:idLineaCredito>{fn:data($TreLinea/lin:idLineaCredito)}</ns2:idLineaCredito>
          <ns2:idAprobacion>{fn:data($TreLinea/lin:idAprobacion)}</ns2:idAprobacion>
        </ns2:TreLineaCreditoAprobacion>
      }
    </ns2:TreLineaCreditoAprobacionCollection>
};

local:func($CrearTreLineaCreditoAprobacionRequest)
