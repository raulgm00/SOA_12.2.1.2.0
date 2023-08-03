xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarCargoByProducto_DB";
(:: import schema at "../XSD/ConsultarCargoByProducto_DB.xsd" ::)

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare variable $ConsultarCargoByProducto_DBOutputCollection as element() (:: schema-element(ns1:ConsultarCargoByProducto_DBOutputCollection) ::) external;

declare function local:func($ConsultarCargoByProducto_DBOutputCollection as element() (:: schema-element(ns1:ConsultarCargoByProducto_DBOutputCollection) ::)) as element() (:: schema-element(ns2:ConsultarCargoByProductoResponse) ::) {
    <ns2:ConsultarCargoByProductoResponse>
    {for $Cargo in $ConsultarCargoByProducto_DBOutputCollection/ns1:ConsultarCargoByProducto_DBOutput
      return
        <ns2:Cargo>
            <cat:Id></cat:Id>
            <cat:Descripcion>{fn:data($Cargo/ns1:DESCRIPCION_COMISION)}</cat:Descripcion>
            <cat:DescripcionCorta></cat:DescripcionCorta>
            <cat:estatus></cat:estatus>
            <cat:codigoExterno>{fn:data($Cargo/ns1:COMPONENTE)}</cat:codigoExterno>
            <des:FechaRegistro></des:FechaRegistro>
            <des:Status></des:Status>
            <des:SoloLectura></des:SoloLectura>
            <des:TCC>
                <mat:id>{fn:data($Cargo/ns1:ID_TCA_COMISION)}</mat:id>
            </des:TCC>
        </ns2:Cargo>
        }
    <ns2:Resultado>
        <res:result>OK</res:result>
        <res:message>{
            if (count($ConsultarCargoByProducto_DBOutputCollection/ns1:ConsultarCargoByProducto_DBOutput) > 0)
            then 'Consulta Exitosa'
            else 'No existen registros'
            }
        </res:message>
    </ns2:Resultado>
    </ns2:ConsultarCargoByProductoResponse>
};

local:func($ConsultarCargoByProducto_DBOutputCollection)
