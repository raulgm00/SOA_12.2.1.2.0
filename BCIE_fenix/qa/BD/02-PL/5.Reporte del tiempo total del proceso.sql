/* Formatted on 10/08/2017 11:54:31 a.m. (QP5 v5.136.908.31019) */
SELECT 'OPERACION' AS CATEGORIA,
       INICIO.ID_OPERACION AS ID_OPERACION,
       INICIO.NOMBRE_OPERACION AS NOMBRE_OPERACION,
       NULL ID_CLIENTE,
       NULL RAZON_SOCIAL,
       INICIO.ID_PROCESO AS ID_PROCESO,
       TPB.DESCRIPCION AS DESCRIPCION_PROCESO,
       INICIO.FECHA_REGISTRO AS FECHA_INICIO,
       INICIO.USUARIO AS USUARIO_INICIA,
       FIN.FECHA_REGISTRO AS FECHA_FIN,
       FIN.USUARIO AS USUARIO_FIN,
       FIN.TIEMPO_PROCESO AS TIEMPO_PROCESO,
       CASE WHEN FIN.BAN_FINALIZADO = 1 THEN 'SI' ELSE 'NO' END FINALIZADO
  FROM (SELECT TPO.ID,
               TPO.ID_OPERACION,
               TPO.NOMBRE_OPERACION,
               TPO.ID_PROCESO,
               TPO.ID_TAREA,
               TPO.FECHA_REGISTRO,
               TPO.USUARIO,
               TPO.BAN_FINALIZADO
          FROM TBI_PROCESO_OPERACION TPO INNER JOIN TCA_PROCESO_BPM TPB
                  ON (TPO.ID_PROCESO = TPB.ID)
         WHERE BAN_ES_PROCESO = 1 AND BAN_ES_FIN_ACTIVIDAD = 0) INICIO
       INNER JOIN (SELECT TPO.ID,
                          TPO.ID_OPERACION,
                          TPO.NOMBRE_OPERACION,
                          TPO.ID_INICIO,
                          TPO.FECHA_REGISTRO,
                          TPO.TIEMPO_PROCESO,
                          TPO.USUARIO,
                          TPO.BAN_FINALIZADO
                     FROM    TBI_PROCESO_OPERACION TPO
                          INNER JOIN
                             TCA_PROCESO_BPM TPB
                          ON (TPO.ID_PROCESO = TPB.ID)
                    WHERE BAN_ES_PROCESO = 1 AND BAN_ES_FIN_ACTIVIDAD = 1)
                  FIN
          ON (INICIO.ID = FIN.ID_INICIO)
       INNER JOIN TCA_PROCESO_BPM TPB
          ON (TPB.ID = INICIO.ID_PROCESO)
UNION ALL
SELECT 'CLIENTE' AS CATEGORIA,
       NULL ID_OPERACION,
       NULL NOMBRE_OPERACION,
       INICIO.ID_CLIENTE AS ID_CLIENTE,
       INICIO.RAZON_SOCIAL AS RAZON_SOCIAL,
       INICIO.ID_PROCESO AS ID_PROCESO,
       TPB.DESCRIPCION AS DESCRIPCION_PROCESO,
       INICIO.FECHA_REGISTRO AS FECHA_INICIO,
       INICIO.USUARIO AS USUARIO_INICIA,
       FIN.FECHA_REGISTRO AS FECHA_FIN,
       FIN.USUARIO AS USUARIO_FIN,
       FIN.TIEMPO_PROCESO AS TIEMPO_PROCESO,
       CASE WHEN FIN.BAN_FINALIZADO = 1 THEN 'SI' ELSE 'NO' END FINALIZADO
  FROM (SELECT TPO.ID,
               TPO.ID_CLIENTE,
               TPO.RAZON_SOCIAL,
               TPO.ID_PROCESO,
               TPO.ID_TAREA,
               TPO.FECHA_REGISTRO,
               TPO.USUARIO,
               TPO.BAN_FINALIZADO
          FROM TBI_PROCESO_CLIENTE TPO INNER JOIN TCA_PROCESO_BPM TPB
                  ON (TPO.ID_PROCESO = TPB.ID)
         WHERE BAN_ES_PROCESO = 1 AND BAN_ES_FIN_ACTIVIDAD = 0) INICIO
       LEFT OUTER JOIN (SELECT TPO.ID,
                               TPO.ID_CLIENTE,
                               TPO.RAZON_SOCIAL,
                               TPO.ID_INICIO,
                               TPO.FECHA_REGISTRO,
                               TPO.TIEMPO_PROCESO,
                               TPO.USUARIO,
                               TPO.BAN_FINALIZADO
                          FROM    TBI_PROCESO_CLIENTE TPO
                               INNER JOIN
                                  TCA_PROCESO_BPM TPB
                               ON (TPO.ID_PROCESO = TPB.ID)
                         WHERE BAN_ES_PROCESO = 1
                               AND BAN_ES_FIN_ACTIVIDAD = 1) FIN
          ON (INICIO.ID = FIN.ID_INICIO)
       INNER JOIN TCA_PROCESO_BPM TPB
          ON (TPB.ID = INICIO.ID_PROCESO)
ORDER BY 1 DESC