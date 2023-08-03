xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace m="http://org/bcie/ws/middle/FLEXCUBE.wsdl";

declare namespace ns2="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)

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

declare variable $ConsultarCommitmentRequestType as element() (:: element(*, ns2:ConsultarCommitmentRequestType) ::) external;
declare variable $consultarCommitmentResponse as element() (:: element(m:consultarCommitmentResponse) ::) external;

declare function local:func($ConsultarCommitmentRequestType as element() (:: element(*, ns2:ConsultarCommitmentRequestType) ::), 
                            $consultarCommitmentResponse as element() (:: element(m:consultarCommitmentResponse) ::)) 
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
            <lin:Descripcion>{fn:data($consultarCommitmentResponse/descripcionLinea_out)}</lin:Descripcion>
            <lin:Flexcube>
                <lin:id>{fn:data($consultarCommitmentResponse/numeroContrato_out)}</lin:id>
                <lin:idProductoFlexcube></lin:idProductoFlexcube>
                <lin:idFlexcubePasivo></lin:idFlexcubePasivo>
            </lin:Flexcube>
            <lin:Fondo>{fn:data($consultarCommitmentResponse/fondo_out)}</lin:Fondo>
            <lin:FechaMaximaDesembolso>{fn:data($consultarCommitmentResponse/fechaMaxDesembolsar_out)}</lin:FechaMaximaDesembolso>
            <lin:MontoLinea>{fn:data($consultarCommitmentResponse/montoAprobado_out)}</lin:MontoLinea>
            <lin:Monto>
                <com:tipo>
                    <cat:Id></cat:Id>
                    <cat:Descripcion></cat:Descripcion>
                    <cat:DescripcionCorta>DISPONIBLE</cat:DescripcionCorta>
                    <cat:estatus></cat:estatus>
                    <cat:codigoExterno></cat:codigoExterno>
                </com:tipo>
                <com:importe>{fn:data($consultarCommitmentResponse/disponibilidad_out)}</com:importe>
                <com:moneda>
                    <cat:Id></cat:Id>
                    <cat:Descripcion></cat:Descripcion>
                    <cat:DescripcionCorta></cat:DescripcionCorta>
                    <cat:estatus></cat:estatus>
                    <cat:codigoExterno>{fn:data($consultarCommitmentResponse/moneda_out)}</cat:codigoExterno>
                </com:moneda>
            </lin:Monto>
            <lin:LineaCreditoFlexcube>
                <lin:numeroLineaCreditoResp_out>{fn:data($consultarCommitmentResponse/numeroLineaCreditoResp_out)}</lin:numeroLineaCreditoResp_out>
                <lin:NumeroContrato>{fn:data($consultarCommitmentResponse/numeroContrato_out)}</lin:NumeroContrato>
                <lin:DescripcionLinea>{fn:data($consultarCommitmentResponse/descripcionLinea_out)}</lin:DescripcionLinea>
                <lin:Moneda>{fn:data($consultarCommitmentResponse/moneda_out)}</lin:Moneda>
                <lin:MontoAprobado>{fn:data($consultarCommitmentResponse/montoAprobado_out)}</lin:MontoAprobado>
                <lin:CodigoCliente>{fn:data($consultarCommitmentResponse/codigoCliente_out)}</lin:CodigoCliente>
                <lin:Revolvencia>{fn:data($consultarCommitmentResponse/revolvencia_out)}</lin:Revolvencia>
                <lin:Fondo>{fn:data($consultarCommitmentResponse/fondo_out)}</lin:Fondo>
                <lin:LineaFinanciera>{fn:data($consultarCommitmentResponse/lineaFinanciera_out)}</lin:LineaFinanciera>
                <lin:Destino>{fn:data($consultarCommitmentResponse/destino_out)}</lin:Destino>
                <lin:TipoRecurso>{fn:data($consultarCommitmentResponse/tipoRecurso_out)}</lin:TipoRecurso>
                <lin:Pais>{fn:data($consultarCommitmentResponse/pais_out)}</lin:Pais>
                <lin:ActividadEconomica>{fn:data($consultarCommitmentResponse/actividadEconomica_out)}</lin:ActividadEconomica>
                <lin:SectorInstitucional>{fn:data($consultarCommitmentResponse/sectorInstitucional_out)}</lin:SectorInstitucional>
                <lin:Ejecutivo>{fn:data($consultarCommitmentResponse/ejecutivo_out)}</lin:Ejecutivo>
                <lin:EjeEstrategico>{fn:data($consultarCommitmentResponse/ejeEstrategico_out)}</lin:EjeEstrategico>
                <lin:AreaFocalizacion>{fn:data($consultarCommitmentResponse/areaFocalizacion_out)}</lin:AreaFocalizacion>
                <lin:ObjeticoEstrategico>{fn:data($consultarCommitmentResponse/objeticoEstrategico_out)}</lin:ObjeticoEstrategico>
                <lin:PlantillaLimite>{fn:data($consultarCommitmentResponse/plantillaLimite_out)}</lin:PlantillaLimite>
                <lin:SerialLimite>{fn:data($consultarCommitmentResponse/serialLimite_out)}</lin:SerialLimite>
                <lin:Saldo>{fn:data($consultarCommitmentResponse/saldo_out)}</lin:Saldo>
                <lin:Disponibilidad>{fn:data($consultarCommitmentResponse/disponibilidad_out)}</lin:Disponibilidad>
                <lin:DisponibilidadIfacil>{fn:data($consultarCommitmentResponse/disponibilidadIfacil_out)}</lin:DisponibilidadIfacil>
                <lin:TieneCondEspeciales>{fn:data($consultarCommitmentResponse/tieneCondEspeciales_out)}</lin:TieneCondEspeciales>
                <lin:DescCondEspeciales>{fn:data($consultarCommitmentResponse/descCondEspeciales_out)}</lin:DescCondEspeciales>
                <lin:FechaMaxDesembolsar>{fn:data($consultarCommitmentResponse/fechaMaxDesembolsar_out)}</lin:FechaMaxDesembolsar>
                <lin:NumeroDesembolsos>{fn:data($consultarCommitmentResponse/numeroDesembolsos_out)}</lin:NumeroDesembolsos>
            </lin:LineaCreditoFlexcube>
            <lin:HerramientaCE>
                <her:ActividadEconomica>
                    <cat:Id></cat:Id>
                    <cat:Descripcion></cat:Descripcion>
                    <cat:DescripcionCorta></cat:DescripcionCorta>
                    <cat:estatus></cat:estatus>
                    <cat:codigoExterno>{fn:data($consultarCommitmentResponse/actividadEconomica_out)}</cat:codigoExterno>
                </her:ActividadEconomica>
                <her:EjeEstrategico>
                    <cat:Id></cat:Id>
                    <cat:Descripcion></cat:Descripcion>
                    <cat:DescripcionCorta></cat:DescripcionCorta>
                    <cat:estatus></cat:estatus>
                    <cat:codigoExterno>{fn:data($consultarCommitmentResponse/ejeEstrategico_out)}</cat:codigoExterno>
                </her:EjeEstrategico>
                <her:AreaFocalizacion>
                    <cat:Id></cat:Id>
                    <cat:Descripcion></cat:Descripcion>
                    <cat:DescripcionCorta></cat:DescripcionCorta>
                    <cat:estatus></cat:estatus>
                    <cat:codigoExterno>{fn:data($consultarCommitmentResponse/areaFocalizacion_out)}</cat:codigoExterno>
                </her:AreaFocalizacion>
            </lin:HerramientaCE>
        </ns2:LineaCredito>
        <ns2:Resultado> 
           <res:result>{if (fn:data($consultarCommitmentResponse/codigoResultado_out = 0))then 'OK'
           else 'ERROR'}</res:result>
          <res:message>{if (fn:string-length(fn:data($consultarCommitmentResponse/mensaje_out))> 0)
            then(fn:data($consultarCommitmentResponse/mensaje_out))
            else 'Consulta Exitosa'}</res:message>
        </ns2:Resultado>
    </ns2:ConsultarCommitmentResponse>
};

local:func($ConsultarCommitmentRequestType, $consultarCommitmentResponse)