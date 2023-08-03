xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns3="http://www.bcie.org/ConfiguracionProcesosMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/Utilidades/ConfiguracionProcesos/V1/Schema/ConfiguracionProcesosMO.xsd" ::)
declare namespace ns2="http://www.bcie.org/CrearBitacoraProcesoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/Utilidades/BitacoraProceso/V1/Schema/BitacoraProcesoMO.xsd" ::)

declare namespace ns1="http://www.w3.org/2005/xquery-local-functions";

declare namespace con = "http://www.bcie.org/ConfiguracionProcesosBO";

declare namespace con2 = "http://www.bcie.org/CondicionBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace adq = "http://www.bcie.org/AdquisicionBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace imp = "http://www.bcie.org/ImplementacionPctBO";

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace cre = "http://www.bcie.org/CrearBitacoraProcesoBO";

declare namespace dec = "http://www.bcie.org/DeclaracionJuradaBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace reg = "http://www.bcie.org/ReglaBO";

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare namespace ter = "http://www.bcie.org/TerminoBO";

declare namespace con1 = "http://www.bcie.org/ContratoBO";

declare variable $consultarBitacoraProcesoResponse as element() (:: schema-element(ns2:consultarBitacoraProcesoResponse) ::) external;

declare function ns1:func($consultarBitacoraProcesoResponse as element() (:: schema-element(ns2:consultarBitacoraProcesoResponse) ::)) as element() (:: schema-element(ns3:configuracionCancelarOperacionResponse) ::) {
    <ns3:configuracionCancelarOperacionResponse>
        <ns3:configuracionCancelarOperacion>
           
            <con:RegistrarComision>
            {
            
            for $instancia in $consultarBitacoraProcesoResponse/ns2:Bitacora
             return
             
            <cre:InstanciaProceso>{fn:data($instancia/cre:instanciaProceso)}</cre:InstanciaProceso>
            }
            </con:RegistrarComision>
        </ns3:configuracionCancelarOperacion>
    </ns3:configuracionCancelarOperacionResponse>
};

ns1:func($consultarBitacoraProcesoResponse)
