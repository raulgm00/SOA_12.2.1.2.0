xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)

declare namespace are="http://org/bcie/ws/middle/ARE/ARE.wsdl";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare variable $ObtenerDisponibilidadARERequest as element() (:: schema-element(ns1:ObtenerDisponibilidadARERequest) ::) external;

declare function local:func($ObtenerDisponibilidadARERequest as element() (:: schema-element(ns1:ObtenerDisponibilidadARERequest) ::))as element() (:: element(are:obtenerDisponibilidad) ::) {
   <are:obtenerDisponibilidad>
			<!-- codigo asignacion de la Linea Pasiva-->
			<numeroasignacion>{fn:data($ObtenerDisponibilidadARERequest/ns1:Fuente/lin:NumeroAsignacion)}</numeroasignacion>
    </are:obtenerDisponibilidad>
};

local:func($ObtenerDisponibilidadARERequest)
