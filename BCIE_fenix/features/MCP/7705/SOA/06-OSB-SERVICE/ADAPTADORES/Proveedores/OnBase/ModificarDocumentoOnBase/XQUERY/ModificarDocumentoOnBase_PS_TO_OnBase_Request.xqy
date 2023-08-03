xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://tempuri.org/";
(:: import schema at "../../WSDL/OnBase.wsdl" ::)
declare namespace ns1="http://www.bcie.org/DocumentoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDocumento/Documento/V1/Schema/DocumentoMO.xsd" ::)

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare variable $ModificarDocumentoOnBaseRequest as element() (:: schema-element(ns1:ModificarDocumentoOnBaseRequest) ::) external;

declare function local:func($ModificarDocumentoOnBaseRequest as element() (:: schema-element(ns1:ModificarDocumentoOnBaseRequest) ::)) as element() (:: schema-element(ns2:UpdateData) ::) {
    <ns2:UpdateData>
        {
            if ($ModificarDocumentoOnBaseRequest/ns1:Documentos/doc:Documento/doc:idExterno)
            then <ns2:documentHandle>{fn:data($ModificarDocumentoOnBaseRequest/ns1:Documentos/doc:Documento/doc:idExterno)}</ns2:documentHandle>
            else ()
        }
        <ns2:KeywordToUpdate>
            <ns2:Key>
                <ns2:name>Codigo  Intervencion</ns2:name>
                <ns2:value></ns2:value>
            </ns2:Key>
                        <ns2:Key>
                <ns2:name>Codigo Cliente</ns2:name>
                <ns2:value>{fn:data($ModificarDocumentoOnBaseRequest/ns1:Documentos/doc:Documento/doc:idOperacion)}</ns2:value>
            </ns2:Key>
                        <ns2:Key>
                <ns2:name>Codigo Proyecto</ns2:name>
                <ns2:value>{fn:data($ModificarDocumentoOnBaseRequest/ns1:Documentos/doc:Documento/doc:idOperacion)}</ns2:value>
            </ns2:Key>
                        <ns2:Key>
                <ns2:name>Estado</ns2:name>
                <ns2:value>Elegibilidad</ns2:value>
            </ns2:Key>
                        <ns2:Key>
                <ns2:name>Linea de Credito</ns2:name>
                <ns2:value xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:nil="true"/>
            </ns2:Key>
                        <ns2:Key>
                <ns2:name>Nombre Cliente</ns2:name>
                <ns2:value>{fn:data($ModificarDocumentoOnBaseRequest/ns1:Cliente/cli:razonSocial)}</ns2:value>
            </ns2:Key>
                        <ns2:Key>
                <ns2:name>Nombre Proyecto</ns2:name>
                <ns2:value>{fn:data($ModificarDocumentoOnBaseRequest/ns1:Operacion/ope:nombre)}</ns2:value>
            </ns2:Key>
                        <ns2:Key>
                <ns2:name>Numero Documento</ns2:name>
                <ns2:value></ns2:value>
            </ns2:Key>
              <ns2:Key>
                <ns2:name>Pais</ns2:name>
                <ns2:value>{fn:data($ModificarDocumentoOnBaseRequest/ns1:Cliente/cli:pais/cat:DescripcionCorta)}</ns2:value>
            </ns2:Key>
              <ns2:Key>
                <ns2:name>Periodo</ns2:name>
                <ns2:value>{fn:concat(fn:day-from-dateTime($ModificarDocumentoOnBaseRequest/ns1:Documentos/doc:Documento/doc:fechaRegistro),"/",fn:month-from-dateTime($ModificarDocumentoOnBaseRequest/ns1:Documentos/doc:Documento/doc:fechaRegistro),"/",fn:year-from-dateTime($ModificarDocumentoOnBaseRequest/ns1:Documentos/doc:Documento/doc:fechaRegistro))}</ns2:value>
            </ns2:Key>
             <ns2:Key>
                <ns2:name>Resumen</ns2:name>
                <ns2:value></ns2:value>
            </ns2:Key>
        </ns2:KeywordToUpdate>
        <ns2:Append>false</ns2:Append>
    </ns2:UpdateData>
};

local:func($ModificarDocumentoOnBaseRequest)
