xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://service.org.bcie.www/";
(:: import schema at "../../../CrearReporteZip/XSD/CrearReporteZip.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarConfiguracion";
(:: import schema at "../../../../../ModeloNegocio/Utilidades/ConsultarConfiguracion/XSD/ConsultarConfiguracion.xsd" ::)

declare variable $configuracion as element() (:: schema-element(ns2:ConsultarConfiguracionOutputCollection) ::) external;
declare variable $avisoXML as element() external;
declare variable $plantilla as xs:string external;

declare function local:func($configuracion as element() (:: schema-element(ns2:ConsultarConfiguracionOutputCollection) ::), 
                            $avisoXML as element(), 
                            $plantilla as xs:string) 
                            as element() (:: schema-element(ns1:generarReporte) ::) {
    <ns1:generarReporte>
        <rutaPlantilla>{fn:concat(fn:data($configuracion/ns2:ConsultarConfiguracionOutput/ns2:VALOR),$plantilla)}</rutaPlantilla>
        <contenidoXML>{fn-bea:serialize($avisoXML)}</contenidoXML>
        {
            let $formato := "docx"
            return 
                <formatosSalida>{$formato}</formatosSalida>
        }
        {
            let $formato := "pdf"
            return 
                <formatosSalida>{$formato}</formatosSalida>
        }
    </ns1:generarReporte>
};

local:func($configuracion, $avisoXML, $plantilla)
