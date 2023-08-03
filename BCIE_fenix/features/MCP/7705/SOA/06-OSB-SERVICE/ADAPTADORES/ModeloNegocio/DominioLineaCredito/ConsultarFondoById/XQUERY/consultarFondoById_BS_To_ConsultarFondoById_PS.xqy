xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace linMO="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarFondoById_BS";
(:: import schema at "../XSD/ConsultarFondoById_BS.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ConsultarFondoCollection as element() (:: schema-element(ns1:ConsultarFondoById_BSOutputCollection) ::) external;

declare function local:Response($ConsultarFondoCollection as element() (:: schema-element(ns1:ConsultarFondoById_BSOutputCollection) ::)) as element() (:: schema-element(linMO:ConsultarFondoResponse) ::) {
    <linMO:ConsultarFondoResponse>
    {
      for $fondo in $ConsultarFondoCollection/ns1:ConsultarFondoById_BSOutput
      where $fondo/ns1:MIS_CLASS eq $fondo/ns1:MIS_CLASS
      return 
        <linMO:fondo>
            <cat:Id>{fn:data($fondo/ns1:MIS_CODE)}</cat:Id>
            <cat:Descripcion>{fn:data($fondo/ns1:CODE_DESC)}</cat:Descripcion>
            <cat:DescripcionCorta>{fn:data($fondo/ns1:MIS_CLASS)}</cat:DescripcionCorta>
            <cat:estatus>{if($fondo/ns1:ACTIVE = 'A') then true() else false()}</cat:estatus>
            <cat:codigoExterno>{fn:data($fondo/ns1:MIS_CODE)}</cat:codigoExterno>
        </linMO:fondo>
    }
        <linMO:Resultado>
            <res:result>OK</res:result>
            <res:message>Consultra Exitosa</res:message>
        </linMO:Resultado>
    </linMO:ConsultarFondoResponse>
};

local:Response($ConsultarFondoCollection)
