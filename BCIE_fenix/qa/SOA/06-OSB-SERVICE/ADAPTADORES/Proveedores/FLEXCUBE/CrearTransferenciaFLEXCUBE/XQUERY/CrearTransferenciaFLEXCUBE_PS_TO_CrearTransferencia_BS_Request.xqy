xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/CrearTransferencia";
(:: import schema at "../XSD/CrearTransferencia_sp.xsd" ::)

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare namespace xsi="http://www.w3.org/2001/XMLSchema-instance";

declare variable $CrearTransferenciaFLEXCUBERequest as element() (:: schema-element(ns1:CrearTransferenciaFLEXCUBERequest) ::) external;

declare function local:func($CrearTransferenciaFLEXCUBERequest as element() (:: schema-element(ns1:CrearTransferenciaFLEXCUBERequest) ::)) as element() (:: schema-element(ns2:InputParameters) ::) {
    <ns2:InputParameters>
        <ns2:NUMERO_LINEA_CREDITO>{fn:data($CrearTransferenciaFLEXCUBERequest/ns1:Parametros[com:name='NUMERO_LINEA']/com:value)}</ns2:NUMERO_LINEA_CREDITO>
        <ns2:NUMERO_TESORERIA>{
         if($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Transferencia/des:estado = 'TB')then 
         fn:concat(fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:idDesembolso),".",fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Transferencia/des:idTransferencia),"TB")
         else if($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Transferencia/des:estado = 'TR')then fn:concat(fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:idDesembolso),".",fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Transferencia/des:idTransferencia),".TR")
         else if($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Transferencia/des:estado = 'TF')then fn:concat(fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:idDesembolso),".",fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Transferencia/des:idTransferencia),".TF")
         else(fn:concat(fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:idDesembolso),".",fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Transferencia/des:idTransferencia)))}</ns2:NUMERO_TESORERIA>
        <ns2:CORRELATIVO_TRANSFERENCIA>{fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Transferencia/des:idTransferencia)}</ns2:CORRELATIVO_TRANSFERENCIA>
        <ns2:PRODUCTO_FINANCIERO>{
         if ($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Transferencia/des:tipoMensaje = 'MT202')then 'FT03'
         else if ($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Transferencia/des:tipoMensaje = 'MT103')then 'FT02'
         else 'FT05'}</ns2:PRODUCTO_FINANCIERO>
        <ns2:NUMERO_CUENTA_CLIENTE>{fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:cuentaCliente)}</ns2:NUMERO_CUENTA_CLIENTE>
        <ns2:MONEDA>{fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Transferencia/des:Monto[com:tipo/cat:DescripcionCorta= 'TRANSFERENCIA_BANCARIA']/com:moneda/cat:codigoExterno)}</ns2:MONEDA>
        <ns2:MONTO>{fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Transferencia/des:Monto[com:tipo/cat:DescripcionCorta= 'TRANSFERENCIA_BANCARIA']/com:importe)}</ns2:MONTO>
        <ns2:FECHA_VALOR>{fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:fechaDisponibilidadFondos)}</ns2:FECHA_VALOR>
        <ns2:CUENTA_NOSTRO>{fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Transferencia/des:numeroCuenta)}</ns2:CUENTA_NOSTRO>
        <ns2:INSTITUCION_ORDENANTE>BCIE</ns2:INSTITUCION_ORDENANTE>
        <ns2:REFERENCIA>{
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
          }</ns2:REFERENCIA>
        <ns2:BENEFICIARIO_NUMERO_CUENTA>{fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Transferencia/des:Beneficiario/des:numeroCta)}</ns2:BENEFICIARIO_NUMERO_CUENTA>
         {
         if (fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Transferencia/des:Beneficiario/des:tipoOpcion) = 'OPCION_A')then
            <ns2:BENEFICIARIO_INDENTIFICADOR>{fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Transferencia/des:Beneficiario/des:identificador)}</ns2:BENEFICIARIO_INDENTIFICADOR>
         else 
          (<ns2:BENEFICIARIO_INDENTIFICADOR xsi:nil="true"/>)
         }
         {
         if (fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Transferencia/des:Beneficiario/des:tipoOpcion) = 'OPCION_D')then
            <ns2:BENEFICIARIO_NOMBRE>{fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Transferencia/des:Beneficiario/des:beneficiario)} </ns2:BENEFICIARIO_NOMBRE>
         else 
            (<ns2:BENEFICIARIO_NOMBRE xsi:nil="true"/>)
         }
         {
         if (fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Transferencia/des:Beneficiario/des:tipoOpcion) = 'OPCION_D')then
            <ns2:BENEFICIARIO_DIRECCION>{fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Transferencia/des:Beneficiario/des:direccion)} </ns2:BENEFICIARIO_DIRECCION>
         else 
           (<ns2:BENEFICIARIO_DIRECCION xsi:nil="true"/>)
         }
        <ns2:BANCO_NUMERO_CUENTA>{fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Transferencia/des:Banco/des:numeroCta)}</ns2:BANCO_NUMERO_CUENTA>
        {
        if (fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Transferencia/des:Banco/des:tipoOpcion)='OPCION_A')then
            <ns2:BANCO_INDENTIFICADOR>{fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Transferencia/des:Banco/des:identificador)}</ns2:BANCO_INDENTIFICADOR>
        else 
            (<ns2:BANCO_INDENTIFICADOR xsi:nil="true"/>)
         }
         {
         if (fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Transferencia/des:Banco/des:tipoOpcion)='OPCION_D')then
            <ns2:BANCO_NOMBRE>{fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Transferencia/des:Banco/des:beneficiario)}</ns2:BANCO_NOMBRE>
         else 
            (<ns2:BANCO_NOMBRE xsi:nil="true"/>)
         }
         {
         if (fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Transferencia/des:Banco/des:tipoOpcion)='OPCION_D')then
            <ns2:BANCO_DIRECCION>{fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Transferencia/des:Banco/des:direccion)}</ns2:BANCO_DIRECCION>
         else 
            (<ns2:BANCO_DIRECCION xsi:nil="true"/>)
         }
        <ns2:INTERMEDIARIO_NUMERO_CUENTA>{fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Transferencia/des:Intermediario/des:numeroCta)}</ns2:INTERMEDIARIO_NUMERO_CUENTA>
        {
         if (fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Transferencia/des:Intermediario/des:tipoOpcion)='OPCION_A')then
            <ns2:INTERMEDIARIO_INDENTIFICADOR>{fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Transferencia/des:Intermediario/des:identificador)}</ns2:INTERMEDIARIO_INDENTIFICADOR>
         else 
            (<ns2:INTERMEDIARIO_INDENTIFICADOR xsi:nil="true"/>)
         }
         {
         if (fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Transferencia/des:Intermediario/des:tipoOpcion)='OPCION_D')then
            <ns2:INTERMEDIARIO_NOMBRE>{fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Transferencia/des:Intermediario/des:beneficiario)} </ns2:INTERMEDIARIO_NOMBRE>
         else 
            (<ns2:INTERMEDIARIO_NOMBRE xsi:nil="true"/>)
         }
         {
         if (fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Transferencia/des:Intermediario/des:tipoOpcion)='OPCION_D')then
            <ns2:INTERMEDIARIO_DIRECCION>{fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Transferencia/des:Intermediario/des:direccion)}</ns2:INTERMEDIARIO_DIRECCION>
         else 
            (<ns2:INTERMEDIARIO_DIRECCION xsi:nil="true"/>)
         }
        <ns2:FONDO>{fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:condicionesFinancieras/des:fondo/cat:Id)}</ns2:FONDO>
        <ns2:LINEA_FINANCIERA>{
         if(fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Programa/her:LineaFinanciera/cat:codigoExterno)!='')
         then fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Programa/her:LineaFinanciera/cat:codigoExterno)
         else('ND')}</ns2:LINEA_FINANCIERA>
        <ns2:DESTINO>{
         if(fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Programa/her:DestinoFinanciamiento/cat:codigoExterno)!='')
         then fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Programa/her:DestinoFinanciamiento/cat:codigoExterno)
         else('ND')}</ns2:DESTINO>
        <ns2:TIPO_RECURSO>{
         if(fn:data($CrearTransferenciaFLEXCUBERequest/ns1:Parametros[com:name='TIPO_RECURSO']/com:value)!='')
         then fn:data($CrearTransferenciaFLEXCUBERequest/ns1:Parametros[com:name='TIPO_RECURSO']/com:value)
         else('ND')}</ns2:TIPO_RECURSO>
        <ns2:PAIS>{fn:data($CrearTransferenciaFLEXCUBERequest/ns1:Parametros[com:name='PAIS']/com:value)}</ns2:PAIS>
        <ns2:ACTIVIDAD_ECONOMICA>{fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:HerramientaCE/her:ActividadEconomica/cat:codigoExterno)}</ns2:ACTIVIDAD_ECONOMICA>
        <ns2:SECTOR_INSTITUCIONAL>{fn:data($CrearTransferenciaFLEXCUBERequest/ns1:Parametros[com:name='SECTOR']/com:value)}</ns2:SECTOR_INSTITUCIONAL>
        <ns2:EJECUTIVO>{fn:data($CrearTransferenciaFLEXCUBERequest/ns1:Parametros[com:name='EJECUTIVO']/com:value)}</ns2:EJECUTIVO>
        <ns2:EJE_ESTRATEGICO>{
         if(fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:HerramientaCE/her:EjeEstrategico/cat:codigoExterno)!= '')
         then fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:HerramientaCE/her:EjeEstrategico/cat:codigoExterno)
         else('ND')}</ns2:EJE_ESTRATEGICO>
        <ns2:AREA_FOCALIZACION>{
         if(fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Programa/her:HerramientaCE/her:AreaFocalizacion/cat:codigoExterno)!='')
         then fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Programa/her:HerramientaCE/her:AreaFocalizacion/cat:codigoExterno)
         else('ND')}</ns2:AREA_FOCALIZACION>
        <ns2:OBJETIVO_ESTRATEGICO>{fn:data($CrearTransferenciaFLEXCUBERequest/ns1:Parametros[com:name='OBJETIVO_ESTRATEGICO']/com:value)}</ns2:OBJETIVO_ESTRATEGICO>
    </ns2:InputParameters>
};

local:func($CrearTransferenciaFLEXCUBERequest)