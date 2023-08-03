xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarCondicionByIdLineaCredito";
(:: import schema at "../XSD/ConsultarCondicionByIdLineaCredito.xsd" ::)

declare namespace con = "http://www.bcie.org/CondicionBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ConsultarCondicionByIdLineaCreditoOutputCollection as element() (:: schema-element(ns1:ConsultarCondicionByIdLineaCreditoOutputCollection) ::) external;

declare function local:func($ConsultarCondicionByIdLineaCreditoOutputCollection as element() (:: schema-element(ns1:ConsultarCondicionByIdLineaCreditoOutputCollection) ::)) as element() (:: schema-element(ns2:ConsultarCondicionByIdLineaCreditoResponse) ::) {
    <ns2:ConsultarCondicionByIdLineaCreditoResponse>
      {
      for $condicion in $ConsultarCondicionByIdLineaCreditoOutputCollection/ns1:ConsultarCondicionByIdLineaCreditoOutput
      return
        <ns2:Condicion>
            <con:idCondicion>{fn:data($condicion/ns1:ID)}</con:idCondicion>
            <con:operacion>{fn:data($condicion/ns1:ID_OPERACION)}</con:operacion>
            <con:nombre>{fn:data($condicion/ns1:NOMBRE)}</con:nombre>
            <con:descripcion>{fn:data($condicion/ns1:DESCRIPCION)}</con:descripcion>
            <con:tipoCondicion>
                <con:idCatCondicion>{fn:data($condicion/ns1:ID_TCA_CONDICION)}</con:idCatCondicion>
            </con:tipoCondicion>
            <con:controlCondicion>
                <cat:Id>{fn:data($condicion/ns1:ID_TCA_CONTROL_CONDICION)}</cat:Id>
            </con:controlCondicion>
            <con:tipoFechaInicio>
                <cat:Id>{fn:data($condicion/ns1:ID_TCA_TIPO_FECHA_INICIO)}</cat:Id>
            </con:tipoFechaInicio>
            <con:fechaInicio>{fn:data($condicion/ns1:FECHA_INICIO)}</con:fechaInicio>
            <con:plazo>{fn:data($condicion/ns1:PLAZO)}</con:plazo>
            <con:frecuenciaPlazo>
                <cat:Id>{fn:data($condicion/ns1:ID_TCA_FRECUENCIA_PLAZO)}</cat:Id>
            </con:frecuenciaPlazo>
            <con:fechaFinal>{fn:data($condicion/ns1:FECHA_FINAL)}</con:fechaFinal>
            <con:estadoTCC>
                <atr:id>{fn:data($condicion/ns1:ID_TCA_ESTADO_TCC)}</atr:id>
            </con:estadoTCC>
            <con:subEstadoTCC>
                <atr:id>{fn:data($condicion/ns1:ID_TCA_SUB_ESTADO_TCC)}</atr:id>
            </con:subEstadoTCC>
            <con:fechaRegistro>{fn:data($condicion/ns1:FECHA_REGISTRO)}</con:fechaRegistro>
            <con:estado>
            {
            if (string($condicion/ns1:BAN_ESTATUS)='') then 
                false()   
            else                 
                  if(fn:data($condicion/ns1:BAN_ESTATUS)=1) then 
                    true() 
                  else 
                    false ()
            }
            </con:estado>
            <con:condicionEnmendada>{fn:data($condicion/ns1:ID_CONDICION_ENMENDADA)}</con:condicionEnmendada>
            <con:fechaEnmienda>{fn:data($condicion/ns1:FECHA_ENMIENDA)}</con:fechaEnmienda>
        </ns2:Condicion>
        }
      <ns2:Resultado>
          <res:result>OK</res:result>
          <res:message>Consulta Exitosa</res:message>
      </ns2:Resultado>
    </ns2:ConsultarCondicionByIdLineaCreditoResponse>
};

local:func($ConsultarCondicionByIdLineaCreditoOutputCollection)
