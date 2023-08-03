xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/top/ActualizarCondicion_BS";
(:: import schema at "../XSD/ActualizarCondicion_BS_table.xsd" ::)

declare namespace con = "http://www.bcie.org/CondicionBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace xsi="http://www.w3.org/2001/XMLSchema-instance";
declare namespace functx = "http://www.functx.com";

declare variable $inActualizarCondicion as element() (:: schema-element(ns1:ActualizarCondicionRequest) ::) external;

declare function local:func($inActualizarCondicion as element() (:: schema-element(ns1:ActualizarCondicionRequest) ::)) as element() (:: schema-element(ns2:CondicionCollection) ::) {
    <ns2:CondicionCollection>
        <ns2:Condicion>
            <ns2:id>{fn:data($inActualizarCondicion/ns1:Condicion/con:idCondicion)}</ns2:id>
            <ns2:idOperacion>{fn:data($inActualizarCondicion/ns1:Condicion/con:operacion)}</ns2:idOperacion>
            <ns2:nombre>{fn:data($inActualizarCondicion/ns1:Condicion/con:nombre)}</ns2:nombre>
            <ns2:descripcion>{fn:data($inActualizarCondicion/ns1:Condicion/con:descripcion)}</ns2:descripcion>
            {
                if ($inActualizarCondicion/ns1:Condicion/con:tipoCondicion/con:idCatCondicion)
                then <ns2:idTcaCondicion>{fn:data($inActualizarCondicion/ns1:Condicion/con:tipoCondicion/con:idCatCondicion)}</ns2:idTcaCondicion>
                else ()
            }
            {
                if ($inActualizarCondicion/ns1:Condicion/con:controlCondicion/cat:Id)
                then <ns2:idTcaControlCondicion>{fn:data($inActualizarCondicion/ns1:Condicion/con:controlCondicion/cat:Id)}</ns2:idTcaControlCondicion>
                else ()
            }
            {
                if ($inActualizarCondicion/ns1:Condicion/con:tipoFechaInicio/cat:Id)
                then <ns2:idTcaTipoFechaInicio>{fn:data($inActualizarCondicion/ns1:Condicion/con:tipoFechaInicio/cat:Id)}</ns2:idTcaTipoFechaInicio>
                else ()
            }
            <ns2:fechaInicio>{fn:data($inActualizarCondicion/ns1:Condicion/con:fechaInicio)}</ns2:fechaInicio>
            <ns2:plazo>{fn:data($inActualizarCondicion/ns1:Condicion/con:plazo)}</ns2:plazo>
            {
                if ($inActualizarCondicion/ns1:Condicion/con:frecuenciaPlazo/cat:Id)
                then <ns2:idTcaFrecuenciaPlazo>{fn:data($inActualizarCondicion/ns1:Condicion/con:frecuenciaPlazo/cat:Id)}</ns2:idTcaFrecuenciaPlazo>
                else ()
            }
            <ns2:fechaFinal>{fn:data($inActualizarCondicion/ns1:Condicion/con:fechaFinal)}</ns2:fechaFinal>
            {
                if ($inActualizarCondicion/ns1:Condicion/con:estadoTCC/atr:id)
                then <ns2:idTcaEstadoTcc>{fn:data($inActualizarCondicion/ns1:Condicion/con:estadoTCC/atr:id)}</ns2:idTcaEstadoTcc>
                else ()
            }
            {
                let $subestado := if ($inActualizarCondicion/ns1:Condicion/con:subEstadoTCC/atr:id) then 
                   <ns2:idTcaSubEstadoTcc> {fn:data($inActualizarCondicion/ns1:Condicion/con:subEstadoTCC/atr:id)}</ns2:idTcaSubEstadoTcc>
                else ()
                let $temp :=  <ns2:idTcaSubEstadoTcc xsi:nil="true" />
                return 
                  if($inActualizarCondicion/ns1:Condicion/con:subEstadoTCC/atr:id/@xsi:nil = 'true') then
                    $temp
                  else 
                    $subestado
            }
            <ns2:fechaRegistro>{fn:current-dateTime()}</ns2:fechaRegistro>
          
            {
            if ($inActualizarCondicion/ns1:Condicion/con:estado)
            then(
             <ns2:banEstatus>{
              if (xs:string($inActualizarCondicion/ns1:Condicion/con:estado) = '')
                then(1)
                  else(
                    if (fn:data($inActualizarCondicion/ns1:Condicion/con:estado) = true())
                      then(1)
                        else(0))
            }
            </ns2:banEstatus>)
            else()
            }
            <ns2:fechaEnmienda>{fn:data($inActualizarCondicion/ns1:Condicion/con:fechaEnmienda)}</ns2:fechaEnmienda>
            {
                if ($inActualizarCondicion/ns1:Condicion/con:fechaVigencia)
                then <ns2:fechaVigencia>{fn:data($inActualizarCondicion/ns1:Condicion/con:fechaVigencia)}</ns2:fechaVigencia>
                else ()
            }</ns2:Condicion>
    </ns2:CondicionCollection>
};
 
local:func($inActualizarCondicion)

