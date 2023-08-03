xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/AdquisicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Adquisicion/V1/Schema/AdquisicionMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarAdquisicion";
(:: import schema at "../XSD/ConsultarAdquisicion.xsd" ::)

declare namespace ns3="http://www.bcie.org/OperacionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Operacion/V1/Schema/OperacionMO.xsd" ::)

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace ter = "http://www.bcie.org/TerminoBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace con1 = "http://www.bcie.org/CondicionBO";

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare namespace dec = "http://www.bcie.org/DeclaracionJuradaBO";

declare namespace con = "http://www.bcie.org/ContratoBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace adq = "http://www.bcie.org/AdquisicionBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare namespace reg = "http://www.bcie.org/ReglaBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $inConsultarAdquisicion as element() (:: schema-element(ns1:ConsultarAdquisicionOutputCollection) ::) external;
declare variable $ConsultarOperacionResponse as element() (:: schema-element(ns3:ConsultarOperacionResponse) ::) external;

declare function local:func($inConsultarAdquisicion as element() (:: schema-element(ns1:ConsultarAdquisicionOutputCollection) ::),
                        $ConsultarOperacionResponse as element() (:: schema-element(ns3:ConsultarOperacionResponse) ::)) as element() 
                        (:: schema-element(ns2:ConsultarAdquisicionResponse) ::) {
    <ns2:ConsultarAdquisicionResponse>
        {
            for $ConsultarAdquisicionOutput in $inConsultarAdquisicion/ns1:ConsultarAdquisicionOutput
            where $ConsultarAdquisicionOutput/ns1:ID_TCA_TIPO_MONEDA_PRESUPUESTO eq $ConsultarAdquisicionOutput/ns1:ID_TCA_TIPO_MONEDA_PRESUPUESTO
            return 
            <ns2:Adquisicion>
                <adq:idAdquisicion>{fn:data($ConsultarAdquisicionOutput/ns1:ID_ADQUISICION)}</adq:idAdquisicion>
                <adq:operacion>
                    {
                        if ($ConsultarOperacionResponse/ns3:Operacion/ope:idOperacion)
                        then <ope:idOperacion>{fn:data($ConsultarOperacionResponse/ns3:Operacion/ope:idOperacion)}</ope:idOperacion>
                        else ()
                    }
                    {
                        if ($ConsultarOperacionResponse/ns3:Operacion/ope:responsable)
                        then <ope:responsable>{fn:data($ConsultarOperacionResponse/ns3:Operacion/ope:responsable)}</ope:responsable>
                        else ()
                    }
                    <ope:oficina></ope:oficina>
                    {
                        if ($ConsultarOperacionResponse/ns3:Operacion/ope:nombre)
                        then <ope:nombre>{fn:data($ConsultarOperacionResponse/ns3:Operacion/ope:nombre)}</ope:nombre>
                        else ()
                    }
                    {
                        if ($ConsultarOperacionResponse/ns3:Operacion/ope:unidadEjecutora)
                        then <ope:unidadEjecutora>{fn:data($ConsultarOperacionResponse/ns3:Operacion/ope:unidadEjecutora)}</ope:unidadEjecutora>
                        else ()
                    }
                </adq:operacion>
                <adq:cliente>
                    <cli:idCliente>{fn:data($ConsultarOperacionResponse/ns3:Operacion/ope:cliente/cli:idCliente)}</cli:idCliente>
                    <cli:idFacturador></cli:idFacturador>
                    {
                        if ($ConsultarOperacionResponse/ns3:Operacion/ope:cliente/cli:razonSocial)
                        then <cli:razonSocial>{fn:data($ConsultarOperacionResponse/ns3:Operacion/ope:cliente/cli:razonSocial)}</cli:razonSocial>
                        else ()
                    }
                    <cli:pais>
                        {
                            if ($ConsultarOperacionResponse/ns3:Operacion/ope:cliente/cli:pais/cat:Id)
                            then <cat:Id>{fn:data($ConsultarOperacionResponse/ns3:Operacion/ope:cliente/cli:pais/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($ConsultarOperacionResponse/ns3:Operacion/ope:cliente/cli:pais/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($ConsultarOperacionResponse/ns3:Operacion/ope:cliente/cli:pais/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        <cat:DescripcionCorta></cat:DescripcionCorta>
                        <cat:estatus></cat:estatus>
                        {
                            if ($ConsultarOperacionResponse/ns3:Operacion/ope:cliente/cli:pais/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($ConsultarOperacionResponse/ns3:Operacion/ope:cliente/cli:pais/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </cli:pais>
                </adq:cliente>
                <adq:normativaAplicada>
                    <cat:Id>{fn:data($ConsultarAdquisicionOutput/ns1:ID_TCA_TIPO_NORMATIVA_APLICADA)}</cat:Id>
                    <cat:Descripcion>{fn:data($ConsultarAdquisicionOutput/ns1:TIPO_NORMATIVA_APLICADA)}</cat:Descripcion>
                </adq:normativaAplicada>
                <adq:normativaEspecifica>{fn:data($ConsultarAdquisicionOutput/ns1:NORMATIVA_ESPECIFICA)}</adq:normativaEspecifica>
                <adq:adquisicionPrevia>{if(fn:string($ConsultarAdquisicionOutput/ns1:CUENTA_CON_ADQUISICION_PREVIA)='1')then 'true' else('false')}</adq:adquisicionPrevia>
                <adq:numero>{fn:data($ConsultarAdquisicionOutput/ns1:NUMERO_ADQUISICION)}</adq:numero>
                <adq:tipoAdquisicion>
                    <cat:Id>{fn:data($ConsultarAdquisicionOutput/ns1:ID_TCA_TIPO_ADQUISICION)}</cat:Id>
                    <cat:Descripcion>{fn:data($ConsultarAdquisicionOutput/ns1:TIPO_ADQUISICION)}</cat:Descripcion>
                </adq:tipoAdquisicion>
                <adq:montoPresupuestado>
                    <com:importe>{fn:data($ConsultarAdquisicionOutput/ns1:MONTO_PRESUPUESTADO)}</com:importe>
                    <com:moneda>
                        <cat:Id>{fn:data($ConsultarAdquisicionOutput/ns1:ID_TCA_TIPO_MONEDA_PRESUPUESTO)}</cat:Id>
                        <cat:DescripcionCorta>{fn:data($ConsultarAdquisicionOutput/ns1:MONEDA_PRESUPUESTADO)}</cat:DescripcionCorta>
                    </com:moneda>
                </adq:montoPresupuestado>
                <adq:montoContratado>
                    <com:importe>{fn:data($ConsultarAdquisicionOutput/ns1:MONTO_CONTRATADO)}</com:importe>
                    <com:moneda>
                        <cat:Id>{fn:data($ConsultarAdquisicionOutput/ns1:ID_TCA_TIPO_MONEDA_CONTRATADO)}</cat:Id>
                        <cat:Descripcion></cat:Descripcion>
                        <cat:DescripcionCorta>{fn:data($ConsultarAdquisicionOutput/ns1:MONEDA_CONTRATADO)}</cat:DescripcionCorta>
                        <cat:estatus></cat:estatus>
                        <cat:codigoExterno></cat:codigoExterno>
                    </com:moneda>
                </adq:montoContratado>
                <adq:montoAsociado>
                    <com:importe>{fn:data($ConsultarAdquisicionOutput/ns1:MONTO_ASOCIADO)}</com:importe>
                    <com:moneda>
                        <cat:Id>{fn:data($ConsultarAdquisicionOutput/ns1:ID_TCA_TIPO_MONEDA_ASOCIADO)}</cat:Id>
                        <cat:DescripcionCorta>{fn:data($ConsultarAdquisicionOutput/ns1:MONEDA_ASOCIADO)}</cat:DescripcionCorta>
                    </com:moneda>
                </adq:montoAsociado>
                <adq:tipoAlcance>
                    <cat:Id>{fn:data($ConsultarAdquisicionOutput/ns1:ID_TCA_TIPO_ALCANCE_PROCESO)}</cat:Id>
                    <cat:Descripcion>{fn:data($ConsultarAdquisicionOutput/ns1:ALCANCE_PROCESO)}</cat:Descripcion>
                    <cat:DescripcionCorta></cat:DescripcionCorta>
                </adq:tipoAlcance>
                <adq:fechaContrato>{fn:data($ConsultarAdquisicionOutput/ns1:FECHA_CONTRATO)}</adq:fechaContrato>
                <adq:tipoModalidad>
                    <cat:Id>{fn:data($ConsultarAdquisicionOutput/ns1:ID_TCA_TIPO_MODALIDAD_PROCESO)}</cat:Id>
                    <cat:Descripcion>{fn:data($ConsultarAdquisicionOutput/ns1:MODALIDAD_PROCESO)}</cat:Descripcion>
                </adq:tipoModalidad>
                <adq:Adjudicatario>
                    <adq:nombre>{fn:data($ConsultarAdquisicionOutput/ns1:NOMBRE_ADJUDICATARIO)}</adq:nombre>
                    <adq:nacionalidad>
                        <cat:Descripcion>{fn:data($ConsultarAdquisicionOutput/ns1:NACIONALIDAD_ADJUDICATARIO)}</cat:Descripcion>
                    </adq:nacionalidad>
                </adq:Adjudicatario>
                <adq:titulo>{fn:data($ConsultarAdquisicionOutput/ns1:TITULO_ADQUISICION)}</adq:titulo>
                <adq:objetivo>{fn:data($ConsultarAdquisicionOutput/ns1:OBJETIVO_ADQUISICION)}</adq:objetivo>
                <adq:instanciaProceso>{fn:data($ConsultarAdquisicionOutput/ns1:INSTANCIA_PROCESO)}</adq:instanciaProceso>
                <adq:estado>{if(fn:string($ConsultarAdquisicionOutput/ns1:BAN_ESTATUS)='1')then 'true'else('false')}</adq:estado>
                <adq:fechaRegistro>{fn:data($ConsultarAdquisicionOutput/ns1:FECHA_REGISTRO)}</adq:fechaRegistro>
                </ns2:Adquisicion>
        }
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>Consulta Exitosa</res:message>
            <res:error>
                <err:errorCode></err:errorCode>
                <err:errorDescription></err:errorDescription>
                <err:errorType></err:errorType>
            </res:error>
        </ns2:Resultado>
    </ns2:ConsultarAdquisicionResponse>
};

local:func($inConsultarAdquisicion, $ConsultarOperacionResponse)