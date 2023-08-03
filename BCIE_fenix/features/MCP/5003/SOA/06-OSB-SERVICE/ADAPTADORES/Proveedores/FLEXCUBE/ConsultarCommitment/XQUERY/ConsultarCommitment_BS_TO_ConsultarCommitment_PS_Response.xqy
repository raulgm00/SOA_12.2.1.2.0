xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ConsultarCommitment";
(:: import schema at "../XSD/ConsultarCommitment_sp.xsd" ::)

declare namespace con = "http://www.bcie.org/CondicionBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace ter = "http://www.bcie.org/TerminoBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare variable $consultarCommitmentResponse as element() (:: schema-element(ns1:OutputParameters) ::) external;
declare variable $ConsultarCommitmentRequestType as element() (:: schema-element(ns2:ConsultarCommitmentRequest) ::) external;

declare function local:func($consultarCommitmentResponse as element() (:: schema-element(ns1:OutputParameters) ::), 
                            $ConsultarCommitmentRequestType as element() (:: schema-element(ns2:ConsultarCommitmentRequest) ::)) 
                            as element() (:: schema-element(ns2:ConsultarCommitmentResponse) ::) {
   <ns2:ConsultarCommitmentResponse>
        <ns2:LineaCredito>
            {
                if ($ConsultarCommitmentRequestType/ns2:LineaCredito/lin:idLineaCredito)
                then <lin:idLineaCredito>{fn:data($ConsultarCommitmentRequestType/ns2:LineaCredito/lin:idLineaCredito)}</lin:idLineaCredito>
                else ()
            }
            <lin:idContrato></lin:idContrato>
            <lin:NumeroLineaCredito>{fn:data($ConsultarCommitmentRequestType/ns2:LineaCredito/lin:NumeroLineaCredito)}</lin:NumeroLineaCredito>
            <lin:Descripcion>{fn:data($consultarCommitmentResponse/ns1:DESCRIPCION_LINEA)}</lin:Descripcion>
            <lin:Flexcube>
                <lin:id>{fn:data($consultarCommitmentResponse/ns1:NUMERO_CONTRATO)}</lin:id>
                <lin:idProductoFlexcube></lin:idProductoFlexcube>
                <lin:idFlexcubePasivo></lin:idFlexcubePasivo>
            </lin:Flexcube>
            <lin:Fondo>{fn:data($consultarCommitmentResponse/ns1:FONDO)}</lin:Fondo>
            <lin:FechaMaximaDesembolso>{fn:data($consultarCommitmentResponse/ns1:FECHA_MAX_DESEMBOLSAR)}</lin:FechaMaximaDesembolso>
            <lin:MontoLinea>{fn:data($consultarCommitmentResponse/ns1:MONTO_APROBADO)}</lin:MontoLinea>
            <lin:Monto>
                <com:tipo>
                    <cat:Id></cat:Id>
                    <cat:Descripcion></cat:Descripcion>
                    <cat:DescripcionCorta>DISPONIBLE</cat:DescripcionCorta>
                    <cat:estatus></cat:estatus>
                    <cat:codigoExterno></cat:codigoExterno>
                </com:tipo>
                <com:importe>{fn:data($consultarCommitmentResponse/ns1:DISPONIBILIDAD)}</com:importe>
                <com:moneda>
                    <cat:Id></cat:Id>
                    <cat:Descripcion></cat:Descripcion>
                    <cat:DescripcionCorta></cat:DescripcionCorta>
                    <cat:estatus></cat:estatus>
                    <cat:codigoExterno>{fn:data($consultarCommitmentResponse/ns1:MONEDA)}</cat:codigoExterno>
                </com:moneda>
            </lin:Monto>
            <lin:LineaCreditoFlexcube>
                <lin:numeroLineaCreditoResp_out>{fn:data($consultarCommitmentResponse/ns1:NUMERO_LINEA_CREDITO_RESP)}</lin:numeroLineaCreditoResp_out>
                <lin:NumeroContrato>{fn:data($consultarCommitmentResponse/ns1:NUMERO_CONTRATO)}</lin:NumeroContrato>
                <lin:DescripcionLinea>{fn:data($consultarCommitmentResponse/ns1:DESCRIPCION_LINEA)}</lin:DescripcionLinea>
                <lin:Moneda>{fn:data($consultarCommitmentResponse/ns1:MONEDA)}</lin:Moneda>
                <lin:MontoAprobado>{fn:data($consultarCommitmentResponse/ns1:MONTO_APROBADO)}</lin:MontoAprobado>
                <lin:CodigoCliente>{fn:data($consultarCommitmentResponse/ns1:CODIGO_CLIENTE)}</lin:CodigoCliente>
                <lin:Revolvencia>{fn:data($consultarCommitmentResponse/ns1:REVOLVENCIA)}</lin:Revolvencia>
                <lin:Fondo>{fn:data($consultarCommitmentResponse/ns1:FONDO)}</lin:Fondo>
                <lin:LineaFinanciera>{fn:data($consultarCommitmentResponse/ns1:LINEA_FINANCIERA)}</lin:LineaFinanciera>
                <lin:Destino>{fn:data($consultarCommitmentResponse/ns1:DESTINO)}</lin:Destino>
                <lin:TipoRecurso>{fn:data($consultarCommitmentResponse/ns1:TIPO_RECURSO)}</lin:TipoRecurso>
                <lin:Pais>{fn:data($consultarCommitmentResponse/ns1:PAIS)}</lin:Pais>
                <lin:ActividadEconomica>{fn:data($consultarCommitmentResponse/ns1:ACTIVIDAD_ECONOMICA)}</lin:ActividadEconomica>
                <lin:SectorInstitucional>{fn:data($consultarCommitmentResponse/ns1:SECTOR_INSTITUCIONAL)}</lin:SectorInstitucional>
                <lin:Ejecutivo>{fn:data($consultarCommitmentResponse/ns1:EJECUTIVO)}</lin:Ejecutivo>
                <lin:EjeEstrategico>{fn:data($consultarCommitmentResponse/ns1:EJE_ESTRATEGICO)}</lin:EjeEstrategico>
                <lin:AreaFocalizacion>{fn:data($consultarCommitmentResponse/ns1:AREA_FOCALIZACION)}</lin:AreaFocalizacion>
                <lin:ObjeticoEstrategico>{fn:data($consultarCommitmentResponse/ns1:OBJETICO_ESTRATEGICO)}</lin:ObjeticoEstrategico>
                <lin:PlantillaLimite>{fn:data($consultarCommitmentResponse/ns1:PLANTILLA_LIMITE)}</lin:PlantillaLimite>
                <lin:SerialLimite>{fn:data($consultarCommitmentResponse/ns1:SERIAL_LIMITE)}</lin:SerialLimite>
                <lin:Saldo>{fn:data($consultarCommitmentResponse/ns1:SALDO)}</lin:Saldo>
                <lin:Disponibilidad>{fn:data($consultarCommitmentResponse/ns1:DISPONIBILIDAD)}</lin:Disponibilidad>
                <lin:DisponibilidadIfacil>{fn:data($consultarCommitmentResponse/ns1:DISPONIBILIDAD_IFACIL)}</lin:DisponibilidadIfacil>
                <lin:TieneCondEspeciales>{fn:data($consultarCommitmentResponse/ns1:TIENE_COND_ESPECIALES)}</lin:TieneCondEspeciales>
                <lin:DescCondEspeciales>{fn:data($consultarCommitmentResponse/ns1:DESC_COND_ESPECIALES)}</lin:DescCondEspeciales>
                <lin:FechaMaxDesembolsar>{fn:data($consultarCommitmentResponse/ns1:FECHA_MAX_DESEMBOLSAR)}</lin:FechaMaxDesembolsar>
                <lin:NumeroDesembolsos>{fn:data($consultarCommitmentResponse/ns1:NUMERO_DESEMBOLSOS)}</lin:NumeroDesembolsos>
            </lin:LineaCreditoFlexcube>
            <lin:HerramientaCE>
                <her:ActividadEconomica>
                    <cat:Id></cat:Id>
                    <cat:Descripcion></cat:Descripcion>
                    <cat:DescripcionCorta></cat:DescripcionCorta>
                    <cat:estatus></cat:estatus>
                    <cat:codigoExterno>{fn:data($consultarCommitmentResponse/ns1:ACTIVIDAD_ECONOMICA)}</cat:codigoExterno>
                </her:ActividadEconomica>
                <her:EjeEstrategico>
                    <cat:Id></cat:Id>
                    <cat:Descripcion></cat:Descripcion>
                    <cat:DescripcionCorta></cat:DescripcionCorta>
                    <cat:estatus></cat:estatus>
                    <cat:codigoExterno>{fn:data($consultarCommitmentResponse/ns1:EJE_ESTRATEGICO)}</cat:codigoExterno>
                </her:EjeEstrategico>
                <her:AreaFocalizacion>
                    <cat:Id></cat:Id>
                    <cat:Descripcion></cat:Descripcion>
                    <cat:DescripcionCorta></cat:DescripcionCorta>
                    <cat:estatus></cat:estatus>
                    <cat:codigoExterno>{fn:data($consultarCommitmentResponse/ns1:AREA_FOCALIZACION)}</cat:codigoExterno>
                </her:AreaFocalizacion>
            </lin:HerramientaCE>
        </ns2:LineaCredito>
        <ns2:Resultado> 
           <res:result>{if (fn:data($consultarCommitmentResponse/ns1:CODIGO_RESULTADO = '0'))then 'OK'
           else 'ERROR'}</res:result>
          <res:message>{if (fn:string-length(fn:data($consultarCommitmentResponse/ns1:MENSAJE))> 0)
            then(fn:data($consultarCommitmentResponse/ns1:MENSAJE))
            else 'Consulta Exitosa'}</res:message>
        </ns2:Resultado>
    </ns2:ConsultarCommitmentResponse>
};

local:func($consultarCommitmentResponse, $ConsultarCommitmentRequestType)