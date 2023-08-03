
CREATE OR REPLACE PACKAGE       PKG_ENVIAR_CORREO
IS
   PROCEDURE P_CONSTRUIR_CORREO (P_ID_PLATILLA     IN     NUMBER,
                                 P_ID_OPERACION    IN     NUMBER,
                                 P_ID_CLIENTE      IN     NUMBER,
                                 P_TAGS            IN     T_TAGS,
                                 P_ASUNTO          IN OUT VARCHAR2,
                                 P_MENSAJE         IN OUT VARCHAR2,
                                 P_DESTINATARIOS   IN OUT VARCHAR2,
                                 P_CC              IN OUT VARCHAR2);
                                 

PROCEDURE P_CONSULTAR_ROLES_USUARIOS (P_DESCRIPCION_PLANTILLA   IN     VARCHAR2,
                                      P_ID_OPERACION    IN     NUMBER,
                                      P_ID_CLIENTE      IN     NUMBER,
                                      P_ID_PROCESO IN     NUMBER,
                                      P_BANDERAS          IN     T_TAGS,
                                      P_ACCIONES          IN     T_TAGS,
                                      P_PARA_USUARIOS  OUT SYS_REFCURSOR,
                                      P_ERROR_USUARIOS   OUT SYS_REFCURSOR);
                                      
PROCEDURE SP_VALIDAR_ENVIO_SUPERV_TCC (P_ID_SUPERVISION IN NUMBER,
                                       P_DESC_PLANTILLA IN VARCHAR,
                                       P_ID_OPERACION IN NUMBER,
                                       P_ESTADO            OUT VARCHAR2,
                                       P_CODIGO            OUT VARCHAR2,
                                       P_MENSAJE          OUT VARCHAR2);                            
                                      
                                 
END PKG_ENVIAR_CORREO;