xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/AdquisicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Adquisicion/V1/Schema/AdquisicionMO.xsd" ::)

declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarAdquisicion";
(:: import schema at "../XSD/ConsultarAdquisicion.xsd" ::)

declare namespace ns3="http://www.bcie.org/OperacionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Operacion/V1/Schema/OperacionMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace adq = "http://www.bcie.org/AdquisicionBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare variable $ConsultarAdquisicionOutputCollection as element() (:: schema-element(ns1:ConsultarAdquisicionOutputCollection) ::) external;
declare variable $ConsultarNoObjecionResponse as element() (:: schema-element(ns2:ConsultarNoObjecionResponse) ::) external;
declare variable $ConsultarOperacionResponse as element() (:: schema-element(ns3:ConsultarOperacionResponse) ::) external;

declare function local:func($ConsultarAdquisicionOutputCollection as element() (:: schema-element(ns1:ConsultarAdquisicionOutputCollection) ::), 
                            $ConsultarNoObjecionResponse as element() (:: schema-element(ns2:ConsultarNoObjecionResponse) ::),
                            $ConsultarOperacionResponse as element() (:: schema-element(ns3:ConsultarOperacionResponse) ::)) 
                            as element() (:: schema-element(ns2:ConsultarAdquisicionResponse) ::) {
    <ns2:ConsultarAdquisicionResponse>
        <ns2:Adquisicion>
            <adq:idAdquisicion>{fn:data($ConsultarAdquisicionOutputCollection/ns1:ConsultarAdquisicionOutput/ns1:ID_ADQUISICION)}</adq:idAdquisicion>
            <adq:operacion>
              <ope:idOperacion>{fn:data($ConsultarOperacionResponse/ns3:Operacion/ope:idOperacion)}</ope:idOperacion>
               <ope:responsable>{fn:data($ConsultarOperacionResponse/ns3:Operacion/ope:responsable)}</ope:responsable>
                <ope:nombre>{fn:data($ConsultarOperacionResponse/ns3:Operacion/ope:nombre)}</ope:nombre>
                <ope:unidadEjecutora>{fn:data($ConsultarOperacionResponse/ns3:Operacion/ope:unidadEjecutora)}</ope:unidadEjecutora>
            </adq:operacion>
            <adq:cliente>
                <cli:idCliente>{fn:data($ConsultarOperacionResponse/ns3:Operacion/ope:cliente/cli:idCliente)}</cli:idCliente>
                <cli:razonSocial>{fn:data($ConsultarOperacionResponse/ns3:Operacion/ope:cliente/cli:razonSocial)}</cli:razonSocial>
                <cli:pais>
                <cat:Id>{fn:data($ConsultarOperacionResponse/ns3:Operacion/ope:cliente/cli:pais/cat:Id)}</cat:Id>
                <cat:Descripcion>{fn:data($ConsultarOperacionResponse/ns3:Operacion/ope:cliente/cli:pais/cat:Descripcion)}</cat:Descripcion>
                    {
                        if ($ConsultarOperacionResponse/ns3:Operacion/ope:cliente/cli:pais/cat:codigoExterno)
                        then <cat:codigoExterno>{fn:data($ConsultarOperacionResponse/ns3:Operacion/ope:cliente/cli:pais/cat:codigoExterno)}</cat:codigoExterno>
                        else ()
                    }
                </cli:pais>
            </adq:cliente>
            <adq:normativaAplicada>
                <cat:Id>{fn:data($ConsultarAdquisicionOutputCollection/ns1:ConsultarAdquisicionOutput/ns1:ID_TCA_TIPO_NORMATIVA_APLICADA)}</cat:Id>
                <cat:Descripcion>{fn:data($ConsultarAdquisicionOutputCollection/ns1:ConsultarAdquisicionOutput/ns1:TIPO_NORMATIVA_APLICADA)}</cat:Descripcion>
            </adq:normativaAplicada>
            <adq:normativaEspecifica>{fn:data($ConsultarAdquisicionOutputCollection/ns1:ConsultarAdquisicionOutput/ns1:NORMATIVA_ESPECIFICA)}</adq:normativaEspecifica>
            <adq:adquisicionPrevia>{if (string(fn:data($ConsultarAdquisicionOutputCollection/ns1:ConsultarAdquisicionOutput/ns1:CUENTA_CON_ADQUISICION_PREVIA)) = '1') then true() else false()}</adq:adquisicionPrevia>
            <adq:numero>{fn:data($ConsultarAdquisicionOutputCollection/ns1:ConsultarAdquisicionOutput/ns1:NUMERO_ADQUISICION)}</adq:numero>
            <adq:tipoAdquisicion>
                <cat:Id>{fn:data($ConsultarAdquisicionOutputCollection/ns1:ConsultarAdquisicionOutput/ns1:ID_TCA_TIPO_ADQUISICION)}</cat:Id>
                <cat:Descripcion>{fn:data($ConsultarAdquisicionOutputCollection/ns1:ConsultarAdquisicionOutput/ns1:TIPO_ADQUISICION)}</cat:Descripcion>
            </adq:tipoAdquisicion>
            <adq:montoPresupuestado>
                <com:importe>{fn:data($ConsultarAdquisicionOutputCollection/ns1:ConsultarAdquisicionOutput/ns1:MONTO_PRESUPUESTADO)}</com:importe>
                <com:moneda>
                    <cat:Id>{fn:data($ConsultarAdquisicionOutputCollection/ns1:ConsultarAdquisicionOutput/ns1:ID_TCA_TIPO_MONEDA_PRESUPUESTO)}</cat:Id>
                    <cat:DescripcionCorta>{fn:data($ConsultarAdquisicionOutputCollection/ns1:ConsultarAdquisicionOutput/ns1:MONEDA_PRESUPUESTADO)}</cat:DescripcionCorta>
                </com:moneda>
            </adq:montoPresupuestado>
            <adq:montoContratado>
                <com:importe>{fn:data($ConsultarAdquisicionOutputCollection/ns1:ConsultarAdquisicionOutput/ns1:MONTO_CONTRATADO)}</com:importe>
                <com:moneda>
                    <cat:Id>{fn:data($ConsultarAdquisicionOutputCollection/ns1:ConsultarAdquisicionOutput/ns1:ID_TCA_TIPO_MONEDA_CONTRATADO)}</cat:Id>
                    <cat:DescripcionCorta>{fn:data($ConsultarAdquisicionOutputCollection/ns1:ConsultarAdquisicionOutput/ns1:MONEDA_CONTRATADO)}</cat:DescripcionCorta>
                </com:moneda>
            </adq:montoContratado>
            <adq:montoAsociado>
                <com:importe>{fn:data($ConsultarAdquisicionOutputCollection/ns1:ConsultarAdquisicionOutput/ns1:MONTO_ASOCIADO)}</com:importe>
                <com:moneda>
                    <cat:Id>{fn:data($ConsultarAdquisicionOutputCollection/ns1:ConsultarAdquisicionOutput/ns1:ID_TCA_TIPO_MONEDA_ASOCIADO)}</cat:Id>
                    <cat:DescripcionCorta>{fn:data($ConsultarAdquisicionOutputCollection/ns1:ConsultarAdquisicionOutput/ns1:MONEDA_ASOCIADO)}</cat:DescripcionCorta>
                </com:moneda>
            </adq:montoAsociado>
            <adq:tipoAlcance>
                <cat:Id>{fn:data($ConsultarAdquisicionOutputCollection/ns1:ConsultarAdquisicionOutput/ns1:ID_TCA_TIPO_ALCANCE_PROCESO)}</cat:Id>
                <cat:Descripcion>{fn:data($ConsultarAdquisicionOutputCollection/ns1:ConsultarAdquisicionOutput/ns1:ALCANCE_PROCESO)}</cat:Descripcion>
            </adq:tipoAlcance>
            <adq:fechaContrato>{fn:data($ConsultarAdquisicionOutputCollection/ns1:ConsultarAdquisicionOutput/ns1:FECHA_CONTRATO)}</adq:fechaContrato>
            <adq:tipoModalidad>
                <cat:Id>{fn:data($ConsultarAdquisicionOutputCollection/ns1:ConsultarAdquisicionOutput/ns1:ID_TCA_TIPO_MODALIDAD_PROCESO)}</cat:Id>
                <cat:Descripcion>{fn:data($ConsultarAdquisicionOutputCollection/ns1:ConsultarAdquisicionOutput/ns1:MODALIDAD_PROCESO)}</cat:Descripcion>
            </adq:tipoModalidad>
            <adq:Adjudicatario>
                <adq:nombre>{fn:data($ConsultarAdquisicionOutputCollection/ns1:ConsultarAdquisicionOutput/ns1:NOMBRE_ADJUDICATARIO)}</adq:nombre>
                <adq:nacionalidad>{fn:data($ConsultarAdquisicionOutputCollection/ns1:ConsultarAdquisicionOutput/ns1:NACIONALIDAD_ADJUDICATARIO)}</adq:nacionalidad>
            </adq:Adjudicatario>
            <adq:titulo>{fn:data($ConsultarAdquisicionOutputCollection/ns1:ConsultarAdquisicionOutput/ns1:TITULO_ADQUISICION)}</adq:titulo>
            <adq:objetivo>{fn:data($ConsultarAdquisicionOutputCollection/ns1:ConsultarAdquisicionOutput/ns1:OBJETIVO_ADQUISICION)}</adq:objetivo>
            <adq:instanciaProceso>{fn:data($ConsultarAdquisicionOutputCollection/ns1:ConsultarAdquisicionOutput/ns1:INSTANCIA_PROCESO)}</adq:instanciaProceso>
            <adq:estado>{if (fn:data($ConsultarAdquisicionOutputCollection/ns1:ConsultarAdquisicionOutput/ns1:BAN_ESTATUS) = 1) then true() else false()}</adq:estado>
            <adq:fechaRegistro>{fn:data($ConsultarAdquisicionOutputCollection/ns1:ConsultarAdquisicionOutput/ns1:FECHA_REGISTRO)}</adq:fechaRegistro>
            {
            if(count($ConsultarNoObjecionResponse/ns2:NoObjecion/adq:idNoObjecion) = 1) then
            <adq:noObjecion>
                <adq:idNoObjecion>{fn:data($ConsultarNoObjecionResponse/ns2:NoObjecion/adq:idNoObjecion)}</adq:idNoObjecion>
                <adq:tipoNoObjecion>
                    {$ConsultarNoObjecionResponse/ns2:NoObjecion/adq:tipoNoObjecion/*}                
                </adq:tipoNoObjecion>
                <adq:fechaPublicacion></adq:fechaPublicacion>
                <adq:fechaInicioDoctoBase>{fn:data($ConsultarNoObjecionResponse/ns2:NoObjecion/adq:fechaInicioDoctoBase)}</adq:fechaInicioDoctoBase>
                <adq:fechaFinDoctoBase>{fn:data($ConsultarNoObjecionResponse/ns2:NoObjecion/adq:fechaFinDoctoBase)}</adq:fechaFinDoctoBase>
                <adq:fechaRecepcionPropuesta>{fn:data($ConsultarNoObjecionResponse/ns2:NoObjecion/adq:fechaRecepcionPropuesta)}</adq:fechaRecepcionPropuesta>
                <adq:lugarObtenerDoctoBase>{fn:data($ConsultarNoObjecionResponse/ns2:NoObjecion/adq:lugarObtenerDoctoBase)}</adq:lugarObtenerDoctoBase>
                <adq:correoInfoAdicional>{fn:data($ConsultarNoObjecionResponse/ns2:NoObjecion/adq:correoInfoAdicional)}</adq:correoInfoAdicional>
                <adq:direccionPresentacionPropuesta>{fn:data($ConsultarNoObjecionResponse/ns2:NoObjecion/adq:direccionPresentacionPropuesta)}</adq:direccionPresentacionPropuesta>
                <adq:resultadoProceso>
                    <cat:Id>{fn:data($ConsultarNoObjecionResponse/ns2:NoObjecion/adq:resultadoProceso/cat:Id)}</cat:Id>
                    <cat:Descripcion>{fn:data($ConsultarNoObjecionResponse/ns2:NoObjecion/adq:resultadoProceso/cat:Descripcion)}</cat:Descripcion>
                </adq:resultadoProceso>
                <adq:revisadoPublicacion></adq:revisadoPublicacion>
                <adq:publicado>{fn:data($ConsultarNoObjecionResponse/ns2:NoObjecion/adq:publicado)}</adq:publicado>
                <adq:numeroCorrespondencia></adq:numeroCorrespondencia>
                <adq:otorgoNoObjecion>{fn:data($ConsultarNoObjecionResponse/ns2:NoObjecion/adq:otorgoNoObjecion)}</adq:otorgoNoObjecion>
                <adq:fechaFirmaDocto></adq:fechaFirmaDocto>
                <adq:estado></adq:estado>
                <adq:fechaRegistro></adq:fechaRegistro>
                {
                for $concursante in $ConsultarNoObjecionResponse/ns2:NoObjecion/adq:concursante
                return
                <adq:concursante>
                    <adq:idConcursante>{fn:data($concursante/adq:idConcursante)}</adq:idConcursante>
                    <adq:tipoPerfil>
                        <cat:Id>{fn:data($concursante/adq:tipoPerfil/cat:Id)}</cat:Id>
                        <cat:Descripcion>{fn:data($concursante/adq:tipoPerfil/cat:Descripcion)}</cat:Descripcion>
                    </adq:tipoPerfil>
                    <adq:nombre>{fn:data($concursante/adq:nombre)}</adq:nombre>
                    <adq:nacionalidad>{$concursante/adq:nacionalidad/*}</adq:nacionalidad>
                    <adq:monto>
                        <com:importe>{fn:data($concursante/adq:monto/com:importe)}</com:importe>
                        <com:moneda>
                            <cat:Id>{fn:data($concursante/adq:monto/com:moneda/cat:Id)}</cat:Id>
                            <cat:DescripcionCorta>{fn:data($concursante/adq:monto/com:moneda/cat:DescripcionCorta)}</cat:DescripcionCorta>
                        </com:moneda>
                    </adq:monto>
                   
                </adq:concursante>
                }
            </adq:noObjecion>
            else
            ()
            }
        </ns2:Adquisicion>
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>Consulta Exitosa</res:message>
        </ns2:Resultado>
    </ns2:ConsultarAdquisicionResponse>
};

local:func($ConsultarAdquisicionOutputCollection, $ConsultarNoObjecionResponse, $ConsultarOperacionResponse)