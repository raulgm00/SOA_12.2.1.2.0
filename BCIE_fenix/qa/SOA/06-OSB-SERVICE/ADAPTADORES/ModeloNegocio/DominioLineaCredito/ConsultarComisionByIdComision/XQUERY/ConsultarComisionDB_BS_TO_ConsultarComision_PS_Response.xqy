xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ComisionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/Comision/V1/Schema/ComisionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarComisionDB";
(:: import schema at "../XSD/ConsultarComisionDB.xsd" ::)

declare namespace car = "http://www.bcie.org/ComisionBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace dec = "http://www.bcie.org/DeclaracionJuradaBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare variable $ConsultarComisionDBOutputCollection as element() (:: schema-element(ns2:ConsultarComisionDBOutputCollection) ::) external;

declare function local:func($ConsultarComisionDBOutputCollection as element() (:: schema-element(ns2:ConsultarComisionDBOutputCollection) ::)) as element() (:: schema-element(ns1:ConsultarComisionResponse) ::) {
    <ns1:ConsultarComisionResponse>
    
    {for $comis in $ConsultarComisionDBOutputCollection/ns2:ConsultarComisionDBOutput
    return
        <ns1:Comision>
           <car:idComision>{fn:data($comis/ns2:ID)}</car:idComision>
           <car:idOperacion>{fn:data($comis/ns2:ID_OPERACION)}</car:idOperacion>
            <car:nombre>{fn:data($comis/ns2:NOMBRE)}</car:nombre>
            <car:descripcion>{fn:data($comis/ns2:DESCRIPCION)}</car:descripcion>
            <car:tipoComision>
                <car:idCatComision>{fn:data($comis/ns2:ID_TCA_COMISION)}</car:idCatComision>
                <car:descripcion>{fn:data($comis/ns2:DESCRIPCION_COMISION)}</car:descripcion>
                <car:descripcionCorta>{fn:data($comis/ns2:COD_EXTERNO_TIPOCOMISION)}</car:descripcionCorta>
                <car:idTipoComision>
                    <cat:Id>{fn:data($comis/ns2:ID_TCA_COMISION)}</cat:Id>
                    <cat:Descripcion>{fn:data($comis/ns2:DESCRIPCION_COMISION)}</cat:Descripcion>
              
                    <cat:codigoExterno>{fn:data($comis/ns2:COD_EXTERNO_TIPOCOMISION)}</cat:codigoExterno>
                </car:idTipoComision>
                <car:requiereValidarCOFI>{  if (string ($comis/ns2:REQUIERE_VALIDAR_COFI)='') then false() else
               if( fn:data($comis/ns2:REQUIERE_VALIDAR_COFI)=1) then true () else 
               false ()
                }</car:requiereValidarCOFI>
               
            </car:tipoComision>
      
            <car:moneda>
                <cat:Id>{fn:data($comis/ns2:ID_TCA_MONEDA)}</cat:Id>
                <cat:Descripcion>{fn:data($comis/ns2:DESCRIPCION_MONEDA)}</cat:Descripcion>
                <cat:DescripcionCorta>{fn:data($comis/ns2:DESCRIPCION_CORTA_MONEDA)}</cat:DescripcionCorta>
                <cat:codigoExterno>{fn:data($comis/ns2:COD_EXTERNO_MONEDA)}</cat:codigoExterno>
            </car:moneda>
          <car:montoComision>{fn:data($comis/ns2:MONTO_COMISION)}</car:montoComision>
            <car:montoBase>
                <car:idMontoBase>{fn:data($comis/ns2:ID_TCA_MONTO_BASE)}</car:idMontoBase>
                <car:valorMontoBase>{fn:data($comis/ns2:MONTO_BASE)}</car:valorMontoBase>
                <car:porcentajeMontoBase>{fn:data($comis/ns2:PORCENTAJE_SOBRE_MONTO_BASE)}</car:porcentajeMontoBase>
                <car:descripcionMontoBase>{fn:data($comis/ns2:DESCRIPCION_MONTO_BASE)}</car:descripcionMontoBase>
            </car:montoBase>
            <car:fechaValor>{fn:substring-before($comis/ns2:FECHA_VALOR/text(),'T')}</car:fechaValor>
            <car:fechaVencimiento>{fn:substring-before($comis/ns2:FECHA_VENCIMIENTO/text(),'T')}</car:fechaVencimiento>
            <car:fechaInicioCapital>{fn:substring-before($comis/ns2:FECHA_INICIO_CAPITAL/text(),'T')}</car:fechaInicioCapital>
            <car:fechaInicioComision>{fn:substring-before($comis/ns2:FECHA_INICIO_COMISION/text(),'T')}</car:fechaInicioComision>
           
            <car:tipoFrecuencia>
                <cat:Id>{fn:data($comis/ns2:ID_TCA_TIPO_FRECUENCIA)}</cat:Id>
                <cat:Descripcion>{fn:data($comis/ns2:DESCRIPCION_TIPOFREC)}</cat:Descripcion>
                <cat:codigoExterno>{fn:data($comis/ns2:COD_EXTERNO_TIPO_FREC)}</cat:codigoExterno>
            </car:tipoFrecuencia>
           <car:frecuenciaPago>{fn:data($comis/ns2:FRECUENCIA_PAGO)}</car:frecuenciaPago>
            <car:fondo>{fn:data($comis/ns2:ID_TCA_FONDO)}</car:fondo>
         
            <car:comisionCompartida>{fn:data($comis/ns2:COMISION_COMPARTIDA)}</car:comisionCompartida>
            <car:codigoDesembolso>{fn:data($comis/ns2:CODIGO_DESEMBOLSO)}</car:codigoDesembolso>
            <car:numeroTesoreria>{fn:data($comis/ns2:NUMERO_TESORERIA)}</car:numeroTesoreria>
            <car:codigoContrato>{fn:data($comis/ns2:CODIGO_CONTRATO)}</car:codigoContrato>
            <car:momentoCobro>
                <cat:Id>{fn:data($comis/ns2:ID_TCA_MOMENTO_COBRO)}</cat:Id>
                <cat:Descripcion>{fn:data($comis/ns2:DESCRIPCION_MOMENTO_COBRO)}</cat:Descripcion>
               
            </car:momentoCobro>
            <car:tipoTasa>
                <cat:Id>{fn:data($comis/ns2:ID_TCA_TIPO_TASA)}</cat:Id>
              
            </car:tipoTasa>
            <car:baseCalculo>
                <cat:Id>{fn:data($comis/ns2:ID_TCA_BASE_CALCULO)}</cat:Id>
                <cat:descripcion>{fn:data($comis/ns2:DESCRIPCION_BASE_CALCULO)}</cat:descripcion>
                <cat:descripcionCorta>{fn:data($comis/ns2:DESCRIPCION_CORTA_B_CALCULO)}</cat:descripcionCorta>
                <cat:estatus>{fn:data($comis/ns2:BAN_ESTATUS_BASE_CALCULO)}</cat:estatus>
                <cat:codigoExterno>{fn:data($comis/ns2:COD_EXTERNO_BASE_CALCULO)}</cat:codigoExterno>
            </car:baseCalculo>
            <car:responsableComision>{fn:data($comis/ns2:RESPONSABLE_COMISION)}</car:responsableComision>
            <car:estadoTCC>
               <atr:id>{fn:data($comis/ns2:ID_TCA_ESTADO_TCC)}</atr:id>
                <atr:descripcion>{fn:data($comis/ns2:DESCRIPCION_ESTADO_TCC)}</atr:descripcion>
                <atr:descripcionCorta>{fn:data($comis/ns2:DESCRIPCION_CORTA_ESTADO_TCC)}</atr:descripcionCorta>
                <atr:estatus>{fn:data($comis/ns2:BAN_ESTATUS_ESTADO_TCC)}</atr:estatus>
                <atr:codigoExterno>{fn:data($comis/ns2:COD_EXTERNO_ESTADO_TCC)}</atr:codigoExterno>
            </car:estadoTCC>
            <car:subEstadoTCC>
                <atr:id>{fn:data($comis/ns2:ID_TCA_SUB_ESTADO_TCC)}</atr:id>
               
            </car:subEstadoTCC>
         
            <car:fechaRegistro>{fn:data($comis/ns2:FECHA_REGISTRO)}</car:fechaRegistro>
          
            <car:estado> {if(string($comis/ns2:BAN_ESTATUS)='') then false () else
                   if( fn:data($comis/ns2:BAN_ESTATUS)=1) then true() else false()
                    }</car:estado>
            <car:comisionEnmendada>{fn:data($comis/ns2:ID_COMISION_ENMENDADA)}</car:comisionEnmendada>
            <car:fechaEnmienda>{fn:data($comis/ns2:FECHA_ENMIENDA)}</car:fechaEnmienda>
            
            <car:banSugerida>{fn:data($comis/ns2:BAN_SUGERIDA)}</car:banSugerida>
            <car:numeroCuentaCliente>{fn:data($comis/ns2:NUMERO_CUENTA_CLIENTE)}</car:numeroCuentaCliente>
          
        </ns1:Comision>}
         {for $comis in $ConsultarComisionDBOutputCollection/ns2:ConsultarComisionDBOutput
          return
        <ns1:Operacion>
            <ope:responsable>{fn:data($comis/ns2:EJECUTIVO)}</ope:responsable>
            <ope:nombre>{fn:data($comis/ns2:NOMBRE_OPERACION)}</ope:nombre>
            <ope:cliente>
                <cli:idFacturador>{fn:data($comis/ns2:ID_FLEXCUBE)}</cli:idFacturador>
                <cli:tipoPersona>
                    
                    <cat:codigoExterno>{fn:data($comis/ns2:ID_FLEXCUBE)}</cat:codigoExterno>
                </cli:tipoPersona>
              
                <cli:sector>
                    <cat:Id>{fn:data($comis/ns2:SECTOR_INSTITUCIONAL)}</cat:Id>
                    <cat:descripcion>{fn:data($comis/ns2:DESCRIPCION_S_INST)}</cat:descripcion>
                    <cat:descripcionCorta>{fn:data($comis/ns2:DESCRIPCION_CORTA_S_INST)}</cat:descripcionCorta>
                    <cat:estatus>{fn:data($comis/ns2:BAN_ESTATUS_S_INST)}</cat:estatus>
                    <cat:codigoExterno>{fn:data($comis/ns2:COD_EXTERNO_S_INST)}</cat:codigoExterno>
                </cli:sector>
               
                <cli:pais>
                   
                    <cat:DescripcionCorta>{fn:data($comis/ns2:COD_EXTERNO_PAIS)}</cat:DescripcionCorta>
                  
                </cli:pais>
                <cli:ejecutivo>{fn:data($comis/ns2:EJECUTIVO)}</cli:ejecutivo>
               
             
            </ope:cliente>
           
            <ope:actividadEconomica>
               
                <cat:codigoExterno>{fn:data($comis/ns2:COD_EXTERNO_ACTIVIDAD)}</cat:codigoExterno>
            </ope:actividadEconomica>
           
          
            <ope:areaFocalizacion>
              
                <cat:codigoExterno>{fn:data($comis/ns2:COD_EXTERNO)}</cat:codigoExterno>
            </ope:areaFocalizacion>
            
            <ope:ejeEstrategico>
                <cat:codigoExterno>{fn:data($comis/ns2:COD_EXTERNO_EJE)}</cat:codigoExterno>
            </ope:ejeEstrategico>
            
        </ns1:Operacion>
        }
         {
            if (count($ConsultarComisionDBOutputCollection/ns2:ConsultarComisionDBOutput)>0) then
               
        <ns1:Resultado>
            <res:result>OK</res:result>
            <res:message>Consulta Realizada correctamente correctamente</res:message>
        </ns1:Resultado>
          else
                <ns1:Resultado>
                    <res:result>ERROR</res:result>
                    <res:message>Consulta de Comisión sin resultados</res:message>
                </ns1:Resultado>
        }
    </ns1:ConsultarComisionResponse>
};

local:func($ConsultarComisionDBOutputCollection)
