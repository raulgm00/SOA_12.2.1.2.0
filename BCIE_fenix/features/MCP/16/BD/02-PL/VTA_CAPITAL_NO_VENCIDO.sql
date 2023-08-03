

	CREATE OR REPLACE VIEW VTA_CAPITAL_NO_VENCIDO AS 

		SELECT calc.Contract_Ref_No, calc.basis_amount,VCO.OPERACION
		FROM middle.fc_v_Contract_ICCF_Calc calc, VTA_CONTRATO_DESEMBOLSO VCO
        WHERE calc.Contract_Ref_No = VCO.NUMERO_CONTRATO
        AND calc.Component LIKE '%INT'
        AND calc.start_date = (
			SELECT MAX(calc2.start_date)
            FROM middle.fc_v_Contract_ICCF_Calc calc2, middle.fc_v_Fecha_Sistema  
            WHERE calc2.contract_ref_no = calc.contract_ref_no 
            AND calc2.component = calc.component
			--  and calc2.start_date<NVL(:P_F_PREPAGO,calc.start_date)
            AND calc2.schedule_date > today
		);
	/