/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sistemabancariolpoo1.trabalho_final_java_lpoo1;

/**
 *
 * @author rafae
 */
public class CPFValidator {
    public static boolean isCPF(String cpf) {
        cpf = cpf.replaceAll("\\D", "");

        if (cpf.length() != 11) {
            return false;
        }

        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        try {
            int sum1 = 0, sum2 = 0;
            int weight1 = 10, weight2 = 11;

            for (int i = 0; i < 9; i++) {
                int digit = Integer.parseInt(cpf.substring(i, i + 1));
                sum1 += digit * weight1--;
                sum2 += digit * weight2--;
            }

            int firstCheckDigit = 11 - (sum1 % 11);
            if (firstCheckDigit >= 10) {
                firstCheckDigit = 0;
            }

            sum2 += firstCheckDigit * 2;

            int secondCheckDigit = 11 - (sum2 % 11);
            if (secondCheckDigit >= 10) {
                secondCheckDigit = 0;
            }

            return cpf.substring(9, 10).equals(String.valueOf(firstCheckDigit))
                && cpf.substring(10).equals(String.valueOf(secondCheckDigit));
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
