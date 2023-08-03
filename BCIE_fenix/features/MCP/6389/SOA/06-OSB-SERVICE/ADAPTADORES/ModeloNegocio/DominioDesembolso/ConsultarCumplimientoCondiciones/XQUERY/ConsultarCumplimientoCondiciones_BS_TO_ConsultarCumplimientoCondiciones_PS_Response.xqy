xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ConsultarCumplimientoCondiciones";
(:: import schema at "../XSD/ConsultarCumplimientoCondiciones_sp.xsd" ::)

declare namespace con = "http://www.bcie.org/CondicionBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace reg = "http://www.bcie.org/ReglaBO";

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ConsultarCumplimientoCondicionesRequest as element() (:: schema-element(ns1:ConsultarCumplimientoCondicionesRequest) ::) external;
declare variable $OutputParameters as element() (:: schema-element(ns2:OutputParameters) ::) external;

declare function local:func($ConsultarCumplimientoCondicionesRequest as element() (:: schema-element(ns1:ConsultarCumplimientoCondicionesRequest) ::), 
                            $OutputParameters as element() (:: schema-element(ns2:OutputParameters) ::)) 
                            as element() (:: schema-element(ns1:ConsultarCumplimientoCondicionesResponse) ::) {
    <ns1:ConsultarCumplimientoCondicionesResponse>
      {for $condicion in $ConsultarCumplimientoCondicionesRequest/ns1:Condicion
       (:where $OutputParameters/ns2:P_CODIGO_RES = 0 and fn:count($OutputParameters/ns2:RECORDSET/ns2:Row) > 0:)
       return
        <ns1:Condicion>
            <con:idCondicion>{fn:data($condicion/con:idCondicion)}</con:idCondicion>
            <con:operacion>{fn:data($condicion/con:operacion)}</con:operacion>
            {
                if ($condicion/con:nombre)
                then <con:nombre>{fn:data($condicion/con:nombre)}</con:nombre>
                else ()
            }
            {
                if ($condicion/con:descripcion)
                then <con:descripcion>{fn:data($condicion/con:descripcion)}</con:descripcion>
                else ()
            }
            <con:tipoCondicion>
                {
                    if ($condicion/con:tipoCondicion/con:idCatCondicion)
                    then <con:idCatCondicion>{fn:data($condicion/con:tipoCondicion/con:idCatCondicion)}</con:idCatCondicion>
                    else ()
                }
            </con:tipoCondicion>
            <con:controlCondicion>
                {
                    if ($condicion/con:controlCondicion/cat:Id)
                    then <cat:Id>{fn:data($condicion/con:controlCondicion/cat:Id)}</cat:Id>
                    else ()
                }
            </con:controlCondicion>
            <con:eventoCondicion>
                {
                    if ($condicion/con:eventoCondicion/cat:Id)
                    then <cat:Id>{fn:data($condicion/con:eventoCondicion/cat:Id)}</cat:Id>
                    else ()
                }
            </con:eventoCondicion>
            <con:tipoFechaInicio>
                {
                    if ($condicion/con:tipoFechaInicio/cat:Id)
                    then <cat:Id>{fn:data($condicion/con:tipoFechaInicio/cat:Id)}</cat:Id>
                    else ()
                }
            </con:tipoFechaInicio>
            {
                if ($condicion/con:fechaInicio)
                then <con:fechaInicio>{fn:data($condicion/con:fechaInicio)}</con:fechaInicio>
                else ()
            }
            {
                if ($condicion/con:plazo)
                then <con:plazo>{fn:data($condicion/con:plazo)}</con:plazo>
                else ()
            }
            <con:frecuenciaPlazo>
                {
                    if ($condicion/con:frecuenciaPlazo/cat:Id)
                    then <cat:Id>{fn:data($condicion/con:frecuenciaPlazo/cat:Id)}</cat:Id>
                    else ()
                }
            </con:frecuenciaPlazo>
            {
                if ($condicion/con:fechaFinal)
                then <con:fechaFinal>{fn:data($condicion/con:fechaFinal)}</con:fechaFinal>
                else ()
            }
            <con:estadoTCC>
                {
                    if ($condicion/con:estadoTCC/atr:id)
                    then <atr:id>{fn:data($condicion/con:estadoTCC/atr:id)}</atr:id>
                    else ()
                }
            </con:estadoTCC>
            <con:subEstadoTCC>
                <atr:id></atr:id>
            </con:subEstadoTCC>
            {
                if ($condicion/con:fechaRegistro)
                then <con:fechaRegistro>{fn:data($condicion/con:fechaRegistro)}</con:fechaRegistro>
                else ()
            }
            {
                if ($condicion/con:estado)
                then <con:estado>{fn:data($condicion/con:estado)}</con:estado>
                else ()
            }
            {
                if ($condicion/con:condicionEnmendada)
                then <con:condicionEnmendada>{fn:data($condicion/con:condicionEnmendada)}</con:condicionEnmendada>
                else ()
            }
            {
                if ($condicion/con:fechaEnmienda)
                then <con:fechaEnmienda>{fn:data($condicion/con:fechaEnmienda)}</con:fechaEnmienda>
                else ()
            }
            {
                if ($condicion/con:fechaVigencia)
                then <con:fechaVigencia>{fn:data($condicion/con:fechaVigencia)}</con:fechaVigencia>
                else ()
            }
            {for $cumplimiento in $OutputParameters/ns2:RECORDSET/ns2:Row
            where $condicion/con:idCondicion = fn:number($cumplimiento/ns2:Column[@name='ID_CONDICION'])
            return
              <con:Cumplimientos>
                  <con:Id>{fn:data($cumplimiento/ns2:Column[@name='ID'])}</con:Id>
                  <con:Operacion>
                      <ope:idOperacion>{fn:data($cumplimiento/ns2:Column[@name='ID_OPERACION'])}</ope:idOperacion>
                  </con:Operacion>
                  <con:Solicitud>
                      <des:idDesembolso>{fn:data($cumplimiento/ns2:Column[@name='ID_SOLICITUD'])}</des:idDesembolso>
                  </con:Solicitud>
                  <con:ContratoDesembolso>
                      <des:idDesembolso>{fn:data($cumplimiento/ns2:Column[@name='ID_CONTRATO_DESEMBOLSO'])}</des:idDesembolso>
                  </con:ContratoDesembolso>
                  <con:EstadoTCC>
                      <cat:Id>{fn:data($cumplimiento/ns2:Column[@name='ID_TCA_ESTADO_TCC'])}</cat:Id>
                  </con:EstadoTCC>
                  <con:EventoCondicion>
                      <cat:Id>{fn:data($cumplimiento/ns2:Column[@name='ID_TCA_EVENTO_CONDICION'])}</cat:Id>
                  </con:EventoCondicion>
                  <con:agrupador>{fn:data($cumplimiento/ns2:Column[@name='AGRUPADOR'])}</con:agrupador>
                  <con:estatus>{fn:data($cumplimiento/ns2:Column[@name='BAN_ESTATUS'])}</con:estatus>
                  <con:subEstadoTCC>
                      <cat:Id>{fn:data($cumplimiento/ns2:Column[@name='ID_TCA_SUB_ESTADO_TCC'])}</cat:Id>
                  </con:subEstadoTCC>
                  <con:enProceso>{
                  if($cumplimiento/ns2:Column[@name='EN_PROCESO'] = '1')then fn:true() else fn:false()
                  }</con:enProceso>
              </con:Cumplimientos>
            }  
        </ns1:Condicion>
      }
      <ns1:Resultado>
        <res:result>OK</res:result>
        {if(fn:count($OutputParameters/ns2:RECORDSET/ns2:Row) > 0)then
          <res:message>Consulta exitosa</res:message>
        else  
          <res:message>Consulta sin resultados</res:message>
        }  
      </ns1:Resultado>  
    </ns1:ConsultarCumplimientoCondicionesResponse>
};

local:func($ConsultarCumplimientoCondicionesRequest, $OutputParameters)
