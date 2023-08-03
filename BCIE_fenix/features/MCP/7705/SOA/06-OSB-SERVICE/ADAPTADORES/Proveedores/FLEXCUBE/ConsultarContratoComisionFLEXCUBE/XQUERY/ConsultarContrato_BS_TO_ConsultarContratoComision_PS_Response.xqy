xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ComisionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/Comision/V1/Schema/ComisionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ConsultarContrato";
(:: import schema at "../../ConsultarFLEXCUBE/XSD/ConsultarContrato_sp.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com = "http://www.bcie.org/ComisionBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare variable $consultarContratoResponse as element() (:: schema-element(ns2:OutputParameters) ::) external;

declare function local:func($consultarContratoResponse as element() (:: schema-element(ns2:OutputParameters) ::)) as element() (:: schema-element(ns1:ConsultarContratoComisionFLEXCUBEResponse) ::) {
    <ns1:ConsultarContratoComisionFLEXCUBEResponse>
         <ns1:Comision>
             <com:idComision></com:idComision>
             <com:idOperacion></com:idOperacion>
             <com:nombre></com:nombre>
        
             <com:descripcion></com:descripcion>
             <com:tipoComision>
                 <com:idCatComision></com:idCatComision>
                 <com:descripcion></com:descripcion>
                 <com:descripcionCorta></com:descripcionCorta>
                 <com:idTipoComision>
                     <cat:Id></cat:Id>
                     <cat:Descripcion></cat:Descripcion>
                     <cat:DescripcionCorta></cat:DescripcionCorta>
                     <cat:estatus></cat:estatus>
                     <cat:codigoExterno></cat:codigoExterno>
                 </com:idTipoComision>
                 <com:esEditableEnFormalizacion></com:esEditableEnFormalizacion>
                 <com:requiereValidarCOFI></com:requiereValidarCOFI>
                 <com:sePuedeDispensar></com:sePuedeDispensar>
                 <com:seRegistraEnFlexCube></com:seRegistraEnFlexCube>
                 <com:esPlantilla></com:esPlantilla>
                 <com:idComisionPrecarga></com:idComisionPrecarga>
                 <com:fechaRegistro></com:fechaRegistro>
                 <com:estado></com:estado>
                 <com:codigoExterno></com:codigoExterno>
             </com:tipoComision>
             <com:moneda>
                 <cat:Id></cat:Id>
                 <cat:Descripcion></cat:Descripcion>
                 <cat:DescripcionCorta></cat:DescripcionCorta>
                 <cat:estatus></cat:estatus>
                 <cat:codigoExterno></cat:codigoExterno>
             </com:moneda>
               { for $monto in $consultarContratoResponse/ns2:CONTRATO/ns2:LISTA_BALANCE/ns2:LISTA_BALANCE_ITEM
            return 
             if(fn:data($monto/ns2:COMPONENTE) = 'TRAMITEINI') then <com:montoComision>{fn:data($monto/ns2:BALANCE)}</com:montoComision>
             else ()
             }
             <com:montoBase>
                 <com:idMontoBase></com:idMontoBase>
                 <com:valorMontoBase></com:valorMontoBase>
                 <com:porcentajeMontoBase></com:porcentajeMontoBase>
             </com:montoBase>
             <com:fechaValor></com:fechaValor>
             <com:fechaVencimiento></com:fechaVencimiento>
             <com:fechaInicioCapital></com:fechaInicioCapital>
             <com:fechaInicioComision></com:fechaInicioComision>
             <com:tipoFrecuencia>
                 <cat:Id></cat:Id>
                 <cat:Descripcion></cat:Descripcion>
                 <cat:DescripcionCorta></cat:DescripcionCorta>
                 <cat:estatus></cat:estatus>
                 <cat:codigoExterno></cat:codigoExterno>
             </com:tipoFrecuencia>
             <com:frecuenciaPago></com:frecuenciaPago>
             <com:fondo></com:fondo>
             <com:comisionCompartida></com:comisionCompartida>
             <com:codigoDesembolso></com:codigoDesembolso>
             <com:numeroTesoreria></com:numeroTesoreria>
             <com:codigoContrato></com:codigoContrato>
             <com:momentoCobro>
                 <cat:Id></cat:Id>
                 <cat:Descripcion></cat:Descripcion>
                 <cat:DescripcionCorta></cat:DescripcionCorta>
                 <cat:estatus></cat:estatus>
                 <cat:codigoExterno></cat:codigoExterno>
             </com:momentoCobro>
             <com:tipoTasa>
                 <cat:Id></cat:Id>
                 <cat:Descripcion></cat:Descripcion>
                 <cat:DescripcionCorta></cat:DescripcionCorta>
                 <cat:estatus></cat:estatus>
                 <cat:codigoExterno></cat:codigoExterno>
             </com:tipoTasa>
             <com:baseCalculo>
                 <cat:Id></cat:Id>
                 <cat:Descripcion></cat:Descripcion>
                 <cat:DescripcionCorta></cat:DescripcionCorta>
                 <cat:estatus></cat:estatus>
                 <cat:codigoExterno></cat:codigoExterno>
             </com:baseCalculo>
             <com:responsableComision></com:responsableComision>
             <com:estadoTCC>
                 <atr:id></atr:id>
                 <atr:descripcion></atr:descripcion>
                 <atr:descripcionCorta></atr:descripcionCorta>
                 <atr:estatus></atr:estatus>
                 <atr:codigoExterno></atr:codigoExterno>
                 <atr:esSubEstado></atr:esSubEstado>
             </com:estadoTCC>
             <com:subEstadoTCC>
                 <atr:id></atr:id>
                 <atr:descripcion></atr:descripcion>
                 <atr:descripcionCorta></atr:descripcionCorta>
                 <atr:estatus></atr:estatus>
                 <atr:codigoExterno></atr:codigoExterno>
                 <atr:esSubEstado></atr:esSubEstado>
             </com:subEstadoTCC>
             <com:fechaRegistro></com:fechaRegistro>
             <com:estado></com:estado>
             <com:comisionEnmendada></com:comisionEnmendada>
             <com:fechaEnmienda></com:fechaEnmienda>
             <com:banSugerida></com:banSugerida>
             <com:numeroCuentaCliente></com:numeroCuentaCliente>
             <com:observaciones></com:observaciones>
             <com:configAtributo>
                 <atr:id></atr:id>
                 <atr:columna></atr:columna>
                 <atr:ordenamiento></atr:ordenamiento>
                 <atr:puedeLeer></atr:puedeLeer>
                 <atr:puedeModificar></atr:puedeModificar>
                 <atr:etiqueta></atr:etiqueta>
             </com:configAtributo>
         </ns1:Comision>
         <ns1:Resultado>
         {
                if (fn:data($consultarContratoResponse/ns2:CODIGO_RESULTADO)= 0) then
            <res:result>OK</res:result>
             else  <res:result>ERROR</res:result>
             }
            <res:message>{fn:data($consultarContratoResponse/ns2:MENSAJE)}</res:message>
            <res:error>
                <err:errorCode>{fn:data($consultarContratoResponse/ns2:CODIGO_RESULTADO)}</err:errorCode>
                <err:errorDescription>{fn:data($consultarContratoResponse/ns2:CODIGO_RESULTADO)}</err:errorDescription>
            </res:error>
        </ns1:Resultado>
    </ns1:ConsultarContratoComisionFLEXCUBEResponse>
};

local:func($consultarContratoResponse)