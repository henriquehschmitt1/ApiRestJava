package com.zup.validators;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Service
public class Validators {
	public char calculaDigito(String cpf, int peso, int qtdDigitos) {
		char digito;
		int soma, resultado, num;
		soma = 0;

		for (int i=0; i<qtdDigitos; i++) {
	            num = (int)(cpf.charAt(i) - 48);//transforma o caractere 0 no inteiro 0
	            soma = soma + (num * peso);
	            peso--;
	    }
		
		resultado = 11 - (soma % 11);
		if(resultado > 9) {
			digito = '0';
		}else {
			digito = (char)(resultado+48);
		}
		return digito;
	}
	
	public boolean validaCpf(String cpf) {
		char digito10 = calculaDigito(cpf, 10, 9);
		char digito11 = calculaDigito(cpf, 11, 10);
		return (digito10 == cpf.charAt(9)) && (digito11 == cpf.charAt(10));
	}
    
	
	final static String DATE_FORMAT = "dd/MM/yyyy";

	public boolean validaData(String date) {
		try {
			DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
			dateFormat.parse(date);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
