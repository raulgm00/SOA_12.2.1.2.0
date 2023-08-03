xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns3="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarFuente";
(:: import schema at "../../ConsultarFuente/XSD/ConsultarFuente.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarLineaCredito";
(:: import schema at "../XSD/ConsultarLineaCredito.xsd" ::)

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ConsultarFuenteOutputCollection as element() (:: schema-element(ns1:ConsultarFuenteOutputCollection) ::) external;
declare variable $ConsultarLineaCreditoOutputCollection as element() (:: schema-element(ns2:ConsultarLineaCreditoOutputCollection) ::) external;

declare function local:func($ConsultarFuenteOutputCollection as element() (:: schema-element(ns1:ConsultarFuenteOutputCollection) ::), 
                            $ConsultarLineaCreditoOutputCollection as element() (:: schema-element(ns2:ConsultarLineaCreditoOutputCollection) ::)) 
                            as element() (:: schema-element(ns3:ConsultarLineaCreditoFuenteResponse) ::) {
    <ns3:ConsultarLineaCreditoFuenteResponse>
        <ns3:lineaCredito>
            <lin:idLineaCredito>{fn:data($ConsultarLineaCreditoOutputCollection/ns2:ConsultarLineaCreditoOutput/ns2:ID)}</lin:idLineaCredito>
            <lin:idContrato>{fn:data($ConsultarLineaCreditoOutputCollection/ns2:ConsultarLineaCreditoOutput/ns2:ID_CONTRATO)}</lin:idContrato>
            <lin:NumeroLineaCredito>{fn:data($ConsultarLineaCreditoOutputCollection/ns2:ConsultarLineaCreditoOutput/ns2:NUMERO_LINEA_CREDITO)}</lin:NumeroLineaCredito>
            <lin:Descripcion>{fn:data($ConsultarLineaCreditoOutputCollection/ns2:ConsultarLineaCreditoOutput/ns2:DESCRIPCION_LINEA)}</lin:Descripcion>
            <lin:Flexcube>
                <lin:id>{fn:data($ConsultarLineaCreditoOutputCollection/ns2:ConsultarLineaCreditoOutput/ns2:ID_FLEXCUBE)}</lin:id>
                <lin:idProductoFlexcube>{fn:data($ConsultarLineaCreditoOutputCollection/ns2:ConsultarLineaCreditoOutput/ns2:ID_PRODUCTO_FLEXCUBE)}</lin:idProductoFlexcube>
                <lin:idFlexcubePasivo></lin:idFlexcubePasivo>
            </lin:Flexcube>
            <lin:Fondo>{fn:data($ConsultarLineaCreditoOutputCollection/ns2:ConsultarLineaCreditoOutput/ns2:FONDO)}</lin:Fondo>
           <lin:IdTipoMonedaMontoLinea>{fn:data($ConsultarLineaCreditoOutputCollection/ns2:ConsultarLineaCreditoOutput/ns2:ID_TCA_TIPO_MONEDA)}</lin:IdTipoMonedaMontoLinea> <lin:MontoLinea>{fn:data($ConsultarLineaCreditoOutputCollection/ns2:ConsultarLineaCreditoOutput/ns2:MONTO_LINEA)}</lin:MontoLinea>            <lin:EsRevolvente>{fn:data($ConsultarLineaCreditoOutputCollection/ns2:ConsultarLineaCreditoOutput/ns2:ES_REVOLVENTE)}</lin:EsRevolvente>
            <lin:TratamientoDiasFeriados>{fn:data($ConsultarLineaCreditoOutputCollection/ns2:ConsultarLineaCreditoOutput/ns2:TRATAMIENTO_DIAS_FERIADOS)}</lin:TratamientoDiasFeriados>
            <lin:FechaValor>{fn:data($ConsultarLineaCreditoOutputCollection/ns2:ConsultarLineaCreditoOutput/ns2:FECHA_VALOR)}</lin:FechaValor>
            <lin:FechaVencimiento>{fn:data($ConsultarLineaCreditoOutputCollection/ns2:ConsultarLineaCreditoOutput/ns2:FECHA_VENCIMIENTO)}</lin:FechaVencimiento>
            <lin:CodigoPantallaLimite>{fn:data($ConsultarLineaCreditoOutputCollection/ns2:ConsultarLineaCreditoOutput/ns2:CODIGO_PLANTILLA_LIMITE)}</lin:CodigoPantallaLimite>
            <lin:CodigoSerialLimite>{fn:data($ConsultarLineaCreditoOutputCollection/ns2:ConsultarLineaCreditoOutput/ns2:CODIGO_SERIAL_LIMITE)}</lin:CodigoSerialLimite>
            <lin:CodigoAsignacion>{fn:data($ConsultarLineaCreditoOutputCollection/ns2:ConsultarLineaCreditoOutput/ns2:CODIGO_ASIGNACION)}</lin:CodigoAsignacion>
            <lin:DescripcionAsignacion>{fn:data($ConsultarLineaCreditoOutputCollection/ns2:ConsultarLineaCreditoOutput/ns2:DESCRIPCION_ASIGNACION)}</lin:DescripcionAsignacion>
            <lin:CondicionEspecial>{fn:data($ConsultarLineaCreditoOutputCollection/ns2:ConsultarLineaCreditoOutput/ns2:CONDICION_ESPECIAL)}</lin:CondicionEspecial>
            <lin:FechaRegistro>{fn:data($ConsultarLineaCreditoOutputCollection/ns2:ConsultarLineaCreditoOutput/ns2:FECHA_REGISTRO)}</lin:FechaRegistro>
            <lin:Estado>{fn:data($ConsultarLineaCreditoOutputCollection/ns2:ConsultarLineaCreditoOutput/ns2:BAN_ESTATUS)}</lin:Estado>
            <lin:EstadoMonto>{ if (
            
           string ($ConsultarLineaCreditoOutputCollection/ns2:ConsultarLineaCreditoOutput/ns2:BAN_ACT_MONTO)='1') then true()else  false()
            
            
            }</lin:EstadoMonto>
            <lin:MoverEntreMeses>{fn:data($ConsultarLineaCreditoOutputCollection/ns2:ConsultarLineaCreditoOutput/ns2:SE_PUEDE_MOVER_ENTRE_MESES)}</lin:MoverEntreMeses>
            {
                for $ConsultarFuenteOutput in $ConsultarFuenteOutputCollection/ns1:ConsultarFuenteOutput
                return 
                <lin:Fuente>
                    <lin:idFuente>{fn:data($ConsultarFuenteOutput/ns1:ID)}</lin:idFuente>
                    <lin:idLineaCredito>{fn:data($ConsultarFuenteOutput/ns1:ID_LINEA_CREDITO)}</lin:idLineaCredito>
                    <lin:idLineaPasiva>{fn:data($ConsultarFuenteOutput/ns1:ID_LINEA_PASIVA)}</lin:idLineaPasiva>
                    <lin:Descripcion>{fn:data($ConsultarFuenteOutput/ns1:DESCRIPCION)}</lin:Descripcion>
                    <lin:FechaObtenido>{fn:data($ConsultarFuenteOutput/ns1:FECHA_OBTENIDO)}</lin:FechaObtenido>
                    <lin:MontoAsignado>{fn:data($ConsultarFuenteOutput/ns1:MONTO_ASIGNADO)}</lin:MontoAsignado>
                    <lin:NumeroAsignacion>{fn:data($ConsultarFuenteOutput/ns1:NUMERO_ASIGNACION)}</lin:NumeroAsignacion>
                    <lin:Comentario>{fn:data($ConsultarFuenteOutput/ns1:COMENTARIO)}</lin:Comentario>
                    <lin:idContrato>{fn:data($ConsultarFuenteOutput/ns1:ID_CONTRATO)}</lin:idContrato>
                    <lin:FechaRegistro>{fn:data($ConsultarFuenteOutput/ns1:FECHA_REGISTRO)}</lin:FechaRegistro>
                    <lin:Estado>{fn:data($ConsultarFuenteOutput/ns1:BAN_ESTATUS)}</lin:Estado>
                    <lin:esExterna></lin:esExterna></lin:Fuente>
            }</ns3:lineaCredito>
        <ns3:Resultado>
            <res:result>OK</res:result>
            {if (string($ConsultarLineaCreditoOutputCollection/ns2:ConsultarLineaCreditoOutput/ns2:ID)='')
            then
            <res:message>No existen datos</res:message>
            else 
            if (string($ConsultarLineaCreditoOutputCollection/ns2:ConsultarLineaCreditoOutput/ns2:CODIGO_ASIGNACION)='')
            then 
             <res:message>Consulta realizada existosamente</res:message>
             else 
              <res:message>Línea de crédito Propagada</res:message>
             
            }
            
        </ns3:Resultado>
    </ns3:ConsultarLineaCreditoFuenteResponse>
};

local:func($ConsultarFuenteOutputCollection, $ConsultarLineaCreditoOutputCollection)