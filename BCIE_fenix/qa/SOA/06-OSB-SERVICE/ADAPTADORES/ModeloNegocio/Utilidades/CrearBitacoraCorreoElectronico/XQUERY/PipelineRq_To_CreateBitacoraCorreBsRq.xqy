xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/CorreoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/EnviarCorreoElectronico/V1/Schema/CorreoElectronicoMO.xsd" ::)
declare namespace ns3="http://xmlns.oracle.com/pcbpel/adapter/db/top/InsertaBiCorreoElectronico";
(:: import schema at "../XSD/InsertaBiCorreoElectronico_table.xsd" ::)

declare namespace ns1="http://www.w3.org/2005/xquery-local-functions";

declare namespace cor = "http://www.bcie.org/CorreoBO";

declare variable $crearBitacoraCorreoElectronicoRequest as element() (:: schema-element(ns2:crearBitacoraCorreoElectronicoRequest) ::) external;

declare function ns1:func($crearBitacoraCorreoElectronicoRequest as element() (:: schema-element(ns2:crearBitacoraCorreoElectronicoRequest) ::)) as element() (:: schema-element(ns3:TbiEnvioCorreo) ::) {
    <ns3:TbiEnvioCorreo>
       
        <ns3:TbiErrorEnvioCorreo>
        {
        if($crearBitacoraCorreoElectronicoRequest/ns2:BitacoraCorreoElectronico/cor:id) then
        <ns3:id>{fn:data($crearBitacoraCorreoElectronicoRequest/ns2:BitacoraCorreoElectronico/cor:id)}</ns3:id>
        else()
        }
            
            {
                if ($crearBitacoraCorreoElectronicoRequest/ns2:BitacoraCorreoElectronico/cor:idOperacion)
                then <ns3:idOperacion>{fn:data($crearBitacoraCorreoElectronicoRequest/ns2:BitacoraCorreoElectronico/cor:idOperacion)}</ns3:idOperacion>
                else ()
            }
            {
                if ($crearBitacoraCorreoElectronicoRequest/ns2:BitacoraCorreoElectronico/cor:idCliente)
                then <ns3:idCliente>{fn:data($crearBitacoraCorreoElectronicoRequest/ns2:BitacoraCorreoElectronico/cor:idCliente)}</ns3:idCliente>
                else ()
            }
            {
                if ($crearBitacoraCorreoElectronicoRequest/ns2:BitacoraCorreoElectronico/cor:idTcaPlantillaCorreo)
                then <ns3:idTcaPlantillaCorreo>{fn:data($crearBitacoraCorreoElectronicoRequest/ns2:BitacoraCorreoElectronico/cor:idTcaPlantillaCorreo)}</ns3:idTcaPlantillaCorreo>
                else ()
            }
            
            {
                if ($crearBitacoraCorreoElectronicoRequest/ns2:BitacoraCorreoElectronico/cor:descripcionError)
                then <ns3:descripcionError>{fn:data($crearBitacoraCorreoElectronicoRequest/ns2:BitacoraCorreoElectronico/cor:descripcionError)}</ns3:descripcionError>
                else ()
            }
            {
                if ($crearBitacoraCorreoElectronicoRequest/ns2:BitacoraCorreoElectronico/cor:mensajeError)
                then <ns3:mensajeError>{fn:data($crearBitacoraCorreoElectronicoRequest/ns2:BitacoraCorreoElectronico/cor:mensajeError)}</ns3:mensajeError>
                else ()
            }
            {
                if ($crearBitacoraCorreoElectronicoRequest/ns2:BitacoraCorreoElectronico/cor:seHaNotificadoError)
                then <ns3:seHaNotificadoError>{fn:data($crearBitacoraCorreoElectronicoRequest/ns2:BitacoraCorreoElectronico/cor:seHaNotificadoError)}</ns3:seHaNotificadoError>
                else ()
            }
            {
                if ((count($crearBitacoraCorreoElectronicoRequest/ns2:BitacoraCorreoElectronico/cor:id)=0) or (string($crearBitacoraCorreoElectronicoRequest/ns2:BitacoraCorreoElectronico/cor:id)=''))
                then <ns3:fechaRegistro>{fn:current-date()}</ns3:fechaRegistro>
                else ()
            }
            {
                if ((count($crearBitacoraCorreoElectronicoRequest/ns2:BitacoraCorreoElectronico/cor:id)=0 ) or (string($crearBitacoraCorreoElectronicoRequest/ns2:BitacoraCorreoElectronico/cor:id)=''))
                then <ns3:banEstatus>1</ns3:banEstatus>
                 
                else 
                <ns3:banEstatus>{fn:data($crearBitacoraCorreoElectronicoRequest/ns2:BitacoraCorreoElectronico/cor:banEstatus)}</ns3:banEstatus>
            }
        </ns3:TbiErrorEnvioCorreo>
    </ns3:TbiEnvioCorreo>
};

ns1:func($crearBitacoraCorreoElectronicoRequest)
