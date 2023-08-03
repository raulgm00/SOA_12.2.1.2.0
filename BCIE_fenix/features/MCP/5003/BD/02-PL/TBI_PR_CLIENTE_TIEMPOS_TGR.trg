
CREATE OR REPLACE TRIGGER TBI_PR_CLIENTE_TIEMPOS_TGR
   BEFORE INSERT
 ON FENIX.TBI_PROCESO_CLIENTE    FOR EACH ROW
DECLARE
   v_id_inicio        NUMBER;
   v_id_cliente       NUMBER;
   v_id_proceso       NUMBER;
   v_ban_es_proceso   NUMBER;
   v_fecha_fin        NUMBER;
   v_fecha_inicio     NUMBER;
   v_id_fin           NUMBER;
   V_RES_MILES        NUMBER;
   v_tarea 			  NUMBER;
   v_instancia 		  VARCHAR2 (50);
   v_instanciaPR      VARCHAR2 (50);
   v_idinstExistente number;
BEGIN
   v_id_fin := :NEW.ID;
   v_id_cliente := :NEW.ID_CLIENTE;
   v_id_proceso := :NEW.ID_PROCESO;
   v_ban_es_proceso := :NEW.BAN_ES_PROCESO;
   v_tarea:=:NEW.ID_TAREA;
   v_instancia:=:NEW.INSTANCIA_TAREA;
   v_instanciaPR := :NEW.INSTANCIA_PROCESO;

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
        FROM TBI_PROCESO_CLIENTE
       WHERE     BAN_FINALIZADO = 0
			AND BAN_ES_FIN_ACTIVIDAD = 0
            AND ID_CLIENTE = v_id_cliente                        --500010
            AND ID_PROCESO = v_id_proceso                                 --1
            AND BAN_ES_PROCESO = v_ban_es_proceso
            AND nvl (ID_TAREA, 000)=nvl(v_tarea, nvl(ID_TAREA,000))
            AND ID <> v_id_fin 
			AND NVL (INSTANCIA_PROCESO,000) = v_instanciaPR
			AND ROWNUM = 1;

      V_RES_MILES := (v_fecha_inicio - v_fecha_fin);

      IF v_ban_es_proceso = 0
      THEN                                              --- 0 es igual a Tarea
         UPDATE TBI_PROCESO_CLIENTE
            SET TIEMPO_TAREA = V_RES_MILES, BAN_FINALIZADO = 1
          WHERE ID IN (v_id_inicio);

         :NEW.TIEMPO_TAREA := V_RES_MILES;

         :NEW.BAN_FINALIZADO := 1;
      ELSIF v_ban_es_proceso = 1
      THEN                                           --1 corresponde a proceso
         UPDATE TBI_PROCESO_CLIENTE
            SET TIEMPO_PROCESO = V_RES_MILES, BAN_FINALIZADO = 1
          WHERE ID IN (v_id_inicio);

         :NEW.TIEMPO_PROCESO := V_RES_MILES;

         :NEW.BAN_FINALIZADO := 1;
      END IF;
      :NEW.ID_INICIO := v_id_inicio;
   ELSE
   
    :NEW.ID_INICIO := null;
      DBMS_OUTPUT.PUT_LINE ('Dato de inicio ');
      
	  /*
      IF   :NEW.BAN_ES_FIN_ACTIVIDAD = 0
      THEN
       SELECT ID  into v_idinstExistente
       FROM TBI_PROCESO_CLIENTE
       where    BAN_FINALIZADO = 0
             AND BAN_ES_FIN_ACTIVIDAD = 0
             AND ID_CLIENTE = v_id_cliente                        --500010
             AND ID_PROCESO = v_id_proceso                                 --1
             AND BAN_ES_PROCESO = v_ban_es_proceso
            AND ID_TAREA=v_tarea
			AND INSTANCIA_TAREA=v_instancia
			AND ROWNUM = 1;
       
          UPDATE TBI_PROCESO_CLIENTE
            SET BAN_FINALIZADO = 1
          WHERE ID =v_idinstExistente;
   
       END IF;
	   */
   END IF;

   
EXCEPTION
   WHEN NO_DATA_FOUND
   THEN
      DBMS_OUTPUT.PUT_LINE ('No existen Datos ');
END;
/