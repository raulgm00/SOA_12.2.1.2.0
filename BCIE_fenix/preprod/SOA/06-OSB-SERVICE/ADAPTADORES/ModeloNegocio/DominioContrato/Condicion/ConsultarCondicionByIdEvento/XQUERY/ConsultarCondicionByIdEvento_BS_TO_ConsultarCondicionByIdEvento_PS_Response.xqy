xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns3="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarEventoCondicion";
(:: import schema at "../../ConsultarEventoByCondicion/XSD/ConsultarEventoCondicion.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/consultarCondicionByRol";
(:: import schema at "../XSD/ConsultarCondicionByIdEvento.xsd" ::)

declare namespace con = "http://www.bcie.org/CondicionBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $consultarCondicionByRolOutputCollection as element() (:: schema-element(ns2:consultarCondicionByRolOutputCollection) ::) external;
declare variable $ConsultarEventoCondicionOutputCollection as element() (:: schema-element(ns1:ConsultarEventoCondicionOutputCollection) ::) external;

declare function local:func($consultarCondicionByRolOutputCollection as element() (:: schema-element(ns2:consultarCondicionByRolOutputCollection) ::), 
                            $ConsultarEventoCondicionOutputCollection as element() (:: schema-element(ns1:ConsultarEventoCondicionOutputCollection) ::)) 
                            as element() (:: schema-element(ns3:ConsultarCondicionByIdEventoResponse) ::) {
    <ns3:ConsultarCondicionByIdEventoResponse>
        {for $condicion in $consultarCondicionByRolOutputCollection/ns2:consultarCondicionByRolOutput
        return
        <ns3:Condicion>
            <con:idCondicion>{fn:data($condicion/ns2:ID)}</con:idCondicion>
            <con:operacion>{fn:data($condicion/ns2:ID_OPERACION)}</con:operacion>
            <con:nombre>{fn:data($condicion/ns2:NOMBRE)}</con:nombre>
            <con:descripcion>{fn:data($condicion/ns2:DESCRIPCION)}</con:descripcion>
            <con:tipoCondicion>
                <con:idCatCondicion>{fn:data($condicion/ns2:ID_TCA_CONDICION)}</con:idCatCondicion>
                <con:descripcion>{fn:data($condicion/ns2:DESCRIPCION_TCA)}</con:descripcion>
                <con:descripcionCorta>{fn:data($condicion/ns2:DESCRIPCION_CORTA)}</con:descripcionCorta>
                <con:idTipoCondicion>{fn:data($condicion/ns2:ID_TCA_TIPO_CONDICION)}</con:idTipoCondicion>
                <con:esEditableEnFormalizacion>{if(fn:string($condicion/ns2:ES_EDITABLE_EN_FORMALIZACION) != '')then 
                  if($condicion/ns2:ES_EDITABLE_EN_FORMALIZACION = 1)then fn:true() else fn:false()
                else fn:false()  }</con:esEditableEnFormalizacion>
                <con:requiereValidarCOFI>{if(fn:string($condicion/ns2:REQUIERE_VALIDAR_COFI) != '')then
                  if($condicion/ns2:REQUIERE_VALIDAR_COFI = 1)then fn:true() else fn:false()
                else fn:false()  
                }</con:requiereValidarCOFI>
                <con:sePuedeDispensar>{if(fn:string($condicion/ns2:SE_PUEDE_DISPENSAR) != '')then
                  if($condicion/ns2:SE_PUEDE_DISPENSAR = 1)then fn:true() else fn:false()
                else fn:false()  }</con:sePuedeDispensar>
                <con:esPlantilla>{if(fn:string($condicion/ns2:ES_PLANTILLA) != '')then
                  if($condicion/ns2:ES_PLANTILLA = 1)then fn:true() else fn:false()
                else fn:false()}</con:esPlantilla>
                <con:idCondicionPrecarga>{fn:data($condicion/ns2:ID_CONDICION_PRECARGA)}</con:idCondicionPrecarga>
                <con:fechaRegistro>{fn:data($condicion/ns2:FECHA_REGISTRO)}</con:fechaRegistro>
                <con:estado>{if(fn:string($condicion/ns2:BAN_ESTATUS) != '')then
                  if($condicion/ns2:BAN_ESTATUS = 1)then fn:true() else fn:false()
                else fn:false()}</con:estado>
                <con:codigoExterno>{fn:data($condicion/ns2:COD_EXTERNO)}</con:codigoExterno>
            </con:tipoCondicion>
            <con:controlCondicion>
                <cat:Id>{fn:data($condicion/ns2:ID_TCA_CONTROL_CONDICION)}</cat:Id>
                <cat:Descripcion>{fn:data($condicion/ns2:DESCRIPCION_T)}</cat:Descripcion>
                <cat:DescripcionCorta>{fn:data($condicion/ns2:DESCRIPCION_CORTA_TC)}</cat:DescripcionCorta>
                <cat:estatus>{if(fn:string($condicion/ns2:BAN_ESTATUS_TC) != '')then
                  if($condicion/ns2:BAN_ESTATUS_TC = 1)then fn:true() else fn:false()
                else fn:false()  }</cat:estatus>
                <cat:codigoExterno>{fn:data($condicion/ns2:COD_EXTERNO_TC)}</cat:codigoExterno>
            </con:controlCondicion>
            {for $evento in $ConsultarEventoCondicionOutputCollection/ns1:ConsultarEventoCondicionOutput
            where fn:data($condicion/ns2:ID)=fn:data($evento/ns1:ID_CONDICION)
            return
            <con:eventoCondicion>
                <cat:Id>{fn:data($evento/ns1:ID)}</cat:Id>
                <cat:Descripcion>{fn:data($evento/ns1:DESCRIPCION)}</cat:Descripcion>
                <cat:DescripcionCorta>{fn:data($evento/ns1:DESCRIPCION_CORTA)}</cat:DescripcionCorta>
                <cat:estatus>{fn:data($evento/ns1:BAN_ESTATUS)}</cat:estatus>
                <cat:codigoExterno>{fn:data($evento/ns1:COD_EXTERNO)}</cat:codigoExterno>
            </con:eventoCondicion>
            }
            <con:tipoFechaInicio>
                <cat:Id>{fn:data($condicion/ns2:ID_TCA_TIPO_FECHA_INICIO)}</cat:Id>
                <cat:Descripcion>{fn:data($condicion/ns2:DESCRIPCION_FI)}</cat:Descripcion>
                <cat:DescripcionCorta>{fn:data($condicion/ns2:DESCRIPCION_CORTA_FI)}</cat:DescripcionCorta>
                <cat:estatus>{if(fn:string($condicion/ns2:BAN_ESTATUS_FI) != '')then
                  if($condicion/ns2:BAN_ESTATUS_FI = 1)then fn:true() else fn:false()
                else fn:false() }</cat:estatus>
                <cat:codigoExterno>{fn:data($condicion/ns2:COD_EXTERNO_FI)}</cat:codigoExterno>
            </con:tipoFechaInicio>
            <con:fechaInicio>{fn:data($condicion/ns2:FECHA_INICIO)}</con:fechaInicio>
            <con:plazo>{fn:data($condicion/ns2:PLAZO)}</con:plazo>
            <con:frecuenciaPlazo>
                <cat:Id>{fn:data($condicion/ns2:ID_TCA_FRECUENCIA_PLAZO)}</cat:Id>
                <cat:Descripcion>{fn:data($condicion/ns2:DESCRIPCION_TF)}</cat:Descripcion>
                <cat:DescripcionCorta>{fn:data($condicion/ns2:DESCRIPCION_CORTA_TF)}</cat:DescripcionCorta>
                <cat:estatus>{if(fn:string($condicion/ns2:BAN_ESTATUS_TF) != '')then
                  if($condicion/ns2:BAN_ESTATUS_TF = 1)then fn:true() else fn:false()
                else fn:false()  }</cat:estatus>
                <cat:codigoExterno>{fn:data($condicion/ns2:COD_EXTERNO_TF)}</cat:codigoExterno>
            </con:frecuenciaPlazo>
            <con:fechaFinal>{fn:data($condicion/ns2:FECHA_FINAL)}</con:fechaFinal>
            <con:estadoTCC>
                <atr:id>{fn:data($condicion/ns2:ID_TCA_ESTADO_TCC)}</atr:id>
                <atr:descripcion>{fn:data($condicion/ns2:DESCRIPCION_EST)}</atr:descripcion>
                <atr:descripcionCorta>{fn:data($condicion/ns2:DESCRIPCION_CORTA_EST)}</atr:descripcionCorta>
                <atr:estatus>{if(fn:string($condicion/ns2:BAN_ESTATUS_EST) != '')then 
                  if($condicion/ns2:BAN_ESTATUS_EST = 1)then fn:true() else fn:false()
                else fn:false()}</atr:estatus>
                <atr:codigoExterno>{fn:data($condicion/ns2:COD_EXTERNO_EST)}</atr:codigoExterno>
                <atr:esSubEstado>{fn:data($condicion/ns2:ES_SUBESTADO_EST)}</atr:esSubEstado>
            </con:estadoTCC>
            <con:subEstadoTCC>
                <atr:id>{fn:data($condicion/ns2:ID_TCA_SUB_ESTADO_TCC)}</atr:id>
                <atr:descripcion>{fn:data($condicion/ns2:DESCRIPCION_SUB)}</atr:descripcion>
            </con:subEstadoTCC>
            <con:fechaRegistro>{fn:data($condicion/ns2:CD_FECHA_REGISTRO)}</con:fechaRegistro>
            <con:estado>{if(fn:string($condicion/ns2:CD_BAN_ESTATUS = 1) != '')then 
              if($condicion/ns2:CD_BAN_ESTATUS = 1)then fn:true() else fn:false()
            else fn:false()
              }</con:estado>
            <con:condicionEnmendada>{fn:data($condicion/ns2:ID_CONDICION_ENMENDADA)}</con:condicionEnmendada>
            <con:fechaEnmienda>{fn:data($condicion/ns2:FECHA_ENMIENDA)}</con:fechaEnmienda>
            <con:tipoDesembolso>
                <cat:Id>{fn:data($condicion/ns2:ID_TCA_TIPO_DESEMBOLSO)}</cat:Id>
            </con:tipoDesembolso>
        </ns3:Condicion>
       }
      <ns3:Resultado>
          <res:result>OK</res:result>
          {if(fn:count($consultarCondicionByRolOutputCollection/ns2:consultarCondicionByRolOutput) > 0)then 
            <res:message>Consulta exitosa</res:message>
          else
            <res:message>Consulta sin resultados</res:message>
          }  
      </ns3:Resultado> 
    </ns3:ConsultarCondicionByIdEventoResponse>
};

local:func($consultarCondicionByRolOutputCollection, $ConsultarEventoCondicionOutputCollection)
