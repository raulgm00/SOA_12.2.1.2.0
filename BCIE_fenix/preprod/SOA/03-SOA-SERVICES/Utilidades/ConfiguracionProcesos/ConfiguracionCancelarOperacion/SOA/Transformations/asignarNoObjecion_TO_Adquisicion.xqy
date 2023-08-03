xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace adq="http://www.bcie.org/AdquisicionMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioOperacion/Adquisicion/V1/Schema/AdquisicionMO.xsd" ::)
declare namespace con="http://www.bcie.org/ConfiguracionProcesosMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/Utilidades/ConfiguracionProcesos/V1/Schema/ConfiguracionProcesosMO.xsd" ::)

declare namespace con3 = "http://www.bcie.org/CondicionBO";

declare namespace con2 = "http://www.bcie.org/ContratoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

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

declare namespace adq1 = "http://www.bcie.org/AdquisicionBO";

declare namespace ter = "http://www.bcie.org/TerminoBO";

declare namespace con1 = "http://www.bcie.org/ConfiguracionProcesosBO";

declare variable $outConsultarNoObjecion.response as element() (:: schema-element(adq:ConsultarNoObjecionResponse) ::) external;

declare function local:funcAsignarnoobjecion_to_adquisicion($outConsultarNoObjecion.response as element() (:: schema-element(adq:ConsultarNoObjecionResponse) ::)) as element() (:: schema-element(con:configuracionCancelarOperacionResponse) ::) {
    <con:configuracionCancelarOperacionResponse>
        <con:configuracionCancelarOperacion>
            <con1:Adquisiciones>
                <adq1:Adquisicion>
       {
            for $noObjecion in $outConsultarNoObjecion.response/adq:NoObjecion
            return 
            <adq1:noObjecion>
              {
              $noObjecion/*
              } 
            </adq1:noObjecion>
        }
                </adq1:Adquisicion>
            </con1:Adquisiciones>
        </con:configuracionCancelarOperacion>
    </con:configuracionCancelarOperacionResponse>
};

local:funcAsignarnoobjecion_to_adquisicion($outConsultarNoObjecion.response)
