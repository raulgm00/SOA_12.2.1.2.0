xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarCondicionByIdOperacion";
(:: import schema at "../XSD/ConsultarCondicionByIdOperacion.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare namespace con = "http://www.bcie.org/CondicionBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare variable $ConsultarCondicionByIdOperacionOutputCollection as element() (:: schema-element(ns1:ConsultarCondicionByIdOperacionOutputCollection) ::) external;

declare function local:func($ConsultarCondicionByIdOperacionOutputCollection as element() (:: schema-element(ns1:ConsultarCondicionByIdOperacionOutputCollection) ::)) as element() (:: schema-element(ns2:ConsultarCondicionByIdOperacionResponse) ::) {
    <ns2:ConsultarCondicionByIdOperacionResponse>
    
        <ns2:ListaCondiciones>
         { for $condicionN in (1 to count( $ConsultarCondicionByIdOperacionOutputCollection/ns1:ConsultarCondicionByIdOperacionOutput))
         return
           if (($ConsultarCondicionByIdOperacionOutputCollection/ns1:ConsultarCondicionByIdOperacionOutput[$condicionN]/ns1:ID != $ConsultarCondicionByIdOperacionOutputCollection/ns1:ConsultarCondicionByIdOperacionOutput[$condicionN - 1]/ns1:ID) or $condicionN=1   )
                    then
            <con:Condicion>
                <con:idCondicion>{fn:data($ConsultarCondicionByIdOperacionOutputCollection/ns1:ConsultarCondicionByIdOperacionOutput[$condicionN]/ns1:ID)}</con:idCondicion>
                <con:operacion>{fn:data($ConsultarCondicionByIdOperacionOutputCollection/ns1:ConsultarCondicionByIdOperacionOutput[$condicionN]/ns1:ID_OPERACION)}</con:operacion>
                <con:nombre>{fn:data($ConsultarCondicionByIdOperacionOutputCollection/ns1:ConsultarCondicionByIdOperacionOutput[$condicionN]/ns1:NOMBRE)}</con:nombre>
                <con:descripcion>{fn:data($ConsultarCondicionByIdOperacionOutputCollection/ns1:ConsultarCondicionByIdOperacionOutput[$condicionN]/ns1:DESCRIPCION)}</con:descripcion>
                <con:tipoCondicion>
                     <con:idCatCondicion>{fn:data($ConsultarCondicionByIdOperacionOutputCollection/ns1:ConsultarCondicionByIdOperacionOutput[$condicionN]/ns1:ID_TCA_CONDICION)}</con:idCatCondicion>
                     <con:requiereValidarCOFI>{    if  (string($ConsultarCondicionByIdOperacionOutputCollection/ns1:ConsultarCondicionByIdOperacionOutput[$condicionN]/ns1:REQUIERE_VALIDAR_COFI)='')then false () 
                    else if(
                     fn:data($ConsultarCondicionByIdOperacionOutputCollection/ns1:ConsultarCondicionByIdOperacionOutput[$condicionN]/ns1:REQUIERE_VALIDAR_COFI)=1) then true() else
                     false()
                     }</con:requiereValidarCOFI>
                     <con:tipoCondicion>
                         <cat:Descripcion>{fn:data($ConsultarCondicionByIdOperacionOutputCollection/ns1:ConsultarCondicionByIdOperacionOutput[$condicionN]/ns1:TIPO_CONDICION)}</cat:Descripcion>
                     </con:tipoCondicion>
    
                </con:tipoCondicion>

                <con:controlCondicion>
                    <cat:Id>{fn:data($ConsultarCondicionByIdOperacionOutputCollection/ns1:ConsultarCondicionByIdOperacionOutput[$condicionN]/ns1:ID_TCA_CONTROL_CONDICION)}</cat:Id>                 
                </con:controlCondicion>
                 { for $condicionEvento in $ConsultarCondicionByIdOperacionOutputCollection/ns1:ConsultarCondicionByIdOperacionOutput
         return
         
                    if (($condicionEvento/ns1:ID = $ConsultarCondicionByIdOperacionOutputCollection/ns1:ConsultarCondicionByIdOperacionOutput[$condicionN]/ns1:ID) and fn:string-length($condicionEvento/ns1:ID_EVENTO/text()) > 0 )
                    then  
                    <con:eventoCondicion>
                      <cat:Id>{fn:data($condicionEvento/ns1:ID_EVENTO)}</cat:Id>
                       <cat:Descripcion>{fn:data($condicionEvento/ns1:DESCRIPCION_EVENTO)}</cat:Descripcion>
                    </con:eventoCondicion>
                          
                    else ()
                }
               
                
                <con:tipoFechaInicio>
                    <cat:Id>{fn:data($ConsultarCondicionByIdOperacionOutputCollection/ns1:ConsultarCondicionByIdOperacionOutput[$condicionN]/ns1:ID_TCA_TIPO_FECHA_INICIO)}</cat:Id>
                </con:tipoFechaInicio>
                <con:fechaInicio>{fn:substring-before($ConsultarCondicionByIdOperacionOutputCollection/ns1:ConsultarCondicionByIdOperacionOutput[$condicionN]/ns1:FECHA_INICIO/text(),'T')}</con:fechaInicio>
                <con:plazo>{fn:data($ConsultarCondicionByIdOperacionOutputCollection/ns1:ConsultarCondicionByIdOperacionOutput[$condicionN]/ns1:PLAZO)}</con:plazo>
                <con:frecuenciaPlazo>
                    <cat:Id>{fn:data($ConsultarCondicionByIdOperacionOutputCollection/ns1:ConsultarCondicionByIdOperacionOutput[$condicionN]/ns1:ID_TCA_FRECUENCIA_PLAZO)}</cat:Id>
                </con:frecuenciaPlazo>
                <con:fechaFinal>{fn:substring-before($ConsultarCondicionByIdOperacionOutputCollection/ns1:ConsultarCondicionByIdOperacionOutput[$condicionN]/ns1:FECHA_FINAL/text(),'T')}</con:fechaFinal>
                <con:estadoTCC>
                    <atr:id>{fn:data($ConsultarCondicionByIdOperacionOutputCollection/ns1:ConsultarCondicionByIdOperacionOutput[$condicionN]/ns1:ID_TCA_ESTADO_TCC)}</atr:id>
                    <atr:descripcion>{fn:data($ConsultarCondicionByIdOperacionOutputCollection/ns1:ConsultarCondicionByIdOperacionOutput[$condicionN]/ns1:DESCRIPCION_ESTADO)}</atr:descripcion>
                </con:estadoTCC>
                <con:subEstadoTCC>
                    <atr:id>{fn:data($ConsultarCondicionByIdOperacionOutputCollection/ns1:ConsultarCondicionByIdOperacionOutput[$condicionN]/ns1:ID_TCA_SUB_ESTADO_TCC)}</atr:id>
                    <atr:descripcion>{fn:data($ConsultarCondicionByIdOperacionOutputCollection/ns1:ConsultarCondicionByIdOperacionOutput[$condicionN]/ns1:DESCRIPCION_SUBESTADO)}</atr:descripcion>
                </con:subEstadoTCC>
                <con:fechaRegistro>{fn:substring-before($ConsultarCondicionByIdOperacionOutputCollection/ns1:ConsultarCondicionByIdOperacionOutput[$condicionN]/ns1:FECHA_REGISTRO/text(),'T')}</con:fechaRegistro>
                <con:estado>true</con:estado>
                <con:condicionEnmendada>{fn:data($ConsultarCondicionByIdOperacionOutputCollection/ns1:ConsultarCondicionByIdOperacionOutput[$condicionN]/ns1:ID_CONDICION_ENMENDADA)}</con:condicionEnmendada>
                <con:fechaEnmienda>{fn:substring-before($ConsultarCondicionByIdOperacionOutputCollection/ns1:ConsultarCondicionByIdOperacionOutput[$condicionN]/ns1:FECHA_ENMIENDA/text(),'T')}</con:fechaEnmienda>
                <con:fechaVigencia>{fn:substring-before($ConsultarCondicionByIdOperacionOutputCollection/ns1:ConsultarCondicionByIdOperacionOutput[$condicionN]/ns1:FECHA_VIGENCIA/text(),'T')}</con:fechaVigencia>
                {
                if($ConsultarCondicionByIdOperacionOutputCollection/ns1:ConsultarCondicionByIdOperacionOutput[$condicionN]/ns1:ID_TIPO_DESEMBOLSO/text() != '' )then 
                  <con:tipoDesembolso>
                    <cat:Id>{data($ConsultarCondicionByIdOperacionOutputCollection/ns1:ConsultarCondicionByIdOperacionOutput[$condicionN]/ns1:ID_TIPO_DESEMBOLSO)}</cat:Id>
                    <cat:Descripcion>{data($ConsultarCondicionByIdOperacionOutputCollection/ns1:ConsultarCondicionByIdOperacionOutput[$condicionN]/ns1:DESCRIPCION_TIPO_DESEMBOLSO)}</cat:Descripcion>
                  </con:tipoDesembolso>
                else()
                }
                <con:banEstatus>{fn:data($ConsultarCondicionByIdOperacionOutputCollection/ns1:ConsultarCondicionByIdOperacionOutput[$condicionN]/ns1:BAN_ESTATUS)}</con:banEstatus>
               </con:Condicion>
                 else ()
            }
        </ns2:ListaCondiciones>
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>Consulta Exitosa</res:message>
        </ns2:Resultado>
    </ns2:ConsultarCondicionByIdOperacionResponse>
};

local:func($ConsultarCondicionByIdOperacionOutputCollection)
