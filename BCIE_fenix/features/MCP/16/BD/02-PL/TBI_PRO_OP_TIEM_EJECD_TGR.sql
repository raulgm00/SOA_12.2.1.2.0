CREATE OR REPLACE TRIGGER TBI_PRO_OP_TIEM_EJECD_TGR
   BEFORE INSERT
   ON TBI_PROCESO_OPERACION    FOR EACH ROW
DECLARE
   v_id_inicio         NUMBER;
   v_id_operacion      NUMBER;
   v_id_proceso        NUMBER;
   v_ban_es_proceso    NUMBER;
   v_fecha_fin         NUMBER;
   v_fecha_inicio      NUMBER;
   v_id_fin            NUMBER;
   V_RES_MILES         NUMBER;
   v_tarea             NUMBER;
   v_instancia         VARCHAR2 (50);
   v_instanciaPR       VARCHAR2 (50);
   v_idinstExistente   NUMBER;
BEGIN
   v_id_fin := :NEW.ID;
   v_id_operacion := :NEW.ID_OPERACION;
   v_id_proceso := :NEW.ID_PROCESO;
   v_ban_es_proceso := :NEW.BAN_ES_PROCESO;
   v_tarea := :NEW.ID_TAREA;
   v_instancia := :NEW.INSTANCIA_TAREA;
   v_instanciaPR := :NEW.INSTANCIA_PROCESO;

   IF :NEW.BAN_ES_FIN_ACTIVIDAD = 1 AND v_id_proceso IN (24, 9)
   THEN
      SELECT (EXTRACT (
                 DAY FROM CURRENT_TIMESTAMP
                          - TO_TIMESTAMP (:NEW.FECHA_REGISTRO))
              * 86400000)
             + (EXTRACT (
                   HOUR FROM CURRENT_TIMESTAMP
                             - TO_TIMESTAMP (:NEW.FECHA_REGISTRO))
                * 3600000)
             + (EXTRACT (
                   MINUTE FROM CURRENT_TIMESTAMP
                               - TO_TIMESTAMP (:NEW.FECHA_REGISTRO))
                * 60000)
             + (EXTRACT (
                   SECOND FROM CURRENT_TIMESTAMP
                               - TO_TIMESTAMP (:NEW.FECHA_REGISTRO))
                * (1000))
                AS FECHA_FIN
        INTO v_fecha_fin
        FROM DUAL;

      SELECT ID,
             (EXTRACT (
                 DAY FROM CURRENT_TIMESTAMP - TO_TIMESTAMP (FECHA_REGISTRO))
              * 86400000)
             + (EXTRACT (
                   HOUR FROM CURRENT_TIMESTAMP
                             - TO_TIMESTAMP (FECHA_REGISTRO))
                * 3600000)
             + (EXTRACT (
                   MINUTE FROM CURRENT_TIMESTAMP
                               - TO_TIMESTAMP (FECHA_REGISTRO))
                * 60000)
             + (EXTRACT (
                   SECOND FROM CURRENT_TIMESTAMP
                               - TO_TIMESTAMP (FECHA_REGISTRO))
                * (1000))
                AS FECHA_INICIO
        INTO v_id_inicio, v_fecha_inicio
        FROM TBI_PROCESO_OPERACION
       WHERE     BAN_FINALIZADO = 0
             AND BAN_ES_FIN_ACTIVIDAD = 0
             AND ID_OPERACION = v_id_operacion                        --500010
             AND ID_PROCESO = v_id_proceso                                 --1
             AND BAN_ES_PROCESO = v_ban_es_proceso
             AND NVL (ID_TAREA, 000) = NVL (v_tarea, NVL (ID_TAREA, 000))
             AND ID <> v_id_fin
             AND ID_PROCESO in ( 24, 9)
             AND INSTANCIA_PROCESO = v_instanciaPR;

      V_RES_MILES := (v_fecha_inicio - v_fecha_fin);



      IF v_ban_es_proceso = 0
      THEN                                              --- 0 es igual a Tarea
         UPDATE TBI_PROCESO_OPERACION
            SET TIEMPO_TAREA = V_RES_MILES, BAN_FINALIZADO = 1
          WHERE ID IN (v_id_inicio);

         :NEW.TIEMPO_TAREA := V_RES_MILES;

         :NEW.BAN_FINALIZADO := 1;
         :NEW.ID_INICIO := v_id_inicio;
      ELSIF v_ban_es_proceso = 1
      THEN                                           --1 corresponde a proceso
         UPDATE TBI_PROCESO_OPERACION
            SET TIEMPO_PROCESO = V_RES_MILES, BAN_FINALIZADO = 1
          WHERE ID IN (v_id_inicio);

         :NEW.TIEMPO_PROCESO := V_RES_MILES;

         :NEW.BAN_FINALIZADO := 1;
         :NEW.ID_INICIO := v_id_inicio;
      END IF;



  
   END IF;



   IF :NEW.BAN_ES_FIN_ACTIVIDAD = 0 AND v_id_proceso IN (24, 9)
   THEN
      SELECT NOMBRE_PROCESO,
             USUARIO,
             NOMBRE_USUARIO,
             STRING01,
             STRING02,
             STRING03
        INTO :NEW.NOMBRE_PROCESO,
             :NEW.USUARIO,
             :NEW.NOMBRE_USUARIO,
             :NEW.STRING01,
             :NEW.STRING02,
             :NEW.STRING03
        FROM TBI_PROCESO_OPERACION
       WHERE     BAN_FINALIZADO = 0
             AND BAN_ES_FIN_ACTIVIDAD = 0
             AND ID_OPERACION = v_id_operacion                        --500010
             AND ID_PROCESO IN (24, 9)                                  --1
             AND BAN_ES_PROCESO = 1
             AND INSTANCIA_PROCESO IS NULL
             AND BAN_ES_FIN_ACTIVIDAD = 0
             AND ID_TAREA IS NULL;

      DELETE  FROM TBI_PROCESO_OPERACION 
      WHERE     BAN_FINALIZADO = 0
          AND BAN_ES_FIN_ACTIVIDAD = 0
             AND ID_OPERACION = v_id_operacion                        --500010
             AND ID_PROCESO IN (24, 9)                                         --1
            AND BAN_ES_PROCESO = 1
             AND INSTANCIA_PROCESO IS NULL
            AND BAN_ES_FIN_ACTIVIDAD = 0
            AND ID_TAREA IS NULL;
   END IF;
EXCEPTION
   WHEN NO_DATA_FOUND
   THEN
      DBMS_OUTPUT.PUT_LINE ('No existen Datos ');
END;
/