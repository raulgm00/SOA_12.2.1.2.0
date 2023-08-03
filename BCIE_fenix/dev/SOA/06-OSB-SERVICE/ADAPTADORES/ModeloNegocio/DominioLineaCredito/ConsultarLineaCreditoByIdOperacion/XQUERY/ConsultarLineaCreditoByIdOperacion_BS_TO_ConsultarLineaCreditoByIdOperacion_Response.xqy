xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarLineaCreditoByIdOperacion_DB";
(:: import schema at "../XSD/ConsultarLineaCreditoByIdOperacion_DB.xsd" ::)

declare namespace con = "http://www.bcie.org/CondicionBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com = "http://www.bcie.org/ComisionBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace ter = "http://www.bcie.org/TerminoBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ConsultarLineaCreditoByIdOperacion_DBOutputCollection as element() (:: schema-element(ns1:ConsultarLineaCreditoByIdOperacion_DBOutputCollection) ::) external;

declare function local:func($ConsultarLineaCreditoByIdOperacion_DBOutputCollection as element() (:: schema-element(ns1:ConsultarLineaCreditoByIdOperacion_DBOutputCollection) ::)) as element() (:: schema-element(ns2:ConsultarLineaCreditoByIdOperacionResponse) ::) {
    <ns2:ConsultarLineaCreditoByIdOperacionResponse>
        <ns2:IdOperacion>{fn:data($ConsultarLineaCreditoByIdOperacion_DBOutputCollection/ns1:ConsultarLineaCreditoByIdOperacion_DBOutput[1]/ns1:ID_OPERACION)}</ns2:IdOperacion>
      {
      for $lineaCredito in  $ConsultarLineaCreditoByIdOperacion_DBOutputCollection/ns1:ConsultarLineaCreditoByIdOperacion_DBOutput
      return
        <ns2:LineasCredito>
            <lin:idLineaCredito>{fn:data($lineaCredito/ns1:ID)}</lin:idLineaCredito>
            <lin:idContrato>{fn:data($lineaCredito/ns1:ID_CONTRATO)}</lin:idContrato>
            <lin:NumeroLineaCredito>{fn:data($lineaCredito/ns1:NUMERO_LINEA_CREDITO)}</lin:NumeroLineaCredito>
            <lin:Descripcion>{fn:data($lineaCredito/ns1:DESCRIPCION_LINEA)}</lin:Descripcion>
            <lin:Flexcube>
                <lin:id>{fn:data($lineaCredito/ns1:ID_FLEXCUBE)}</lin:id>
                <lin:idProductoFlexcube>{fn:data($lineaCredito/ns1:ID_PRODUCTO_FLEXCUBE)}</lin:idProductoFlexcube>
                <lin:idFlexcubePasivo>{fn:data($lineaCredito/ns1:ID_FLEXCUBE_PASIVO)}</lin:idFlexcubePasivo>
            </lin:Flexcube>
            <lin:Fondo>{fn:data($lineaCredito/ns1:FONDO)}</lin:Fondo>
            <lin:MontoLinea>{fn:data($lineaCredito/ns1:MONTO_LINEA)}</lin:MontoLinea>
            <lin:EsRevolvente>{fn:data($lineaCredito/ns1:ES_REVOLVENTE)}</lin:EsRevolvente>
            <lin:TratamientoDiasFeriados>{fn:data($lineaCredito/ns1:TRATAMIENTO_DIAS_FERIADOS)}</lin:TratamientoDiasFeriados>
            <lin:FechaValor>{fn:data($lineaCredito/ns1:FECHA_VALOR)}</lin:FechaValor>
            <lin:FechaVencimiento>{fn:data($lineaCredito/ns1:FECHA_VENCIMIENTO)}</lin:FechaVencimiento>
            <lin:CodigoPantallaLimite>{fn:data($lineaCredito/ns1:CODIGO_PLANTILLA_LIMITE)}</lin:CodigoPantallaLimite>
            <lin:CodigoSerialLimite>{fn:data($lineaCredito/ns1:CODIGO_SERIAL_LIMITE)}</lin:CodigoSerialLimite>
            <lin:CodigoAsignacion>{fn:data($lineaCredito/ns1:CODIGO_ASIGNACION)}</lin:CodigoAsignacion>
            <lin:DescripcionAsignacion>{fn:data($lineaCredito/ns1:DESCRIPCION_ASIGNACION)}</lin:DescripcionAsignacion>
            <lin:CondicionEspecial>{fn:data($lineaCredito/ns1:CONDICION_ESPECIAL)}</lin:CondicionEspecial>
            <lin:FechaRegistro>{fn:data($lineaCredito/ns1:FECHA_REGISTRO)}</lin:FechaRegistro>
            <lin:Estado>{fn:data($lineaCredito/ns1:BAN_ESTATUS)}</lin:Estado>
            <lin:descCondEspecial>{fn:data($lineaCredito/ns1:DESCRIPCION_COND_ESPECIAL)}</lin:descCondEspecial>
        </ns2:LineasCredito>
        }
      <ns2:Resultado>
          <res:result>OK</res:result>
          {
          if (count($ConsultarLineaCreditoByIdOperacion_DBOutputCollection/ns1:ConsultarLineaCreditoByIdOperacion_DBOutput)>0) then
          <res:message>Operación exitosa</res:message>
          else
          <res:message>No existen registros</res:message>
          }
          <res:error>
              <err:errorCode></err:errorCode>
              <err:errorDescription></err:errorDescription>
              <err:errorType></err:errorType>
          </res:error>
      </ns2:Resultado>
    </ns2:ConsultarLineaCreditoByIdOperacionResponse>
};

local:func($ConsultarLineaCreditoByIdOperacion_DBOutputCollection)
