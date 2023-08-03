create or replace PACKAGE BODY CFG_K_BPM AS

    PROCEDURE obtener_tareas_producto (
        p_idproducto   IN    NUMBER,
        p_idproceso    IN    NUMBER,
        rs_tareas      OUT   SYS_REFCURSOR,
        p_codigo_res   OUT   NUMBER,
        p_mensaje      OUT   VARCHAR2
    ) AS
    BEGIN
        OPEN rs_tareas FOR SELECT
                                     pt.id_tarea,t.descripcion,t.descripcion_corta, t.id_proceso_bpm
                                FROM
                                    tca_producto_tarea   pt,
                                    tca_tarea_bpm        t
                                WHERE
                                    t.id = pt.id_tarea
                                    AND id_producto = p_idproducto
                                    AND id_proceso_bpm = p_idproceso
                                    AND pt.ban_estatus = 1;

        NULL;
    END obtener_tareas_producto;

END CFG_K_BPM;