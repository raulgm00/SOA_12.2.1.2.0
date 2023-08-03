create or replace PACKAGE CFG_K_BPM AS 

  PROCEDURE obtener_tareas_producto (
        p_idProducto      IN    NUMBER,
        p_idProceso     IN    NUMBER,
        rs_tareas   OUT   SYS_REFCURSOR,
        p_codigo_res     OUT   NUMBER,
        p_mensaje        OUT   VARCHAR2
    );

END CFG_K_BPM;