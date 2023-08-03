xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace flex="http://org/bcie/ws/middle/FLEXCUBE.wsdl";
declare namespace typ="http://org/bcie/ws/middle/FLEXCUBE.wsdl/types/";
(:: import schema at "../../WSDL/FLEXCUBE_WSDL.wsdl" ::)

declare namespace ns1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare namespace xsi="http://www.w3.org/2001/XMLSchema-instance";

declare variable $CrearTransferenciaFLEXCUBERequest as element() (:: schema-element(ns1:CrearTransferenciaFLEXCUBERequest) ::) external;

declare function local:func($CrearTransferenciaFLEXCUBERequest as element() (:: schema-element(ns1:CrearTransferenciaFLEXCUBERequest) ::)) as element() (:: element(flex:crearTransferencia) ::) {
    <flex:crearTransferencia>
         <numeroLineaCredito>{fn:data($CrearTransferenciaFLEXCUBERequest/ns1:Parametros[com:name='NUMERO_LINEA']/com:value)}</numeroLineaCredito>
         <numeroTesoreria>{
         if($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Transferencia/des:estado = 'TB')then 
         fn:concat(fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:idDesembolso),".",fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Transferencia/des:idTransferencia),"TB")
         else if($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Transferencia/des:estado = 'TR')then fn:concat(fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:idDesembolso),".",fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Transferencia/des:idTransferencia),".TR")
         else if($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Transferencia/des:estado = 'TF')then fn:concat(fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:idDesembolso),".",fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Transferencia/des:idTransferencia),".TF")
         else(fn:concat(fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:idDesembolso),".",fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Transferencia/des:idTransferencia)))}</numeroTesoreria>
         <correlativoTransferencia>{fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Transferencia/des:idTransferencia)}</correlativoTransferencia>
         <productoFinanciero>{
         if ($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Transferencia/des:tipoMensaje = 'MT202')then 'FT03'
         else if ($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Transferencia/des:tipoMensaje = 'MT103')then 'FT02'
         else 'FT05'}</productoFinanciero>
         <numeroCuentaCliente>{fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:cuentaCliente)}</numeroCuentaCliente>
         <moneda>{fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Transferencia/des:Monto[com:tipo/cat:DescripcionCorta= 'TRANSFERENCIA_BANCARIA']/com:moneda/cat:codigoExterno)}</moneda>
         <monto>{fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Transferencia/des:Monto[com:tipo/cat:DescripcionCorta= 'TRANSFERENCIA_BANCARIA']/com:importe)}</monto>
         <fechaValor>{fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:fechaDisponibilidadFondos)}</fechaValor>
         <cuentaNostro>{fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Transferencia/des:numeroCuenta)}</cuentaNostro>
         <institucionOrdenante>BCIE</institucionOrdenante>
         <referencia>{
         let $referencia := if ($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Transferencia/des:tipoMensaje = 'MT202')
                            then (fn:concat('/BNF/PTMO ',
                                            fn:data($CrearTransferenciaFLEXCUBERequest/ns1:Parametros[com:name='NUMERO_LINEA']/com:value),' ',
                                            string(fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Transferencia/des:idTransferencia)),' ',
                                            fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Transferencia/des:referenciaMensaje)))
                            else if ($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Transferencia/des:tipoMensaje = 'MT103')
                            then (fn:concat('/RFB/PTMO ',
                                            fn:data($CrearTransferenciaFLEXCUBERequest/ns1:Parametros[com:name='NUMERO_LINEA']/com:value),' ',
                                            string(fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Transferencia/des:idTransferencia)),' ',
                                            fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Transferencia/des:referenciaMensaje)))
                            else if ($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Transferencia/des:tipoMensaje = 'FT05')
                            then (fn:concat('PTMO ',
                                            fn:data($CrearTransferenciaFLEXCUBERequest/ns1:Parametros[com:name='NUMERO_LINEA']/com:value),' ',
                                            string(fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Transferencia/des:idTransferencia))))
                            else()
          return if(fn:matches($referencia,"[\\sa-zA-Z0-9/?:().,'+-]{0,35}"))then $referencia else ()
          }</referencia> 
          
         <beneficiarioNumeroCuenta>{fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Transferencia/des:Beneficiario/des:numeroCta)}</beneficiarioNumeroCuenta>
         {
         if (fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Transferencia/des:Beneficiario/des:tipoOpcion) = 'OPCION_A')then
            <beneficiarioIndentificador>{fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Transferencia/des:Beneficiario/des:identificador)}</beneficiarioIndentificador>
         else 
          (<beneficiarioIndentificador xsi:nil="true"/>)
         }
         {
         if (fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Transferencia/des:Beneficiario/des:tipoOpcion) = 'OPCION_D')then
            <beneficiarioNombre>{fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Transferencia/des:Beneficiario/des:beneficiario)} </beneficiarioNombre>
         else 
            (<beneficiarioNombre xsi:nil="true"/>)
         }
         {
         if (fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Transferencia/des:Beneficiario/des:tipoOpcion) = 'OPCION_D')then
            <beneficiarioDireccion>{fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Transferencia/des:Beneficiario/des:direccion)} </beneficiarioDireccion>
         else 
           (<beneficiarioDireccion xsi:nil="true"/>)
         }
         
         
        <bancoNumeroCuenta>{fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Transferencia/des:Banco/des:numeroCta)}</bancoNumeroCuenta>
        {
        if (fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Transferencia/des:Banco/des:tipoOpcion)='OPCION_A')then
            <bancoIndentificador>{fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Transferencia/des:Banco/des:identificador)}</bancoIndentificador>
        else 
            (<bancoIndentificador xsi:nil="true"/>)
         }
         {
         if (fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Transferencia/des:Banco/des:tipoOpcion)='OPCION_D')then
            <bancoNombre>{fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Transferencia/des:Banco/des:beneficiario)}</bancoNombre>
         else 
            (<bancoNombre xsi:nil="true"/>)
         }
         {
         if (fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Transferencia/des:Banco/des:tipoOpcion)='OPCION_D')then
            <bancoDireccion>{fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Transferencia/des:Banco/des:direccion)}</bancoDireccion>
         else 
            (<bancoDireccion xsi:nil="true"/>)
         }
   
   
         <intermediarioNumeroCuenta>{fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Transferencia/des:Intermediario/des:numeroCta)}</intermediarioNumeroCuenta>
         {
         if (fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Transferencia/des:Intermediario/des:tipoOpcion)='OPCION_A')then
            <intermediarioIndentificador>{fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Transferencia/des:Intermediario/des:identificador)}</intermediarioIndentificador>
         else 
            (<intermediarioIndentificador xsi:nil="true"/>)
         }
         {
         if (fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Transferencia/des:Intermediario/des:tipoOpcion)='OPCION_D')then
            <intermediarioNombre>{fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Transferencia/des:Intermediario/des:beneficiario)} </intermediarioNombre>
         else 
            (<intermediarioNombre xsi:nil="true"/>)
         }
         {
         if (fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Transferencia/des:Intermediario/des:tipoOpcion)='OPCION_D')then
            <intermediarioDireccion>{fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Transferencia/des:Intermediario/des:direccion)}</intermediarioDireccion>
         else 
            (<intermediarioDireccion xsi:nil="true"/>)
         }
         
         <fondo>{fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:condicionesFinancieras/des:fondo/cat:Id)}</fondo>
         <lineaFinanciera>{
         if(fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Programa/her:LineaFinanciera/cat:codigoExterno)!='')
         then fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Programa/her:LineaFinanciera/cat:codigoExterno)
         else('ND')}</lineaFinanciera>
         <destino>{
         if(fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Programa/her:DestinoFinanciamiento/cat:codigoExterno)!='')
         then fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Programa/her:DestinoFinanciamiento/cat:codigoExterno)
         else('ND')}</destino>
         <tipoRecurso>{
         if(fn:data($CrearTransferenciaFLEXCUBERequest/ns1:Parametros[com:name='TIPO_RECURSO']/com:value)!='')
         then fn:data($CrearTransferenciaFLEXCUBERequest/ns1:Parametros[com:name='TIPO_RECURSO']/com:value)
         else('ND')}</tipoRecurso>
         <pais>{fn:data($CrearTransferenciaFLEXCUBERequest/ns1:Parametros[com:name='PAIS']/com:value)}</pais>
         <actividadEconomica>{fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:HerramientaCE/her:ActividadEconomica/cat:codigoExterno)}</actividadEconomica>
         <sectorInstitucional>{fn:data($CrearTransferenciaFLEXCUBERequest/ns1:Parametros[com:name='SECTOR']/com:value)}</sectorInstitucional>
         <ejecutivo>{fn:data($CrearTransferenciaFLEXCUBERequest/ns1:Parametros[com:name='EJECUTIVO']/com:value)}</ejecutivo>
         <ejeEstrategico>{
         if(fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:HerramientaCE/her:EjeEstrategico/cat:codigoExterno)!= '')
         then fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:HerramientaCE/her:EjeEstrategico/cat:codigoExterno)
         else('ND')}</ejeEstrategico>
         <areaFocalizacion>{
         if(fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Programa/her:HerramientaCE/her:AreaFocalizacion/cat:codigoExterno)!='')
         then fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Programa/her:HerramientaCE/her:AreaFocalizacion/cat:codigoExterno)
         else('ND')}</areaFocalizacion>
         <objetivoEstrategico>{fn:data($CrearTransferenciaFLEXCUBERequest/ns1:Parametros[com:name='OBJETIVO_ESTRATEGICO']/com:value)}</objetivoEstrategico>
    </flex:crearTransferencia>
};

local:func($CrearTransferenciaFLEXCUBERequest)