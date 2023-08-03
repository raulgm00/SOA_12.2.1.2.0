xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/FLEXCUBE14/LineaCreditoMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/Flexcube14/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/crearCommitment_DB";
(:: import schema at "../XSD/crearCommitment_DB_sp.xsd" ::)

declare namespace com = "http://www.bcie.org/FLEXCUBE14/ComisionBO";

declare variable $CommitmentRequest as element() (:: schema-element(ns1:CrearCommitmentRequest) ::) external;

declare function local:func($CommitmentRequest as element() (:: schema-element(ns1:CrearCommitmentRequest) ::)) as element() (:: schema-element(ns2:InputParameters) ::) {
    <ns2:InputParameters>
        <ns2:PRECCOMMITMENT>
            {
            if ($CommitmentRequest/ns1:Commitment/ns1:Codigo_Intervencion)
            then <ns2:CODIGO_INTERVENCION>{fn:data($CommitmentRequest/ns1:Commitment/ns1:Codigo_Intervencion)}</ns2:CODIGO_INTERVENCION>
            else ()
            }
            {
            if ($CommitmentRequest/ns1:Commitment/ns1:Nombre_Negocio)
            then <ns2:NOMBRE_NEGOCIO>{fn:data(fn:substring($CommitmentRequest/ns1:Commitment/ns1:Nombre_Negocio,0,105))}</ns2:NOMBRE_NEGOCIO>
            else ()
            }
            {
            if ($CommitmentRequest/ns1:Commitment/ns1:Codigo_Cliente)
            then <ns2:CODIGO_CLIENTE>{fn:data($CommitmentRequest/ns1:Commitment/ns1:Codigo_Cliente)}</ns2:CODIGO_CLIENTE>
            else ()
            }
            {
            if ($CommitmentRequest/ns1:Commitment/ns1:Sector_Institucional)
            then <ns2:SECTOR_INSTITUCIONAL>{fn:data($CommitmentRequest/ns1:Commitment/ns1:Sector_Institucional)}</ns2:SECTOR_INSTITUCIONAL>
            else ()
            }
            {
            if ($CommitmentRequest/ns1:Commitment/ns1:Actividad_Economica)
            then <ns2:ACTIVIDAD_ECONOMICA>{fn:data($CommitmentRequest/ns1:Commitment/ns1:Actividad_Economica)}</ns2:ACTIVIDAD_ECONOMICA>
            else ()
            }
            {
            if ($CommitmentRequest/ns1:Commitment/ns1:Ejecutivo)
            then <ns2:EJECUTIVO>{fn:data($CommitmentRequest/ns1:Commitment/ns1:Ejecutivo)}</ns2:EJECUTIVO>
            else ()
            }
            {
            if ($CommitmentRequest/ns1:Commitment/ns1:Pais)
            then <ns2:PAIS>{fn:data($CommitmentRequest/ns1:Commitment/ns1:Pais)}</ns2:PAIS>
            else ()
            }
            {
            if ($CommitmentRequest/ns1:Commitment/ns1:Eje_Estrategico)
            then <ns2:EJE_ESTRATEGICO>{fn:data($CommitmentRequest/ns1:Commitment/ns1:Eje_Estrategico)}</ns2:EJE_ESTRATEGICO>
            else ()
            }
            {
            if ($CommitmentRequest/ns1:Commitment/ns1:Area_Focalizacion)
            then <ns2:AREA_FOCALIZACION>{fn:data($CommitmentRequest/ns1:Commitment/ns1:Area_Focalizacion)}</ns2:AREA_FOCALIZACION>
            else ()
            }
            {
            if ($CommitmentRequest/ns1:Commitment/ns1:Objetivo_Estrategico)
            then <ns2:OBJETIVO_ESTRATEGICO>{fn:data($CommitmentRequest/ns1:Commitment/ns1:Objetivo_Estrategico)}</ns2:OBJETIVO_ESTRATEGICO>
            else ()
            }
            {
            if ($CommitmentRequest/ns1:Commitment/ns1:Tipo_Recurso)
            then <ns2:TIPO_RECURSO>{fn:data($CommitmentRequest/ns1:Commitment/ns1:Tipo_Recurso)}</ns2:TIPO_RECURSO>
            else ()
            }
            {
            if ($CommitmentRequest/ns1:Commitment/ns1:Tipo_Financiamiento)
            then <ns2:TIPO_FINANCIAMIENTO>{fn:data($CommitmentRequest/ns1:Commitment/ns1:Tipo_Financiamiento)}</ns2:TIPO_FINANCIAMIENTO>
            else ()
            }
            {
            if ($CommitmentRequest/ns1:Commitment/ns1:Numero_Resolucion)
            then <ns2:NUMERO_RESOLUCION>{fn:data($CommitmentRequest/ns1:Commitment/ns1:Numero_Resolucion)}</ns2:NUMERO_RESOLUCION>
            else ()
            }
            {
            if ($CommitmentRequest/ns1:Commitment/ns1:Fecha_Aprobacion)
            then <ns2:FECHA_APROBACION>{fn:data($CommitmentRequest/ns1:Commitment/ns1:Fecha_Aprobacion)}</ns2:FECHA_APROBACION>
            else ()
            }
            {
            if ($CommitmentRequest/ns1:Commitment/ns1:Porcentaje_Cobertura)
            then <ns2:PORCENTAJE_COBERTURA>{fn:data($CommitmentRequest/ns1:Commitment/ns1:Porcentaje_Cobertura)}</ns2:PORCENTAJE_COBERTURA>
            else ()
            }
            {
            if ($CommitmentRequest/ns1:Commitment/ns1:Numero_Linea_Credito)
            then <ns2:NUMERO_LINEA_CREDITO>{fn:data($CommitmentRequest/ns1:Commitment/ns1:Numero_Linea_Credito)}</ns2:NUMERO_LINEA_CREDITO>
            else ()
            }
            {
            if ($CommitmentRequest/ns1:Commitment/ns1:Linea_Financiera)
            then <ns2:LINEA_FINANCIERA>{fn:data($CommitmentRequest/ns1:Commitment/ns1:Linea_Financiera)}</ns2:LINEA_FINANCIERA>
            else ()
            }
            {
            if ($CommitmentRequest/ns1:Commitment/ns1:Fondo)
            then <ns2:FONDO>{fn:data($CommitmentRequest/ns1:Commitment/ns1:Fondo)}</ns2:FONDO>
            else ()
            }
            {
            if ($CommitmentRequest/ns1:Commitment/ns1:Moneda)
            then <ns2:MONEDA>{fn:data($CommitmentRequest/ns1:Commitment/ns1:Moneda)}</ns2:MONEDA>
            else ()
            }
            {
            if ($CommitmentRequest/ns1:Commitment/ns1:Monto_Aprobado)
            then <ns2:MONTO_APROBADO>{fn:data($CommitmentRequest/ns1:Commitment/ns1:Monto_Aprobado)}</ns2:MONTO_APROBADO>
            else ()
            }
            {
            if ($CommitmentRequest/ns1:Commitment/ns1:Fecha_Valor)
            then <ns2:FECHA_VALOR>{fn:data($CommitmentRequest/ns1:Commitment/ns1:Fecha_Valor)}</ns2:FECHA_VALOR>
            else ()
            }
            {
            if ($CommitmentRequest/ns1:Commitment/ns1:Monto_Escriturado)
            then <ns2:MONTO_ESCRITURADO>{fn:data($CommitmentRequest/ns1:Commitment/ns1:Monto_Escriturado)}</ns2:MONTO_ESCRITURADO>
            else ()
            }
            {
            if ($CommitmentRequest/ns1:Commitment/ns1:Fecha_Escrituracion)
            then <ns2:FECHA_ESCRITURACION>{fn:data($CommitmentRequest/ns1:Commitment/ns1:Fecha_Escrituracion)}</ns2:FECHA_ESCRITURACION>
            else ()
            }
            {
            if ($CommitmentRequest/ns1:Commitment/ns1:Monto_Max_Desem)
            then <ns2:MONTO_MAX_DESEM>{fn:data($CommitmentRequest/ns1:Commitment/ns1:Monto_Max_Desem)}</ns2:MONTO_MAX_DESEM>
            else ()
            }
            {
            if ($CommitmentRequest/ns1:Commitment/ns1:Monto_Min_Desem)
            then <ns2:MONTO_MIN_DESEM>{fn:data($CommitmentRequest/ns1:Commitment/ns1:Monto_Min_Desem)}</ns2:MONTO_MIN_DESEM>
            else ()
            }
            {
            if ($CommitmentRequest/ns1:Commitment/ns1:Monto_Catalizar)
            then <ns2:MONTO_CATALIZAR>{fn:data($CommitmentRequest/ns1:Commitment/ns1:Monto_Catalizar)}</ns2:MONTO_CATALIZAR>
            else ()
            }
            {
            if ($CommitmentRequest/ns1:Commitment/ns1:Tipo_Catalizar)
            then <ns2:TIPO_CATALIZAR>{fn:data($CommitmentRequest/ns1:Commitment/ns1:Tipo_Catalizar)}</ns2:TIPO_CATALIZAR>
            else ()
            }
            {
            if ($CommitmentRequest/ns1:Commitment/ns1:Recursos_Ordinarios)
            then <ns2:RECURSOS_ORDINARIOS>{fn:data($CommitmentRequest/ns1:Commitment/ns1:Recursos_Ordinarios)}</ns2:RECURSOS_ORDINARIOS>
            else ()
            }
            {
            if ($CommitmentRequest/ns1:Commitment/ns1:Codigo_Asignacion)
            then <ns2:CODIGO_ASIGNACION>{fn:data($CommitmentRequest/ns1:Commitment/ns1:Codigo_Asignacion)}</ns2:CODIGO_ASIGNACION>
            else ()
            }
            {
            if ($CommitmentRequest/ns1:Commitment/ns1:Dia_Pago)
            then <ns2:DIA_PAGO>{fn:data($CommitmentRequest/ns1:Commitment/ns1:Dia_Pago)}</ns2:DIA_PAGO>
            else ()
            }
            {
            if ($CommitmentRequest/ns1:Commitment/ns1:Plazo_Linea_Credito)
            then <ns2:PLAZO_LINEA_CREDITO>{fn:data($CommitmentRequest/ns1:Commitment/ns1:Plazo_Linea_Credito)}</ns2:PLAZO_LINEA_CREDITO>
            else ()
            }
            {
            if ($CommitmentRequest/ns1:Commitment/ns1:Tipo_Plazo_Linea)
            then <ns2:TIPO_PLAZO_LINEA>{fn:data($CommitmentRequest/ns1:Commitment/ns1:Tipo_Plazo_Linea)}</ns2:TIPO_PLAZO_LINEA>
            else ()
            }
            {
            if ($CommitmentRequest/ns1:Commitment/ns1:Periodo_Gracia)
            then  <ns2:PERIODO_GRACIA>{fn:data($CommitmentRequest/ns1:Commitment/ns1:Periodo_Gracia)}</ns2:PERIODO_GRACIA>
            else ()
            }
            {
            if ($CommitmentRequest/ns1:Commitment/ns1:Tipo_Plazo_Gracia)
            then <ns2:TIPO_PLAZO_GRACIA>{fn:data($CommitmentRequest/ns1:Commitment/ns1:Tipo_Plazo_Gracia)}</ns2:TIPO_PLAZO_GRACIA>
            else ()
            }
            {
            if ($CommitmentRequest/ns1:Commitment/ns1:Destino)
            then <ns2:DESTINO>{fn:data($CommitmentRequest/ns1:Commitment/ns1:Destino)}</ns2:DESTINO>
            else ()
            }
            {
            if ($CommitmentRequest/ns1:Commitment/ns1:Numero_Cuenta_Cliente)
            then <ns2:NUMERO_CUENTA_CLIENTE>{fn:data($CommitmentRequest/ns1:Commitment/ns1:Numero_Cuenta_Cliente)}</ns2:NUMERO_CUENTA_CLIENTE>
            else ()
            }
            {
            if ($CommitmentRequest/ns1:Commitment/ns1:Producto_Financiero)
            then <ns2:PRODUCTO_FINANCIERO>{fn:data($CommitmentRequest/ns1:Commitment/ns1:Producto_Financiero)}</ns2:PRODUCTO_FINANCIERO>
            else ()
            }
            {
            if ($CommitmentRequest/ns1:Commitment/ns1:Revolvencia)
            then <ns2:REVOLVENCIA>{fn:data($CommitmentRequest/ns1:Commitment/ns1:Revolvencia)}</ns2:REVOLVENCIA>
            else ()
            }
            {
            if ($CommitmentRequest/ns1:Commitment/ns1:Fecha_Maxima_Escriturar)
            then <ns2:FECHA_MAXIMA_ESCRITURAR>{fn:data($CommitmentRequest/ns1:Commitment/ns1:Fecha_Maxima_Escriturar)}</ns2:FECHA_MAXIMA_ESCRITURAR>
            else ()
            }
            {
            if ($CommitmentRequest/ns1:Commitment/ns1:Fecha_Minima_Iniciar)
            then <ns2:FECHA_MINIMA_INICIAR>{fn:data($CommitmentRequest/ns1:Commitment/ns1:Fecha_Minima_Iniciar)}</ns2:FECHA_MINIMA_INICIAR>
            else ()
            }
            {
            if ($CommitmentRequest/ns1:Commitment/ns1:Fecha_Maxima_Totalidad)
            then <ns2:FECHA_MAXIMA_TOTALIDAD>{fn:data($CommitmentRequest/ns1:Commitment/ns1:Fecha_Maxima_Totalidad)}</ns2:FECHA_MAXIMA_TOTALIDAD>
            else ()
            }
            {
            if ($CommitmentRequest/ns1:Commitment/ns1:Tiene_Cond_Especiales)
            then <ns2:TIENE_COND_ESPECIALES>{fn:data($CommitmentRequest/ns1:Commitment/ns1:Tiene_Cond_Especiales)}</ns2:TIENE_COND_ESPECIALES>
            else ()
            }
            {
            if ($CommitmentRequest/ns1:Commitment/ns1:Desc_Cond_Especiales)
            then <ns2:DESC_COND_ESPECIALES>{fn:data($CommitmentRequest/ns1:Commitment/ns1:Desc_Cond_Especiales)}</ns2:DESC_COND_ESPECIALES>
            else ()
            }
            {
            if ($CommitmentRequest/ns1:Commitment/ns1:Considerar_Feriados)
            then <ns2:CONSIDERAR_FERIDADOS>{fn:data($CommitmentRequest/ns1:Commitment/ns1:Considerar_Feriados)}</ns2:CONSIDERAR_FERIDADOS>
            else ()
            }
            {
            if ($CommitmentRequest/ns1:Commitment/ns1:Mover_Entre_Meses)
            then <ns2:MOVER_ENTRE_MESES>{fn:data($CommitmentRequest/ns1:Commitment/ns1:Mover_Entre_Meses)}</ns2:MOVER_ENTRE_MESES>
            else ()
            }
            <ns2:LISTACOMISIONES>
            {
                for $row in $CommitmentRequest/ns1:Commitment/ns1:ListaComisiones/com:comision  
            return
             <ns2:LISTACOMISIONES_ITEM>
               {
                if ($row/com:Tipo_Tasa)
                then <ns2:TIPO_TASA>{fn:data($row/com:Tipo_Tasa)}</ns2:TIPO_TASA>
                else ()
                }
                {
                if ($row/com:Codigo_Tasa)
                then <ns2:CODIGO_TASA>{fn:data($row/com:Codigo_Tasa)}</ns2:CODIGO_TASA>
                else ()
                }
                {
                if ($row/com:Tipo_Comision)
                then <ns2:TIPO_COMISION>{fn:data($row/com:Tipo_Comision)}</ns2:TIPO_COMISION>
                else ()
                }
                {
                if ($row/com:Base_Calculo)
                then <ns2:BASE_CALCULO>{fn:data($row/com:Base_Calculo)}</ns2:BASE_CALCULO>
                else ()
                }
                {
                if ($row/com:Fecha_Inicio)
                then <ns2:FECHA_INICIO>{fn:data($row/com:Fecha_Inicio)}</ns2:FECHA_INICIO>
                else ()
                }
                {
                if ($row/com:Frecuencia)
                then <ns2:FRECUENCIA>{fn:data($row/com:Frecuencia)}</ns2:FRECUENCIA>
                else ()
                }
                {
                if ($row/com:Tipo_Frecuencia)
                then <ns2:TIPO_FRECUENCIA>{fn:data($row/com:Tipo_Frecuencia)}</ns2:TIPO_FRECUENCIA>
                else ()
                }
                {
                if ($row/com:Spread)
                then <ns2:SPREAD>{fn:data($row/com:Spread)}</ns2:SPREAD>
                else ()
                }
                {
                if ($row/com:Spread_Mora)
                then <ns2:SPREAD_MORA>{fn:data($row/com:Spread_Mora)}</ns2:SPREAD_MORA>
                else ()
                }
                {
                if ($row/com:Valor_Comision)
                then <ns2:VALOR_COMISION>{fn:data($row/com:Valor_Comision)}</ns2:VALOR_COMISION>
                else ()
                }
                {
                if ($row/com:Monto)
                then <ns2:MONTO>{fn:data($row/com:Monto)}</ns2:MONTO>
                else ()
                }
                {
                if ($row/com:Tasa_Minima)
                then <ns2:TASA_MINIMA>{fn:data($row/com:Tasa_Minima)}</ns2:TASA_MINIMA>
                else ()
                }
                {
                if ($row/com:Tasa_Maxima)
                then <ns2:TASA_MAXIMA>{fn:data($row/com:Tasa_Maxima)}</ns2:TASA_MAXIMA>
                else ()
                }
                {
                if ($row/com:Fecha_Inicio_Revision)
                then <ns2:FECHA_INICIO_REVISION>{fn:data($row/com:Fecha_Inicio_Revision)}</ns2:FECHA_INICIO_REVISION>
                else ()
                }
                {
                if ($row/com:Frecuencia_Revision)
                then <ns2:FRECUENCIA_REVISION>{fn:data($row/com:Frecuencia_Revision)}</ns2:FRECUENCIA_REVISION>
                else ()
                }
                {
                if ($row/com:Tipo_Frecuencia_Revision)
                then <ns2:TIPO_FRECUENCIA_REVISION>{fn:data($row/com:Tipo_Frecuencia_Revision)}</ns2:TIPO_FRECUENCIA_REVISION>
                else ()
                }
             </ns2:LISTACOMISIONES_ITEM>
            }
            </ns2:LISTACOMISIONES>
            <ns2:LISTATRANCHES>
            {
             for $row in $CommitmentRequest/ns1:Commitment/ns1:ListaTranches/ns1:tranch
              return
                <ns2:LISTATRANCHES_ITEM>
                {
                if ($row/ns1:Fecha_Vencimiento)
                then <ns2:FECHA_VENCIMIENTO>{fn:data($row/ns1:Fecha_Vencimiento)}</ns2:FECHA_VENCIMIENTO>
                else ()
                }
                {
                if ($row/ns1:Monto)
                then <ns2:MONTO>{fn:data($row/ns1:Monto)}</ns2:MONTO>
                else ()
                }
                </ns2:LISTATRANCHES_ITEM>
            }
            </ns2:LISTATRANCHES>
        </ns2:PRECCOMMITMENT>
        <ns2:PVUSUARIO>{fn:data($CommitmentRequest/ns1:Usuario)}</ns2:PVUSUARIO>
    </ns2:InputParameters>
};

local:func($CommitmentRequest)