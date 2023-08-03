xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarSolicitudDesembolso_DB";
(:: import schema at "../XSD/ConsultarSolicitudDesembolso_DB.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace reg = "http://www.bcie.org/ReglaBO";

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ConsultarSolicitudDesembolso_DBOutputCollection as element() (:: schema-element(ns1:ConsultarSolicitudDesembolso_DBOutputCollection) ::) external;
declare variable $ConsultarDesembolsosByIdResponse as element() (:: schema-element(ns2:ConsultarDesembolsosByIdResponse) ::) external;

declare function local:func($ConsultarSolicitudDesembolso_DBOutputCollection as element() (:: schema-element(ns1:ConsultarSolicitudDesembolso_DBOutputCollection) ::), 
                            $ConsultarDesembolsosByIdResponse as element() (:: schema-element(ns2:ConsultarDesembolsosByIdResponse) ::)) 
                            as element() (:: schema-element(ns2:ConsultarSolicitudDesembolsoResponse) ::) {
    <ns2:ConsultarSolicitudDesembolsoResponse>
    {
    for $idSolicitudDesembolso in distinct-values($ConsultarSolicitudDesembolso_DBOutputCollection/ns1:ConsultarSolicitudDesembolso_DBOutput/ns1:ID_SOLICITUD)
    let $solicitudKey:= $ConsultarSolicitudDesembolso_DBOutputCollection/ns1:ConsultarSolicitudDesembolso_DBOutput[ns1:ID_SOLICITUD=$idSolicitudDesembolso][1]
    return
    <ns2:SolicitudDesembolso>
            <des:idDesembolso>{fn:data($solicitudKey/ns1:ID_SOLICITUD)}</des:idDesembolso>
            <des:idFacturador> </des:idFacturador>
            <des:fechaCreacion>{fn:data($solicitudKey/ns1:FECHA_CREACION)}</des:fechaCreacion>
            <des:fechaSolicitud>{fn:data($solicitudKey/ns1:FECHA_SOLICITUD)}</des:fechaSolicitud>
            <des:monto>
                <com:tipo>
                    <cat:Id></cat:Id>
                    <cat:Descripcion></cat:Descripcion>
                    <cat:DescripcionCorta>SOLICITUD</cat:DescripcionCorta>
                    <cat:estatus></cat:estatus>
                    <cat:codigoExterno></cat:codigoExterno>
                </com:tipo>
                <com:importe>{fn:data($solicitudKey/ns1:MONTO_SOLICITUD)}</com:importe>
                <com:moneda>
                    <cat:Id>{fn:data($solicitudKey/ns1:ID_TCA_TIPO_MONEDA)}</cat:Id>
                    <cat:Descripcion></cat:Descripcion>
                    <cat:DescripcionCorta>{fn:data($solicitudKey/ns1:DESCRIPCION_CORTA_MONEDA)}</cat:DescripcionCorta>
                    <cat:estatus></cat:estatus>
                    <cat:codigoExterno>{fn:data($solicitudKey/ns1:CODIGO_EXTERNO_MONEDA)}</cat:codigoExterno>
                </com:moneda>
            </des:monto>
            <des:tipoMoneda>
                <cat:Id>{fn:data($solicitudKey/ns1:ID_TCA_TIPO_MONEDA)}</cat:Id>
                <cat:Descripcion></cat:Descripcion>
                <cat:DescripcionCorta>{fn:data($solicitudKey/ns1:DESCRIPCION_CORTA_MONEDA)}</cat:DescripcionCorta>
                <cat:estatus></cat:estatus>
                <cat:codigoExterno>{fn:data($solicitudKey/ns1:CODIGO_EXTERNO_MONEDA)}</cat:codigoExterno>
            </des:tipoMoneda>
            <des:estado>
                <cat:Id>{fn:data($solicitudKey/ns1:ID_TCA_ESTADO)}</cat:Id>
                <cat:Descripcion></cat:Descripcion>
                <cat:DescripcionCorta></cat:DescripcionCorta>
                <cat:estatus></cat:estatus>
                <cat:codigoExterno></cat:codigoExterno>
            </des:estado>
            <des:estatus></des:estatus>
            <des:instanciaProceso></des:instanciaProceso>
            <des:ValidacionAsignacion>{if( fn:string( fn:data($solicitudKey/ns1:REQUIERE_VALIDACION_REC_EXT))='1')then 'true' else ('false')}</des:ValidacionAsignacion>
            <des:Excepcion>
                <reg:Id></reg:Id>
                <reg:Descripcion></reg:Descripcion>
                <reg:Transaccion></reg:Transaccion>
                <reg:Tipo>
                    <cat:Id></cat:Id>
                    <cat:Descripcion></cat:Descripcion>
                    <cat:DescripcionCorta></cat:DescripcionCorta>
                    <cat:estatus></cat:estatus>
                    <cat:codigoExterno></cat:codigoExterno>
                </reg:Tipo>
                <reg:PosibleExceptuar></reg:PosibleExceptuar>
                <reg:Exceptuado>
                    <cat:Id></cat:Id>
                    <cat:Descripcion></cat:Descripcion>
                    <cat:DescripcionCorta></cat:DescripcionCorta>
                    <cat:estatus></cat:estatus>
                    <cat:codigoExterno></cat:codigoExterno>
                </reg:Exceptuado>
                <reg:UsuarioExceptua></reg:UsuarioExceptua>
                <reg:FechaExcepcion></reg:FechaExcepcion>
                <reg:Estado>
                    <cat:Id>{fn:data($solicitudKey/ns1:ID_TCA_ESTADO)}</cat:Id>
                    <cat:Descripcion></cat:Descripcion>
                    <cat:DescripcionCorta>{fn:data($solicitudKey/ns1:DESCRIPCION_CORTA_EDO)}</cat:DescripcionCorta>
                    <cat:estatus></cat:estatus>
                    <cat:codigoExterno>{fn:data($solicitudKey/ns1:COD_EXTERNO_EDO)}</cat:codigoExterno>
                </reg:Estado>
                <reg:Estatus></reg:Estatus>
                <reg:DetalleRegla>
                    <atr:id></atr:id>
                    <atr:descripcion></atr:descripcion>
                    <atr:estatus></atr:estatus>
                </reg:DetalleRegla>
                <des:instanciaProceso></des:instanciaProceso>
                <des:enProcesoExcepcion></des:enProcesoExcepcion>
            </des:Excepcion>
    {
      for $contratoDesembolso in $ConsultarSolicitudDesembolso_DBOutputCollection/ns1:ConsultarSolicitudDesembolso_DBOutput
      let $contrato := $ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso[des:idDesembolso = $contratoDesembolso/ns1:ID_CONTRATO_DESEMBOLSO]
      where $contratoDesembolso/ns1:ID_SOLICITUD= $idSolicitudDesembolso
      return
        if(not(empty($contrato))) then 
          <des:ContratoDesembolso>
              <des:idDesembolso>{data($contrato/des:idDesembolso)}</des:idDesembolso>
              <des:idFacturador>{data($contrato/des:idFacturador)}</des:idFacturador>
              <des:producto>{$contrato/des:producto/*}</des:producto>
              <des:referencia>{data($contrato/des:referencia)}</des:referencia>
              {
                for $monto in $contrato/des:monto
                return
                <des:monto>{$monto/*}</des:monto>
              }
              <des:estado>{$contrato/des:estado/*}</des:estado>
              <des:programado>{data($contrato/des:programado)}</des:programado>
              <des:fechaEstimadaDesembolsar>{data($contrato/des:fechaEstimadaDesembolsar)}</des:fechaEstimadaDesembolsar>
              <des:fechaEfectiva>{data($contrato/des:fechaEfectiva)}</des:fechaEfectiva>
              <des:fechaAsignacion>{data($contrato/des:fechaAsignacion)}</des:fechaAsignacion>
              <des:fechaRegistro>{data($contrato/des:fechaRegistro)}</des:fechaRegistro>
              <des:fechaVencimiento>{data($contrato/des:fechaVencimiento)}</des:fechaVencimiento>
              <des:estatus>{data($contrato/des:estatus)}</des:estatus>
              <des:condicionesFinancieras>
                   <des:idCondicionFinanciera>{data($contrato/des:condicionesFinancieras/des:idCondicionFinanciera)}</des:idCondicionFinanciera>
                   <des:tasa>{$contrato/des:condicionesFinancieras/des:tasa/*}</des:tasa>
                   <des:fondo>
                       <cat:Id>{fn:data($contratoDesembolso/ns1:ID_FONDO)}</cat:Id>
                       <cat:Descripcion></cat:Descripcion>
                       <cat:DescripcionCorta>{fn:data($contratoDesembolso/ns1:FONDO)}</cat:DescripcionCorta>
                       <cat:estatus></cat:estatus>
                       <cat:codigoExterno></cat:codigoExterno>
                       <des:Validador>
                           <cat:Id>{fn:data($contratoDesembolso/ns1:ID_ROL_BPM)}</cat:Id>
                           <cat:Descripcion></cat:Descripcion>
                           <cat:DescripcionCorta>{fn:data($contratoDesembolso/ns1:DESCRIPCION_CORTA)}</cat:DescripcionCorta>
                           <cat:estatus></cat:estatus>
                           <cat:codigoExterno></cat:codigoExterno>
                       </des:Validador>
                   </des:fondo>
                   <des:baseCalculo>{$contrato/des:condicionesFinancieras/des:baseCalculo/*}</des:baseCalculo>
                   <des:principal>{$contrato/des:condicionesFinancieras/des:principal/*}</des:principal>
                   <des:interes>{$contrato/des:condicionesFinancieras/des:interes/*}</des:interes>
                   <des:periodoGracia>{$contrato/des:condicionesFinancieras/des:periodoGracia/*}</des:periodoGracia>
                   <des:tratamientoDiasFeriados>{data($contrato/des:condicionesFinancieras/des:tratamientoDiasFeriados)}</des:tratamientoDiasFeriados>
                   <des:tipoCalendario>{$contrato/des:condicionesFinancieras/des:tipoCalendario/*}</des:tipoCalendario>
                   <des:calendarioComplejo>{$contrato/des:condicionesFinancieras/des:calendarioComplejo/*}</des:calendarioComplejo>
               </des:condicionesFinancieras>
              <des:idLinea>{data($contrato/des:idLinea)}</des:idLinea>
              <des:recursosExternos>{data($contrato/des:recursosExternos)}</des:recursosExternos>
              <des:cuentaCliente>{data($contrato/des:cuentaCliente)}</des:cuentaCliente>
              <des:usuario>{data($contrato/des:usuario)}</des:usuario>
              <des:fechaDisponibilidadFondos>{data($contrato/des:fechaDisponibilidadFondos)}</des:fechaDisponibilidadFondos>
              <des:origenTransferenciaCte>{data($contrato/des:origenTransferenciaCte)}</des:origenTransferenciaCte>
              <des:Programa>{$contrato/des:Programa/*}</des:Programa>
              <des:EstimadoDesembolso>{$contrato/des:EstimadoDesembolso/*}</des:EstimadoDesembolso>
              <des:Utilizacion>{$contrato/des:Utilizacion/*}</des:Utilizacion>
              <des:Cargo>{$contrato/des:Cargo/*}</des:Cargo>
              <des:Comision>{$contrato/des:Comision/*}</des:Comision>
              <des:Transferencia>{$contrato/des:Transferencia/*}</des:Transferencia>
              <des:TransferenciaFT05>{$contrato/des:TransferenciaFT05/*}</des:TransferenciaFT05>
              <des:TransferenciaRecursos>{$contrato/des:TransferenciaRecursos/*}</des:TransferenciaRecursos>
              <des:HerramientaCE>{$contrato/des:HerramientaCE/*}</des:HerramientaCE>
          </des:ContratoDesembolso>
        else
          ()
      }
    </ns2:SolicitudDesembolso>
    
    }
      <ns2:Resultado>
        <res:result>OK</res:result>
          <res:message>{
            if (count($ConsultarSolicitudDesembolso_DBOutputCollection/ns1:ConsultarSolicitudDesembolso_DBOutput)>0)then 'Consulta Exitosa'
            else 'No existen registros'}</res:message>
        </ns2:Resultado>
    </ns2:ConsultarSolicitudDesembolsoResponse>
};

local:func($ConsultarSolicitudDesembolso_DBOutputCollection, $ConsultarDesembolsosByIdResponse)