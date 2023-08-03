xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/GestionCobroMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioCliente/GestionCobro/V1/Schema/GestionCobroMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarFondosAvisoCobro";
(:: import schema at "../../ConsultarFondosAvisoCobro/XSD/ConsultarFondosAvisoCobro.xsd" ::)

declare namespace ges = "http://www.bcie.org/GestionCobroBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace functx = "http://www.functx.com"; 
 
declare variable $ConsultarParametrosAvisoCobroRequest as element() (:: schema-element(ns1:ConsultarParametrosAvisoCobroRequest) ::) external;
declare variable $ConsultarFondosAvisoCobroOutputCollection as element() (:: schema-element(ns2:ConsultarFondosAvisoCobroOutputCollection) ::) external;

(: FUNCION PARA OBTENER EL PRIMER DIA DE CUALQUIER MES :)
declare function functx:first-day-of-month( $date as xs:anyAtomicType? )  as xs:date? {
  if(fn:month-from-date($date) < 10)then
    xs:date(fn:concat(fn:year-from-date($date),"-0",fn:month-from-date($date),"-","01"))
  else
    xs:date(fn:concat(fn:year-from-date($date),"-",fn:month-from-date($date),"-","01"))
 } ;
 
 (: FUNCION PARA OBTENER EL ÚLTIMO DIA DE CUALQUIER MES :)
 declare function functx:last-day-of-month( $date as xs:anyAtomicType? )  as xs:date? {
  if(fn:month-from-date($date) < 10)then
   xs:date(fn:concat(year-from-date(xs:date($date)),"-0",month-from-date(xs:date($date)),"-",functx:days-in-month($date)))
  else
  xs:date(fn:concat(year-from-date(xs:date($date)),"-",month-from-date(xs:date($date)),"-",functx:days-in-month($date)))
 } ;
 
 (:FUNCION PARA SABER SI ES AÑO BICIESTO, Y QUE ULTIMO DIA CORRESPONDE DEPENDIENDO EL MES:)
 declare function functx:days-in-month($date as xs:anyAtomicType?)  as xs:integer? {
   if (month-from-date(xs:date($date)) = 2 and functx:is-leap-year($date))
   then 29
   else
   (31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
    [month-from-date(xs:date($date))]
 } ;
 
 (:FUNCION PARA DETERMINAR SI ES AÑO BICIESTO :)
 declare function functx:is-leap-year
  ( $date as xs:anyAtomicType? )  as xs:boolean {

    for $year in xs:integer(substring(string($date),1,4))
    return ($year mod 4 = 0 and $year mod 100 != 0) or $year mod 400 = 0
 } ;
 
declare function local:func($ConsultarParametrosAvisoCobroRequest as element() (:: schema-element(ns1:ConsultarParametrosAvisoCobroRequest) ::), 
                            $ConsultarFondosAvisoCobroOutputCollection as element() (:: schema-element(ns2:ConsultarFondosAvisoCobroOutputCollection) ::)) 
                            as element() (:: schema-element(ns1:ConsultarParametrosAvisoCobroResponse) ::) {
    <ns1:ConsultarParametrosAvisoCobroResponse>
        <ns1:Parametros>
            <ges:fechaInicio>{functx:first-day-of-month(fn:data($ConsultarParametrosAvisoCobroRequest/ns1:Fecha))}</ges:fechaInicio>
            <ges:fechaFin>{functx:last-day-of-month(fn:data($ConsultarParametrosAvisoCobroRequest/ns1:Fecha))}</ges:fechaFin>
            <ges:esPublico>{if(fn:lower-case($ConsultarParametrosAvisoCobroRequest/ns1:Sector) = 'privado')then "N" else ("S")}</ges:esPublico>
            <ges:tipoAviso>A</ges:tipoAviso>
            <ges:linea>TD</ges:linea>
            <ges:cliente>TD</ges:cliente>
            <ges:moneda>TD</ges:moneda>
            <ges:pais>TD</ges:pais>
            <ges:sector>{if(fn:lower-case($ConsultarParametrosAvisoCobroRequest/ns1:Sector) = 'privado')then "2" else ("1")}</ges:sector>
            <ges:periodicidad>A</ges:periodicidad>
            <ges:tipoSaldo>A</ges:tipoSaldo>
            <ges:fondos>TD</ges:fondos>
            <ges:operacion></ges:operacion>
            <ges:usuarioCreador>FENIX</ges:usuarioCreador>
        </ns1:Parametros>
        {if(fn:count($ConsultarFondosAvisoCobroOutputCollection/ns2:ConsultarFondosAvisoCobroOutput) > 0)then
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>Consulta Exitosa</res:message>
          </ns2:Resultado>
          else
            <ns2:Resultado>
              <res:result>OK</res:result>
              <res:message>Consulta sin resultados</res:message>
            </ns2:Resultado>
        }    
    </ns1:ConsultarParametrosAvisoCobroResponse>
};

local:func($ConsultarParametrosAvisoCobroRequest, $ConsultarFondosAvisoCobroOutputCollection)
