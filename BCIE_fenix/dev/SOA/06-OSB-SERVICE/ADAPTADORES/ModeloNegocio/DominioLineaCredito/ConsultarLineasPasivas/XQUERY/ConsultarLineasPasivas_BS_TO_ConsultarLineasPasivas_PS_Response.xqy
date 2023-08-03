xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarLineasPasivas";
(:: import schema at "../XSD/ConsultarLineasPasivas.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare variable $ConsultarLineasPasivasOutputCollection as element() (:: schema-element(ns1:ConsultarLineasPasivasOutputCollection) ::) external;
declare variable $index as xs:integer external;

declare function local:func($ConsultarLineasPasivasOutputCollection as element() (:: schema-element(ns1:ConsultarLineasPasivasOutputCollection) ::), 
                            $index as xs:integer) 
                            as element() (:: schema-element(ns2:ConsultarLineasPasivasResponse) ::) {
    <ns2:ConsultarLineasPasivasResponse>
    {
    for $lineaPasiva in $ConsultarLineasPasivasOutputCollection/ns1:ConsultarLineasPasivasOutput
    return
        <ns2:Fuente>
            <lin:idFuente></lin:idFuente>
            <lin:idLineaCredito></lin:idLineaCredito>
            <lin:idLineaPasiva>{fn:data($lineaPasiva/ns1:ID)}</lin:idLineaPasiva>
            <lin:codigoLineaPasiva> </lin:codigoLineaPasiva>
            <lin:idFacturadorLineaPasiva>{fn:data($lineaPasiva/ns1:CODIGO_CLIENTE)}</lin:idFacturadorLineaPasiva>
            <lin:idFondo>{fn:data($lineaPasiva/ns1:CODIGO_FONDO)}</lin:idFondo>
            <lin:Descripcion>{fn:data($lineaPasiva/ns1:DESCRIPCION)}</lin:Descripcion>
            <lin:FechaObtenido></lin:FechaObtenido>
            <lin:MontoAsignado></lin:MontoAsignado>
            <lin:montoDisponible>{fn:data($lineaPasiva/ns1:MONTO_DISPONIBLE)}</lin:montoDisponible>
            <lin:Monto>
                <com:tipo>
                    <cat:Id></cat:Id>
                    <cat:Descripcion></cat:Descripcion>
                    <cat:DescripcionCorta>DISPONIBLE</cat:DescripcionCorta>
                    <cat:estatus></cat:estatus>
                    <cat:codigoExterno></cat:codigoExterno>
                </com:tipo>
                <com:importe>{fn:data($lineaPasiva/ns1:MONTO_DISPONIBLE)}</com:importe>
                <com:moneda>
                    <cat:Id></cat:Id>
                    <cat:Descripcion></cat:Descripcion>
                    <cat:DescripcionCorta></cat:DescripcionCorta>
                    <cat:estatus></cat:estatus>
                    <cat:codigoExterno>{fn:data($lineaPasiva/ns1:MONEDA)}</cat:codigoExterno>
                </com:moneda>
            </lin:Monto>
            <lin:NumeroAsignacion></lin:NumeroAsignacion>
            <lin:Comentario></lin:Comentario>
            <lin:idContrato></lin:idContrato>
            <lin:FechaRegistro></lin:FechaRegistro>
            <lin:Estado></lin:Estado>
            <lin:esExterna>{if(fn:string($lineaPasiva/ns1:ES_EXTERNO)='1')then fn:true()else(fn:false())}</lin:esExterna>
        </ns2:Fuente>
      }
    </ns2:ConsultarLineasPasivasResponse>
};

local:func($ConsultarLineasPasivasOutputCollection, $index)
