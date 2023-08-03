xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/top/CrearActualizarDesembolsos_DB";
(:: import schema at "../XSD/CrearActualizarDesembolsos_DB_table.xsd" ::)

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare variable $CrearActualizarDesembolsosRequest as element() (:: schema-element(ns1:CrearActualizarDesembolsosRequest) ::) external;

declare function local:func($CrearActualizarDesembolsosRequest as element() (:: schema-element(ns1:CrearActualizarDesembolsosRequest) ::)) as element() (:: schema-element(ns2:ContratoDesembolsoCollection) ::) {
    <ns2:ContratoDesembolsoCollection>
    {
    for $contratoDesembolso in $CrearActualizarDesembolsosRequest/ns1:ContratoDesembolso
    return
        <ns2:ContratoDesembolso>
            <ns2:id>{fn:data($contratoDesembolso/des:idDesembolso)}</ns2:id>
            {
                if ($contratoDesembolso/des:monto/com:importe)
                then <ns2:montoDesembolsar>{fn:data($contratoDesembolso/des:monto[com:tipo/cat:DescripcionCorta='DESEMBOLSO']/com:importe)}</ns2:montoDesembolsar>
                else ()
            }
            {
                if ($contratoDesembolso/des:monto/com:moneda/cat:Id)
                then <ns2:idTcaTipoMoneda>{fn:data($contratoDesembolso/des:monto/com:moneda/cat:Id/text())}</ns2:idTcaTipoMoneda>
                else ()
            }
            {
                if ($contratoDesembolso/des:estado/cat:Id)
                then <ns2:idTcaEstado>{fn:data($contratoDesembolso/des:estado/cat:Id)}</ns2:idTcaEstado>
                else ()
            }
            {
                if ($contratoDesembolso/des:programado)
                then <ns2:programado>{
                if (fn:data($contratoDesembolso/des:programado)= true())then 1 else 0}</ns2:programado>
                else ()
            }
            {
                if ($contratoDesembolso/des:fechaEstimadaDesembolsar)
                then <ns2:fechaEstimadaDesembolsar>{fn:data($contratoDesembolso/des:fechaEstimadaDesembolsar)}</ns2:fechaEstimadaDesembolsar>
                else ()
            }
            {
                if ($contratoDesembolso/des:fechaEfectiva)
                then <ns2:fechaEfectiva>{fn:data($contratoDesembolso/des:fechaEfectiva)}</ns2:fechaEfectiva>
                else ()
            }
            {
                if ($contratoDesembolso/des:fechaRegistro)
                then <ns2:fechaRegistro>{fn:data($contratoDesembolso/des:fechaRegistro)}</ns2:fechaRegistro>
                else ()
            }
            {
                if ($contratoDesembolso/des:estatus)
                then <ns2:banEstatus>{
                if(fn:data($contratoDesembolso/des:estatus)= true()) then 1 else 0}</ns2:banEstatus>
                else ()
            }
            {
                if ($contratoDesembolso/des:idFacturador)
                then <ns2:contratoFlexcube>{fn:data($contratoDesembolso/des:idFacturador)}</ns2:contratoFlexcube>
                else ()
            }
            {
                if ($contratoDesembolso/des:instanciaProceso)
                then <ns2:instanciaProceso>{fn:data($contratoDesembolso/des:instanciaProceso)}</ns2:instanciaProceso>
                else ()
            }
            {
                if ($contratoDesembolso/des:recursosExternos)
                then <ns2:cuentaConRecursosExternos>{
                if(fn:data($contratoDesembolso/des:recursosExternos = true()))then 1 else 0}</ns2:cuentaConRecursosExternos>
                else ()
            }
            {
                if ($contratoDesembolso/des:monto/com:importe)
                then <ns2:montoDesembolsarUsd>{fn:data($contratoDesembolso/des:monto[com:tipo/cat:DescripcionCorta='DESEMBOLSO_USD']/com:importe)}</ns2:montoDesembolsarUsd>
                else ()
            }
            {
                if ($contratoDesembolso/des:condicionesFinancieras/des:fondo/cat:Id)
                then <ns2:fondo>{fn:data($contratoDesembolso/des:condicionesFinancieras/des:fondo/cat:Id)}</ns2:fondo>
                else ()
            }
            {
                if ($contratoDesembolso/des:Programa/her:LineaFinanciera/cat:codigoExterno)
                then <ns2:programaOperacion>{fn:data($contratoDesembolso/des:Programa/her:LineaFinanciera/cat:codigoExterno)}</ns2:programaOperacion>
                else ()
            }
            {
                if ($contratoDesembolso/des:Programa/her:DestinoFinanciamiento/cat:codigoExterno)
                then <ns2:destinoFinanciamiento>{fn:data($contratoDesembolso/des:Programa/her:DestinoFinanciamiento/cat:codigoExterno)}</ns2:destinoFinanciamiento>
                else ()
            }
            {
                if ($contratoDesembolso/des:Programa/her:ModalidadFinanciamiento/cat:Id)
                then <ns2:idTcaModalidadFinan>{fn:data($contratoDesembolso/des:Programa/her:ModalidadFinanciamiento/cat:Id)}</ns2:idTcaModalidadFinan>
                else ()
            }
            {
                if ($contratoDesembolso/des:Programa/her:HerramientaCE/her:ActividadEconomica/cat:Id)
                then <ns2:idCatActividadEconomica>{fn:data($contratoDesembolso/des:Programa/her:HerramientaCE/her:ActividadEconomica/cat:Id)}</ns2:idCatActividadEconomica>
                else ()
            }
			 {
                if ($contratoDesembolso/des:HerramientaCE/her:ActividadEconomica/cat:Id)
                then <ns2:idCatActividadEconomica>{fn:data($contratoDesembolso/des:HerramientaCE/her:ActividadEconomica/cat:Id)}</ns2:idCatActividadEconomica>
                else ()
            }
            {
                if ($contratoDesembolso/des:Programa/her:HerramientaCE/her:AreaFocalizacion/cat:Id)
                then <ns2:idCatAreaFocalizacion>{fn:data($contratoDesembolso/des:Programa/her:HerramientaCE/her:AreaFocalizacion/cat:Id)}</ns2:idCatAreaFocalizacion>
                else ()
            }
			 {
                if ($contratoDesembolso/des:HerramientaCE/her:AreaFocalizacion/cat:Id)
                then <ns2:idCatAreaFocalizacion>{fn:data($contratoDesembolso/des:HerramientaCE/her:AreaFocalizacion/cat:Id)}</ns2:idCatAreaFocalizacion>
                else ()
            }
            {
                if ($contratoDesembolso/des:Programa/her:HerramientaCE/her:EjeEstrategico/cat:Id)
                then <ns2:idCatEjeEstrategico>{fn:data($contratoDesembolso/des:Programa/her:HerramientaCE/her:EjeEstrategico/cat:Id)}</ns2:idCatEjeEstrategico>
                else ()
            }
			{
                if ($contratoDesembolso/des:HerramientaCE/her:EjeEstrategico/cat:Id)
                then <ns2:idCatEjeEstrategico>{fn:data($contratoDesembolso/des:HerramientaCE/her:EjeEstrategico/cat:Id)}</ns2:idCatEjeEstrategico>
                else ()
            }
            {
                if ($contratoDesembolso/des:origenTransferenciaCte)
                then <ns2:origenTranferenciaCliente>{fn:data($contratoDesembolso/des:origenTransferenciaCte)}</ns2:origenTranferenciaCliente>
                else ()
            }
            {
                if ($contratoDesembolso/des:cuentaCliente)
                then <ns2:cuentaCliente>{fn:data($contratoDesembolso/des:cuentaCliente)}</ns2:cuentaCliente>
                else ()
            }
            {
                if ($contratoDesembolso/des:fechaDisponibilidadFondos)
                then <ns2:fechaDisponibilidadFondos>{fn:data($contratoDesembolso/des:fechaDisponibilidadFondos)}</ns2:fechaDisponibilidadFondos>
                else ()
            }
            <ns2:idCatIniciativaEstrategica></ns2:idCatIniciativaEstrategica>
            {
                if ($contratoDesembolso/des:fechaEstimadaDisponibilidad)
                then <ns2:fechaEstimadaDispRecursos>{fn:data($contratoDesembolso/des:fechaEstimadaDisponibilidad)}</ns2:fechaEstimadaDispRecursos>
                else ()
            }
            {
                if ($contratoDesembolso/des:usuario)
                then <ns2:loginUsuario>{fn:data($contratoDesembolso/des:usuario)}</ns2:loginUsuario>
                else ()
            }
            {
                if ($contratoDesembolso/des:fechaInicioProceso)
                then <ns2:fechaInicioProcesoDesem>{fn:data($contratoDesembolso/des:fechaInicioProceso)}</ns2:fechaInicioProcesoDesem>
                else ()
            }
            {
                if ($contratoDesembolso/des:referencia)
                then <ns2:referencia>{fn:data($contratoDesembolso/des:referencia)}</ns2:referencia>
                else ()
            }
            {
                if ($contratoDesembolso/des:idClasificacionEstrategica)
                then <ns2:idClasificacionEstrategica>{fn:data($contratoDesembolso/des:idClasificacionEstrategica)}</ns2:idClasificacionEstrategica>
                else ()
            }
        </ns2:ContratoDesembolso>
        }
    </ns2:ContratoDesembolsoCollection>
};

local:func($CrearActualizarDesembolsosRequest)