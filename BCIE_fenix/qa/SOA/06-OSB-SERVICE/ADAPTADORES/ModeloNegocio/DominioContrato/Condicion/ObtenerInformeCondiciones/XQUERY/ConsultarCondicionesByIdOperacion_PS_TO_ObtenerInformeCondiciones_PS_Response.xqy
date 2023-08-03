xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)

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

declare variable $ConsultarCondicionByIdOperacionResponse as element() (:: schema-element(ns1:ConsultarCondicionByIdOperacionResponse) ::) external;
declare variable $CondicionesPeriodicasPorCumplirResponse as element() (:: schema-element(ns1:CondicionesPeriodicasPorCumplirResponse) ::) external;
declare variable $ConsultarValidacionCondicionByRolSJResponse as element() (:: schema-element(ns1:ConsultarValidacionCondicionByRolSJResponse) ::) external;

declare function local:func($ConsultarCondicionByIdOperacionResponse as element() (:: schema-element(ns1:ConsultarCondicionByIdOperacionResponse) ::), 
                            $CondicionesPeriodicasPorCumplirResponse as element() (:: schema-element(ns1:CondicionesPeriodicasPorCumplirResponse) ::), 
                            $ConsultarValidacionCondicionByRolSJResponse as element() (:: schema-element(ns1:ConsultarValidacionCondicionByRolSJResponse) ::)) 
                            as element() (:: schema-element(ns1:ObtenerInformeCondicionesResponse) ::) {
                            
    let $CondicionesGenerales := $ConsultarCondicionByIdOperacionResponse/ns1:ListaCondiciones/con:Condicion[con:controlCondicion/cat:Id = 2]
    let $CondicionesPeriodicas := $CondicionesGenerales[con:idCondicion = $CondicionesPeriodicasPorCumplirResponse/ns1:Condicion/con:idCondicion]
    let $CondicionesGeneralesSinPeriodicas := if(count($CondicionesPeriodicas)>0) then $CondicionesGenerales[con:idCondicion != $CondicionesPeriodicas/con:idCondicion] else $CondicionesGenerales
    let $CondicionesValidacionCondicion := $CondicionesGeneralesSinPeriodicas[con:idCondicion = distinct-values($ConsultarValidacionCondicionByRolSJResponse/ns1:Condicion/ns1:Condicion/con:idCondicion)]
    let $CondicionesGeneralesSinValidadas:= if(count($CondicionesValidacionCondicion)>0 and count($CondicionesPeriodicas)>0)then $CondicionesGenerales[con:idCondicion != $CondicionesPeriodicas/con:idCondicion][con:idCondicion != $CondicionesValidacionCondicion/con:idCondicion]
    else if (count($CondicionesValidacionCondicion)>0 and count($CondicionesPeriodicas)=0) then $CondicionesGenerales[con:idCondicion != $CondicionesValidacionCondicion/con:idCondicion]
    else if (count($CondicionesValidacionCondicion)=0 and count($CondicionesPeriodicas)=0) then $CondicionesGenerales
    else ()
    return
    
    <ns1:ObtenerInformeCondicionesResponse>
        <ns1:InformeEstado>
        {
        for $condPeriodica in $CondicionesPeriodicas
        return
            <con:Condicion>
                <con:idCondicion>{fn:data($condPeriodica/con:idCondicion)}</con:idCondicion>
                <con:operacion></con:operacion>
                <con:nombre>{fn:data($condPeriodica/con:nombre)}</con:nombre>
                <con:descripcion>{fn:data($condPeriodica/con:descripcion)}</con:descripcion>
                <con:tipoCondicion>
                    <con:tipoCondicion>
                        <cat:Descripcion>{fn:data($condPeriodica/con:tipoCondicion/con:tipoCondicion/cat:Descripcion)}</cat:Descripcion>
                    </con:tipoCondicion>
                </con:tipoCondicion>
                {
                for $catPeriodica in $condPeriodica/con:categoriaCondicion
                return
                <con:categoriaCondicion>
                    <con:descripcion>{fn:data($catPeriodica/con:descripcion)}</con:descripcion>
                    <con:validadores>
                        <cat:Descripcion>No</cat:Descripcion>
                    </con:validadores>
                </con:categoriaCondicion>
                }


             <con:estadoTCC>
                    <atr:descripcion>Por Validar</atr:descripcion>
                </con:estadoTCC>
                <con:fechaVigencia>{fn:data($condPeriodica/con:fechaVigencia)}</con:fechaVigencia>
            </con:Condicion>
          }
          
          {
          for $condValidacion in $CondicionesValidacionCondicion
          return
            <con:Condicion>
                <con:idCondicion>{fn:data($condValidacion/con:idCondicion)}</con:idCondicion>
                <con:operacion></con:operacion>
                <con:nombre>{fn:data($condValidacion/con:nombre)}</con:nombre>
                <con:descripcion>{fn:data($condValidacion/con:descripcion)}</con:descripcion>
                <con:tipoCondicion>
                    <con:tipoCondicion>
                        <cat:Descripcion>{fn:data($condValidacion/con:tipoCondicion/con:tipoCondicion/cat:Descripcion)}</cat:Descripcion>
                    </con:tipoCondicion>
                </con:tipoCondicion>
                {
                for $catValidacion in $condValidacion/con:categoriaCondicion
                let $ValidadacionRol:= $ConsultarValidacionCondicionByRolSJResponse/ns1:Condicion[ns1:Condicion/con:idCondicion = $condValidacion/con:idCondicion][ns1:Condicion/con:categoriaCondicion/con:id = $catValidacion/con:id]
                let $validadorRol:= count($ValidadacionRol/ns1:ValidacionCondicion[con:rolBPM/cat:Id = $ValidadacionRol/ns1:Condicion/con:categoriaCondicion/con:validadores/cat:Id][con:estado = true()][con:esValidador = true()])
                return
                <con:categoriaCondicion>
                    <con:descripcion>{fn:data($catValidacion/con:descripcion)}</con:descripcion>
                    <con:validadores>
                    {
                    if ($validadorRol > 0 ) then
                        <cat:Descripcion>Si</cat:Descripcion>
                    else
                        <cat:Descripcion>No</cat:Descripcion>
                    }
                    </con:validadores>
                </con:categoriaCondicion>
                }
               <con:estadoTCC>
                    <atr:descripcion>{fn:data($condValidacion/con:estadoTCC/atr:descripcion)}</atr:descripcion>
                </con:estadoTCC>
                <con:fechaVigencia>{fn:data($condValidacion/con:fechaVigencia)}</con:fechaVigencia>
            </con:Condicion>
          }
		
          {
          for $condOtras in $CondicionesGeneralesSinValidadas
          return
            <con:Condicion>
                <con:idCondicion>{fn:data($condOtras/con:idCondicion)}</con:idCondicion>
                <con:operacion></con:operacion>
                <con:nombre>{fn:data($condOtras/con:nombre)}</con:nombre>
                <con:descripcion>{fn:data($condOtras/con:descripcion)}</con:descripcion>
                <con:tipoCondicion>
                    <con:tipoCondicion>
                        <cat:Descripcion>{fn:data($condOtras/con:tipoCondicion/con:tipoCondicion/cat:Descripcion)}</cat:Descripcion>
                    </con:tipoCondicion>
                </con:tipoCondicion>
                {
                if ( count($condOtras/con:categoriaCondicion)>0)then
                for $catOtras in $condOtras/con:categoriaCondicion
                return
                <con:categoriaCondicion>
                    <con:descripcion>{fn:data($catOtras/con:descripcion)}</con:descripcion>
                    <con:validadores>
                      {
                      if ((string($condOtras/con:fechaInicio)= '' or $condOtras/con:fechaInicio <= fn:current-date())
                        and ((string($condOtras/con:fechaFinal)= '' or $condOtras/con:fechaFinal >= fn:current-date()))
                        and ((string($condOtras/con:fechaVigencia)= '' or $condOtras/con:fechaVigencia < fn:current-date())))
                        then
                          <cat:Descripcion>No</cat:Descripcion>
                        else
                         if ((string($condOtras/con:fechaInicio)= '' or $condOtras/con:fechaInicio <= fn:current-date())
                        and ((string($condOtras/con:fechaFinal)= '' or $condOtras/con:fechaFinal >= fn:current-date()))
                        and ((string($condOtras/con:fechaVigencia)= '' or $condOtras/con:fechaVigencia > fn:current-date())))
                        then
                          <cat:Descripcion>Si</cat:Descripcion>
                        else if (string($condOtras/con:fechaInicio)!= '' and $condOtras/con:fechaInicio > fn:current-date())
                        then
                        <cat:Descripcion>No</cat:Descripcion>
                        else if ((string($condOtras/con:fechaFinal)!= '' and $condOtras/con:fechaFinal < fn:current-date()))
                        then
                            if (string($condOtras/con:fechaVigencia)!= '' and $condOtras/con:fechaVigencia < $condOtras/con:fechaFinal)
                            then
                              <cat:Descripcion>No</cat:Descripcion>
                            else
                              <cat:Descripcion>Si</cat:Descripcion>
                        else()
                       }
                    </con:validadores>
                </con:categoriaCondicion>
                else()
                }
                
                <con:estadoTCC>
                 {
                 
                  if ((string($condOtras/con:fechaInicio)= '' or $condOtras/con:fechaInicio <= fn:current-date())
                    and ((string($condOtras/con:fechaFinal)= '' or $condOtras/con:fechaFinal >= fn:current-date()))
                    and ((string($condOtras/con:fechaVigencia)= '' or $condOtras/con:fechaVigencia < fn:current-date())))
                    then
                     <atr:descripcion>No Validada</atr:descripcion>
                    else
                     if ((string($condOtras/con:fechaInicio)= '' or $condOtras/con:fechaInicio <= fn:current-date())
                    and ((string($condOtras/con:fechaFinal)= '' or $condOtras/con:fechaFinal >= fn:current-date()))
                    and ((string($condOtras/con:fechaVigencia)= '' or $condOtras/con:fechaVigencia > fn:current-date())))
                    then
                      <atr:descripcion>Validada</atr:descripcion>
                    else if (string($condOtras/con:fechaInicio)!= '' and $condOtras/con:fechaInicio > fn:current-date())
                    then
                    <atr:descripcion>No Validada</atr:descripcion>
                    else if ((string($condOtras/con:fechaFinal)!= '' and $condOtras/con:fechaFinal < fn:current-date()))
                    then
                        if (string($condOtras/con:fechaVigencia)!= '' and $condOtras/con:fechaVigencia < $condOtras/con:fechaFinal)
                        then
                          <atr:descripcion>No Validada</atr:descripcion>
                        else
                          <atr:descripcion>Validada</atr:descripcion>
                    else()
                   }
                </con:estadoTCC>
                <con:fechaVigencia>{fn:data($condOtras/con:fechaVigencia)}</con:fechaVigencia>
            </con:Condicion>
          }
       
        </ns1:InformeEstado>
        <ns1:Resultado>
            <res:result>OK</res:result>
            <res:message>Operación Exitosa</res:message>
        </ns1:Resultado>
    </ns1:ObtenerInformeCondicionesResponse>
};

local:func($ConsultarCondicionByIdOperacionResponse, $CondicionesPeriodicasPorCumplirResponse, $ConsultarValidacionCondicionByRolSJResponse)