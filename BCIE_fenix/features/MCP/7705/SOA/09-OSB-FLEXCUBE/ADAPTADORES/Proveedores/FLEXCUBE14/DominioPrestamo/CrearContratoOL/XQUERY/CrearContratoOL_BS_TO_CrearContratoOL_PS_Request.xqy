xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/FLEXCUBE14/PrestamoMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/Flexcube14/DominioPrestamo/Prestamo/V1/Schema/PrestamoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/crearContratoOL_DB";
(:: import schema at "../XSD/crearContratoOL_DB_sp.xsd" ::)

declare namespace com = "http://www.bcie.org/FLEXCUBE14/ComponenteBO";
declare namespace cal = "http://www.bcie.org/FLEXCUBE14/CalendarioBO";

declare variable $ContratoOLRequest as element() (:: schema-element(ns1:CreaContratoOLRequest) ::) external;

declare function local:func($ContratoOLRequest as element() (:: schema-element(ns1:CreaContratoOLRequest) ::)) as element() (:: schema-element(ns2:InputParameters) ::) {
    <ns2:InputParameters>
        <ns2:PTYPECONTRATO>
              {
              if ($ContratoOLRequest/ns1:ContratoOL/ns1:Codigo_Desembolso)
              then <ns2:CODIGO_DESEMBOLSO>{fn:data($ContratoOLRequest/ns1:ContratoOL/ns1:Codigo_Desembolso)}</ns2:CODIGO_DESEMBOLSO>
              else ()
              }
              {
              if ($ContratoOLRequest/ns1:ContratoOL/ns1:Nombre_Negocio)
              then <ns2:NOMBRE_NEGOCIO>{fn:data($ContratoOLRequest/ns1:ContratoOL/ns1:Nombre_Negocio)}</ns2:NOMBRE_NEGOCIO>
              else ()
              }
              {
              if ($ContratoOLRequest/ns1:ContratoOL/ns1:Codigo_Cliente)
              then <ns2:CODIGO_CLIENTE>{fn:data($ContratoOLRequest/ns1:ContratoOL/ns1:Codigo_Cliente)}</ns2:CODIGO_CLIENTE>
              else ()
              }
              {
              if ($ContratoOLRequest/ns1:ContratoOL/ns1:Sector_Institucional)
              then <ns2:SECTOR_INSTITUCIONAL>{fn:data($ContratoOLRequest/ns1:ContratoOL/ns1:Sector_Institucional)}</ns2:SECTOR_INSTITUCIONAL>
              else ()
              }
              {
              if ($ContratoOLRequest/ns1:ContratoOL/ns1:Actividad_Economica)
              then <ns2:ACTIVIDAD_ECONOMICA>{fn:data($ContratoOLRequest/ns1:ContratoOL/ns1:Actividad_Economica)}</ns2:ACTIVIDAD_ECONOMICA>
              else ()
              }
              {
              if ($ContratoOLRequest/ns1:ContratoOL/ns1:Ejecutivo)
              then <ns2:EJECUTIVO>{fn:data($ContratoOLRequest/ns1:ContratoOL/ns1:Ejecutivo)}</ns2:EJECUTIVO>
              else ()
              }
              {
              if ($ContratoOLRequest/ns1:ContratoOL/ns1:Pais)
              then <ns2:PAIS>{fn:data($ContratoOLRequest/ns1:ContratoOL/ns1:Pais)}</ns2:PAIS>
              else ()
              }
              {
              if ($ContratoOLRequest/ns1:ContratoOL/ns1:Eje_Estrategico)
              then <ns2:EJE_ESTRATEGICO>{fn:data($ContratoOLRequest/ns1:ContratoOL/ns1:Eje_Estrategico)}</ns2:EJE_ESTRATEGICO>
              else ()
              }
              {
              if ($ContratoOLRequest/ns1:ContratoOL/ns1:Area_Focalizacion)
              then <ns2:AREA_FOCALIZACION>{fn:data($ContratoOLRequest/ns1:ContratoOL/ns1:Area_Focalizacion)}</ns2:AREA_FOCALIZACION>
              else ()
              }
              {
              if ($ContratoOLRequest/ns1:ContratoOL/ns1:Objetivo_Estrategico)
              then <ns2:OBJETIVO_ESTRATEGICO>{fn:data($ContratoOLRequest/ns1:ContratoOL/ns1:Objetivo_Estrategico)}</ns2:OBJETIVO_ESTRATEGICO>
              else ()
              }
              {
              if ($ContratoOLRequest/ns1:ContratoOL/ns1:Tipo_Recurso)
              then <ns2:TIPO_RECURSO>{fn:data($ContratoOLRequest/ns1:ContratoOL/ns1:Tipo_Recurso)}</ns2:TIPO_RECURSO>
              else ()
              }
              {
              if ($ContratoOLRequest/ns1:ContratoOL/ns1:Tipo_Financiamiento)
              then <ns2:TIPO_FINANCIAMIENTO>{fn:data($ContratoOLRequest/ns1:ContratoOL/ns1:Tipo_Financiamiento)}</ns2:TIPO_FINANCIAMIENTO>
              else ()
              }
              {
              if ($ContratoOLRequest/ns1:ContratoOL/ns1:Numero_Tesoreria)
              then <ns2:NUMERO_TESORERIA>{fn:data($ContratoOLRequest/ns1:ContratoOL/ns1:Numero_Tesoreria)}</ns2:NUMERO_TESORERIA>
              else ()
              }
              {
              if ($ContratoOLRequest/ns1:ContratoOL/ns1:Linea_Financiera)
              then <ns2:LINEA_FINANCIERA>{fn:data($ContratoOLRequest/ns1:ContratoOL/ns1:Linea_Financiera)}</ns2:LINEA_FINANCIERA>
              else ()
              }
              {
              if ($ContratoOLRequest/ns1:ContratoOL/ns1:Fondo)
              then <ns2:FONDO>{fn:data($ContratoOLRequest/ns1:ContratoOL/ns1:Fondo)}</ns2:FONDO>
              else ()
              }
              {
              if ($ContratoOLRequest/ns1:ContratoOL/ns1:Moneda)
              then <ns2:MONEDA>{fn:data($ContratoOLRequest/ns1:ContratoOL/ns1:Moneda)}</ns2:MONEDA>
              else ()
              }
              {
              if ($ContratoOLRequest/ns1:ContratoOL/ns1:Monto)
              then <ns2:MONTO>{fn:data($ContratoOLRequest/ns1:ContratoOL/ns1:Monto)}</ns2:MONTO>
              else ()
              }
              {
              if ($ContratoOLRequest/ns1:ContratoOL/ns1:Fecha_Valor)
              then <ns2:FECHA_VALOR>{fn:data($ContratoOLRequest/ns1:ContratoOL/ns1:Fecha_Valor)}</ns2:FECHA_VALOR>
              else ()
              }
              {
              if ($ContratoOLRequest/ns1:ContratoOL/ns1:Fecha_Vencimiento)
              then <ns2:FECHA_VENCIMIENTO>{fn:data($ContratoOLRequest/ns1:ContratoOL/ns1:Fecha_Vencimiento)}</ns2:FECHA_VENCIMIENTO>
              else ()
              }
              {
              if ($ContratoOLRequest/ns1:ContratoOL/ns1:Destino)
              then <ns2:DESTINO>{fn:data($ContratoOLRequest/ns1:ContratoOL/ns1:Destino)}</ns2:DESTINO>
              else ()
              }
              {
              if ($ContratoOLRequest/ns1:ContratoOL/ns1:Numero_Cuenta_Cliente)
              then <ns2:NUMERO_CUENTA_CLIENTE>{fn:data($ContratoOLRequest/ns1:ContratoOL/ns1:Numero_Cuenta_Cliente)}</ns2:NUMERO_CUENTA_CLIENTE>
              else ()
              }
              {
              if ($ContratoOLRequest/ns1:ContratoOL/ns1:Producto_Financiero)
              then <ns2:PRODUCTO_FINANCIERO>{fn:data($ContratoOLRequest/ns1:ContratoOL/ns1:Producto_Financiero)}</ns2:PRODUCTO_FINANCIERO>
              else ()
              }
              {
              if ($ContratoOLRequest/ns1:ContratoOL/ns1:Revolvencia)
              then <ns2:REVOLVENCIA>{fn:data($ContratoOLRequest/ns1:ContratoOL/ns1:Revolvencia)}</ns2:REVOLVENCIA>
              else ()
              }
              {
              if ($ContratoOLRequest/ns1:ContratoOL/ns1:Considerar_Feridados)
              then <ns2:CONSIDERAR_FERIDADOS>{fn:data($ContratoOLRequest/ns1:ContratoOL/ns1:Considerar_Feridados)}</ns2:CONSIDERAR_FERIDADOS>
              else ()
              }
              {
              if ($ContratoOLRequest/ns1:ContratoOL/ns1:Mover_Entre_Meses)
              then <ns2:MOVER_ENTRE_MESES>{fn:data($ContratoOLRequest/ns1:ContratoOL/ns1:Mover_Entre_Meses)}</ns2:MOVER_ENTRE_MESES>
              else ()
              }
              {
              if ($ContratoOLRequest/ns1:ContratoOL/ns1:Numero_Contrato_Linea)
              then <ns2:NUMERO_CONTRATO_LINEA>{fn:data($ContratoOLRequest/ns1:ContratoOL/ns1:Numero_Contrato_Linea)}</ns2:NUMERO_CONTRATO_LINEA>
              else ()
              }
              {
              if ($ContratoOLRequest/ns1:ContratoOL/ns1:Plantilla_Limite)
              then <ns2:PLANTILLA_LIMITE>{fn:data($ContratoOLRequest/ns1:ContratoOL/ns1:Plantilla_Limite)}</ns2:PLANTILLA_LIMITE>
              else ()
              }
              {
              if ($ContratoOLRequest/ns1:ContratoOL/ns1:Serial_Limite)
              then <ns2:SERIAL_LIMITE>{fn:data($ContratoOLRequest/ns1:ContratoOL/ns1:Serial_Limite)}</ns2:SERIAL_LIMITE>
              else ()
              }
              <ns2:LISTA_COMPONENTES>
              {
                  for $row in $ContratoOLRequest/ns1:ContratoOL/ns1:Lista_Componentes/com:componente  
              return
               <ns2:LISTA_COMPONENTES_ITEM>
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
                  if ($row/com:Spread)
                  then <ns2:SPREAD>{fn:data($row/com:Spread)}</ns2:SPREAD>
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
                  if ($row/com:Es_Dependiente)
                  then <ns2:ES_DEPENDIENTE>{fn:data($row/com:Es_Dependiente)}</ns2:ES_DEPENDIENTE>
                  else ()
                  }
                  {
                  if ($row/com:Es_Factor)
                  then <ns2:ES_FACTOR>{fn:data($row/com:Es_Factor)}</ns2:ES_FACTOR>
                  else ()
                  }
                  {
                  if ($row/com:Dias_Spot_Tasa)
                  then <ns2:DIAS_SPOT_TASA>{fn:data($row/com:Dias_Spot_Tasa)}</ns2:DIAS_SPOT_TASA>
                  else ()
                  }
                  {
                  if ($row/com:Cantidad_Decimales)
                  then <ns2:CANTIDAD_DECIMALES>{fn:data($row/com:Cantidad_Decimales)}</ns2:CANTIDAD_DECIMALES>
                  else ()
                  }
                  {
                  if ($row/com:Tipo_Redondeo)
                  then <ns2:TIPO_REDONDEO>{fn:data($row/com:Tipo_Redondeo)}</ns2:TIPO_REDONDEO>
                  else ()
                  }
                    <ns2:LISTA_CALENDARIO>
                      {
                      for $rowCal in $row/com:Lista_Calendario/cal:calendario
                       return
                       <ns2:LISTA_CALENDARIO_ITEM>
                       {
                       if ($rowCal/cal:Tipo_Plan)
                       then <ns2:TIPO_PLAN>{fn:data($rowCal/cal:Tipo_Plan)}</ns2:TIPO_PLAN>
                       else ()
                       }
                       {
                       if ($rowCal/cal:Tipo_Frecuencia)
                       then <ns2:TIPO_FRECUENCIA>{fn:data($rowCal/cal:Tipo_Frecuencia)}</ns2:TIPO_FRECUENCIA>
                       else ()
                       }
                       {
                       if ($rowCal/cal:Frecuencia)
                       then <ns2:FRECUENCIA>{fn:data($rowCal/cal:Frecuencia)}</ns2:FRECUENCIA>
                       else ()
                       }
                       {
                       if ($rowCal/cal:Monto)
                       then <ns2:MONTO>{fn:data($rowCal/cal:Monto)}</ns2:MONTO>
                       else ()
                       }
                       {
                       if ($rowCal/cal:Fecha_Inicio)
                       then <ns2:FECHA_INICIO>{fn:data($rowCal/cal:Fecha_Inicio)}</ns2:FECHA_INICIO>
                       else ()
                       }
                       {
                         if ($rowCal/cal:Numero_cuotas)
                       then <ns2:NUMERO_CUOTAS>{fn:data($rowCal/cal:Numero_cuotas)}</ns2:NUMERO_CUOTAS>
                       else ()
                       }
                    </ns2:LISTA_CALENDARIO_ITEM>
                   }
                  </ns2:LISTA_CALENDARIO>
                </ns2:LISTA_COMPONENTES_ITEM>
               } 
            </ns2:LISTA_COMPONENTES>
            <ns2:LISTA_CARGOS>
             {
               for $row in $ContratoOLRequest/ns1:ContratoOL/ns1:Lista_Cargos/ns1:cargo
                return
                  <ns2:LISTA_CARGOS_ITEM>
                  {
                  if ($row/ns1:Tipo_Cargo)
                  then <ns2:TIPO_CARGO>{fn:data($row/ns1:Tipo_Cargo)}</ns2:TIPO_CARGO>
                  else ()
                  }
                  {
                  if ($row/ns1:MontoCargo)
                  then <ns2:MONTOCARGO>{fn:data($row/ns1:MontoCargo)}</ns2:MONTOCARGO>
                  else ()
                  }
                  </ns2:LISTA_CARGOS_ITEM>
              }
            </ns2:LISTA_CARGOS>
            <ns2:LISTA_CALENDARIO_CAPITAL>
            {
               for $rowCalCap in $ContratoOLRequest/ns1:ContratoOL/ns1:Lista_Calendario_Capital/cal:calendario
                return
                 <ns2:LISTA_CALENDARIO_ITEM>
                 {
                  if ($rowCalCap/cal:Tipo_Plan)
                  then <ns2:TIPO_PLAN>{fn:data($rowCalCap/cal:Tipo_Plan)}</ns2:TIPO_PLAN>
                  else ()
                  }
                  {
                  if ($rowCalCap/cal:Tipo_Frecuencia)
                  then <ns2:TIPO_FRECUENCIA>{fn:data($rowCalCap/cal:Tipo_Frecuencia)}</ns2:TIPO_FRECUENCIA>
                  else ()
                  }
                  {
                  if ($rowCalCap/cal:Frecuencia)
                  then <ns2:FRECUENCIA>{fn:data($rowCalCap/cal:Frecuencia)}</ns2:FRECUENCIA>
                  else ()
                  }
                  {
                  if ($rowCalCap/cal:Monto)
                  then <ns2:MONTO>{fn:data($rowCalCap/cal:Monto)}</ns2:MONTO>
                  else ()
                  }
                  {
                  if ($rowCalCap/cal:Fecha_Inicio)
                  then <ns2:FECHA_INICIO>{fn:data($rowCalCap/cal:Fecha_Inicio)}</ns2:FECHA_INICIO>
                  else ()
                  }
                  {
                  if ($rowCalCap/cal:Numero_cuotas)
                  then <ns2:NUMERO_CUOTAS>{fn:data($rowCalCap/cal:Numero_cuotas)}</ns2:NUMERO_CUOTAS>
                  else ()
                  }
               </ns2:LISTA_CALENDARIO_ITEM>
              }
            </ns2:LISTA_CALENDARIO_CAPITAL>
        </ns2:PTYPECONTRATO>
        <ns2:PVUSUARIO>{fn:data($ContratoOLRequest/ns1:Usuario)}</ns2:PVUSUARIO>
    </ns2:InputParameters>
};
local:func($ContratoOLRequest)