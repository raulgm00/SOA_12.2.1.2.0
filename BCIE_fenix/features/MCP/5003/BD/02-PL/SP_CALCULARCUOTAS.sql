CREATE OR REPLACE PROCEDURE SP_calcularCuotas (vdFecha_Inicio       IN     DATE,
                          vvTipo_Frecuencia    IN     VARCHAR2,
                          vnFrecuencia         IN     NUMBER,
                          pdFechaVencimiento   IN     DATE,
                          pnFlags              IN     NUMBER:= 0,
                          vnCuotas                OUT NUMBER,
                          pcMensaje            IN OUT VARCHAR2)
AS



   vvformatofechaMes    VARCHAR2 (50) := 'mm/dd/yy';

   veException EXCEPTION;
   vnPeriodos           NUMBER;
   ldFecha              DATE;
BEGIN 

   IF vvTipo_frecuencia NOT IN ('D', 'M', 'Y', 'B')    THEN
      pcMensaje:=(   pcMensaje
                           || ','
                           || 'calcularCuotas: el campo tipo de frecuencia('
                           || vvTipo_Frecuencia
                           || ') solo acepta los siguientes valores (D, M, Y, B)');
   --      concatenar (          pcMensaje, 'calcularCuotas: el campo tipo de frecuencia('          || vvTipo_Frecuencia          || ') solo acepta los siguientes valores (D, M, Y, B)');

   END IF;

   CASE
      WHEN vvTipo_Frecuencia = 'B'
      THEN
         vnCuotas := 1;

         IF NVL (vdFecha_Inicio, SYSDATE) <>
               NVL (pdFechaVencimiento, SYSDATE)
         THEN
            --            concatenar (   pcMensaje,  'Para Tipo Frecuencia Bullet la Fehca de Inicio('  || TO_CHAR (vdFecha_Inicio, vvformatofechaMes)    || ') debe ser igual a la Fecha de Vencimiento('  || TO_CHAR (pdFechaVencimiento, vvformatofechaMes)   || ') para el :componente; ');

       pcMensaje:=(pcMensaje
                                 || 'Para Tipo Frecuencia Bullet la Fecha de Inicio('
                                 || TO_CHAR (vdFecha_Inicio,
                                             vvformatofechaMes)
                                 || ') debe ser igual a la Fecha de Vencimiento('
                                 || TO_CHAR (pdFechaVencimiento,
                                             vvformatofechaMes)
                                 || ') para el :componente; ');
         END IF;

         IF pcMensaje IS NOT NULL
         THEN
            RAISE veexception;
         END IF;
      WHEN vvTipo_Frecuencia = 'M'
      THEN
         vnPeriodos := MONTHS_BETWEEN (pdFechaVencimiento, vdFecha_Inicio);
      WHEN vvTipo_Frecuencia = 'D'
      THEN
         vnPeriodos := pdFechaVencimiento - vdFecha_Inicio;
      WHEN vvTipo_Frecuencia = 'Y'
      THEN
         vnPeriodos :=
            MONTHS_BETWEEN (pdFechaVencimiento, vdFecha_Inicio) / 12;

         IF vnPeriodos <= 0
         THEN
            --concatenar ( pcMensaje,  'Las cuotas para la frecuencia anual, debe ser mayor que cero; pdFechaVencimiento = '  || TO_CHAR (pdFechaVencimiento, vvformatofechaMes)  || ' vdFecha_Inicio = ' || TO_CHAR (vdFecha_Inicio, vvformatofechaMes)  || ' para el :componente; ');
            pcMensaje:=(pcMensaje
                                 || 'Las cuotas para la frecuencia anual, debe ser mayor que cero; pdFechaVencimiento = '
                                 || TO_CHAR (pdFechaVencimiento,
                                             vvformatofechaMes)
                                 || ' vdFecha_Inicio = '
                                 || TO_CHAR (vdFecha_Inicio,
                                             vvformatofechaMes)
                                 || ' para el :componente; ');

            RAISE veexception;
         END IF;
      WHEN vvTipo_Frecuencia IS NULL
      THEN
         NULL;
      ELSE
       pcMensaje:=(
            pcMensaje || 'vvTipo_Frecuencia=' || vvTipo_Frecuencia);
   --         concatenar (pcMensaje, 'vvTipo_Frecuencia=' || vvTipo_Frecuencia);
   END CASE;

   IF vnFrecuencia <> 0 AND vvTipo_Frecuencia <> 'B'
   THEN
      vnCuotas := CEIL (vnPeriodos / vnFrecuencia); -- Se elimina el +1 debido a que con la función ceil se esta obteniendo el numero inmediato superior y ya no es necesario sumarle 1 para obtener el entero

      ldFecha :=          bcie.modificarFecha (pdFecha        => vdFecha_Inicio,
                         pnPlazo        => (vnFrecuencia * (vnCuotas)), -- al eliminar el +1 en el renglon anterior este -1 deja de ser necesario
                         pvTipo_Plazo   => vvTipo_Frecuencia);

      IF pdFechaVencimiento = ldFecha
      THEN
         vnCuotas := vnCuotas - 0;
      END IF;

      ldFecha :=
         bcie.modificarFecha (pdFecha        => vdFecha_Inicio,
                              pnPlazo        => (vnFrecuencia * vnCuotas),
                              pvTipo_Plazo   => vvTipo_Frecuencia);

      IF pdFechaVencimiento > ldFecha
      THEN
         vnCuotas := vnCuotas + 1;
      END IF;
   ELSIF vvTipo_Frecuencia <> 'B'
   THEN
      --      concatenar (pcMensaje,              'La frecuencia para el componente :componente es 0');

     pcMensaje:=(
         pcMensaje || 'La frecuencia para el componente :componente es 0');
   END IF;

   IF vvTipo_frecuencia = 'B' AND vnFrecuencia <> 1
   THEN
      --      concatenar ( pcMensaje,  'La frecuencia para el tipo de frecuencia bullet debe ser 1 actualmente es ('  || vnFrecuencia     || ') para el :componente');
      pcMensaje:=(pcMensaje
                           || 'La frecuencia para el tipo de frecuencia bullet debe ser 1 actualmente es ('
                           || vnFrecuencia
                           || ') para el :componente');
   ELSIF CEIL (vnPeriodos) < vnFrecuencia AND pnFlags <> 1
   THEN
      --      concatenar (  pcMensaje,   'La fecha de vencimiento('     || TO_CHAR (pdFechaVencimiento, vvformatofechaMes)   || ') debe ser mayor o igual a 1 periodicidad('   || TO_CHAR (vdFecha_Inicio, vvformatofechaMes)  || '+'  || vnFrecuencia
      --         || vvTipo_Frecuencia
      --         || ') para el componente :componente '
      --         || 'vnPeriodos:'
      --         || vnPeriodos
      --         || ',vnFrecuencia:'
      --         || vnFrecuencia);

     pcMensaje:=(   pcMensaje
                           || 'La fecha de vencimiento('
                           || TO_CHAR (pdFechaVencimiento, vvformatofechaMes)
                           || ') debe ser mayor o igual a 1 periodicidad('
                           || TO_CHAR (vdFecha_Inicio, vvformatofechaMes)
                           || '+'
                           || vnFrecuencia
                           || vvTipo_Frecuencia
                           || ') para el componente :componente '
                           || 'vnPeriodos:'
                           || vnPeriodos
                           || ',vnFrecuencia:'
                           || vnFrecuencia);
   ELSIF vnCuotas = 0
   THEN
      --      concatenar (pcMensaje,  'El calculo de cuotas para :componente resulto en cero');

      pcMensaje:=(pcMensaje
                           || 'El calculo de cuotas para :componente resulto en cero');
   END IF;

   IF pcMensaje IS NOT NULL
   THEN
      RAISE veException;
   END IF;

   pcMensaje :=
         'calcularCuotas(vdFecha_Inicio => '''
      || TO_CHAR (vdFecha_Inicio, vvformatofechaMes)
      || ''', vvTipo_Frecuencia => '''
      || vvTipo_Frecuencia
      || ''', vnFrecuencia => '''
      || vnFrecuencia
      || ''', pdFechaVencimiento => '''
      || TO_CHAR (pdFechaVencimiento, vvformatofechaMes)
      || ''', vnCuotas => '''
      || vnCuotas
      || ''', pcMensaje => '''
      || pcMensaje
      || '); vnPeriodos:='
      || vnPeriodos;
   --DBMS_OUTPUT.PUT_LINE(pcMensaje);
   pcMensaje := NULL;


   DBMS_OUTPUT.put_line (pcMensaje);

   DBMS_OUTPUT.put_line (vnCuotas);
/*
            dbms_output.put_line(
                vdFecha_Inicio||' i-(vnFrecuencia:vvTipo_Frecuencia='||vnFrecuencia||vvTipo_Frecuencia||')*(vnCuotas:'||vnCuotas||') - '||
                ' -modificarFecha'||
                modificarFecha(
                 pdFecha       => vdFecha_Inicio
               , pnPlazo       => (vnFrecuencia*vnCuotas)
               , pvTipo_Plazo  => vvTipo_Frecuencia
               )||' -v '||pdFechaVencimiento);
*/
EXCEPTION
   WHEN veException
   THEN
      vnCuotas := NULL;
   WHEN OTHERS
   THEN
      pcMensaje :=
            pcMensaje
         || ' Error desconcido --> '
         || SQLERRM
         || DBMS_UTILITY.FORMAT_ERROR_BACKTRACE;
      vnCuotas := NULL;

--      DBMS_OUTPUT.put_line (pcMensaje);
END;
/
