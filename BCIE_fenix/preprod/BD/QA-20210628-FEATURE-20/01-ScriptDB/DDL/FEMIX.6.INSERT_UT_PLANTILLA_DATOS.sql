--INSERT XSL FORMULARIO
INSERT INTO bcie.ut_plantilla (codigo, nombre, tipo, contenido) VALUES (
    'FNX_FORDERI_XSL',
    'Transformacion formulario Derivados email F�nix',
    'XSL',
    '<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html"/>
	<xsl:template match="/">
		<xsl:apply-templates select="formulario"/>
	</xsl:template>
	<xsl:template match="formulario">
		<span>
			<table style="border: 0.5px solid #4f81bd; border-spacing: 0px;"  cellpadding="0" cellspacing="0" width="100%">
				<thead>
					<tr>
						<th class="cell-header" width="40%">El contrato contiene</th>
						<th class="cell-header" width="20%">Respuesta (Si/No)</th>
						<th class="cell-header" width="40%">Cl�usula del contrato - Observaciones</th>
					</tr>
				</thead>
				<tbody>
					<xsl:for-each select="detalle">
						<tr>
							<td class="cell">
								<xsl:apply-templates select="PREGUNTA"/>
							</td>
							<td class="cell" align="center">
								<xsl:apply-templates select="RESPUESTA"/>
							</td>
							<td class="cell">
								<xsl:apply-templates select="JUSTIFICACION"/>
							</td>
						</tr>
					</xsl:for-each>
				</tbody>
			</table>
		</span>
	</xsl:template>
</xsl:stylesheet>'
);

--INSERT XQL FORMULARIO
INSERT INTO bcie.ut_plantilla (codigo, nombre, tipo, contenido) VALUES (
    'FNX_FORDERI_SQL',
    'Datos formulario Derivados email F�nix',
    'XQL',
    'Select
xmlSerialize(CONTENT
    xmlElement("formulario" 
      , xmlAgg(
            xmlElement("detalle"
             , xmlForest(
                        ID_PREGUNTA,              
                        PREGUNTA,    
                        RESPUESTA,
                        JUSTIFICACION  
                  )
            )
        )
     )
   AS CLOB INDENT SIZE = 2) 
   as xml
  From (
    SELECT PreguntaEO.ID_PREGUNTA,              
    PreguntaEO.PREGUNTA,              
    PreguntaEO.RESPUESTA,
    PreguntaEO.JUSTIFICACION
    FROM  FENIX.PREGUNTA PreguntaEO
    INNER JOIN FENIX.TRE_PREGUNTA_OPCION TPO ON TPO.ID_PREGUNTA = PreguntaEO.ID_CAT_PREGUNTA
    INNER JOIN FENIX.TCO_PREGUNTA_OPCION_NOTIFICACION TPON ON TPON.ID_TRE_PREGUNTA_OPCION = TPO.ID
    WHERE PreguntaEO.ID_CUESTIONARIO IN (
        SELECT MAX(ID_CUESTIONARIO) FROM FENIX.CUESTIONARIO 
        WHERE id_operacion = :ID_PARAMETRO AND 
        ban_estatus = 1 AND
        ROWNUM = 1 AND ID_TCA_TIPO_CUESTIONARIO = 2) AND
        PreguntaEO.respuesta = TPO.DESC_OPCION)'
);

--INSERT XSL USUARIO REGISTRA LINEA
INSERT INTO bcie.ut_plantilla (codigo, nombre, tipo, contenido) VALUES (
    'FNX_REGLINEA_XSL',
    'Transformacion Ejecutor Registrar Linea F�nix',
    'XSL',
    '<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html"/>
	<xsl:template match="/">
<table  style="border: 0.5px solid #4f81bd; border-spacing: 0px;"  cellpadding="0" cellspacing="0" width="100%">
			<thead>
				<tr>
					<th class="cell-header" width="34%">
						Respondido por
					</th>
					<th class="cell-header" width="33%">
						Area o puesto
					</th>
					<th class="cell-header" width="33%">
						Fecha
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td class="cell" align="center">
							<xsl:apply-templates select="//RESPONSABLE"/>
					</td>
					<td class="cell" align="center">
							<xsl:apply-templates select="//ROL"/>
					</td>
					<td class="cell" align="center">
							<xsl:apply-templates select="//FECHA_FIN"/>
					</td>
				</tr>
			</tbody>
		</table>
	</xsl:template>
</xsl:stylesheet>'
);

--INSERT SQL USUARIO REGISTRA LINEA
INSERT INTO bcie.ut_plantilla (codigo, nombre, tipo, contenido) VALUES (
    'FNX_REGLINEA_SQL',
    'Datos Ejecutor tarea Registrar Linea de Credito',
    'SQL',
    'SELECT TPO.nombre_usuario RESPONSABLE, TRB.DESCRIPCION ROL, TO_CHAR(TPO.FECHA_REGISTRO,'||'''dd/MM/YYYY||'''||') FECHA_FIN
FROM FENIX.tbi_proceso_operacion TPO
INNER JOIN FENIX.TCA_TAREA_BPM TTB
ON TTB.ID = TPO.id_tarea
INNER JOIN F�nix.TCA_ROL_BPM TRB
ON TRB.ID = TTB.ID_ROL_BPM
WHERE
TTB.id_proceso_bpm = 5 AND
TTB.DESCRIPCION_CORTA = '||'''HT10RegistrarDatosLineaCredito'||''' AND
TPO.id_operacion = :ID_PARAMETRO  AND
TPO.BAN_FINALIZADO = 1 AND
TPO.ID_INICIO IS NOT NULL AND
ROWNUM = 1
Order by TPO.FECHA_REGISTRO DESC'
);
