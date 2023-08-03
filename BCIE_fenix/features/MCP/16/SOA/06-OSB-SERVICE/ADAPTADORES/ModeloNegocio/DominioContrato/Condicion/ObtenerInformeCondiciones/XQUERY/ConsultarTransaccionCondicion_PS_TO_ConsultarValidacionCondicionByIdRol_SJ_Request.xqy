xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)

declare namespace con = "http://www.bcie.org/CondicionBO";

declare variable $ConsultarTransaccionCondicionResponse as element() (:: schema-element(ns1:ConsultarTransaccionCondicionResponse) ::) external;
declare variable $ObtenerInformeCondicionesRequest as element() (:: schema-element(ns1:ObtenerInformeCondicionesRequest) ::) external;

declare function local:func($ConsultarTransaccionCondicionResponse as element() (:: schema-element(ns1:ConsultarTransaccionCondicionResponse) ::),
$ObtenerInformeCondicionesRequest as element() (:: schema-element(ns1:ObtenerInformeCondicionesRequest) ::)) as element() (:: schema-element(ns1:ConsultarValidacionCondicionByRolSJRequest) ::) {

  let $CondicionesEnProceso:= $ConsultarTransaccionCondicionResponse/ns1:TransaccionCondicion[con:enProceso = true()]
  return
    <ns1:ConsultarValidacionCondicionByRolSJRequest>
        <ns1:idOperacion>{fn:data($ObtenerInformeCondicionesRequest/ns1:idOperacion)}</ns1:idOperacion>
        {
        for $agrupador in distinct-values($CondicionesEnProceso/con:agrupador)
        return
        <ns1:agrupador>{fn:data($agrupador)}</ns1:agrupador>
        }
    </ns1:ConsultarValidacionCondicionByRolSJRequest>
};

local:func($ConsultarTransaccionCondicionResponse, $ObtenerInformeCondicionesRequest)
