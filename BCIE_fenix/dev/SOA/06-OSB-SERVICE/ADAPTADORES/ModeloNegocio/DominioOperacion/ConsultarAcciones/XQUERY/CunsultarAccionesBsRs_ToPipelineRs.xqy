xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns3="http://www.bcie.org/OperacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Operacion/V1/Schema/OperacionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarAcciones";
(:: import schema at "../XSD/ConsultarAcciones.xsd" ::)

declare namespace ns1="http://www.w3.org/2005/xquery-local-functions";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ConsultarAcciones as element() (:: schema-element(ns2:ConsultarAccionesOutputCollection) ::) external;

declare function ns1:func($ConsultarAcciones as element() (:: schema-element(ns2:ConsultarAccionesOutputCollection) ::)) as element() (:: schema-element(ns3:ConsultarAccionesResponse) ::) {
    <ns3:ConsultarAccionesResponse>
      <ns3:ListaAcciones>
    {
            for $Accion in $ConsultarAcciones/ns2:ConsultarAccionesOutput
            return
      
            <ope:Accion>
                <ope:IdAccion>{fn:data($Accion/ns2:IdAccion)}</ope:IdAccion>
                <ope:IdCliente>{fn:data($Accion/ns2:IdCliente)}</ope:IdCliente>
                <ope:IdOperacion>{fn:data($Accion/ns2:IdOperacion)}</ope:IdOperacion>
                <ope:Accion>{fn:data($Accion/ns2:Accion)}</ope:Accion>
                <ope:IdEstadoAccion>{fn:data($Accion/ns2:IdEstadoAccion)}</ope:IdEstadoAccion>
                <ope:TcaCategoriaAccion>
                    <cat:Id>{fn:data($Accion/ns2:IdTcaCategoriaAccion)}</cat:Id>
                </ope:TcaCategoriaAccion>
                <ope:TcaRolAsignado>
                    <cat:Id>{fn:data($Accion/ns2:IdTcaRolAsignado)}</cat:Id>
                    <cat:DescripcionCorta>{fn:data($Accion/ns2:DescripcionCorta)}</cat:DescripcionCorta>
                </ope:TcaRolAsignado>
                <ope:FechaRegistro>{fn:data($Accion/ns2:FechaRegistro)}</ope:FechaRegistro>
                <ope:FechaAtencion>{fn:data($Accion/ns2:FechaAtencion)}</ope:FechaAtencion>
                <ope:BanEstatus>{fn:data($Accion/ns2:BanEstatus)}</ope:BanEstatus>
            </ope:Accion>
       
        }
         </ns3:ListaAcciones>
    <ns3:Resultado>
        <res:result>OK</res:result>
        <res:message>Consulta Exitosa</res:message>
    </ns3:Resultado>
    </ns3:ConsultarAccionesResponse>
};

ns1:func($ConsultarAcciones)
