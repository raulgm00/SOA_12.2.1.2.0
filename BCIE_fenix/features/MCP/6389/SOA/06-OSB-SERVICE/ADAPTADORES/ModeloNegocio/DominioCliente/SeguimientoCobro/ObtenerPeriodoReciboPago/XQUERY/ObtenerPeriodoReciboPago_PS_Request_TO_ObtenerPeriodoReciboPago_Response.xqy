xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/GestionCobroMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioCliente/GestionCobro/V1/Schema/GestionCobroMO.xsd" ::)

declare namespace functx = "http://www.functx.com"; 

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO"; 


declare variable $ObtenerPeriodoReciboPagoRequest as element() (:: schema-element(ns1:ObtenerPeriodoReciboPagoRequest) ::) external;

declare function functx:day-of-week
  ( $date as xs:anyAtomicType? )  as xs:integer? {

  if (empty($date))
  then ()
  else xs:integer((xs:date($date) - xs:date('1901-01-06'))
          div xs:dayTimeDuration('P1D')) mod 7
 } ;
 
declare function local:func($ObtenerPeriodoReciboPagoRequest as element() (:: schema-element(ns1:ObtenerPeriodoReciboPagoRequest) ::)) as element() (:: schema-element(ns1:ObtenerPeriodoReciboPagoResponse) ::) {

    <ns1:ObtenerPeriodoReciboPagoResponse>
      <ns1:fechaInicio>
      {
        if (functx:day-of-week($ObtenerPeriodoReciboPagoRequest/ns1:fechaBase)= 1) then 
          fn:data(xs:date($ObtenerPeriodoReciboPagoRequest/ns1:fechaBase) - xs:dayTimeDuration("P3D")) 
        else 
          fn:data(xs:date($ObtenerPeriodoReciboPagoRequest/ns1:fechaBase) - xs:dayTimeDuration("P1D"))
      }</ns1:fechaInicio> 
      <ns1:fechaFin>{fn:data(xs:date($ObtenerPeriodoReciboPagoRequest/ns1:fechaBase) - xs:dayTimeDuration("P1D"))}</ns1:fechaFin>
	  <ns1:Resultado>
		<res:result>OK</res:result>
		<res:message>Operacion Exitosa</res:message>
      </ns1:Resultado>
    </ns1:ObtenerPeriodoReciboPagoResponse>
};

local:func($ObtenerPeriodoReciboPagoRequest)
