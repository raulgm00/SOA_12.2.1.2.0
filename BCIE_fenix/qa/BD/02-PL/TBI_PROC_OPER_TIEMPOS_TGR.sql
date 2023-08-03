create or replace TRIGGER FENIX.TBI_PROC_OPER_TIEMPOS_TGR
   BEFORE INSERT
   ON FENIX.TBI_PROCESO_OPERACION    FOR EACH ROW
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

   IF v_id_proceso NOT IN (24, 9)
   THEN
      IF :NEW.BAN_ES_FIN_ACTIVIDAD = 1
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
                    DAY FROM CURRENT_TIMESTAMP
                             - TO_TIMESTAMP (FECHA_REGISTRO))
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
                AND ID_OPERACION = v_id_operacion                     --500010
                AND ID_PROCESO = v_id_proceso                              --1
                AND BAN_ES_PROCESO = v_ban_es_proceso
                AND NVL (ID_TAREA, 000) = NVL (v_tarea, NVL (ID_TAREA, 000))
                AND ID <> v_id_fin
				AND NVL (INSTANCIA_PROCESO,000) = v_instanciaPR
                AND ROWNUM = 1;

         V_RES_MILES := (v_fecha_inicio - v_fecha_fin);

         IF v_ban_es_proceso = 0
         THEN                                           --- 0 es igual a Tarea
            UPDATE TBI_PROCESO_OPERACION
               SET TIEMPO_TAREA = V_RES_MILES, BAN_FINALIZADO = 1
             WHERE ID IN (v_id_inicio);

            :NEW.TIEMPO_TAREA := V_RES_MILES;

            :NEW.BAN_FINALIZADO := 1;
         ELSIF v_ban_es_proceso = 1
         THEN                                        --1 corresponde a proceso
            UPDATE TBI_PROCESO_OPERACION
               SET TIEMPO_PROCESO = V_RES_MILES, BAN_FINALIZADO = 1
             WHERE ID IN (v_id_inicio);

            :NEW.TIEMPO_PROCESO := V_RES_MILES;

            :NEW.BAN_FINALIZADO := 1;
         END IF;

         :NEW.ID_INICIO := v_id_inicio;
      ELSE
         :NEW.ID_INICIO := NULL;
         DBMS_OUTPUT.PUT_LINE ('Dato de inicio ');

         IF :NEW.BAN_ES_FIN_ACTIVIDAD = 0
            AND:NEW.ID_PROCESO NOT IN (26, 25, 24) ---  Se coloca una excepción a la regla ;) procesos que inician más de una ves
         THEN
            SELECT ID
              INTO v_idinstExistente
              FROM TBI_PROCESO_OPERACION
             WHERE     BAN_FINALIZADO = 0
                   AND BAN_ES_FIN_ACTIVIDAD = 0
                   AND ID_OPERACION = v_id_operacion                  --500010
                   AND ID_PROCESO = v_id_proceso                           --1
                   AND BAN_ES_PROCESO = v_ban_es_proceso
                   AND ID_TAREA = v_tarea
                   AND INSTANCIA_TAREA = v_instancia
                   AND ROWNUM = 1;

            UPDATE TBI_PROCESO_OPERACION
               SET BAN_FINALIZADO = 1
             WHERE ID = v_idinstExistente;
         END IF;
      END IF;
   END IF;
EXCEPTION
   WHEN NO_DATA_FOUND
   THEN
      DBMS_OUTPUT.PUT_LINE ('No existen Datos ');
END;