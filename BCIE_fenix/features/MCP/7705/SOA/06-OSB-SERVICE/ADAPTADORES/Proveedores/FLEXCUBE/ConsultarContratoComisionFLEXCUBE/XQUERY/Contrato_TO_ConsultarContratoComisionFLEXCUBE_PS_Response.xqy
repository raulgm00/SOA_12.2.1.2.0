xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://org/bcie/ws/middle/FLEXCUBE.wsdl/types/";
(:: import schema at "../../WSDL/FLEXCUBE_WSDL.wsdl" ::)
declare namespace ns1="http://www.bcie.org/ComisionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/Comision/V1/Schema/ComisionMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com = "http://www.bcie.org/ComisionBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare variable $codigoResultado as xs:string external;
declare variable $tipoResultado as xs:string external;
declare variable $mensaje as xs:string external;
declare variable $contrato as element() (:: element(*, ns2:ContratoTypeUser) ::) external;

declare function local:func($codigoResultado as xs:string, 
                            $tipoResultado as xs:string, 
                            $mensaje as xs:string, 
                            $contrato as element() (:: element(*, ns2:ContratoTypeUser) ::)) 
                            as element() (:: schema-element(ns1:ConsultarContratoComisionFLEXCUBEResponse) ::) {
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
               { for $monto in $contrato/ns2:listaBalance/ns2:array
            return 
             if(fn:data($monto/ns2:componente) = 'TRAMITEINI') then <com:montoComision>{fn:data($monto/ns2:balance)}</com:montoComision>
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
                if (fn:data($codigoResultado)= '0') then
            <res:result>OK</res:result>
             else  <res:result>ERROR</res:result>
             }
            <res:message>{fn:data($mensaje)}</res:message>
            <res:error>
                <err:errorCode>{fn:data($codigoResultado)}</err:errorCode>
                <err:errorDescription>{fn:data($tipoResultado)}</err:errorDescription>
            </res:error>
        </ns1:Resultado>
    </ns1:ConsultarContratoComisionFLEXCUBEResponse>
};

local:func($codigoResultado, $tipoResultado, $mensaje, $contrato)