xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace con="http://www.bcie.org/ConfiguracionProcesosMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/Utilidades/ConfiguracionProcesos/V1/Schema/ConfiguracionProcesosMO.xsd" ::)
declare namespace des="http://www.bcie.org/DesembolsoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)

declare namespace com = "http://www.bcie.org/ComisionBO";

declare namespace ter = "http://www.bcie.org/TerminoBO";

declare namespace con1 = "http://www.bcie.org/ConfiguracionProcesosBO";

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace dec = "http://www.bcie.org/DeclaracionJuradaBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace imp = "http://www.bcie.org/ImplementacionPctBO";

declare namespace adq = "http://www.bcie.org/AdquisicionBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace des1 = "http://www.bcie.org/DesembolsoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare namespace reg = "http://www.bcie.org/ReglaBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace cre = "http://www.bcie.org/CrearBitacoraProcesoBO";

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace com1 = "http://www.bcie.org/CommonBO";

declare namespace con2 = "http://www.bcie.org/CondicionBO";

declare namespace con3 = "http://www.bcie.org/ContratoBO";

declare variable $outSolicitudDesembolso.response as element() (:: schema-element(des:ConsultarSolicitudDesembolsoResponse) ::) external;

declare function local:funcXq_solicituddesembolso_to_response($outSolicitudDesembolso.response as element() (:: schema-element(des:ConsultarSolicitudDesembolsoResponse) ::)) as element() (:: schema-element(con:configuracionReasignarOperacionResponse) ::) {
    <con:configuracionReasignarOperacionResponse>
        <con:configuracionReasignarOperacion>
                <con1:SolicitudDesembolso>
                {
                for $solicitud in $outSolicitudDesembolso.response/des:SolicitudDesembolso
                return
                   <des1:SolicitudDesembolso>{fn:data($solicitud/des1:idDesembolso)}</des1:SolicitudDesembolso>
                }
                </con1:SolicitudDesembolso>
        </con:configuracionReasignarOperacion>
    </con:configuracionReasignarOperacionResponse>
};

local:funcXq_solicituddesembolso_to_response($outSolicitudDesembolso.response)
