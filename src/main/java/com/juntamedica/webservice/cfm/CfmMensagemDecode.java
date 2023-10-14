package com.juntamedica.webservice.cfm;

public class CfmMensagemDecode {

    private CfmMensagemDecode() {
        throw new IllegalStateException("Classe para utils");
    }

    public static String decodeCodigoErro(Integer codeError) {
        switch (codeError) {
            case 1010 : return "Erro de inicialização do driver de conexão MYSQL.";
            case 1020 : return "Erro de inicialização do driver de conexão ORACLE.";
            case 1030 : return "Erro ao tentar estabelecer conexão com o banco de dados MYSQL.";
            case 1040 : return "Erro ao tentar estabelecer conexão com o banco de dados ORACLE.";
            case 1050 : return "Erro ao tentar fechar a conexão com o banco de dados MYSQL.";
            case 1060 : return "Erro ao tentar fechar a conexão com o banco de dados ORACLE.";
            case 2010 : return "Erro ao realizar a validação da chave de identificação.";
            case 2020 : return "Erro ao tentar identificar o convênio do órgão com o CFM.";
            case 2030 : return "Erro ao gravar o registro de log de acesso.";
            case 2040 : return "Erro ao consultar dados de um médico.";
            case 3010 : return "A chave de acesso informada é inválida.";
            case 4000 : return "O parâmetro UF não foi informado.";
            case 4010 : return "O parâmetro número do CRM não foi informado (igual a 0).";
            case 4020 : return "A chave de identificação não foi informada.";
            case 4030 : return "O número do CPF não foi informado.";
            case 4040 : return "A Data de nascimento não foi informada.";
            case 8101 : return "Médico não encontrado.";
            default : return "Erro não identificado no retorno da consulta do médico no CFM.";
        }
    }

    public static String decodeSituacao(String situacao) {
        switch (situacao) {
            case "A": return "Regular";
            case "B": return "Suspensão parcial permanente";
            case "C": return "Cassado";
            case "E": return "Inoperante";
            case "F": return "Falecido";
            case "G": return "Sem o exercício da profissão na UF";
            case "I": return "Interdição cautelar - total";
            case "J": return "Suspenso por ordem judicial - parcial";
            case "L": return "Cancelado";
            case "M": return "Suspensão total temporária";
            case "N": return "Interdição cautelar - parcial";
            case "O": return "Suspenso por ordem judicial - total";
            case "P": return "Aposentado";
            case "R": return "Suspensão temporária";
            case "S": return "Suspenso - total";
            case "T": return "Transferido";
            case "X": return "Suspenso - parcial";
            default : return "Valor não identificado no retorno da situação do médico no CFM.";
        }
    }

    public static String decodeTipoInscricao(String tipoInscricao) {
        switch (tipoInscricao) {
            case "P": return "Principal";
            case "S": return "Secundária";
            case "V": return "Provisória";
            case "T": return "Temporária";
            case "E": return "Estudando Méd. Estrangeiro";
            default : return "Valor não identificado no retorno do Tipo de Inscrição do médico no CFM.";
        }
    }
}
