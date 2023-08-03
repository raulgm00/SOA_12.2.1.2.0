xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ConsultarOperacion";
(:: import schema at "../XSD/ConsultarOperacionBO_JSON.xsd" ::)
declare namespace ns1="http://www.bcie.org/ConsultarOperacionMO";
(:: import schema at "../../../../MDS/Canales/Votacion/ConsultarDetalleVotacion/V1/Schema/ConsultarOperacionMO.xsd" ::)

declare namespace con = "http://www.bcie.org/ConsultarOperacionBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare variable $responseConsultarOperacionMessage as element() (:: schema-element(ns1:responseConsultarOperacionMessage) ::) external;

declare function local:func($responseConsultarOperacionMessage as element() (:: schema-element(ns1:responseConsultarOperacionMessage) ::)) as element() (:: schema-element(ns2:ConsultarOperacionResponse) ::) {
    <ns2:ConsultarOperacionResponse>
        <ns2:detalleOperacion>
        {
        if (fn:string($responseConsultarOperacionMessage/ns1:detalleOperacion/con:operacion/con:idOperacion)!='') then 
            <ns2:operacion>
              <ns2:idOperacion>{fn:data($responseConsultarOperacionMessage/ns1:detalleOperacion/con:operacion/con:idOperacion)}</ns2:idOperacion>
              <ns2:nombreOperacion>{fn:data($responseConsultarOperacionMessage/ns1:detalleOperacion/con:operacion/con:nombreOperacion)}</ns2:nombreOperacion>
              <ns2:idProducto>{fn:data($responseConsultarOperacionMessage/ns1:detalleOperacion/con:operacion/con:idProducto)}</ns2:idProducto>
              <ns2:producto>{fn:data($responseConsultarOperacionMessage/ns1:detalleOperacion/con:operacion/con:producto)}</ns2:producto>
              <ns2:idCliente>{fn:data($responseConsultarOperacionMessage/ns1:detalleOperacion/con:operacion/con:idCliente)}</ns2:idCliente>
              <ns2:nombreCliente>{fn:data($responseConsultarOperacionMessage/ns1:detalleOperacion/con:operacion/con:nombreCliente)}</ns2:nombreCliente>
              <ns2:idPais>{fn:data($responseConsultarOperacionMessage/ns1:detalleOperacion/con:operacion/con:idPais)}</ns2:idPais>
              <ns2:pais>{fn:data($responseConsultarOperacionMessage/ns1:detalleOperacion/con:operacion/con:pais)}</ns2:pais>
              <ns2:idSector>{fn:data($responseConsultarOperacionMessage/ns1:detalleOperacion/con:operacion/con:idSector)}</ns2:idSector>
              <ns2:sector>{fn:data($responseConsultarOperacionMessage/ns1:detalleOperacion/con:operacion/con:sector)}</ns2:sector>
            </ns2:operacion>
            else()
            }
             {
            if (fn:string($responseConsultarOperacionMessage/ns1:detalleOperacion/con:cliente/con:idCliente) !='') then 
            <ns2:cliente>
                <ns2:idCliente>{fn:data($responseConsultarOperacionMessage/ns1:detalleOperacion/con:cliente/con:idCliente)}</ns2:idCliente>
                <ns2:NombreCliente>{fn:data($responseConsultarOperacionMessage/ns1:detalleOperacion/con:cliente/con:NombreCliente)}</ns2:NombreCliente>
                <ns2:idFacturador>{fn:data($responseConsultarOperacionMessage/ns1:detalleOperacion/con:cliente/con:idFacturador)}</ns2:idFacturador>
                <ns2:idOficina>{fn:data($responseConsultarOperacionMessage/ns1:detalleOperacion/con:cliente/con:idOficina)}</ns2:idOficina>
                <ns2:NombreOficina>{fn:data($responseConsultarOperacionMessage/ns1:detalleOperacion/con:cliente/con:NombreOficina)}</ns2:NombreOficina>
                <ns2:idScrVigente>{fn:data($responseConsultarOperacionMessage/ns1:detalleOperacion/con:cliente/con:idScrVigente)}</ns2:idScrVigente>
                <ns2:scrVigente>{fn:data($responseConsultarOperacionMessage/ns1:detalleOperacion/con:cliente/con:scrVigente)}</ns2:scrVigente>
                <ns2:idScrRecomendado>{fn:data($responseConsultarOperacionMessage/ns1:detalleOperacion/con:cliente/con:idScrRecomendado)}</ns2:idScrRecomendado>
                <ns2:scrRecomendado>{fn:data($responseConsultarOperacionMessage/ns1:detalleOperacion/con:cliente/con:scrRecomendado)}</ns2:scrRecomendado>
                <ns2:idSector>{fn:data($responseConsultarOperacionMessage/ns1:detalleOperacion/con:cliente/con:idSector)}</ns2:idSector>
                <ns2:nombreSector>{fn:data($responseConsultarOperacionMessage/ns1:detalleOperacion/con:cliente/con:nombreSector)}</ns2:nombreSector>
                <ns2:Argumentado>{fn:data($responseConsultarOperacionMessage/ns1:detalleOperacion/con:cliente/con:Argumentado)}</ns2:Argumentado>
            </ns2:cliente>
            else()
            }
            {
            for $i in $responseConsultarOperacionMessage/ns1:detalleOperacion/con:listadoDocumentos
            return
            <ns2:listadoDocumentos>
                <ns2:idDocumento>{fn:data($i/con:idDocumento)}</ns2:idDocumento>
                <ns2:idAdjunto>{fn:data($i/con:idAdjunto)}</ns2:idAdjunto>
                <ns2:nombreDocumento>{fn:data($i/con:nombreDocumento)}</ns2:nombreDocumento>
                <ns2:esJustificacion>{fn:data($i/con:esJustificacion)}</ns2:esJustificacion>
                {
                    if ($i/con:comentario)
                    then <ns2:comentario>{fn:substring(fn:string($i/con:comentario),1,256)}</ns2:comentario>
                    else ()
                }
                <ns2:mimeType>application/pdf</ns2:mimeType>
                <ns2:fechaDocumento>{fn:substring(fn:data($i/con:fechaDocumento/text()),0,11)}</ns2:fechaDocumento>
                <ns2:fechaRegistro>{fn:substring(fn:data($i/con:fechaRegistro/text()),0,11)}</ns2:fechaRegistro>
            </ns2:listadoDocumentos>
            }
            {
            for $j in $responseConsultarOperacionMessage/ns1:detalleOperacion/con:listadoCatalogos
            return
            <ns2:listadoCatalogos>
                <ns2:Id>{fn:data($j/cat:Id)}</ns2:Id>
                <ns2:Descripcion>{fn:data($j/cat:Descripcion)}</ns2:Descripcion>
                <ns2:DescripcionCorta>{fn:data($j/cat:DescripcionCorta)}</ns2:DescripcionCorta>
                <ns2:estatus>{fn:data($j/cat:estatus)}</ns2:estatus>
                <ns2:codigoExterno>{fn:data($j/cat:codigoExterno)}</ns2:codigoExterno>
            </ns2:listadoCatalogos>
            }
        </ns2:detalleOperacion>
        <ns2:Result>
            <ns2:result>{fn:data($responseConsultarOperacionMessage/ns1:Result/res:result)}</ns2:result>
            <ns2:message>{fn:data($responseConsultarOperacionMessage/ns1:Result/res:message)}</ns2:message>
            <ns2:error>
                <ns2:errorCode></ns2:errorCode>
                <ns2:errorDescription></ns2:errorDescription>
                <ns2:errorType></ns2:errorType>
            </ns2:error>
        </ns2:Result>
    </ns2:ConsultarOperacionResponse>
};

local:func($responseConsultarOperacionMessage)
