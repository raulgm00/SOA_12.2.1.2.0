xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/SeguridadMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioSeguridad/Schema/SeguridadMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/tieneAcceso";
(:: import schema at "../XSD/tieneAcceso_sp.xsd" ::)

declare variable $tieneAccesoRequest as element() (:: schema-element(ns1:TieneAccesoRequest) ::) external;

declare function local:func($tieneAccesoRequest as element() (:: schema-element(ns1:TieneAccesoRequest) ::)) as element() (:: schema-element(ns2:InputParameters) ::) {
    <ns2:InputParameters>
        <ns2:PVUSER>{fn:data($tieneAccesoRequest/ns1:PVUSER)}</ns2:PVUSER>
        <ns2:PVAPPLICATION>{fn:data($tieneAccesoRequest/ns1:PVAPPLICATION)}</ns2:PVAPPLICATION>
        <ns2:PVWINDOW>{fn:data($tieneAccesoRequest/ns1:PVWINDOW)}</ns2:PVWINDOW>
        <ns2:PVCONTROL>{fn:data($tieneAccesoRequest/ns1:PVCONTROL)}</ns2:PVCONTROL>
        <ns2:N1>{fn:data($tieneAccesoRequest/ns1:N1)}</ns2:N1>
        <ns2:V1>{fn:data($tieneAccesoRequest/ns1:V1)}</ns2:V1>
        <ns2:N2>{fn:data($tieneAccesoRequest/ns1:N2)}</ns2:N2>
        <ns2:V2>{fn:data($tieneAccesoRequest/ns1:V2)}</ns2:V2>
        <ns2:N3>{fn:data($tieneAccesoRequest/ns1:N3)}</ns2:N3>
        <ns2:V3>{fn:data($tieneAccesoRequest/ns1:V3)}</ns2:V3>
        <ns2:N4>{fn:data($tieneAccesoRequest/ns1:N4)}</ns2:N4>
        <ns2:V4>{fn:data($tieneAccesoRequest/ns1:V4)}</ns2:V4>
        <ns2:N5>{fn:data($tieneAccesoRequest/ns1:N5)}</ns2:N5>
        <ns2:V5>{fn:data($tieneAccesoRequest/ns1:V5)}</ns2:V5> 
    </ns2:InputParameters>
};

local:func($tieneAccesoRequest)