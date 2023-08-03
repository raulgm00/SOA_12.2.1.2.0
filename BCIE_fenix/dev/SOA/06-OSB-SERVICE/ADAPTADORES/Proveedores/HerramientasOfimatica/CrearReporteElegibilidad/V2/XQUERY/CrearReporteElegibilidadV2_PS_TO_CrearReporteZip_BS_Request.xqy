xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://service.org.bcie.www/";
(:: import schema at "../../../CrearReporteZip/XSD/CrearReporteZip.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarConfiguracion";
(:: import schema at "../../../../../ModeloNegocio/Utilidades/ConsultarConfiguracion/XSD/ConsultarConfiguracion.xsd" ::)

declare variable $avisoXml as element() external;
declare variable $plantilla as xs:string external;
declare variable $configuracion as element() (:: schema-element(ns1:ConsultarConfiguracionOutputCollection) ::) external;

declare function local:func($avisoXml as element(), 
                            $plantilla as xs:string, 
                            $configuracion as element() (:: schema-element(ns1:ConsultarConfiguracionOutputCollection) ::)) 
                            as element() (:: schema-element(ns2:generarReporte) ::) {
    <ns2:generarReporte>
        <rutaPlantilla>{fn:concat(fn:data($configuracion/ns1:ConsultarConfiguracionOutput/ns1:VALOR),$plantilla)}</rutaPlantilla>
        <contenidoXML>{fn-bea:serialize($avisoXml)}</contenidoXML>
        {
            let $formato := "pdf"
            return 
                <formatosSalida>{$formato}</formatosSalida>
        }
    </ns2:generarReporte>
};

local:func($avisoXml, $plantilla, $configuracion)
